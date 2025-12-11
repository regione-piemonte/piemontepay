/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper;

import java.util.LinkedList;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import it.csi.epay.epayapi.integration.domain.CsiLogAudit;
import it.csi.epay.epayapi.integration.dto.CsiLogAuditDTO;
import it.csi.epay.epayapi.integration.mapper.mapping.CsiLogAuditMapping;

/**
 * MapStruct mapper for "CsiLogAudit" 
 *
 * @author fabio.fenoglio
 *
 */
@Service
public class CsiLogAuditMapper implements IMapper<CsiLogAudit, CsiLogAuditDTO>  {

	private CsiLogAuditMapping mapping = Mappers.getMapper ( CsiLogAuditMapping.class );

	@Override
	public CsiLogAuditDTO toDTO(CsiLogAudit record) {
		
		CsiLogAuditDTO dto = mapping.toDTO( record );
		
		return dto;
	}

	@Override
    public List<CsiLogAuditDTO> toDTO(Iterable<CsiLogAudit> recordList) {
    	List<CsiLogAuditDTO> output = new LinkedList<>();
    		
    	if (recordList != null) {
    		for (CsiLogAudit record : recordList) {
    			output.add( this.toDTO( record ) );
    		}
    	}
    	
    	return output;
    }

}
