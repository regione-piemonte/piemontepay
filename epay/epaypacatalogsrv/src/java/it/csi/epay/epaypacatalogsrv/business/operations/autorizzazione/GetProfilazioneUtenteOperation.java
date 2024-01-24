/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.operations.autorizzazione;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.business.dispatcher.Operation;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationDispatchingContext;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationHandler;
import it.csi.epay.epaypacatalogsrv.business.service.ProfilazioneService;
import it.csi.epay.epaypacatalogsrv.dto.CallerInputDto;
import it.csi.epay.epaypacatalogsrv.dto.autorizzazione.GetProfilazioneUtenteInput;
import it.csi.epay.epaypacatalogsrv.dto.autorizzazione.GetProfilazioneUtenteOutput;
import it.csi.epay.epaypacatalogsrv.exception.NotAuthorizedException;
import it.csi.epay.epaypacatalogsrv.vo.Constants;
import it.csi.epay.epaypacatalogsrv.vo.security.PrincipalVO;


@Operation ( consumes = GetProfilazioneUtenteInput.class, produces = GetProfilazioneUtenteOutput.class, skipAuthentication = true )
@Component
public class GetProfilazioneUtenteOperation implements OperationHandler<GetProfilazioneUtenteInput, GetProfilazioneUtenteOutput> {

    @Autowired
    private ProfilazioneService profilazioneService;
    //
    //    @Autowired
    //    private AuditService auditService;

    @Autowired
    private Mapper dozerBeanMapper;

    @Override
    @Transactional
    public GetProfilazioneUtenteOutput execute ( GetProfilazioneUtenteInput input,
        OperationDispatchingContext<GetProfilazioneUtenteInput, GetProfilazioneUtenteOutput> context ) {

        CallerInputDto caller = input.getCaller ();
        PrincipalVO principal = profilazioneService.getPrincipal ( caller );

        if ( principal != null ) {
            if ( !principal.hasScope ( Constants.SCOPES.PROFILAZIONE_UTENTE ) ) {
                throw new NotAuthorizedException ();
            }
        }

        GetProfilazioneUtenteOutput output = GetProfilazioneUtenteOutput.ok ( GetProfilazioneUtenteOutput.class );

        GetProfilazioneUtenteOutput outputMapped = dozerBeanMapper.map ( principal, GetProfilazioneUtenteOutput.class );

        output.setUtente ( outputMapped.getUtente () );
        output.setEnte ( outputMapped.getEnte () );
        output.setTematiche ( outputMapped.getTematiche () );

        // auditService.postAccesso ( input.getCaller (), principal );

        return output;
    }

    @Override
    @Transactional
    public void onException ( GetProfilazioneUtenteInput input,
        OperationDispatchingContext<GetProfilazioneUtenteInput, GetProfilazioneUtenteOutput> context, Exception e ) {

        // auditService.postAccessoNegato ( input.getCaller (), e );

    }
}
