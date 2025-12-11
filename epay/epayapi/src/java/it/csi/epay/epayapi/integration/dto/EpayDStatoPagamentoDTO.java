/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.dto;

import java.io.Serializable;
import javax.validation.constraints.*;

import java.util.List;

/**
 * DTO for entity "epay_d_stato_pagamento" <br>
 * Contains only "wrapper types" (no primitive types) <br>
 * Can be used both as a "web form" and "persistence record" <br>
 * 
 * @author fabio.fenoglio
 *
 */
public class EpayDStatoPagamentoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Short idStato ; // Id or Primary Key

    @NotNull
    @Size( min = 1, max = 250 )
    private String descStato ; 
    @NotNull
    private Boolean pagabile ; 
    @NotNull
    private Boolean modificabile ; 

    private List<EpayTRegistroVersamentiReferenceDTO> listOfEpayTRegistroVersamenti ;

    private List<EpayTPagamentoReferenceDTO> listOfEpayTPagamento ;


    /**
     * Default constructor
     */
    public EpayDStatoPagamentoDTO() {
        super();
    }
    
    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR ID OR PRIMARY KEY 
    //----------------------------------------------------------------------
    /**
     * Set the "idStato" field value
     * This field is mapped on the database column "id_stato" ( type "int2", NotNull : true ) 
     * @param idStato
     */
	public void setIdStato( Short idStato ) {
        this.idStato = idStato ;
    }
    /**
     * Get the "idStato" field value
     * This field is mapped on the database column "id_stato" ( type "int2", NotNull : true ) 
     * @return the field value
     */
	public Short getIdStato() {
        return this.idStato;
    }

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR OTHER DATA FIELDS 
    //----------------------------------------------------------------------
    
    /**
     * Set the "descStato" field value
     * This field is mapped on the database column "desc_stato" ( type "varchar", NotNull : true ) 
     * @param descStato
     */
    public void setDescStato( String descStato ) {
        this.descStato = descStato;
    }
    /**
     * Get the "descStato" field value
     * This field is mapped on the database column "desc_stato" ( type "varchar", NotNull : true ) 
     * @return the field value
     */
    public String getDescStato() {
        return this.descStato;
    }

    /**
     * Set the "pagabile" field value
     * This field is mapped on the database column "pagabile" ( type "bool", NotNull : true ) 
     * @param pagabile
     */
    public void setPagabile( Boolean pagabile ) {
        this.pagabile = pagabile;
    }
    /**
     * Get the "pagabile" field value
     * This field is mapped on the database column "pagabile" ( type "bool", NotNull : true ) 
     * @return the field value
     */
    public Boolean getPagabile() {
        return this.pagabile;
    }

    /**
     * Set the "modificabile" field value
     * This field is mapped on the database column "modificabile" ( type "bool", NotNull : true ) 
     * @param modificabile
     */
    public void setModificabile( Boolean modificabile ) {
        this.modificabile = modificabile;
    }
    /**
     * Get the "modificabile" field value
     * This field is mapped on the database column "modificabile" ( type "bool", NotNull : true ) 
     * @return the field value
     */
    public Boolean getModificabile() {
        return this.modificabile;
    }


    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR RELATIONSHIPS
    //----------------------------------------------------------------------
    public void setListOfEpayTRegistroVersamenti( List<EpayTRegistroVersamentiReferenceDTO> listOfEpayTRegistroVersamenti ) {
        this.listOfEpayTRegistroVersamenti = listOfEpayTRegistroVersamenti;
    }
    public List<EpayTRegistroVersamentiReferenceDTO> getListOfEpayTRegistroVersamenti() {
        return this.listOfEpayTRegistroVersamenti;
    }
    public void setListOfEpayTPagamento( List<EpayTPagamentoReferenceDTO> listOfEpayTPagamento ) {
        this.listOfEpayTPagamento = listOfEpayTPagamento;
    }
    public List<EpayTPagamentoReferenceDTO> getListOfEpayTPagamento() {
        return this.listOfEpayTPagamento;
    }

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    @Override
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(idStato);
        sb.append("|");
        sb.append(descStato);
        sb.append("|");
        sb.append(pagabile);
        sb.append("|");
        sb.append(modificabile);
        return sb.toString(); 
    } 



}
