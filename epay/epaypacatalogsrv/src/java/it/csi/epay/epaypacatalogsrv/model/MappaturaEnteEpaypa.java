/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the epaycat_d_mappatura_ente_epaypa database table.
 * 
 */
@Entity
@Table(name="epaycat_d_mappatura_ente_epaypa")
@NamedQuery(name="MappaturaEnteEpaypa.findAll", query="SELECT m FROM MappaturaEnteEpaypa m")
public class MappaturaEnteEpaypa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String bic;

	private String cap;

	private String civico;

	@Column(name="codice_fiscale")
	private String codiceFiscale;

	@Column(name="codice_modalita_acquisizione_provvisori")
	private String codiceModalitaAcquisizioneProvvisori;

	@Column(name="codice_periodicita_schedulazione_riconciliazione")
	private String codicePeriodicitaSchedulazioneRiconciliazione;

	@Column(name="codice_tipologia_accertamento")
	private String codiceTipologiaAccertamento;

	@Column(name="flag_accertamento")
	private Boolean flagAccertamento;

	@Column(name="flag_ente_plurintermediato")
	private Boolean flagEntePlurintermediato;

	@Column(name="flag_qualsiasi_codice_versamento")
	private Boolean flagQualsiasiCodiceVersamento;

	@Column(name="flag_ricezione_errori")
	private Boolean flagRicezioneErrori;

	@Column(name="flag_ricezione_flusso_base_rendicontazione")
	private Boolean flagRicezioneFlussoBaseRendicontazione;

	@Column(name="flag_riconciliazione_versamenti")
	private Boolean flagRiconciliazioneVersamenti;

	@Column(name="giorno_schedulazione")
	private Integer giornoSchedulazione;

	private String iban;

	@Column(name="id_utente_amministratore")
	private Integer idUtenteAmministratore;

	private String indirizzo;

	private String localita;

	@Column(name="sigla_provincia")
	private String siglaProvincia;

	public MappaturaEnteEpaypa() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBic() {
		return bic;
	}

	public void setBic(String bic) {
		this.bic = bic;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getCivico() {
		return civico;
	}

	public void setCivico(String civico) {
		this.civico = civico;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getCodiceModalitaAcquisizioneProvvisori() {
		return codiceModalitaAcquisizioneProvvisori;
	}

	public void setCodiceModalitaAcquisizioneProvvisori(String codiceModalitaAcquisizioneProvvisori) {
		this.codiceModalitaAcquisizioneProvvisori = codiceModalitaAcquisizioneProvvisori;
	}

	public String getCodicePeriodicitaSchedulazioneRiconciliazione() {
		return codicePeriodicitaSchedulazioneRiconciliazione;
	}

	public void setCodicePeriodicitaSchedulazioneRiconciliazione(String codicePeriodicitaSchedulazioneRiconciliazione) {
		this.codicePeriodicitaSchedulazioneRiconciliazione = codicePeriodicitaSchedulazioneRiconciliazione;
	}

	public String getCodiceTipologiaAccertamento() {
		return codiceTipologiaAccertamento;
	}

	public void setCodiceTipologiaAccertamento(String codiceTipologiaAccertamento) {
		this.codiceTipologiaAccertamento = codiceTipologiaAccertamento;
	}

	public Boolean getFlagAccertamento() {
		return flagAccertamento;
	}

	public void setFlagAccertamento(Boolean flagAccertamento) {
		this.flagAccertamento = flagAccertamento;
	}

	public Boolean getFlagEntePlurintermediato() {
		return flagEntePlurintermediato;
	}

	public void setFlagEntePlurintermediato(Boolean flagEntePlurintermediato) {
		this.flagEntePlurintermediato = flagEntePlurintermediato;
	}

	public Boolean getFlagQualsiasiCodiceVersamento() {
		return flagQualsiasiCodiceVersamento;
	}

	public void setFlagQualsiasiCodiceVersamento(Boolean flagQualsiasiCodiceVersamento) {
		this.flagQualsiasiCodiceVersamento = flagQualsiasiCodiceVersamento;
	}

	public Boolean getFlagRicezioneErrori() {
		return flagRicezioneErrori;
	}

	public void setFlagRicezioneErrori(Boolean flagRicezioneErrori) {
		this.flagRicezioneErrori = flagRicezioneErrori;
	}

	public Boolean getFlagRicezioneFlussoBaseRendicontazione() {
		return flagRicezioneFlussoBaseRendicontazione;
	}

	public void setFlagRicezioneFlussoBaseRendicontazione(Boolean flagRicezioneFlussoBaseRendicontazione) {
		this.flagRicezioneFlussoBaseRendicontazione = flagRicezioneFlussoBaseRendicontazione;
	}

	public Boolean getFlagRiconciliazioneVersamenti() {
		return flagRiconciliazioneVersamenti;
	}

	public void setFlagRiconciliazioneVersamenti(Boolean flagRiconciliazioneVersamenti) {
		this.flagRiconciliazioneVersamenti = flagRiconciliazioneVersamenti;
	}

	public Integer getGiornoSchedulazione() {
		return giornoSchedulazione;
	}

	public void setGiornoSchedulazione(Integer giornoSchedulazione) {
		this.giornoSchedulazione = giornoSchedulazione;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public Integer getIdUtenteAmministratore() {
		return idUtenteAmministratore;
	}

	public void setIdUtenteAmministratore(Integer idUtenteAmministratore) {
		this.idUtenteAmministratore = idUtenteAmministratore;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getLocalita() {
		return localita;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}

	public String getSiglaProvincia() {
		return siglaProvincia;
	}

	public void setSiglaProvincia(String siglaProvincia) {
		this.siglaProvincia = siglaProvincia;
	}

	@Override
	public String toString() {
		return "MappaturaEnteEpaypa [id=" + id + ", bic=" + bic + ", cap=" + cap + ", civico=" + civico
				+ ", codiceFiscale=" + codiceFiscale + "]";
	}

}
