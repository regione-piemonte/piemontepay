/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper;

import java.util.LinkedList;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import it.csi.epay.epaybeapi.integration.domain.EpayTRegistroElaborazioniFault;
import it.csi.epay.epaybeapi.integration.dto.EpayTRegistroElaborazioniFaultDTO;
import it.csi.epay.epaybeapi.integration.mapper.mapping.EpayTRegistroElaborazioniFaultMapping;

/**
 * MapStruct mapper for "EpayTRegistroElaborazioniFault" 
 *
 * @author EII
 *
 */
@Service
public class EpayTRegistroElaborazioniFaultMapper implements IMapper<EpayTRegistroElaborazioniFault, EpayTRegistroElaborazioniFaultDTO>  {

	private EpayTRegistroElaborazioniFaultMapping mapping = Mappers.getMapper ( EpayTRegistroElaborazioniFaultMapping.class );

	@Override
	public EpayTRegistroElaborazioniFaultDTO toDTO(EpayTRegistroElaborazioniFault record) {
		
		EpayTRegistroElaborazioniFaultDTO dto = mapping.toDTO( record );
		
		return dto;
	}
	
	@Override
    public List<EpayTRegistroElaborazioniFaultDTO> toDTO(Iterable<EpayTRegistroElaborazioniFault> recordList) {
    	List<EpayTRegistroElaborazioniFaultDTO> output = new LinkedList<>();
    		
    	if (recordList != null) {
    		for (EpayTRegistroElaborazioniFault record : recordList) {
    			output.add( this.toDTO( record ) );
    		}
    	}
    	
    	return output;
    }

}
