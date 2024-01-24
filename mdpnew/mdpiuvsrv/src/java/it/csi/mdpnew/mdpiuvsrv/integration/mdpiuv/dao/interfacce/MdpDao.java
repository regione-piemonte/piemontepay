/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpiuvsrv.integration.mdpiuv.dao.interfacce;

import java.math.BigInteger;
import java.util.List;

import javax.xml.soap.SOAPException;

import it.csi.mdpnew.mdpiuvsrv.dto.mdpiuv.ApplicationcustomfieldsDTO;
import it.csi.mdpnew.mdpiuvsrv.dto.mdpiuv.DescrizioneApplicativoDTO;
import it.csi.mdpnew.mdpiuvsrv.dto.mdpiuv.IuvAttributeDTO;
import it.csi.mdpnew.mdpiuvsrv.exception.mdpiuv.ErrorParameterException;

public interface MdpDao {

	String dataOdierna();
	
	boolean isPresentIDApplication(String idApplication);
	
	DescrizioneApplicativoDTO isPresentIDEnte(String idApplication);
	
	IuvAttributeDTO getIuvCorrente(String idEnte);
	
	public String getAuxDigit(String idApp) throws ErrorParameterException, SOAPException;
	
    public List<ApplicationcustomfieldsDTO> getApplicationcustomfields(String applicationid, String gatewayid, String sKey) throws SOAPException;

	void insertNewDay(String idEnte);
	
	void updateStoricizza(String idEnte);
	
	void updateIncremento(String idEnte, BigInteger progressivo, String data);
}
