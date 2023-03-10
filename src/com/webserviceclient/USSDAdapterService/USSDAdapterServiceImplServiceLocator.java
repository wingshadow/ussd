/**
 * USSDAdapterServiceImplServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.webserviceclient.USSDAdapterService;

public class USSDAdapterServiceImplServiceLocator extends org.apache.axis.client.Service implements com.webserviceclient.USSDAdapterService.USSDAdapterServiceImplService {

    public USSDAdapterServiceImplServiceLocator() {
    }


    public USSDAdapterServiceImplServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public USSDAdapterServiceImplServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for USSDAdapterService
    private java.lang.String USSDAdapterService_address = "http://10.16.248.5:80/mportal/services/USSDAdapterService";

    public java.lang.String getUSSDAdapterServiceAddress() {
        return USSDAdapterService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String USSDAdapterServiceWSDDServiceName = "USSDAdapterService";

    public java.lang.String getUSSDAdapterServiceWSDDServiceName() {
        return USSDAdapterServiceWSDDServiceName;
    }

    public void setUSSDAdapterServiceWSDDServiceName(java.lang.String name) {
        USSDAdapterServiceWSDDServiceName = name;
    }

    public com.webserviceclient.USSDAdapterService.USSDAdapterServiceImpl getUSSDAdapterService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(USSDAdapterService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getUSSDAdapterService(endpoint);
    }

    public com.webserviceclient.USSDAdapterService.USSDAdapterServiceImpl getUSSDAdapterService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
        	com.webserviceclient.USSDAdapterService.USSDAdapterServiceSoapBindingStub _stub = new com.webserviceclient.USSDAdapterService.USSDAdapterServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getUSSDAdapterServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setUSSDAdapterServiceEndpointAddress(java.lang.String address) {
        USSDAdapterService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.webserviceclient.USSDAdapterService.USSDAdapterServiceImpl.class.isAssignableFrom(serviceEndpointInterface)) {
            	com.webserviceclient.USSDAdapterService.USSDAdapterServiceSoapBindingStub _stub = new com.webserviceclient.USSDAdapterService.USSDAdapterServiceSoapBindingStub(new java.net.URL(USSDAdapterService_address), this);
                _stub.setPortName(getUSSDAdapterServiceWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("USSDAdapterService".equals(inputPortName)) {
            return getUSSDAdapterService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://10.16.248.5:80/mportal/services/USSDAdapterService", "USSDAdapterServiceImplService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://10.16.248.5:80/mportal/services/USSDAdapterService", "USSDAdapterService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("USSDAdapterService".equals(portName)) {
            setUSSDAdapterServiceEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
