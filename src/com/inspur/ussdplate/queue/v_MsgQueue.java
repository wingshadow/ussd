// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MsgQueue.java

package com.inspur.ussdplate.queue;

import com.inspur.ussdplate.message.SendMsgHead;
import com.inspur.ussdplate.message.USSDHead;
import com.inspur.ussdplate.ussdpara.USSDPara;
import java.util.LinkedList;

public class v_MsgQueue
{

	private static MsgQueue _instance;
	public LinkedList mtList;//发送给网关数据包队列
	public LinkedList moList;//接收网关发送数据包队列
	private USSDPara up;

	public v_MsgQueue()
	{
		mtList = new LinkedList();
		moList = new LinkedList();
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
			mtList.add(msg);
		}
	}

	public void addMTMsgFirst(SendMsgHead msg)
	{
		synchronized (mtList)
		{
			mtList.addFirst(msg);
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
			mtList.addFirst(msg);
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
				obj = mtList.getFirst();
				mtList.removeFirst();
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
			moList.add(msg);
		}
	}

	public void addMOMsgFirst(USSDHead msg)
	{
		synchronized (moList)
		{
			moList.addFirst(msg);
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
				obj = moList.getFirst();
				moList.removeFirst();
			}
			return obj;
		} else
		{
			return null;
		}
	}
}
