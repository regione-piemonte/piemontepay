/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.domain;
import java.io.Serializable;

import javax.persistence.*;

/**
 * Composite primary key for entity "EpayRPagamentoRegistroElaborazioni" ( stored in table "epay_r_pagamento_registro_elaborazioni" )
 *
 * @author EII
 *
 */
 @Embeddable
public class EpayRPagamentoRegistroElaborazioniKey implements Serializable {
    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY KEY ATTRIBUTES 
    //----------------------------------------------------------------------
    @Column(name="id_pagamento", nullable=false)
    private Long       idPagamento  ;
    
    @Column(name="id_registro_elaborazioni", nullable=false)
    private Long       idRegistroElaborazioni ;
    

    //----------------------------------------------------------------------
    // CONSTRUCTORS
    //----------------------------------------------------------------------
    public EpayRPagamentoRegistroElaborazioniKey() {
        super();
    }

    public EpayRPagamentoRegistroElaborazioniKey( Long idPagamento, Long idRegistroElaborazioni ) {
        super();
        this.idPagamento = idPagamento ;
        this.idRegistroElaborazioni = idRegistroElaborazioni ;
    }
    
    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR KEY FIELDS
    //----------------------------------------------------------------------
    public void setIdPagamento( Long value ) {
        this.idPagamento = value;
    }
    public Long getIdPagamento() {
        return this.idPagamento;
    }

    public void setIdRegistroElaborazioni( Long value ) {
        this.idRegistroElaborazioni = value;
    }
    public Long getIdRegistroElaborazioni() {
        return this.idRegistroElaborazioni;
    }


    //----------------------------------------------------------------------
    // equals METHOD
    //----------------------------------------------------------------------
	public boolean equals(Object obj) { 
		if ( this == obj ) return true ; 
		if ( obj == null ) return false ;
		if ( this.getClass() != obj.getClass() ) return false ; 
		EpayRPagamentoRegistroElaborazioniKey other = (EpayRPagamentoRegistroElaborazioniKey) obj; 
		//--- Attribute idPagamento
		if ( idPagamento == null ) { 
			if ( other.idPagamento != null ) 
				return false ; 
		} else if ( ! idPagamento.equals(other.idPagamento) ) 
			return false ; 
		//--- Attribute idRegistroElaborazioni
		if ( idRegistroElaborazioni == null ) { 
			if ( other.idRegistroElaborazioni != null ) 
				return false ; 
		} else if ( ! idRegistroElaborazioni.equals(other.idRegistroElaborazioni) ) 
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
		result = prime * result + ((idPagamento == null) ? 0 : idPagamento.hashCode() ) ; 
		//--- Attribute idRegistroElaborazioni
		result = prime * result + ((idRegistroElaborazioni == null) ? 0 : idRegistroElaborazioni.hashCode() ) ; 
		
		return result; 
	} 


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() {
		StringBuffer sb = new StringBuffer(); 
		sb.append(idPagamento); 
		sb.append("|"); 
		sb.append(idRegistroElaborazioni); 
        return sb.toString();
    }
}
