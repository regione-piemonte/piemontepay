/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.manager;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import it.csi.epay.epayservices.integration.db.entities.EpayTPagamentoComponenti;
import it.csi.epay.epayservices.interfaces.exception.ComponentiImportoException;
import it.csi.epay.epayservices.model.ComponentiImportoInput;
import it.csi.epay.epayservices.model.ComponentiImportoOutput;
import it.csi.epay.epayservices.model.SingoloComponenteImportoOutput;

@Stateless(name = "ComponentiImportoManager", mappedName = "ComponentiImportoManager")
public class ComponentiImportoManager extends _Manager {

	public static final String RETURN_CODE_OK = "000";
	public static final String RETURN_MESSAGE_OK = "Operazione completata correttamente";
	public static final String RETURN_CODE_KO_BAD_INPUT = "400";
	public static final String RETURN_MESSAGE_KO_BAD_INPUT = "I seguenti parametri di input non sono valorizzati correttamente: %s";
	public static final String RETURN_CODE_KO_ERROR = "500";
	public static final String RETURN_MESSAGE_KO_ERROR = "Errore durante l'esecuzione del servizio";

	private static final String REGEX_TWO_DIGITS = "(^\\d{1,}\\.\\d{2}$)|(^\\d{1,}$)";

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Metodo che estrae i componenti di un importo i cui dati costitutivi sono passati come parametro di input
	 * @param componentiImportoInput
	 * @return un oggetto contenente un esito (codice e descrizione) e una lista di componenti importo
	 */
	public ComponentiImportoOutput estraiComponentiImporto(ComponentiImportoInput componentiImportoInput) {

		log.debug("estraiComponentiImporto", "entrata nel metodo");
		
		List<EpayTPagamentoComponenti> tPagamentoComponenti = null;

		ComponentiImportoOutput output = new ComponentiImportoOutput();

		try {
			/*
			 * Esecuzione della logica principale di estrazione
			 */
			tPagamentoComponenti = execute(componentiImportoInput);

			/*
			 * Mapping entity -> singolo componente importo
			 */
			if (null != tPagamentoComponenti) {
				for (EpayTPagamentoComponenti componenteImporto : tPagamentoComponenti) {
					SingoloComponenteImportoOutput singoloComponenteImportoOutput = new SingoloComponenteImportoOutput();
					singoloComponenteImportoOutput.setAnnoAccertamento(componenteImporto.getAnnoAccertamento());
					singoloComponenteImportoOutput
							.setDatiSpecificiRiscossione(componenteImporto.getDatiSpecificiRiscossione());
					singoloComponenteImportoOutput
							.setImportoSingoloVersamento(componenteImporto.getImporto().toString());
					singoloComponenteImportoOutput.setNumeroAccertamento(componenteImporto.getNumeroAccertamento());
					
					output.getComponentiImportoList().add(singoloComponenteImportoOutput);
				}
			}

			output.setCodiceEsito(RETURN_CODE_OK);
			output.setDescrizioneEsito(RETURN_MESSAGE_OK);

		} catch (ComponentiImportoException cie) {
			output.setCodiceEsito(cie.getCodice());
			output.setDescrizioneEsito(cie.getMessage());
			log.error("estraiComponentiImporto", "Errori nell'esecuzione del servizio", cie);
		}
		
		log.debug("estraiComponentiImporto", "uscita dal metodo");
		return output;
	}

	private List<EpayTPagamentoComponenti> execute(ComponentiImportoInput componentiImportoInput)
			throws ComponentiImportoException {

		log.debug("execute", "validazione dell'input - INIZIO");
		ComponentiImportoInputErrors componentiImportoInputErrors = validateInput(componentiImportoInput);
		log.debug("execute", "validazione dell'input - FINE");
		List<EpayTPagamentoComponenti> tPagamentoComponenti = null;

		if (componentiImportoInputErrors.getHasErrors()) {

			String inputErrors = String.format(RETURN_MESSAGE_KO_BAD_INPUT, componentiImportoInputErrors.prettyPrint());
			log.error("execute", "Parametri di input errati. " + inputErrors);
			throw new ComponentiImportoException(RETURN_CODE_KO_BAD_INPUT, inputErrors);
		}
		try {
			log.debug("execute", "Esecuzione query");
			TypedQuery<EpayTPagamentoComponenti> query = null;
			/*
			 * Query di estrazione dei componenti di un importo dato IUV e importo totale
			 */
			query = entityManager.createNamedQuery("EpayTPagamentoComponenti.findByIUVAndImporto",
					EpayTPagamentoComponenti.class);
			query.setParameter("iuv", componentiImportoInput.getIuvDatiSingoloPagamento());
			query.setParameter("importo", componentiImportoInput.getImportoDatiSingoloPagamento());
			tPagamentoComponenti = query.getResultList();
		} catch (NoResultException nre) {
			log.warn("estraiComponentiImporto", "Nessun risultato trovato");
		} catch (Exception e) {
			throw new ComponentiImportoException(RETURN_CODE_KO_ERROR, RETURN_MESSAGE_KO_ERROR);
		}

		return tPagamentoComponenti;
	}

