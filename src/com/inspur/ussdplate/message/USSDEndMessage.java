package com.inspur.ussdplate.message;


public class USSDEndMessage extends USSDHead
{

	public int Version;
	public int Op_type;
	public String Msisdn;
	public String Service_code;
	public int Msg_fmt;
	public String Content;

	public USSDEndMessage()
	{
		nCommandID = 113;
	}
}
