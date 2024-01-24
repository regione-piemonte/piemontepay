/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.dto.epaymodric.base;

import java.io.Serializable;

/*
 * 
 * @Gueye
 */
//@XmlAccessorType ( XmlAccessType.PROPERTY )
//@XmlType ( name = "dtoAuditAction" )
public class DTOAuditAction implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String idaction;

	private String description;

	public DTOAuditAction(String idaction, String description) {
		super();
		this.idaction = idaction;
		this.description = description;
	}

	public DTOAuditAction() {
		
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
		return "DTOAuditAction [idaction=" + idaction + ", description=" + description + "]";
	}
	
	
	
	

}
