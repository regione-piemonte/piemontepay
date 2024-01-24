/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.business.migrazione.CategoriaCduMigrator;
import it.csi.epay.epaypacatalogsrv.business.migrazione.CduMigrator;
import it.csi.epay.epaypacatalogsrv.business.migrazione.CodiceVersamentoMigrator;
import it.csi.epay.epaypacatalogsrv.business.migrazione.EnteMigrator;
import it.csi.epay.epaypacatalogsrv.business.migrazione.MigrationContext;
import it.csi.epay.epaypacatalogsrv.business.migrazione.RelationshipMigrator;
import it.csi.epay.epaypacatalogsrv.business.migrazione.RiferimentoContabileMigrator;
import it.csi.epay.epaypacatalogsrv.business.migrazione.UtenteMigrator;
import it.csi.epay.epaypacatalogsrv.business.service.DataMigrationExecutorService;
import it.csi.epay.epaypacatalogsrv.business.util.EntityUtils;
import it.csi.epay.epaypacatalogsrv.model.LogMigrazione;
import it.csi.epay.epaypacatalogsrv.repository.LogMigrazioneRepository;
import it.csi.epay.epaypacatalogsrv.vo.migrazione.CodiceEntitaMigrazione;
import it.csi.epay.epaypacatalogsrv.vo.migrazione.TipologiaLogMigrazione;


/**
 *
 */
@Service
public class DataMigrationExecutorServiceImpl implements DataMigrationExecutorService {

    @Autowired
    private EnteMigrator enteMigrator;

    @Autowired
    private CategoriaCduMigrator categoriaCduMigrator;

    @Autowired
    private CduMigrator cduMigrator;

    @Autowired
    private CodiceVersamentoMigrator codiceVersamentoMigrator;

    @Autowired
    private RiferimentoContabileMigrator riferimentoContabileMigrator;

    @Autowired
    private UtenteMigrator utenteMigrator;

    @Autowired
    private RelationshipMigrator relationshipMigrator;

    @Autowired
    private LogMigrazioneRepository logMigrazioneRepository;

    /*
     * (non-Javadoc)
     * @see it.csi.epay.epaypacatalogsrv.business.service.impl.DataMigrationService#runMigration()
     */
    @Override
    @Transactional ( propagation = Propagation.REQUIRED )
    public MigrationContext eseguiMigrazione ( MigrationContext context ) {

        if ( EntityUtils.isTrue ( context.getInput ().getImportaEnti () ) ) {
            try {
                context.debug ( CodiceEntitaMigrazione.ENTE, "esegui migrazione degli enti" );
                enteMigrator.migra ( context );
            } catch ( Exception e ) {
                context.error ( CodiceEntitaMigrazione.ENTE, "errore imprevisto nella fase di migrazione", e );
                throw e;
            }
        }

        if ( EntityUtils.isTrue ( context.getInput ().getImportaCdu () ) ) {
            try {
                context.debug ( CodiceEntitaMigrazione.CATEGORIA_CDU, "esegui migrazione delle categorie CDU" );
                categoriaCduMigrator.migra ( context );
            } catch ( Exception e ) {
                context.error ( CodiceEntitaMigrazione.CATEGORIA_CDU, "errore imprevisto nella fase di migrazione", e );
                throw e;
            }
        }

        if ( EntityUtils.isTrue ( context.getInput ().getImportaCdu () ) ) {
            try {
                context.debug ( CodiceEntitaMigrazione.CDU, "esegui migrazione dei CDU" );
                cduMigrator.migra ( context );
            } catch ( Exception e ) {
                context.error ( CodiceEntitaMigrazione.CDU, "errore imprevisto nella fase di migrazione", e );
                throw e;
            }
        }

        if ( EntityUtils.isTrue ( context.getInput ().getImportaCodiciVersamento () ) ) {
            try {
                context.debug ( CodiceEntitaMigrazione.CODICE_VERSAMENTO, "esegui migrazione dei codici versamento" );
                codiceVersamentoMigrator.migra ( context );
            } catch ( Exception e ) {
                context.error ( CodiceEntitaMigrazione.CODICE_VERSAMENTO, "errore imprevisto nella fase di migrazione", e );
                throw e;
            }
        }

        if ( EntityUtils.isTrue ( context.getInput ().getImportaRiferimentiContabili () ) ) {
            try {
                context.debug ( CodiceEntitaMigrazione.RIFERIMENTO_CONTABILE, "esegui migrazione dei riferimenti contabili" );
                riferimentoContabileMigrator.migra ( context );
            } catch ( Exception e ) {
                context.error ( CodiceEntitaMigrazione.RIFERIMENTO_CONTABILE, "errore imprevisto nella fase di migrazione", e );
                throw e;
            }
        }

        if ( EntityUtils.isTrue ( context.getInput ().getImportaUtenti () ) ) {
            try {
                context.debug ( CodiceEntitaMigrazione.UTENTE, "esegui migrazione degli utenti" );
                utenteMigrator.migra ( context );
            } catch ( Exception e ) {
                context.error ( CodiceEntitaMigrazione.UTENTE, "errore imprevisto nella fase di migrazione", e );
                throw e;
            }
        }

        try {
            context.debug ( CodiceEntitaMigrazione.RELAZIONE, "esegui migrazione delle relazioni fra oggetti" );
            relationshipMigrator.migra ( context );
        } catch ( Exception e ) {
            context.error ( CodiceEntitaMigrazione.RELAZIONE, "errore imprevisto nella fase di migrazione", e );
            throw e;
        }

        return context;
    }

