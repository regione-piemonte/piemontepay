/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper;

import java.util.LinkedList;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import it.csi.epay.epaybeapi.integration.domain.EpayTTipoPagamentoLog;
import it.csi.epay.epaybeapi.integration.dto.EpayTTipoPagamentoLogDTO;
import it.csi.epay.epaybeapi.integration.mapper.mapping.EpayTTipoPagamentoLogMapping;

/**
 * MapStruct mapper for "EpayTTipoPagamentoLog" 
 *
 * @author EII
 *
 */
@Service
public class EpayTTipoPagamentoLogMapper implements IMapper<EpayTTipoPagamentoLog, EpayTTipoPagamentoLogDTO>  {

	private EpayTTipoPagamentoLogMapping mapping = Mappers.getMapper ( EpayTTipoPagamentoLogMapping.class );

	@Override
	public EpayTTipoPagamentoLogDTO toDTO(EpayTTipoPagamentoLog record) {
		
		EpayTTipoPagamentoLogDTO dto = mapping.toDTO( record );
		
		return dto;
	}

	@Override
    public List<EpayTTipoPagamentoLogDTO> toDTO(Iterable<EpayTTipoPagamentoLog> recordList) {
    	List<EpayTTipoPagamentoLogDTO> output = new LinkedList<>();
    		
    	if (recordList != null) {
    		for (EpayTTipoPagamentoLog record : recordList) {
    			output.add( this.toDTO( record ) );
    		}
    	}
    	
    	return output;
    }

}
