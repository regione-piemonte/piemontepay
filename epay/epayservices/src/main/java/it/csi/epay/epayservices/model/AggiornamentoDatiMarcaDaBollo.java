/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.model;

import java.io.Serializable;


public class AggiornamentoDatiMarcaDaBollo implements Serializable {

    private static final long serialVersionUID = -5815676788946729240L;

    private String iuv;

    private String iubd;
    
    private byte []  xmlReceiptMb;

    public String getIuv () {
        return iuv;
    }

    public void setIuv ( String iuv ) {
        this.iuv = iuv;
    }

    public String getIubd () {
        return iubd;
    }

    public void setIubd ( String iubd ) {
        this.iubd = iubd;
    }

    
    /**
     * @return the xmlReceiptMb
     */
    public byte [] getXmlReceiptMb () {
        return xmlReceiptMb;
    }

    
    /**
     * @param xmlReceiptMb the xmlReceiptMb to set
     */
    public void setXmlReceiptMb ( byte [] xmlReceiptMb ) {
        this.xmlReceiptMb = xmlReceiptMb;
    }
    
    

}
