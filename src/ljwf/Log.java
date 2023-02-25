package ljwf;


import org.apache.log4j.Category;
import org.apache.log4j.Level;
import org.apache.log4j.PropertyConfigurator;

import com.inspur.utils.SystemUtil;

public class Log {

  public Log() {
  }

//  static boolean initialized = false;
  static Category category;
  static String category_name = "test";
  
//  static String prop_file = "/logs/qqq.properties";
  static String prop_file = SystemUtil.getProperty("logprop");
  static int lv;
  static boolean canLog = false;
  static Class clsT = null;

  static {
	init();
  }

  static public synchronized void init() {
	try {
	  clsT = Class.forName("java.lang.Throwable");
	  org.apache.log4j.LogManager.shutdown();
	  PropertyConfigurator.configureAndWatch(prop_file);
	  category = Category.getInstance(category_name);
	  lv = category.getLevel().toInt();
	  canLog = true;
	}
	catch (Exception e) {
	  lv = 20000; //INFO,WARN,ERROR都可以打出
	  canLog = false;
	  ljwf.Log.error("初始化LOG未成功:", e);
	}
	ljwf.Log.info("inited!");
  }

  static public boolean isThrowable(Object obj) {
	boolean b = false;
	try {
	  b = clsT.isAssignableFrom(obj.getClass());
	}
	catch (Exception e) {}
	return b;
  }

  static public void print(Object obj) {
	if (isThrowable(obj)) {
	  print("", (Throwable) obj);
	}
	else {
	  System.out.println(ljwf.StackTrace.getMsg() + obj);
	}
  }

  static public void print(Object obj, Throwable t) {
	System.out.println(ljwf.StackTrace.getMsg(t) + obj);
	System.out.println(t);
  }

  static public void debug(Object obj) {
	if (lv <= Level.DEBUG_INT) {
	  if (!canLog) {
		print(obj);
	  }
	  else {
		if (isThrowable(obj)) {
		  debug("", (Throwable) obj);
		}
		else {
		  category.debug(ljwf.StackTrace.getMsg() + obj);
		}
	  }
	}
  }

  static public void debug(Object obj, Throwable throwable) {
	if (lv <= Level.DEBUG_INT) {
	  if (!canLog) {
		print(obj, throwable);
	  }
	  else {
		category.debug(ljwf.StackTrace.getMsg(throwable) + obj, throwable);
	  }
	}
  }

  static public void error(Object obj) {
	if (lv <= Level.ERROR_INT) {
	  if (!canLog) {
		print(obj);
	  }
	  else {
		if (isThrowable(obj)) {
		  error("", (Throwable) obj);
		}
		else {
		  category.error(ljwf.StackTrace.getMsg() + obj);
		}
	  }
	}
  }

  static public void error(Object obj, Throwable throwable) {
	if (lv <= Level.ERROR_INT) {
	  if (!canLog) {
		print(obj, throwable);
	  }
	  else {
		category.error(ljwf.StackTrace.getMsg(throwable) + obj, throwable);
	  }
	}
  }

  static public void info(Object obj) {
	if (lv <= Level.INFO_INT) {
	  if (!canLog) {
		print(obj);
	  }
	  else {
		if (isThrowable(obj)) {
		  info("", (Throwable) obj);
		}
		else {
		  category.info(ljwf.StackTrace.getMsg() + obj);
		}
	  }
	}
  }

  static public void info(Object obj, Throwable throwable) {
	if (lv <= Level.INFO_INT) {
	  if (!canLog) {
		print(obj, throwable);
	  }
	  else {
		category.info(ljwf.StackTrace.getMsg(throwable) + obj, throwable);
	  }
	}
  }

  static public void warn(Object obj) {
	if (lv <= Level.WARN_INT) {
	  if (!canLog) {
		print(obj);
	  }
	  else {
		if (isThrowable(obj)) {
		  warn("", (Throwable) obj);
		}
		else {
		  category.warn(ljwf.StackTrace.getMsg() + obj);
		}
	  }
	}
  }

  static public void warn(Object obj, Throwable throwable) {
	if (lv <= Level.WARN_INT) {
	  if (!canLog) {
		print(obj, throwable);
	  }
	  else {
		category.warn(ljwf.StackTrace.getMsg(throwable) + obj, throwable);
	  }
	}
  }

  static public int getLevel() {
	return lv;
  }

  static public void setLevel(int i) {
	lv = i;
  }
}
