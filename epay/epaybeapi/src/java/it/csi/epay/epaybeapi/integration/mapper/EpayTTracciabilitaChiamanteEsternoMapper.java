/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper;

import java.util.LinkedList;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import it.csi.epay.epaybeapi.integration.domain.EpayTTracciabilitaChiamanteEsterno;
import it.csi.epay.epaybeapi.integration.dto.EpayTTracciabilitaChiamanteEsternoDTO;
import it.csi.epay.epaybeapi.integration.mapper.mapping.EpayTTracciabilitaChiamanteEsternoMapping;

/**
 * MapStruct mapper for "EpayTTracciabilitaChiamanteEsterno" 
 *
 * @author EII
 *
 */
@Service
public class EpayTTracciabilitaChiamanteEsternoMapper implements IMapper<EpayTTracciabilitaChiamanteEsterno, EpayTTracciabilitaChiamanteEsternoDTO>  {

	private EpayTTracciabilitaChiamanteEsternoMapping mapping = Mappers.getMapper ( EpayTTracciabilitaChiamanteEsternoMapping.class );

	@Override
	public EpayTTracciabilitaChiamanteEsternoDTO toDTO(EpayTTracciabilitaChiamanteEsterno record) {
		
		EpayTTracciabilitaChiamanteEsternoDTO dto = mapping.toDTO( record );
		
		return dto;
	}

	@Override
    public List<EpayTTracciabilitaChiamanteEsternoDTO> toDTO(Iterable<EpayTTracciabilitaChiamanteEsterno> recordList) {
    	List<EpayTTracciabilitaChiamanteEsternoDTO> output = new LinkedList<>();
    		
    	if (recordList != null) {
    		for (EpayTTracciabilitaChiamanteEsterno record : recordList) {
    			output.add( this.toDTO( record ) );
    		}
    	}
    	
    	return output;
    }

}
