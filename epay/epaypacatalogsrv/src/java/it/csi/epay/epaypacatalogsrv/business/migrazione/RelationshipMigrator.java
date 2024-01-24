/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.business.migrazione;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jca.cci.object.EisOperation;
import org.springframework.stereotype.Service;

import it.csi.epay.epaypacatalogsrv.business.util.EntityUtils;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteEnte;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteEntePK;
import it.csi.epay.epaypacatalogsrv.model.Ente;
import it.csi.epay.epaypacatalogsrv.model.EpaypaTEnte;
import it.csi.epay.epaypacatalogsrv.model.MappaturaEnteEpaypa;
import it.csi.epay.epaypacatalogsrv.model.StoricoEnte;
import it.csi.epay.epaypacatalogsrv.model.Utente;
import it.csi.epay.epaypacatalogsrv.repository.AssociazioneUtenteEnteRepository;
import it.csi.epay.epaypacatalogsrv.repository.EnteRepository;
import it.csi.epay.epaypacatalogsrv.repository.EpaypaTEnteRepository;
import it.csi.epay.epaypacatalogsrv.repository.MappaturaEnteEpaypaRepository;
import it.csi.epay.epaypacatalogsrv.repository.StoricoEnteRepository;
import it.csi.epay.epaypacatalogsrv.repository.UtenteRepository;
import it.csi.epay.epaypacatalogsrv.vo.Constants;
import it.csi.epay.epaypacatalogsrv.vo.migrazione.CodiceEntitaMigrazione;


/**
 *
 */
@Service
public class RelationshipMigrator {

    @Autowired
    private EnteRepository epaycatTEnteRepository;

    @Autowired
    private AssociazioneUtenteEnteRepository associazioneUtenteEnteRepository;

    @Autowired
    private StoricoEnteRepository epaycatTStoricoEnteRepository;

    @Autowired
    private EpaypaTEnteRepository epaypaTEnteRepository;

    @Autowired
    private MappaturaEnteEpaypaRepository mappaturaEnteEpaypaRepository;

    @Autowired
    private MigratorHelper migratorHelper;

    @Autowired
    private UtenteRepository epaycatTUtenteRepository;

    public void migra ( MigrationContext context ) {

        if ( EntityUtils.isTrue ( context.getInput ().getImportaEnti () ) ) {
            importaRelazioniEnti ( context );
        }
    }

    private void importaRelazioniEnti ( MigrationContext context ) {
        List<EpaypaTEnte> entiPA = epaypaTEnteRepository.findAll ();

        for ( EpaypaTEnte entePA: entiPA ) {
            // cerco ente corrispondente importato
            Ente enteCAT = migratorHelper.getEnteCATDaEntePASePresente ( entePA );
            if ( enteCAT != null ) {

                //if ( enteCAT.getUtenteAmministratore () == null ) {
                // ottengo mappatura
                MappaturaEnteEpaypa mappatura = mappaturaEnteEpaypaRepository.findOneByCodiceFiscale ( entePA.getCodFiscaleEnte () );

                if ( mappatura != null && mappatura.getIdUtenteAmministratore () != null ) {

                    // cerco l'utente importato
                    Utente utenteCAT = migratorHelper.getUtenteCATDaIdUtentePASePresente ( mappatura.getIdUtenteAmministratore ().longValue () );
                    if ( utenteCAT != null ) {
                        enteCAT.setUtenteAmministratore ( utenteCAT );
                        epaycatTEnteRepository.save ( enteCAT );

                        context.info ( CodiceEntitaMigrazione.ENTE,
                            "associato all'ente " + enteCAT.getCodiceFiscale () +
                                " l'utente amministratore " + utenteCAT.getCodiceFiscale () );
                    }
                }
                //}
            }
        }
    }

    public void eliminaOggettiMigrati ( MigrationContext context ) {
        if ( EntityUtils.isTrue ( context.getInput ().getImportaEnti () ) ) {
            eliminaRelazioniEnti ( context );
        }
    }

    private void eliminaRelazioniEnti ( MigrationContext context ) {
        List<Ente> enti = epaycatTEnteRepository.findAll ();
        List<StoricoEnte> storici = epaycatTStoricoEnteRepository.findAll ();
        List<Utente> listaDaEliminare = epaycatTUtenteRepository.findByUtenteModifica ( Constants.CODICE_UTENTE_MIGRAZIONE );

        for ( Utente daEliminare: listaDaEliminare ) {

            for ( Ente ente: enti ) {

                boolean isAdmin = false;
                if ( daEliminare != null && ente != null ) {
                    AssociazioneUtenteEntePK pk = new AssociazioneUtenteEntePK ();
                    pk.setIdEnte ( ente.getId ().intValue () );
                    pk.setIdUtente ( daEliminare.getId () );
                    AssociazioneUtenteEnte admin = associazioneUtenteEnteRepository.findOne ( pk );
                    if ( admin != null )
                        isAdmin = admin.getFlagAdmin ();
                }

                if ( isAdmin ) {
                    ente.setUtenteAmministratore ( null );
                    epaycatTEnteRepository.save ( ente );

                    context.info ( CodiceEntitaMigrazione.ENTE,
                        "elimino associazione fra ente " + ente.getCodiceFiscale () +
                            " ed utente amministratore " + daEliminare.getCodiceFiscale () );
                }
            }

            for ( StoricoEnte ente: storici ) {
                if ( ente.getUtenteAmministratore () != null && ente.getUtenteAmministratore ().getId ().equals ( daEliminare.getId () ) ) {
                    ente.setUtenteAmministratore ( null );
                    epaycatTStoricoEnteRepository.save ( ente );

                    context.info ( CodiceEntitaMigrazione.ENTE,
                        "elimino associazione fra voce di storico ente " + ente.getCodiceFiscale () +
                            " ed utente amministratore " + daEliminare.getCodiceFiscale () );
                }
            }
        }
    }

}
