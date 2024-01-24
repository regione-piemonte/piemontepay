/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.model;

import java.io.Serializable;


/**
 *
 */

public class AggiornaPosizioneDebitoriaChiamanteEsternoOutput implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    String codice;

    String messaggio;

    public String getCodice () {
        return codice;
    }

    public void setCodice ( String codice ) {
        this.codice = codice;
    }

    public String getMessaggio () {
        return messaggio;
    }

    public void setMessaggio ( String messaggio ) {
        this.messaggio = messaggio;
    }

    //    @Override
    //    public String toString () {
    //        return "GetIuvChiamanteEsternoInput [" + ( codiceFiscaleEnte != null ? "codiceFiscaleEnte=" + codiceFiscaleEnte + ", " : "" ) + ( causale != null ? "causale=" + causale + ", " : "" )
    //                        + ( tipoPagamento != null ? "tipoPagamento=" + tipoPagamento + ", " : "" ) + ( importo != null ? "importo=" + importo + ", " : "" )
    //                        + ( nome != null ? "nome=" + nome + ", " : "" ) + ( cognome != null ? "cognome=" + cognome + ", " : "" )
    //                        + ( ragioneSociale != null ? "ragioneSociale=" + ragioneSociale + ", " : "" ) + ( email != null ? "email=" + email + ", " : "" )
    //                        + ( codiceFiscalePartitaIVAPagatore != null ? "codiceFiscalePartitaIVAPagatore=" + codiceFiscalePartitaIVAPagatore + ", " : "" )
    //                        + ( componentiPagamento != null ? "componentiPagamento=" + componentiPagamento : "" ) + "]";
    //    }

}
