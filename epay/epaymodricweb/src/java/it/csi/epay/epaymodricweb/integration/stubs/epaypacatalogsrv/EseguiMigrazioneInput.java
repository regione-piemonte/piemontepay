/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for eseguiMigrazioneInput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="eseguiMigrazioneInput">
 *   &lt;complexContent>
 *     &lt;extension base="{http://interfacews.epaypacatalogsrv.epay.csi.it/}parentInput">
 *       &lt;sequence>
 *         &lt;element name="anteprima" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="eliminaGiaMigrati" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="eliminaLogPrecedenti" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="eseguiMigrazione" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="importaCdu" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="importaCodiciVersamento" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="importaEnti" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="importaRiferimentiContabili" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="importaUtenti" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "eseguiMigrazioneInput", propOrder = {
    "anteprima",
    "eliminaGiaMigrati",
    "eliminaLogPrecedenti",
    "eseguiMigrazione",
    "importaCdu",
    "importaCodiciVersamento",
    "importaEnti",
    "importaRiferimentiContabili",
    "importaUtenti"
})
public class EseguiMigrazioneInput
    extends ParentInput
{

    protected Boolean anteprima;
    protected Boolean eliminaGiaMigrati;
    protected Boolean eliminaLogPrecedenti;
    protected Boolean eseguiMigrazione;
    protected Boolean importaCdu;
    protected Boolean importaCodiciVersamento;
    protected Boolean importaEnti;
    protected Boolean importaRiferimentiContabili;
    protected Boolean importaUtenti;

    /**
     * Gets the value of the anteprima property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAnteprima() {
        return anteprima;
    }

    /**
     * Sets the value of the anteprima property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAnteprima(Boolean value) {
        this.anteprima = value;
    }

    /**
     * Gets the value of the eliminaGiaMigrati property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEliminaGiaMigrati() {
        return eliminaGiaMigrati;
    }

    /**
     * Sets the value of the eliminaGiaMigrati property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEliminaGiaMigrati(Boolean value) {
        this.eliminaGiaMigrati = value;
    }

    /**
     * Gets the value of the eliminaLogPrecedenti property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEliminaLogPrecedenti() {
        return eliminaLogPrecedenti;
    }

    /**
     * Sets the value of the eliminaLogPrecedenti property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEliminaLogPrecedenti(Boolean value) {
        this.eliminaLogPrecedenti = value;
    }

    /**
     * Gets the value of the eseguiMigrazione property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEseguiMigrazione() {
        return eseguiMigrazione;
    }

    /**
     * Sets the value of the eseguiMigrazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEseguiMigrazione(Boolean value) {
        this.eseguiMigrazione = value;
    }

    /**
     * Gets the value of the importaCdu property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isImportaCdu() {
        return importaCdu;
    }

    /**
     * Sets the value of the importaCdu property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setImportaCdu(Boolean value) {
        this.importaCdu = value;
    }

    /**
     * Gets the value of the importaCodiciVersamento property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isImportaCodiciVersamento() {
        return importaCodiciVersamento;
    }

    /**
     * Sets the value of the importaCodiciVersamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setImportaCodiciVersamento(Boolean value) {
        this.importaCodiciVersamento = value;
    }

    /**
     * Gets the value of the importaEnti property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isImportaEnti() {
        return importaEnti;
    }

    /**
     * Sets the value of the importaEnti property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setImportaEnti(Boolean value) {
        this.importaEnti = value;
    }

    /**
     * Gets the value of the importaRiferimentiContabili property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isImportaRiferimentiContabili() {
        return importaRiferimentiContabili;
    }

    /**
     * Sets the value of the importaRiferimentiContabili property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setImportaRiferimentiContabili(Boolean value) {
        this.importaRiferimentiContabili = value;
    }

    /**
     * Gets the value of the importaUtenti property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isImportaUtenti() {
        return importaUtenti;
    }

    /**
     * Sets the value of the importaUtenti property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setImportaUtenti(Boolean value) {
        this.importaUtenti = value;
    }

}
