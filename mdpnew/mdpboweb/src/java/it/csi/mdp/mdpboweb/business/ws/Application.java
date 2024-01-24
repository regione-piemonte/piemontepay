/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class Application  implements java.io.Serializable {
    private java.lang.String applicationname;

    private java.lang.String cliente;

    private java.lang.String esercemail;

    private java.lang.String id;

    private java.lang.String note;

    private java.lang.String numeroverde;

    private java.lang.String progetto;

    private long redirectNewmdp;

    private java.lang.String referentecsi;

    private java.util.Calendar validoAl;

    private java.util.Calendar validoDal;

    public Application() {
    }

    public Application(
           java.lang.String applicationname,
           java.lang.String cliente,
           java.lang.String esercemail,
           java.lang.String id,
           java.lang.String note,
           java.lang.String numeroverde,
           java.lang.String progetto,
           long redirectNewmdp,
           java.lang.String referentecsi,
           java.util.Calendar validoAl,
           java.util.Calendar validoDal) {
           this.applicationname = applicationname;
           this.cliente = cliente;
           this.esercemail = esercemail;
           this.id = id;
           this.note = note;
           this.numeroverde = numeroverde;
           this.progetto = progetto;
           this.redirectNewmdp = redirectNewmdp;
           this.referentecsi = referentecsi;
           this.validoAl = validoAl;
           this.validoDal = validoDal;
    }


    /**
     * Gets the applicationname value for this Application.
     * 
     * @return applicationname
     */
    public java.lang.String getApplicationname() {
        return applicationname;
    }


    /**
     * Sets the applicationname value for this Application.
     * 
     * @param applicationname
     */
    public void setApplicationname(java.lang.String applicationname) {
        this.applicationname = applicationname;
    }


    /**
     * Gets the cliente value for this Application.
     * 
     * @return cliente
     */
    public java.lang.String getCliente() {
        return cliente;
    }


    /**
     * Sets the cliente value for this Application.
     * 
     * @param cliente
     */
    public void setCliente(java.lang.String cliente) {
        this.cliente = cliente;
    }


    /**
     * Gets the esercemail value for this Application.
     * 
     * @return esercemail
     */
    public java.lang.String getEsercemail() {
        return esercemail;
    }


    /**
     * Sets the esercemail value for this Application.
     * 
     * @param esercemail
     */
    public void setEsercemail(java.lang.String esercemail) {
        this.esercemail = esercemail;
    }


    /**
     * Gets the id value for this Application.
     * 
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this Application.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the note value for this Application.
     * 
     * @return note
     */
    public java.lang.String getNote() {
        return note;
    }


    /**
     * Sets the note value for this Application.
     * 
     * @param note
     */
    public void setNote(java.lang.String note) {
        this.note = note;
    }


    /**
     * Gets the numeroverde value for this Application.
     * 
     * @return numeroverde
     */
    public java.lang.String getNumeroverde() {
        return numeroverde;
    }


    /**
     * Sets the numeroverde value for this Application.
     * 
     * @param numeroverde
     */
    public void setNumeroverde(java.lang.String numeroverde) {
        this.numeroverde = numeroverde;
    }


    /**
     * Gets the progetto value for this Application.
     * 
     * @return progetto
     */
    public java.lang.String getProgetto() {
        return progetto;
    }


    /**
     * Sets the progetto value for this Application.
     * 
     * @param progetto
     */
    public void setProgetto(java.lang.String progetto) {
        this.progetto = progetto;
    }


    /**
     * Gets the redirectNewmdp value for this Application.
     * 
     * @return redirectNewmdp
     */
    public long getRedirectNewmdp() {
        return redirectNewmdp;
    }


    /**
     * Sets the redirectNewmdp value for this Application.
     * 
     * @param redirectNewmdp
     */
    public void setRedirectNewmdp(long redirectNewmdp) {
        this.redirectNewmdp = redirectNewmdp;
    }


    /**
     * Gets the referentecsi value for this Application.
     * 
     * @return referentecsi
     */
    public java.lang.String getReferentecsi() {
        return referentecsi;
    }


    /**
     * Sets the referentecsi value for this Application.
     * 
     * @param referentecsi
     */
    public void setReferentecsi(java.lang.String referentecsi) {
        this.referentecsi = referentecsi;
    }


    /**
     * Gets the validoAl value for this Application.
     * 
     * @return validoAl
     */
    public java.util.Calendar getValidoAl() {
        return validoAl;
    }


    /**
     * Sets the validoAl value for this Application.
     * 
     * @param validoAl
     */
    public void setValidoAl(java.util.Calendar validoAl) {
        this.validoAl = validoAl;
    }


    /**
     * Gets the validoDal value for this Application.
     * 
     * @return validoDal
     */
    public java.util.Calendar getValidoDal() {
        return validoDal;
    }


    /**
     * Sets the validoDal value for this Application.
     * 
     * @param validoDal
     */
    public void setValidoDal(java.util.Calendar validoDal) {
        this.validoDal = validoDal;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Application)) return false;
        Application other = (Application) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.applicationname==null && other.getApplicationname()==null) || 
             (this.applicationname!=null &&
              this.applicationname.equals(other.getApplicationname()))) &&
            ((this.cliente==null && other.getCliente()==null) || 
             (this.cliente!=null &&
              this.cliente.equals(other.getCliente()))) &&
            ((this.esercemail==null && other.getEsercemail()==null) || 
             (this.esercemail!=null &&
              this.esercemail.equals(other.getEsercemail()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.note==null && other.getNote()==null) || 
             (this.note!=null &&
              this.note.equals(other.getNote()))) &&
            ((this.numeroverde==null && other.getNumeroverde()==null) || 
             (this.numeroverde!=null &&
              this.numeroverde.equals(other.getNumeroverde()))) &&
            ((this.progetto==null && other.getProgetto()==null) || 
             (this.progetto!=null &&
              this.progetto.equals(other.getProgetto()))) &&
            this.redirectNewmdp == other.getRedirectNewmdp() &&
            ((this.referentecsi==null && other.getReferentecsi()==null) || 
             (this.referentecsi!=null &&
              this.referentecsi.equals(other.getReferentecsi()))) &&
            ((this.validoAl==null && other.getValidoAl()==null) || 
             (this.validoAl!=null &&
              this.validoAl.equals(other.getValidoAl()))) &&
            ((this.validoDal==null && other.getValidoDal()==null) || 
             (this.validoDal!=null &&
              this.validoDal.equals(other.getValidoDal())));
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
        if (getApplicationname() != null) {
            _hashCode += getApplicationname().hashCode();
        }
        if (getCliente() != null) {
            _hashCode += getCliente().hashCode();
        }
        if (getEsercemail() != null) {
            _hashCode += getEsercemail().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getNote() != null) {
            _hashCode += getNote().hashCode();
        }
        if (getNumeroverde() != null) {
            _hashCode += getNumeroverde().hashCode();
        }
        if (getProgetto() != null) {
            _hashCode += getProgetto().hashCode();
        }
        _hashCode += new Long(getRedirectNewmdp()).hashCode();
        if (getReferentecsi() != null) {
            _hashCode += getReferentecsi().hashCode();
        }
        if (getValidoAl() != null) {
            _hashCode += getValidoAl().hashCode();
        }
        if (getValidoDal() != null) {
            _hashCode += getValidoDal().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Application.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "application"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationname");
        elemField.setXmlName(new javax.xml.namespace.QName("", "applicationname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cliente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cliente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("esercemail");
        elemField.setXmlName(new javax.xml.namespace.QName("", "esercemail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("note");
        elemField.setXmlName(new javax.xml.namespace.QName("", "note"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroverde");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroverde"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("progetto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "progetto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("redirectNewmdp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "redirectNewmdp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("referentecsi");
        elemField.setXmlName(new javax.xml.namespace.QName("", "referentecsi"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("validoAl");
        elemField.setXmlName(new javax.xml.namespace.QName("", "validoAl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("validoDal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "validoDal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
