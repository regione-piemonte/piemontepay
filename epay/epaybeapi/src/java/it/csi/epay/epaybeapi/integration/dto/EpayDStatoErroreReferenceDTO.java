/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.dto;

import java.io.Serializable;
import javax.validation.constraints.*;

/**
 * Reference DTO for entity "epay_d_stato_errore" <br>
 * Contains only "wrapper types" (no primitive types) <br>
 * Can be used both as a "web form" and "persistence record" <br>
 * 
 * @author EII
 *
 */
public class EpayDStatoErroreReferenceDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Integer idStato ; // Integer // Id or Primary Key

    @Size( max = 200 )
    private String descrizione ;  // String 

    /**
     * Default constructor
     */
    public EpayDStatoErroreReferenceDTO() {
        super();
    }
    
    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR ID OR PRIMARY KEY 
    //----------------------------------------------------------------------
    /**
     * Set the "idStato" field value
     * This field is mapped on the database column "id_stato" ( type "int4", NotNull : true ) 
     * @param idStato
     */
	public void setIdStato( Integer idStato ) {
        this.idStato = idStato ;
    }
    /**
     * Get the "idStato" field value
     * This field is mapped on the database column "id_stato" ( type "int4", NotNull : true ) 
     * @return the field value
     */
	public Integer getIdStato() {
        return this.idStato;
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
        sb.append(idStato);
        sb.append("|");
        sb.append(descrizione);
        return sb.toString(); 
    } 



}
