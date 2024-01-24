/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class Verrori  implements java.io.Serializable {
    private float amount;

    private boolean amountNull;

    private java.lang.String applicationId;

    private java.lang.String applicationname;

    private java.lang.String atewaypaymodeid;

    private long codStato;

    private boolean codStatoNull;

    private java.lang.String currencydescr;

    private java.util.Calendar data;

    private java.lang.String descrizione;

    private java.lang.String descrizionestatotrans;

    private java.util.Calendar finishDate;

    private java.lang.String gatewayDescription;

    private java.lang.String gatewayId;

    private java.util.Calendar initDate;

    private java.lang.String paymentmodeDescription;

    private java.lang.String payurl;

    private java.lang.String pgresultcode;

    private java.util.Calendar startDate;

    private java.lang.String transactionId;

    public Verrori() {
    }

    public Verrori(
           float amount,
           boolean amountNull,
           java.lang.String applicationId,
           java.lang.String applicationname,
           java.lang.String atewaypaymodeid,
           long codStato,
           boolean codStatoNull,
           java.lang.String currencydescr,
           java.util.Calendar data,
           java.lang.String descrizione,
           java.lang.String descrizionestatotrans,
           java.util.Calendar finishDate,
           java.lang.String gatewayDescription,
           java.lang.String gatewayId,
           java.util.Calendar initDate,
           java.lang.String paymentmodeDescription,
           java.lang.String payurl,
           java.lang.String pgresultcode,
           java.util.Calendar startDate,
           java.lang.String transactionId) {
           this.amount = amount;
           this.amountNull = amountNull;
           this.applicationId = applicationId;
           this.applicationname = applicationname;
           this.atewaypaymodeid = atewaypaymodeid;
           this.codStato = codStato;
           this.codStatoNull = codStatoNull;
           this.currencydescr = currencydescr;
           this.data = data;
           this.descrizione = descrizione;
           this.descrizionestatotrans = descrizionestatotrans;
           this.finishDate = finishDate;
           this.gatewayDescription = gatewayDescription;
           this.gatewayId = gatewayId;
           this.initDate = initDate;
           this.paymentmodeDescription = paymentmodeDescription;
           this.payurl = payurl;
           this.pgresultcode = pgresultcode;
           this.startDate = startDate;
           this.transactionId = transactionId;
    }


    /**
     * Gets the amount value for this Verrori.
     * 
     * @return amount
     */
    public float getAmount() {
        return amount;
    }


    /**
     * Sets the amount value for this Verrori.
     * 
     * @param amount
     */
    public void setAmount(float amount) {
        this.amount = amount;
    }


    /**
     * Gets the amountNull value for this Verrori.
     * 
     * @return amountNull
     */
    public boolean isAmountNull() {
        return amountNull;
    }


    /**
     * Sets the amountNull value for this Verrori.
     * 
     * @param amountNull
     */
    public void setAmountNull(boolean amountNull) {
        this.amountNull = amountNull;
    }


    /**
     * Gets the applicationId value for this Verrori.
     * 
     * @return applicationId
     */
    public java.lang.String getApplicationId() {
        return applicationId;
    }


    /**
     * Sets the applicationId value for this Verrori.
     * 
     * @param applicationId
     */
    public void setApplicationId(java.lang.String applicationId) {
        this.applicationId = applicationId;
    }


    /**
     * Gets the applicationname value for this Verrori.
     * 
     * @return applicationname
     */
    public java.lang.String getApplicationname() {
        return applicationname;
    }


    /**
     * Sets the applicationname value for this Verrori.
     * 
     * @param applicationname
     */
    public void setApplicationname(java.lang.String applicationname) {
        this.applicationname = applicationname;
    }


    /**
     * Gets the atewaypaymodeid value for this Verrori.
     * 
     * @return atewaypaymodeid
     */
    public java.lang.String getAtewaypaymodeid() {
        return atewaypaymodeid;
    }


    /**
     * Sets the atewaypaymodeid value for this Verrori.
     * 
     * @param atewaypaymodeid
     */
    public void setAtewaypaymodeid(java.lang.String atewaypaymodeid) {
        this.atewaypaymodeid = atewaypaymodeid;
    }


    /**
     * Gets the codStato value for this Verrori.
     * 
     * @return codStato
     */
    public long getCodStato() {
        return codStato;
    }


    /**
     * Sets the codStato value for this Verrori.
     * 
     * @param codStato
     */
    public void setCodStato(long codStato) {
        this.codStato = codStato;
    }


    /**
     * Gets the codStatoNull value for this Verrori.
     * 
     * @return codStatoNull
     */
    public boolean isCodStatoNull() {
        return codStatoNull;
    }


    /**
     * Sets the codStatoNull value for this Verrori.
     * 
     * @param codStatoNull
     */
    public void setCodStatoNull(boolean codStatoNull) {
        this.codStatoNull = codStatoNull;
    }


    /**
     * Gets the currencydescr value for this Verrori.
     * 
     * @return currencydescr
     */
    public java.lang.String getCurrencydescr() {
        return currencydescr;
    }


    /**
     * Sets the currencydescr value for this Verrori.
     * 
     * @param currencydescr
     */
    public void setCurrencydescr(java.lang.String currencydescr) {
        this.currencydescr = currencydescr;
    }


    /**
     * Gets the data value for this Verrori.
     * 
     * @return data
     */
    public java.util.Calendar getData() {
        return data;
    }


    /**
     * Sets the data value for this Verrori.
     * 
     * @param data
     */
    public void setData(java.util.Calendar data) {
        this.data = data;
    }


    /**
     * Gets the descrizione value for this Verrori.
     * 
     * @return descrizione
     */
    public java.lang.String getDescrizione() {
        return descrizione;
    }


    /**
     * Sets the descrizione value for this Verrori.
     * 
     * @param descrizione
     */
    public void setDescrizione(java.lang.String descrizione) {
        this.descrizione = descrizione;
    }


    /**
     * Gets the descrizionestatotrans value for this Verrori.
     * 
     * @return descrizionestatotrans
     */
    public java.lang.String getDescrizionestatotrans() {
        return descrizionestatotrans;
    }


    /**
     * Sets the descrizionestatotrans value for this Verrori.
     * 
     * @param descrizionestatotrans
     */
    public void setDescrizionestatotrans(java.lang.String descrizionestatotrans) {
        this.descrizionestatotrans = descrizionestatotrans;
    }


    /**
     * Gets the finishDate value for this Verrori.
     * 
     * @return finishDate
     */
    public java.util.Calendar getFinishDate() {
        return finishDate;
    }


    /**
     * Sets the finishDate value for this Verrori.
     * 
     * @param finishDate
     */
    public void setFinishDate(java.util.Calendar finishDate) {
        this.finishDate = finishDate;
    }


    /**
     * Gets the gatewayDescription value for this Verrori.
     * 
     * @return gatewayDescription
     */
    public java.lang.String getGatewayDescription() {
        return gatewayDescription;
    }


    /**
     * Sets the gatewayDescription value for this Verrori.
     * 
     * @param gatewayDescription
     */
    public void setGatewayDescription(java.lang.String gatewayDescription) {
        this.gatewayDescription = gatewayDescription;
    }


    /**
     * Gets the gatewayId value for this Verrori.
     * 
     * @return gatewayId
     */
    public java.lang.String getGatewayId() {
        return gatewayId;
    }


    /**
     * Sets the gatewayId value for this Verrori.
     * 
     * @param gatewayId
     */
    public void setGatewayId(java.lang.String gatewayId) {
        this.gatewayId = gatewayId;
    }


    /**
     * Gets the initDate value for this Verrori.
     * 
     * @return initDate
     */
    public java.util.Calendar getInitDate() {
        return initDate;
    }


    /**
     * Sets the initDate value for this Verrori.
     * 
     * @param initDate
     */
    public void setInitDate(java.util.Calendar initDate) {
        this.initDate = initDate;
    }


    /**
     * Gets the paymentmodeDescription value for this Verrori.
     * 
     * @return paymentmodeDescription
     */
    public java.lang.String getPaymentmodeDescription() {
        return paymentmodeDescription;
    }


    /**
     * Sets the paymentmodeDescription value for this Verrori.
     * 
     * @param paymentmodeDescription
     */
    public void setPaymentmodeDescription(java.lang.String paymentmodeDescription) {
        this.paymentmodeDescription = paymentmodeDescription;
    }


    /**
     * Gets the payurl value for this Verrori.
     * 
     * @return payurl
     */
    public java.lang.String getPayurl() {
        return payurl;
    }


    /**
     * Sets the payurl value for this Verrori.
     * 
     * @param payurl
     */
    public void setPayurl(java.lang.String payurl) {
        this.payurl = payurl;
    }


    /**
     * Gets the pgresultcode value for this Verrori.
     * 
     * @return pgresultcode
     */
    public java.lang.String getPgresultcode() {
        return pgresultcode;
    }


    /**
     * Sets the pgresultcode value for this Verrori.
     * 
     * @param pgresultcode
     */
    public void setPgresultcode(java.lang.String pgresultcode) {
        this.pgresultcode = pgresultcode;
    }


    /**
     * Gets the startDate value for this Verrori.
     * 
     * @return startDate
     */
    public java.util.Calendar getStartDate() {
        return startDate;
    }


    /**
     * Sets the startDate value for this Verrori.
     * 
     * @param startDate
     */
    public void setStartDate(java.util.Calendar startDate) {
        this.startDate = startDate;
    }


    /**
     * Gets the transactionId value for this Verrori.
     * 
     * @return transactionId
     */
    public java.lang.String getTransactionId() {
        return transactionId;
    }


    /**
     * Sets the transactionId value for this Verrori.
     * 
     * @param transactionId
     */
    public void setTransactionId(java.lang.String transactionId) {
        this.transactionId = transactionId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Verrori)) return false;
        Verrori other = (Verrori) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.amount == other.getAmount() &&
            this.amountNull == other.isAmountNull() &&
            ((this.applicationId==null && other.getApplicationId()==null) || 
             (this.applicationId!=null &&
              this.applicationId.equals(other.getApplicationId()))) &&
            ((this.applicationname==null && other.getApplicationname()==null) || 
             (this.applicationname!=null &&
              this.applicationname.equals(other.getApplicationname()))) &&
            ((this.atewaypaymodeid==null && other.getAtewaypaymodeid()==null) || 
             (this.atewaypaymodeid!=null &&
              this.atewaypaymodeid.equals(other.getAtewaypaymodeid()))) &&
            this.codStato == other.getCodStato() &&
            this.codStatoNull == other.isCodStatoNull() &&
            ((this.currencydescr==null && other.getCurrencydescr()==null) || 
             (this.currencydescr!=null &&
              this.currencydescr.equals(other.getCurrencydescr()))) &&
            ((this.data==null && other.getData()==null) || 
             (this.data!=null &&
              this.data.equals(other.getData()))) &&
            ((this.descrizione==null && other.getDescrizione()==null) || 
             (this.descrizione!=null &&
              this.descrizione.equals(other.getDescrizione()))) &&
            ((this.descrizionestatotrans==null && other.getDescrizionestatotrans()==null) || 
             (this.descrizionestatotrans!=null &&
              this.descrizionestatotrans.equals(other.getDescrizionestatotrans()))) &&
            ((this.finishDate==null && other.getFinishDate()==null) || 
             (this.finishDate!=null &&
              this.finishDate.equals(other.getFinishDate()))) &&
            ((this.gatewayDescription==null && other.getGatewayDescription()==null) || 
             (this.gatewayDescription!=null &&
              this.gatewayDescription.equals(other.getGatewayDescription()))) &&
            ((this.gatewayId==null && other.getGatewayId()==null) || 
             (this.gatewayId!=null &&
              this.gatewayId.equals(other.getGatewayId()))) &&
            ((this.initDate==null && other.getInitDate()==null) || 
             (this.initDate!=null &&
              this.initDate.equals(other.getInitDate()))) &&
            ((this.paymentmodeDescription==null && other.getPaymentmodeDescription()==null) || 
             (this.paymentmodeDescription!=null &&
              this.paymentmodeDescription.equals(other.getPaymentmodeDescription()))) &&
            ((this.payurl==null && other.getPayurl()==null) || 
             (this.payurl!=null &&
              this.payurl.equals(other.getPayurl()))) &&
            ((this.pgresultcode==null && other.getPgresultcode()==null) || 
             (this.pgresultcode!=null &&
              this.pgresultcode.equals(other.getPgresultcode()))) &&
            ((this.startDate==null && other.getStartDate()==null) || 
             (this.startDate!=null &&
              this.startDate.equals(other.getStartDate()))) &&
            ((this.transactionId==null && other.getTransactionId()==null) || 
             (this.transactionId!=null &&
              this.transactionId.equals(other.getTransactionId())));
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
        _hashCode += new Float(getAmount()).hashCode();
        _hashCode += (isAmountNull() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getApplicationId() != null) {
            _hashCode += getApplicationId().hashCode();
        }
        if (getApplicationname() != null) {
            _hashCode += getApplicationname().hashCode();
        }
        if (getAtewaypaymodeid() != null) {
            _hashCode += getAtewaypaymodeid().hashCode();
        }
        _hashCode += new Long(getCodStato()).hashCode();
        _hashCode += (isCodStatoNull() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getCurrencydescr() != null) {
            _hashCode += getCurrencydescr().hashCode();
        }
        if (getData() != null) {
            _hashCode += getData().hashCode();
        }
        if (getDescrizione() != null) {
            _hashCode += getDescrizione().hashCode();
        }
        if (getDescrizionestatotrans() != null) {
            _hashCode += getDescrizionestatotrans().hashCode();
        }
        if (getFinishDate() != null) {
            _hashCode += getFinishDate().hashCode();
        }
        if (getGatewayDescription() != null) {
            _hashCode += getGatewayDescription().hashCode();
        }
        if (getGatewayId() != null) {
            _hashCode += getGatewayId().hashCode();
        }
        if (getInitDate() != null) {
            _hashCode += getInitDate().hashCode();
        }
        if (getPaymentmodeDescription() != null) {
            _hashCode += getPaymentmodeDescription().hashCode();
        }
        if (getPayurl() != null) {
            _hashCode += getPayurl().hashCode();
        }
        if (getPgresultcode() != null) {
            _hashCode += getPgresultcode().hashCode();
        }
        if (getStartDate() != null) {
            _hashCode += getStartDate().hashCode();
        }
        if (getTransactionId() != null) {
            _hashCode += getTransactionId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Verrori.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "verrori"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("amountNull");
        elemField.setXmlName(new javax.xml.namespace.QName("", "amountNull"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "applicationId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationname");
        elemField.setXmlName(new javax.xml.namespace.QName("", "applicationname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("atewaypaymodeid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "atewaypaymodeid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codStato");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codStato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codStatoNull");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codStatoNull"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currencydescr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "currencydescr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("data");
        elemField.setXmlName(new javax.xml.namespace.QName("", "data"));
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
        elemField.setFieldName("descrizionestatotrans");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descrizionestatotrans"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("finishDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "finishDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gatewayDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gatewayDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gatewayId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gatewayId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("initDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "initDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paymentmodeDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("", "paymentmodeDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("payurl");
        elemField.setXmlName(new javax.xml.namespace.QName("", "payurl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pgresultcode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pgresultcode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("startDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "startDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transactionId"));
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
