package com.inspur.ussdplate;

import com.inspur.ussdplate.message.ChargeMsg;
import com.inspur.ussdplate.message.SendMsgRecord;
import com.inspur.ussdplate.message.SessionMsg;
import com.inspur.ussdplate.message.SwitchMsg;
import com.inspur.ussdplate.message.USSDAbortMessage;
import com.inspur.ussdplate.message.USSDActiveTestMessage;
import com.inspur.ussdplate.message.USSDBeginMessage;
import com.inspur.ussdplate.message.USSDBindMessage;
import com.inspur.ussdplate.message.USSDBindRespMessage;
import com.inspur.ussdplate.message.USSDChargeMessage;
import com.inspur.ussdplate.message.USSDContinueMessage;
import com.inspur.ussdplate.message.USSDHead;
import com.inspur.ussdplate.message.USSDSwitchBeginMessage;
import com.inspur.ussdplate.message.USSDSwitchMessage;
import com.inspur.ussdplate.ussdpara.USSDCommand;
import com.inspur.ussdplate.ussdpara.USSDMonitorPara;
import com.inspur.ussdplate.ussdpara.USSDPara;
import com.inspur.ussdplate.util.Tools;
import java.io.*;
import java.net.Socket;
import java.util.Date;
import ljwf.Log;


public class USSD
{

	private static USSD _instance;
	private USSDPara up;
	private SocketManager spm;
	private Socket s;
	private int sendOut;

	public USSD()
	{
		up = USSDPara.getInstance();
		spm = SocketManager.getInstance();
		s = null;
		sendOut = 1;
	}

	public static synchronized USSD getInstance()
	{
		if (_instance == null)
			_instance = new USSD();
		return _instance;
	}

	public static String displayErrMsg(Throwable t){
		String s=t.getClass()+":"+t.getLocalizedMessage();
		StackTraceElement[] elements=t.getStackTrace();
		for(int i=0;i<elements.length;i++){
			StackTraceElement ele=elements[i];
			s+="\r\n\tat "+ele.getClassName()+"."+ele.getMethodName()+"("+ele.getFileName()+":"+ele.getLineNumber()+")";
		}
		return s;
		
	}
	
	public int connect()
	{
		int stat = 0;
		try
		{
			USSDBindMessage ubm = new USSDBindMessage();
			ubm.setSendID(-1);
			ubm.nReceiceID = -1;
			ubm.stat = 0;
			ubm.System_ID = up.getIcpID();
			ubm.Password = up.getIpcAuth();
			ubm.Version = up.getIsmgVer();
			Log.info((new StringBuilder("System_ID:")).append(ubm.System_ID).toString());
			Log.info((new StringBuilder("ubm.Password:")).append(ubm.Password).toString());
			stat = login(ubm);
			if (stat != 0)
			{
				Log.info((new StringBuilder("登录网关发生错误,resp stat:")).append(stat).toString());
			} else
			{
				Log.info((new StringBuilder("登录网关成功")).append(Thread.currentThread().getName()).toString());
				USSDMonitorPara.isLogin = true;
			}
		}
		catch (Exception ex)
		{
			stat = -15;
			Log.info((new StringBuilder("登录USSD发生错误")).append(Thread.currentThread().getName()).toString());
			USSDMonitorPara.isLogin = false;
			Log.info("connect set:"+USSDMonitorPara.isLogin);
			Log.info(ex.getLocalizedMessage());
			
		}
		return stat;
	}

