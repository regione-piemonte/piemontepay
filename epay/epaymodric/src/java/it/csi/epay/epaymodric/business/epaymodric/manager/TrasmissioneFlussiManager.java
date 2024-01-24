/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.manager;

import java.util.List;
import java.util.Map;

import it.csi.epay.epaymodric.business.epaymodric.bo.Configurazione;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsTrasmettiFlussiInErrorePagoPA;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsTrasmettiFlussiPagoPA;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsTrasmettiFlussiInErrorePagoPA;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsTrasmettiFlussiPagoPA;
import it.csi.epay.epaymodric.util.wsdl.epayriconciliazioneversamentipsp.ResponseType;

public interface TrasmissioneFlussiManager {

    DTOOutputWsTrasmettiFlussiPagoPA creaOutputTrasmettiFlussiPagoPA ( DTOInputWsTrasmettiFlussiPagoPA input );

    DTOOutputWsTrasmettiFlussiInErrorePagoPA creaOutputTrasmettiFlussiInErrorePagoPA (
        DTOInputWsTrasmettiFlussiInErrorePagoPA input );

    ResponseType trasmettiFlussiPagoPA ( Map<String, Configurazione> configurazioneEnte, String identificativoFlusso );

    ResponseType trasmettiFlussiInErrorePagoPA ( Configurazione conf, List<String> identificativoFlussoList );
    

    it.csi.epay.epaymodric.util.xsd.epayriconciliazioneversamentipsp.ResponseType
        trasmettiFlussiCompletiPagoPA ( Map<String, Configurazione> configurazioneEnte, String identificativoFlusso );


}
