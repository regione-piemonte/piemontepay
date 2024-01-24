/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.interfaces.ejb;

import java.util.List;

import javax.ejb.Remote;

import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.Ente;

@Remote
public interface EnteFacade {

    /**
     * Elenco di tutti gli enti
     */
    public List<Ente> listaEntiConTasse ();

    /**
     * Elenco degli enti con nomeEnte
     */
    public List<Ente> listaEntiConTasse ( String nomeEnte );

    /**
     * Elenco degli enti con pagamenti spontanei
     */
    public List<Ente> listaEntiConPagamentiSpontanei ();

    
    /**
     * Elenco degli enti con pagamenti spontanei visibili da sportello
     */
    public List<Ente> getListaEntiConTasseVisibili (String name);

    /**
     * Elenco degli enti con pagamenti con iuv da pagare
     */
    public List<Ente> listaEntiConPagamentiConIuvPagabili ();

    /**
     * Cerca un Ente per id
     */
    public Ente ricerca(Long Id) throws IllegalArgumentException;


    /**
     * Cerca un Ente per nome
     */
    public Ente ricerca ( String Name ) throws NoDataException;

    /**
     * Inserisce ente
     */
    public Ente inserisci(Ente ente) throws IllegalArgumentException;

    /**
     * Aggiorna ente
     */
    public Ente aggiorna(Ente ente) throws IllegalArgumentException;

}
