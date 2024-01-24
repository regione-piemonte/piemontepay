/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import java.math.BigDecimal;
import java.util.Date;


/** dto facade <-> business <-> persistence */
public class PagamentiExportDto extends PosizioneDebitoriaDto {

    private static final long serialVersionUID = 1L;

    private String statoPagamento;

    private BigDecimal importoPagato;

    private Date dataPagamento;

    private String codiceVersamento;

    //RDI-23
    private String causale;
    
    private String costiDiNotifica;

    public PagamentiExportDto ( Long id ) {
        super ( id );
    }

    public String getStatoPagamento () {
        return statoPagamento;
    }

    public void setStatoPagamento ( String statoPagamento ) {
        this.statoPagamento = statoPagamento;
    }

    public BigDecimal getImportoPagato () {
        return importoPagato;
    }

    public void setImportoPagato ( BigDecimal importoPagato ) {
        this.importoPagato = importoPagato;
    }

    public Date getDataPagamento () {
        return dataPagamento;
    }

    public void setDataPagamento ( Date dataPagamento ) {
        this.dataPagamento = dataPagamento;
    }

    public String getCodiceVersamento () {
        return codiceVersamento;
    }

    public void setCodiceVersamento ( String codiceVersamento ) {
        this.codiceVersamento = codiceVersamento;
    }

    public String getCausale () {
        return causale;
    }

    public void setCausale ( String causale ) {
        this.causale = causale;
    }

	public String getCostiDiNotifica() {
		return costiDiNotifica;
	}

	public void setCostiDiNotifica(String costiDiNotifica) {
		this.costiDiNotifica = costiDiNotifica;
	}

    
}
