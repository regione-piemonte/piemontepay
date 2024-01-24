/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.model.lock;

import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class LockValidator implements Validator {

    @Override
    public boolean supports ( Class<?> clazz ) {
        return true;
    }

    @Override
    public void validate ( Object raw, Errors errors ) {

        if ( raw instanceof RicercaLockFiltroVO ) {
            RicercaLockFiltroVO o = (RicercaLockFiltroVO) raw;
            if ( o.isEmpty () ) {
                errors.rejectValue (
                    "validitaGenerica",
                    "validitaGenerica.validitaGenerica",
                    "E' necessario inserire almeno un criterio di ricerca" );
            }

            if ( ( o.getDataLockDa () != null && o.getDataLockA () == null ) ) {
                o.setDataLockA ( new Date () );
            }

            if ( ( o.getDataLockDa () == null && o.getDataLockA () != null ) ) {
                errors.rejectValue (
                    "dataElaborazioneDa",
                    "dataElaborazioneDa.validitaGenerica",
                    "E' necessario inserire anche la data di inizio periodo" );
            }
        }

    }

}
