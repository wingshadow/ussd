package com.inspur.ussdplate.message;


public class SessionMsg extends SendMsgHead
{

	private int version;
	private int op_type;
	private String Msisdn;
	private String Service_code;
	private int fmt;
	private String content;
	private byte picAndRing[];

	public SessionMsg()
	{
	}

	public int getVersion()
	{
		return version;
	}

	public void setVersion(int version)
	{
		this.version = version;
	}

	public int getOptype()
	{
		return op_type;
	}

	public void setOptype(int op_type)
	{
		this.op_type = op_type;
	}

	public String getMsisdn()
	{
		return Msisdn;
	}

	public void setMsisdn(String Msdsin)
	{
		Msisdn = Msdsin;
	}

	public String getServicecode()
	{
		return Service_code;
	}

	public void setServicecode(String Service_code)
	{
		this.Service_code = Service_code;
	}

	public int getFmt()
	{
		return fmt;
	}

	public void setFmt(int fmt)
	{
		this.fmt = fmt;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}
}
