/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.dto;

import java.io.Serializable;
import javax.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * DTO for entity "epay_t_enti" <br>
 * Contains only "wrapper types" (no primitive types) <br>
 * Can be used both as a "web form" and "persistence record" <br>
 * 
 * @author fabio.fenoglio
 *
 */
public class EpayTEntiDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Long idEnte ; // Id or Primary Key

    @NotNull
    @Size( min = 1, max = 250 )
    private String nome ; 
    @NotNull
    @Size( min = 1, max = 16 )
    private String codiceFiscale ; 
    @NotNull
    private byte[] logo ; 
    @NotNull
    private BigDecimal codiceGs1Gln ; 
    @Size( max = 2000 )
    private String orari ; 
    @NotNull
    private Boolean flagInvioPagamenti ; 
    @Size( max = 5 )
    private String codiceInterbancario ; 

    private List<EpayTEntiTempReferenceDTO> listOfEpayTEntiTemp ;

    private List<EpayTTipoPagamentoLogReferenceDTO> listOfEpayTTipoPagamentoLog ;

    private List<EpayTEntiLogReferenceDTO> listOfEpayTEntiLog ;

    private List<EpayTConfigurazioneReferenceDTO> listOfEpayTConfigurazione ;

    private List<EpayTTipoPagamentoReferenceDTO> listOfEpayTTipoPagamento ;

    private List<EpayTTipoPagamentoTempReferenceDTO> listOfEpayTTipoPagamentoTemp ;


    /**
     * Default constructor
     */
    public EpayTEntiDTO() {
        super();
    }
    
    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR ID OR PRIMARY KEY 
    //----------------------------------------------------------------------
    /**
     * Set the "idEnte" field value
     * This field is mapped on the database column "id_ente" ( type "bigserial", NotNull : true ) 
     * @param idEnte
     */
	public void setIdEnte( Long idEnte ) {
        this.idEnte = idEnte ;
    }
    /**
     * Get the "idEnte" field value
     * This field is mapped on the database column "id_ente" ( type "bigserial", NotNull : true ) 
     * @return the field value
     */
	public Long getIdEnte() {
        return this.idEnte;
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
     * Set the "codiceFiscale" field value
     * This field is mapped on the database column "codice_fiscale" ( type "varchar", NotNull : true ) 
     * @param codiceFiscale
     */
    public void setCodiceFiscale( String codiceFiscale ) {
        this.codiceFiscale = codiceFiscale;
    }
    /**
     * Get the "codiceFiscale" field value
     * This field is mapped on the database column "codice_fiscale" ( type "varchar", NotNull : true ) 
     * @return the field value
     */
    public String getCodiceFiscale() {
        return this.codiceFiscale;
    }

    /**
     * Set the "logo" field value
     * This field is mapped on the database column "logo" ( type "bytea", NotNull : true ) 
     * @param logo
     */
    public void setLogo( byte[] logo ) {
        this.logo = logo;
    }
    /**
     * Get the "logo" field value
     * This field is mapped on the database column "logo" ( type "bytea", NotNull : true ) 
     * @return the field value
     */
    public byte[] getLogo() {
        return this.logo;
    }

    /**
     * Set the "codiceGs1Gln" field value
     * This field is mapped on the database column "codice_gs1_gln" ( type "numeric", NotNull : true ) 
     * @param codiceGs1Gln
     */
    public void setCodiceGs1Gln( BigDecimal codiceGs1Gln ) {
        this.codiceGs1Gln = codiceGs1Gln;
    }
    /**
     * Get the "codiceGs1Gln" field value
     * This field is mapped on the database column "codice_gs1_gln" ( type "numeric", NotNull : true ) 
     * @return the field value
     */
    public BigDecimal getCodiceGs1Gln() {
        return this.codiceGs1Gln;
    }

    /**
     * Set the "orari" field value
     * This field is mapped on the database column "orari" ( type "varchar", NotNull : false ) 
     * @param orari
     */
    public void setOrari( String orari ) {
        this.orari = orari;
    }
    /**
     * Get the "orari" field value
     * This field is mapped on the database column "orari" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getOrari() {
        return this.orari;
    }

    /**
     * Set the "flagInvioPagamenti" field value
     * This field is mapped on the database column "flag_invio_pagamenti" ( type "bool", NotNull : true ) 
     * @param flagInvioPagamenti
     */
    public void setFlagInvioPagamenti( Boolean flagInvioPagamenti ) {
        this.flagInvioPagamenti = flagInvioPagamenti;
    }
    /**
     * Get the "flagInvioPagamenti" field value
     * This field is mapped on the database column "flag_invio_pagamenti" ( type "bool", NotNull : true ) 
     * @return the field value
     */
    public Boolean getFlagInvioPagamenti() {
        return this.flagInvioPagamenti;
    }

    /**
     * Set the "codiceInterbancario" field value
     * This field is mapped on the database column "codice_interbancario" ( type "varchar", NotNull : false ) 
     * @param codiceInterbancario
     */
    public void setCodiceInterbancario( String codiceInterbancario ) {
        this.codiceInterbancario = codiceInterbancario;
    }
    /**
     * Get the "codiceInterbancario" field value
     * This field is mapped on the database column "codice_interbancario" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getCodiceInterbancario() {
        return this.codiceInterbancario;
    }


    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR RELATIONSHIPS
    //----------------------------------------------------------------------
    public void setListOfEpayTEntiTemp( List<EpayTEntiTempReferenceDTO> listOfEpayTEntiTemp ) {
        this.listOfEpayTEntiTemp = listOfEpayTEntiTemp;
    }
    public List<EpayTEntiTempReferenceDTO> getListOfEpayTEntiTemp() {
        return this.listOfEpayTEntiTemp;
    }
    public void setListOfEpayTTipoPagamentoLog( List<EpayTTipoPagamentoLogReferenceDTO> listOfEpayTTipoPagamentoLog ) {
        this.listOfEpayTTipoPagamentoLog = listOfEpayTTipoPagamentoLog;
    }
    public List<EpayTTipoPagamentoLogReferenceDTO> getListOfEpayTTipoPagamentoLog() {
        return this.listOfEpayTTipoPagamentoLog;
    }
    public void setListOfEpayTEntiLog( List<EpayTEntiLogReferenceDTO> listOfEpayTEntiLog ) {
        this.listOfEpayTEntiLog = listOfEpayTEntiLog;
    }
    public List<EpayTEntiLogReferenceDTO> getListOfEpayTEntiLog() {
        return this.listOfEpayTEntiLog;
    }
    public void setListOfEpayTConfigurazione( List<EpayTConfigurazioneReferenceDTO> listOfEpayTConfigurazione ) {
        this.listOfEpayTConfigurazione = listOfEpayTConfigurazione;
    }
    public List<EpayTConfigurazioneReferenceDTO> getListOfEpayTConfigurazione() {
        return this.listOfEpayTConfigurazione;
    }
    public void setListOfEpayTTipoPagamento( List<EpayTTipoPagamentoReferenceDTO> listOfEpayTTipoPagamento ) {
        this.listOfEpayTTipoPagamento = listOfEpayTTipoPagamento;
    }
    public List<EpayTTipoPagamentoReferenceDTO> getListOfEpayTTipoPagamento() {
        return this.listOfEpayTTipoPagamento;
    }
    public void setListOfEpayTTipoPagamentoTemp( List<EpayTTipoPagamentoTempReferenceDTO> listOfEpayTTipoPagamentoTemp ) {
        this.listOfEpayTTipoPagamentoTemp = listOfEpayTTipoPagamentoTemp;
    }
    public List<EpayTTipoPagamentoTempReferenceDTO> getListOfEpayTTipoPagamentoTemp() {
        return this.listOfEpayTTipoPagamentoTemp;
    }

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    @Override
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(idEnte);
        sb.append("|");
        sb.append(nome);
        sb.append("|");
        sb.append(codiceFiscale);
        // attribute 'logo' not usable (type = byte[])
        sb.append("|");
        sb.append(codiceGs1Gln);
        sb.append("|");
        sb.append(orari);
        sb.append("|");
        sb.append(flagInvioPagamenti);
        sb.append("|");
        sb.append(codiceInterbancario);
        return sb.toString(); 
    } 



}
