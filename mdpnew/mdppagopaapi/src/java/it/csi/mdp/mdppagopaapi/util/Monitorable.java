/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdppagopaapi.util;

import it.csi.mdp.mdppagopaapi.dto.monitoring.ServiceStatusDTO;


/**
 *
 */

public interface Monitorable {

    public ServiceStatusDTO checkStatus () throws Exception;
}
