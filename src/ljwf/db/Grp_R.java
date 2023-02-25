package ljwf.db;

public class Grp_R
  extends ljwf.db.tbls {
  final public static String ID = "id";
  final public static String GID = "gid";
  final public static String FID = "fid";
  final public static String MID = "mid";

  public static String getOriName() { //get origin name
	return "grp_r";
  }

  public static String[] getOriKeys() { //get origin keys
	return new String[] {
	  ID};
  }

  public Grp_R() {
	super(getOriName(), getOriKeys());
  }

  public Grp_R(ljwf.db.tbl tb) {
	super(getOriName(), getOriKeys(), tb);
  }

  public ljwf.ListView getGrpAndUsrs() {
//	select id,name,0 as tp
//	from grp g
//	where not exists(select * from grp_r where gid=g.id and tp=0)
	String s = "select id,name,0 as tp from " + ljwf.db.Group.getOriName() +
	  " g where not exists(select * from " + name + " where gid=g.id and tp=0)";
	return super.getRs(s, null, null);
  }

  public ljwf.ListView count() {
//	select id,name,0 as tp
//	from grp g
//	where not exists(select * from grp_r where gid=g.id and tp=0)
	String s = "select * from grp_r where gid=fid";
	return super.getRs(s, null, null);
  }

  public ljwf.ListView getGrpAndUsrs(String fid) {
//	select g.id,g.name,0 as tp
//	from grp g,grp_r r
//	where r.fid=f_id and r.tp=0 and g.id=r.gid
//	union
//	select u.id,u.name,1
//	from usr u,grp_usr r
//	where r.gid=f_id and u.id=r.uid
	String s = "select g.id,g.name,0 as tp" +
	  " from " + Group.getOriName() + " g," + Grp_R.getOriName() + " r" +
	  " where r.fid=? and r.tp=0 and g.id=r.gid" +
	  " union " +
	  " select u.id,u.name,1" +
	  " from " + User.getOriName() + " u," + Grp_Usr.getOriName() + " r" +
	  " where r.gid=? and u.id=r.u_id" +
	  " order by tp";
	return super.getRs(s, new String[] {fid, fid}, null);
  }

  public ljwf.ListView yichuGrps(String gid) {
	String s = "update grp_r set fid='lclg' where gid=? and tp=0";
	super.Exec(s, new String[] {gid}, null);
	return null;
  }

  public ljwf.ListView moveaddGrps(String fid, String gid) {
	String s = "INSERT INTO grp_r (fid,gid,tp) VALUES (?,?,0)";
	super.Exec(s, new String[] {fid, gid}, null);
	return null;
  }

  public ljwf.ListView movedelGrps(String fid, String gid) {
	String s = "delete from grp_r  where fid=? and gid=? ";
	super.Exec(s, new String[] {fid, gid}, null);
	return null;
  }

  /**
   * 得祖先ID与NAME的路径,不包括自己
   * @param gid String
   * @return String[]
   * 0:gid path:,lclg,mssd
   * 1:gname path:/浪潮乐金/管理支撑部
   */
  public String[] getPath(String gid) {
	String[] s = new String[] {
	  "", ""};
	String sql =
	  "select r.fid,g.name from grp_r r left join grp g on r.fid=g.id where r.gid=?";
	ljwf.ListView v;
	for (; ; ) {
	  v = this.getRs(sql, new String[] {gid}, null);
	  if (!v.next()) {
		break;
	  }
	  s[0] += "," + v.getFld(0);//id
	  s[1] += "/" + v.getFld(1);//name
	  gid = v.getFld(0);
	}
	return s;
  }

}
