/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.dto;

import static it.csi.epay.epaywsosrv.util.Util.date2strdatetime;
import static it.csi.epay.epaywsosrv.util.Util.quote;

import java.io.Serializable;
import java.util.Date;

public class AppDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String des;
	private Integer idEnte;
	private String utente;
	private String msInbound;
	private String msOutbound;
	private Date dataInizioValidita;
	private Date dataFineValidita;
	private String codiceModalitaIntegrazione;

	public AppDto(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Integer getIdEnte() {
		return idEnte;
	}

	public void setIdEnte(Integer idEnte) {
		this.idEnte = idEnte;
	}

	public String getUtente() {
		return utente;
	}

	public void setUtente(String utente) {
		this.utente = utente;
	}

	public String getMsInbound() {
		return msInbound;
	}

	public void setMsInbound(String msInbound) {
		this.msInbound = msInbound;
	}

	public String getMsOutbound() {
		return msOutbound;
	}

	public void setMsOutbound(String msOutbound) {
		this.msOutbound = msOutbound;
	}

	public Date getDataInizioValidita() {
		return dataInizioValidita;
	}

	public void setDataInizioValidita(Date dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}

	public Date getDataFineValidita() {
		return dataFineValidita;
	}

	public void setDataFineValidita(Date dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}

	
    public String getCodiceModalitaIntegrazione () {
        return codiceModalitaIntegrazione;
    }

    
    public void setCodiceModalitaIntegrazione ( String codiceModalitaIntegrazione ) {
        this.codiceModalitaIntegrazione = codiceModalitaIntegrazione;
    }

    @Override
    public String toString () {
        return "AppDto [id=" + id + ", des=" + des + ", idEnte=" + idEnte + ", utente=" + utente + ", msInbound=" + msInbound + ", msOutbound=" + msOutbound
            + ", dataInizioValidita=" + dataInizioValidita + ", dataFineValidita=" + dataFineValidita + ", codiceModalitaIntegrazione="
            + codiceModalitaIntegrazione + "]";
    }

}
