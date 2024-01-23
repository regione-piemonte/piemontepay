/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.adapters.business;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.EJBHome;
import javax.ejb.EJBObject;
import javax.ejb.Handle;
import javax.ejb.RemoveException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import it.csi.mdp.adapters.business.BankAdapter;
import it.csi.mdp.core.business.dto.*;
import it.csi.mdp.core.business.dto.multicarrello.RPTData;

public class BankAdapterImpl implements SessionBean, BankAdapter 
{

	private static final long serialVersionUID = -7900950568992275371L;

	public void ejbActivate()
    throws EJBException, RemoteException
  {}

  public void ejbPassivate()
    throws EJBException, RemoteException
  {}
  
  public void ejbCreate() throws javax.ejb.CreateException
  {
  }

  public void ejbRemove()
    throws EJBException, RemoteException
  {
	  
  }
  public void setSessionContext(SessionContext arg0) throws EJBException,
	RemoteException
{
// TODO Auto-generated method stub

}
  
  public EJBHome getEJBHome()
  throws RemoteException
  {
    return null;
  }

public Handle getHandle()
  throws RemoteException
{
  return null;
}
public void remove()
throws RemoteException, RemoveException
{}
	
public Object getPrimaryKey() throws RemoteException
{
	// TODO Auto-generated method stub
	return null;
}

public boolean isIdentical(EJBObject arg0) throws RemoteException
{
	// TODO Auto-generated method stub
	return false;
}

public Transazione initTransazione (Transazione t) throws RemoteException
{
	return null;
}
public String startTransazione (Transazione t,Gatewaydetail gd,Applicationcustomfields [] acf) throws RemoteException
{
	return null;	
}	
public Transazione getCommissioni(Transazione t, ApplicationDetail ad) throws RemoteException
{
	return null;
}
public Transazione getStatoTransazione(Transazione t,Gatewaydetail gd,Applicationcustomfields [] acf, long oldstate) throws RemoteException
{
	return t;
}

public Transazione startTransazione (Transazione t,Gatewaydetail gd,Applicationcustomfields [] acf, ApplicationDetail ad) throws RemoteException
{
	return null;
}

public Transazione startTransazione (Transazione t,Gatewaydetail gd,Applicationcustomfields [] acf, ApplicationDetail ad, TransazioneExtraAttribute[] tea) throws RemoteException, BusinessException, SystemException
{
	return startTransazione(t, gd, acf, ad);
}

public boolean isAlive () throws RemoteException
{
	return true;
}

public Transazione confirmPayment(Transazione t ,Applicationcustomfields [] acf, Gatewaydetail gd) throws RemoteException
{
	throw new RemoteException ("Operazione di conferma pagamento differita non abilitata per questo gateway.");
}

public Transazione refundPayment (Transazione t,Applicationcustomfields [] acf, Gatewaydetail gd) throws RemoteException
{
	throw new RemoteException ("Operazione di storno non abilitata per questo gateway.");
	//return t;
}

public Transazione startTransazione(Transazione t, Gatewaydetail gd,
		Applicationcustomfields[] acf, ApplicationDetail ad,
		TransazioneExtraAttribute[] tea,
		List<RPTData> elencoRPT)
		throws RemoteException, BusinessException, SystemException {
	return startTransazione(t, gd, acf, ad, tea);
}


}
