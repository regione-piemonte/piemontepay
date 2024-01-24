/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.model.tassonomia;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import it.csi.epay.epaypacatalogweb.model.GenericVO;

/**
 *
 */

public class ComboBoxTassonomiaVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<GenericVO> macroArea ;
	
	private List<GenericVO> datiSpecificiIncasso = new LinkedList<GenericVO>();
	
	private List<GenericVO> tipoServizio = new LinkedList<GenericVO>();

	/**
	 * @return the macroArea
	 */
	public List<GenericVO> getMacroArea() {
		return macroArea;
	}

	/**
	 * @param macroArea the macroArea to set
	 */
	public void setMacroArea(List<GenericVO> macroArea) {
		this.macroArea = macroArea;
	}

	/**
	 * @return the datiSpecificiRiscossione
	 */
	public List<GenericVO> getDatiSpecificiIncasso() {
		return datiSpecificiIncasso;
	}

	/**
	 * @param datiSpecificiRiscossione the datiSpecificiRiscossione to set
	 */
	public void setDatiSpecificiIncasso(List<GenericVO> datiSpecificiIncasso) {
		this.datiSpecificiIncasso = datiSpecificiIncasso;
	}

	/**
	 * @return the tipoServizio
	 */
	public List<GenericVO> getTipoServizio() {
		return tipoServizio;
	}

	/**
	 * @param tipoServizio the tipoServizio to set
	 */
	public void setTipoServizio(List<GenericVO> tipoServizio) {
		this.tipoServizio = tipoServizio;
	}
	
	
	
	
	

}
