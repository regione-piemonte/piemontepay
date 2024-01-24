/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.business;

import it.csi.epay.epaybeapi.dto.request.RichiestaAvvisoPagamentoRequest;
import it.csi.epay.epaybeapi.dto.request.StampaAvvisoPagamentoRequest;
import it.csi.epay.epaybeapi.dto.response.StampaAvvisoPagamentoResponse;


public interface StampaAvvisoPagamentoService {

    StampaAvvisoPagamentoResponse getAvvisoPagamento ( RichiestaAvvisoPagamentoRequest request );

    StampaAvvisoPagamentoResponse getAvvisoPagamentoSportello ( StampaAvvisoPagamentoRequest request );
}
