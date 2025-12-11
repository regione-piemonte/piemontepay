/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.domain;




import javax.persistence.*;

/**
 * Persistent class for "EpayRChiamanteAutorizzazioneChiamante" entity stored in table "epay_r_chiamante_autorizzazione_chiamante" <br>
 * This class is a "record entity"  <br>
 *
 * @author fabio.fenoglio
 *
 */
@Entity
@Table(name="epay_r_chiamante_autorizzazione_chiamante", schema="epay" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="EpayRChiamanteAutorizzazioneChiamante.countAll",  query="SELECT COUNT(x) FROM EpayRChiamanteAutorizzazioneChiamante x" ),
  @NamedQuery ( name="EpayRChiamanteAutorizzazioneChiamante.countById", query="SELECT COUNT(x) FROM EpayRChiamanteAutorizzazioneChiamante x WHERE x.compositePrimaryKey.codiceChiamante = ?1  AND x.compositePrimaryKey.codiceAutorizzazioneChiamante = ?2 " )
} )
public class EpayRChiamanteAutorizzazioneChiamante implements IEntity<EpayRChiamanteAutorizzazioneChiamanteKey> {

    private static final long serialVersionUID = 1L;

	public static EpayRChiamanteAutorizzazioneChiamante reference ( String codiceChiamante,  String codiceAutorizzazioneChiamante ) {
        EpayRChiamanteAutorizzazioneChiamante reference = new EpayRChiamanteAutorizzazioneChiamante ();
		reference.setCodiceChiamante( codiceChiamante );
		reference.setCodiceAutorizzazioneChiamante( codiceAutorizzazioneChiamante );
        return reference;
    }
    
    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( EMBEDDED IN AN EXTERNAL CLASS )  
    //----------------------------------------------------------------------
	@EmbeddedId
    private EpayRChiamanteAutorizzazioneChiamanteKey compositePrimaryKey;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    

    //----------------------------------------------------------------------
    // ENTITY RELATIONSHIPS 
    //----------------------------------------------------------------------    

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public EpayRChiamanteAutorizzazioneChiamante() {
		super();
		this.compositePrimaryKey = new EpayRChiamanteAutorizzazioneChiamanteKey();       
    }

	@Override
	public EpayRChiamanteAutorizzazioneChiamanteKey getPrimaryKey() {
		return this.compositePrimaryKey;
	}

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE COMPOSITE KEY 
    //----------------------------------------------------------------------
    public void setCodiceChiamante( String codiceChiamante ) {
        this.compositePrimaryKey.setCodiceChiamante( codiceChiamante ) ;
    }
    public String getCodiceChiamante() {
        return this.compositePrimaryKey.getCodiceChiamante() ;
    }
    public void setCodiceAutorizzazioneChiamante( String codiceAutorizzazioneChiamante ) {
        this.compositePrimaryKey.setCodiceAutorizzazioneChiamante( codiceAutorizzazioneChiamante ) ;
    }
    public String getCodiceAutorizzazioneChiamante() {
        return this.compositePrimaryKey.getCodiceAutorizzazioneChiamante() ;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------

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
        return sb.toString(); 
    } 

}
