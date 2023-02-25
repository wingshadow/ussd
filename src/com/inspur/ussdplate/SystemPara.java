package com.inspur.ussdplate;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Properties;

public class SystemPara
{

	private String leadchar;
	private String endchar;
	private String parachar;
	private String upkey;
	private String downkey;
	private String exitkey;
	private String rebackkey;
	private String masserverip;
	private int masserverport;

	public SystemPara()
	{
		readConfig();
	}

	public void readConfig()
	{
		try
		{
			Properties p = new Properties();
			BufferedInputStream in = new BufferedInputStream(new FileInputStream("d:\\system.properties"));
			p.load(in);
			leadchar = p.getProperty("LEADCHAR");
			endchar = p.getProperty("ENDCHAR");
			parachar = p.getProperty("PARACHAR");
			upkey = p.getProperty("UPKEY");
			downkey = p.getProperty("DOWNKEY");
			exitkey = p.getProperty("EXITKEY");
			rebackkey = p.getProperty("REBACKKEY");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public String getParseContent(String content)
	{
		String parameter = null;
		if (content.indexOf(leadchar) != -1)
		{
			parameter = content.substring(content.indexOf(leadchar), content.length());
			if (parameter.lastIndexOf(endchar) != -1)
				parameter = parameter.substring(0, parameter.lastIndexOf(endchar));
		}
		return parameter;
	}

	public String[] analyzePara(String parameter)
	{
		String arg[] = new String[2];
		String result = null;
		if (parameter.indexOf(parachar) != -1)
			result = "success";
		else
			result = "success";
		arg[0] = result;
		if (parameter.indexOf(exitkey) != -1)
			arg[1] = String.valueOf(113);
		else
			arg[1] = String.valueOf(112);
		return arg;
	}

	public String[] process(String content)
	{
		readConfig();
		String para = getParseContent(content);
		String result[] = analyzePara(para);
		return result;
	}
}
