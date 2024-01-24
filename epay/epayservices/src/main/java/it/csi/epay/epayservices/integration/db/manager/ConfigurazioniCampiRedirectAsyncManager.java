/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.integration.db.manager;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import it.csi.epay.epayservices.integration.db.entities.EpayCCampiRedirectAsync;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.ConfigurazioniCampiRedirectAsync;

/**
 *
 */
@Stateless(name = "ConfigurazioniCampiRedirectAsyncManager", mappedName = "ConfigurazioniCampiRedirectAsyncManager")
public class ConfigurazioniCampiRedirectAsyncManager extends _Manager {

	@PersistenceContext
	private EntityManager entityManager;

	public List<ConfigurazioniCampiRedirectAsync> getConfigurazioni() throws NoDataException {
		List<ConfigurazioniCampiRedirectAsync> configurazioni = new ArrayList<>();
		try {
			TypedQuery<EpayCCampiRedirectAsync> query = entityManager
					.createNamedQuery("EpayCCampiRedirectAsync.findAll", EpayCCampiRedirectAsync.class);
			List<EpayCCampiRedirectAsync> cConfig = query.getResultList();

			for (EpayCCampiRedirectAsync c : cConfig) {
				configurazioni.add(map(c, ConfigurazioniCampiRedirectAsync.class));
			}
		} catch (NoResultException error) {
			throw new NoDataException("Nessuna configurazione trovata per i campi della redirect. ", error);
		} catch (IllegalArgumentException e) {
			throw new NoDataException("Errore nella ricerca dei campi della redirect. ", e);
		}

		return configurazioni;
	}
}
