/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class GetInformativePSPByParam  implements java.io.Serializable {
    private it.csi.mdp.mdpboweb.business.ws.Credentials auth;

    private java.lang.Integer idinformativapsp;

    private java.lang.String identificativoFlusso;

    private java.lang.String identificativoPSP;

    private java.lang.String ragioneSociale;

    private java.util.Calendar dataPubblicazione;

    private java.util.Calendar dataInizioValidita;

    private java.lang.String urlInformazioniPSP;

    private java.lang.Integer stornoPagamento;

    private java.lang.String identificativoIntermediario;

    private java.lang.String identificativoCanale;

    private java.lang.String tipoVersamento;

    private java.lang.Integer modelloPagamento;

    private java.lang.Integer priorita;

    private java.lang.String disponibilitaServizio;

    private java.lang.String descrizioneServizio;

    private java.lang.String condizioniEconomicheMassime;

    private java.lang.String urlInformazioniCanale;

    private java.util.Calendar datainserimento;

    private java.lang.String statoinserimento;

    private java.lang.Integer ordinamento;

    private java.lang.String origine;

    public GetInformativePSPByParam() {
    }

    public GetInformativePSPByParam(
           it.csi.mdp.mdpboweb.business.ws.Credentials auth,
           java.lang.Integer idinformativapsp,
           java.lang.String identificativoFlusso,
           java.lang.String identificativoPSP,
           java.lang.String ragioneSociale,
           java.util.Calendar dataPubblicazione,
           java.util.Calendar dataInizioValidita,
           java.lang.String urlInformazioniPSP,
           java.lang.Integer stornoPagamento,
           java.lang.String identificativoIntermediario,
           java.lang.String identificativoCanale,
           java.lang.String tipoVersamento,
           java.lang.Integer modelloPagamento,
           java.lang.Integer priorita,
           java.lang.String disponibilitaServizio,
           java.lang.String descrizioneServizio,
           java.lang.String condizioniEconomicheMassime,
           java.lang.String urlInformazioniCanale,
           java.util.Calendar datainserimento,
           java.lang.String statoinserimento,
           java.lang.Integer ordinamento,
           java.lang.String origine) {
           this.auth = auth;
           this.idinformativapsp = idinformativapsp;
           this.identificativoFlusso = identificativoFlusso;
           this.identificativoPSP = identificativoPSP;
           this.ragioneSociale = ragioneSociale;
           this.dataPubblicazione = dataPubblicazione;
           this.dataInizioValidita = dataInizioValidita;
           this.urlInformazioniPSP = urlInformazioniPSP;
           this.stornoPagamento = stornoPagamento;
           this.identificativoIntermediario = identificativoIntermediario;
           this.identificativoCanale = identificativoCanale;
           this.tipoVersamento = tipoVersamento;
           this.modelloPagamento = modelloPagamento;
           this.priorita = priorita;
           this.disponibilitaServizio = disponibilitaServizio;
           this.descrizioneServizio = descrizioneServizio;
           this.condizioniEconomicheMassime = condizioniEconomicheMassime;
           this.urlInformazioniCanale = urlInformazioniCanale;
           this.datainserimento = datainserimento;
           this.statoinserimento = statoinserimento;
           this.ordinamento = ordinamento;
           this.origine = origine;
    }


    /**
     * Gets the auth value for this GetInformativePSPByParam.
     * 
     * @return auth
     */
    public it.csi.mdp.mdpboweb.business.ws.Credentials getAuth() {
        return auth;
    }


    /**
     * Sets the auth value for this GetInformativePSPByParam.
     * 
     * @param auth
     */
    public void setAuth(it.csi.mdp.mdpboweb.business.ws.Credentials auth) {
        this.auth = auth;
    }


    /**
     * Gets the idinformativapsp value for this GetInformativePSPByParam.
     * 
     * @return idinformativapsp
     */
    public java.lang.Integer getIdinformativapsp() {
        return idinformativapsp;
    }


    /**
     * Sets the idinformativapsp value for this GetInformativePSPByParam.
     * 
     * @param idinformativapsp
     */
    public void setIdinformativapsp(java.lang.Integer idinformativapsp) {
        this.idinformativapsp = idinformativapsp;
    }


    /**
     * Gets the identificativoFlusso value for this GetInformativePSPByParam.
     * 
     * @return identificativoFlusso
     */
    public java.lang.String getIdentificativoFlusso() {
        return identificativoFlusso;
    }


    /**
     * Sets the identificativoFlusso value for this GetInformativePSPByParam.
     * 
     * @param identificativoFlusso
     */
    public void setIdentificativoFlusso(java.lang.String identificativoFlusso) {
        this.identificativoFlusso = identificativoFlusso;
    }


    /**
     * Gets the identificativoPSP value for this GetInformativePSPByParam.
     * 
     * @return identificativoPSP
     */
    public java.lang.String getIdentificativoPSP() {
        return identificativoPSP;
    }


    /**
     * Sets the identificativoPSP value for this GetInformativePSPByParam.
     * 
     * @param identificativoPSP
     */
    public void setIdentificativoPSP(java.lang.String identificativoPSP) {
        this.identificativoPSP = identificativoPSP;
    }


    /**
     * Gets the ragioneSociale value for this GetInformativePSPByParam.
     * 
     * @return ragioneSociale
     */
    public java.lang.String getRagioneSociale() {
        return ragioneSociale;
    }


    /**
     * Sets the ragioneSociale value for this GetInformativePSPByParam.
     * 
     * @param ragioneSociale
     */
    public void setRagioneSociale(java.lang.String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }


    /**
     * Gets the dataPubblicazione value for this GetInformativePSPByParam.
     * 
     * @return dataPubblicazione
     */
    public java.util.Calendar getDataPubblicazione() {
        return dataPubblicazione;
    }


    /**
     * Sets the dataPubblicazione value for this GetInformativePSPByParam.
     * 
     * @param dataPubblicazione
     */
    public void setDataPubblicazione(java.util.Calendar dataPubblicazione) {
        this.dataPubblicazione = dataPubblicazione;
    }


    /**
     * Gets the dataInizioValidita value for this GetInformativePSPByParam.
     * 
     * @return dataInizioValidita
     */
    public java.util.Calendar getDataInizioValidita() {
        return dataInizioValidita;
    }


    /**
     * Sets the dataInizioValidita value for this GetInformativePSPByParam.
     * 
     * @param dataInizioValidita
     */
    public void setDataInizioValidita(java.util.Calendar dataInizioValidita) {
        this.dataInizioValidita = dataInizioValidita;
    }


    /**
     * Gets the urlInformazioniPSP value for this GetInformativePSPByParam.
     * 
     * @return urlInformazioniPSP
     */
    public java.lang.String getUrlInformazioniPSP() {
        return urlInformazioniPSP;
    }


    /**
     * Sets the urlInformazioniPSP value for this GetInformativePSPByParam.
     * 
     * @param urlInformazioniPSP
     */
    public void setUrlInformazioniPSP(java.lang.String urlInformazioniPSP) {
        this.urlInformazioniPSP = urlInformazioniPSP;
    }


    /**
     * Gets the stornoPagamento value for this GetInformativePSPByParam.
     * 
     * @return stornoPagamento
     */
    public java.lang.Integer getStornoPagamento() {
        return stornoPagamento;
    }


    /**
     * Sets the stornoPagamento value for this GetInformativePSPByParam.
     * 
     * @param stornoPagamento
     */
    public void setStornoPagamento(java.lang.Integer stornoPagamento) {
        this.stornoPagamento = stornoPagamento;
    }


    /**
     * Gets the identificativoIntermediario value for this GetInformativePSPByParam.
     * 
     * @return identificativoIntermediario
     */
    public java.lang.String getIdentificativoIntermediario() {
        return identificativoIntermediario;
    }


    /**
     * Sets the identificativoIntermediario value for this GetInformativePSPByParam.
     * 
     * @param identificativoIntermediario
     */
    public void setIdentificativoIntermediario(java.lang.String identificativoIntermediario) {
        this.identificativoIntermediario = identificativoIntermediario;
    }


    /**
     * Gets the identificativoCanale value for this GetInformativePSPByParam.
     * 
     * @return identificativoCanale
     */
    public java.lang.String getIdentificativoCanale() {
        return identificativoCanale;
    }


    /**
     * Sets the identificativoCanale value for this GetInformativePSPByParam.
     * 
     * @param identificativoCanale
     */
    public void setIdentificativoCanale(java.lang.String identificativoCanale) {
        this.identificativoCanale = identificativoCanale;
    }


    /**
     * Gets the tipoVersamento value for this GetInformativePSPByParam.
     * 
     * @return tipoVersamento
     */
    public java.lang.String getTipoVersamento() {
        return tipoVersamento;
    }


    /**
     * Sets the tipoVersamento value for this GetInformativePSPByParam.
     * 
     * @param tipoVersamento
     */
    public void setTipoVersamento(java.lang.String tipoVersamento) {
        this.tipoVersamento = tipoVersamento;
    }


    /**
     * Gets the modelloPagamento value for this GetInformativePSPByParam.
     * 
     * @return modelloPagamento
     */
    public java.lang.Integer getModelloPagamento() {
        return modelloPagamento;
    }


    /**
     * Sets the modelloPagamento value for this GetInformativePSPByParam.
     * 
     * @param modelloPagamento
     */
    public void setModelloPagamento(java.lang.Integer modelloPagamento) {
        this.modelloPagamento = modelloPagamento;
    }


    /**
     * Gets the priorita value for this GetInformativePSPByParam.
     * 
     * @return priorita
     */
    public java.lang.Integer getPriorita() {
        return priorita;
    }


    /**
     * Sets the priorita value for this GetInformativePSPByParam.
     * 
     * @param priorita
     */
    public void setPriorita(java.lang.Integer priorita) {
        this.priorita = priorita;
    }


    /**
     * Gets the disponibilitaServizio value for this GetInformativePSPByParam.
     * 
     * @return disponibilitaServizio
     */
    public java.lang.String getDisponibilitaServizio() {
        return disponibilitaServizio;
    }


    /**
     * Sets the disponibilitaServizio value for this GetInformativePSPByParam.
     * 
     * @param disponibilitaServizio
     */
    public void setDisponibilitaServizio(java.lang.String disponibilitaServizio) {
        this.disponibilitaServizio = disponibilitaServizio;
    }


    /**
     * Gets the descrizioneServizio value for this GetInformativePSPByParam.
     * 
     * @return descrizioneServizio
     */
    public java.lang.String getDescrizioneServizio() {
        return descrizioneServizio;
    }


    /**
     * Sets the descrizioneServizio value for this GetInformativePSPByParam.
     * 
     * @param descrizioneServizio
     */
    public void setDescrizioneServizio(java.lang.String descrizioneServizio) {
        this.descrizioneServizio = descrizioneServizio;
    }


    /**
     * Gets the condizioniEconomicheMassime value for this GetInformativePSPByParam.
     * 
     * @return condizioniEconomicheMassime
     */
    public java.lang.String getCondizioniEconomicheMassime() {
        return condizioniEconomicheMassime;
    }


    /**
     * Sets the condizioniEconomicheMassime value for this GetInformativePSPByParam.
     * 
     * @param condizioniEconomicheMassime
     */
    public void setCondizioniEconomicheMassime(java.lang.String condizioniEconomicheMassime) {
        this.condizioniEconomicheMassime = condizioniEconomicheMassime;
    }


    /**
     * Gets the urlInformazioniCanale value for this GetInformativePSPByParam.
     * 
     * @return urlInformazioniCanale
     */
    public java.lang.String getUrlInformazioniCanale() {
        return urlInformazioniCanale;
    }


    /**
     * Sets the urlInformazioniCanale value for this GetInformativePSPByParam.
     * 
     * @param urlInformazioniCanale
     */
    public void setUrlInformazioniCanale(java.lang.String urlInformazioniCanale) {
        this.urlInformazioniCanale = urlInformazioniCanale;
    }


    /**
     * Gets the datainserimento value for this GetInformativePSPByParam.
     * 
     * @return datainserimento
     */
    public java.util.Calendar getDatainserimento() {
        return datainserimento;
    }


    /**
     * Sets the datainserimento value for this GetInformativePSPByParam.
     * 
     * @param datainserimento
     */
    public void setDatainserimento(java.util.Calendar datainserimento) {
        this.datainserimento = datainserimento;
    }


    /**
     * Gets the statoinserimento value for this GetInformativePSPByParam.
     * 
     * @return statoinserimento
     */
    public java.lang.String getStatoinserimento() {
        return statoinserimento;
    }


    /**
     * Sets the statoinserimento value for this GetInformativePSPByParam.
     * 
     * @param statoinserimento
     */
    public void setStatoinserimento(java.lang.String statoinserimento) {
        this.statoinserimento = statoinserimento;
    }


    /**
     * Gets the ordinamento value for this GetInformativePSPByParam.
     * 
     * @return ordinamento
     */
    public java.lang.Integer getOrdinamento() {
        return ordinamento;
    }


    /**
     * Sets the ordinamento value for this GetInformativePSPByParam.
     * 
     * @param ordinamento
     */
    public void setOrdinamento(java.lang.Integer ordinamento) {
        this.ordinamento = ordinamento;
    }


    /**
     * Gets the origine value for this GetInformativePSPByParam.
     * 
     * @return origine
     */
    public java.lang.String getOrigine() {
        return origine;
    }


    /**
     * Sets the origine value for this GetInformativePSPByParam.
     * 
     * @param origine
     */
    public void setOrigine(java.lang.String origine) {
        this.origine = origine;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetInformativePSPByParam)) return false;
        GetInformativePSPByParam other = (GetInformativePSPByParam) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.auth==null && other.getAuth()==null) || 
             (this.auth!=null &&
              this.auth.equals(other.getAuth()))) &&
            ((this.idinformativapsp==null && other.getIdinformativapsp()==null) || 
             (this.idinformativapsp!=null &&
              this.idinformativapsp.equals(other.getIdinformativapsp()))) &&
            ((this.identificativoFlusso==null && other.getIdentificativoFlusso()==null) || 
             (this.identificativoFlusso!=null &&
              this.identificativoFlusso.equals(other.getIdentificativoFlusso()))) &&
            ((this.identificativoPSP==null && other.getIdentificativoPSP()==null) || 
             (this.identificativoPSP!=null &&
              this.identificativoPSP.equals(other.getIdentificativoPSP()))) &&
            ((this.ragioneSociale==null && other.getRagioneSociale()==null) || 
             (this.ragioneSociale!=null &&
              this.ragioneSociale.equals(other.getRagioneSociale()))) &&
            ((this.dataPubblicazione==null && other.getDataPubblicazione()==null) || 
             (this.dataPubblicazione!=null &&
              this.dataPubblicazione.equals(other.getDataPubblicazione()))) &&
            ((this.dataInizioValidita==null && other.getDataInizioValidita()==null) || 
             (this.dataInizioValidita!=null &&
              this.dataInizioValidita.equals(other.getDataInizioValidita()))) &&
            ((this.urlInformazioniPSP==null && other.getUrlInformazioniPSP()==null) || 
             (this.urlInformazioniPSP!=null &&
              this.urlInformazioniPSP.equals(other.getUrlInformazioniPSP()))) &&
            ((this.stornoPagamento==null && other.getStornoPagamento()==null) || 
             (this.stornoPagamento!=null &&
              this.stornoPagamento.equals(other.getStornoPagamento()))) &&
            ((this.identificativoIntermediario==null && other.getIdentificativoIntermediario()==null) || 
             (this.identificativoIntermediario!=null &&
              this.identificativoIntermediario.equals(other.getIdentificativoIntermediario()))) &&
            ((this.identificativoCanale==null && other.getIdentificativoCanale()==null) || 
             (this.identificativoCanale!=null &&
              this.identificativoCanale.equals(other.getIdentificativoCanale()))) &&
            ((this.tipoVersamento==null && other.getTipoVersamento()==null) || 
             (this.tipoVersamento!=null &&
              this.tipoVersamento.equals(other.getTipoVersamento()))) &&
            ((this.modelloPagamento==null && other.getModelloPagamento()==null) || 
             (this.modelloPagamento!=null &&
              this.modelloPagamento.equals(other.getModelloPagamento()))) &&
            ((this.priorita==null && other.getPriorita()==null) || 
             (this.priorita!=null &&
              this.priorita.equals(other.getPriorita()))) &&
            ((this.disponibilitaServizio==null && other.getDisponibilitaServizio()==null) || 
             (this.disponibilitaServizio!=null &&
              this.disponibilitaServizio.equals(other.getDisponibilitaServizio()))) &&
            ((this.descrizioneServizio==null && other.getDescrizioneServizio()==null) || 
             (this.descrizioneServizio!=null &&
              this.descrizioneServizio.equals(other.getDescrizioneServizio()))) &&
            ((this.condizioniEconomicheMassime==null && other.getCondizioniEconomicheMassime()==null) || 
             (this.condizioniEconomicheMassime!=null &&
              this.condizioniEconomicheMassime.equals(other.getCondizioniEconomicheMassime()))) &&
            ((this.urlInformazioniCanale==null && other.getUrlInformazioniCanale()==null) || 
             (this.urlInformazioniCanale!=null &&
              this.urlInformazioniCanale.equals(other.getUrlInformazioniCanale()))) &&
            ((this.datainserimento==null && other.getDatainserimento()==null) || 
             (this.datainserimento!=null &&
              this.datainserimento.equals(other.getDatainserimento()))) &&
            ((this.statoinserimento==null && other.getStatoinserimento()==null) || 
             (this.statoinserimento!=null &&
              this.statoinserimento.equals(other.getStatoinserimento()))) &&
            ((this.ordinamento==null && other.getOrdinamento()==null) || 
             (this.ordinamento!=null &&
              this.ordinamento.equals(other.getOrdinamento()))) &&
            ((this.origine==null && other.getOrigine()==null) || 
             (this.origine!=null &&
              this.origine.equals(other.getOrigine())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getAuth() != null) {
            _hashCode += getAuth().hashCode();
        }
        if (getIdinformativapsp() != null) {
            _hashCode += getIdinformativapsp().hashCode();
        }
        if (getIdentificativoFlusso() != null) {
            _hashCode += getIdentificativoFlusso().hashCode();
        }
        if (getIdentificativoPSP() != null) {
            _hashCode += getIdentificativoPSP().hashCode();
        }
        if (getRagioneSociale() != null) {
            _hashCode += getRagioneSociale().hashCode();
        }
        if (getDataPubblicazione() != null) {
            _hashCode += getDataPubblicazione().hashCode();
        }
        if (getDataInizioValidita() != null) {
            _hashCode += getDataInizioValidita().hashCode();
        }
        if (getUrlInformazioniPSP() != null) {
            _hashCode += getUrlInformazioniPSP().hashCode();
        }
        if (getStornoPagamento() != null) {
            _hashCode += getStornoPagamento().hashCode();
        }
        if (getIdentificativoIntermediario() != null) {
            _hashCode += getIdentificativoIntermediario().hashCode();
        }
        if (getIdentificativoCanale() != null) {
            _hashCode += getIdentificativoCanale().hashCode();
        }
        if (getTipoVersamento() != null) {
            _hashCode += getTipoVersamento().hashCode();
        }
        if (getModelloPagamento() != null) {
            _hashCode += getModelloPagamento().hashCode();
        }
        if (getPriorita() != null) {
            _hashCode += getPriorita().hashCode();
        }
        if (getDisponibilitaServizio() != null) {
            _hashCode += getDisponibilitaServizio().hashCode();
        }
        if (getDescrizioneServizio() != null) {
            _hashCode += getDescrizioneServizio().hashCode();
        }
        if (getCondizioniEconomicheMassime() != null) {
            _hashCode += getCondizioniEconomicheMassime().hashCode();
        }
        if (getUrlInformazioniCanale() != null) {
            _hashCode += getUrlInformazioniCanale().hashCode();
        }
        if (getDatainserimento() != null) {
            _hashCode += getDatainserimento().hashCode();
        }
        if (getStatoinserimento() != null) {
            _hashCode += getStatoinserimento().hashCode();
        }
        if (getOrdinamento() != null) {
            _hashCode += getOrdinamento().hashCode();
        }
        if (getOrigine() != null) {
            _hashCode += getOrigine().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetInformativePSPByParam.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getInformativePSPByParam"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("auth");
        elemField.setXmlName(new javax.xml.namespace.QName("", "auth"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "credentials"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idinformativapsp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idinformativapsp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificativoFlusso");
        elemField.setXmlName(new javax.xml.namespace.QName("", "identificativoFlusso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificativoPSP");
        elemField.setXmlName(new javax.xml.namespace.QName("", "identificativoPSP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ragioneSociale");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ragioneSociale"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataPubblicazione");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataPubblicazione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataInizioValidita");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataInizioValidita"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("urlInformazioniPSP");
        elemField.setXmlName(new javax.xml.namespace.QName("", "urlInformazioniPSP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stornoPagamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "stornoPagamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificativoIntermediario");
        elemField.setXmlName(new javax.xml.namespace.QName("", "identificativoIntermediario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificativoCanale");
        elemField.setXmlName(new javax.xml.namespace.QName("", "identificativoCanale"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoVersamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoVersamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modelloPagamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "modelloPagamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("priorita");
        elemField.setXmlName(new javax.xml.namespace.QName("", "priorita"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("disponibilitaServizio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "disponibilitaServizio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrizioneServizio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descrizioneServizio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("condizioniEconomicheMassime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "condizioniEconomicheMassime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("urlInformazioniCanale");
        elemField.setXmlName(new javax.xml.namespace.QName("", "urlInformazioniCanale"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datainserimento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "datainserimento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statoinserimento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "statoinserimento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ordinamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ordinamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("origine");
        elemField.setXmlName(new javax.xml.namespace.QName("", "origine"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
