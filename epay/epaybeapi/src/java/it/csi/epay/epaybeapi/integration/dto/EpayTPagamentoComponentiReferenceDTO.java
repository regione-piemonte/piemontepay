/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.*;

import java.math.BigDecimal;
/**
 * Reference DTO for entity "epay_t_pagamento_componenti" <br>
 * Contains only "wrapper types" (no primitive types) <br>
 * Can be used both as a "web form" and "persistence record" <br>
 * 
 * @author EII
 *
 */
public class EpayTPagamentoComponentiReferenceDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Long idComponente ; // Long // Id or Primary Key

    @NotNull
    private Integer progressivo ;  // Integer 
    @NotNull
    private BigDecimal importo ;  // BigDecimal 
    @NotNull
    @Size( min = 1, max = 140 )
    private String causale ;  // String 
    @NotNull
    @Size( min = 1, max = 140 )
    private String datiSpecificiRiscossione ;  // String 
    @NotNull
    @Size( min = 1, max = 100 )
    private String utenteUltimaModifica ;  // String 

    private Long annoAccertamento ;  // Long 
    @Size( max = 35 )
    private String numeroAccertamento ;  // String 
    
    private String codiceTributo;
    

    /**
     * Default constructor
     */
    public EpayTPagamentoComponentiReferenceDTO() {
        super();
    }
    
    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR ID OR PRIMARY KEY 
    //----------------------------------------------------------------------
    /**
     * Set the "idComponente" field value
     * This field is mapped on the database column "id_componente" ( type "bigserial", NotNull : true ) 
     * @param idComponente
     */
	public void setIdComponente( Long idComponente ) {
        this.idComponente = idComponente ;
    }
    /**
     * Get the "idComponente" field value
     * This field is mapped on the database column "id_componente" ( type "bigserial", NotNull : true ) 
     * @return the field value
     */
	public Long getIdComponente() {
        return this.idComponente;
    }

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR OTHER DATA FIELDS 
    //----------------------------------------------------------------------

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
     * Set the "importo" field value
     * This field is mapped on the database column "importo" ( type "numeric", NotNull : true ) 
     * @param importo
     */
    public void setImporto( BigDecimal importo ) {
        this.importo = importo;
    }
    /**
     * Get the "importo" field value
     * This field is mapped on the database column "importo" ( type "numeric", NotNull : true ) 
     * @return the field value
     */
    public BigDecimal getImporto() {
        return this.importo;
    }

    /**
     * Set the "causale" field value
     * This field is mapped on the database column "causale" ( type "varchar", NotNull : true ) 
     * @param causale
     */
    public void setCausale( String causale ) {
        this.causale = causale;
    }
    /**
     * Get the "causale" field value
     * This field is mapped on the database column "causale" ( type "varchar", NotNull : true ) 
     * @return the field value
     */
    public String getCausale() {
        return this.causale;
    }

    /**
     * Set the "datiSpecificiRiscossione" field value
     * This field is mapped on the database column "dati_specifici_riscossione" ( type "varchar", NotNull : true ) 
     * @param datiSpecificiRiscossione
     */
    public void setDatiSpecificiRiscossione( String datiSpecificiRiscossione ) {
        this.datiSpecificiRiscossione = datiSpecificiRiscossione;
    }
    /**
     * Get the "datiSpecificiRiscossione" field value
     * This field is mapped on the database column "dati_specifici_riscossione" ( type "varchar", NotNull : true ) 
     * @return the field value
     */
    public String getDatiSpecificiRiscossione() {
        return this.datiSpecificiRiscossione;
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

    /**
     * Set the "annoAccertamento" field value
     * This field is mapped on the database column "anno_accertamento" ( type "int8", NotNull : false ) 
     * @param annoAccertamento
     */
    public void setAnnoAccertamento( Long annoAccertamento ) {
        this.annoAccertamento = annoAccertamento;
    }
    /**
     * Get the "annoAccertamento" field value
     * This field is mapped on the database column "anno_accertamento" ( type "int8", NotNull : false ) 
     * @return the field value
     */
    public Long getAnnoAccertamento() {
        return this.annoAccertamento;
    }

    /**
     * Set the "numeroAccertamento" field value
     * This field is mapped on the database column "numero_accertamento" ( type "varchar", NotNull : false ) 
     * @param numeroAccertamento
     */
    public void setNumeroAccertamento( String numeroAccertamento ) {
        this.numeroAccertamento = numeroAccertamento;
    }
    /**
     * Get the "numeroAccertamento" field value
     * This field is mapped on the database column "numero_accertamento" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getNumeroAccertamento() {
        return this.numeroAccertamento;
    }

    
    
    /**
     * @return the codiceTributo
     */
    public String getCodiceTributo () {
        return codiceTributo;
    }

    
    /**
     * @param codiceTributo the codiceTributo to set
     */
    public void setCodiceTributo ( String codiceTributo ) {
        this.codiceTributo = codiceTributo;
    }

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    @Override
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(idComponente);
        sb.append("|");
        sb.append(progressivo);
        sb.append("|");
        sb.append(importo);
        sb.append("|");
        sb.append(causale);
        sb.append("|");
        sb.append(datiSpecificiRiscossione);
        sb.append("|");
        sb.append(utenteUltimaModifica);
        sb.append("|");
        sb.append(annoAccertamento);
        sb.append("|");
        sb.append(numeroAccertamento);
        sb.append("|");
        sb.append(codiceTributo);
        return sb.toString(); 
    } 



}
