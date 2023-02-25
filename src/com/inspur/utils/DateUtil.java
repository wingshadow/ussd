package com.inspur.utils;

import java.sql.Timestamp;
import java.text.*;
import java.util.*;

public final class DateUtil
{

	public static final int SECOND = 1000;
	public static final int MINUTE = 60000;
	public static final int HOUR = 0x36ee80;
	public static final int DAY = 0x5265c00;
	public static final int WEEK = 0x240c8400;
	public static final int YEAR = 0x57b12c00;
	public static final long GMT_VIETNAM_TIME_OFFSET = 0x1808580L;
	private static long SERVER_TIME_OFFSET = 0L;
	private static DateFormat serialFormat = new SimpleDateFormat("yyyyMMdd");
	private static DateFormat serialFullFormat = new SimpleDateFormat("yyyyMMddhhmmss");
	private static DateFormat serialbossFormat = new SimpleDateFormat("yyyyMMdd");
	private static DateFormat yyyyMMddHHmmssFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static DateFormat ddMMyyyyFormat = new SimpleDateFormat("dd/MM/yyyy");
	private static DateFormat yyyyMMddFormat = new SimpleDateFormat("yyyy-MM-dd");
	private static DateFormat yyyyMMddFormatCN = new SimpleDateFormat("yyyyå¹´MMæœˆddæ—?");
	private static DateFormat yyyyMdEFormatCN;
	private static DateFormat yyyyMMddhhmmssFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private static DateFormat yyyyMMddhhmmssFormatCN = new SimpleDateFormat("yyyyå¹´MMæœˆddæ—? hhæ—¶mmåˆ†ssç§?");
	private static DateFormat MMddhhmmFormat = new SimpleDateFormat("MMæœˆddæ—? hhæ—¶mmåˆ?");
	private static DateFormat yyyyMMddhhmmFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
	private static DateFormat yyyyMMddhhmmFormatCN = new SimpleDateFormat("yyyyå¹´MMæœˆddæ—? hhæ—¶mmåˆ?");
	private static DateFormat MMddFormat = new SimpleDateFormat("MMdd");
	private static DateFormat dateFormat = SimpleDateFormat.getDateInstance(2);
	private static DateFormat datetimeFormat = SimpleDateFormat.getDateTimeInstance(2, 2);

	private DateUtil()
	{
	}

	public static synchronized String getDateMMDD(Date date)
	{
		return MMddFormat.format(date);
	}

	public static synchronized String getDateDDMMYYYY(Date date)
	{
		return ddMMyyyyFormat.format(date);
	}

	public static synchronized Date getDDMMYYYYDate(String ddMMyyyyDate)
		throws ParseException
	{
		return ddMMyyyyFormat.parse(ddMMyyyyDate);
	}

	public static synchronized String getDateYYYYMMDD(Date date)
	{
		return yyyyMMddFormat.format(date);
	}

	public static synchronized String getDateyyyyMMddHHmmss(Date date)
	{
		return yyyyMMddHHmmssFormat.format(date);
	}

	public static synchronized Date getYYYYMMDDDate(String yyyyMMddDate)
		throws ParseException
	{
		return yyyyMMddFormat.parse(yyyyMMddDate);
	}

	public static synchronized String getDateYYYYMMDD_CN(Date date)
	{
		return yyyyMMddFormatCN.format(date);
	}

	public static synchronized Date getYYYYMMDD_CN_Date(String yyyyMMddCN_Date)
		throws ParseException
	{
		return yyyyMMddFormatCN.parse(yyyyMMddCN_Date);
	}

	public static synchronized String getDateyyyyMMddhhmmss(Date date)
	{
		return yyyyMMddhhmmssFormat.format(date);
	}

	public static synchronized Date getyyyyMMddhhmmssDate(String yyyyMMddhhmmssDate)
		throws ParseException
	{
		return yyyyMMddhhmmssFormat.parse(yyyyMMddhhmmssDate);
	}

	public static synchronized Date getyyyyMMddHHmmssDate(String yyyyMMddHHmmssDate)
		throws ParseException
	{
		return yyyyMMddHHmmssFormat.parse(yyyyMMddHHmmssDate);
	}

	public static synchronized String getDateyyyyMMddhhmm(Date date)
	{
		return yyyyMMddhhmmFormat.format(date);
	}

	public static synchronized Date getyyyyMMddhhmmDate(String yyyyMMddhhmmDate)
		throws ParseException
	{
		return yyyyMMddhhmmFormat.parse(yyyyMMddhhmmDate);
	}

