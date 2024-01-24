/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.dto;

import java.io.Serializable;
import javax.validation.constraints.*;

import java.util.List;

/**
 * DTO for entity "epay_t_transazione_mdp" <br>
 * Contains only "wrapper types" (no primitive types) <br>
 * Can be used both as a "web form" and "persistence record" <br>
 * 
 * @author EII
 *
 */
public class EpayTTransazioneMdpDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size( min = 1, max = 50 )
    private String idTransazione ; // Id or Primary Key

    @NotNull
    @Size( min = 1, max = 25 )
    private String iuv ; 
    @Size( max = 50 )
    private String idGateway ; 
    @Size( max = 50 )
    private String idPaymentMode ; 

    private Integer idInformativaPsp ; 
    @Size( max = 250 )
    private String identificativoPsp ; 
    @Size( max = 250 )
    private String ragioneSocialePsp ; 
    @Size( max = 250 )
    private String identificativoCanalePsp ; 
    @Size( max = 250 )
    private String tipoVersamentoPsp ; 

    private Integer modelloPagamentoPsp ; 

    private List<EpayTRegistroVersamentiReferenceDTO> listOfEpayTRegistroVersamenti ;


    /**
     * Default constructor
     */
    public EpayTTransazioneMdpDTO() {
        super();
    }
    
    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR ID OR PRIMARY KEY 
    //----------------------------------------------------------------------
    /**
     * Set the "idTransazione" field value
     * This field is mapped on the database column "id_transazione" ( type "varchar", NotNull : true ) 
     * @param idTransazione
     */
	public void setIdTransazione( String idTransazione ) {
        this.idTransazione = idTransazione ;
    }
    /**
     * Get the "idTransazione" field value
     * This field is mapped on the database column "id_transazione" ( type "varchar", NotNull : true ) 
     * @return the field value
     */
	public String getIdTransazione() {
        return this.idTransazione;
    }

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR OTHER DATA FIELDS 
    //----------------------------------------------------------------------
    
    /**
     * Set the "iuv" field value
     * This field is mapped on the database column "iuv" ( type "varchar", NotNull : true ) 
     * @param iuv
     */
    public void setIuv( String iuv ) {
        this.iuv = iuv;
    }
    /**
     * Get the "iuv" field value
     * This field is mapped on the database column "iuv" ( type "varchar", NotNull : true ) 
     * @return the field value
     */
    public String getIuv() {
        return this.iuv;
    }

    /**
     * Set the "idGateway" field value
     * This field is mapped on the database column "id_gateway" ( type "varchar", NotNull : false ) 
     * @param idGateway
     */
    public void setIdGateway( String idGateway ) {
        this.idGateway = idGateway;
    }
    /**
     * Get the "idGateway" field value
     * This field is mapped on the database column "id_gateway" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getIdGateway() {
        return this.idGateway;
    }

    /**
     * Set the "idPaymentMode" field value
     * This field is mapped on the database column "id_payment_mode" ( type "varchar", NotNull : false ) 
     * @param idPaymentMode
     */
    public void setIdPaymentMode( String idPaymentMode ) {
        this.idPaymentMode = idPaymentMode;
    }
    /**
     * Get the "idPaymentMode" field value
     * This field is mapped on the database column "id_payment_mode" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getIdPaymentMode() {
        return this.idPaymentMode;
    }

    /**
     * Set the "idInformativaPsp" field value
     * This field is mapped on the database column "id_informativa_psp" ( type "int4", NotNull : false ) 
     * @param idInformativaPsp
     */
    public void setIdInformativaPsp( Integer idInformativaPsp ) {
        this.idInformativaPsp = idInformativaPsp;
    }
    /**
     * Get the "idInformativaPsp" field value
     * This field is mapped on the database column "id_informativa_psp" ( type "int4", NotNull : false ) 
     * @return the field value
     */
    public Integer getIdInformativaPsp() {
        return this.idInformativaPsp;
    }

    /**
     * Set the "identificativoPsp" field value
     * This field is mapped on the database column "identificativo_psp" ( type "varchar", NotNull : false ) 
     * @param identificativoPsp
     */
    public void setIdentificativoPsp( String identificativoPsp ) {
        this.identificativoPsp = identificativoPsp;
    }
    /**
     * Get the "identificativoPsp" field value
     * This field is mapped on the database column "identificativo_psp" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getIdentificativoPsp() {
        return this.identificativoPsp;
    }

    /**
     * Set the "ragioneSocialePsp" field value
     * This field is mapped on the database column "ragione_sociale_psp" ( type "varchar", NotNull : false ) 
     * @param ragioneSocialePsp
     */
    public void setRagioneSocialePsp( String ragioneSocialePsp ) {
        this.ragioneSocialePsp = ragioneSocialePsp;
    }
    /**
     * Get the "ragioneSocialePsp" field value
     * This field is mapped on the database column "ragione_sociale_psp" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getRagioneSocialePsp() {
        return this.ragioneSocialePsp;
    }

    /**
     * Set the "identificativoCanalePsp" field value
     * This field is mapped on the database column "identificativo_canale_psp" ( type "varchar", NotNull : false ) 
     * @param identificativoCanalePsp
     */
    public void setIdentificativoCanalePsp( String identificativoCanalePsp ) {
        this.identificativoCanalePsp = identificativoCanalePsp;
    }
    /**
     * Get the "identificativoCanalePsp" field value
     * This field is mapped on the database column "identificativo_canale_psp" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getIdentificativoCanalePsp() {
        return this.identificativoCanalePsp;
    }

    /**
     * Set the "tipoVersamentoPsp" field value
     * This field is mapped on the database column "tipo_versamento_psp" ( type "varchar", NotNull : false ) 
     * @param tipoVersamentoPsp
     */
    public void setTipoVersamentoPsp( String tipoVersamentoPsp ) {
        this.tipoVersamentoPsp = tipoVersamentoPsp;
    }
    /**
     * Get the "tipoVersamentoPsp" field value
     * This field is mapped on the database column "tipo_versamento_psp" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getTipoVersamentoPsp() {
        return this.tipoVersamentoPsp;
    }

    /**
     * Set the "modelloPagamentoPsp" field value
     * This field is mapped on the database column "modello_pagamento_psp" ( type "int4", NotNull : false ) 
     * @param modelloPagamentoPsp
     */
    public void setModelloPagamentoPsp( Integer modelloPagamentoPsp ) {
        this.modelloPagamentoPsp = modelloPagamentoPsp;
    }
    /**
     * Get the "modelloPagamentoPsp" field value
     * This field is mapped on the database column "modello_pagamento_psp" ( type "int4", NotNull : false ) 
     * @return the field value
     */
    public Integer getModelloPagamentoPsp() {
        return this.modelloPagamentoPsp;
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

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    @Override
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(idTransazione);
        sb.append("|");
        sb.append(iuv);
        sb.append("|");
        sb.append(idGateway);
        sb.append("|");
        sb.append(idPaymentMode);
        sb.append("|");
        sb.append(idInformativaPsp);
        sb.append("|");
        sb.append(identificativoPsp);
        sb.append("|");
        sb.append(ragioneSocialePsp);
        sb.append("|");
        sb.append(identificativoCanalePsp);
        sb.append("|");
        sb.append(tipoVersamentoPsp);
        sb.append("|");
        sb.append(modelloPagamentoPsp);
        return sb.toString(); 
    } 



}
