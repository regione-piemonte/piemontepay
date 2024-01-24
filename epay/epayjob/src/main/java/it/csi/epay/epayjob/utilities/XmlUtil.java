/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.utilities;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XmlUtil {
	private static final XStream xstream = new XStream(new DomDriver());
	
	public static String obj2Xml(Object o)
	{
		try {
			return  xstream.toXML(o);
		} 
		catch (Exception e) {
			return "Oggetto non serializzabile.";
		}
	}
	
	public static Object xml2Obj(String xml)
	{
		try {
			return xstream.fromXML(xml);
		} 
		catch (Exception e) {
			return null;
		}
	}

}
