/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpiuvsrv.business.mdpiuv;

import java.rmi.*;
import javax.ejb.*;

public interface MdpiuvHome extends EJBHome{
	public MdpiuvRemote create() throws RemoteException, CreateException;
}
