/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.operations.riferimentocontabile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.business.dispatcher.Operation;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationDispatchingContext;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationHandler;
import it.csi.epay.epaypacatalogsrv.business.service.PropagazioneService;
import it.csi.epay.epaypacatalogsrv.business.service.RiferimentoContabileService;
import it.csi.epay.epaypacatalogsrv.business.util.EntityUtils;
import it.csi.epay.epaypacatalogsrv.business.util.SecurityUtils;
import it.csi.epay.epaypacatalogsrv.dto.EsitoPropagazioneDTO;
import it.csi.epay.epaypacatalogsrv.dto.enums.AzioneDaPropagare;
import it.csi.epay.epaypacatalogsrv.dto.enums.EsitoPropagazione;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.ChiudiRiferimentoContabileInput;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.ChiudiRiferimentoContabileOutput;
import it.csi.epay.epaypacatalogsrv.exception.BadRequestException;
import it.csi.epay.epaypacatalogsrv.exception.NotFoundException;
import it.csi.epay.epaypacatalogsrv.exception.Wso2BroadcastingFailedException;
import it.csi.epay.epaypacatalogsrv.model.RiferimentoContabile;
import it.csi.epay.epaypacatalogsrv.repository.RiferimentoContabileRepository;
import it.csi.epay.epaypacatalogsrv.repository.StatoAggiornamentoRepository;
import it.csi.epay.epaypacatalogsrv.vo.Constants;


@Operation ( consumes = ChiudiRiferimentoContabileInput.class, produces = ChiudiRiferimentoContabileOutput.class )
@Component
public class ChiudiRiferimentoContabileOperation implements OperationHandler<ChiudiRiferimentoContabileInput, ChiudiRiferimentoContabileOutput> {

    @Autowired
    private RiferimentoContabileRepository repository;

    @Autowired
    private StatoAggiornamentoRepository statoRepository;

    @Autowired
    private PropagazioneService propagazioneService;

    @Autowired
    private RiferimentoContabileService riferimentoContabileService;

    @Override
    public void preValidateInput ( ChiudiRiferimentoContabileInput input,
        OperationDispatchingContext<ChiudiRiferimentoContabileInput, ChiudiRiferimentoContabileOutput> context ) {

        if ( input.getId () == null ) {
            throw new BadRequestException ( Constants.MESSAGES.FIELD_REQUIRED, "ID" );
        }
        if ( input.getId () < 1L ) {
            throw new BadRequestException ( Constants.MESSAGES.INVALID_FIELD, "ID" );
        }
    }

    @Override
    public void authorize ( ChiudiRiferimentoContabileInput input,
        OperationDispatchingContext<ChiudiRiferimentoContabileInput, ChiudiRiferimentoContabileOutput> context ) {

        SecurityUtils.assertUseCase ( Constants.USE_CASES.MODIFICA_RIFERIMENTO_CONTABILE );
        SecurityUtils.assertScope ( Constants.SCOPES.CONFIGURATORE );
    }

    @Override
    @Transactional
    public void validateInput ( ChiudiRiferimentoContabileInput input,
        OperationDispatchingContext<ChiudiRiferimentoContabileInput, ChiudiRiferimentoContabileOutput> context ) {

        RiferimentoContabile entity = repository.findOne ( input.getId () );
        if ( entity == null ) {
            throw new NotFoundException ( Constants.MESSAGES.NOT_FOUND );
        }

        if ( input.getDataFineValidita () == null ) {
            throw new BadRequestException ( Constants.MESSAGES.FIELD_REQUIRED, "data fine validita'" );
        }

        if ( EntityUtils.isTrue ( entity.getFlagAnnullato () ) ) {
            throw new BadRequestException ( Constants.MESSAGES.RIFERIMENTO_CONTABILE_NON_IN_VITA );
        }

        if ( !EntityUtils.isRiferimentoInVita ( entity ) ) {
            throw new BadRequestException ( Constants.MESSAGES.RIFERIMENTO_CONTABILE_NON_IN_VITA );
        }
    }

    @Override
    @Transactional
    public ChiudiRiferimentoContabileOutput execute ( ChiudiRiferimentoContabileInput input,
        OperationDispatchingContext<ChiudiRiferimentoContabileInput, ChiudiRiferimentoContabileOutput> context ) {

        RiferimentoContabile corrente = repository.findOne ( input.getId () );

        // l'utente deve avere visibilita' del codice
        SecurityUtils.assertVisibilitaSuCodiceVersamento ( corrente.getCodiceVersamento () );

        if ( input.getDataFineValidita ().getTime () < corrente.getDataInizioValidita ().getTime () ) {
            throw new BadRequestException ( Constants.MESSAGES.RIFERIMENTO_CONTABILE_INVALID_DATE_SEQUENCE );
        }

        // salvo storico modifica
        riferimentoContabileService.salvaVoceStoricoPerModifica ( corrente );

        EntityUtils.populateEditFields ( corrente );
        corrente.setDataFineValidita ( input.getDataFineValidita () );
        corrente.setStatoAggiornamento ( statoRepository.findOneByCodice ( StatoAggiornamentoRepository.CODICE_DEFAULT ) );
        corrente.setDescrizioneErroreAggiornamento ( "In attesa di propagazione" );
        repository.save ( corrente );

        EsitoPropagazioneDTO risultatoPropagazione = propagazioneService.propagaRiferimentoContabile (
            corrente,
            AzioneDaPropagare.MODIFICA );

        if ( risultatoPropagazione.getEsito () != EsitoPropagazione.OK && risultatoPropagazione.getEsito () != EsitoPropagazione.NONE ) {
            throw new Wso2BroadcastingFailedException ( Constants.MESSAGES.WSO2_BROADCASTING_FAILED,
                risultatoPropagazione.getMessaggio () );
        }

        corrente.setStatoAggiornamento ( statoRepository.findOneByCodice ( risultatoPropagazione.getEsito ().name () ) );
        corrente.setDescrizioneErroreAggiornamento ( risultatoPropagazione.getMessaggio () );
        repository.save ( corrente );

        return ChiudiRiferimentoContabileOutput.ok ();
    }

}
