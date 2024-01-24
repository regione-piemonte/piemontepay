/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.business.migrazione;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.epay.epaypacatalogsrv.model.Ente;
import it.csi.epay.epaypacatalogsrv.model.Ente_;
import it.csi.epay.epaypacatalogsrv.model.EpaypaTEnte;
import it.csi.epay.epaypacatalogsrv.model.EpaypaTEnte_;
import it.csi.epay.epaypacatalogsrv.model.MappaturaEnteEpaypa;
import it.csi.epay.epaypacatalogsrv.repository.EmailRepository;
import it.csi.epay.epaypacatalogsrv.repository.EnteRepository;
import it.csi.epay.epaypacatalogsrv.repository.EpaypaTEnteRepository;
import it.csi.epay.epaypacatalogsrv.repository.MappaturaEnteEpaypaRepository;
import it.csi.epay.epaypacatalogsrv.repository.ModalitaAcquisizioneProvvisoriRepository;
import it.csi.epay.epaypacatalogsrv.repository.PeriodicitaSchedulazioneRiconciliazioneRepository;
import it.csi.epay.epaypacatalogsrv.repository.StatoAggiornamentoEnteRepository;
import it.csi.epay.epaypacatalogsrv.repository.TipologiaAccertamentoRepository;
import it.csi.epay.epaypacatalogsrv.vo.Constants;
import it.csi.epay.epaypacatalogsrv.vo.migrazione.CodiceEntitaMigrazione;


/**
 *
 */
@Service
public class EnteMigrator extends EntityMigrator<EpaypaTEnte, Ente> {

    @Autowired
    private EnteRepository epaycatTEnteRepository;

    @Autowired
    private EpaypaTEnteRepository epaypaTEnteRepository;

    @Autowired
    private StatoAggiornamentoEnteRepository saeRepository;

    @Autowired
    private ModalitaAcquisizioneProvvisoriRepository epaypacatDModalitaAcquisizioneProvvisoriRepository;

    @Autowired
    private PeriodicitaSchedulazioneRiconciliazioneRepository epaypacatDPeriodicitaSchedulazioneRiconciliazioneRepository;

    @Autowired
    private TipologiaAccertamentoRepository epaypacatDTipologiaAccertamentoRepository;

    @Autowired
    private MappaturaEnteEpaypaRepository mappaturaEnteEpaypaRepository;

    @Autowired
    private EmailRepository emailRepository;

    @Override
    public Class<EpaypaTEnte> getPaClass () {
        return EpaypaTEnte.class;
    }

    @Override
    public Class<Ente> getCatalogClass () {
        return Ente.class;
    }

    @Override
    public boolean match ( EpaypaTEnte entityPA, Ente entityCAT ) {

        return entityPA.getCodFiscaleEnte ().equals ( entityCAT.getCodiceFiscale () );
    }

    @Override
    public void configureMapper () {
        getDozerBeanMapper ().addMapping ( new BeanMappingBuilder () {

            @Override
            protected void configure () {
                mapping ( Ente.class, EpaypaTEnte.class )
                    .fields (
                        Ente_.codiceFiscale.getName (), EpaypaTEnte_.codFiscaleEnte.getName () )
                    .fields (
                        Ente_.id.getName (), EpaypaTEnte_.idEnte.getName () )
                    .fields (
                        Ente_.gs1Gln.getName (), EpaypaTEnte_.codGs1Gln.getName () )
                    .fields (
                        Ente_.cbill.getName (), EpaypaTEnte_.codInterbancario.getName () )
                    .fields (
                        Ente_.email.getName (), EpaypaTEnte_.email.getName () )
                    .fields (
                        Ente_.denominazione.getName (), EpaypaTEnte_.denominazione.getName () )
                    .fields (
                        Ente_.logoContent.getName (), EpaypaTEnte_.logo.getName () );
            }
        } );
    }

