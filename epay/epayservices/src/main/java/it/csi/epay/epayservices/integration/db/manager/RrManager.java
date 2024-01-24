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

import it.csi.epay.epayservices.integration.db.entities.EpayTDatiSingolaRevoca;
import it.csi.epay.epayservices.integration.db.entities.EpayTRr;
import it.csi.epay.epayservices.integration.db.entities.EpayTRt;
import it.csi.epay.epayservices.model.DatiSingolaRevoca;
import it.csi.epay.epayservices.model.Rr;
import it.csi.epay.epayservices.model.Rt;

@Stateless(name="RrManager", mappedName = "RrManager")
public class RrManager extends _Manager {

	@PersistenceContext
	private EntityManager entityManager;
	
	public Integer inserisci(Rr rr) {
		Integer idRR = 0;
	try {
		EpayTRr tRr = map (rr, EpayTRr.class);
		tRr.setXmlRr(rr.getRrXml());
		tRr.setIdRr(null);
		entityManager.persist(tRr);
		idRR = tRr.getIdRr();
	} catch (RuntimeException rEx) {
			throw rEx;
	}
		return idRR;
	}	
	
	public Integer inserisciSingolaRevoca(DatiSingolaRevoca revoca, Integer rrID) {
		EpayTDatiSingolaRevoca tSingolaRevova = map (revoca, EpayTDatiSingolaRevoca.class);
		tSingolaRevova.setIdRr(rrID);
		tSingolaRevova.setId(null);
		entityManager.persist(tSingolaRevova);
		return tSingolaRevova.getIdRr();
	}
	
//	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
//	public void savePdf(final Long id, final byte[] rt_pdf) {
//		EpayTRt tRt = entityManager.find(EpayTRt.class, id);
//		tRt.setRicevutaPdf(rt_pdf);
//    }
//	
//	public byte[] readXml(final Long id) {
//		return entityManager.find(EpayTRt.class, id).getRtXml();
//    }
//	
//	public List<Rt> elencoRtSenzaRicevutaPdf() {
//		TypedQuery<EpayTRt> query = entityManager.createNamedQuery("EpayTRt.elencoRtSenzaRicevutaPdf", EpayTRt.class);
//		List<EpayTRt> tRts = query.getResultList();
//		List<Rt> rts = new ArrayList<Rt>();
//		for (EpayTRt tRt : tRts) {
//			Rt rt = map(tRt, Rt.class);
//			rt.setIdRegistro(tRt.getEpayTRegistroVersamenti().getIdRegistro());
//			rts.add(rt);
//		}
//		return rts;
//	}
//	
	public void ricercaRtByIuvAndUpdateIdRr(String iuv, Integer idRR) {
		try {
			TypedQuery<EpayTRt> query = entityManager.createNamedQuery("EpayTRt.ricercaRtByIuv", EpayTRt.class);
			query.setParameter("iuv", iuv);
			List<EpayTRt> tRts = query.getResultList();
			List<Rt> rts = new ArrayList<Rt>();
			for (EpayTRt tRt : tRts) {
				if (tRt.getIdRr()==null) {
					tRt.setIdRr(idRR);	
					entityManager.persist(tRt);
				}
			}
		} catch  (RuntimeException rEx) {
			throw rEx;
		}
	}
}