	public int login(USSDBindMessage msg)
		throws IOException, Exception
	{
		Log.info("登录login");
		int stat = 0;
		byte bmsg[] = new byte[100];
		String tmp = "0";
		System.arraycopy(Tools.int2Hbyte(msg.nCommandID), 0, bmsg, 4, 4);
		System.arraycopy(Tools.int2Hbyte(msg.stat), 0, bmsg, 8, 4);
		System.arraycopy(Tools.int2Hbyte(msg.nSendID), 0, bmsg, 12, 4);
		System.arraycopy(Tools.int2Hbyte(msg.nReceiceID), 0, bmsg, 16, 4);
		int length = 20;
		System.arraycopy(up.getIcpID().getBytes(), 0, bmsg, length, up.getIcpID().getBytes().length);
		length += up.getIcpID().getBytes().length;
		length = (length + 11) - up.getIcpID().getBytes().length;
		System.arraycopy(up.getIpcAuth().getBytes(), 0, bmsg, length, up.getIpcAuth().getBytes().length);
		length += up.getIpcAuth().getBytes().length;
		length = (length + 9) - up.getIpcAuth().getBytes().length;
		System.arraycopy(msg.System_Type.getBytes(), 0, bmsg, length, msg.System_Type.getBytes().length);
		length += msg.System_Type.getBytes().length;
		length = (length + 13) - msg.System_Type.getBytes().length;
		System.arraycopy(Tools.int2Hbyte(up.getIsmgVer()), 0, bmsg, length, 4);
		length += 4;
		msg.nMsgSize = length;
		System.arraycopy(Tools.int2Hbyte(msg.nMsgSize), 0, bmsg, 0, 4);
		byte send[] = new byte[length];
		System.arraycopy(bmsg, 0, send, 0, length);
		s = spm.getSocket();
		send(send);
		USSDHead head = Deliver();
		if (head.nCommandID == 103)
		{
			stat = head.stat;
		} else
		{
			Log.error("标准：登录连接响应发生错误！");
			stat = -13;
		}
		Log.info("103 stat:"+stat);
		if (stat != 0)
			close();
//		Log.info("标准：登录连接响应发生错误！");
		return stat;
	}

	public void logout()
	{
		USSDHead logout = new USSDHead();
		logout.nReceiceID = 0;
		logout.nCommandID = 102;
		logout.nMsgSize = 20;
		logout.stat = 0;
		try
		{
			sendHead(logout);
		}
		catch (Exception exception) { }
		close();
	}

	public int session(SessionMsg smsg, int flag)
	{
		if (smsg == null)
		{
			return -4;
		} else
		{
			USSDBeginMessage ubm = new USSDBeginMessage(smsg, flag);
			return 0;
		}
	}

	public int ussdswitch(SwitchMsg smsg)
	{
		if (smsg == null)
		{
			return -16;
		} else
		{
			USSDSwitchMessage uscm = new USSDSwitchMessage(smsg);
			return 0;
		}
	}

	public int charge(ChargeMsg cmsg)
	{
		if (cmsg == null)
		{
			return -19;
		} else
		{
			USSDChargeMessage ucm = new USSDChargeMessage(cmsg);
			return 0;
		}
	}

	public int submitSession(SessionMsg msg, int flag)
	{
		if (msg == null)
			return -4;
		try
		{
			USSDBeginMessage ubm = new USSDBeginMessage(msg, flag);
			ubm.setSendID(msg.getSendout());
			ubm.setReceiceID(msg.getReceout());
//			Log.info((new StringBuilder("ussd send id :")).append(ubm.nSendID).toString());
//			Log.info((new StringBuilder("ussd rece id :")).append(ubm.nReceiceID).toString());
//			Log.info((new StringBuilder("ussd command :")).append(ubm.nCommandID).toString());
			sendSession(ubm, flag);
		}
		catch (Exception e)
		{
			close();
			Log.info("submitSession:"+e.getLocalizedMessage());
		}
		return 0;
	}

	public int submitSwitch(SwitchMsg msg)
	{
		if (msg == null)
			return -16;
		try
		{
			USSDSwitchMessage usm = new USSDSwitchMessage(msg);
			sendSwitch(usm);
		}
		catch (Exception e)
		{
			close();
		}
		return 0;
	}

	public int submitCharge(ChargeMsg msg)
	{
		if (msg == null)
			return -19;
		try
		{
			USSDChargeMessage ucm = new USSDChargeMessage(msg);
			sendCharge(ucm);
		}
		catch (Exception e)
		{
			close();
		}
		return 0;
	}

