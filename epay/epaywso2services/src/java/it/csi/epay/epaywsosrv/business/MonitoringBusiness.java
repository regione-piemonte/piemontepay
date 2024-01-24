/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.business;

import it.csi.epay.epaywsosrv.dto.MonitorResultDto;

/** logica di business */
public interface MonitoringBusiness {

	public MonitorResultDto getStatus();

	public Boolean isAlive();

}
