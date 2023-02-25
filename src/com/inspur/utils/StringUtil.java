package com.inspur.utils;

import java.io.PrintStream;
import java.security.MessageDigest;
import org.apache.oro.text.regex.*;

public class StringUtil
{

	private static final String hexDigits[] = {
		"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", 
		"a", "b", "c", "d", "e", "f"
	};

	public StringUtil()
	{
	}

	private static String byteToHexString(byte b)
	{
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return (new StringBuilder(String.valueOf(hexDigits[d1]))).append(hexDigits[d2]).toString();
	}

	private static String byteArrayToHexString(byte b[])
	{
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
			sb.append(byteToHexString(b[i]));

		return sb.toString();
	}

	public static String MD5Encode(String origin)
	{
		String resultStr = null;
		try
		{
			resultStr = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultStr = byteArrayToHexString(md.digest(resultStr.getBytes()));
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return resultStr;
	}

	public static String ReplaceAll(String str, String str1, String str2)
	{
		if (str == null)
			return "";
		if (str1 == null || str2 == null)
			return str;
		org.apache.oro.text.regex.Pattern pattern;
		org.apache.oro.text.regex.PatternMatcher matcher;
		PatternCompiler pc = new Perl5Compiler();
		try {
			pattern = pc.compile(str1);		
			matcher = new Perl5Matcher();
			return Util.substitute(matcher, pattern, new Perl5Substitution(str2, 1), str, -1);
		} catch (MalformedPatternException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static final String substr(String str, int byteCount)
	{
		if (str.length() <= 0)
			return "";
		if (str.length() >= str.length())
			return str.substring(0, byteCount);
		else
			return str;
	}

	public static String fitLen(String val, int len)
	{
		if (val.length() == len)
			return val;
		if (val.length() > len)
			return val.substring(0, len);
		String tmp = "";
		for (int i = 0; i < len - val.length(); i++)
			tmp = (new StringBuilder(String.valueOf(tmp))).append(" ").toString();

		return (new StringBuilder(String.valueOf(val))).append(tmp).toString();
	}

	public static boolean ishave(String a, String b)
	{
		int t = 0;
		for (int i = 0; i < b.split("k").length; i++)
			if (a.indexOf(b.split("k")[i]) > -1)
				t = 1;

		return t == 0;
	}

	public static boolean GxIsInteger(String Text)
	{
		boolean b = true;
		if (!Text.equals(""))
		{
			for (int j = 0; j < Text.length(); j++)
			{
				if (Text.charAt(j) >= '0' && Text.charAt(j) <= '9')
					continue;
				b = false;
				break;
			}

		} else
		{
			b = false;
		}
		return b;
	}

	public static boolean GxIsEmail(String objText)
	{
		if (!objText.equals(""))
		{
			int pos1 = objText.indexOf("@");
			int pos2 = objText.indexOf(".");
			int pos3 = objText.lastIndexOf("@");
			int pos4 = objText.lastIndexOf(".");
			if (pos1 <= 0 || pos1 == objText.length() || pos2 <= 0 || pos2 == objText.length())
				return false;
			return pos1 != pos2 - 1 && pos1 != pos2 + 1 && pos1 == pos3 && pos4 >= pos3;
		} else
		{
			return false;
		}
	}

	public static void main(String args[])
	{
		System.out.println(MD5Encode("ºº×Ö"));
	}

	public static String toBj(String s)
	{
		if (s == null || s.trim().equals(""))
			return s;
		StringBuffer sb = new StringBuffer(s.length());
		for (int i = 0; i < s.length(); i++)
		{
			char c = s.charAt(i);
			if (c == '\u3000')
				sb.append(' ');
			else
			if (isQjChar(c))
				sb.append((char)(c - 65248));
			else
				sb.append(c);
		}

		return sb.toString();
	}

	private static boolean isBjChar(char c)
	{
		int i = c;
		return i >= 32 && i <= 126;
	}

	private static boolean isQjChar(char c)
	{
		if (c == '\u3000')
			return true;
		int i = c - 65248;
		if (i < 32)
			return false;
		else
			return isBjChar((char)i);
	}

}
