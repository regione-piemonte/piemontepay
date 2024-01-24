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

import it.csi.epay.epayservices.integration.db.entities.EpayDStatoErrore;
import it.csi.epay.epayservices.integration.db.entities.EpayTErrori;
import it.csi.epay.epayservices.model.Errori;

@Stateless(name="ErroriManager", mappedName = "ErroriManager")
public class ErroriManager extends _Manager{
	
	@PersistenceContext
	private EntityManager entityManager;	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void inserisci(Errori errore) {
		
		EpayTErrori tErrori = new EpayTErrori();
		tErrori.setData(getTimestamp());
		tErrori.setDescrizione(errore.getDescrizione());
		tErrori.setIdPagamento(errore.getId_pagamento());
		tErrori.setIdRegistroVersamento(errore.getId_registro_versamento());
		tErrori.setIuv(errore.getIuv());
		tErrori.setIdTransazione(errore.getId_transazione());
		if(errore.getId_stato_errore() != null){
			tErrori.setEpayDStatoErrore(entityManager.find(EpayDStatoErrore.class, errore.getId_stato_errore()));
		}		
		tErrori.setDescCorrezione(errore.getDesc_correzione());
		tErrori.setApplicativo(errore.getApplicativo());
		entityManager.persist(tErrori);
		
	}

}
