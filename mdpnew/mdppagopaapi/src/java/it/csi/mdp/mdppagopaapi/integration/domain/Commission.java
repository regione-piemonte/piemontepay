/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the commission database table.
 * 
 */
@Entity
@NamedQuery(name="Commission.findAll", query="SELECT c FROM Commission c")
public class Commission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="COMMISSION_COMMISSIONID_GENERATOR", sequenceName="COMMISSION_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="COMMISSION_COMMISSIONID_GENERATOR")
	@Column(name="commission_id")
	private String commissionId;

	@Column(name="commission_description")
	private String commissionDescription;

	//bi-directional many-to-one association to Applicationdetail
	@OneToMany(mappedBy="commission")
	private List<Applicationdetail> applicationdetails;

	public Commission() {
	}

	public String getCommissionId() {
		return this.commissionId;
	}

	public void setCommissionId(String commissionId) {
		this.commissionId = commissionId;
	}

	public String getCommissionDescription() {
		return this.commissionDescription;
	}

	public void setCommissionDescription(String commissionDescription) {
		this.commissionDescription = commissionDescription;
	}

	public List<Applicationdetail> getApplicationdetails() {
		return this.applicationdetails;
	}

	public void setApplicationdetails(List<Applicationdetail> applicationdetails) {
		this.applicationdetails = applicationdetails;
	}

	public Applicationdetail addApplicationdetail(Applicationdetail applicationdetail) {
		getApplicationdetails().add(applicationdetail);
		applicationdetail.setCommission(this);

		return applicationdetail;
	}

	public Applicationdetail removeApplicationdetail(Applicationdetail applicationdetail) {
		getApplicationdetails().remove(applicationdetail);
		applicationdetail.setCommission(null);

		return applicationdetail;
	}

}
