/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.integration.mdp;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import it.csi.epay.epayservices.model.Anagrafica;
import it.csi.mdpcore.TransazioneExtraAttribute;

public class TeaUtils {

	private static enum TypeTEA {
		TEA_IDENTIFICAZIONE_DOMINIO("identificativoDominio"),
		TEA_IDENTIFICATIVO_STAZIONE_RICHIEDENTE("identificativoStazioneRichiedente"),
		
		TEA_MAIL_VERSANTE("mailVersante"),
		TEA_NAZIONE_VERSANTE("nazioneVersante"),
		TEA_PROVINCIA_VERSANTE("provinciaVersante"),
		TEA_LOCALITA_VERSANTE("localitaVersante"),
		TEA_CAP_VERSANTE("capVersante"),
		TEA_CIVICO_VERSANTE("civicoVersante"),
		TEA_INDIRIZZO_VERSANTE("indirizzoVersante"),
		TEA_ANAGRAFICA_VERSANTE("anagraficaVersante"),
		TEA_CODICE_IDENTIFICATIVO_UNIVOCO_VERSANTE("codiceIdentificativoUnivocoVersante"),
		TEA_MAIL_PAGATORE("mailPagatore"),
		TEA_NAZIONE_PAGATORE("nazionePagatore"),
		TEA_PROVINCIA_PAGATORE("provinciaPagatore"),
		TEA_LOCALITA_PAGATORE("localitaPagatore"),
		TEA_CAP_PAGATORE("capPagatore"),
		TEA_CIVICO_PAGATORE("civicoPagatore"),
		TEA_INDIRIZZO_PAGATORE("indirizzoPagatore"),
		TEA_TIPO_IDENTIFICATIVO_UNIVOCO_PAGATORE("tipoIdentificativoUnivocoPagatore"),
		TEA_ANAGRAFICA_PAGATORE("anagraficaPagatore"),
		TEA_NAZIONE_BENEFICIARIO("nazioneBeneficiario"),
		TEA_PROVINCIA_BENEFICIARIO("provinciaBeneficiario"),
		TEA_LOCALITA_BENEFICIARIO("localitaBeneficiario"),
		TEA_CAP_BENEFICIARIO("capBeneficiario"),
		TEA_CIVICO_BENEFICIARIO("civicoBeneficiario"),
		TEA_INDIRIZZO_BENEFICIARIO("indirizzoBeneficiario"),
		TEA_DENOM_UNIT_OPER_BENEFICIARIO("denomUnitOperBeneficiario"),
		TEA_CODICE_UNIT_OPER_BENEFICIARIO("codiceUnitOperBeneficiario"),
		TEA_DENOMINAZIONE_BENEFICIARIO("denominazioneBeneficiario"),
		TEA_CODICE_IDENTIFICATIVO_UNIVOCO_BENEFICIARIO("codiceIdentificativoUnivocoBeneficiario"),
		TEA_CODICE_IDENTIFICATIVO_UNIVOCO_PAGATORE("codiceIdentificativoUnivocoPagatore"),
		TEA_DATI_SPECIFICI_RISCOSSIONE("datiSpecificiRiscossione"),
		TEA_CAUSALE_VERSAMENTO("causaleVersamento"),
		TEA_CREDENZIALI_PAGATORE("credenzialiPagatore"),
		TEA_BIC_APPOGGIO("bicAppoggio"),
		TEA_IBAN_APPOGGIO("ibanAppoggio"),
		TEA_BIC_ACCREDITO("bicAccredito"),
		TEA_IBAN_ACCREDITO("ibanAccredito"),
		TEA_FIRMA_RICEVUTA("firmaRicevuta"),
		TEA_BIC_ADDEBITO("bicAddebito"),
		TEA_IBAN_ADDEBITO("ibanAddebito"),
		TEA_TIPO_IDENTIFICATIVO_UNIVOCO_VERSANTE("tipoIdentificativoUnivocoVersante"),
		TEA_AUTENTICAZIONE_SOGGETTO("autenticazioneSoggetto"),
		TEA_IDENTIFICATIVO_UNIVOCO_VERSAMENTO("identificativoUnivocoVersamento"),
		TEA_ID_UNIVOCO_PSP("idinformativapsp"),
		TEA_TIPO_VERSAMENTO("tipoVersamento"),
		TEA_COMMISSIONE_CARICO_PA("commissioneCaricoPA");
		

		private String value;

