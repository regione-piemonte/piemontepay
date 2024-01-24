/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.domain;




import javax.persistence.*;

/**
 * Persistent class for "EpayTParametri" entity stored in table "epay_t_parametri" <br>
 * This class is a "record entity"  <br>
 *
 * @author EII
 *
 */
@Entity
@Table(name="epay_t_parametri", schema="epay" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="EpayTParametri.countAll",  query="SELECT COUNT(x) FROM EpayTParametri x" ),
  @NamedQuery ( name="EpayTParametri.countById", query="SELECT COUNT(x) FROM EpayTParametri x WHERE x.compositePrimaryKey.gruppo = ?1  AND x.compositePrimaryKey.codice = ?2 " )
} )
public class EpayTParametri implements IEntity<EpayTParametriKey> {

    private static final long serialVersionUID = 1L;

	public static EpayTParametri reference ( String gruppo,  String codice ) {
        EpayTParametri reference = new EpayTParametri ();
		reference.setGruppo( gruppo );
		reference.setCodice( codice );
        return reference;
    }
    
    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( EMBEDDED IN AN EXTERNAL CLASS )  
    //----------------------------------------------------------------------
	@EmbeddedId
    private EpayTParametriKey compositePrimaryKey;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="valore", nullable=false, length=1000)
    private String valore ;

    @Column(name="descrizione", length=2000)
    private String descrizione ;

    @Column(name="progressivo")
    private Integer progressivo ;


    //----------------------------------------------------------------------
    // ENTITY RELATIONSHIPS 
    //----------------------------------------------------------------------    

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public EpayTParametri() {
		super();
		this.compositePrimaryKey = new EpayTParametriKey();       
    }

	@Override
	public EpayTParametriKey getPrimaryKey() {
		return this.compositePrimaryKey;
	}

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE COMPOSITE KEY 
    //----------------------------------------------------------------------
    public void setGruppo( String gruppo ) {
        this.compositePrimaryKey.setGruppo( gruppo ) ;
    }
    public String getGruppo() {
        return this.compositePrimaryKey.getGruppo() ;
    }
    public void setCodice( String codice ) {
        this.compositePrimaryKey.setCodice( codice ) ;
    }
    public String getCodice() {
        return this.compositePrimaryKey.getCodice() ;
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

    //--- DATABASE MAPPING : progressivo ( int4 ) 
    public void setProgressivo( Integer progressivo ) {
        this.progressivo = progressivo;
    }
    public Integer getProgressivo() {
        return this.progressivo;
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
        if ( compositePrimaryKey != null ) {  
            sb.append(compositePrimaryKey.toString());  
        }  
        else {  
            sb.append( "(null-key)" ); 
        }  
        sb.append("]:"); 
        sb.append(valore);
        sb.append("|");
        sb.append(descrizione);
        sb.append("|");
        sb.append(progressivo);
        return sb.toString(); 
    } 

}
