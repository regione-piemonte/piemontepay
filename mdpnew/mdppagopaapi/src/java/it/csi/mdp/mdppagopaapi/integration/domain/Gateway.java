/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the gateway database table.
 * 
 */
@Entity
@NamedQuery(name="Gateway.findAll", query="SELECT g FROM Gateway g")
public class Gateway implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="GATEWAY_GATEWAYID_GENERATOR", sequenceName="GATEWAY_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GATEWAY_GATEWAYID_GENERATOR")
	@Column(name="gateway_id")
	private String gatewayId;

	@Column(name="flag_nodo")
	private Boolean flagNodo;

	@Column(name="gateway_description")
	private String gatewayDescription;

	@Column(name="gateway_provider")
	private String gatewayProvider;

	private String gatewayservicename;

	@Column(name="valido_al")
	private Timestamp validoAl;

	@Column(name="valido_dal")
	private Timestamp validoDal;

	//bi-directional many-to-one association to Applicationcustomfield
	@OneToMany(mappedBy="gateway")
	private List<Applicationcustomfield> applicationcustomfields;

	//bi-directional many-to-one association to Applicationdetail
	@OneToMany(mappedBy="gateway")
	private List<Applicationdetail> applicationdetails;

	//bi-directional many-to-one association to Gatewaycustomfield
	@OneToMany(mappedBy="gateway")
	private List<Gatewaycustomfield> gatewaycustomfields;

	//bi-directional many-to-one association to Gatewaydetail
	@OneToMany(mappedBy="gateway")
	private List<Gatewaydetail> gatewaydetails;

	//bi-directional many-to-one association to Language
	@OneToMany(mappedBy="gateway")
	private List<Language> languages;

	//bi-directional many-to-one association to MdpCurrency
	@OneToMany(mappedBy="gateway")
	private List<MdpCurrency> mdpCurrencies;

	//bi-directional many-to-one association to Transazione
	@OneToMany(mappedBy="gateway")
	private List<Transazione> transaziones;

	public Gateway() {
	}

	public String getGatewayId() {
		return this.gatewayId;
	}

	public void setGatewayId(String gatewayId) {
		this.gatewayId = gatewayId;
	}

	public Boolean getFlagNodo() {
		return this.flagNodo;
	}

	public void setFlagNodo(Boolean flagNodo) {
		this.flagNodo = flagNodo;
	}

	public String getGatewayDescription() {
		return this.gatewayDescription;
	}

	public void setGatewayDescription(String gatewayDescription) {
		this.gatewayDescription = gatewayDescription;
	}

	public String getGatewayProvider() {
		return this.gatewayProvider;
	}

	public void setGatewayProvider(String gatewayProvider) {
		this.gatewayProvider = gatewayProvider;
	}

	public String getGatewayservicename() {
		return this.gatewayservicename;
	}

	public void setGatewayservicename(String gatewayservicename) {
		this.gatewayservicename = gatewayservicename;
	}

	public Timestamp getValidoAl() {
		return this.validoAl;
	}

	public void setValidoAl(Timestamp validoAl) {
		this.validoAl = validoAl;
	}

	public Timestamp getValidoDal() {
		return this.validoDal;
	}

	public void setValidoDal(Timestamp validoDal) {
		this.validoDal = validoDal;
	}

	public List<Applicationcustomfield> getApplicationcustomfields() {
		return this.applicationcustomfields;
	}

	public void setApplicationcustomfields(List<Applicationcustomfield> applicationcustomfields) {
		this.applicationcustomfields = applicationcustomfields;
	}

	public Applicationcustomfield addApplicationcustomfield(Applicationcustomfield applicationcustomfield) {
		getApplicationcustomfields().add(applicationcustomfield);
		applicationcustomfield.setGateway(this);

		return applicationcustomfield;
	}

	public Applicationcustomfield removeApplicationcustomfield(Applicationcustomfield applicationcustomfield) {
		getApplicationcustomfields().remove(applicationcustomfield);
		applicationcustomfield.setGateway(null);

		return applicationcustomfield;
	}

	public List<Applicationdetail> getApplicationdetails() {
		return this.applicationdetails;
	}

	public void setApplicationdetails(List<Applicationdetail> applicationdetails) {
		this.applicationdetails = applicationdetails;
	}

	public Applicationdetail addApplicationdetail(Applicationdetail applicationdetail) {
		getApplicationdetails().add(applicationdetail);
		applicationdetail.setGateway(this);

		return applicationdetail;
	}

	public Applicationdetail removeApplicationdetail(Applicationdetail applicationdetail) {
		getApplicationdetails().remove(applicationdetail);
		applicationdetail.setGateway(null);

		return applicationdetail;
	}

	public List<Gatewaycustomfield> getGatewaycustomfields() {
		return this.gatewaycustomfields;
	}

	public void setGatewaycustomfields(List<Gatewaycustomfield> gatewaycustomfields) {
		this.gatewaycustomfields = gatewaycustomfields;
	}

	public Gatewaycustomfield addGatewaycustomfield(Gatewaycustomfield gatewaycustomfield) {
		getGatewaycustomfields().add(gatewaycustomfield);
		gatewaycustomfield.setGateway(this);

		return gatewaycustomfield;
	}

	public Gatewaycustomfield removeGatewaycustomfield(Gatewaycustomfield gatewaycustomfield) {
		getGatewaycustomfields().remove(gatewaycustomfield);
		gatewaycustomfield.setGateway(null);

		return gatewaycustomfield;
	}

	public List<Gatewaydetail> getGatewaydetails() {
		return this.gatewaydetails;
	}

	public void setGatewaydetails(List<Gatewaydetail> gatewaydetails) {
		this.gatewaydetails = gatewaydetails;
	}

	public Gatewaydetail addGatewaydetail(Gatewaydetail gatewaydetail) {
		getGatewaydetails().add(gatewaydetail);
		gatewaydetail.setGateway(this);

		return gatewaydetail;
	}

	public Gatewaydetail removeGatewaydetail(Gatewaydetail gatewaydetail) {
		getGatewaydetails().remove(gatewaydetail);
		gatewaydetail.setGateway(null);

		return gatewaydetail;
	}

	public List<Language> getLanguages() {
		return this.languages;
	}

	public void setLanguages(List<Language> languages) {
		this.languages = languages;
	}

	public Language addLanguage(Language language) {
		getLanguages().add(language);
		language.setGateway(this);

		return language;
	}

	public Language removeLanguage(Language language) {
		getLanguages().remove(language);
		language.setGateway(null);

		return language;
	}

	public List<MdpCurrency> getMdpCurrencies() {
		return this.mdpCurrencies;
	}

	public void setMdpCurrencies(List<MdpCurrency> mdpCurrencies) {
		this.mdpCurrencies = mdpCurrencies;
	}

	public MdpCurrency addMdpCurrency(MdpCurrency mdpCurrency) {
		getMdpCurrencies().add(mdpCurrency);
		mdpCurrency.setGateway(this);

		return mdpCurrency;
	}

	public MdpCurrency removeMdpCurrency(MdpCurrency mdpCurrency) {
		getMdpCurrencies().remove(mdpCurrency);
		mdpCurrency.setGateway(null);

		return mdpCurrency;
	}

	public List<Transazione> getTransaziones() {
		return this.transaziones;
	}

	public void setTransaziones(List<Transazione> transaziones) {
		this.transaziones = transaziones;
	}

	public Transazione addTransazione(Transazione transazione) {
		getTransaziones().add(transazione);
		transazione.setGateway(this);

		return transazione;
	}

	public Transazione removeTransazione(Transazione transazione) {
		getTransaziones().remove(transazione);
		transazione.setGateway(null);

		return transazione;
	}

}
