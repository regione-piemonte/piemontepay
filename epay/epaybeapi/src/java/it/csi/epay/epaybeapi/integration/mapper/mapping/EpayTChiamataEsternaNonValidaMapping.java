/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epaybeapi.integration.domain.EpayTChiamataEsternaNonValida;
import it.csi.epay.epaybeapi.integration.dto.EpayTChiamataEsternaNonValidaDTO;

/**
 * MapStruct mapping specifications for "EpayTChiamataEsternaNonValida" 
 *
 * @author EII
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = { 
	EpayTChiamataEsternaNonValidaReferenceMapping.class,
} )
public interface EpayTChiamataEsternaNonValidaMapping {

    @Mappings ( { 
    	/*
		@Mapping ( source = "idChiamata", target = "idChiamata" ),
		@Mapping ( source = "codiceChiamante", target = "codiceChiamante" ),
		@Mapping ( source = "digest", target = "digest" ),
		@Mapping ( source = "iuv", target = "iuv" ),
		@Mapping ( source = "codiceFiscale", target = "codiceFiscale" ),
		@Mapping ( source = "timestampChiamata", target = "timestampChiamata" ),
		@Mapping ( source = "remoteHost", target = "remoteHost" ),
		@Mapping ( source = "descrizioneErrore", target = "descrizioneErrore" ),
		@Mapping ( source = "identificativoPagamento", target = "identificativoPagamento" ),
    	*/
    } )
    EpayTChiamataEsternaNonValidaDTO toDTO ( EpayTChiamataEsternaNonValida input );

}
