// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   UssdMenu.java

package com.webserviceclient.bo;

import java.io.Serializable;
import javax.xml.namespace.QName;
import org.apache.axis.description.ElementDesc;
import org.apache.axis.description.TypeDesc;
import org.apache.axis.encoding.Deserializer;
import org.apache.axis.encoding.Serializer;
import org.apache.axis.encoding.ser.BeanDeserializer;
import org.apache.axis.encoding.ser.BeanSerializer;

public class UssdMenu
	implements Serializable
{

	private Long id;
	private String interfacecode;
	private String memo;
	private String menuname;
	private Long menutype;
	private Long optype;
	private Long pid;
	private Long seq;
	private Long smsflag;
	private String svcid;
	private String svcname;
	private Object __equalsCalc;
	private boolean __hashCodeCalc;
	private static TypeDesc typeDesc;

	public UssdMenu()
	{
		__equalsCalc = null;
		__hashCodeCalc = false;
	}

	public UssdMenu(Long id, String interfacecode, String memo, String menuname, Long menutype, Long optype, Long pid, 
			Long seq, Long smsflag, String svcid, String svcname)
	{
		__equalsCalc = null;
		__hashCodeCalc = false;
		this.id = id;
		this.interfacecode = interfacecode;
		this.memo = memo;
		this.menuname = menuname;
		this.menutype = menutype;
		this.optype = optype;
		this.pid = pid;
		this.seq = seq;
		this.smsflag = smsflag;
		this.svcid = svcid;
		this.svcname = svcname;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getInterfacecode()
	{
		return interfacecode;
	}

	public void setInterfacecode(String interfacecode)
	{
		this.interfacecode = interfacecode;
	}

	public String getMemo()
	{
		return memo;
	}

	public void setMemo(String memo)
	{
		this.memo = memo;
	}

	public String getMenuname()
	{
		return menuname;
	}

	public void setMenuname(String menuname)
	{
		this.menuname = menuname;
	}

	public Long getMenutype()
	{
		return menutype;
	}

	public void setMenutype(Long menutype)
	{
		this.menutype = menutype;
	}

	public Long getOptype()
	{
		return optype;
	}

	public void setOptype(Long optype)
	{
		this.optype = optype;
	}

	public Long getPid()
	{
		return pid;
	}

	public void setPid(Long pid)
	{
		this.pid = pid;
	}

	public Long getSeq()
	{
		return seq;
	}

	public void setSeq(Long seq)
	{
		this.seq = seq;
	}

	public Long getSmsflag()
	{
		return smsflag;
	}

	public void setSmsflag(Long smsflag)
	{
		this.smsflag = smsflag;
	}

	public String getSvcid()
	{
		return svcid;
	}

	public void setSvcid(String svcid)
	{
		this.svcid = svcid;
	}

	public String getSvcname()
	{
		return svcname;
	}

	public void setSvcname(String svcname)
	{
		this.svcname = svcname;
	}

	public synchronized boolean equals(Object obj)
	{
		if (!(obj instanceof UssdMenu))
			return false;
		UssdMenu other = (UssdMenu)obj;
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (__equalsCalc != null)
		{
			return __equalsCalc == obj;
		} else
		{
			__equalsCalc = obj;
			boolean _equals = (id == null && other.getId() == null || id != null && id.equals(other.getId())) && (interfacecode == null && other.getInterfacecode() == null || interfacecode != null && interfacecode.equals(other.getInterfacecode())) && (memo == null && other.getMemo() == null || memo != null && memo.equals(other.getMemo())) && (menuname == null && other.getMenuname() == null || menuname != null && menuname.equals(other.getMenuname())) && (menutype == null && other.getMenutype() == null || menutype != null && menutype.equals(other.getMenutype())) && (optype == null && other.getOptype() == null || optype != null && optype.equals(other.getOptype())) && (pid == null && other.getPid() == null || pid != null && pid.equals(other.getPid())) && (seq == null && other.getSeq() == null || seq != null && seq.equals(other.getSeq())) && (smsflag == null && other.getSmsflag() == null || smsflag != null && smsflag.equals(other.getSmsflag())) && (svcid == null && other.getSvcid() == null || svcid != null && svcid.equals(other.getSvcid())) && (svcname == null && other.getSvcname() == null || svcname != null && svcname.equals(other.getSvcname()));
			__equalsCalc = null;
			return _equals;
		}
	}

	public synchronized int hashCode()
	{
		if (__hashCodeCalc)
			return 0;
		__hashCodeCalc = true;
		int _hashCode = 1;
		if (getId() != null)
			_hashCode += getId().hashCode();
		if (getInterfacecode() != null)
			_hashCode += getInterfacecode().hashCode();
		if (getMemo() != null)
			_hashCode += getMemo().hashCode();
		if (getMenuname() != null)
			_hashCode += getMenuname().hashCode();
		if (getMenutype() != null)
			_hashCode += getMenutype().hashCode();
		if (getOptype() != null)
			_hashCode += getOptype().hashCode();
		if (getPid() != null)
			_hashCode += getPid().hashCode();
		if (getSeq() != null)
			_hashCode += getSeq().hashCode();
		if (getSmsflag() != null)
			_hashCode += getSmsflag().hashCode();
		if (getSvcid() != null)
			_hashCode += getSvcid().hashCode();
		if (getSvcname() != null)
			_hashCode += getSvcname().hashCode();
		__hashCodeCalc = false;
		return _hashCode;
	}

	public static TypeDesc getTypeDesc()
	{
		return typeDesc;
	}

	public static Serializer getSerializer(String mechType, Class _javaType, QName _xmlType)
	{
		return new BeanSerializer(_javaType, _xmlType, typeDesc);
	}

	public static Deserializer getDeserializer(String mechType, Class _javaType, QName _xmlType)
	{
		return new BeanDeserializer(_javaType, _xmlType, typeDesc);
	}

	static 
	{
		typeDesc = new TypeDesc(UssdMenu.class, true);
		typeDesc.setXmlType(new QName("http://pojo.mportal.inspur.com", "UssdMenu"));
		ElementDesc elemField = new ElementDesc();
		elemField.setFieldName("id");
		elemField.setXmlName(new QName("", "id"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "long"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new ElementDesc();
		elemField.setFieldName("interfacecode");
		elemField.setXmlName(new QName("", "interfacecode"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new ElementDesc();
		elemField.setFieldName("memo");
		elemField.setXmlName(new QName("", "memo"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new ElementDesc();
		elemField.setFieldName("menuname");
		elemField.setXmlName(new QName("", "menuname"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new ElementDesc();
		elemField.setFieldName("menutype");
		elemField.setXmlName(new QName("", "menutype"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "long"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new ElementDesc();
		elemField.setFieldName("optype");
		elemField.setXmlName(new QName("", "optype"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "long"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new ElementDesc();
		elemField.setFieldName("pid");
		elemField.setXmlName(new QName("", "pid"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "long"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new ElementDesc();
		elemField.setFieldName("seq");
		elemField.setXmlName(new QName("", "seq"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "long"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new ElementDesc();
		elemField.setFieldName("smsflag");
		elemField.setXmlName(new QName("", "smsflag"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "long"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new ElementDesc();
		elemField.setFieldName("svcid");
		elemField.setXmlName(new QName("", "svcid"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new ElementDesc();
		elemField.setFieldName("svcname");
		elemField.setXmlName(new QName("", "svcname"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
	}
}
