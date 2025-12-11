/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.dto;

import java.io.Serializable;
import javax.validation.constraints.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * DTO for entity "epay_t_registro_elaborazioni" <br>
 * Contains only "wrapper types" (no primitive types) <br>
 * Can be used both as a "web form" and "persistence record" <br>
 * 
 * @author fabio.fenoglio
 *
 */
public class EpayTRegistroElaborazioniDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Long id ; // Id or Primary Key

    @NotNull
    private Date dataInizio ; 

    private Date dataFine ; 
    @Size( max = 250 )
    private String operazione ; 
    @Size( max = 250 )
    private String idMessaggio ; 

    private Long idEnte ; 

    private Long idTipoPagamento ; 

    private Boolean pagamentiSpontanei ; 

    private Integer numPagamenti ; 

    private BigDecimal importoTotPagamenti ; 
    @NotNull
    @Size( min = 1, max = 250 )
    private String esito ; 
    @Size( max = 2000 )
    private String messageFault ; 

    private List<EpayTPagamentoReferenceDTO> listOfEpayTPagamento ;

    private List<EpayTRegistroElaborazioniFaultReferenceDTO> listOfEpayTRegistroElaborazioniFault ;


    /**
     * Default constructor
     */
    public EpayTRegistroElaborazioniDTO() {
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
     * Set the "dataInizio" field value
     * This field is mapped on the database column "data_inizio" ( type "timestamp", NotNull : true ) 
     * @param dataInizio
     */
    public void setDataInizio( Date dataInizio ) {
        this.dataInizio = dataInizio;
    }
    /**
     * Get the "dataInizio" field value
     * This field is mapped on the database column "data_inizio" ( type "timestamp", NotNull : true ) 
     * @return the field value
     */
    public Date getDataInizio() {
        return this.dataInizio;
    }

    /**
     * Set the "dataFine" field value
     * This field is mapped on the database column "data_fine" ( type "timestamp", NotNull : false ) 
     * @param dataFine
     */
    public void setDataFine( Date dataFine ) {
        this.dataFine = dataFine;
    }
    /**
     * Get the "dataFine" field value
     * This field is mapped on the database column "data_fine" ( type "timestamp", NotNull : false ) 
     * @return the field value
     */
    public Date getDataFine() {
        return this.dataFine;
    }

    /**
     * Set the "operazione" field value
     * This field is mapped on the database column "operazione" ( type "varchar", NotNull : false ) 
     * @param operazione
     */
    public void setOperazione( String operazione ) {
        this.operazione = operazione;
    }
    /**
     * Get the "operazione" field value
     * This field is mapped on the database column "operazione" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getOperazione() {
        return this.operazione;
    }

    /**
     * Set the "idMessaggio" field value
     * This field is mapped on the database column "id_messaggio" ( type "varchar", NotNull : false ) 
     * @param idMessaggio
     */
    public void setIdMessaggio( String idMessaggio ) {
        this.idMessaggio = idMessaggio;
    }
    /**
     * Get the "idMessaggio" field value
     * This field is mapped on the database column "id_messaggio" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getIdMessaggio() {
        return this.idMessaggio;
    }

    /**
     * Set the "idEnte" field value
     * This field is mapped on the database column "id_ente" ( type "int8", NotNull : false ) 
     * @param idEnte
     */
    public void setIdEnte( Long idEnte ) {
        this.idEnte = idEnte;
    }
    /**
     * Get the "idEnte" field value
     * This field is mapped on the database column "id_ente" ( type "int8", NotNull : false ) 
     * @return the field value
     */
    public Long getIdEnte() {
        return this.idEnte;
    }

    /**
     * Set the "idTipoPagamento" field value
     * This field is mapped on the database column "id_tipo_pagamento" ( type "int8", NotNull : false ) 
     * @param idTipoPagamento
     */
    public void setIdTipoPagamento( Long idTipoPagamento ) {
        this.idTipoPagamento = idTipoPagamento;
    }
    /**
     * Get the "idTipoPagamento" field value
     * This field is mapped on the database column "id_tipo_pagamento" ( type "int8", NotNull : false ) 
     * @return the field value
     */
    public Long getIdTipoPagamento() {
        return this.idTipoPagamento;
    }

    /**
     * Set the "pagamentiSpontanei" field value
     * This field is mapped on the database column "pagamenti_spontanei" ( type "bool", NotNull : false ) 
     * @param pagamentiSpontanei
     */
    public void setPagamentiSpontanei( Boolean pagamentiSpontanei ) {
        this.pagamentiSpontanei = pagamentiSpontanei;
    }
    /**
     * Get the "pagamentiSpontanei" field value
     * This field is mapped on the database column "pagamenti_spontanei" ( type "bool", NotNull : false ) 
     * @return the field value
     */
    public Boolean getPagamentiSpontanei() {
        return this.pagamentiSpontanei;
    }

    /**
     * Set the "numPagamenti" field value
     * This field is mapped on the database column "num_pagamenti" ( type "int4", NotNull : false ) 
     * @param numPagamenti
     */
    public void setNumPagamenti( Integer numPagamenti ) {
        this.numPagamenti = numPagamenti;
    }
    /**
     * Get the "numPagamenti" field value
     * This field is mapped on the database column "num_pagamenti" ( type "int4", NotNull : false ) 
     * @return the field value
     */
    public Integer getNumPagamenti() {
        return this.numPagamenti;
    }

    /**
     * Set the "importoTotPagamenti" field value
     * This field is mapped on the database column "importo_tot_pagamenti" ( type "numeric", NotNull : false ) 
     * @param importoTotPagamenti
     */
    public void setImportoTotPagamenti( BigDecimal importoTotPagamenti ) {
        this.importoTotPagamenti = importoTotPagamenti;
    }
    /**
     * Get the "importoTotPagamenti" field value
     * This field is mapped on the database column "importo_tot_pagamenti" ( type "numeric", NotNull : false ) 
     * @return the field value
     */
    public BigDecimal getImportoTotPagamenti() {
        return this.importoTotPagamenti;
    }

    /**
     * Set the "esito" field value
     * This field is mapped on the database column "esito" ( type "varchar", NotNull : true ) 
     * @param esito
     */
    public void setEsito( String esito ) {
        this.esito = esito;
    }
    /**
     * Get the "esito" field value
     * This field is mapped on the database column "esito" ( type "varchar", NotNull : true ) 
     * @return the field value
     */
    public String getEsito() {
        return this.esito;
    }

    /**
     * Set the "messageFault" field value
     * This field is mapped on the database column "message_fault" ( type "varchar", NotNull : false ) 
     * @param messageFault
     */
    public void setMessageFault( String messageFault ) {
        this.messageFault = messageFault;
    }
    /**
     * Get the "messageFault" field value
     * This field is mapped on the database column "message_fault" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getMessageFault() {
        return this.messageFault;
    }


    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR RELATIONSHIPS
    //----------------------------------------------------------------------
    public void setListOfEpayTPagamento( List<EpayTPagamentoReferenceDTO> listOfEpayTPagamento ) {
        this.listOfEpayTPagamento = listOfEpayTPagamento;
    }
    public List<EpayTPagamentoReferenceDTO> getListOfEpayTPagamento() {
        return this.listOfEpayTPagamento;
    }
    public void setListOfEpayTRegistroElaborazioniFault( List<EpayTRegistroElaborazioniFaultReferenceDTO> listOfEpayTRegistroElaborazioniFault ) {
        this.listOfEpayTRegistroElaborazioniFault = listOfEpayTRegistroElaborazioniFault;
    }
    public List<EpayTRegistroElaborazioniFaultReferenceDTO> getListOfEpayTRegistroElaborazioniFault() {
        return this.listOfEpayTRegistroElaborazioniFault;
    }

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    @Override
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
        sb.append(dataInizio);
        sb.append("|");
        sb.append(dataFine);
        sb.append("|");
        sb.append(operazione);
        sb.append("|");
        sb.append(idMessaggio);
        sb.append("|");
        sb.append(idEnte);
        sb.append("|");
        sb.append(idTipoPagamento);
        sb.append("|");
        sb.append(pagamentiSpontanei);
        sb.append("|");
        sb.append(numPagamenti);
        sb.append("|");
        sb.append(importoTotPagamenti);
        sb.append("|");
        sb.append(esito);
        sb.append("|");
        sb.append(messageFault);
        return sb.toString(); 
    } 



}
