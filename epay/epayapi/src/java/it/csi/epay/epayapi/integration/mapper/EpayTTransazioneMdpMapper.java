/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper;

import java.util.LinkedList;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import it.csi.epay.epayapi.integration.domain.EpayTTransazioneMdp;
import it.csi.epay.epayapi.integration.dto.EpayTTransazioneMdpDTO;
import it.csi.epay.epayapi.integration.mapper.mapping.EpayTTransazioneMdpMapping;

/**
 * MapStruct mapper for "EpayTTransazioneMdp" 
 *
 * @author fabio.fenoglio
 *
 */
@Service
public class EpayTTransazioneMdpMapper implements IMapper<EpayTTransazioneMdp, EpayTTransazioneMdpDTO>  {

	private EpayTTransazioneMdpMapping mapping = Mappers.getMapper ( EpayTTransazioneMdpMapping.class );

	@Override
	public EpayTTransazioneMdpDTO toDTO(EpayTTransazioneMdp record) {
		
		EpayTTransazioneMdpDTO dto = mapping.toDTO( record );
		
		return dto;
	}

	@Override
    public List<EpayTTransazioneMdpDTO> toDTO(Iterable<EpayTTransazioneMdp> recordList) {
    	List<EpayTTransazioneMdpDTO> output = new LinkedList<>();
    		
    	if (recordList != null) {
    		for (EpayTTransazioneMdp record : recordList) {
    			output.add( this.toDTO( record ) );
    		}
    	}
    	
    	return output;
    }

}
