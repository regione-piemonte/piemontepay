/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpmultiiuvsrv.business.mdpmultiiuv.interfaces;

import javax.xml.soap.SOAPException;

import it.csi.mdpnew.mdpmultiiuvsrv.dto.mdpmultiiuv.IuvComplexResponse;
import it.csi.mdpnew.mdpmultiiuvsrv.exception.mdpmultiiuv.ErrorParameterException;
import it.csi.mdpnew.mdpmultiiuvsrv.exception.mdpmultiiuv.MdpMultiIuvSrvException;
import it.csi.mdpnew.mdpmultiiuvsrv.exception.mdpmultiiuv.MissingParameterException;
import it.csi.mdpnew.mdpmultiiuvsrv.interfacecsi.mdpmultiiuv.MdpMultiIuvSrv;

public interface MdpMultiIuvBusinessInterface extends MdpMultiIuvSrv {

	public IuvComplexResponse getIuvComplex(String idApplication, String codicePagamento, Integer numeroRichiesto, String timeStamp, String mac)
			throws SOAPException, MdpMultiIuvSrvException, MissingParameterException, ErrorParameterException;
}
