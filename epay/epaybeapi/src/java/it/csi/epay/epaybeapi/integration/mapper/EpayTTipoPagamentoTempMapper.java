/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper;

import java.util.LinkedList;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import it.csi.epay.epaybeapi.integration.domain.EpayTTipoPagamentoTemp;
import it.csi.epay.epaybeapi.integration.dto.EpayTTipoPagamentoTempDTO;
import it.csi.epay.epaybeapi.integration.mapper.mapping.EpayTTipoPagamentoTempMapping;

/**
 * MapStruct mapper for "EpayTTipoPagamentoTemp" 
 *
 * @author EII
 *
 */
@Service
public class EpayTTipoPagamentoTempMapper implements IMapper<EpayTTipoPagamentoTemp, EpayTTipoPagamentoTempDTO>  {

	private EpayTTipoPagamentoTempMapping mapping = Mappers.getMapper ( EpayTTipoPagamentoTempMapping.class );

	@Override
	public EpayTTipoPagamentoTempDTO toDTO(EpayTTipoPagamentoTemp record) {
		
		EpayTTipoPagamentoTempDTO dto = mapping.toDTO( record );
		
		return dto;
	}

	@Override
    public List<EpayTTipoPagamentoTempDTO> toDTO(Iterable<EpayTTipoPagamentoTemp> recordList) {
    	List<EpayTTipoPagamentoTempDTO> output = new LinkedList<>();
    		
    	if (recordList != null) {
    		for (EpayTTipoPagamentoTemp record : recordList) {
    			output.add( this.toDTO( record ) );
    		}
    	}
    	
    	return output;
    }

}
