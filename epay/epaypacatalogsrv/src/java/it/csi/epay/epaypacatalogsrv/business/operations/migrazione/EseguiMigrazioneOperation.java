/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.operations.migrazione;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.business.dispatcher.Operation;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationDispatchingContext;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationHandler;
import it.csi.epay.epaypacatalogsrv.business.enums.ParametriConfigurazione;
import it.csi.epay.epaypacatalogsrv.business.migrazione.DataMigrationFatalErrorException;
import it.csi.epay.epaypacatalogsrv.business.migrazione.DataMigrationPreviewOnlyException;
import it.csi.epay.epaypacatalogsrv.business.migrazione.MigrationContext;
import it.csi.epay.epaypacatalogsrv.business.service.ConfigurazioneService;
import it.csi.epay.epaypacatalogsrv.business.service.DataMigrationService;
import it.csi.epay.epaypacatalogsrv.business.service.ProfilazioneService;
import it.csi.epay.epaypacatalogsrv.business.util.EntityUtils;
import it.csi.epay.epaypacatalogsrv.dto.migrazione.EseguiMigrazioneInput;
import it.csi.epay.epaypacatalogsrv.dto.migrazione.EseguiMigrazioneLogOutputDto;
import it.csi.epay.epaypacatalogsrv.dto.migrazione.EseguiMigrazioneOutput;
import it.csi.epay.epaypacatalogsrv.exception.NotAuthorizedException;
import it.csi.epay.epaypacatalogsrv.model.LogMigrazione;
import it.csi.epay.epaypacatalogsrv.vo.ConfigurazioneVO;
import it.csi.epay.epaypacatalogsrv.vo.Constants;


@Operation ( consumes = EseguiMigrazioneInput.class, produces = EseguiMigrazioneOutput.class, skipAuthentication = true )
@Component
public class EseguiMigrazioneOperation implements OperationHandler<EseguiMigrazioneInput, EseguiMigrazioneOutput> {

    private final Logger logger = LogManager.getLogger ( EseguiMigrazioneOperation.class );

    @Autowired
    private DataMigrationService dataMigrationService;

    @Autowired
    private ConfigurazioneService configurazioneService;

    @Autowired
    private ProfilazioneService profilazioneService;

    @Override
    public void authorize ( EseguiMigrazioneInput input, OperationDispatchingContext<EseguiMigrazioneInput, EseguiMigrazioneOutput> context ) {

        profilazioneService.impersonateIfAnonymous ( Constants.CODICE_UTENTE_MIGRAZIONE );

        boolean anteprima = EntityUtils.isTrue ( input.getAnteprima () );

        if ( !anteprima ) {
            ConfigurazioneVO configValue = configurazioneService.getParametroNoCache (
                ParametriConfigurazione.MIGRATION_ENABLE );
            if ( configValue == null || !configValue.asBoolean ( false ) ) {
                throw new NotAuthorizedException ( "MIGRATION_NOT_ENABLED" );
            }
        }
    }

    @Override
    @Transactional
    public EseguiMigrazioneOutput execute ( EseguiMigrazioneInput input, OperationDispatchingContext<EseguiMigrazioneInput, EseguiMigrazioneOutput> context ) {

        MigrationContext result;

        try {
            result = dataMigrationService.runMigration ( input );
        } catch ( DataMigrationPreviewOnlyException e ) {
            result = e.getContext ();
        } catch ( DataMigrationFatalErrorException e ) {
            logger.error ( "Si e' verificato un errore bloccante durante la migrazione", e );

            EseguiMigrazioneOutput output = EseguiMigrazioneOutput.fail ( e, EseguiMigrazioneOutput.class );

            output.setCodiceEsito ( Constants.RESULT_CODES.BAD_REQUEST );
            output.setDescrizioneEsito ( "Si e' verificato un errore bloccante durante la migrazione: " +
                e.getMessage () != null ? e.getMessage () : "verificare la tabella di log per ulteriori dettagli." );
            output.setCodiceStato ( 400 );

            return output;
        }

        return map ( result );
    }

    private EseguiMigrazioneOutput map ( MigrationContext result ) {

        List<EseguiMigrazioneLogOutputDto> outputLogDto = new ArrayList<> ();
        for ( LogMigrazione log: result.getLog () ) {
            EseguiMigrazioneLogOutputDto mapped = new EseguiMigrazioneLogOutputDto ();
            EntityUtils.copyProperties ( mapped, log );
            outputLogDto.add ( mapped );
        }

        EseguiMigrazioneOutput output = EseguiMigrazioneOutput.ok ();
        output.setLog ( outputLogDto );
        return output;
    }

}
