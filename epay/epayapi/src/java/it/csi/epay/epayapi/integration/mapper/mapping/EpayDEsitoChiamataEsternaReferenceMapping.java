/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epayapi.integration.domain.EpayDEsitoChiamataEsterna;
import it.csi.epay.epayapi.integration.dto.EpayDEsitoChiamataEsternaReferenceDTO;

/**
 * MapStruct mapping specifications for "EpayDEsitoChiamataEsterna" 
 *
 * @author fabio.fenoglio
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {} )
public interface EpayDEsitoChiamataEsternaReferenceMapping {

    @Mappings ( { 
		@Mapping ( source = "codice", target = "codice" ),
		@Mapping ( source = "descrizione", target = "descrizione" ),
    } )
    EpayDEsitoChiamataEsternaReferenceDTO toDTO ( EpayDEsitoChiamataEsterna input );

}
