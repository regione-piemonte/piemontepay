/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the d_comuni_attivi_e_chiusi database table.
 * 
 */
@Entity
@Table(name="d_comuni_attivi_e_chiusi")
@NamedQuery(name="DComuniAttiviEChiusi.findAll", query="SELECT d FROM DComuniAttiviEChiusi d")
public class DComuniAttiviEChiusi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="D_COMUNI_ATTIVI_E_CHIUSI_IDCOMUNE_GENERATOR", sequenceName="D_COMUNI_ATTIVI_E_CHIUSI_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="D_COMUNI_ATTIVI_E_CHIUSI_IDCOMUNE_GENERATOR")
	@Column(name="id_comune")
	private Long idComune;

	private BigDecimal altitudine;

	private String attiva;

	private String cap;

	@Column(name="cod_catasto")
	private String codCatasto;

	@Column(name="data_fine_validita")
	private Timestamp dataFineValidita;

	@Column(name="data_upd")
	private Timestamp dataUpd;

	@Column(name="desc_comune")
	private String descComune;

	@Column(name="desc_zona_altimetrica")
	private String descZonaAltimetrica;

	@Column(name="istat_comune")
	private String istatComune;

	@Column(name="istat_comune_ktl")
	private Long istatComuneKtl;

	@Column(name="istat_provincia")
	private String istatProvincia;

	@Column(name="istat_regione")
	private String istatRegione;

	@Column(name="istat_zona_altimetrica")
	private String istatZonaAltimetrica;

	@Column(name="superficie_hm2")
	private BigDecimal superficieHm2;

	public DComuniAttiviEChiusi() {
	}

	public Long getIdComune() {
		return this.idComune;
	}

	public void setIdComune(Long idComune) {
		this.idComune = idComune;
	}

	public BigDecimal getAltitudine() {
		return this.altitudine;
	}

	public void setAltitudine(BigDecimal altitudine) {
		this.altitudine = altitudine;
	}

	public String getAttiva() {
		return this.attiva;
	}

	public void setAttiva(String attiva) {
		this.attiva = attiva;
	}

	public String getCap() {
		return this.cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getCodCatasto() {
		return this.codCatasto;
	}

	public void setCodCatasto(String codCatasto) {
		this.codCatasto = codCatasto;
	}

	public Timestamp getDataFineValidita() {
		return this.dataFineValidita;
	}

	public void setDataFineValidita(Timestamp dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}

	public Timestamp getDataUpd() {
		return this.dataUpd;
	}

	public void setDataUpd(Timestamp dataUpd) {
		this.dataUpd = dataUpd;
	}

	public String getDescComune() {
		return this.descComune;
	}

	public void setDescComune(String descComune) {
		this.descComune = descComune;
	}

	public String getDescZonaAltimetrica() {
		return this.descZonaAltimetrica;
	}

	public void setDescZonaAltimetrica(String descZonaAltimetrica) {
		this.descZonaAltimetrica = descZonaAltimetrica;
	}

	public String getIstatComune() {
		return this.istatComune;
	}

	public void setIstatComune(String istatComune) {
		this.istatComune = istatComune;
	}

	public Long getIstatComuneKtl() {
		return this.istatComuneKtl;
	}

	public void setIstatComuneKtl(Long istatComuneKtl) {
		this.istatComuneKtl = istatComuneKtl;
	}

	public String getIstatProvincia() {
		return this.istatProvincia;
	}

	public void setIstatProvincia(String istatProvincia) {
		this.istatProvincia = istatProvincia;
	}

	public String getIstatRegione() {
		return this.istatRegione;
	}

	public void setIstatRegione(String istatRegione) {
		this.istatRegione = istatRegione;
	}

	public String getIstatZonaAltimetrica() {
		return this.istatZonaAltimetrica;
	}

	public void setIstatZonaAltimetrica(String istatZonaAltimetrica) {
		this.istatZonaAltimetrica = istatZonaAltimetrica;
	}

	public BigDecimal getSuperficieHm2() {
		return this.superficieHm2;
	}

	public void setSuperficieHm2(BigDecimal superficieHm2) {
		this.superficieHm2 = superficieHm2;
	}

}
