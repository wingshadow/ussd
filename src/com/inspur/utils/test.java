package com.inspur.utils;

import java.io.PrintStream;
import java.util.Calendar;


public class test
{

	public test()
	{
	}

	public static void main(String args[])
	{
		String tmp = "";
		String a = SystemUtil.getYear();
		String b = SystemUtil.getMM();
		Calendar cal = Calendar.getInstance();
		int max = cal.getActualMaximum(5);
		tmp = (new StringBuilder(String.valueOf(a))).append("-").append(b).append("-").append(max).append(" ").append("00:00:00").toString();
		System.out.println(tmp);
	}
}
