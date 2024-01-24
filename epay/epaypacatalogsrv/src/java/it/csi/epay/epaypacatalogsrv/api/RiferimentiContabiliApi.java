/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.api;

import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.AggiornaDatoSpecificoRiscossioneInput;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.RiferimentiContabiliGetByIdInput;
import it.csi.epay.epaypacatalogsrv.dto.security.Scopes;
import it.csi.epay.epaypacatalogsrv.dto.security.Secured;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.RiferimentoContabileInScadenzaInput;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.VerificaNumeroRiferimentiContabiliInVitaPerCovInput;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.VerificaPresenzaRiferimentiContabiliInput;
import it.csi.epay.epaypacatalogsrv.vo.Constants;
import org.jboss.resteasy.spi.validation.ValidateRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


/**
 * Risorsa RestEasy per il reperimento di enti i cui riferimenti contabli risultano in scadenza
 */
@Path ( "/" )
@Consumes ( Constants.DEFAULT_PROTOCOL )
@Produces ( Constants.DEFAULT_PROTOCOL )
@ValidateRequest
public interface RiferimentiContabiliApi {

    /**
     * Ottiene una lista di enti i cui riferimenti contabli risultano in scadenza
     *
     * @return response JSON
     */
	@Secured ( Scopes.RIFERIMENTI_CONTABILI )
//	@Secured( notSecured = true)
    @POST
    @Path ( "/entiRiferimentiContabiliInScadenza" )
    public Response getEntiRiferimentiContabiliInScadenza ();
	
	
	 /**
     * Ottiene una lista di riferimenti contabli che risultano in scadenza
     *
     * @return response JSON
     */
	@Secured ( Scopes.RIFERIMENTI_CONTABILI )
//	@Secured( notSecured = true)
    @POST
    @Path ( "/riferimentiContabiliInScadenza" ) Response getRiferimentiContabiliInScadenza(RiferimentoContabileInScadenzaInput input) ;
	
	 /**
     * Ottiene una lista di riferimenti contabli che risultano in scadenza
     *
     * @return response JSON
     */
	@Secured ( Scopes.RIFERIMENTI_CONTABILI )
//	@Secured( notSecured = true)
    @POST
    @Path ( "/verificaPresenzaRiferimentiContabili" ) Response verificaPresenzaRiferimentiContabili(VerificaPresenzaRiferimentiContabiliInput input) ;
	
	@Secured ( Scopes.RIFERIMENTI_CONTABILI )
//  @Secured( notSecured = true)
    @POST
    @Path ( "/verificaNumeroRiferimentiContabiliInVitaPerCov" ) Response verificaNumeroRiferimentiContabiliInVitaPerCov(VerificaNumeroRiferimentiContabiliInVitaPerCovInput input) ;

	@Secured ( Scopes.RIFERIMENTI_CONTABILI )
	@POST
	@Path ( "/getByIdTassonomia" ) Response getByIdTassonomia ( RiferimentiContabiliGetByIdInput input );

	@Secured ( Scopes.RIFERIMENTI_CONTABILI )
	@POST
	@Path ( "/aggiornaDatoSpecificoRiscossione" ) Response aggiornaDatoSpecificoRiscossione ( AggiornaDatoSpecificoRiscossioneInput input );
}
