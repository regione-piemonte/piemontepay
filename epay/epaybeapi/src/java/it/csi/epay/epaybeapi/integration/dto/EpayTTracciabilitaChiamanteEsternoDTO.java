/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.dto;

import java.io.Serializable;
import javax.validation.constraints.*;

import java.util.Date;
/**
 * DTO for entity "epay_t_tracciabilita_chiamante_esterno" <br>
 * Contains only "wrapper types" (no primitive types) <br>
 * Can be used both as a "web form" and "persistence record" <br>
 * 
 * @author EII
 *
 */
public class EpayTTracciabilitaChiamanteEsternoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Long idChiamata ; // Id or Primary Key

    @Size( max = 100 )
    private String digest ; 
    @Size( max = 35 )
    private String iuv ; 
    @Size( max = 35 )
    private String codiceFiscale ; 
    @Size( max = 50 )
    private String idTransazione ; 
    @NotNull
    private Date timestampChiamata ; 
    @Size( max = 500 )
    private String remoteHost ; 
    @NotNull
    @Size( min = 1, max = 50 )
    private String identificativoPagamento ; 

	private EpayDChiamanteEsternoReferenceDTO epayDChiamanteEsterno ;


    /**
     * Default constructor
     */
    public EpayTTracciabilitaChiamanteEsternoDTO() {
        super();
    }
    
    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR ID OR PRIMARY KEY 
    //----------------------------------------------------------------------
    /**
     * Set the "idChiamata" field value
     * This field is mapped on the database column "id_chiamata" ( type "bigserial", NotNull : true ) 
     * @param idChiamata
     */
	public void setIdChiamata( Long idChiamata ) {
        this.idChiamata = idChiamata ;
    }
    /**
     * Get the "idChiamata" field value
     * This field is mapped on the database column "id_chiamata" ( type "bigserial", NotNull : true ) 
     * @return the field value
     */
	public Long getIdChiamata() {
        return this.idChiamata;
    }

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR OTHER DATA FIELDS 
    //----------------------------------------------------------------------
    
    /**
     * Set the "digest" field value
     * This field is mapped on the database column "digest" ( type "varchar", NotNull : false ) 
     * @param digest
     */
    public void setDigest( String digest ) {
        this.digest = digest;
    }
    /**
     * Get the "digest" field value
     * This field is mapped on the database column "digest" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getDigest() {
        return this.digest;
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
     * Set the "codiceFiscale" field value
     * This field is mapped on the database column "codice_fiscale" ( type "varchar", NotNull : false ) 
     * @param codiceFiscale
     */
    public void setCodiceFiscale( String codiceFiscale ) {
        this.codiceFiscale = codiceFiscale;
    }
    /**
     * Get the "codiceFiscale" field value
     * This field is mapped on the database column "codice_fiscale" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getCodiceFiscale() {
        return this.codiceFiscale;
    }

    /**
     * Set the "idTransazione" field value
     * This field is mapped on the database column "id_transazione" ( type "varchar", NotNull : false ) 
     * @param idTransazione
     */
    public void setIdTransazione( String idTransazione ) {
        this.idTransazione = idTransazione;
    }
    /**
     * Get the "idTransazione" field value
     * This field is mapped on the database column "id_transazione" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getIdTransazione() {
        return this.idTransazione;
    }

    /**
     * Set the "timestampChiamata" field value
     * This field is mapped on the database column "timestamp_chiamata" ( type "timestamp", NotNull : true ) 
     * @param timestampChiamata
     */
    public void setTimestampChiamata( Date timestampChiamata ) {
        this.timestampChiamata = timestampChiamata;
    }
    /**
     * Get the "timestampChiamata" field value
     * This field is mapped on the database column "timestamp_chiamata" ( type "timestamp", NotNull : true ) 
     * @return the field value
     */
    public Date getTimestampChiamata() {
        return this.timestampChiamata;
    }

    /**
     * Set the "remoteHost" field value
     * This field is mapped on the database column "remote_host" ( type "varchar", NotNull : false ) 
     * @param remoteHost
     */
    public void setRemoteHost( String remoteHost ) {
        this.remoteHost = remoteHost;
    }
    /**
     * Get the "remoteHost" field value
     * This field is mapped on the database column "remote_host" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getRemoteHost() {
        return this.remoteHost;
    }

    /**
     * Set the "identificativoPagamento" field value
     * This field is mapped on the database column "identificativo_pagamento" ( type "varchar", NotNull : true ) 
     * @param identificativoPagamento
     */
    public void setIdentificativoPagamento( String identificativoPagamento ) {
        this.identificativoPagamento = identificativoPagamento;
    }
    /**
     * Get the "identificativoPagamento" field value
     * This field is mapped on the database column "identificativo_pagamento" ( type "varchar", NotNull : true ) 
     * @return the field value
     */
    public String getIdentificativoPagamento() {
        return this.identificativoPagamento;
    }


    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR RELATIONSHIPS
    //----------------------------------------------------------------------
    public void setEpayDChiamanteEsterno( EpayDChiamanteEsternoReferenceDTO epayDChiamanteEsterno ) {
        this.epayDChiamanteEsterno = epayDChiamanteEsterno;
    }
    public EpayDChiamanteEsternoReferenceDTO getEpayDChiamanteEsterno() {
        return this.epayDChiamanteEsterno;
    }

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    @Override
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(idChiamata);
        sb.append("|");
        sb.append(digest);
        sb.append("|");
        sb.append(iuv);
        sb.append("|");
        sb.append(codiceFiscale);
        sb.append("|");
        sb.append(idTransazione);
        sb.append("|");
        sb.append(timestampChiamata);
        sb.append("|");
        sb.append(remoteHost);
        sb.append("|");
        sb.append(identificativoPagamento);
        return sb.toString(); 
    } 



}
