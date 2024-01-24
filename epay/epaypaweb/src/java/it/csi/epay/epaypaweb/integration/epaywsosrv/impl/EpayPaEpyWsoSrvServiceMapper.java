/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.integration.epaywsosrv.impl;

import java.util.ArrayList;
import java.util.List;

import it.csi.epay.epaypaweb.dto.StatoFlussoAggregatoDto;
import it.csi.epay.epaypaweb.integration.epaywsosrv.EsitoRicercaStatiAggregatiWsoType;


public abstract class EpayPaEpyWsoSrvServiceMapper {

    public static List<StatoFlussoAggregatoDto> map ( List<EsitoRicercaStatiAggregatiWsoType> input ) {

        List<StatoFlussoAggregatoDto> output = new ArrayList<> ();
        for ( EsitoRicercaStatiAggregatiWsoType entry: input ) {
            output.add ( new StatoFlussoAggregatoDto (
                entry.getIdFlusso(),
                entry.getCodFiscaleEnte(),
                entry.getStatoFlusso(),
                entry.getDatoAggiuntivoCodEsito(),
                entry.getDatoAggiuntivoDescEsito()) );
        }

        return output;
    }

}
