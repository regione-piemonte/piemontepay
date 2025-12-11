/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.dto;

import java.io.Serializable;
import javax.validation.constraints.*;

/**
 * Reference DTO for entity "epay_r_chiamante_autorizzazione_chiamante" <br>
 * Contains only "wrapper types" (no primitive types) <br>
 * Can be used both as a "web form" and "persistence record" <br>
 * 
 * @author fabio.fenoglio
 *
 */
public class EpayRChiamanteAutorizzazioneChiamanteReferenceDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size( min = 1, max = 100 )
    private String codiceChiamante ; // String // Id or Primary Key
    @NotNull
    @Size( min = 1, max = 100 )
    private String codiceAutorizzazioneChiamante ; // String // Id or Primary Key


    /**
     * Default constructor
     */
    public EpayRChiamanteAutorizzazioneChiamanteReferenceDTO() {
        super();
    }
    
    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR ID OR PRIMARY KEY 
    //----------------------------------------------------------------------
    /**
     * Set the "codiceChiamante" field value
     * This field is mapped on the database column "codice_chiamante" ( type "varchar", NotNull : true ) 
     * @param codiceChiamante
     */
	public void setCodiceChiamante( String codiceChiamante ) {
        this.codiceChiamante = codiceChiamante ;
    }
    /**
     * Get the "codiceChiamante" field value
     * This field is mapped on the database column "codice_chiamante" ( type "varchar", NotNull : true ) 
     * @return the field value
     */
	public String getCodiceChiamante() {
        return this.codiceChiamante;
    }
    /**
     * Set the "codiceAutorizzazioneChiamante" field value
     * This field is mapped on the database column "codice_autorizzazione_chiamante" ( type "varchar", NotNull : true ) 
     * @param codiceAutorizzazioneChiamante
     */
	public void setCodiceAutorizzazioneChiamante( String codiceAutorizzazioneChiamante ) {
        this.codiceAutorizzazioneChiamante = codiceAutorizzazioneChiamante ;
    }
    /**
     * Get the "codiceAutorizzazioneChiamante" field value
     * This field is mapped on the database column "codice_autorizzazione_chiamante" ( type "varchar", NotNull : true ) 
     * @return the field value
     */
	public String getCodiceAutorizzazioneChiamante() {
        return this.codiceAutorizzazioneChiamante;
    }

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR OTHER DATA FIELDS 
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    @Override
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(codiceChiamante);
        sb.append("|");
        sb.append(codiceAutorizzazioneChiamante);
        return sb.toString(); 
    } 



}
