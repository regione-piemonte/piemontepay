/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epaybeapi.integration.domain.EpayDModalitaPagamento;
import it.csi.epay.epaybeapi.integration.dto.EpayDModalitaPagamentoDTO;

/**
 * MapStruct mapping specifications for "EpayDModalitaPagamento" 
 *
 * @author EII
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = { 
	EpayDModalitaPagamentoReferenceMapping.class,
    EpayTEsitiRicevutiReferenceMapping.class,
} )
public interface EpayDModalitaPagamentoMapping {

    @Mappings ( { 
    	/*
		@Mapping ( source = "idModalitaPagamento", target = "idModalitaPagamento" ),
		@Mapping ( source = "descrizione", target = "descrizione" ),
		@Mapping ( source = "listOfEpayTEsitiRicevuti", target = "listOfEpayTEsitiRicevuti" ),
    	*/
    } )
    EpayDModalitaPagamentoDTO toDTO ( EpayDModalitaPagamento input );

}
