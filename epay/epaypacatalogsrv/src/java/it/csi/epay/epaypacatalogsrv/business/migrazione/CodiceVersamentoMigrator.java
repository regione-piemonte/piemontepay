/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.business.migrazione;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.epay.epaypacatalogsrv.business.util.EntityUtils;
import it.csi.epay.epaypacatalogsrv.model.CodiceVersamento;
import it.csi.epay.epaypacatalogsrv.model.CodiceVersamento_;
import it.csi.epay.epaypacatalogsrv.model.Ente;
import it.csi.epay.epaypacatalogsrv.model.MappaturaCodiceVersamentoEpaypa;
import it.csi.epay.epaypacatalogsrv.model.MappaturaCodiceVersamentoEpaypa_;
import it.csi.epay.epaypacatalogsrv.model.VoceEntrata;
import it.csi.epay.epaypacatalogsrv.repository.CodiceVersamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.EnteRepository;
import it.csi.epay.epaypacatalogsrv.repository.MappaturaCodiceVersamentoEpaypaRepository;
import it.csi.epay.epaypacatalogsrv.repository.ModalitaIntegrazioneRepository;
import it.csi.epay.epaypacatalogsrv.repository.StatoAggiornamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.TipoPagamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.VoceEntrataRepository;
import it.csi.epay.epaypacatalogsrv.vo.Constants;
import it.csi.epay.epaypacatalogsrv.vo.migrazione.CodiceEntitaMigrazione;


/**
 *
 */
@Service
public class CodiceVersamentoMigrator extends EntityMigrator<MappaturaCodiceVersamentoEpaypa, CodiceVersamento> {

    @Autowired
    private EnteRepository enteRepository;

    @Autowired
    private CodiceVersamentoRepository codiceVersamentoRepository;

    @Autowired
    private ModalitaIntegrazioneRepository modalitaIntegrazioneRepository;

    @Autowired
    private MappaturaCodiceVersamentoEpaypaRepository mappaturaCodiceVersamentoEpaypaRepository;

    @Autowired
    private VoceEntrataRepository voceEntrataRepository;

    @Autowired
    private TipoPagamentoRepository tipoPagamentoRepository;

    @Autowired
    private StatoAggiornamentoRepository statoAggiornamentoRepository;

    @Autowired
    private MigratorHelper migratorHelper;

    @Override
    public Class<MappaturaCodiceVersamentoEpaypa> getPaClass () {
        return MappaturaCodiceVersamentoEpaypa.class;
    }

    @Override
    public Class<CodiceVersamento> getCatalogClass () {
        return CodiceVersamento.class;
    }

    @Override
    public List<MappaturaCodiceVersamentoEpaypa> caricaListaPA ( MigrationContext context ) {
        // leggo da tabella di mappatura
        List<MappaturaCodiceVersamentoEpaypa> lista = mappaturaCodiceVersamentoEpaypaRepository.findAll ();
        List<MappaturaCodiceVersamentoEpaypa> listaFiltrata = new ArrayList<> ();

        // elaboro le voci una ad una
        for ( MappaturaCodiceVersamentoEpaypa el: lista ) {
            validaOMuori ( el );

            listaFiltrata.add ( el );
        }

        // ordino per codice nuovo, in modo da inserire prima il padre e poi i collegati, in ordine
        listaFiltrata.sort ( new Comparator<MappaturaCodiceVersamentoEpaypa> () {

            @Override
            public int compare ( MappaturaCodiceVersamentoEpaypa o1, MappaturaCodiceVersamentoEpaypa o2 ) {
                String v1 = o1.getCodiceVoceEntrata () + "_" + o1.getCodiceNuovo ();
                String v2 = o2.getCodiceVoceEntrata () + "_" + o2.getCodiceNuovo ();
                return v1.compareTo ( v2 );
            }

        } );

        return listaFiltrata;
    }

    private void validaOMuori ( MappaturaCodiceVersamentoEpaypa el ) {
        if ( StringUtils.isEmpty ( el.getCodiceVoceEntrata () ) ) {
            throw new DataMigrationFatalErrorException ( "codice voce entrata mancante su codice versamento " + el.getId () );
        }

        if ( StringUtils.isEmpty ( el.getDescrizione () ) ) {
            throw new DataMigrationFatalErrorException ( "descrizione mancante su codice versamento " + el.getId () );
        }

        VoceEntrata voceEntrata = voceEntrataRepository.findOneByCodice ( el.getCodiceVoceEntrata () );
        if ( voceEntrata == null ) {
            throw new DataMigrationFatalErrorException ( "voce entrata non valida su codice versamento " + el.getId () );
        }

        if ( StringUtils.isEmpty ( el.getCodiceNuovo () ) ) {
            el.setCodiceNuovo ( voceEntrata.getCodice () );
            el.setDescrizione ( voceEntrata.getDescrizione () );
        } else {

        }

        // if ( !el.getCodiceNuovo ().matches ( "[A-Z]{2}[0-9]{2}" ) ) {
        if ( !el.getCodiceNuovo ().matches ( "[A-Z0-9]{4}" ) ) {
            throw new DataMigrationFatalErrorException ( "codice nuovo non valido su codice versamento: " + el.getCodiceNuovo () );
        }

        if ( el.getCodiceNuovo ().endsWith ( "0" ) ) {
            // codice base

            if ( false ) {
                if ( !el.getCodiceNuovo ().equals ( el.getCodiceVoceEntrata () ) ) {
                    throw new DataMigrationFatalErrorException (
                        "il codice nuovo non coincide con la voce entrata: ID " + el.getId () + " con codice " + el.getCodiceNuovo () );
                }
            }

            if ( voceEntrataRepository.findOneByCodice ( el.getCodiceVoceEntrata () ) == null ) {
                throw new DataMigrationFatalErrorException (
                    "il codice voce entrata non esiste: ID " + el.getId () + " con codice " + el.getCodiceVoceEntrata () );
            }
        }
    }

