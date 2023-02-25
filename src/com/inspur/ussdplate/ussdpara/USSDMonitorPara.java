package com.inspur.ussdplate.ussdpara;

import com.inspur.ussdplate.message.SendMsgRecord;
import java.util.Date;
import java.util.Vector;

public class USSDMonitorPara
{

	public static boolean isLogin = false;
	public static int seqid = 0;
	public static int parentid = 0;
	private static Date actTime = new Date();
	public static Vector msgRecordList = new Vector();

	public USSDMonitorPara()
	{
	}

	public static Date getActTime()
	{
		synchronized(actTime){
			return actTime;
		}
	}

	public static  void setActTime(Date actTime)
	{
		synchronized(actTime){
			USSDMonitorPara.actTime = actTime;
		}
		
	}

	public static void addElement(SendMsgRecord msgRecord)
	{
		synchronized (msgRecordList)
		{
			msgRecordList.addElement(msgRecord);
		}
	}

	public static void removeElement(SendMsgRecord msgRecord)
	{
		synchronized (msgRecordList)
		{
			msgRecordList.removeElement(msgRecord);
		}
	}

}
