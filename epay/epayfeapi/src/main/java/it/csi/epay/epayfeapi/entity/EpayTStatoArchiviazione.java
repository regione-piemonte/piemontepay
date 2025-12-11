/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table (
				name = "epay_t_stato_archiviazione",
				uniqueConstraints = { @UniqueConstraint (
								name = "epay_t_stato_archiviazione_ukey",
								columnNames = { "codice_fiscale", "id_ente" } ) } )
@SuppressWarnings ( "unused" )
public class EpayTStatoArchiviazione implements Serializable {

	private static final long serialVersionUID = 8940141112371442277L;

	@Id
	@SequenceGenerator ( name = "EPAY_T_STATO_ARCHIVIAZIONE_ID_GENERATOR", allocationSize = 1,
					sequenceName = "EPAY_T_STATO_ARCHIVIAZIONE_ID_SEQ" )
	@GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "EPAY_T_STATO_ARCHIVIAZIONE_ID_GENERATOR" )
	@Column ( name = "id", unique = true, nullable = false )
	private Long id;

	@Column ( name = "codice_fiscale" )
	private String codiceFiscale;

	@ManyToOne
	@JoinColumn ( name = "id_ente", nullable = false )
	private EpayTEnti epayTEnti;

	@Column ( name = "flag_abilita_archiviazione", nullable = false )
	private Boolean flagAbilitaArchiviazione;

	@Column ( name = "data_ora_ultima_modifica", nullable = false )
	@Temporal ( TemporalType.TIMESTAMP )
	private Date dataOraUltimaModifica;

	@ManyToOne
	@JoinColumn ( name = "utente_modifica", nullable = false )
	private EpayDChiamanteEsterno epayDChiamanteEsterno;

	public EpayTStatoArchiviazione () {
	}

	public Long getId () {
		return this.id;
	}

	public void setId ( Long id ) {
		this.id = id;
	}

	public String getCodiceFiscale () {
		return this.codiceFiscale;
	}

	public void setCodiceFiscale ( String codiceFiscale ) {
		this.codiceFiscale = codiceFiscale;
	}

	public EpayTEnti getEpayTEnti () {
		return epayTEnti;
	}

	public void setEpayTEnti ( EpayTEnti epayTEnti ) {
		this.epayTEnti = epayTEnti;
	}

	public Boolean getFlagAbilitaArchiviazione () {
		return flagAbilitaArchiviazione;
	}

	public void setFlagAbilitaArchiviazione ( Boolean flagAbilitaArchiviazione ) {
		this.flagAbilitaArchiviazione = flagAbilitaArchiviazione;
	}

	public EpayDChiamanteEsterno getEpayDChiamanteEsterno () {
		return this.epayDChiamanteEsterno;
	}

	public void setEpayDChiamanteEsterno ( EpayDChiamanteEsterno epayDChiamanteEsterno ) {
		this.epayDChiamanteEsterno = epayDChiamanteEsterno;
	}

	public Date getDataOraUltimaModifica () {
		return dataOraUltimaModifica;
	}

	public void setDataOraUltimaModifica ( Date dataOraUltimaModifica ) {
		this.dataOraUltimaModifica = dataOraUltimaModifica;
	}

	@Override
	public String toString () {
		return "{ " +
						"id:" + id +
						", codiceFiscale:" + codiceFiscale +
						", epayTEnti:" + epayTEnti +
						", flagAbilitaArchiviazione:" + flagAbilitaArchiviazione +
						", dataOraUltimaModifica:" + dataOraUltimaModifica +
						// non esporre epayDChiamanteEsterno
						" }";
	}
}
