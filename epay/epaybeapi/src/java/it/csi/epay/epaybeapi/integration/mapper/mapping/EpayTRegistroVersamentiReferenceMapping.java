/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epaybeapi.integration.domain.EpayTRegistroVersamenti;
import it.csi.epay.epaybeapi.integration.dto.EpayTRegistroVersamentiReferenceDTO;

/**
 * MapStruct mapping specifications for "EpayTRegistroVersamenti" 
 *
 * @author EII
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {} )
public interface EpayTRegistroVersamentiReferenceMapping {

    @Mappings ( { 
		@Mapping ( source = "dataOperazione", target = "dataOperazione" ),
		@Mapping ( source = "iuv", target = "iuv" ),
		@Mapping ( source = "idRegistro", target = "idRegistro" ),
		@Mapping ( source = "descEsitoPagamento", target = "descEsitoPagamento" ),
		@Mapping ( source = "origineInserimento", target = "origineInserimento" ),
    } )
    EpayTRegistroVersamentiReferenceDTO toDTO ( EpayTRegistroVersamenti input );

}
