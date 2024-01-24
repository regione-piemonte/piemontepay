/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class GetFlussoRiversamentoByParam  implements java.io.Serializable {
    private it.csi.mdp.mdpboweb.business.ws.Credentials auth;

    private java.lang.Integer id;

    private java.lang.String identificativopsp;

    private java.lang.String identificativoflusso;

    private java.lang.String versioneoggetto;

    private java.lang.String identificativounivocoregolamento;

    private java.lang.String identificativoistitutomittente;

    private java.lang.String identificativoistitutoricevente;

    private java.lang.Integer numerototalepagamenti;

    private java.lang.Double importototalepagamenti;

    private java.util.Calendar dataoraflusso;

    private java.util.Calendar dataregolamentoDa;

    private java.util.Calendar dataregolamentoA;

    private java.util.Calendar datainserimento;

    private java.util.Calendar datamodifica;

    private java.lang.String xmlflusso;

    private java.lang.String denominazionemittente;

    private java.lang.String denominazionericevente;

    public GetFlussoRiversamentoByParam() {
    }

    public GetFlussoRiversamentoByParam(
           it.csi.mdp.mdpboweb.business.ws.Credentials auth,
           java.lang.Integer id,
           java.lang.String identificativopsp,
           java.lang.String identificativoflusso,
           java.lang.String versioneoggetto,
           java.lang.String identificativounivocoregolamento,
           java.lang.String identificativoistitutomittente,
           java.lang.String identificativoistitutoricevente,
           java.lang.Integer numerototalepagamenti,
           java.lang.Double importototalepagamenti,
           java.util.Calendar dataoraflusso,
           java.util.Calendar dataregolamentoDa,
           java.util.Calendar dataregolamentoA,
           java.util.Calendar datainserimento,
           java.util.Calendar datamodifica,
           java.lang.String xmlflusso,
           java.lang.String denominazionemittente,
           java.lang.String denominazionericevente) {
           this.auth = auth;
           this.id = id;
           this.identificativopsp = identificativopsp;
           this.identificativoflusso = identificativoflusso;
           this.versioneoggetto = versioneoggetto;
           this.identificativounivocoregolamento = identificativounivocoregolamento;
           this.identificativoistitutomittente = identificativoistitutomittente;
           this.identificativoistitutoricevente = identificativoistitutoricevente;
           this.numerototalepagamenti = numerototalepagamenti;
           this.importototalepagamenti = importototalepagamenti;
           this.dataoraflusso = dataoraflusso;
           this.dataregolamentoDa = dataregolamentoDa;
           this.dataregolamentoA = dataregolamentoA;
           this.datainserimento = datainserimento;
           this.datamodifica = datamodifica;
           this.xmlflusso = xmlflusso;
           this.denominazionemittente = denominazionemittente;
           this.denominazionericevente = denominazionericevente;
    }


    /**
     * Gets the auth value for this GetFlussoRiversamentoByParam.
     * 
     * @return auth
     */
    public it.csi.mdp.mdpboweb.business.ws.Credentials getAuth() {
        return auth;
    }


    /**
     * Sets the auth value for this GetFlussoRiversamentoByParam.
     * 
     * @param auth
     */
    public void setAuth(it.csi.mdp.mdpboweb.business.ws.Credentials auth) {
        this.auth = auth;
    }


    /**
     * Gets the id value for this GetFlussoRiversamentoByParam.
     * 
     * @return id
     */
    public java.lang.Integer getId() {
        return id;
    }


    /**
     * Sets the id value for this GetFlussoRiversamentoByParam.
     * 
     * @param id
     */
    public void setId(java.lang.Integer id) {
        this.id = id;
    }


    /**
     * Gets the identificativopsp value for this GetFlussoRiversamentoByParam.
     * 
     * @return identificativopsp
     */
    public java.lang.String getIdentificativopsp() {
        return identificativopsp;
    }


    /**
     * Sets the identificativopsp value for this GetFlussoRiversamentoByParam.
     * 
     * @param identificativopsp
     */
    public void setIdentificativopsp(java.lang.String identificativopsp) {
        this.identificativopsp = identificativopsp;
    }


    /**
     * Gets the identificativoflusso value for this GetFlussoRiversamentoByParam.
     * 
     * @return identificativoflusso
     */
    public java.lang.String getIdentificativoflusso() {
        return identificativoflusso;
    }


    /**
     * Sets the identificativoflusso value for this GetFlussoRiversamentoByParam.
     * 
     * @param identificativoflusso
     */
    public void setIdentificativoflusso(java.lang.String identificativoflusso) {
        this.identificativoflusso = identificativoflusso;
    }


    /**
     * Gets the versioneoggetto value for this GetFlussoRiversamentoByParam.
     * 
     * @return versioneoggetto
     */
    public java.lang.String getVersioneoggetto() {
        return versioneoggetto;
    }


    /**
     * Sets the versioneoggetto value for this GetFlussoRiversamentoByParam.
     * 
     * @param versioneoggetto
     */
    public void setVersioneoggetto(java.lang.String versioneoggetto) {
        this.versioneoggetto = versioneoggetto;
    }


    /**
     * Gets the identificativounivocoregolamento value for this GetFlussoRiversamentoByParam.
     * 
     * @return identificativounivocoregolamento
     */
    public java.lang.String getIdentificativounivocoregolamento() {
        return identificativounivocoregolamento;
    }


    /**
     * Sets the identificativounivocoregolamento value for this GetFlussoRiversamentoByParam.
     * 
     * @param identificativounivocoregolamento
     */
    public void setIdentificativounivocoregolamento(java.lang.String identificativounivocoregolamento) {
        this.identificativounivocoregolamento = identificativounivocoregolamento;
    }


    /**
     * Gets the identificativoistitutomittente value for this GetFlussoRiversamentoByParam.
     * 
     * @return identificativoistitutomittente
     */
    public java.lang.String getIdentificativoistitutomittente() {
        return identificativoistitutomittente;
    }


    /**
     * Sets the identificativoistitutomittente value for this GetFlussoRiversamentoByParam.
     * 
     * @param identificativoistitutomittente
     */
    public void setIdentificativoistitutomittente(java.lang.String identificativoistitutomittente) {
        this.identificativoistitutomittente = identificativoistitutomittente;
    }


    /**
     * Gets the identificativoistitutoricevente value for this GetFlussoRiversamentoByParam.
     * 
     * @return identificativoistitutoricevente
     */
    public java.lang.String getIdentificativoistitutoricevente() {
        return identificativoistitutoricevente;
    }


    /**
     * Sets the identificativoistitutoricevente value for this GetFlussoRiversamentoByParam.
     * 
     * @param identificativoistitutoricevente
     */
    public void setIdentificativoistitutoricevente(java.lang.String identificativoistitutoricevente) {
        this.identificativoistitutoricevente = identificativoistitutoricevente;
    }


    /**
     * Gets the numerototalepagamenti value for this GetFlussoRiversamentoByParam.
     * 
     * @return numerototalepagamenti
     */
    public java.lang.Integer getNumerototalepagamenti() {
        return numerototalepagamenti;
    }


    /**
     * Sets the numerototalepagamenti value for this GetFlussoRiversamentoByParam.
     * 
     * @param numerototalepagamenti
     */
    public void setNumerototalepagamenti(java.lang.Integer numerototalepagamenti) {
        this.numerototalepagamenti = numerototalepagamenti;
    }


    /**
     * Gets the importototalepagamenti value for this GetFlussoRiversamentoByParam.
     * 
     * @return importototalepagamenti
     */
    public java.lang.Double getImportototalepagamenti() {
        return importototalepagamenti;
    }


    /**
     * Sets the importototalepagamenti value for this GetFlussoRiversamentoByParam.
     * 
     * @param importototalepagamenti
     */
    public void setImportototalepagamenti(java.lang.Double importototalepagamenti) {
        this.importototalepagamenti = importototalepagamenti;
    }


    /**
     * Gets the dataoraflusso value for this GetFlussoRiversamentoByParam.
     * 
     * @return dataoraflusso
     */
    public java.util.Calendar getDataoraflusso() {
        return dataoraflusso;
    }


    /**
     * Sets the dataoraflusso value for this GetFlussoRiversamentoByParam.
     * 
     * @param dataoraflusso
     */
    public void setDataoraflusso(java.util.Calendar dataoraflusso) {
        this.dataoraflusso = dataoraflusso;
    }


    /**
     * Gets the dataregolamentoDa value for this GetFlussoRiversamentoByParam.
     * 
     * @return dataregolamentoDa
     */
    public java.util.Calendar getDataregolamentoDa() {
        return dataregolamentoDa;
    }


    /**
     * Sets the dataregolamentoDa value for this GetFlussoRiversamentoByParam.
     * 
     * @param dataregolamentoDa
     */
    public void setDataregolamentoDa(java.util.Calendar dataregolamentoDa) {
        this.dataregolamentoDa = dataregolamentoDa;
    }


    /**
     * Gets the dataregolamentoA value for this GetFlussoRiversamentoByParam.
     * 
     * @return dataregolamentoA
     */
    public java.util.Calendar getDataregolamentoA() {
        return dataregolamentoA;
    }


    /**
     * Sets the dataregolamentoA value for this GetFlussoRiversamentoByParam.
     * 
     * @param dataregolamentoA
     */
    public void setDataregolamentoA(java.util.Calendar dataregolamentoA) {
        this.dataregolamentoA = dataregolamentoA;
    }


    /**
     * Gets the datainserimento value for this GetFlussoRiversamentoByParam.
     * 
     * @return datainserimento
     */
    public java.util.Calendar getDatainserimento() {
        return datainserimento;
    }


    /**
     * Sets the datainserimento value for this GetFlussoRiversamentoByParam.
     * 
     * @param datainserimento
     */
    public void setDatainserimento(java.util.Calendar datainserimento) {
        this.datainserimento = datainserimento;
    }


    /**
     * Gets the datamodifica value for this GetFlussoRiversamentoByParam.
     * 
     * @return datamodifica
     */
    public java.util.Calendar getDatamodifica() {
        return datamodifica;
    }


    /**
     * Sets the datamodifica value for this GetFlussoRiversamentoByParam.
     * 
     * @param datamodifica
     */
    public void setDatamodifica(java.util.Calendar datamodifica) {
        this.datamodifica = datamodifica;
    }


    /**
     * Gets the xmlflusso value for this GetFlussoRiversamentoByParam.
     * 
     * @return xmlflusso
     */
    public java.lang.String getXmlflusso() {
        return xmlflusso;
    }


    /**
     * Sets the xmlflusso value for this GetFlussoRiversamentoByParam.
     * 
     * @param xmlflusso
     */
    public void setXmlflusso(java.lang.String xmlflusso) {
        this.xmlflusso = xmlflusso;
    }


    /**
     * Gets the denominazionemittente value for this GetFlussoRiversamentoByParam.
     * 
     * @return denominazionemittente
     */
    public java.lang.String getDenominazionemittente() {
        return denominazionemittente;
    }


    /**
     * Sets the denominazionemittente value for this GetFlussoRiversamentoByParam.
     * 
     * @param denominazionemittente
     */
    public void setDenominazionemittente(java.lang.String denominazionemittente) {
        this.denominazionemittente = denominazionemittente;
    }


    /**
     * Gets the denominazionericevente value for this GetFlussoRiversamentoByParam.
     * 
     * @return denominazionericevente
     */
    public java.lang.String getDenominazionericevente() {
        return denominazionericevente;
    }


    /**
     * Sets the denominazionericevente value for this GetFlussoRiversamentoByParam.
     * 
     * @param denominazionericevente
     */
    public void setDenominazionericevente(java.lang.String denominazionericevente) {
        this.denominazionericevente = denominazionericevente;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetFlussoRiversamentoByParam)) return false;
        GetFlussoRiversamentoByParam other = (GetFlussoRiversamentoByParam) obj;
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
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.identificativopsp==null && other.getIdentificativopsp()==null) || 
             (this.identificativopsp!=null &&
              this.identificativopsp.equals(other.getIdentificativopsp()))) &&
            ((this.identificativoflusso==null && other.getIdentificativoflusso()==null) || 
             (this.identificativoflusso!=null &&
              this.identificativoflusso.equals(other.getIdentificativoflusso()))) &&
            ((this.versioneoggetto==null && other.getVersioneoggetto()==null) || 
             (this.versioneoggetto!=null &&
              this.versioneoggetto.equals(other.getVersioneoggetto()))) &&
            ((this.identificativounivocoregolamento==null && other.getIdentificativounivocoregolamento()==null) || 
             (this.identificativounivocoregolamento!=null &&
              this.identificativounivocoregolamento.equals(other.getIdentificativounivocoregolamento()))) &&
            ((this.identificativoistitutomittente==null && other.getIdentificativoistitutomittente()==null) || 
             (this.identificativoistitutomittente!=null &&
              this.identificativoistitutomittente.equals(other.getIdentificativoistitutomittente()))) &&
            ((this.identificativoistitutoricevente==null && other.getIdentificativoistitutoricevente()==null) || 
             (this.identificativoistitutoricevente!=null &&
              this.identificativoistitutoricevente.equals(other.getIdentificativoistitutoricevente()))) &&
            ((this.numerototalepagamenti==null && other.getNumerototalepagamenti()==null) || 
             (this.numerototalepagamenti!=null &&
              this.numerototalepagamenti.equals(other.getNumerototalepagamenti()))) &&
            ((this.importototalepagamenti==null && other.getImportototalepagamenti()==null) || 
             (this.importototalepagamenti!=null &&
              this.importototalepagamenti.equals(other.getImportototalepagamenti()))) &&
            ((this.dataoraflusso==null && other.getDataoraflusso()==null) || 
             (this.dataoraflusso!=null &&
              this.dataoraflusso.equals(other.getDataoraflusso()))) &&
            ((this.dataregolamentoDa==null && other.getDataregolamentoDa()==null) || 
             (this.dataregolamentoDa!=null &&
              this.dataregolamentoDa.equals(other.getDataregolamentoDa()))) &&
            ((this.dataregolamentoA==null && other.getDataregolamentoA()==null) || 
             (this.dataregolamentoA!=null &&
              this.dataregolamentoA.equals(other.getDataregolamentoA()))) &&
            ((this.datainserimento==null && other.getDatainserimento()==null) || 
             (this.datainserimento!=null &&
              this.datainserimento.equals(other.getDatainserimento()))) &&
            ((this.datamodifica==null && other.getDatamodifica()==null) || 
             (this.datamodifica!=null &&
              this.datamodifica.equals(other.getDatamodifica()))) &&
            ((this.xmlflusso==null && other.getXmlflusso()==null) || 
             (this.xmlflusso!=null &&
              this.xmlflusso.equals(other.getXmlflusso()))) &&
            ((this.denominazionemittente==null && other.getDenominazionemittente()==null) || 
             (this.denominazionemittente!=null &&
              this.denominazionemittente.equals(other.getDenominazionemittente()))) &&
            ((this.denominazionericevente==null && other.getDenominazionericevente()==null) || 
             (this.denominazionericevente!=null &&
              this.denominazionericevente.equals(other.getDenominazionericevente())));
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
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getIdentificativopsp() != null) {
            _hashCode += getIdentificativopsp().hashCode();
        }
        if (getIdentificativoflusso() != null) {
            _hashCode += getIdentificativoflusso().hashCode();
        }
        if (getVersioneoggetto() != null) {
            _hashCode += getVersioneoggetto().hashCode();
        }
        if (getIdentificativounivocoregolamento() != null) {
            _hashCode += getIdentificativounivocoregolamento().hashCode();
        }
        if (getIdentificativoistitutomittente() != null) {
            _hashCode += getIdentificativoistitutomittente().hashCode();
        }
        if (getIdentificativoistitutoricevente() != null) {
            _hashCode += getIdentificativoistitutoricevente().hashCode();
        }
        if (getNumerototalepagamenti() != null) {
            _hashCode += getNumerototalepagamenti().hashCode();
        }
        if (getImportototalepagamenti() != null) {
            _hashCode += getImportototalepagamenti().hashCode();
        }
        if (getDataoraflusso() != null) {
            _hashCode += getDataoraflusso().hashCode();
        }
        if (getDataregolamentoDa() != null) {
            _hashCode += getDataregolamentoDa().hashCode();
        }
        if (getDataregolamentoA() != null) {
            _hashCode += getDataregolamentoA().hashCode();
        }
        if (getDatainserimento() != null) {
            _hashCode += getDatainserimento().hashCode();
        }
        if (getDatamodifica() != null) {
            _hashCode += getDatamodifica().hashCode();
        }
        if (getXmlflusso() != null) {
            _hashCode += getXmlflusso().hashCode();
        }
        if (getDenominazionemittente() != null) {
            _hashCode += getDenominazionemittente().hashCode();
        }
        if (getDenominazionericevente() != null) {
            _hashCode += getDenominazionericevente().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetFlussoRiversamentoByParam.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getFlussoRiversamentoByParam"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("auth");
        elemField.setXmlName(new javax.xml.namespace.QName("", "auth"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "credentials"));
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
        elemField.setFieldName("identificativopsp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "identificativopsp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificativoflusso");
        elemField.setXmlName(new javax.xml.namespace.QName("", "identificativoflusso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("versioneoggetto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "versioneoggetto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificativounivocoregolamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "identificativounivocoregolamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificativoistitutomittente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "identificativoistitutomittente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificativoistitutoricevente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "identificativoistitutoricevente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numerototalepagamenti");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numerototalepagamenti"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("importototalepagamenti");
        elemField.setXmlName(new javax.xml.namespace.QName("", "importototalepagamenti"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataoraflusso");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataoraflusso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataregolamentoDa");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataregolamentoDa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataregolamentoA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataregolamentoA"));
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
        elemField.setFieldName("datamodifica");
        elemField.setXmlName(new javax.xml.namespace.QName("", "datamodifica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("xmlflusso");
        elemField.setXmlName(new javax.xml.namespace.QName("", "xmlflusso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("denominazionemittente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "denominazionemittente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("denominazionericevente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "denominazionericevente"));
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
