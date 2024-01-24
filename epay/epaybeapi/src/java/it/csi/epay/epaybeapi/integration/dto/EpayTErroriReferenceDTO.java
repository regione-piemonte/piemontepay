/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.dto;

import java.io.Serializable;
import javax.validation.constraints.*;

import java.util.Date;
/**
 * Reference DTO for entity "epay_t_errori" <br>
 * Contains only "wrapper types" (no primitive types) <br>
 * Can be used both as a "web form" and "persistence record" <br>
 * 
 * @author EII
 *
 */
public class EpayTErroriReferenceDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Long id ; // Long // Id or Primary Key

    @NotNull
    private Date data ;  // Date 
    @NotNull
    @Size( min = 1, max = 500 )
    private String descrizione ;  // String 

    private Long idPagamento ;  // Long 

    private Long idRegistroVersamento ;  // Long 
    @Size( max = 25 )
    private String iuv ;  // String 

    private Long idTransazione ;  // Long 
    @Size( max = 500 )
    private String descCorrezione ;  // String 
    @NotNull
    @Size( min = 1, max = 200 )
    private String applicativo ;  // String 

    /**
     * Default constructor
     */
    public EpayTErroriReferenceDTO() {
        super();
    }
    
    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR ID OR PRIMARY KEY 
    //----------------------------------------------------------------------
    /**
     * Set the "id" field value
     * This field is mapped on the database column "id" ( type "bigserial", NotNull : true ) 
     * @param id
     */
	public void setId( Long id ) {
        this.id = id ;
    }
    /**
     * Get the "id" field value
     * This field is mapped on the database column "id" ( type "bigserial", NotNull : true ) 
     * @return the field value
     */
	public Long getId() {
        return this.id;
    }

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR OTHER DATA FIELDS 
    //----------------------------------------------------------------------

    /**
     * Set the "data" field value
     * This field is mapped on the database column "data" ( type "timestamp", NotNull : true ) 
     * @param data
     */
    public void setData( Date data ) {
        this.data = data;
    }
    /**
     * Get the "data" field value
     * This field is mapped on the database column "data" ( type "timestamp", NotNull : true ) 
     * @return the field value
     */
    public Date getData() {
        return this.data;
    }

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

    /**
     * Set the "idPagamento" field value
     * This field is mapped on the database column "id_pagamento" ( type "int8", NotNull : false ) 
     * @param idPagamento
     */
    public void setIdPagamento( Long idPagamento ) {
        this.idPagamento = idPagamento;
    }
    /**
     * Get the "idPagamento" field value
     * This field is mapped on the database column "id_pagamento" ( type "int8", NotNull : false ) 
     * @return the field value
     */
    public Long getIdPagamento() {
        return this.idPagamento;
    }

    /**
     * Set the "idRegistroVersamento" field value
     * This field is mapped on the database column "id_registro_versamento" ( type "int8", NotNull : false ) 
     * @param idRegistroVersamento
     */
    public void setIdRegistroVersamento( Long idRegistroVersamento ) {
        this.idRegistroVersamento = idRegistroVersamento;
    }
    /**
     * Get the "idRegistroVersamento" field value
     * This field is mapped on the database column "id_registro_versamento" ( type "int8", NotNull : false ) 
     * @return the field value
     */
    public Long getIdRegistroVersamento() {
        return this.idRegistroVersamento;
    }

    /**
     * Set the "iuv" field value
     * This field is mapped on the database column "iuv" ( type "varchar", NotNull : false ) 
     * @param iuv
     */
    public void setIuv( String iuv ) {
        this.iuv = iuv;
    }
    /**
     * Get the "iuv" field value
     * This field is mapped on the database column "iuv" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getIuv() {
        return this.iuv;
    }

    /**
     * Set the "idTransazione" field value
     * This field is mapped on the database column "id_transazione" ( type "int8", NotNull : false ) 
     * @param idTransazione
     */
    public void setIdTransazione( Long idTransazione ) {
        this.idTransazione = idTransazione;
    }
    /**
     * Get the "idTransazione" field value
     * This field is mapped on the database column "id_transazione" ( type "int8", NotNull : false ) 
     * @return the field value
     */
    public Long getIdTransazione() {
        return this.idTransazione;
    }

    /**
     * Set the "descCorrezione" field value
     * This field is mapped on the database column "desc_correzione" ( type "varchar", NotNull : false ) 
     * @param descCorrezione
     */
    public void setDescCorrezione( String descCorrezione ) {
        this.descCorrezione = descCorrezione;
    }
    /**
     * Get the "descCorrezione" field value
     * This field is mapped on the database column "desc_correzione" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getDescCorrezione() {
        return this.descCorrezione;
    }

    /**
     * Set the "applicativo" field value
     * This field is mapped on the database column "applicativo" ( type "varchar", NotNull : true ) 
     * @param applicativo
     */
    public void setApplicativo( String applicativo ) {
        this.applicativo = applicativo;
    }
    /**
     * Get the "applicativo" field value
     * This field is mapped on the database column "applicativo" ( type "varchar", NotNull : true ) 
     * @return the field value
     */
    public String getApplicativo() {
        return this.applicativo;
    }

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    @Override
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
        sb.append(data);
        sb.append("|");
        sb.append(descrizione);
        sb.append("|");
        sb.append(idPagamento);
        sb.append("|");
        sb.append(idRegistroVersamento);
        sb.append("|");
        sb.append(iuv);
        sb.append("|");
        sb.append(idTransazione);
        sb.append("|");
        sb.append(descCorrezione);
        sb.append("|");
        sb.append(applicativo);
        return sb.toString(); 
    } 



}
