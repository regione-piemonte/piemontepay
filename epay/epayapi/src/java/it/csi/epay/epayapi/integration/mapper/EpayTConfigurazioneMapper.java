/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper;

import java.util.LinkedList;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import it.csi.epay.epayapi.integration.domain.EpayTConfigurazione;
import it.csi.epay.epayapi.integration.dto.EpayTConfigurazioneDTO;
import it.csi.epay.epayapi.integration.mapper.mapping.EpayTConfigurazioneMapping;

/**
 * MapStruct mapper for "EpayTConfigurazione" 
 *
 * @author fabio.fenoglio
 *
 */
@Service
public class EpayTConfigurazioneMapper implements IMapper<EpayTConfigurazione, EpayTConfigurazioneDTO>  {

	private EpayTConfigurazioneMapping mapping = Mappers.getMapper ( EpayTConfigurazioneMapping.class );

	@Override
	public EpayTConfigurazioneDTO toDTO(EpayTConfigurazione record) {
		
		EpayTConfigurazioneDTO dto = mapping.toDTO( record );
		
		return dto;
	}

	@Override
    public List<EpayTConfigurazioneDTO> toDTO(Iterable<EpayTConfigurazione> recordList) {
    	List<EpayTConfigurazioneDTO> output = new LinkedList<>();
    		
    	if (recordList != null) {
    		for (EpayTConfigurazione record : recordList) {
    			output.add( this.toDTO( record ) );
    		}
    	}
    	
    	return output;
    }

}
