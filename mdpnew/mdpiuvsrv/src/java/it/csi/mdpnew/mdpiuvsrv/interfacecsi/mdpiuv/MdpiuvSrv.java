/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpiuvsrv.interfacecsi.mdpiuv;

import it.csi.mdpnew.mdpiuvsrv.dto.mdpiuv.*;
import it.csi.mdpnew.mdpiuvsrv.exception.mdpiuv.*;

 
public interface MdpiuvSrv{
	
  
  public String getDataSistema (it.csi.mdpnew.mdpiuvsrv.dto.mdpiuv.ProvaEntita p1)
  throws it.csi.csi.wrapper.CSIException, it.csi.csi.wrapper.SystemException, it.csi.csi.wrapper.UnrecoverableException 
        ,it.csi.mdpnew.mdpiuvsrv.exception.mdpiuv.MdpIuvSrvException;

	
	public boolean testResources() throws  it.csi.csi.wrapper.CSIException;
	
	
	public it.csi.coopdiag.api.InvocationNode selfCheck( it.csi.coopdiag.api.CalledResource[] alreadyChecked ) throws it.csi.csi.wrapper.CSIException;
	
	public boolean hasSelfCheck() throws it.csi.csi.wrapper.CSIException;
	
}
	
