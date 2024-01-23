/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;


@XStreamAlias("rr")
public class RR implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -6888017310256192951L;
    
    @XStreamAlias("id_rr")
    private Integer id;
    
    @XStreamAlias("id_dominio")
    private String idDominio;
    
    @XStreamAlias("application_id")
    private String applicationId;
    
    @XStreamAlias("identificativo_messaggio_revoca")
    private String identificativoMessaggioRevoca;
    
    @XStreamAlias("data_ora_messaggio_revoca")
    private Timestamp dataOraMessaggioRevoca;
    
    @XStreamAlias("codice_identificativo_univoco_attestante")
    private String  codiceIdentificativoUnivocoAttestante;
     
    @XStreamAlias("denominazione_istituto_attestante")
    private String denominazioneIstitutoAttestante;
    
    @XStreamAlias("importo_totale_revocato")
    private Double importoTotaleRevocato;
    
    @XStreamAlias("iuv")
    private String iuv;
    
    @XStreamAlias("codice_contesto_pagamento")
    private String codiceContestoPagamento;
    
    @XStreamAlias("tipo_revoca")
    private Double tipoRevoca;
    
    @XStreamAlias("lista_dati_singola_revoca")
    private List<DatiSingolaRevoca> listaDatiSingolaRevoca = new ArrayList<DatiSingolaRevoca>();
        
    private byte[] xmlRR;
    


    
    public List<DatiSingolaRevoca> getListaDatiSingolaRevoca () {
        return listaDatiSingolaRevoca;
    }


    
    public void setListaDatiSingolaRevoca ( List<DatiSingolaRevoca> listaDatiSingolaRevoca ) {
        this.listaDatiSingolaRevoca = listaDatiSingolaRevoca;
    }


    public Integer getId () {
        return id;
    }

    
    public void setId ( Integer id ) {
        this.id = id;
    }

    
    public String getIdDominio () {
        return idDominio;
    }

    
    public void setIdDominio ( String idDominio ) {
        this.idDominio = idDominio;
    }

    
    public String getApplicationId () {
        return applicationId;
    }

    
    public void setApplicationId ( String applicationId ) {
        this.applicationId = applicationId;
    }

    
    public String getIdentificativoMessaggioRevoca () {
        return identificativoMessaggioRevoca;
    }

    
    public void setIdentificativoMessaggioRevoca ( String identificativoMessaggioRevoca ) {
        this.identificativoMessaggioRevoca = identificativoMessaggioRevoca;
    }

    
    public Timestamp getDataOraMessaggioRevoca () {
        return dataOraMessaggioRevoca;
    }

    
    public void setDataOraMessaggioRevoca ( Timestamp dataOraMessaggioRevoca ) {
        this.dataOraMessaggioRevoca = dataOraMessaggioRevoca;
    }

    
    public String getCodiceIdentificativoUnivocoAttestante () {
        return codiceIdentificativoUnivocoAttestante;
    }

    
    public void setCodiceIdentificativoUnivocoAttestante ( String codiceIdentificativoUnivocoAttestante ) {
        this.codiceIdentificativoUnivocoAttestante = codiceIdentificativoUnivocoAttestante;
    }

    
    public String getDenominazioneIstitutoAttestante () {
        return denominazioneIstitutoAttestante;
    }

    
    public void setDenominazioneIstitutoAttestante ( String denominazioneIstitutoAttestante ) {
        this.denominazioneIstitutoAttestante = denominazioneIstitutoAttestante;
    }

    
    public Double getImportoTotaleRevocato () {
        return importoTotaleRevocato;
    }

    
    public void setImportoTotaleRevocato ( Double importoTotaleRevocato ) {
        this.importoTotaleRevocato = importoTotaleRevocato;
    }

    
    public String getIuv () {
        return iuv;
    }

    
    public void setIuv ( String iuv ) {
        this.iuv = iuv;
    }

    
    public String getCodiceContestoPagamento () {
        return codiceContestoPagamento;
    }

    
    public void setCodiceContestoPagamento ( String codiceContestoPagamento ) {
        this.codiceContestoPagamento = codiceContestoPagamento;
    }

    
    public Double getTipoRevoca () {
        return tipoRevoca;
    }

    
    public void setTipoRevoca ( Double tipoRevoca ) {
        this.tipoRevoca = tipoRevoca;
    }

    
    public byte [] getXmlRR () {
        return xmlRR;
    }

    
    public void setXmlRR ( byte [] xmlRR ) {
        this.xmlRR = xmlRR;
    }

    
    public static long getSerialversionuid () {
        return serialVersionUID;
    }
    
        
}
