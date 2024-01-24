/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.manager;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.csi.epay.epayservices.integration.db.entities.EpayTTransazioneMdp;
import it.csi.epay.epayservices.model.TransazioneMdp;

@Stateless(name="TransazioneMdpManager", mappedName = "TransazioneMdpManager")
public class TransazioneMdpManager extends _Manager {

	@PersistenceContext
	private EntityManager entityManager;
	
	//@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void inserisci(TransazioneMdp transazione) {
		EpayTTransazioneMdp tPagamentoMdp = map(transazione, EpayTTransazioneMdp.class);
		entityManager.persist(tPagamentoMdp);
	}

	public void aggiorna(TransazioneMdp transazione) {
		EpayTTransazioneMdp transazioneMdp = entityManager.find(EpayTTransazioneMdp.class, transazione.getIdTransazione());
		map(transazione, transazioneMdp);
	}	
	
	public TransazioneMdp ricerca(final String idTransazione) {
		EpayTTransazioneMdp transazioneMdp = entityManager.find(EpayTTransazioneMdp.class, idTransazione);
		return map(transazioneMdp, TransazioneMdp.class);
	}
	
}
