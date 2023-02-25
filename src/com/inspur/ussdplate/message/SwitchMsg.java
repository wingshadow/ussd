package com.inspur.ussdplate.message;


public class SwitchMsg extends SendMsgHead
{

	private int switch_mode;
	private int version;
	private int op_type;
	private String Msisdn;
	private String org_Service_code;
	private String dest_Service_code;
	private int fmt;
	private String content;
	private byte picAndRing[];

	public SwitchMsg()
	{
	}

	public int getSwitchmode()
	{
		return switch_mode;
	}

	public void setSwitchmode(int switch_mode)
	{
		this.switch_mode = switch_mode;
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

	public String getOrgServicecode()
	{
		return org_Service_code;
	}

	public void setOrgServicecode(String org_Service_code)
	{
		this.org_Service_code = org_Service_code;
	}

	public String getDestServicecode()
	{
		return dest_Service_code;
	}

	public void setDestServicecode(String dest_Service_code)
	{
		this.dest_Service_code = dest_Service_code;
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
