/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import it.csi.epay.epaymodric.business.epaymodric.manager.CodiciVersamentoConfigManager;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTCodiciVersamentoConfig;
import it.csi.epay.epaymodric.business.epaymodric.repository.CodiciVersamentoConfigRepository;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.RiconciliazioneException;

@Service
public class CodiciVersamentoConfigManagerImpl implements CodiciVersamentoConfigManager {

	@Autowired
	private CodiciVersamentoConfigRepository repository;

	@Override
	public Boolean leggiCodiciVersamentoConfig(String idEnte, String codiceVersamento)
			throws RiconciliazioneException {

		List<CblTCodiciVersamentoConfig> codici = repository.findByIdEnteAndCodiceVersamento(idEnte, codiceVersamento);

		if (!CollectionUtils.isEmpty(codici)) {
			if (codici.size() > 1) {
				String message = String.format(
						"Flag configurativo non univoco per l'ente [%s] e codice versamento [%s]", idEnte,
						codiceVersamento);
				throw new RiconciliazioneException(message);
			} else {
				return codici.get(0).getFlgApplicazioneEpay();
			}
		} else {
			String message = String.format("Flag configurativo non presente per l'ente [%s] e codice versamento [%s]",
					idEnte, codiceVersamento);
			throw new RiconciliazioneException(message);
		}
	}

}
