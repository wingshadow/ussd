package ljwf.db;

public class Grp_Usr_V
  extends ljwf.db.tbls {
  final public static String GID = "gid";
  final public static String UID = "u_id";

  public static String getOriName() { //get origin name
	return "grp_usr_v";
  }

  public static String[] getOriKeys() { //get origin keys
	return new String[] {
	  "id"};
  }

  public Grp_Usr_V() {
	super(getOriName(), getOriKeys());
  }

  public Grp_Usr_V(ljwf.db.tbl tb) {
	super(getOriName(), getOriKeys(), tb);
  }

  public String[] getGrps(String user) {
	ljwf.ListView v= super.getRs("gid", "u_id=?", new String[] {user}, null);//.getFlds(0);
	return v.getFlds(0);
  }
}
