/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.domain;



import javax.persistence.*;

/**
 * Persistent class for "EpayDMessaggio" entity stored in table "epay_d_messaggio" <br>
 * This class is a "record entity"  <br>
 *
 * @author fabio.fenoglio
 *
 */
@Entity
@Table(name="epay_d_messaggio", schema="epay" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="EpayDMessaggio.countAll",  query="SELECT COUNT(x) FROM EpayDMessaggio x" ),
  @NamedQuery ( name="EpayDMessaggio.countById", query="SELECT COUNT(x) FROM EpayDMessaggio x WHERE x.codice = ?1 " )
} )
public class EpayDMessaggio implements IEntity<String> {

    private static final long serialVersionUID = 1L;

	public static EpayDMessaggio reference ( String codice ) {
        EpayDMessaggio reference = new EpayDMessaggio ();
        reference.setCodice( codice );
        return reference;
    }
    
    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @Column(name="codice", nullable=false, length=100)
    private String codice ;



    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="valore", length=1000)
    private String valore ;

    @Column(name="descrizione", length=255)
    private String descrizione ;


    //----------------------------------------------------------------------
    // ENTITY RELATIONSHIPS 
    //----------------------------------------------------------------------    

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public EpayDMessaggio() {
		super();
    }

	@Override
	public String getPrimaryKey() {
		return getCodice();
	}

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setCodice( String codice ) {
        this.codice = codice ;
    }
    public String getCodice() {
        return this.codice;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : valore ( varchar ) 
    public void setValore( String valore ) {
        this.valore = valore;
    }
    public String getValore() {
        return this.valore;
    }

    //--- DATABASE MAPPING : descrizione ( varchar ) 
    public void setDescrizione( String descrizione ) {
        this.descrizione = descrizione;
    }
    public String getDescrizione() {
        return this.descrizione;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR RELATIONSHIPS
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(codice);
        sb.append("]:"); 
        sb.append(valore);
        sb.append("|");
        sb.append(descrizione);
        return sb.toString(); 
    } 

}
