package com.inspur.ussdplate.message;


public class USSDSwitchMessage extends USSDHead
{

	public int Switch_Mode;
	public String Msisdn;
	public String Org_Service_code;
	public String Dest_Service_code;
	public String Content;

	public USSDSwitchMessage()
	{
		nCommandID = 116;
	}

	public USSDSwitchMessage(SwitchMsg smsg)
	{
		nCommandID = 116;
		Switch_Mode = smsg.getSwitchmode();
		Msisdn = smsg.getMsisdn();
		Org_Service_code = smsg.getOrgServicecode();
		Dest_Service_code = smsg.getDestServicecode();
		Content = smsg.getContent();
	}
}
