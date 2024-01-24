/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.common.config;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WebappConfig  implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -6568575495362698100L;

    @Value("#{application[risStatic]}")
    private String staticResourcesPath;

    @Value("#{application[contextPath]}")
    private String contextPath;

    private java.util.ResourceBundle resourceBundle;

    private String ambiente;

    @Value ( "#{application[epaypaHome]}" )
    private String epaypaHome;

    @Value ( "#{application[requestParam]}" )
    private String requestParam;

    public WebappConfig() {
        resourceBundle = java.util.ResourceBundle.getBundle("config");
    }

    public java.util.ResourceBundle getRes() {
        return resourceBundle;
    }

    public String getAmbiente() {
        return ambiente;
    }

    public void setStaticResourcesPath(String staticResourcesPath) {
        this.staticResourcesPath = staticResourcesPath;
    }

    public String getLogoutUrl() {
        return "???";
    }

    public String getStaticResourcesPath() {
        return staticResourcesPath;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    /**
     * @return the epaypaHome
     */
    public String getEpaypaHome () {
        return epaypaHome;
    }

    /**
     * @param epaypaHome the epaypaHome to set
     */
    public void setEpaypaHome ( String epaypaHome ) {
        this.epaypaHome = epaypaHome;
    }

    /**
     * @return the requestParam
     */
    public String getRequestParam () {
        return requestParam;
    }

    /**
     * @param requestParam the requestParam to set
     */
    public void setRequestParam ( String requestParam ) {
        this.requestParam = requestParam;
    }

}
