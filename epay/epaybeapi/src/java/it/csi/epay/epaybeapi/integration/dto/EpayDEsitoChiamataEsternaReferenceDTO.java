/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.dto;

import java.io.Serializable;
import javax.validation.constraints.*;

/**
 * Reference DTO for entity "epay_d_esito_chiamata_esterna" <br>
 * Contains only "wrapper types" (no primitive types) <br>
 * Can be used both as a "web form" and "persistence record" <br>
 * 
 * @author EII
 *
 */
public class EpayDEsitoChiamataEsternaReferenceDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size( min = 1, max = 3 )
    private String codice ; // String // Id or Primary Key

    @Size( max = 50 )
    private String descrizione ;  // String 

    /**
     * Default constructor
     */
    public EpayDEsitoChiamataEsternaReferenceDTO() {
        super();
    }
    
    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR ID OR PRIMARY KEY 
    //----------------------------------------------------------------------
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

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    @Override
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(codice);
        sb.append("|");
        sb.append(descrizione);
        return sb.toString(); 
    } 



}
