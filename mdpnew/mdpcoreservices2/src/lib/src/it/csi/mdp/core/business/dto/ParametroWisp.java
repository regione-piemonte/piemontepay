/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import java.io.Serializable;

public class ParametroWisp implements Serializable {

	private static final long serialVersionUID = 5611278554319092012L;
	
	private String keyPA;
	private String transactionId;
	private String applicationId;
	private String idDominio;
	private String enteCreditore;
	private String urlReturn;
	private String urlBack;
	private String urlGestione;
	private String primitiva;
	private int numPagamentiRPT;
	private String stornoPagamento;
	private String bolloDigitale;
	private String terzoModelloPagamento;
	private String idPsp;
	private String tipoVersamento;
	private double importoTransazione;
	
	private String keyWISP;
	private String type; //Type del wisp
	private String effettuazioneScelta;
	private String identificativoPSPScelto;
	private String identificativoIntermediarioPSPScelto;
	private String identificativoCanale;
	private String tipoVersamentoScelto;
	private String esito;//OK o KO
	private String stringaErrore; //solo in caso KO faultString se servizi nodo oppure messaggio eccezione in caso di errore interno
	private String versioneInterfacciaWisp;
	private String contoPoste;
	private String pagamentiModello2;
	private String ibanAccredito;
	private String codiceLingua;
	
	
	
	
	public String getKeyPA() {
		return keyPA;
	}
	public void setKeyPA(String keyPA) {
		this.keyPA = keyPA;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	public String getIdDominio() {
		return idDominio;
	}
	public void setIdDominio(String idDominio) {
		this.idDominio = idDominio;
	}
	public String getEnteCreditore() {
		return enteCreditore;
	}
	public void setEnteCreditore(String enteCreditore) {
		this.enteCreditore = enteCreditore;
	}
	public String getUrlReturn() {
		return urlReturn;
	}
	public void setUrlReturn(String urlReturn) {
		this.urlReturn = urlReturn;
	}
	public String getUrlBack() {
		return urlBack;
	}
	public void setUrlBack(String urlBack) {
		this.urlBack = urlBack;
	}
	public String getPrimitiva() {
		return primitiva;
	}
	public void setPrimitiva(String primitiva) {
		this.primitiva = primitiva;
	}
	public int getNumPagamentiRPT() {
		return numPagamentiRPT;
	}
	public void setNumPagamentiRPT(int numPagamentiRPT) {
		this.numPagamentiRPT = numPagamentiRPT;
	}
	public String getStornoPagamento() {
		return stornoPagamento;
	}
	public void setStornoPagamento(String stornoPagamento) {
		this.stornoPagamento = stornoPagamento;
	}
	public String getBolloDigitale() {
		return bolloDigitale;
	}
	public void setBolloDigitale(String bolloDigitale) {
		this.bolloDigitale = bolloDigitale;
	}
	public String getTerzoModelloPagamento() {
		return terzoModelloPagamento;
	}
	public void setTerzoModelloPagamento(String terzoModelloPagamento) {
		this.terzoModelloPagamento = terzoModelloPagamento;
	}
	public String getIdPsp() {
		return idPsp;
	}
	public void setIdPsp(String idPsp) {
		this.idPsp = idPsp;
	}
	public String getTipoVersamento() {
		return tipoVersamento;
	}
	public void setTipoVersamento(String tipoVersamento) {
		this.tipoVersamento = tipoVersamento;
	}
	public double getImportoTransazione() {
		return importoTransazione;
	}
	public void setImportoTransazione(double importoTransazione) {
		this.importoTransazione = importoTransazione;
	}
	public String getKeyWISP() {
		return keyWISP;
	}
	public void setKeyWISP(String keyWISP) {
		this.keyWISP = keyWISP;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getEffettuazioneScelta() {
		return effettuazioneScelta;
	}
	public void setEffettuazioneScelta(String effettuazioneScelta) {
		this.effettuazioneScelta = effettuazioneScelta;
	}
	public String getIdentificativoPSPScelto() {
		return identificativoPSPScelto;
	}
	public void setIdentificativoPSPScelto(String identificativoPSPScelto) {
		this.identificativoPSPScelto = identificativoPSPScelto;
	}
	public String getIdnetificativoIntermediarioPSPScelto() {
		return identificativoIntermediarioPSPScelto;
	}
	public void setIdnetificativoIntermediarioPSPScelto(String idnetificativoIntermediarioPSPScelto) {
		this.identificativoIntermediarioPSPScelto = idnetificativoIntermediarioPSPScelto;
	}
	public String getIdentificativoCanale() {
		return identificativoCanale;
	}
	public void setIdentificativoCanale(String identificativoCanale) {
		this.identificativoCanale = identificativoCanale;
	}
	public String getTipoVersamentoScelto() {
		return tipoVersamentoScelto;
	}
	public void setTipoVersamentoScelto(String tipoVersamentoScelto) {
		this.tipoVersamentoScelto = tipoVersamentoScelto;
	}
	public String getEsito() {
		return esito;
	}
	public void setEsito(String esito) {
		this.esito = esito;
	}
	public String getStringaErrore() {
		return stringaErrore;
	}
	public void setStringaErrore(String stringaErrore) {
		this.stringaErrore = stringaErrore;
	}
	public String getIdentificativoIntermediarioPSPScelto() {
		return identificativoIntermediarioPSPScelto;
	}
	public void setIdentificativoIntermediarioPSPScelto(String identificativoIntermediarioPSPScelto) {
		this.identificativoIntermediarioPSPScelto = identificativoIntermediarioPSPScelto;
	}
	public String getVersioneInterfacciaWisp() {
		return versioneInterfacciaWisp;
	}
	public void setVersioneInterfacciaWisp(String versioneInterfacciaWisp) {
		this.versioneInterfacciaWisp = versioneInterfacciaWisp;
	}
	public String getContoPoste() {
		return contoPoste;
	}
	public void setContoPoste(String contoPoste) {
		this.contoPoste = contoPoste;
	}
	public String getPagamentiModello2() {
		return pagamentiModello2;
	}
	public void setPagamentiModello2(String pagamentiModello2) {
		this.pagamentiModello2 = pagamentiModello2;
	}
	public String getIbanAccredito() {
		return ibanAccredito;
	}
	public void setIbanAccredito(String ibanAccredito) {
		this.ibanAccredito = ibanAccredito;
	}
	public String getUrlGestione() {
		return urlGestione;
	}
	public void setUrlGestione(String urlGestione) {
		this.urlGestione = urlGestione;
	}
	public String getCodiceLingua() {
		return codiceLingua;
	}
	public void setCodiceLingua(String codiceLingua) {
		this.codiceLingua = codiceLingua;
	}
	
	
}
