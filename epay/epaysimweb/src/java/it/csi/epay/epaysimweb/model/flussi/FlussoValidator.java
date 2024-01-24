/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysimweb.model.flussi;

import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class FlussoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return true;		
	}

	@Override
	public void validate(Object raw, Errors errors) {
		
		if (raw instanceof RicercaFlussoFiltroVO) {
			RicercaFlussoFiltroVO o = (RicercaFlussoFiltroVO)raw;
			if (o.isEmpty()) {
				errors.rejectValue(
					"validitaGenerica", 
					"validitaGenerica.validitaGenerica", 
					"E' necessario inserire almeno un criterio di ricerca"
				);
			}
			
			if ( (o.getDataElaborazioneDa() != null && o.getDataElaborazioneA() == null) ) {
                o.setDataElaborazioneA ( new Date () );
			}
			
			if ( (o.getDataElaborazioneDa() == null && o.getDataElaborazioneA() != null)) {
				errors.rejectValue(
						"dataElaborazioneDa", 
						"dataElaborazioneDa.validitaGenerica", 
						"E' necessario inserire anche la data di inizio periodo"
					);
			}
		}
		
	}
	
}
