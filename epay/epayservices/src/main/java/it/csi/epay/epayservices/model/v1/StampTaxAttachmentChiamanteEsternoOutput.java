/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.model.v1;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;


@SuppressWarnings ( "unused" )
public class StampTaxAttachmentChiamanteEsternoOutput extends AccessoChiamanteEsternoSincronoSplitOutput implements Serializable {

	private static final long serialVersionUID = 8377937832375101313L;

    private String iuv;

    @JsonInclude ( JsonInclude.Include.NON_NULL )
	private byte[] ricevutaXML;
	
	
    /**
     * @return the ricevutaXML
     */
    public byte [] getRicevutaXML () {
        return ricevutaXML;
    }

    
    /**
     * @param ricevutaXML the ricevutaXML to set
     */
    public void setRicevutaXML ( byte [] ricevutaXML ) {
        this.ricevutaXML = ricevutaXML;
    }

    
    /**
     * @return the iuv
     */
    public String getIuv () {
        return iuv;
    }


    
    /**
     * @param iuv the iuv to set
     */
    public void setIuv ( String iuv ) {
        this.iuv = iuv;
    }


    @Override
	public String toString () {
		return "StampTaxAttachmentChiamanteEsternoOutput ["
		                + (" codiceEsito=" + getCodiceEsito ()
                        + ", descrizioneEsito=" + getDescrizioneEsito () )
		                + ( iuv != null ? ", iuv=" + iuv + ", " : "" )
						+ ( ricevutaXML != null ? ", ricevutaXML=" + ricevutaXML + ", " : "" )
						+ "]";
	}

}
