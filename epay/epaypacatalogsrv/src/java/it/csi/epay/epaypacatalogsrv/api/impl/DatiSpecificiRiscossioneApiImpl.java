/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.api.impl;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import it.csi.epay.epaypacatalogsrv.api.DatiSpecificiRiscossioneApi;
import it.csi.epay.epaypacatalogsrv.api.util.CodiciEsito;
import it.csi.epay.epaypacatalogsrv.api.util.SpringSupportedResource;
import it.csi.epay.epaypacatalogsrv.business.service.DatiSpecificiRiscossioneService;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.DatiSpecificiRiscossioneInput;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.DatiSpecificiRiscossioneOutput;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.DatoSpecificoRiscossioneOutput;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.DatoSpecificoRiscossioneInput;


@SpringSupportedResource
public class DatiSpecificiRiscossioneApiImpl implements DatiSpecificiRiscossioneApi {

    @Autowired
    private DatiSpecificiRiscossioneService service;

    @Override
    public Response getDatoSpecificoRiscossione ( DatoSpecificoRiscossioneInput input ) {

        DatoSpecificoRiscossioneOutput output = service.getDatoSpecificoRiscossione ( input );
        return Response
            .status ( output != null && CodiciEsito.ESITO_OK.getCodice ().equals ( output.getCodiceEsito () ) ? 200
                            : CodiciEsito.CODICE_ERRORE_REPERIMENTO_DSR.getCodice ().equals ( output.getCodiceEsito () )
                                || CodiciEsito.CODICE_ERRORE_ENTE_NON_TROVATO.getCodice ().equals ( output.getCodiceEsito () )
                                || CodiciEsito.CODICE_ERRORE_COV_NON_TROVATO.getCodice ().equals ( output.getCodiceEsito () )
                                || CodiciEsito.ERRORE_DATI_NON_UNIVOCI.getCodice ().equals ( output.getCodiceEsito () )
                                || CodiciEsito.ERRORE_DATI_NON_PRESENTI.getCodice ().equals ( output.getCodiceEsito () )
                                || CodiciEsito.DATI_INPUT_MANCANTI.getCodice ().equals ( output.getCodiceEsito () ) ? 400 : 500 )
            .entity ( output ).build ();
    }
    
    @Override
    public Response getDatiSpecificiRiscossione ( DatiSpecificiRiscossioneInput input ) {

        DatiSpecificiRiscossioneOutput output = service.getDatiSpecificiRiscossione  ( input );
        return Response
            .status ( output != null && CodiciEsito.ESITO_OK.getCodice ().equals ( output.getCodiceEsito () ) ? 200
                            : CodiciEsito.CODICE_ERRORE_REPERIMENTO_DSR.getCodice ().equals ( output.getCodiceEsito () )
                                || CodiciEsito.CODICE_ERRORE_ENTE_NON_TROVATO.getCodice ().equals ( output.getCodiceEsito () )
                                || CodiciEsito.CODICE_ERRORE_COV_NON_TROVATO.getCodice ().equals ( output.getCodiceEsito () )
                                || CodiciEsito.ERRORE_DATI_NON_UNIVOCI.getCodice ().equals ( output.getCodiceEsito () )
                                || CodiciEsito.ERRORE_DATI_NON_PRESENTI.getCodice ().equals ( output.getCodiceEsito () )
                                || CodiciEsito.DATI_INPUT_MANCANTI.getCodice ().equals ( output.getCodiceEsito () ) ? 400 : 500 )
            .entity ( output ).build ();
    }

}