	public int sendSession(USSDBeginMessage ubm, int flag)
		throws IOException
	{
		int stat = 0;
		byte sendMsg[] = (byte[])null;
		try
		{
			sendMsg = getSessionMsg(ubm, flag);
		}
		catch (Exception e)
		{
			return -5;
		}
		try
		{
			byte send[] = new byte[20 + sendMsg.length];
			ubm.nMsgSize = 20 + sendMsg.length;
//			Log.info((new StringBuilder("ubm.nMsgSize1111111111:")).append(ubm.nMsgSize).toString());
			System.arraycopy(Tools.int2Hbyte(ubm.nMsgSize), 0, send, 0, 4);
			System.arraycopy(Tools.int2Hbyte(ubm.nCommandID), 0, send, 4, 4);
//			Log.info((new StringBuilder("ubm.nCommandID:")).append(ubm.nCommandID).toString());
			System.arraycopy(Tools.int2Hbyte(ubm.stat), 0, send, 8, 4);
			System.arraycopy(Tools.int2Hbyte(ubm.nSendID), 0, send, 12, 4);
			System.arraycopy(Tools.int2Hbyte(ubm.nReceiceID), 0, send, 16, 4);
			System.arraycopy(sendMsg, 0, send, 20, sendMsg.length);
			SendMsgRecord msgRecord = new SendMsgRecord();
			msgRecord.setSndTime(new Date());
			send(send);
//			System.out.println("发送完毕");
		}
		catch (IOException e)
		{
			Log.info("sendSession:"+e.getLocalizedMessage());
			return 38183;
		}
		return stat;
	}

	public int sendSwitch(USSDSwitchMessage usm)
		throws IOException
	{
		int stat = 0;
		byte sendMsg[] = (byte[])null;
		try
		{
			sendMsg = getSwitchMsg(usm);
		}
		catch (Exception e)
		{
			return -17;
		}
		try
		{
			byte send[] = new byte[20 + sendMsg.length];
			usm.nMsgSize = 20 + sendMsg.length;
			System.arraycopy(Tools.int2Hbyte(usm.nMsgSize), 0, send, 0, 4);
			System.arraycopy(Tools.int2Hbyte(usm.nCommandID), 0, send, 4, 4);
			System.arraycopy(Tools.int2Hbyte(usm.stat), 0, send, 8, 4);
			System.arraycopy(Tools.int2Hbyte(usm.nSendID), 0, send, 12, 4);
			System.arraycopy(Tools.int2Hbyte(usm.nReceiceID), 0, send, 16, 4);
			System.arraycopy(usm, 0, send, 20, sendMsg.length);
			SendMsgRecord msgRecord = new SendMsgRecord();
			msgRecord.setSendID(usm.nSendID);
			msgRecord.setMsg(send);
			msgRecord.setSndTime(new Date());
			send(send);
		}
		catch (IOException e)
		{
			return 38183;
		}
		return stat;
	}

	public int sendCharge(USSDChargeMessage ucm)
		throws IOException
	{
		int stat = 0;
		byte sendMsg[] = (byte[])null;
		try
		{
			sendMsg = getChargeMsg(ucm);
		}
		catch (Exception e)
		{
			return -20;
		}
		try
		{
			byte send[] = new byte[20 + sendMsg.length];
			ucm.nMsgSize = 20 + sendMsg.length;
			System.arraycopy(Tools.int2Hbyte(ucm.nMsgSize), 0, send, 0, 4);
			System.arraycopy(Tools.int2Hbyte(ucm.nCommandID), 0, send, 4, 4);
			System.arraycopy(Tools.int2Hbyte(ucm.stat), 0, send, 8, 4);
			System.arraycopy(Tools.int2Hbyte(ucm.nSendID), 0, send, 12, 4);
			System.arraycopy(Tools.int2Hbyte(ucm.nReceiceID), 0, send, 16, 4);
			System.arraycopy(ucm, 0, send, 20, sendMsg.length);
			SendMsgRecord msgRecord = new SendMsgRecord();
			msgRecord.setSendID(ucm.nSendID);
			msgRecord.setMsg(send);
			msgRecord.setSndTime(new Date());
			send(send);
		}
		catch (IOException e)
		{
			sendOut--;
			return 38183;
		}
		return stat;
	}

