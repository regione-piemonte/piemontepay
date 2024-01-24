/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper;

import java.util.LinkedList;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import it.csi.epay.epaybeapi.integration.domain.EpayDModalitaRicezioneEsito;
import it.csi.epay.epaybeapi.integration.dto.EpayDModalitaRicezioneEsitoDTO;
import it.csi.epay.epaybeapi.integration.mapper.mapping.EpayDModalitaRicezioneEsitoMapping;

/**
 * MapStruct mapper for "EpayDModalitaRicezioneEsito" 
 *
 * @author EII
 *
 */
@Service
public class EpayDModalitaRicezioneEsitoMapper implements IMapper<EpayDModalitaRicezioneEsito, EpayDModalitaRicezioneEsitoDTO>  {

	private EpayDModalitaRicezioneEsitoMapping mapping = Mappers.getMapper ( EpayDModalitaRicezioneEsitoMapping.class );

	@Override
	public EpayDModalitaRicezioneEsitoDTO toDTO(EpayDModalitaRicezioneEsito record) {
		
		EpayDModalitaRicezioneEsitoDTO dto = mapping.toDTO( record );
		
		return dto;
	}
	
	@Override
    public List<EpayDModalitaRicezioneEsitoDTO> toDTO(Iterable<EpayDModalitaRicezioneEsito> recordList) {
    	List<EpayDModalitaRicezioneEsitoDTO> output = new LinkedList<>();
    		
    	if (recordList != null) {
    		for (EpayDModalitaRicezioneEsito record : recordList) {
    			output.add( this.toDTO( record ) );
    		}
    	}
    	
    	return output;
    }

}
