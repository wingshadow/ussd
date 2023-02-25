// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MsgQueue.java

package com.inspur.ussdplate.queue;

import com.inspur.ussdplate.message.SendMsgHead;
import com.inspur.ussdplate.message.USSDHead;
import com.inspur.ussdplate.ussdpara.USSDPara;
import java.util.LinkedList;
import java.util.Vector;

public class MsgQueue
{

	private static MsgQueue _instance;
	public Vector mtList;//发送给网关数据包队列
	public Vector moList;//接收网关发送数据包队列
	private USSDPara up;

	public MsgQueue()
	{
		mtList = new Vector();
		moList = new Vector();
		up = USSDPara.getInstance();
	}

	public static synchronized MsgQueue getInstance()
	{
		if (_instance == null)
			_instance = new MsgQueue();
		return _instance;
	}

	public void addMtList(SendMsgHead msg)
	{
		synchronized (mtList)
		{
			mtList.addElement(msg);
		}
	}

	public void addMTMsgFirst(SendMsgHead msg)
	{
		synchronized (mtList)
		{
			mtList.add(0,msg);
		}
	}

	public void addMTMsg(SendMsgHead msg)
	{
		synchronized (mtList)
		{
			mtList.add(msg);
		}
	}

	public void addMTMsgFirst(USSDHead msg)
	{
		synchronized (mtList)
		{
			mtList.addElement(msg);
		}
	}

	public void clearMTMsg()
	{
		synchronized (mtList)
		{
			mtList.clear();
		}
	}

	public Object getMTMsg()
	{
		if (!mtList.isEmpty())
		{
			Object obj;
			synchronized (mtList)
			{
				obj = mtList.get(0);
				mtList.remove(0);
			}
			return obj;
		} else
		{
			return null;
		}
	}

	public void addMOMsg(USSDHead msg)
	{
		synchronized (moList)
		{
			moList.addElement(msg);
		}
	}

	public void addMOMsgFirst(USSDHead msg)
	{
		synchronized (moList)
		{
			moList.add(0,msg);
		}
	}

	public void clearMOMsg()
	{
		synchronized (moList)
		{
			moList.clear();
		}
	}

	public Object getMOMsg()
	{
		if (!moList.isEmpty())
		{
			Object obj;
			synchronized (moList)
			{
				obj = moList.get(0);
				moList.remove(0);
			}
			return obj;
		} else
		{
			return null;
		}
	}
}
