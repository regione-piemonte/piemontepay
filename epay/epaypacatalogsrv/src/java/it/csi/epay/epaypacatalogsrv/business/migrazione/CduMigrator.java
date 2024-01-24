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

import it.csi.epay.epaypacatalogsrv.model.Cdu;
import it.csi.epay.epaypacatalogsrv.model.Cdu_;
import it.csi.epay.epaypacatalogsrv.model.EpaypaTCdu;
import it.csi.epay.epaypacatalogsrv.model.EpaypaTCdu_;
import it.csi.epay.epaypacatalogsrv.repository.CategoriaCduRepository;
import it.csi.epay.epaypacatalogsrv.repository.CduRepository;
import it.csi.epay.epaypacatalogsrv.repository.EpaypaTCduRepository;
import it.csi.epay.epaypacatalogsrv.vo.migrazione.CodiceEntitaMigrazione;


/**
 *
 */
@Service
public class CduMigrator extends EntityMigrator<EpaypaTCdu, Cdu> {

    @Autowired
    private CduRepository epaycatTCduRepository;

    @Autowired
    private CategoriaCduRepository epaycatTCategoriaCduRepository;

    @Autowired
    private EpaypaTCduRepository epaypaTCduRepository;

    @Override
    public Class<EpaypaTCdu> getPaClass () {
        return EpaypaTCdu.class;
    }

    @Override
    public Class<Cdu> getCatalogClass () {
        return Cdu.class;
    }

    @Override
    public boolean match ( EpaypaTCdu entityPA, Cdu entityCAT ) {

        return entityPA.getCodCdu ().equals ( entityCAT.getCodice () );
    }

    @Override
    public void configureMapper () {
        getDozerBeanMapper ().addMapping ( new BeanMappingBuilder () {

            @Override
            protected void configure () {
                mapping ( Cdu.class, EpaypaTCdu.class )
                    .fields (
                        Cdu_.id.getName (), EpaypaTCdu_.idCdu.getName () )
                    .fields (
                        Cdu_.codice.getName (), EpaypaTCdu_.codCdu.getName () )
                    .fields (
                        Cdu_.descrizione.getName (), EpaypaTCdu_.descrizione.getName () )
                    .fields (
                        "categoria.codice", EpaypaTCdu_.codCategoriaCdu.getName () );
            }
        } );
    }

    @Override
    public void preInsert ( Cdu entity, MigrationContext context ) {

        entity.setId ( entity.getId () + MigratorHelper.OFFSET_ID );

        entity.setCategoria ( epaycatTCategoriaCduRepository.findOneByCodice ( entity.getCategoria ().getCodice () ) );
    }

    @Override
    public Cdu salva ( Cdu entityCAT, MigrationContext context ) {
        Cdu inserito = epaycatTCduRepository.save ( entityCAT );

        context.info ( CodiceEntitaMigrazione.CDU,
            "inserito CDU con codice " + inserito.getCodice () + " e ID " + inserito.getId () );

        return inserito;
    }

    @Override
    public List<EpaypaTCdu> caricaListaPA ( MigrationContext context ) {
        return epaypaTCduRepository.findAll ();
    }

    @Override
    public List<Cdu> caricaListaCAT ( MigrationContext context ) {
        return epaycatTCduRepository.findAll ();
    }

    @Override
    public void eliminaOggettiMigrati ( MigrationContext context ) {

        // List<Cdu> listaDaEliminare = epaycatTCduRepository.findByUtenteInserimento ( Constants.CODICE_UTENTE_MIGRAZIONE );
        List<Cdu> listaDaEliminare = new ArrayList<> ();

        //        context.warn ( CodiceEntitaMigrazione.CDU, "Non e' stato possibile individuare i CDU importati. Nessun CDU verra' eliminato" );

        for ( Cdu candidate: epaycatTCduRepository.findAll () ) {
            if ( candidate.getId () >= MigratorHelper.OFFSET_ID ) {
                listaDaEliminare.add ( candidate );
            }
        }

        for ( Cdu daEliminare: listaDaEliminare ) {

            epaycatTCduRepository.delete ( daEliminare );

            context.info ( CodiceEntitaMigrazione.CDU,
                "eliminato l'oggetto con codice " + daEliminare.getCodice () );
        }
    }

}
