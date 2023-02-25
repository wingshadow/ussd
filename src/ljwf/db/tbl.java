/**
 * good package
 */
package ljwf.db;

import java.io.StringReader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.jsp.*;
import oracle.sql.CLOB;
import ljwf.ListView;

public class tbl {
	/**
	 * string separator
	 */
	public static final String STRSEP = "`";

	public static final String STRSEP2 = "~@";

	public static final String STRSEP3 = "$^!";

	public CN cn = null;

	public boolean keep = false; // 是否保持连接

	public boolean returnMData = false; // 是否返回数据类型RsMData

	public static java.text.SimpleDateFormat sf = new java.text.SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss"); // normal

	public static java.text.SimpleDateFormat sfs = new java.text.SimpleDateFormat(
			"yyyy-MM-dd"); // short

	public static java.text.SimpleDateFormat sfm = new java.text.SimpleDateFormat(
			"yyyy-MM-dd HH:mm"); // middle

	public static java.text.SimpleDateFormat sfc = new java.text.SimpleDateFormat(
			"yyyyMMddHHmmss"); // continue

	public static java.text.SimpleDateFormat sfa = new java.text.SimpleDateFormat(
			"yyyyMMddHHmmssSSS"); // all

	public static java.text.SimpleDateFormat sfz = new java.text.SimpleDateFormat(
			"yyyyMMdd");

	public int iPage; // cur page,begin from 1

	public int numpp = 0; // row num per page,0 means not to split to pages

	public java.text.SimpleDateFormat fldsf; // the format of date fld,if
												// this's null,then use default
												// method to format

	public tbl() {
	}

	public void setPage(int curpage, int numperpage) {
		iPage = curpage;
		numpp = numperpage;
	}

	protected void print(Object s, java.lang.Throwable e) {
		ljwf.Log.error(s, e);
		if (cn != null) {
			cn.transerr = true;
		}
	}

	protected void print(Object s) {
		ljwf.Log.error(s);
		if (cn != null) {
			cn.transerr = true;
		}
	}

	private String getColumnValue(java.sql.ResultSet rs,
			java.sql.ResultSetMetaData rm, int i) throws Exception {
		String s = null;
		try {
			switch (rm.getColumnType(i)) {
			case java.sql.Types.TIMESTAMP: // 93
				java.sql.Timestamp t = rs.getTimestamp(i);
				if (t != null) {
					if (this.fldsf == null) {
						java.util.Calendar c = java.util.Calendar.getInstance();
						c.setTime(t);
						if (c.get(java.util.Calendar.HOUR_OF_DAY) == 0
								&& c.get(java.util.Calendar.MINUTE) == 0
								&& c.get(java.util.Calendar.SECOND) == 0) { // 日期型，不含时间
							return sfs.format(t);
						} else { // 含时间，精确日期
							return sf.format(t);
						}
					} else {
						return fldsf.format(t);
					}
				}
				break;
			case java.sql.Types.DATE: // 91
				java.sql.Date t2 = rs.getDate(i);
				if (t2 != null) {
					if (this.fldsf == null) {
						return sf.format(t2);
					} else { // 可能有问题,DB2里,比较严格
						return fldsf.format(t2);
					}
				}
				break;
			// 还有其他类型,可继续加
			case java.sql.Types.CLOB:
				java.sql.Clob clob=(rs).getClob(i);
				s = clob.getSubString(1,(int)clob.length()) ;
				break;
			default:
				s = rs.getString(i);
				break;
			}
		} catch (Exception e) {
			print("tbl.getcolvalue:", e);
		}
		if (s == null) {
			return "";
		} else {
			return s;
		}
	}

	private void setFld(java.sql.PreparedStatement st, int iParam, int type,
			String val) throws Exception {
		// iParam:st中当前需要赋的？//type:字段的类型//val:字段的值
		// 设置字段值
		if (ljwf.NStr.IsNull(val)) {
			st.setNull(iParam, type);
		} else {
			try {
				switch (type) {
				case java.sql.Types.TIMESTAMP:
					java.util.Date d;
					if (val.length() > 10) {
						d = sf.parse(val);
					} else {
						d = sfs.parse(val);
					}
					long l = d.getTime();
					java.sql.Timestamp ts = new java.sql.Timestamp(l);
					st.setTimestamp(iParam, ts);
					break;
				case java.sql.Types.TIME:
					if (val.length() > 10) {
						d = sf.parse(val);
					} else {
						d = sfs.parse(val);
					}
					l = d.getTime();
					java.sql.Time stm = new java.sql.Time(l);
					st.setTime(iParam, stm);
					break;
				case java.sql.Types.DATE:
					if (val.length() > 10) {
						d = sf.parse(val);
					} else {
						d = sfs.parse(val);
					}
					l = d.getTime();
					java.sql.Date sd = new java.sql.Date(l);
					st.setDate(iParam, sd);
				case java.sql.Types.CLOB:
					st.setCharacterStream(iParam, new StringReader(val), val.length());
				// 还有其他类型,可继续加
				default:
					st.setObject(iParam, val);
					break;
				}
			} catch (Exception e) { // error ,set null
				st.setNull(iParam, type);
				print("err.setFld:", e);
			}
		}
	}

