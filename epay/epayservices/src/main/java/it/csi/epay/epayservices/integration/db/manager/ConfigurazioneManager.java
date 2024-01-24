/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.manager;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import it.csi.epay.epayservices.integration.db.entities.EpayTConfigurazione;
import it.csi.epay.epayservices.model.Configurazione;
import it.csi.epay.epayservices.model.Ente;


@Stateless ( name = "ConfigurazioneManager", mappedName = "ConfigurazioneManager" )
public class ConfigurazioneManager extends _Manager {

    @PersistenceContext
    private EntityManager entityManager;

    public Configurazione getConfigurazione ( String codice, String codiceFiscale ) {
        try {
            TypedQuery<EpayTConfigurazione> query = entityManager.createNamedQuery ( "EpayTConfigurazione.findByCodice", EpayTConfigurazione.class );
            query.setParameter ( "codice", codice );
            query.setParameter ( "codiceFiscale", codiceFiscale );
            EpayTConfigurazione tConfigurazione = query.getSingleResult ();
            Configurazione configurazione = mappaConfigurazione ( tConfigurazione );
            return configurazione;
        } catch (NoResultException e) {
            return null;
        }
    }

    private Configurazione mappaConfigurazione ( EpayTConfigurazione tConfigurazione ) {
        Configurazione configurazione = map ( tConfigurazione, Configurazione.class );
        configurazione.setEnte ( map ( tConfigurazione.getEpayTEnti (), Ente.class ) );
        configurazione.setCodice ( tConfigurazione.getCodice () );
        configurazione.setValore ( tConfigurazione.getValore () );
        configurazione.setDescrizione ( tConfigurazione.getDescrizione () );

        return configurazione;
    }


}
