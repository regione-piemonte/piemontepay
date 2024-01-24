/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.domain;




import javax.persistence.*;

/**
 * Persistent class for "EpayRTipoPagamentoCollegato" entity stored in table "epay_r_tipo_pagamento_collegato" <br>
 * This class is a "record entity"  <br>
 *
 * @author fabio.fenoglio
 *
 */
@Entity
@Table(name="epay_r_tipo_pagamento_collegato", schema="epay" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="EpayRTipoPagamentoCollegato.countAll",  query="SELECT COUNT(x) FROM EpayRTipoPagamentoCollegato x" ),
  @NamedQuery ( name="EpayRTipoPagamentoCollegato.countById", query="SELECT COUNT(x) FROM EpayRTipoPagamentoCollegato x WHERE x.compositePrimaryKey.idTipoPagamentoPrincipale = ?1  AND x.compositePrimaryKey.idTipoPagamentoSecondario = ?2 " )
} )
public class EpayRTipoPagamentoCollegato implements IEntity<EpayRTipoPagamentoCollegatoKey> {

    private static final long serialVersionUID = 1L;

	public static EpayRTipoPagamentoCollegato reference ( Long idTipoPagamentoPrincipale,  Long idTipoPagamentoSecondario ) {
        EpayRTipoPagamentoCollegato reference = new EpayRTipoPagamentoCollegato ();
		reference.setIdTipoPagamentoPrincipale(idTipoPagamentoPrincipale);
		reference.setIdTipoPagamentoSecondario(idTipoPagamentoSecondario);
        return reference;
    }
    
    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( EMBEDDED IN AN EXTERNAL CLASS )  
    //----------------------------------------------------------------------
	@EmbeddedId
    private EpayRTipoPagamentoCollegatoKey compositePrimaryKey;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    

    //----------------------------------------------------------------------
    // ENTITY RELATIONSHIPS 
    //----------------------------------------------------------------------    

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public EpayRTipoPagamentoCollegato() {
		super();
		this.compositePrimaryKey = new EpayRTipoPagamentoCollegatoKey();       
    }

	@Override
	public EpayRTipoPagamentoCollegatoKey getPrimaryKey() {
		return this.compositePrimaryKey;
	}

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE COMPOSITE KEY 
    //----------------------------------------------------------------------
    public void setIdTipoPagamentoPrincipale( Long idTipoPagamentoPrincipale ) {
        this.compositePrimaryKey.setIdTipoPagamentoPrincipale( idTipoPagamentoPrincipale ) ;
    }
    public Long getIdTipoPagamentoPrincipale() {
        return this.compositePrimaryKey.getIdTipoPagamentoPrincipale() ;
    }
    public void setIdTipoPagamentoSecondario( Long idTipoPagamentoSecondario ) {
        this.compositePrimaryKey.setIdTipoPagamentoSecondario( idTipoPagamentoSecondario ) ;
    }
    public Long getIdTipoPagamentoSecondario() {
        return this.compositePrimaryKey.getIdTipoPagamentoSecondario() ;
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
