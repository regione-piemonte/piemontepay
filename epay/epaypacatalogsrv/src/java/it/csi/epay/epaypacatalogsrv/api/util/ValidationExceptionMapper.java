/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.api.util;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
/**
 *
 */

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<javax.validation.ConstraintViolationException> {

    public Response toResponse(javax.validation.ConstraintViolationException cex) {
       Error error = new Error();
       error.setMessage("Errore in fase di validazione delle richiesta. " + cex);
       ResponseBuilder b = Response.ok();
       b.entity(error);
       b.status ( Status.BAD_REQUEST );
       return b.build ();
    }
}
