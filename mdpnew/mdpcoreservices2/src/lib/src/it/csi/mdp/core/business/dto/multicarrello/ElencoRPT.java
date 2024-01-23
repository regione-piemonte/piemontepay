/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto.multicarrello;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ElencoRPT")
@XmlAccessorType(XmlAccessType.FIELD)
public class ElencoRPT implements Serializable{

	private static final long serialVersionUID = -2826070331370535811L;
	
	@XmlElement(name="rptdata", required = true)
	protected List<RPTData> elenco;

	public List<RPTData> getElenco() {
		return elenco;
	}

	public void setElenco(List<RPTData> elenco) {
		this.elenco = elenco;
	}
	
	
}
