/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.manager;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.csi.epay.epayservices.integration.db.entities.EpayDEsitoChiamataEsterna;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.EsitoChiamataEsterna;


@Stateless ( name = "EsitoChiamataEsternaManager", mappedName = "EsitoChiamataEsternaManager" )
public class EsitoChiamataEsternaManager extends _Manager {

	@PersistenceContext
	private EntityManager entityManager;

    @TransactionAttribute ( TransactionAttributeType.REQUIRES_NEW )
    public EsitoChiamataEsterna ricerca ( String codice ) throws NoDataException, IllegalAccessException {
        EpayDEsitoChiamataEsterna epayDEsitoChiamataEsterna = entityManager.find ( EpayDEsitoChiamataEsterna.class, codice );

        /*
         * TypedQuery<EpayDEsitoChiamataEsterna> query = entityManager.createNamedQuery ( "EpayTTracciabilitaChiamanteEsterno.findByIdTransazione",
         * EpayDEsitoChiamataEsterna.class ); query.setParameter ( "codice", codice ); EpayDEsitoChiamataEsterna epayDEsitoChiamataEsterna =
         * query.getSingleResult ();
         */

        EsitoChiamataEsterna esitoChiamataEsterna = mappaTracciabilitaChiamanteEsterno ( epayDEsitoChiamataEsterna );

        return esitoChiamataEsterna;

	}

    private EsitoChiamataEsterna mappaTracciabilitaChiamanteEsterno ( EpayDEsitoChiamataEsterna epayDEsitoChiamataEsterna ) {

        EsitoChiamataEsterna esitoChiamataEsterna = map ( epayDEsitoChiamataEsterna, EsitoChiamataEsterna.class );

        return esitoChiamataEsterna;
	}


}
