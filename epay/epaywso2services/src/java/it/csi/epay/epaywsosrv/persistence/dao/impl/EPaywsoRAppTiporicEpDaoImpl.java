/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.dao.impl;

import static it.csi.epay.epaywsosrv.util.Util.date2strdatetime;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import it.csi.epay.epaywsosrv.exception.PersistenceException;
import it.csi.epay.epaywsosrv.persistence.dao.EPaywsoDaoBaseImpl;
import it.csi.epay.epaywsosrv.persistence.dao.interf.EPaywsoRAppTiporicEpDao;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoRAppTiporicEp;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoRAppTiporicEpPK;
import it.csi.epay.epaywsosrv.util.LogAndWatch;

public class EPaywsoRAppTiporicEpDaoImpl extends EPaywsoDaoBaseImpl<EPaywsoRAppTiporicEpPK, EPaywsoRAppTiporicEp> implements EPaywsoRAppTiporicEpDao {
	static private final String CLASSNAME = EPaywsoRAppTiporicEpDaoImpl.class.getSimpleName();

	@Override
	public List<EPaywsoRAppTiporicEp> findAllByIdApp(Integer idApp, Timestamp timestamp) throws PersistenceException {
		String methodName = "findAllByIdApp";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("idApp", idApp);
		lw.addParam("timestamp", timestamp);

		List<EPaywsoRAppTiporicEp> entityList = new ArrayList<EPaywsoRAppTiporicEp>();

		try {
			lw.start();

			TypedQuery<EPaywsoRAppTiporicEp> query = entityManager.createNamedQuery("EPaywsoRAppTiporicEp.findAllByIdApp", EPaywsoRAppTiporicEp.class);
			query.setParameter("idApplicativo", idApp);
			query.setParameter("dt", timestamp);
			//
			entityList = query.getResultList();
			if (entityList.isEmpty()) {
				lw.warn("nessun risultato trovato per idApp:" + idApp + " che sia valido alla data/ora:" + date2strdatetime(timestamp));
			}
		} catch (Throwable e) {
			handlePersistenceException(e);

		} finally {
			lw.stop();
		}

		return entityList;
	}

	@Override
	public List<EPaywsoRAppTiporicEp> findAllByIdTipoRichiesta(Integer idTipoRichiesta, Timestamp timestamp) throws PersistenceException {
		String methodName = "findAllByIdTipoRichiesta";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("idTipoRichiesta", idTipoRichiesta);
		lw.addParam("timestamp", timestamp);

		List<EPaywsoRAppTiporicEp> entityList = new ArrayList<EPaywsoRAppTiporicEp>();

		try {
			lw.start();

			TypedQuery<EPaywsoRAppTiporicEp> query = entityManager.createNamedQuery("EPaywsoRAppTiporicEp.findAllByIdTipoRichista", EPaywsoRAppTiporicEp.class);
			query.setParameter("idTipoRichiesta", idTipoRichiesta);
			query.setParameter("dt", timestamp);
			//
			entityList = query.getResultList();
			if (entityList.isEmpty()) {
				lw.warn("nessun risultato trovato per idTipoRichiesta:" + idTipoRichiesta + " che sia valido alla data/ora:" + date2strdatetime(timestamp));
			}
		} catch (Throwable e) {
			handlePersistenceException(e);

		} finally {
			lw.stop();
		}

		return entityList;
	}

	@Override
	public List<EPaywsoRAppTiporicEp> findAllByIdTipoRichiestaAndIdApp(Integer idTipoRichiesta, Integer idApp, Timestamp timestamp) throws PersistenceException {
		String methodName = "findAllByIdTipoRichiestaAndIdApps";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("idTipoRichiesta", idTipoRichiesta);
		lw.addParam("idApp", idApp);
		lw.addParam("timestamp", timestamp);

		List<EPaywsoRAppTiporicEp> entityList = new ArrayList<EPaywsoRAppTiporicEp>();

		try {
			lw.start();

			TypedQuery<EPaywsoRAppTiporicEp> query = entityManager.createNamedQuery("EPaywsoRAppTiporicEp.findAllByIdTipoRichistaAndIdApp", EPaywsoRAppTiporicEp.class);
			query.setParameter("idTipoRichiesta", idTipoRichiesta);
			query.setParameter("idApp", idApp);
			query.setParameter("dt", timestamp);
			//
			entityList = query.getResultList();
			if (entityList.isEmpty()) {
				lw.warn("nessun risultato trovato per idTipoRichiesta:" + idTipoRichiesta + " e idApp:" + idApp + " che sia valido alla data/ora:" + date2strdatetime(timestamp));
			}
		} catch (Throwable e) {
			handlePersistenceException(e);

		} finally {
			lw.stop();
		}

		return entityList;
	}

	@Override
	public List<EPaywsoRAppTiporicEp> findAllByIdTipoRichiestaAndIdApps(Integer idTipoRichiesta, List<Integer> idApps, Timestamp timestamp) throws PersistenceException {
		String methodName = "findAllByIdTipoRichiestaAndIdApps";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("idTipoRichiesta", idTipoRichiesta);
		lw.addParam("idApps", idApps);
		lw.addParam("timestamp", timestamp);

		List<EPaywsoRAppTiporicEp> entityList = new ArrayList<EPaywsoRAppTiporicEp>();

		try {
			lw.start();

			TypedQuery<EPaywsoRAppTiporicEp> query = entityManager.createNamedQuery("EPaywsoRAppTiporicEp.findAllByIdTipoRichistaAndIdApps", EPaywsoRAppTiporicEp.class);
			query.setParameter("idTipoRichiesta", idTipoRichiesta);
			query.setParameter("idApps", idApps);
			query.setParameter("dt", timestamp);
			//
			entityList = query.getResultList();
			if (entityList.isEmpty()) {
				lw.warn("nessun risultato trovato per idTipoRichiesta:" + idTipoRichiesta + " e idApps:" + idApps + " che sia valido alla data/ora:" + date2strdatetime(timestamp));
			}
		} catch (Throwable e) {
			handlePersistenceException(e);

		} finally {
			lw.stop();
		}

		return entityList;
	}

}
