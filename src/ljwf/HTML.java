package ljwf;

public class HTML {
  public HTML() {
  }

  public static String Encode(String s) {
    s=ljwf.NStr.replace(s, "<", "&lt;");
    s=ljwf.NStr.replace(s, ">", "&gt;");
    return ljwf.NStr.replace(s, "\"", "&quot;");
  }

  public static String TagEncode(String s) {
    s=ljwf.NStr.replace(s, "<", "&lt;");
    return ljwf.NStr.replace(s, ">", "&gt;");
  }

  public static String convQuot(String s) {
    return ljwf.NStr.replace(s, "\"", "&quot;");
  }
}
