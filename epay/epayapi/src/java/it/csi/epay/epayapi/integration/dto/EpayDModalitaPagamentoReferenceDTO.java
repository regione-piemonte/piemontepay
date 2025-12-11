/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.dto;

import java.io.Serializable;
import javax.validation.constraints.*;

/**
 * Reference DTO for entity "epay_d_modalita_pagamento" <br>
 * Contains only "wrapper types" (no primitive types) <br>
 * Can be used both as a "web form" and "persistence record" <br>
 * 
 * @author fabio.fenoglio
 *
 */
public class EpayDModalitaPagamentoReferenceDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Integer idModalitaPagamento ; // Integer // Id or Primary Key

    @NotNull
    @Size( min = 1, max = 200 )
    private String descrizione ;  // String 

    /**
     * Default constructor
     */
    public EpayDModalitaPagamentoReferenceDTO() {
        super();
    }
    
    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR ID OR PRIMARY KEY 
    //----------------------------------------------------------------------
    /**
     * Set the "idModalitaPagamento" field value
     * This field is mapped on the database column "id_modalita_pagamento" ( type "int4", NotNull : true ) 
     * @param idModalitaPagamento
     */
	public void setIdModalitaPagamento( Integer idModalitaPagamento ) {
        this.idModalitaPagamento = idModalitaPagamento ;
    }
    /**
     * Get the "idModalitaPagamento" field value
     * This field is mapped on the database column "id_modalita_pagamento" ( type "int4", NotNull : true ) 
     * @return the field value
     */
	public Integer getIdModalitaPagamento() {
        return this.idModalitaPagamento;
    }

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR OTHER DATA FIELDS 
    //----------------------------------------------------------------------

    /**
     * Set the "descrizione" field value
     * This field is mapped on the database column "descrizione" ( type "varchar", NotNull : true ) 
     * @param descrizione
     */
    public void setDescrizione( String descrizione ) {
        this.descrizione = descrizione;
    }
    /**
     * Get the "descrizione" field value
     * This field is mapped on the database column "descrizione" ( type "varchar", NotNull : true ) 
     * @return the field value
     */
    public String getDescrizione() {
        return this.descrizione;
    }

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    @Override
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(idModalitaPagamento);
        sb.append("|");
        sb.append(descrizione);
        return sb.toString(); 
    } 



}
