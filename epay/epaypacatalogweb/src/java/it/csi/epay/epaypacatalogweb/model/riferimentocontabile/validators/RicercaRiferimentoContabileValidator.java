/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.model.riferimentocontabile.validators;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.csi.epay.epaypacatalogweb.model.riferimentocontabile.RicercaRiferimentoContabileFiltroVO;


public class RicercaRiferimentoContabileValidator implements Validator {

	@Override
	public boolean supports(Class<?> paramClass) {
		return RicercaRiferimentoContabileFiltroVO.class.equals(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {

		RicercaRiferimentoContabileFiltroVO input = (RicercaRiferimentoContabileFiltroVO) obj;

		if (StringUtils.isBlank(input.getDescrizioneCodiceVersamento()) &&
						StringUtils.isBlank(input.getCodiceMacrotipo()) &&
						StringUtils.isBlank(input.getCodiceTematica()) &&
						StringUtils.isBlank(input.getCodiceVoceEntrata()) &&
						input.getIdCodiceVersamento() == null) {
			errors.rejectValue("id", "id.validitaGenerica", "E' necessario inserire almeno un criterio di ricerca.");
		}
	}

}
