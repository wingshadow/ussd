

package com.inspur.ussdplate.control;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

// Referenced classes of package com.inspur.ussdplate.control:
//			ServiceControl

public class USSDListener
	implements ServletContextListener
{

	public USSDListener()
	{
	}

	public void contextDestroyed(ServletContextEvent servletcontextevent)
	{
	}

	public void contextInitialized(ServletContextEvent sce)
	{
		ServiceControl.main(null);
	}
}
