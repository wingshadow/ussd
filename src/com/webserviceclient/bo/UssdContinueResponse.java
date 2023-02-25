/**
 * UssdContinueResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.webserviceclient.bo;

public class UssdContinueResponse  implements java.io.Serializable {
    private java.lang.String ussdMessge;

    public UssdContinueResponse() {
    }

    public UssdContinueResponse(
           java.lang.String ussdMessge) {
           this.ussdMessge = ussdMessge;
    }


    /**
     * Gets the ussdMessge value for this UssdContinueResponse.
     * 
     * @return ussdMessge
     */
    public java.lang.String getUssdMessge() {
        return ussdMessge;
    }


    /**
     * Sets the ussdMessge value for this UssdContinueResponse.
     * 
     * @param ussdMessge
     */
    public void setUssdMessge(java.lang.String ussdMessge) {
        this.ussdMessge = ussdMessge;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UssdContinueResponse)) return false;
        UssdContinueResponse other = (UssdContinueResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ussdMessge==null && other.getUssdMessge()==null) || 
             (this.ussdMessge!=null &&
              this.ussdMessge.equals(other.getUssdMessge())));
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
        if (getUssdMessge() != null) {
            _hashCode += getUssdMessge().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UssdContinueResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:bo.webservice.inspur.com", "UssdContinueResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ussdMessge");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ussdMessge"));
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
