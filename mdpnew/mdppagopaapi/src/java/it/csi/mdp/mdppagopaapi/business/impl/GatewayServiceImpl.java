/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.mdp.mdppagopaapi.business.GatewayService;
import it.csi.mdp.mdppagopaapi.integration.domain.Gateway;
import it.csi.mdp.mdppagopaapi.integration.repository.GatewayRepository;

@Service
public class GatewayServiceImpl implements GatewayService {

    @Autowired
    private GatewayRepository gatewayRepository;

    @Override
    public Gateway findByIdGateway(String idGateway) {

        return gatewayRepository.findOne ( idGateway );
    }


}
