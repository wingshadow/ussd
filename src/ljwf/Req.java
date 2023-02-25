package ljwf;

public class Req {
  javax.servlet.http.HttpServletRequest request = null;

  public Req(javax.servlet.http.HttpServletRequest request) {
	this.request = request;
  }

  public String getParameter(Object o) {
	if (o == null) {
	  return "";
	}
	else {
	  o = request.getParameter(o.toString());
	  if (o == null) {
		return "";
	  }
	  else {
		return o.toString();
	  }
	}
  }

  public String getParameter(Object o, String s) {
	if (o == null) {
	  return s;
	}
	else {
	  o = request.getParameter(o.toString());
	  if (o == null) {
		return s;
	  }
	  else {
		return o.toString();
	  }
	}
  }

  public String getAttribute(Object o) {
	if (o == null) {
	  return "";
	}
	else {
	  o = request.getAttribute(o.toString());
	  if (o == null) {
		return "";
	  }
	  else {
		return o.toString();
	  }
	}
  }

  public String getParaWithNull(Object o) {
	if (o == null) {
	  return null;
	}
	else {
	  return request.getParameter(o.toString());
	}
  }

  public String getAttrWithNull(Object o) {
	if (o == null) {
	  return null;
	}
	else {
	  o = request.getAttribute(o.toString());
	  if (o == null) {
		return null;
	  }
	  else {
		return o.toString();
	  }
	}
  }

  public void setAttribute(String s, Object o) {
	request.setAttribute(s, o);
  }

  public String getParaAttr(Object o) {
	String s = this.getParameter(o);
	if (s.equals("")) {
	  s = this.getAttribute(o);
	}
	return s;
  }

}
