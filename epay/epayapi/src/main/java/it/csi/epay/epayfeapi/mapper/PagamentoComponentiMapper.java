/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.mapper;

import it.csi.epay.epayfeapi.dto.PagamentoComponentiDTO;
import it.csi.epay.epayfeapi.entity.EpayTPagamentoComponenti;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;


@Mapper ( componentModel = "cdi", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
public interface PagamentoComponentiMapper {

	@Mapping ( target = "idComponente", source = "idComponente" )
	EpayTPagamentoComponenti toEntity ( PagamentoComponentiDTO pagamentoComponentiDTO );

	List<PagamentoComponentiDTO> toDtoList ( List<EpayTPagamentoComponenti> epayTPagamentoComponentiList );
}
