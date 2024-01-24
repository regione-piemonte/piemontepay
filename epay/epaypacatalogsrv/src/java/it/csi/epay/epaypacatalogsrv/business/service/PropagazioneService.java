/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.business.service;

import it.csi.epay.epaypacatalogsrv.dto.EsitoPropagazioneDTO;
import it.csi.epay.epaypacatalogsrv.dto.enums.AzioneDaPropagare;
import it.csi.epay.epaypacatalogsrv.model.CodiceVersamento;
import it.csi.epay.epaypacatalogsrv.model.Ente;
import it.csi.epay.epaypacatalogsrv.model.RiferimentoContabile;
import it.csi.epay.epaypacatalogsrv.model.StoricoRiferimentoContabile;
import it.csi.epay.epaypacatalogsrv.model.Utente;

public interface PropagazioneService {

    default EsitoPropagazioneDTO propagaEnte ( Ente corrente, AzioneDaPropagare azione,String ibanOriginale, String bicOriginale ) {
        return propagaEnte ( corrente, azione, null, true, ibanOriginale, bicOriginale );
    }

    default EsitoPropagazioneDTO propagaCodiceVersamento ( CodiceVersamento corrente, AzioneDaPropagare azione ) {
		return propagaCodiceVersamento ( corrente, azione, null, true );
    }

    default EsitoPropagazioneDTO propagaRiferimentoContabile ( RiferimentoContabile corrente, AzioneDaPropagare azione ) {
        return propagaRiferimentoContabile ( corrente, azione, null, true );
    }

    default EsitoPropagazioneDTO propagaRiferimentoContabile ( StoricoRiferimentoContabile corrente, AzioneDaPropagare azione ) {
        return propagaRiferimentoContabile ( corrente, azione, null, true );
    }

    default EsitoPropagazioneDTO propagaUtente ( Utente corrente, AzioneDaPropagare azione ) {
        return propagaUtente ( corrente, azione, null, true );
    }

    EsitoPropagazioneDTO propagaEnte ( Ente corrente, AzioneDaPropagare azione, Long idTransazione, boolean doCommit, String ibanOriginale, String bicOriginale );

    EsitoPropagazioneDTO propagaCodiceVersamento ( CodiceVersamento corrente, AzioneDaPropagare azione, Long idTransazione, boolean doCommit );

    EsitoPropagazioneDTO propagaRiferimentoContabile ( RiferimentoContabile corrente, AzioneDaPropagare azione, Long idTransazione, boolean doCommit );

    EsitoPropagazioneDTO propagaRiferimentoContabile ( StoricoRiferimentoContabile corrente, AzioneDaPropagare azione, Long idTransazione, boolean doCommit );

    EsitoPropagazioneDTO propagaUtente ( Utente corrente, AzioneDaPropagare azione, Long idTransazione, boolean doCommit );

    Boolean isPropagazioneAbilitata ();
}
