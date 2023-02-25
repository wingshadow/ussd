package ljwf.db;

public class tbls
  extends tbl {
  public String name; //table name
  public String keys[]; //primary key names,if have auto_id,must be the first
  public int types[]; //primary key types
  public ljwf.db.RsMData rm; //desc of table flds

  void init(String name, String[] keys) {
	this.name = name;
	this.keys = keys;
  }

  public tbls() {
  }

  public tbls(String name, String[] keys) {
	this.name = name;
	this.keys = keys;
  }

  public tbls(String name, String[] keys, CN cn) {
	this.cn = cn;
	init(name, keys);
  }

  public tbls(String name, String[] keys, boolean keep) {
	this.keep = keep;
	init(name, keys);
  }

  public tbls(String name, String[] keys, CN cn, boolean keep) {
	this.cn = cn;
	this.keep = keep;
	init(name, keys);
  }

  public tbls(String name, String[] keys, ljwf.db.tbl tb) {
	this.cn = tb.cn;
	this.keep = tb.keep;
	init(name, keys);
  }

  public tbls(ljwf.db.tbls tb) {
	this.cn = tb.cn;
	this.keep = tb.keep;
	init(tb.name, tb.keys);
  }

  public String getName() {
	return name;
  }

  public String[] getKeys() {
	return keys;
  }

  /**
   * 开连接不关,使用要小心,禁止类外使用
   * @return int[]
   */
  private int[] getTypes() {
	if (types == null) {
	  types = new int[keys.length];
	  getRsMData(); //may modi
	  for (int i = 0; i < keys.length; i++) {
		types[i] = rm.getType(keys[i]);
	  }
	}
	return types;
  }

  /**
   *开连接不关,使用要小心,禁止类外使用
   * @return RsMData
   */
  private ljwf.db.RsMData getRsMData() {
	if (rm == null) {
	  try {
		getCn();
		java.sql.PreparedStatement st = cn.prepareStatement("select * from " +
		  name + " where 1=2");
		rm = new ljwf.db.RsMData(st.executeQuery().getMetaData());
	  }
	  catch (Exception e) {
		rm = null;
		print("tbl.getMetaData.err:" + name, e);
	  }
	}
	return rm;
  }

  public String getSQL(String list) {
	if (list == null) {
	  list = "*";
	}
	String s = "";
	for (int i = 0; i < keys.length; i++) {
	  s += " and " + keys[i] + "=?";
	}
	s = "select " + list + " from " + name + " where 1=1" + s;
	return s;
  }

  public ljwf.ListView getRs(String list, String where, String[] vals,
							 int[] types) { //all getRs funcs through this proc to super func
	if (list == null) {
	  list = "*";
	}
	if (where == null) {
	  where = "1=1";
	}
	String s = "select " + list + " from " + name + " where " + where;
	return super.getRs(s, vals, types);
  }

  public ljwf.ListView getRs(String list, String[] keyvals) {
	return super.getRs(getSQL(list), keyvals, getTypes());
  }

  public ljwf.ListView getRs() {
	return this.getRs(null, null, null, null);
  }

  public ljwf.ListView getRs(String list, String keyval) { //single key
	return getRs(list, new String[] {keyval});
  }

  public ljwf.ListView getRs(String[] keyvals) { //multi keys
	return getRs("*", keyvals);
  }

  public ljwf.ListView getRs(String keyval) {
	return getRs("*", keyval);
  }

  //输入各字段的名与值
  public void AddWithKeys(java.util.HashMap m) {
	//m,包括各字段的值
	try {
	  //得连接
	  getCn();
	  //得表结构
	  getRsMData();
	  //构造tbls插入串
	  String s = "";
	  String t = "";
	  Object v[] = m.keySet().toArray();
	  int k = v.length;
	  for (int i = 0; i < k; i++) {
		s += "," + v[i];
		String u = m.get(v[i]).toString();
		if (u.equals(ljwf.db.sysOper.AUTOID)) { //=auto_id
		  t += ",?";
		  m.remove(v[i]);
		  m.put(v[i], m.get(this.keys[0]));
		}
		else {
		  u = getSysOper(m, v[i]);
		  if (u != null) {
			t += "," + u;
			m.remove(v[i]);
			v[i] = null;
		  }
		  else {
			t += ",?";
		  }
		}
	  }
	  s = s.substring(1);
	  t = t.substring(1);
	  s = "insert into " + name + "(" + s +
		") values(" + t + ")";
	  super.Exec(s, v, m, rm);
	}
	catch (Exception e) {
	  print("tbls.add.err:", e);
	}
	finally {
	  close();
	}
  }

  public String getSeq() {
	return "seq_" + name;
  }

  //输入各字段的名与值,返回主键
  public String AddWithoutKeys(java.util.HashMap m) {
	//得自动生成的主键
	String id = new ljwf.db.sequences().getNext(name);
	m.put(this.keys[0], id);
	this.AddWithKeys(m);
	return id;
  }

  //输入各字段的名与值
  public void Update(java.util.HashMap m) {
	String tbls_id = null;
	try {
	  //得连接
	  getCn();
	  //构造tbls插入串
	  String s = "";
	  String t = "";
	  Object v[] = m.keySet().toArray();
	  String v2[] = new String[v.length];
	  int h, k;
	  h = 0;
	  k = v.length - keys.length;
	  for (int i = 0; i < v.length; i++) {
		if (ljwf.NStr.Find(keys, v[i].toString())) { //it's key
		  v2[k++] = v[i].toString();
		  t += " and " + v[i] + "=?";
		}
		else { //the flds needing update
		  String u = getSysOper(m, v[i]);
		  if (u != null) {
			s += "," + v[i] + "=" + u;
			m.remove(v[i]);
			v2[h++] = null;
		  }
		  else {
			v2[h++] = v[i].toString();
			s += "," + v[i] + "=?";
		  }
		}
	  }
	  s = s.substring(1);
	  s = "update " + name + " set " + s +
		" where 1=1" + t;
	  super.Exec(s, v2, m, this.getRsMData());
	}
	catch (Exception e) {
	  print("tbls.update.err:", e);
	}
	finally {
	  close();
	}
  }

  public void Del(String[] keyval) {
	String s = ""; //sql
	for (int i = 0; i < keys.length; i++) {
	  s = " and " + keys[i] + "=?";
	}
	s = "delete from " + name + " where 1=1" + s;
	super.Exec(s, keyval, getTypes());
  }

  public void Del(String key) {
	Del(new String[] {key});
  }

  public void Dels(String[] keyval) {
	if (keyval == null) {
	  return;
	}
	if (keyval.length == 0) {
	  return;
	}
	//if multi keys,fill in keyval in order
	try {
	  //得连接
	  getCn();
	  //调用语句
	  String s = ""; //sql
	  if (keys.length == 1) { //single key
		for (int i = 0; i < keyval.length; i++) {
		  s += ",?";
		}
		s = "delete from " + name + " where " + keys[0] + " in(" +
		  s.substring(1) +
		  ")";
	  }
	  else { //multi keys
		String t = "";
		for (int i = 0; i < keys.length; i++) {
		  t += " and " + keys[i] + "=?";
		}
		t = "or(" + t.substring(5) + ")";
		int k = keyval.length / keys.length;
		for (int i = 0; i < k; i++) {
		  s += t;
		}
		s = s.substring(2);
		s = "delete from " + name + " where " + s;
	  }
	  super.Exec(s, keyval, getTypes());
	}
	catch (Exception e) {
	  print("tbls.dels.err:", e);
	}
	finally {
	  close();
	}
  }

  public void Dels(String keyval, String spliter) {
	//get id[]
	String[] val = ljwf.NStr.split(keyval, spliter);
	Dels(val);
  }

  public void Dels(String keyval) {
	Dels(keyval, ljwf.db.tbl.STRSEP);
  }

  public void Del(String where, String[] vals,
				  int[] types) { //del recs where ...
	String s = "delete from " + name + " where " + where;
	super.Exec(s, vals, types);
  }

  public ljwf.ListView getRsMax() {
	String s = "select * from " + name + " where " + keys[0] + "=(select max(" +
	  keys[0] + ") from " + name + ")";
	return getRsbySQL(s);
  }

  public ljwf.ListView getRsMin() {
	String s = "select * from " + name + " where " + keys[0] + "=(select min(" +
	  keys[0] + ") from " + name + ")";
	return getRsbySQL(s);
  }

  public ljwf.ListView getRsMax(String fld) {
	String s = "select * from " + name + " where " + fld + "=(select max(" +
	  fld + ") from " + name + ")";
	return getRsbySQL(s);
  }

  public ljwf.ListView getRsMin(String fld) {
	String s = "select * from " + name + " where " + fld + "=(select min(" +
	  fld + ") from " + name + ")";
	return getRsbySQL(s);
  }
}
