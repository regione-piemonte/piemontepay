/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import java.io.Serializable;
import java.math.BigDecimal;


public class DatiSingoloVersamentoDto implements Serializable{

	private static final long serialVersionUID = 4270880709228418234L;
	
	private int id;
    private BigDecimal importoSingoloVersamento;
    private BigDecimal commissioneCaricoPA;
    private String credenzialiPagatore;
    private String causaleVersamento;
    private String datiSpecificiRiscossione;
    private String tipoBollo;
    private String hashDocumento;
    private String provinciaResidenza;
    private int idMultiversamento; // FK
    
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BigDecimal getImportoSingoloVersamento() {
		return importoSingoloVersamento;
	}
	public void setImportoSingoloVersamento(BigDecimal importoSingoloVersamento) {
		this.importoSingoloVersamento = importoSingoloVersamento;
	}
	public BigDecimal getCommissioneCaricoPA() {
		return commissioneCaricoPA;
	}
	public void setCommissioneCaricoPA(BigDecimal commissioneCaricoPA) {
		this.commissioneCaricoPA = commissioneCaricoPA;
	}
	public String getCredenzialiPagatore() {
		return credenzialiPagatore;
	}
	public void setCredenzialiPagatore(String credenzialiPagatore) {
		this.credenzialiPagatore = credenzialiPagatore;
	}
	public String getCausaleVersamento() {
		return causaleVersamento;
	}
	public void setCausaleVersamento(String causaleVersamento) {
		this.causaleVersamento = causaleVersamento;
	}
	public String getDatiSpecificiRiscossione() {
		return datiSpecificiRiscossione;
	}
	public void setDatiSpecificiRiscossione(String datiSpecificiRiscossione) {
		this.datiSpecificiRiscossione = datiSpecificiRiscossione;
	}
	public String getTipoBollo() {
		return tipoBollo;
	}
	public void setTipoBollo(String tipoBollo) {
		this.tipoBollo = tipoBollo;
	}
	public String getHashDocumento() {
		return hashDocumento;
	}
	public void setHashDocumento(String hashDocumento) {
		this.hashDocumento = hashDocumento;
	}
	public String getProvinciaResidenza() {
		return provinciaResidenza;
	}
	public void setProvinciaResidenza(String provinciaResidenza) {
		this.provinciaResidenza = provinciaResidenza;
	}
	public int getIdMultiversamento() {
		return idMultiversamento;
	}
	public void setIdMultiversamento(int idMultiversamento) {
		this.idMultiversamento = idMultiversamento;
	}
    
    
   
}
