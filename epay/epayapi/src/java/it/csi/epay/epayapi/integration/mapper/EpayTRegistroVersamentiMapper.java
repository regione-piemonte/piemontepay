/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper;

import java.util.LinkedList;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import it.csi.epay.epayapi.integration.domain.EpayTRegistroVersamenti;
import it.csi.epay.epayapi.integration.dto.EpayTRegistroVersamentiDTO;
import it.csi.epay.epayapi.integration.mapper.mapping.EpayTRegistroVersamentiMapping;

/**
 * MapStruct mapper for "EpayTRegistroVersamenti" 
 *
 * @author fabio.fenoglio
 *
 */
@Service
public class EpayTRegistroVersamentiMapper implements IMapper<EpayTRegistroVersamenti, EpayTRegistroVersamentiDTO>  {

	private EpayTRegistroVersamentiMapping mapping = Mappers.getMapper ( EpayTRegistroVersamentiMapping.class );

	@Override
	public EpayTRegistroVersamentiDTO toDTO(EpayTRegistroVersamenti record) {
		
		EpayTRegistroVersamentiDTO dto = mapping.toDTO( record );
		
		return dto;
	}

	@Override
    public List<EpayTRegistroVersamentiDTO> toDTO(Iterable<EpayTRegistroVersamenti> recordList) {
    	List<EpayTRegistroVersamentiDTO> output = new LinkedList<>();
    		
    	if (recordList != null) {
    		for (EpayTRegistroVersamenti record : recordList) {
    			output.add( this.toDTO( record ) );
    		}
    	}
    	
    	return output;
    }

}
