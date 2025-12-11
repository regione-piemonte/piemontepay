/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.domain;




import javax.persistence.*;

/**
 * Persistent class for "EpayRPagamentoRegistroElaborazioni" entity stored in table "epay_r_pagamento_registro_elaborazioni" <br>
 * This class is a "record entity"  <br>
 *
 * @author fabio.fenoglio
 *
 */
@Entity
@Table(name="epay_r_pagamento_registro_elaborazioni", schema="epay" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="EpayRPagamentoRegistroElaborazioni.countAll",  query="SELECT COUNT(x) FROM EpayRPagamentoRegistroElaborazioni x" ),
  @NamedQuery ( name="EpayRPagamentoRegistroElaborazioni.countById", query="SELECT COUNT(x) FROM EpayRPagamentoRegistroElaborazioni x WHERE x.compositePrimaryKey.idPagamento = ?1  AND x.compositePrimaryKey.idRegistroElaborazioni = ?2 " )
} )
public class EpayRPagamentoRegistroElaborazioni implements IEntity<EpayRPagamentoRegistroElaborazioniKey> {

    private static final long serialVersionUID = 1L;

	public static EpayRPagamentoRegistroElaborazioni reference ( Long idPagamento,  Long idRegistroElaborazioni ) {
        EpayRPagamentoRegistroElaborazioni reference = new EpayRPagamentoRegistroElaborazioni ();
		reference.setIdPagamento( idPagamento );
		reference.setIdRegistroElaborazioni( idRegistroElaborazioni );
        return reference;
    }
    
    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( EMBEDDED IN AN EXTERNAL CLASS )  
    //----------------------------------------------------------------------
	@EmbeddedId
    private EpayRPagamentoRegistroElaborazioniKey compositePrimaryKey;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    

    //----------------------------------------------------------------------
    // ENTITY RELATIONSHIPS 
    //----------------------------------------------------------------------    

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public EpayRPagamentoRegistroElaborazioni() {
		super();
		this.compositePrimaryKey = new EpayRPagamentoRegistroElaborazioniKey();       
    }

	@Override
	public EpayRPagamentoRegistroElaborazioniKey getPrimaryKey() {
		return this.compositePrimaryKey;
	}

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE COMPOSITE KEY 
    //----------------------------------------------------------------------
    public void setIdPagamento( Long idPagamento ) {
        this.compositePrimaryKey.setIdPagamento( idPagamento ) ;
    }
    public Long getIdPagamento() {
        return this.compositePrimaryKey.getIdPagamento() ;
    }
    public void setIdRegistroElaborazioni( Long idRegistroElaborazioni ) {
        this.compositePrimaryKey.setIdRegistroElaborazioni( idRegistroElaborazioni ) ;
    }
    public Long getIdRegistroElaborazioni() {
        return this.compositePrimaryKey.getIdRegistroElaborazioni() ;
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