	public ljwf.ListView getRsbySQL(String sql) {
		return getRs(sql, null, null);
	}

	private ljwf.ListView getRs(java.sql.PreparedStatement st) throws Throwable {
		// all getRs funcs invoke this func finally
		// cn has been created from out
		ljwf.ListView r = new ljwf.ListView();
		try {
			java.sql.ResultSet rs = st.executeQuery();
			// ljwf.Log.info( "st.executeQuery()---");
			java.sql.ResultSetMetaData m = rs.getMetaData();
			if (returnMData) {// 返回字段类型
				r.setRsMData(new RsMData(m));
			}
			if (rs.last()) {
				int i = rs.getRow(); // rs size
				// cur page
				ljwf.MultiPages mp = new ljwf.MultiPages(i, iPage, numpp);
				int bg = mp.getBegin() + 1; // begin row,index begin from 0 in
											// MultiPage
				int sz = mp.getSize(); // real size of this page
				r.setPage(mp);
				// prepare listview
				int h = m.getColumnCount(); // -1;
				r.setLen(sz, h);
				for (i = 1; i <= h; i++) {
					r.setDesc(m.getColumnName(i).toLowerCase(), i - 1);
				}
				// get rows
				rs.absolute(bg);
				for (i = 0; i < sz; i++, rs.next()) {
					// get columns
					for (int k = 1; k <= h; k++) {
						String t = getColumnValue(rs, m, k);
						r.setFld(i, k - 1, t);
					}
				}
			} else {
				ljwf.MultiPages mp = new ljwf.MultiPages(0, iPage, numpp);
				r.setPage(mp);
				r.setLen(0, 0);
			}
		} catch (Throwable e) {
			close();
			ljwf.Log.info("tbl.getrs(st)err:" + st.toString() + e.getMessage());
			throw e;
		}
		close();
		return r;
	}

