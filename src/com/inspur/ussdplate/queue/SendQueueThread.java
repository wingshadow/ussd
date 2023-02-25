// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SendQueueThread.java

package com.inspur.ussdplate.queue;

import com.inspur.ussdplate.USSD;
import com.inspur.ussdplate.message.*;
import com.inspur.ussdplate.ussdpara.USSDMonitorPara;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;
import ljwf.Log;

// Referenced classes of package com.inspur.ussdplate.queue:
//			MsgQueue

public class SendQueueThread
	implements Runnable
{

	int nCommandID;
	SendMsgHead msg;
	USSDBeginMessage ubm;
	USSDContinueMessage ucm;
	USSDSwitchMessage usm;
	USSDChargeMessage uchm;
	USSDHead head;
	SendMsgRecord msgRecord;
	Object obj;
	USSD ussd;

	public SendQueueThread()
	{
		ussd = USSD.getInstance();
	}

	public void run()
	{
		do
		{
			while ((obj = MsgQueue.getInstance().getMTMsg()) != null && USSDMonitorPara.isLogin) 
				if (obj instanceof SendMsgRecord)
				{
					msgRecord = (SendMsgRecord)obj;
					try
					{
						USSDMonitorPara.removeElement(msgRecord);
						msgRecord.setSndTime(new Date());
						msgRecord.setSndTimes((short)(msgRecord.getSndTimes() + 1));
						USSDMonitorPara.addElement(msgRecord);
						ussd.send(msgRecord.getMsg());
					}
					catch (IOException ioexception) { }
				} else
				if (obj instanceof SendMsgHead)
				{
					msg = (SendMsgHead)obj;
					nCommandID = msg.getNCommandID();
//					Log.info("开始执行发送队列");
//					Log.info((new StringBuilder("nCommandID:")).append(nCommandID).toString());
					switch (nCommandID)
					{
					case 111: // 'o'
						SessionMsg sm = (SessionMsg)msg;
						ussd.submitSession(sm, 0);
						break;

					case 112: // 'p'
						SessionMsg cm = (SessionMsg)msg;
//						System.out.println((new StringBuilder("send message:")).append(cm.getContent()).toString());
//						System.out.println((new StringBuilder("receive id:")).append(cm.getReceout()).toString());
//						System.out.println((new StringBuilder("send id:")).append(cm.getSendout()).toString());
//						System.out.println((new StringBuilder("fmt:")).append(cm.getFmt()).toString());
						ussd.submitSession(cm, 1);
						break;

					case 113: // 'q'
						SessionMsg em = (SessionMsg)msg;
						ussd.submitSession(em, 2);
						break;

					case 116: // 't'
						SwitchMsg sms = (SwitchMsg)msg;
						ussd.submitSwitch(sms);
						break;

					case 117: // 'u'
						ChargeMsg cmc = (ChargeMsg)msg;
						ussd.submitCharge(cmc);
						break;
					}
				} else
				if (obj instanceof USSDHead)
				{
					head = (USSDHead)obj;
					nCommandID = msg.getNCommandID();
					switch (nCommandID)
					{
					case 113: // 'q'
						USSDAbortMessage uam = (USSDAbortMessage)head;
						ussd.sendAbort(uam);
						break;
					}
				}
			try
			{
				Thread.sleep(2000L);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		} while (true);
	}
}
