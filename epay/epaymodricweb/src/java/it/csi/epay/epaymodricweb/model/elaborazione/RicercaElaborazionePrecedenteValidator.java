/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.model.elaborazione;

import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class RicercaElaborazionePrecedenteValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public void validate(Object raw, Errors errors) {

        if (raw instanceof RicercaElaborazionePrecedenteFiltroVO) {
            RicercaElaborazionePrecedenteFiltroVO o = (RicercaElaborazionePrecedenteFiltroVO)raw;

            if ( (o.getDataInizio() != null && o.getDataFine() == null) ) {
                o.setDataFine ( new Date () );
            }

            if ( (o.getDataFine() != null && o.getDataInizio() == null)) {
                errors.rejectValue(
                    "dataInizio",
                    "dataInizio.validitaGenerica",
                    "E' necessario inserire anche la data di inizio periodo"
                                );
            }

            if ( o.getDataInizio () != null
                && o.getDataFine () != null
                && ( o.getDataInizio ().after ( o.getDataFine () ) ) ) {
                errors.rejectValue (
                    "dataInizio",
                    "dataInizio.validitaGenerica",
                    "La \"data inizio elaborazione da\" deve essere precedente o uguale alla \"data inizio elaborazione a\"" );
            }
        }

    }

}
