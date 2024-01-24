/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the epay_t_dati_singola_revoca database table.
 *
 */
@Entity
@Table(name="epay_t_dati_singola_revoca")
@NamedQueries ( {
    @NamedQuery ( name = "EpayTDatiSingolaRevoca.findAll", query = "SELECT e FROM EpayTDatiSingolaRevoca e" ),
    @NamedQuery ( name = "EpayTDatiSingolaRevoca.elencoDettagliPerInvioRevoche",
                    query = "SELECT e FROM EpayTDatiSingolaRevoca e WHERE e.idRr = :idRr ORDER BY id ASC" )
} )
public class EpayTDatiSingolaRevoca implements Serializable {
	private static final long serialVersionUID = 1L;
	private String causaleRevoca;
	private String datiAggiuntiviRevoca;
	private Integer id;
	private Integer idRr;
	private String iur;
	private BigDecimal singoloImportoRevocato;

	public EpayTDatiSingolaRevoca() {
	}


	@Column(name="causale_revoca", nullable=false, length=140)
	public String getCausaleRevoca() {
		return causaleRevoca;
	}

	public void setCausaleRevoca(String causaleRevoca) {
		this.causaleRevoca = causaleRevoca;
	}


	@Column(name="dati_aggiuntivi_revoca", nullable=false, length=140)
	public String getDatiAggiuntiviRevoca() {
		return datiAggiuntiviRevoca;
	}

	public void setDatiAggiuntiviRevoca(String datiAggiuntiviRevoca) {
		this.datiAggiuntiviRevoca = datiAggiuntiviRevoca;
	}

	@Id
	@SequenceGenerator(name="EPAY_T_DATI_SINGOLA_REVOCA_GENERATOR", allocationSize=1, sequenceName="EPAY_T_DATI_SINGOLA_REVOCA_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EPAY_T_DATI_SINGOLA_REVOCA_GENERATOR")
	@Column(name="id", unique=true, nullable=false)

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	@Column(name="id_rr", nullable=false)
	public Integer getIdRr() {
		return idRr;
	}

	public void setIdRr(Integer idRr) {
		this.idRr = idRr;
	}


	@Column(nullable=false, length=35)
	public String getIur() {
		return iur;
	}

	public void setIur(String iur) {
		this.iur = iur;
	}


	@Column(name="singolo_importo_revocato", nullable=false, precision=13, scale=2)
	public BigDecimal getSingoloImportoRevocato() {
		return singoloImportoRevocato;
	}

	public void setSingoloImportoRevocato(BigDecimal singoloImportoRevocato) {
		this.singoloImportoRevocato = singoloImportoRevocato;
	}

}
