/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cbl_d_tipo_acquisizione database table.
 * 
 */
@Entity
@Table(name="cbl_d_tipo_acquisizione")
@NamedQuery(name="CblDTipoAcquisizione.findAll", query="SELECT c FROM CblDTipoAcquisizione c")
public class CblDTipoAcquisizione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CBL_D_TIPO_ACQUISIZIONE_ID_GENERATOR", sequenceName="CBL_D_TIPO_ACQUISIZIONE_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CBL_D_TIPO_ACQUISIZIONE_ID_GENERATOR")
	private Long id;

	private String codice;

	private String descrizione;

	//bi-directional many-to-one association to CblTFlussoOrigine
	@OneToMany(mappedBy="cblDTipoAcquisizione")
	private List<CblTFlussoOrigine> cblTFlussoOrigines;

	public CblDTipoAcquisizione() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodice() {
		return this.codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<CblTFlussoOrigine> getCblTFlussoOrigines() {
		return this.cblTFlussoOrigines;
	}

	public void setCblTFlussoOrigines(List<CblTFlussoOrigine> cblTFlussoOrigines) {
		this.cblTFlussoOrigines = cblTFlussoOrigines;
	}

	public CblTFlussoOrigine addCblTFlussoOrigine(CblTFlussoOrigine cblTFlussoOrigine) {
		getCblTFlussoOrigines().add(cblTFlussoOrigine);
		cblTFlussoOrigine.setCblDTipoAcquisizione(this);

		return cblTFlussoOrigine;
	}

	public CblTFlussoOrigine removeCblTFlussoOrigine(CblTFlussoOrigine cblTFlussoOrigine) {
		getCblTFlussoOrigines().remove(cblTFlussoOrigine);
		cblTFlussoOrigine.setCblDTipoAcquisizione(null);

		return cblTFlussoOrigine;
	}

}
