/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper;

import java.util.LinkedList;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import it.csi.epay.epayapi.integration.domain.EpayTPagamento;
import it.csi.epay.epayapi.integration.dto.EpayTPagamentoDTO;
import it.csi.epay.epayapi.integration.mapper.mapping.EpayTPagamentoMapping;

/**
 * MapStruct mapper for "EpayTPagamento" 
 *
 * @author fabio.fenoglio
 *
 */
@Service
public class EpayTPagamentoMapper implements IMapper<EpayTPagamento, EpayTPagamentoDTO>  {

	private EpayTPagamentoMapping mapping = Mappers.getMapper ( EpayTPagamentoMapping.class );

	@Override
	public EpayTPagamentoDTO toDTO(EpayTPagamento record) {
		
		EpayTPagamentoDTO dto = mapping.toDTO( record );
		
		return dto;
	}

	@Override
    public List<EpayTPagamentoDTO> toDTO(Iterable<EpayTPagamento> recordList) {
    	List<EpayTPagamentoDTO> output = new LinkedList<>();
    		
    	if (recordList != null) {
    		for (EpayTPagamento record : recordList) {
    			output.add( this.toDTO( record ) );
    		}
    	}
    	
    	return output;
    }

}
