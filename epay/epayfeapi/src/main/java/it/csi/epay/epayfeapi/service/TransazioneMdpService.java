/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.service;

import it.csi.epay.epayfeapi.entity.EpayTTransazioneMdp;
import it.csi.epay.epayfeapi.repository.TransazioneMdpRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;


@ApplicationScoped
@Transactional
public class TransazioneMdpService {

	@Inject
	TransazioneMdpRepository trasTransazioneMdpRepository;

	public void save ( EpayTTransazioneMdp epayTTransazioneMdp ) {
		trasTransazioneMdpRepository.persist ( epayTTransazioneMdp );
	}
}
