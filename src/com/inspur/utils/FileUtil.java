package com.inspur.utils;

import java.io.File;

public class FileUtil
{

	public static final String next_file_token = "|lclg next file token|";

	public FileUtil()
	{
	}

	public static String getExtension(File f)
	{
		return f == null ? "" : getExtension(f.getName());
	}

	public static String getExtension(String filename)
	{
		return getExtension(filename, "");
	}

	public static String getExtension(String filename, String defExt)
	{
		if (filename != null && filename.length() > 0)
		{
			int i = filename.lastIndexOf(".");
			if (i > 0 && i < filename.length() - 1)
				return filename.substring(i + 1).toLowerCase();
		}
		return defExt;
	}

	public static String makeString(String src, int length)
	{
		if (src == null)
			src = "";
		int len = src.getBytes().length;
		if (len < length)
		{
			for (int i = 0; i < length - len; i++)
				src = (new StringBuilder(String.valueOf(src))).append(" ").toString();

		} else
		if (src.length() > length)
			src = src.substring(0, length - 1);
		return src;
	}
}
