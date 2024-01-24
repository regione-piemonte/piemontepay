/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.interfacecxf;

import javax.xml.bind.annotation.XmlAccessType;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "MyFaultBean", namespace = "http://interfacecxf.core.mdp.csi.it/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "it.csi.mdp.core.interfacecxf.MissingParameterException", propOrder = {"message"
//		,
//    "code",
//    "faultCode",
//    "faultMessage",
//    "faultDetail",
//    "detail"
})

public class CheckFaultBean 
{
		private String message;	
//	    private String code;
//	    private String faultCode;
//	    private String faultMessage;
//	    private String faultDetail;
	
	
//	private String detail;
	    public CheckFaultBean() { }

	    public String getMessage() {
	        return message;
	    }

	    public void setMessage(String message) {
	        this.message = message;
	    }

//		public String getCode()
//		{
//			return code;
//		}
//
//		public void setCode(String code)
//		{
//			this.code = code;
//		}
//
//		public String getFaultCode()
//		{
//			return faultCode;
//		}
//
//		public void setFaultCode(String faultCode)
//		{
//			this.faultCode = faultCode;
//		}
//
//		public String getFaultMessage()
//		{
//			return faultMessage;
//		}
//
//		public void setFaultMessage(String faultMessage)
//		{
//			this.faultMessage = faultMessage;
//		}
//
//		public String getDetail()
//		{
//			return detail;
//		}
//
//		public void setDetail(String detail)
//		{
//			this.detail = detail;
//		}
//
//		public String getFaultDetail()
//		{
//			return faultDetail;
//		}
//
//		public void setFaultDetail(String faultDetail)
//		{
//			this.faultDetail = faultDetail;
//		}
//	
}
