package com.inspur.utils;

import java.text.SimpleDateFormat;
import java.util.*;

public class SystemUtil
{

	private static SimpleDateFormat sdf;
	private static HashMap m = null;
	private static HashMap cmap = null;
	public static String openCommands = "";

	public SystemUtil()
	{
	}

	public static void initOpenCommands()
	{
	}

	public static void init()
	{
		m = new HashMap();
		cmap = new HashMap();
		try
		{
			Properties p = new Properties();
			java.io.InputStream in = SystemUtil.class.getResourceAsStream("/system.properties");
			p.load(in);
			String key;
			String val;
			for (Enumeration keys = p.keys(); keys.hasMoreElements(); m.put(key, val))
			{
				key = (String)keys.nextElement();
				val = p.getProperty(key);
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static String getProperty(String key)
	{
		String val = (String)m.get(key);
		if (val == null)
			val = "";
		return val;
	}

	public static void setProperty(String key, String value)
	{
		if (!m.containsKey(key))
		{
			return;
		} else
		{
			m.put(key, value);
			return;
		}
	}

	public static final String getTimeStamp(Date d)
	{
		sdf.applyPattern("yyyyMMddHHmmss");
		return sdf.format(d);
	}

	public static final String getTimeStamp(long d)
	{
		return getTimeStamp(new Date(d));
	}

	public static final String getDate(Date d)
	{
		sdf.applyPattern("yyyy-MM-dd");
		return sdf.format(d);
	}

	public static final String getZhDate(long d)
	{
		sdf.applyPattern("yyyy年MM月dd日");
		Date s = new Date(d);
		return sdf.format(s);
	}

	public static final String getDate(long d)
	{
		return getDate(new Date(d));
	}

	public static final String getDateTime(Date d)
	{
		sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
		return sdf.format(d);
	}

	public static final String getDateYYYYMMDD(Date d)
	{
		sdf.applyPattern("yyyyMMdd");
		return sdf.format(d);
	}

	public static String getYear()
	{
		Date d = new Date();
		sdf.applyPattern("yyyy");
		return sdf.format(d);
	}

	public static String getMM()
	{
		Date d = new Date();
		sdf.applyPattern("MM");
		return sdf.format(d);
	}

	public static String getYearMM()
	{
		Date d = new Date();
		sdf.applyPattern("yyyyMM");
		return sdf.format(d);
	}

	public static String getMMdd()
	{
		Date d = new Date();
		sdf.applyPattern("MMdd");
		return sdf.format(d);
	}

	public static String getY4MMDD()
	{
		Date d = new Date();
		sdf.applyPattern("yyyy-MM-dd");
		return sdf.format(d);
	}

	public static String getHHmmss()
	{
		Date d = new Date();
		sdf.applyPattern("HH:mm:ss");
		return sdf.format(d);
	}

	public static final String getDateTime(long d)
	{
		return getDateTime(new Date(d));
	}

	public static final String getWeek(Date d)
	{
		String dayNames[] = {
			"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"
		};
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		int dayOfWeek = calendar.get(7);
		return dayNames[dayOfWeek - 1];
	}

	public static final String getWeek(long d)
	{
		return getWeek(new Date(d));
	}

	public static String getOsVersion()
	{
		String os = System.getProperty("os.name");
		String osver;
		if (os.indexOf("Windows") != -1)
			osver = "Windows_NT";
		else
			osver = "Linux";
		return osver;
	}

	public static void main(String args1[])
	{
	}

	static 
	{
		sdf = new SimpleDateFormat("", Locale.SIMPLIFIED_CHINESE);
		init();
	}
}
