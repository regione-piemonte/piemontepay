/*
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 *
 * SPDX-License-Identifier: EUPL-1.2 */
package it.csi.epay.epayfeapi.rest.client;

import javax.ws.rs.core.Response;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class RestEasyHttpResponse {
	
	private Response response;

    public RestEasyHttpResponse(Response response) {
        this.response = response;
    }

    // Mappa getResponseCode su getStatus di Response
    public int getResponseCode() {
        return response.getStatus();
    }

    // Mappa getInputStream su il contenuto della risposta
    public InputStream getInputStream() {
        String entity = response.readEntity(String.class); // Legge l'entità come String
        return new ByteArrayInputStream(entity.getBytes());
    }

    // Mappa getErrorStream se la risposta contiene un errore
    public InputStream getErrorStream() {
        if (response.getStatus() >= 400) {
            String entity = response.readEntity(String.class); // Legge l'errore come String
            return new ByteArrayInputStream(entity.getBytes());
        }
        return null;
    }

    public void close() {
        response.close();
    }
}


