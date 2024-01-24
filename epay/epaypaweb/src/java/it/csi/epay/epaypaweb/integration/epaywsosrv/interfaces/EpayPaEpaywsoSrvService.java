/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.integration.epaywsosrv.interfaces;

import java.math.BigInteger;
import java.util.List;

import it.csi.epay.epaypaweb.dto.StatoFlussoAggregatoDto;
import it.csi.epay.epaypaweb.exception.PersistenceException;


public interface EpayPaEpaywsoSrvService {

    public final static String URL_PROPERTY = "epaywsosrv.url";


    public  List<StatoFlussoAggregatoDto>  ricercaStatiAggregatiWso ( String idFlusso, String idMessaggio, String codFiscEnte , BigInteger idTipoRichiesta) throws PersistenceException;
}
