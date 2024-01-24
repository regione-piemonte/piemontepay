/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.dto;

import java.sql.Timestamp;

@SuppressWarnings("serial")
public class CodaInvioReceipt extends BaseDTO {
	
    

    private Integer contatoreTentativi;
    private String transactionId;
    private String applicationId;
    private Timestamp dataInizioTentativi;
    private Integer numGiorniTentativiKo;
    private String ultimoEsitoFruitore;
	private String iuv;
	private Timestamp dataUltimaModifica;
	private Timestamp dataInserimento;
	private Timestamp dataTentativi;
//	private Date dataInizioTentativi;

	
	public String getIuv() {
		return iuv;
	}
	public void setIuv(String iuv) {
		this.iuv = iuv;
	}
	
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public Integer getContatoreTentativi() {
		return contatoreTentativi;
	}
	public void setContatoreTentativi(Integer contatoreTentativi) {
		this.contatoreTentativi = contatoreTentativi;
	}
	public Integer getNumGiorniTentativiKo() {
		return numGiorniTentativiKo;
	}
	public void setNumGiorniTentativiKo(Integer numGiorniTentativiKo) {
		this.numGiorniTentativiKo = numGiorniTentativiKo;
	}
	public String getUltimoEsitoFruitore() {
		return ultimoEsitoFruitore;
	}
	public void setUltimoEsitoFruitore(String ultimoEsitoFruitore) {
		this.ultimoEsitoFruitore = ultimoEsitoFruitore;
	}
	
	public Timestamp getDataTentativi() {
		return dataTentativi;
	}
	public void setDataTentativi(Timestamp dataTentativi) {
		this.dataTentativi = dataTentativi;
	}
	public Timestamp getDataInizioTentativi() {
		return dataInizioTentativi;
	}
	public void setDataInizioTentativi(Timestamp dataInizioTentativi) {
		this.dataInizioTentativi = dataInizioTentativi;
	}
	public Timestamp getDataUltimaModifica() {
		return dataUltimaModifica;
	}
	public void setDataUltimaModifica(Timestamp dataUltimaModifica) {
		this.dataUltimaModifica = dataUltimaModifica;
	}
	public Timestamp getDataInserimento() {
		return dataInserimento;
	}
	public void setDataInserimento(Timestamp dataInserimento) {
		this.dataInserimento = dataInserimento;
	}
	
	
	
	

}
