/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.mapper;

import it.csi.epay.epayfeapi.dto.PagamentoRiferimentiDTO;
import it.csi.epay.epayfeapi.entity.EpayTPagamentoRiferimenti;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;


@Mapper ( componentModel = "cdi", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
public interface PagamentoRiferimentiMapper {

	@Mapping ( target = "idRiferimento", source = "idRiferimento" )
	EpayTPagamentoRiferimenti toEntity ( PagamentoRiferimentiDTO pagamentoRiferimentiDTO );

	List<PagamentoRiferimentiDTO> toDtoList ( List<EpayTPagamentoRiferimenti> epayTPagamentoRiferimentis );
}
