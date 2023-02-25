package ljwf;

public class CmnJsp {

  //draw checkbox group with some name
  static public void DrawChk(javax.servlet.jsp.JspWriter out, ljwf.ListView v,
							 String name, boolean wrap) throws Exception {
	//v中包括：第0列为id,第1列为name
	//wrap:自动换行
	if (!v.beforefirst()) {
	  return;
	}
	for (; v.next(); ) {
	  out.println("<input type=checkbox name='" + name + "' value='" +
				  v.getFld(0) + "'>" + v.getFld(1));
	  if (wrap) {
		out.println("<br>");
	  }
	}
  }

  //draw select (dropdown) list
  static public void DrawSel(javax.servlet.jsp.JspWriter out, ljwf.ListView v,
							 String cur) throws Exception {
	//v中包括：第0列为id,第1列为name
	//cur：当前值
	v.beforefirst();
	for (; v.next(); ) {
	  out.println("<option value='" + v.getFld(0) + "'");
	  if (v.getFld(0).equals(cur)) {
		out.println(" selected");
	  }
	  out.println(">" + v.getFld(1));
	}
  }

  //draw select (dropdown) list
  static public void DrawSel(javax.servlet.jsp.JspWriter out, String[] ids,
							 String[] names
							 , String cur) throws Exception {
	//cur：当前值
	if (ids == null) {
	  ids = names;
	}
	else if (names == null) {
	  names = ids;
	}
	for (int i = 0; i < ids.length; i++) {
	  out.println("<option value='" + ids[i] + "'");
	  if (ids[i].equals(cur)) {
		out.println(" selected");
	  }
	  out.println(">" + names[i]);
	}
  }

  //draw select (dropdown) list
  static public void DrawSel(javax.servlet.jsp.JspWriter out, String tblname,
							 String sellist, String where, String order,
							 String cur) throws Exception {
	//sellist：第0列为id,第1列为name
	//cur：当前值
	//忽略参数，要填写空值NULL
	String sql = "";
	if (where != null) {
	  sql += " where " + where;
	}
	if (order != null) {
	  sql += " order by " + order;
	}
	if (sellist != null) {
	  sql = "select " + sellist + " from " + tblname + sql;
	}
	else {
	  sql = "select * from " + tblname + sql;
	}
	ljwf.ListView v = new ljwf.db.tbl().getRsbySQL(sql);
	DrawSel(out, v, cur);
  }

  //draw table like a dir,example list of discuss tree,auto display 0,1 levels
  static public void DrawDirList(javax.servlet.http.HttpServletRequest request,
								 javax.servlet.jsp.JspWriter out,
								 ljwf.ListView lv) throws
	Exception {
  }

  //draw select list
  static public void DrawList(javax.servlet.jsp.JspWriter out, ljwf.ListView lv) throws
	Exception { //flds of lv in order:id,name
	lv.beforefirst();
	for (; lv.next(); ) {
	  out.println("<option value='" + lv.getFld(0) + "'>" + lv.getFld(1));
	}
  }

  //draw select list with id and name specified
  static public void DrawList(javax.servlet.jsp.JspWriter out, ljwf.ListView lv,
							  String[] ids, boolean idfld, String[] names,
							  boolean namefld) throws
	Exception {
	lv.beforefirst();
	for (; lv.next(); ) {
	  String id = "";
	  boolean b = idfld;
	  for (int i = 0; i < ids.length; i++) {
		if (ids[i] != null) {
		  if (b) {
			id += lv.getFld(ids[i]);
		  }
		  else {
			id += ids[i];
		  }
		  b = !b;
		}
	  }

	  String name = "";
	  b = namefld;
	  for (int i = 0; i < names.length; i++) {
		if (names[i] != null) {
		  if (b) {
			name += lv.getFld(names[i]);
		  }
		  else {
			name += names[i];
		  }
		  b = !b;
		}
	  }

	  out.println("<option value='" + id + "'>" + name);
	}
  }

  //draw select list with id and name specified
  static public void DrawList(javax.servlet.jsp.JspWriter out, ljwf.ListView lv,
							  String[] ids, String[] names) throws
	Exception {
	DrawList(out, lv, ids, true, names, true);
  }

  //draw hidden
  static public void DrawHidden(javax.servlet.jsp.JspWriter out, String name,
								String val) throws
	Exception {
	if (val != null) {
	  out.println("<input type=hidden id='" + name + "' name='" + name +
				  "' value=\"" + ljwf.HTML.Encode(val) + "\">");
	}
  }

  //draw hidden
  static public void DrawHidden(javax.servlet.jsp.JspWriter out, String id,
								String name, String val) throws
	Exception {
	if (val != null) {
	  String s = "";
	  if (id != null) {
		s = "id='" + id + "' ";
	  }
	  if (name != null) {
		s += "name='" + name + "' ";
	  }
	  out.println("<input type=hidden " + s + "value=\"" +
				  ljwf.HTML.Encode(val) + "\">");
	}
  }
}
