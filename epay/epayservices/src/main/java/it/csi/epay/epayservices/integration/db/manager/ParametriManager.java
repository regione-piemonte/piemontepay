/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.manager;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import it.csi.epay.epayservices.integration.db.entities.EpayTParametri;
import it.csi.epay.epayservices.model.Parametro;

@Stateless(name="ParametriManager", mappedName = "ParametriManager")
public class ParametriManager extends _Manager{

	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Parametro> elencoCompleto() {
		TypedQuery<EpayTParametri> query = entityManager.createNamedQuery("EpayTParametri.findAll", EpayTParametri.class);
		
		List<EpayTParametri> tParametri = query.getResultList();
		
		ArrayList<Parametro> parametri = new ArrayList<Parametro>();		
		for (EpayTParametri tParametro : tParametri) {
			Parametro parametro = new Parametro();
			parametro.setGruppo(tParametro.getId().getGruppo());
			parametro.setCodice(tParametro.getId().getCodice());
			parametro.setValore(tParametro.getValore());
			parametro.setDescrizione(tParametro.getDescrizione());
			parametro.setProgressivo(tParametro.getProgressivo());
			parametri.add(parametro);
		}
		return parametri;
	}
	
	public List<Parametro> elencoPerGruppo(String gruppo) {
		TypedQuery<EpayTParametri> query = entityManager.createNamedQuery("EpayTParametri.findAllGroup", EpayTParametri.class);
		query.setParameter("gruppo", gruppo);
		
		List<EpayTParametri> tParametri = query.getResultList();
		
		ArrayList<Parametro> parametri = new ArrayList<Parametro>();		
		for (EpayTParametri tParametro : tParametri) {
			Parametro parametro = new Parametro();
			parametro.setGruppo(tParametro.getId().getGruppo());
			parametro.setCodice(tParametro.getId().getCodice());
			parametro.setValore(tParametro.getValore());
			parametro.setDescrizione(tParametro.getDescrizione());
			parametro.setProgressivo(tParametro.getProgressivo());
			parametri.add(parametro);
		}
		return parametri;
	}
	
	public Parametro getParametro(String gruppo, String codice) {
		final String methodName = "getParametro";
		
		TypedQuery<EpayTParametri> query = entityManager.createNamedQuery("EpayTParametri.findOne", EpayTParametri.class);
		query.setParameter("gruppo", gruppo);
		query.setParameter("codice", codice);
		
		EpayTParametri tParametri = null;
		try {
			tParametri = query.getSingleResult();
		} catch (RuntimeException rEx) {
			log.error(methodName, "errore query");
			log.error(methodName, "- gruppo : " + query.getParameterValue("gruppo"));
			log.error(methodName, "- codice : " + query.getParameterValue("codice"));
			throw rEx;
		}
		
		Parametro parametro = new Parametro();
		parametro.setGruppo(tParametri.getId().getGruppo());
		parametro.setCodice(tParametri.getId().getCodice());
		parametro.setValore(tParametri.getValore());
		parametro.setDescrizione(tParametri.getDescrizione());
		parametro.setProgressivo(tParametri.getProgressivo());
		
		return parametro;
	}
	
	public List<Parametro> getGruppoParametri(String gruppo) {
		final String methodName = "getGruppoParametri";
		
		TypedQuery<EpayTParametri> query = entityManager.createNamedQuery("EpayTParametri.findAllGroup", EpayTParametri.class);
		query.setParameter("gruppo", gruppo);
		List<Parametro> parametri = new ArrayList<Parametro>();
		try {
			List<EpayTParametri> tParametri = query.getResultList();
			for (EpayTParametri tParametro : tParametri) {
				Parametro parametro = new Parametro();
				parametro.setGruppo(tParametro.getId().getGruppo());
				parametro.setCodice(tParametro.getId().getCodice());
				parametro.setValore(tParametro.getValore());
				parametro.setDescrizione(tParametro.getDescrizione());
				parametro.setProgressivo(tParametro.getProgressivo());
				parametri.add(parametro);
			}
		} catch (RuntimeException rEx) {
			log.error(methodName, "errore query");
			log.error(methodName, "- gruppo : " + query.getParameterValue("gruppo"));
			log.error(methodName, "- codice : " + query.getParameterValue("codice"));
			throw rEx;
		}
		return parametri;
	}
	
}
