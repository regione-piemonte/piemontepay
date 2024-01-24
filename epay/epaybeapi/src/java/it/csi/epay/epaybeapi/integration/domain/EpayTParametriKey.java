/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.domain;
import java.io.Serializable;

import javax.persistence.*;

/**
 * Composite primary key for entity "EpayTParametri" ( stored in table "epay_t_parametri" )
 *
 * @author EII
 *
 */
 @Embeddable
public class EpayTParametriKey implements Serializable {
    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY KEY ATTRIBUTES 
    //----------------------------------------------------------------------
    @Column(name="gruppo", nullable=false, length=100)
    private String     gruppo       ;
    
    @Column(name="codice", nullable=false, length=100)
    private String     codice       ;
    

    //----------------------------------------------------------------------
    // CONSTRUCTORS
    //----------------------------------------------------------------------
    public EpayTParametriKey() {
        super();
    }

    public EpayTParametriKey( String gruppo, String codice ) {
        super();
        this.gruppo = gruppo ;
        this.codice = codice ;
    }
    
    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR KEY FIELDS
    //----------------------------------------------------------------------
    public void setGruppo( String value ) {
        this.gruppo = value;
    }
    public String getGruppo() {
        return this.gruppo;
    }

    public void setCodice( String value ) {
        this.codice = value;
    }
    public String getCodice() {
        return this.codice;
    }


    //----------------------------------------------------------------------
    // equals METHOD
    //----------------------------------------------------------------------
	public boolean equals(Object obj) { 
		if ( this == obj ) return true ; 
		if ( obj == null ) return false ;
		if ( this.getClass() != obj.getClass() ) return false ; 
		EpayTParametriKey other = (EpayTParametriKey) obj; 
		//--- Attribute gruppo
		if ( gruppo == null ) { 
			if ( other.gruppo != null ) 
				return false ; 
		} else if ( ! gruppo.equals(other.gruppo) ) 
			return false ; 
		//--- Attribute codice
		if ( codice == null ) { 
			if ( other.codice != null ) 
				return false ; 
		} else if ( ! codice.equals(other.codice) ) 
			return false ; 
		return true; 
	} 


    //----------------------------------------------------------------------
    // hashCode METHOD
    //----------------------------------------------------------------------
	public int hashCode() { 
		final int prime = 31; 
		int result = 1; 
		
		//--- Attribute gruppo
		result = prime * result + ((gruppo == null) ? 0 : gruppo.hashCode() ) ; 
		//--- Attribute codice
		result = prime * result + ((codice == null) ? 0 : codice.hashCode() ) ; 
		
		return result; 
	} 


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() {
		StringBuffer sb = new StringBuffer(); 
		sb.append(gruppo); 
		sb.append("|"); 
		sb.append(codice); 
        return sb.toString();
    }
}