    @Override
    public List<CodiceVersamento> caricaListaCAT ( MigrationContext context ) {
        List<CodiceVersamento> lista = codiceVersamentoRepository.findAll ();
        List<CodiceVersamento> listaFiltrata = new ArrayList<> ();

        for ( CodiceVersamento el: lista ) {
            if ( !EntityUtils.isTrue ( el.getFlagAnnullato () ) ) {
                listaFiltrata.add ( el );
            }
        }

        return listaFiltrata;
    }

    @Override
    public void configureMapper () {
        getDozerBeanMapper ().addMapping ( new BeanMappingBuilder () {

            @Override
            protected void configure () {
                mapping ( CodiceVersamento.class, MappaturaCodiceVersamentoEpaypa.class )
                    .fields (
                        CodiceVersamento_.id.getName (), MappaturaCodiceVersamentoEpaypa_.id.getName () )
                    .fields (
                        CodiceVersamento_.codice.getName (), MappaturaCodiceVersamentoEpaypa_.codiceNuovo.getName () )
                    .fields (
                        CodiceVersamento_.descrizione.getName (), MappaturaCodiceVersamentoEpaypa_.descrizione.getName () )
                    .fields (
                        "ente.id", MappaturaCodiceVersamentoEpaypa_.idEnte.getName () )
                    .fields (
                        "voceEntrata.codice", MappaturaCodiceVersamentoEpaypa_.codiceVoceEntrata.getName () )
                    .fields (
                        "tipoPagamento.codice", MappaturaCodiceVersamentoEpaypa_.codiceTipoPagamento.getName () );
            }
        } );
    }

    @Override
    public boolean match ( MappaturaCodiceVersamentoEpaypa entityPA, CodiceVersamento entityCAT ) {

        return entityPA.getCodiceNuovo ().equals ( entityCAT.getCodice () ) &&
            migratorHelper.getIdEnteCATDaIdEntePA ( entityPA.getIdEnte () )
                .equals ( entityCAT.getEnte ().getId () );
    }

    @Override
    public CodiceVersamento map ( MappaturaCodiceVersamentoEpaypa entityPA, MigrationContext context ) {
        CodiceVersamento mapped = getDozerBeanMapper ().map ( entityPA, CodiceVersamento.class );

        Long identeCAT = migratorHelper.getIdEnteCATDaIdEntePA ( entityPA.getIdEnte () );
        Ente ente = enteRepository.findOne ( identeCAT );
        mapped.setEnte ( ente );

        VoceEntrata voceEntrata = voceEntrataRepository.findOneByCodice ( entityPA.getCodiceVoceEntrata () );

        mapped.setVoceEntrata ( voceEntrata );

        return mapped;
    }

    @Override
    public void preInsert ( CodiceVersamento entity, MigrationContext context ) {

        //        MappaturaCodiceVersamentoEpaypa mappatura = mappaturaCodiceVersamentoEpaypaRepository.findByCodice ( entity.getCodice () );

        entity.setId ( null );
        entity.setUtenteInserimento ( Constants.CODICE_UTENTE_MIGRAZIONE );
        entity.setDataInserimento ( context.getStartTimestamp () );
        entity.setUtenteModifica ( Constants.CODICE_UTENTE_MIGRAZIONE );
        entity.setDataModifica ( context.getStartTimestamp () );
        entity.setStatoAggiornamento ( statoAggiornamentoRepository.findOneByCodice ( "NONE" ) );
        entity.setDescrizioneErroreAggiornamento ( "Sincronizzazione non eseguita" );
        entity.setTipoPagamento ( tipoPagamentoRepository.findOneByCodice ( entity.getTipoPagamento ().getCodice () ) );

        // TODO : COME COMPILARE QUESTI CAMPI
        entity.setFlagInvioFlussi ( false );
        entity.setBic ( null );
        entity.setEmail ( null );
        entity.setIban ( null );

        entity.setFlagAnnullato ( null );
        entity.setFattura ( entity.getFattura () );

        //        if ( !StringUtils.isBlank ( mappatura.getCodiceModalitaIntegrazione () ) ) {
        //            entity.setModalitaIntegrazione (
        //                modalitaIntegrazioneRepository.findOneByCodice ( mappatura.getCodiceModalitaIntegrazione () ) );
        //        }
    }

