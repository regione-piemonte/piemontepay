/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.operations.autorizzazione;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.business.dispatcher.Operation;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationDispatchingContext;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationHandler;
import it.csi.epay.epaypacatalogsrv.dto.autorizzazione.GetEntiAssociatiInput;
import it.csi.epay.epaypacatalogsrv.dto.autorizzazione.GetEntiAssociatiOutput;
import it.csi.epay.epaypacatalogsrv.dto.autorizzazione.GetEntiAssociatiOutputDto;
import it.csi.epay.epaypacatalogsrv.exception.BadRequestException;
import it.csi.epay.epaypacatalogsrv.exception.NotFoundException;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteEnte;
import it.csi.epay.epaypacatalogsrv.model.Ente;
import it.csi.epay.epaypacatalogsrv.model.Utente;
import it.csi.epay.epaypacatalogsrv.repository.EnteRepository;
import it.csi.epay.epaypacatalogsrv.repository.UtenteRepository;
import it.csi.epay.epaypacatalogsrv.vo.Constants;


@Operation ( consumes = GetEntiAssociatiInput.class, produces = GetEntiAssociatiOutput.class, skipAuthentication = true )
@Component
public class GetEntiAssociatiOperation implements OperationHandler<GetEntiAssociatiInput, GetEntiAssociatiOutput> {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private EnteRepository enteRepository;

    @Override
    public void authorize ( GetEntiAssociatiInput input,
        OperationDispatchingContext<GetEntiAssociatiInput, GetEntiAssociatiOutput> context ) {

        // SecurityUtils.assertScope ( Constants.SCOPES.PROFILAZIONE_UTENTE );
    }

    @Override
    public void validateInput ( GetEntiAssociatiInput input,
        OperationDispatchingContext<GetEntiAssociatiInput, GetEntiAssociatiOutput> context ) {

        if ( StringUtils.isBlank ( input.getCodiceFiscaleUtente () ) ) {
            throw new BadRequestException ( Constants.MESSAGES.FIELD_REQUIRED, "codiceFiscaleUtente" );
        }

    }

    @Override
    @Transactional
    public GetEntiAssociatiOutput execute ( GetEntiAssociatiInput input,
        OperationDispatchingContext<GetEntiAssociatiInput, GetEntiAssociatiOutput> context ) {

        GetEntiAssociatiOutput output = GetEntiAssociatiOutput.ok ( GetEntiAssociatiOutput.class );

        output.setEntiAssociati ( new ArrayList<> () );

        Utente utente = utenteRepository.findOneByCodiceFiscale ( input.getCodiceFiscaleUtente () );

        if ( utente == null ) {
            throw new NotFoundException ( Constants.MESSAGES.USER_NOT_FOUND );
        }

        //EPAY-80
        List<Ente> enti = new ArrayList<Ente> ();
        if ( null != utente.getAssociazioneUtenteEnte () ) {
            for ( AssociazioneUtenteEnte rUtenteEnte: utente.getAssociazioneUtenteEnte () ) {
                enti.add ( enteRepository.findOneById ( rUtenteEnte.getId ().getIdEnte ().longValue () ) );
            }
        }

        //EPAY-80
        //for ( Ente ente: utente.getEnti () ) {
        for ( Ente ente: enti ) {
            GetEntiAssociatiOutputDto newEnteAssociato = new GetEntiAssociatiOutputDto ();

            newEnteAssociato.setCodiceFiscale ( ente.getCodiceFiscale () );
            newEnteAssociato.setDenominazione ( ente.getDenominazione () );
            newEnteAssociato.setId ( ente.getId () );
            newEnteAssociato.setEmail ( ente.getEmail () );

            output.getEntiAssociati ().add ( newEnteAssociato );
        }

        output.getEntiAssociati ().sort ( new Comparator<GetEntiAssociatiOutputDto> () {

            @Override
            public int compare ( GetEntiAssociatiOutputDto o1, GetEntiAssociatiOutputDto o2 ) {
                return o1.getDenominazione ().compareTo ( o2.getDenominazione () );
            }

        } );

        return output;
    }

}
