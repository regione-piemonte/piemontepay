/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epaybeapi.integration.domain.EpayTDatiSingoliVersamenti;
import it.csi.epay.epaybeapi.integration.dto.EpayTDatiSingoliVersamentiReferenceDTO;

/**
 * MapStruct mapping specifications for "EpayTDatiSingoliVersamenti" 
 *
 * @author EII
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {} )
public interface EpayTDatiSingoliVersamentiReferenceMapping {

    @Mappings ( { 
		@Mapping ( source = "id", target = "id" ),
		@Mapping ( source = "importo", target = "importo" ),
		@Mapping ( source = "descrizioneCausale", target = "descrizioneCausale" ),
		@Mapping ( source = "datiSpecificiRiscossione", target = "datiSpecificiRiscossione" ),
    } )
    EpayTDatiSingoliVersamentiReferenceDTO toDTO ( EpayTDatiSingoliVersamenti input );

}
