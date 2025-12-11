/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.dto;

import java.io.Serializable;
import javax.validation.constraints.*;

import java.util.Date;
import java.util.List;

/**
 * DTO for entity "epay_t_registro_versamenti" <br>
 * Contains only "wrapper types" (no primitive types) <br>
 * Can be used both as a "web form" and "persistence record" <br>
 * 
 * @author fabio.fenoglio
 *
 */
public class EpayTRegistroVersamentiDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Long idRegistro ; // Id or Primary Key

    @NotNull
    private Date dataOperazione ; 
    @Size( max = 25 )
    private String iuv ; 
    @Size( max = 255 )
    private String descEsitoPagamento ; 
    @Size( max = 255 )
    private String origineInserimento ; 

    private List<EpayTEsitiRicevutiReferenceDTO> listOfEpayTEsitiRicevuti ;

	private EpayTTransazioneMdpReferenceDTO epayTTransazioneMdp ;

	private EpayTPagamentoReferenceDTO epayTPagamento ;

	private EpayDStatoPagamentoReferenceDTO epayDStatoPagamento ;

	private EpayTAnagraficaReferenceDTO epayTAnagrafica ;

    private List<EpayTRtReferenceDTO> listOfEpayTRt ;


    /**
     * Default constructor
     */
    public EpayTRegistroVersamentiDTO() {
        super();
    }
    
    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR ID OR PRIMARY KEY 
    //----------------------------------------------------------------------
    /**
     * Set the "idRegistro" field value
     * This field is mapped on the database column "id_registro" ( type "bigserial", NotNull : true ) 
     * @param idRegistro
     */
	public void setIdRegistro( Long idRegistro ) {
        this.idRegistro = idRegistro ;
    }
    /**
     * Get the "idRegistro" field value
     * This field is mapped on the database column "id_registro" ( type "bigserial", NotNull : true ) 
     * @return the field value
     */
	public Long getIdRegistro() {
        return this.idRegistro;
    }

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR OTHER DATA FIELDS 
    //----------------------------------------------------------------------
    
    /**
     * Set the "dataOperazione" field value
     * This field is mapped on the database column "data_operazione" ( type "timestamp", NotNull : true ) 
     * @param dataOperazione
     */
    public void setDataOperazione( Date dataOperazione ) {
        this.dataOperazione = dataOperazione;
    }
    /**
     * Get the "dataOperazione" field value
     * This field is mapped on the database column "data_operazione" ( type "timestamp", NotNull : true ) 
     * @return the field value
     */
    public Date getDataOperazione() {
        return this.dataOperazione;
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
     * Set the "origineInserimento" field value
     * This field is mapped on the database column "origine_inserimento" ( type "varchar", NotNull : false ) 
     * @param origineInserimento
     */
    public void setOrigineInserimento( String origineInserimento ) {
        this.origineInserimento = origineInserimento;
    }
    /**
     * Get the "origineInserimento" field value
     * This field is mapped on the database column "origine_inserimento" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getOrigineInserimento() {
        return this.origineInserimento;
    }


    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR RELATIONSHIPS
    //----------------------------------------------------------------------
    public void setListOfEpayTEsitiRicevuti( List<EpayTEsitiRicevutiReferenceDTO> listOfEpayTEsitiRicevuti ) {
        this.listOfEpayTEsitiRicevuti = listOfEpayTEsitiRicevuti;
    }
    public List<EpayTEsitiRicevutiReferenceDTO> getListOfEpayTEsitiRicevuti() {
        return this.listOfEpayTEsitiRicevuti;
    }
    public void setEpayTTransazioneMdp( EpayTTransazioneMdpReferenceDTO epayTTransazioneMdp ) {
        this.epayTTransazioneMdp = epayTTransazioneMdp;
    }
    public EpayTTransazioneMdpReferenceDTO getEpayTTransazioneMdp() {
        return this.epayTTransazioneMdp;
    }
    public void setEpayTPagamento( EpayTPagamentoReferenceDTO epayTPagamento ) {
        this.epayTPagamento = epayTPagamento;
    }
    public EpayTPagamentoReferenceDTO getEpayTPagamento() {
        return this.epayTPagamento;
    }
    public void setEpayDStatoPagamento( EpayDStatoPagamentoReferenceDTO epayDStatoPagamento ) {
        this.epayDStatoPagamento = epayDStatoPagamento;
    }
    public EpayDStatoPagamentoReferenceDTO getEpayDStatoPagamento() {
        return this.epayDStatoPagamento;
    }
    public void setEpayTAnagrafica( EpayTAnagraficaReferenceDTO epayTAnagrafica ) {
        this.epayTAnagrafica = epayTAnagrafica;
    }
    public EpayTAnagraficaReferenceDTO getEpayTAnagrafica() {
        return this.epayTAnagrafica;
    }
    public void setListOfEpayTRt( List<EpayTRtReferenceDTO> listOfEpayTRt ) {
        this.listOfEpayTRt = listOfEpayTRt;
    }
    public List<EpayTRtReferenceDTO> getListOfEpayTRt() {
        return this.listOfEpayTRt;
    }

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    @Override
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(dataOperazione);
        sb.append("|");
        sb.append(iuv);
        sb.append("|");
        sb.append(idRegistro);
        sb.append("|");
        sb.append(descEsitoPagamento);
        sb.append("|");
        sb.append(origineInserimento);
        return sb.toString(); 
    } 



}
