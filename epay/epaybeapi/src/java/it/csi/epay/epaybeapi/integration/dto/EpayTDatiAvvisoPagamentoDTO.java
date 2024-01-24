/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.dto;

import java.io.Serializable;
import javax.validation.constraints.*;

import java.util.Date;
/**
 * DTO for entity "epay_t_dati_avviso_pagamento" <br>
 * Contains only "wrapper types" (no primitive types) <br>
 * Can be used both as a "web form" and "persistence record" <br>
 * 
 * @author EII
 *
 */
public class EpayTDatiAvvisoPagamentoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Long idDatiAvvisoPagamento ; // Id or Primary Key

    @Size( max = 50 )
    private String settore ; 
    @Size( max = 30 )
    private String indirizzo ; 
    @Size( max = 30 )
    private String localita ; 
    @Size( max = 5 )
    private String cap ; 
    @Size( max = 2 )
    private String siglaProvincia ; 
    @Size( max = 27 )
    private String email ; 
    @Size( max = 100 )
    private String infoEnte ; 
    @Size( max = 50 )
    private String intestatarioCcPostale ; 
    @Size( max = 20 )
    private String numeroCcPostale ; 
    @Size( max = 37 )
    private String autorizzazioneDaPosteIt ; 
    @NotNull
    @Size( min = 1, max = 50 )
    private String utenteUltimaModifica ; 
    @NotNull
    private Date dataUltimaModifica ; 

	private EpayTTipoPagamentoReferenceDTO epayTTipoPagamento ;


    /**
     * Default constructor
     */
    public EpayTDatiAvvisoPagamentoDTO() {
        super();
    }
    
    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR ID OR PRIMARY KEY 
    //----------------------------------------------------------------------
    /**
     * Set the "idDatiAvvisoPagamento" field value
     * This field is mapped on the database column "id_dati_avviso_pagamento" ( type "bigserial", NotNull : true ) 
     * @param idDatiAvvisoPagamento
     */
	public void setIdDatiAvvisoPagamento( Long idDatiAvvisoPagamento ) {
        this.idDatiAvvisoPagamento = idDatiAvvisoPagamento ;
    }
    /**
     * Get the "idDatiAvvisoPagamento" field value
     * This field is mapped on the database column "id_dati_avviso_pagamento" ( type "bigserial", NotNull : true ) 
     * @return the field value
     */
	public Long getIdDatiAvvisoPagamento() {
        return this.idDatiAvvisoPagamento;
    }

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR OTHER DATA FIELDS 
    //----------------------------------------------------------------------
    
    /**
     * Set the "settore" field value
     * This field is mapped on the database column "settore" ( type "varchar", NotNull : false ) 
     * @param settore
     */
    public void setSettore( String settore ) {
        this.settore = settore;
    }
    /**
     * Get the "settore" field value
     * This field is mapped on the database column "settore" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getSettore() {
        return this.settore;
    }

    /**
     * Set the "indirizzo" field value
     * This field is mapped on the database column "indirizzo" ( type "varchar", NotNull : false ) 
     * @param indirizzo
     */
    public void setIndirizzo( String indirizzo ) {
        this.indirizzo = indirizzo;
    }
    /**
     * Get the "indirizzo" field value
     * This field is mapped on the database column "indirizzo" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getIndirizzo() {
        return this.indirizzo;
    }

    /**
     * Set the "localita" field value
     * This field is mapped on the database column "localita" ( type "varchar", NotNull : false ) 
     * @param localita
     */
    public void setLocalita( String localita ) {
        this.localita = localita;
    }
    /**
     * Get the "localita" field value
     * This field is mapped on the database column "localita" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getLocalita() {
        return this.localita;
    }

    /**
     * Set the "cap" field value
     * This field is mapped on the database column "cap" ( type "varchar", NotNull : false ) 
     * @param cap
     */
    public void setCap( String cap ) {
        this.cap = cap;
    }
    /**
     * Get the "cap" field value
     * This field is mapped on the database column "cap" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getCap() {
        return this.cap;
    }

    /**
     * Set the "siglaProvincia" field value
     * This field is mapped on the database column "sigla_provincia" ( type "varchar", NotNull : false ) 
     * @param siglaProvincia
     */
    public void setSiglaProvincia( String siglaProvincia ) {
        this.siglaProvincia = siglaProvincia;
    }
    /**
     * Get the "siglaProvincia" field value
     * This field is mapped on the database column "sigla_provincia" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getSiglaProvincia() {
        return this.siglaProvincia;
    }

    /**
     * Set the "email" field value
     * This field is mapped on the database column "email" ( type "varchar", NotNull : false ) 
     * @param email
     */
    public void setEmail( String email ) {
        this.email = email;
    }
    /**
     * Get the "email" field value
     * This field is mapped on the database column "email" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Set the "infoEnte" field value
     * This field is mapped on the database column "info_ente" ( type "varchar", NotNull : false ) 
     * @param infoEnte
     */
    public void setInfoEnte( String infoEnte ) {
        this.infoEnte = infoEnte;
    }
    /**
     * Get the "infoEnte" field value
     * This field is mapped on the database column "info_ente" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getInfoEnte() {
        return this.infoEnte;
    }

    /**
     * Set the "intestatarioCcPostale" field value
     * This field is mapped on the database column "intestatario_cc_postale" ( type "varchar", NotNull : false ) 
     * @param intestatarioCcPostale
     */
    public void setIntestatarioCcPostale( String intestatarioCcPostale ) {
        this.intestatarioCcPostale = intestatarioCcPostale;
    }
    /**
     * Get the "intestatarioCcPostale" field value
     * This field is mapped on the database column "intestatario_cc_postale" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getIntestatarioCcPostale() {
        return this.intestatarioCcPostale;
    }

    /**
     * Set the "numeroCcPostale" field value
     * This field is mapped on the database column "numero_cc_postale" ( type "varchar", NotNull : false ) 
     * @param numeroCcPostale
     */
    public void setNumeroCcPostale( String numeroCcPostale ) {
        this.numeroCcPostale = numeroCcPostale;
    }
    /**
     * Get the "numeroCcPostale" field value
     * This field is mapped on the database column "numero_cc_postale" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getNumeroCcPostale() {
        return this.numeroCcPostale;
    }

    /**
     * Set the "autorizzazioneDaPosteIt" field value
     * This field is mapped on the database column "autorizzazione_da_poste_it" ( type "varchar", NotNull : false ) 
     * @param autorizzazioneDaPosteIt
     */
    public void setAutorizzazioneDaPosteIt( String autorizzazioneDaPosteIt ) {
        this.autorizzazioneDaPosteIt = autorizzazioneDaPosteIt;
    }
    /**
     * Get the "autorizzazioneDaPosteIt" field value
     * This field is mapped on the database column "autorizzazione_da_poste_it" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getAutorizzazioneDaPosteIt() {
        return this.autorizzazioneDaPosteIt;
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
     * Set the "dataUltimaModifica" field value
     * This field is mapped on the database column "data_ultima_modifica" ( type "timestamp", NotNull : true ) 
     * @param dataUltimaModifica
     */
    public void setDataUltimaModifica( Date dataUltimaModifica ) {
        this.dataUltimaModifica = dataUltimaModifica;
    }
    /**
     * Get the "dataUltimaModifica" field value
     * This field is mapped on the database column "data_ultima_modifica" ( type "timestamp", NotNull : true ) 
     * @return the field value
     */
    public Date getDataUltimaModifica() {
        return this.dataUltimaModifica;
    }


    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR RELATIONSHIPS
    //----------------------------------------------------------------------
    public void setEpayTTipoPagamento( EpayTTipoPagamentoReferenceDTO epayTTipoPagamento ) {
        this.epayTTipoPagamento = epayTTipoPagamento;
    }
    public EpayTTipoPagamentoReferenceDTO getEpayTTipoPagamento() {
        return this.epayTTipoPagamento;
    }

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    @Override
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(idDatiAvvisoPagamento);
        sb.append("|");
        sb.append(settore);
        sb.append("|");
        sb.append(indirizzo);
        sb.append("|");
        sb.append(localita);
        sb.append("|");
        sb.append(cap);
        sb.append("|");
        sb.append(siglaProvincia);
        sb.append("|");
        sb.append(email);
        sb.append("|");
        sb.append(infoEnte);
        sb.append("|");
        sb.append(intestatarioCcPostale);
        sb.append("|");
        sb.append(numeroCcPostale);
        sb.append("|");
        sb.append(autorizzazioneDaPosteIt);
        sb.append("|");
        sb.append(utenteUltimaModifica);
        sb.append("|");
        sb.append(dataUltimaModifica);
        return sb.toString(); 
    } 



}
