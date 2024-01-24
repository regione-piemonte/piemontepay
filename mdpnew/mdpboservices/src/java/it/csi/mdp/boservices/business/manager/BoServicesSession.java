/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package  it.csi.mdp.boservices.business.manager;

import java.rmi.RemoteException;

import it.csi.mdp.boservices.business.manager.BoServicesBean;

public class BoServicesSession
   extends BoServicesBean
   implements javax.ejb.SessionBean
{
   public void ejbActivate() throws javax.ejb.EJBException, java.rmi.RemoteException
   {
      super.ejbActivate();
   }

   public void ejbPassivate() throws javax.ejb.EJBException, java.rmi.RemoteException
   {
      super.ejbPassivate();
   }

   public void setSessionContext(javax.ejb.SessionContext ctx) throws javax.ejb.EJBException, RemoteException
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
