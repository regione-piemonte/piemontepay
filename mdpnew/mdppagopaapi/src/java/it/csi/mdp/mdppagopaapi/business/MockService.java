/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.business;

import it.csi.mdp.mdppagopaapi.dto.exception.VerificaDatiPagamentoException;
import it.csi.mdp.mdppagopaapi.util.clientws.EsitoChiediDatiPagamento;
import it.csi.mdp.mdppagopaapi.util.clientws.EsitoRiceviEsito;
import it.csi.mdp.mdppagopaapi.util.clientws.EsitoVerificaDatiPagamento;
import it.csi.mdp.mdppagopaapi.util.clientws.ParametriChiediDatiPagamento;
import it.csi.mdp.mdppagopaapi.util.clientws.ParametriRiceviEsito;
import it.csi.mdp.mdppagopaapi.util.clientws.ParametriVerificaDatiPagamento;

public interface MockService {

    /**
     *
     * servizio di mock di esempio.
     */
    void esempioServzioMock ();

    public EsitoChiediDatiPagamento chiediDatiPagamento ( String endPointFruitore, ParametriChiediDatiPagamento parametri );

    public EsitoRiceviEsito RiceviEsito ( ParametriRiceviEsito parametri );

    public EsitoVerificaDatiPagamento verificaDatiPagamento ( ParametriVerificaDatiPagamento request ) throws VerificaDatiPagamentoException;
}
