/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.dto;

import java.io.Serializable;
import javax.validation.constraints.*;

import java.math.BigDecimal;
import java.util.Date;
/**
 * DTO for entity "epay_t_esiti_ricevuti" <br>
 * Contains only "wrapper types" (no primitive types) <br>
 * Can be used both as a "web form" and "persistence record" <br>
 * 
 * @author fabio.fenoglio
 *
 */
public class EpayTEsitiRicevutiDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Long idEsito ; // Id or Primary Key

    @NotNull
    private Integer idModalitaRicezione ; 
    @Size( max = 30 )
    private String idApplicazione ; 
    @Size( max = 50 )
    private String idTransazione ; 

    private Date dataPagamento ; 

    private Integer codEsitoPagamento ; 
    @Size( max = 255 )
    private String descEsitoPagamento ; 

    private BigDecimal importo ; 
    @Size( max = 250 )
    private String identificativoPsp ; 
    @Size( max = 250 )
    private String ragioneSocialePsp ; 
    @Size( max = 35 )
    private String iur ; 

	private EpayDModalitaPagamentoReferenceDTO epayDModalitaPagamento ;

	private EpayTRegistroVersamentiReferenceDTO epayTRegistroVersamenti ;


    /**
     * Default constructor
     */
    public EpayTEsitiRicevutiDTO() {
        super();
    }
    
    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR ID OR PRIMARY KEY 
    //----------------------------------------------------------------------
    /**
     * Set the "idEsito" field value
     * This field is mapped on the database column "id_esito" ( type "bigserial", NotNull : true ) 
     * @param idEsito
     */
	public void setIdEsito( Long idEsito ) {
        this.idEsito = idEsito ;
    }
    /**
     * Get the "idEsito" field value
     * This field is mapped on the database column "id_esito" ( type "bigserial", NotNull : true ) 
     * @return the field value
     */
	public Long getIdEsito() {
        return this.idEsito;
    }

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR OTHER DATA FIELDS 
    //----------------------------------------------------------------------
    
    /**
     * Set the "idModalitaRicezione" field value
     * This field is mapped on the database column "id_modalita_ricezione" ( type "int4", NotNull : true ) 
     * @param idModalitaRicezione
     */
    public void setIdModalitaRicezione( Integer idModalitaRicezione ) {
        this.idModalitaRicezione = idModalitaRicezione;
    }
    /**
     * Get the "idModalitaRicezione" field value
     * This field is mapped on the database column "id_modalita_ricezione" ( type "int4", NotNull : true ) 
     * @return the field value
     */
    public Integer getIdModalitaRicezione() {
        return this.idModalitaRicezione;
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
     * Set the "dataPagamento" field value
     * This field is mapped on the database column "data_pagamento" ( type "timestamp", NotNull : false ) 
     * @param dataPagamento
     */
    public void setDataPagamento( Date dataPagamento ) {
        this.dataPagamento = dataPagamento;
    }
    /**
     * Get the "dataPagamento" field value
     * This field is mapped on the database column "data_pagamento" ( type "timestamp", NotNull : false ) 
     * @return the field value
     */
    public Date getDataPagamento() {
        return this.dataPagamento;
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
     * Set the "importo" field value
     * This field is mapped on the database column "importo" ( type "numeric", NotNull : false ) 
     * @param importo
     */
    public void setImporto( BigDecimal importo ) {
        this.importo = importo;
    }
    /**
     * Get the "importo" field value
     * This field is mapped on the database column "importo" ( type "numeric", NotNull : false ) 
     * @return the field value
     */
    public BigDecimal getImporto() {
        return this.importo;
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
     * Set the "iur" field value
     * This field is mapped on the database column "iur" ( type "varchar", NotNull : false ) 
     * @param iur
     */
    public void setIur( String iur ) {
        this.iur = iur;
    }
    /**
     * Get the "iur" field value
     * This field is mapped on the database column "iur" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getIur() {
        return this.iur;
    }


    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR RELATIONSHIPS
    //----------------------------------------------------------------------
    public void setEpayDModalitaPagamento( EpayDModalitaPagamentoReferenceDTO epayDModalitaPagamento ) {
        this.epayDModalitaPagamento = epayDModalitaPagamento;
    }
    public EpayDModalitaPagamentoReferenceDTO getEpayDModalitaPagamento() {
        return this.epayDModalitaPagamento;
    }
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
        sb.append(idEsito);
        sb.append("|");
        sb.append(idModalitaRicezione);
        sb.append("|");
        sb.append(idApplicazione);
        sb.append("|");
        sb.append(idTransazione);
        sb.append("|");
        sb.append(dataPagamento);
        sb.append("|");
        sb.append(codEsitoPagamento);
        sb.append("|");
        sb.append(descEsitoPagamento);
        sb.append("|");
        sb.append(importo);
        sb.append("|");
        sb.append(identificativoPsp);
        sb.append("|");
        sb.append(ragioneSocialePsp);
        sb.append("|");
        sb.append(iur);
        return sb.toString(); 
    } 



}
