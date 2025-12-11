/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper;

import java.util.LinkedList;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import it.csi.epay.epayapi.integration.domain.EpayTErrori;
import it.csi.epay.epayapi.integration.dto.EpayTErroriDTO;
import it.csi.epay.epayapi.integration.mapper.mapping.EpayTErroriMapping;

/**
 * MapStruct mapper for "EpayTErrori"
 *
 * @author fabio.fenoglio
 *
 */
@Service
public class EpayTErroriMapper implements IMapper<EpayTErrori, EpayTErroriDTO>  {

	private EpayTErroriMapping mapping = Mappers.getMapper ( EpayTErroriMapping.class );

	@Override
	public EpayTErroriDTO toDTO(EpayTErrori record) {

		EpayTErroriDTO dto = mapping.toDTO( record );

		return dto;
	}

	@Override
    public List<EpayTErroriDTO> toDTO(Iterable<EpayTErrori> recordList) {
    	List<EpayTErroriDTO> output = new LinkedList<>();

    	if (recordList != null) {
    		for (EpayTErrori record : recordList) {
    			output.add( this.toDTO( record ) );
    		}
    	}

    	return output;
    }

}
