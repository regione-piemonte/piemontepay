/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.model.riferimentocontabile.validators;

import it.csi.epay.epaypacatalogweb.common.Constants;
import it.csi.epay.epaypacatalogweb.model.riferimentocontabile.ModificaRiferimentoContabileVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Calendar;
import java.util.Date;


public class ModificaRiferimentoContabileValidator implements Validator {

	@Override
	public boolean supports ( Class<?> paramClass ) {
		return ModificaRiferimentoContabileVO.class.equals ( paramClass );
	}

	@Override
	public void validate ( Object obj, Errors errors ) {

		ModificaRiferimentoContabileVO input = (ModificaRiferimentoContabileVO) obj;

		ValidationUtils.rejectIfEmpty ( errors, "dataFineValidita", "dataFineValidita.required", "Data fine validita obbligatoria" );

		ValidationUtils.rejectIfEmpty ( errors, "dataInizioValidita", "dataInizioValidita.required", "Data inizio validita obbligatoria" );

		if ( input.getDataInizioValidita () != null && input.getDataFineValidita () != null ) {
			if ( input.getDataFineValidita ().before ( input.getDataInizioValidita () ) ) {
				errors.rejectValue ( "dataFineValidita", "dataFineValidita.validitaGenerica",
								"La data fine validita' deve essere successiva alla data inizio validita'" );
			}
		}

		if ( input.getId () == null ) {
			ValidationUtils.rejectIfEmpty ( errors, "idCodiceVersamento", "idCodiceVersamento.required", "Codice versamento obbligatorio" );
		}

		if ( input.getId () == null || "duplica".equalsIgnoreCase ( input.getTipologiaModifica () ) ) {
			ValidationUtils.rejectIfEmpty ( errors, "annoEsercizio", "annoEsercizio.required", "Anno esercizio obbligatorio" );

			ValidationUtils.rejectIfEmpty ( errors, "datoSpecificoRiscossione", "datoSpecificoRiscossione.required",
							"Dato specifico riscossione tassonomia obbligatorio" );

			ValidationUtils.rejectIfEmpty ( errors, "idTassonomia", "idTassonomia.required", "Dato specifico riscossione tassonomia obbligatorio" );

			if ( Constants.TIPO_PAGAMENTO_SPONTANEO.equals ( input.getTipoPagamento () ) ) { // es cov codice AS20
				ValidationUtils.rejectIfEmpty ( errors, "annoAccertamento", "annoAccertamento.required",
								"Anno accertamento obbligatorio in caso di pagamento Spontaneo" );
				ValidationUtils.rejectIfEmpty ( errors, "numeroAccertamento", "numeroAccertamento.required",
								"Numero accertamento obbligatorio in caso di pagamento Spontaneo" );
				Date inizio = input.getDataInizioValidita ();
				Date fine = input.getDataFineValidita ();
				if ( input.getAnnoEsercizio () != null && inizio != null && fine != null ) {
					Calendar calendar = Calendar.getInstance ();
					calendar.setTime ( inizio );
					int annoInizio = calendar.get ( Calendar.YEAR );
					calendar.setTime ( fine );
					int annoFine = calendar.get ( Calendar.YEAR );
					if ( ( annoInizio + annoFine ) != 2 * input.getAnnoEsercizio () ) {
						errors.rejectValue ( "annoEsercizio", "annoEsercizio.validitaGenerica",
										"In caso di pagamento spontaneo anno esercizio deve essere lo stesso di data inizio e fine validita'" );
					}
				}
			} else {
				if ( input.getAnnoAccertamento () != null && input.getNumeroAccertamento () == null ) {
					errors.rejectValue ( "numeroAccertamento", "numeroAccertamento.validitaGenerica",
									"Se anno accertamento e' valorizzato, deve essere valorizzato anche il numero accertamento" );
				}
				if ( input.getAnnoAccertamento () == null && input.getNumeroAccertamento () != null ) {
					errors.rejectValue ( "annoAccertamento", "annoAccertamento.validitaGenerica",
									"Se numero accertamento e' valorizzato, deve essere valorizzato anche l'anno accertamento" );
				}
			}

			if ( !StringUtils.isEmpty ( input.getDatoSpecificoRiscossione () ) && input.getDatoSpecificoRiscossione ().length () != 12 ) {
				errors.rejectValue ( "datoSpecificoRiscossione", "datoSpecificoRiscossione.validitaGenerica",
								"Il dato Specifico Riscossione deve essere di dodici caratteri" );
			}

			if ( input.getAnnoAccertamento () != null && input.getAnnoAccertamento () < 0 ) {
				errors.rejectValue ( "annoAccertamento", "annoAccertamento.validitaGenerica",
								"L'Anno Accertamento deve essere positivo" );
			}

			if ( input.getNumeroAccertamento () != null && input.getNumeroAccertamento () < 0 ) {
				errors.rejectValue ( "numeroAccertamento", "numeroAccertamento.validitaGenerica",
								"Il Numero Accertamento deve essere positivo" );
			}

			if ( input.getAnnoEsercizio () != null && input.getAnnoEsercizio () < 0 ) {
				errors.rejectValue ( "annoEsercizio", "annoEsercizio.validitaGenerica",
								"L'Anno Esercizio deve essere positivo" );
			}

			if ( input.getNumeroArticolo () != null && input.getNumeroArticolo () < 0 ) {
				errors.rejectValue ( "numeroArticolo", "numeroArticolo.validitaGenerica",
								"Il Numero Articolo deve essere positivo" );
			}

			if ( input.getId () == null || "duplica".equalsIgnoreCase ( input.getTipologiaModifica () ) ) {
				// con sto check si rompe qualunque INSERIMENTO di rif contabile -> ValidationUtils.rejectIfEmpty ( errors, "codiceTipologiaDatoSpecificoRiscossione", "codiceTipologiaDatoSpecificoRiscossione.required",
				//"Tipologia Dato specifico di riscossione obbligatorio" );

				if ( !StringUtils.isBlank ( input.getCodiceTipologiaDatoSpecificoRiscossione () ) ) {
					ValidationUtils.rejectIfEmpty ( errors, "datoSpecificoRiscossione", "datoSpecificoRiscossione.required",
									"Dato specifico di riscossione obbligatorio" );
					ValidationUtils.rejectIfEmpty ( errors, "descrizioneDatoSpecificoRiscossione",
									"descrizioneDatoSpecificoRiscossione.required", "Descrizione dato specifico di riscossione obbligatorio" );

					if ( input.getDatoSpecificoRiscossione () != null && !input.getDatoSpecificoRiscossione ().matches ( "\\S{3,138}" ) ) {
						errors.rejectValue ( "datoSpecificoRiscossione", "datoSpecificoRiscossione.validitaGenerica",
										"Formato del campo non corretto" );
					}
				}
			}

			if ( input.getDataFineValidita () != null ) {
				if ( input.getDataInizioValiditaCodiceTassonomico () != null
								&& input.getDataFineValidita ().before ( input.getDataInizioValiditaCodiceTassonomico () )
								|| input.getDataFineValiditaCodiceTassonomico () != null
								&& input.getDataFineValidita ().after ( input.getDataFineValiditaCodiceTassonomico () ) ) {
					errors.rejectValue ( "dataFineValidita", "dataFineValidita.validitaGenerica",
									"La data fine validita' deve essere compresa tra la data inizio validita' e la data fine validita' del codice tassonomico" );
				}
			}
		}

		if ( Boolean.TRUE.equals ( input.getFlagElementiMultibeneficiario () )
						&& Boolean.TRUE.equals ( input.getFlagAssociaRifContSecondarioValue () ) ) {
			ValidationUtils.rejectIfEmpty ( errors, "idRifContabileSecondarioAssociato", "idRifContabileSecondarioAssociato.required",
							"Riferimento Contabile Associato obbligatorio" );
		}

		if ( Boolean.TRUE.equals ( input.getFlagAssociaRifContSecondarioValue () )
						&& Boolean.TRUE.equals ( input.getFlagAssociaRifContPrimarioValue () ) ) {
			ValidationUtils.rejectIfEmpty ( errors, "idRifContabileSecondarioAssociato", "idRifContabileSecondarioAssociato.required",
							"Riferimento Contabile Associato puo' essere o primario o secondario" );
		}

		// validazione solo per TARI/TEFA
		if ( input.getDescrizioneDatoSpecificoRiscossione () != null && input.getDescrizioneDatoSpecificoRiscossione ().toLowerCase ().contains ( "tari," ) ) {
			ValidationUtils.rejectIfEmpty ( errors, "codiceTributo", "codiceTributo.required",
							"La compilazione del campo e' obbligatoria per pagamento riferito al tributo TARI-TEFA" );
		}
	}
}
