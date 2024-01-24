/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper;

import java.util.LinkedList;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import it.csi.epay.epaybeapi.integration.domain.EpayTDatiAvvisoPagamento;
import it.csi.epay.epaybeapi.integration.dto.EpayTDatiAvvisoPagamentoDTO;
import it.csi.epay.epaybeapi.integration.mapper.mapping.EpayTDatiAvvisoPagamentoMapping;

/**
 * MapStruct mapper for "EpayTDatiAvvisoPagamento" 
 *
 * @author EII
 *
 */
@Service
public class EpayTDatiAvvisoPagamentoMapper implements IMapper<EpayTDatiAvvisoPagamento, EpayTDatiAvvisoPagamentoDTO>  {

	private EpayTDatiAvvisoPagamentoMapping mapping = Mappers.getMapper ( EpayTDatiAvvisoPagamentoMapping.class );

	@Override
	public EpayTDatiAvvisoPagamentoDTO toDTO(EpayTDatiAvvisoPagamento record) {
		
		EpayTDatiAvvisoPagamentoDTO dto = mapping.toDTO( record );
		
		return dto;
	}

	@Override
    public List<EpayTDatiAvvisoPagamentoDTO> toDTO(Iterable<EpayTDatiAvvisoPagamento> recordList) {
    	List<EpayTDatiAvvisoPagamentoDTO> output = new LinkedList<>();
    		
    	if (recordList != null) {
    		for (EpayTDatiAvvisoPagamento record : recordList) {
    			output.add( this.toDTO( record ) );
    		}
    	}
    	
    	return output;
    }

}
