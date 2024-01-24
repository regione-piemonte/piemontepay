/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.operations.utente;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.business.dispatcher.Operation;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationDispatchingContext;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationHandler;
import it.csi.epay.epaypacatalogsrv.business.service.AuditService;
import it.csi.epay.epaypacatalogsrv.business.util.EntityUtils;
import it.csi.epay.epaypacatalogsrv.business.util.SecurityUtils;
import it.csi.epay.epaypacatalogsrv.dto.utente.RicercaUtenteInput;
import it.csi.epay.epaypacatalogsrv.dto.utente.RicercaUtenteOutput;
import it.csi.epay.epaypacatalogsrv.dto.utente.RicercaUtenteOutputDto;
import it.csi.epay.epaypacatalogsrv.exception.BadRequestException;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteEnte;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteEntePK;
import it.csi.epay.epaypacatalogsrv.model.Ente;
import it.csi.epay.epaypacatalogsrv.model.Utente;
import it.csi.epay.epaypacatalogsrv.repository.AssociazioneUtenteEnteRepository;
import it.csi.epay.epaypacatalogsrv.repository.EnteRepository;
import it.csi.epay.epaypacatalogsrv.repository.UtenteRepository;
import it.csi.epay.epaypacatalogsrv.vo.Constants;


@Operation ( consumes = RicercaUtenteInput.class, produces = RicercaUtenteOutput.class )
@Component
public class RicercaUtenteOperation implements OperationHandler<RicercaUtenteInput, RicercaUtenteOutput> {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private EnteRepository enteRepository;

    @Autowired
    private Mapper dozerBeanMapper;

    @Autowired
    private AssociazioneUtenteEnteRepository associazioneUtenteEnteRepository;
    
    @Autowired
    private AuditService auditService;

    @Override
    public void authorize ( RicercaUtenteInput input,
        OperationDispatchingContext<RicercaUtenteInput, RicercaUtenteOutput> context ) {
        SecurityUtils.assertAnyUseCase (
            Constants.USE_CASES.RICERCA_UTENTE,
            Constants.USE_CASES.AUTORIZZA_FUNZIONE,
            Constants.USE_CASES.AUTORIZZA_CODICE_VERSAMENTO );

        SecurityUtils.assertScope ( Constants.SCOPES.CONFIGURATORE );

        SecurityUtils.assertAmministratoreEnteCorrente ();
    }

    @Override
    public void validateInput ( RicercaUtenteInput input,
        OperationDispatchingContext<RicercaUtenteInput, RicercaUtenteOutput> context ) {
        if ( EntityUtils.isEmpty ( input ) ) {
            throw new BadRequestException ();
        }

        if ( input.getIdCodiceVersamento () != null && input.getIdCodiceVersamento () < 1L ) {
            throw new BadRequestException ( Constants.MESSAGES.INVALID_FIELD, "id codice versamento" );
        }
    }

    @Override
    @Transactional
    public RicercaUtenteOutput execute ( RicercaUtenteInput input, OperationDispatchingContext<RicercaUtenteInput, RicercaUtenteOutput> context ) {

        Long idEnteCorrente = SecurityUtils.getCurrentIdEnte ();

        auditService.preExport ( "epaycat_t_utente", "", input.getAudit () );
        List<Utente> records = utenteRepository.ricerca ( input, idEnteCorrente );

        //EPAY-80
        List<AssociazioneUtenteEnte> assUEAdmins = associazioneUtenteEnteRepository.findByIdEnteAndFlagAdmin ( idEnteCorrente.intValue (), true );
        if ( null != assUEAdmins ) {
            for ( AssociazioneUtenteEnte ass: assUEAdmins ) {

                Utente admin = utenteRepository.findOne ( ass.getId ().getIdUtente () );
                boolean added = false;
                for ( Utente record: records ) {
                    if ( record.getCodiceFiscale ().equalsIgnoreCase ( admin.getCodiceFiscale () ) ) {
                        added = true;
                        break;
                    }
                }
                if ( !added )
                    records.add ( admin );

            }
        }

        //EPAY-80
        //        Utente admin = getAdmin(idEnteCorrente) ;
        //
        //        if(admin != null) {
        //            boolean added = false;
        //            for(Utente record:records) {
        //                if(record.getCodiceFiscale ().equalsIgnoreCase ( admin.getCodiceFiscale () )) {
        //                    added = true;
        //                    break;
        //                }
        //            }
        //            if(!added) records.add (admin);
        //        }

        ArrayList<RicercaUtenteOutputDto> risultati = new ArrayList<> ();

        for ( Utente record: records ) {

            RicercaUtenteOutputDto item = map ( record );

            //EPAY-80
            //item.setAdmin ( isAdmin(idEnteCorrente,item.getId ()) );
            item.setAdmin ( isAdminNew ( idEnteCorrente, item.getId () ) );

            risultati.add ( item );
        }

        // build output
        return RicercaUtenteOutput.ok ( risultati );
    }

//    private Utente getAdmin(Long idEnte) {
//        Ente ente = enteRepository.findOneById ( idEnte );
//
//        return ente.getUtenteAmministratore ();
//    }

//    private Boolean isAdmin(Long idEnte,Long idUtente) {
//
//        Ente ente = enteRepository.findOneById(idEnte);
//
//        Utente admin = (ente.getUtenteAmministratore ());
//
//        return (admin != null && admin.getId ().equals ( idUtente ));
//    }

    private Boolean isAdminNew ( Long idEnte, Long idUtente ) {
        AssociazioneUtenteEntePK rUtenteEnte = new AssociazioneUtenteEntePK ();
        rUtenteEnte.setIdEnte ( idEnte.intValue () );
        rUtenteEnte.setIdUtente ( idUtente );
        AssociazioneUtenteEnte utente = associazioneUtenteEnteRepository.findOne ( rUtenteEnte );
        if ( null != utente && utente.getFlagAdmin () ) {
            return true;
        }
        return false;

    }

    private RicercaUtenteOutputDto map ( Utente input ) {
        RicercaUtenteOutputDto output = dozerBeanMapper.map ( input, RicercaUtenteOutputDto.class );
        //EPAY-80
        AssociazioneUtenteEnte ass = null;
        if ( null != input.getAssociazioneUtenteEnte () ) {
            for ( AssociazioneUtenteEnte aue: input.getAssociazioneUtenteEnte () ) {
                if ( output.getId ().equals ( aue.getId ().getIdUtente () )
                                && SecurityUtils.getCurrentIdEnte ().equals ( new Long ( aue.getId ().getIdEnte () ) ) ) {
                    ass = aue;
                    break;
                }
            }
        }
        if ( null != ass && null != ass.getId () ) {
            output.setDataInizioValidita ( ass.getDataInizioValidita () );
            output.setDataFineValidita ( ass.getDataFineValidita () );
            output.setEmail ( ass.getEmail () );
        }
        return output;
    }

}
