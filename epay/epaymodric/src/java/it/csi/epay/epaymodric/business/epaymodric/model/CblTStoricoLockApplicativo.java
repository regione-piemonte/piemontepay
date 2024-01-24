/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the cbl_t_storico_lock_applicativo database table.
 * 
 */
@Entity
@Table(name="cbl_t_storico_lock_applicativo",schema="epaymodric")
@NamedQuery(name="CblTStoricoLockApplicativo.findAll", query="SELECT c FROM CblTStoricoLockApplicativo c")
public class CblTStoricoLockApplicativo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="data_fine")
	private Timestamp dataFine;

	@Column(name="data_inizio")
	private Timestamp dataInizio;

    @Id
    @SequenceGenerator(name="CBL_T_STORICO_LOCK_APPLICATIVO_ID_GENERATOR", sequenceName="epaymodric.CBL_T_STORICO_LOCK_APPLICATIVO_ID_SEQ" ,schema="epaymodric", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CBL_T_STORICO_LOCK_APPLICATIVO_ID_GENERATOR")
    private Long id;


	@Column(name="id_ente")
	private Long idEnte;

	@Column(name="id_utente")
	private String idUtente;

	public CblTStoricoLockApplicativo() {
	}

	public Timestamp getDataFine() {
		return this.dataFine;
	}

	public void setDataFine(Timestamp dataFine) {
		this.dataFine = dataFine;
	}

	public Timestamp getDataInizio() {
		return this.dataInizio;
	}

	public void setDataInizio(Timestamp dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdEnte() {
		return this.idEnte;
	}

	public void setIdEnte(Long idEnte) {
		this.idEnte = idEnte;
	}

	public String getIdUtente() {
		return this.idUtente;
	}

	public void setIdUtente(String idUtente) {
		this.idUtente = idUtente;
	}

}
