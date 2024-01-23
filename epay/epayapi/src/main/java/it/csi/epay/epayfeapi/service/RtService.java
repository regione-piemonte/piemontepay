/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.service;

import it.csi.epay.epayfeapi.entity.EpayTRt;
import it.csi.epay.epayfeapi.entity.EpayTRtPdf;
import it.csi.epay.epayfeapi.repository.RtRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;


@ApplicationScoped
@Transactional
public class RtService {

	@Inject
	RtRepository rtRepository;

	public EpayTRt findByIdRegistroAndCodEsitoPagamento ( Long idRegistro, Integer codEsitoPagamento ) {
		return rtRepository.findByIdRegistroAndCodEsitoPagamento ( idRegistro, codEsitoPagamento );
	}

	public EpayTRtPdf findPdfByIdRegistroAndCodEsitoPagamento ( Long idRegistro, Integer codEsitoPagamento ) {
		return rtRepository.findPdfByIdRegistroAndCodEsitoPagamento ( idRegistro, codEsitoPagamento );
	}

}
