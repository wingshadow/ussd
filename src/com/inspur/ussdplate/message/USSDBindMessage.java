package com.inspur.ussdplate.message;


// Referenced classes of package com.inspur.ussdplate.message:
//			USSDHead

public class USSDBindMessage extends USSDHead
{

	public String System_ID;
	public String Password;
	public String System_Type;
	public int Version;

	public USSDBindMessage()
	{
		System_Type = "USSD";
		nCommandID = 101;
	}
}
