/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.model;

import java.io.Serializable;
import java.math.BigDecimal;


public class TaglioMarca implements Serializable {

    private static final long serialVersionUID = 6817672157676927519L;

    private Long idTaglioMarca;

    private String codiceMarca;

    private BigDecimal importo;

    private Integer limite;

    private Boolean controlloHash;

    private Integer lunghezzaHash;

    public Long getIdTaglioMarca () {
        return idTaglioMarca;
    }

    public void setIdTaglioMarca ( Long idTaglioMarca ) {
        this.idTaglioMarca = idTaglioMarca;
    }

    public String getCodiceMarca () {
        return codiceMarca;
    }

    public void setCodiceMarca ( String codiceMarca ) {
        this.codiceMarca = codiceMarca;
    }

    public BigDecimal getImporto () {
        return importo;
    }

    public void setImporto ( BigDecimal importo ) {
        this.importo = importo;
    }

    public Integer getLimite () {
        return limite;
    }

    public void setLimite ( Integer limite ) {
        this.limite = limite;
    }

    public Boolean getControlloHash () {
        return controlloHash;
    }

    public void setControlloHash ( Boolean controlloHash ) {
        this.controlloHash = controlloHash;
    }

    public Integer getLunghezzaHash () {
        return lunghezzaHash;
    }

    public void setLunghezzaHash ( Integer lunghezzaHash ) {
        this.lunghezzaHash = lunghezzaHash;
    }

}
