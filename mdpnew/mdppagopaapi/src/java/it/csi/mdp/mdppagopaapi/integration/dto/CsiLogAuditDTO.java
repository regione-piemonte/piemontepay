/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.dto;

import java.io.Serializable;
import javax.validation.constraints.*;

import java.util.Date;
/**
 * DTO for entity "csi_log_audit" <br>
 * Contains only "wrapper types" (no primitive types) <br>
 * Can be used both as a "web form" and "persistence record" <br>
 * 
 * @author fabio.fenoglio
 *
 */
public class CsiLogAuditDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Long idLog ; // Id or Primary Key

    @NotNull
    private Date dataOra ; 
    @NotNull
    @Size( min = 1, max = 100 )
    private String idApp ; 
    @Size( max = 40 )
    private String ipAddress ; 
    @NotNull
    @Size( min = 1, max = 100 )
    private String utente ; 
    @NotNull
    @Size( min = 1, max = 50 )
    private String operazione ; 
    @NotNull
    @Size( min = 1, max = 500 )
    private String oggOper ; 
    @NotNull
    @Size( min = 1, max = 500 )
    private String keyOper ; 


    /**
     * Default constructor
     */
    public CsiLogAuditDTO() {
        super();
    }
    
    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR ID OR PRIMARY KEY 
    //----------------------------------------------------------------------
    /**
     * Set the "idLog" field value
     * This field is mapped on the database column "id_log" ( type "bigserial", NotNull : true ) 
     * @param idLog
     */
	public void setIdLog( Long idLog ) {
        this.idLog = idLog ;
    }
    /**
     * Get the "idLog" field value
     * This field is mapped on the database column "id_log" ( type "bigserial", NotNull : true ) 
     * @return the field value
     */
	public Long getIdLog() {
        return this.idLog;
    }

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR OTHER DATA FIELDS 
    //----------------------------------------------------------------------
    
    /**
     * Set the "dataOra" field value
     * This field is mapped on the database column "data_ora" ( type "timestamp", NotNull : true ) 
     * @param dataOra
     */
    public void setDataOra( Date dataOra ) {
        this.dataOra = dataOra;
    }
    /**
     * Get the "dataOra" field value
     * This field is mapped on the database column "data_ora" ( type "timestamp", NotNull : true ) 
     * @return the field value
     */
    public Date getDataOra() {
        return this.dataOra;
    }

    /**
     * Set the "idApp" field value
     * This field is mapped on the database column "id_app" ( type "varchar", NotNull : true ) 
     * @param idApp
     */
    public void setIdApp( String idApp ) {
        this.idApp = idApp;
    }
    /**
     * Get the "idApp" field value
     * This field is mapped on the database column "id_app" ( type "varchar", NotNull : true ) 
     * @return the field value
     */
    public String getIdApp() {
        return this.idApp;
    }

    /**
     * Set the "ipAddress" field value
     * This field is mapped on the database column "ip_address" ( type "varchar", NotNull : false ) 
     * @param ipAddress
     */
    public void setIpAddress( String ipAddress ) {
        this.ipAddress = ipAddress;
    }
    /**
     * Get the "ipAddress" field value
     * This field is mapped on the database column "ip_address" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getIpAddress() {
        return this.ipAddress;
    }

    /**
     * Set the "utente" field value
     * This field is mapped on the database column "utente" ( type "varchar", NotNull : true ) 
     * @param utente
     */
    public void setUtente( String utente ) {
        this.utente = utente;
    }
    /**
     * Get the "utente" field value
     * This field is mapped on the database column "utente" ( type "varchar", NotNull : true ) 
     * @return the field value
     */
    public String getUtente() {
        return this.utente;
    }

    /**
     * Set the "operazione" field value
     * This field is mapped on the database column "operazione" ( type "varchar", NotNull : true ) 
     * @param operazione
     */
    public void setOperazione( String operazione ) {
        this.operazione = operazione;
    }
    /**
     * Get the "operazione" field value
     * This field is mapped on the database column "operazione" ( type "varchar", NotNull : true ) 
     * @return the field value
     */
    public String getOperazione() {
        return this.operazione;
    }

    /**
     * Set the "oggOper" field value
     * This field is mapped on the database column "ogg_oper" ( type "varchar", NotNull : true ) 
     * @param oggOper
     */
    public void setOggOper( String oggOper ) {
        this.oggOper = oggOper;
    }
    /**
     * Get the "oggOper" field value
     * This field is mapped on the database column "ogg_oper" ( type "varchar", NotNull : true ) 
     * @return the field value
     */
    public String getOggOper() {
        return this.oggOper;
    }

    /**
     * Set the "keyOper" field value
     * This field is mapped on the database column "key_oper" ( type "varchar", NotNull : true ) 
     * @param keyOper
     */
    public void setKeyOper( String keyOper ) {
        this.keyOper = keyOper;
    }
    /**
     * Get the "keyOper" field value
     * This field is mapped on the database column "key_oper" ( type "varchar", NotNull : true ) 
     * @return the field value
     */
    public String getKeyOper() {
        return this.keyOper;
    }


    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR RELATIONSHIPS
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    @Override
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(idLog);
        sb.append("|");
        sb.append(dataOra);
        sb.append("|");
        sb.append(idApp);
        sb.append("|");
        sb.append(ipAddress);
        sb.append("|");
        sb.append(utente);
        sb.append("|");
        sb.append(operazione);
        sb.append("|");
        sb.append(oggOper);
        sb.append("|");
        sb.append(keyOper);
        return sb.toString(); 
    } 



}
