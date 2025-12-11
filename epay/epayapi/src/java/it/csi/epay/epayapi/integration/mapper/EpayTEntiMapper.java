/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper;

import java.util.LinkedList;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import it.csi.epay.epayapi.integration.domain.EpayTEnti;
import it.csi.epay.epayapi.integration.dto.EpayTEntiDTO;
import it.csi.epay.epayapi.integration.mapper.mapping.EpayTEntiMapping;

/**
 * MapStruct mapper for "EpayTEnti" 
 *
 * @author fabio.fenoglio
 *
 */
@Service
public class EpayTEntiMapper implements IMapper<EpayTEnti, EpayTEntiDTO>  {

	private EpayTEntiMapping mapping = Mappers.getMapper ( EpayTEntiMapping.class );

	@Override
	public EpayTEntiDTO toDTO(EpayTEnti record) {
		
		EpayTEntiDTO dto = mapping.toDTO( record );
		
		return dto;
	}

	@Override
    public List<EpayTEntiDTO> toDTO(Iterable<EpayTEnti> recordList) {
    	List<EpayTEntiDTO> output = new LinkedList<>();
    		
    	if (recordList != null) {
    		for (EpayTEnti record : recordList) {
    			output.add( this.toDTO( record ) );
    		}
    	}
    	
    	return output;
    }

}
