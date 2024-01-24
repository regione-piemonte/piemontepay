/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.factory.model;

import org.springframework.web.client.RestTemplate;

import it.csi.epay.epaymodric.business.epaymodric.factory.FeignClientProvider;

/**
 *
 */

public class FeignClientContext {

    private FeignClientProvider provider;

    private RestTemplate restTemplate;

    public FeignClientProvider getProvider () {
        return provider;
    }

    public void setProvider ( FeignClientProvider provider ) {
        this.provider = provider;
    }

    public RestTemplate getRestTemplate () {
        return restTemplate;
    }

    public void setRestTemplate ( RestTemplate restTemplate ) {
        this.restTemplate = restTemplate;
    }

}
