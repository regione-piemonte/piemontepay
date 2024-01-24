/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import it.csi.epay.epayservices.integration.db.manager.EnteManager;
import it.csi.epay.epayservices.interfaces.ejb.EnteFacade;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.Ente;

@Stateless(name="EnteFacade", mappedName = "Ente")
public class EnteBean extends _BaseBean implements EnteFacade {

    @EJB
    private EnteManager enteManager;

    @Override
    public List<Ente> listaEntiConTasse () {
        List<Ente> listaEnti = enteManager.getListaEntiConTasse ();
        return listaEnti;
    }

    @Override
    public List<Ente> listaEntiConTasse ( String nomeEnte ) {
        List<Ente> listaEnti = enteManager.getListaEntiConTasse ( nomeEnte );
        return listaEnti;
    }

    @Override
    public List<Ente> listaEntiConPagamentiSpontanei () {
        List<Ente> listaEnti = enteManager.getListaEntiConPagamentiSpontanei ();
        return listaEnti;
    }
    
    @Override
    public List<Ente> getListaEntiConTasseVisibili ( String nomeEnte ) {
        List<Ente> listaEnti = enteManager.getListaEntiConTasseVisibili (nomeEnte);
        return listaEnti;
    }

    @Override
    public List<Ente> listaEntiConPagamentiConIuvPagabili () {
        List<Ente> listaEnti = enteManager.getListaEntiConPagamentiConIuvPagabili ();
        return listaEnti;
    }

    @Override
    public Ente ricerca(Long id) throws IllegalArgumentException {
        Ente ente  = enteManager.getById(id);
        return ente;
    }

    @Override
    public Ente ricerca ( String codiceFiscale ) throws NoDataException {
        Ente ente = enteManager.getByCF ( codiceFiscale );
        return ente;
    }

    @Override
    public Ente inserisci(Ente ente) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Ente aggiorna(Ente ente) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        return null;
    }

}
