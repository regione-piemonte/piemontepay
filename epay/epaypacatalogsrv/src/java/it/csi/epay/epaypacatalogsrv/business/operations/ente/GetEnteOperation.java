/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.operations.ente;

import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationDispatchingContext;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationHandler;
import it.csi.epay.epaypacatalogsrv.business.util.SecurityUtils;
import it.csi.epay.epaypacatalogsrv.dto.ente.GetEnteInput;
import it.csi.epay.epaypacatalogsrv.dto.ente.GetEnteOutput;
import it.csi.epay.epaypacatalogsrv.dto.ente.GetEnteOutputDto;
import it.csi.epay.epaypacatalogsrv.exception.BadRequestException;
import it.csi.epay.epaypacatalogsrv.exception.NotFoundException;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteEnte;
import it.csi.epay.epaypacatalogsrv.model.Ente;
import it.csi.epay.epaypacatalogsrv.repository.AssociazioneUtenteEnteRepository;
import it.csi.epay.epaypacatalogsrv.repository.EnteRepository;
import it.csi.epay.epaypacatalogsrv.vo.Constants;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component
public class GetEnteOperation implements OperationHandler<GetEnteInput, GetEnteOutput> {

	@Autowired
	private AssociazioneUtenteEnteRepository associazioneUtenteEnteRepository;

	@Autowired
	private EnteRepository enteRepository;

	@Autowired
	private Mapper dozerBeanMapper;

	@Override
	public void authorize ( GetEnteInput input, OperationDispatchingContext<GetEnteInput, GetEnteOutput> context ) {
		SecurityUtils.assertUseCase ( Constants.USE_CASES.MODIFICA_ENTE );
		SecurityUtils.assertScope ( Constants.SCOPES.CONFIGURATORE );
	}

	@Override
	public void validateInput ( GetEnteInput input, OperationDispatchingContext<GetEnteInput, GetEnteOutput> context ) {
		if ( input.getId () == null || input.getId () < 1L ) {
			throw new BadRequestException ( Constants.MESSAGES.INVALID_FIELD, "id" );
		}
	}

	@Override
	@Transactional
	public GetEnteOutput execute ( GetEnteInput input, OperationDispatchingContext<GetEnteInput, GetEnteOutput> context ) {

		Ente raw = enteRepository.findOne ( input.getId () );
		if ( raw == null ) {
			throw new NotFoundException ();
		}

		GetEnteOutputDto mapped = this.map ( raw );

		List<AssociazioneUtenteEnte> admins = associazioneUtenteEnteRepository.findByIdEnteAndFlagAdmin ( input.getId ().intValue (), true );

		for ( AssociazioneUtenteEnte admin : admins ) {
			mapped.getAdmins ().add ( admin.getId ().getIdUtente () );
		}

		return GetEnteOutput.ok ( mapped );
	}

	private GetEnteOutputDto map ( Ente input ) {
		return dozerBeanMapper.map ( input, GetEnteOutputDto.class );
	}

}
