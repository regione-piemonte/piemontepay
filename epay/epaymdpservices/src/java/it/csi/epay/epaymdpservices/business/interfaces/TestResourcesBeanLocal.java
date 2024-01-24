/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymdpservices.business.interfaces;

import javax.ejb.Local;

@Local
public interface TestResourcesBeanLocal {
	
	public int getHits();
	
}
