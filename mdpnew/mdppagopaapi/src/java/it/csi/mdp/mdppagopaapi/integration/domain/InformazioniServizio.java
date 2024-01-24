/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the informazioni_servizio database table.
 * 
 */
@Entity
@Table(name="informazioni_servizio")
@NamedQuery(name="InformazioniServizio.findAll", query="SELECT i FROM InformazioniServizio i")
public class InformazioniServizio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="codice_lingua")
	private String codiceLingua;

	@Column(name="data_validita")
	private Timestamp dataValidita;

	@Column(name="descrizione_servizio")
	private String descrizioneServizio;

	@Column(name="disponibilita_servizio")
	private String disponibilitaServizio;

	@Column(name="limitazioni_servizio")
	private String limitazioniServizio;

	@Id
	@Column(name="url_informazioni_canale")
	private String urlInformazioniCanale;

	private Boolean valido;

	//bi-directional many-to-one association to InformativaPsp
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_informativa_psp")
	private InformativaPsp informativaPsp;

	public InformazioniServizio() {
	}

	public String getCodiceLingua() {
		return this.codiceLingua;
	}

	public void setCodiceLingua(String codiceLingua) {
		this.codiceLingua = codiceLingua;
	}

	public Timestamp getDataValidita() {
		return this.dataValidita;
	}

	public void setDataValidita(Timestamp dataValidita) {
		this.dataValidita = dataValidita;
	}

	public String getDescrizioneServizio() {
		return this.descrizioneServizio;
	}

	public void setDescrizioneServizio(String descrizioneServizio) {
		this.descrizioneServizio = descrizioneServizio;
	}

	public String getDisponibilitaServizio() {
		return this.disponibilitaServizio;
	}

	public void setDisponibilitaServizio(String disponibilitaServizio) {
		this.disponibilitaServizio = disponibilitaServizio;
	}

	public String getLimitazioniServizio() {
		return this.limitazioniServizio;
	}

	public void setLimitazioniServizio(String limitazioniServizio) {
		this.limitazioniServizio = limitazioniServizio;
	}

	public String getUrlInformazioniCanale() {
		return this.urlInformazioniCanale;
	}

	public void setUrlInformazioniCanale(String urlInformazioniCanale) {
		this.urlInformazioniCanale = urlInformazioniCanale;
	}

	public Boolean getValido() {
		return this.valido;
	}

	public void setValido(Boolean valido) {
		this.valido = valido;
	}

	public InformativaPsp getInformativaPsp() {
		return this.informativaPsp;
	}

	public void setInformativaPsp(InformativaPsp informativaPsp) {
		this.informativaPsp = informativaPsp;
	}

}
