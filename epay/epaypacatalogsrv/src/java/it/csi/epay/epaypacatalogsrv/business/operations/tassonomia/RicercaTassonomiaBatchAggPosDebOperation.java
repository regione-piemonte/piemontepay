/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.operations.tassonomia;

import it.csi.epay.epaypacatalogsrv.business.dispatcher.Operation;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationDispatchingContext;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationHandler;
import it.csi.epay.epaypacatalogsrv.business.util.SecurityUtils;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.OutputRicercaTassonomieBatchAggPosDeb;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.RicercaTassonomiaBatchAggPosDebInput;
import it.csi.epay.epaypacatalogsrv.model.Tassonomia;
import it.csi.epay.epaypacatalogsrv.repository.TassonomiaRepository;
import it.csi.epay.epaypacatalogsrv.vo.Constants;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;


@Operation ( consumes = RicercaTassonomiaBatchAggPosDebInput.class, produces = OutputRicercaTassonomieBatchAggPosDeb.class )
@Component
public class RicercaTassonomiaBatchAggPosDebOperation implements OperationHandler<RicercaTassonomiaBatchAggPosDebInput, OutputRicercaTassonomieBatchAggPosDeb> {

	@Autowired
	private TassonomiaRepository tassonomiaRepository;

	@Autowired
	private Mapper dozerBeanMapper;

	@Override
	public void authorize ( RicercaTassonomiaBatchAggPosDebInput input,
					OperationDispatchingContext<RicercaTassonomiaBatchAggPosDebInput, OutputRicercaTassonomieBatchAggPosDeb> context ) {

		SecurityUtils.assertUseCase ( Constants.USE_CASES.RICERCA_RIFERIMENTO_CONTABILE );
		SecurityUtils.assertScope ( Constants.SCOPES.CONFIGURATORE );
	}

	@Override
	public void validateInput ( RicercaTassonomiaBatchAggPosDebInput input,
					OperationDispatchingContext<RicercaTassonomiaBatchAggPosDebInput, OutputRicercaTassonomieBatchAggPosDeb> context ) {

	}

	@Override
	public OutputRicercaTassonomieBatchAggPosDeb execute ( RicercaTassonomiaBatchAggPosDebInput input,
					OperationDispatchingContext<RicercaTassonomiaBatchAggPosDebInput, OutputRicercaTassonomieBatchAggPosDeb> context ) {
		List<Tassonomia> tassonomie = tassonomiaRepository.findByFlagAggiornato ( true );

		List<OutputRicercaTassonomieBatchAggPosDeb> risultati = new LinkedList<> ();

		for ( Tassonomia tassonomia : tassonomie ) {
			risultati.add ( dozerBeanMapper.map ( tassonomia, OutputRicercaTassonomieBatchAggPosDeb.class ) );
		}

		return OutputRicercaTassonomieBatchAggPosDeb.ok ( risultati );
	}
}
