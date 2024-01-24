/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import it.csi.epay.epaybeapi.dto.security.ClientInfo;
import it.csi.epay.epaybeapi.dto.security.ClientInfoDTO;
import it.csi.epay.epaybeapi.integration.mapper.mapping.ClientInfoMapping;


/**
 * MapStruct mapper for "ClientInfo"
 *
 * @author EII
 *
 */
@Service
public class ClientInfoMapper {

    private ClientInfoMapping mapping = Mappers.getMapper ( ClientInfoMapping.class );

    public ClientInfoDTO toDTO ( ClientInfo record ) {

        ClientInfoDTO dto = mapping.toDTO ( record );

        return dto;
    }

}
