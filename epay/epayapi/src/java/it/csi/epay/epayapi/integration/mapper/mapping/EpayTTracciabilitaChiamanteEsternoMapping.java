/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epayapi.integration.domain.EpayTTracciabilitaChiamanteEsterno;
import it.csi.epay.epayapi.integration.dto.EpayTTracciabilitaChiamanteEsternoDTO;

/**
 * MapStruct mapping specifications for "EpayTTracciabilitaChiamanteEsterno" 
 *
 * @author fabio.fenoglio
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = { 
	EpayTTracciabilitaChiamanteEsternoReferenceMapping.class,
	EpayDChiamanteEsternoReferenceMapping.class,
} )
public interface EpayTTracciabilitaChiamanteEsternoMapping {

    @Mappings ( { 
    	/*
		@Mapping ( source = "idChiamata", target = "idChiamata" ),
		@Mapping ( source = "digest", target = "digest" ),
		@Mapping ( source = "iuv", target = "iuv" ),
		@Mapping ( source = "codiceFiscale", target = "codiceFiscale" ),
		@Mapping ( source = "idTransazione", target = "idTransazione" ),
		@Mapping ( source = "timestampChiamata", target = "timestampChiamata" ),
		@Mapping ( source = "remoteHost", target = "remoteHost" ),
		@Mapping ( source = "identificativoPagamento", target = "identificativoPagamento" ),
		@Mapping ( source = "epayDChiamanteEsterno", target = "epayDChiamanteEsterno" ),
    	*/
    } )
    EpayTTracciabilitaChiamanteEsternoDTO toDTO ( EpayTTracciabilitaChiamanteEsterno input );

}
