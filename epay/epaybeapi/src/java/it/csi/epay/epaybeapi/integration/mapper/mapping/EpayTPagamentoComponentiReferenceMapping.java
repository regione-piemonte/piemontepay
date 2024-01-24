/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epaybeapi.integration.domain.EpayTPagamentoComponenti;
import it.csi.epay.epaybeapi.integration.dto.EpayTPagamentoComponentiReferenceDTO;

/**
 * MapStruct mapping specifications for "EpayTPagamentoComponenti" 
 *
 * @author EII
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {} )
public interface EpayTPagamentoComponentiReferenceMapping {

    @Mappings ( { 
		@Mapping ( source = "idComponente", target = "idComponente" ),
		@Mapping ( source = "progressivo", target = "progressivo" ),
		@Mapping ( source = "importo", target = "importo" ),
		@Mapping ( source = "causale", target = "causale" ),
		@Mapping ( source = "datiSpecificiRiscossione", target = "datiSpecificiRiscossione" ),
		@Mapping ( source = "utenteUltimaModifica", target = "utenteUltimaModifica" ),
		@Mapping ( source = "annoAccertamento", target = "annoAccertamento" ),
		@Mapping ( source = "numeroAccertamento", target = "numeroAccertamento" ),
    } )
    EpayTPagamentoComponentiReferenceDTO toDTO ( EpayTPagamentoComponenti input );

}
