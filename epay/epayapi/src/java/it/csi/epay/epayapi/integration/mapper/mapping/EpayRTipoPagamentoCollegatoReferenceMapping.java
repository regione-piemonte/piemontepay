/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epayapi.integration.domain.EpayRTipoPagamentoCollegato;
import it.csi.epay.epayapi.integration.dto.EpayRTipoPagamentoCollegatoReferenceDTO;

/**
 * MapStruct mapping specifications for "EpayRTipoPagamentoCollegato" 
 *
 * @author fabio.fenoglio
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {} )
public interface EpayRTipoPagamentoCollegatoReferenceMapping {

    @Mappings ( { 
		@Mapping ( source = "idTipoPagamentoPrincipale", target = "idTipoPagamentoPrincipale" ),
		@Mapping ( source = "idTipoPagamentoSecondario", target = "idTipoPagamentoSecondario" ),
    } )
    EpayRTipoPagamentoCollegatoReferenceDTO toDTO ( EpayRTipoPagamentoCollegato input );

}
