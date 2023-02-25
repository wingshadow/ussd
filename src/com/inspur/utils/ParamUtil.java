package com.inspur.utils;

import java.io.PrintStream;
import java.sql.Date;
import java.text.ParseException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

// Referenced classes of package com.inspur.utils:
//			DateUtil

public class ParamUtil
{

	private ParamUtil()
	{
	}

	public static String getServer(HttpServletRequest request)
	{
		StringBuffer server = new StringBuffer(128);
		String scheme = request.getScheme();
		int port = request.getServerPort();
		if (port < 0)
			port = 80;
		server.append(scheme);
		server.append("://");
		server.append(request.getServerName());
		if (scheme.equals("http") && port != 80 || scheme.equals("https") && port != 443)
		{
			server.append(':');
			server.append(port);
		}
		return server.toString();
	}

	public static String getServer2(HttpServletRequest request)
	{
		StringBuffer server = new StringBuffer(128);
		server.append(request.getScheme());
		server.append("://");
		server.append(request.getHeader("host"));
		return server.toString();
	}

	public static String getRequestAction(HttpServletRequest request)
	{
		String ruri = request.getRequestURI();
		String ar[] = ruri.split("/");
		String action = ar[ar.length - 1];
		String ad[] = action.split(".do");
		action = ad[0];
		return action;
	}

	public static String getParameter(HttpServletRequest request, String param)
	{
		String ret = request.getParameter(param);
		if (ret == null)
			ret = "";
		return ret.trim();
	}

	public static String getParameter(HttpServletRequest request, String param, String defaultValue)
	{
		String ret = request.getParameter(param);
		if (ret == null)
			ret = defaultValue;
		return ret.trim();
	}

	public static String getParameter(HttpServletRequest request, String param, boolean checkEmpty)
	{
		String ret = request.getParameter(param);
		if (ret == null)
			ret = "";
		ret = ret.trim();
		return ret;
	}

	public static String[] getParameterValues(HttpServletRequest request, String param)
	{
		String ret[] = request.getParameterValues(param);
		return ret;
	}

	public static String getParameterSafe(HttpServletRequest request, String param, boolean checkEmpty)
	{
		String ret = getParameter(request, param, checkEmpty);
		if (ret.indexOf('<') == -1)
			ret.indexOf('>');
		return ret;
	}

	public static int getParameterInt(HttpServletRequest request, String param)
	{
		String inputStr = getParameter(request, param, true);
		int ret = Integer.parseInt(inputStr);
		return ret;
	}

	public static int getParameterInt(HttpServletRequest request, String param, int defaultValue)
	{
		String inputStr = getParameter(request, param, false);
		if (inputStr.length() == 0)
		{
			return defaultValue;
		} else
		{
			int ret = Integer.parseInt(inputStr);
			return ret;
		}
	}

	public static long getParameterLong(HttpServletRequest request, String param)
	{
		String inputStr = getParameter(request, param, true);
		long ret = Long.parseLong(inputStr);
		return ret;
	}

	public static long getParameterLong(HttpServletRequest request, String param, long defaultValue)
	{
		String inputStr = getParameter(request, param, false);
		if (inputStr.length() == 0)
		{
			return defaultValue;
		} else
		{
			long ret = Long.parseLong(inputStr);
			return ret;
		}
	}

	public static boolean getParameterBoolean(HttpServletRequest request, String param)
	{
		String inputStr = getParameter(request, param);
		if (inputStr.length() == 0)
			return false;
		if (inputStr.equals("0"))
		{
			return false;
		} else
		{
			System.out.println(inputStr);
			return true;
		}
	}

	public static byte getParameterByte(HttpServletRequest request, String param)
	{
		String inputStr = getParameter(request, param, true);
		byte ret = Byte.parseByte(inputStr);
		return ret;
	}

	public static double getParameterDouble(HttpServletRequest request, String param)
	{
		String inputStr = getParameter(request, param, true);
		double ret = Double.parseDouble(inputStr);
		return ret;
	}

	public static float getParameterFloat(HttpServletRequest request, String param)
	{
		String inputStr = getParameter(request, param, true);
		float ret = Float.parseFloat(inputStr);
		return ret;
	}

	public static String getParameterUrl(HttpServletRequest request, String param)
	{
		String ret = getParameter(request, param);
		if (ret.length() > 0 && !ret.startsWith("http://") && !ret.startsWith("https://") && !ret.startsWith("ftp://"))
			return "badformat";
		else
			return ret;
	}

	public static String getParameterPassword(HttpServletRequest request, String param, int minLength, int option)
	{
		if (minLength < 1)
			minLength = 1;
		String ret = request.getParameter(param);
		if (ret == null)
			ret = "";
		ret = ret.trim();
		if (option == 1);
		return ret;
	}

	public static String getParameterEmail(HttpServletRequest request, String param)
		throws Exception
	{
		String email = getParameterSafe(request, param, true);
		return email;
	}

	public static Date getParameterDate(HttpServletRequest request, String param)
		throws ParseException
	{
		String inputStr = getParameter(request, param, true);
		java.util.Date ret = DateUtil.getDDMMYYYYDate(inputStr);
		return new Date(ret.getTime());
	}

	public static Date getParameterDate(HttpServletRequest request, String paramDay, String paramMonth, String paramYear)
		throws ParseException
	{
		int day = getParameterInt(request, paramDay);
		int month = getParameterInt(request, paramMonth);
		int year = getParameterInt(request, paramYear);
		StringBuffer buffer = new StringBuffer();
		buffer.append(day).append("/").append(month).append("/").append(year);
		String inputStr = buffer.toString();
		java.util.Date ret = DateUtil.getDDMMYYYYDate(inputStr);
		return new Date(ret.getTime());
	}

	public static int getParameterTimeZone(HttpServletRequest request, String param)
	{
		int timeZone = getParameterInt(request, param, 0);
		if (timeZone < -12 || timeZone > 13)
			timeZone = 0;
		return timeZone;
	}

	public static String getAttribute(HttpSession session, String name)
	{
		String ret = (String)session.getAttribute(name);
		if (ret == null)
			ret = "";
		return ret.trim();
	}

	public static String getAttribute(HttpServletRequest request, String name)
	{
		String ret = (String)request.getAttribute(name);
		if (ret == null)
			ret = "";
		return ret.trim();
	}
}
