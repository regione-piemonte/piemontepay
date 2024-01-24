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
 * The persistent class for the paymentmode database table.
 * 
 */
@Entity
@NamedQuery(name="Paymentmode.findAll", query="SELECT p FROM Paymentmode p")
public class Paymentmode implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PAYMENTMODE_PAYMENTMODEID_GENERATOR", sequenceName="PAYMENTMODE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PAYMENTMODE_PAYMENTMODEID_GENERATOR")
	@Column(name="paymentmode_id")
	private String paymentmodeId;

	@Column(name="paymentmode_description")
	private String paymentmodeDescription;

	@Column(name="valido_al")
	private Timestamp validoAl;

	@Column(name="valido_dal")
	private Timestamp validoDal;

	//bi-directional many-to-one association to Applicationdetail
	@OneToMany(mappedBy="paymentmode")
	private List<Applicationdetail> applicationdetails;

	//bi-directional many-to-one association to Gatewaydetail
	@OneToMany(mappedBy="paymentmode")
	private List<Gatewaydetail> gatewaydetails;

	//bi-directional many-to-one association to Transazione
	@OneToMany(mappedBy="paymentmode")
	private List<Transazione> transaziones;

	public Paymentmode() {
	}

	public String getPaymentmodeId() {
		return this.paymentmodeId;
	}

	public void setPaymentmodeId(String paymentmodeId) {
		this.paymentmodeId = paymentmodeId;
	}

	public String getPaymentmodeDescription() {
		return this.paymentmodeDescription;
	}

	public void setPaymentmodeDescription(String paymentmodeDescription) {
		this.paymentmodeDescription = paymentmodeDescription;
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

	public List<Applicationdetail> getApplicationdetails() {
		return this.applicationdetails;
	}

	public void setApplicationdetails(List<Applicationdetail> applicationdetails) {
		this.applicationdetails = applicationdetails;
	}

	public Applicationdetail addApplicationdetail(Applicationdetail applicationdetail) {
		getApplicationdetails().add(applicationdetail);
		applicationdetail.setPaymentmode(this);

		return applicationdetail;
	}

	public Applicationdetail removeApplicationdetail(Applicationdetail applicationdetail) {
		getApplicationdetails().remove(applicationdetail);
		applicationdetail.setPaymentmode(null);

		return applicationdetail;
	}

	public List<Gatewaydetail> getGatewaydetails() {
		return this.gatewaydetails;
	}

	public void setGatewaydetails(List<Gatewaydetail> gatewaydetails) {
		this.gatewaydetails = gatewaydetails;
	}

	public Gatewaydetail addGatewaydetail(Gatewaydetail gatewaydetail) {
		getGatewaydetails().add(gatewaydetail);
		gatewaydetail.setPaymentmode(this);

		return gatewaydetail;
	}

	public Gatewaydetail removeGatewaydetail(Gatewaydetail gatewaydetail) {
		getGatewaydetails().remove(gatewaydetail);
		gatewaydetail.setPaymentmode(null);

		return gatewaydetail;
	}

	public List<Transazione> getTransaziones() {
		return this.transaziones;
	}

	public void setTransaziones(List<Transazione> transaziones) {
		this.transaziones = transaziones;
	}

	public Transazione addTransazione(Transazione transazione) {
		getTransaziones().add(transazione);
		transazione.setPaymentmode(this);

		return transazione;
	}

	public Transazione removeTransazione(Transazione transazione) {
		getTransaziones().remove(transazione);
		transazione.setPaymentmode(null);

		return transazione;
	}

}