    @Override
    public void preInsert ( Ente entity, MigrationContext context ) {

        MappaturaEnteEpaypa mappatura = mappaturaEnteEpaypaRepository.findOneByCodiceFiscale ( entity.getCodiceFiscale () );

        if ( mappatura == null ) {
            throw new RuntimeException ( "mappatura dei campi aggiuntivi non presente per codice fiscale " + entity.getCodiceFiscale () );
        }

        entity.setId ( entity.getId () + MigratorHelper.OFFSET_ID );

        entity.setUtenteInserimento ( Constants.CODICE_UTENTE_MIGRAZIONE );
        entity.setDataInserimento ( context.getStartTimestamp () );

        entity.setUtenteModifica ( Constants.CODICE_UTENTE_MIGRAZIONE );
        entity.setDataModifica ( context.getStartTimestamp () );

        entity.setIndirizzo ( mappatura.getIndirizzo () );
        entity.setLocalita ( mappatura.getLocalita () );
        entity.setCap ( mappatura.getCap () );
        entity.setCivico ( mappatura.getCivico () );
        entity.setSiglaProvincia ( mappatura.getSiglaProvincia () );
        entity.setIban ( mappatura.getIban () );
        entity.setBic ( mappatura.getBic () );
        entity.setFlagAccertamento ( mappatura.getFlagAccertamento () );
        entity.setFlagEntePlurintermediato ( mappatura.getFlagEntePlurintermediato () );
        entity.setFlagQualsiasiCodiceVersamento ( mappatura.getFlagQualsiasiCodiceVersamento () );
        entity.setFlagRicezioneErrori ( mappatura.getFlagRicezioneErrori () );
        entity.setFlagRicezioneFlussoBaseRendicontazione ( mappatura.getFlagRicezioneFlussoBaseRendicontazione () );
        entity.setFlagRiconciliazioneVersamenti ( mappatura.getFlagRiconciliazioneVersamenti () );
        entity.setGiornoSchedulazione ( mappatura.getGiornoSchedulazione () );

        if ( !StringUtils.isBlank ( mappatura.getCodiceModalitaAcquisizioneProvvisori () ) ) {
            entity.setModalitaAcquisizioneProvvisori (
                epaypacatDModalitaAcquisizioneProvvisoriRepository.findOneByCodice ( mappatura.getCodiceModalitaAcquisizioneProvvisori () ) );
        }

        if ( !StringUtils.isBlank ( mappatura.getCodicePeriodicitaSchedulazioneRiconciliazione () ) ) {
            entity.setPeriodicitaSchedulazioneRiconciliazione (
                epaypacatDPeriodicitaSchedulazioneRiconciliazioneRepository.findOneByCodice ( mappatura.getCodicePeriodicitaSchedulazioneRiconciliazione () ) );
        }

        if ( !StringUtils.isBlank ( mappatura.getCodiceTipologiaAccertamento () ) ) {
            entity.setTipologiaAccertamento (
                epaypacatDTipologiaAccertamentoRepository.findOneByCodice ( mappatura.getCodiceTipologiaAccertamento () ) );
        }

        entity.setUtenteAmministratore ( null );

        entity.setStatoAggiornamentoEnte ( saeRepository.findOneByCodice ( "NONE" ) );
        entity.setDescrizioneErroreAggiornamento ( "Sincronizzazione non eseguita" );

        if ( entity.getLogoContent () != null ) {
            String guessResult;
            InputStream is = new BufferedInputStream ( new ByteArrayInputStream ( entity.getLogoContent () ) );
            try {
                guessResult = URLConnection.guessContentTypeFromStream ( is );
                entity.setLogoContentType ( guessResult );
            } catch ( IOException e ) {
                throw new RuntimeException ( "cannot guess MIME type from logo content of entity " + entity.getDenominazione () );
            }

            if ( guessResult == null ) {
                throw new RuntimeException ( "contenuto del logo non valido per l'ente " + entity.getDenominazione () );
            }

            entity.setLogoFileName ( "logo" + ( guessResult.contains ( "/" ) ? "." + guessResult.split ( "/" ) [1] : "" ) );
            entity.setLogoFileSize ( entity.getLogoContent ().length );
        }

    }

    @Override
    public Ente salva ( Ente entityCAT, MigrationContext context ) {
        Ente inserito = epaycatTEnteRepository.save ( entityCAT );

        context.info ( CodiceEntitaMigrazione.ENTE,
            "inserito ente con codice " + inserito.getCodiceFiscale () + " e ID " + inserito.getId () );

        return inserito;
    }

    @Override
    public List<EpaypaTEnte> caricaListaPA ( MigrationContext context ) {
        List<EpaypaTEnte> lista = epaypaTEnteRepository.findAll ();
        List<EpaypaTEnte> listaFiltrata = new ArrayList<> ();

        for ( EpaypaTEnte o: lista ) {

            MappaturaEnteEpaypa mappatura = mappaturaEnteEpaypaRepository.findOneByCodiceFiscale ( o.getCodFiscaleEnte () );

            if ( mappatura != null ) {
                listaFiltrata.add ( o );
            } else {
                context.warn ( "Mappatura per ente non trovata: " + o.getCodFiscaleEnte () );
            }
        }

        return listaFiltrata;
    }

    @Override
    public List<Ente> caricaListaCAT ( MigrationContext context ) {
        return epaycatTEnteRepository.findAll ();
    }

    @Override
    public void eliminaOggettiMigrati ( MigrationContext context ) {

        emailRepository.deleteAll ();

        List<Ente> listaDaEliminare = epaycatTEnteRepository.findByUtenteModifica ( Constants.CODICE_UTENTE_MIGRAZIONE );
        for ( Ente daEliminare: listaDaEliminare ) {

            epaycatTEnteRepository.delete ( daEliminare );

            context.info ( CodiceEntitaMigrazione.ENTE,
                "eliminato l'oggetto con codice " + daEliminare.getCodiceFiscale () );
        }
    }
}
