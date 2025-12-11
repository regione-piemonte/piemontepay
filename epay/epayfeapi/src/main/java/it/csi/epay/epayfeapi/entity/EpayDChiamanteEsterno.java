/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table ( schema = "epay", name = "epay_d_chiamante_esterno" )
@SuppressWarnings ( "unused" )
public class EpayDChiamanteEsterno implements Serializable {

	private static final long serialVersionUID = -4861205948971729612L;

	@Id
	@Column ( name = "codice_chiamante" )
	private String codiceChiamante;

	@Column ( name = "descrizione_chiamante" )
	private String descrizioneChiamante;

	@Column ( name = "data_inizio_validita" )
	private Date dataInizioValidita;

	@Column ( name = "data_fine_validita" )
	private Date dataFineValidita;

	@Column ( name = "passphrase" )
	private String passphrase;

	@Column ( name = "url" )
	private String url;

	public String getCodiceChiamante () {
		return codiceChiamante;
	}

	public void setCodiceChiamante ( String codiceChiamante ) {
		this.codiceChiamante = codiceChiamante;
	}

	public String getDescrizioneChiamante () {
		return descrizioneChiamante;
	}

	public void setDescrizioneChiamante ( String descrizioneChiamante ) {
		this.descrizioneChiamante = descrizioneChiamante;
	}

	public Date getDataInizioValidita () {
		return dataInizioValidita;
	}

	public void setDataInizioValidita ( Date dataInizioValidita ) {
		this.dataInizioValidita = dataInizioValidita;
	}

	public Date getDataFineValidita () {
		return dataFineValidita;
	}

	public void setDataFineValidita ( Date dataFineValidita ) {
		this.dataFineValidita = dataFineValidita;
	}

	public String getPassphrase () {
		return passphrase;
	}

	public void setPassphrase ( String passphrase ) {
		this.passphrase = passphrase;
	}

	public String getUrl () {
		return url;
	}

	public void setUrl ( String url ) {
		this.url = url;
	}

	@Override
	public String toString () {
		return "{ " +
						"codiceChiamante:" + codiceChiamante +
						", descrizioneChiamante:" + descrizioneChiamante +
						", dataInizioValidita:" + dataInizioValidita +
						", dataFineValidita:" + dataFineValidita +
			", passphrase (valore non esposto) " +
						", url:" + url +
						" }";
	}
}