		private TypeTEA(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	static public enum TypeIdentificativoUnivoco {
		PERSONA_GIURIDICA("G"),
		PERSONA_FISICA("F");
		
		private String value;

		private TypeIdentificativoUnivoco(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}
	
	static public enum TypeAutenticazioneSoggetto {
		CIE_CNS("CNS"), 
		USERID_PASSAWORD("USR"), 
		ALTRO("OTH"),
		NON_APPLICABILE("N/A");
		
		private String value;

		private TypeAutenticazioneSoggetto(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}
	
	static public enum TypeTipoVersamento {
		 BONIFICO_BANCARIO_DI_TESORERIA("BBT"),
		 BOLLETTINO_POSTALE("BP"),
		 ADDEBITO_DIRETTO("AD"),
		 CARTA_DI_PAGAMENTO("CP"),
		 PAGAMENTO_ATTIVATO_PRESSO_PSP("PO");
		
		private String value;

		private TypeTipoVersamento(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	static public enum TypeFirmaRicevuta {
		 FIRMA_NON_RICHIESTA("0"),
		 CADES("1"),
		 XADES("3"),
		 ELETTRONICA_AVANZATA("4");
		
		private String value;

		private TypeFirmaRicevuta(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}
	
	private static TransazioneExtraAttribute newTea(TypeTEA typeTEA, final String value) {
		TransazioneExtraAttribute tea = new TransazioneExtraAttribute();
		tea.setName(typeTEA.getValue());
		tea.setValue(value);
		return tea;
	}

	public static List<TransazioneExtraAttribute> versante(final Anagrafica anagrafica) {
		List<TransazioneExtraAttribute> list = new ArrayList<TransazioneExtraAttribute>();
		if (StringUtils.isEmpty(anagrafica.getRagioneSociale())) {
			list.add(newTea(TypeTEA.TEA_ANAGRAFICA_VERSANTE, anagrafica.getNome() + " " + anagrafica.getCognome()));
		}
		else {
			list.add(newTea(TypeTEA.TEA_ANAGRAFICA_VERSANTE, anagrafica.getRagioneSociale()));
		}
		list.add(newTea(TypeTEA.TEA_INDIRIZZO_VERSANTE, anagrafica.getIndirizzo()));
		list.add(newTea(TypeTEA.TEA_MAIL_VERSANTE, anagrafica.getEmail()));
		list.add(newTea(TypeTEA.TEA_TIPO_IDENTIFICATIVO_UNIVOCO_VERSANTE, anagrafica.getCodiceFiscale().length() == 11 ? 
				TypeIdentificativoUnivoco.PERSONA_GIURIDICA.getValue() : TypeIdentificativoUnivoco.PERSONA_FISICA.getValue()));
		list.add(newTea(TypeTEA.TEA_CODICE_IDENTIFICATIVO_UNIVOCO_VERSANTE, anagrafica.getCodiceFiscale()));
		return list;
	}
	
	public static List<TransazioneExtraAttribute> pagatore(final Anagrafica anagrafica) {
		List<TransazioneExtraAttribute> list = new ArrayList<TransazioneExtraAttribute>();
		if (StringUtils.isEmpty(anagrafica.getRagioneSociale())) {
            list.add ( newTea ( TypeTEA.TEA_ANAGRAFICA_PAGATORE, StringUtils.substring ( anagrafica.getNome () + " " + anagrafica.getCognome (), 0, 70 ) ) );
		}
		else {
            list.add ( newTea ( TypeTEA.TEA_ANAGRAFICA_PAGATORE, StringUtils.substring ( anagrafica.getRagioneSociale (), 0, 70 ) ) );
		}
		list.add(newTea(TypeTEA.TEA_INDIRIZZO_PAGATORE, anagrafica.getIndirizzo()));
		list.add(newTea(TypeTEA.TEA_MAIL_PAGATORE, anagrafica.getEmail()));
		list.add(newTea(TypeTEA.TEA_TIPO_IDENTIFICATIVO_UNIVOCO_PAGATORE, anagrafica.getCodiceFiscale().length() == 11 ? 
				TypeIdentificativoUnivoco.PERSONA_GIURIDICA.getValue() : TypeIdentificativoUnivoco.PERSONA_FISICA.getValue()));
		list.add(newTea(TypeTEA.TEA_CODICE_IDENTIFICATIVO_UNIVOCO_PAGATORE, anagrafica.getCodiceFiscale()));
		return list;
	}

	public static TransazioneExtraAttribute informativaPSP(final String value) {
		return newTea(TypeTEA.TEA_ID_UNIVOCO_PSP, value);
	}
	
	public static TransazioneExtraAttribute causale(final String value) {
		return newTea(TypeTEA.TEA_CAUSALE_VERSAMENTO, value);
	}

	public static TransazioneExtraAttribute iuv(final String value) {
		return newTea(TypeTEA.TEA_IDENTIFICATIVO_UNIVOCO_VERSAMENTO, value);
	}
	
	public static TransazioneExtraAttribute autenticazioneSoggetto(final TypeAutenticazioneSoggetto value) {
		return newTea(TypeTEA.TEA_AUTENTICAZIONE_SOGGETTO, value.getValue());
	}

	public static TransazioneExtraAttribute datiSpecificiRiscossione(final String value) {
		return newTea(TypeTEA.TEA_DATI_SPECIFICI_RISCOSSIONE, value);
	}	
	
	public static void setTransactionId(List<TransazioneExtraAttribute> teas, final  String transactionId) {
		for (TransazioneExtraAttribute tea : teas) {
			tea.setTransactionId(transactionId);
		}
	}
}
