/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.business.migrazione;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.epay.epaypacatalogsrv.business.util.ComparatoreDiListeDiverse;
import it.csi.epay.epaypacatalogsrv.business.util.ComparatoreDiListeDiverse.ComparatoreDiElementiDiversi;
import it.csi.epay.epaypacatalogsrv.business.util.ComparatoreDiListeDiverse.DifferenzaFraListeDiverse;
import it.csi.epay.epaypacatalogsrv.vo.migrazione.AnalisiMigrazione;


/**
 *
 */

public abstract class EntityMigrator<EntityPA, EntityCAT> {

    @Autowired
    private Mapper dozerBeanMapper;

    public AnalisiMigrazione<EntityPA, EntityCAT> migra ( MigrationContext context ) {
        AnalisiMigrazione<EntityPA, EntityCAT> analisi = analizza ( context );

        for ( EntityCAT daInserire: analisi.getListaPaMappateCatalogDaInserire () ) {
            this.salva ( daInserire, context );
            this.postInsert ( daInserire, context );
        }

        return analisi;
    }

    public abstract void eliminaOggettiMigrati ( MigrationContext context );

    protected abstract Class<EntityPA> getPaClass ();

    protected abstract Class<EntityCAT> getCatalogClass ();

    protected abstract boolean match ( EntityPA entityPA, EntityCAT entityCAT );

    protected abstract void configureMapper ();

    protected abstract EntityCAT salva ( EntityCAT entityCAT, MigrationContext context );

    protected abstract void preInsert ( EntityCAT entity, MigrationContext context );

    protected void postInsert ( EntityCAT entity, MigrationContext context ) {
        // NOP
    }

    @PostConstruct
    protected void init () {
        configureMapper ();
    }

    protected EntityCAT map ( EntityPA entityPA, MigrationContext context ) {
        return getDozerBeanMapper ().map ( entityPA, getCatalogClass () );
    }

    protected abstract List<EntityPA> caricaListaPA ( MigrationContext context );

    protected abstract List<EntityCAT> caricaListaCAT ( MigrationContext context );

    private AnalisiMigrazione<EntityPA, EntityCAT> analizza ( MigrationContext context ) {

        List<EntityPA> listaPA = caricaListaPA ( context );
        List<EntityCAT> listaCAT = caricaListaCAT ( context );

        AnalisiMigrazione<EntityPA, EntityCAT> output = new AnalisiMigrazione<> ();

        output.setListaCatalog ( listaCAT );
        output.setListaPa ( listaPA );

        List<EntityCAT> listaPaMappateCatalog = new ArrayList<>();
        for ( EntityPA o: listaPA ) {
            listaPaMappateCatalog.add ( map ( o, context ) );
        }

        output.setListaPaMappateCatalog ( listaPaMappateCatalog );

        DifferenzaFraListeDiverse<EntityPA, EntityCAT> esitoComparazioneListe
            = ComparatoreDiListeDiverse.compareLists ( listaPA, listaCAT, new ComparatoreDiElementiDiversi<EntityPA, EntityCAT> () {

                @Override
                public boolean compara ( EntityPA fromSouce, EntityCAT onTarget ) {
                    return match ( fromSouce, onTarget );
                }

            } );

        output.setEsitoComparazione ( esitoComparazioneListe );

        List<EntityCAT> listaPaMappateCatalogDaInserire = new ArrayList<> ();
        for ( EntityPA o: esitoComparazioneListe.getElementiSoloNellaPrima () ) {
            listaPaMappateCatalogDaInserire.add ( map ( o, context ) );
        }

        output.setListaPaMappateCatalogDaInserire ( listaPaMappateCatalogDaInserire );

        for ( EntityCAT o: output.getListaPaMappateCatalogDaInserire () ) {
            preInsert ( o, context );
        }

        return output;
    }

    protected DozerBeanMapper getDozerBeanMapper () {
        if ( dozerBeanMapper instanceof DozerBeanMapper ) {
            return (DozerBeanMapper) dozerBeanMapper;
        } else {
            throw new RuntimeException ( "invalid mapper type" );
        }
    }

}
