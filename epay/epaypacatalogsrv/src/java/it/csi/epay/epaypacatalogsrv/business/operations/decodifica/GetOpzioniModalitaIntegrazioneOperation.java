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
import it.csi.epay.epaypacatalogsrv.dto.decodifica.GetOpzioniModalitaIntegrazioneOutput;
import it.csi.epay.epaypacatalogsrv.model.ModalitaIntegrazione;
import it.csi.epay.epaypacatalogsrv.repository.ModalitaIntegrazioneRepository;
import it.csi.epay.epaypacatalogsrv.vo.Constants;


@Operation ( consumes = GetOpzioniInput.class, produces = GetOpzioniModalitaIntegrazioneOutput.class )
@Component
public class GetOpzioniModalitaIntegrazioneOperation implements OperationHandler<GetOpzioniInput, GetOpzioniModalitaIntegrazioneOutput> {

	@Autowired
    private ModalitaIntegrazioneRepository repository;

	@Override
    public void authorize ( GetOpzioniInput input,
        OperationDispatchingContext<GetOpzioniInput, GetOpzioniModalitaIntegrazioneOutput> context ) {
		SecurityUtils.assertValidPrincipal();
        SecurityUtils.assertScope ( Constants.SCOPES.CONFIGURATORE );
	}

	@Override
	@Transactional
    public GetOpzioniModalitaIntegrazioneOutput execute ( GetOpzioniInput input,
        OperationDispatchingContext<GetOpzioniInput, GetOpzioniModalitaIntegrazioneOutput> context ) {

        GetOpzioniModalitaIntegrazioneOutput output = GetOpzioniModalitaIntegrazioneOutput
            .ok ( GetOpzioniModalitaIntegrazioneOutput.class );
		output.setOpzioni(new ArrayList<>());

        for ( ModalitaIntegrazione entry: repository
				.findAll(new Sort("id"))) {
			output.getOpzioni()
					.add(new DecodificaOutputDto(entry.getId().longValue(), entry.getCodice(), entry.getDescrizione()));
		}
		return output;
	}

}
