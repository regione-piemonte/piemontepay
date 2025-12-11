/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper;

import java.util.LinkedList;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import it.csi.epay.epayapi.integration.domain.EpayDMessaggio;
import it.csi.epay.epayapi.integration.dto.EpayDMessaggioDTO;
import it.csi.epay.epayapi.integration.mapper.mapping.EpayDMessaggioMapping;

/**
 * MapStruct mapper for "EpayDMessaggio" 
 *
 * @author fabio.fenoglio
 *
 */
@Service
public class EpayDMessaggioMapper implements IMapper<EpayDMessaggio, EpayDMessaggioDTO>  {

	private EpayDMessaggioMapping mapping = Mappers.getMapper ( EpayDMessaggioMapping.class );

	@Override
	public EpayDMessaggioDTO toDTO(EpayDMessaggio record) {
		
		EpayDMessaggioDTO dto = mapping.toDTO( record );
		
		return dto;
	}

	@Override
    public List<EpayDMessaggioDTO> toDTO(Iterable<EpayDMessaggio> recordList) {
    	List<EpayDMessaggioDTO> output = new LinkedList<>();
    		
    	if (recordList != null) {
    		for (EpayDMessaggio record : recordList) {
    			output.add( this.toDTO( record ) );
    		}
    	}
    	
    	return output;
    }

}
