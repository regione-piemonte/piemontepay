/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.business.migrazione;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteCdu;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteCduPK;
import it.csi.epay.epaypacatalogsrv.model.EpaypaRRuoloCdu;
import it.csi.epay.epaypacatalogsrv.model.EpaypaRUtenteRuolo;
import it.csi.epay.epaypacatalogsrv.model.EpaypaTRuolo;
import it.csi.epay.epaypacatalogsrv.model.Utente;
import it.csi.epay.epaypacatalogsrv.repository.AssociazioneUtenteCduRepository;
import it.csi.epay.epaypacatalogsrv.repository.EpaypaRRuoloCduRepository;
import it.csi.epay.epaypacatalogsrv.repository.EpaypaRUtenteRuoloRepository;
import it.csi.epay.epaypacatalogsrv.repository.EpaypaTRuoloRepository;
import it.csi.epay.epaypacatalogsrv.vo.migrazione.CodiceEntitaMigrazione;


/**
 *
 */
@Service
public class AssociazioneUtenteRuoloMigrator {

    @Autowired
    private MigratorHelper migratorHelper;

    @Autowired
    private AssociazioneUtenteCduRepository epaycatRUtenteCduRepository;

    @Autowired
    private EpaypaRUtenteRuoloRepository epaypaRUtenteRuoloRepository;

    @Autowired
    private EpaypaTRuoloRepository epaypaTRuoloRepository;

    @Autowired
    private EpaypaRRuoloCduRepository epaypaRRuoloCduRepository;

    public void inserisciRelazioniUtenteCdu ( MigrationContext context, Utente nuovoUtente ) {

        Long idUtenteNuovoPA = migratorHelper.getIdUtentePADaIdUtenteCAT ( nuovoUtente.getId () );

        List<EpaypaRUtenteRuolo> relazioniPA = epaypaRUtenteRuoloRepository.findByIdUtente ( idUtenteNuovoPA );

        for ( EpaypaRUtenteRuolo relazionePA: relazioniPA ) {

            // ottengo tutti i CDU legati a quell'utente passando attraverso il ruolo

            EpaypaTRuolo ruolo = epaypaTRuoloRepository.findOne ( relazionePA.getId ().getIdRuolo () );
            Long idEnteCAT = migratorHelper.getIdEnteCATDaIdEntePA ( ruolo.getIdEnte () );
            List<EpaypaRRuoloCdu> cduRuolo = epaypaRRuoloCduRepository.findByCodRuolo ( ruolo.getCodRuolo () );

            // aggiungo sempre il cdu di LOGIN
            aggiungiCduArbitrario ( context, nuovoUtente, idEnteCAT.intValue (), "LOGIN" );

            for ( EpaypaRRuoloCdu relazioneUtenteCduPA: cduRuolo ) {
                AssociazioneUtenteCdu relazioneCAT = new AssociazioneUtenteCdu ();
                AssociazioneUtenteCduPK relazioneCATID = new AssociazioneUtenteCduPK ();

                relazioneCATID.setCodCdu ( relazioneUtenteCduPA.getId ().getCodCdu () );
                relazioneCATID.setIdEnte ( idEnteCAT.intValue () );
                relazioneCATID.setIdUtente ( nuovoUtente.getId () );

                relazioneCAT.setId ( relazioneCATID );

                epaycatRUtenteCduRepository.save ( relazioneCAT );

                context.info ( CodiceEntitaMigrazione.UTENTE,
                    "inserita associazione fra utente " + nuovoUtente.getCodiceFiscale () + " e CDU " + relazioneCATID.getCodCdu () );
            }
        }

    }

    private void aggiungiCduArbitrario ( MigrationContext context, Utente nuovoUtente, Integer idEnte, String codiceCdu ) {
        AssociazioneUtenteCdu relazioneCAT = new AssociazioneUtenteCdu ();
        AssociazioneUtenteCduPK relazioneCATID = new AssociazioneUtenteCduPK ();

        relazioneCATID.setCodCdu ( codiceCdu );
        relazioneCATID.setIdEnte ( idEnte );
        relazioneCATID.setIdUtente ( nuovoUtente.getId () );

        relazioneCAT.setId ( relazioneCATID );

        epaycatRUtenteCduRepository.save ( relazioneCAT );

        context.info ( CodiceEntitaMigrazione.UTENTE,
            "inserita associazione fra utente " + nuovoUtente.getCodiceFiscale () + " e CDU " + relazioneCATID.getCodCdu () );
    }

    public void eliminaRelazioniUtenteCdu ( MigrationContext context, Utente utente ) {

        epaycatRUtenteCduRepository.deleteByIdUtente ( utente.getId () );
    }

}
