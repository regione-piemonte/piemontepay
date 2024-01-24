/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpmultiiuvsrv.business.mdpmultiiuv;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;

public interface MdpMultiIuvLocalHome extends EJBLocalHome {

	public MdpMultiIuvLocal create() throws CreateException;
}
