/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



/**
 * The persistent class for the cbl_d_stato_flusso database table.
 * 
 */
@Entity
@Table(name="cbl_d_stato_flusso",schema="epaymodric")
@NamedQuery(name="CblDStatoFlusso.findAll", query="SELECT c FROM CblDStatoFlusso c")
public class CblDStatoFlusso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CBL_D_STATO_FLUSSO_ID_GENERATOR", sequenceName="epaymodric.CBL_D_STATO_FLUSSO_ID_SEQ" ,schema="epaymodric", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CBL_D_STATO_FLUSSO_ID_GENERATOR")
	private Long id;

	@Column(name="codice_stato")
	private String codiceStato;

	@Column(name="descrizione_stato")
	private String descrizioneStato;

    @Column ( name = "permetti_rielaborazione" )
    private Boolean permettiRielaborazione;

	//bi-directional many-to-one association to CblTFlussoOrigine
	@OneToMany(mappedBy="cblDStatoFlusso")
	private List<CblTFlussoOrigine> cblTFlussoOrigines;

	public CblDStatoFlusso() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodiceStato() {
		return this.codiceStato;
	}

	public void setCodiceStato(String codiceStato) {
		this.codiceStato = codiceStato;
	}

	public String getDescrizioneStato() {
		return this.descrizioneStato;
	}

	public void setDescrizioneStato(String descrizioneStato) {
		this.descrizioneStato = descrizioneStato;
	}

	public List<CblTFlussoOrigine> getCblTFlussoOrigines() {
		return this.cblTFlussoOrigines;
	}

	public void setCblTFlussoOrigines(List<CblTFlussoOrigine> cblTFlussoOrigines) {
		this.cblTFlussoOrigines = cblTFlussoOrigines;
	}

    public Boolean getPermettiRielaborazione () {
        return permettiRielaborazione;
    }

    public void setPermettiRielaborazione ( Boolean permettiRielaborazione ) {
        this.permettiRielaborazione = permettiRielaborazione;
    }

    public CblTFlussoOrigine addCblTFlussoOrigine ( CblTFlussoOrigine cblTFlussoOrigine ) {
		getCblTFlussoOrigines().add(cblTFlussoOrigine);
		cblTFlussoOrigine.setCblDStatoFlusso(this);

		return cblTFlussoOrigine;
	}

	public CblTFlussoOrigine removeCblTFlussoOrigine(CblTFlussoOrigine cblTFlussoOrigine) {
		getCblTFlussoOrigines().remove(cblTFlussoOrigine);
		cblTFlussoOrigine.setCblDStatoFlusso(null);

		return cblTFlussoOrigine;
	}

}
