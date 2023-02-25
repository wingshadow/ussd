package ljwf;

public class StackTrace {
//  String class_name;
//  String method_name;
//  String class_name;
  static boolean disp_method = true;

  public StackTrace() {

  }

  public static String getMsg() {
	if (!disp_method) {
	  return "";
	}
	return getMsg(new Throwable(), 2);
  }

  public static String getMsg(java.lang.Throwable t) {
	if (!disp_method) {
	  return "";
	}
	return getMsg(t, 0);
  }

  public static String getMsg(java.lang.Throwable t, int lv) {
	if (!disp_method) {
	  return "";
	}
	java.io.StringWriter w = new java.io.StringWriter();
	java.io.PrintWriter p = new java.io.PrintWriter(w);
	t.printStackTrace(p);
	java.io.StringReader s = new java.io.StringReader(w.toString());
	java.io.LineNumberReader r = new java.io.LineNumberReader(s);
//	r.setLineNumber(lv+1);
	String u = null;
	try {
	  for (int i = 0; i < lv + 1; i++) {
		r.readLine();
	  }
	  u = r.readLine();
	}
	catch (Exception e) {
	}
	if (u == null) {
	  return "";
	}
	int i = u.indexOf("at");
	if (i == -1) {
	  return u + "\r\n\t";
	}
	else {
	  return u.substring(i + 3) + "\r\n\t";
	}
  }
}
