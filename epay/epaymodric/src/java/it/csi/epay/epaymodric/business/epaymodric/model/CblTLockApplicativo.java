/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the cbl_t_lock_applicativo database table.
 * 
 */
@Entity
@Table ( name = "cbl_t_lock_applicativo", schema="epaymodric" )
@NamedQuery(name="CblTLockApplicativo.findAll", query="SELECT c FROM CblTLockApplicativo c")
public class CblTLockApplicativo implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator ( name = "CBL_T_LOCK_APPLICATIVO_ID_GENERATOR", sequenceName="epaymodric.CBL_T_LOCK_APPLICATIVO_ID_SEQ", schema="epaymodric",
                    allocationSize = 1 )
    @GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "CBL_T_LOCK_APPLICATIVO_ID_GENERATOR" )
    private Long id;

	@Column(name="data_fine")
	private Timestamp dataFine;

	@Column(name="data_inizio")
	private Timestamp dataInizio;

	@Column(name="id_utente")
	private String idUtente;

	//bi-directional many-to-one association to CblTEnte
	@ManyToOne
	@JoinColumn(name="id_ente")
	private CblTEnte cblTEnte;

	public CblTLockApplicativo() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getIdUtente() {
		return this.idUtente;
	}

	public void setIdUtente(String idUtente) {
		this.idUtente = idUtente;
	}

	public CblTEnte getCblTEnte() {
		return this.cblTEnte;
	}

	public void setCblTEnte(CblTEnte cblTEnte) {
		this.cblTEnte = cblTEnte;
	}

}
