/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.mapper;

import it.csi.epay.epayfeapi.dto.PagamentoDTO;
import it.csi.epay.epayfeapi.entity.EpayTPagamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper ( componentModel = "cdi", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
public interface PagamentoMapper {

	@Mapping ( target = "idPagamento", source = "idPagamento" )
	@Mapping ( target = "epayTTipoPagamento", source = "tipoPagamento" )
	@Mapping ( target = "iuvNumeroAvviso", source = "iuv" )
	@Mapping ( target = "epayTAnagrafica", source = "pagatore" )
	EpayTPagamento toEntity ( PagamentoDTO pagamentoDTO );

	@Mapping ( target = "idPagamento", source = "idPagamento" )
	@Mapping ( target = "tipoPagamento", source = "epayTTipoPagamento" )
	@Mapping ( target = "iuv", source = "iuvNumeroAvviso" )
	@Mapping ( target = "pagatore", source = "epayTAnagrafica" )
	PagamentoDTO toDto ( EpayTPagamento epayTPagamento );
}
