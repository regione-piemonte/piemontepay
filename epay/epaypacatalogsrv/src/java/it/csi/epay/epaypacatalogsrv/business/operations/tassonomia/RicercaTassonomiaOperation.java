/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.operations.tassonomia;

import java.util.LinkedList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.epay.epaypacatalogsrv.business.dispatcher.Operation;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationDispatchingContext;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationHandler;
import it.csi.epay.epaypacatalogsrv.business.util.SecurityUtils;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.RicercaTassonomiaInput;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.RicercaTassonomiaOutput;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.TassonomiaOutputDto;
import it.csi.epay.epaypacatalogsrv.model.Ente;
import it.csi.epay.epaypacatalogsrv.model.Tassonomia;
import it.csi.epay.epaypacatalogsrv.repository.EnteRepository;
import it.csi.epay.epaypacatalogsrv.repository.TassonomiaRepository;
import it.csi.epay.epaypacatalogsrv.vo.Constants;


@Operation ( consumes = RicercaTassonomiaInput.class, produces = RicercaTassonomiaOutput.class )
@Component
public class RicercaTassonomiaOperation implements OperationHandler<RicercaTassonomiaInput, RicercaTassonomiaOutput> {
	
	
  @Autowired
  private EnteRepository enteRepository;
  
  @Autowired
  private TassonomiaRepository repository;
  
@Autowired
private Mapper dozerBeanMapper;
	
  
	
  @Override
  public void authorize ( RicercaTassonomiaInput input,
      OperationDispatchingContext<RicercaTassonomiaInput, RicercaTassonomiaOutput> context ) {

      SecurityUtils.assertUseCase ( Constants.USE_CASES.RICERCA_RIFERIMENTO_CONTABILE );
      SecurityUtils.assertScope ( Constants.SCOPES.CONFIGURATORE );
  }
  
@Override
public void validateInput ( RicercaTassonomiaInput input,
    OperationDispatchingContext<RicercaTassonomiaInput, RicercaTassonomiaOutput> context ) {
    
}
	

	@Override
	public RicercaTassonomiaOutput execute(RicercaTassonomiaInput input,
			OperationDispatchingContext<RicercaTassonomiaInput, RicercaTassonomiaOutput> context) {
		
		Ente ente= enteRepository.findOneByCodiceFiscale(input.getCaller().getPrincipal().getCodiceEnte());
		List<Tassonomia> listEntity = repository.ricerca(input, ente.getCodiceTipoEnteCreditore());
		List <TassonomiaOutputDto> risultati = new LinkedList<>();
		
		for (Tassonomia entity: listEntity)
		{
			risultati.add( dozerBeanMapper.map ( entity, TassonomiaOutputDto.class ));
			
		}
		
		 return RicercaTassonomiaOutput.ok ( risultati );
		 
	}

}
