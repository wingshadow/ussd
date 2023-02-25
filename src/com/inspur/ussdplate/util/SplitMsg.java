package com.inspur.ussdplate.util;

import java.util.Vector;

public class SplitMsg
{

	private static final int step = 58;

	public SplitMsg()
	{
	}

	public static Vector split(String msg, boolean splitFlag)
	{
		if (msg == null)
			return null;
		Vector v = new Vector();
		if (splitFlag || msg.length() <= 70)
		{
			v.addElement(msg);
			return v;
		}
		int length = msg.length();
		int splitNum = length / 58 + 1;
		boolean flag = true;
		if (splitNum > 10)
		{
			splitNum = 10;
			flag = false;
		}
		int len = 0;
		for (int i = 0; i < splitNum; i++)
			if (i == 0)
			{
				String spMsg = (new StringBuilder(String.valueOf(msg.substring(0, 58)))).append(getFirstEnd(splitNum)).toString();
				v.addElement(spMsg);
				spMsg = null;
				len = 58;
			} else
			if (i == splitNum - 1)
			{
				String spMsg = null;
				if (!flag)
					spMsg = (new StringBuilder(String.valueOf(getSecondFirst(i, splitNum)))).append(msg.substring(len, len + 58)).toString();
				v.addElement(spMsg);
				spMsg = null;
			} else
			{
				String spMsg = null;
				spMsg = (new StringBuilder(String.valueOf(getSecondFirst(i, splitNum)))).append(msg.substring(len, len + 58)).append(getSecondEnd(i, splitNum)).toString();
				v.addElement(spMsg);
				spMsg = null;
			}

		return v;
	}

	private static String getFirstEnd(int spNum)
	{
		return (new StringBuilder("(1/")).append(spNum).append(")").toString();
	}

	private static String getSecondFirst(int n, int spNum)
	{
		return (new StringBuilder("(½Ó")).append(n).append("/").append(spNum).append(")").toString();
	}

	private static String getSecondEnd(int n, int spNum)
	{
		return (new StringBuilder("(")).append(n + 1).append("/").append(spNum).append(")").toString();
	}
}
