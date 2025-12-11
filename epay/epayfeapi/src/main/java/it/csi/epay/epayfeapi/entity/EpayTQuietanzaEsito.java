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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;


@Entity
@Table ( name = "epay_t_quietanza_esito" )
@SuppressWarnings ( "unused" )
public class EpayTQuietanzaEsito implements Serializable {

	private static final long serialVersionUID = -1359167341632178976L;

	@Id
	@SequenceGenerator ( name = "EPAY_T_ESITI_RICEVUTI_IDESITO_GENERATOR", allocationSize = 1, sequenceName = "EPAY_T_ESITI_RICEVUTI_ID_ESITO_SEQ" )
	@GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "EPAY_T_ESITI_RICEVUTI_IDESITO_GENERATOR" )
	@Column ( name = "id_quietanza_esito", unique = true, nullable = false )
	private Long idQuietanzaEsito;

	@Column ( name = "ricevuta_pdf" )
	private byte[] ricevutaPdf;

	@Column ( name = "data_ora_creazione" )
	private Timestamp dataOraCreazione;

	@Column ( name = "origine_inserimento" )
	private String origineInserimento;

	public Long getIdQuietanzaEsito () {
		return idQuietanzaEsito;
	}

	public void setIdQuietanzaEsito ( Long idQuietanzaEsito ) {
		this.idQuietanzaEsito = idQuietanzaEsito;
	}

	public byte[] getRicevutaPdf () {
		return ricevutaPdf;
	}

	public void setRicevutaPdf ( byte[] ricevutaPdf ) {
		this.ricevutaPdf = ricevutaPdf;
	}

	public Timestamp getDataOraCreazione () {
		return dataOraCreazione;
	}

	public void setDataOraCreazione ( Timestamp dataOraCreazione ) {
		this.dataOraCreazione = dataOraCreazione;
	}

	public String getOrigineInserimento () {
		return origineInserimento;
	}

	public void setOrigineInserimento ( String origineInserimento ) {
		this.origineInserimento = origineInserimento;
	}

	@Override
	public String toString () {
		return "{ " +
						"idQuietanzaEsito:" + idQuietanzaEsito +
						// non esporre ricevutaPdf
						", dataOraCreazione:" + dataOraCreazione +
						", origineInserimento:" + origineInserimento +
						" }";
	}
}
