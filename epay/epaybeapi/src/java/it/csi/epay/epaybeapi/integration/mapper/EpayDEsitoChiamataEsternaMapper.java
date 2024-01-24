/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper;

import java.util.LinkedList;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import it.csi.epay.epaybeapi.integration.domain.EpayDEsitoChiamataEsterna;
import it.csi.epay.epaybeapi.integration.dto.EpayDEsitoChiamataEsternaDTO;
import it.csi.epay.epaybeapi.integration.mapper.mapping.EpayDEsitoChiamataEsternaMapping;

/**
 * MapStruct mapper for "EpayDEsitoChiamataEsterna" 
 *
 * @author EII
 *
 */
@Service
public class EpayDEsitoChiamataEsternaMapper implements IMapper<EpayDEsitoChiamataEsterna, EpayDEsitoChiamataEsternaDTO>  {

	private EpayDEsitoChiamataEsternaMapping mapping = Mappers.getMapper ( EpayDEsitoChiamataEsternaMapping.class );

	@Override
	public EpayDEsitoChiamataEsternaDTO toDTO(EpayDEsitoChiamataEsterna record) {
		
		EpayDEsitoChiamataEsternaDTO dto = mapping.toDTO( record );
		
		return dto;
	}

	@Override
    public List<EpayDEsitoChiamataEsternaDTO> toDTO(Iterable<EpayDEsitoChiamataEsterna> recordList) {
    	List<EpayDEsitoChiamataEsternaDTO> output = new LinkedList<>();
    		
    	if (recordList != null) {
    		for (EpayDEsitoChiamataEsterna record : recordList) {
    			output.add( this.toDTO( record ) );
    		}
    	}
    	
    	return output;
    }

}
