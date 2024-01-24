/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class CsiLogAudit  implements java.io.Serializable {
    private java.lang.String applicationid;

    private java.lang.String codappmodify;

    private java.lang.String codfisc;

    private java.util.Calendar dataOra;

    private java.lang.String descrizione;

    private java.lang.String gatewayid;

    private java.lang.String idApp;

    private java.lang.String idaction;

    private java.lang.String ipAddress;

    private java.lang.String keyOper;

    private java.lang.String oggOper;

    private java.lang.String operazione;

    private java.lang.String transactionid;

    private int uniqueid;

    private int utente;

    public CsiLogAudit() {
    }

    public CsiLogAudit(
           java.lang.String applicationid,
           java.lang.String codappmodify,
           java.lang.String codfisc,
           java.util.Calendar dataOra,
           java.lang.String descrizione,
           java.lang.String gatewayid,
           java.lang.String idApp,
           java.lang.String idaction,
           java.lang.String ipAddress,
           java.lang.String keyOper,
           java.lang.String oggOper,
           java.lang.String operazione,
           java.lang.String transactionid,
           int uniqueid,
           int utente) {
           this.applicationid = applicationid;
           this.codappmodify = codappmodify;
           this.codfisc = codfisc;
           this.dataOra = dataOra;
           this.descrizione = descrizione;
           this.gatewayid = gatewayid;
           this.idApp = idApp;
           this.idaction = idaction;
           this.ipAddress = ipAddress;
           this.keyOper = keyOper;
           this.oggOper = oggOper;
           this.operazione = operazione;
           this.transactionid = transactionid;
           this.uniqueid = uniqueid;
           this.utente = utente;
    }


    /**
     * Gets the applicationid value for this CsiLogAudit.
     * 
     * @return applicationid
     */
    public java.lang.String getApplicationid() {
        return applicationid;
    }


    /**
     * Sets the applicationid value for this CsiLogAudit.
     * 
     * @param applicationid
     */
    public void setApplicationid(java.lang.String applicationid) {
        this.applicationid = applicationid;
    }


    /**
     * Gets the codappmodify value for this CsiLogAudit.
     * 
     * @return codappmodify
     */
    public java.lang.String getCodappmodify() {
        return codappmodify;
    }


    /**
     * Sets the codappmodify value for this CsiLogAudit.
     * 
     * @param codappmodify
     */
    public void setCodappmodify(java.lang.String codappmodify) {
        this.codappmodify = codappmodify;
    }


    /**
     * Gets the codfisc value for this CsiLogAudit.
     * 
     * @return codfisc
     */
    public java.lang.String getCodfisc() {
        return codfisc;
    }


    /**
     * Sets the codfisc value for this CsiLogAudit.
     * 
     * @param codfisc
     */
    public void setCodfisc(java.lang.String codfisc) {
        this.codfisc = codfisc;
    }


    /**
     * Gets the dataOra value for this CsiLogAudit.
     * 
     * @return dataOra
     */
    public java.util.Calendar getDataOra() {
        return dataOra;
    }


    /**
     * Sets the dataOra value for this CsiLogAudit.
     * 
     * @param dataOra
     */
    public void setDataOra(java.util.Calendar dataOra) {
        this.dataOra = dataOra;
    }


    /**
     * Gets the descrizione value for this CsiLogAudit.
     * 
     * @return descrizione
     */
    public java.lang.String getDescrizione() {
        return descrizione;
    }


    /**
     * Sets the descrizione value for this CsiLogAudit.
     * 
     * @param descrizione
     */
    public void setDescrizione(java.lang.String descrizione) {
        this.descrizione = descrizione;
    }


    /**
     * Gets the gatewayid value for this CsiLogAudit.
     * 
     * @return gatewayid
     */
    public java.lang.String getGatewayid() {
        return gatewayid;
    }


    /**
     * Sets the gatewayid value for this CsiLogAudit.
     * 
     * @param gatewayid
     */
    public void setGatewayid(java.lang.String gatewayid) {
        this.gatewayid = gatewayid;
    }


    /**
     * Gets the idApp value for this CsiLogAudit.
     * 
     * @return idApp
     */
    public java.lang.String getIdApp() {
        return idApp;
    }


    /**
     * Sets the idApp value for this CsiLogAudit.
     * 
     * @param idApp
     */
    public void setIdApp(java.lang.String idApp) {
        this.idApp = idApp;
    }


    /**
     * Gets the idaction value for this CsiLogAudit.
     * 
     * @return idaction
     */
    public java.lang.String getIdaction() {
        return idaction;
    }


    /**
     * Sets the idaction value for this CsiLogAudit.
     * 
     * @param idaction
     */
    public void setIdaction(java.lang.String idaction) {
        this.idaction = idaction;
    }


    /**
     * Gets the ipAddress value for this CsiLogAudit.
     * 
     * @return ipAddress
     */
    public java.lang.String getIpAddress() {
        return ipAddress;
    }


    /**
     * Sets the ipAddress value for this CsiLogAudit.
     * 
     * @param ipAddress
     */
    public void setIpAddress(java.lang.String ipAddress) {
        this.ipAddress = ipAddress;
    }


    /**
     * Gets the keyOper value for this CsiLogAudit.
     * 
     * @return keyOper
     */
    public java.lang.String getKeyOper() {
        return keyOper;
    }


    /**
     * Sets the keyOper value for this CsiLogAudit.
     * 
     * @param keyOper
     */
    public void setKeyOper(java.lang.String keyOper) {
        this.keyOper = keyOper;
    }


    /**
     * Gets the oggOper value for this CsiLogAudit.
     * 
     * @return oggOper
     */
    public java.lang.String getOggOper() {
        return oggOper;
    }


    /**
     * Sets the oggOper value for this CsiLogAudit.
     * 
     * @param oggOper
     */
    public void setOggOper(java.lang.String oggOper) {
        this.oggOper = oggOper;
    }


    /**
     * Gets the operazione value for this CsiLogAudit.
     * 
     * @return operazione
     */
    public java.lang.String getOperazione() {
        return operazione;
    }


    /**
     * Sets the operazione value for this CsiLogAudit.
     * 
     * @param operazione
     */
    public void setOperazione(java.lang.String operazione) {
        this.operazione = operazione;
    }


    /**
     * Gets the transactionid value for this CsiLogAudit.
     * 
     * @return transactionid
     */
    public java.lang.String getTransactionid() {
        return transactionid;
    }


    /**
     * Sets the transactionid value for this CsiLogAudit.
     * 
     * @param transactionid
     */
    public void setTransactionid(java.lang.String transactionid) {
        this.transactionid = transactionid;
    }


    /**
     * Gets the uniqueid value for this CsiLogAudit.
     * 
     * @return uniqueid
     */
    public int getUniqueid() {
        return uniqueid;
    }


    /**
     * Sets the uniqueid value for this CsiLogAudit.
     * 
     * @param uniqueid
     */
    public void setUniqueid(int uniqueid) {
        this.uniqueid = uniqueid;
    }


    /**
     * Gets the utente value for this CsiLogAudit.
     * 
     * @return utente
     */
    public int getUtente() {
        return utente;
    }


    /**
     * Sets the utente value for this CsiLogAudit.
     * 
     * @param utente
     */
    public void setUtente(int utente) {
        this.utente = utente;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CsiLogAudit)) return false;
        CsiLogAudit other = (CsiLogAudit) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.applicationid==null && other.getApplicationid()==null) || 
             (this.applicationid!=null &&
              this.applicationid.equals(other.getApplicationid()))) &&
            ((this.codappmodify==null && other.getCodappmodify()==null) || 
             (this.codappmodify!=null &&
              this.codappmodify.equals(other.getCodappmodify()))) &&
            ((this.codfisc==null && other.getCodfisc()==null) || 
             (this.codfisc!=null &&
              this.codfisc.equals(other.getCodfisc()))) &&
            ((this.dataOra==null && other.getDataOra()==null) || 
             (this.dataOra!=null &&
              this.dataOra.equals(other.getDataOra()))) &&
            ((this.descrizione==null && other.getDescrizione()==null) || 
             (this.descrizione!=null &&
              this.descrizione.equals(other.getDescrizione()))) &&
            ((this.gatewayid==null && other.getGatewayid()==null) || 
             (this.gatewayid!=null &&
              this.gatewayid.equals(other.getGatewayid()))) &&
            ((this.idApp==null && other.getIdApp()==null) || 
             (this.idApp!=null &&
              this.idApp.equals(other.getIdApp()))) &&
            ((this.idaction==null && other.getIdaction()==null) || 
             (this.idaction!=null &&
              this.idaction.equals(other.getIdaction()))) &&
            ((this.ipAddress==null && other.getIpAddress()==null) || 
             (this.ipAddress!=null &&
              this.ipAddress.equals(other.getIpAddress()))) &&
            ((this.keyOper==null && other.getKeyOper()==null) || 
             (this.keyOper!=null &&
              this.keyOper.equals(other.getKeyOper()))) &&
            ((this.oggOper==null && other.getOggOper()==null) || 
             (this.oggOper!=null &&
              this.oggOper.equals(other.getOggOper()))) &&
            ((this.operazione==null && other.getOperazione()==null) || 
             (this.operazione!=null &&
              this.operazione.equals(other.getOperazione()))) &&
            ((this.transactionid==null && other.getTransactionid()==null) || 
             (this.transactionid!=null &&
              this.transactionid.equals(other.getTransactionid()))) &&
            this.uniqueid == other.getUniqueid() &&
            this.utente == other.getUtente();
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
        if (getApplicationid() != null) {
            _hashCode += getApplicationid().hashCode();
        }
        if (getCodappmodify() != null) {
            _hashCode += getCodappmodify().hashCode();
        }
        if (getCodfisc() != null) {
            _hashCode += getCodfisc().hashCode();
        }
        if (getDataOra() != null) {
            _hashCode += getDataOra().hashCode();
        }
        if (getDescrizione() != null) {
            _hashCode += getDescrizione().hashCode();
        }
        if (getGatewayid() != null) {
            _hashCode += getGatewayid().hashCode();
        }
        if (getIdApp() != null) {
            _hashCode += getIdApp().hashCode();
        }
        if (getIdaction() != null) {
            _hashCode += getIdaction().hashCode();
        }
        if (getIpAddress() != null) {
            _hashCode += getIpAddress().hashCode();
        }
        if (getKeyOper() != null) {
            _hashCode += getKeyOper().hashCode();
        }
        if (getOggOper() != null) {
            _hashCode += getOggOper().hashCode();
        }
        if (getOperazione() != null) {
            _hashCode += getOperazione().hashCode();
        }
        if (getTransactionid() != null) {
            _hashCode += getTransactionid().hashCode();
        }
        _hashCode += getUniqueid();
        _hashCode += getUtente();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CsiLogAudit.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "csiLogAudit"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "applicationid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codappmodify");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codappmodify"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codfisc");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codfisc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataOra");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataOra"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrizione");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descrizione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gatewayid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gatewayid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idApp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idApp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idaction");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idaction"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ipAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ipAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("keyOper");
        elemField.setXmlName(new javax.xml.namespace.QName("", "keyOper"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("oggOper");
        elemField.setXmlName(new javax.xml.namespace.QName("", "oggOper"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("operazione");
        elemField.setXmlName(new javax.xml.namespace.QName("", "operazione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transactionid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("uniqueid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "uniqueid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("utente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "utente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
