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

@XStreamAlias("er")
public class ER implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 8149879100127331580L;
    
    @XStreamAlias("id_er")
    private Integer id;
    
    @XStreamAlias("id_dominio")
    private String idDominio;
    
    @XStreamAlias("application_id")
    private String applicationId;
    
    @XStreamAlias("identificativo_messaggio_esito")
    private String identificativoMessaggioEsito;
    
    @XStreamAlias("data_ora_messaggio_esito")
    private Timestamp dataOraMessaggioEsito;
    
    @XStreamAlias("riferimento_messaggio_revoca")
    private String riferimentoMessaggioRevoca;
    
    @XStreamAlias("riferimento_data_revoca")
    private Timestamp riferimentoDataRevoca;
    
    @XStreamAlias("codice_identificativo_univoco_attestante")
    private String codiceIdentificativoUnivocoAttestante;
    
    @XStreamAlias("denominazione_istituto_attestante")
    private String denominazioneIstitutoAttestante;
    
    @XStreamAlias("importo_totale_revocato")
    private Double importoTotaleRevocato;
    
    @XStreamAlias("iuv")
    private String iuv;
    
    @XStreamAlias("codice_contesto_pagamento")
    private String codiceContestoPagamento;
    
    private byte[] xmlEr;
    
    private Boolean invioOkRispostaRevoca;
    
    @XStreamAlias("lista_dati_singolo_esito")
    private List<DatiSingoloEsito> listaDatiSingoloEsito = new ArrayList<DatiSingoloEsito>();
    
    public List<DatiSingoloEsito> getListaDatiSingoloEsito () {
        return listaDatiSingoloEsito;
    }

    public void setListaDatiSingoloEsito ( List<DatiSingoloEsito> listaDatiSingoloEsito ) {
        this.listaDatiSingoloEsito = listaDatiSingoloEsito;
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

    
    public String getIdentificativoMessaggioEsito () {
        return identificativoMessaggioEsito;
    }

    
    public void setIdentificativoMessaggioEsito ( String identificativoMessaggioEsito ) {
        this.identificativoMessaggioEsito = identificativoMessaggioEsito;
    }

   
    public Timestamp getDataOraMessaggioEsito () {
        return dataOraMessaggioEsito;
    }

    
    public void setDataOraMessaggioEsito ( Timestamp dataOraMessaggioEsito ) {
        this.dataOraMessaggioEsito = dataOraMessaggioEsito;
    }
    
    public String getRiferimentoMessaggioRevoca () {
        return riferimentoMessaggioRevoca;
    }

    
    public void setRiferimentoMessaggioRevoca ( String riferimentoMessaggioRevoca ) {
        this.riferimentoMessaggioRevoca = riferimentoMessaggioRevoca;
    }

    
    public Timestamp getRiferimentoDataRevoca () {
        return riferimentoDataRevoca;
    }

    
    public void setRiferimentoDataRevoca ( Timestamp riferimentoDataRevoca ) {
        this.riferimentoDataRevoca = riferimentoDataRevoca;
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

    
    public byte [] getXmlEr () {
        return xmlEr;
    }

    
    public void setXmlEr ( byte [] xmlEr ) {
        this.xmlEr = xmlEr;
    }

    
    public Boolean getInvioOkRispostaRevoca () {
        return invioOkRispostaRevoca;
    }

    
    public void setInvioOkRispostaRevoca ( Boolean invioOkRispostaRevoca ) {
        this.invioOkRispostaRevoca = invioOkRispostaRevoca;
    }

    
    public static long getSerialversionuid () {
        return serialVersionUID;
    }
    
    

}
