/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.boservices.business.manager;



import javax.ejb.EJBHome;

public interface BoServicesHome extends EJBHome
{
	  public BoServices create()
      throws javax.ejb.CreateException,java.rmi.RemoteException;
}
