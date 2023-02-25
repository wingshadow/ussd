package com.inspur.ussdplate.message;


public class USSDChargeMessage extends USSDHead
{

	public int Charge_Ratio;
	public int Charge_Type;
	public String Charge_Resource;
	public int Charge_Location;

	public USSDChargeMessage()
	{
		nCommandID = 117;
	}

	public USSDChargeMessage(ChargeMsg cmsg)
	{
		nCommandID = 117;
		Charge_Ratio = cmsg.getChargeRatio();
		Charge_Type = cmsg.getChargeType();
		Charge_Resource = cmsg.getChargeResource();
		Charge_Location = cmsg.getChargeLocation();
	}
}
