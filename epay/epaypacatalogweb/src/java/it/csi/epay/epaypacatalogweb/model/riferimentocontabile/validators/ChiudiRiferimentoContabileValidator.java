/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.model.riferimentocontabile.validators;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.csi.epay.epaypacatalogweb.model.riferimentocontabile.ChiudiRiferimentoContabileVO;


public class ChiudiRiferimentoContabileValidator implements Validator {

    @Override
    public boolean supports ( Class<?> paramClass ) {
        return ChiudiRiferimentoContabileVO.class.equals ( paramClass );
    }

    @Override
    public void validate ( Object obj, Errors errors ) {

        ChiudiRiferimentoContabileVO input = (ChiudiRiferimentoContabileVO) obj;

        input.setCodiceTipologiaDatoSpecificoRiscossione ( sanitizeString ( input.getCodiceTipologiaDatoSpecificoRiscossione () ) );

        ValidationUtils.rejectIfEmpty ( errors, "id", "id.required", "ID obbligatoria" );

        ValidationUtils.rejectIfEmpty ( errors, "dataFineValidita", "dataFineValidita.required", "Data fine validita' obbligatoria" );

        ChiudiRiferimentoContabileVO vo = (ChiudiRiferimentoContabileVO) obj;

        if ( null!= vo.getDataFineValidita () &&  null!= vo.getDataInizioValidita  () ) 
        {
            if ( vo.getDataFineValidita ().before ( vo.getDataInizioValidita () ) ) {
                errors.rejectValue ( "dataFineValidita", "dataFineValidita.validitaGenerica",
                                "La Data Fine Validita' deve essere successiva alla Data Inizio Validita'" );
            }
        }
    }

    public static String sanitizeString ( String raw ) {
        if ( StringUtils.isNotBlank ( raw ) ) {
            raw = raw.replaceAll ( "[^a-zA-Z0-9]", "0" );
        }
        return raw;
    }
}
