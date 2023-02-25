package com.inspur.ussdplate.rcv;

import com.inspur.ussdplate.db.USSDLog;
import com.inspur.ussdplate.message.*;
import com.inspur.ussdplate.queue.MsgQueue;
import com.inspur.ussdplate.ussdpara.USSDCommand;
import com.inspur.ussdplate.ussdpara.USSDPara;
import com.inspur.utils.SystemUtil;
import com.webserviceclient.USSDAdapterService.USSDAdapterServiceImplServiceLocator;
import com.webserviceclient.USSDAdapterService.USSDAdapterServiceSoapBindingStub;
import com.webserviceclient.bo.*;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import ljwf.Log;
import com.inspur.utils.InterFaceLog;

public class ReceiveUSSDThread
	implements Runnable
{

	Object obj;
	int nCommandID;
	USSDHead respMsg;
	private USSDPara up;
	public ThreadPoolExecutor threadpool;

	public ReceiveUSSDThread()
	{
		up = USSDPara.getInstance();
		threadpool = new ThreadPoolExecutor(10, 30, 5,
				TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10),
				new ThreadPoolExecutor.DiscardOldestPolicy());
	}

	public void run()
	{
		USSDLog ul = new USSDLog();
		do
		{
			while ((obj = MsgQueue.getInstance().getMOMsg()) != null) 
				if (obj instanceof USSDHead)
				{
					respMsg = (USSDHead)obj;
					nCommandID = respMsg.nCommandID;
					threadpool.execute(new ServiceOperDeal(respMsg));
				}
			try
			{
				Thread.sleep(5000L);
			}
			catch (InterruptedException e)
			{
				Log.info((new StringBuilder("USSD 接收进程:")).append(e.getMessage()).toString());
			}
			
		} while (true);
	}
}
