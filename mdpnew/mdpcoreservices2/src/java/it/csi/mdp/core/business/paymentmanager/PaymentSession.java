/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package  it.csi.mdp.core.business.paymentmanager;

import it.csi.mdp.core.business.paymentmanager.PaymentBean;

public class PaymentSession
   extends PaymentBean
   implements javax.ejb.SessionBean
{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1116820076433937619L;

public void ejbActivate() throws javax.ejb.EJBException, java.rmi.RemoteException
   {
      super.ejbActivate();
   }

   public void ejbPassivate() throws javax.ejb.EJBException, java.rmi.RemoteException
   {
      super.ejbPassivate();
   }

   public void setSessionContext(javax.ejb.SessionContext ctx) throws javax.ejb.EJBException
   {
      super.setSessionContext(ctx);
   }

   public void unsetSessionContext()
   {
   }

   public void ejbRemove() throws javax.ejb.EJBException, java.rmi.RemoteException
   {
      super.ejbRemove();
   }

   public void ejbCreate() throws javax.ejb.CreateException
   {
   }
}
