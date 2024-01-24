/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db;

import it.csi.epay.epayservices.utilities.MapId;

/**
 * Id dei mapping di coversione.
 * 
 * Naming convention: * 
 * Il nome dell'Entity ed il nome del Model concatenati da "2" 
 * 
 * @author Massimo
 */
public enum MapIdDB implements MapId {
	A("a2a"),
	B("B2B")
	;
		
	private String nameMapId;
	
	private MapIdDB(String nameMapId) {
		this.nameMapId = nameMapId;
	}

	@Override
	public String getNameMapId() {
		return nameMapId;
	}
}
