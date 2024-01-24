/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.manager;

import java.util.List;

import it.csi.epay.epaymodric.business.epaymodric.model.CblTReport;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsRicercaFlussoOrigine;

public interface ReportFlussiCreatorManager {
	List<String> generaReport(DTOInputWsRicercaFlussoOrigine flussoInput, CblTReport reportDto , int pageSize);
}
