/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.operations.decodifica;

import java.util.ArrayList;
import java.util.List;

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
import it.csi.epay.epaypacatalogsrv.dto.decodifica.GetOpzioniTematicaCleanOutput;
import it.csi.epay.epaypacatalogsrv.model.TematicaPpay;
import it.csi.epay.epaypacatalogsrv.model.TematicheDaEscludere;
import it.csi.epay.epaypacatalogsrv.repository.TematicaPpayRepository;
import it.csi.epay.epaypacatalogsrv.repository.TematicheDaEscludereRepository;
import it.csi.epay.epaypacatalogsrv.vo.Constants;

@Operation(consumes = GetOpzioniInput.class, produces = GetOpzioniTematicaCleanOutput.class)
@Component
public class GetOpzioniTematicaCleanOperation implements OperationHandler<GetOpzioniInput, GetOpzioniTematicaCleanOutput> {

	@Autowired
	private TematicaPpayRepository repository;

    @Autowired
    private TematicheDaEscludereRepository repositoryEx;

	@Override
    public void authorize ( GetOpzioniInput input, OperationDispatchingContext<GetOpzioniInput, GetOpzioniTematicaCleanOutput> context ) {
		SecurityUtils.assertValidPrincipal();
        SecurityUtils.assertScope ( Constants.SCOPES.CONFIGURATORE );
	}

	@Override
	@Transactional
    public GetOpzioniTematicaCleanOutput execute ( GetOpzioniInput input, OperationDispatchingContext<GetOpzioniInput, GetOpzioniTematicaCleanOutput> context ) {

	    GetOpzioniTematicaCleanOutput output = GetOpzioniTematicaCleanOutput
				.ok(GetOpzioniTematicaCleanOutput.class);
		output.setOpzioni(new ArrayList<>());

		List<TematicheDaEscludere> exclude = repositoryEx.findAll ();
		
		for (TematicaPpay entry : repository.findAll(new Sort("id"))) {
	          boolean skip=false;
	            for(TematicheDaEscludere ex : repositoryEx.findAll ()) {
	                if(ex.getIdTematica().compareTo ( entry.getId () ) == 0) {
	                    skip=true;
	                    break;
	                }
	            }
	            if(skip) continue;

			output.getOpzioni().add(new DecodificaOutputDto(entry.getId().longValue(), entry.getCodice(), entry.getDescrizione()));
		}
		
		return output;
	}

}
