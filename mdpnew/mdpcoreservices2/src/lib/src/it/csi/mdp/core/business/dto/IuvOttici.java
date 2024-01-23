/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import java.io.Serializable;
import java.util.Date;

public class IuvOttici implements Serializable {

	private static final long serialVersionUID = 6392173375364159953L;
	
	private Integer id;
	private String iuvOttico;
	private String iuvStandard;
	private String enteId;
	private String codiceVersamento;
	private String applicationId;
	private Date dataCreazione;
	private Date dataRiconciliazione;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIuvOttico() {
		return iuvOttico;
	}
	public void setIuvOttico(String iuvOttico) {
		this.iuvOttico = iuvOttico;
	}
	public String getIuvStandard() {
		return iuvStandard;
	}
	public void setIuvStandard(String iuvStandard) {
		this.iuvStandard = iuvStandard;
	}
	public String getEnteId() {
		return enteId;
	}
	public void setEnteId(String enteId) {
		this.enteId = enteId;
	}
	public String getCodiceVersamento() {
		return codiceVersamento;
	}
	public void setCodiceVersamento(String codiceVersamento) {
		this.codiceVersamento = codiceVersamento;
	}
	public Date getDataCreazione() {
		return dataCreazione;
	}
	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}
	public Date getDataRiconciliazione() {
		return dataRiconciliazione;
	}
	public void setDataRiconciliazione(Date dataRiconciliazione) {
		this.dataRiconciliazione = dataRiconciliazione;
	}
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	
}
