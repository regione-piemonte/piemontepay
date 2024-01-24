/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.schedule;

import javax.ejb.Local;

@Local
public interface JobScheduler {

	void initialize();

	void stop();

}
