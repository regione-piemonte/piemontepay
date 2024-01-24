/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class Gatewaydetail  implements java.io.Serializable {
    private java.lang.String backofficeurl;

    private java.lang.String contextpg;

    private java.lang.String defaultpaymenturl;

    private java.lang.String enabled;

    private java.lang.String errorurl;

    private java.lang.String gatewayId;

    private long httpport;

    private boolean httpportNull;

    private java.lang.String mdpgatewaypage;

    private java.lang.String paymentmodeId;

    private java.lang.String receipturl;

    private java.lang.String returnurlmdp;

    private java.lang.String sendmailonresponse;

    private boolean verificaesito;

    public Gatewaydetail() {
    }

    public Gatewaydetail(
           java.lang.String backofficeurl,
           java.lang.String contextpg,
           java.lang.String defaultpaymenturl,
           java.lang.String enabled,
           java.lang.String errorurl,
           java.lang.String gatewayId,
           long httpport,
           boolean httpportNull,
           java.lang.String mdpgatewaypage,
           java.lang.String paymentmodeId,
           java.lang.String receipturl,
           java.lang.String returnurlmdp,
           java.lang.String sendmailonresponse,
           boolean verificaesito) {
           this.backofficeurl = backofficeurl;
           this.contextpg = contextpg;
           this.defaultpaymenturl = defaultpaymenturl;
           this.enabled = enabled;
           this.errorurl = errorurl;
           this.gatewayId = gatewayId;
           this.httpport = httpport;
           this.httpportNull = httpportNull;
           this.mdpgatewaypage = mdpgatewaypage;
           this.paymentmodeId = paymentmodeId;
           this.receipturl = receipturl;
           this.returnurlmdp = returnurlmdp;
           this.sendmailonresponse = sendmailonresponse;
           this.verificaesito = verificaesito;
    }


    /**
     * Gets the backofficeurl value for this Gatewaydetail.
     * 
     * @return backofficeurl
     */
    public java.lang.String getBackofficeurl() {
        return backofficeurl;
    }


    /**
     * Sets the backofficeurl value for this Gatewaydetail.
     * 
     * @param backofficeurl
     */
    public void setBackofficeurl(java.lang.String backofficeurl) {
        this.backofficeurl = backofficeurl;
    }


    /**
     * Gets the contextpg value for this Gatewaydetail.
     * 
     * @return contextpg
     */
    public java.lang.String getContextpg() {
        return contextpg;
    }


    /**
     * Sets the contextpg value for this Gatewaydetail.
     * 
     * @param contextpg
     */
    public void setContextpg(java.lang.String contextpg) {
        this.contextpg = contextpg;
    }


    /**
     * Gets the defaultpaymenturl value for this Gatewaydetail.
     * 
     * @return defaultpaymenturl
     */
    public java.lang.String getDefaultpaymenturl() {
        return defaultpaymenturl;
    }


    /**
     * Sets the defaultpaymenturl value for this Gatewaydetail.
     * 
     * @param defaultpaymenturl
     */
    public void setDefaultpaymenturl(java.lang.String defaultpaymenturl) {
        this.defaultpaymenturl = defaultpaymenturl;
    }


    /**
     * Gets the enabled value for this Gatewaydetail.
     * 
     * @return enabled
     */
    public java.lang.String getEnabled() {
        return enabled;
    }


    /**
     * Sets the enabled value for this Gatewaydetail.
     * 
     * @param enabled
     */
    public void setEnabled(java.lang.String enabled) {
        this.enabled = enabled;
    }


    /**
     * Gets the errorurl value for this Gatewaydetail.
     * 
     * @return errorurl
     */
    public java.lang.String getErrorurl() {
        return errorurl;
    }


    /**
     * Sets the errorurl value for this Gatewaydetail.
     * 
     * @param errorurl
     */
    public void setErrorurl(java.lang.String errorurl) {
        this.errorurl = errorurl;
    }


    /**
     * Gets the gatewayId value for this Gatewaydetail.
     * 
     * @return gatewayId
     */
    public java.lang.String getGatewayId() {
        return gatewayId;
    }


    /**
     * Sets the gatewayId value for this Gatewaydetail.
     * 
     * @param gatewayId
     */
    public void setGatewayId(java.lang.String gatewayId) {
        this.gatewayId = gatewayId;
    }


    /**
     * Gets the httpport value for this Gatewaydetail.
     * 
     * @return httpport
     */
    public long getHttpport() {
        return httpport;
    }


    /**
     * Sets the httpport value for this Gatewaydetail.
     * 
     * @param httpport
     */
    public void setHttpport(long httpport) {
        this.httpport = httpport;
    }


    /**
     * Gets the httpportNull value for this Gatewaydetail.
     * 
     * @return httpportNull
     */
    public boolean isHttpportNull() {
        return httpportNull;
    }


    /**
     * Sets the httpportNull value for this Gatewaydetail.
     * 
     * @param httpportNull
     */
    public void setHttpportNull(boolean httpportNull) {
        this.httpportNull = httpportNull;
    }


    /**
     * Gets the mdpgatewaypage value for this Gatewaydetail.
     * 
     * @return mdpgatewaypage
     */
    public java.lang.String getMdpgatewaypage() {
        return mdpgatewaypage;
    }


    /**
     * Sets the mdpgatewaypage value for this Gatewaydetail.
     * 
     * @param mdpgatewaypage
     */
    public void setMdpgatewaypage(java.lang.String mdpgatewaypage) {
        this.mdpgatewaypage = mdpgatewaypage;
    }


    /**
     * Gets the paymentmodeId value for this Gatewaydetail.
     * 
     * @return paymentmodeId
     */
    public java.lang.String getPaymentmodeId() {
        return paymentmodeId;
    }


    /**
     * Sets the paymentmodeId value for this Gatewaydetail.
     * 
     * @param paymentmodeId
     */
    public void setPaymentmodeId(java.lang.String paymentmodeId) {
        this.paymentmodeId = paymentmodeId;
    }


    /**
     * Gets the receipturl value for this Gatewaydetail.
     * 
     * @return receipturl
     */
    public java.lang.String getReceipturl() {
        return receipturl;
    }


    /**
     * Sets the receipturl value for this Gatewaydetail.
     * 
     * @param receipturl
     */
    public void setReceipturl(java.lang.String receipturl) {
        this.receipturl = receipturl;
    }


    /**
     * Gets the returnurlmdp value for this Gatewaydetail.
     * 
     * @return returnurlmdp
     */
    public java.lang.String getReturnurlmdp() {
        return returnurlmdp;
    }


    /**
     * Sets the returnurlmdp value for this Gatewaydetail.
     * 
     * @param returnurlmdp
     */
    public void setReturnurlmdp(java.lang.String returnurlmdp) {
        this.returnurlmdp = returnurlmdp;
    }


    /**
     * Gets the sendmailonresponse value for this Gatewaydetail.
     * 
     * @return sendmailonresponse
     */
    public java.lang.String getSendmailonresponse() {
        return sendmailonresponse;
    }


    /**
     * Sets the sendmailonresponse value for this Gatewaydetail.
     * 
     * @param sendmailonresponse
     */
    public void setSendmailonresponse(java.lang.String sendmailonresponse) {
        this.sendmailonresponse = sendmailonresponse;
    }


    /**
     * Gets the verificaesito value for this Gatewaydetail.
     * 
     * @return verificaesito
     */
    public boolean isVerificaesito() {
        return verificaesito;
    }


    /**
     * Sets the verificaesito value for this Gatewaydetail.
     * 
     * @param verificaesito
     */
    public void setVerificaesito(boolean verificaesito) {
        this.verificaesito = verificaesito;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Gatewaydetail)) return false;
        Gatewaydetail other = (Gatewaydetail) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.backofficeurl==null && other.getBackofficeurl()==null) || 
             (this.backofficeurl!=null &&
              this.backofficeurl.equals(other.getBackofficeurl()))) &&
            ((this.contextpg==null && other.getContextpg()==null) || 
             (this.contextpg!=null &&
              this.contextpg.equals(other.getContextpg()))) &&
            ((this.defaultpaymenturl==null && other.getDefaultpaymenturl()==null) || 
             (this.defaultpaymenturl!=null &&
              this.defaultpaymenturl.equals(other.getDefaultpaymenturl()))) &&
            ((this.enabled==null && other.getEnabled()==null) || 
             (this.enabled!=null &&
              this.enabled.equals(other.getEnabled()))) &&
            ((this.errorurl==null && other.getErrorurl()==null) || 
             (this.errorurl!=null &&
              this.errorurl.equals(other.getErrorurl()))) &&
            ((this.gatewayId==null && other.getGatewayId()==null) || 
             (this.gatewayId!=null &&
              this.gatewayId.equals(other.getGatewayId()))) &&
            this.httpport == other.getHttpport() &&
            this.httpportNull == other.isHttpportNull() &&
            ((this.mdpgatewaypage==null && other.getMdpgatewaypage()==null) || 
             (this.mdpgatewaypage!=null &&
              this.mdpgatewaypage.equals(other.getMdpgatewaypage()))) &&
            ((this.paymentmodeId==null && other.getPaymentmodeId()==null) || 
             (this.paymentmodeId!=null &&
              this.paymentmodeId.equals(other.getPaymentmodeId()))) &&
            ((this.receipturl==null && other.getReceipturl()==null) || 
             (this.receipturl!=null &&
              this.receipturl.equals(other.getReceipturl()))) &&
            ((this.returnurlmdp==null && other.getReturnurlmdp()==null) || 
             (this.returnurlmdp!=null &&
              this.returnurlmdp.equals(other.getReturnurlmdp()))) &&
            ((this.sendmailonresponse==null && other.getSendmailonresponse()==null) || 
             (this.sendmailonresponse!=null &&
              this.sendmailonresponse.equals(other.getSendmailonresponse()))) &&
            this.verificaesito == other.isVerificaesito();
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
        if (getBackofficeurl() != null) {
            _hashCode += getBackofficeurl().hashCode();
        }
        if (getContextpg() != null) {
            _hashCode += getContextpg().hashCode();
        }
        if (getDefaultpaymenturl() != null) {
            _hashCode += getDefaultpaymenturl().hashCode();
        }
        if (getEnabled() != null) {
            _hashCode += getEnabled().hashCode();
        }
        if (getErrorurl() != null) {
            _hashCode += getErrorurl().hashCode();
        }
        if (getGatewayId() != null) {
            _hashCode += getGatewayId().hashCode();
        }
        _hashCode += new Long(getHttpport()).hashCode();
        _hashCode += (isHttpportNull() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getMdpgatewaypage() != null) {
            _hashCode += getMdpgatewaypage().hashCode();
        }
        if (getPaymentmodeId() != null) {
            _hashCode += getPaymentmodeId().hashCode();
        }
        if (getReceipturl() != null) {
            _hashCode += getReceipturl().hashCode();
        }
        if (getReturnurlmdp() != null) {
            _hashCode += getReturnurlmdp().hashCode();
        }
        if (getSendmailonresponse() != null) {
            _hashCode += getSendmailonresponse().hashCode();
        }
        _hashCode += (isVerificaesito() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Gatewaydetail.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "gatewaydetail"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("backofficeurl");
        elemField.setXmlName(new javax.xml.namespace.QName("", "backofficeurl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contextpg");
        elemField.setXmlName(new javax.xml.namespace.QName("", "contextpg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("defaultpaymenturl");
        elemField.setXmlName(new javax.xml.namespace.QName("", "defaultpaymenturl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("enabled");
        elemField.setXmlName(new javax.xml.namespace.QName("", "enabled"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorurl");
        elemField.setXmlName(new javax.xml.namespace.QName("", "errorurl"));
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
        elemField.setFieldName("httpport");
        elemField.setXmlName(new javax.xml.namespace.QName("", "httpport"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("httpportNull");
        elemField.setXmlName(new javax.xml.namespace.QName("", "httpportNull"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mdpgatewaypage");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mdpgatewaypage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paymentmodeId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "paymentmodeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("receipturl");
        elemField.setXmlName(new javax.xml.namespace.QName("", "receipturl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("returnurlmdp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "returnurlmdp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sendmailonresponse");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sendmailonresponse"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("verificaesito");
        elemField.setXmlName(new javax.xml.namespace.QName("", "verificaesito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
