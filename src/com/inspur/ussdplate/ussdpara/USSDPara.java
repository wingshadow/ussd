package com.inspur.ussdplate.ussdpara;

import com.inspur.ussdplate.db.Config;
import ljwf.ListView;
import ljwf.Log;

public class USSDPara
{

	private static USSDPara _instance;
	private String icpID;
	private String ipcAuth;
	private String ismgIP;
	private int ismgPort;
	private String spNumber;
	private int ismgVer;
	private String leadchar;
	private String endchar;
	private String parachar;
	private String upkey;
	private String downkey;
	private String exitkey;
	private String rebackkey;
	private String masserverip;
	private String servicecode;
	private int masserverport;
	private int actTimes;
	public static final int USSD_SUCCESS = 0;
	public static final int USSD_UNKNOWN_PACKAGE_ERROR = -9;
	public static final int USSD_LOGOUT_HAPPEND = -2;
	public static final int SESSION_MSG_NULL_ERROR = -4;
	public static final int SESSION_MSG_FORMAT_ERROR = -5;
	public static final int SESSION_MSG_TIME_OUT = -6;
	public static final int DELIVER_MSG_FORMAT_REPORT_ERROR = -7;
	public static final int DELIVER_MSG_NULL_ERROR = -8;
	public static final int DELIVER_MSG_TIME_OUT = -9;
	public static final int DELIVER_MSG_FORMAT_ERROR = -10;
	public static final int DELIVER_MSG_FORMAT_DECODE_ERROR = -11;
	public static final int CONNECT_MSG_NULL_ERROR = -12;
	public static final int CONNECT_MSG_RESP_NOT_FOUNT_ERROR = -13;
	public static final int CONNECT_MSG_FORMAT_ERROR = -14;
	public static final int CONNECT_INIT_ERROR = -15;
	public static final int SWITCH_MSG_NULL_ERROR = -16;
	public static final int SWITCH_MSG_FORMAT_ERROR = -17;
	public static final int SWITCH_MSG_TIME_OUT = -18;
	public static final int CHARGE_MSG_NULL_ERROR = -19;
	public static final int CHARGE_MSG_FORMAT_ERROR = -20;
	public static final int CHARGE_MSG_TIME_OUT = -21;
	public static final int COMMAND_USSD_PLATE_RESP = 38183;
	public static final int HEAD_LEN = 20;

	public USSDPara()
	{
		actTimes = 5;
	}

	public int getActTimes()
	{
		return actTimes;
	}

	public void setActTimes(int actTimes)
	{
		this.actTimes = actTimes;
	}

	public static synchronized USSDPara getInstance()
	{
		if (_instance == null)
			_instance = new USSDPara();
		return _instance;
	}

	public String getIcpID()
	{
		return icpID;
	}

	public void setIcpID(String icpID)
	{
		this.icpID = icpID;
	}

	public String getIpcAuth()
	{
		return ipcAuth;
	}

	public void setIpcAuth(String ipcAuth)
	{
		this.ipcAuth = ipcAuth;
	}

	public String getIsmgIP()
	{
		return ismgIP;
	}

	public void setIsmgIP(String ismgIP)
	{
		this.ismgIP = ismgIP;
	}

	public int getIsmgPort()
	{
		return ismgPort;
	}

	public void setIsmgPort(int ismgPort)
	{
		this.ismgPort = ismgPort;
	}

	public int getIsmgVer()
	{
		return ismgVer;
	}

	public void setIsmgVer(int ismgVer)
	{
		this.ismgVer = ismgVer;
	}

	public String getSpNumber()
	{
		return spNumber;
	}

	public void setSpNumber(String spNumber)
	{
		this.spNumber = spNumber;
	}

	public String getLeadchar()
	{
		return leadchar;
	}

	public void setLeadchar(String leadchar)
	{
		this.leadchar = leadchar;
	}

	public String getEndchar()
	{
		return endchar;
	}

	public void setEndchar(String endchar)
	{
		this.endchar = endchar;
	}

	public String getParachar()
	{
		return parachar;
	}

	public void setParachar(String parachar)
	{
		this.parachar = parachar;
	}

	public String getUpkey()
	{
		return upkey;
	}

	public void setUpkey(String upkey)
	{
		this.upkey = upkey;
	}

	public String getDownkey()
	{
		return downkey;
	}

	public void setDownkey(String downkey)
	{
		this.downkey = downkey;
	}

	public String getExitkey()
	{
		return exitkey;
	}

	public void setExitkey(String exitkey)
	{
		this.exitkey = exitkey;
	}

	public String getRebackkey()
	{
		return rebackkey;
	}

	public void setRebackkey(String rebackkey)
	{
		this.rebackkey = rebackkey;
	}

	public String getMasserverip()
	{
		return masserverip;
	}

	public void setMasserverip(String masserverip)
	{
		this.masserverip = masserverip;
	}

	public int getMasserverport()
	{
		return masserverport;
	}

	public void setMasserverport(int masserverport)
	{
		this.masserverport = masserverport;
	}

	public void readConfig()
	{
		Config config = new Config();
		ListView lv = config.getVal();
		setIsmgIP("211.137.179.205");
		setIcpID("123test");
		setIpcAuth("123test");
		setIsmgPort(4400);
		if (lv != null && lv.getLen() > 0)
		{
			lv.beforefirst();
			while (lv.next()) 
				if (lv.getFld("name").equals("USSD_ICP_ID"))
				{
					Log.info((new StringBuilder("mame:")).append(lv.getFld("val")).toString());
					setIcpID(lv.getFld("val"));
				} else
				if (lv.getFld("name").equals("USSD_ICP_AUTH"))
					setIpcAuth(lv.getFld("val"));
				else
				if (lv.getFld("name").equals("USSD_IP"))
					setIsmgIP(lv.getFld("val"));
				else
				if (lv.getFld("name").equals("USSD_PORT"))
					setIsmgPort(Integer.parseInt(lv.getFld("val")));
				else
				if (lv.getFld("name").equals("USSD_LEADCHAR"))
					setLeadchar(lv.getFld("val"));
				else
				if (lv.getFld("name").equals("USSD_ENDCHAR"))
					setEndchar(lv.getFld("val"));
				else
				if (lv.getFld("name").equals("USSD_VER"))
					setIsmgVer(Integer.parseInt(lv.getFld("val")));
				else
				if (lv.getFld("name").equals("USSD_ENDSESSION"))
					setExitkey(lv.getFld("val"));
				else
				if (lv.getFld("name").equals("USSD_UP"))
					setRebackkey(lv.getFld("val"));
				else
				if(lv.getFld("name").equals("USSD_SERVICE_CODE"))				
					setServicecode(lv.getFld("val"));
		}else{
			setIsmgIP("211.137.179.205");
			setIcpID("123test");
			setIpcAuth("123test");
			setIsmgPort(4400);
		}
	}

	public String getServicecode() {
		return servicecode;
	}

	public void setServicecode(String servicecode) {
		this.servicecode = servicecode;
	}
}
