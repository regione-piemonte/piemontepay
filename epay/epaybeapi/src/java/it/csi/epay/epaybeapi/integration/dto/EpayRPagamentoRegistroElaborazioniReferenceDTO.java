/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.dto;

import java.io.Serializable;
import javax.validation.constraints.*;

/**
 * Reference DTO for entity "epay_r_pagamento_registro_elaborazioni" <br>
 * Contains only "wrapper types" (no primitive types) <br>
 * Can be used both as a "web form" and "persistence record" <br>
 * 
 * @author EII
 *
 */
public class EpayRPagamentoRegistroElaborazioniReferenceDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Long idPagamento ; // Long // Id or Primary Key
    @NotNull
    private Long idRegistroElaborazioni ; // Long // Id or Primary Key


    /**
     * Default constructor
     */
    public EpayRPagamentoRegistroElaborazioniReferenceDTO() {
        super();
    }
    
    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR ID OR PRIMARY KEY 
    //----------------------------------------------------------------------
    /**
     * Set the "idPagamento" field value
     * This field is mapped on the database column "id_pagamento" ( type "int8", NotNull : true ) 
     * @param idPagamento
     */
	public void setIdPagamento( Long idPagamento ) {
        this.idPagamento = idPagamento ;
    }
    /**
     * Get the "idPagamento" field value
     * This field is mapped on the database column "id_pagamento" ( type "int8", NotNull : true ) 
     * @return the field value
     */
	public Long getIdPagamento() {
        return this.idPagamento;
    }
    /**
     * Set the "idRegistroElaborazioni" field value
     * This field is mapped on the database column "id_registro_elaborazioni" ( type "int8", NotNull : true ) 
     * @param idRegistroElaborazioni
     */
	public void setIdRegistroElaborazioni( Long idRegistroElaborazioni ) {
        this.idRegistroElaborazioni = idRegistroElaborazioni ;
    }
    /**
     * Get the "idRegistroElaborazioni" field value
     * This field is mapped on the database column "id_registro_elaborazioni" ( type "int8", NotNull : true ) 
     * @return the field value
     */
	public Long getIdRegistroElaborazioni() {
        return this.idRegistroElaborazioni;
    }

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR OTHER DATA FIELDS 
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    @Override
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(idPagamento);
        sb.append("|");
        sb.append(idRegistroElaborazioni);
        return sb.toString(); 
    } 



}
