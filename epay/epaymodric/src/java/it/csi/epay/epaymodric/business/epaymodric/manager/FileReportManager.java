/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.manager;

import it.csi.epay.epaymodric.business.epaymodric.model.CblTFileReport;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTReport;

public interface FileReportManager {
	void salvaFileReport(CblTFileReport fileReport, CblTReport report);
}
