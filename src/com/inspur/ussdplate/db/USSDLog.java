package com.inspur.ussdplate.db;

import java.io.PrintStream;
import ljwf.ListView;
import ljwf.Log;
import ljwf.db.sequences;
import ljwf.db.tbls;

public class USSDLog extends tbls
{

	public String tbname;

	public static String getOriName()
	{
		return "ussdlog";
	}

	public static String[] getOriKeys()
	{
		return (new String[] {
			"sessionid"
		});
	}

	void init()
	{
		name = getOriName();
		keys = getOriKeys();
	}

	public USSDLog()
	{
		tbname = "ussdlogseq";
		init();
	}

	public String getSessionID()
	{
		sequences seq = new sequences();
		String ap_seq_id = seq.getNext(getOriName());
		if (Integer.parseInt(ap_seq_id) > 0x7fffffff)
		{
			seq.ExecSQL((new StringBuilder("update ")).append(sequences.getOriName()).append(" set seq=1 where tb_name = '").append(getOriName()).append("'").toString());
			ap_seq_id = seq.getNext(getOriName());
		}
		return ap_seq_id;
	}

	public String setLog(String command, String receid, String message, String msisdn, String date, String app, String type, 
			String source, String ussdcid, String sucflag)
	{
		
		sequences seq = new sequences();
		String ap_seq_id = seq.getNext(getOriName());
//		Log.info("********log seq********");
		if (Integer.parseInt(ap_seq_id) > 0x7fffffff)
		{
			seq.ExecSQL((new StringBuilder("update ")).append(sequences.getOriName()).append(" set seq=1 where tb_name = '").append(getOriName()).append("'").toString());
			ap_seq_id = seq.getNext(getOriName());
		} else
		{
			String sql = (new StringBuilder("insert into ussdlog(sessionid,receid,command,message,msisdn,dealtime,app,type,source,ussdcid,sucflag) values('")).append(ap_seq_id).append("','").append(receid).append("','").append(command).append("','").append(message).append("','").append(msisdn).append("',to_date('").append(date).append("','yyyy-mm-dd hh24:mi:ss'),'").append(app).append("','").append(type).append("','").append(source).append("','").append(ussdcid).append("','").append(sucflag).append("')").toString();
//			Log.info((new StringBuilder("ap_seq_id:")).append(ap_seq_id).toString());
//			Log.info((new StringBuilder("sql:")).append(sql).toString());
			super.ExecSQL(sql);
		}
		return ap_seq_id;
	}

	public void setLog1(String sessionid, String receid, String command, String message, String msisdn, String date, String app, 
			String type, String source, String ussdcid, String sucflag)
	{
		String sql = (new StringBuilder("insert into ussdlog(sessionid,receid,command,message,msisdn,dealtime,app,type,source,ussdcid,sucflag) values('")).append(sessionid).append("','").append(receid).append("','").append(command).append("','").append(message).append("','").append(msisdn).append("',to_date('").append(date).append("','yyyy-mm-dd hh24:mi:ss'),'").append(app).append("','").append(type).append("','").append(source).append("','").append(ussdcid).append("','").append(sucflag).append("')").toString();
//		Log.info((new StringBuilder("log1:")).append(sql).toString());
		super.ExecSQL(sql);
	}

	public ListView getLog(String msisdn, String sdate, String edate, String app)
	{
		ListView lv = null;
		String sql = "select * from ussdlog where 1=1";
		if (msisdn != null && !"".equals(msisdn))
			sql = (new StringBuilder(String.valueOf(sql))).append(" and msisdn like '%").append(msisdn).append("%'").toString();
		if (sdate != null && !"".equals(sdate))
			sql = (new StringBuilder(String.valueOf(sql))).append(" and dealtime >= to_date('").append(sdate).append("','yyyy-mm-dd hh24:mi:ss')").toString();
		if (edate != null && !"".equals(edate))
			sql = (new StringBuilder(String.valueOf(sql))).append(" and dealtime <= to_date('").append(edate).append("','yyyy-mm-dd hh24:mi:ss')").toString();
		if (app != null && !"".equals(app))
			sql = (new StringBuilder(String.valueOf(sql))).append(" and app = '").append(app).append("'").toString();
		sql = (new StringBuilder(String.valueOf(sql))).append(" order by dealtime desc ").toString();
		Log.info((new StringBuilder("log query sql:")).append(sql).toString());
		lv = super.getRsbySQL(sql);
		return lv;
	}

	public String getMsisdn(String sessionid)
	{
		String msisdn = "";
		String sql = (new StringBuilder("select msisdn from ussdlog where sessionid = '")).append(sessionid).append("'").toString();
		ListView lv = super.getRsbySQL(sql);
		lv.first();
		return lv.getFld("msisdn");
	}

