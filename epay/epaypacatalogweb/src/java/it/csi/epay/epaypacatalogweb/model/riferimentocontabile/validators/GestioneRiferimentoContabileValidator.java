/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.model.riferimentocontabile.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.csi.epay.epaypacatalogweb.model.riferimentocontabile.ChiudiRiferimentoContabileVO;
import it.csi.epay.epaypacatalogweb.model.riferimentocontabile.ModificaRiferimentoContabileVO;
import it.csi.epay.epaypacatalogweb.model.riferimentocontabile.RicercaRiferimentoContabileFiltroVO;
import it.csi.epay.epaypacatalogweb.model.riferimentocontabile.RiferimentoContabileStoricoVO;
import it.csi.epay.epaypacatalogweb.model.riferimentocontabile.RiferimentoContabileVO;


public class GestioneRiferimentoContabileValidator implements Validator {

    @Override
    public boolean supports ( Class<?> paramClass ) {
        return RicercaRiferimentoContabileFiltroVO.class.equals ( paramClass ) ||
            RiferimentoContabileVO.class.equals ( paramClass ) ||
            RiferimentoContabileStoricoVO.class.equals ( paramClass ) ||
            ModificaRiferimentoContabileVO.class.equals ( paramClass ) ||
            ChiudiRiferimentoContabileVO.class.equals ( paramClass );
    }

    @Override
	public void validate ( Object obj, Errors errors ) {
		if ( obj instanceof RicercaRiferimentoContabileFiltroVO ) {
			new RicercaRiferimentoContabileValidator ().validate ( obj, errors );
		} else if ( obj instanceof ChiudiRiferimentoContabileVO ) {
			new ChiudiRiferimentoContabileValidator ().validate ( obj, errors );
		} else if ( obj instanceof ModificaRiferimentoContabileVO ) { // va bene che entri qui in caso di NUOVO riferimento contabile
			new ModificaRiferimentoContabileValidator ().validate ( obj, errors );
		}
	}
}
