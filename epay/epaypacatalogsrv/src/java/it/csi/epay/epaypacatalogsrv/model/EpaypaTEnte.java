/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the epaypa_t_ente database table.
 * 
 */
@Entity
@Table(schema="epaypa", name="epaypa_t_ente")
@NamedQuery(name="EpaypaTEnte.findAll", query="SELECT e FROM EpaypaTEnte e")
public class EpaypaTEnte implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_ente")
	private Integer idEnte;

	@Column(name="cod_fiscale_ente")
	private String codFiscaleEnte;

	@Column(name="cod_gs1_gln")
	private String codGs1Gln;

	@Column(name="cod_interbancario")
	private String codInterbancario;

	private String denominazione;

	private String email;

	private byte[] logo;

	public EpaypaTEnte() {
	}

	public Integer getIdEnte() {
		return this.idEnte;
	}

	public void setIdEnte(Integer idEnte) {
		this.idEnte = idEnte;
	}

	public String getCodFiscaleEnte() {
		return this.codFiscaleEnte;
	}

	public void setCodFiscaleEnte(String codFiscaleEnte) {
		this.codFiscaleEnte = codFiscaleEnte;
	}

	public String getCodGs1Gln() {
		return this.codGs1Gln;
	}

	public void setCodGs1Gln(String codGs1Gln) {
		this.codGs1Gln = codGs1Gln;
	}

	public String getCodInterbancario() {
		return this.codInterbancario;
	}

	public void setCodInterbancario(String codInterbancario) {
		this.codInterbancario = codInterbancario;
	}

	public String getDenominazione() {
		return this.denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getLogo() {
		return this.logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

}
