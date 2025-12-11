/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.business.v1;

import it.csi.epay.epayapi.integration.dto.EpayTPdfReportDTO;


public interface AvvisoPagamentoReportService {

	EpayTPdfReportDTO getJasperReport ();
}
