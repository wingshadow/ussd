package ljwf.db;

import ljwf.*;

public class User
  extends ljwf.db.tbls {
  final public static String ID = "id";
  final public static String NAME = "name";

  public static String getOriName() { //get origin name
	return "usr";
  }

  public static String[] getOriKeys() { //get origin keys
	return new String[] {
	  ID};
  }

  public User() {
	super(getOriName(), getOriKeys());
  }

  public User(ljwf.db.tbl tb) {
	super(getOriName(), getOriKeys(), tb);
  }

  public ljwf.ListView getUser(String user, String pwd) {
	return super.getRs(null, "id=? and pwd=?", new String[] {user, pwd}, null);
  }

  public ljwf.ListView getUser(java.util.HashMap m) {
	if (m.size() == 0) {
	  return ljwf.ListView.getDefault(); //""为了返回0条记录
	}
	else {
	  java.util.Iterator i = m.keySet().iterator();
	  String s[] = new String[m.size()];
	  for (int k = 0; i.hasNext(); k++) {
		s[k] = i.next().toString();
	  }
	  return super.getRs("id,name",
						 "id in(" + ljwf.NStr.getStr("?", ",", m.size()) +
						 ")", s, null);
	}
  }

  /**
   * getUserInfoForExpert
   * 获取用户信息，给专家维护用的。author： 阎强
   * @return ListView
   */
  public ListView getUserInfoForExpert() {
	String sql = "select id,name,email,phone,mobile from usr order by name";

	return super.getRsbySQL(sql);
  }

  public ljwf.ListView addusr(String id, String name, String pwd, String gid) {

	String s = "INSERT INTO usr(id, name,pwd) VALUES (?,?,?)";
	super.Exec(s, new String[] {id, name, pwd}, null);
//	  s = "INSERT INTO grp_usr(gid, u_id) VALUES (?,?)";
//	 super.Exec(s, new String[] {gid,id}, null);
	s = "insert into grp_usr(gid,u_id) select ?,? from dual where not exists(select * from grp_usr where gid=? and u_id=?)";
	super.Exec(s, new String[] {gid, id, gid, id}, null);
	return null;

  }

  public ljwf.ListView changeusr(String id, String usrname) {

	String s = "update usr set name=? where id=?";
	super.Exec(s, new String[] {usrname, id}, null);
	s = "SELECT name FROM usr WHERE id = ? ";
	super.Exec(s, new String[] {id}, null);
	return null;


}


  public void delUsr(String gid) { //根据gid移除用户
//   delete  grp_mtn  where gid='ld1' and u_id='ly'
	String s = "delete from usr where id=?";
	super.Exec(s, new String[] {gid}, null);

  }
  public ljwf.ListView GetUsrcount(String id) {
  String s = "SELECT * FROM usr where id=?";
  return super.getRs(s, new String[] {id}, null);
}

}
