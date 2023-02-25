package ljwf;

public class URL {
//  String url;
//  String para[][]; //2 cols:name,value
//  String seri; //serialized data

  public URL() {
  }

//  public URL(String seri) {
// //   Parse(seri);
//  }

//  public void Parse(String seri) {
//    String[] t = ljwf.NStr.split(seri, ljwf.db.tbl.STRSEP);
//    url = t[0];
//    para = new String[ (t.length - 1) / 2][2];
//    for (int i = 1; i < t.length; ) {
//      para[i][0] = t[i++];
//      para[i][1] = t[i++];
//    }
//  }

  public static void PrintForm(javax.servlet.jsp.JspWriter out, String seri) throws
      Exception {
    if (seri == null) {
      return;
    }
    String[] h = ljwf.NStr.split(seri, ljwf.db.tbl.STRSEP2, 2);
    String[] t = ljwf.NStr.split(h[0], ljwf.db.tbl.STRSEP);
    out.println("<form id=frmP method=post action=\"" + ljwf.HTML.convQuot(t[0]) +
                "\">");
    if (h.length > 1) {
      out.println("<input type=hidden name=purl value=\"" +
                  ljwf.HTML.convQuot(h[1]) + "\">");
    }
    for (int i = 1; i < t.length; i = i + 2) {
      out.println("<input type=hidden name='" + t[i] + "' value=\"" +
                  ljwf.HTML.convQuot(t[i + 1]) + "\">");
    }
    out.println("</form>");
  }

  public static void PrintUrl(javax.servlet.jsp.JspWriter out, String seri) throws
      Exception {
    ljwf.CmnJsp.DrawHidden(out,"purl",seri);
  }
}
