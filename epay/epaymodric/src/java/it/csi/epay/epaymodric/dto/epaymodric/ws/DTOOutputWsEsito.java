/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.dto.epaymodric.ws;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * @author vsgro
 */
@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "dtoOutputWsEsito" )
public class DTOOutputWsEsito implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3669214391536565576L;

    @Deprecated public static final String ESITO_OK_DEFAULT = "OK";
    @Deprecated public static final String ESITO_CODICE_ERRORE_OK_DEFAULT = "200";
    @Deprecated public static final String ESITO_DESCRIZIONE_OK_DEFAULT = "Elaborazione terminata con successo";
    @Deprecated public static final String ESITO_DESCRIZIONE_OK_NO_RESULT = "Non sono presenti dati";
    @Deprecated public static final String ESITO_CODICE_ERRORE_KO_IS_EMPTY = "201";
    @Deprecated public static final String ESITO_DESCRIZIONE_KO_IS_EMPTY = "Valorizzare almeno uno dei parametri di inut";
    @Deprecated public static final String ESITO_KO_DEFAULT = "KO";
    @Deprecated public static final String ESITO_CODICE_ERRORE_KO_DEFAULT = "999";
    @Deprecated public static final String ESITO_DESCRIZIONE_KO_DEFAULT = "Errore in fase di elaborazione";
    @Deprecated public static final String ESITO_CODICE_ERRORE_NO_PARAM = "111";
    @Deprecated public static final String ESITO_DESCRIZIONE_KO_NO_PARAM = "Parametro obbligatorio non valorizzato $1";

    private String esito;

    private String codiceErrore;

    private String descrizione;

    public DTOOutputWsEsito ( String esito, String codiceErrore, String descrizione ) {
        super ();
        this.esito = esito;
        this.codiceErrore = codiceErrore;
        this.descrizione = descrizione;
    }
    
    

    public DTOOutputWsEsito() {
		
	}



	public String getEsito () {
        return esito;
    }

    public void setEsito ( String esito ) {
        this.esito = esito;
    }

    public String getCodiceErrore () {
        return codiceErrore;
    }

    public void setCodiceErrore ( String codiceErrore ) {
        this.codiceErrore = codiceErrore;
    }

    public String getDescrizione () {
        return descrizione;
    }

    public void setDescrizione ( String descrizione ) {
        this.descrizione = descrizione;
    }

    @Override
    public String toString () {
        StringBuffer stringBuffer = new StringBuffer ();
        stringBuffer.append ( "esito: " + esito + " " );
        stringBuffer.append ( "codiceErrore: " + codiceErrore + " " );
        stringBuffer.append ( "descrizione: " + descrizione + " " );
        return stringBuffer.toString ();
    }

}
