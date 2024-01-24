/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.operations.decodifica;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.business.dispatcher.Operation;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationDispatchingContext;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationHandler;
import it.csi.epay.epaypacatalogsrv.business.util.SecurityUtils;
import it.csi.epay.epaypacatalogsrv.dto.decodifica.DecodificaOutputDto;
import it.csi.epay.epaypacatalogsrv.dto.decodifica.GetOpzioniInput;
import it.csi.epay.epaypacatalogsrv.dto.decodifica.GetOpzioniStatoAggiornamentoEnteOutput;
import it.csi.epay.epaypacatalogsrv.model.StatoAggiornamentoEnte;
import it.csi.epay.epaypacatalogsrv.repository.StatoAggiornamentoEnteRepository;
import it.csi.epay.epaypacatalogsrv.vo.Constants;

@Operation(consumes = GetOpzioniInput.class, produces = GetOpzioniStatoAggiornamentoEnteOutput.class)
@Component
public class GetOpzioniStatoAggiornamentoEnteOperation implements OperationHandler<GetOpzioniInput, GetOpzioniStatoAggiornamentoEnteOutput> {

	@Autowired
	private StatoAggiornamentoEnteRepository repository;

	@Override
    public void authorize ( GetOpzioniInput input, OperationDispatchingContext<GetOpzioniInput, GetOpzioniStatoAggiornamentoEnteOutput> context ) {
		SecurityUtils.assertValidPrincipal();
        SecurityUtils.assertScope ( Constants.SCOPES.CONFIGURATORE );
	}

	@Override
	@Transactional
    public GetOpzioniStatoAggiornamentoEnteOutput execute ( GetOpzioniInput input,
        OperationDispatchingContext<GetOpzioniInput, GetOpzioniStatoAggiornamentoEnteOutput> context ) {

		GetOpzioniStatoAggiornamentoEnteOutput output = GetOpzioniStatoAggiornamentoEnteOutput
				.ok(GetOpzioniStatoAggiornamentoEnteOutput.class);
		output.setOpzioni(new ArrayList<>());

		for (StatoAggiornamentoEnte entry : repository
				.findAll(new Sort("id"))) {
			output.getOpzioni()
					.add(new DecodificaOutputDto(entry.getId().longValue(), entry.getCodice(), entry.getDescrizione()));
		}
		return output;
	}

}
