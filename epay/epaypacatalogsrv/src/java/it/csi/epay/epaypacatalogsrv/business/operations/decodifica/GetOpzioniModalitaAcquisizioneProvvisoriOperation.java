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
import it.csi.epay.epaypacatalogsrv.dto.decodifica.GetOpzioniModalitaAcquisizioneProvvisoriOutput;
import it.csi.epay.epaypacatalogsrv.model.ModalitaAcquisizioneProvvisori;
import it.csi.epay.epaypacatalogsrv.repository.ModalitaAcquisizioneProvvisoriRepository;
import it.csi.epay.epaypacatalogsrv.vo.Constants;

@Operation(consumes = GetOpzioniInput.class, produces = GetOpzioniModalitaAcquisizioneProvvisoriOutput.class)
@Component
public class GetOpzioniModalitaAcquisizioneProvvisoriOperation implements OperationHandler<GetOpzioniInput, GetOpzioniModalitaAcquisizioneProvvisoriOutput> {

	@Autowired
	private ModalitaAcquisizioneProvvisoriRepository repository;

	@Override
    public void authorize ( GetOpzioniInput input, OperationDispatchingContext<GetOpzioniInput, GetOpzioniModalitaAcquisizioneProvvisoriOutput> context ) {
		SecurityUtils.assertValidPrincipal();
        SecurityUtils.assertScope ( Constants.SCOPES.CONFIGURATORE );
	}

	@Override
	@Transactional
    public GetOpzioniModalitaAcquisizioneProvvisoriOutput execute ( GetOpzioniInput input,
        OperationDispatchingContext<GetOpzioniInput, GetOpzioniModalitaAcquisizioneProvvisoriOutput> context ) {

		GetOpzioniModalitaAcquisizioneProvvisoriOutput output = GetOpzioniModalitaAcquisizioneProvvisoriOutput
				.ok(GetOpzioniModalitaAcquisizioneProvvisoriOutput.class);
		output.setOpzioni(new ArrayList<>());

		for (ModalitaAcquisizioneProvvisori entry : repository
				.findAll(new Sort("id"))) {
			output.getOpzioni()
					.add(new DecodificaOutputDto(entry.getId().longValue(), entry.getCodice(), entry.getDescrizione()));
		}
		return output;
	}

}
