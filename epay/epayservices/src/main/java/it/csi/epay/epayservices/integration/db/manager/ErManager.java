/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.manager;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.csi.epay.epayservices.integration.db.entities.EpayTDatiSingolaRevoca;
import it.csi.epay.epayservices.integration.db.entities.EpayTEr;
import it.csi.epay.epayservices.model.DatiSingolaRevoca;
import it.csi.epay.epayservices.model.Er;

@Stateless(name="ErManager", mappedName = "ErManager")
public class ErManager extends _Manager {

	@PersistenceContext
	private EntityManager entityManager;
	
	public Integer inserisci(Er er) {
		EpayTEr tEr = map (er, EpayTEr.class);
		tEr.setIdEr(null);
		entityManager.persist(tEr);
		return tEr.getIdEr();
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
//	public Rt ricercaRtByIdRegistro(final Long idRegistro) throws NoDataException {
//		try {
//			TypedQuery<EpayTRt> query = entityManager.createNamedQuery("EpayTRt.ricercaRtByIdRegistro", EpayTRt.class);
//			query.setParameter("idRegistro", idRegistro);
//			EpayTRt tRt = query.getSingleResult();
//			return map(tRt, Rt.class);
//		} catch (NoResultException e) {
//			throw new NoDataException("Rt non trovata (id registro = " + idRegistro + ")");
//		}
//	}
}
