/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import it.csi.epay.epayapi.dto.security.ClientInfo;
import it.csi.epay.epayapi.dto.security.ClientInfoDTO;
import it.csi.epay.epayapi.integration.mapper.mapping.ClientInfoMapping;


/**
 * MapStruct mapper for "ClientInfo"
 *
 * @author fabio.fenoglio
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
