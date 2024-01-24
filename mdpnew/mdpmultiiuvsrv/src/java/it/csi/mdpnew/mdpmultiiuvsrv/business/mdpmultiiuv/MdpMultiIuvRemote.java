/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpmultiiuvsrv.business.mdpmultiiuv;

import it.csi.coopdiag.api.CalledResource;
import it.csi.coopdiag.api.InvocationNode;
import it.csi.csi.wrapper.CSIException;

/**
 * Interfaccia remota dell'EJB che implementa il servizio mdpmultiiuv.
 */
public interface MdpMultiIuvRemote extends javax.ejb.EJBObject {

	public boolean testResources() throws it.csi.csi.wrapper.CSIException, java.rmi.RemoteException;

	public InvocationNode selfCheck(CalledResource[] alreadyChecked) throws CSIException, java.rmi.RemoteException;

	public boolean hasSelfCheck() throws it.csi.csi.wrapper.CSIException, java.rmi.RemoteException;

}
