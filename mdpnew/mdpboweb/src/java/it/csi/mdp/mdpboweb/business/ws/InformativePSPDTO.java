/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class InformativePSPDTO  extends it.csi.mdp.mdpboweb.business.ws.BaseDto  implements java.io.Serializable {
    private java.lang.String condizioniEconomicheMassime;

    private java.util.Calendar dataInizioValidita;

    private java.util.Calendar dataPubblicazione;

    private java.util.Calendar datainserimento;

    private java.lang.String descrizioneServizio;

    private java.lang.String disponibilitaServizio;

    private java.lang.String identificativoCanale;

    private java.lang.String identificativoFlusso;

    private java.lang.String identificativoIntermediario;

    private java.lang.String identificativoPSP;

    private java.lang.Integer idinformativapsp;

    private java.lang.Integer modelloPagamento;

    private java.lang.Integer ordinamento;

    private java.lang.String origine;

    private java.lang.Integer priorita;

    private java.lang.String ragioneSociale;

    private java.lang.String statoinserimento;

    private java.lang.Integer stornoPagamento;

    private java.lang.String tipoVersamento;

    private java.lang.String urlInformazioniCanale;

    private java.lang.String urlInformazioniPSP;

    public InformativePSPDTO() {
    }

    public InformativePSPDTO(
           java.lang.String condizioniEconomicheMassime,
           java.util.Calendar dataInizioValidita,
           java.util.Calendar dataPubblicazione,
           java.util.Calendar datainserimento,
           java.lang.String descrizioneServizio,
           java.lang.String disponibilitaServizio,
           java.lang.String identificativoCanale,
           java.lang.String identificativoFlusso,
           java.lang.String identificativoIntermediario,
           java.lang.String identificativoPSP,
           java.lang.Integer idinformativapsp,
           java.lang.Integer modelloPagamento,
           java.lang.Integer ordinamento,
           java.lang.String origine,
           java.lang.Integer priorita,
           java.lang.String ragioneSociale,
           java.lang.String statoinserimento,
           java.lang.Integer stornoPagamento,
           java.lang.String tipoVersamento,
           java.lang.String urlInformazioniCanale,
           java.lang.String urlInformazioniPSP) {
        this.condizioniEconomicheMassime = condizioniEconomicheMassime;
        this.dataInizioValidita = dataInizioValidita;
        this.dataPubblicazione = dataPubblicazione;
        this.datainserimento = datainserimento;
        this.descrizioneServizio = descrizioneServizio;
        this.disponibilitaServizio = disponibilitaServizio;
        this.identificativoCanale = identificativoCanale;
        this.identificativoFlusso = identificativoFlusso;
        this.identificativoIntermediario = identificativoIntermediario;
        this.identificativoPSP = identificativoPSP;
        this.idinformativapsp = idinformativapsp;
        this.modelloPagamento = modelloPagamento;
        this.ordinamento = ordinamento;
        this.origine = origine;
        this.priorita = priorita;
        this.ragioneSociale = ragioneSociale;
        this.statoinserimento = statoinserimento;
        this.stornoPagamento = stornoPagamento;
        this.tipoVersamento = tipoVersamento;
        this.urlInformazioniCanale = urlInformazioniCanale;
        this.urlInformazioniPSP = urlInformazioniPSP;
    }


    /**
     * Gets the condizioniEconomicheMassime value for this InformativePSPDTO.
     * 
     * @return condizioniEconomicheMassime
     */
    public java.lang.String getCondizioniEconomicheMassime() {
        return condizioniEconomicheMassime;
    }


    /**
     * Sets the condizioniEconomicheMassime value for this InformativePSPDTO.
     * 
     * @param condizioniEconomicheMassime
     */
    public void setCondizioniEconomicheMassime(java.lang.String condizioniEconomicheMassime) {
        this.condizioniEconomicheMassime = condizioniEconomicheMassime;
    }


    /**
     * Gets the dataInizioValidita value for this InformativePSPDTO.
     * 
     * @return dataInizioValidita
     */
    public java.util.Calendar getDataInizioValidita() {
        return dataInizioValidita;
    }


    /**
     * Sets the dataInizioValidita value for this InformativePSPDTO.
     * 
     * @param dataInizioValidita
     */
    public void setDataInizioValidita(java.util.Calendar dataInizioValidita) {
        this.dataInizioValidita = dataInizioValidita;
    }


    /**
     * Gets the dataPubblicazione value for this InformativePSPDTO.
     * 
     * @return dataPubblicazione
     */
    public java.util.Calendar getDataPubblicazione() {
        return dataPubblicazione;
    }


    /**
     * Sets the dataPubblicazione value for this InformativePSPDTO.
     * 
     * @param dataPubblicazione
     */
    public void setDataPubblicazione(java.util.Calendar dataPubblicazione) {
        this.dataPubblicazione = dataPubblicazione;
    }


    /**
     * Gets the datainserimento value for this InformativePSPDTO.
     * 
     * @return datainserimento
     */
    public java.util.Calendar getDatainserimento() {
        return datainserimento;
    }


    /**
     * Sets the datainserimento value for this InformativePSPDTO.
     * 
     * @param datainserimento
     */
    public void setDatainserimento(java.util.Calendar datainserimento) {
        this.datainserimento = datainserimento;
    }


    /**
     * Gets the descrizioneServizio value for this InformativePSPDTO.
     * 
     * @return descrizioneServizio
     */
    public java.lang.String getDescrizioneServizio() {
        return descrizioneServizio;
    }


    /**
     * Sets the descrizioneServizio value for this InformativePSPDTO.
     * 
     * @param descrizioneServizio
     */
    public void setDescrizioneServizio(java.lang.String descrizioneServizio) {
        this.descrizioneServizio = descrizioneServizio;
    }


    /**
     * Gets the disponibilitaServizio value for this InformativePSPDTO.
     * 
     * @return disponibilitaServizio
     */
    public java.lang.String getDisponibilitaServizio() {
        return disponibilitaServizio;
    }


    /**
     * Sets the disponibilitaServizio value for this InformativePSPDTO.
     * 
     * @param disponibilitaServizio
     */
    public void setDisponibilitaServizio(java.lang.String disponibilitaServizio) {
        this.disponibilitaServizio = disponibilitaServizio;
    }


    /**
     * Gets the identificativoCanale value for this InformativePSPDTO.
     * 
     * @return identificativoCanale
     */
    public java.lang.String getIdentificativoCanale() {
        return identificativoCanale;
    }


    /**
     * Sets the identificativoCanale value for this InformativePSPDTO.
     * 
     * @param identificativoCanale
     */
    public void setIdentificativoCanale(java.lang.String identificativoCanale) {
        this.identificativoCanale = identificativoCanale;
    }


    /**
     * Gets the identificativoFlusso value for this InformativePSPDTO.
     * 
     * @return identificativoFlusso
     */
    public java.lang.String getIdentificativoFlusso() {
        return identificativoFlusso;
    }


    /**
     * Sets the identificativoFlusso value for this InformativePSPDTO.
     * 
     * @param identificativoFlusso
     */
    public void setIdentificativoFlusso(java.lang.String identificativoFlusso) {
        this.identificativoFlusso = identificativoFlusso;
    }


    /**
     * Gets the identificativoIntermediario value for this InformativePSPDTO.
     * 
     * @return identificativoIntermediario
     */
    public java.lang.String getIdentificativoIntermediario() {
        return identificativoIntermediario;
    }


    /**
     * Sets the identificativoIntermediario value for this InformativePSPDTO.
     * 
     * @param identificativoIntermediario
     */
    public void setIdentificativoIntermediario(java.lang.String identificativoIntermediario) {
        this.identificativoIntermediario = identificativoIntermediario;
    }


    /**
     * Gets the identificativoPSP value for this InformativePSPDTO.
     * 
     * @return identificativoPSP
     */
    public java.lang.String getIdentificativoPSP() {
        return identificativoPSP;
    }


    /**
     * Sets the identificativoPSP value for this InformativePSPDTO.
     * 
     * @param identificativoPSP
     */
    public void setIdentificativoPSP(java.lang.String identificativoPSP) {
        this.identificativoPSP = identificativoPSP;
    }


    /**
     * Gets the idinformativapsp value for this InformativePSPDTO.
     * 
     * @return idinformativapsp
     */
    public java.lang.Integer getIdinformativapsp() {
        return idinformativapsp;
    }


    /**
     * Sets the idinformativapsp value for this InformativePSPDTO.
     * 
     * @param idinformativapsp
     */
    public void setIdinformativapsp(java.lang.Integer idinformativapsp) {
        this.idinformativapsp = idinformativapsp;
    }


    /**
     * Gets the modelloPagamento value for this InformativePSPDTO.
     * 
     * @return modelloPagamento
     */
    public java.lang.Integer getModelloPagamento() {
        return modelloPagamento;
    }


    /**
     * Sets the modelloPagamento value for this InformativePSPDTO.
     * 
     * @param modelloPagamento
     */
    public void setModelloPagamento(java.lang.Integer modelloPagamento) {
        this.modelloPagamento = modelloPagamento;
    }


    /**
     * Gets the ordinamento value for this InformativePSPDTO.
     * 
     * @return ordinamento
     */
    public java.lang.Integer getOrdinamento() {
        return ordinamento;
    }


    /**
     * Sets the ordinamento value for this InformativePSPDTO.
     * 
     * @param ordinamento
     */
    public void setOrdinamento(java.lang.Integer ordinamento) {
        this.ordinamento = ordinamento;
    }


    /**
     * Gets the origine value for this InformativePSPDTO.
     * 
     * @return origine
     */
    public java.lang.String getOrigine() {
        return origine;
    }


    /**
     * Sets the origine value for this InformativePSPDTO.
     * 
     * @param origine
     */
    public void setOrigine(java.lang.String origine) {
        this.origine = origine;
    }


    /**
     * Gets the priorita value for this InformativePSPDTO.
     * 
     * @return priorita
     */
    public java.lang.Integer getPriorita() {
        return priorita;
    }


    /**
     * Sets the priorita value for this InformativePSPDTO.
     * 
     * @param priorita
     */
    public void setPriorita(java.lang.Integer priorita) {
        this.priorita = priorita;
    }


    /**
     * Gets the ragioneSociale value for this InformativePSPDTO.
     * 
     * @return ragioneSociale
     */
    public java.lang.String getRagioneSociale() {
        return ragioneSociale;
    }


    /**
     * Sets the ragioneSociale value for this InformativePSPDTO.
     * 
     * @param ragioneSociale
     */
    public void setRagioneSociale(java.lang.String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }


    /**
     * Gets the statoinserimento value for this InformativePSPDTO.
     * 
     * @return statoinserimento
     */
    public java.lang.String getStatoinserimento() {
        return statoinserimento;
    }


    /**
     * Sets the statoinserimento value for this InformativePSPDTO.
     * 
     * @param statoinserimento
     */
    public void setStatoinserimento(java.lang.String statoinserimento) {
        this.statoinserimento = statoinserimento;
    }


    /**
     * Gets the stornoPagamento value for this InformativePSPDTO.
     * 
     * @return stornoPagamento
     */
    public java.lang.Integer getStornoPagamento() {
        return stornoPagamento;
    }


    /**
     * Sets the stornoPagamento value for this InformativePSPDTO.
     * 
     * @param stornoPagamento
     */
    public void setStornoPagamento(java.lang.Integer stornoPagamento) {
        this.stornoPagamento = stornoPagamento;
    }


    /**
     * Gets the tipoVersamento value for this InformativePSPDTO.
     * 
     * @return tipoVersamento
     */
    public java.lang.String getTipoVersamento() {
        return tipoVersamento;
    }


    /**
     * Sets the tipoVersamento value for this InformativePSPDTO.
     * 
     * @param tipoVersamento
     */
    public void setTipoVersamento(java.lang.String tipoVersamento) {
        this.tipoVersamento = tipoVersamento;
    }


    /**
     * Gets the urlInformazioniCanale value for this InformativePSPDTO.
     * 
     * @return urlInformazioniCanale
     */
    public java.lang.String getUrlInformazioniCanale() {
        return urlInformazioniCanale;
    }


    /**
     * Sets the urlInformazioniCanale value for this InformativePSPDTO.
     * 
     * @param urlInformazioniCanale
     */
    public void setUrlInformazioniCanale(java.lang.String urlInformazioniCanale) {
        this.urlInformazioniCanale = urlInformazioniCanale;
    }


    /**
     * Gets the urlInformazioniPSP value for this InformativePSPDTO.
     * 
     * @return urlInformazioniPSP
     */
    public java.lang.String getUrlInformazioniPSP() {
        return urlInformazioniPSP;
    }


    /**
     * Sets the urlInformazioniPSP value for this InformativePSPDTO.
     * 
     * @param urlInformazioniPSP
     */
    public void setUrlInformazioniPSP(java.lang.String urlInformazioniPSP) {
        this.urlInformazioniPSP = urlInformazioniPSP;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InformativePSPDTO)) return false;
        InformativePSPDTO other = (InformativePSPDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.condizioniEconomicheMassime==null && other.getCondizioniEconomicheMassime()==null) || 
             (this.condizioniEconomicheMassime!=null &&
              this.condizioniEconomicheMassime.equals(other.getCondizioniEconomicheMassime()))) &&
            ((this.dataInizioValidita==null && other.getDataInizioValidita()==null) || 
             (this.dataInizioValidita!=null &&
              this.dataInizioValidita.equals(other.getDataInizioValidita()))) &&
            ((this.dataPubblicazione==null && other.getDataPubblicazione()==null) || 
             (this.dataPubblicazione!=null &&
              this.dataPubblicazione.equals(other.getDataPubblicazione()))) &&
            ((this.datainserimento==null && other.getDatainserimento()==null) || 
             (this.datainserimento!=null &&
              this.datainserimento.equals(other.getDatainserimento()))) &&
            ((this.descrizioneServizio==null && other.getDescrizioneServizio()==null) || 
             (this.descrizioneServizio!=null &&
              this.descrizioneServizio.equals(other.getDescrizioneServizio()))) &&
            ((this.disponibilitaServizio==null && other.getDisponibilitaServizio()==null) || 
             (this.disponibilitaServizio!=null &&
              this.disponibilitaServizio.equals(other.getDisponibilitaServizio()))) &&
            ((this.identificativoCanale==null && other.getIdentificativoCanale()==null) || 
             (this.identificativoCanale!=null &&
              this.identificativoCanale.equals(other.getIdentificativoCanale()))) &&
            ((this.identificativoFlusso==null && other.getIdentificativoFlusso()==null) || 
             (this.identificativoFlusso!=null &&
              this.identificativoFlusso.equals(other.getIdentificativoFlusso()))) &&
            ((this.identificativoIntermediario==null && other.getIdentificativoIntermediario()==null) || 
             (this.identificativoIntermediario!=null &&
              this.identificativoIntermediario.equals(other.getIdentificativoIntermediario()))) &&
            ((this.identificativoPSP==null && other.getIdentificativoPSP()==null) || 
             (this.identificativoPSP!=null &&
              this.identificativoPSP.equals(other.getIdentificativoPSP()))) &&
            ((this.idinformativapsp==null && other.getIdinformativapsp()==null) || 
             (this.idinformativapsp!=null &&
              this.idinformativapsp.equals(other.getIdinformativapsp()))) &&
            ((this.modelloPagamento==null && other.getModelloPagamento()==null) || 
             (this.modelloPagamento!=null &&
              this.modelloPagamento.equals(other.getModelloPagamento()))) &&
            ((this.ordinamento==null && other.getOrdinamento()==null) || 
             (this.ordinamento!=null &&
              this.ordinamento.equals(other.getOrdinamento()))) &&
            ((this.origine==null && other.getOrigine()==null) || 
             (this.origine!=null &&
              this.origine.equals(other.getOrigine()))) &&
            ((this.priorita==null && other.getPriorita()==null) || 
             (this.priorita!=null &&
              this.priorita.equals(other.getPriorita()))) &&
            ((this.ragioneSociale==null && other.getRagioneSociale()==null) || 
             (this.ragioneSociale!=null &&
              this.ragioneSociale.equals(other.getRagioneSociale()))) &&
            ((this.statoinserimento==null && other.getStatoinserimento()==null) || 
             (this.statoinserimento!=null &&
              this.statoinserimento.equals(other.getStatoinserimento()))) &&
            ((this.stornoPagamento==null && other.getStornoPagamento()==null) || 
             (this.stornoPagamento!=null &&
              this.stornoPagamento.equals(other.getStornoPagamento()))) &&
            ((this.tipoVersamento==null && other.getTipoVersamento()==null) || 
             (this.tipoVersamento!=null &&
              this.tipoVersamento.equals(other.getTipoVersamento()))) &&
            ((this.urlInformazioniCanale==null && other.getUrlInformazioniCanale()==null) || 
             (this.urlInformazioniCanale!=null &&
              this.urlInformazioniCanale.equals(other.getUrlInformazioniCanale()))) &&
            ((this.urlInformazioniPSP==null && other.getUrlInformazioniPSP()==null) || 
             (this.urlInformazioniPSP!=null &&
              this.urlInformazioniPSP.equals(other.getUrlInformazioniPSP())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getCondizioniEconomicheMassime() != null) {
            _hashCode += getCondizioniEconomicheMassime().hashCode();
        }
        if (getDataInizioValidita() != null) {
            _hashCode += getDataInizioValidita().hashCode();
        }
        if (getDataPubblicazione() != null) {
            _hashCode += getDataPubblicazione().hashCode();
        }
        if (getDatainserimento() != null) {
            _hashCode += getDatainserimento().hashCode();
        }
        if (getDescrizioneServizio() != null) {
            _hashCode += getDescrizioneServizio().hashCode();
        }
        if (getDisponibilitaServizio() != null) {
            _hashCode += getDisponibilitaServizio().hashCode();
        }
        if (getIdentificativoCanale() != null) {
            _hashCode += getIdentificativoCanale().hashCode();
        }
        if (getIdentificativoFlusso() != null) {
            _hashCode += getIdentificativoFlusso().hashCode();
        }
        if (getIdentificativoIntermediario() != null) {
            _hashCode += getIdentificativoIntermediario().hashCode();
        }
        if (getIdentificativoPSP() != null) {
            _hashCode += getIdentificativoPSP().hashCode();
        }
        if (getIdinformativapsp() != null) {
            _hashCode += getIdinformativapsp().hashCode();
        }
        if (getModelloPagamento() != null) {
            _hashCode += getModelloPagamento().hashCode();
        }
        if (getOrdinamento() != null) {
            _hashCode += getOrdinamento().hashCode();
        }
        if (getOrigine() != null) {
            _hashCode += getOrigine().hashCode();
        }
        if (getPriorita() != null) {
            _hashCode += getPriorita().hashCode();
        }
        if (getRagioneSociale() != null) {
            _hashCode += getRagioneSociale().hashCode();
        }
        if (getStatoinserimento() != null) {
            _hashCode += getStatoinserimento().hashCode();
        }
        if (getStornoPagamento() != null) {
            _hashCode += getStornoPagamento().hashCode();
        }
        if (getTipoVersamento() != null) {
            _hashCode += getTipoVersamento().hashCode();
        }
        if (getUrlInformazioniCanale() != null) {
            _hashCode += getUrlInformazioniCanale().hashCode();
        }
        if (getUrlInformazioniPSP() != null) {
            _hashCode += getUrlInformazioniPSP().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InformativePSPDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "informativePSPDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("condizioniEconomicheMassime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "condizioniEconomicheMassime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("dataPubblicazione");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataPubblicazione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
        elemField.setFieldName("descrizioneServizio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descrizioneServizio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("identificativoCanale");
        elemField.setXmlName(new javax.xml.namespace.QName("", "identificativoCanale"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("identificativoIntermediario");
        elemField.setXmlName(new javax.xml.namespace.QName("", "identificativoIntermediario"));
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
        elemField.setFieldName("idinformativapsp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idinformativapsp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("priorita");
        elemField.setXmlName(new javax.xml.namespace.QName("", "priorita"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
        elemField.setFieldName("statoinserimento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "statoinserimento"));
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
        elemField.setFieldName("tipoVersamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoVersamento"));
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
        elemField.setFieldName("urlInformazioniPSP");
        elemField.setXmlName(new javax.xml.namespace.QName("", "urlInformazioniPSP"));
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
