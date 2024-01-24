/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysimweb.util;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.csi.epay.epaysimweb.common.config.LogConfig;
import it.csi.epay.epaysimweb.model.cache.CachedElement;

public class HttpUtils {

    private static final int CONNECTION_TIMEOUT = 2000;
    private static final int SO_TIMEOUT = 2000;
    private static final long PAGE_TIMEOUT = 30 * 60 * 1000;

    private static HttpClient Client = null;
    private static Map<String, CachedElement<String>> Cache = new LinkedHashMap<>();

    private static final Logger LOGGER = LoggerFactory.getLogger ( LogConfig.HANDLER_UTILS );

    private static HttpClient getClient(int connectionTimeout, int soTimeout) {
        // Create an instance of HttpClient.
        if (Client == null) {
            Client = new HttpClient();
            Client.getHttpConnectionManager().getParams()
            .setConnectionTimeout(connectionTimeout);
            Client.getHttpConnectionManager().getParams()
            .setSoTimeout(soTimeout);
        }

        return Client;

    }

    public static String getResource(String portalLayout, String resource) {
        return getResourceFromUrl(portalLayout + resource);
    }

    public static String getResourceFromUrl(String url) {
        CachedElement<String> page = null;
        String resource = null;

        if (!isEmpty(url)) {
            if (Cache.containsKey(url)) {
                page = Cache.get(url);
            }

            if (page != null && page.isValid(PAGE_TIMEOUT)) {
                if ( LOGGER.isDebugEnabled () )
                    LOGGER.debug ( "[HttpUtils::getResource] get <" + url
                        + "> from cache");

                resource = page.getElement();
            } else {
                if ( LOGGER.isDebugEnabled () )
                    LOGGER.debug ( "[HttpUtils::getResource] read <" + url + ">" );

                resource = getRemoteResource(url);
                Cache.put(url, new CachedElement<>(resource));
            }
        }

        return nvl(resource, "<!--  -->");

    }

    private static String getRemoteResource(String url) {
        String resource = null;

        // Create a method instance.
        GetMethod method = new GetMethod(url);

        // Provide custom retry handler is necessary
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
            new DefaultHttpMethodRetryHandler(0, false));

        try {
            // Execute the method.
            int statusCode = getClient(CONNECTION_TIMEOUT, SO_TIMEOUT)
                            .executeMethod(method);
            if (statusCode != HttpStatus.SC_OK) {
                LOGGER.error ( "[HttpUtils::getResource] [" + url + "] "
                                + "Method failed: " + method.getStatusLine());
            } else {
                // Read the response body.
                byte[] responseBody = method.getResponseBody();
                resource = new String(responseBody);
            }
        } catch (HttpException e) {
            LOGGER.error ( "[HttpUtils::getResource] [" + url + "] "
                            + "Fatal protocol violation", e);
        } catch (IOException e) {
            LOGGER.error ( "[HttpUtils::getResource] [" + url + "] "
                            + "Fatal transport error", e);
        } finally {
            // Release the connection.
            method.releaseConnection();
        }

        return resource;
    }

    private static boolean isEmpty(String value) {
        boolean empty = true;

        if (value != null) {
            int tot = value.length();
            for (int index = 0; empty && index < tot; index++)
                empty = empty && Character.isWhitespace(value.charAt(index));
        }
        return empty;
    }

    private static String nvl(String value, String defaultValue) {
        return (isEmpty(value) ? defaultValue : value);
    }

}
