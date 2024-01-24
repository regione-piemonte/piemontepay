/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.mapper;

import it.csi.epay.epayfeapi.dto.TransazioneMdpDTO;
import it.csi.epay.epayfeapi.entity.EpayTTransazioneMdp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper ( componentModel = "cdi", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
public interface TransazioneMdpMapper {

	@Mapping ( target = "idTransazione", source = "idTransazione" )
	EpayTTransazioneMdp toEntity ( TransazioneMdpDTO transazioneMdpDTO );
}
