/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the iban_ente_application database table.
 * 
 */
@Entity
@Table(name="iban_ente_application")
@NamedQuery(name="IbanEnteApplication.findAll", query="SELECT i FROM IbanEnteApplication i")
public class IbanEnteApplication implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="IBAN_ENTE_APPLICATION_ID_GENERATOR", sequenceName="IBAN_ENTE_APPLICATION_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IBAN_ENTE_APPLICATION_ID_GENERATOR")
	private Integer id;

	@Column(name="application_id")
	private String applicationId;

	private String attivo;

	@Column(name="data_fine_validita")
	private Timestamp dataFineValidita;

	@Column(name="data_inizio_validita")
	private Timestamp dataInizioValidita;

	private String iban;

	@Column(name="id_ente")
	private String idEnte;

	private String identificativopsp;

	private String tipoversamento;

	public IbanEnteApplication() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getAttivo() {
		return this.attivo;
	}

	public void setAttivo(String attivo) {
		this.attivo = attivo;
	}

	public Timestamp getDataFineValidita() {
		return this.dataFineValidita;
	}

	public void setDataFineValidita(Timestamp dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}

	public Timestamp getDataInizioValidita() {
		return this.dataInizioValidita;
	}

	public void setDataInizioValidita(Timestamp dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}

	public String getIban() {
		return this.iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getIdEnte() {
		return this.idEnte;
	}

	public void setIdEnte(String idEnte) {
		this.idEnte = idEnte;
	}

	public String getIdentificativopsp() {
		return this.identificativopsp;
	}

	public void setIdentificativopsp(String identificativopsp) {
		this.identificativopsp = identificativopsp;
	}

	public String getTipoversamento() {
		return this.tipoversamento;
	}

	public void setTipoversamento(String tipoversamento) {
		this.tipoversamento = tipoversamento;
	}

}
