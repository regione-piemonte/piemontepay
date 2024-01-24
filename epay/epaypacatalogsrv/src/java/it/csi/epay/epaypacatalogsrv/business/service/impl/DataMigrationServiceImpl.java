/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.business.migrazione.DataMigrationPreviewOnlyException;
import it.csi.epay.epaypacatalogsrv.business.migrazione.MigrationContext;
import it.csi.epay.epaypacatalogsrv.business.service.DataMigrationExecutorService;
import it.csi.epay.epaypacatalogsrv.business.service.DataMigrationService;
import it.csi.epay.epaypacatalogsrv.business.util.EntityUtils;
import it.csi.epay.epaypacatalogsrv.dto.migrazione.EseguiMigrazioneInput;


/**
 *
 */
@Service
public class DataMigrationServiceImpl implements DataMigrationService {

    @Autowired
    private DataMigrationExecutorService dataMigrationExecutorService;

    /*
     * (non-Javadoc)
     * @see it.csi.epay.epaypacatalogsrv.business.service.impl.DataMigrationService#runMigration()
     */
    @Override
    @Transactional ( propagation = Propagation.REQUIRES_NEW )
    public MigrationContext runMigration ( EseguiMigrazioneInput input ) {
        boolean previewOnly = EntityUtils.isTrue ( input.getAnteprima () );

        MigrationContext context = new MigrationContext ( input );

        context.info ( "avvio della procedura di migrazione alle ore " + context.getStartTime () );

        try {
            if ( EntityUtils.isTrue ( input.getEliminaLogPrecedenti () ) ) {
                context.debug ( "elimino i log precedenti" );
                dataMigrationExecutorService.eliminaLog ( context );
            }

            if ( EntityUtils.isTrue ( input.getEliminaGiaMigrati () ) ) {
                context.debug ( "elimino gli oggetti migrati" );
                dataMigrationExecutorService.eliminaOggettiMigrati ( context );
            }

            if ( EntityUtils.isTrue ( input.getEseguiMigrazione () ) ) {
                context.debug ( "migro i nuovi oggetti" );
                dataMigrationExecutorService.eseguiMigrazione ( context );
            }

            context.success ();

            if ( previewOnly ) {
                context.debug ( "migrazione in modalita' anteprima, quindi verra' forzato il rollback" );
                throw new DataMigrationPreviewOnlyException ( context, "migrazione dati in modalita' anteprima" );
            } else {
                context.info ( "migrazione completata con successo" );
            }

        } catch ( DataMigrationPreviewOnlyException e ) {
            throw e;

        } catch ( Throwable e ) {
            context.error ( "errore durante la procedura di migrazione " + context.getMigrationUUID (), e );
            context.fail ( e );
            throw e;

        } finally {
            context.debug ( "salvo i log su tabella" );
            dataMigrationExecutorService.salvaLog ( context );
        }

        return context;
    }

}
