package ljwf.db;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import ljwf.Log;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

import com.inspur.utils.SystemUtil;

public class CN__back {
	// db connection
	private static BasicDataSource ds = null;

	java.sql.Connection db_cn = null;

	int trans = 0; // trans num,

	boolean transerr = false; // true:中间有ROLLBACK操作，跟下来也不进行提交
	
	public static final String dbip =SystemUtil.getProperty("dbip");
	public static final String dbpwd =SystemUtil.getProperty("dbpwd");
	public static final String dbusr =SystemUtil.getProperty("dbusr");
	public static final String dbname = SystemUtil.getProperty("dbname");
	public static final String maxActive = SystemUtil.getProperty("maxActive");
	public static final String maxIdle = SystemUtil.getProperty("maxIdle");
	public static final String maxWait = SystemUtil.getProperty("maxWait");
	public static final String removeAbandoned = SystemUtil.getProperty("removeAbandoned");
	public static final String removeAbandonedTimeout = SystemUtil.getProperty("removeAbandonedTimeout");
	public static final String testOnBorrow = SystemUtil.getProperty("testOnBorrow");
	public static final String testOnReturn = SystemUtil.getProperty("testOnReturn");
	public static final String testWhileIdle = SystemUtil.getProperty("testWhileIdle");
	public static final String validationQuery = SystemUtil.getProperty("validationQuery");
	public static final String logAbandoned = SystemUtil.getProperty("logAbandoned");

	public static void init() {
		if (null != ds) {
			try {
				ds.close();
			} catch (Exception e) {
				//
			}
			ds = null;
		}

		try {
			Properties p = new Properties();
			p.setProperty("driverClassName", "org.gjt.mm.mysql.Driver");
			p.setProperty("url","jdbc:mysql://" + dbip + ":3306/" + dbname + "?useUnicode=true&characterEncoding=gbk&zeroDateTimeBehavior=convertToNull");
			p.setProperty("password", dbpwd);
			p.setProperty("username", dbusr);
			p.setProperty("maxActive", maxActive);
			p.setProperty("maxIdle", maxIdle);
			p.setProperty("maxWait", maxWait);
			p.setProperty("removeAbandoned", removeAbandoned);
			p.setProperty("removeAbandonedTimeout", removeAbandonedTimeout);
			p.setProperty("testOnBorrow", testOnBorrow);
			p.setProperty("testOnReturn", testOnReturn);
			p.setProperty("testWhileIdle", testWhileIdle);
			p.setProperty("validationQuery", validationQuery);
			p.setProperty("logAbandoned", logAbandoned);

			ds = (BasicDataSource) BasicDataSourceFactory.createDataSource(p);

		} catch (Exception e) {
			//
		}
	}

	public synchronized static CN__back getCn() throws Exception {
		// get a new cn
		java.sql.Connection cn = null;
		// 用DATASOURCE的方式连接数据库
		if (true) {
			if (null == ds) {
				init();
			}
			cn = ds.getConnection();
		}
		if (false) {
			Context ctx = new InitialContext();// 命名服务
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mas");
			cn = ds.getConnection();
		}
		if (false) {
			Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
			String url = "jdbc:microsoft:sqlserver://bi_server1:1433;SelectMethod=Cursor;databasename=ekp";
			String username = "sa"; // 10.16.255.254
			String password = "sa";
			cn = java.sql.DriverManager.getConnection(url, username, password);
		}
		if (false) {
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			
			String url = "jdbc:mysql://" + dbip + "/" + dbname + "?user=" +
			dbusr + "&password=" + dbpwd;
			url = url +"&useUnicode=true&characterEncoding=gbk&zeroDateTimeBehavior=convertToNull";
			cn = java.sql.DriverManager.getConnection(url);
		}
		return InsertCn(cn);
	}

	public CN__back(java.sql.Connection cn) {
		db_cn = cn;
	}

	public CN__back() {
		// db_cn = cn;
	}

	static CN__back InsertCn(java.sql.Connection cn) {
		return new CN__back(cn);
	}

	public java.sql.Connection get() {
		return db_cn;
	}

	public void close() throws java.lang.Throwable {
		db_cn.close();
	}

	public java.sql.PreparedStatement prepareStatement(String s)
			throws Exception {
		return db_cn.prepareStatement(s);
	}

	public java.sql.PreparedStatement prepareStatement(String s, int type,
			int concurrency) throws Exception {
		return db_cn.prepareStatement(s, type, concurrency);
	}

	public java.sql.CallableStatement prepareCall(String s) throws Exception {
		return db_cn.prepareCall(s);
	}

	public void begintrans() throws Exception {
		if (trans == 0) {
			db_cn.setAutoCommit(false);
			transerr = false;
		}
		trans++;
	}

	public void commit() throws Exception {
		if (trans > 0) { // ==0,说明外部有错,不要执行操作
			trans--;
			if (trans == 0) {
				if (transerr) { // 中间有ROLLBACK操作，跟下来也不进行提交
					db_cn.rollback();
					transerr = false;
				} else {
					db_cn.commit();
				}
				db_cn.setAutoCommit(true);
			}
		}
	}

	public void rollback() throws Exception {
		if (trans > 0) {
			trans--;
			if (trans == 0) {
				db_cn.rollback();
				db_cn.setAutoCommit(true);
				transerr = false;
			} else {
				transerr = true; // 标记：中间有ROLLBACK操作，跟下来也不进行提交
			}
		}
	}
}
