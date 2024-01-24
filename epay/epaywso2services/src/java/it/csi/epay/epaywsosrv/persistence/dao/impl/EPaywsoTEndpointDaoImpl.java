/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.dao.impl;

import static it.csi.epay.epaywsosrv.util.Util.date2strdatetime;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.TypedQuery;

import it.csi.epay.epaywsosrv.enumeration.IssueEnum;
import it.csi.epay.epaywsosrv.exception.PersistenceException;
import it.csi.epay.epaywsosrv.persistence.dao.EPaywsoDaoBaseImpl;
import it.csi.epay.epaywsosrv.persistence.dao.interf.EPaywsoTEndpointDao;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoTEndpoint;
import it.csi.epay.epaywsosrv.util.LogAndWatch;

public class EPaywsoTEndpointDaoImpl extends EPaywsoDaoBaseImpl<String, EPaywsoTEndpoint> implements EPaywsoTEndpointDao {
	static private final String CLASSNAME = EPaywsoTEndpointDaoImpl.class.getSimpleName();

	@Override
	public EPaywsoTEndpoint findOneById(Integer id, Timestamp timestamp) throws PersistenceException {
		String methodName = "findOneById";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("id", id);
		lw.addParam("timestamp", timestamp);

		EPaywsoTEndpoint entity = null;

		try {
			lw.start();

			TypedQuery<EPaywsoTEndpoint> query = entityManager.createNamedQuery("EPaywsoTEndpoint.findOneById", EPaywsoTEndpoint.class);
			query.setParameter("idEndpoint", id);
			query.setParameter("dt", timestamp);
			//
			List<EPaywsoTEndpoint> entityList = query.getResultList();
			switch (entityList.size()) {
			case 0:
				lw.warn("nessun risultato trovato per idEndpoint:" + id + " che sia valido alla data/ora:" + date2strdatetime(timestamp));
				break;
			case 1:
				entity = entityList.get(0);
				break;
			default:
				throw new PersistenceException(IssueEnum.DB_MORE_THAN_ONE_RESULT_AT_TIME_4ARGS, "endpoint", "idEndpoint", "" + id, date2strdatetime(timestamp));
			}
		} catch (Throwable e) {
			handlePersistenceException(e);

		} finally {
			lw.stop();
		}

		return entity;
	}

}
