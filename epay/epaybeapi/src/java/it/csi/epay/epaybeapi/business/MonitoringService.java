/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.business;

import it.csi.epay.epaybeapi.dto.monitoring.ServiceStatusDTO;


public interface MonitoringService {

    /**
     * Ottiene informazioni sullo stato dei servizi che compongono l'applicativo
     *
     * @return le informazioni sullo stato dei servizi, aggregate
     */
    ServiceStatusDTO getServiceStatus ();
}
