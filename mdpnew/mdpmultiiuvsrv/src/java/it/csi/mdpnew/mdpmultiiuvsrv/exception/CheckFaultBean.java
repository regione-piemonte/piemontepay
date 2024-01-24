/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpmultiiuvsrv.exception;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "MyFaultBean", namespace = "http://mdpnew.csi.it/mdpmultiiuv/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "it.csi.mdpnew.mdpmultiiuvsrv.exception.mdpmultiiuv.MissingParameterException", propOrder = {"message"})

public class CheckFaultBean {

	private String message;	
	
    public CheckFaultBean() { }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }	

}
