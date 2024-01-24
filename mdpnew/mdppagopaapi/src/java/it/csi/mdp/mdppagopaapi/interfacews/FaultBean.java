/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdppagopaapi.interfacews;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.annotation.Generated;


/**
 * <p>Java class for faultBean complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="faultBean">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="faultCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="faultString" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serial" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "faultBean", namespace = "http://ws.pagamenti.telematici.gov/", propOrder = {
    "faultCode",
    "faultString",
    "id",
    "description",
    "serial",
    "originalFaultCode",
    "originalFaultString",
    "originalDescription"
})
public class FaultBean {

    @XmlElement(namespace = "", required = true)
    protected String faultCode;
    @XmlElement(namespace = "", required = true)
    protected String faultString;
    @XmlElement(namespace = "", required = true)
    protected String id;
    @XmlElement(namespace = "")
    protected String description;
    @XmlElement(namespace = "")
    protected Integer serial;
    @XmlElement(namespace = "")
    protected Integer originalFaultCode;
    @XmlElement(namespace = "")
    protected Integer originalFaultString;
    @XmlElement(namespace = "")
    protected Integer originalDescription;


    /**
     * 
     */
    public FaultBean () {
        super ();
    }


    @Generated ( "SparkTools" )
    private FaultBean ( Builder builder ) {
        this.faultCode = builder.faultCode;
        this.faultString = builder.faultString;
        this.id = builder.id;
        this.description = builder.description;
        this.serial = builder.serial;
        this.originalFaultCode = builder.originalFaultCode;
        this.originalFaultString = builder.originalFaultString;
        this.originalDescription = builder.originalDescription;
    }
    

    /**
     * Gets the value of the faultCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFaultCode() {
        return faultCode;
    }

    /**
     * Sets the value of the faultCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFaultCode(String value) {
        this.faultCode = value;
    }

    /**
     * Gets the value of the faultString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFaultString() {
        return faultString;
    }

    /**
     * Sets the value of the faultString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFaultString(String value) {
        this.faultString = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the serial property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSerial() {
        return serial;
    }

    /**
     * Sets the value of the serial property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSerial(Integer value) {
        this.serial = value;
    }

    /**
     * Gets the value of the serial property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
	public Integer getOriginalFaultCode() {
		return originalFaultCode;
	}

    /**
     * Sets the value of the serial property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
	public void setOriginalFaultCode(Integer originalFaultCode) {
		this.originalFaultCode = originalFaultCode;
	}

    /**
     * Gets the value of the serial property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
	public Integer getOriginalFaultString() {
		return originalFaultString;
	}

    /**
     * Sets the value of the serial property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
	public void setOriginalFaultString(Integer originalFaultString) {
		this.originalFaultString = originalFaultString;
	}

    /**
     * Gets the value of the serial property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
	public Integer getOriginalDescription() {
		return originalDescription;
	}

    /**
     * Sets the value of the serial property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
	public void setOriginalDescription(Integer originalDescription) {
		this.originalDescription = originalDescription;
	}

    /**
     * Creates builder to build {@link FaultBean}.
     * @return created builder
     */
    @Generated ( "SparkTools" )
    public static Builder builder () {
        return new Builder ();
    }

    /**
     * Builder to build {@link FaultBean}.
     */
    @Generated ( "SparkTools" )
    public static final class Builder {

        private String faultCode;

        private String faultString;

        private String id;

        private String description;

        private Integer serial;

        private Integer originalFaultCode;

        private Integer originalFaultString;

        private Integer originalDescription;

        private Builder () {
        }

        public Builder withFaultCode ( String faultCode ) {
            this.faultCode = faultCode;
            return this;
        }

        public Builder withFaultString ( String faultString ) {
            this.faultString = faultString;
            return this;
        }

        public Builder withId ( String id ) {
            this.id = id;
            return this;
        }

        public Builder withDescription ( String description ) {
            this.description = description;
            return this;
        }

        public Builder withSerial ( Integer serial ) {
            this.serial = serial;
            return this;
        }

        public Builder withOriginalFaultCode ( Integer originalFaultCode ) {
            this.originalFaultCode = originalFaultCode;
            return this;
        }

        public Builder withOriginalFaultString ( Integer originalFaultString ) {
            this.originalFaultString = originalFaultString;
            return this;
        }

        public Builder withOriginalDescription ( Integer originalDescription ) {
            this.originalDescription = originalDescription;
            return this;
        }

        public FaultBean build () {
            return new FaultBean ( this );
        }
    }
    
    

}
