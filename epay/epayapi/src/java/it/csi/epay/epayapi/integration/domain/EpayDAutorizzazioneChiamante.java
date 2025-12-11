/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * Persistent class for "EpayDAutorizzazioneChiamante" entity stored in table "epay_d_autorizzazione_chiamante" <br>
 * This class is a "record entity" <br>
 *
 * @author fabio.fenoglio
 *
 */
@Entity
@Table ( name = "epay_d_autorizzazione_chiamante", schema = "epay" )
// Define named queries here
@NamedQueries ( {
    @NamedQuery ( name = "EpayDAutorizzazioneChiamante.countAll", query = "SELECT COUNT(x) FROM EpayDAutorizzazioneChiamante x" ),
    @NamedQuery ( name = "EpayDAutorizzazioneChiamante.countById", query = "SELECT COUNT(x) FROM EpayDAutorizzazioneChiamante x WHERE x.codice = ?1 " )
} )
public class EpayDAutorizzazioneChiamante implements IEntity<String> {

    private static final long serialVersionUID = 1L;

    public static EpayDAutorizzazioneChiamante reference ( String codice ) {
        EpayDAutorizzazioneChiamante reference = new EpayDAutorizzazioneChiamante ();
        reference.setCodice ( codice );
        return reference;
    }

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @Column ( name = "codice", nullable = false, length = 100 )
    private String codice;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS
    //----------------------------------------------------------------------
    @Column ( name = "descrizione", length = 255 )
    private String descrizione;

    @Column ( name = "assegnazione_automatica", length = 1 )
    private String assegnazioneAutomatica;

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public EpayDAutorizzazioneChiamante () {
        super ();
    }

    @Override
    public String getPrimaryKey () {
        return getCodice ();
    }

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setCodice ( String codice ) {
        this.codice = codice;
    }

    public String getCodice () {
        return codice;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : descrizione ( varchar )
    public void setDescrizione ( String descrizione ) {
        this.descrizione = descrizione;
    }

    public String getDescrizione () {
        return descrizione;
    }

    public String getAssegnazioneAutomatica () {
        return assegnazioneAutomatica;
    }

    public void setAssegnazioneAutomatica ( String assegnazioneAutomatica ) {
        this.assegnazioneAutomatica = assegnazioneAutomatica;
    }

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    @Override
    public String toString () {
        StringBuffer sb = new StringBuffer ();
        sb.append ( "[" );
        sb.append ( codice );
        sb.append ( "]:" );
        sb.append ( descrizione );
        return sb.toString ();
    }

}
