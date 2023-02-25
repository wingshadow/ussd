package com.inspur.ussdplate;

import com.inspur.ussdplate.ussdpara.USSDMonitorPara;
import com.inspur.ussdplate.ussdpara.USSDPara;
import java.util.Date;
import ljwf.Log;


public class USSDMonitorThread
	implements Runnable
{

	static final float SECOND = 5000F;
	int actTimes;
	USSD ussd;

	public USSDMonitorThread()
	{
		actTimes = USSDPara.getInstance().getActTimes();
		ussd = USSD.getInstance();
	}

	public void run()
	{
		do
		{
			
			if (USSDMonitorPara.isLogin)
			{
//				if (diffAct() - (float)actTimes >= 0.0F)
					ussd.sendEnquireLink();
			} else
			{
				try
				{
					Thread.sleep(1000L);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				Log.info("monitor:"+USSDMonitorPara.isLogin);
				while (ussd.connect() != 0) 
					try
					{
						Thread.sleep(1000L);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				ussd.sendEnquireLink();
			}
			try
			{
				Thread.sleep(5000L);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		} while (true);
	}

	private float diffAct()
	{
		return (float)((new Date()).getTime() - USSDMonitorPara.getActTime().getTime()) / 1000F;
	}
	

}
