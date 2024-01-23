/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package  it.csi.mdp.core.business.paymentmanager;

/**
 * Home interface for Test
 *
 * <p>
 *
 * @version 1.0,  &nbsp; 15-APR-2008
 * @since SDK1.5
 */

import it.csi.mdp.core.business.paymentmanager.Payment;

public interface PaymentHome
   extends javax.ejb.EJBHome
{
   public static final String COMP_NAME="java:comp/env/ejb/PaymentManager";
   public static final String JNDI_NAME="ejb/PaymentManager";

   public Payment create()
      throws javax.ejb.CreateException,java.rmi.RemoteException;

}
