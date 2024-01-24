/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpcoopapplicativasrv.business.mdpcoopapplicativa;

import java.rmi.*;
import javax.ejb.*;

public interface MdpcoopapplicativaHome extends EJBHome{
	public MdpcoopapplicativaRemote create() throws RemoteException, CreateException;
}
