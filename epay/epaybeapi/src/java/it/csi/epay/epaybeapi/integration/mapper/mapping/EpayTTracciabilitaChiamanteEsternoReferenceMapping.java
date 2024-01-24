/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epaybeapi.integration.domain.EpayTTracciabilitaChiamanteEsterno;
import it.csi.epay.epaybeapi.integration.dto.EpayTTracciabilitaChiamanteEsternoReferenceDTO;

/**
 * MapStruct mapping specifications for "EpayTTracciabilitaChiamanteEsterno" 
 *
 * @author EII
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {} )
public interface EpayTTracciabilitaChiamanteEsternoReferenceMapping {

    @Mappings ( { 
		@Mapping ( source = "idChiamata", target = "idChiamata" ),
		@Mapping ( source = "digest", target = "digest" ),
		@Mapping ( source = "iuv", target = "iuv" ),
		@Mapping ( source = "codiceFiscale", target = "codiceFiscale" ),
		@Mapping ( source = "idTransazione", target = "idTransazione" ),
		@Mapping ( source = "timestampChiamata", target = "timestampChiamata" ),
		@Mapping ( source = "remoteHost", target = "remoteHost" ),
		@Mapping ( source = "identificativoPagamento", target = "identificativoPagamento" ),
    } )
    EpayTTracciabilitaChiamanteEsternoReferenceDTO toDTO ( EpayTTracciabilitaChiamanteEsterno input );

}
