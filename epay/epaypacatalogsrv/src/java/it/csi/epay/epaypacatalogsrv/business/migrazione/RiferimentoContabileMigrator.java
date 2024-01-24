/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.business.migrazione;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.epay.epaypacatalogsrv.business.util.EntityUtils;
import it.csi.epay.epaypacatalogsrv.model.CodiceVersamento;
import it.csi.epay.epaypacatalogsrv.model.MappaturaCodiceVersamentoEpaypa;
import it.csi.epay.epaypacatalogsrv.model.MappaturaRiferimentoContabileEpaypa;
import it.csi.epay.epaypacatalogsrv.model.MappaturaRiferimentoContabileEpaypa_;
import it.csi.epay.epaypacatalogsrv.model.RiferimentoContabile;
import it.csi.epay.epaypacatalogsrv.model.RiferimentoContabile_;
import it.csi.epay.epaypacatalogsrv.repository.CodiceVersamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.MappaturaCodiceVersamentoEpaypaRepository;
import it.csi.epay.epaypacatalogsrv.repository.MappaturaRiferimentoContabileEpaypaRepository;
import it.csi.epay.epaypacatalogsrv.repository.RiferimentoContabileRepository;
import it.csi.epay.epaypacatalogsrv.repository.StatoAggiornamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.TipologiaDatoSpecificoRiscossioneRepository;
import it.csi.epay.epaypacatalogsrv.vo.Constants;
import it.csi.epay.epaypacatalogsrv.vo.migrazione.CodiceEntitaMigrazione;


/**
 *
 */
@Service
public class RiferimentoContabileMigrator extends EntityMigrator<MappaturaRiferimentoContabileEpaypa, RiferimentoContabile> {

    @Autowired
    private RiferimentoContabileRepository riferimentoContabileRepository;

    @Autowired
    private CodiceVersamentoRepository codiceVersamentoRepository;

    @Autowired
    private TipologiaDatoSpecificoRiscossioneRepository tipologiaDatoSpecificoRiscossioneRepository;

    @Autowired
    private MappaturaRiferimentoContabileEpaypaRepository mappaturaRiferimentoContabileEpaypaRepository;

    @Autowired
    private MappaturaCodiceVersamentoEpaypaRepository mappaturaCodiceVersamentoEpaypaRepository;

    @Autowired
    private StatoAggiornamentoRepository statoAggiornamentoRepository;

    @Autowired
    private MigratorHelper migratorHelper;

    @Override
    protected Class<MappaturaRiferimentoContabileEpaypa> getPaClass () {
        return MappaturaRiferimentoContabileEpaypa.class;
    }

    @Override
    protected Class<RiferimentoContabile> getCatalogClass () {
        return RiferimentoContabile.class;
    }

