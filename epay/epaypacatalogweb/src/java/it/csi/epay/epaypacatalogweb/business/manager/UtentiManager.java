/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.business.manager;

import java.util.List;

import it.csi.epay.epaypacatalogweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaypacatalogweb.model.utente.ModificaUtenteVO;
import it.csi.epay.epaypacatalogweb.model.utente.RicercaUtenteFiltroVO;
import it.csi.epay.epaypacatalogweb.model.utente.RisultatoRicercaUtenteVO;
import it.csi.epay.epaypacatalogweb.model.utente.UtenteVO;
import it.csi.epay.epaypacatalogweb.vo.UserVO;



public interface UtentiManager {

    List<UserVO> ricerca();

    UserVO get ( int id );

    void aggiungi ( UserVO userVO );

    void save ();

    void modifica ( UserVO user );

    boolean elimina ( int id );

    List<UserVO> search ( UserVO userVO );

    List<RisultatoRicercaUtenteVO> ricerca ( RicercaUtenteFiltroVO filtro ) throws OperationFailedException;

    void elimina ( Long id ) throws OperationFailedException;

    void aggiorna ( ModificaUtenteVO userInput ) throws OperationFailedException;

    void inserisci ( ModificaUtenteVO userInput ) throws OperationFailedException;

    UtenteVO merge ( Long id, ModificaUtenteVO input ) throws OperationFailedException;

    UtenteVO istanzia ();

    UtenteVO get ( Long id ) throws OperationFailedException;

}
