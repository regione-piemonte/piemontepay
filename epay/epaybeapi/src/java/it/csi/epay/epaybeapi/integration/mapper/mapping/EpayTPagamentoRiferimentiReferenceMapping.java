/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epaybeapi.integration.domain.EpayTPagamentoRiferimenti;
import it.csi.epay.epaybeapi.integration.dto.EpayTPagamentoRiferimentiReferenceDTO;

/**
 * MapStruct mapping specifications for "EpayTPagamentoRiferimenti" 
 *
 * @author EII
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {} )
public interface EpayTPagamentoRiferimentiReferenceMapping {

    @Mappings ( { 
		@Mapping ( source = "idRiferimento", target = "idRiferimento" ),
		@Mapping ( source = "nome", target = "nome" ),
		@Mapping ( source = "progressivo", target = "progressivo" ),
		@Mapping ( source = "valore", target = "valore" ),
		@Mapping ( source = "utenteUltimaModifica", target = "utenteUltimaModifica" ),
    } )
    EpayTPagamentoRiferimentiReferenceDTO toDTO ( EpayTPagamentoRiferimenti input );

}
