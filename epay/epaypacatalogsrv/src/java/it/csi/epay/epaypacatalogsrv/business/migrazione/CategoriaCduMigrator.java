/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.business.migrazione;

import java.util.ArrayList;
import java.util.List;

import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.epay.epaypacatalogsrv.model.CategoriaCdu;
import it.csi.epay.epaypacatalogsrv.model.CategoriaCdu_;
import it.csi.epay.epaypacatalogsrv.model.EpaypaDCategoriaCdu;
import it.csi.epay.epaypacatalogsrv.model.EpaypaDCategoriaCdu_;
import it.csi.epay.epaypacatalogsrv.repository.CategoriaCduRepository;
import it.csi.epay.epaypacatalogsrv.repository.EpaypaDCategoriaCduRepository;
import it.csi.epay.epaypacatalogsrv.vo.migrazione.CodiceEntitaMigrazione;


/**
 *
 */
@Service
public class CategoriaCduMigrator extends EntityMigrator<EpaypaDCategoriaCdu, CategoriaCdu> {

    @Autowired
    private CategoriaCduRepository epaycatDCategoriaCduRepository;

    @Autowired
    private EpaypaDCategoriaCduRepository epaypaDCategoriaCduRepository;

    @Override
    public Class<EpaypaDCategoriaCdu> getPaClass () {
        return EpaypaDCategoriaCdu.class;
    }

    @Override
    public Class<CategoriaCdu> getCatalogClass () {
        return CategoriaCdu.class;
    }

    @Override
    public boolean match ( EpaypaDCategoriaCdu entityPA, CategoriaCdu entityCAT ) {

        return entityPA.getCodCategoriaCdu ().equals ( entityCAT.getCodice () );
    }

    @Override
    public void configureMapper () {
        getDozerBeanMapper ().addMapping ( new BeanMappingBuilder () {

            @Override
            protected void configure () {
                mapping ( CategoriaCdu.class, EpaypaDCategoriaCdu.class )
                    .fields (
                        CategoriaCdu_.id.getName (), EpaypaDCategoriaCdu_.idCategoriaCdu.getName () )
                    .fields (
                        CategoriaCdu_.codice.getName (), EpaypaDCategoriaCdu_.codCategoriaCdu.getName () )
                    .fields (
                        CategoriaCdu_.descrizione.getName (), EpaypaDCategoriaCdu_.descrizione.getName () );
            }
        } );
    }

    @Override
    public void preInsert ( CategoriaCdu entity, MigrationContext context ) {

        entity.setId ( entity.getId () + MigratorHelper.OFFSET_ID );

    }

    @Override
    public CategoriaCdu salva ( CategoriaCdu entityCAT, MigrationContext context ) {
        CategoriaCdu inserito = epaycatDCategoriaCduRepository.save ( entityCAT );

        context.info ( CodiceEntitaMigrazione.CATEGORIA_CDU,
            "inserita categoria CDU con codice " + inserito.getCodice () + " e ID " + inserito.getId () );

        return inserito;
    }

    @Override
    public List<EpaypaDCategoriaCdu> caricaListaPA ( MigrationContext context ) {
        return epaypaDCategoriaCduRepository.findAll ();
    }

    @Override
    public List<CategoriaCdu> caricaListaCAT ( MigrationContext context ) {
        return epaycatDCategoriaCduRepository.findAll ();
    }

    @Override
    public void eliminaOggettiMigrati ( MigrationContext context ) {

        // List<CategoriaCdu> listaDaEliminare = epaycatDCategoriaCduRepository.findByUtenteInserimento ( Constants.CODICE_UTENTE_MIGRAZIONE );
        List<CategoriaCdu> listaDaEliminare = new ArrayList<> ();
        //
        //        context.warn ( CodiceEntitaMigrazione.CATEGORIA_CDU,
        //            "Non e' stato possibile individuare le categorie CDU importate. Nessuna categoria CDU verra' eliminata" );

        for ( CategoriaCdu candidate: epaycatDCategoriaCduRepository.findAll () ) {
            if ( candidate.getId () >= MigratorHelper.OFFSET_ID ) {
                listaDaEliminare.add ( candidate );
            }
        }

        for ( CategoriaCdu daEliminare: listaDaEliminare ) {

            epaycatDCategoriaCduRepository.delete ( daEliminare );

            context.info ( CodiceEntitaMigrazione.CATEGORIA_CDU,
                "eliminato l'oggetto con codice " + daEliminare.getCodice () );
        }
    }
}
