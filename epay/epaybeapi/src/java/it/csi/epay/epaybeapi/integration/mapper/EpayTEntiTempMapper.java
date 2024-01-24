/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper;

import java.util.LinkedList;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import it.csi.epay.epaybeapi.integration.domain.EpayTEntiTemp;
import it.csi.epay.epaybeapi.integration.dto.EpayTEntiTempDTO;
import it.csi.epay.epaybeapi.integration.mapper.mapping.EpayTEntiTempMapping;

/**
 * MapStruct mapper for "EpayTEntiTemp" 
 *
 * @author EII
 *
 */
@Service
public class EpayTEntiTempMapper implements IMapper<EpayTEntiTemp, EpayTEntiTempDTO>  {

	private EpayTEntiTempMapping mapping = Mappers.getMapper ( EpayTEntiTempMapping.class );

	@Override
	public EpayTEntiTempDTO toDTO(EpayTEntiTemp record) {
		
		EpayTEntiTempDTO dto = mapping.toDTO( record );
		
		return dto;
	}
	
	@Override
    public List<EpayTEntiTempDTO> toDTO(Iterable<EpayTEntiTemp> recordList) {
    	List<EpayTEntiTempDTO> output = new LinkedList<>();
    		
    	if (recordList != null) {
    		for (EpayTEntiTemp record : recordList) {
    			output.add( this.toDTO( record ) );
    		}
    	}
    	
    	return output;
    }

}
