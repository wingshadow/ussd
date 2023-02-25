/**
 * EndUssdRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.webserviceclient.bo;

public class EndUssdRequest  implements java.io.Serializable {
    private java.lang.String msisdn;

    private java.lang.String ussdMessage;

    private java.lang.String reciveid;

    private java.lang.String ussdIdentifier;

    public EndUssdRequest() {
    }

    public EndUssdRequest(
           java.lang.String msisdn,
           java.lang.String ussdMessage,
           java.lang.String reciveid,
           java.lang.String ussdIdentifier) {
           this.msisdn = msisdn;
           this.ussdMessage = ussdMessage;
           this.reciveid = reciveid;
           this.ussdIdentifier = ussdIdentifier;
    }


    /**
     * Gets the msisdn value for this EndUssdRequest.
     * 
     * @return msisdn
     */
    public java.lang.String getMsisdn() {
        return msisdn;
    }


    /**
     * Sets the msisdn value for this EndUssdRequest.
     * 
     * @param msisdn
     */
    public void setMsisdn(java.lang.String msisdn) {
        this.msisdn = msisdn;
    }


    /**
     * Gets the ussdMessage value for this EndUssdRequest.
     * 
     * @return ussdMessage
     */
    public java.lang.String getUssdMessage() {
        return ussdMessage;
    }


    /**
     * Sets the ussdMessage value for this EndUssdRequest.
     * 
     * @param ussdMessage
     */
    public void setUssdMessage(java.lang.String ussdMessage) {
        this.ussdMessage = ussdMessage;
    }


    /**
     * Gets the reciveid value for this EndUssdRequest.
     * 
     * @return reciveid
     */
    public java.lang.String getReciveid() {
        return reciveid;
    }


    /**
     * Sets the reciveid value for this EndUssdRequest.
     * 
     * @param reciveid
     */
    public void setReciveid(java.lang.String reciveid) {
        this.reciveid = reciveid;
    }


    /**
     * Gets the ussdIdentifier value for this EndUssdRequest.
     * 
     * @return ussdIdentifier
     */
    public java.lang.String getUssdIdentifier() {
        return ussdIdentifier;
    }


    /**
     * Sets the ussdIdentifier value for this EndUssdRequest.
     * 
     * @param ussdIdentifier
     */
    public void setUssdIdentifier(java.lang.String ussdIdentifier) {
        this.ussdIdentifier = ussdIdentifier;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EndUssdRequest)) return false;
        EndUssdRequest other = (EndUssdRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.msisdn==null && other.getMsisdn()==null) || 
             (this.msisdn!=null &&
              this.msisdn.equals(other.getMsisdn()))) &&
            ((this.ussdMessage==null && other.getUssdMessage()==null) || 
             (this.ussdMessage!=null &&
              this.ussdMessage.equals(other.getUssdMessage()))) &&
            ((this.reciveid==null && other.getReciveid()==null) || 
             (this.reciveid!=null &&
              this.reciveid.equals(other.getReciveid()))) &&
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
        if (getMsisdn() != null) {
            _hashCode += getMsisdn().hashCode();
        }
        if (getUssdMessage() != null) {
            _hashCode += getUssdMessage().hashCode();
        }
        if (getReciveid() != null) {
            _hashCode += getReciveid().hashCode();
        }
        if (getUssdIdentifier() != null) {
            _hashCode += getUssdIdentifier().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EndUssdRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:bo.webservice.inspur.com", "EndUssdRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("msisdn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "msisdn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ussdMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ussdMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reciveid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "reciveid"));
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
