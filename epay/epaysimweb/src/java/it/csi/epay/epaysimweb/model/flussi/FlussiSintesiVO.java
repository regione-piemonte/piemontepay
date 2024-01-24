/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysimweb.model.flussi;

import java.util.List;

public class FlussiSintesiVO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    protected FlussoOrigineVO flussoOrigine;
    protected List<FlussoSintesiVO> flussiSintesi;

	public FlussiSintesiVO() {
		// TODO Auto-generated constructor stub
	}

	public FlussoOrigineVO getFlussoOrigine() {
		return flussoOrigine;
	}

	public void setFlussoOrigine(FlussoOrigineVO flussoOrigine) {
		this.flussoOrigine = flussoOrigine;
	}

	public List<FlussoSintesiVO> getFlussiSintesi() {
		return flussiSintesi;
	}

	public void setFlussiSintesi(List<FlussoSintesiVO> flussiSintesi) {
		this.flussiSintesi = flussiSintesi;
	}

}
