/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities;

import org.apache.commons.lang3.StringUtils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


public class XmlUtil {
	private static XStream xstream = new XStream(new DomDriver());
	
	private XmlUtil () {
	}
	public static String obj2Xml(Object o)
	{
		if (o == null) return "<NULL>";
		try {
			return  xstream.toXML(o);
		} 
		catch (Exception e) {
			return "Oggetto non serializzabile.";
		}
	}
	
	public static Object xml2Obj(String xml)
	{
		if (StringUtils.isBlank(xml)) return null;
		try {
			return xstream.fromXML(xml);
		} 
		catch (Exception e) {
			return null;
		}
	}

}
