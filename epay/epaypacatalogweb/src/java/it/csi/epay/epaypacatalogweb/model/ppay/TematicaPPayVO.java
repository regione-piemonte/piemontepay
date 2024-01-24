/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.model.ppay;

import it.csi.epay.epaypacatalogweb.model.GenericVO;

/**
 *
 */

public class TematicaPPayVO extends GenericVO {

    /**
     *
     */
    private static final long serialVersionUID = 7419351186959951411L;
    
    private Long id;
	private String codice;
	private String descrizione;
	
    public TematicaPPayVO () {
        // TODO Auto-generated constructor stub
    }

	public TematicaPPayVO(Long id, String codice, String descrizione) {
		super();
		this.id = id;
		this.codice = codice;
		this.descrizione = descrizione;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}
