/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.facade;

import static it.csi.epay.epaywsosrv.util.Util.APPLICATION_CODE;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import it.csi.epay.epaywsosrv.enumeration.IssueEnum;
import it.csi.epay.epaywsosrv.exception.FacadeException;
import it.csi.epay.epaywsosrv.facade.dto.common.StatoRichiestaType;
import it.csi.epay.epaywsosrv.facade.dto.common.TipoRichiestaType;
import it.csi.epay.epaywsosrv.facade.dto.integrationservice.FiltroSelezioneRichiesteType;
import it.csi.epay.epaywsosrv.facade.dto.integrationservice.StatoRichiestaTypeList;
import it.csi.epay.epaywsosrv.util.LogAndWatch;

public class EPaywsoFacadeValidator {
	static private final String CLASSNAME = EPaywsoFacadeValidator.class.getSimpleName();
	static protected Logger log = LogManager.getLogger(APPLICATION_CODE + ".facade");

	private static void logValidation(LogAndWatch lw, Object value, boolean ok) {
		lw.debug("value:" + value + " - ok:" + ok);
	}

	static public void notNull(String elementName, Object value) throws FacadeException {
		String methodName = "notNull";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName, false);

		boolean ok = (value != null);
		logValidation(lw, value, ok);

		if (!ok) {
			throw new FacadeException(IssueEnum.XML_NULL_1ARG, elementName);
		}
	}

	static public void notZero(String fieldName, long value) throws FacadeException {
		String methodName = "notZero";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName, false);

		boolean ok = (value != 0);
		logValidation(lw, value, ok);

		if (!ok) {
			throw new FacadeException(IssueEnum.XML_ZERO_1ARG, fieldName);
		}
	}

	static public void mandatoryField(String fieldName, Object value) throws FacadeException {
		String methodName = "mandatoryField";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName, false);

		boolean ok;
		if (value instanceof String) {
			ok = !StringUtils.isBlank((String) value);
		} else {
			ok = (value != null);
		}
		logValidation(lw, value, ok);

		if (!ok) {
			throw new FacadeException(IssueEnum.XML_MANDATORY_1ARG, fieldName);
		}
	}

	static public void stringLength(String fieldName, String value, int minLength, int maxLength, boolean isMandatory) throws FacadeException {
		String methodName = "stringLength";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName, false);

		boolean ok;
		if (value != null) {
			int length = value.length();
			ok = (length >= minLength && length <= maxLength);
		} else {
			ok = !isMandatory;
		}
		logValidation(lw, value, ok);

		if (!ok) {
			throw new FacadeException(IssueEnum.XML_MINLENGTH_MAXLENGTH_3ARG, fieldName, "" + minLength, "" + maxLength);
		}
	}

	static public void tipoRichiesta(String fieldName, TipoRichiestaType tipo, boolean isMandatory) throws FacadeException {
		String methodName = "tipoRichiesta";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName, false);

        lw.info ( "tipo:" + tipo );

		boolean ok = false;
		String value = null;
		if (tipo != null) {
			value = tipo.name();

            lw.info ( "tipo value:" + value );

			if (value != null) {
				switch (tipo) {
				case INSERISCI_LISTA_DI_CARICO:
				case AGGIORNA_POSIZIONI_DEBITORIE:
				case TRASMETTI_NOTIFICHE_PAGAMENTO:
                case TRASMETTI_RICHIESTE_DI_REVOCA:
				case TRASMETTI_AVVISI_SCADUTI:
				case TRASMETTI_FLUSSO_RENDICONTAZIONE:
				case TRASMETTI_FLUSSO_RENDICONTAZIONE_ESTESO:
				case TRASMETTI_FLUSSO_RENDICONTAZIONE_COMPLETO:
                case TRASMETTI_RT:
                case TRASMETTI_RT_ESTESA:
					ok = true;
				}
			}
		} else {
			ok = !isMandatory;
			lw.warn("TipoRichiesta non valorizzato nella request o valorizzato con valore non previsto nell'xsd");
		}
		logValidation(lw, value, ok);

		if (!ok) {
			throw new FacadeException(IssueEnum.XSD_ERROR_2ARGS, value, fieldName);
		}
	}

	static public void statoRichiesta(String fieldName, StatoRichiestaType stato, boolean isMandatory) throws FacadeException {
		String methodName = "statoRichiesta";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName, false);

		boolean ok = false;
		String value = null;
		if (stato != null) {
			value = stato.name();
			if (value != null) {
				switch (stato) {
				case IN_CORSO_DI_ACQUISIZIONE:
				case ERRORE_IN_FASE_DI_ACQUISIZIONE:
				case DA_ELABORARE:
				case ERRORE_IN_FASE_DI_ELABORAZIONE:
				case ELABORATA:
				case IN_CORSO_DI_ELABORAZIONE:
					ok = true;
				}
			}
		} else {
			ok = !isMandatory;
			lw.warn("StatoRichiesta non valorizzato nella request o valorizzato con valore non previsto nell'xsd");
		}
		logValidation(lw, value, ok);

		if (!ok) {
			throw new FacadeException(IssueEnum.XSD_ERROR_2ARGS, value, fieldName);
		}
	}

	static public void filtroSelezione(FiltroSelezioneRichiesteType filtroType) throws FacadeException {
		if (filtroType != null) {
			EPaywsoFacadeValidator.tipoRichiesta("TipoRichiesta", filtroType.getTipoRichiesta(), false);
			StatoRichiestaTypeList statoRichiestaTypeList = filtroType.getStatiRichiesta();
			if (statoRichiestaTypeList != null) {
				for (StatoRichiestaType statoRichiestaType : statoRichiestaTypeList.getStatoRichiesta()) {
					EPaywsoFacadeValidator.statoRichiesta("StatoRichiesta", statoRichiestaType, true);
				}
			}
		}
	}
	
	
	static public void isAlphanumeric(String fieldName, String value, boolean isMandatory) throws FacadeException {

        boolean ok;
        if (!StringUtils.isEmpty ( value )) {
            
            ok = (StringUtils.isAlphanumeric ( value ));
        } else {
            ok = !isMandatory;
        }
        if (!ok) {
            throw new FacadeException("Caratteri non validi", fieldName);
        }
    }

}
