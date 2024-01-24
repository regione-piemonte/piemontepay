/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import it.csi.epay.epaybeapi.integration.proto.TimeBoundedValidity;


/**
 * Persistent class for "EpayDChiamanteEsterno" entity stored in table "epay_d_chiamante_esterno" <br>
 * This class is a "record entity" <br>
 *
 * @author EII
 *
 */
@Entity
@Table ( name = "epay_d_chiamante_esterno", schema = "epay" )
// Define named queries here
@NamedQueries ( {
    @NamedQuery ( name = "EpayDChiamanteEsterno.countAll", query = "SELECT COUNT(x) FROM EpayDChiamanteEsterno x" ),
    @NamedQuery ( name = "EpayDChiamanteEsterno.countById", query = "SELECT COUNT(x) FROM EpayDChiamanteEsterno x WHERE x.codiceChiamante = ?1 " )
} )
public class EpayDChiamanteEsterno implements IEntity<String>, TimeBoundedValidity {

    private static final long serialVersionUID = 1L;

    public static EpayDChiamanteEsterno reference ( String codiceChiamante ) {
        EpayDChiamanteEsterno reference = new EpayDChiamanteEsterno ();
        reference.setCodiceChiamante ( codiceChiamante );
        return reference;
    }

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @Column ( name = "codice_chiamante", nullable = false, length = 100 )
    private String codiceChiamante;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS
    //----------------------------------------------------------------------
    @Column ( name = "descrizione_chiamante", length = 200 )
    private String descrizioneChiamante;

    @Temporal ( TemporalType.TIMESTAMP )
    @Column ( name = "data_inizio_validita", nullable = false )
    private Date dataInizioValidita;

    @Temporal ( TemporalType.TIMESTAMP )
    @Column ( name = "data_fine_validita" )
    private Date dataFineValidita;

    @Column ( name = "passphrase", nullable = false, length = 200 )
    private String passphrase;

    @Column ( name = "url", nullable = false, length = 200 )
    private String url;

    //----------------------------------------------------------------------
    // ENTITY RELATIONSHIPS
    //----------------------------------------------------------------------
    @OneToMany ( mappedBy = "epayDChiamanteEsterno", targetEntity = EpayTTracciabilitaChiamanteEsterno.class )
    private List<EpayTTracciabilitaChiamanteEsterno> listOfEpayTTracciabilitaChiamanteEsterno;

    @ManyToMany ( targetEntity = EpayDAutorizzazioneChiamante.class )
    @JoinTable ( name = "epay_r_chiamante_autorizzazione_chiamante",
                    joinColumns = @JoinColumn ( name = "codice_chiamante", referencedColumnName = "codice_chiamante" ),
                    inverseJoinColumns = @JoinColumn ( name = "codice_autorizzazione_chiamante", referencedColumnName = "codice" ) )
    private List<EpayDAutorizzazioneChiamante> autorizzazioni;

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public EpayDChiamanteEsterno () {
        super ();
    }

    @Override
    public String getPrimaryKey () {
        return getCodiceChiamante ();
    }

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setCodiceChiamante ( String codiceChiamante ) {
        this.codiceChiamante = codiceChiamante;
    }

    public String getCodiceChiamante () {
        return codiceChiamante;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : descrizione_chiamante ( varchar )
    public void setDescrizioneChiamante ( String descrizioneChiamante ) {
        this.descrizioneChiamante = descrizioneChiamante;
    }

    public String getDescrizioneChiamante () {
        return descrizioneChiamante;
    }

    //--- DATABASE MAPPING : data_inizio_validita ( timestamp )
    public void setDataInizioValidita ( Date dataInizioValidita ) {
        this.dataInizioValidita = dataInizioValidita;
    }

    @Override
    public Date getDataInizioValidita () {
        return dataInizioValidita;
    }

    //--- DATABASE MAPPING : data_fine_validita ( timestamp )
    public void setDataFineValidita ( Date dataFineValidita ) {
        this.dataFineValidita = dataFineValidita;
    }

    @Override
    public Date getDataFineValidita () {
        return dataFineValidita;
    }

    //--- DATABASE MAPPING : passphrase ( varchar )
    public void setPassphrase ( String passphrase ) {
        this.passphrase = passphrase;
    }

    public String getPassphrase () {
        return passphrase;
    }

    //--- DATABASE MAPPING : url ( varchar )
    public void setUrl ( String url ) {
        this.url = url;
    }

    public String getUrl () {
        return url;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR RELATIONSHIPS
    //----------------------------------------------------------------------
    public void setListOfEpayTTracciabilitaChiamanteEsterno ( List<EpayTTracciabilitaChiamanteEsterno> listOfEpayTTracciabilitaChiamanteEsterno ) {
        this.listOfEpayTTracciabilitaChiamanteEsterno = listOfEpayTTracciabilitaChiamanteEsterno;
    }

    public List<EpayTTracciabilitaChiamanteEsterno> getListOfEpayTTracciabilitaChiamanteEsterno () {
        return listOfEpayTTracciabilitaChiamanteEsterno;
    }

    public List<EpayDAutorizzazioneChiamante> getAutorizzazioni () {
        return autorizzazioni;
    }

    public void setAutorizzazioni ( List<EpayDAutorizzazioneChiamante> autorizzazioni ) {
        this.autorizzazioni = autorizzazioni;
    }

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    @Override
    public String toString () {
        StringBuffer sb = new StringBuffer ();
        sb.append ( "[" );
        sb.append ( codiceChiamante );
        sb.append ( "]:" );
        sb.append ( descrizioneChiamante );
        sb.append ( "|" );
        sb.append ( dataInizioValidita );
        sb.append ( "|" );
        sb.append ( dataFineValidita );
        sb.append ( "|" );
        sb.append ( passphrase );
        sb.append ( "|" );
        sb.append ( url );
        return sb.toString ();
    }

}
