/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.operations.codiceversamento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.business.dispatcher.Operation;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationDispatchingContext;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationHandler;
import it.csi.epay.epaypacatalogsrv.business.util.SecurityUtils;
import it.csi.epay.epaypacatalogsrv.dto.codiceversamento.RicercaCodiceVersamentoRifContabileSecondarioInput;
import it.csi.epay.epaypacatalogsrv.dto.codiceversamento.RicercaCodiceVersamentoRifContabileSecondarioOutput;
import it.csi.epay.epaypacatalogsrv.dto.decodifica.DecodificaCodiciVersamentoOutputDto;
import it.csi.epay.epaypacatalogsrv.exception.BadRequestException;
import it.csi.epay.epaypacatalogsrv.model.CodiceVersamentoLight;
import it.csi.epay.epaypacatalogsrv.model.Ente;
import it.csi.epay.epaypacatalogsrv.repository.CodiceVersamentoLightRepository;
import it.csi.epay.epaypacatalogsrv.repository.EnteRepository;
import it.csi.epay.epaypacatalogsrv.vo.Constants;


@Operation ( consumes = RicercaCodiceVersamentoRifContabileSecondarioInput.class, produces = RicercaCodiceVersamentoRifContabileSecondarioOutput.class )
@Component
public class RicercaCodiceVersamentoRifContabileSecondarioOperation implements OperationHandler<RicercaCodiceVersamentoRifContabileSecondarioInput, RicercaCodiceVersamentoRifContabileSecondarioOutput> {

	@Autowired
	private CodiceVersamentoLightRepository codiceVersamentoRepository;

	@Autowired
	private EnteRepository enteRepository;
	
	

	@Override
	public void authorize ( RicercaCodiceVersamentoRifContabileSecondarioInput input,
		OperationDispatchingContext<RicercaCodiceVersamentoRifContabileSecondarioInput, RicercaCodiceVersamentoRifContabileSecondarioOutput> context ) {

		SecurityUtils.assertUseCase ( Constants.USE_CASES.RICERCA_CODICE_VERSAMENTO );
		SecurityUtils.assertScope ( Constants.SCOPES.CONFIGURATORE );
	}

	@Override
	public void validateInput ( RicercaCodiceVersamentoRifContabileSecondarioInput input,
		OperationDispatchingContext<RicercaCodiceVersamentoRifContabileSecondarioInput, RicercaCodiceVersamentoRifContabileSecondarioOutput> context ) {
		if ( null== input || null== input.getIdEnte() ) {
			throw new BadRequestException ();
		}
		
		Ente ente = enteRepository.findOneById(input.getIdEnte()) ;
		if (null== ente)
		{
			throw new BadRequestException ();
		}
	}

	@Override
	@Transactional
	public RicercaCodiceVersamentoRifContabileSecondarioOutput execute ( RicercaCodiceVersamentoRifContabileSecondarioInput input,
		OperationDispatchingContext<RicercaCodiceVersamentoRifContabileSecondarioInput, RicercaCodiceVersamentoRifContabileSecondarioOutput> context ) {

		// ricerca i codici versamento
		List<CodiceVersamentoLight> records = codiceVersamentoRepository.ricercaCovSecondariPerEnte(input.getIdEnte());

		ArrayList<DecodificaCodiciVersamentoOutputDto> risultati = new ArrayList<> ();

		for ( CodiceVersamentoLight record: records ) {
			risultati.add ( map ( record ) );
		}

		
		// sort output
//		risultati.sort ( new Comparator<RicercaCodiceVersamentoOutputDto> () {
//
//			@Override
//			public int compare ( RicercaCodiceVersamentoOutputDto o1, RicercaCodiceVersamentoOutputDto o2 ) {
//				return o1.getCodice ().compareTo ( o2.getCodice () );
//			}
//		} );


		// build output
		return RicercaCodiceVersamentoRifContabileSecondarioOutput.ok ( risultati );
	}

	

	private DecodificaCodiciVersamentoOutputDto map ( CodiceVersamentoLight input ) {
		DecodificaCodiciVersamentoOutputDto decodifica = new DecodificaCodiciVersamentoOutputDto();
        decodifica.setId( input.getId ().longValue ());
        decodifica.setCodice( input.getCodice());
        decodifica.setDescrizione( input.getDescrizione());
		

//		DecodificaCodiciVersamentoOutputDto output = dozerBeanMapper.map ( input, DecodificaCodiciVersamentoOutputDto.class );

		return decodifica;
	}
}