	public boolean sendEnquireLink()
	{
		USSDActiveTestMessage uatm = new USSDActiveTestMessage();
		sendOut = uatm.setSendID(sendOut);
		try
		{
			sendHead(uatm);
		}
		catch (IOException e)
		{
			sendOut--;
			return false;
		}
		return true;
	}

	public byte[] getSessionMsg(USSDBeginMessage ubm, int flag)
		throws Exception
	{
		byte msg[] = new byte[1024];
		byte content[] = (byte[])null;
		int length = 0;
		msg[0] = (byte)ubm.Version;
		length++;
		msg[1] = (byte)ubm.Op_type;
		length++;
		System.arraycopy(ubm.Msisdn.getBytes(), 0, msg, 2, ubm.Msisdn.getBytes().length);
		length += ubm.Msisdn.getBytes().length;
		length += 21 - ubm.Msisdn.getBytes().length;
		System.arraycopy(ubm.Service_code.getBytes(), 0, msg, length, ubm.Service_code.getBytes().length);
		if (flag != 0)
			length = length + ubm.Service_code.getBytes().length + 1;
		else
			length += 4;
		msg[length] = (byte)ubm.Msg_fmt;
		length++;
		try
		{
			content = ubm.Content.getBytes();
		}
		catch (Exception ex)
		{
			Log.error((new StringBuilder("转换会话内容为utf8格式出错:")).append(ex.toString()).toString());
		}
		System.arraycopy(content, 0, msg, length, content.length);
		length += content.length;
		byte sessionMsg[] = new byte[length];
		System.arraycopy(msg, 0, sessionMsg, 0, length);
//		for (int i = 0; i < sessionMsg.length; i++)
//			System.out.println(sessionMsg[i]);

		return sessionMsg;
	}

	public byte[] getSwitchMsg(USSDSwitchMessage usm)
		throws Exception
	{
		byte msg[] = new byte[1024];
		byte content[] = (byte[])null;
		int length = 0;
		msg[0] = (byte)usm.Switch_Mode;
		length++;
		System.arraycopy(usm.Msisdn.getBytes(), 0, msg, 1, usm.Msisdn.getBytes().length);
		length += 21;
		System.arraycopy(usm.Org_Service_code.getBytes(), 0, msg, 23, usm.Org_Service_code.getBytes().length);
		length += 21;
		System.arraycopy(usm.Dest_Service_code.getBytes(), 0, msg, 43, usm.Dest_Service_code.getBytes().length);
		length += 21;
		try
		{
			content = usm.Content.getBytes();
		}
		catch (Exception ex)
		{
			Log.error((new StringBuilder("转换转移内容为utf8格式出错:")).append(ex.toString()).toString());
		}
		System.arraycopy(content, 0, msg, length, content.length);
		length += content.length;
		byte sessionMsg[] = new byte[length];
		System.arraycopy(msg, 0, sessionMsg, 0, length);
		return sessionMsg;
	}

	public byte[] getChargeMsg(USSDChargeMessage ucm)
		throws Exception
	{
		byte msg[] = new byte[1024];
		byte content[] = (byte[])null;
		int length = 0;
		System.arraycopy(Tools.int2Hbyte(ucm.Charge_Ratio), 0, msg, 0, 4);
		length += 4;
		System.arraycopy(Tools.int2Hbyte(ucm.Charge_Type), 0, msg, 4, 4);
		length += 4;
		System.arraycopy(ucm.Charge_Resource.getBytes(), 0, msg, 8, ucm.Charge_Resource.getBytes().length);
		length += 21;
		msg[length] = (byte)ucm.Charge_Location;
		byte chargebyte[] = new byte[30];
		System.arraycopy(msg, 0, chargebyte, 0, length);
		return chargebyte;
	}

