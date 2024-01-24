/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.api.impl;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import it.csi.epay.epaypacatalogsrv.api.EpaypacatalogsrvApi;
import it.csi.epay.epaypacatalogsrv.api.util.SpringSupportedResource;
import it.csi.epay.epaypacatalogsrv.business.service.GetProfilazioneUtenteCorrenteService;
import it.csi.epay.epaypacatalogsrv.business.service.GetProfilazioneUtenteService;
import it.csi.epay.epaypacatalogsrv.business.service.TestResourcesService;
import it.csi.epay.epaypacatalogsrv.dto.autorizzazione.GetProfilazioneUtenteCorrenteInput;
import it.csi.epay.epaypacatalogsrv.dto.autorizzazione.GetProfilazioneUtenteCorrenteOutput;
import it.csi.epay.epaypacatalogsrv.dto.autorizzazione.GetProfilazioneUtenteInput;
import it.csi.epay.epaypacatalogsrv.dto.autorizzazione.GetProfilazioneUtenteOutput;
import it.csi.epay.epaypacatalogsrv.dto.test.TestResourcesInput;
import it.csi.epay.epaypacatalogsrv.dto.test.TestResourcesOutput;
import it.csi.epay.epaypacatalogsrv.vo.Constants;


@SpringSupportedResource
public class EpaypacatalogsrvApiImpl implements EpaypacatalogsrvApi {

	 @Autowired
	    private GetProfilazioneUtenteCorrenteService getProfilazioneUtenteCorrenteService;
	 
	 @Autowired
	    private GetProfilazioneUtenteService getProfilazioneUtenteService;
	 
	 @Autowired
	    private TestResourcesService testResourcesService;
	 
	 
	  

    @Override
    public Response testResources (TestResourcesInput input ){

    	TestResourcesOutput output = testResourcesService.testResources(input);
        return Response
            .status ( output != null && Constants.RESULT_CODES.OK.equals ( output.getCodiceEsito () ) ? 200
                            : Constants.RESULT_CODES.BAD_REQUEST.equals ( output.getCodiceEsito () ) ? 400 : 500 )
            .entity ( output ).build ();
    }
    
    public Response getProfilazioneUtenteCorrente (GetProfilazioneUtenteCorrenteInput input ) {
    	
    	GetProfilazioneUtenteCorrenteOutput output = getProfilazioneUtenteCorrenteService.getProfilazioneUtenteCorrente(input);
        return Response
            .status ( output != null && Constants.RESULT_CODES.OK.equals ( output.getCodiceEsito () ) ? 200
                            : output != null && output.getCodiceStato() != null? output.getCodiceStato():500 )
            .entity ( output ).build ();
    	
    }
    
 public Response getProfilazioneUtente (GetProfilazioneUtenteInput input ) {
    	
    	GetProfilazioneUtenteOutput output = getProfilazioneUtenteService.getProfilazioneUtente(input);
        return Response
            .status ( output != null && Constants.RESULT_CODES.OK.equals ( output.getCodiceEsito () ) ? 200
                            : output != null && output.getCodiceStato() != null? output.getCodiceStato():500 )
            .entity ( output ).build ();
    	
    }

    

}
