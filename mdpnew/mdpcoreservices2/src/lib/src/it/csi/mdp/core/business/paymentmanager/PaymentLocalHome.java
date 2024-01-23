/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.paymentmanager;



import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;

public interface PaymentLocalHome extends EJBLocalHome {
	public PaymentLocal create() throws CreateException;
}
