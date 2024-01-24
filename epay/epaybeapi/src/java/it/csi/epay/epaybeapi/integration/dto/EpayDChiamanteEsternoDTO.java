/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO for entity "epay_d_chiamante_esterno" <br>
 * Contains only "wrapper types" (no primitive types) <br>
 * Can be used both as a "web form" and "persistence record" <br>
 * 
 * @author EII
 *
 */
public class EpayDChiamanteEsternoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size( min = 1, max = 100 )
    private String codiceChiamante ; // Id or Primary Key

    @Size( max = 200 )
    private String descrizioneChiamante ; 
    @NotNull
    private Date dataInizioValidita ; 

    private Date dataFineValidita ; 

    /**
     * Default constructor
     */
    public EpayDChiamanteEsternoDTO() {
        super();
    }
    
    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR ID OR PRIMARY KEY 
    //----------------------------------------------------------------------
    /**
     * Set the "codiceChiamante" field value
     * This field is mapped on the database column "codice_chiamante" ( type "varchar", NotNull : true ) 
     * @param codiceChiamante
     */
	public void setCodiceChiamante( String codiceChiamante ) {
        this.codiceChiamante = codiceChiamante ;
    }
    /**
     * Get the "codiceChiamante" field value
     * This field is mapped on the database column "codice_chiamante" ( type "varchar", NotNull : true ) 
     * @return the field value
     */
	public String getCodiceChiamante() {
        return codiceChiamante;
    }

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR OTHER DATA FIELDS 
    //----------------------------------------------------------------------
    
    /**
     * Set the "descrizioneChiamante" field value
     * This field is mapped on the database column "descrizione_chiamante" ( type "varchar", NotNull : false ) 
     * @param descrizioneChiamante
     */
    public void setDescrizioneChiamante( String descrizioneChiamante ) {
        this.descrizioneChiamante = descrizioneChiamante;
    }
    /**
     * Get the "descrizioneChiamante" field value
     * This field is mapped on the database column "descrizione_chiamante" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getDescrizioneChiamante() {
        return descrizioneChiamante;
    }

    /**
     * Set the "dataInizioValidita" field value
     * This field is mapped on the database column "data_inizio_validita" ( type "timestamp", NotNull : true ) 
     * @param dataInizioValidita
     */
    public void setDataInizioValidita( Date dataInizioValidita ) {
        this.dataInizioValidita = dataInizioValidita;
    }
    /**
     * Get the "dataInizioValidita" field value
     * This field is mapped on the database column "data_inizio_validita" ( type "timestamp", NotNull : true ) 
     * @return the field value
     */
    public Date getDataInizioValidita() {
        return dataInizioValidita;
    }

    /**
     * Set the "dataFineValidita" field value
     * This field is mapped on the database column "data_fine_validita" ( type "timestamp", NotNull : false ) 
     * @param dataFineValidita
     */
    public void setDataFineValidita( Date dataFineValidita ) {
        this.dataFineValidita = dataFineValidita;
    }
    /**
     * Get the "dataFineValidita" field value
     * This field is mapped on the database column "data_fine_validita" ( type "timestamp", NotNull : false ) 
     * @return the field value
     */
    public Date getDataFineValidita() {
        return dataFineValidita;
    }

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    @Override
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(codiceChiamante);
        sb.append("|");
        sb.append(descrizioneChiamante);
        sb.append("|");
        sb.append(dataInizioValidita);
        sb.append("|");
        sb.append(dataFineValidita);
        return sb.toString(); 
    } 



}
