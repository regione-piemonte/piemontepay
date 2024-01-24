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
import it.csi.epay.epaypacatalogsrv.dto.decodifica.GetOpzioniStatoAggiornamentoOutput;
import it.csi.epay.epaypacatalogsrv.model.StatoAggiornamento;
import it.csi.epay.epaypacatalogsrv.repository.StatoAggiornamentoRepository;
import it.csi.epay.epaypacatalogsrv.vo.Constants;


@Operation ( consumes = GetOpzioniInput.class, produces = GetOpzioniStatoAggiornamentoOutput.class )
@Component
public class GetOpzioniStatoAggiornamentoOperation implements OperationHandler<GetOpzioniInput, GetOpzioniStatoAggiornamentoOutput> {

	@Autowired
    private StatoAggiornamentoRepository repository;

	@Override
    public void authorize ( GetOpzioniInput input, OperationDispatchingContext<GetOpzioniInput, GetOpzioniStatoAggiornamentoOutput> context ) {
		SecurityUtils.assertValidPrincipal();
        SecurityUtils.assertScope ( Constants.SCOPES.CONFIGURATORE );
	}

	@Override
	@Transactional
    public GetOpzioniStatoAggiornamentoOutput execute ( GetOpzioniInput input,
        OperationDispatchingContext<GetOpzioniInput, GetOpzioniStatoAggiornamentoOutput> context ) {

        GetOpzioniStatoAggiornamentoOutput output = GetOpzioniStatoAggiornamentoOutput
            .ok ( GetOpzioniStatoAggiornamentoOutput.class );
		output.setOpzioni(new ArrayList<>());

        for ( StatoAggiornamento entry: repository
				.findAll(new Sort("id"))) {
			output.getOpzioni()
					.add(new DecodificaOutputDto(entry.getId().longValue(), entry.getCodice(), entry.getDescrizione()));
		}
		return output;
	}

}
