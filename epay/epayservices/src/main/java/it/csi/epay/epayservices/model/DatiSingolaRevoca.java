/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;
import java.math.BigDecimal;


public class DatiSingolaRevoca implements Serializable {
	
	private static final long serialVersionUID = -4798523297268806406L;
	
	private String causaleRevoca;
	private String datiAggiuntiviRevoca;
	private Integer id;
	private Integer idRr;
	private String iur;
	private BigDecimal singoloImportoRevocato;
	/**
	 * @return the causaleRevoca
	 */
	public String getCausaleRevoca() {
		return causaleRevoca;
	}
	/**
	 * @param causaleRevoca the causaleRevoca to set
	 */
	public void setCausaleRevoca(String causaleRevoca) {
		this.causaleRevoca = causaleRevoca;
	}
	/**
	 * @return the datiAggiuntiviRevoca
	 */
	public String getDatiAggiuntiviRevoca() {
		return datiAggiuntiviRevoca;
	}
	/**
	 * @param datiAggiuntiviRevoca the datiAggiuntiviRevoca to set
	 */
	public void setDatiAggiuntiviRevoca(String datiAggiuntiviRevoca) {
		this.datiAggiuntiviRevoca = datiAggiuntiviRevoca;
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the idRr
	 */
	public Integer getIdRr() {
		return idRr;
	}
	/**
	 * @param idRr the idRr to set
	 */
	public void setIdRr(Integer idRr) {
		this.idRr = idRr;
	}
	/**
	 * @return the iur
	 */
	public String getIur() {
		return iur;
	}
	/**
	 * @param iur the iur to set
	 */
	public void setIur(String iur) {
		this.iur = iur;
	}
	/**
	 * @return the singoloImportoRevocato
	 */
	public BigDecimal getSingoloImportoRevocato() {
		return singoloImportoRevocato;
	}
	/**
	 * @param singoloImportoRevocato the singoloImportoRevocato to set
	 */
	public void setSingoloImportoRevocato(BigDecimal singoloImportoRevocato) {
		this.singoloImportoRevocato = singoloImportoRevocato;
	}


}
