/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.business;

import it.csi.epay.epaywsosrv.enumeration.IssueEnum;
import it.csi.epay.epaywsosrv.exception.BusinessException;
import it.csi.epay.epaywsosrv.exception.PersistenceException;
import it.csi.epay.epaywsosrv.exception.SystemException;
import it.csi.epay.epaywsosrv.util.LogAndWatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;

import static it.csi.epay.epaywsosrv.util.Util.APPLICATION_CODE;

/** Classe padre di epay lato business */
public class EPaywsoBusinessBase {
	static private final String CLASSNAME = EPaywsoBusinessBase.class.getSimpleName();
	static protected Logger log = LogManager.getLogger(APPLICATION_CODE + ".business");

	protected void handleBusinessException(Throwable e) throws BusinessException {
		String methodName = "handleBusinessException";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName, false);

		try {
			lw.start();

			if (e instanceof BusinessException) {
				BusinessException b = (BusinessException) e;
				lw.error("errorCod:" + b.getErrorCod() + " errorDes:" + b.getErrorDes(), b.getCause());
				throw b;
			}
			if (e instanceof PersistenceException) {
				PersistenceException p = (PersistenceException) e;
				SystemException s = new SystemException(p.getErrorCod(), p.getErrorDes(), p.getCause());
				lw.error("errorCod:" + s.getErrorCod() + " errorDes:" + s.getErrorDes(), s.getCause());
				throw s;

			} else {
				// eccezione imprevista
				SystemException s = new SystemException(IssueEnum.GENERIC_SYSTEM_ERROR.getCod(), e.getMessage(), e);
				lw.error("errorCod:" + s.getErrorCod() + " errorDes:" + s.getErrorDes(), s.getCause());
				throw s;
			}

		} finally {
			lw.stop();
		}
	}

	protected Timestamp getTimestampNow() {
		return new Timestamp(System.currentTimeMillis());
	}

}
