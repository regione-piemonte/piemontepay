/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import it.csi.mdp.core.business.dto.multicarrello.DatiSingoloVersamentoRPT;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ElementoMultiversamento implements Serializable {

	private static final long serialVersionUID = 5077791026473392307L;
	
	private int id;
	private String applicationId;
	private String transactionId;
	private int posizione;
	private String iuv;
	private String xmlElemento;
	private List<DatiSingoloVersamentoRPT> elencoSingoliPagamentiSpacchettati = new ArrayList<DatiSingoloVersamentoRPT>();
	private String modelloPagamento;
	
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
	public int getPosizione() {
		return posizione;
	}
	public void setPosizione(int posizione) {
		this.posizione = posizione;
	}
	public String getIuv() {
		return iuv;
	}
	public void setIuv(String iuv) {
		this.iuv = iuv;
	}
	public String getXmlElemento() {
		return xmlElemento;
	}
	public void setXmlElemento(String xmlElemento) {
		this.xmlElemento = xmlElemento;
	}
	public List<DatiSingoloVersamentoRPT> getElencoSingoliPagamentiSpacchettati() {
		return elencoSingoliPagamentiSpacchettati;
	}
	public void setElencoSingoliPagamentiSpacchettati(
			List<DatiSingoloVersamentoRPT> elencoSingoliPagamentiSpacchettati) {
		this.elencoSingoliPagamentiSpacchettati = elencoSingoliPagamentiSpacchettati;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getModelloPagamento() {
		return modelloPagamento;
	}
	public void setModelloPagamento(String modelloPagamento) {
		this.modelloPagamento = modelloPagamento;
	}
	
}
