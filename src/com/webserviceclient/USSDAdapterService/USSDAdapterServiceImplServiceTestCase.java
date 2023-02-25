/**
 * USSDAdapterServiceImplServiceTestCase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.webserviceclient.USSDAdapterService;

public class USSDAdapterServiceImplServiceTestCase extends junit.framework.TestCase {
    public USSDAdapterServiceImplServiceTestCase(java.lang.String name) {
        super(name);
    }

    public void testUSSDAdapterServiceWSDL() throws Exception {
        javax.xml.rpc.ServiceFactory serviceFactory = javax.xml.rpc.ServiceFactory.newInstance();
        java.net.URL url = new java.net.URL(new com.webserviceclient.USSDAdapterService.USSDAdapterServiceImplServiceLocator().getUSSDAdapterServiceAddress() + "?WSDL");
        javax.xml.rpc.Service service = serviceFactory.createService(url, new com.webserviceclient.USSDAdapterService.USSDAdapterServiceImplServiceLocator().getServiceName());
        assertTrue(service != null);
    }

    public void test1USSDAdapterServiceExample() throws Exception {
        com.webserviceclient.USSDAdapterService.USSDAdapterServiceSoapBindingStub binding;
        try {
            binding = (com.webserviceclient.USSDAdapterService.USSDAdapterServiceSoapBindingStub)
                          new com.webserviceclient.USSDAdapterService.USSDAdapterServiceImplServiceLocator().getUSSDAdapterService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.example(new java.lang.String());
        // TBD - validate results
    }

    public void test2USSDAdapterServiceMakeUssd() throws Exception {
        com.webserviceclient.USSDAdapterService.USSDAdapterServiceSoapBindingStub binding;
        try {
            binding = (com.webserviceclient.USSDAdapterService.USSDAdapterServiceSoapBindingStub)
                          new com.webserviceclient.USSDAdapterService.USSDAdapterServiceImplServiceLocator().getUSSDAdapterService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        com.webserviceclient.bo.MakeUssdResponse value = null;
        value = binding.makeUssd(new com.webserviceclient.bo.MakeUssdRequest());
        // TBD - validate results
    }

    public void test3USSDAdapterServiceHandleUssd() throws Exception {
        com.webserviceclient.USSDAdapterService.USSDAdapterServiceSoapBindingStub binding;
        try {
            binding = (com.webserviceclient.USSDAdapterService.USSDAdapterServiceSoapBindingStub)
                          new com.webserviceclient.USSDAdapterService.USSDAdapterServiceImplServiceLocator().getUSSDAdapterService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        com.webserviceclient.bo.HandleUssdResponse value = null;
        value = binding.handleUssd(new com.webserviceclient.bo.HandleUssdRequest());
        // TBD - validate results
    }

    public void test4USSDAdapterServiceUssdContinue() throws Exception {
        com.webserviceclient.USSDAdapterService.USSDAdapterServiceSoapBindingStub binding;
        try {
            binding = (com.webserviceclient.USSDAdapterService.USSDAdapterServiceSoapBindingStub)
                          new com.webserviceclient.USSDAdapterService.USSDAdapterServiceImplServiceLocator().getUSSDAdapterService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        com.webserviceclient.bo.UssdContinueResponse value = null;
        value = binding.ussdContinue(new com.webserviceclient.bo.UssdContinueRequest());
        // TBD - validate results
    }

    public void test5USSDAdapterServiceNotifyUssdEnd() throws Exception {
        com.webserviceclient.USSDAdapterService.USSDAdapterServiceSoapBindingStub binding;
        try {
            binding = (com.webserviceclient.USSDAdapterService.USSDAdapterServiceSoapBindingStub)
                          new com.webserviceclient.USSDAdapterService.USSDAdapterServiceImplServiceLocator().getUSSDAdapterService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.notifyUssdEnd(new com.webserviceclient.bo.NotifyUssdEndRequest());
        // TBD - validate results
    }

    public void test6USSDAdapterServiceEndUssd() throws Exception {
        com.webserviceclient.USSDAdapterService.USSDAdapterServiceSoapBindingStub binding;
        try {
            binding = (com.webserviceclient.USSDAdapterService.USSDAdapterServiceSoapBindingStub)
                          new com.webserviceclient.USSDAdapterService.USSDAdapterServiceImplServiceLocator().getUSSDAdapterService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.endUssd(new com.webserviceclient.bo.EndUssdRequest(), 0);
        // TBD - validate results
    }

    public void test7USSDAdapterServiceGetUssdMenuByList() throws Exception {
        com.webserviceclient.USSDAdapterService.USSDAdapterServiceSoapBindingStub binding;
        try {
            binding = (com.webserviceclient.USSDAdapterService.USSDAdapterServiceSoapBindingStub)
                          new com.webserviceclient.USSDAdapterService.USSDAdapterServiceImplServiceLocator().getUSSDAdapterService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.getUssdMenuByList(new java.lang.Object[0]);
        // TBD - validate results
    }

    public void test8USSDAdapterServiceGetUssdMenuByContinueCommand() throws Exception {
        com.webserviceclient.USSDAdapterService.USSDAdapterServiceSoapBindingStub binding;
        try {
            binding = (com.webserviceclient.USSDAdapterService.USSDAdapterServiceSoapBindingStub)
                          new com.webserviceclient.USSDAdapterService.USSDAdapterServiceImplServiceLocator().getUSSDAdapterService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.getUssdMenuByContinueCommand(new java.lang.String(), new java.lang.String(), new java.lang.String[0]);
        // TBD - validate results
    }

    public void test9USSDAdapterServiceMain() throws Exception {
        com.webserviceclient.USSDAdapterService.USSDAdapterServiceSoapBindingStub binding;
        try {
            binding = (com.webserviceclient.USSDAdapterService.USSDAdapterServiceSoapBindingStub)
                          new com.webserviceclient.USSDAdapterService.USSDAdapterServiceImplServiceLocator().getUSSDAdapterService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.main(new java.lang.String[0]);
        // TBD - validate results
    }

}
