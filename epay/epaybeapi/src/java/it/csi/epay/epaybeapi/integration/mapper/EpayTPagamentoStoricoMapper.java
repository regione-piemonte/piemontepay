/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper;

import java.util.LinkedList;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import it.csi.epay.epaybeapi.integration.domain.EpayTPagamentoStorico;
import it.csi.epay.epaybeapi.integration.dto.EpayTPagamentoStoricoDTO;
import it.csi.epay.epaybeapi.integration.mapper.mapping.EpayTPagamentoStoricoMapping;

/**
 * MapStruct mapper for "EpayTPagamentoStorico" 
 *
 * @author EII
 *
 */
@Service
public class EpayTPagamentoStoricoMapper implements IMapper<EpayTPagamentoStorico, EpayTPagamentoStoricoDTO>  {

	private EpayTPagamentoStoricoMapping mapping = Mappers.getMapper ( EpayTPagamentoStoricoMapping.class );

	@Override
	public EpayTPagamentoStoricoDTO toDTO(EpayTPagamentoStorico record) {
		
		EpayTPagamentoStoricoDTO dto = mapping.toDTO( record );
		
		return dto;
	}

	@Override
    public List<EpayTPagamentoStoricoDTO> toDTO(Iterable<EpayTPagamentoStorico> recordList) {
    	List<EpayTPagamentoStoricoDTO> output = new LinkedList<>();
    		
    	if (recordList != null) {
    		for (EpayTPagamentoStorico record : recordList) {
    			output.add( this.toDTO( record ) );
    		}
    	}
    	
    	return output;
    }

}
