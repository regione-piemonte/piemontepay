/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper;

import java.util.LinkedList;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import it.csi.epay.epayapi.integration.domain.EpayRChiamanteAutorizzazioneChiamante;
import it.csi.epay.epayapi.integration.dto.EpayRChiamanteAutorizzazioneChiamanteDTO;
import it.csi.epay.epayapi.integration.mapper.mapping.EpayRChiamanteAutorizzazioneChiamanteMapping;

/**
 * MapStruct mapper for "EpayRChiamanteAutorizzazioneChiamante" 
 *
 * @author fabio.fenoglio
 *
 */
@Service
public class EpayRChiamanteAutorizzazioneChiamanteMapper implements IMapper<EpayRChiamanteAutorizzazioneChiamante, EpayRChiamanteAutorizzazioneChiamanteDTO>  {

	private EpayRChiamanteAutorizzazioneChiamanteMapping mapping = Mappers.getMapper ( EpayRChiamanteAutorizzazioneChiamanteMapping.class );

	@Override
	public EpayRChiamanteAutorizzazioneChiamanteDTO toDTO(EpayRChiamanteAutorizzazioneChiamante record) {
		
		EpayRChiamanteAutorizzazioneChiamanteDTO dto = mapping.toDTO( record );
		
		return dto;
	}
	
	@Override
    public List<EpayRChiamanteAutorizzazioneChiamanteDTO> toDTO(Iterable<EpayRChiamanteAutorizzazioneChiamante> recordList) {
    	List<EpayRChiamanteAutorizzazioneChiamanteDTO> output = new LinkedList<>();
    		
    	if (recordList != null) {
    		for (EpayRChiamanteAutorizzazioneChiamante record : recordList) {
    			output.add( this.toDTO( record ) );
    		}
    	}
    	
    	return output;
    }

}
