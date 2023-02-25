package com.inspur.ussdplate.message;


public class USSDHead
{

	private static int i = 1;
	private static int j = 1;
	public int nMsgSize;
	public int nCommandID;
	public int nSendID;
	public int nReceiceID;
	public int stat;

	public USSDHead()
	{
		nMsgSize = 20;
		nSendID = 0;
		stat = 0;
	}

	public int setSendID(int p)
	{
		if (p >= 0x7fffffff)
			p = 1;
		nSendID = p;
		return p;
	}

	public void setReceiceID()
	{
		nReceiceID = j;
		j++;
		if (j == 0x7fffffff)
			j = 1;
	}

	public int setReceiceID(int p)
	{
		if (p >= 0x7fffffff)
			p = 1;
		nReceiceID = p;
		return ++p;
	}

	public static void initnReceiceID(int num)
	{
		i = num;
	}

	public static void initnSendID(int num)
	{
		i = num;
	}

}
