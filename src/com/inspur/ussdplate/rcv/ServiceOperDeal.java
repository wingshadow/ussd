package com.inspur.ussdplate.rcv;

import java.util.Date;

import ljwf.Log;

import com.inspur.ussdplate.db.USSDLog;
import com.inspur.ussdplate.message.SessionMsg;
import com.inspur.ussdplate.message.USSDAbortMessage;
import com.inspur.ussdplate.message.USSDBeginMessage;
import com.inspur.ussdplate.message.USSDContinueMessage;
import com.inspur.ussdplate.message.USSDHead;
import com.inspur.ussdplate.queue.MsgQueue;
import com.inspur.ussdplate.ussdpara.USSDCommand;
import com.inspur.ussdplate.ussdpara.USSDPara;
import com.inspur.utils.InterFaceLog;
import com.inspur.utils.SystemUtil;
import com.webserviceclient.USSDAdapterService.USSDAdapterServiceImplServiceLocator;
import com.webserviceclient.USSDAdapterService.USSDAdapterServiceSoapBindingStub;
import com.webserviceclient.bo.EndUssdRequest;
import com.webserviceclient.bo.HandleUssdRequest;
import com.webserviceclient.bo.HandleUssdResponse;
import com.webserviceclient.bo.NotifyUssdEndRequest;
import com.webserviceclient.bo.UssdContinueRequest;
import com.webserviceclient.bo.UssdContinueResponse;

public class ServiceOperDeal implements Runnable{
	
	private int nCommandID;
	private USSDPara up;
	public USSDHead respMsg;
	public USSDLog ul = new USSDLog();
	public ServiceOperDeal(USSDHead msg){
		up = USSDPara.getInstance();
		this.respMsg = msg;
	}
	
