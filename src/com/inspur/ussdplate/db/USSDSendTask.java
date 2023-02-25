package com.inspur.ussdplate.db;

import ljwf.ListView;
import ljwf.db.sequences;
import ljwf.db.tbls;

public class USSDSendTask extends tbls
{

	public static String getOriName()
	{
		return "ussdsendtask";
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

	public USSDSendTask()
	{
		init();
	}

	public String setUssdSendTask(String mobile, String message)
	{
		sequences seq = new sequences();
		String ap_seq_id = seq.getNext(getOriName());
		if (Integer.parseInt(ap_seq_id) > 0x7fffffff)
		{
			seq.ExecSQL((new StringBuilder("update ")).append(sequences.getOriName()).append(" set seq=1 where tb_name = '").append(getOriName()).append("'").toString());
			ap_seq_id = seq.getNext(getOriName());
		} else
		{
			String sql = (new StringBuilder("insert into ussdsendtask(id,mobile,message) values('")).append(ap_seq_id).append("','").append(mobile).append("','").append(message).append("')").toString();
			super.ExecSQL(sql);
		}
		return ap_seq_id;
	}

	public ListView getSendUssdTask(String id)
	{
		String sql = (new StringBuilder("select * from ussdsendtask where id>")).append(id).toString();
		ListView lv = super.getRs(sql);
		return lv;
	}
}
