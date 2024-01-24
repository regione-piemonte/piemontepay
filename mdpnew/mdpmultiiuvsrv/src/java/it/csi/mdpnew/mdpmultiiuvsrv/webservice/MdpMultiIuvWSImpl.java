/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpmultiiuvsrv.webservice;

import it.csi.mdpnew.mdpmultiiuvsrv.business.mdpmultiiuv.interfaces.MdpMultiIuvBusinessInterface;
import it.csi.mdpnew.mdpmultiiuvsrv.dto.mdpmultiiuv.IuvComplexResponse;
import it.csi.mdpnew.mdpmultiiuvsrv.exception.mdpmultiiuv.ErrorParameterException;
import it.csi.mdpnew.mdpmultiiuvsrv.exception.mdpmultiiuv.MdpMultiIuvSrvException;
import it.csi.mdpnew.mdpmultiiuvsrv.exception.mdpmultiiuv.MissingParameterException;
import it.csi.mdpnew.mdpmultiiuvsrv.util.MdpMultiIuvConstants;
import org.apache.log4j.Logger;

import javax.jws.WebService;
import javax.xml.soap.SOAPException;

@WebService(endpointInterface = "it.csi.mdpnew.mdpmultiiuvsrv.webservice.MdpMultiIuvWSInterface", targetNamespace = "http://mdpnew.csi.it/mdpmultiiuv/", portName = "MdpMultiIuvWSPort", serviceName = "MdpMultiIuvWSInterface")
public class MdpMultiIuvWSImpl implements MdpMultiIuvWSInterface {

	private static final Logger log = Logger.getLogger(MdpMultiIuvConstants.MDPIUV_ROOT_LOG_CATEGORY + ".ws");
	private static MdpMultiIuvBusinessInterface mdpMultiIuvBusinessInterface;

	public IuvComplexResponse getIuvComplex(String idApplication, String codicePagamento, Integer numeroOccorrenzeIUV, String timeStamp, String mac)
			throws SOAPException, MdpMultiIuvSrvException, MissingParameterException, ErrorParameterException {

		log.debug("[MdpMultiIuvWSImpl:getIUVComplex] BEGIN");
		IuvComplexResponse ret = new IuvComplexResponse();
		ret = mdpMultiIuvBusinessInterface.getIuvComplex(idApplication, codicePagamento, numeroOccorrenzeIUV, timeStamp, mac);
		log.debug("[MdpMultiIuvWSImpl:getIUVComplex] END");

		return ret;

	}

	public MdpMultiIuvBusinessInterface getMdpMultiIuvBusinessInterface() {
		return mdpMultiIuvBusinessInterface;
	}

	public void setMdpMultiIuvBusinessInterface(MdpMultiIuvBusinessInterface mdpMultiIuvBusinessInterface) {
		MdpMultiIuvWSImpl.mdpMultiIuvBusinessInterface = mdpMultiIuvBusinessInterface;
	}

}
