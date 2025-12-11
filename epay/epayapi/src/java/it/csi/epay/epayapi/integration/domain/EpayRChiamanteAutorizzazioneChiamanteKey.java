/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.domain;
import java.io.Serializable;

import javax.persistence.*;

/**
 * Composite primary key for entity "EpayRChiamanteAutorizzazioneChiamante" ( stored in table "epay_r_chiamante_autorizzazione_chiamante" )
 *
 * @author fabio.fenoglio
 *
 */
 @Embeddable
public class EpayRChiamanteAutorizzazioneChiamanteKey implements Serializable {
    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY KEY ATTRIBUTES 
    //----------------------------------------------------------------------
    @Column(name="codice_chiamante", nullable=false, length=100)
    private String     codiceChiamante ;
    
    @Column(name="codice_autorizzazione_chiamante", nullable=false, length=100)
    private String     codiceAutorizzazioneChiamante ;
    

    //----------------------------------------------------------------------
    // CONSTRUCTORS
    //----------------------------------------------------------------------
    public EpayRChiamanteAutorizzazioneChiamanteKey() {
        super();
    }

    public EpayRChiamanteAutorizzazioneChiamanteKey( String codiceChiamante, String codiceAutorizzazioneChiamante ) {
        super();
        this.codiceChiamante = codiceChiamante ;
        this.codiceAutorizzazioneChiamante = codiceAutorizzazioneChiamante ;
    }
    
    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR KEY FIELDS
    //----------------------------------------------------------------------
    public void setCodiceChiamante( String value ) {
        this.codiceChiamante = value;
    }
    public String getCodiceChiamante() {
        return this.codiceChiamante;
    }

    public void setCodiceAutorizzazioneChiamante( String value ) {
        this.codiceAutorizzazioneChiamante = value;
    }
    public String getCodiceAutorizzazioneChiamante() {
        return this.codiceAutorizzazioneChiamante;
    }


    //----------------------------------------------------------------------
    // equals METHOD
    //----------------------------------------------------------------------
	public boolean equals(Object obj) { 
		if ( this == obj ) return true ; 
		if ( obj == null ) return false ;
		if ( this.getClass() != obj.getClass() ) return false ; 
		EpayRChiamanteAutorizzazioneChiamanteKey other = (EpayRChiamanteAutorizzazioneChiamanteKey) obj; 
		//--- Attribute codiceChiamante
		if ( codiceChiamante == null ) { 
			if ( other.codiceChiamante != null ) 
				return false ; 
		} else if ( ! codiceChiamante.equals(other.codiceChiamante) ) 
			return false ; 
		//--- Attribute codiceAutorizzazioneChiamante
		if ( codiceAutorizzazioneChiamante == null ) { 
			if ( other.codiceAutorizzazioneChiamante != null ) 
				return false ; 
		} else if ( ! codiceAutorizzazioneChiamante.equals(other.codiceAutorizzazioneChiamante) ) 
			return false ; 
		return true; 
	} 


    //----------------------------------------------------------------------
    // hashCode METHOD
    //----------------------------------------------------------------------
	public int hashCode() { 
		final int prime = 31; 
		int result = 1; 
		
		//--- Attribute codiceChiamante
		result = prime * result + ((codiceChiamante == null) ? 0 : codiceChiamante.hashCode() ) ; 
		//--- Attribute codiceAutorizzazioneChiamante
		result = prime * result + ((codiceAutorizzazioneChiamante == null) ? 0 : codiceAutorizzazioneChiamante.hashCode() ) ; 
		
		return result; 
	} 


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() {
		StringBuffer sb = new StringBuffer(); 
		sb.append(codiceChiamante); 
		sb.append("|"); 
		sb.append(codiceAutorizzazioneChiamante); 
        return sb.toString();
    }
}
