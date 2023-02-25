package com.inspur.ussdplate.db;

import ljwf.ListView;
import ljwf.db.tbls;

public class Config extends tbls
{

	public static String getOriName()
	{
		return "config";
	}

	public static String[] getOriKeys()
	{
		return (new String[] {
			"name"
		});
	}

	void init()
	{
		name = getOriName();
		keys = getOriKeys();
	}

	public Config()
	{
		init();
	}

	public ListView getVal()
	{
		String sql = (new StringBuilder("select name,val from ")).append(name).toString();
		return super.getRsbySQL(sql);
	}

	public void downRatio()
	{
		int radio = 2;
		ListView lv = super.getRsbySQL((new StringBuilder("select val from ")).append(name).append(" where name='SMS_SP_RATIO'").toString());
		if (lv != null)
		{
			lv.next();
			radio = Integer.parseInt(lv.getFld("val"));
		}
		radio--;
		String sql = (new StringBuilder("update ")).append(name).append(" set val='").append(radio).append("' where name='SMS_SP_RATIO'").toString();
		super.ExecSQL(sql);
	}
}
