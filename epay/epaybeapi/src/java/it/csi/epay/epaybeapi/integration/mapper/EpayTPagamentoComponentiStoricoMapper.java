/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper;

import java.util.LinkedList;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import it.csi.epay.epaybeapi.integration.domain.EpayTPagamentoComponentiStorico;
import it.csi.epay.epaybeapi.integration.dto.EpayTPagamentoComponentiStoricoDTO;
import it.csi.epay.epaybeapi.integration.mapper.mapping.EpayTPagamentoComponentiStoricoMapping;

/**
 * MapStruct mapper for "EpayTPagamentoComponentiStorico" 
 *
 * @author EII
 *
 */
@Service
public class EpayTPagamentoComponentiStoricoMapper implements IMapper<EpayTPagamentoComponentiStorico, EpayTPagamentoComponentiStoricoDTO>  {

	private EpayTPagamentoComponentiStoricoMapping mapping = Mappers.getMapper ( EpayTPagamentoComponentiStoricoMapping.class );

	@Override
	public EpayTPagamentoComponentiStoricoDTO toDTO(EpayTPagamentoComponentiStorico record) {
		
		EpayTPagamentoComponentiStoricoDTO dto = mapping.toDTO( record );
		
		return dto;
	}
	
	@Override
    public List<EpayTPagamentoComponentiStoricoDTO> toDTO(Iterable<EpayTPagamentoComponentiStorico> recordList) {
    	List<EpayTPagamentoComponentiStoricoDTO> output = new LinkedList<>();
    		
    	if (recordList != null) {
    		for (EpayTPagamentoComponentiStorico record : recordList) {
    			output.add( this.toDTO( record ) );
    		}
    	}
    	
    	return output;
    }

}
