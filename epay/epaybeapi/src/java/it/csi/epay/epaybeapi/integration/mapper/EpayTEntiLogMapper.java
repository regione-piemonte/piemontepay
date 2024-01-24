/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper;

import java.util.LinkedList;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import it.csi.epay.epaybeapi.integration.domain.EpayTEntiLog;
import it.csi.epay.epaybeapi.integration.dto.EpayTEntiLogDTO;
import it.csi.epay.epaybeapi.integration.mapper.mapping.EpayTEntiLogMapping;

/**
 * MapStruct mapper for "EpayTEntiLog" 
 *
 * @author EII
 *
 */
@Service
public class EpayTEntiLogMapper implements IMapper<EpayTEntiLog, EpayTEntiLogDTO>  {

	private EpayTEntiLogMapping mapping = Mappers.getMapper ( EpayTEntiLogMapping.class );

	@Override
	public EpayTEntiLogDTO toDTO(EpayTEntiLog record) {
		
		EpayTEntiLogDTO dto = mapping.toDTO( record );
		
		return dto;
	}

	@Override
    public List<EpayTEntiLogDTO> toDTO(Iterable<EpayTEntiLog> recordList) {
    	List<EpayTEntiLogDTO> output = new LinkedList<>();
    		
    	if (recordList != null) {
    		for (EpayTEntiLog record : recordList) {
    			output.add( this.toDTO( record ) );
    		}
    	}
    	
    	return output;
    }

}
