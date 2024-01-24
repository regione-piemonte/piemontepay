/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.operations.voceentrata;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.business.dispatcher.Operation;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationDispatchingContext;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationHandler;
import it.csi.epay.epaypacatalogsrv.business.util.SecurityUtils;
import it.csi.epay.epaypacatalogsrv.dto.voceentrata.RicercaVoceEntrataInput;
import it.csi.epay.epaypacatalogsrv.dto.voceentrata.RicercaVoceEntrataOutput;
import it.csi.epay.epaypacatalogsrv.dto.voceentrata.RicercaVoceEntrataOutputDto;
import it.csi.epay.epaypacatalogsrv.model.VoceEntrata;
import it.csi.epay.epaypacatalogsrv.repository.VoceEntrataRepository;
import it.csi.epay.epaypacatalogsrv.vo.Constants;

@Operation(consumes = RicercaVoceEntrataInput.class, produces = RicercaVoceEntrataOutput.class)
@Component
public class RicercaVoceEntrataOperation implements OperationHandler<RicercaVoceEntrataInput, RicercaVoceEntrataOutput> {

	@Autowired
	private VoceEntrataRepository voceEntrataRepository;

    @Autowired
    private Mapper dozerBeanMapper;

	@Override
    public void authorize ( RicercaVoceEntrataInput input,
        OperationDispatchingContext<RicercaVoceEntrataInput, RicercaVoceEntrataOutput> context ) {
        SecurityUtils.assertAnyUseCase (
            Constants.USE_CASES.RICERCA_VOCE_ENTRATA,
            Constants.USE_CASES.RICERCA_CODICE_VERSAMENTO,
            Constants.USE_CASES.RICERCA_RIFERIMENTO_CONTABILE );
        SecurityUtils.assertScope ( Constants.SCOPES.CONFIGURATORE );
	}

	@Override
	@Transactional
    public RicercaVoceEntrataOutput execute ( RicercaVoceEntrataInput input,
        OperationDispatchingContext<RicercaVoceEntrataInput, RicercaVoceEntrataOutput> context ) {
		List<VoceEntrata> records = voceEntrataRepository.ricerca(input);

		ArrayList<RicercaVoceEntrataOutputDto> risultati = new ArrayList<>();

		for (VoceEntrata record : records) {
			risultati.add(map(record));
		}

		return RicercaVoceEntrataOutput.ok(risultati);
	}

	private RicercaVoceEntrataOutputDto map(VoceEntrata input) {
        RicercaVoceEntrataOutputDto output = dozerBeanMapper.map ( input, RicercaVoceEntrataOutputDto.class );
		return output;
	}

}
