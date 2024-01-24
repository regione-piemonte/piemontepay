/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.model.codiceversamento.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.csi.epay.epaypacatalogweb.model.codiceversamento.RicercaCodiceVersamentoFiltroVO;
import it.csi.epay.epaypacatalogweb.model.validators.EPayValidatorUtils;


public class RicercaCodiceVersamentoValidator implements Validator {

    @Override
    public boolean supports ( Class<?> paramClass ) {
        return RicercaCodiceVersamentoFiltroVO.class.equals ( paramClass );
    }

    @Override
    public void validate ( Object obj, Errors errors ) {

        RicercaCodiceVersamentoFiltroVO mevo = (RicercaCodiceVersamentoFiltroVO) obj;

        if ( !EPayValidatorUtils.almenoUnCampoValidato ( mevo ) ) {
            errors.rejectValue ( "id", "id.validitaGenerica", "E' necessario inserire almeno un criterio di ricerca." );
        }
    }

}
