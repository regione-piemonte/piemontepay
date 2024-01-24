/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper;

import java.util.LinkedList;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import it.csi.epay.epaybeapi.integration.domain.EpayTParametri;
import it.csi.epay.epaybeapi.integration.dto.EpayTParametriDTO;
import it.csi.epay.epaybeapi.integration.mapper.mapping.EpayTParametriMapping;

/**
 * MapStruct mapper for "EpayTParametri" 
 *
 * @author EII
 *
 */
@Service
public class EpayTParametriMapper implements IMapper<EpayTParametri, EpayTParametriDTO>  {

	private EpayTParametriMapping mapping = Mappers.getMapper ( EpayTParametriMapping.class );

	@Override
	public EpayTParametriDTO toDTO(EpayTParametri record) {
		
		EpayTParametriDTO dto = mapping.toDTO( record );
		
		return dto;
	}

	@Override
    public List<EpayTParametriDTO> toDTO(Iterable<EpayTParametri> recordList) {
    	List<EpayTParametriDTO> output = new LinkedList<>();
    		
    	if (recordList != null) {
    		for (EpayTParametri record : recordList) {
    			output.add( this.toDTO( record ) );
    		}
    	}
    	
    	return output;
    }

}
