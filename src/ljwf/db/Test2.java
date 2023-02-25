package ljwf.db;

public class Test2
  extends tbls {

  public static String getOriName() { //get origin name
	return "Test2";
  }

  public static String[] getOriKeys() { //get origin keys
	return new String[] {
	  "Test2_id"};
  }

  void init() {
	name = getOriName();
	keys = getOriKeys();
  }

  public Test2() {
	init();
  }

  public Test2(CN cn) {
	this.cn = cn;
	init();
  }

  public void ttt() {
	try {
	  super.begintrans();
//	  super.ExecSQL("insert into Test2 values('101','a')");
//	  super.ExecSQL("update Test2 set mc='goodnot' where id='101'");
	  super.ExecSQL("select * from Test2");
	}
	catch (Exception e) {
	}
  }

}
