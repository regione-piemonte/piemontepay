/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.dto;

import java.io.Serializable;
import javax.validation.constraints.*;

/**
 * DTO for entity "epay_d_modalita_ricezione_esito" <br>
 * Contains only "wrapper types" (no primitive types) <br>
 * Can be used both as a "web form" and "persistence record" <br>
 * 
 * @author EII
 *
 */
public class EpayDModalitaRicezioneEsitoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Integer idModalitaRicezioneEsito ; // Id or Primary Key

    @NotNull
    @Size( min = 1, max = 200 )
    private String descrizione ; 


    /**
     * Default constructor
     */
    public EpayDModalitaRicezioneEsitoDTO() {
        super();
    }
    
    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR ID OR PRIMARY KEY 
    //----------------------------------------------------------------------
    /**
     * Set the "idModalitaRicezioneEsito" field value
     * This field is mapped on the database column "id_modalita_ricezione_esito" ( type "int4", NotNull : true ) 
     * @param idModalitaRicezioneEsito
     */
	public void setIdModalitaRicezioneEsito( Integer idModalitaRicezioneEsito ) {
        this.idModalitaRicezioneEsito = idModalitaRicezioneEsito ;
    }
    /**
     * Get the "idModalitaRicezioneEsito" field value
     * This field is mapped on the database column "id_modalita_ricezione_esito" ( type "int4", NotNull : true ) 
     * @return the field value
     */
	public Integer getIdModalitaRicezioneEsito() {
        return this.idModalitaRicezioneEsito;
    }

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR OTHER DATA FIELDS 
    //----------------------------------------------------------------------
    
    /**
     * Set the "descrizione" field value
     * This field is mapped on the database column "descrizione" ( type "varchar", NotNull : true ) 
     * @param descrizione
     */
    public void setDescrizione( String descrizione ) {
        this.descrizione = descrizione;
    }
    /**
     * Get the "descrizione" field value
     * This field is mapped on the database column "descrizione" ( type "varchar", NotNull : true ) 
     * @return the field value
     */
    public String getDescrizione() {
        return this.descrizione;
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
        sb.append(idModalitaRicezioneEsito);
        sb.append("|");
        sb.append(descrizione);
        return sb.toString(); 
    } 



}