	public String getApp(String sessionid)
	{
		String msisdn = "";
		String sql = (new StringBuilder("select app from ussdlog where sessionid = '")).append(sessionid).append("'").toString();
		ListView lv = super.getRsbySQL(sql);
		lv.first();
		return lv.getFld("app");
	}

	public String getReceID(String sessionid)
	{
		String msisdn = "";
		String sql = (new StringBuilder("select receid from ussdlog where sessionid = '")).append(sessionid).append("'").toString();
		ListView lv = super.getRsbySQL(sql);
		lv.last();
		return lv.getFld("receid");
	}

	public int getUSSDNum()
	{
		String sql = "select count(*) from ussdlog where command ='begin' and source<>'app'";
		ListView lv = super.getRsbySQL(sql);
		lv.first();
		return Integer.parseInt(lv.getFld(0));
	}

	public String getSource(String sessionid)
	{
		String sql = (new StringBuilder("select source from ussdlog where sessionid = ")).append(sessionid).toString();
		ListView lv = super.getRsbySQL(sql);
		lv.first();
		return lv.getFld(0);
	}

	public String getUssdcID(String sessionid)
	{
		String sql = (new StringBuilder("select ussdcid from ussdlog where source='msisdn' and sessionid = ")).append(sessionid).toString();
		ListView lv = super.getRsbySQL(sql);
		lv.first();
		return lv.getFld(0);
	}

	public int[] getOnline2()
	{
		int res[] = new int[7];
		for (int i = 0; i < 7; i++)
			res[i] = 0;

		String sql = "select distinct count(*),app from ussdlog where (command<>'end' or command<>'abort')  group by app";
		ListView lv = super.getRsbySQL(sql);
		if (lv != null)
		{
			lv.beforefirst();
			String appID = "";
			while (lv.next()) 
			{
				appID = lv.getFld("app");
				if (appID.equals("0001"))
					res[0] = Integer.parseInt(lv.getFld(0));
				else
				if (appID.equals("0002"))
					res[1] = Integer.parseInt(lv.getFld(0));
				else
				if (appID.equals("0003"))
					res[2] = Integer.parseInt(lv.getFld(0));
				else
				if (appID.equals("0004"))
					res[3] = Integer.parseInt(lv.getFld(0));
				else
				if (appID.equals("0005"))
					res[4] = Integer.parseInt(lv.getFld(0));
				else
				if (appID.equals("0006"))
					res[5] = Integer.parseInt(lv.getFld(0));
				else
					res[6] = res[6] + Integer.parseInt(lv.getFld(0));
			}
		}
		return res;
	}

	public int[] getFailSession()
	{
		int res[] = new int[7];
		for (int i = 0; i < 7; i++)
			res[i] = 0;

		String sql = "select distinct count(*),app from ussdlog where sucflag = '1'  group by app";
		ListView lv = super.getRsbySQL(sql);
		if (lv != null)
		{
			lv.beforefirst();
			String appID = "";
			while (lv.next()) 
			{
				appID = lv.getFld("app");
				if (appID.equals("0001"))
					res[0] = Integer.parseInt(lv.getFld(0));
				else
				if (appID.equals("0002"))
					res[1] = Integer.parseInt(lv.getFld(0));
				else
				if (appID.equals("0003"))
					res[2] = Integer.parseInt(lv.getFld(0));
				else
				if (appID.equals("0004"))
					res[3] = Integer.parseInt(lv.getFld(0));
				else
				if (appID.equals("0005"))
					res[4] = Integer.parseInt(lv.getFld(0));
				else
				if (appID.equals("0006"))
					res[5] = Integer.parseInt(lv.getFld(0));
				else
					res[6] = res[6] + Integer.parseInt(lv.getFld(0));
			}
		}
		return res;
	}

	public boolean getWhoStart(String sessionid, String receid)
	{
		boolean flag = true;
		String sql = (new StringBuilder("select source from ussdlog where sessionid='")).append(sessionid).append("' and receid='").append(receid).append("'").toString();
		ListView lv = super.getRsbySQL(sql);
		lv.last();
		if (lv.getFld(0).equals("msisdn"))
			flag = true;
		else
			flag = false;
		return flag;
	}

	public String getLastSessionid()
	{
		ListView lv = super.getRsbySQL("select distinct  sessionid  from ussdlog ");
		lv.last();
		return lv.getFld(0);
	}

	public static void main(String arg[])
	{
		USSDLog l = new USSDLog();
		int a[] = l.getOnline2();
		for (int i = 0; i < a.length; i++)
			System.out.println((new StringBuilder(String.valueOf(i))).append(":").append(a[i]).toString());

	}
}
