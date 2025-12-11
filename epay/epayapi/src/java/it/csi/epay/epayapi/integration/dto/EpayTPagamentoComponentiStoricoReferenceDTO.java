/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.dto;

import java.io.Serializable;
import javax.validation.constraints.*;

import java.math.BigDecimal;
import java.util.Date;
/**
 * Reference DTO for entity "epay_t_pagamento_componenti_storico" <br>
 * Contains only "wrapper types" (no primitive types) <br>
 * Can be used both as a "web form" and "persistence record" <br>
 * 
 * @author fabio.fenoglio
 *
 */
public class EpayTPagamentoComponentiStoricoReferenceDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Long id ; // Long // Id or Primary Key

    @NotNull
    private Date dataStoricizzazione ;  // Date 

    private Long idComponente ;  // Long 

    private Long idPagamento ;  // Long 

    private Integer progressivo ;  // Integer 

    private BigDecimal importo ;  // BigDecimal 
    @Size( max = 140 )
    private String causale ;  // String 
    @Size( max = 140 )
    private String datiSpecificiRiscossione ;  // String 
    @NotNull
    @Size( min = 1, max = 100 )
    private String utenteUltimaModifica ;  // String 
    
    private String codiceTributo;

    /**
     * Default constructor
     */
    public EpayTPagamentoComponentiStoricoReferenceDTO() {
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
     * Set the "dataStoricizzazione" field value
     * This field is mapped on the database column "data_storicizzazione" ( type "timestamp", NotNull : true ) 
     * @param dataStoricizzazione
     */
    public void setDataStoricizzazione( Date dataStoricizzazione ) {
        this.dataStoricizzazione = dataStoricizzazione;
    }
    /**
     * Get the "dataStoricizzazione" field value
     * This field is mapped on the database column "data_storicizzazione" ( type "timestamp", NotNull : true ) 
     * @return the field value
     */
    public Date getDataStoricizzazione() {
        return this.dataStoricizzazione;
    }

    /**
     * Set the "idComponente" field value
     * This field is mapped on the database column "id_componente" ( type "int8", NotNull : false ) 
     * @param idComponente
     */
    public void setIdComponente( Long idComponente ) {
        this.idComponente = idComponente;
    }
    /**
     * Get the "idComponente" field value
     * This field is mapped on the database column "id_componente" ( type "int8", NotNull : false ) 
     * @return the field value
     */
    public Long getIdComponente() {
        return this.idComponente;
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
     * Set the "progressivo" field value
     * This field is mapped on the database column "progressivo" ( type "int4", NotNull : false ) 
     * @param progressivo
     */
    public void setProgressivo( Integer progressivo ) {
        this.progressivo = progressivo;
    }
    /**
     * Get the "progressivo" field value
     * This field is mapped on the database column "progressivo" ( type "int4", NotNull : false ) 
     * @return the field value
     */
    public Integer getProgressivo() {
        return this.progressivo;
    }

    /**
     * Set the "importo" field value
     * This field is mapped on the database column "importo" ( type "numeric", NotNull : false ) 
     * @param importo
     */
    public void setImporto( BigDecimal importo ) {
        this.importo = importo;
    }
    /**
     * Get the "importo" field value
     * This field is mapped on the database column "importo" ( type "numeric", NotNull : false ) 
     * @return the field value
     */
    public BigDecimal getImporto() {
        return this.importo;
    }

    /**
     * Set the "causale" field value
     * This field is mapped on the database column "causale" ( type "varchar", NotNull : false ) 
     * @param causale
     */
    public void setCausale( String causale ) {
        this.causale = causale;
    }
    /**
     * Get the "causale" field value
     * This field is mapped on the database column "causale" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getCausale() {
        return this.causale;
    }

    /**
     * Set the "datiSpecificiRiscossione" field value
     * This field is mapped on the database column "dati_specifici_riscossione" ( type "varchar", NotNull : false ) 
     * @param datiSpecificiRiscossione
     */
    public void setDatiSpecificiRiscossione( String datiSpecificiRiscossione ) {
        this.datiSpecificiRiscossione = datiSpecificiRiscossione;
    }
    /**
     * Get the "datiSpecificiRiscossione" field value
     * This field is mapped on the database column "dati_specifici_riscossione" ( type "varchar", NotNull : false ) 
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
        sb.append(id);
        sb.append("|");
        sb.append(dataStoricizzazione);
        sb.append("|");
        sb.append(idComponente);
        sb.append("|");
        sb.append(idPagamento);
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
        return sb.toString(); 
    } 



}
