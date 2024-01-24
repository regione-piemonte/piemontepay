/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpmultiiuvsrv.business.mdpmultiiuv;

import java.rmi.*;
import javax.ejb.*;

public interface MdpMultiIuvHome extends EJBHome {

	public MdpMultiIuvRemote create() throws RemoteException, CreateException;
}