	public void run() {
		Date now = new Date();
		nCommandID = respMsg.nCommandID;
		switch (nCommandID)
		{
		case USSDCommand.USSD_END:	//��ֹ�Ự
			break;
		default:
			break;

		case USSDCommand.USSD_BEGIN:	//��ʼ�Ự
			// ȡ������begin��Ϣ�����ݷ������ȡ��Ӧ��ID������Ӧ��ID��ȡ��Ӧ��WebService��handleUssd����
			// ���Ұ�����begin��Ϣ��д�����ݿ⽻����־
			
			USSDBeginMessage b_msg = (USSDBeginMessage)respMsg;
			String menu = "";
			String id = "";
			
			try
			{
				
				(new InterFaceLog(b_msg.Msisdn, null, null, String.valueOf(b_msg.nSendID), now,
						"beginstart", b_msg.Content,	now)).log();
				
				
				String dealtime = SystemUtil.getDateTime(now);
				String ussdIdentifier = ul.setLog("begin", String.valueOf(b_msg.nSendID), b_msg.Content, b_msg.Msisdn, dealtime, "0007", "u", "msisdn", String.valueOf(b_msg.nSendID), "0");
				id = ussdIdentifier;
				
				
				//���mobile����sp�������
				HandleUssdRequest req = new HandleUssdRequest();
				req.setSenderAddress(b_msg.Msisdn);
				req.setUssdIdentifier(ussdIdentifier);
				req.setUssdMessage(b_msg.Content);

				//����webservice�ͻ��ˣ�ȡ��sp��Ӧ����
				USSDAdapterServiceSoapBindingStub binding = (USSDAdapterServiceSoapBindingStub)(new USSDAdapterServiceImplServiceLocator()).getUSSDAdapterService();
				binding.setTimeout(60000);

				HandleUssdResponse res = binding.handleUssd(req);
				
				if(res.getUssdMessage()!=null&&!res.getUssdMessage().equals("")){
					menu = res.getUssdMessage();
				}else{
					menu = "û�иù��ܲ˵�";
				}
				/***log��ʼ***/
				(new InterFaceLog(b_msg.Msisdn, null, null, String.valueOf(b_msg.nSendID), now,
						"dealbegin", b_msg.Content,	now)).log();
				/***log����***/
				MsgQueue mq = MsgQueue.getInstance();
				SessionMsg msg = new SessionMsg();
				if(menu.equals("*")){
					
					msg.setOptype(3);//�ֻ�������Ӧ
					msg.setContent("�Ժ���ע����ն��ţ����ն�����ʾ����!");
					msg.setFmt(72);
					msg.setMsisdn(b_msg.Msisdn);
					msg.setNCommandID(113);
					msg.setServicecode(b_msg.Service_code);
					msg.setVersion(32);
					msg.setSendout(Integer.parseInt(ussdIdentifier));
					msg.setReceout(b_msg.nSendID);
					mq.addMTMsg(msg);
				}else{
					// ����Ӧ��Ϣд�����ж���
					
					msg.setContent(menu);
					msg.setFmt(72);
					msg.setMsisdn(b_msg.Msisdn);
					msg.setNCommandID(112);
					msg.setOptype(1);
					msg.setReceout(b_msg.nSendID);
					msg.setSendout(Integer.parseInt(ussdIdentifier));
					msg.setServicecode(b_msg.Service_code);
					msg.setVersion(b_msg.Version);
					mq.addMTMsg(msg);
				}
				res = null;
			}
			catch (Exception e)
			{
				if(!id.equals("")){
					//webservice�ͻ��˳�ʱ����������
					MsgQueue mq = MsgQueue.getInstance();
					SessionMsg msg = new SessionMsg();
					msg.setContent("USSD���糬ʱ��ϵͳ�쳣");
					msg.setFmt(72);
					msg.setMsisdn(b_msg.Msisdn);
					msg.setNCommandID(112);
					msg.setOptype(1);
					msg.setReceout(b_msg.nSendID);
					msg.setSendout(Integer.parseInt(id));
					msg.setServicecode(b_msg.Service_code);
					msg.setVersion(b_msg.Version);
					mq.addMTMsg(msg);
				}
				(new InterFaceLog(b_msg.Msisdn, null, null, String.valueOf(b_msg.nSendID), now,
						"USSD_BEGIN", e.getMessage().toString(),now)).log();
//				Log.info((new StringBuilder("USSD USSD_BEGIN ���ս���:")).append(e.getMessage()).toString());
			}
			break;

		case USSDCommand.USSD_CONTINUE:	//�����Ի�
			// ������Ϣͷ��ȡ��Ӧ�öα�ʶ����ȡ��Ӧ��ID�����ҰѸ���Ӧ��IDȡ��Ӧ�ö�webservice��continue����
			// д�뽻����
			// ����Ӧ�ö�webservice����,Ӧ�ö�url
			USSDContinueMessage c_msg = (USSDContinueMessage)respMsg;
			String id2= "";
			
			try
			{
				String ussdIdentifier = String.valueOf(c_msg.nReceiceID);
				id2 = ussdIdentifier;
				String message = c_msg.Content;
				String msisdn = c_msg.Msisdn;

//				Log.info((new StringBuilder("to sp Content :")).append(c_msg.Content).toString());

				
				
				String dealtime = SystemUtil.getDateTime(now);
				//�ж���Ϣ��ȷ
				if (c_msg.stat != 0)
					ul.setLog1(ussdIdentifier, String.valueOf(c_msg.nSendID), "continue", message, msisdn, dealtime, "0007", "", "", String.valueOf(c_msg.nSendID), "1");
				else
					ul.setLog1(ussdIdentifier, String.valueOf(c_msg.nSendID), "continue", message, msisdn, dealtime, "0007", "", "", String.valueOf(c_msg.nSendID), "0");
				//��ϢΪ��
				if (message != null && !"".equals(message))
				{
					USSDPara upara = USSDPara.getInstance();
					String end = upara.getExitkey();
//					System.out.println((new Date()) + " at line 149 mobile "+msisdn.substring(2)+" message is: "+message);
					if (message.equals("*"))
					{
//						System.out.println((new Date()) + " at line 152 mobile "+msisdn.substring(2)+" begin call USSDAdapterService to log quit");
						//*��ʾ�˳�USSDӦ��
						USSDAdapterServiceSoapBindingStub binding = (USSDAdapterServiceSoapBindingStub)(new USSDAdapterServiceImplServiceLocator()).getUSSDAdapterService();
						binding.setTimeout(60000);
						
						EndUssdRequest e_req = new EndUssdRequest();
						e_req.setReciveid(String.valueOf(c_msg.nSendID));
						e_req.setUssdIdentifier(ussdIdentifier);
						
//						System.out.println((new Date()) + " at line 161 mobile "+msisdn.substring(2)+" call USSDAdapterService success! Reciveid: "+c_msg.nSendID+", UssdIdentifier: "+ussdIdentifier);
						
						//�ж���������Ự����false��mobile��������Ự									
						boolean flag = ul.getWhoStart(ussdIdentifier, String.valueOf(c_msg.nSendID));
//						System.out.println((new Date()) + " at line 165 mobile "+msisdn.substring(2)+" flag: " + flag);
						
						/***log��ʼ***/
						(new InterFaceLog(c_msg.Msisdn, null, null, String.valueOf(c_msg.nSendID), now,
								"dealcontinue", c_msg.Content,	now)).log();
						/***log����***/
						
						MsgQueue mq = MsgQueue.getInstance();
						SessionMsg msg = new SessionMsg();
						if (!flag)
						{
							e_req.setUssdMessage("ллʹ���ټ�!");
							msg.setOptype(3);
						} else
						{
							e_req.setUssdMessage("ллʹ���ټ�!");
							msg.setOptype(4);
						}
						msg.setContent("ллʹ���ټ�!");
						msg.setFmt(72);
						msg.setMsisdn(msisdn);
						msg.setNCommandID(113);
						msg.setServicecode(up.getServicecode());
						msg.setVersion(32);
						msg.setSendout(c_msg.nReceiceID);
						msg.setReceout(c_msg.nSendID);
						mq.addMTMsg(msg);
//						System.out.println((new Date()) + " at line 192 mobile "+msisdn.substring(2)+" send byebye info ");
						break;
					}
					
					UssdContinueRequest req = new UssdContinueRequest();
					req.setUssdIdentifier(ussdIdentifier);
					req.setUssdMessage(message);
					req.setSenderAddress(msisdn);
//					System.out.println((new Date()) + " at line 200 mobile "+msisdn.substring(2)+" begin call USSDAdapterService to log other");
//					System.out.println((new Date()) + " at line 201 mobile "+msisdn.substring(2)
//							+" UssdContinueRequest is: ussdIdentifier��"+req.getUssdIdentifier()
//							+", ussdMessage:"+req.getUssdMessage()+", senderAddress:"+req.getSenderAddress());
					USSDAdapterServiceSoapBindingStub binding = (USSDAdapterServiceSoapBindingStub)(new USSDAdapterServiceImplServiceLocator()).getUSSDAdapterService();
					binding.setTimeout(60000);
					UssdContinueResponse res = binding.ussdContinue(req);
//					System.out.println((new Date()) + " at line 207 mobile "+msisdn.substring(2)
//							+" UssdContinueResponse is: ussdMessage��"+res.getUssdMessge());
					/***log��ʼ***/
					(new InterFaceLog(c_msg.Msisdn, null, null, String.valueOf(c_msg.nSendID), now,
							"dealcontinue", c_msg.Content,	now)).log();
					/***log����***/
//					System.out.println((new Date()) + " at line 213 mobile "+msisdn.substring(2)+" call USSDAdapterService success!");
					if (res.getUssdMessge() != null && !"".equals(res.getUssdMessge()))
					{
						MsgQueue mq = MsgQueue.getInstance();
						SessionMsg msg = new SessionMsg();
//						System.out.println((new Date()) + " at line 218 mobile "+msisdn.substring(2)+" msg is: "+res.getUssdMessge());
						if (res.getUssdMessge().equals("*"))
						{
							EndUssdRequest e_req = new EndUssdRequest();
							e_req.setReciveid(String.valueOf(c_msg.nSendID));
							e_req.setUssdIdentifier(ussdIdentifier);
							e_req.setUssdMessage("ллʹ���ټ�!");
							msg.setOptype(3);
							binding.endUssd(e_req, 0);
							msg.setContent("ллʹ���ټ�!");
							msg.setFmt(72);
							msg.setMsisdn(msisdn);
							msg.setNCommandID(113);
							msg.setServicecode(up.getSpNumber());
							msg.setVersion(32);
							msg.setSendout(c_msg.nReceiceID);
							msg.setReceout(c_msg.nSendID);
							mq.addMTMsg(msg);
//							System.out.println((new Date()) + " at line 236 mobile "+msisdn.substring(2)+" send byebye info ");
						}else{
							msg.setContent(res.getUssdMessge());
							msg.setFmt(72);
							msg.setMsisdn(msisdn);
							msg.setNCommandID(112);
							msg.setOptype(1);
							msg.setReceout(c_msg.nSendID);
							msg.setSendout(Integer.parseInt(ussdIdentifier));
							msg.setServicecode(up.getServicecode());
							msg.setVersion(c_msg.Version);
							ul.setLog1(ussdIdentifier, String.valueOf(c_msg.nSendID), "continue", res.getUssdMessge(), msisdn, dealtime, "0007", "", "", String.valueOf(c_msg.nSendID), "0");
							mq.addMTMsg(msg);
//							System.out.println((new Date()) + " at line 249 mobile "+msisdn.substring(2)+" send continue info ");
						}
						res = null;
					}else{
						MsgQueue mq = MsgQueue.getInstance();
						SessionMsg msg = new SessionMsg();
						msg.setContent("û�и���˵�!");
						msg.setFmt(72);
						msg.setMsisdn(msisdn);
						msg.setNCommandID(112);
						msg.setOptype(1);
						msg.setReceout(c_msg.nSendID);
						msg.setSendout(Integer.parseInt(ussdIdentifier));
						msg.setServicecode(up.getServicecode());
						msg.setVersion(c_msg.Version);
						ul.setLog1(ussdIdentifier, String.valueOf(c_msg.nSendID), "continue", res.getUssdMessge(), msisdn, dealtime, "0007", "", "", String.valueOf(c_msg.nSendID), "0");
						mq.addMTMsg(msg);
//						System.out.println((new Date()) + " at line 265 mobile "+msisdn.substring(2)+" send û�и���˵� info ");
					}
				}else{
					USSDAbortMessage uam = new USSDAbortMessage();
					uam.setReceiceID(c_msg.nSendID);
					uam.setSendID(c_msg.nReceiceID);
					uam.stat = 0;
					uam.nCommandID = 114;
					MsgQueue mq = MsgQueue.getInstance();
					mq.addMTMsgFirst(uam);
//					System.out.println((new Date()) + " at line 275 mobile "+msisdn.substring(2)+" ��ϢΪ��! ");
				}
			}
			catch (Exception e)
			{
				if(!id2.equals("")){
					//webservice�ͻ��˳�ʱ����������
					MsgQueue mq = MsgQueue.getInstance();
					SessionMsg msg = new SessionMsg();
					msg.setContent("USSD���糬ʱ��ϵͳ�쳣");
					msg.setFmt(72);
					msg.setMsisdn(c_msg.Msisdn);
					msg.setNCommandID(112);
					msg.setOptype(1);
					msg.setReceout(c_msg.nSendID);
					msg.setSendout(Integer.parseInt(id2));
					msg.setServicecode(up.getServicecode());
					msg.setVersion(c_msg.Version);
					mq.addMTMsg(msg);
				}
				
				(new InterFaceLog(c_msg.Msisdn, null, null, String.valueOf(c_msg.nSendID), now,
						"USSD_CONTINUE", e.getMessage().toString(),now)).log();
			}
			break;

		case USSDCommand.USSD_ABORT: //��ֹ�Ự
			try
			{
				USSDAbortMessage a_msg = (USSDAbortMessage)respMsg;
				USSDAdapterServiceSoapBindingStub binding = (USSDAdapterServiceSoapBindingStub)(new USSDAdapterServiceImplServiceLocator()).getUSSDAdapterService();
				binding.setTimeout(1200000);
				NotifyUssdEndRequest req = new NotifyUssdEndRequest();
				req.setUssdIdentifier(String.valueOf(a_msg.nSendID));
				binding.notifyUssdEnd(req);
				break;
			}
			catch (Exception ex)
			{
				Log.info((new StringBuilder("USSD USSD_ABORT ���ս���:")).append(ex.getMessage()).toString());
				ex.printStackTrace();
			}
			break;
		}
		try
		{
			Thread.sleep(5000L);
		}
		catch (InterruptedException e)
		{
			Log.info((new StringBuilder("USSD ���ս���:")).append(e.getMessage()).toString());
		}
	}
	
}
