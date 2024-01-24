/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.model;

import java.io.Serializable;
import java.util.List;


/**
 *
 */

public class PagamentoMarcaBolloChiamanteEsternoOutput extends AccessoChiamanteEsternoSincronoSplitOutput implements Serializable {

    private static final long serialVersionUID = 8377937832375101313L;

    private String urlWisp;

    private String iuvDocumento;

    private List<String> elencoIuvMarcaBollo;

    public String getUrlWisp () {
        return urlWisp;
    }

    public void setUrlWisp ( String urlWisp ) {
        this.urlWisp = urlWisp;
    }

    public String getIuvDocumento () {
        return iuvDocumento;
    }

    public void setIuvDocumento ( String iuvDocumento ) {
        this.iuvDocumento = iuvDocumento;
    }

    public List<String> getElencoIuvMarcaBollo () {
        return elencoIuvMarcaBollo;
    }

    public void setElencoIuvMarcaBollo ( List<String> elencoIuvMarcaBollo ) {
        this.elencoIuvMarcaBollo = elencoIuvMarcaBollo;
    }

    @Override
    public String toString () {
        return "PagamentoIuvChiamanteEsternoOutput ["
            + ( urlWisp != null ? "urlWisp=" + urlWisp + ", " : "" )
            + "]";
    }

}
