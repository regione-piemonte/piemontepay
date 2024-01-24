/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper;

import java.util.LinkedList;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import it.csi.epay.epaybeapi.integration.domain.EpayTPdfReport;
import it.csi.epay.epaybeapi.integration.dto.EpayTPdfReportDTO;
import it.csi.epay.epaybeapi.integration.mapper.mapping.EpayTPdfReportMapping;

/**
 * MapStruct mapper for "EpayTPdfReport" 
 *
 * @author EII
 *
 */
@Service
public class EpayTPdfReportMapper implements IMapper<EpayTPdfReport, EpayTPdfReportDTO>  {

	private EpayTPdfReportMapping mapping = Mappers.getMapper ( EpayTPdfReportMapping.class );

	@Override
	public EpayTPdfReportDTO toDTO(EpayTPdfReport record) {
		
		EpayTPdfReportDTO dto = mapping.toDTO( record );
		
		return dto;
	}

	@Override
    public List<EpayTPdfReportDTO> toDTO(Iterable<EpayTPdfReport> recordList) {
    	List<EpayTPdfReportDTO> output = new LinkedList<>();
    		
    	if (recordList != null) {
    		for (EpayTPdfReport record : recordList) {
    			output.add( this.toDTO( record ) );
    		}
    	}
    	
    	return output;
    }

}
