package ljwf.db;

import ljwf.ListView;

public class sequences
  extends tbls {
  
  public static final int VINT = 0;
  public static final int VLONG = 1;
	
  public static String getOriName() { //get origin name
	return "sequences";
  }

  public static String[] getOriKeys() { //get origin keys
	return new String[] {
	  "tb_name"};
  }

  void init() {
	name = getOriName();
	keys = getOriKeys();
  }

  public sequences() {
	init();
  }

  public String getNext(String tb_name) {
	String s = null;
	try {
	  begintrans();
	  Exec("update " + name + " set seq=seq+1 where tb_name=?",
		   new String[] {tb_name}
		   , new int[] {java.sql.Types.VARCHAR});
	  ljwf.ListView v = getRs("select seq from " + name + " where tb_name=?",
							  new String[] {tb_name}
							  , new int[] {java.sql.Types.VARCHAR});
	  super.commit();
	  return v.getFld(0, "seq");
	}
	catch (Throwable e) {
	  print("ljwf.db.sequences.getNext.err:");
	  print(e);
	  super.rollback();
	}
	return null;
  }
  
  public String getNext(String tb_name, int t){
	  try {
		  ListView v = null;
		  begintrans();
		  Exec("update " + name + " set seq=seq+1 where tb_name=?",
			   new String[] {tb_name}
			   , new int[] {java.sql.Types.VARCHAR});
		  v = getRs("select seq from " + name + " where tb_name=?",
								  new String[] {tb_name}
								  , new int[] {java.sql.Types.VARCHAR});
		  
		  int seq = Integer.parseInt(v.getFld(0, "seq"));
		  if((t == VINT && seq >= Integer.MAX_VALUE) || (t == VLONG && seq >= Long.MAX_VALUE)){
			  Exec("update " + name + " set seq=1 where tb_name=?",
					   new String[] {tb_name}
					   , new int[] {java.sql.Types.VARCHAR});
			  seq = 1;
		  }
		  super.commit();
		  return String.valueOf(seq);
		}
		catch (Throwable e) {
		  print("ljwf.db.sequences.getNext.err:");
		  print(e);
		  super.rollback();
		}
		return null;
  }

}
