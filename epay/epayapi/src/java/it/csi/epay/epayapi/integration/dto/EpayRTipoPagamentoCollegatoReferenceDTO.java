/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.dto;

import java.io.Serializable;

/**
 * Reference DTO for entity "epay_r_tipo_pagamento_collegato" <br>
 * Contains only "wrapper types" (no primitive types) <br>
 * Can be used both as a "web form" and "persistence record" <br>
 * 
 * @author fabio.fenoglio
 *
 */
public class EpayRTipoPagamentoCollegatoReferenceDTO implements Serializable {

    private static final long serialVersionUID = 1L;

   
    private Long idTipoPagamentoPrincipale ; // Id or Primary Key
    
    private Long idTipoPagamentoSecondario ; // Id or Primary Key



    /**
     * Default constructor
     */
    public EpayRTipoPagamentoCollegatoReferenceDTO() {
        super();
    }
    
    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR ID OR PRIMARY KEY 
    //----------------------------------------------------------------------
    /**
     * Set the "idTipoPagamentoPrincipale" field value
     * This field is mapped on the database column "id_tipo_pagamento_principale" ( type "int8", NotNull : false ) 
     * @param idPagamento
     */
    public void setIdTipoPagamentoPrincipale(Long idTipoPagamentoPrincipale) {
		this.idTipoPagamentoPrincipale = idTipoPagamentoPrincipale;
	}
    
    /**
     * Get the "idTipoPagamentoPrincipale" field value
     * This field is mapped on the database column "id_tipo_pagamento_principale" ( type "int8", NotNull : false ) 
     * @return the field value
     */
	
    
    public Long getIdTipoPagamentoPrincipale() {
		return idTipoPagamentoPrincipale;
	}
    
    /**
     * Set the "idTipoPagamentoSecondario" field value
     * This field is mapped on the database column "id_tipo_pagamento_secondario" ( type "int8", NotNull : false ) 
     * @param idPagamento
     */
    public void setIdTipoPagamentoSecondario(Long idTipoPagamentoSecondario) {
		this.idTipoPagamentoSecondario = idTipoPagamentoSecondario;
	}
    
    /**
     * Get the "idTipoPagamentoSecondario" field value
     * This field is mapped on the database column "id_tipo_pagamento_secondario" ( type "int8", NotNull : false ) 
     * @return the field value
     */
	
    
    public Long getIdTipoPagamentoSecondario() {
		return idTipoPagamentoSecondario;
	}
    
	

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR OTHER DATA FIELDS 
    //----------------------------------------------------------------------
    

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR RELATIONSHIPS
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    @Override
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(idTipoPagamentoPrincipale);
        sb.append("|");
        sb.append(idTipoPagamentoSecondario);
        return sb.toString(); 
    } 



}
