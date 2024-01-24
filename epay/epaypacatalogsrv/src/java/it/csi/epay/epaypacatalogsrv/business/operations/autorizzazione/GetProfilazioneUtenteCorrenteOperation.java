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
import it.csi.epay.epaypacatalogsrv.business.service.AuditService;
import it.csi.epay.epaypacatalogsrv.business.service.ProfilazioneService;
import it.csi.epay.epaypacatalogsrv.dto.CallerInputDto;
import it.csi.epay.epaypacatalogsrv.dto.autorizzazione.GetProfilazioneUtenteCorrenteInput;
import it.csi.epay.epaypacatalogsrv.dto.autorizzazione.GetProfilazioneUtenteCorrenteOutput;
import it.csi.epay.epaypacatalogsrv.exception.NotAuthorizedException;
import it.csi.epay.epaypacatalogsrv.vo.Constants;
import it.csi.epay.epaypacatalogsrv.vo.security.PrincipalVO;


@Operation ( consumes = GetProfilazioneUtenteCorrenteInput.class, produces = GetProfilazioneUtenteCorrenteOutput.class, skipAuthentication = true )
@Component
public class GetProfilazioneUtenteCorrenteOperation implements OperationHandler<GetProfilazioneUtenteCorrenteInput, GetProfilazioneUtenteCorrenteOutput> {

    @Autowired
    private ProfilazioneService profilazioneService;

    @Autowired
    private AuditService auditService;

    @Autowired
    private Mapper dozerBeanMapper;

    @Override
    @Transactional
    public GetProfilazioneUtenteCorrenteOutput execute ( GetProfilazioneUtenteCorrenteInput input,
        OperationDispatchingContext<GetProfilazioneUtenteCorrenteInput, GetProfilazioneUtenteCorrenteOutput> context ) {

        CallerInputDto caller = input.getCaller ();
        PrincipalVO principal = profilazioneService.getPrincipal ( caller );

        if ( principal != null ) {
            if ( !principal.hasScope ( Constants.SCOPES.PROFILAZIONE_UTENTE_CORRENTE ) ) {
                throw new NotAuthorizedException ();
            }
        }

        GetProfilazioneUtenteCorrenteOutput output = GetProfilazioneUtenteCorrenteOutput.ok ( GetProfilazioneUtenteCorrenteOutput.class );

        GetProfilazioneUtenteCorrenteOutput outputMapped = dozerBeanMapper.map ( principal, GetProfilazioneUtenteCorrenteOutput.class );

        output.setUtente ( outputMapped.getUtente () );
        output.setEnte ( outputMapped.getEnte () );
        output.setTematiche ( outputMapped.getTematiche () );

        auditService.postAccesso ( input.getCaller (), principal );

        return output;
    }

    @Override
    @Transactional
    public void onException ( GetProfilazioneUtenteCorrenteInput input,
        OperationDispatchingContext<GetProfilazioneUtenteCorrenteInput, GetProfilazioneUtenteCorrenteOutput> context, Exception e ) {

        auditService.postAccessoNegato ( input.getCaller (), e );

    }
}
