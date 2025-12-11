/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper;

import java.util.LinkedList;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import it.csi.epay.epayapi.integration.domain.EpayTPagamentoRiferimenti;
import it.csi.epay.epayapi.integration.dto.EpayTPagamentoRiferimentiDTO;
import it.csi.epay.epayapi.integration.mapper.mapping.EpayTPagamentoRiferimentiMapping;

/**
 * MapStruct mapper for "EpayTPagamentoRiferimenti" 
 *
 * @author fabio.fenoglio
 *
 */
@Service
public class EpayTPagamentoRiferimentiMapper implements IMapper<EpayTPagamentoRiferimenti, EpayTPagamentoRiferimentiDTO>  {

	private EpayTPagamentoRiferimentiMapping mapping = Mappers.getMapper ( EpayTPagamentoRiferimentiMapping.class );

	@Override
	public EpayTPagamentoRiferimentiDTO toDTO(EpayTPagamentoRiferimenti record) {
		
		EpayTPagamentoRiferimentiDTO dto = mapping.toDTO( record );
		
		return dto;
	}

	@Override
    public List<EpayTPagamentoRiferimentiDTO> toDTO(Iterable<EpayTPagamentoRiferimenti> recordList) {
    	List<EpayTPagamentoRiferimentiDTO> output = new LinkedList<>();
    		
    	if (recordList != null) {
    		for (EpayTPagamentoRiferimenti record : recordList) {
    			output.add( this.toDTO( record ) );
    		}
    	}
    	
    	return output;
    }

}
