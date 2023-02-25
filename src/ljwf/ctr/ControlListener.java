// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ControlListener.java

package ljwf.ctr;

import javax.servlet.*;

public class ControlListener
	implements ServletContextListener
{

	public static String confdir = "";

	public ControlListener()
	{
	}

	public void contextInitialized(ServletContextEvent arg0)
	{
		confdir = arg0.getServletContext().getRealPath("/");
	}

	public void contextDestroyed(ServletContextEvent servletcontextevent)
	{
	}

}
