package com.inspur.ussdplate.snd;

import com.inspur.ussdplate.db.*;
import com.inspur.ussdplate.message.SessionMsg;
import com.inspur.ussdplate.queue.MsgQueue;
import com.inspur.utils.SystemUtil;
import java.io.PrintStream;
import java.util.Date;
import java.util.HashMap;
import ljwf.ListView;
import ljwf.Log;

public class SendUSSDThread
	implements Runnable
{

	public SendUSSDThread()
	{
	}

	public void run()
	{
		do
			try
			{
				do
				{
					Thread.sleep(30000L);
					USSDLog ul = new USSDLog();
					String sessionid = ul.getSessionID();
					String cmd = "6F";
					MsgQueue mq = MsgQueue.getInstance();
					USSDProcess up = new USSDProcess();
					USSDSeqFlag seqflag = new USSDSeqFlag();
					String id = seqflag.getUssdSeqflag();
					if (id != null && !id.equals(""))
					{
						USSDSendTask task = new USSDSendTask();
						ListView lv = task.getSendUssdTask(id);
						if (lv != null && lv.getLen() > 0)
						{
							HashMap map;
							for (; lv.next(); seqflag.Update(map))
							{
								SessionMsg msg = new SessionMsg();
								msg.setContent((new StringBuilder("@*123@")).append(lv.getFld("message")).toString());
								msg.setFmt(72);
								msg.setMsisdn(lv.getFld("mobile"));
								msg.setVersion(32);
								msg.setServicecode("123");
								msg.setOptype(1);//服务器端向终端推送request是1，向终端推送通知时2
								msg.setReceout(-1);
								msg.setSendout(Integer.parseInt(lv.getFld("id")));
								if (cmd.equals("6F"))
									msg.setNCommandID(111);
								Date now = new Date();
								String dealtime = SystemUtil.getDateTime(now);
								ul.setLog1(String.valueOf(sessionid), String.valueOf(-1), "begin", "@*123@菜单", "8613506418307", dealtime, "0007", "u", "app", String.valueOf(-1), "0");
								up.setUssdRecord(sessionid, 0, 1, lv.getFld("message"), "M", "8615866621726");
								mq.addMTMsg(msg);
								map = new HashMap();
								map.put("id", lv.getFld("id"));
							}

						}
					}
					Thread.sleep(0x124f80L);
				} while (true);
			}
			catch (InterruptedException e)
			{
				System.out.println((new StringBuilder("发送线程:")).append(e.getMessage()).toString());
				Log.error((new StringBuilder("短信发送线程线程错误：")).append(e.toString()).toString());
			}
		while (true);
	}
}
