package com.inspur.ussdplate.message;


public class USSDContinueMessage extends USSDHead
{

	public int Version;
	public int Op_type;
	public String Msisdn;
	public String Service_code;
	public int Msg_fmt;
	public String Content;

	public USSDContinueMessage()
	{
		nCommandID = 112;
	}

	public USSDContinueMessage(SessionMsg msg)
	{
		nCommandID = 111;
		Version = msg.getVersion();
		Op_type = msg.getOptype();
		Msisdn = msg.getMsisdn();
		Service_code = msg.getServicecode();
		Msg_fmt = msg.getFmt();
		Content = msg.getContent();
	}
}
