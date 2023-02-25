// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TestWebService.java

package com.webserviceclient.USSDAdapterService;

import com.webserviceclient.bo.HandleUssdRequest;
import com.webserviceclient.bo.HandleUssdResponse;
import java.io.PrintStream;
import ljwf.Log;

// Referenced classes of package com.webserviceclient.USSDAdapterService:
//			USSDAdapterServiceImplServiceLocator, USSDAdapterServiceSoapBindingStub

public class TestWebService
{

	public TestWebService()
	{
	}

	public static void main(String arg[])
	{
		try
		{
			Log.info("aa");
			USSDAdapterServiceSoapBindingStub binding = (USSDAdapterServiceSoapBindingStub)(new USSDAdapterServiceImplServiceLocator()).getUSSDAdapterService();
			binding.setTimeout(60000);
			System.out.println(binding);
			HandleUssdRequest req = new HandleUssdRequest();
			req.setUssdIdentifier("13");
			req.setUssdMessage("*123#");
			req.setSenderAddress("13606330816");
			HandleUssdResponse res = binding.handleUssd(req);
			System.out.println(res.getUssdMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}
