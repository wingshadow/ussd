package ljwf.db;

public class Grp_Usr
  extends ljwf.db.tbls {
  final public static String ID = "id";
  final public static String GID = "gid";
  final public static String UID = "u_id";

  public static String getOriName() { //get origin name
	return "grp_usr";
  }

  public static String[] getOriKeys() { //get origin keys
	return new String[] {
	  ID};
  }

  public Grp_Usr() {
	super(getOriName(), getOriKeys());
  }

  public Grp_Usr(ljwf.db.tbl tb) {
	super(getOriName(), getOriKeys(), tb);
  }

  public String[] getGroups(String user) {
	ljwf.ListView v = getRs(GID, UID + "=?", new String[] {user}, null);
	return v.getFlds(0);
  }

  public String[] getUsers(String group) {
	ljwf.ListView v = getRs(UID, GID + "=?", new String[] {group}, null);
	return v.getFlds(0);
  }

  public ljwf.ListView yichuUsers(String gid, String u_id) {

	String s = "delete from grp_usr  where gid=? and u_id=?";
	super.Exec(s, new String[] {gid, u_id}, null);
	return null;

  }

  public ljwf.ListView moveUsers(String gid, String u_id) {

	String s = "update grp_usr set gid=? where u_id=?";
	super.Exec(s, new String[] {gid, u_id}, null);
	return null;

  }

  public ljwf.ListView tianjiaUsers(String gid, String u_id) {

//	String s = "INSERT INTO grp_usr (gid,u_id) VALUES (?,?)";
	String s = "insert into grp_usr(gid,u_id) select ?,? from dual where not exists(select * from grp_usr where gid=? and u_id=?)";
	super.Exec(s, new String[] {gid, u_id,gid,u_id}, null);
	return null;

  }

  public ljwf.ListView getcountGid(String u_id) { //查看移出的时候grpusr里的记录数

	String s = "select gid from grp_usr where u_id=? ";

	return super.getRs(s, new String[] {u_id}, null);

  }
  public ljwf.ListView getcountUsr(String gid) {

  String s = "select u_id from grp_usr where gid=? ";

  return super.getRs(s, new String[] {gid}, null);

}


  public ljwf.ListView getexistusr(String gid, String u_id) {

	String s = "select * from grp_usr where gid=? and u_id=? ";

	return super.getRs(s, new String[] {gid, u_id}, null);

  }

  public ljwf.ListView count() {

	String s = "select * from usr where id not in (select u_id from grp_usr )";
//	super.Exec(s, new String[] {}, null);
//	return null;
	return super.getRs(s, new String[] {}, null);
  }

}
