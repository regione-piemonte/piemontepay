/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.domain;



import javax.persistence.*;

/**
 * Persistent class for "EpayDEsitoChiamataEsterna" entity stored in table "epay_d_esito_chiamata_esterna" <br>
 * This class is a "record entity"  <br>
 *
 * @author fabio.fenoglio
 *
 */
@Entity
@Table(name="epay_d_esito_chiamata_esterna", schema="epay" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="EpayDEsitoChiamataEsterna.countAll",  query="SELECT COUNT(x) FROM EpayDEsitoChiamataEsterna x" ),
  @NamedQuery ( name="EpayDEsitoChiamataEsterna.countById", query="SELECT COUNT(x) FROM EpayDEsitoChiamataEsterna x WHERE x.codice = ?1 " )
} )
public class EpayDEsitoChiamataEsterna implements IEntity<String> {

    private static final long serialVersionUID = 1L;

	public static EpayDEsitoChiamataEsterna reference ( String codice ) {
        EpayDEsitoChiamataEsterna reference = new EpayDEsitoChiamataEsterna ();
        reference.setCodice( codice );
        return reference;
    }
    
    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @Column(name="codice", nullable=false, length=3)
    private String codice ;



    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="descrizione", length=50)
    private String descrizione ;


    //----------------------------------------------------------------------
    // ENTITY RELATIONSHIPS 
    //----------------------------------------------------------------------    

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public EpayDEsitoChiamataEsterna() {
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
        sb.append(descrizione);
        return sb.toString(); 
    } 

}