	private ComponentiImportoInputErrors validateInput(ComponentiImportoInput componentiImportoInput) {

		List<ComponenteImportoValidationError> errorList = new LinkedList<ComponenteImportoValidationError>();

		ComponentiImportoInputErrors componentiImportoInputErrors = null;

		if (componentiImportoInput.getAnagraficaPagatore() == null
				|| componentiImportoInput.getAnagraficaPagatore().trim().length() == 0) {
			errorList.add(new ComponenteImportoValidationError("Anagrafica Pagatore", "Parametro non valorizzato"));
		}

		if (componentiImportoInput.getCodiceFiscaleEnte() == null
				|| componentiImportoInput.getCodiceFiscaleEnte().trim().length() == 0) {
			errorList.add(new ComponenteImportoValidationError("Codice Fiscale Ente", "Parametro non valorizzato"));
		}

		if (componentiImportoInput.getCodiceFiscalePagatore() == null
				|| componentiImportoInput.getCodiceFiscalePagatore().trim().length() == 0) {
			errorList.add(new ComponenteImportoValidationError("Codice Fiscale Pagatore", "Parametro non valorizzato"));
		}

		if (componentiImportoInput.getCodiceVersamento() == null
				|| componentiImportoInput.getCodiceVersamento().trim().length() == 0) {
			errorList.add(new ComponenteImportoValidationError("Codice Versamento", "Parametro non valorizzato"));
		}

		if (componentiImportoInput.getDatiSpecificiRiscossione() == null
				|| componentiImportoInput.getDatiSpecificiRiscossione().trim().length() == 0) {
			errorList.add(
					new ComponenteImportoValidationError("Dati Specifici di Riscossione", "Parametro non valorizzato"));
		}

		if (componentiImportoInput.getEsitoPagamento() == null
				|| componentiImportoInput.getEsitoPagamento().trim().length() == 0) {
			errorList.add(new ComponenteImportoValidationError("Esito Pagamento", "Parametro non valorizzato"));
		}
		
		/*
		 * Vengono ritenuti accettabili importi con zero o due decimali
		 */
		if (componentiImportoInput.getImportoDatiSingoloPagamento() != null
				&& !componentiImportoInput.getImportoDatiSingoloPagamento().matches(REGEX_TWO_DIGITS)) {
			errorList.add(new ComponenteImportoValidationError("Importo Dati Singolo Pagamento",
					"Importo con formato non corretto"));
		}

		if (componentiImportoInput.getIuvDatiSingoloPagamento() == null
				|| componentiImportoInput.getIuvDatiSingoloPagamento().trim().length() == 0) {
			errorList.add(
					new ComponenteImportoValidationError("IUV Dati Singolo Pagamento", "Parametro non valorizzato"));
		}

		if (componentiImportoInput.getTransactionId() == null
				|| componentiImportoInput.getTransactionId().trim().length() == 0) {
			errorList.add(new ComponenteImportoValidationError("Transaction ID", "Parametro non valorizzato"));
		}

		componentiImportoInputErrors = new ComponentiImportoInputErrors(errorList);

		return componentiImportoInputErrors;

	}

	/*
	 * Classe privata che modella un singolo errore di validazione dell'input
	 * field - descrizione del parametro di input
	 * message - messaggio di errore
	 */
	private class ComponenteImportoValidationError {

		private String field;
		private String message;

		public ComponenteImportoValidationError(String field, String message) {
			super();
			this.field = field;
			this.message = message;
		}

		public String getField() {
			return field;
		}

		public String getMessage() {
			return message;
		}
	}

	/*
	 * Classe privata che modella una lista di errori
	 * Ha il compito di salvarli all'interno di una Stringa raggruppando tutti gli errori su un singolo parametro
	 */
	private class ComponentiImportoInputErrors {

		private List<ComponenteImportoValidationError> erroriParametriInput;

		private Map<String, String> mapErrori = null;

		private Boolean hasErrors = false;

		public ComponentiImportoInputErrors(List<ComponenteImportoValidationError> erroriParametriInput) {

			this.erroriParametriInput = erroriParametriInput;

			this.hasErrors = new Boolean(null != this.erroriParametriInput && this.erroriParametriInput.size() > 0);
		}

		/*
		 * Metodo di salvataggio errori all'interno di una stringa, raggruppati per singolo campo
		 */
		public String prettyPrint() {

			String prettyPrintedErrors = null;

			hasErrors = null != this.erroriParametriInput && this.erroriParametriInput.size() > 0;

			if (hasErrors) {
				if (null == mapErrori) {
					mapErrori = populateMap();
				}
				prettyPrintedErrors = "";
				for (String key : this.mapErrori.keySet()) {
					prettyPrintedErrors += " " + key + "-> " + this.mapErrori.get(key) + "\n";
				}
				prettyPrintedErrors = prettyPrintedErrors.trim();
			}

			return prettyPrintedErrors;

		}

		private Map<String, String> populateMap() {

			this.mapErrori = new HashMap<String, String>();
			for (ComponenteImportoValidationError singleError : this.erroriParametriInput) {
				String message = this.mapErrori.get(singleError.getField());
				if (null == message) {
					this.mapErrori.put(singleError.getField(), singleError.getMessage());
				} else {
					message += " - " + singleError.getMessage();
					this.mapErrori.replace(singleError.getField(), message);
				}
			}

			return this.mapErrori;
		}

		public Boolean getHasErrors() {
			return hasErrors;
		}
	}
}
