/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.integration.facade;

import it.csi.epay.epaypacatalogweb.vo.UserVO;

public interface GestioneUtentiFacade {

    UserVO getUser(int id);

}
