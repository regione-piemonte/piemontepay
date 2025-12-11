/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper;

import java.util.LinkedList;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import it.csi.epay.epayapi.integration.domain.EpayDStatoErrore;
import it.csi.epay.epayapi.integration.dto.EpayDStatoErroreDTO;
import it.csi.epay.epayapi.integration.mapper.mapping.EpayDStatoErroreMapping;

/**
 * MapStruct mapper for "EpayDStatoErrore" 
 *
 * @author fabio.fenoglio
 *
 */
@Service
public class EpayDStatoErroreMapper implements IMapper<EpayDStatoErrore, EpayDStatoErroreDTO>  {

	private EpayDStatoErroreMapping mapping = Mappers.getMapper ( EpayDStatoErroreMapping.class );

	@Override
	public EpayDStatoErroreDTO toDTO(EpayDStatoErrore record) {
		
		EpayDStatoErroreDTO dto = mapping.toDTO( record );
		
		return dto;
	}
	
	@Override
    public List<EpayDStatoErroreDTO> toDTO(Iterable<EpayDStatoErrore> recordList) {
    	List<EpayDStatoErroreDTO> output = new LinkedList<>();
    		
    	if (recordList != null) {
    		for (EpayDStatoErrore record : recordList) {
    			output.add( this.toDTO( record ) );
    		}
    	}
    	
    	return output;
    }

}
