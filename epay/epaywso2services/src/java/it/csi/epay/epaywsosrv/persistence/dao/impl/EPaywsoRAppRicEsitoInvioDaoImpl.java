/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import it.csi.epay.epaywsosrv.enumeration.IssueEnum;
import it.csi.epay.epaywsosrv.exception.PersistenceException;
import it.csi.epay.epaywsosrv.persistence.dao.EPaywsoDaoBaseImpl;
import it.csi.epay.epaywsosrv.persistence.dao.interf.EPaywsoRAppRicEsitoInvioDao;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoRAppRicEsitoInvio;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoRAppRicEsitoInvioPK;
import it.csi.epay.epaywsosrv.util.LogAndWatch;

public class EPaywsoRAppRicEsitoInvioDaoImpl extends EPaywsoDaoBaseImpl<EPaywsoRAppRicEsitoInvioPK, EPaywsoRAppRicEsitoInvio> implements EPaywsoRAppRicEsitoInvioDao {
	static private final String CLASSNAME = EPaywsoRAppRicEsitoInvioDaoImpl.class.getSimpleName();

	@Override
	public EPaywsoRAppRicEsitoInvio findOneByIdRichiestaAndIdApp(Long idRichiesta, Integer idApp) throws PersistenceException {
		String methodName = "findOneByIdRichiestaAndIdApp";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("idRichiesta", idRichiesta);
		lw.addParam("idApp", idApp);

		EPaywsoRAppRicEsitoInvio entity = null;

		try {
			lw.start();

			TypedQuery<EPaywsoRAppRicEsitoInvio> query = entityManager.createNamedQuery("EPaywsoRAppRicEsitoInvio.findAllByIdRichiestaAndIdApp", EPaywsoRAppRicEsitoInvio.class);
			query.setParameter("idRichiesta", idRichiesta);
			query.setParameter("idApp", idApp);
			//
			List<EPaywsoRAppRicEsitoInvio> entitytList = query.getResultList();
			switch (entitytList.size()) {
			case 0:
				lw.warn("nessun risultato trovato per idRichiesta:" + idRichiesta + " e idApp:" + idApp);
				break;
			case 1:
				entity = entitytList.get(0);
				break;
			default:
				// N.B. non puo mai verificarsi per come viene gestito l'inserimento e l'aggiornamento dei dati
				throw new PersistenceException(IssueEnum.DB_MORE_THAN_ONE_RESULT_AT_TIME_5ARGS, "esitoInvio", "idRichiesta", "" + idRichiesta, "idApp" + "" + idApp);
			}
		} catch (Throwable e) {
			handlePersistenceException(e);

		} finally {
			lw.stop();
		}

		return entity;
	}
	
	@Override
	public EPaywsoRAppRicEsitoInvio findOneByIdRichiestaAndIdEsitoInvio(Long idRichiesta, Long idEsitoInvio) throws PersistenceException {
		String methodName = "findOneByIdRichiestaAndIdEsitoInvio";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("idRichiesta", idRichiesta);
		lw.addParam("idEsitoInvio", idEsitoInvio);
		
		EPaywsoRAppRicEsitoInvio entity = null;
		
		try {
			lw.start();
			
			TypedQuery<EPaywsoRAppRicEsitoInvio> query = entityManager.createNamedQuery("EPaywsoRAppRicEsitoInvio.findAllByIdRichiestaAndIdEsitoInvio", EPaywsoRAppRicEsitoInvio.class);
			query.setParameter("idRichiesta", idRichiesta);
			query.setParameter("idEsitoInvio", idEsitoInvio);
			//
			List<EPaywsoRAppRicEsitoInvio> entitytList = query.getResultList();
			switch (entitytList.size()) {
			case 0:
				lw.warn("nessun risultato trovato per idRichiesta:" + idRichiesta + " e idEsitoInvio:" + idEsitoInvio);
				break;
			case 1:
				entity = entitytList.get(0);
				break;
			default:
				// N.B. non puo mai verificarsi per come viene gestito l'inserimento e l'aggiornamento dei dati
				throw new PersistenceException(IssueEnum.DB_MORE_THAN_ONE_RESULT_AT_TIME_5ARGS, "esitoInvio", "idRichiesta", "" + idRichiesta, "idEsitoInvio" + "" + idEsitoInvio);
			}
		} catch (Throwable e) {
			handlePersistenceException(e);
			
		} finally {
			lw.stop();
		}
		
		return entity;
	}

}
