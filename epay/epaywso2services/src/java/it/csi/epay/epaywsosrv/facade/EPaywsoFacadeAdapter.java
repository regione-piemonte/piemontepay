/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.facade;

import static it.csi.epay.epaywsosrv.util.Util.APPLICATION_CODE;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import it.csi.epay.epaywsosrv.enumeration.ColumnNameRichiestaEnum;
import it.csi.epay.epaywsosrv.enumeration.IssueEnum;
import it.csi.epay.epaywsosrv.enumeration.SortTypeEnum;
import it.csi.epay.epaywsosrv.enumeration.StatoRichiestaEnum;
import it.csi.epay.epaywsosrv.enumeration.TipoRichiestaEnum;
import it.csi.epay.epaywsosrv.exception.FacadeException;
import it.csi.epay.epaywsosrv.facade.dto.common.StatoRichiestaType;
import it.csi.epay.epaywsosrv.facade.dto.common.TipoRichiestaType;
import it.csi.epay.epaywsosrv.facade.dto.integrationservice.ColumnNameRichiestaType;
import it.csi.epay.epaywsosrv.facade.dto.integrationservice.EmbeddedXMLType;
import it.csi.epay.epaywsosrv.facade.dto.integrationservice.SortTypeType;
import it.csi.epay.epaywsosrv.util.LogAndWatch;

public class EPaywsoFacadeAdapter {
	static private final String CLASSNAME = EPaywsoFacadeAdapter.class.getSimpleName();

	static protected Logger log = LogManager.getLogger(APPLICATION_CODE + ".facade");

	static public TipoRichiestaEnum toTipoRichiestaEnum(TipoRichiestaType type) {
		TipoRichiestaEnum en = null;

		if (type != null) {
			switch (type) {
			case INSERISCI_LISTA_DI_CARICO:
				en = TipoRichiestaEnum.INSERISCI_LISTA_DI_CARICO;
				break;
			case AGGIORNA_POSIZIONI_DEBITORIE:
				en = TipoRichiestaEnum.AGGIORNA_POSIZIONI_DEBITORIE;
				break;
			case TRASMETTI_NOTIFICHE_PAGAMENTO:
				en = TipoRichiestaEnum.TRASMETTI_NOTIFICHE_PAGAMENTO;
				break;
            case TRASMETTI_RICHIESTE_DI_REVOCA:
                en = TipoRichiestaEnum.TRASMETTI_RICHIESTA_DI_REVOCA;
                break;
			case TRASMETTI_AVVISI_SCADUTI:
				en = TipoRichiestaEnum.TRASMETTI_AVVISI_SCADUTI;
				break;
			case TRASMETTI_FLUSSO_RENDICONTAZIONE:
				en = TipoRichiestaEnum.TRASMETTI_FLUSSO_RENDICONTAZIONE;
				break;
			case TRASMETTI_FLUSSO_RENDICONTAZIONE_ESTESO:
				en = TipoRichiestaEnum.TRASMETTI_FLUSSO_RENDICONTAZIONE_ESTESO;
				break;
			case TRASMETTI_FLUSSO_RENDICONTAZIONE_COMPLETO:
				en = TipoRichiestaEnum.TRASMETTI_FLUSSO_RENDICONTAZIONE_COMPLETO;
				break;
            case TRASMETTI_RT:
                en = TipoRichiestaEnum.TRASMETTI_RT;
                break;
            case TRASMETTI_RT_ESTESA:
                en = TipoRichiestaEnum.TRASMETTI_RT_ESTESA;
                break;
			default:
				String methodName = "toTipoRichiestaEnum";
				LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName, false);
				lw.warn("TipoRichiestaType:" + type.name() + " non convertibile in TipoRichiestaEnum");
				en = null;
				break;
			}
		}

