// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TestContinue.java

package com.webserviceclient.USSDAdapterService;

import com.webserviceclient.bo.UssdContinueRequest;
import com.webserviceclient.bo.UssdContinueResponse;
import java.io.PrintStream;

// Referenced classes of package com.webserviceclient.USSDAdapterService:
//			USSDAdapterServiceImplServiceLocator, USSDAdapterServiceSoapBindingStub

public class TestContinue
{

	public TestContinue()
	{
	}

	public static void main(String arg[])
	{
		try
		{
			USSDAdapterServiceSoapBindingStub binding = (USSDAdapterServiceSoapBindingStub)(new USSDAdapterServiceImplServiceLocator()).getUSSDAdapterService();
			binding.setTimeout(60000);
			UssdContinueRequest req = new UssdContinueRequest();
			req.setSenderAddress("13606330816");
			req.setUssdIdentifier("13");
			req.setUssdMessage("2");
			UssdContinueResponse res = binding.ussdContinue(req);
			System.out.println(res.getUssdMessge());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}
