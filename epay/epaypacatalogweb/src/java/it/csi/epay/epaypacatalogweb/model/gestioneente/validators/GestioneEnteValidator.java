/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.model.gestioneente.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.csi.epay.epaypacatalogweb.model.gestioneente.EnteVO;
import it.csi.epay.epaypacatalogweb.model.gestioneente.ModificaEnteVO;
import it.csi.epay.epaypacatalogweb.model.gestioneente.RicercaEnteFiltroVO;

/**
 * Validatore dei form di Gestione Ente
 * 
 * @author fabio.fenoglio@eng.it
 *
 */
public class GestioneEnteValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> paramClass) {
		return RicercaEnteFiltroVO.class.equals(paramClass) ||
				EnteVO.class.equals(paramClass) || 
				ModificaEnteVO.class.equals(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		if (obj == null) {
			return;
			
		} else if (obj instanceof RicercaEnteFiltroVO) {
			
			new RicercaEnteValidator().validate(obj, errors);
			
		} else if (obj instanceof ModificaEnteVO) {
			
			new ModificaEnteValidator().validate(obj, errors);
		}
	}

}
