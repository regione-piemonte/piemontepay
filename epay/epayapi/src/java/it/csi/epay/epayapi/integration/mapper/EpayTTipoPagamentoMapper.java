/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper;

import java.util.LinkedList;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import it.csi.epay.epayapi.integration.domain.EpayTTipoPagamento;
import it.csi.epay.epayapi.integration.dto.EpayTTipoPagamentoDTO;
import it.csi.epay.epayapi.integration.mapper.mapping.EpayTTipoPagamentoMapping;

/**
 * MapStruct mapper for "EpayTTipoPagamento" 
 *
 * @author fabio.fenoglio
 *
 */
@Service
public class EpayTTipoPagamentoMapper implements IMapper<EpayTTipoPagamento, EpayTTipoPagamentoDTO>  {

	private EpayTTipoPagamentoMapping mapping = Mappers.getMapper ( EpayTTipoPagamentoMapping.class );

	@Override
	public EpayTTipoPagamentoDTO toDTO(EpayTTipoPagamento record) {
		
		EpayTTipoPagamentoDTO dto = mapping.toDTO( record );
		
		return dto;
	}
	
	@Override
    public List<EpayTTipoPagamentoDTO> toDTO(Iterable<EpayTTipoPagamento> recordList) {
    	List<EpayTTipoPagamentoDTO> output = new LinkedList<>();
    		
    	if (recordList != null) {
    		for (EpayTTipoPagamento record : recordList) {
    			output.add( this.toDTO( record ) );
    		}
    	}
    	
    	return output;
    }

}
