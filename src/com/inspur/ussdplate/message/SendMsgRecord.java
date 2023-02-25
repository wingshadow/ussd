package com.inspur.ussdplate.message;

import java.util.Date;

public class SendMsgRecord
{

	private int nSendID;
	private int nReceiveID;
	private byte msg[];
	private Date sndTime;
	private short type;
	private short sndTimes;

	public SendMsgRecord()
	{
		type = 0;
		sndTimes = 1;
	}

	public Date getSndTime()
	{
		return sndTime;
	}

	public synchronized void setSndTime(Date sndTime)
	{
		this.sndTime = sndTime;
	}

	public byte[] getMsg()
	{
		return msg;
	}

	public synchronized void setMsg(byte msg[])
	{
		this.msg = msg;
	}

	public short getType()
	{
		return type;
	}

	public synchronized void setType(short type)
	{
		this.type = type;
	}

	public short getSndTimes()
	{
		return sndTimes;
	}

	public synchronized void setSndTimes(short sndTimes)
	{
		this.sndTimes = sndTimes;
	}

	public int getSendID()
	{
		return nSendID;
	}

	public synchronized void setSendID(int nSendID)
	{
		this.nSendID = nSendID;
	}
}
