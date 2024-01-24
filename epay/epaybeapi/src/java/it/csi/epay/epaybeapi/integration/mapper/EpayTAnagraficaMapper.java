/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper;

import java.util.LinkedList;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import it.csi.epay.epaybeapi.integration.domain.EpayTAnagrafica;
import it.csi.epay.epaybeapi.integration.dto.EpayTAnagraficaDTO;
import it.csi.epay.epaybeapi.integration.mapper.mapping.EpayTAnagraficaMapping;

/**
 * MapStruct mapper for "EpayTAnagrafica" 
 *
 * @author EII
 *
 */
@Service
public class EpayTAnagraficaMapper implements IMapper<EpayTAnagrafica, EpayTAnagraficaDTO>  {

	private EpayTAnagraficaMapping mapping = Mappers.getMapper ( EpayTAnagraficaMapping.class );

	@Override
	public EpayTAnagraficaDTO toDTO(EpayTAnagrafica record) {
		
		EpayTAnagraficaDTO dto = mapping.toDTO( record );
		
		return dto;
	}

	@Override
    public List<EpayTAnagraficaDTO> toDTO(Iterable<EpayTAnagrafica> recordList) {
    	List<EpayTAnagraficaDTO> output = new LinkedList<>();
    		
    	if (recordList != null) {
    		for (EpayTAnagrafica record : recordList) {
    			output.add( this.toDTO( record ) );
    		}
    	}
    	
    	return output;
    }

}
