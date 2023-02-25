package ljwf.db;

public class sysOper {
  public static final String SYSDATE = "@sysdate";
  public static final String AUTOID = "@autoid";

  //ora
  public static final String F_SYSDATE = "sysdate";
  public static final String F_SUBSTR = "substr";
  public static final String F_STRLEN = "length";
  public static final String F_ISNULL = "nvl";
  public static final String F_STRCON = "||";
  public static final String F_STR = "to_char(";
  //sql
//  public static final String F_SYSDATE = "getdate()";
//  public static final String F_SUBSTR = "substring";
//  public static final String F_STRLEN = "len";
//  public static final String F_ISNULL = "isnull";
//  public static final String F_STRCON = "+";
//  public static final String F_STR = "convert(varchar(50),";
//  public static String F_STR(String s) {
//	return "convert(varchar(50)," + s + ")";
//  }
}
