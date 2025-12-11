/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper;

import java.util.LinkedList;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import it.csi.epay.epayapi.integration.domain.EpayDStatoPagamento;
import it.csi.epay.epayapi.integration.dto.EpayDStatoPagamentoDTO;
import it.csi.epay.epayapi.integration.mapper.mapping.EpayDStatoPagamentoMapping;

/**
 * MapStruct mapper for "EpayDStatoPagamento" 
 *
 * @author fabio.fenoglio
 *
 */
@Service
public class EpayDStatoPagamentoMapper implements IMapper<EpayDStatoPagamento, EpayDStatoPagamentoDTO>  {

	private EpayDStatoPagamentoMapping mapping = Mappers.getMapper ( EpayDStatoPagamentoMapping.class );

	@Override
	public EpayDStatoPagamentoDTO toDTO(EpayDStatoPagamento record) {
		
		EpayDStatoPagamentoDTO dto = mapping.toDTO( record );
		
		return dto;
	}

	@Override
    public List<EpayDStatoPagamentoDTO> toDTO(Iterable<EpayDStatoPagamento> recordList) {
    	List<EpayDStatoPagamentoDTO> output = new LinkedList<>();
    		
    	if (recordList != null) {
    		for (EpayDStatoPagamento record : recordList) {
    			output.add( this.toDTO( record ) );
    		}
    	}
    	
    	return output;
    }

}
