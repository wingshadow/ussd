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
		Log.info("读取配置文件成功!");
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

		Log.info("连接/登录USSD网关成功");
		(new Thread(new USSDMonitorThread())).start();
		Log.info("启动USSD平台监控线程成功");
		(new Thread(new SendQueueThread())).start();
		Log.info("启动USSD发送请求线程成功");
		(new Thread(new ReceiveMsgThread())).start();
		Log.info("启动USSD接收响应线程成功");
		(new Thread(new ReceiveUSSDThread())).start();
		Log.info("启动USSD逻辑处理线程成功");
		Log.info("USSD平台启动成功");
	}
}
