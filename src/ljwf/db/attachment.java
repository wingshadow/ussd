package ljwf.db;

public class attachment
  extends tbls {
  //附件的存放路径
  public final static String PATH_DISCUSS = "discuss";
  public final static String PATH_CALENDAR = "calendar";
  public final static String PATH_MAIL = "mail";
  public final static String PATH_TROUB = "troub";

  //模块类型编号，在附件表中的来源字段用
  public final static String SRC_CALENDAR = "0";
  public final static String SRC_NOTE = "1";
  public final static String SRC_ADVICE = "2";
  public final static String SRC_DISCUSS = "3";
  public final static String SRC_MAIL = "4";
  public final static String SRC_TROUB = "5";
  public final static String SRC_LA = "6";

  public static String getOriName() { //get origin name
	return "attachment";
  }

  public static String[] getOriKeys() { //get origin keys
	return new String[] {
	  "auto_id"};
  }

  void init() {
	name = getOriName();
	keys = getOriKeys();
  }

  public attachment() {
	init();
  }

  public attachment(CN cn) {
	this.cn = cn;
	init();
  }

  public attachment(boolean keep) {
	this.keep = keep;
	init();
  }

  public attachment(CN cn, boolean keep) {
	this.cn = cn;
	this.keep = keep;
	init();
  }

  public attachment(ljwf.db.tbl tb) {
	this.cn = tb.cn;
	this.keep = tb.keep;
	init();
  }

  public attachment(ljwf.db.tbls tb) {
	this.cn = tb.cn;
	this.keep = tb.keep;
	init();
  }

  public ljwf.ListView getAttachs(String out_id, String src_num) {
	String sql = "out_id=? and src_num=?";
	return super.getRs("*", sql, new String[] {out_id, src_num}
					   , new int[] {java.sql.Types.DECIMAL,
					   java.sql.Types.DECIMAL});
  }

  public ljwf.ListView getAttachs(String out_id[], String src_num) {
	String sql;
	if (out_id == null) {
	  return ljwf.ListView.getDefault();
	}
	else if (out_id.length == 0) {
	  return ljwf.ListView.getDefault();
	}
	else {
	  sql = "out_id in(" + ljwf.NStr.getStr("?", ",", out_id.length) + ") and src_num=?";
	}
	return super.getRs("*", sql, ljwf.NStr.Merge(out_id, src_num)
					   , new int[] {java.sql.Types.DECIMAL});
  }

  //except_id String[],不包括这些ID，需排除的记录
  public ljwf.ListView getAttachs(String out_id, String src_num,
								  String[] except_id) throws
	Exception {
	String s = "";
	if (except_id != null) {
	  for (int i = 0; i < except_id.length; i++) {
		s += "," + except_id[i];
	  }
	  if (!s.equals("")) {
		s = " and not auto_id in(" + s.substring(1) + ")";
	  }
	}
	String sql = "out_id=? and src_num=?" + s;
	return super.getRs("*", sql, new String[] {out_id, src_num}
					   , new int[] {java.sql.Types.DECIMAL,
					   java.sql.Types.DECIMAL});
  }

  public ljwf.ListView getAttachs(String[] ids) {
	String s = "";
	for (int i = 0; i < ids.length; i++) {
	  s += "," + ids[i];
	}
	if (s.equals("")) {
	  s = "0null";
	}
	s = "select * from " + name + " where auto_id in(" + s.substring(1) + ")";
	return super.getRsbySQL(s);
  }

  //del attach from db ,not del files
  public java.util.Vector delAttachs(String out_id, String src_num, java.util.Vector vFile) {
	//get records to delete
	ljwf.ListView v = getAttachs(out_id, src_num);
	//delete records
	String where = "out_id=? and src_num=?";
	super.Del(where, new String[] {out_id, src_num}
			  , new int[] {java.sql.Types.DECIMAL,
			  java.sql.Types.DECIMAL});
	//rec the file names
	if (vFile == null) {
	  vFile = new java.util.Vector();
	}
	v.beforefirst();
	for (; v.next(); ) {
	  vFile.add(v.getFld("real_name"));
	}
	return vFile;
  }

  //del attach from db ,not del files
  public java.util.Vector delAttachs(String out_id[], String src_num, java.util.Vector vFile) {
	//judge
	if (out_id == null) {
	  return vFile;
	}
	if (out_id.length == 0) {
	  return vFile;
	}
	//get records to delete
	ljwf.ListView v = getAttachs(out_id, src_num);
	//delete records
	String where = "out_id in(" + ljwf.NStr.getStr("?", ",", out_id.length) + ") and src_num=?";
	super.Del(where, ljwf.NStr.Merge(out_id, src_num)
			  , new int[] {java.sql.Types.DECIMAL});
	//rec the file names
	if (vFile == null) {
	  vFile = new java.util.Vector();
	}
	v.beforefirst();
	for (; v.next(); ) {
	  vFile.add(v.getFld("real_name"));
	}
	return vFile;
  }

  public void delAttachs(String out_id, String src_num) {
	//get records to delete
	ljwf.ListView v = getAttachs(out_id, src_num);
	//delete records
	String where = "out_id=? and src_num=?";
	super.Del(where, new String[] {out_id, src_num}
			  , new int[] {java.sql.Types.DECIMAL,
			  java.sql.Types.DECIMAL});
	//delete files
	java.io.File f;
	v.beforefirst();
	for (; v.next(); ) {
	  f = new java.io.File(v.getFld("real_name"));
	  f.delete();
	}
  }

  public void delAttachs(String[] ids) {
	//get records to delete
	ljwf.ListView v = getAttachs(ids);
	//delete records
	super.Dels(ids);
	//delete files
	java.io.File f;
	v.beforefirst();
	for (; v.next(); ) {
	  f = new java.io.File(v.getFld("real_name"));
	  f.delete();
	}
  }

  public void copyAttachs(ljwf.ListView vSrc, String des_out_id,
						  String des_src_num, String[] path) {
	try {
	  begintrans();
	  vSrc.beforefirst();
	  for (; vSrc.next(); ) {
		String n = ljwf.upload.Upload.copyFiles(path, vSrc.getFld("real_name"));
		String[] a = new String[] {
		  "attach_name", "attach_size"};
		java.util.HashMap m = new java.util.HashMap(8);
		for (int i = 0; i < a.length; i++) {
		  m.put(a[i], vSrc.getFld(a[i]));
		}
		m.put("real_name", n);
		m.put("out_id", des_out_id);
		m.put("src_num", des_src_num);
		AddWithoutKeys(m);
	  }
	  commit();
	}
	catch (Exception e) {
	  rollback();
	  print(e);
	}
  }

  public void copyAttachs(String ori_out_id, String ori_src_num,
						  String des_out_id, String des_src_num, String[] path) throws
	Exception {
	//get records to copy
	ljwf.ListView v = getAttachs(ori_out_id, ori_src_num);
	this.copyAttachs(v, des_out_id, des_src_num, path);
  }

  public void copyAttachs(String[] auto_id, String des_out_id,
						  String des_src_num, String[] path) {
	//get records to copy
	ljwf.ListView v = getAttachs(auto_id);
	this.copyAttachs(v, des_out_id, des_src_num, path);
  }
}