		return en;
	}

	static public TipoRichiestaType toTipoRichiestaType(TipoRichiestaEnum en) {
		TipoRichiestaType type = null;

		if (en != null) {
			switch (en) {
			case INSERISCI_LISTA_DI_CARICO:
				type = TipoRichiestaType.INSERISCI_LISTA_DI_CARICO;
				break;
			case AGGIORNA_POSIZIONI_DEBITORIE:
				type = TipoRichiestaType.AGGIORNA_POSIZIONI_DEBITORIE;
				break;
			case TRASMETTI_NOTIFICHE_PAGAMENTO:
				type = TipoRichiestaType.TRASMETTI_NOTIFICHE_PAGAMENTO;
				break;
            case TRASMETTI_RICHIESTA_DI_REVOCA:
                type = TipoRichiestaType.TRASMETTI_RICHIESTE_DI_REVOCA;
                break;
			case TRASMETTI_AVVISI_SCADUTI:
				type = TipoRichiestaType.TRASMETTI_AVVISI_SCADUTI;
				break;
			case TRASMETTI_FLUSSO_RENDICONTAZIONE:
				type = TipoRichiestaType.TRASMETTI_FLUSSO_RENDICONTAZIONE;
				break;
			case TRASMETTI_FLUSSO_RENDICONTAZIONE_ESTESO:
				type = TipoRichiestaType.TRASMETTI_FLUSSO_RENDICONTAZIONE_ESTESO;
				break;
			case TRASMETTI_FLUSSO_RENDICONTAZIONE_COMPLETO:
				type = TipoRichiestaType.TRASMETTI_FLUSSO_RENDICONTAZIONE_COMPLETO;
				break;
            case TRASMETTI_RT:
                type = TipoRichiestaType.TRASMETTI_RT;
                break;
            case TRASMETTI_RT_ESTESA:
                type = TipoRichiestaType.TRASMETTI_RT_ESTESA;
                break;
			default:
				String methodName = "toTipoRichiestaType";
				LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName, false);
				lw.warn("TipoRichiestaEnum:" + en.name() + " non convertibile in TipoRichiestaType");
				type = null;
				break;
			}
		}

		return type;
	}

	static public StatoRichiestaEnum toStatoRichiestaEnum(StatoRichiestaType type) {
		StatoRichiestaEnum en = null;

		if (type != null) {
			switch (type) {
			case IN_CORSO_DI_ACQUISIZIONE:
				en = StatoRichiestaEnum.IN_CORSO_DI_ACQUISIZIONE;
				break;
			case ERRORE_IN_FASE_DI_ACQUISIZIONE:
				en = StatoRichiestaEnum.ERRORE_IN_FASE_DI_ACQUISIZIONE;
				break;
			case DA_ELABORARE:
				en = StatoRichiestaEnum.DA_ELABORARE;
				break;
			case ERRORE_IN_FASE_DI_ELABORAZIONE:
				en = StatoRichiestaEnum.ERRORE_IN_FASE_DI_ELABORAZIONE;
				break;
			case ELABORATA:
				en = StatoRichiestaEnum.ELABORATA;
				break;
			case IN_CORSO_DI_ELABORAZIONE:
				en = StatoRichiestaEnum.IN_CORSO_DI_ELABORAZIONE;
				break;
			default:
				String methodName = "toStatoRichiestaEnum";
				LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName, false);
				lw.warn("StatoRichiestaType:" + type.name() + " non convertibile in StatoRichiestaEnum");
				en = null;
				break;
			}
		}

		return en;
	}

	static public StatoRichiestaType toStatoRichiestaType(StatoRichiestaEnum en) {
		StatoRichiestaType type = null;

		if (en != null) {
			switch (en) {
			case IN_CORSO_DI_ACQUISIZIONE:
				type = StatoRichiestaType.IN_CORSO_DI_ACQUISIZIONE;
				break;
			case ERRORE_IN_FASE_DI_ACQUISIZIONE:
				type = StatoRichiestaType.ERRORE_IN_FASE_DI_ACQUISIZIONE;
				break;
			case DA_ELABORARE:
				type = StatoRichiestaType.DA_ELABORARE;
				break;
			case ERRORE_IN_FASE_DI_ELABORAZIONE:
				type = StatoRichiestaType.ERRORE_IN_FASE_DI_ELABORAZIONE;
				break;
			case ELABORATA:
				type = StatoRichiestaType.ELABORATA;
				break;
			case IN_CORSO_DI_ELABORAZIONE:
				type = StatoRichiestaType.IN_CORSO_DI_ELABORAZIONE;
				break;
			default:
				String methodName = "toStatoRichiestaType";
				LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName, false);
				lw.warn("StatoRichiestaEnum:" + en.name() + " non convertibile in StatoRichiestaType");
				type = null;
				break;
			}
		}

		return type;
	}

	static public ColumnNameRichiestaEnum toColumnNameRichiestaEnum(ColumnNameRichiestaType type) {
		ColumnNameRichiestaEnum en = null;

		if (type != null) {
			switch (type) {
			case ID_RICHIESTA:
				en = ColumnNameRichiestaEnum.ID_RICHIESTA;
				break;
			case CODICE_FISCALE_ENTE:
				en = ColumnNameRichiestaEnum.CODICE_FISCALE_ENTE;
				break;
			case ID_TIPO_RICHIESTA:
				en = ColumnNameRichiestaEnum.ID_TIPO_RICHIESTA;
				break;
			case PAGAMENTO_SPONTANEO:
				en = ColumnNameRichiestaEnum.PAGAMENTO_SPONTANEO;
				break;
			case CODICE_VERSAMENTO:
				en = ColumnNameRichiestaEnum.CODICE_VERSAMENTO;
				break;
			case DATA_INSERIMENTO_RICHIESTA:
				en = ColumnNameRichiestaEnum.DATA_INSERIMENTO_RICHIESTA;
				break;
			case DATA_INVIO_AL_DESTINATARIO:
				en = ColumnNameRichiestaEnum.DATA_INVIO_AL_DESTINATARIO;
				break;
			default:
				String methodName = "toColumnNameRichiestaEnum";
				LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName, false);
				lw.warn("ColumnNameRichiestaType:" + type.name() + " non convertibile in ColumnNameRichiestaEnum");
				en = null;
				break;
			}
		}

		return en;
	}

	static public SortTypeEnum toSortTypeEnum(SortTypeType type) {
		SortTypeEnum en = null;

		if (type != null) {
			switch (type) {
			case ASC:
				en = SortTypeEnum.ASC;
				break;
			case DESC:
				en = SortTypeEnum.DESC;
				break;
			default:
				String methodName = "toSortTypeEnum";
				LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName, false);
				lw.warn("SortTypeType:" + type.name() + " non convertibile in SortTypeEnum");
				en = null;
				break;
			}
		}

		return en;
	}

	static public EmbeddedXMLType stringToEmbeddedXMLType(String string) {
		EmbeddedXMLType embeddedXMLType = null;

		if (string != null) {
			embeddedXMLType = new EmbeddedXMLType();
			//
			StringReader stringReader = new StringReader(string);
			Source source = new StreamSource(stringReader);
			embeddedXMLType.setContenuto(source);
		}

		return embeddedXMLType;
	}

	static public Date toDate(XMLGregorianCalendar xmlGregorianCalendar) {
		Date date = null;

		if (xmlGregorianCalendar != null) {
			date = xmlGregorianCalendar.toGregorianCalendar().getTime();
		}

		return date;
	}

	static public XMLGregorianCalendar toXMLGregorianCalendar(Date date) throws FacadeException {
		XMLGregorianCalendar xmlGregCalendar = null;

		if (date != null) {
			try {
				GregorianCalendar gregorianCalendar = new GregorianCalendar();
				gregorianCalendar.setTime(date);
				xmlGregCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);

			} catch (DatatypeConfigurationException e) {
				throw new FacadeException(IssueEnum.GENERIC_SYSTEM_ERROR.getCod(), e.getMessage(), e);
			}
		}

		return xmlGregCalendar;
	}

	static public String embeddedXMLTypeToString(EmbeddedXMLType embeddedXMLType) throws FacadeException {
		String string = null;

		if (embeddedXMLType != null) {
			try {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				StreamResult result = new StreamResult(baos);
				TransformerFactory transFactory = TransformerFactory.newInstance();
				Transformer transformer = transFactory.newTransformer();
				transformer.transform(embeddedXMLType.getContenuto(), result);
				byte[] byteArray = baos.toByteArray();
				string = new String(byteArray);

			} catch (TransformerException e) {
				throw new FacadeException(IssueEnum.GENERIC_SYSTEM_ERROR.getCod(), e.getMessage(), e);
			}
		}

		return string;
	}

}
