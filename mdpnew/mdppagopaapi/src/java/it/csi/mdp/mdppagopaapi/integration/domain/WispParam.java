/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the wisp_params database table.
 * 
 */
@Entity
@Table(name="wisp_params")
@NamedQuery(name="WispParam.findAll", query="SELECT w FROM WispParam w")
public class WispParam implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="WISP_PARAMS_KEYPA_GENERATOR", sequenceName="WISP_PARAMS_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="WISP_PARAMS_KEYPA_GENERATOR")
	private String keypa;

	@Column(name="application_id")
	private String applicationId;

	@Column(name="bollo_digitale")
	private String bolloDigitale;

	private String codicelingua;

	private String contoposte;

	private String effettuazionescelta;

	@Column(name="ente_creditore")
	private String enteCreditore;

	private String esito;

	private String ibanaccredito;

	@Column(name="id_psp")
	private String idPsp;

	private String identificativocanale;

	private String identificativocanalescelto;

	private String identificativodominio;

	private String identificativointermediariopspscelto;

	private String identificativopspscelto;

	@Column(name="importo_transazione")
	private BigDecimal importoTransazione;

	@Column(name="insert_date")
	private Timestamp insertDate;

	private String keywisp;

	@Column(name="last_update")
	private Timestamp lastUpdate;

	@Column(name="num_pagamenti_rpt")
	private Integer numPagamentiRpt;

	private String pagamentimodello2;

	private String primitiva;

	@Column(name="storno_pagamento")
	private String stornoPagamento;

	private String stringaerrore;

	@Column(name="terzo_modello_pagamento")
	private String terzoModelloPagamento;

	@Column(name="tipo_versamento")
	private String tipoVersamento;

	private String tipoversamentoscelto;

	@Column(name="transaction_id")
	private String transactionId;

	private String type;

	@Column(name="url_back")
	private String urlBack;

	@Column(name="url_return")
	private String urlReturn;

	private String urlgestione;

	private String versioneinterfacciawisp;

	public WispParam() {
	}

	public String getKeypa() {
		return this.keypa;
	}

	public void setKeypa(String keypa) {
		this.keypa = keypa;
	}

	public String getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getBolloDigitale() {
		return this.bolloDigitale;
	}

	public void setBolloDigitale(String bolloDigitale) {
		this.bolloDigitale = bolloDigitale;
	}

	public String getCodicelingua() {
		return this.codicelingua;
	}

	public void setCodicelingua(String codicelingua) {
		this.codicelingua = codicelingua;
	}

	public String getContoposte() {
		return this.contoposte;
	}

	public void setContoposte(String contoposte) {
		this.contoposte = contoposte;
	}

	public String getEffettuazionescelta() {
		return this.effettuazionescelta;
	}

	public void setEffettuazionescelta(String effettuazionescelta) {
		this.effettuazionescelta = effettuazionescelta;
	}

	public String getEnteCreditore() {
		return this.enteCreditore;
	}

	public void setEnteCreditore(String enteCreditore) {
		this.enteCreditore = enteCreditore;
	}

	public String getEsito() {
		return this.esito;
	}

	public void setEsito(String esito) {
		this.esito = esito;
	}

	public String getIbanaccredito() {
		return this.ibanaccredito;
	}

	public void setIbanaccredito(String ibanaccredito) {
		this.ibanaccredito = ibanaccredito;
	}

	public String getIdPsp() {
		return this.idPsp;
	}

	public void setIdPsp(String idPsp) {
		this.idPsp = idPsp;
	}

	public String getIdentificativocanale() {
		return this.identificativocanale;
	}

	public void setIdentificativocanale(String identificativocanale) {
		this.identificativocanale = identificativocanale;
	}

	public String getIdentificativocanalescelto() {
		return this.identificativocanalescelto;
	}

	public void setIdentificativocanalescelto(String identificativocanalescelto) {
		this.identificativocanalescelto = identificativocanalescelto;
	}

	public String getIdentificativodominio() {
		return this.identificativodominio;
	}

	public void setIdentificativodominio(String identificativodominio) {
		this.identificativodominio = identificativodominio;
	}

	public String getIdentificativointermediariopspscelto() {
		return this.identificativointermediariopspscelto;
	}

	public void setIdentificativointermediariopspscelto(String identificativointermediariopspscelto) {
		this.identificativointermediariopspscelto = identificativointermediariopspscelto;
	}

	public String getIdentificativopspscelto() {
		return this.identificativopspscelto;
	}

	public void setIdentificativopspscelto(String identificativopspscelto) {
		this.identificativopspscelto = identificativopspscelto;
	}

	public BigDecimal getImportoTransazione() {
		return this.importoTransazione;
	}

	public void setImportoTransazione(BigDecimal importoTransazione) {
		this.importoTransazione = importoTransazione;
	}

	public Timestamp getInsertDate() {
		return this.insertDate;
	}

	public void setInsertDate(Timestamp insertDate) {
		this.insertDate = insertDate;
	}

	public String getKeywisp() {
		return this.keywisp;
	}

	public void setKeywisp(String keywisp) {
		this.keywisp = keywisp;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Integer getNumPagamentiRpt() {
		return this.numPagamentiRpt;
	}

	public void setNumPagamentiRpt(Integer numPagamentiRpt) {
		this.numPagamentiRpt = numPagamentiRpt;
	}

	public String getPagamentimodello2() {
		return this.pagamentimodello2;
	}

	public void setPagamentimodello2(String pagamentimodello2) {
		this.pagamentimodello2 = pagamentimodello2;
	}

	public String getPrimitiva() {
		return this.primitiva;
	}

	public void setPrimitiva(String primitiva) {
		this.primitiva = primitiva;
	}

	public String getStornoPagamento() {
		return this.stornoPagamento;
	}

	public void setStornoPagamento(String stornoPagamento) {
		this.stornoPagamento = stornoPagamento;
	}

	public String getStringaerrore() {
		return this.stringaerrore;
	}

	public void setStringaerrore(String stringaerrore) {
		this.stringaerrore = stringaerrore;
	}

	public String getTerzoModelloPagamento() {
		return this.terzoModelloPagamento;
	}

	public void setTerzoModelloPagamento(String terzoModelloPagamento) {
		this.terzoModelloPagamento = terzoModelloPagamento;
	}

	public String getTipoVersamento() {
		return this.tipoVersamento;
	}

	public void setTipoVersamento(String tipoVersamento) {
		this.tipoVersamento = tipoVersamento;
	}

	public String getTipoversamentoscelto() {
		return this.tipoversamentoscelto;
	}

	public void setTipoversamentoscelto(String tipoversamentoscelto) {
		this.tipoversamentoscelto = tipoversamentoscelto;
	}

	public String getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrlBack() {
		return this.urlBack;
	}

	public void setUrlBack(String urlBack) {
		this.urlBack = urlBack;
	}

	public String getUrlReturn() {
		return this.urlReturn;
	}

	public void setUrlReturn(String urlReturn) {
		this.urlReturn = urlReturn;
	}

	public String getUrlgestione() {
		return this.urlgestione;
	}

	public void setUrlgestione(String urlgestione) {
		this.urlgestione = urlgestione;
	}

	public String getVersioneinterfacciawisp() {
		return this.versioneinterfacciawisp;
	}

	public void setVersioneinterfacciawisp(String versioneinterfacciawisp) {
		this.versioneinterfacciawisp = versioneinterfacciawisp;
	}

}
