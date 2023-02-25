// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   UssdMenuDb.java

package com.inspur.ussdmenu;

import java.io.PrintStream;
import ljwf.*;
import ljwf.db.sequences;
import ljwf.db.tbls;

public class UssdMenuDb extends tbls
{

	public static String getOriName()
	{
		return "ussdmenu";
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

	public UssdMenuDb()
	{
		init();
	}

	public ListView getFristMenu()
	{
		ListView lv = null;
		lv = super.getRsbySQL("select * from ussdmenu where pid=1 order by seqid");
		return lv;
	}

	public ListView getUSSDMenu()
	{
		ListView lv = null;
		lv = super.getRs();
		return lv;
	}

	public ListView getMenuByID(int id)
	{
		ListView lv = null;
		lv = super.getRsbySQL((new StringBuilder("select * from ussdmenu where pid = ")).append(id).append(" order by seqid,id").toString());
		return lv;
	}

	public ListView getMenuByID(String id)
	{
		ListView lv = null;
		lv = super.getRsbySQL((new StringBuilder("select * from ")).append(name).append(" where pid=").append(id).append(" order by seqid,id").toString());
		return lv;
	}

	public ListView getAllById(String id)
	{
		ListView lv = null;
		String sql = (new StringBuilder("select * from ")).append(name).append(" where id=").append(id).toString();
		lv = super.getRsbySQL(sql);
		return lv;
	}

	public ListView getReById(String id)
	{
		ListView lv = null;
		String sql = (new StringBuilder("select * from ")).append(name).append(" where pid=").append(id).toString();
		lv = super.getRsbySQL(sql);
		return lv;
	}

	public ListView getMenu()
	{
		ListView lv = null;
		lv = super.getRsbySQL("select * from ussdmenu  order by id");
		return lv;
	}

	public String getCurrentID(int parentID, int seqID)
	{
		ListView lv = null;
		String id = "0";
		lv = super.getRsbySQL((new StringBuilder("select * from ussdmenu where pid = ")).append(parentID).append(" and seqid = ").append(seqID).toString());
		if (lv != null && lv.getLen() > 0)
			id = lv.getFld("id");
		return id;
	}

	public ListView getChildMenuByPid(int pid)
	{
		ListView lv = null;
		lv = super.getRsbySQL((new StringBuilder("select * from ussdmenu where pid= ")).append(pid).append(" order by seqid").toString());
		return lv;
	}

	public void setUssdMenu(String pid, String seqid, String menu, String type)
	{
		String sql = "";
		sequences seq = new sequences();
		String id = seq.getNext(getOriName());
		sql = (new StringBuilder("insert into ussdmenu(id,pid,content,seqid,type) values(")).append(id).append(",").append(pid).append(",'").append(menu).append("',").append(seqid).append(",'").append(type).append("')").toString();
		super.ExecSQL(sql);
	}

	public void updateMenu(String id, String seqid, String menu, String type)
	{
		String sql = (new StringBuilder("update ussdmenu set seqid=")).append(seqid).append(",content='").append(menu).append("',type='").append(type).append("' where id = ").append(id).toString();
		super.ExecSQL(sql);
	}

	public void delUssdMenu(String ids)
	{
		String sql = "";
		String tmp = "";
		if (ids.indexOf(",") > 0)
		{
			String s[] = NStr.split(ids, ",");
			for (int i = 0; i < s.length; i++)
			{
				String t = getchildNode(s[i]);
				if (t.indexOf(",") > 0)
				{
					String a[] = NStr.split(t, ",");
					if (a.length > 1)
					{
						String b = "";
						for (int j = 0; j < a.length; j++)
							if (!"".equals(a[j]))
								b = (new StringBuilder(String.valueOf(a[j]))).append(",").append(b).toString();

						tmp = (new StringBuilder(String.valueOf(ids))).append(",").append(s[i]).append(",").append(b.substring(0, b.lastIndexOf(","))).toString();
						sql = (new StringBuilder("delete from ussdmenu where id in(")).append(tmp).append(")").toString();
					} else
					{
						sql = (new StringBuilder("delete from ussdmenu where id in(")).append(a[0]).append(",").append(ids).append(")").toString();
					}
					super.ExecSQL(sql);
				} else
				{
					sql = (new StringBuilder("delete from ussdmenu where id in(")).append(t).append(",").append(ids).append(")").toString();
					super.ExecSQL(sql);
				}
			}

		} else
		{
			String t = getchildNode(ids);
			if (t.indexOf(",") > 0)
			{
				String a[] = NStr.split(t, ",");
				if (a.length > 1)
				{
					String b = "";
					for (int j = 0; j < a.length; j++)
						if (!"".equals(a[j]))
							b = (new StringBuilder(String.valueOf(a[j]))).append(",").append(b).toString();

					tmp = (new StringBuilder(String.valueOf(ids))).append(",").append(b.substring(0, b.lastIndexOf(","))).toString();
					sql = (new StringBuilder("delete from ussdmenu where id in(")).append(tmp).append(")").toString();
				} else
				{
					sql = (new StringBuilder("delete from ussdmenu where id in(")).append(a[0]).append(",").append(ids).append(")").toString();
				}
				super.ExecSQL(sql);
			} else
			{
				sql = (new StringBuilder("delete from ussdmenu where id in(")).append(t).append(",").append(ids).append(")").toString();
				super.ExecSQL(sql);
			}
		}
	}

	public String getchildNode(String id)
	{
		String ids = "";
		String sql = (new StringBuilder("select id,pid from ussdmenu where pid = ")).append(id).toString();
		ListView lv = super.getRsbySQL(sql);
		if (lv.getLen() > 0)
			while (lv.next()) 
			{
				ids = (new StringBuilder(String.valueOf(lv.getFld(0)))).append(",").append(ids).toString();
				String tmp = getchildNode(lv.getFld(0));
				if (!tmp.equals(lv.getFld(0)))
					ids = (new StringBuilder(String.valueOf(ids))).append(",").append(tmp).toString();
			}
		else
			ids = id;
		return ids;
	}

	public ListView getUSSDMenu(String id, String seq[])
	{
		String pid = id;
		ListView lv = null;
		for (int i = 0; i < seq.length; i++)
		{
			lv = getMenu(pid, seq[i]);
			lv.first();
			pid = lv.getFld("id");
		}

		lv = getMenu(pid, null);
		if (lv == null || lv.getLen() == 0)
			lv = super.getRs(pid);
		return lv;
	}

	public ListView getMenu(String pid, String seq)
	{
		String sql = (new StringBuilder("select * from ussdmenu where pid=")).append(pid).toString();
		if (seq != null && !seq.equals(""))
			sql = (new StringBuilder(String.valueOf(sql))).append(" and seqid = ").append(seq).toString();
		Log.info((new StringBuilder("get menu sql:")).append(sql).toString());
		ListView lv = super.getRsbySQL(sql);
		return lv;
	}

	public String getRsByPid(String pid)
	{
		String fmenu = "";
		String sql = (new StringBuilder("select id,pid from ")).append(name).append(" where id = ").append(pid).toString();
		ListView lv = getRsbySQL(sql);
		if (lv != null && lv.getLen() > 0)
			while (lv.next()) 
				fmenu = lv.getFld("pid");
		return fmenu;
	}

	public String getTypeByID(String id)
	{
		String type = "";
		String sql = (new StringBuilder("select type from ")).append(name).append(" where id = ").append(id).toString();
		ListView lv = getRsbySQL(sql);
		if (lv != null && lv.getLen() > 0)
			while (lv.next()) 
				type = lv.getFld("type");
		return type;
	}

	public boolean getFlagByServiceid(String trim, String id)
	{
		boolean flag = false;
		String sql = (new StringBuilder("select id from ")).append(name).append(" where serviceid = '").append(trim).append("' and id != '").append(id).append("'").toString();
		ListView lv = getRsbySQL(sql);
		if (lv != null && lv.getLen() > 0)
		{
			if (lv.next())
				flag = true;
		} else
		{
			flag = false;
		}
		return flag;
	}

	public ListView getSearchUSSDMenu()
	{
		String sql = "select * from ussdmenu where flag = 1";
		ListView lv = super.getRsbySQL(sql);
		return lv;
	}

	public ListView getInfoByServiceId(String serviceid)
	{
		String sql = (new StringBuilder("select id from ")).append(name).append(" where serviceid = '").append(serviceid).append("'").toString();
		return getRsbySQL(sql);
	}

	public String getSearchByKeyword(String keyword)
	{
		String tmp = "";
		String sql = "select * from ussdmenu where 1=1 and flag = 1 and type=1 ";
		if (keyword != null && !keyword.equals(""))
		{
			if (keyword.indexOf(",") != -1)
			{
				String a[] = keyword.split(",");
				for (int i = 0; i < a.length; i++)
					if (i == 0)
						sql = (new StringBuilder(String.valueOf(sql))).append(" and (keyword like '%").append(a[i]).append("%'").toString();
					else
					if (i == a.length - 1)
						sql = (new StringBuilder(String.valueOf(sql))).append(" or keyword like '%").append(a[i]).append("%')").toString();
					else
						sql = (new StringBuilder(String.valueOf(sql))).append(" or keyword like '%").append(a[i]).append("%'").toString();

			} else
			{
				sql = (new StringBuilder(String.valueOf(sql))).append("and  keyword like '%").append(keyword).append("%'").toString();
			}
			System.out.println(sql);
			ListView lv = super.getRsbySQL(sql);
			if (lv != null && lv.getLen() > 0)
			{
				for (int i = 1; lv.next(); i++)
				{
					tmp = (new StringBuilder(String.valueOf(tmp))).append(i).append(".").append(lv.getFld("content")).append('\n').toString();
					if (tmp.length() > 80)
						break;
				}

			}
		}
		return tmp;
	}

	public static void main(String arg[])
	{
		UssdMenuDb u = new UssdMenuDb();
		String message = u.getSearchByKeyword("aa,bb");
		System.out.println(message);
	}
}
