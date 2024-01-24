/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.spi.validation.ValidateRequest;

import it.csi.epay.epaypacatalogsrv.dto.security.Scopes;
import it.csi.epay.epaypacatalogsrv.dto.security.Secured;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.DatiSpecificiRiscossioneInput;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.DatoSpecificoRiscossioneInput;
import it.csi.epay.epaypacatalogsrv.vo.Constants;

/**
 * Risorsa RestEasy per il reperimento dei dati specifici riscossione
 */
@Path ( "/" )
@Consumes ( Constants.DEFAULT_PROTOCOL )
@Produces ( Constants.DEFAULT_PROTOCOL )
@ValidateRequest
public interface DatiSpecificiRiscossioneApi {
  
    /**
     * Ottiene dati specifici di riscossione
     * potrebbe esporre dati sensibili
     *
     * @return response JSON
     */
    //@Secured( notSecured = true)
    @Secured ( Scopes.DATI_SPEC_RISC )
    @POST
    @Path ( "/datoSpecificoRiscossione" )
    public Response getDatoSpecificoRiscossione (DatoSpecificoRiscossioneInput input);
    
    /**
     * Ottiene dati specifici di riscossione
     * potrebbe esporre dati sensibili
     *
     * @return response JSON
     */
    //@Secured( notSecured = true)
    @Secured ( Scopes.DATI_SPEC_RISC )
    @POST
    @Path ( "/datiSpecificiRiscossione" )
    public Response getDatiSpecificiRiscossione (DatiSpecificiRiscossioneInput input);

}