	public USSDHead Deliver()
		throws InterruptedIOException, IOException
	{
		USSDHead head = new USSDHead();
		byte bMsg[] = (byte[])null;
		int i = 0;
//label0:
//		do
//		{
//label1:
//			do
//				try
//				{
//					bMsg = read(head);
//					break label1;
//				}
//				catch (InterruptedIOException ex)
//				{
//					Log.info("deliver 1:"+ex.getLocalizedMessage());
//					if (i == 3)
//					{
//						head.stat = -9;
//						break label0;
//					}
//					i++;
//				}
//				catch (IOException ex)
//				{
//					//Log.info("deliver 2:"+ex.getLocalizedMessage());
//					Log.info("deliver 2:\r\n"+USSD.displayErrMsg(ex));
//					throw ex;
//				}
//			while (true);
//			try
//			{
//				head = dealMsg(head, bMsg);
//			}
//			catch (IOException ex)
//			{
//				Log.info("deliver 3:"+ex.getLocalizedMessage());
//				throw ex;
//			}
//			catch (Exception ex)
//			{
//				Log.info("deliver 4:"+ex.getLocalizedMessage());
//				Log.error(ex.toString());
//			}
//		} while (head.nCommandID == 132);
		while(true)
        {
        	try {
                bMsg=read(head);
            }catch(InterruptedIOException ex){
            	 if(i==3){
                     head.stat=USSDPara.DELIVER_MSG_TIME_OUT;//由于没有消息而退出－－stat=1
                     break;
                 }else{
                     i++;
                      continue;
                 }
            }catch(IOException ex){
            	Log.info("deliver 2:\r\n"+USSD.displayErrMsg(ex));
            	throw ex;
            }
            try{
            	head = dealMsg(head,bMsg);
            }catch(IOException ex){
            	Log.info("deliver 3:\r\n"+USSD.displayErrMsg(ex));
            	throw ex;
            }
            catch (Exception ex) {
                Log.error(ex.toString());
            }
            if(head.nCommandID == USSDCommand.USSD_ENQUIRE_LINK_RESP){
            	continue;
            }
            break;
        }
		
		return head;
	}

	private USSDHead dealMsg(USSDHead head, byte msg[])
		throws InterruptedIOException, Exception
	{
		USSDHead back = new USSDHead();
		int stat = head.stat;
		switch (head.nCommandID)
		{
		case 104: // 'h'
		case 118: // 'v'
		case 132: 
		default:
			break;

		case 103: // 'g'
			USSDBindRespMessage ubrm = new USSDBindRespMessage();
			if (msg == null)
				stat = -12;
			else
				try
				{
					ubrm.System_ID = Tools.byteToString(msg);
				}
				catch (Exception ex)
				{
					stat = -10;
				}
			ubrm.stat = stat;
			back = ubrm;
			break;

		case 111: // 'o'
			USSDBeginMessage ubm = new USSDBeginMessage();
			stat = getBeginMessage(ubm, msg);
			back.stat = stat;
			back = ubm;
			break;

		case 112: // 'p'
			USSDContinueMessage ucm = new USSDContinueMessage();
			stat = getContinueMessage(ucm, msg);
			back.stat = stat;
			back = ucm;
			break;

		case 114: // 'r'
			USSDAbortMessage uam = new USSDAbortMessage();
			uam.nSendID = head.nSendID;
			uam.nReceiceID = head.nReceiceID;
			back.stat = stat;
			back = uam;
			break;
		}
		back.nCommandID = head.nCommandID;
		back.nSendID = head.nSendID;
		back.nReceiceID = head.nReceiceID;
		back.nMsgSize = head.nMsgSize;
		back.stat = stat;
		return back;
	}

	private int getBeginMessage(USSDBeginMessage msg, byte content[])
		throws Exception
	{
		if (content == null)
			return -4;
		byte version = 0;
		byte op_type = 0;
		byte fmt = 0;
		byte msisdn[] = new byte[21];
		byte servicecode[] = new byte[21];
		byte ussdcon[] = new byte[182];
		try
		{
			version = content[0];
			op_type = content[1];
			System.arraycopy(content, 2, msisdn, 0, 21);
			System.arraycopy(content, 23, servicecode, 0, 4);
			fmt = content[27];
			System.arraycopy(content, 28, ussdcon, 0, content.length - 28);
		}
		catch (Exception e)
		{
			return -5;
		}
		msg.Version = version;
		msg.Op_type = op_type;
		msg.Msisdn = Tools.byteToString(msisdn).trim();
		msg.Service_code = Tools.byteToString(servicecode).trim();
		msg.Msg_fmt = fmt;
		msg.Content = Tools.byteToString(ussdcon).trim();
		int stat = 0;
		return stat;
	}

