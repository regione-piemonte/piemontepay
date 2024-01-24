/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.operations.ente;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationDispatchingContext;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationHandler;
import it.csi.epay.epaypacatalogsrv.dto.decodifica.DecodificaOutputDto;
import it.csi.epay.epaypacatalogsrv.dto.ente.GetEntiConRiferimentoContabileSecondarioInput;
import it.csi.epay.epaypacatalogsrv.dto.ente.GetEntiConRiferimentoContabileSecondarioOutput;
import it.csi.epay.epaypacatalogsrv.exception.BadRequestException;
import it.csi.epay.epaypacatalogsrv.model.EnteLight;
import it.csi.epay.epaypacatalogsrv.repository.EnteLightRepository;

@Component
public class GetEntiConRiferimentoContabileSecondarioOperation implements 
OperationHandler<GetEntiConRiferimentoContabileSecondarioInput, GetEntiConRiferimentoContabileSecondarioOutput> {

    
	@Autowired
	private EnteLightRepository enteRepository;
	
	

	@Override
    public void authorize ( GetEntiConRiferimentoContabileSecondarioInput input, OperationDispatchingContext<GetEntiConRiferimentoContabileSecondarioInput, GetEntiConRiferimentoContabileSecondarioOutput> context ) {
//        SecurityUtils.assertUseCase ( Constants.USE_CASES.MODIFICA_CODICE_VERSAMENTO);
//        SecurityUtils.assertScope ( Constants.SCOPES.CONFIGURATORE );
	}
	

	

    @Override
    public void validateInput ( GetEntiConRiferimentoContabileSecondarioInput input,
        OperationDispatchingContext<GetEntiConRiferimentoContabileSecondarioInput, GetEntiConRiferimentoContabileSecondarioOutput> context ) {
        // TODO Auto-generated method stub
        if ( null== input || null== input.getCaller ()|| null== input.getCaller ().getPrincipal () 
                        || null== input.getCaller ().getPrincipal () .getCodiceEnte ()) {
            throw new BadRequestException ();
        }
        
        EnteLight ente = enteRepository.findOneByCodiceFiscale ( input.getCaller ().getPrincipal () .getCodiceEnte () ) ;
        if (null== ente)
        {
            throw new BadRequestException ();
        }
    }





    @Override
	@Transactional
    public GetEntiConRiferimentoContabileSecondarioOutput execute ( GetEntiConRiferimentoContabileSecondarioInput input,
    		OperationDispatchingContext<GetEntiConRiferimentoContabileSecondarioInput, GetEntiConRiferimentoContabileSecondarioOutput> context ) {

		
		GetEntiConRiferimentoContabileSecondarioOutput output = GetEntiConRiferimentoContabileSecondarioOutput
	            .ok ( GetEntiConRiferimentoContabileSecondarioOutput.class );
	        output.setOpzioni ( new ArrayList<> () );
		List<EnteLight> entiModel = enteRepository.ricercaEntiConRiferimentoContabileSecondario(input.getCaller ().getPrincipal ().getCodiceEnte ());
		if (entiModel!= null && entiModel.size()>0)
		{
			for (EnteLight ente: entiModel)
			{
			output.getOpzioni ()
	         .add ( new DecodificaOutputDto (
	        		 ente.getId ().longValue (), ente.getCodiceFiscale(), ente.getDenominazione() ) );
			}
		}
		return output;
		 
        
	}


}
