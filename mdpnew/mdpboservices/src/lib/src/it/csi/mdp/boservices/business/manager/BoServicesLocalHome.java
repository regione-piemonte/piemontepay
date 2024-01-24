/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.boservices.business.manager;


import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;


public interface BoServicesLocalHome extends EJBLocalHome
{
	public BoServicesLocal create() throws CreateException;
}
