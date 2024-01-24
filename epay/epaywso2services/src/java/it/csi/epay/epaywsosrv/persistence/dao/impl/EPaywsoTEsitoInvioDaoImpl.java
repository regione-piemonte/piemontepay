/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import it.csi.epay.epaywsosrv.exception.PersistenceException;
import it.csi.epay.epaywsosrv.persistence.dao.EPaywsoDaoBaseImpl;
import it.csi.epay.epaywsosrv.persistence.dao.interf.EPaywsoTEsitoInvioDao;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoTEsitoInvio;
import it.csi.epay.epaywsosrv.util.LogAndWatch;

public class EPaywsoTEsitoInvioDaoImpl extends EPaywsoDaoBaseImpl<Integer, EPaywsoTEsitoInvio> implements EPaywsoTEsitoInvioDao {
	static private final String CLASSNAME = EPaywsoTEsitoInvioDaoImpl.class.getSimpleName();

	@Override
	public List<EPaywsoTEsitoInvio> findAllByIdRichiesta(Long idRichiesta) throws PersistenceException {
		String methodName = "findAllByIdRichiesta";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("idRichiesta", idRichiesta);

		List<EPaywsoTEsitoInvio> entityList = new ArrayList<EPaywsoTEsitoInvio>();

		try {
			lw.start();

			TypedQuery<EPaywsoTEsitoInvio> query = entityManager.createNamedQuery("EPaywsoTEsitoInvio.findAllByIdRichiesta", EPaywsoTEsitoInvio.class);
			query.setParameter("idRichiesta", idRichiesta);
			//
			entityList = query.getResultList();
			if (entityList.isEmpty()) {
				lw.warn("nessun risultato trovato per idRichiesta:" + idRichiesta);
			}
		} catch (Throwable e) {
			handlePersistenceException(e);

		} finally {
			lw.stop();
		}

		return entityList;
	}

}
