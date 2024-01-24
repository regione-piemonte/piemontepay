/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper;

import java.util.LinkedList;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import it.csi.epay.epaybeapi.integration.domain.EpayTDatiSingoliVersamenti;
import it.csi.epay.epaybeapi.integration.dto.EpayTDatiSingoliVersamentiDTO;
import it.csi.epay.epaybeapi.integration.mapper.mapping.EpayTDatiSingoliVersamentiMapping;

/**
 * MapStruct mapper for "EpayTDatiSingoliVersamenti" 
 *
 * @author EII
 *
 */
@Service
public class EpayTDatiSingoliVersamentiMapper implements IMapper<EpayTDatiSingoliVersamenti, EpayTDatiSingoliVersamentiDTO>  {

	private EpayTDatiSingoliVersamentiMapping mapping = Mappers.getMapper ( EpayTDatiSingoliVersamentiMapping.class );

	@Override
	public EpayTDatiSingoliVersamentiDTO toDTO(EpayTDatiSingoliVersamenti record) {
		
		EpayTDatiSingoliVersamentiDTO dto = mapping.toDTO( record );
		
		return dto;
	}

	@Override
    public List<EpayTDatiSingoliVersamentiDTO> toDTO(Iterable<EpayTDatiSingoliVersamenti> recordList) {
    	List<EpayTDatiSingoliVersamentiDTO> output = new LinkedList<>();
    		
    	if (recordList != null) {
    		for (EpayTDatiSingoliVersamenti record : recordList) {
    			output.add( this.toDTO( record ) );
    		}
    	}
    	
    	return output;
    }

}
