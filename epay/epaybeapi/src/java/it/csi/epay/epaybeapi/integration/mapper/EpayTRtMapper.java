/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper;

import java.util.LinkedList;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import it.csi.epay.epaybeapi.integration.domain.EpayTRt;
import it.csi.epay.epaybeapi.integration.dto.EpayTRtDTO;
import it.csi.epay.epaybeapi.integration.mapper.mapping.EpayTRtMapping;

/**
 * MapStruct mapper for "EpayTRt" 
 *
 * @author EII
 *
 */
@Service
public class EpayTRtMapper implements IMapper<EpayTRt, EpayTRtDTO>  {

	private EpayTRtMapping mapping = Mappers.getMapper ( EpayTRtMapping.class );

	@Override
	public EpayTRtDTO toDTO(EpayTRt record) {
		
		EpayTRtDTO dto = mapping.toDTO( record );
		
		return dto;
	}

	@Override
    public List<EpayTRtDTO> toDTO(Iterable<EpayTRt> recordList) {
    	List<EpayTRtDTO> output = new LinkedList<>();
    		
    	if (recordList != null) {
    		for (EpayTRt record : recordList) {
    			output.add( this.toDTO( record ) );
    		}
    	}
    	
    	return output;
    }

}
