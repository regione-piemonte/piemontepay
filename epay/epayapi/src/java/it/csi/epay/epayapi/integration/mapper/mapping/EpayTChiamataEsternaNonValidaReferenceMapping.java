/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epayapi.integration.domain.EpayTChiamataEsternaNonValida;
import it.csi.epay.epayapi.integration.dto.EpayTChiamataEsternaNonValidaReferenceDTO;

/**
 * MapStruct mapping specifications for "EpayTChiamataEsternaNonValida" 
 *
 * @author fabio.fenoglio
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {} )
public interface EpayTChiamataEsternaNonValidaReferenceMapping {

    @Mappings ( { 
		@Mapping ( source = "idChiamata", target = "idChiamata" ),
		@Mapping ( source = "codiceChiamante", target = "codiceChiamante" ),
		@Mapping ( source = "digest", target = "digest" ),
		@Mapping ( source = "iuv", target = "iuv" ),
		@Mapping ( source = "codiceFiscale", target = "codiceFiscale" ),
		@Mapping ( source = "timestampChiamata", target = "timestampChiamata" ),
		@Mapping ( source = "remoteHost", target = "remoteHost" ),
		@Mapping ( source = "descrizioneErrore", target = "descrizioneErrore" ),
		@Mapping ( source = "identificativoPagamento", target = "identificativoPagamento" ),
    } )
    EpayTChiamataEsternaNonValidaReferenceDTO toDTO ( EpayTChiamataEsternaNonValida input );

}
