package com.inspur.utils;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

public class XmlUtil
{

	public XmlUtil()
	{
	}

	public static Document createXMLDocument(String xmlFile)
		throws Exception
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = null;
		doc = builder.parse(xmlFile);
		doc.normalize();
		return doc;
	}
}