    @Override
    public CodiceVersamento salva ( CodiceVersamento entityCAT, MigrationContext context ) {

        // TROVARE PADRE BY CODICE E ID ENTE
        String lastDigit = entityCAT.getCodice ().substring ( 3 );

        if ( lastDigit.matches ( "[A-Z0]" ) ) {
            entityCAT.setCodiceVersamentoPadre ( null );
        } else {
            String codicePadre = entityCAT.getCodice ().substring ( 0, 3 ) + "0";
            List<CodiceVersamento> codiciVersamentoCAT
                = codiceVersamentoRepository.findByEnte_IdAndCodice ( entityCAT.getEnte ().getId (), codicePadre );

            CodiceVersamento codiceVersamentoPadre = null;
            for ( CodiceVersamento o: codiciVersamentoCAT ) {
                if ( !EntityUtils.isTrue ( o.getFlagAnnullato () ) ) {
                    codiceVersamentoPadre = o;
                    break;
                }
            }

            if ( codiceVersamentoPadre == null ) {
                codiceVersamentoPadre = creaPadrePerCodiceVersamento ( codicePadre, entityCAT, context );
            }

            entityCAT.setCodiceVersamentoPadre ( codiceVersamentoPadre );
        }

        CodiceVersamento inserito = codiceVersamentoRepository.save ( entityCAT );

        context.info ( CodiceEntitaMigrazione.CODICE_VERSAMENTO,
            "inserito codice versamento con codice " + inserito.getCodice () + " e ID " + inserito.getId () + " per ente "
                + inserito.getEnte ().getCodiceFiscale () );

        return inserito;
    }

    private CodiceVersamento creaPadrePerCodiceVersamento ( String codice, CodiceVersamento entityCAT, MigrationContext context ) {

        if ( false ) {
            throw new DataMigrationFatalErrorException (
                "codice versamento padre non trovato per codice versamento " + entityCAT.getCodice () + " per ente "
                    + entityCAT.getEnte ().getCodiceFiscale () );
        } else {

            CodiceVersamento entity = new CodiceVersamento ();

            entity.setCodice ( codice );
            entity.setCodiceVersamentoPadre ( null );
            entity.setDescrizione ( entityCAT.getVoceEntrata ().getDescrizione () );
            entity.setEnte ( entityCAT.getEnte () );
            entity.setVoceEntrata ( entityCAT.getVoceEntrata () );

            entity.setId ( null );
            entity.setUtenteInserimento ( Constants.CODICE_UTENTE_MIGRAZIONE );
            entity.setDataInserimento ( context.getStartTimestamp () );
            entity.setUtenteModifica ( Constants.CODICE_UTENTE_MIGRAZIONE );
            entity.setDataModifica ( context.getStartTimestamp () );
            entity.setStatoAggiornamento ( statoAggiornamentoRepository.findOneByCodice ( "NONE" ) );
            entity.setDescrizioneErroreAggiornamento ( "Sincronizzazione non eseguita" );
            entity.setTipoPagamento ( entityCAT.getTipoPagamento () );

            // TODO : COME COMPILARE QUESTI CAMPI
            entity.setFlagInvioFlussi ( false );
            entity.setBic ( null );
            entity.setEmail ( null );
            entity.setIban ( null );

            entity.setFlagAnnullato ( null );

            CodiceVersamento inserito = codiceVersamentoRepository.save ( entity );

            context.warn ( CodiceEntitaMigrazione.CODICE_VERSAMENTO,
                "inserito codice versamento padre " + codice + " generato automaticamente per il codice " + entityCAT.getCodice () + " e ID "
                    + inserito.getId ()
                    + " per ente "
                    + inserito.getEnte ().getCodiceFiscale () );

            return inserito;
        }

    }

    @Override
    public void eliminaOggettiMigrati ( MigrationContext context ) {

        List<CodiceVersamento> listaDaEliminare = codiceVersamentoRepository.findByUtenteModifica ( Constants.CODICE_UTENTE_MIGRAZIONE );
        for ( CodiceVersamento daEliminare: listaDaEliminare ) {
            if ( daEliminare.getCodiceVersamentoPadre () == null ) {
                continue;
            }

            codiceVersamentoRepository.delete ( daEliminare );

            context.info ( CodiceEntitaMigrazione.CODICE_VERSAMENTO,
                "eliminato l'oggetto con codice " + daEliminare.getCodice () + " per ente " + daEliminare.getEnte ().getCodiceFiscale () );
        }

        for ( CodiceVersamento daEliminare: listaDaEliminare ) {
            if ( daEliminare.getCodiceVersamentoPadre () != null ) {
                continue;
            }

            codiceVersamentoRepository.delete ( daEliminare );

            context.info ( CodiceEntitaMigrazione.CODICE_VERSAMENTO,
                "eliminato l'oggetto con codice " + daEliminare.getCodice () + " per ente " + daEliminare.getEnte ().getCodiceFiscale () );
        }
    }

}
