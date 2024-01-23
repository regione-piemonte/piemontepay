/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.mapper;

import it.csi.epay.epayfeapi.dto.PagamentoSecondarioComponentiDTO;
import it.csi.epay.epayfeapi.entity.EpayTPagamentoSecondarioComponenti;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;


@Mapper ( componentModel = "cdi", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
public interface PagamentoSecondarioComponentiMapper {

	List<PagamentoSecondarioComponentiDTO> toDtoList ( List<EpayTPagamentoSecondarioComponenti> epayTPagamentoSecondarioComponentis );
}
