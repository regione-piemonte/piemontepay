/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpmultiiuvsrv.integration.mdpmultiiuv.dao.interfacce;

import it.csi.mdpnew.mdpmultiiuvsrv.dto.mdpmultiiuv.*;

import javax.xml.soap.SOAPException;
import java.util.List;

public interface MdpDao {

	boolean isPresentIDApplication(String idApplication) throws SOAPException;

	DescrizioneApplicativoDTO isPresentIDEnte(String idApplication) throws SOAPException;

	List<ConfigDTO> findAllConfig() throws SOAPException;

	List<ApplicationcustomfieldsDTO> getApplicationcustomfields(String applicationid, String gatewayid, String sKey) throws SOAPException;

	List<GatewayDTO> getGatewayNodoSPC(String idApplication) throws SOAPException;

	int[] insertMassiveIuvOttici(IuvComplexResponse iuvCplx, String ente_id) throws SOAPException;

	Long getProgressivoIuv() throws SOAPException;

}
