/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.business;

import it.csi.epay.epaybeapi.integration.dto.EpayTPdfReportDTO;

public interface AvvisoPagamentoReportService {

	EpayTPdfReportDTO getJasperReport ();
}
