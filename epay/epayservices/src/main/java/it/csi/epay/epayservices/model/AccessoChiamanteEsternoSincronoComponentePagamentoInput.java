/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;
import java.math.BigDecimal;


public class AccessoChiamanteEsternoSincronoComponentePagamentoInput implements Serializable {

    private static final long serialVersionUID = -6903007449346036409L;

    private Integer progressivo;

    private BigDecimal importo;

    private String causale;

    private Integer annoAccertamento;

    private String numeroAccertamento;

	private String datiSpecificiRiscossione;

    public Integer getProgressivo () {
        return progressivo;
    }

    public void setProgressivo ( Integer progressivo ) {
        this.progressivo = progressivo;
    }

    public BigDecimal getImporto () {
        return importo;
    }

    public void setImporto ( BigDecimal importo ) {
        this.importo = importo;
    }

    public String getCausale () {
        return causale;
    }

    public void setCausale ( String causale ) {
        this.causale = causale;
    }

    public Integer getAnnoAccertamento () {
        return annoAccertamento;
    }

    public void setAnnoAccertamento ( Integer annoAccertamento ) {
        this.annoAccertamento = annoAccertamento;
    }

    public String getNumeroAccertamento () {
        return numeroAccertamento;
    }

    public void setNumeroAccertamento ( String numeroAccertamento ) {
        this.numeroAccertamento = numeroAccertamento;
    }

	public String getDatiSpecificiRiscossione () {
		return datiSpecificiRiscossione;
	}

	public void setDatiSpecificiRiscossione ( String datiSpecificiRiscossione ) {
		this.datiSpecificiRiscossione = datiSpecificiRiscossione;
	}

	@Override
    public String toString () {
        return "AccessoChiamanteEsternoSincronoComponentePagamentoInput [" + ( progressivo != null ? "progressivo=" + progressivo + ", " : "" )
            + ( importo != null ? "importo=" + importo + ", " : "" ) + ( causale != null ? "causale=" + causale + ", " : "" )
            + ( annoAccertamento != null ? "annoAccertamento=" + annoAccertamento + ", " : "" )
            + ( numeroAccertamento != null ? "numeroAccertamento=" + numeroAccertamento : "" ) + "]";
    }

}
