/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epayapi.integration.domain.EpayTPagamentoRiferimenti;
import it.csi.epay.epayapi.integration.dto.EpayTPagamentoRiferimentiDTO;

/**
 * MapStruct mapping specifications for "EpayTPagamentoRiferimenti" 
 *
 * @author fabio.fenoglio
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = { 
	EpayTPagamentoRiferimentiReferenceMapping.class,
	EpayTPagamentoReferenceMapping.class,
} )
public interface EpayTPagamentoRiferimentiMapping {

    @Mappings ( { 
    	/*
		@Mapping ( source = "idRiferimento", target = "idRiferimento" ),
		@Mapping ( source = "nome", target = "nome" ),
		@Mapping ( source = "progressivo", target = "progressivo" ),
		@Mapping ( source = "valore", target = "valore" ),
		@Mapping ( source = "utenteUltimaModifica", target = "utenteUltimaModifica" ),
		@Mapping ( source = "epayTPagamento", target = "epayTPagamento" ),
    	*/
    } )
    EpayTPagamentoRiferimentiDTO toDTO ( EpayTPagamentoRiferimenti input );

}
