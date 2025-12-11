/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper;

import java.util.LinkedList;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import it.csi.epay.epayapi.integration.domain.EpayTEsitiRicevuti;
import it.csi.epay.epayapi.integration.dto.EpayTEsitiRicevutiDTO;
import it.csi.epay.epayapi.integration.mapper.mapping.EpayTEsitiRicevutiMapping;

/**
 * MapStruct mapper for "EpayTEsitiRicevuti" 
 *
 * @author fabio.fenoglio
 *
 */
@Service
public class EpayTEsitiRicevutiMapper implements IMapper<EpayTEsitiRicevuti, EpayTEsitiRicevutiDTO>  {

	private EpayTEsitiRicevutiMapping mapping = Mappers.getMapper ( EpayTEsitiRicevutiMapping.class );

	@Override
	public EpayTEsitiRicevutiDTO toDTO(EpayTEsitiRicevuti record) {
		
		EpayTEsitiRicevutiDTO dto = mapping.toDTO( record );
		
		return dto;
	}

	@Override
    public List<EpayTEsitiRicevutiDTO> toDTO(Iterable<EpayTEsitiRicevuti> recordList) {
    	List<EpayTEsitiRicevutiDTO> output = new LinkedList<>();
    		
    	if (recordList != null) {
    		for (EpayTEsitiRicevuti record : recordList) {
    			output.add( this.toDTO( record ) );
    		}
    	}
    	
    	return output;
    }

}
