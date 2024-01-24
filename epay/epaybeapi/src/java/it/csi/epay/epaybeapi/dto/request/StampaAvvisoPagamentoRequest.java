/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaybeapi.dto.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import it.csi.epay.epayservices.model.Anagrafica;
import it.csi.epay.epayservices.model.Ente;


/**
 *
 */

public class StampaAvvisoPagamentoRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String iuv;

    private String codAvviso;

    private BigDecimal importoTotale;

    private String causale;

    private Date scadenza;

    private Ente ente;

    private Anagrafica soggettoDebitore;

    private Long idTipoPagamento;

    private Boolean bollettinoPostale;

    public String getIuv () {
        return iuv;
    }

    public void setIuv ( String iuv ) {
        this.iuv = iuv;
    }

    public String getCodAvviso () {
        return codAvviso;
    }

    public void setCodAvviso ( String codAvviso ) {
        this.codAvviso = codAvviso;
    }

    public BigDecimal getImportoTotale () {
        return importoTotale;
    }

    public void setImportoTotale ( BigDecimal importoTotale ) {
        this.importoTotale = importoTotale;
    }

    public String getCausale () {
        return causale;
    }

    public void setCausale ( String causale ) {
        this.causale = causale;
    }

    public Date getScadenza () {
        return scadenza;
    }

    public void setScadenza ( Date scadenza ) {
        this.scadenza = scadenza;
    }

    public Ente getEnte () {
        return ente;
    }

    public void setEnte ( Ente ente ) {
        this.ente = ente;
    }

    public Anagrafica getSoggettoDebitore () {
        return soggettoDebitore;
    }

    public void setSoggettoDebitore ( Anagrafica soggettoDebitore ) {
        this.soggettoDebitore = soggettoDebitore;
    }

    public Long getIdTipoPagamento () {
        return idTipoPagamento;
    }

    public void setIdTipoPagamento ( Long idTipoPagamento ) {
        this.idTipoPagamento = idTipoPagamento;
    }

    public Boolean getBollettinoPostale () {
        return bollettinoPostale;
    }

    public void setBollettinoPostale ( Boolean bollettinoPostale ) {
        this.bollettinoPostale = bollettinoPostale;
    }

}
