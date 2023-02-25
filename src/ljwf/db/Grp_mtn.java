package ljwf.db;

public class Grp_mtn
  extends ljwf.db.tbls {
  final public static String ID = "id";
  final public static String GID = "gid";
  final public static String UID = "u_id";

  public static String getOriName() { //get origin name
	return "grp_mtn";
  }

  public static String[] getOriKeys() { //get origin keys
	return new String[] {
	  ID};
  }

  public Grp_mtn() {
	super(getOriName(), getOriKeys());
  }

  public ljwf.ListView getGrp(String u_id) { //通过grp_mtn表控制权限
//	select m.gid,g.name,0 tg
//	from grp_mtn m ,grp g
//	where m.gid=g.id and u_id=? and
//	not exists (select gid from grp_r r where m.gid=r.gid and fid in(select gid from grp_mtn where u_id=?) )
	String s = "select m.gid as gid,g.name,0 tg " + "from grp_mtn m ,grp g " +
	  "where m.gid=g.id and u_id=? and " +
	  "not exists (select gid from grp_r r where m.gid=r.gid and fid in(select gid from grp_mtn where u_id=?) )";
	return super.getRs(s, new String[] {u_id, u_id}, null);
  }

  public ljwf.ListView getsubGrpandUsr(String u_id) { //根据用户uid得到子组
	String s = "(select  g.name, g.id gid ,0 tg from grp_mtn m,grp_r r,grp g where  m.gid=r.fid and r.tp=0 and r.gid=g.id and m.u_id=?) " +
	  " union " +
	  " (select u.name, gr.u_id gid, 1 tg from grp_usr gr, usr u where  u.id=gr.u_id and gr.gid in (select gid from grp_mtn m where u_id=? and not exists (select gid from grp_r r where m.gid=r.gid and fid in(select gid from grp_mtn where u_id=?) )))" +
	  "order by tg ";
	return super.getRs(s, new String[] {u_id, u_id, u_id}, null);
  }

  public ljwf.ListView getUsrsAndGrp(String gid) { //根据gid得到用户
//	(select r.name,r.id gid,1 tg
//	from grp_mtn m, usr r
//	where m.u_id=r.id and
//		  m.gid='ld')
//	union
//(select g.name,g.id  gid ,0 tg from grp_r r left join grp g on r.gid=g.id where r.fid='ld' and r.tp=0)
//order by tg

	String s = "(select r.name,r.id gid,1 tg from grp_mtn m, usr r where m.u_id=r.id and m.gid=?)"
	  + "union" + "(select g.name,g.id  gid ,0 tg from grp_r r left join grp g on r.gid=g.id where r.fid=? and r.tp=0)" +
	  "order by tg";
	return super.getRs(s, new String[] {gid, gid}, null);
  }

  public ljwf.ListView getUsrsAndGrpAll(String gid) { //根据gid得到用户
//	(select r.name,r.id gid,1 tg
//	from grp_usr m, usr r
//	where m.u_id=r.id and
//		  m.gid='ld')
//	union
//(select g.name,g.id  gid ,0 tg from grp_r r left join grp g on r.gid=g.id where r.fid='ld' and r.tp=0)
//order by tg

	String s = "(select r.name,r.id gid,1 tg from grp_usr m, usr r where m.u_id=r.id and m.gid=?)"
	  + "union" + "(select g.name,g.id  gid ,0 tg from grp_r r left join grp g on r.gid=g.id where r.fid=? and r.tp=0)" +
	  "order by tg";
	return super.getRs(s, new String[] {gid, gid}, null);
  }

  public ljwf.ListView newgetUsrsAndGrp(String gid) { //根据gid得到用户(全部用户和组)
//	(select r.name,r.id gid,1 tg
//	from grp_usr m, usr r
//	where m.u_id=r.id and
//		  m.gid='ld')
//	union
//(select g.name,g.id  gid ,0 tg from grp_r r left join grp g on r.gid=g.id where r.fid='ld' and r.tp=0)
//order by tg

	String s = "(select r.name,r.id gid,1 tg from grp_usr m left join usr r on m.u_id=r.id where m.gid=?)"
	  + "union" + "(select g.name,g.id  gid ,0 tg from grp_r r left join grp g on r.gid=g.id where r.fid=? and r.tp=0)" +
	  "order by tg";
	return super.getRs(s, new String[] {gid, gid}, null);
  }

  public ljwf.ListView judgequanxian(String u_id, String gid) { //得到用户是否有权限的（组和他的父组是否有权限）
	String s = "select distinct m.gid from grp_mtn m, grp_r r where m.u_id=? and (m.gid=? or m.gid in(select fid from grp_r where gid=?) )";
	return super.getRs(s, new String[] {u_id, gid, gid}, null);
  }

  public void delGrp_usr(String wherestr) { //根据gid.u_id移除用户
//   delete  grp_mtn  where gid='ld1' and u_id='ly'
//   String s = "delete from grp_mtn  where gid=? and u_id=?";
	super.Del(wherestr, null, null);

//   super.Del("gid=? and u_id in(" + ljwf.NStr.getStr("?", ",", u_id.length) + ")",
//			  ljwf.NStr.Merge(gid, u_id), null);

  }

  public void delGrp(String gid, String u_id) { //根据gid.u_id移除用户
//   delete  grp_mtn  where gid='ld1' and u_id='ly'
	String s = "delete from grp_mtn  where gid=? and u_id=?";
	super.Exec(s, new String[] {gid, u_id}, null);

  }

  public void addGrp_usr(String u_id, String gid) { //根据gid.u_id添加用户
//   INSERT INTO grp_mtn(u_id,gid) VALUES ('ld1','ly')
	String s = "INSERT INTO grp_mtn(u_id,gid) VALUES (?,?)";
	super.Exec(s, new String[] {u_id, gid}, null);

  }

  public ljwf.ListView AdminGrp() { //获得管理员初始组
//   String s = "select g.name,g.id  gid ,0 tg from grp g where g.id='lclg'";
	String s = "select g.name,g.id  gid ,0 tg FROM grp g WHERE (id NOT IN (SELECT gid FROM grp_r))";
	return super.getRs(s, new String[] {}, null);
  }

  public ljwf.ListView getexitusr(String u_id, String gid) { //获得管理员初始组
	String s = "SELECT * FROM grp_mtn WHERE gid = ? AND u_id = ?";
	return super.getRs(s, new String[] {u_id, gid}, null);
  }

  public ljwf.ListView AdminGrpandUsr(String gid) { //获得管理员权限的组和用户
//	(select r.name,r.id gid,1 tg
//	from grp_mtn m, usr r
//	where m.u_id=r.id and
//		  m.gid='lclg')
//	union
//(select g.name,g.id  gid ,0 tg from grp_r r left join grp g on r.gid=g.id where r.fid='lclg' and r.tp=0)
//order by tg
	String s = "(select r.name,r.id gid,1 tg from grp_mtn m, usr r where m.u_id=r.id and  m.gid=?)"
	  + "union" + "(select g.name,g.id  gid ,0 tg from grp_r r left join grp g on r.gid=g.id where r.fid=? and r.tp=0)" +
	  "order by tg";
	return super.getRs(s, new String[] {gid, gid}, null);
  }

}
