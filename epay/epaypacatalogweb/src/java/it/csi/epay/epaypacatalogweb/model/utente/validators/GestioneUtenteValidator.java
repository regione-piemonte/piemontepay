/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.model.utente.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.csi.epay.epaypacatalogweb.model.utente.ModificaUtenteVO;
import it.csi.epay.epaypacatalogweb.model.utente.RicercaUtenteFiltroVO;


public class GestioneUtenteValidator implements Validator {

    @Override
    public boolean supports ( Class<?> paramClass ) {
        return true;
        //        
        //        return RicercaUtenteFiltroVO.class.equals ( paramClass ) ||
        //            UtenteVO.class.equals ( paramClass ) ||
        //            AssociazioneUtenteTematicaVO.class.equals ( paramClass ) ||
        //            ModificaUtenteVO.class.equals ( paramClass );
    }

    @Override
    public void validate ( Object obj, Errors errors ) {
        if ( obj == null ) {

            return;

        } else if ( obj instanceof RicercaUtenteFiltroVO ) {

            new RicercaUtenteValidator ().validate ( obj, errors );

        } else if ( obj instanceof ModificaUtenteVO ) {

            new ModificaUtenteValidator ().validate ( obj, errors );

        } else {

            // va tutto bene

        }
    }
}