    @Override
    @Transactional ( propagation = Propagation.REQUIRED )
    public MigrationContext eliminaOggettiMigrati ( MigrationContext context ) {

        try {
            context.debug ( CodiceEntitaMigrazione.RELAZIONE, "elimino relazioni fra oggetti migrati" );
            relationshipMigrator.eliminaOggettiMigrati ( context );
        } catch ( Exception e ) {
            context.error ( CodiceEntitaMigrazione.RELAZIONE, "errore imprevisto nella fase di eliminazione", e );
            throw e;
        }

        if ( EntityUtils.isTrue ( context.getInput ().getImportaUtenti () ) ) {
            try {
                context.debug ( CodiceEntitaMigrazione.UTENTE, "elimino utenti migrati" );
                utenteMigrator.eliminaOggettiMigrati ( context );
            } catch ( Exception e ) {
                context.error ( CodiceEntitaMigrazione.UTENTE, "errore imprevisto nella fase di eliminazione", e );
                throw e;
            }
        }

        if ( EntityUtils.isTrue ( context.getInput ().getImportaRiferimentiContabili () ) ) {
            try {
                context.debug ( CodiceEntitaMigrazione.RIFERIMENTO_CONTABILE, "elimino riferimenti contabili migrati" );
                riferimentoContabileMigrator.eliminaOggettiMigrati ( context );
            } catch ( Exception e ) {
                context.error ( CodiceEntitaMigrazione.RIFERIMENTO_CONTABILE, "errore imprevisto nella fase di eliminazione", e );
                throw e;
            }
        }

        if ( EntityUtils.isTrue ( context.getInput ().getImportaCodiciVersamento () ) ) {
            try {
                context.debug ( CodiceEntitaMigrazione.CODICE_VERSAMENTO, "elimino codici versamento migrati" );
                codiceVersamentoMigrator.eliminaOggettiMigrati ( context );
            } catch ( Exception e ) {
                context.error ( CodiceEntitaMigrazione.CODICE_VERSAMENTO, "errore imprevisto nella fase di eliminazione", e );
                throw e;
            }
        }

        if ( EntityUtils.isTrue ( context.getInput ().getImportaCdu () ) ) {
            try {
                context.debug ( CodiceEntitaMigrazione.CDU, "elimino CDU migrati" );
                cduMigrator.eliminaOggettiMigrati ( context );
            } catch ( Exception e ) {
                context.error ( CodiceEntitaMigrazione.CDU, "errore imprevisto nella fase di eliminazione", e );
                throw e;
            }
        }

        if ( EntityUtils.isTrue ( context.getInput ().getImportaCdu () ) ) {
            try {
                context.debug ( CodiceEntitaMigrazione.CATEGORIA_CDU, "elimino categorie CDU migrate" );
                categoriaCduMigrator.eliminaOggettiMigrati ( context );
            } catch ( Exception e ) {
                context.error ( CodiceEntitaMigrazione.CATEGORIA_CDU, "errore imprevisto nella fase di eliminazione", e );
                throw e;
            }
        }

        if ( EntityUtils.isTrue ( context.getInput ().getImportaEnti () ) ) {
            try {
                context.debug ( CodiceEntitaMigrazione.ENTE, "elimino enti migrati" );
                enteMigrator.eliminaOggettiMigrati ( context );
            } catch ( Exception e ) {
                context.error ( CodiceEntitaMigrazione.ENTE, "errore imprevisto nella fase di eliminazione", e );
                throw e;
            }
        }

        return context;
    }

    @Override
    @Transactional ( propagation = Propagation.REQUIRES_NEW )
    public MigrationContext salvaLog ( MigrationContext context ) {

        for ( LogMigrazione log: context.getLog () ) {
            if ( TipologiaLogMigrazione.DEBUG.name ().equals ( log.getCodiceMessaggio () ) ) {
                // non salvo i debug
            } else {
                logMigrazioneRepository.save ( log );
            }
        }

        return context;
    }

    @Override
    @Transactional ( propagation = Propagation.REQUIRES_NEW )
    public MigrationContext eliminaLog ( MigrationContext context ) {
        logMigrazioneRepository.deleteAll ();
        return context;
    }

}
