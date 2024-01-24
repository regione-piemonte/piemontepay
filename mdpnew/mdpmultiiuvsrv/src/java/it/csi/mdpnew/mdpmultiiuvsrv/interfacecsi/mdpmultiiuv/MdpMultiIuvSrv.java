/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpmultiiuvsrv.interfacecsi.mdpmultiiuv;

import it.csi.coopdiag.api.CalledResource;
import it.csi.coopdiag.api.InvocationNode;
import it.csi.csi.wrapper.CSIException;

public interface MdpMultiIuvSrv {

	public boolean testResources() throws it.csi.csi.wrapper.CSIException;

	public InvocationNode selfCheck(CalledResource[] alreadyChecked) throws CSIException;

	public boolean hasSelfCheck() throws it.csi.csi.wrapper.CSIException;

}
