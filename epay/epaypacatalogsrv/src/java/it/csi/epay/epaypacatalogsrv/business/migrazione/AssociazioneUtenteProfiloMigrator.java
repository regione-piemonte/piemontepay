/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.business.migrazione;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.epay.epaypacatalogsrv.business.util.EntityUtils;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteCodiceVersamento;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteCodiceVersamentoPK;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteTematica;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteTematicaPK;
import it.csi.epay.epaypacatalogsrv.model.CodiceVersamento;
import it.csi.epay.epaypacatalogsrv.model.EpaypaRProfiloCodVer;
import it.csi.epay.epaypacatalogsrv.model.EpaypaRUtenteProfilo;
import it.csi.epay.epaypacatalogsrv.model.EpaypaTProfilo;
import it.csi.epay.epaypacatalogsrv.model.MappaturaCodiceVersamentoEpaypa;
import it.csi.epay.epaypacatalogsrv.model.Utente;
import it.csi.epay.epaypacatalogsrv.repository.AssociazioneUtenteCodiceVersamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.AssociazioneUtenteTematicaRepository;
import it.csi.epay.epaypacatalogsrv.repository.CodiceVersamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.EpaypaRProfiloCodVerRepository;
import it.csi.epay.epaypacatalogsrv.repository.EpaypaRUtenteProfiloRepository;
import it.csi.epay.epaypacatalogsrv.repository.EpaypaTProfiloRepository;
import it.csi.epay.epaypacatalogsrv.repository.MappaturaCodiceVersamentoEpaypaRepository;
import it.csi.epay.epaypacatalogsrv.vo.migrazione.CodiceEntitaMigrazione;


/**
 *
 */
@Service
public class AssociazioneUtenteProfiloMigrator {

    @Autowired
    private MigratorHelper migratorHelper;

    @Autowired
    private AssociazioneUtenteCodiceVersamentoRepository epaycatRUtenteCodiceVersamentoRepository;

    @Autowired
    private AssociazioneUtenteTematicaRepository epaycatRUtenteTematicaRepository;

    @Autowired
    private CodiceVersamentoRepository epaycatTCodiceVersamentoRepository;

    @Autowired
    private EpaypaRUtenteProfiloRepository epaypaRUtenteProfiloRepository;

    @Autowired
    private EpaypaTProfiloRepository epaypaTProfiloRepository;

    @Autowired
    private EpaypaRProfiloCodVerRepository epaypaRProfiloCodVerRepository;

    @Autowired
    private MappaturaCodiceVersamentoEpaypaRepository mappaturaCodiceVersamentoEpaypaRepository;

