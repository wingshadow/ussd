/**
 * UssdContinueRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.webserviceclient.bo;

public class UssdContinueRequest  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5607508900760084930L;

	private java.lang.String ussdMessage;

    private java.lang.String senderAddress;

    private java.lang.String ussdIdentifier;

    public UssdContinueRequest() {
    }

    public UssdContinueRequest(
           java.lang.String ussdMessage,
           java.lang.String senderAddress,
           java.lang.String ussdIdentifier) {
           this.ussdMessage = ussdMessage;
           this.senderAddress = senderAddress;
           this.ussdIdentifier = ussdIdentifier;
    }


    /**
     * Gets the ussdMessage value for this UssdContinueRequest.
     * 
     * @return ussdMessage
     */
    public java.lang.String getUssdMessage() {
        return ussdMessage;
    }


    /**
     * Sets the ussdMessage value for this UssdContinueRequest.
     * 
     * @param ussdMessage
     */
    public void setUssdMessage(java.lang.String ussdMessage) {
        this.ussdMessage = ussdMessage;
    }


    /**
     * Gets the senderAddress value for this UssdContinueRequest.
     * 
     * @return senderAddress
     */
    public java.lang.String getSenderAddress() {
        return senderAddress;
    }


    /**
     * Sets the senderAddress value for this UssdContinueRequest.
     * 
     * @param senderAddress
     */
    public void setSenderAddress(java.lang.String senderAddress) {
        this.senderAddress = senderAddress;
    }


    /**
     * Gets the ussdIdentifier value for this UssdContinueRequest.
     * 
     * @return ussdIdentifier
     */
    public java.lang.String getUssdIdentifier() {
        return ussdIdentifier;
    }


    /**
     * Sets the ussdIdentifier value for this UssdContinueRequest.
     * 
     * @param ussdIdentifier
     */
    public void setUssdIdentifier(java.lang.String ussdIdentifier) {
        this.ussdIdentifier = ussdIdentifier;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UssdContinueRequest)) return false;
        UssdContinueRequest other = (UssdContinueRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ussdMessage==null && other.getUssdMessage()==null) || 
             (this.ussdMessage!=null &&
              this.ussdMessage.equals(other.getUssdMessage()))) &&
            ((this.senderAddress==null && other.getSenderAddress()==null) || 
             (this.senderAddress!=null &&
              this.senderAddress.equals(other.getSenderAddress()))) &&
            ((this.ussdIdentifier==null && other.getUssdIdentifier()==null) || 
             (this.ussdIdentifier!=null &&
              this.ussdIdentifier.equals(other.getUssdIdentifier())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getUssdMessage() != null) {
            _hashCode += getUssdMessage().hashCode();
        }
        if (getSenderAddress() != null) {
            _hashCode += getSenderAddress().hashCode();
        }
        if (getUssdIdentifier() != null) {
            _hashCode += getUssdIdentifier().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UssdContinueRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:bo.webservice.inspur.com", "UssdContinueRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ussdMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ussdMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("senderAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "senderAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ussdIdentifier");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ussdIdentifier"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
