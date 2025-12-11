/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper;

import java.util.LinkedList;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import it.csi.epay.epayapi.integration.domain.EpayTPagamentoComponenti;
import it.csi.epay.epayapi.integration.dto.EpayTPagamentoComponentiDTO;
import it.csi.epay.epayapi.integration.mapper.mapping.EpayTPagamentoComponentiMapping;

/**
 * MapStruct mapper for "EpayTPagamentoComponenti" 
 *
 * @author fabio.fenoglio
 *
 */
@Service
public class EpayTPagamentoComponentiMapper implements IMapper<EpayTPagamentoComponenti, EpayTPagamentoComponentiDTO>  {

	private EpayTPagamentoComponentiMapping mapping = Mappers.getMapper ( EpayTPagamentoComponentiMapping.class );

	@Override
	public EpayTPagamentoComponentiDTO toDTO(EpayTPagamentoComponenti record) {
		
		EpayTPagamentoComponentiDTO dto = mapping.toDTO( record );
		
		return dto;
	}

	@Override
    public List<EpayTPagamentoComponentiDTO> toDTO(Iterable<EpayTPagamentoComponenti> recordList) {
    	List<EpayTPagamentoComponentiDTO> output = new LinkedList<>();
    		
    	if (recordList != null) {
    		for (EpayTPagamentoComponenti record : recordList) {
    			output.add( this.toDTO( record ) );
    		}
    	}
    	
    	return output;
    }

}
