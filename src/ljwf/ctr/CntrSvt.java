// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   CntrSvt.java

package ljwf.ctr;

import com.inspur.utils.SystemUtil;
import java.io.IOException;
import java.lang.reflect.Method;
import javax.servlet.*;
import javax.servlet.http.*;
import ljwf.Log;
import ljwf.exception.AppException;

// Referenced classes of package ljwf.ctr:
//			Config

public class CntrSvt extends HttpServlet
{

	public CntrSvt()
	{
	}

	public void init()
		throws ServletException
	{
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		perform(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		perform(request, response);
	}

	public void destroy()
	{
	}

	public void perform(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		String s = request.getPathInfo();
		if (s == null)
			return;
		String sFull = (new StringBuilder(String.valueOf(request.getContextPath()))).append("/servlet").append(s).toString();
		s = s.substring(1);
		boolean accessPermitted = true;
		String accessMsg = "";
		String openCommands = (new StringBuilder(",")).append(SystemUtil.openCommands).append(",login,logout,toLogin,bs,").toString();
		if (openCommands.indexOf((new StringBuilder(",")).append(s).append(",").toString()) == -1)
		{
			String loginFlag = (String)request.getSession().getAttribute("loginFlag");
			if (loginFlag == null || !loginFlag.equals("ok"))
				s = "toLogin";
		}
		String m[] = Config.get(s);
		if (m == null)
			return;
		if (!s.startsWith("tf/"))
		{
			request.setCharacterEncoding("gbk");
			response.setContentType("text/html;charset=gbk");
		}
		boolean logFlag = true;
		try
		{
			if (!accessPermitted)
			{
				accessMsg = "您无权访问该模块！";
				throw new AppException(request, accessMsg);
			}
			for (int i = 0; i < m.length; i++)
				if (m[i] != null && !"null".equals(m[i].toLowerCase()))
					if (m[i].startsWith("/"))
					{
						request.getRequestDispatcher("/head.jsp").include(request, response);
						request.getRequestDispatcher(m[i]).include(request, response);
					} else
					{
						Class cls = Class.forName(m[i]);
						Class p1 = Class.forName("javax.servlet.http.HttpServletRequest");
						Class p2 = Class.forName("javax.servlet.http.HttpServletResponse");
						Method md = cls.getMethod("doExec", new Class[] {
							p1, p2
						});
						md.invoke(cls.newInstance(), new Object[] {
							request, response
						});
					}

		}
		catch (Exception e)
		{
			e.printStackTrace();
			Log.error(e);
			request.getSession().getServletContext().getRequestDispatcher("/jsp/failure.jsp").forward(request, response);
		}
	}
}
