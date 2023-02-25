package com.inspur.ussdplate.ussdpara;


public class USSDCommand
{

	public static final int USSD_BIND = 101;
	public static final int USSD_BIND_RESP = 103;
	public static final int USSD_UNBIND = 102;
	public static final int USSD_UNBIND_RESP = 104;
	public static final int USSD_ENQUIRE_LINK = 131;
	public static final int USSD_ENQUIRE_LINK_RESP = 132;
	public static final int USSD_BEGIN = 111;
	public static final int USSD_CONTINUE = 112;
	public static final int USSD_END = 113;
	public static final int USSD_ABORT = 114;
	public static final int USSD_SWITCH = 116;
	public static final int USSD_CHARGEIND = 117;
	public static final int USSD_CHARGEIND_RESP = 118;
	public static final int USSD_SWITCH_BEGIN = 119;
	public static final int USSD_NOUSE = -1;
	public static final int COMMAND_USSD_PLATE_RESP = 38183;
	public static final int PLATE_CONNECT_CLOSE = -999;

	public USSDCommand()
	{
	}
}
