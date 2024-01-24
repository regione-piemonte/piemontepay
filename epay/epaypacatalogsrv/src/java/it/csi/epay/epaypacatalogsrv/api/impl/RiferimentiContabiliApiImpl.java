/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.api.impl;

import it.csi.epay.epaypacatalogsrv.api.RiferimentiContabiliApi;
import it.csi.epay.epaypacatalogsrv.api.util.CodiciEsito;
import it.csi.epay.epaypacatalogsrv.api.util.SpringSupportedResource;
import it.csi.epay.epaypacatalogsrv.business.service.RiferimentiContabiliInScadenzaService;
import it.csi.epay.epaypacatalogsrv.business.service.RiferimentoContabileService;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.AggiornaDatoSpecificoRiscossioneInput;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.RiferimentiContabiliGetByIdInput;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.*;
import it.csi.epay.epaypacatalogsrv.model.RiferimentoContabile;
import it.csi.epay.epaypacatalogsrv.vo.Constants;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;
import java.util.List;


@SpringSupportedResource
public class RiferimentiContabiliApiImpl implements RiferimentiContabiliApi {

    @Autowired
    private RiferimentiContabiliInScadenzaService service;
    
    @Autowired
    private RiferimentoContabileService riferimentoContabileService;

    @Override
    public Response getEntiRiferimentiContabiliInScadenza () {

        EntiRiferimentiContabiliInScadenzaOutput output = service.getEntiRiferimentiContabiliInScadenza ();
        return Response
            .status ( output != null && CodiciEsito.ESITO_OK.getCodice ().equals ( output.getCodiceEsito () ) ? 200
                            : CodiciEsito.ERRORE_REPERIMENTO_ENTI.getCodice ().equals ( output.getCodiceEsito () )
                                || CodiciEsito.DATI_NON_TROVATI.getCodice ().equals ( output.getCodiceEsito () ) ? 400 : 500 )
            .entity ( output ).build ();
    }
    

    public Response getRiferimentiContabiliInScadenza ( RiferimentoContabileInScadenzaInput input ) {
        RiferimentiContabiliInScadenzaOutput output = service.getRiferimentiContabiliInScadenza ( input );
        return Response
            .status ( output != null && CodiciEsito.ESITO_OK.getCodice ().equals ( output.getCodiceEsito () ) ? 200
                            : CodiciEsito.DATI_INPUT_MANCANTI.getCodice ().equals ( output.getCodiceEsito () ) ||
                                CodiciEsito.ERRORE_REPERIMENTO_RIFERIMENTI_CONTAB_ENTE.getCodice ().equals ( output.getCodiceEsito () )
                                || CodiciEsito.DATI_NON_TROVATI.getCodice ().equals ( output.getCodiceEsito () ) ? 400 : 500 )
            .entity ( output ).build ();
    }


	@Override
	public Response verificaPresenzaRiferimentiContabili(VerificaPresenzaRiferimentiContabiliInput input) {
		
		VerificaPresenzaRiferimentiContabiliOutput output  = riferimentoContabileService.verificaPresenzaRiferimentiContabili ( input );
		  return Response
		            .status ( output != null && Constants.RESULT_CODES.OK.equals ( output.getCodiceEsito () ) ? 200
		                            : CodiciEsito.DATI_INPUT_MANCANTI.getCodice ().equals ( output.getCodiceEsito () ) ||
		                                CodiciEsito.ERRORE_REPERIMENTO_RIFERIMENTI_CONTAB_ENTE.getCodice ().equals ( output.getCodiceEsito () )
		                                || CodiciEsito.DATI_NON_TROVATI.getCodice ().equals ( output.getCodiceEsito () ) ? 400 : 500 )
		            .entity ( output ).build ();
	}


    @Override
    public Response verificaNumeroRiferimentiContabiliInVitaPerCov ( VerificaNumeroRiferimentiContabiliInVitaPerCovInput input ) {
        VerificaNumeroRiferimentiContabiliInVitaPerCovOutput output  = riferimentoContabileService.verificaNumeroRiferimentiContabiliInVitaPerCov ( input );
        return Response
                        .status ( output != null && Constants.RESULT_CODES.OK.equals ( output.getCodiceEsito () ) ? 200
                                        : CodiciEsito.DATI_INPUT_MANCANTI.getCodice ().equals ( output.getCodiceEsito () ) ||
                                            CodiciEsito.ERRORE_REPERIMENTO_RIFERIMENTI_CONTAB_ENTE.getCodice ().equals ( output.getCodiceEsito () )
                                            || CodiciEsito.DATI_NON_TROVATI.getCodice ().equals ( output.getCodiceEsito () ) ? 400 : 500 )
                        .entity ( output ).build ();
    }

	@Override public Response getByIdTassonomia ( RiferimentiContabiliGetByIdInput input ) {
		List<RiferimentoContabile> output = riferimentoContabileService.getByIdTassonomia ( input.getId () );
		return Response.status ( 200 ).entity ( output ).build ();
	}

	@Override public Response aggiornaDatoSpecificoRiscossione ( AggiornaDatoSpecificoRiscossioneInput input ) {
		riferimentoContabileService.aggiornaDatoSpecificoRiscossione ( input.getIdTassonomia (), input.getTassonomiaEsistente (), input.getTassonomiaNuova () );
		return Response.status ( 200 ).build ();
	}

}
