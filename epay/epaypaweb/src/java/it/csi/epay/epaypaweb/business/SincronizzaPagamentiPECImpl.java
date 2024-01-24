/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */



package it.csi.epay.epaypaweb.business;

import it.csi.epay.epaypaweb.business.manager.SincronizzaPagamentiManager;
import it.csi.epay.epaypaweb.facade.EPaypaFacadeBase;
import it.csi.epay.epaypaweb.facade.sincronizzapagamentipec.dto.*;
import it.csi.epay.epaypaweb.facade.sincronizzapagamentipec.service.SincronizzaPagamentiPEC;
import it.csi.epay.epaypaweb.util.CostantiErrori;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.jws.WebService;

@Stateless
@WebService ( name = "SincronizzaPagamentiPEC", serviceName = "SincronizzaPagamentiPEC", portName = "SincronizzaPagamentiPEC",
                targetNamespace = "http://www.csi.it/epay/epaypa/sincronizzapagamentipec" )
@TransactionManagement(TransactionManagementType.CONTAINER)
public class SincronizzaPagamentiPECImpl extends EPaypaFacadeBase implements SincronizzaPagamentiPEC {

    private final String CLASS_NAME = "SincronizzaPagamentiPECImpl";

    @Inject
    private SincronizzaPagamentiManager sincronizzaPagamentiManager;

    @Override
    public TestResourcesResponse testResources ( TestResourcesRequest parameters ) {
        try {
            TestResourcesResponse response = new TestResourcesResponse ();
            ResponseType responseType = new ResponseType();
            ResultType result = new ResultType();
            result.setCodice ( CostantiErrori.WS_ESITO_OK_DEFAULT );
            result.setMessaggio ( "Operazione completata correttamente" );
            responseType.setResult ( result );
            response.setEsito ( responseType );
            return response;
        } catch (java.lang.Exception ex) {
            log.error ( "Errore nel testResources", ex );
            throw new RuntimeException(ex);
        }
    }

    @Override
    public SincronizzaPagamentiResponse sincronizzaPagamenti ( SincronizzaPagamentiRequest parameters ) {

        String methodName = "sincronizzaPagamentiWSCall";
		log.info ( CLASS_NAME + " " + methodName + " - START" );
        SincronizzaPagamentiResponse output = null;

        try {
            ResponseType responseType = sincronizzaPagamentiManager.sincronizzaPagamenti ( parameters );

            output = new SincronizzaPagamentiResponse ();
            output.setEsito ( responseType );
            return output;

        } catch ( Throwable e ) {
            log.error ( "Errore imprevisto in " + methodName, e );

            if ( output == null ) {
                output = new SincronizzaPagamentiResponse ();
                ResponseType errorRT = new ResponseType ();
                ResultType errorRTT = new ResultType ();
                errorRTT.setCodice ( CostantiErrori.ERRORE_DI_SISTEMA );
                errorRTT.setMessaggio ( "Errore imprevisto in " + methodName + ": " + e.getMessage () );
                errorRT.setResult ( errorRTT );
                output.setEsito ( errorRT );
            }

        } finally {
            log.error ( "result" + output );
			log.info ( CLASS_NAME + " " + methodName + " - STOP" );
        }

        return null;
    }

}
