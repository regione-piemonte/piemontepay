/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.mapper;

import it.csi.epay.epayfeapi.dto.AnagraficaDTO;
import it.csi.epay.epayfeapi.entity.EpayTAnagrafica;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper ( componentModel = "cdi", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
public interface AnagraficaMapper {

	@Mapping ( target = "idAnagrafica", source = "idAnagrafica" )
	AnagraficaDTO toDto ( EpayTAnagrafica epayTAnagrafica );

	@Mapping ( target = "idAnagrafica", source = "idAnagrafica" )
	EpayTAnagrafica toEntity ( AnagraficaDTO anagraficaDTO );
}
