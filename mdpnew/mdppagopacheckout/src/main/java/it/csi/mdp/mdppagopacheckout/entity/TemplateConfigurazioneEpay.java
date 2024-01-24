/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * The persistent class for the template_configurazione_epay database table.
 */
@Entity
@Table ( name = "template_configurazione_epay" )
@NamedQuery ( name = "TemplateConfigurazioneEpay.findAll", query = "SELECT t FROM TemplateConfigurazioneEpay t" )
@SuppressWarnings ( "unused" )
public class TemplateConfigurazioneEpay implements Serializable {

	private static final long serialVersionUID = -4787569385265070490L;

	@Id
	@SequenceGenerator ( name = "TEMPLATE_CONFIGURAZIONE_EPAY_ID_GENERATOR", sequenceName = "TEMPLATE_CONFIGURAZIONE_EPAY_SEQ", allocationSize = 1 )
	@GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "TEMPLATE_CONFIGURAZIONE_EPAY_ID_GENERATOR" )
	private Integer id;

	@Column ( name = "campo_cifrato" )
	private Boolean campoCifrato;

	private String chiave;

	private String descrizione;

	@Column ( name = "flag_attivo" )
	private Boolean flagAttivo;

	@Column ( name = "gateway_id" )
	private String gatewayId;

	private String progetto;

	@Column ( name = "tabella_riferimento" )
	private String tabellaRiferimento;

	private String valore;

	public TemplateConfigurazioneEpay () {
	}

	public Integer getId () {
		return this.id;
	}

	public void setId ( Integer id ) {
		this.id = id;
	}

	public Boolean getCampoCifrato () {
		return this.campoCifrato;
	}

	public void setCampoCifrato ( Boolean campoCifrato ) {
		this.campoCifrato = campoCifrato;
	}

	public String getChiave () {
		return this.chiave;
	}

	public void setChiave ( String chiave ) {
		this.chiave = chiave;
	}

	public String getDescrizione () {
		return this.descrizione;
	}

	public void setDescrizione ( String descrizione ) {
		this.descrizione = descrizione;
	}

	public Boolean getFlagAttivo () {
		return this.flagAttivo;
	}

	public void setFlagAttivo ( Boolean flagAttivo ) {
		this.flagAttivo = flagAttivo;
	}

	public String getGatewayId () {
		return this.gatewayId;
	}

	public void setGatewayId ( String gatewayId ) {
		this.gatewayId = gatewayId;
	}

	public String getProgetto () {
		return this.progetto;
	}

	public void setProgetto ( String progetto ) {
		this.progetto = progetto;
	}

	public String getTabellaRiferimento () {
		return this.tabellaRiferimento;
	}

	public void setTabellaRiferimento ( String tabellaRiferimento ) {
		this.tabellaRiferimento = tabellaRiferimento;
	}

	public String getValore () {
		return this.valore;
	}

	public void setValore ( String valore ) {
		this.valore = valore;
	}

}
