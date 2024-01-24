/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class PagamentoComponenti implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long idComponente;
	private Integer progressivo;
	private String causale;
	private String datiSpecificiRiscossione;
	private String codiceTributo;

    private Integer annoAccertamento;

    private String numeroAccertamento;
	private BigDecimal importo;
	private String utenteUltimaModifica;
	/**
	 * @return the idComponente
	 */
	public Long getIdComponente() {
		return idComponente;
	}
	/**
	 * @param idComponente the idComponente to set
	 */
	public void setIdComponente(Long idComponente) {
		this.idComponente = idComponente;
	}
	/**
	 * @return the progressivo
	 */
	public Integer getProgressivo() {
		return progressivo;
	}
	/**
	 * @param progressivo the progressivo to set
	 */
	public void setProgressivo(Integer progressivo) {
		this.progressivo = progressivo;
	}
	/**
	 * @return the causale
	 */
	public String getCausale() {
		return causale;
	}
	/**
	 * @param causale the causale to set
	 */
	public void setCausale(String causale) {
		this.causale = causale;
	}
	/**
	 * @return the datiSpecificiRiscossione
	 */
	public String getDatiSpecificiRiscossione() {
		return datiSpecificiRiscossione;
	}
	/**
	 * @param datiSpecificiRiscossione the datiSpecificiRiscossione to set
	 */
	public void setDatiSpecificiRiscossione(String datiSpecificiRiscossione) {
		this.datiSpecificiRiscossione = datiSpecificiRiscossione;
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

    /**
     * @return the importo
     */
	public BigDecimal getImporto() {
		return importo;
	}
	/**
	 * @param importo the importo to set
	 */
	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}
	/**
	 * @return the utente
	 */
	public String getUtenteUltimaModifica() {
		return utenteUltimaModifica;
	}
	/**
	 * @param utente the utente to set
	 */
	public void setUtenteUltimaModifica(String utenteUltimaModifica) {
		this.utenteUltimaModifica = utenteUltimaModifica;
	}
    
    /**
     * @return the codiceTributo
     */
    public String getCodiceTributo () {
        return codiceTributo;
    }
    
    /**
     * @param codiceTributo the codiceTributo to set
     */
    public void setCodiceTributo ( String codiceTributo ) {
        this.codiceTributo = codiceTributo;
    }
	
	
	
}
