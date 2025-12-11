/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epayapi.integration.domain.EpayTTransazioneMdp;
import it.csi.epay.epayapi.integration.dto.EpayTTransazioneMdpDTO;

/**
 * MapStruct mapping specifications for "EpayTTransazioneMdp" 
 *
 * @author fabio.fenoglio
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = { 
	EpayTTransazioneMdpReferenceMapping.class,
    EpayTRegistroVersamentiReferenceMapping.class,
} )
public interface EpayTTransazioneMdpMapping {

    @Mappings ( { 
    	/*
		@Mapping ( source = "idTransazione", target = "idTransazione" ),
		@Mapping ( source = "iuv", target = "iuv" ),
		@Mapping ( source = "idGateway", target = "idGateway" ),
		@Mapping ( source = "idPaymentMode", target = "idPaymentMode" ),
		@Mapping ( source = "idInformativaPsp", target = "idInformativaPsp" ),
		@Mapping ( source = "identificativoPsp", target = "identificativoPsp" ),
		@Mapping ( source = "ragioneSocialePsp", target = "ragioneSocialePsp" ),
		@Mapping ( source = "identificativoCanalePsp", target = "identificativoCanalePsp" ),
		@Mapping ( source = "tipoVersamentoPsp", target = "tipoVersamentoPsp" ),
		@Mapping ( source = "modelloPagamentoPsp", target = "modelloPagamentoPsp" ),
		@Mapping ( source = "listOfEpayTRegistroVersamenti", target = "listOfEpayTRegistroVersamenti" ),
    	*/
    } )
    EpayTTransazioneMdpDTO toDTO ( EpayTTransazioneMdp input );

}
