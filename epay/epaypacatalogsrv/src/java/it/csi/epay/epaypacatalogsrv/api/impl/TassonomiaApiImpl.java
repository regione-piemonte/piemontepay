/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.api.impl;

import it.csi.epay.epaypacatalogsrv.api.TassonomiaApi;
import it.csi.epay.epaypacatalogsrv.api.util.CodiciEsito;
import it.csi.epay.epaypacatalogsrv.api.util.SpringSupportedResource;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationDispatcher;
import it.csi.epay.epaypacatalogsrv.business.service.TassonomiaService;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.ws.rs.core.Response;
import javax.xml.ws.WebServiceContext;


@SpringSupportedResource
public class TassonomiaApiImpl implements TassonomiaApi {

    @Autowired
    private TassonomiaService service;

    @Resource
    WebServiceContext wsContext;

    @Autowired
    private OperationDispatcher dispatcher;


    @Override
    public Response getElencoTassonomiaPerCodiceTipoEnteCreditore(ElencoTassonomiaPerCodiceTipoEnteCreditoreInput input) {
        ElencoTassonomiaPerCodiceTipoEnteCreditoreOutput output = service.getElencoTassonomiaPerCodiceTipoEnteCreditore(input);
        return Response
                        .status ( output != null && CodiciEsito.ESITO_OK.getCodice ().equals ( output.getCodiceEsito () ) ? 200
                                        : CodiciEsito.DATI_INPUT_MANCANTI.getCodice ().equals ( output.getCodiceEsito () ) ||
                                        CodiciEsito.DATI_NON_TROVATI.getCodice ().equals ( output.getCodiceEsito () ) ? 400 : 500 )
                        .entity ( output ).build ();
    }


    @Override
    public Response ricercaTassonomia(RicercaTassonomiaInput input) {

        RicercaTassonomiaOutput output=   dispatcher.dispatch ( input, RicercaTassonomiaOutput.class );
        return Response
                        .status ( output.getCodiceStato() )
                        .entity ( output ).build ();
    }

    @Override
    public Response ricercaAllTassonomie () {
        RicercaTassonomiaOutput output = dispatcher.dispatch ( new RicercaTassonomiaAllInput (), RicercaTassonomiaOutput.class );
        return Response
                        .status ( output.getCodiceStato () )
                        .entity ( output ).build ();
    }


    @Override
    public Response updateTassonomie ( UpdateTassonomieInput input ) {
        UpdateTassonomieOutput output = dispatcher.dispatch ( input, UpdateTassonomieOutput.class );
        return Response
                        .status ( output.getCodiceStato () )
                        .entity ( output ).build ();
    }

	@Override public Response ricercaTassonomieBatchAggPosDeb () {
		OutputRicercaTassonomieBatchAggPosDeb output = dispatcher.dispatch ( new RicercaTassonomiaBatchAggPosDebInput (),
						OutputRicercaTassonomieBatchAggPosDeb.class );
		return Response.status ( output.getCodiceStato () )
						.entity ( output ).build ();
	}

}
