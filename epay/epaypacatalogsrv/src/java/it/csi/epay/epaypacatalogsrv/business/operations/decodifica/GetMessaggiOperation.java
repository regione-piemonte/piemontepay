/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.operations.decodifica;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.business.dispatcher.Operation;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationDispatchingContext;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationHandler;
import it.csi.epay.epaypacatalogsrv.business.util.SecurityUtils;
import it.csi.epay.epaypacatalogsrv.dto.decodifica.GetMessaggiInput;
import it.csi.epay.epaypacatalogsrv.dto.decodifica.GetMessaggiOutput;
import it.csi.epay.epaypacatalogsrv.dto.decodifica.GetMessaggiOutputDto;
import it.csi.epay.epaypacatalogsrv.model.Errore;
import it.csi.epay.epaypacatalogsrv.repository.ErroreRepository;
import it.csi.epay.epaypacatalogsrv.vo.Constants;

@Operation(consumes = GetMessaggiInput.class, produces = GetMessaggiOutput.class)
@Component
public class GetMessaggiOperation implements OperationHandler<GetMessaggiInput, GetMessaggiOutput> {

	@Autowired
	private ErroreRepository repository;

	@Override
    public void authorize ( GetMessaggiInput input, OperationDispatchingContext<GetMessaggiInput, GetMessaggiOutput> context ) {
		SecurityUtils.assertValidPrincipal();
        SecurityUtils.assertScope ( Constants.SCOPES.CONFIGURATORE );
	}

	@Override
	@Transactional
    public GetMessaggiOutput execute ( GetMessaggiInput input, OperationDispatchingContext<GetMessaggiInput, GetMessaggiOutput> context ) {

		GetMessaggiOutput output = GetMessaggiOutput.ok(GetMessaggiOutput.class);
		output.setMessaggi(new ArrayList<>());

		String codiceApplicativo = input.getCaller().getCodiceApplicativo();

		for (Errore entry : repository.findByCodiceApplicativo(codiceApplicativo)) {
			output.getMessaggi()
					.add(new GetMessaggiOutputDto(
							entry.getId().longValue(),
							entry.getCodiceLingua() + "." + entry.getCodiceApplicativo() + "." + entry.getCodice(),
							entry.getCodiceLingua(),
							entry.getCodiceApplicativo(),
							entry.getCodice(),
							entry.getMessaggio()));
		}
		return output;
	}

}
