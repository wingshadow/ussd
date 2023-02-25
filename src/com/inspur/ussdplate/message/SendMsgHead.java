package com.inspur.ussdplate.message;


public class SendMsgHead
{

	private int nCommandID;
	private int sendout;
	private int receout;

	public SendMsgHead()
	{
	}

	public int getNCommandID()
	{
		return nCommandID;
	}

	public void setNCommandID(int commandID)
	{
		nCommandID = commandID;
	}

	public int getSendout()
	{
		return sendout;
	}

	public void setSendout(int sendout)
	{
		this.sendout = sendout;
	}

	public int getReceout()
	{
		return receout;
	}

	public void setReceout(int receout)
	{
		this.receout = receout;
	}
}
