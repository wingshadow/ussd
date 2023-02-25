package ljwf.exception;

import javax.servlet.http.HttpServletRequest;

/**
 *自定义的异常类

 *@autor
 *@version
 */
public class AppException extends Exception {

  public AppException() {
  	super();

  	}
  public AppException(String message)
  {
  	super(message);
  }
  public AppException(String message,Throwable cause){
	  super(message,cause);
  }
  public AppException(Throwable cause){
	  super(cause);
  }
  public AppException(HttpServletRequest request,String message){
	  request.setAttribute("javax.servlet.jsp.jspException",message);
  }

  public AppException(HttpServletRequest request,String message,String nexrUrl){
	  request.setAttribute("NextUrl",nexrUrl);
	  request.setAttribute("javax.servlet.jsp.jspException",message);
  }
}
