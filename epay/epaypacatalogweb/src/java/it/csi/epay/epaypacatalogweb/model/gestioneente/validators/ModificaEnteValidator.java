/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.model.gestioneente.validators;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.checkdigit.IBANCheckDigit;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.csi.epay.epaypacatalogweb.integration.mapper.ParentMapper;
import it.csi.epay.epaypacatalogweb.model.gestioneente.ModificaEnteVO;
import it.csi.epay.epaypacatalogweb.model.validators.BicValidator;
import it.csi.epay.epaypacatalogweb.model.validators.EPayValidatorUtils;

/**
 * Validatore del form di modifica Ente
 *
 * @author Alessandro
 *
 */
public class ModificaEnteValidator implements Validator {

    private static long LOGO_MAX_FILE_SIZE = 1048576L;

    @Override
    public boolean supports(Class<?> paramClass) {
        return ModificaEnteVO.class.equals(paramClass);
    }
    
    @Override
    public void validate(Object obj, Errors errors) {

        //		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codiceFiscale", "codiceFiscale.required",
        //				"CodiceFiscale obbligatorio");
        ModificaEnteVO mevo = (ModificaEnteVO) obj;
        IBANCheckDigit ibanValidator = new IBANCheckDigit ();

        //		if (!EPayValidatorUtils.isValidCodiceFiscale(mevo.getCodiceFiscale())) {
        //			errors.rejectValue("codiceFiscale", "controlloFormale", new Object[] { "'codiceFiscale'" },
        //					"Il codice fiscale non rispetta le regole formali di costruzione");
        //		}

        if ( StringUtils.isNotBlank ( mevo.getEmail () ) ) {
            if ( !EPayValidatorUtils.isValidEmail ( mevo.getEmail () ) ) {
                errors.rejectValue ( "email", "email.validitaGenerica", "Email non valida" );
            }
        }

        if (mevo.getFlagRiconciliazioneVersamenti() == null || !mevo.getFlagRiconciliazioneVersamenti()) {
            mevo.setFlagAccertamento(false);
            mevo.setFlagRicezioneErrori(false);
            mevo.setCodicePeriodicitaSchedulazioneRiconciliazione(null);
            mevo.setCodiceTipologiaAccertamento(null);
            mevo.setCodiceModalitaAcquisizioneProvvisori(null);
            mevo.setGiornoSchedulazione(null);
        }

        if (mevo.getFlagRiconciliazioneVersamenti() != null &&
                        mevo.getFlagRiconciliazioneVersamenti() &&
                        mevo.getCodicePeriodicitaSchedulazioneRiconciliazione() != null &&
                        !mevo.getCodicePeriodicitaSchedulazioneRiconciliazione().isEmpty()) {

            String codPeriodicita = mevo.getCodicePeriodicitaSchedulazioneRiconciliazione();
            String valPeriodicita = mevo.getGiornoSchedulazione();

            if ( "GIO".equals ( codPeriodicita ) || "SGF".equals ( codPeriodicita ) ) {
                mevo.setGiornoSchedulazione(null);
            } else {
                if (valPeriodicita == null || valPeriodicita.isEmpty()) {
                    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "giornoSchedulazione", "giornoSchedulazione",
                                    "Giorno di schedulazione obbligatorio");
                } else {
                    if ("ANN".equals(codPeriodicita)) {
                        try {
                            String val = ParentMapper.daDataAGiorno(valPeriodicita);
                            if (val == null || val.isEmpty() || Integer.valueOf(val) < 1 || Integer.valueOf(val) > 366) {
                                errors.rejectValue("giornoSchedulazione", "giornoSchedulazione", "Il giorno di schedulazione deve essere compreso fra 1 e 366");
                            }
                        } catch (Exception e) {
                            errors.rejectValue("giornoSchedulazione", "giornoSchedulazione", "Il giorno di schedulazione non  valido");
                        }
                    } else {
                        try {
                            Integer val = Integer.valueOf(valPeriodicita);
                            Integer max = 0;
                            switch (codPeriodicita) {
                            case "SET":
                                max = 7;
                                break;
                            case "MEN":
                                max = 30;
                                break;
                            case "BIM":
                                max = 30;
                                break;
                            case "TRI":
                                max = 30;
                                break;
                            case "QUA":
                                max = 30;
                                break;
                            case "SEM":
                                max = 30;
                                break;
                            }
                            if (val < 1 || val > max) {
                                errors.rejectValue("giornoSchedulazione",
                                    "giornoSchedulazione",
                                    "Il giorno di schedulazione deve essere compreso fra 1 e " + max);
                            }
                        } catch (Exception e) {
                            errors.rejectValue("giornoSchedulazione", "giornoSchedulazione", "Il giorno di schedulazione non  valido");
                        }
                    }
                }
            }
        } else {
            mevo.setGiornoSchedulazione(null);
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "denominazione", "denominazione.required",
                        "Denominazione Obbligatoria");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "indirizzo", "indirizzo.required",
                        "Indirizzo Obbligatorio");
        ValidationUtils.rejectIfEmptyOrWhitespace ( errors, "civico", "civico.required",
                        "Civico Obbligatorio" );
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "localita", "localita.required", "Localita' obbligatoria");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cap", "cap.required", "CAP obbligatorio");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "siglaProvincia", "siglaProvincia.required",
                        "Provincia obbligatoria");

        if (mevo.getFlagRiconciliazioneVersamenti()) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codicePeriodicitaSchedulazioneRiconciliazione", "codicePeriodicitaSchedulazioneRiconciliazione.required",
                            "Periodicita' schedulazione riconciliazione Obbligatoria");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codiceTipologiaAccertamento", "codiceTipologiaAccertamento.required",
                            "Tipologia accertamento Obbligatoria");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codiceModalitaAcquisizioneProvvisori",
                "codiceModalitaAcquisizioneProvvisori.required", "Modalita' Acquisizione Provvisori obbligatoria");
        }

        if (mevo.getFlagRicezioneFlussoBaseRendicontazione()) {
            if (!mevo.getFlagQualsiasiCodiceVersamento()) {
                // almeno una voce selezionata
                boolean almenoUna = false;
                if (mevo.getIdCodiciVersamentoSelezionati() != null) {
                    for (Integer v : mevo.getIdCodiciVersamentoSelezionati().values()) {
                        if (v != null && v > 0) {
                            almenoUna = true;
                            break;
                        }
                    }
                }
                if (!almenoUna) {
                    errors.rejectValue("flagQualsiasiCodiceVersamento",
                        "flagQualsiasiCodiceVersamento.validitaGenerica",
                                    "E' necessario selezionare 'qualsiasi versamento' oppure almeno un codice versamento dalla lista");
                }
            }
        }

        if ( mevo.getNewLogo () != null &&
                        mevo.getNewLogo ().getSize () > 0 &&
                        !StringUtils.isEmpty ( mevo.getNewLogo ().getOriginalFilename () ) ) {
            String fn = mevo.getNewLogo ().getOriginalFilename ().toLowerCase ();
            if ( ! ( fn.endsWith ( ".jpg" ) || fn.endsWith ( ".jpeg" ) ||
                            fn.endsWith ( ".png" ) || fn.endsWith ( ".gif" ) || fn.endsWith ( ".bmp" ) ) ) {
                errors.rejectValue ( "logoContent",
                    "logoContent.validitaGenerica",
                                "Il tipo di file selezionato non e' permesso." );
            }
        }

        if ( mevo.getNewLogo () != null && mevo.getNewLogo ().getSize () > LOGO_MAX_FILE_SIZE ) {
            errors.rejectValue ( "logoContent",
                "logoContent.validitaGenerica",
                "Il logo non puo' superare la dimensione di 1MB" );
        }


        //IBAN SEMPRE OBBLIGATORIO - EPAY-98
        //if (StringUtils.isNotBlank(mevo.getIban()) || StringUtils.isNotBlank(mevo.getBic())) {
            
            //----------------------------------------------------------------//
            
            if (StringUtils.isNotBlank(mevo.getIban())) {
                if ( mevo.getIban () != null && !mevo.getIban ().toLowerCase ().startsWith ( "it" ) ) {
                    errors.rejectValue ( "iban", "iban", "Il codice iban non risulta italiano." );
                } else if ( mevo.getIban () != null && !ibanValidator.isValid ( mevo.getIban () ) ) {
                    errors.rejectValue ( "iban", "iban", "Il codice iban non risulta corretto." );
                }
                mevo.setIban ( mevo.getIban ().toUpperCase () );
            } else {
                ValidationUtils.rejectIfEmptyOrWhitespace ( errors, "iban", "iban.required", "IBAN Obbligatorio" );
            }
            
            //----------------------------------------------------------------//
            
            if (StringUtils.isNotBlank(mevo.getBic())) {
                
                BicValidator bicValidator = new BicValidator();
                
                if ( mevo.getBic () != null && !bicValidator.isValid ( mevo.getBic() ) ) {
                    errors.rejectValue ( "bic", "bic", "Il codice bic non risulta corretto." );
                }
                mevo.setBic ( mevo.getBic().toUpperCase () );
            } else {
                ValidationUtils.rejectIfEmptyOrWhitespace ( errors, "bic", "bic.required", "BIC Obbligatorio" );
            }
            
            if (Boolean.TRUE.equals ( mevo.getFlagAdesioneCittaFacile () ) )
            {
                if (StringUtils.isEmpty ( mevo.getTemplateEmailId () ) ||
                                StringUtils.isEmpty ( mevo.getUrlDominio () )||
                                StringUtils.isEmpty ( mevo.getCodiceIpa () ) )
                {
                    errors.rejectValue ( "flagAdesioneCittaFacile", "flagAdesioneCittaFacile", "In caso di adesione a CittaFacile il template id, la url dominio e il codice ipa sono obbligatori" );
                }
                
            }
            else
            {
                mevo.setUrlDominio ( "" );
                mevo.setTemplateEmailId ( "" );
                mevo.setCodiceIpa ("");
            }
        //}
        //IBAN SEMPRE OBBLIGATORIO - EPAY-98

    }

}
