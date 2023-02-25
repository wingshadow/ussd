// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ReceiveMsgThread.java

package com.inspur.ussdplate.queue;

import com.inspur.ussdplate.USSD;
import com.inspur.ussdplate.message.USSDBeginMessage;
import com.inspur.ussdplate.message.USSDContinueMessage;
import com.inspur.ussdplate.message.USSDHead;
import com.inspur.ussdplate.ussdpara.USSDCommand;
import com.inspur.ussdplate.ussdpara.USSDMonitorPara;
import com.inspur.utils.InterFaceLog;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;

import ljwf.Log;

// Referenced classes of package com.inspur.ussdplate.queue:
//			MsgQueue

public class ReceiveMsgThread
	implements Runnable
{

	int nCommandID;
	USSDHead head;
	USSD ussd;

	public ReceiveMsgThread()
	{
		ussd = USSD.getInstance();
	}

	public void run()
	{
		do
		{
			if (USSDMonitorPara.isLogin)
				try
				{
					head = ussd.Deliver();
					nCommandID = head.nCommandID;
//					Log.info((new StringBuilder("deliver :")).append(nCommandID).toString()
//							+",state:"+head.stat+",id:"+head.nReceiceID);
					switch (nCommandID)
					{
					case USSDCommand.USSD_BEGIN: // 开始USSD会话
						/***log开始***/
						USSDBeginMessage b_msg = (USSDBeginMessage)head;
						Date now = new Date();
						(new InterFaceLog(b_msg.Msisdn, null, null, String.valueOf(b_msg.nSendID), now,
								"receivebegin", b_msg.Content,	now)).log();
						/***log结束***/
						MsgQueue.getInstance().addMOMsg(head);
						break;

					case USSDCommand.USSD_CONTINUE: // 继续USSD会话
						/***log开始***/
						USSDContinueMessage c_msg = (USSDContinueMessage)head;
						Date now2 = new Date();
						(new InterFaceLog(c_msg.Msisdn, null, null, String.valueOf(c_msg.nSendID), now2,
								"receivecotinue", c_msg.Content,	now2)).log();
						/***log结束***/
						MsgQueue.getInstance().addMOMsg(head);
						break;

					case USSDCommand.USSD_SWITCH_BEGIN: // 开始转移USSD会话
						MsgQueue.getInstance().addMOMsg(head);
						break;

					case USSDCommand.USSD_UNBIND_RESP: // 终止连接应答
						ussd.close();
						USSDMonitorPara.isLogin = false;
						Log.info("ReceiveMsgThread set:"+USSDMonitorPara.isLogin);
						break;
					}
				}
				catch (IOException e)
				{
					Log.info("ReceiveMsgThread set:"+USSDMonitorPara.isLogin+":"+e.getLocalizedMessage());
					ussd.close();
					USSDMonitorPara.isLogin = false;
					Log.info("ReceiveMsgThread set:"+USSDMonitorPara.isLogin+":"+e.getLocalizedMessage());
				}
			try
			{
				Thread.sleep(500L);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		} while (true);
	}
}
