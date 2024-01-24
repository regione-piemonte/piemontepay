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
import it.csi.epay.epaypacatalogsrv.dto.decodifica.GetOpzioniPeriodicitaSchedulazioneRiconciliazioneOutput;
import it.csi.epay.epaypacatalogsrv.model.PeriodicitaSchedulazioneRiconciliazione;
import it.csi.epay.epaypacatalogsrv.repository.PeriodicitaSchedulazioneRiconciliazioneRepository;
import it.csi.epay.epaypacatalogsrv.vo.Constants;

@Operation(consumes = GetOpzioniInput.class, produces = GetOpzioniPeriodicitaSchedulazioneRiconciliazioneOutput.class)
@Component
public class GetOpzioniPeriodicitaSchedulazioneRiconciliazioneOperation implements OperationHandler<GetOpzioniInput, GetOpzioniPeriodicitaSchedulazioneRiconciliazioneOutput> {

	@Autowired
	private PeriodicitaSchedulazioneRiconciliazioneRepository repository;

	@Override
    public void authorize ( GetOpzioniInput input,
        OperationDispatchingContext<GetOpzioniInput, GetOpzioniPeriodicitaSchedulazioneRiconciliazioneOutput> context ) {
		SecurityUtils.assertValidPrincipal();
        SecurityUtils.assertScope ( Constants.SCOPES.CONFIGURATORE );
	}

	@Override
	@Transactional
    public GetOpzioniPeriodicitaSchedulazioneRiconciliazioneOutput execute ( GetOpzioniInput input,
        OperationDispatchingContext<GetOpzioniInput, GetOpzioniPeriodicitaSchedulazioneRiconciliazioneOutput> context ) {

		GetOpzioniPeriodicitaSchedulazioneRiconciliazioneOutput output = GetOpzioniPeriodicitaSchedulazioneRiconciliazioneOutput
				.ok(GetOpzioniPeriodicitaSchedulazioneRiconciliazioneOutput.class);
		output.setOpzioni(new ArrayList<>());

		for (PeriodicitaSchedulazioneRiconciliazione entry : repository
				.findAll(new Sort("id"))) {
			output.getOpzioni()
					.add(new DecodificaOutputDto(entry.getId().longValue(), entry.getCodice(), entry.getDescrizione()));
		}
		return output;
	}

}
