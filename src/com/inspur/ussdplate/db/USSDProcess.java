package com.inspur.ussdplate.db;

import ljwf.ListView;
import ljwf.Log;
import ljwf.db.tbls;

public class USSDProcess extends tbls
{

	public static String getOriName()
	{
		return "ussdopprocess";
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

	public USSDProcess()
	{
		init();
	}

	public void setUssdRecord(String sessionID, int pid, int cid, String content, String type, String mobile)
	{
		String sql = (new StringBuilder("insert into ussdopprocess(id,pid,cid,content,type,mobile)values('")).append(sessionID).append("',").append(pid).append(",").append(cid).append(",'").append(content).append("','").append(type).append("','").append(mobile).append("')").toString();
		super.ExecSQL(sql);
	}

	public String getSessionMenu(String sessionID, int seqno)
	{
		String message = "";
		String sql = (new StringBuilder("select * from ussdmenu where pid = (select id from ussdmenu where pid =(select id  from ussdmenu m where m.pid = (select pid from ussdopprocess where id = '")).append(sessionID).append("')  ) ").append("and seqid = ").append(seqno).append(") order by seqid").toString();
		Log.info((new StringBuilder("menu:")).append(sql).toString());
		ListView lv = super.getRsbySQL(sql);
		if (lv != null && lv.getLen() > 0)
			while (lv.next()) 
				if (lv.getFld("type").equals("2"))
					message = (new StringBuilder(String.valueOf(message))).append(lv.getFld("seqid")).append(".").append(lv.getFld("remark")).append('\n').toString();
				else
					message = (new StringBuilder(String.valueOf(message))).append(lv.getFld("seqid")).append(".").append(lv.getFld("content")).append('\n').toString();
		return message;
	}

	public String getDownMenu(String pid)
	{
		String message = "";
		ListView lv = null;
		String sql = (new StringBuilder("select * from ussdmenu where pid = ")).append(pid).append(" order by seqid ").toString();
		Log.info((new StringBuilder("down menu:")).append(sql).toString());
		lv = super.getRsbySQL(sql);
		if (lv != null && lv.getLen() > 0)
			while (lv.next()) 
				if (lv.getFld("type").equals("2"))
					message = (new StringBuilder(String.valueOf(message))).append(lv.getFld("remark")).append('\n').toString();
				else
					message = (new StringBuilder(String.valueOf(message))).append(lv.getFld("seqid")).append(".").append(lv.getFld("content")).append('\n').toString();
		return message;
	}

	public ListView getDownMenu2(String pid)
	{
		String message = "";
		ListView lv = null;
		String sql = (new StringBuilder("select * from ussdmenu where pid = ")).append(pid).append(" order by seqid ").toString();
		Log.info((new StringBuilder("down menu:")).append(sql).toString());
		lv = super.getRsbySQL(sql);
		return lv;
	}

	public String getUpSessionMenu(String sessionID)
	{
		String message = "";
		ListView lv = null;
		String sql = (new StringBuilder("select * from ussdopprocess where id = ")).append(sessionID).toString();
		ListView c_lv = super.getRsbySQL(sql);
		c_lv.first();
		lv = super.getRsbySQL((new StringBuilder("select * from ussdmenu where pid = ")).append(c_lv.getFld("pid")).append(" order by seqid ").toString());
		if (lv != null && lv.getLen() > 0)
			while (lv.next()) 
				message = (new StringBuilder(String.valueOf(message))).append(lv.getFld("seqid")).append(".").append(lv.getFld("content")).append('\n').toString();
		return message;
	}

	public ListView getDownDict(String sessionID, String seqno)
	{
		String sql = (new StringBuilder("select * from ussdopprocess where id = ")).append(sessionID).toString();
		Log.info((new StringBuilder("down dict:")).append(sql).toString());
		ListView p_lv = super.getRsbySQL(sql);
		p_lv.first();
		String sql2 = (new StringBuilder("select * from ussdmenu where pid=")).append(p_lv.getFld("cid")).append(" and seqid = ").append(seqno).toString();
		Log.info((new StringBuilder("down menu:")).append(sql2).toString());
		ListView c_lv = super.getRsbySQL(sql2);
		return c_lv;
	}

	public ListView getUpDict(String sessionID)
	{
		String sql = (new StringBuilder("select * from ussdopprocess where id = ")).append(sessionID).toString();
		ListView p_lv = super.getRsbySQL(sql);
		p_lv.first();
		ListView c_lv = super.getRsbySQL((new StringBuilder("select * from ussdmenu where id = ")).append(p_lv.getFld("pid")).toString());
		return c_lv;
	}

	public ListView getCurrent(String sessionID)
	{
		String sql = (new StringBuilder("select * from ussdmenu where id = (select cid from ussdopprocess where id = ")).append(sessionID).append(")").toString();
		ListView p_lv = super.getRsbySQL(sql);
		p_lv.first();
		return p_lv;
	}

	public void updateCurrentDict(String sessionid, String cid, String pid, String content, String type)
	{
		String sql = (new StringBuilder("update ussdopprocess set pid =")).append(pid).append(",cid=").append(cid).append(",content='").append(content).append("',").append("type='").append(type).append("' where id = ").append(sessionid).toString();
		Log.info((new StringBuilder("update :")).append(sql).toString());
		super.ExecSQL(sql);
	}

	public static void main(String arg[])
	{
		USSDProcess up = new USSDProcess();
		ListView lv = up.getCurrent("1");
		lv.first();
	}
}