    public void inserisciRelazioniUtenteCodiciVersamento ( MigrationContext context, Utente nuovoUtente ) {

        Long idUtenteNuovoPA = migratorHelper.getIdUtentePADaIdUtenteCAT ( nuovoUtente.getId () );

        List<EpaypaRUtenteProfilo> relazioniPA = epaypaRUtenteProfiloRepository.findByIdUtente ( idUtenteNuovoPA );

        Set<String> codiciAssocEntiTematiche = new HashSet<> ();

        for ( EpaypaRUtenteProfilo relazionePA: relazioniPA ) {

            EpaypaTProfilo profiloPA = epaypaTProfiloRepository.findOne ( relazionePA.getId ().getIdProfilo () );

            if ( profiloPA == null ) {
                throw new DataMigrationFatalErrorException ( "profilo PA non trovato" );
            }

            Long idEnteCAT = migratorHelper.getIdEnteCATDaIdEntePA ( profiloPA.getIdEnte () );

            List<EpaypaRProfiloCodVer> relazioniCodiciVersamentoProfilo = epaypaRProfiloCodVerRepository.findByIdProfilo ( profiloPA.getIdProfilo () );

            for ( EpaypaRProfiloCodVer relazioneCVPA: relazioniCodiciVersamentoProfilo ) {

                MappaturaCodiceVersamentoEpaypa mappatura
                    = mappaturaCodiceVersamentoEpaypaRepository.findOne ( relazioneCVPA.getId ().getIdCodiceVersamento () );

                if ( mappatura == null ) {
                    String message = "mappatura codice versamento PA non trovato: " + relazioneCVPA.getId ().getIdCodiceVersamento ();

                    context.warn ( CodiceEntitaMigrazione.UTENTE, message );
                    //                    throw new DataMigrationFatalErrorException ( message );

                    continue;
                }

                List<CodiceVersamento> codiciVersamentoCAT
                    = epaycatTCodiceVersamentoRepository.findByEnte_IdAndCodice (
                        idEnteCAT,
                        mappatura.getCodiceNuovo () );

                CodiceVersamento codiceVersamentoProfiloCAT = null;
                for ( CodiceVersamento o: codiciVersamentoCAT ) {
                    if ( !EntityUtils.isTrue ( o.getFlagAnnullato () ) ) {
                        codiceVersamentoProfiloCAT = o;
                        break;
                    }
                }

                if ( codiceVersamentoProfiloCAT == null ) {
                    throw new DataMigrationFatalErrorException ( "associazione CV mancante per estremi " + mappatura.getIdEnte () + " - "
                        + mappatura.getCodiceNuovo () );
                }

                AssociazioneUtenteCodiceVersamento relazioneCAT = new AssociazioneUtenteCodiceVersamento ();
                AssociazioneUtenteCodiceVersamentoPK relazioneCATID = new AssociazioneUtenteCodiceVersamentoPK ();
                relazioneCATID.setIdCodiceVersamento ( codiceVersamentoProfiloCAT.getId ().intValue () );
                relazioneCATID.setIdEnte ( idEnteCAT.intValue () );
                relazioneCATID.setIdUtente ( nuovoUtente.getId () );
                relazioneCAT.setId ( relazioneCATID );

                epaycatRUtenteCodiceVersamentoRepository.save ( relazioneCAT );

                context.info ( CodiceEntitaMigrazione.UTENTE,
                    "inserita associazione fra utente " + nuovoUtente.getCodiceFiscale () +
                        " e codice versamento " + codiceVersamentoProfiloCAT.getCodice () + " con ID " + codiceVersamentoProfiloCAT.getId () );

                String codiceAssocEntiTematiche = codiceVersamentoProfiloCAT.getEnte ().getId () + "_" +
                    codiceVersamentoProfiloCAT.getVoceEntrata ().getTematica ().getCodice ();

                if ( !codiciAssocEntiTematiche.contains ( codiceAssocEntiTematiche ) ) {
                    codiciAssocEntiTematiche.add ( codiceAssocEntiTematiche );

                    AssociazioneUtenteTematica relazioneTematicaCAT = new AssociazioneUtenteTematica ();
                    AssociazioneUtenteTematicaPK relazioneTematicaCATID = new AssociazioneUtenteTematicaPK ();
                    relazioneTematicaCATID.setCodTematica (
                        codiceVersamentoProfiloCAT.getVoceEntrata ().getTematica ().getCodice () );
                    relazioneTematicaCATID.setIdEnte ( idEnteCAT.intValue () );
                    relazioneTematicaCATID.setIdUtente ( nuovoUtente.getId () );
                    relazioneTematicaCAT.setId ( relazioneTematicaCATID );
                    relazioneTematicaCAT.setFlagVisibilitaTotale ( false );

                    epaycatRUtenteTematicaRepository.save ( relazioneTematicaCAT );

                    context.info ( CodiceEntitaMigrazione.UTENTE,
                        "inserita associazione fra utente " + nuovoUtente.getCodiceFiscale () +
                            " e tematica " + codiceVersamentoProfiloCAT.getVoceEntrata ().getTematica ().getCodice () +
                            " con visibilita' " + ( relazioneTematicaCAT.getFlagVisibilitaTotale () ? "totale" : "parziale" ) );
                }
            }
        }

    }

    public void eliminaRelazioniUtenteCodiciVersamento ( MigrationContext context, Utente utente ) {
        epaycatRUtenteCodiceVersamentoRepository.deleteByIdUtente ( utente.getId () );
        epaycatRUtenteTematicaRepository.deleteByIdUtente ( utente.getId () );
    }

}
