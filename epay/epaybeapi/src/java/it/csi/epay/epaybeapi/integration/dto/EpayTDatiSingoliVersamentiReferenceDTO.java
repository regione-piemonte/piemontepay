/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.dto;

import java.io.Serializable;
import javax.validation.constraints.*;

import java.math.BigDecimal;
/**
 * Reference DTO for entity "epay_t_dati_singoli_versamenti" <br>
 * Contains only "wrapper types" (no primitive types) <br>
 * Can be used both as a "web form" and "persistence record" <br>
 * 
 * @author EII
 *
 */
public class EpayTDatiSingoliVersamentiReferenceDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Long id ; // Long // Id or Primary Key

    @NotNull
    private BigDecimal importo ;  // BigDecimal 
    @Size( max = 100 )
    private String descrizioneCausale ;  // String 
    @NotNull
    @Size( min = 1, max = 140 )
    private String datiSpecificiRiscossione ;  // String 

    /**
     * Default constructor
     */
    public EpayTDatiSingoliVersamentiReferenceDTO() {
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
     * Set the "descrizioneCausale" field value
     * This field is mapped on the database column "descrizione_causale" ( type "varchar", NotNull : false ) 
     * @param descrizioneCausale
     */
    public void setDescrizioneCausale( String descrizioneCausale ) {
        this.descrizioneCausale = descrizioneCausale;
    }
    /**
     * Get the "descrizioneCausale" field value
     * This field is mapped on the database column "descrizione_causale" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getDescrizioneCausale() {
        return this.descrizioneCausale;
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

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    @Override
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
        sb.append(importo);
        sb.append("|");
        sb.append(descrizioneCausale);
        sb.append("|");
        sb.append(datiSpecificiRiscossione);
        return sb.toString(); 
    } 



}
