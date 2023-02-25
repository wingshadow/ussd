package com.inspur.ussdplate.message;


// Referenced classes of package com.inspur.ussdplate.message:
//			USSDHead, SessionMsg

public class USSDBeginMessage extends USSDHead
{

	public int Version;
	public int Op_type;
	public String Msisdn;
	public String Service_code;
	public int Msg_fmt;
	public String Content;

	public USSDBeginMessage()
	{
		nCommandID = 111;
	}

	public USSDBeginMessage(SessionMsg msg, int flag)
	{
		if (flag == 0)
			nCommandID = 111;
		else
		if (flag == 1)
			nCommandID = 112;
		else
		if (flag == 2)
		{
			nCommandID = 113;
			stat = 0;
		}
		Version = msg.getVersion();
		Op_type = msg.getOptype();
		Msisdn = msg.getMsisdn();
		Service_code = msg.getServicecode();
		Msg_fmt = msg.getFmt();
		Content = msg.getContent();
		nSendID = msg.getSendout();
		nReceiceID = msg.getReceout();
	}
}
