/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.model;

import java.io.Serializable;


/**
 *
 */

public class DatiPDFReport implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long id;

    private String codice;

    private String nomeTemplate;

    private byte [] template;

    private byte [] templateCompilato;

    /**
     * @return the id
     */
    public Long getId () {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId ( Long id ) {
        this.id = id;
    }

    /**
     * @return the codice
     */
    public String getCodice () {
        return codice;
    }

    /**
     * @param codice the codice to set
     */
    public void setCodice ( String codice ) {
        this.codice = codice;
    }

    /**
     * @return the nomeTemplate
     */
    public String getNomeTemplate () {
        return nomeTemplate;
    }

    /**
     * @param nomeTemplate the nomeTemplate to set
     */
    public void setNomeTemplate ( String nomeTemplate ) {
        this.nomeTemplate = nomeTemplate;
    }

    /**
     * @return the template
     */
    public byte [] getTemplate () {
        return template;
    }

    /**
     * @param template the template to set
     */
    public void setTemplate ( byte [] template ) {
        this.template = template;
    }

    /**
     * @return the templateCompilato
     */
    public byte [] getTemplateCompilato () {
        return templateCompilato;
    }

    /**
     * @param templateCompilato the templateCompilato to set
     */
    public void setTemplateCompilato ( byte [] templateCompilato ) {
        this.templateCompilato = templateCompilato;
    }

}
