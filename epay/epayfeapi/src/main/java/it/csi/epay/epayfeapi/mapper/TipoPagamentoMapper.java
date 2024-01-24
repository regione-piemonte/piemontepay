/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.mapper;

import it.csi.epay.epayfeapi.dto.TipoPagamentoDTO;
import it.csi.epay.epayfeapi.entity.EpayTTipoPagamento;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper ( componentModel = "cdi", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
public interface TipoPagamentoMapper {

	TipoPagamentoDTO toDto ( EpayTTipoPagamento epayTTipoPagamento );

	EpayTTipoPagamento toEntity ( TipoPagamentoDTO tipoPagamentoDTO );
}
