/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper;

import java.util.LinkedList;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import it.csi.epay.epayapi.integration.domain.EpayRPagamentoRegistroElaborazioni;
import it.csi.epay.epayapi.integration.dto.EpayRPagamentoRegistroElaborazioniDTO;
import it.csi.epay.epayapi.integration.mapper.mapping.EpayRPagamentoRegistroElaborazioniMapping;

/**
 * MapStruct mapper for "EpayRPagamentoRegistroElaborazioni" 
 *
 * @author fabio.fenoglio
 *
 */
@Service
public class EpayRPagamentoRegistroElaborazioniMapper implements IMapper<EpayRPagamentoRegistroElaborazioni, EpayRPagamentoRegistroElaborazioniDTO>  {

	private EpayRPagamentoRegistroElaborazioniMapping mapping = Mappers.getMapper ( EpayRPagamentoRegistroElaborazioniMapping.class );

	@Override
	public EpayRPagamentoRegistroElaborazioniDTO toDTO(EpayRPagamentoRegistroElaborazioni record) {
		
		EpayRPagamentoRegistroElaborazioniDTO dto = mapping.toDTO( record );
		
		return dto;
	}

	@Override
    public List<EpayRPagamentoRegistroElaborazioniDTO> toDTO(Iterable<EpayRPagamentoRegistroElaborazioni> recordList) {
    	List<EpayRPagamentoRegistroElaborazioniDTO> output = new LinkedList<>();
    		
    	if (recordList != null) {
    		for (EpayRPagamentoRegistroElaborazioni record : recordList) {
    			output.add( this.toDTO( record ) );
    		}
    	}
    	
    	return output;
    }

}
