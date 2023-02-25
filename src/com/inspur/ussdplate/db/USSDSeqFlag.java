package com.inspur.ussdplate.db;

import ljwf.ListView;
import ljwf.db.tbls;

public class USSDSeqFlag extends tbls
{

	public static String getOriName()
	{
		return "ussdseqflag";
	}

	public static String[] getOriKeys()
	{
		return (new String[] {
			"id"
		});
	}

	void init()
	{
		name = getOriName();
		keys = getOriKeys();
	}

	public USSDSeqFlag()
	{
		init();
	}

	public String getUssdSeqflag()
	{
		ListView lv = super.getRs();
		if (lv != null && lv.getLen() > 0)
		{
			lv.first();
			return lv.getFld("id");
		} else
		{
			return "";
		}
	}
}
