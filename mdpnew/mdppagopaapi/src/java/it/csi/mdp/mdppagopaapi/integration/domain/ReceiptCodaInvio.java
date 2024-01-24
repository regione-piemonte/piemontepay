/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the rt_coda_invio database table.
 * 
 */
@Entity
@Table(name="receipt_coda_invio")
@NamedQuery(name="ReceiptCodaInvio.findAll", query="SELECT r FROM ReceiptCodaInvio r")
public class ReceiptCodaInvio implements Serializable {
	private static final long serialVersionUID = 1L;

//	
//	@Id
//    @SequenceGenerator ( name = "RECEIPT_CODA_INVIO_ID_GENERATOR", sequenceName = "receipt_coda_invio_id_seq", allocationSize = 1 )
//    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RECEIPT_CODA_INVIO_ID_GENERATOR")


    @EmbeddedId
    private ReceiptCodaInvioPK id;
	
	@Column(name="application_id")
	private String applicationId;

	@Column(name="contatore_tentativi")
	private Integer contatoreTentativi;

	@Column(name="data_inizio_tentativi")
	private Timestamp dataInizioTentativi;

	@Column(name="data_inserimento")
	private Timestamp dataInserimento;

	@Column(name="data_tentativi")
	private Timestamp dataTentativi;

	@Column(name="data_ultima_modifica")
	private Timestamp dataUltimaModifica;

	@Column(name="num_giorni_tentativi_ko")
	private Integer numGiorniTentativiKo;

	@Column(name="ultimo_esito_fruitore")
	private String ultimoEsitoFruitore;

	public ReceiptCodaInvio() {
	}
	
    public ReceiptCodaInvioPK getId () {
        return id;
    }
    
    public void setId ( ReceiptCodaInvioPK id ) {
        this.id = id;
    }

    public String getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public Integer getContatoreTentativi() {
		return this.contatoreTentativi;
	}

	public void setContatoreTentativi(Integer contatoreTentativi) {
		this.contatoreTentativi = contatoreTentativi;
	}

	public Timestamp getDataInizioTentativi() {
		return this.dataInizioTentativi;
	}

	public void setDataInizioTentativi(Timestamp dataInizioTentativi) {
		this.dataInizioTentativi = dataInizioTentativi;
	}

	public Timestamp getDataInserimento() {
		return this.dataInserimento;
	}

	public void setDataInserimento(Timestamp dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	public Timestamp getDataTentativi() {
		return this.dataTentativi;
	}

	public void setDataTentativi(Timestamp dataTentativi) {
		this.dataTentativi = dataTentativi;
	}

	public Timestamp getDataUltimaModifica() {
		return this.dataUltimaModifica;
	}

	public void setDataUltimaModifica(Timestamp dataUltimaModifica) {
		this.dataUltimaModifica = dataUltimaModifica;
	}

	public Integer getNumGiorniTentativiKo() {
		return this.numGiorniTentativiKo;
	}

	public void setNumGiorniTentativiKo(Integer numGiorniTentativiKo) {
		this.numGiorniTentativiKo = numGiorniTentativiKo;
	}

	public String getUltimoEsitoFruitore() {
		return this.ultimoEsitoFruitore;
	}

	public void setUltimoEsitoFruitore(String ultimoEsitoFruitore) {
		this.ultimoEsitoFruitore = ultimoEsitoFruitore;
	}

}
