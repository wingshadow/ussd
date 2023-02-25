package com.inspur.utils;

import java.io.PrintStream;

public class EscapeUnescape
{

	public EscapeUnescape()
	{
	}

	public static String escape(String src)
	{
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);
		for (int i = 0; i < src.length(); i++)
		{
			char j = src.charAt(i);
			if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j))
				tmp.append(j);
			else
			if (j < '\u0100')
			{
				tmp.append("%");
				if (j < '\020')
					tmp.append("0");
				tmp.append(Integer.toString(j, 16));
			} else
			{
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}

		return tmp.toString();
	}

	public static String unescape(String src)
	{
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0;
		int pos = 0;
		while (lastPos < src.length()) 
		{
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos)
			{
				if (src.charAt(pos + 1) == 'u')
				{
					char ch = (char)Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else
				{
					char ch = (char)Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else
			if (pos == -1)
			{
				tmp.append(src.substring(lastPos));
				lastPos = src.length();
			} else
			{
				tmp.append(src.substring(lastPos, pos));
				lastPos = pos;
			}
		}
		return tmp.toString();
	}

	public static void main(String args[])
	{
		String tmp = "~!@#$%^&*()_+|\\=-,./?><;'][{}\"";
		System.out.println((new StringBuilder("testing escape : ")).append(tmp).toString());
		tmp = escape(tmp);
		System.out.println(tmp);
		System.out.println((new StringBuilder("testing unescape :")).append(tmp).toString());
		System.out.println(unescape(tmp));
	}
}
