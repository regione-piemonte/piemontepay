/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epayapi.integration.domain.EpayDStatoPagamento;
import it.csi.epay.epayapi.integration.dto.EpayDStatoPagamentoReferenceDTO;

/**
 * MapStruct mapping specifications for "EpayDStatoPagamento" 
 *
 * @author fabio.fenoglio
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {} )
public interface EpayDStatoPagamentoReferenceMapping {

    @Mappings ( { 
		@Mapping ( source = "idStato", target = "idStato" ),
		@Mapping ( source = "descStato", target = "descStato" ),
		@Mapping ( source = "pagabile", target = "pagabile" ),
		@Mapping ( source = "modificabile", target = "modificabile" ),
    } )
    EpayDStatoPagamentoReferenceDTO toDTO ( EpayDStatoPagamento input );

}
