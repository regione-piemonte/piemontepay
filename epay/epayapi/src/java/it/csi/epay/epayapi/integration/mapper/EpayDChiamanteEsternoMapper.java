/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper;

import java.util.LinkedList;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import it.csi.epay.epayapi.integration.domain.EpayDChiamanteEsterno;
import it.csi.epay.epayapi.integration.dto.EpayDChiamanteEsternoDTO;
import it.csi.epay.epayapi.integration.mapper.mapping.EpayDChiamanteEsternoMapping;

/**
 * MapStruct mapper for "EpayDChiamanteEsterno" 
 *
 * @author fabio.fenoglio
 *
 */
@Service
public class EpayDChiamanteEsternoMapper implements IMapper<EpayDChiamanteEsterno, EpayDChiamanteEsternoDTO>  {

	private EpayDChiamanteEsternoMapping mapping = Mappers.getMapper ( EpayDChiamanteEsternoMapping.class );

	@Override
	public EpayDChiamanteEsternoDTO toDTO(EpayDChiamanteEsterno record) {
		
		EpayDChiamanteEsternoDTO dto = mapping.toDTO( record );
		
		return dto;
	}

	@Override
    public List<EpayDChiamanteEsternoDTO> toDTO(Iterable<EpayDChiamanteEsterno> recordList) {
    	List<EpayDChiamanteEsternoDTO> output = new LinkedList<>();
    		
    	if (recordList != null) {
    		for (EpayDChiamanteEsterno record : recordList) {
    			output.add( this.toDTO( record ) );
    		}
    	}
    	
    	return output;
    }

}
