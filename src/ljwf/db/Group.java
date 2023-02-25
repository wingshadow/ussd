package ljwf.db;

public class Group
  extends ljwf.db.tbls {
  final public static String ID = "id";
  final public static String NAME = "name";

  public static String getOriName() { //get origin name
	return "grp";
  }

  public static String[] getOriKeys() { //get origin keys
	return new String[] {
	  ID};
  }

  public Group() {
	super(getOriName(), getOriKeys());
  }

  public Group(ljwf.db.tbl tb) {
	super(getOriName(), getOriKeys(), tb);
  }

  public ljwf.ListView AddGroup(String id, String name, String fid) {
	String s = "INSERT INTO grp(id,name) VALUES (?,?)";
	super.Exec(s, new String[] {id, name}, null);
	s = "INSERT INTO grp_r(fid, gid,tp) VALUES (?,?,0)";
	super.Exec(s, new String[] {fid, id}, null);
	return null;
  }

  public ljwf.ListView GetGroupName(String gid) {
	String s = "SELECT name FROM grp where id=?";
	return super.getRs(s, new String[] {gid}, null);
  }

  public ljwf.ListView UpdateGroupName(String grpname, String gid) {
	String s = "update grp set name=? where id=?";
	super.Exec(s, new String[] {grpname, gid}, null);
	s = "SELECT name FROM grp WHERE id = ? ";
	super.Exec(s, new String[] {gid}, null);
	return null;
  }

  public void delGrp(String gid) { //根据gid移除用户
	String s = "delete from grp where id=?";
	super.Exec(s, new String[] {gid}, null);
//	s = "delete from grp_usr where gid=?";
//	super.Exec(s, new String[] {gid}, null);
//	String s ="delete  from grp where id=? and ( (select count(*)  from grp_usr where u_id in (select u_id from grp_usr where gid=?))>1 or (select count(*)  from grp_usr where u_id in (select u_id from grp_usr where gid=?))=0)";
//	super.Exec(s, new String[] {gid,gid,gid}, null);
  }

  public ljwf.ListView GetGroupcount(String id) {
	String s = "SELECT * FROM grp where id=?";
	return super.getRs(s, new String[] {id}, null);
  }

  public ljwf.ListView AddGroupNull(String id, String name) {
	String s = "INSERT INTO grp(id,name) VALUES (?,?)";
	super.Exec(s, new String[] {id, name}, null);
	return null;
  }

}