    @Override
    protected void configureMapper () {
        getDozerBeanMapper ().addMapping ( new BeanMappingBuilder () {

            @Override
            protected void configure () {
                mapping ( RiferimentoContabile.class, MappaturaRiferimentoContabileEpaypa.class )
                    .fields ( RiferimentoContabile_.id.getName (), MappaturaRiferimentoContabileEpaypa_.id.getName () )
                    .fields ( RiferimentoContabile_.dataInizioValidita.getName (), MappaturaRiferimentoContabileEpaypa_.dataInizioValidita.getName () )
                    .fields ( RiferimentoContabile_.dataFineValidita.getName (), MappaturaRiferimentoContabileEpaypa_.dataFineValidita.getName () )
                    .fields ( RiferimentoContabile_.codiceTipologiaDatoSpecificoRiscossione.getName (),
                        MappaturaRiferimentoContabileEpaypa_.codiceTipologiaDatoSpecificoRiscossione.getName () )
                    .fields ( RiferimentoContabile_.datoSpecificoRiscossione.getName (),
                        MappaturaRiferimentoContabileEpaypa_.datoSpecificoRiscossione.getName () )
                    .fields ("tassonomia.descrTipoServizio",
                        MappaturaRiferimentoContabileEpaypa_.descrizioneDatoSpecificoRiscossione.getName () )
                    .fields ( RiferimentoContabile_.annoEsercizio.getName (), MappaturaRiferimentoContabileEpaypa_.annoEsercizio.getName () )
                    .fields ( RiferimentoContabile_.annoAccertamento.getName (), MappaturaRiferimentoContabileEpaypa_.annoAccertamento.getName () )
                    .fields ( RiferimentoContabile_.numeroAccertamento.getName (), MappaturaRiferimentoContabileEpaypa_.numeroAccertamento.getName () )
                    .fields ( RiferimentoContabile_.numeroCapitolo.getName (), MappaturaRiferimentoContabileEpaypa_.numeroCapitolo.getName () )
                    .fields ( RiferimentoContabile_.numeroArticolo.getName (), MappaturaRiferimentoContabileEpaypa_.numeroArticolo.getName () )
                    .fields ( RiferimentoContabile_.livelloPdc.getName (), MappaturaRiferimentoContabileEpaypa_.livelloPdc.getName () )
                    .fields ( RiferimentoContabile_.titolo.getName (), MappaturaRiferimentoContabileEpaypa_.titolo.getName () )
                    .fields ( RiferimentoContabile_.tipologia.getName (), MappaturaRiferimentoContabileEpaypa_.tipologia.getName () )
                    .fields ( RiferimentoContabile_.categoria.getName (), MappaturaRiferimentoContabileEpaypa_.categoria.getName () );
            }
        } );
    }

    @Override
    protected List<MappaturaRiferimentoContabileEpaypa> caricaListaPA ( MigrationContext context ) {

        List<MappaturaRiferimentoContabileEpaypa> mappaturaRiferimentoContabileEpaypas = mappaturaRiferimentoContabileEpaypaRepository.findAll ();
        for ( MappaturaRiferimentoContabileEpaypa el: mappaturaRiferimentoContabileEpaypas ) {
            if ( StringUtils.isEmpty ( el.getCodiceTipologiaDatoSpecificoRiscossione () ) ) {
                throw new DataMigrationFatalErrorException (
                    "Missing codice tipologia dato specifico riscossione on riferimento contabile with id: " + el.getId () );
            }

            MappaturaCodiceVersamentoEpaypa mappaturaCodiceVersamentoEpaypa
                = mappaturaCodiceVersamentoEpaypaRepository.findByIdCodiceVersamentoOld ( el.getIdCodiceVersamento () );

            if ( mappaturaCodiceVersamentoEpaypa == null ) {
                throw new DataMigrationFatalErrorException ( "Missing codice versamento of riferimento contabile with id: " + el.getId () );
            }
        }
        return mappaturaRiferimentoContabileEpaypas;

    }

    @Override
    protected List<RiferimentoContabile> caricaListaCAT ( MigrationContext context ) {
        List<RiferimentoContabile> lista = riferimentoContabileRepository.findAll ();
        List<RiferimentoContabile> listaFiltrata = new ArrayList<> ();

        for ( RiferimentoContabile el: lista ) {
            if ( !EntityUtils.isTrue ( el.getFlagAnnullato () ) ) {
                listaFiltrata.add ( el );
            }
        }

        return listaFiltrata;
    }

    @Override
    public RiferimentoContabile map ( MappaturaRiferimentoContabileEpaypa entityPA, MigrationContext context ) {
        RiferimentoContabile mapped = getDozerBeanMapper ().map ( entityPA, RiferimentoContabile.class );

        CodiceVersamento codiceVersamento = getCodiceVersamentoReferenziato ( entityPA );

        mapped.setCodiceVersamento ( codiceVersamento );

        return mapped;
    }