	private int getContinueMessage(USSDContinueMessage msg, byte content[])
		throws Exception
	{
		if (content == null)
			return -4;
		byte version = 0;
		byte op_type = 0;
		byte fmt = 0;
		byte msisdn[] = new byte[21];
		byte servicecode[] = new byte[21];
		byte ussdcon[] = new byte[182];
		try
		{
			version = content[0];
			op_type = content[1];
			System.arraycopy(content, 2, msisdn, 0, 13);
			System.arraycopy(content, 23, servicecode, 0, 4);
			fmt = content[27];
			System.arraycopy(content, 28, ussdcon, 0, content.length - 28);
		}
		catch (Exception e)
		{
			return -5;
		}
		msg.Version = version;
		msg.Op_type = op_type;
		msg.Msisdn = Tools.byteToString(msisdn).trim();
		msg.Service_code = Tools.byteToString(servicecode).trim();
		msg.Msg_fmt = fmt;
		msg.Content = Tools.byteToString(ussdcon).trim();
		int stat = 0;
		return stat;
	}

	private int getSwitchBeginMessage(USSDSwitchBeginMessage usbm, byte content[])
		throws Exception
	{
		if (content == null)
		{
			Log.error("转移命令字节数组是空！");
			return -16;
		}
		byte version = 0;
		byte op_type = 0;
		byte fmt = 0;
		byte msisdn[] = new byte[21];
		byte orgservicecode[] = new byte[21];
		byte destservicecode[] = new byte[21];
		byte ussdcon[] = new byte[182];
		try
		{
			version = content[0];
			op_type = content[1];
			System.arraycopy(content, 2, msisdn, 0, 21);
			System.arraycopy(content, 23, orgservicecode, 0, 21);
			System.arraycopy(content, 44, destservicecode, 0, 21);
			fmt = content[65];
			System.arraycopy(content, 66, ussdcon, 0, content.length - 66);
		}
		catch (Exception e)
		{
			return -17;
		}
		usbm.Version = version;
		usbm.Op_type = op_type;
		usbm.Msisdn = Tools.byteToString(msisdn).trim();
		usbm.Org_Service_code = Tools.byteToString(orgservicecode).trim();
		usbm.Dest_Service_code = Tools.byteToString(destservicecode).trim();
		usbm.Msg_fmt = fmt;
		usbm.Content = (new String(ussdcon)).trim();
		int stat = 0;
		return stat;
	}

	private byte[] read(USSDHead head)
		throws InterruptedIOException, IOException
	{
		byte resp[] = (byte[])null;
		DataInputStream in;
		in = creatInputStream();
		byte size[] = new byte[4];
		byte commandid[] = new byte[4];
		byte stat[] = new byte[4];
		byte sendid[] = new byte[4];
		byte receid[] = new byte[4];
		in.read(size, 0, 4);
		head.nMsgSize = Tools.byte2int(Tools.byteH2L(size));
//		Log.info((new StringBuilder("receive head.size ")).append(head.nMsgSize).toString());
		in.read(commandid, 0, 4);
		head.nCommandID = Tools.byte2int(Tools.byteH2L(commandid));
		in.read(stat, 0, 4);
		head.stat = Tools.byte2int(Tools.byteH2L(stat));
		in.read(sendid, 0, 4);
		head.nSendID = Tools.byte2int(Tools.byteH2L(sendid));
		in.read(receid, 0, 4);
		head.nReceiceID = Tools.byte2int(Tools.byteH2L(receid));
////		Log.info((new StringBuilder("receive head.commandid ")).append(head.nCommandID).toString()+" "+
//				(new StringBuilder("head.stat ")).append(head.stat).toString()+" "+
//				(new StringBuilder("receive head.nSendID ")).append(head.nSendID).toString()+" "+
//				(new StringBuilder("receive head.nReceiceID ")).append(head.nReceiceID).toString());
		if (head.nMsgSize <= 20)
			return null;
		try
		{
			resp = new byte[head.nMsgSize - 20];
			in.read(resp, 0, head.nMsgSize - 20);
			USSDMonitorPara.setActTime(new Date());
		}
		catch (InterruptedIOException ie)
		{
			Log.info("接收时与网关连接中断，再连接...");
			Log.info((new StringBuilder("read exception:")).append(ie.toString()).toString());
			close();
			USSDMonitorPara.isLogin = false;
			Log.info("read set:"+USSDMonitorPara.isLogin);
			throw ie;
		}
		catch (IOException e)
		{
			Log.info((new StringBuilder("read exception:")).append(e.toString()).toString());
			Log.info("接收时与网关连接中断，再连接...");
			close();
			USSDMonitorPara.isLogin = false;
			Log.info("read set:"+USSDMonitorPara.isLogin);
			throw e;
		}
		return resp;
	}

