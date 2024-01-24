/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epaybeapi.integration.domain.EpayTTransazioneMdp;
import it.csi.epay.epaybeapi.integration.dto.EpayTTransazioneMdpReferenceDTO;

/**
 * MapStruct mapping specifications for "EpayTTransazioneMdp" 
 *
 * @author EII
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {} )
public interface EpayTTransazioneMdpReferenceMapping {

    @Mappings ( { 
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
    } )
    EpayTTransazioneMdpReferenceDTO toDTO ( EpayTTransazioneMdp input );

}
