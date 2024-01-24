/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the epaywso_t_scheduler_lock database table.
 * 
 */
@Entity
@Table(name="epaywso_t_scheduler_lock")
@NamedQuery(name="EPaywsoTSchedulerLock.getLock", query="SELECT e FROM EPaywsoTSchedulerLock e")
public class EPaywsoTSchedulerLock implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_scheduler_lock")
	private Integer idSchedulerLock;

	public EPaywsoTSchedulerLock() {
	}

	public Integer getIdSchedulerLock() {
		return this.idSchedulerLock;
	}

	public void setIdSchedulerLock(Integer idSchedulerLock) {
		this.idSchedulerLock = idSchedulerLock;
	}

}
