/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.dao.impl;

import it.csi.epay.epaywsosrv.enumeration.IssueEnum;
import it.csi.epay.epaywsosrv.exception.PersistenceException;
import it.csi.epay.epaywsosrv.persistence.dao.EPaywsoDaoBaseImpl;
import it.csi.epay.epaywsosrv.persistence.dao.interf.EPaywsoDEsitoDao;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoDEsito;
import it.csi.epay.epaywsosrv.util.LogAndWatch;

public class EPaywsoDEsitoDaoImpl extends EPaywsoDaoBaseImpl<String, EPaywsoDEsito> implements EPaywsoDEsitoDao {
	static private final String CLASSNAME = EPaywsoDEsitoDaoImpl.class.getSimpleName();

	@Override
	public EPaywsoDEsito findOneByCod(String cod) throws PersistenceException {
		String methodName = "findOneByCod";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("cod", cod);

		EPaywsoDEsito entity = null;

		try {
			lw.start();

			entity = findOne(cod);
			if (entity == null) {
				throw new PersistenceException(IssueEnum.DB_NO_DATA_FOUND_3ARGS, "esito", "cod", cod);
			}

		} catch (Throwable e) {
			handlePersistenceException(e);

		} finally {
			lw.stop();
		}

		return entity;
	}

}
