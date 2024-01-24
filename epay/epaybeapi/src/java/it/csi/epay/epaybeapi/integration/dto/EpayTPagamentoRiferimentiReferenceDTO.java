/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.dto;

import java.io.Serializable;
import javax.validation.constraints.*;

/**
 * Reference DTO for entity "epay_t_pagamento_riferimenti" <br>
 * Contains only "wrapper types" (no primitive types) <br>
 * Can be used both as a "web form" and "persistence record" <br>
 * 
 * @author EII
 *
 */
public class EpayTPagamentoRiferimentiReferenceDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Long idRiferimento ; // Long // Id or Primary Key

    @NotNull
    @Size( min = 1, max = 70 )
    private String nome ;  // String 
    @NotNull
    private Integer progressivo ;  // Integer 
    @NotNull
    @Size( min = 1, max = 70 )
    private String valore ;  // String 
    @NotNull
    @Size( min = 1, max = 2147483647 )
    private String utenteUltimaModifica ;  // String 

    /**
     * Default constructor
     */
    public EpayTPagamentoRiferimentiReferenceDTO() {
        super();
    }
    
    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR ID OR PRIMARY KEY 
    //----------------------------------------------------------------------
    /**
     * Set the "idRiferimento" field value
     * This field is mapped on the database column "id_riferimento" ( type "int8", NotNull : true ) 
     * @param idRiferimento
     */
	public void setIdRiferimento( Long idRiferimento ) {
        this.idRiferimento = idRiferimento ;
    }
    /**
     * Get the "idRiferimento" field value
     * This field is mapped on the database column "id_riferimento" ( type "int8", NotNull : true ) 
     * @return the field value
     */
	public Long getIdRiferimento() {
        return this.idRiferimento;
    }

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR OTHER DATA FIELDS 
    //----------------------------------------------------------------------

    /**
     * Set the "nome" field value
     * This field is mapped on the database column "nome" ( type "varchar", NotNull : true ) 
     * @param nome
     */
    public void setNome( String nome ) {
        this.nome = nome;
    }
    /**
     * Get the "nome" field value
     * This field is mapped on the database column "nome" ( type "varchar", NotNull : true ) 
     * @return the field value
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Set the "progressivo" field value
     * This field is mapped on the database column "progressivo" ( type "int4", NotNull : true ) 
     * @param progressivo
     */
    public void setProgressivo( Integer progressivo ) {
        this.progressivo = progressivo;
    }
    /**
     * Get the "progressivo" field value
     * This field is mapped on the database column "progressivo" ( type "int4", NotNull : true ) 
     * @return the field value
     */
    public Integer getProgressivo() {
        return this.progressivo;
    }

    /**
     * Set the "valore" field value
     * This field is mapped on the database column "valore" ( type "varchar", NotNull : true ) 
     * @param valore
     */
    public void setValore( String valore ) {
        this.valore = valore;
    }
    /**
     * Get the "valore" field value
     * This field is mapped on the database column "valore" ( type "varchar", NotNull : true ) 
     * @return the field value
     */
    public String getValore() {
        return this.valore;
    }

    /**
     * Set the "utenteUltimaModifica" field value
     * This field is mapped on the database column "utente_ultima_modifica" ( type "varchar", NotNull : true ) 
     * @param utenteUltimaModifica
     */
    public void setUtenteUltimaModifica( String utenteUltimaModifica ) {
        this.utenteUltimaModifica = utenteUltimaModifica;
    }
    /**
     * Get the "utenteUltimaModifica" field value
     * This field is mapped on the database column "utente_ultima_modifica" ( type "varchar", NotNull : true ) 
     * @return the field value
     */
    public String getUtenteUltimaModifica() {
        return this.utenteUltimaModifica;
    }

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    @Override
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(idRiferimento);
        sb.append("|");
        sb.append(nome);
        sb.append("|");
        sb.append(progressivo);
        sb.append("|");
        sb.append(valore);
        sb.append("|");
        sb.append(utenteUltimaModifica);
        return sb.toString(); 
    } 



}