	public ljwf.ListView getRs(String sql, String vals[], int type[]) {
		try {
			getCn();
			// ljwf.Log.info( "getCn()---");
			// ljwf.Log.info( "getRs() 229");
			if (cn == null) {
				ljwf.Log.info("cn is null");
			}

			java.sql.PreparedStatement st = cn.prepareStatement(sql,
					java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE,
					java.sql.ResultSet.CONCUR_READ_ONLY);
			// ljwf.Log.info( "getRs()---234");
			if (vals != null) {
				if (type == null || type.length == 0) {
					for (int i = 0; i < vals.length; i++) {
						setFld(st, i + 1, java.sql.Types.VARCHAR, vals[i]);
					}
					// ljwf.Log.info( "getRs()---240");
				} else {
					for (int i = 0; i < vals.length; i++) {
						setFld(st, i + 1, type[i % type.length], vals[i]);
						// ljwf.Log.info( "getRs()---244");
					}

				}
			}
			// ljwf.Log.info( "getRs()---245");
			return getRs(st);

		} catch (Exception e) {

			// ljwf.Log.info("tbl.getRs(String,String[],int[]).err:" + sql +
			// e.toString() );
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public CN getCn() {
		try {
			// ljwf.Log.info("getCn:256");
			if (null == cn) {
				cn = CN.getCn();
			}
		} catch (Exception e) {
			ljwf.Log.info("e:" + e.toString());
		}
		return cn;
	}

	protected boolean close() {
		try {
			// 要求保持CN，或在一个未完成的事务中间，不真正关闭CN
			if (cn != null && !keep && cn.trans == 0) {
				cn.close();
				cn = null;
			}
			return true;
		} catch (Throwable e) {
			print("tbl.close.err:", e);
			return false;
		}
	}

	//
	// public boolean forceclose() {
	// keep = false;
	// return close();
	// }

	public void begintrans() throws Exception {
		getCn();
		cn.begintrans();
	}

	public void commit() throws Exception {
		cn.commit();
		close();
	}

	public boolean rollback() {
		try {
			cn.rollback();
		} catch (Exception e) {
			print("tbl.rollback.err:", e);
			return false;
		}
		return close();
	}

	public void ExecSQL(String sql) {
		try {
			getCn();
			java.sql.PreparedStatement st = cn.prepareStatement(sql);
			st.execute();
		} catch (Exception e) {
			print("tbl.execsql.err:" + sql, e);
		} finally {
			close();
		}
	}

	public void ExecSQLBatch(String sql, List values) {
		try {
			getCn();
			java.sql.PreparedStatement st = cn.prepareStatement(sql);
			Iterator iter = values.iterator();

			while (iter.hasNext()) {
				String[] list = (String[]) iter.next();
				for (int i = 0; i < list.length; i++) {
					st.setString(i + 1, list[i]);
				}
				st.addBatch();
			}
			st.executeBatch();
		} catch (Exception e) {
			print("tbl.execsqlBatch.err:" + sql, e);
		} finally {
			close();
		}
	}

	private String getSysOper(Object val) {
		if (val != null) {
			String s = val.toString();
			if (s.equals(ljwf.db.sysOper.SYSDATE)) {
				// return "getdate()";
				// //oracle:sysdate;sql:getdate();mysql:now()
				//
				// return "now()";
				 return "sysdate";
//				return "current_timestamp()";
			}
		}
		return null;
	}

	protected String getSysOper(java.util.HashMap m, Object keyn) {
		return getSysOper(m.get(keyn));
	}

	public void ExecCall(String sql, String[] vals, int[] type) {
		try {
			getCn();
			java.sql.CallableStatement st = cn.prepareCall(sql);
			if (vals != null) {
				if (type == null || type.length == 0) {
					for (int i = 0; i < vals.length; i++) {
						setFld(st, i + 1, java.sql.Types.VARCHAR, vals[i]);
					}
				} else {
					for (int i = 0; i < vals.length; i++) {
						setFld(st, i + 1, type[i % type.length], vals[i]);
					}
				}
			}
			st.execute();
		} catch (Throwable e) {
			print("tbl.execcall[][].err:" + sql, e);
		} finally {
			close();
		}
	}

	public ListView ExecCallQuery(String sql, String[] vals, int[] type) {
		ljwf.ListView r = new ljwf.ListView();
		try {
			getCn();
			java.sql.CallableStatement st = cn.prepareCall(sql);
			if (vals != null) {
				if (type == null || type.length == 0) {
					for (int i = 0; i < vals.length; i++) {
						setFld(st, i + 1, java.sql.Types.VARCHAR, vals[i]);
					}
				} else {
					for (int i = 0; i < vals.length; i++) {
						setFld(st, i + 1, type[i % type.length], vals[i]);
					}
				}
			}
			r = getRs(st);
		} catch (Throwable e) {
			print("tbl.execcall[][].err:" + sql, e);
		} finally {
			close();
		}
		return r;
	}

	public void Exec(String sql, String[] vals, int[] type) {
		try {
			getCn();
			java.sql.PreparedStatement st = cn.prepareStatement(sql);
			if (vals != null) {
				if (type == null || type.length == 0) {
					for (int i = 0; i < vals.length; i++) {
						setFld(st, i + 1, java.sql.Types.VARCHAR, vals[i]);
					}
				} else {
					for (int i = 0; i < vals.length; i++) {
						setFld(st, i + 1, type[i % type.length], vals[i]);
					}
				}
			}
			st.execute();
		} catch (Throwable e) {
			print("tbl.exec[][].err:" + sql, e);
		} finally {
			close();
		}
	}

	/**
	 * 
	 * @param sql
	 *            String
	 * @param keyname
	 *            Object[]:SQL中的参数名
	 * @param mVal
	 *            HashMap:SQL中的参数的值
	 * @param rm
	 *            RsMData:参数的类型说明
	 */
	protected void Exec(String sql, Object[] keyname, java.util.HashMap mVal,
			ljwf.db.RsMData rm) {
		try {
			getCn();
			java.sql.PreparedStatement st = cn.prepareStatement(sql);
			int h = 1;
			for (int i = 0; i < keyname.length; i++) {
				if (keyname[i] != null) { // 空表示在外面的调用函数中已经取消了此参数
					setFld(st, h++, rm.getType(keyname[i].toString()), mVal
							.get(keyname[i]).toString());
				}
			}
			st.execute();
		} catch (Throwable e) {
			print("tbl.exec[]rm.err:" + sql, e);
			// print("len="+keyname.length);
			// for (int i = 0; i < keyname.length; i++) {
			// if (keyname[i] != null) { //空表示在外面的调用函数中已经取消了此参数
			// print(keyname[i]+"="+mVal.get(keyname[i]).toString()+";");
			// }
			// }
		} finally {
			close();
		}
	}

	public void setFldSf(String format) {
		fldsf = new java.text.SimpleDateFormat(format);
	}

	public void setFldSf(java.text.SimpleDateFormat format) {
		fldsf = format;
	}

	public java.text.SimpleDateFormat getFldSf() {
		return fldsf;
	}

}
