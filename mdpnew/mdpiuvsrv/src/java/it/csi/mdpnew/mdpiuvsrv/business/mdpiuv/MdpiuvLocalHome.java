/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpiuvsrv.business.mdpiuv;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;

public interface MdpiuvLocalHome extends EJBLocalHome {
	public MdpiuvLocal create() throws CreateException;
}
