/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.model.codiceversamento.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.csi.epay.epaypacatalogweb.model.codiceversamento.CodiceVersamentoVO;
import it.csi.epay.epaypacatalogweb.model.codiceversamento.ModificaCodiceVersamentoCollegatoVO;
import it.csi.epay.epaypacatalogweb.model.codiceversamento.ModificaCodiceVersamentoVO;
import it.csi.epay.epaypacatalogweb.model.codiceversamento.RicercaCodiceVersamentoFiltroVO;
import it.csi.epay.epaypacatalogweb.model.ppay.RicercaVoceEntrataPPayFiltroVO;
import it.csi.epay.epaypacatalogweb.model.ppay.validators.RicercaVoceEntrataValidator;


public class GestioneCodiceVersamentoValidator implements Validator {

    @Override
    public boolean supports ( Class<?> paramClass ) {
        return RicercaCodiceVersamentoFiltroVO.class.equals ( paramClass ) ||
            CodiceVersamentoVO.class.equals ( paramClass ) ||
            ModificaCodiceVersamentoVO.class.equals ( paramClass ) ||
            ModificaCodiceVersamentoCollegatoVO.class.equals ( paramClass ) ||
            RicercaVoceEntrataPPayFiltroVO.class.equals ( paramClass );
    }

    @Override
    public void validate ( Object obj, Errors errors ) {
        if ( obj == null ) {

            return;

        } else if ( obj instanceof RicercaCodiceVersamentoFiltroVO ) {

            new RicercaCodiceVersamentoValidator ().validate ( obj, errors );

        } else if ( obj instanceof ModificaCodiceVersamentoVO ) {

            new ModificaCodiceVersamentoValidator ().validate ( obj, errors );

        } else if ( obj instanceof ModificaCodiceVersamentoCollegatoVO ) {

            new ModificaCodiceVersamentoCollegatoValidator ().validate ( obj, errors );

        } else if ( obj instanceof RicercaVoceEntrataPPayFiltroVO ) {

            new RicercaVoceEntrataValidator ().validate ( obj, errors );

        } else {

            // va tutto bene

        }
    }
}
