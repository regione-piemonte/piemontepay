/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.dto;

import java.io.Serializable;
import javax.validation.constraints.*;

/**
 * DTO for entity "epay_t_parametri" <br>
 * Contains only "wrapper types" (no primitive types) <br>
 * Can be used both as a "web form" and "persistence record" <br>
 * 
 * @author EII
 *
 */
public class EpayTParametriDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size( min = 1, max = 100 )
    private String gruppo ; // Id or Primary Key
    @NotNull
    @Size( min = 1, max = 100 )
    private String codice ; // Id or Primary Key

    @NotNull
    @Size( min = 1, max = 1000 )
    private String valore ; 
    @Size( max = 2000 )
    private String descrizione ; 

    private Integer progressivo ; 


    /**
     * Default constructor
     */
    public EpayTParametriDTO() {
        super();
    }
    
    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR ID OR PRIMARY KEY 
    //----------------------------------------------------------------------
    /**
     * Set the "gruppo" field value
     * This field is mapped on the database column "gruppo" ( type "varchar", NotNull : true ) 
     * @param gruppo
     */
	public void setGruppo( String gruppo ) {
        this.gruppo = gruppo ;
    }
    /**
     * Get the "gruppo" field value
     * This field is mapped on the database column "gruppo" ( type "varchar", NotNull : true ) 
     * @return the field value
     */
	public String getGruppo() {
        return this.gruppo;
    }
    /**
     * Set the "codice" field value
     * This field is mapped on the database column "codice" ( type "varchar", NotNull : true ) 
     * @param codice
     */
	public void setCodice( String codice ) {
        this.codice = codice ;
    }
    /**
     * Get the "codice" field value
     * This field is mapped on the database column "codice" ( type "varchar", NotNull : true ) 
     * @return the field value
     */
	public String getCodice() {
        return this.codice;
    }

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR OTHER DATA FIELDS 
    //----------------------------------------------------------------------
    
    /**
     * Set the "valore" field value
     * This field is mapped on the database column "valore" ( type "varchar", NotNull : true ) 
     * @param valore
     */
    public void setValore( String valore ) {
        this.valore = valore;
    }
    /**
     * Get the "valore" field value
     * This field is mapped on the database column "valore" ( type "varchar", NotNull : true ) 
     * @return the field value
     */
    public String getValore() {
        return this.valore;
    }

    /**
     * Set the "descrizione" field value
     * This field is mapped on the database column "descrizione" ( type "varchar", NotNull : false ) 
     * @param descrizione
     */
    public void setDescrizione( String descrizione ) {
        this.descrizione = descrizione;
    }
    /**
     * Get the "descrizione" field value
     * This field is mapped on the database column "descrizione" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getDescrizione() {
        return this.descrizione;
    }

    /**
     * Set the "progressivo" field value
     * This field is mapped on the database column "progressivo" ( type "int4", NotNull : false ) 
     * @param progressivo
     */
    public void setProgressivo( Integer progressivo ) {
        this.progressivo = progressivo;
    }
    /**
     * Get the "progressivo" field value
     * This field is mapped on the database column "progressivo" ( type "int4", NotNull : false ) 
     * @return the field value
     */
    public Integer getProgressivo() {
        return this.progressivo;
    }


    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR RELATIONSHIPS
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    @Override
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(gruppo);
        sb.append("|");
        sb.append(codice);
        sb.append("|");
        sb.append(valore);
        sb.append("|");
        sb.append(descrizione);
        sb.append("|");
        sb.append(progressivo);
        return sb.toString(); 
    } 



}
