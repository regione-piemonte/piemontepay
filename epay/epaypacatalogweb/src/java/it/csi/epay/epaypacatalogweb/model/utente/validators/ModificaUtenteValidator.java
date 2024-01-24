/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.model.utente.validators;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.csi.epay.epaypacatalogweb.model.utente.ModificaUtenteVO;
import it.csi.epay.epaypacatalogweb.model.validators.EPayValidatorUtils;


public class ModificaUtenteValidator implements Validator {

    @Override
    public boolean supports ( Class<?> paramClass ) {
        return ModificaUtenteVO.class.equals ( paramClass );
    }

    @Override
    public void validate ( Object obj, Errors errors ) {

        ModificaUtenteVO input = (ModificaUtenteVO) obj;

        ValidationUtils.rejectIfEmpty ( errors, "codiceFiscale", "codiceFiscale.required", "Codice fiscale obbligatorio" );

        ValidationUtils.rejectIfEmpty ( errors, "nome", "nome.required", "Nome obbligatorio" );
        ValidationUtils.rejectIfEmpty ( errors, "cognome", "cognome.required", "Cognome obbligatorio" );
        //        ValidationUtils.rejectIfEmpty ( errors, "email", "email.required", "Email obbligatoria" );

        ValidationUtils.rejectIfEmpty ( errors, "dataInizioValidita", "dataInizioValidita.required", "Data inizio validita obbligatoria" );

        if ( null != input.getDataFineValidita () && null != input.getDataInizioValidita ()
                        && input.getDataFineValidita ().compareTo ( input.getDataInizioValidita () ) < 0 ) {
            errors.rejectValue ( "dataFineValidita", "dataFineValidita.required", "Data fine validita minore data inizio validita" );
        }

        if ( !StringUtils.isBlank ( input.getCodiceFiscale () ) ) {
            input.setCodiceFiscale ( input.getCodiceFiscale ().toUpperCase () );
        }

        if ( !StringUtils.isBlank ( input.getEmail () ) ) {
            if ( !EPayValidatorUtils.isValidEmail ( input.getEmail () ) ) {
                errors.rejectValue ( "email", "email.validitaGenerica", "Email non valida" );
            }
        }

        if ( !StringUtils.isBlank ( input.getCodiceFiscale () ) ) {
            if ( !EPayValidatorUtils.isValidCodiceFiscale ( input.getCodiceFiscale () ) ) {
                errors.rejectValue ( "codiceFiscale", "codiceFiscale.validitaGenerica", "Codice Fiscale non valido" );
            }
        }
    }

}
