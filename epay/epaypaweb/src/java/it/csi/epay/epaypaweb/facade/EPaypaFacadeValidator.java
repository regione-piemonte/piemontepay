/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.facade;

import it.csi.epay.epaypaweb.exception.*;
import it.csi.epay.epaypaweb.facade.epaywso2enti.dto.TipoDettaglioVoce;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static it.csi.epay.epaypaweb.util.Util.APPLICATION_CODE;

public class EPaypaFacadeValidator {
	static private final String CLASSNAME = EPaypaFacadeValidator.class.getSimpleName();
	static protected Logger log = LogManager.getLogger(APPLICATION_CODE + ".facade");

	private static void logValidation(Logger lw, Object value, boolean ok) {
		log.debug("value:".concat(value != null ? value.toString() : "null").concat(" - ok:").concat(Boolean.toString(ok)));
	}

	static public void notNull(String name, Object value) throws FacadeXmlNullException {
		String methodName = "notNull";
		
		boolean ok;

		ok = (value != null);
		logValidation(log, value, ok);

		if (!ok) {
			throw new FacadeXmlNullException(name);
		}
	}

	static public void notZero(String name, long value) throws FacadeXmlZeroException {
		String methodName = "notZero";
		
		boolean ok;

		ok = (value != 0);
		logValidation(log, value, ok);

		if (!ok) {
			throw new FacadeXmlZeroException(name);
		}
	}

	static public void mandatory(String name, Object value) throws FacadeXmlMandatoryException {
		String methodName = "mandatory";
		
		boolean ok;

		if (value instanceof String) {
			ok = !StringUtils.isBlank((String) value);
		} else {
			ok = (value != null);
		}
		logValidation(log, value, ok);

		if (!ok) {
			throw new FacadeXmlMandatoryException(name);
		}
	}

	static public void stringLength(String name, String value, int minLength, int maxLength, boolean isMandatory) throws FacadeXmlLenghtException {
		String methodName = "stringLength";
		
		boolean ok;

		if (value != null) {
			int length = value.length();
			ok = (length >= minLength && length <= maxLength);
		} else {
			ok = !isMandatory;
		}
		logValidation(log, value, ok);

		if (!ok) {
			throw new FacadeXmlLenghtException(name, minLength, maxLength);
		}
	}

	static public void tipoDettaglioVoce(String name, TipoDettaglioVoce type, boolean isMandatory) throws FacadeXmlMismatchException {
		String methodName = "tipoDettaglioVoce";
		
		boolean ok = false;

		String value = null;
		if (type != null) {
			value = type.name();
			switch (type) {
			case IMPORTO_TRANSATO:
			case IMPORTO_AUTORIZZATO:
			case IMPORTO_COMMISSIONI:
				ok = true;
			}
		} else {
			ok = !isMandatory;
			log.warn("TipoRichiesta non valorizzato nella request o valorizzato con valore non previsto nell'xsd");
		}
		logValidation(log, value, ok);

		if (!ok) {
			throw new FacadeXmlMismatchException(name, value);
		}
	}

}
