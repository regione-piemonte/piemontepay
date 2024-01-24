/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.domain;
import java.io.Serializable;

import javax.persistence.*;

/**
 * Composite primary key for entity "EpayRTipoPagamentoCollegato" ( stored in table "epay_r_tipo_pagamento_collegato" )
 *
 * @author fabio.fenoglio
 *
 */
 @Embeddable
public class EpayRTipoPagamentoCollegatoKey implements Serializable {
    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY KEY ATTRIBUTES 
    //----------------------------------------------------------------------
    @Column(name="id_tipo_pagamento_principale", nullable=false)
    private Long       idTipoPagamentoPrincipale  ;
    
    @Column(name="id_tipo_pagamento_secondario", nullable=false)
    private Long       idTipoPagamentoSecondario ;
    

    //----------------------------------------------------------------------
    // CONSTRUCTORS
    //----------------------------------------------------------------------
    public EpayRTipoPagamentoCollegatoKey() {
        super();
    }

    public EpayRTipoPagamentoCollegatoKey( Long idTipoPagamentoPrincipale, Long idTipoPagamentoSecondario ) {
        super();
        this.idTipoPagamentoPrincipale = idTipoPagamentoPrincipale ;
        this.idTipoPagamentoSecondario = idTipoPagamentoSecondario ;
    }
    
    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR KEY FIELDS
    //----------------------------------------------------------------------
    
   	public Long getIdTipoPagamentoPrincipale() {
   		return idTipoPagamentoPrincipale;
   	}

   	public void setIdTipoPagamentoPrincipale(Long idTipoPagamentoPrincipale) {
   		this.idTipoPagamentoPrincipale = idTipoPagamentoPrincipale;
   	}

   	public Long getIdTipoPagamentoSecondario() {
   		return idTipoPagamentoSecondario;
   	}

   	public void setIdTipoPagamentoSecondario(Long idTipoPagamentoSecondario) {
   		this.idTipoPagamentoSecondario = idTipoPagamentoSecondario;
   	}

    //----------------------------------------------------------------------
    // equals METHOD
    //----------------------------------------------------------------------
	public boolean equals(Object obj) { 
		if ( this == obj ) return true ; 
		if ( obj == null ) return false ;
		if ( this.getClass() != obj.getClass() ) return false ; 
		EpayRTipoPagamentoCollegatoKey other = (EpayRTipoPagamentoCollegatoKey) obj; 
		//--- Attribute idPagamento
		if ( idTipoPagamentoPrincipale == null ) { 
			if ( other.idTipoPagamentoPrincipale != null ) 
				return false ; 
		} else if ( ! idTipoPagamentoPrincipale.equals(other.idTipoPagamentoPrincipale) ) 
			return false ; 
		//--- Attribute idRegistroElaborazioni
		if ( idTipoPagamentoSecondario == null ) { 
			if ( other.idTipoPagamentoSecondario != null ) 
				return false ; 
		} else if ( ! idTipoPagamentoSecondario.equals(other.idTipoPagamentoSecondario) ) 
			return false ; 
		return true; 
	} 


   

	//----------------------------------------------------------------------
    // hashCode METHOD
    //----------------------------------------------------------------------
	public int hashCode() { 
		final int prime = 31; 
		int result = 1; 
		
		//--- Attribute idPagamento
		result = prime * result + ((idTipoPagamentoPrincipale == null) ? 0 : idTipoPagamentoPrincipale.hashCode() ) ; 
		//--- Attribute idRegistroElaborazioni
		result = prime * result + ((idTipoPagamentoSecondario == null) ? 0 : idTipoPagamentoSecondario.hashCode() ) ; 
		
		return result; 
	} 


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() {
		StringBuffer sb = new StringBuffer(); 
		sb.append(idTipoPagamentoPrincipale); 
		sb.append("|"); 
		sb.append(idTipoPagamentoSecondario); 
        return sb.toString();
    }
}
