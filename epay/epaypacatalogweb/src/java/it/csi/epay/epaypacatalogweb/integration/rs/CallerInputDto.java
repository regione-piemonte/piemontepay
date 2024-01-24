/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.integration.rs;

import org.codehaus.jackson.annotate.JsonProperty;

public class CallerInputDto {

	@JsonProperty("codiceApplicativo")
    protected String codiceApplicativo;
	
	@JsonProperty("ip")
    protected String ip;
	
	@JsonProperty("principal")
    protected PrincipalInputDto principal;

    /**
     * Recupera il valore della propriet codiceApplicativo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceApplicativo() {
        return codiceApplicativo;
    }

    /**
     * Imposta il valore della propriet codiceApplicativo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceApplicativo(String value) {
        this.codiceApplicativo = value;
    }

    /**
     * Recupera il valore della propriet ip.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIp() {
        return ip;
    }

    /**
     * Imposta il valore della propriet ip.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIp(String value) {
        this.ip = value;
    }

    /**
     * Recupera il valore della propriet principal.
     * 
     * @return
     *     possible object is
     *     {@link PrincipalInputDto }
     *     
     */
    public PrincipalInputDto getPrincipal() {
        return principal;
    }

    /**
     * Imposta il valore della propriet principal.
     * 
     * @param value
     *     allowed object is
     *     {@link PrincipalInputDto }
     *     
     */
    public void setPrincipal(PrincipalInputDto value) {
        this.principal = value;
    }

}
