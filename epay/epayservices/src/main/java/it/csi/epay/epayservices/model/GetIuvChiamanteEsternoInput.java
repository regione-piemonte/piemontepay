/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


/**
 *
 */

public class GetIuvChiamanteEsternoInput extends GetIuvCommonChiamanteEsternoInput implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tipoPagamento;
    
    private List<AccessoChiamanteEsternoSincronoComponentePagamentoDSRInput> componentiPagamento;

    private BigDecimal importo;

    /**
     * @return the componentiPagamento
     */
    public List<AccessoChiamanteEsternoSincronoComponentePagamentoDSRInput> getComponentiPagamento () {
        return componentiPagamento;
    }

    /**
     * @param componentiPagamento the componentiPagamento to set
     */
    public void setComponentiPagamento ( List<AccessoChiamanteEsternoSincronoComponentePagamentoDSRInput> componentiPagamento ) {
        this.componentiPagamento = componentiPagamento;
    }

    public BigDecimal getImporto () {
        return importo;
    }

    public void setImporto ( BigDecimal importo ) {
        this.importo = importo;
    }

    public String getTipoPagamento () {
        return tipoPagamento;
    }

    public void setTipoPagamento ( String tipoPagamento ) {
        this.tipoPagamento = tipoPagamento;
    }

    @Override
    public String toString () {
        return "GetIuvChiamanteEsternoInput [" + ( codiceFiscaleEnte != null ? "codiceFiscaleEnte=" + codiceFiscaleEnte + ", " : "" )
            + ( causale != null ? "causale=" + causale + ", " : "" )
            + ( tipoPagamento != null ? "tipoPagamento=" + tipoPagamento + ", " : "" ) + ( importo != null ? "importo=" + importo + ", " : "" )
            + ( nome != null ? "nome=" + nome + ", " : "" ) + ( cognome != null ? "cognome=" + cognome + ", " : "" )
            + ( ragioneSociale != null ? "ragioneSociale=" + ragioneSociale + ", " : "" ) + ( email != null ? "email=" + email + ", " : "" )
            + ( codiceFiscalePartitaIVAPagatore != null ? "codiceFiscalePartitaIVAPagatore=" + codiceFiscalePartitaIVAPagatore + ", " : "" )
            + ( componentiPagamento != null ? "componentiPagamento=" + componentiPagamento : "" ) + "]";
    }

}
