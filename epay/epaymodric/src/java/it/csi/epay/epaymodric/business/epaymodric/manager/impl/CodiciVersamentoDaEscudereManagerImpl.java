/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager.impl;

import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.epay.epaymodric.business.epaymodric.manager.CodiciVersamentoDaEscudereManager;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTCodiciversamentoEsclusione;
import it.csi.epay.epaymodric.business.epaymodric.repository.CodiciVersamentoDaEscludereRepository;


/**
 * @author vsgro
 */
@Service
public class CodiciVersamentoDaEscudereManagerImpl implements CodiciVersamentoDaEscudereManager {

    private final Logger logger = LogManager.getLogger ( this.getClass () );

    @Autowired
    private CodiciVersamentoDaEscludereRepository repository;

    /**
     * restituisce la lista di codici versamento da escludere per l'ente passato come parametro
     */
    @Override
    public List<String> leggiCodiciVersamentoDaEscudere ( String idEnte ) {
        logger.info ( "CodiciVersamentoDaEscudereServiceImpl.findByIdEnte: INIZIO" );
        List<String> codiciVersamentoDaEscludere = new LinkedList<String> ();
        List<CblTCodiciversamentoEsclusione> lista =  repository.findByIdEnte (idEnte);
		for ( CblTCodiciversamentoEsclusione cblTCodiciversamentoEsclusione : lista ) {
			codiciVersamentoDaEscludere.add ( cblTCodiciversamentoEsclusione.getCodiceVersamento () );
		}
        logger.info ( "CodiciVersamentoDaEscudereServiceImpl.findByIdEnte: FINE" );
        return codiciVersamentoDaEscludere;
    }


}
