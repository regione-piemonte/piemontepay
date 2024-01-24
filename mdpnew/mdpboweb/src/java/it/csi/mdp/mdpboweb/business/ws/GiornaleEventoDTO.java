/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class GiornaleEventoDTO  extends it.csi.mdp.mdpboweb.business.ws.BaseDto  implements java.io.Serializable {
    private java.lang.String applicationId;

    private java.lang.String canalepagamento;

    private java.lang.String categoriaevento;

    private java.lang.String codiceContesto;

    private java.lang.String componente;

    private java.util.Calendar dataoraevento;

    private java.lang.String esito;

    private java.lang.Integer id;

    private java.lang.String idIntPsp;

    private java.lang.String idPsp;

    private java.lang.String identificativodominio;

    private java.lang.String identificativoerogatore;

    private java.lang.String identificativofruitore;

    private java.lang.String identificativostazioneintermediariopa;

    private java.util.Calendar insertDate;

    private java.lang.String iuv;

    private java.util.Calendar lastUpdate;

    private java.lang.String parametrispecificiinterfaccia;

    private java.lang.String sottotipoevento;

    private java.lang.String tipoevento;

    private java.lang.String tipoversamento;

    public GiornaleEventoDTO() {
    }

    public GiornaleEventoDTO(
           java.lang.String applicationId,
           java.lang.String canalepagamento,
           java.lang.String categoriaevento,
           java.lang.String codiceContesto,
           java.lang.String componente,
           java.util.Calendar dataoraevento,
           java.lang.String esito,
           java.lang.Integer id,
           java.lang.String idIntPsp,
           java.lang.String idPsp,
           java.lang.String identificativodominio,
           java.lang.String identificativoerogatore,
           java.lang.String identificativofruitore,
           java.lang.String identificativostazioneintermediariopa,
           java.util.Calendar insertDate,
           java.lang.String iuv,
           java.util.Calendar lastUpdate,
           java.lang.String parametrispecificiinterfaccia,
           java.lang.String sottotipoevento,
           java.lang.String tipoevento,
           java.lang.String tipoversamento) {
        this.applicationId = applicationId;
        this.canalepagamento = canalepagamento;
        this.categoriaevento = categoriaevento;
        this.codiceContesto = codiceContesto;
        this.componente = componente;
        this.dataoraevento = dataoraevento;
        this.esito = esito;
        this.id = id;
        this.idIntPsp = idIntPsp;
        this.idPsp = idPsp;
        this.identificativodominio = identificativodominio;
        this.identificativoerogatore = identificativoerogatore;
        this.identificativofruitore = identificativofruitore;
        this.identificativostazioneintermediariopa = identificativostazioneintermediariopa;
        this.insertDate = insertDate;
        this.iuv = iuv;
        this.lastUpdate = lastUpdate;
        this.parametrispecificiinterfaccia = parametrispecificiinterfaccia;
        this.sottotipoevento = sottotipoevento;
        this.tipoevento = tipoevento;
        this.tipoversamento = tipoversamento;
    }


    /**
     * Gets the applicationId value for this GiornaleEventoDTO.
     * 
     * @return applicationId
     */
    public java.lang.String getApplicationId() {
        return applicationId;
    }


    /**
     * Sets the applicationId value for this GiornaleEventoDTO.
     * 
     * @param applicationId
     */
    public void setApplicationId(java.lang.String applicationId) {
        this.applicationId = applicationId;
    }


    /**
     * Gets the canalepagamento value for this GiornaleEventoDTO.
     * 
     * @return canalepagamento
     */
    public java.lang.String getCanalepagamento() {
        return canalepagamento;
    }


    /**
     * Sets the canalepagamento value for this GiornaleEventoDTO.
     * 
     * @param canalepagamento
     */
    public void setCanalepagamento(java.lang.String canalepagamento) {
        this.canalepagamento = canalepagamento;
    }


    /**
     * Gets the categoriaevento value for this GiornaleEventoDTO.
     * 
     * @return categoriaevento
     */
    public java.lang.String getCategoriaevento() {
        return categoriaevento;
    }


    /**
     * Sets the categoriaevento value for this GiornaleEventoDTO.
     * 
     * @param categoriaevento
     */
    public void setCategoriaevento(java.lang.String categoriaevento) {
        this.categoriaevento = categoriaevento;
    }


    /**
     * Gets the codiceContesto value for this GiornaleEventoDTO.
     * 
     * @return codiceContesto
     */
    public java.lang.String getCodiceContesto() {
        return codiceContesto;
    }


    /**
     * Sets the codiceContesto value for this GiornaleEventoDTO.
     * 
     * @param codiceContesto
     */
    public void setCodiceContesto(java.lang.String codiceContesto) {
        this.codiceContesto = codiceContesto;
    }


    /**
     * Gets the componente value for this GiornaleEventoDTO.
     * 
     * @return componente
     */
    public java.lang.String getComponente() {
        return componente;
    }


    /**
     * Sets the componente value for this GiornaleEventoDTO.
     * 
     * @param componente
     */
    public void setComponente(java.lang.String componente) {
        this.componente = componente;
    }


    /**
     * Gets the dataoraevento value for this GiornaleEventoDTO.
     * 
     * @return dataoraevento
     */
    public java.util.Calendar getDataoraevento() {
        return dataoraevento;
    }


    /**
     * Sets the dataoraevento value for this GiornaleEventoDTO.
     * 
     * @param dataoraevento
     */
    public void setDataoraevento(java.util.Calendar dataoraevento) {
        this.dataoraevento = dataoraevento;
    }


    /**
     * Gets the esito value for this GiornaleEventoDTO.
     * 
     * @return esito
     */
    public java.lang.String getEsito() {
        return esito;
    }


    /**
     * Sets the esito value for this GiornaleEventoDTO.
     * 
     * @param esito
     */
    public void setEsito(java.lang.String esito) {
        this.esito = esito;
    }


    /**
     * Gets the id value for this GiornaleEventoDTO.
     * 
     * @return id
     */
    public java.lang.Integer getId() {
        return id;
    }


    /**
     * Sets the id value for this GiornaleEventoDTO.
     * 
     * @param id
     */
    public void setId(java.lang.Integer id) {
        this.id = id;
    }


    /**
     * Gets the idIntPsp value for this GiornaleEventoDTO.
     * 
     * @return idIntPsp
     */
    public java.lang.String getIdIntPsp() {
        return idIntPsp;
    }


    /**
     * Sets the idIntPsp value for this GiornaleEventoDTO.
     * 
     * @param idIntPsp
     */
    public void setIdIntPsp(java.lang.String idIntPsp) {
        this.idIntPsp = idIntPsp;
    }


    /**
     * Gets the idPsp value for this GiornaleEventoDTO.
     * 
     * @return idPsp
     */
    public java.lang.String getIdPsp() {
        return idPsp;
    }


    /**
     * Sets the idPsp value for this GiornaleEventoDTO.
     * 
     * @param idPsp
     */
    public void setIdPsp(java.lang.String idPsp) {
        this.idPsp = idPsp;
    }


    /**
     * Gets the identificativodominio value for this GiornaleEventoDTO.
     * 
     * @return identificativodominio
     */
    public java.lang.String getIdentificativodominio() {
        return identificativodominio;
    }


    /**
     * Sets the identificativodominio value for this GiornaleEventoDTO.
     * 
     * @param identificativodominio
     */
    public void setIdentificativodominio(java.lang.String identificativodominio) {
        this.identificativodominio = identificativodominio;
    }


    /**
     * Gets the identificativoerogatore value for this GiornaleEventoDTO.
     * 
     * @return identificativoerogatore
     */
    public java.lang.String getIdentificativoerogatore() {
        return identificativoerogatore;
    }


    /**
     * Sets the identificativoerogatore value for this GiornaleEventoDTO.
     * 
     * @param identificativoerogatore
     */
    public void setIdentificativoerogatore(java.lang.String identificativoerogatore) {
        this.identificativoerogatore = identificativoerogatore;
    }


    /**
     * Gets the identificativofruitore value for this GiornaleEventoDTO.
     * 
     * @return identificativofruitore
     */
    public java.lang.String getIdentificativofruitore() {
        return identificativofruitore;
    }


    /**
     * Sets the identificativofruitore value for this GiornaleEventoDTO.
     * 
     * @param identificativofruitore
     */
    public void setIdentificativofruitore(java.lang.String identificativofruitore) {
        this.identificativofruitore = identificativofruitore;
    }


    /**
     * Gets the identificativostazioneintermediariopa value for this GiornaleEventoDTO.
     * 
     * @return identificativostazioneintermediariopa
     */
    public java.lang.String getIdentificativostazioneintermediariopa() {
        return identificativostazioneintermediariopa;
    }


    /**
     * Sets the identificativostazioneintermediariopa value for this GiornaleEventoDTO.
     * 
     * @param identificativostazioneintermediariopa
     */
    public void setIdentificativostazioneintermediariopa(java.lang.String identificativostazioneintermediariopa) {
        this.identificativostazioneintermediariopa = identificativostazioneintermediariopa;
    }


    /**
     * Gets the insertDate value for this GiornaleEventoDTO.
     * 
     * @return insertDate
     */
    public java.util.Calendar getInsertDate() {
        return insertDate;
    }


    /**
     * Sets the insertDate value for this GiornaleEventoDTO.
     * 
     * @param insertDate
     */
    public void setInsertDate(java.util.Calendar insertDate) {
        this.insertDate = insertDate;
    }


    /**
     * Gets the iuv value for this GiornaleEventoDTO.
     * 
     * @return iuv
     */
    public java.lang.String getIuv() {
        return iuv;
    }


    /**
     * Sets the iuv value for this GiornaleEventoDTO.
     * 
     * @param iuv
     */
    public void setIuv(java.lang.String iuv) {
        this.iuv = iuv;
    }


    /**
     * Gets the lastUpdate value for this GiornaleEventoDTO.
     * 
     * @return lastUpdate
     */
    public java.util.Calendar getLastUpdate() {
        return lastUpdate;
    }


    /**
     * Sets the lastUpdate value for this GiornaleEventoDTO.
     * 
     * @param lastUpdate
     */
    public void setLastUpdate(java.util.Calendar lastUpdate) {
        this.lastUpdate = lastUpdate;
    }


    /**
     * Gets the parametrispecificiinterfaccia value for this GiornaleEventoDTO.
     * 
     * @return parametrispecificiinterfaccia
     */
    public java.lang.String getParametrispecificiinterfaccia() {
        return parametrispecificiinterfaccia;
    }


    /**
     * Sets the parametrispecificiinterfaccia value for this GiornaleEventoDTO.
     * 
     * @param parametrispecificiinterfaccia
     */
    public void setParametrispecificiinterfaccia(java.lang.String parametrispecificiinterfaccia) {
        this.parametrispecificiinterfaccia = parametrispecificiinterfaccia;
    }


    /**
     * Gets the sottotipoevento value for this GiornaleEventoDTO.
     * 
     * @return sottotipoevento
     */
    public java.lang.String getSottotipoevento() {
        return sottotipoevento;
    }


    /**
     * Sets the sottotipoevento value for this GiornaleEventoDTO.
     * 
     * @param sottotipoevento
     */
    public void setSottotipoevento(java.lang.String sottotipoevento) {
        this.sottotipoevento = sottotipoevento;
    }


    /**
     * Gets the tipoevento value for this GiornaleEventoDTO.
     * 
     * @return tipoevento
     */
    public java.lang.String getTipoevento() {
        return tipoevento;
    }


    /**
     * Sets the tipoevento value for this GiornaleEventoDTO.
     * 
     * @param tipoevento
     */
    public void setTipoevento(java.lang.String tipoevento) {
        this.tipoevento = tipoevento;
    }


    /**
     * Gets the tipoversamento value for this GiornaleEventoDTO.
     * 
     * @return tipoversamento
     */
    public java.lang.String getTipoversamento() {
        return tipoversamento;
    }


    /**
     * Sets the tipoversamento value for this GiornaleEventoDTO.
     * 
     * @param tipoversamento
     */
    public void setTipoversamento(java.lang.String tipoversamento) {
        this.tipoversamento = tipoversamento;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GiornaleEventoDTO)) return false;
        GiornaleEventoDTO other = (GiornaleEventoDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.applicationId==null && other.getApplicationId()==null) || 
             (this.applicationId!=null &&
              this.applicationId.equals(other.getApplicationId()))) &&
            ((this.canalepagamento==null && other.getCanalepagamento()==null) || 
             (this.canalepagamento!=null &&
              this.canalepagamento.equals(other.getCanalepagamento()))) &&
            ((this.categoriaevento==null && other.getCategoriaevento()==null) || 
             (this.categoriaevento!=null &&
              this.categoriaevento.equals(other.getCategoriaevento()))) &&
            ((this.codiceContesto==null && other.getCodiceContesto()==null) || 
             (this.codiceContesto!=null &&
              this.codiceContesto.equals(other.getCodiceContesto()))) &&
            ((this.componente==null && other.getComponente()==null) || 
             (this.componente!=null &&
              this.componente.equals(other.getComponente()))) &&
            ((this.dataoraevento==null && other.getDataoraevento()==null) || 
             (this.dataoraevento!=null &&
              this.dataoraevento.equals(other.getDataoraevento()))) &&
            ((this.esito==null && other.getEsito()==null) || 
             (this.esito!=null &&
              this.esito.equals(other.getEsito()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.idIntPsp==null && other.getIdIntPsp()==null) || 
             (this.idIntPsp!=null &&
              this.idIntPsp.equals(other.getIdIntPsp()))) &&
            ((this.idPsp==null && other.getIdPsp()==null) || 
             (this.idPsp!=null &&
              this.idPsp.equals(other.getIdPsp()))) &&
            ((this.identificativodominio==null && other.getIdentificativodominio()==null) || 
             (this.identificativodominio!=null &&
              this.identificativodominio.equals(other.getIdentificativodominio()))) &&
            ((this.identificativoerogatore==null && other.getIdentificativoerogatore()==null) || 
             (this.identificativoerogatore!=null &&
              this.identificativoerogatore.equals(other.getIdentificativoerogatore()))) &&
            ((this.identificativofruitore==null && other.getIdentificativofruitore()==null) || 
             (this.identificativofruitore!=null &&
              this.identificativofruitore.equals(other.getIdentificativofruitore()))) &&
            ((this.identificativostazioneintermediariopa==null && other.getIdentificativostazioneintermediariopa()==null) || 
             (this.identificativostazioneintermediariopa!=null &&
              this.identificativostazioneintermediariopa.equals(other.getIdentificativostazioneintermediariopa()))) &&
            ((this.insertDate==null && other.getInsertDate()==null) || 
             (this.insertDate!=null &&
              this.insertDate.equals(other.getInsertDate()))) &&
            ((this.iuv==null && other.getIuv()==null) || 
             (this.iuv!=null &&
              this.iuv.equals(other.getIuv()))) &&
            ((this.lastUpdate==null && other.getLastUpdate()==null) || 
             (this.lastUpdate!=null &&
              this.lastUpdate.equals(other.getLastUpdate()))) &&
            ((this.parametrispecificiinterfaccia==null && other.getParametrispecificiinterfaccia()==null) || 
             (this.parametrispecificiinterfaccia!=null &&
              this.parametrispecificiinterfaccia.equals(other.getParametrispecificiinterfaccia()))) &&
            ((this.sottotipoevento==null && other.getSottotipoevento()==null) || 
             (this.sottotipoevento!=null &&
              this.sottotipoevento.equals(other.getSottotipoevento()))) &&
            ((this.tipoevento==null && other.getTipoevento()==null) || 
             (this.tipoevento!=null &&
              this.tipoevento.equals(other.getTipoevento()))) &&
            ((this.tipoversamento==null && other.getTipoversamento()==null) || 
             (this.tipoversamento!=null &&
              this.tipoversamento.equals(other.getTipoversamento())));
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
        if (getApplicationId() != null) {
            _hashCode += getApplicationId().hashCode();
        }
        if (getCanalepagamento() != null) {
            _hashCode += getCanalepagamento().hashCode();
        }
        if (getCategoriaevento() != null) {
            _hashCode += getCategoriaevento().hashCode();
        }
        if (getCodiceContesto() != null) {
            _hashCode += getCodiceContesto().hashCode();
        }
        if (getComponente() != null) {
            _hashCode += getComponente().hashCode();
        }
        if (getDataoraevento() != null) {
            _hashCode += getDataoraevento().hashCode();
        }
        if (getEsito() != null) {
            _hashCode += getEsito().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getIdIntPsp() != null) {
            _hashCode += getIdIntPsp().hashCode();
        }
        if (getIdPsp() != null) {
            _hashCode += getIdPsp().hashCode();
        }
        if (getIdentificativodominio() != null) {
            _hashCode += getIdentificativodominio().hashCode();
        }
        if (getIdentificativoerogatore() != null) {
            _hashCode += getIdentificativoerogatore().hashCode();
        }
        if (getIdentificativofruitore() != null) {
            _hashCode += getIdentificativofruitore().hashCode();
        }
        if (getIdentificativostazioneintermediariopa() != null) {
            _hashCode += getIdentificativostazioneintermediariopa().hashCode();
        }
        if (getInsertDate() != null) {
            _hashCode += getInsertDate().hashCode();
        }
        if (getIuv() != null) {
            _hashCode += getIuv().hashCode();
        }
        if (getLastUpdate() != null) {
            _hashCode += getLastUpdate().hashCode();
        }
        if (getParametrispecificiinterfaccia() != null) {
            _hashCode += getParametrispecificiinterfaccia().hashCode();
        }
        if (getSottotipoevento() != null) {
            _hashCode += getSottotipoevento().hashCode();
        }
        if (getTipoevento() != null) {
            _hashCode += getTipoevento().hashCode();
        }
        if (getTipoversamento() != null) {
            _hashCode += getTipoversamento().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GiornaleEventoDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "giornaleEventoDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "applicationId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("canalepagamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "canalepagamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("categoriaevento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "categoriaevento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceContesto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiceContesto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("componente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "componente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataoraevento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataoraevento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("esito");
        elemField.setXmlName(new javax.xml.namespace.QName("", "esito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idIntPsp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idIntPsp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idPsp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idPsp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificativodominio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "identificativodominio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificativoerogatore");
        elemField.setXmlName(new javax.xml.namespace.QName("", "identificativoerogatore"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificativofruitore");
        elemField.setXmlName(new javax.xml.namespace.QName("", "identificativofruitore"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificativostazioneintermediariopa");
        elemField.setXmlName(new javax.xml.namespace.QName("", "identificativostazioneintermediariopa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("insertDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "insertDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("iuv");
        elemField.setXmlName(new javax.xml.namespace.QName("", "iuv"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastUpdate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "lastUpdate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrispecificiinterfaccia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "parametrispecificiinterfaccia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sottotipoevento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sottotipoevento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoevento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoevento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoversamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoversamento"));
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
