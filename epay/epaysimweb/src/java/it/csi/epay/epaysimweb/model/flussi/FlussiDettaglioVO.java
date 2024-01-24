/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysimweb.model.flussi;

import java.util.List;

public class FlussiDettaglioVO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    protected FlussoSintesiVO flussoSintesi;
    protected List<FlussoDettaglioVO> flussiDettaglio;

	public FlussiDettaglioVO() {
		// TODO Auto-generated constructor stub
	}

	public FlussoSintesiVO getFlussoSintesi() {
		return flussoSintesi;
	}

	public void setFlussoSintesi(FlussoSintesiVO flussoSintesi) {
		this.flussoSintesi = flussoSintesi;
	}

	public List<FlussoDettaglioVO> getFlussiDettaglio() {
		return flussiDettaglio;
	}

	public void setFlussiDettaglio(List<FlussoDettaglioVO> flussiDettaglio) {
		this.flussiDettaglio = flussiDettaglio;
	}

}
