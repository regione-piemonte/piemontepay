/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpcoopapplicativasrv.business.mdpcoopapplicativa;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;

public interface MdpcoopapplicativaLocalHome extends EJBLocalHome {
	public MdpcoopapplicativaLocal create() throws CreateException;
}
