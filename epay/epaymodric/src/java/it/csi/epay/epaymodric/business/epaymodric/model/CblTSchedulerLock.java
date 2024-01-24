/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the cbl_t_scheduler_lock database table.
 *
 */
@Entity
@Table(name="cbl_t_scheduler_lock",schema="epaymodric")
public class CblTSchedulerLock implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4662423728362923900L;
	
	@Id
	@Column(name="id_scheduler_lock")
	private Integer idSchedulerLock;

	public CblTSchedulerLock() {
		super();
	}

	public Integer getIdSchedulerLock() {
		return idSchedulerLock;
	}

	public void setIdSchedulerLock(Integer idSchedulerLock) {
		this.idSchedulerLock = idSchedulerLock;
	}

}
