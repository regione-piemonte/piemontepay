/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayapi.util;

import it.csi.epay.epayapi.dto.monitoring.ServiceStatusDTO;


/**
 *
 */

public interface Monitorable {

    public ServiceStatusDTO checkStatus () throws Exception;
}
