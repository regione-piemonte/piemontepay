/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the peas_table_id database table.
 * 
 */
@Entity
@Table(name="peas_table_id")
@NamedQuery(name="PeasTableId.findAll", query="SELECT p FROM PeasTableId p")
public class PeasTableId implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PEAS_TABLE_ID_TABLEPK_GENERATOR", sequenceName="PEAS_TABLE_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PEAS_TABLE_ID_TABLEPK_GENERATOR")
	@Column(name="table_pk")
	private String tablePk;

	@Column(name="table_id")
	private String tableId;

	public PeasTableId() {
	}

	public String getTablePk() {
		return this.tablePk;
	}

	public void setTablePk(String tablePk) {
		this.tablePk = tablePk;
	}

	public String getTableId() {
		return this.tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

}
