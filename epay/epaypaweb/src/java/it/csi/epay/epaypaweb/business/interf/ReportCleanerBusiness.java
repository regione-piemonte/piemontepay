/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.business.interf;

import javax.ejb.Local;

/** logica di business */
@Local
public interface ReportCleanerBusiness {

	boolean isRunning();

	void cancellazioneReport(String[] reportStatusCodes, int thresholdDays);
}
