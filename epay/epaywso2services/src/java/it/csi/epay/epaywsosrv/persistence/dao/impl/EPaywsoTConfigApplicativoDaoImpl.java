/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.dao.impl;

import it.csi.epay.epaywsosrv.enumeration.IssueEnum;
import it.csi.epay.epaywsosrv.exception.PersistenceException;
import it.csi.epay.epaywsosrv.persistence.dao.interf.EPaywsoTConfigApplicativoDao;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoTConfigApplicativo;
import it.csi.epay.epaywsosrv.util.LogAndWatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static it.csi.epay.epaywsosrv.util.Util.APPLICATION_CODE;
import static it.csi.epay.epaywsosrv.util.Util.date2strdatetime;

public class EPaywsoTConfigApplicativoDaoImpl implements EPaywsoTConfigApplicativoDao {
	static private final String CLASSNAME = EPaywsoTConfigApplicativoDaoImpl.class.getSimpleName();
	static private final Logger log = LogManager.getLogger(APPLICATION_CODE + ".dao");

	@PersistenceContext(unitName = "epaywso")
	private EntityManager entityManager;

	@Override
	//@formatter:off
	public List<EPaywsoTConfigApplicativo> findAllByParams(String codFiscaleEnte, List<String> codVersamentoList, Integer idTipoRichiesta, Timestamp timestamp)
	throws PersistenceException
	{
	//@formatter:on
		String methodName = "findAllByIdApp";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("codFiscaleEnte", codFiscaleEnte);
		lw.addParam("codVersamentoList", codVersamentoList);
		lw.addParam("timestamp", timestamp);

		List<EPaywsoTConfigApplicativo> entityList = new ArrayList<EPaywsoTConfigApplicativo>();

		try {
			lw.start();

			TypedQuery<EPaywsoTConfigApplicativo> query = entityManager.createNamedQuery("EPaywsoTConfigApplicativo.findAllByParams", EPaywsoTConfigApplicativo.class);
			query.setParameter("codFiscaleEnte", codFiscaleEnte);
			query.setParameter("codsVers", codVersamentoList);
			query.setParameter("idTipoRichiesta", idTipoRichiesta);
			query.setParameter("dt", timestamp);
			//
			entityList = query.getResultList();
			if (entityList.isEmpty()) {
				lw.warn("nessun risultato trovato per codFiscaleEnte:" + codFiscaleEnte + ", codVersamentoList:" + codVersamentoList
						+ " che sia valido alla data/ora:" + date2strdatetime(timestamp));
			}
		} catch (Throwable e) {
			PersistenceException p = new PersistenceException(IssueEnum.GENERIC_SYSTEM_ERROR.getCod(), e.getMessage(), e);
			lw.error("errorCod:" + p.getErrorCod() + " errorDes:" + p.getErrorDes(), p.getCause());
			throw p;

		} finally {
			lw.stop();
		}

		return entityList;
	}

}
