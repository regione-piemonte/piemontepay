/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper;

import java.util.LinkedList;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import it.csi.epay.epaybeapi.integration.domain.EpayDAutorizzazioneChiamante;
import it.csi.epay.epaybeapi.integration.dto.EpayDAutorizzazioneChiamanteDTO;
import it.csi.epay.epaybeapi.integration.mapper.mapping.EpayDAutorizzazioneChiamanteMapping;

/**
 * MapStruct mapper for "EpayDAutorizzazioneChiamante" 
 *
 * @author EII
 *
 */
@Service
public class EpayDAutorizzazioneChiamanteMapper implements IMapper<EpayDAutorizzazioneChiamante, EpayDAutorizzazioneChiamanteDTO>  {

	private EpayDAutorizzazioneChiamanteMapping mapping = Mappers.getMapper ( EpayDAutorizzazioneChiamanteMapping.class );

	@Override
	public EpayDAutorizzazioneChiamanteDTO toDTO(EpayDAutorizzazioneChiamante record) {
		
		EpayDAutorizzazioneChiamanteDTO dto = mapping.toDTO( record );
		
		return dto;
	}

	@Override
    public List<EpayDAutorizzazioneChiamanteDTO> toDTO(Iterable<EpayDAutorizzazioneChiamante> recordList) {
    	List<EpayDAutorizzazioneChiamanteDTO> output = new LinkedList<>();
    		
    	if (recordList != null) {
    		for (EpayDAutorizzazioneChiamante record : recordList) {
    			output.add( this.toDTO( record ) );
    		}
    	}
    	
    	return output;
    }

}
