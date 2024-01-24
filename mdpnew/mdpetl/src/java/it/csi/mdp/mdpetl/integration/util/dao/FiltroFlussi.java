/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import java.sql.Date;


public class FiltroFlussi {
	
	private Date dataDa;
	private Date dataA;
	
	public FiltroFlussi(Date dataDa, Date dataA) {
		super();
		this.dataDa = dataDa;
		this.dataA = dataA;
	}
	
	public Date getDataDa() {
		return dataDa;
	}
	public void setDataDa(Date dataDa) {
		this.dataDa = dataDa;
	}
	public Date getDataA() {
		return dataA;
	}
	public void setDataA(Date dataA) {
		this.dataA = dataA;
	}

	
}
