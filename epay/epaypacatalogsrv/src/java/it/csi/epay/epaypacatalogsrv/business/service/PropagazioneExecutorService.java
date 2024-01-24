/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.business.service;

import java.util.List;

import it.csi.epay.epaypacatalogsrv.dto.EsitoPropagazioneCommitDTO;
import it.csi.epay.epaypacatalogsrv.dto.EsitoPropagazioneDatiDTO;
import it.csi.epay.epaypacatalogsrv.dto.EsitoPropagazioneRollbackDTO;
import it.csi.epay.epaypacatalogsrv.dto.EsitoVerificaStatoServizioDTO;
import it.csi.epay.epaypacatalogsrv.dto.enums.AzioneDaPropagare;
import it.csi.epay.epaypacatalogsrv.model.CodiceVersamento;
import it.csi.epay.epaypacatalogsrv.model.Ente;
import it.csi.epay.epaypacatalogsrv.model.LogTransazione;
import it.csi.epay.epaypacatalogsrv.model.RiferimentoContabile;
import it.csi.epay.epaypacatalogsrv.model.Servizio;
import it.csi.epay.epaypacatalogsrv.model.Sottoscrittore;
import it.csi.epay.epaypacatalogsrv.model.StoricoRiferimentoContabile;
import it.csi.epay.epaypacatalogsrv.model.Transazione;

public interface PropagazioneExecutorService {

    EsitoPropagazioneDatiDTO propagaEnte ( Transazione transazione, Sottoscrittore target, Servizio servizio, Ente enteNew, AzioneDaPropagare azione, String iban, String bic );

    EsitoPropagazioneDatiDTO propagaCodiceVersamento ( Transazione transazione, Sottoscrittore target, Servizio servizio, CodiceVersamento corrente,
        AzioneDaPropagare azione );

    EsitoPropagazioneDatiDTO propagaRiferimentoContabile ( Transazione transazione, Sottoscrittore target, Servizio servizio, RiferimentoContabile corrente,
        AzioneDaPropagare azione );

    EsitoPropagazioneDatiDTO propagaRiferimentoContabile ( Transazione transazione, Sottoscrittore target, Servizio servizio,
        StoricoRiferimentoContabile corrente,
        AzioneDaPropagare azione );

    EsitoPropagazioneCommitDTO propagaCommit ( Transazione transazione, Sottoscrittore target, Servizio servizio );

    EsitoPropagazioneRollbackDTO propagaRollback ( Transazione transazione, Sottoscrittore target, Servizio servizio );

    EsitoVerificaStatoServizioDTO testStatoServizio ( Sottoscrittore target, Servizio servizio );

    void salvaStatoTransazione ( Transazione transazione, List<LogTransazione> statoSottoscrittori );

}