	public static synchronized String getDateyyyyMMddhhmmss_CN(Date date)
	{
		return yyyyMMddhhmmssFormatCN.format(date);
	}

	public static synchronized Date getyyyyMMddhhmmss_CN_Date(String yyyyMMddhhmmssCN_Date)
		throws ParseException
	{
		return yyyyMMddhhmmssFormatCN.parse(yyyyMMddhhmmssCN_Date);
	}

	public static synchronized String getDateindex(Date date)
	{
		return yyyyMMddhhmmssFormat.format(date);
	}

	public static synchronized String getDateSearch(Date date)
	{
		return MMddhhmmFormat.format(date);
	}

	public static synchronized String getDateNoSencond(Date date)
	{
		return yyyyMMddhhmmFormat.format(date);
	}

	public static synchronized String formatDate(Date date)
	{
		return dateFormat.format(date);
	}

	public static synchronized String formatDateTime(Date date)
	{
		return datetimeFormat.format(date);
	}

	public static Timestamp getCurrentGMTTimestamp()
	{
		return new Timestamp(System.currentTimeMillis() + SERVER_TIME_OFFSET);
	}

	public static void updateCurrentGMTTimestamp(Timestamp timeToUpdate)
	{
		timeToUpdate.setTime(System.currentTimeMillis() + SERVER_TIME_OFFSET);
	}

	public static Date getVietnamDateFromGMTDate(Date date)
	{
		return new Date(date.getTime() + 0x1808580L);
	}

	public static Date convertGMTDate(Date gmtDate, int hourOffset)
	{
		return new Date(gmtDate.getTime() + (long)(hourOffset * 0x36ee80));
	}

	public static Timestamp convertGMTTimestamp(Timestamp gmtTimestamp, int hourOffset)
	{
		return new Timestamp(gmtTimestamp.getTime() + (long)(hourOffset * 0x36ee80));
	}

	public static synchronized String getSerialDate()
	{
		GregorianCalendar now = new GregorianCalendar();
		return serialFormat.format(now.getTime());
	}

	public static synchronized String getSerialFullDate()
	{
		GregorianCalendar now = new GregorianCalendar();
		return serialFullFormat.format(now.getTime());
	}

	public static synchronized String getSerialBossDate()
	{
		GregorianCalendar now = new GregorianCalendar();
		return serialbossFormat.format(now.getTime());
	}

	public static synchronized Date getNow()
	{
		GregorianCalendar now = new GregorianCalendar();
		return now.getTime();
	}

	public static synchronized String getNowMMDD()
	{
		GregorianCalendar now = new GregorianCalendar();
		return MMddFormat.format(now.getTime());
	}

	public static synchronized String getNowyyyyMMdd()
	{
		GregorianCalendar now = new GregorianCalendar();
		return yyyyMMddFormat.format(now.getTime());
	}

	public static synchronized String getNowyyyyMMddCN()
	{
		GregorianCalendar now = new GregorianCalendar();
		return yyyyMMddFormatCN.format(now.getTime());
	}

	public static synchronized String getNowyyyyMdECN()
	{
		GregorianCalendar now = new GregorianCalendar();
		return yyyyMdEFormatCN.format(now.getTime());
	}

	public static synchronized String getNowyyyyMMddHHmmss()
	{
		GregorianCalendar now = new GregorianCalendar();
		return yyyyMMddHHmmssFormat.format(now.getTime());
	}

	public static synchronized String getNowyyyyMMddhhmm()
	{
		GregorianCalendar now = new GregorianCalendar();
		return yyyyMMddhhmmFormat.format(now.getTime());
	}

	public static synchronized String getNowyyyyMMddhhmmCN()
	{
		GregorianCalendar now = new GregorianCalendar();
		return yyyyMMddhhmmFormatCN.format(now.getTime());
	}

	public static synchronized String getNowyyyyMMddhhmmss()
	{
		GregorianCalendar now = new GregorianCalendar();
		return yyyyMMddhhmmssFormat.format(now.getTime());
	}

	public static synchronized String getNowyyyyMMddhhmmssCN()
	{
		GregorianCalendar now = new GregorianCalendar();
		return yyyyMMddhhmmssFormatCN.format(now.getTime());
	}

	static 
	{
		yyyyMdEFormatCN = new SimpleDateFormat("yyyyå¹´Mæœˆdæ—? E", Locale.CHINESE);
	}
}
