/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.dto;

import java.io.Serializable;
import javax.validation.constraints.*;

/**
 * Reference DTO for entity "epay_t_pdf_report" <br>
 * Contains only "wrapper types" (no primitive types) <br>
 * Can be used both as a "web form" and "persistence record" <br>
 * 
 * @author EII
 *
 */
public class EpayTPdfReportReferenceDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Long id ; // Long // Id or Primary Key

    @NotNull
    @Size( min = 1, max = 10 )
    private String codice ;  // String 
    @NotNull
    @Size( min = 1, max = 100 )
    private String nomeTemplate ;  // String 
    @NotNull
    @Size( min = 1, max = 2147483647 )
    private String template ;  // String 

    private byte[] templateCompilato ;  // byte[] 

    /**
     * Default constructor
     */
    public EpayTPdfReportReferenceDTO() {
        super();
    }
    
    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR ID OR PRIMARY KEY 
    //----------------------------------------------------------------------
    /**
     * Set the "id" field value
     * This field is mapped on the database column "id" ( type "int8", NotNull : true ) 
     * @param id
     */
	public void setId( Long id ) {
        this.id = id ;
    }
    /**
     * Get the "id" field value
     * This field is mapped on the database column "id" ( type "int8", NotNull : true ) 
     * @return the field value
     */
	public Long getId() {
        return this.id;
    }

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR OTHER DATA FIELDS 
    //----------------------------------------------------------------------

    /**
     * Set the "codice" field value
     * This field is mapped on the database column "codice" ( type "varchar", NotNull : true ) 
     * @param codice
     */
    public void setCodice( String codice ) {
        this.codice = codice;
    }
    /**
     * Get the "codice" field value
     * This field is mapped on the database column "codice" ( type "varchar", NotNull : true ) 
     * @return the field value
     */
    public String getCodice() {
        return this.codice;
    }

    /**
     * Set the "nomeTemplate" field value
     * This field is mapped on the database column "nome_template" ( type "varchar", NotNull : true ) 
     * @param nomeTemplate
     */
    public void setNomeTemplate( String nomeTemplate ) {
        this.nomeTemplate = nomeTemplate;
    }
    /**
     * Get the "nomeTemplate" field value
     * This field is mapped on the database column "nome_template" ( type "varchar", NotNull : true ) 
     * @return the field value
     */
    public String getNomeTemplate() {
        return this.nomeTemplate;
    }

    /**
     * Set the "template" field value
     * This field is mapped on the database column "template" ( type "xml", NotNull : true ) 
     * @param template
     */
    public void setTemplate( String template ) {
        this.template = template;
    }
    /**
     * Get the "template" field value
     * This field is mapped on the database column "template" ( type "xml", NotNull : true ) 
     * @return the field value
     */
    public String getTemplate() {
        return this.template;
    }

    /**
     * Set the "templateCompilato" field value
     * This field is mapped on the database column "template_compilato" ( type "bytea", NotNull : false ) 
     * @param templateCompilato
     */
    public void setTemplateCompilato( byte[] templateCompilato ) {
        this.templateCompilato = templateCompilato;
    }
    /**
     * Get the "templateCompilato" field value
     * This field is mapped on the database column "template_compilato" ( type "bytea", NotNull : false ) 
     * @return the field value
     */
    public byte[] getTemplateCompilato() {
        return this.templateCompilato;
    }

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    @Override
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
        sb.append(codice);
        sb.append("|");
        sb.append(nomeTemplate);
        sb.append("|");
        sb.append(template);
        // attribute 'templateCompilato' not usable (type = byte[])
        return sb.toString(); 
    } 



}
