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
import it.csi.epay.epaypacatalogsrv.business.util.SecurityUtils;
import it.csi.epay.epaypacatalogsrv.dto.utente.GetUtenteAssociazioneCduOutputDto;
import it.csi.epay.epaypacatalogsrv.dto.utente.GetUtenteAssociazioneCodiceVersamentoOutputDto;
import it.csi.epay.epaypacatalogsrv.dto.utente.GetUtenteAssociazioneTematicaOutputDto;
import it.csi.epay.epaypacatalogsrv.dto.utente.GetUtenteInput;
import it.csi.epay.epaypacatalogsrv.dto.utente.GetUtenteOutput;
import it.csi.epay.epaypacatalogsrv.dto.utente.GetUtenteOutputDto;
import it.csi.epay.epaypacatalogsrv.exception.BadRequestException;
import it.csi.epay.epaypacatalogsrv.exception.NotFoundException;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteCdu;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteCodiceVersamento;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteEnte;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteTematica;
import it.csi.epay.epaypacatalogsrv.model.Utente;
import it.csi.epay.epaypacatalogsrv.repository.AssociazioneUtenteCduRepository;
import it.csi.epay.epaypacatalogsrv.repository.AssociazioneUtenteCodiceVersamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.AssociazioneUtenteTematicaRepository;
import it.csi.epay.epaypacatalogsrv.repository.UtenteRepository;
import it.csi.epay.epaypacatalogsrv.vo.Constants;


@Operation ( consumes = GetUtenteInput.class, produces = GetUtenteOutput.class )
@Component
public class GetUtenteOperation implements OperationHandler<GetUtenteInput, GetUtenteOutput> {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private AssociazioneUtenteCduRepository associazioneUtenteCduRepository;

    @Autowired
    private AssociazioneUtenteTematicaRepository associazioneUtenteTematicaRepository;

    @Autowired
    private AssociazioneUtenteCodiceVersamentoRepository associazioneUtenteCodiceVersamentoRepository;

    @Autowired
    private Mapper dozerBeanMapper;
    
    @Autowired
    private AuditService auditService;

    @Override
    public void authorize ( GetUtenteInput input,
        OperationDispatchingContext<GetUtenteInput, GetUtenteOutput> context ) {
        SecurityUtils.assertAnyUseCase (
            Constants.USE_CASES.RICERCA_UTENTE,
            Constants.USE_CASES.AUTORIZZA_FUNZIONE,
            Constants.USE_CASES.AUTORIZZA_CODICE_VERSAMENTO );
        SecurityUtils.assertScope ( Constants.SCOPES.CONFIGURATORE );
    }

    @Override
    public void validateInput ( GetUtenteInput input,
        OperationDispatchingContext<GetUtenteInput, GetUtenteOutput> context ) {
        if ( input.getId () == null || input.getId () < 1L ) {
            throw new BadRequestException ( Constants.MESSAGES.INVALID_FIELD, "id" );
        }
    }

    @Override
    @Transactional
    public GetUtenteOutput execute ( GetUtenteInput input,
        OperationDispatchingContext<GetUtenteInput, GetUtenteOutput> context ) {

    	List<String> target = new ArrayList<String>();
    	target.add("Id utente: "+input.getId());
    	auditService.preExport ( "epaycat_t_utente","", target );
        Utente raw = utenteRepository.findOne ( input.getId () );
        if ( raw == null ) {
            throw new NotFoundException ();
        }

        Integer idEnteCorrente = SecurityUtils.getCurrentIdEnte ().intValue ();

        List<AssociazioneUtenteCdu> associazioniCdu
        = associazioneUtenteCduRepository.findByIdUtenteAndIdEnte ( raw.getId (), idEnteCorrente );

        List<AssociazioneUtenteTematica> associazioniTematiche
        = associazioneUtenteTematicaRepository.findByIdUtenteAndIdEnte ( raw.getId (), idEnteCorrente );

        List<AssociazioneUtenteCodiceVersamento> associazioniCodiciVersamento
        = associazioneUtenteCodiceVersamentoRepository.findByIdUtenteAndIdEnte ( raw.getId (), idEnteCorrente );

        GetUtenteOutputDto mapped = this.map ( raw, associazioniCdu, associazioniTematiche, associazioniCodiciVersamento );
        return GetUtenteOutput.ok ( mapped );
    }

    private GetUtenteOutputDto map ( Utente input, List<AssociazioneUtenteCdu> associazioniCdu, List<AssociazioneUtenteTematica> associazioniTematiche,
        List<AssociazioneUtenteCodiceVersamento> associazioniCodiciVersamento ) {

        GetUtenteOutputDto output = dozerBeanMapper.map ( input, GetUtenteOutputDto.class );

        output.setCdu ( new ArrayList<> () );
        output.setTematiche ( new ArrayList<> () );

        for ( AssociazioneUtenteCdu associazione: associazioniCdu ) {
            GetUtenteAssociazioneCduOutputDto dtoAssociazioneOutput = new GetUtenteAssociazioneCduOutputDto ();
            dtoAssociazioneOutput.setId ( associazione.getCdu ().getId ().longValue () );
            dtoAssociazioneOutput.setCodice ( associazione.getCdu ().getCodice () );
            dtoAssociazioneOutput.setDescrizione ( associazione.getCdu ().getDescrizione () );
            dtoAssociazioneOutput.setCodiceCategoria ( associazione.getCdu ().getCategoria ().getCodice () );
            dtoAssociazioneOutput.setDescrizioneCategoria ( associazione.getCdu ().getCategoria ().getDescrizione () );
            output.getCdu ().add ( dtoAssociazioneOutput );
        }

        for ( AssociazioneUtenteTematica associazioneTematica: associazioniTematiche ) {
            GetUtenteAssociazioneTematicaOutputDto dtoAssociazioneOutput = new GetUtenteAssociazioneTematicaOutputDto ();
            dtoAssociazioneOutput.setId ( associazioneTematica.getTematica ().getId ().longValue () );
            dtoAssociazioneOutput.setCodice ( associazioneTematica.getTematica ().getCodice () );
            dtoAssociazioneOutput.setDescrizione ( associazioneTematica.getTematica ().getDescrizione () );
            dtoAssociazioneOutput.setFlagVisibilitaTotale ( associazioneTematica.getFlagVisibilitaTotale () );

            dtoAssociazioneOutput.setCodiciVersamento ( new ArrayList<> () );

            if ( associazioniCodiciVersamento.size () > 0 ) {
                for ( AssociazioneUtenteCodiceVersamento associazioneCodiceVersamento: associazioniCodiciVersamento ) {
                    if ( !associazioneCodiceVersamento.getCodiceVersamento ().getVoceEntrata ().getTematica ().getCodice ()
                                    .equals ( associazioneTematica.getTematica ().getCodice () ) ) {
                        continue;
                    }
                    GetUtenteAssociazioneCodiceVersamentoOutputDto dtoCodiceVersamento = new GetUtenteAssociazioneCodiceVersamentoOutputDto ();
                    dtoCodiceVersamento.setCodice ( associazioneCodiceVersamento.getCodiceVersamento ().getCodice () );
                    dtoCodiceVersamento.setDescrizione ( associazioneCodiceVersamento.getCodiceVersamento ().getDescrizione () );
                    dtoCodiceVersamento.setId ( associazioneCodiceVersamento.getCodiceVersamento ().getId ().longValue () );
                    dtoAssociazioneOutput.getCodiciVersamento ().add ( dtoCodiceVersamento );
                }
            }

            output.getTematiche ().add ( dtoAssociazioneOutput );
        }

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
