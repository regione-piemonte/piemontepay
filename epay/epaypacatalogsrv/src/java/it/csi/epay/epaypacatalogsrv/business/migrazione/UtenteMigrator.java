/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.business.migrazione;

import java.util.GregorianCalendar;
import java.util.List;

import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.epay.epaypacatalogsrv.model.EpaypaTUtente;
import it.csi.epay.epaypacatalogsrv.model.EpaypaTUtente_;
import it.csi.epay.epaypacatalogsrv.model.Utente;
import it.csi.epay.epaypacatalogsrv.model.Utente_;
import it.csi.epay.epaypacatalogsrv.repository.EpaypaTUtenteRepository;
import it.csi.epay.epaypacatalogsrv.repository.StatoAggiornamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.UtenteRepository;
import it.csi.epay.epaypacatalogsrv.vo.Constants;
import it.csi.epay.epaypacatalogsrv.vo.migrazione.CodiceEntitaMigrazione;


/**
 *
 */
@Service
public class UtenteMigrator extends EntityMigrator<EpaypaTUtente, Utente> {

    @Autowired
    private UtenteRepository epaycatTUtenteRepository;

    @Autowired
    private EpaypaTUtenteRepository epaypaTUtenteRepository;

    @Autowired
    private AssociazioneUtenteEnteMigrator associazioneUtenteEnteMigrator;

    @Autowired
    private AssociazioneUtenteRuoloMigrator associazioneUtenteRuoloMigrator;

    @Autowired
    private AssociazioneUtenteProfiloMigrator associazioneUtenteProfiloMigrator;

    @Autowired
    private StatoAggiornamentoRepository statoAggiornamentoRepository;

    @Override
    public Class<EpaypaTUtente> getPaClass () {
        return EpaypaTUtente.class;
    }

    @Override
    public Class<Utente> getCatalogClass () {
        return Utente.class;
    }

    @Override
    public boolean match ( EpaypaTUtente entityPA, Utente entityCAT ) {

        return entityPA.getCodUtente ().equals ( entityCAT.getCodiceFiscale () );
    }

    @Override
    public void configureMapper () {
        getDozerBeanMapper ().addMapping ( new BeanMappingBuilder () {

            @Override
            protected void configure () {
                mapping ( Utente.class, EpaypaTUtente.class )
                    .fields (
                        Utente_.codiceFiscale.getName (), EpaypaTUtente_.codUtente.getName () )
                    .fields (
                        Utente_.id.getName (), EpaypaTUtente_.idUtente.getName () )
                    .fields (
                        Utente_.nome.getName (), EpaypaTUtente_.nome.getName () );
            }
        } );
    }

    @Override
    public Utente map ( EpaypaTUtente entityPA, MigrationContext context ) {
        Utente mapped = getDozerBeanMapper ().map ( entityPA, Utente.class );
        return mapped;
    }

    @Override
    public void preInsert ( Utente entity, MigrationContext context ) {

        entity.setId ( null );

        entity.setUtenteInserimento ( Constants.CODICE_UTENTE_MIGRAZIONE );
        entity.setDataInserimento ( context.getStartTimestamp () );

        entity.setUtenteModifica ( Constants.CODICE_UTENTE_MIGRAZIONE );
        entity.setDataModifica ( context.getStartTimestamp () );

        entity.setStatoAggiornamento ( statoAggiornamentoRepository.findOneByCodice ( "NONE" ) );

        String nome = entity.getNome ();
        if ( nome.contains ( " " ) ) {
            int firstSpacePosition = nome.indexOf ( " " );
            entity.setNome ( nome.substring ( 0, firstSpacePosition ) );
            entity.setCognome ( nome.substring ( firstSpacePosition + 1 ) );
        } else {
            entity.setCognome ( nome );
        }

        entity.setDataInizioValidita ( new GregorianCalendar ( 2000, 1, 1 ).getTime () );
        entity.setDataFineValidita ( null );
        entity.setEmail ( null );
    }

    @Override
    public Utente salva ( Utente entityCAT, MigrationContext context ) {
        Utente inserito = epaycatTUtenteRepository.save ( entityCAT );

        context.info ( CodiceEntitaMigrazione.UTENTE,
            "inserito utente con codice " + inserito.getCodiceFiscale () + " e ID " + inserito.getId () );

        return inserito;
    }

    @Override
    public void postInsert ( Utente entity, MigrationContext context ) {
        associazioneUtenteEnteMigrator.inserisciRelazioniUtenteEnte ( context, entity );
        associazioneUtenteRuoloMigrator.inserisciRelazioniUtenteCdu ( context, entity );
        associazioneUtenteProfiloMigrator.inserisciRelazioniUtenteCodiciVersamento ( context, entity );
    }

    @Override
    public List<EpaypaTUtente> caricaListaPA ( MigrationContext context ) {
        return epaypaTUtenteRepository.findAll ();
    }

    @Override
    public List<Utente> caricaListaCAT ( MigrationContext context ) {
        return epaycatTUtenteRepository.findAll ();
    }

    @Override
    public void eliminaOggettiMigrati ( MigrationContext context ) {

        List<Utente> listaDaEliminare = epaycatTUtenteRepository.findByUtenteModifica ( Constants.CODICE_UTENTE_MIGRAZIONE );
        for ( Utente daEliminare: listaDaEliminare ) {
            associazioneUtenteProfiloMigrator.eliminaRelazioniUtenteCodiciVersamento ( context, daEliminare );
            associazioneUtenteRuoloMigrator.eliminaRelazioniUtenteCdu ( context, daEliminare );
            associazioneUtenteEnteMigrator.eliminaRelazioniUtenteEnte ( context, daEliminare );

            epaycatTUtenteRepository.delete ( daEliminare );

            context.info ( CodiceEntitaMigrazione.UTENTE,
                "eliminato l'oggetto con codice " + daEliminare.getCodiceFiscale () );
        }
    }

}
