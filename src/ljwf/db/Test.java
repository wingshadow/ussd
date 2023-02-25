package ljwf.db;

public class Test
  extends tbls {

  public static String getOriName() { //get origin name
	return "Test";
  }

  public static String[] getOriKeys() { //get origin keys
	return new String[] {
	  "Test_id"};
  }

  void init() {
	name = getOriName();
	keys = getOriKeys();
  }

  public Test() {
	init();
  }

  public Test(CN cn) {
	this.cn = cn;
	init();
  }

  public void ttt() {
	try {
	  super.begintrans();
//	  super.ExecSQL("insert into test values('101','a')");
//	  super.ExecSQL("update test set mc='goodnot' where id='101'");
	  super.ExecSQL("select * from test");
	}
	catch (Exception e) {
	}
  }

}
