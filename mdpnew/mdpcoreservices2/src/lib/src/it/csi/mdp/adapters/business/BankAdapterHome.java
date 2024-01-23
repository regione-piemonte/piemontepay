/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.adapters.business;

import it.csi.mdp.adapters.business.*;

import javax.ejb.EJBHome;

public abstract interface BankAdapterHome extends EJBHome
{
	   public BankAdapter create()
	      throws javax.ejb.CreateException,java.rmi.RemoteException;
}
