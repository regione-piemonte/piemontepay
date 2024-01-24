/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper;

import java.util.LinkedList;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import it.csi.epay.epaybeapi.integration.domain.EpayTRegistroElaborazioni;
import it.csi.epay.epaybeapi.integration.dto.EpayTRegistroElaborazioniDTO;
import it.csi.epay.epaybeapi.integration.mapper.mapping.EpayTRegistroElaborazioniMapping;

/**
 * MapStruct mapper for "EpayTRegistroElaborazioni" 
 *
 * @author EII
 *
 */
@Service
public class EpayTRegistroElaborazioniMapper implements IMapper<EpayTRegistroElaborazioni, EpayTRegistroElaborazioniDTO>  {

	private EpayTRegistroElaborazioniMapping mapping = Mappers.getMapper ( EpayTRegistroElaborazioniMapping.class );

	@Override
	public EpayTRegistroElaborazioniDTO toDTO(EpayTRegistroElaborazioni record) {
		
		EpayTRegistroElaborazioniDTO dto = mapping.toDTO( record );
		
		return dto;
	}

	@Override
    public List<EpayTRegistroElaborazioniDTO> toDTO(Iterable<EpayTRegistroElaborazioni> recordList) {
    	List<EpayTRegistroElaborazioniDTO> output = new LinkedList<>();
    		
    	if (recordList != null) {
    		for (EpayTRegistroElaborazioni record : recordList) {
    			output.add( this.toDTO( record ) );
    		}
    	}
    	
    	return output;
    }

}
