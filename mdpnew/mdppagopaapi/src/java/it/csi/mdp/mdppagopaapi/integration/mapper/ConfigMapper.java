/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import it.csi.mdp.mdppagopaapi.integration.domain.Config;
import it.csi.mdp.mdppagopaapi.integration.dto.ConfigDTO;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR,
componentModel = "spring")
public interface ConfigMapper extends IMapper<Config, ConfigDTO> {

}
