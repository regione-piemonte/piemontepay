/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the epay_t_quietanza_esito database table.
 *
 */
@Entity
@Table ( name = "epay_t_quietanza_esito" )
public class EpayTQuietanzaEsito implements Serializable {

	private static final long serialVersionUID = -3526203470528073775L;

	@Id
    @SequenceGenerator(name="EPAY_T_ESITI_RICEVUTI_IDESITO_GENERATOR", allocationSize=1, sequenceName="EPAY_T_ESITI_RICEVUTI_ID_ESITO_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EPAY_T_ESITI_RICEVUTI_IDESITO_GENERATOR")
    @Column ( name = "id_quietanza_esito", unique = true, nullable = false )
    private Long idQuietanzaEsito;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_ora_creazione")
	private Date dataOraCreazione;

    @Column ( name = "origine_inserimento", length = 255 )
    private String origineInserimento;

    @Column ( name = "ricevuta_pdf" )
    private byte [] ricevutaPdf;

	//bi-directional many-to-one association to EpayTQuietanzaDaElaborare
	@OneToMany(mappedBy="epayTQuietanzaEsito")
	private List<EpayTQuietanzaDaElaborare> epayTQuietanzaDaElaborares;

	public EpayTQuietanzaEsito() {
	}

    public Long getIdQuietanzaEsito () {
        return idQuietanzaEsito;
    }

    public void setIdQuietanzaEsito ( Long idQuietanzaEsito ) {
        this.idQuietanzaEsito = idQuietanzaEsito;
    }

	public Date getDataOraCreazione() {
		return this.dataOraCreazione;
	}

	public void setDataOraCreazione(Date dataOraCreazione) {
		this.dataOraCreazione = dataOraCreazione;
	}

    public String getOrigineInserimento () {
        return origineInserimento;
    }

    public void setOrigineInserimento ( String origineInserimento ) {
        this.origineInserimento = origineInserimento;
    }

    public byte [] getRicevutaPdf () {
        return ricevutaPdf;
    }

    public void setRicevutaPdf ( byte [] ricevutaPdf ) {
        this.ricevutaPdf = ricevutaPdf;
    }

	public List<EpayTQuietanzaDaElaborare> getEpayTQuietanzaDaElaborares() {
		return this.epayTQuietanzaDaElaborares;
	}

	public void setEpayTQuietanzaDaElaborares(List<EpayTQuietanzaDaElaborare> epayTQuietanzaDaElaborares) {
		this.epayTQuietanzaDaElaborares = epayTQuietanzaDaElaborares;
	}

	public EpayTQuietanzaDaElaborare addEpayTQuietanzaDaElaborare(EpayTQuietanzaDaElaborare epayTQuietanzaDaElaborare) {
		getEpayTQuietanzaDaElaborares().add(epayTQuietanzaDaElaborare);
		epayTQuietanzaDaElaborare.setEpayTQuietanzaEsito(this);

		return epayTQuietanzaDaElaborare;
	}

	public EpayTQuietanzaDaElaborare removeEpayTQuietanzaDaElaborare(EpayTQuietanzaDaElaborare epayTQuietanzaDaElaborare) {
		getEpayTQuietanzaDaElaborares().remove(epayTQuietanzaDaElaborare);
		epayTQuietanzaDaElaborare.setEpayTQuietanzaEsito(null);

		return epayTQuietanzaDaElaborare;
	}

}