	private void sendHead(USSDHead head)
		throws IOException
	{
		byte send[] = new byte[20];
		System.arraycopy(Tools.int2Hbyte(head.nMsgSize), 0, send, 0, 4);
		System.arraycopy(Tools.int2Hbyte(head.nCommandID), 0, send, 4, 4);
		System.arraycopy(Tools.int2Hbyte(head.stat), 0, send, 8, 4);
		System.arraycopy(Tools.int2Hbyte(head.nSendID), 0, send, 12, 4);
		System.arraycopy(Tools.int2Hbyte(head.nReceiceID), 0, send, 16, 4);
		send(send);
	}

	public void sendAbort(USSDAbortMessage uam)
	{
		try
		{
			byte send[] = new byte[20];
			System.arraycopy(Tools.int2Hbyte(uam.nMsgSize), 0, send, 0, 4);
			System.arraycopy(Tools.int2Hbyte(uam.nCommandID), 0, send, 4, 4);
			System.arraycopy(Tools.int2Hbyte(uam.stat), 0, send, 8, 4);
			System.arraycopy(Tools.int2Hbyte(uam.nSendID), 0, send, 12, 4);
			System.arraycopy(Tools.int2Hbyte(uam.nReceiceID), 0, send, 16, 4);
			send(send);
		}
		catch (Exception e)
		{
			Log.info((new StringBuilder("发送终止命令:")).append(e.getMessage()).toString());
		}
	}

	private void sendPacket(USSDHead head, byte msg[])
		throws IOException
	{
		byte send[] = new byte[20 + msg.length];
		System.arraycopy(Tools.int2Hbyte(head.nMsgSize), 0, send, 0, 4);
		System.arraycopy(Tools.int2Hbyte(head.nCommandID), 0, send, 4, 4);
		System.arraycopy(Tools.int2Hbyte(head.stat), 0, send, 8, 4);
		System.arraycopy(Tools.int2Hbyte(head.nSendID), 0, send, 12, 4);
		System.arraycopy(Tools.int2Hbyte(head.nReceiceID), 0, send, 16, 4);
		System.arraycopy(msg, 0, send, 20, msg.length);
		send(send);
	}

	public void send(byte msg[])
		throws IOException
	{
		try
		{
			DataOutputStream out = creatOutputStream();
			out.write(msg);
			out.flush();
			USSDMonitorPara.setActTime(new Date());
		}
		catch (IOException e)
		{
			Log.info("send exception:"+e.getLocalizedMessage());
			close();
			
			USSDMonitorPara.isLogin = false;
			Log.info("send set:"+USSDMonitorPara.isLogin);
			Log.info("发送时与网关连接中断，再连接...");
			throw e;
		}
	}

	private DataInputStream creatInputStream()
		throws IOException
	{
		return new DataInputStream(s.getInputStream());
	}

	private DataOutputStream creatOutputStream()
		throws IOException
	{
		return new DataOutputStream(s.getOutputStream());
	}

	public void close()
	{
		spm.freeSocket();
		s = null;
	}
	
	public static void main(String[] args){
		try{
			int i=0;
			int j=1;
			int k=j/i;
		}catch(Exception e){
			System.out.println(displayErrMsg(e));
			e.printStackTrace();
		}
	}
}
