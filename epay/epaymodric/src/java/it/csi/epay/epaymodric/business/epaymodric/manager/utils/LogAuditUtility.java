/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.manager.utils;

import it.csi.epay.epaymodric.business.epaymodric.model.AuditAction;
import it.csi.epay.epaymodric.business.epaymodric.model.CsiLogAudit;
import it.csi.epay.epaymodric.business.epaymodric.utils.DateUtils;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsLogAudit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.text.ParseException;

/**
 *
 *
 * @author NGueye
 *
 */
public class LogAuditUtility implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	final static Logger logger = LogManager.getLogger(LogAuditUtility.class);

	public static CsiLogAudit trasformaInCsiLogAudit(DTOInputWsLogAudit dtoLogAudit) throws ParseException {
		CsiLogAudit csiLogaudit = null;
		try {
			csiLogaudit = new CsiLogAudit();
            csiLogaudit.setId ( dtoLogAudit.getUniqueid () );
            csiLogaudit.setAction ( new AuditAction () );
            csiLogaudit.getAction ().setId ( dtoLogAudit.getIdaction () );
            csiLogaudit.getAction ().setDescrizione ( dtoLogAudit.getDescrizione () );
			csiLogaudit.setDescrizione(dtoLogAudit.getDescrizione());
            csiLogaudit.setCodiceApplicazione ( dtoLogAudit.getCodappmodify () );
			csiLogaudit.setDataOra(DateUtils.stringToTimeStamp(dtoLogAudit.getDataOra()));
            csiLogaudit.setIdUtente ( dtoLogAudit.getUtente () );
		} catch (Exception e) {
			logger.info("Errore durante la trasformazione in DTO " + e.getMessage());
		}
		return csiLogaudit;

	}

}
