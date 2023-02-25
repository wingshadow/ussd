package com.inspur.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;

// Referenced classes of package com.inspur.utils:
//			Base64

public class Encoder
{

	private static MessageDigest digest = null;
	private static boolean isInited = false;

	private Encoder()
	{
	}

	public static synchronized String getMD5_Base64(String input)
	{
		if (!isInited)
		{
			isInited = true;
			try
			{
				digest = MessageDigest.getInstance("MD5");
			}
			catch (Exception exception) { }
		}
		if (digest == null)
			return input;
		try
		{
			digest.update(input.getBytes("GBK"));
		}
		catch (UnsupportedEncodingException unsupportedencodingexception) { }
		byte rawData[] = digest.digest();
		byte encoded[] = Base64.encode(rawData);
		String retValue = new String(encoded);
		return retValue;
	}

	public static String encodeURL(String input)
	{
		return URLEncoder.encode(input);
	}

	public static String decodeURL(String input)
	{
		return URLDecoder.decode(input);
	}

	public static String filterUrl(String url)
	{
		String lowerUrl = url.toLowerCase();
		if (lowerUrl.indexOf("javascript:") >= 0 || lowerUrl.indexOf("file:") >= 0)
			return "";
		String protocol = "http://";
		String name = null;
		if (url.startsWith("http://"))
		{
			protocol = "http://";
			name = url.substring(protocol.length());
		} else
		if (url.startsWith("https://"))
		{
			protocol = "https://";
			name = url.substring(protocol.length());
		} else
		if (url.startsWith("ftp://"))
		{
			protocol = "ftp://";
			name = url.substring(protocol.length());
		} else
		if (url.startsWith("mailto:"))
		{
			protocol = "mailto:";
			name = url.substring(protocol.length());
		} else
		{
			name = url;
		}
		String ret;
		if (protocol.equals("mailto:"))
			try
			{
				ret = (new StringBuilder(String.valueOf(protocol))).append(name).toString();
			}
			catch (Exception ex)
			{
				ret = "";
			}
		else
			ret = (new StringBuilder(String.valueOf(protocol))).append(encodePath(name)).toString();
		return ret;
	}

	private static String encodePath(String path)
	{
		return path;
	}

}
