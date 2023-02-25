package com.inspur.ussdplate;

import java.util.HashMap;

public class BossClassConfig
{

	public static HashMap m = null;

	public BossClassConfig()
	{
	}

	public static void init()
	{
		m = new HashMap();
		m.put("234", "com.inspur.ussd.USSDNewsInterface");
	}

	public static String get(String n)
	{
		return (String)m.get("234");
	}

	static 
	{
		init();
	}
}
