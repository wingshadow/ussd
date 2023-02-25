package com.inspur.ussdplate.message;


public class USSDSwitchBeginMessage extends USSDHead
{

	public int Version;
	public int Op_type;
	public String Msisdn;
	public String Org_Service_code;
	public String Dest_Service_code;
	public int Msg_fmt;
	public String Content;

	public USSDSwitchBeginMessage()
	{
		nCommandID = 119;
	}
}
