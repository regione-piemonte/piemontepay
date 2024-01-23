/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.mapper;

import it.csi.epay.epayfeapi.dto.PagamentoSecondarioDTO;
import it.csi.epay.epayfeapi.entity.EpayTPagamentoSecondario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper ( componentModel = "cdi", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
public interface PagamentoSecondarioMapper {

	@Mapping ( target = "idPagamento", source = "idPagamentoSecondario" )
	PagamentoSecondarioDTO toDto ( EpayTPagamentoSecondario epayTPagamentoSecondario );
}
