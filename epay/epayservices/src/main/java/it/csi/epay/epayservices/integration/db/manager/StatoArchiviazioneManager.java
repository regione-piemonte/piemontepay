/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.manager;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import it.csi.epay.epayservices.integration.db.entities.EpayTStatoArchiviazione;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.StatoArchiviazione;


@Stateless ( name = "StatoArchiviazioneManager", mappedName = "StatoArchiviazioneManager" )
public class StatoArchiviazioneManager extends _Manager {

	@PersistenceContext
	private EntityManager entityManager;

	@EJB
	private ConfigurazioneManager configurazioneManager;

	public StatoArchiviazione findById ( Long id ) {
		EpayTStatoArchiviazione tStatoArchiviazione = entityManager.find ( EpayTStatoArchiviazione.class, id );
		return map ( tStatoArchiviazione, StatoArchiviazione.class );
	}

	public StatoArchiviazione findByIdEnteAndCF ( Long idEnte, String codiceFiscalePagatore ) throws NoDataException {
		try {
			TypedQuery<EpayTStatoArchiviazione> query
				= entityManager.createNamedQuery ( "EpayTStatoArchiviazione.findByIdEnteAndCodiceFiscale", EpayTStatoArchiviazione.class );
			query.setParameter ( "idEnte", idEnte );
			query.setParameter ( "codiceFiscale", codiceFiscalePagatore );
			EpayTStatoArchiviazione tStatoArchiviazione = query.getSingleResult ();
			return map ( tStatoArchiviazione, StatoArchiviazione.class );

		} catch ( NoResultException nre ) {
			throw new NoDataException ( "Nessun stato di archiviazione trovato", nre.getCause () );
		}
	}
}