    private CodiceVersamento getCodiceVersamentoReferenziato ( MappaturaRiferimentoContabileEpaypa entityPA ) {

        MappaturaCodiceVersamentoEpaypa mappaturaCodiceVersamentoEpaypa
            = mappaturaCodiceVersamentoEpaypaRepository.findByIdCodiceVersamentoOld ( entityPA.getIdCodiceVersamento () );

        Long idEnteNuovo = migratorHelper.getIdEnteCATDaIdEntePA ( mappaturaCodiceVersamentoEpaypa.getIdEnte () );
        List<CodiceVersamento> codiciVersamento
            = codiceVersamentoRepository.findByEnte_IdAndCodice ( idEnteNuovo, mappaturaCodiceVersamentoEpaypa.getCodiceNuovo () );

        CodiceVersamento codiceVersamento = null;
        for ( CodiceVersamento candidate: codiciVersamento ) {
            if ( !EntityUtils.isTrue ( candidate.getFlagAnnullato () ) ) {
                if ( codiceVersamento == null ) {
                    codiceVersamento = candidate;
                } else {
                    throw new DataMigrationFatalErrorException ( "codice versamento di catalog referenziato non univoco per id ente " + idEnteNuovo
                        + " e codice " + mappaturaCodiceVersamentoEpaypa.getCodiceNuovo () );
                }
            }
        }
        return codiceVersamento;
    }

    @Override
    protected boolean match ( MappaturaRiferimentoContabileEpaypa entityPA, RiferimentoContabile entityCAT ) {

        CodiceVersamento codiceVersamentoReferenziato = getCodiceVersamentoReferenziato ( entityPA );

        return ( codiceVersamentoReferenziato.getEnte ().getId ().equals ( entityCAT.getCodiceVersamento ().getEnte ().getId () )
            && codiceVersamentoReferenziato.getId ().equals ( entityCAT.getCodiceVersamento ().getId () )
            && entityPA.getAnnoEsercizio ().equals ( entityCAT.getAnnoEsercizio () )
            && entityPA.getDatoSpecificoRiscossione ().equals ( entityCAT.getDatoSpecificoRiscossione () )
            && entityPA.getDataInizioValidita ().getTime () == entityCAT.getDataInizioValidita ().getTime () );
    }

    @Override
    protected void preInsert ( RiferimentoContabile entity, MigrationContext context ) {

        entity.setId ( null );
        entity.setUtenteInserimento ( Constants.CODICE_UTENTE_MIGRAZIONE );
        entity.setDataInserimento ( context.getStartTimestamp () );
        entity.setUtenteModifica ( Constants.CODICE_UTENTE_MIGRAZIONE );
        entity.setDataModifica ( context.getStartTimestamp () );
        entity.setDescrizioneErroreAggiornamento ( "Sincronizzazione non eseguita" );
        entity.setStatoAggiornamento ( statoAggiornamentoRepository.findOneByCodice ( "NONE" ) );
        entity.setFlagAnnullato ( null );
        String chiave = UUID.randomUUID ().toString ();
        entity.setChiaveIntersistema ( chiave );
        

//        entity.setTipologiaDatoSpecificoRiscossione (
//            tipologiaDatoSpecificoRiscossioneRepository.findOneByCodice ( entity.getTipologiaDatoSpecificoRiscossione ().getCodice () ) );
    }

    @Override
    protected RiferimentoContabile salva ( RiferimentoContabile entityCAT, MigrationContext context ) {
        RiferimentoContabile inserito = riferimentoContabileRepository.save ( entityCAT );

        context.info ( CodiceEntitaMigrazione.RIFERIMENTO_CONTABILE,
            "inserito riferimento contabile con ID " + inserito.getId () +
                " per codice versamento " + inserito.getCodiceVersamento ().getCodice () +
                " per ente " + inserito.getCodiceVersamento ().getEnte ().getCodiceFiscale () );

        return inserito;
    }

    @Override
    public void eliminaOggettiMigrati ( MigrationContext context ) {

        List<RiferimentoContabile> listaDaEliminare = riferimentoContabileRepository.findByUtenteModifica ( Constants.CODICE_UTENTE_MIGRAZIONE );
        for ( RiferimentoContabile daEliminare: listaDaEliminare ) {

            riferimentoContabileRepository.delete ( daEliminare );

            context.info ( CodiceEntitaMigrazione.RIFERIMENTO_CONTABILE,
                "eliminato l'oggetto con ID " + daEliminare.getId () );
        }
    }

}
