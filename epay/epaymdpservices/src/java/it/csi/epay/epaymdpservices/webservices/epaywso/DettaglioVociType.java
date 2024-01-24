/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymdpservices.webservices.epaywso;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per DettaglioVociType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="DettaglioVociType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DettaglioVoce">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Tipo" type="{http://www.csi.it/epay/epaywso/epaywso2enti/types}TipoDettaglioVoce"/>
 *                   &lt;element name="Descrizione" type="{http://www.csi.it/epay/epaywso/types}String100Type" minOccurs="0"/>
 *                   &lt;element name="Importo" type="{http://www.csi.it/epay/epaywso/types}ImportoType"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DettaglioVociType", propOrder = {
    "dettaglioVoce"
})
public class DettaglioVociType {

    @XmlElement(name = "DettaglioVoce", required = true)
    protected DettaglioVociType.DettaglioVoce dettaglioVoce;

    /**
     * Recupera il valore della proprietà dettaglioVoce.
     * 
     * @return
     *     possible object is
     *     {@link DettaglioVociType.DettaglioVoce }
     *     
     */
    public DettaglioVociType.DettaglioVoce getDettaglioVoce() {
        return dettaglioVoce;
    }

    /**
     * Imposta il valore della proprietà dettaglioVoce.
     * 
     * @param value
     *     allowed object is
     *     {@link DettaglioVociType.DettaglioVoce }
     *     
     */
    public void setDettaglioVoce(DettaglioVociType.DettaglioVoce value) {
        this.dettaglioVoce = value;
    }


    /**
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Tipo" type="{http://www.csi.it/epay/epaywso/epaywso2enti/types}TipoDettaglioVoce"/>
     *         &lt;element name="Descrizione" type="{http://www.csi.it/epay/epaywso/types}String100Type" minOccurs="0"/>
     *         &lt;element name="Importo" type="{http://www.csi.it/epay/epaywso/types}ImportoType"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "tipo",
        "descrizione",
        "importo"
    })
    public static class DettaglioVoce {

        @XmlElement(name = "Tipo", required = true)
        @XmlSchemaType(name = "string")
        protected TipoDettaglioVoce tipo;
        @XmlElement(name = "Descrizione")
        protected String descrizione;
        @XmlElement(name = "Importo", required = true)
        protected BigDecimal importo;

        /**
         * Recupera il valore della proprietà tipo.
         * 
         * @return
         *     possible object is
         *     {@link TipoDettaglioVoce }
         *     
         */
        public TipoDettaglioVoce getTipo() {
            return tipo;
        }

        /**
         * Imposta il valore della proprietà tipo.
         * 
         * @param value
         *     allowed object is
         *     {@link TipoDettaglioVoce }
         *     
         */
        public void setTipo(TipoDettaglioVoce value) {
            this.tipo = value;
        }

        /**
         * Recupera il valore della proprietà descrizione.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDescrizione() {
            return descrizione;
        }

        /**
         * Imposta il valore della proprietà descrizione.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDescrizione(String value) {
            this.descrizione = value;
        }

        /**
         * Recupera il valore della proprietà importo.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getImporto() {
            return importo;
        }

        /**
         * Imposta il valore della proprietà importo.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setImporto(BigDecimal value) {
            this.importo = value;
        }

    }

}
