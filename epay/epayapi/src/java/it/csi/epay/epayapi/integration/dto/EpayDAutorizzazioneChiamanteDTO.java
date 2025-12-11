/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * DTO for entity "epay_d_autorizzazione_chiamante" <br>
 * Contains only "wrapper types" (no primitive types) <br>
 * Can be used both as a "web form" and "persistence record" <br>
 *
 * @author fabio.fenoglio
 *
 */
public class EpayDAutorizzazioneChiamanteDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size ( min = 1, max = 100 )
    private String codice; // Id or Primary Key

    @Size ( max = 255 )
    private String descrizione;

    private Boolean assegnazioneAutomatica;

    /**
     * Default constructor
     */
    public EpayDAutorizzazioneChiamanteDTO () {
        super ();
    }

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR ID OR PRIMARY KEY
    //----------------------------------------------------------------------
    /**
     * Set the "codice" field value This field is mapped on the database column "codice" ( type "varchar", NotNull : true )
     *
     * @param codice
     */
    public void setCodice ( String codice ) {
        this.codice = codice;
    }

    /**
     * Get the "codice" field value This field is mapped on the database column "codice" ( type "varchar", NotNull : true )
     *
     * @return the field value
     */
    public String getCodice () {
        return codice;
    }

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR OTHER DATA FIELDS
    //----------------------------------------------------------------------

    /**
     * Set the "descrizione" field value This field is mapped on the database column "descrizione" ( type "varchar", NotNull : false )
     *
     * @param descrizione
     */
    public void setDescrizione ( String descrizione ) {
        this.descrizione = descrizione;
    }

    /**
     * Get the "descrizione" field value This field is mapped on the database column "descrizione" ( type "varchar", NotNull : false )
     *
     * @return the field value
     */
    public String getDescrizione () {
        return descrizione;
    }

    public Boolean getAssegnazioneAutomatica () {
        return assegnazioneAutomatica;
    }

    public void setAssegnazioneAutomatica ( Boolean assegnazioneAutomatica ) {
        this.assegnazioneAutomatica = assegnazioneAutomatica;
    }

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    @Override
    public String toString () {
        StringBuffer sb = new StringBuffer ();
        sb.append ( codice );
        sb.append ( "|" );
        sb.append ( descrizione );
        return sb.toString ();
    }

}
