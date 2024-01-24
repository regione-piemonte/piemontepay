/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.bo;

import java.io.Serializable;


/*
 * 
 * NGueye
 */
public class ActionAudit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String idaction;

	private String description;

	public ActionAudit(String idaction, String description) {
		super();
		this.idaction = idaction;
		this.description = description;
	}

	public ActionAudit() {
	
	}

	public String getIdaction() {
		return idaction;
	}

	public void setIdaction(String idaction) {
		this.idaction = idaction;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ActionAudit [idaction=" + idaction + ", description=" + description + "]";
	}
	
	

	
}
