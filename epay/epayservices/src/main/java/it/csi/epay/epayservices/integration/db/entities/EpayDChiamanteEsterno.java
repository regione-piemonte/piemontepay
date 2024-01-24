/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the epay_d_chiamante_esterno database table.
 * 
 */
@Entity
@Table(name="epay_d_chiamante_esterno")
public class EpayDChiamanteEsterno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codice_chiamante", unique=true, nullable=false)
	private String codiceChiamante;

	@Column(name="data_fine_validita")
	private Timestamp dataFineValidita;

	@Column(name="data_inizio_validita", nullable=false)
	private Timestamp dataInizioValidita;

	@Column(name="descrizione_chiamante", length = 200)
	private String descrizioneChiamante;
	
	@Column(name="passphrase", nullable=false, length = 200)
	private String passphrase;

    @Column ( name = "url", nullable = false, length = 200 )
    private String url;

	//bi-directional many-to-one association to EpayTTracciabilitaChiamanteEsterno
	@OneToMany(mappedBy="epayDChiamanteEsterno")
	private List<EpayTTracciabilitaChiamanteEsterno> epayTTracciabilitaChiamanteEsternos;

	public EpayDChiamanteEsterno() {
	}

	public String getCodiceChiamante() {
		return this.codiceChiamante;
	}

	public void setCodiceChiamante(String codiceChiamante) {
		this.codiceChiamante = codiceChiamante;
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

	public String getDescrizioneChiamante() {
		return this.descrizioneChiamante;
	}

	public void setDescrizioneChiamante(String descrizioneChiamante) {
		this.descrizioneChiamante = descrizioneChiamante;
	}

	public List<EpayTTracciabilitaChiamanteEsterno> getEpayTTracciabilitaChiamanteEsternos() {
		return this.epayTTracciabilitaChiamanteEsternos;
	}

	public void setEpayTTracciabilitaChiamanteEsternos(List<EpayTTracciabilitaChiamanteEsterno> epayTTracciabilitaChiamanteEsternos) {
		this.epayTTracciabilitaChiamanteEsternos = epayTTracciabilitaChiamanteEsternos;
	}

	public EpayTTracciabilitaChiamanteEsterno addEpayTTracciabilitaChiamanteEsterno(EpayTTracciabilitaChiamanteEsterno epayTTracciabilitaChiamanteEsterno) {
		getEpayTTracciabilitaChiamanteEsternos().add(epayTTracciabilitaChiamanteEsterno);
		epayTTracciabilitaChiamanteEsterno.setEpayDChiamanteEsterno(this);

		return epayTTracciabilitaChiamanteEsterno;
	}

	public EpayTTracciabilitaChiamanteEsterno removeEpayTTracciabilitaChiamanteEsterno(EpayTTracciabilitaChiamanteEsterno epayTTracciabilitaChiamanteEsterno) {
		getEpayTTracciabilitaChiamanteEsternos().remove(epayTTracciabilitaChiamanteEsterno);
		epayTTracciabilitaChiamanteEsterno.setEpayDChiamanteEsterno(null);

		return epayTTracciabilitaChiamanteEsterno;
	}

	public String getPassphrase() {
		return passphrase;
	}

	public void setPassphrase(String passphrase) {
		this.passphrase = passphrase;
	}

    public String getUrl () {
        return url;
    }

    public void setUrl ( String url ) {
        this.url = url;
    }

}
