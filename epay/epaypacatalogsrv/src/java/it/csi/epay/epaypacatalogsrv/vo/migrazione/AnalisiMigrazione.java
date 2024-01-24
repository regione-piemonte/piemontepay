/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.vo.migrazione;

import java.util.List;

import it.csi.epay.epaypacatalogsrv.business.util.ComparatoreDiListeDiverse.DifferenzaFraListeDiverse;


/**
 *
 */

public class AnalisiMigrazione<EntityPA, EntityCAT> {

    private List<EntityPA> listaPa;

    private List<EntityCAT> listaCatalog;

    private List<EntityCAT> listaPaMappateCatalog;

    private List<EntityCAT> listaPaMappateCatalogDaInserire;

    private DifferenzaFraListeDiverse<EntityPA, EntityCAT> esitoComparazione;

    public AnalisiMigrazione () {
        // TODO Auto-generated constructor stub
    }

    public List<EntityCAT> getListaPaMappateCatalogDaInserire () {
        return listaPaMappateCatalogDaInserire;
    }

    public void setListaPaMappateCatalogDaInserire ( List<EntityCAT> listaPaMappateCatalogDaInserire ) {
        this.listaPaMappateCatalogDaInserire = listaPaMappateCatalogDaInserire;
    }

    public List<EntityPA> getListaPa () {
        return listaPa;
    }

    public void setListaPa ( List<EntityPA> listaPa ) {
        this.listaPa = listaPa;
    }

    public List<EntityCAT> getListaCatalog () {
        return listaCatalog;
    }

    public void setListaCatalog ( List<EntityCAT> listaCatalog ) {
        this.listaCatalog = listaCatalog;
    }

    public List<EntityCAT> getListaPaMappateCatalog () {
        return listaPaMappateCatalog;
    }

    public void setListaPaMappateCatalog ( List<EntityCAT> listaPaMappateCatalog ) {
        this.listaPaMappateCatalog = listaPaMappateCatalog;
    }

    public DifferenzaFraListeDiverse<EntityPA, EntityCAT> getEsitoComparazione () {
        return esitoComparazione;
    }

    public void setEsitoComparazione ( DifferenzaFraListeDiverse<EntityPA, EntityCAT> esitoComparazione ) {
        this.esitoComparazione = esitoComparazione;
    }

}
