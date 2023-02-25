package com.inspur.ussdplate.message;


public class USSDBindRespMessage extends USSDHead
{

	public String System_ID;

	public USSDBindRespMessage()
	{
		nCommandID = 103;
	}
}
