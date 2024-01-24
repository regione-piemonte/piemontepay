/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.model.utente.validators;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.csi.epay.epaypacatalogweb.model.utente.RicercaUtenteFiltroVO;


public class RicercaUtenteValidator implements Validator {

    @Override
    public boolean supports ( Class<?> paramClass ) {
        return RicercaUtenteFiltroVO.class.equals ( paramClass );
    }

    @Override
    public void validate ( Object obj, Errors errors ) {

        RicercaUtenteFiltroVO input = (RicercaUtenteFiltroVO) obj;

        if ( StringUtils.isBlank ( input.getCodiceFiscale () ) &&
            StringUtils.isBlank ( input.getNome () ) &&
            StringUtils.isBlank ( input.getCognome () ) &&
            StringUtils.isBlank ( input.getEmail () ) &&
            StringUtils.isBlank ( input.getCodiceCategoriaCdu () ) &&
            StringUtils.isBlank ( input.getCodiceCdu () ) &&
            StringUtils.isBlank ( input.getCodiceTematica () ) &&
            ( input.getIdCodiceVersamento () == null ) &&
            ( input.getSoloUtentiInVita () == null || !input.getSoloUtentiInVita () ) ) {
            errors.rejectValue ( "id", "id.validitaGenerica", "E' necessario inserire almeno un criterio di ricerca." );
        }
    }

}
