/*
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 *
 * SPDX-License-Identifier: EUPL-1.2 */
package it.csi.epay.epayfeapi.rest.client;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.logging.Log;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

public class ApiClient {

    protected static final int TIMEOUT = 60000;
    private ResteasyClient client;

    // Costruttore per inizializzare il client
    public ApiClient() {
        this.client = (ResteasyClient) ResteasyClientBuilder.newBuilder()
                .connectTimeout(TIMEOUT,TimeUnit.MILLISECONDS)
                .build();
    }

    public RestEasyHttpResponse getResponse(String url, Object request, String secret, String requestMethod) {
        var methodName = "[getResponse] ";

        Log.infof("%sBEGIN", methodName);
		if (null!= request)
		{
		Log.infof ( "%sparam request:%s", methodName, request );
		}
        Log.infof("%sparam secret:%s", methodName, secret);
        Log.infof("%sparam requestMethod:%s", methodName, requestMethod);

        String basicAuth = Base64.getEncoder().encodeToString(secret.getBytes());

        // Costruisce il target REST
        ResteasyWebTarget target = client.target(URI.create(url));

        // Serializza il request object in JSON
        String requestString = buildJsonInvio(request);
        Log.infof("%srequestString:%s", methodName, requestString);

        Response response = null;
        if ("POST".equalsIgnoreCase(requestMethod)) {
            response = target.request(MediaType.APPLICATION_JSON)
                    .header("Authorization", "Basic " + basicAuth)
                    .accept(MediaType.APPLICATION_JSON)
                    .post(Entity.json(requestString));
        } else if ("GET".equalsIgnoreCase(requestMethod)) {
            response = target.request(MediaType.APPLICATION_JSON)
                    .header("Authorization", "Basic " + basicAuth)
                    .accept(MediaType.APPLICATION_JSON)
                    .get();
        }

        Log.infof("%sEND", methodName);
        
        // Ritorna la risposta come CustomHttpResponse
        return new RestEasyHttpResponse(response);
    }

    public String buildJsonInvio(Object model) {
        var methodName = "[buildJsonInvio] ";

        Log.infof("%sBEGIN", methodName);
        Log.infof("%sparam model:%s", methodName, model);

        String result;
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(Include.NON_NULL);
            result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(model);
        } catch (Exception e) {
            Log.errorf("%serrore nella costruzione del JSon: %s", methodName, e.getMessage());
            return "";
        }

        Log.infof("%sEND", methodName);
        return result;
    }
}
