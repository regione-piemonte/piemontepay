/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpiuvsrv.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;

public class ObjectStreamer  {
	/* (non-Javadoc)
   * @see it.csi.sipred.sipredsincro.business.service.messaggi.sipred.MessaggioStreamer#getMessageAsStringXml(it.csi.sipred.sipredsincro.dto.Messaggio)
   */
	public String getObjectAsStringXml(Object obj)
	{
		String xml = null;
		
		//int code = msg.getId();
		//MessageTypeClasses type = MessageTypeClasses.get(code);
		if(obj != null)
		{	
			//XStream xstream = new XStream();
			XmlFriendlyReplacer replacer = new XmlFriendlyReplacer("ddd", "_");
			XStream xstream = new XStream(new DomDriver("UTF-8", replacer));
			xstream.alias(obj.getClass().getName(), obj.getClass());
			
			
			//xstream.alias(type.getName(), type.getClassdef());
			xml = xstream.toXML(obj);
		}
		
		return xml;
	}
	
}
