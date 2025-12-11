/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper;

import java.util.LinkedList;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import it.csi.epay.epayapi.integration.domain.EpayTChiamataEsternaNonValida;
import it.csi.epay.epayapi.integration.dto.EpayTChiamataEsternaNonValidaDTO;
import it.csi.epay.epayapi.integration.mapper.mapping.EpayTChiamataEsternaNonValidaMapping;

/**
 * MapStruct mapper for "EpayTChiamataEsternaNonValida" 
 *
 * @author fabio.fenoglio
 *
 */
@Service
public class EpayTChiamataEsternaNonValidaMapper implements IMapper<EpayTChiamataEsternaNonValida, EpayTChiamataEsternaNonValidaDTO>  {

	private EpayTChiamataEsternaNonValidaMapping mapping = Mappers.getMapper ( EpayTChiamataEsternaNonValidaMapping.class );

	@Override
	public EpayTChiamataEsternaNonValidaDTO toDTO(EpayTChiamataEsternaNonValida record) {
		
		EpayTChiamataEsternaNonValidaDTO dto = mapping.toDTO( record );
		
		return dto;
	}
	
	@Override
    public List<EpayTChiamataEsternaNonValidaDTO> toDTO(Iterable<EpayTChiamataEsternaNonValida> recordList) {
    	List<EpayTChiamataEsternaNonValidaDTO> output = new LinkedList<>();
    		
    	if (recordList != null) {
    		for (EpayTChiamataEsternaNonValida record : recordList) {
    			output.add( this.toDTO( record ) );
    		}
    	}
    	
    	return output;
    }

}
