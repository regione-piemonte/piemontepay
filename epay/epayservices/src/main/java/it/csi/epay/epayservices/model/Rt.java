/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;

public class Rt implements Serializable {
	
	private static final long serialVersionUID = -4798523297268806406L;
	
	private Long idRt;
	private Integer codEsitoPagamento;
	private Timestamp dataoraMsgRicevuta;
	private String descEsitoPagamento;
	private String idApplicazione;
	private String idMsgRicevuta;
	private String idMsgRichiesta;
	private String idTransazione;
	private String iuv;
	private byte[] ricevutaPdf;
	private byte[] rtXml;
	private String tipoFirma;
	private Long idRegistro;
	private Integer idRR;
	private BigDecimal importoTotalePagato;
	
	private String codiceContestoPagamento;
    private String identificativoDominio;


    public BigDecimal getImportoTotalePagato () {
        return importoTotalePagato;
    }

    public void setImportoTotalePagato ( BigDecimal importoTotalePagato ) {
        this.importoTotalePagato = importoTotalePagato;
    }
	/**
	 * @return the idRR
	 */
	public Integer getIdRR() {
		return idRR;
	}
	/**
	 * @param idRR the idRR to set
	 */
	public void setIdRR(Integer idRR) {
		this.idRR = idRR;
	}
	/**
	 * @return the idRt
	 */
	public Long getIdRt() {
		return idRt;
	}
	/**
	 * @param idRt the idRt to set
	 */
	public void setIdRt(Long idRt) {
		this.idRt = idRt;
	}
	/**
	 * @return the codEsitoPagamento
	 */
	public Integer getCodEsitoPagamento() {
		return codEsitoPagamento;
	}
	/**
	 * @param codEsitoPagamento the codEsitoPagamento to set
	 */
	public void setCodEsitoPagamento(Integer codEsitoPagamento) {
		this.codEsitoPagamento = codEsitoPagamento;
	}
	/**
	 * @return the dataoraMsgRicevuta
	 */
	public Timestamp getDataoraMsgRicevuta() {
		return dataoraMsgRicevuta;
	}
	/**
	 * @param dataoraMsgRicevuta the dataoraMsgRicevuta to set
	 */
	public void setDataoraMsgRicevuta(Timestamp dataoraMsgRicevuta) {
		this.dataoraMsgRicevuta = dataoraMsgRicevuta;
	}
	/**
	 * @return the descEsitoPagamento
	 */
	public String getDescEsitoPagamento() {
		return descEsitoPagamento;
	}
	/**
	 * @param descEsitoPagamento the descEsitoPagamento to set
	 */
	public void setDescEsitoPagamento(String descEsitoPagamento) {
		this.descEsitoPagamento = descEsitoPagamento;
	}
	/**
	 * @return the idApplicazione
	 */
	public String getIdApplicazione() {
		return idApplicazione;
	}
	/**
	 * @param idApplicazione the idApplicazione to set
	 */
	public void setIdApplicazione(String idApplicazione) {
		this.idApplicazione = idApplicazione;
	}
	/**
	 * @return the idMsgRicevuta
	 */
	public String getIdMsgRicevuta() {
		return idMsgRicevuta;
	}
	/**
	 * @param idMsgRicevuta the idMsgRicevuta to set
	 */
	public void setIdMsgRicevuta(String idMsgRicevuta) {
		this.idMsgRicevuta = idMsgRicevuta;
	}
	/**
	 * @return the idMsgRichiesta
	 */
	public String getIdMsgRichiesta() {
		return idMsgRichiesta;
	}
	/**
	 * @param idMsgRichiesta the idMsgRichiesta to set
	 */
	public void setIdMsgRichiesta(String idMsgRichiesta) {
		this.idMsgRichiesta = idMsgRichiesta;
	}
	/**
	 * @return the idTransazione
	 */
	public String getIdTransazione() {
		return idTransazione;
	}
	/**
	 * @param idTransazione the idTransazione to set
	 */
	public void setIdTransazione(String idTransazione) {
		this.idTransazione = idTransazione;
	}
	/**
	 * @return the iuv
	 */
	public String getIuv() {
		return iuv;
	}
	/**
	 * @param iuv the iuv to set
	 */
	public void setIuv(String iuv) {
		this.iuv = iuv;
	}
	/**
	 * @return the ricevutaPdf
	 */
	public byte[] getRicevutaPdf() {
		return ricevutaPdf;
	}
	/**
	 * @param ricevutaPdf the ricevutaPdf to set
	 */
	public void setRicevutaPdf(byte[] ricevutaPdf) {
		this.ricevutaPdf = ricevutaPdf;
	}
	/**
	 * @return the rtXml
	 */
	public byte[] getRtXml() {
		return rtXml;
	}
	/**
	 * @param rtXml the rtXml to set
	 */
	public void setRtXml(byte[] rtXml) {
		this.rtXml = rtXml;
	}
	/**
	 * @return the tipoFirma
	 */
	public String getTipoFirma() {
		return tipoFirma;
	}
	/**
	 * @param tipoFirma the tipoFirma to set
	 */
	public void setTipoFirma(String tipoFirma) {
		this.tipoFirma = tipoFirma;
	}
	/**
	 * @return the idRegistro
	 */
	public Long getIdRegistro() {
		return idRegistro;
	}
	/**
	 * @param idRegistro the idRegistro to set
	 */
	public void setIdRegistro(Long idRegistro) {
		this.idRegistro = idRegistro;
	}
    
    /**
     * @return the codiceContestoPagamento
     */
    public String getCodiceContestoPagamento () {
        return codiceContestoPagamento;
    }

    
    /**
     * @param codiceContestoPagamento the codiceContestoPagamento to set
     */
    public void setCodiceContestoPagamento ( String codiceContestoPagamento ) {
        this.codiceContestoPagamento = codiceContestoPagamento;
    }

    
    /**
     * @return the identificativoDominio
     */
    public String getIdentificativoDominio () {
        return identificativoDominio;
    }

    
    /**
     * @param identificativoDominio the identificativoDominio to set
     */
    public void setIdentificativoDominio ( String identificativoDominio ) {
        this.identificativoDominio = identificativoDominio;
    }

    @Override
    public String toString () {
        return "Rt [idRt=" + idRt + ", codEsitoPagamento=" + codEsitoPagamento + ", dataoraMsgRicevuta=" + dataoraMsgRicevuta + ", descEsitoPagamento="
            + descEsitoPagamento + ", idApplicazione=" + idApplicazione + ", idMsgRicevuta=" + idMsgRicevuta + ", idMsgRichiesta=" + idMsgRichiesta
            + ", idTransazione=" + idTransazione + ", iuv=" + iuv + ", ricevutaPdf=" + Arrays.toString ( ricevutaPdf ) + ", rtXml=" + Arrays.toString ( rtXml )
            + ", tipoFirma=" + tipoFirma + ", idRegistro=" + idRegistro 
            + ", codiceContestoPagamento=" + codiceContestoPagamento + ", identificativoDominio=" + identificativoDominio 
            + "]";
    }

}
