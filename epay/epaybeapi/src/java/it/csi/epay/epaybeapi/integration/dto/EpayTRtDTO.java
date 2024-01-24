/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.dto;

import java.io.Serializable;
import javax.validation.constraints.*;

import java.util.Date;
/**
 * DTO for entity "epay_t_rt" <br>
 * Contains only "wrapper types" (no primitive types) <br>
 * Can be used both as a "web form" and "persistence record" <br>
 * 
 * @author EII
 *
 */
public class EpayTRtDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Long idRt ; // Id or Primary Key


    private byte[] ricevutaPdf ; 
    @NotNull
    private byte[] rtXml ; 
    @Size( max = 50 )
    private String idApplicazione ; 
    @Size( max = 30 )
    private String idTransazione ; 

    private Date dataoraMsgRicevuta ; 
    @Size( max = 35 )
    private String idMsgRicevuta ; 
    @Size( max = 10 )
    private String tipoFirma ; 
    @Size( max = 35 )
    private String iuv ; 

    private Integer codEsitoPagamento ; 
    @Size( max = 255 )
    private String descEsitoPagamento ; 
    @Size( max = 35 )
    private String idMsgRichiesta ; 

	private EpayTRegistroVersamentiReferenceDTO epayTRegistroVersamenti ;


    /**
     * Default constructor
     */
    public EpayTRtDTO() {
        super();
    }
    
    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR ID OR PRIMARY KEY 
    //----------------------------------------------------------------------
    /**
     * Set the "idRt" field value
     * This field is mapped on the database column "id_rt" ( type "bigserial", NotNull : true ) 
     * @param idRt
     */
	public void setIdRt( Long idRt ) {
        this.idRt = idRt ;
    }
    /**
     * Get the "idRt" field value
     * This field is mapped on the database column "id_rt" ( type "bigserial", NotNull : true ) 
     * @return the field value
     */
	public Long getIdRt() {
        return this.idRt;
    }

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR OTHER DATA FIELDS 
    //----------------------------------------------------------------------
    
    /**
     * Set the "ricevutaPdf" field value
     * This field is mapped on the database column "ricevuta_pdf" ( type "bytea", NotNull : false ) 
     * @param ricevutaPdf
     */
    public void setRicevutaPdf( byte[] ricevutaPdf ) {
        this.ricevutaPdf = ricevutaPdf;
    }
    /**
     * Get the "ricevutaPdf" field value
     * This field is mapped on the database column "ricevuta_pdf" ( type "bytea", NotNull : false ) 
     * @return the field value
     */
    public byte[] getRicevutaPdf() {
        return this.ricevutaPdf;
    }

    /**
     * Set the "rtXml" field value
     * This field is mapped on the database column "rt_xml" ( type "bytea", NotNull : true ) 
     * @param rtXml
     */
    public void setRtXml( byte[] rtXml ) {
        this.rtXml = rtXml;
    }
    /**
     * Get the "rtXml" field value
     * This field is mapped on the database column "rt_xml" ( type "bytea", NotNull : true ) 
     * @return the field value
     */
    public byte[] getRtXml() {
        return this.rtXml;
    }

    /**
     * Set the "idApplicazione" field value
     * This field is mapped on the database column "id_applicazione" ( type "varchar", NotNull : false ) 
     * @param idApplicazione
     */
    public void setIdApplicazione( String idApplicazione ) {
        this.idApplicazione = idApplicazione;
    }
    /**
     * Get the "idApplicazione" field value
     * This field is mapped on the database column "id_applicazione" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getIdApplicazione() {
        return this.idApplicazione;
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
     * Set the "dataoraMsgRicevuta" field value
     * This field is mapped on the database column "dataora_msg_ricevuta" ( type "timestamp", NotNull : false ) 
     * @param dataoraMsgRicevuta
     */
    public void setDataoraMsgRicevuta( Date dataoraMsgRicevuta ) {
        this.dataoraMsgRicevuta = dataoraMsgRicevuta;
    }
    /**
     * Get the "dataoraMsgRicevuta" field value
     * This field is mapped on the database column "dataora_msg_ricevuta" ( type "timestamp", NotNull : false ) 
     * @return the field value
     */
    public Date getDataoraMsgRicevuta() {
        return this.dataoraMsgRicevuta;
    }

    /**
     * Set the "idMsgRicevuta" field value
     * This field is mapped on the database column "id_msg_ricevuta" ( type "varchar", NotNull : false ) 
     * @param idMsgRicevuta
     */
    public void setIdMsgRicevuta( String idMsgRicevuta ) {
        this.idMsgRicevuta = idMsgRicevuta;
    }
    /**
     * Get the "idMsgRicevuta" field value
     * This field is mapped on the database column "id_msg_ricevuta" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getIdMsgRicevuta() {
        return this.idMsgRicevuta;
    }

    /**
     * Set the "tipoFirma" field value
     * This field is mapped on the database column "tipo_firma" ( type "varchar", NotNull : false ) 
     * @param tipoFirma
     */
    public void setTipoFirma( String tipoFirma ) {
        this.tipoFirma = tipoFirma;
    }
    /**
     * Get the "tipoFirma" field value
     * This field is mapped on the database column "tipo_firma" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getTipoFirma() {
        return this.tipoFirma;
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
     * Set the "codEsitoPagamento" field value
     * This field is mapped on the database column "cod_esito_pagamento" ( type "int4", NotNull : false ) 
     * @param codEsitoPagamento
     */
    public void setCodEsitoPagamento( Integer codEsitoPagamento ) {
        this.codEsitoPagamento = codEsitoPagamento;
    }
    /**
     * Get the "codEsitoPagamento" field value
     * This field is mapped on the database column "cod_esito_pagamento" ( type "int4", NotNull : false ) 
     * @return the field value
     */
    public Integer getCodEsitoPagamento() {
        return this.codEsitoPagamento;
    }

    /**
     * Set the "descEsitoPagamento" field value
     * This field is mapped on the database column "desc_esito_pagamento" ( type "varchar", NotNull : false ) 
     * @param descEsitoPagamento
     */
    public void setDescEsitoPagamento( String descEsitoPagamento ) {
        this.descEsitoPagamento = descEsitoPagamento;
    }
    /**
     * Get the "descEsitoPagamento" field value
     * This field is mapped on the database column "desc_esito_pagamento" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getDescEsitoPagamento() {
        return this.descEsitoPagamento;
    }

    /**
     * Set the "idMsgRichiesta" field value
     * This field is mapped on the database column "id_msg_richiesta" ( type "varchar", NotNull : false ) 
     * @param idMsgRichiesta
     */
    public void setIdMsgRichiesta( String idMsgRichiesta ) {
        this.idMsgRichiesta = idMsgRichiesta;
    }
    /**
     * Get the "idMsgRichiesta" field value
     * This field is mapped on the database column "id_msg_richiesta" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getIdMsgRichiesta() {
        return this.idMsgRichiesta;
    }


    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR RELATIONSHIPS
    //----------------------------------------------------------------------
    public void setEpayTRegistroVersamenti( EpayTRegistroVersamentiReferenceDTO epayTRegistroVersamenti ) {
        this.epayTRegistroVersamenti = epayTRegistroVersamenti;
    }
    public EpayTRegistroVersamentiReferenceDTO getEpayTRegistroVersamenti() {
        return this.epayTRegistroVersamenti;
    }

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    @Override
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(idRt);
        // attribute 'ricevutaPdf' not usable (type = byte[])
        // attribute 'rtXml' not usable (type = byte[])
        sb.append("|");
        sb.append(idApplicazione);
        sb.append("|");
        sb.append(idTransazione);
        sb.append("|");
        sb.append(dataoraMsgRicevuta);
        sb.append("|");
        sb.append(idMsgRicevuta);
        sb.append("|");
        sb.append(tipoFirma);
        sb.append("|");
        sb.append(iuv);
        sb.append("|");
        sb.append(codEsitoPagamento);
        sb.append("|");
        sb.append(descEsitoPagamento);
        sb.append("|");
        sb.append(idMsgRichiesta);
        return sb.toString(); 
    } 



}
