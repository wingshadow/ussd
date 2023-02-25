package com.inspur.ussdplate.control;

import com.inspur.ussdplate.USSD;
import com.inspur.ussdplate.USSDMonitorThread;
import com.inspur.ussdplate.queue.*;
import com.inspur.ussdplate.rcv.ReceiveUSSDThread;
import com.inspur.ussdplate.ussdpara.USSDPara;
import ljwf.Log;

public class ServiceControl
{

	public ServiceControl()
	{
	}

	public static void main(String args[])
	{
		MsgQueue mq = MsgQueue.getInstance();
		mq.clearMTMsg();
		mq.clearMOMsg();
		USSDPara up = USSDPara.getInstance();
		up.readConfig();
		Log.info("��ȡ�����ļ��ɹ�!");
		Log.info((new StringBuilder()).append(up.getIcpID()).toString());
		Log.info((new StringBuilder()).append(up.getIpcAuth()).toString());
		for (USSD ussd = USSD.getInstance(); ussd.connect() != 0;)
			try
			{
				Thread.sleep(5000L);
			}
			catch (InterruptedException e)
			{
				Log.info("control:"+e.getLocalizedMessage());
			}

		Log.info("����/��¼USSD���سɹ�");
		(new Thread(new USSDMonitorThread())).start();
		Log.info("����USSDƽ̨����̳߳ɹ�");
		(new Thread(new SendQueueThread())).start();
		Log.info("����USSD���������̳߳ɹ�");
		(new Thread(new ReceiveMsgThread())).start();
		Log.info("����USSD������Ӧ�̳߳ɹ�");
		(new Thread(new ReceiveUSSDThread())).start();
		Log.info("����USSD�߼������̳߳ɹ�");
		Log.info("USSDƽ̨�����ɹ�");
	}
}
