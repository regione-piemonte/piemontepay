/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.mapper;

import it.csi.epay.epayfeapi.dto.RegistroVersamentiDTO;
import it.csi.epay.epayfeapi.entity.EpayTRegistroVersamenti;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper ( componentModel = "cdi", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
public interface RegistroVersamentiMapper {

	@Mapping ( target = "idRegistro", source = "idRegistro" )
	@Mapping ( target = "descEsitoPagamento", source = "risultato")
	@Mapping ( target = "epayDStatoPagamento.idStato", source = "idStato")
	EpayTRegistroVersamenti toEntity ( RegistroVersamentiDTO registroVersamentiDTO );
}
