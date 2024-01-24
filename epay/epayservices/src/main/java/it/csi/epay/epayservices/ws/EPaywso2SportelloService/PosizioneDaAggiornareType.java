/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.ws.EPaywso2SportelloService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>
 * Java class for PosizioneDaAggiornareType complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="PosizioneDaAggiornareType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TipoAggiornamento" type="{http://www.csi.it/epay/epaywso/types}TipoAggiornamentoType"/>
 *         &lt;element name="IdPosizioneDebitoria" type="{http://www.csi.it/epay/epaywso/types}String50Type"/>
 *         &lt;element name="DataScadenza" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="DataInizioValidita" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="DataFineValidita" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="ImportoTotale" type="{http://www.csi.it/epay/epaywso/types}ImportoType" minOccurs="0"/>
 *         &lt;element name="ImportoPrincipale" type="{http://www.csi.it/epay/epaywso/types}ImportoType" minOccurs="0"/>
 *         &lt;element name="ImportoSecondarioAltroEnte" type="{http://www.csi.it/epay/epaywso/types}ImportoType" minOccurs="0"/>
 *         &lt;element name="ComponentiImporto" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ComponenteImporto" type="{http://www.csi.it/epay/epaywso/enti2epaywso/types}ComponenteImportoType" maxOccurs="5"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="DescrizioneCausaleVersamento" type="{http://www.csi.it/epay/epaywso/types}String100Type" minOccurs="0"/>
 *         &lt;element name="Motivazione" type="{http://www.csi.it/epay/epaywso/types}String400Type" minOccurs="0"/>
 *         &lt;element name="RiferimentiPagamento" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Riferimento" type="{http://www.csi.it/epay/epaywso/enti2epaywso/types}RiferimentoType" maxOccurs="5"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="SoggettoPagatore" type="{http://www.csi.it/epay/epaywso/types}SoggettoType" minOccurs="0"/>
 *         &lt;element name="ComponentiImportoSecondario" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ComponenteImporto" type="{http://www.csi.it/epay/epaywso/enti2epaywso/types}ComponenteImportoType" maxOccurs="5"/>
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
@XmlType(name = "PosizioneDaAggiornareType", propOrder = {
    "tipoAggiornamento",
    "idPosizioneDebitoria",
    "dataScadenza",
    "dataInizioValidita",
    "dataFineValidita",
    "importoTotale",
    "importoPrincipale",
    "importoSecondarioAltroEnte",
    "componentiImporto",
    "descrizioneCausaleVersamento",
    "motivazione",
    "riferimentiPagamento",
    "soggettoPagatore",
    "componentiImportoSecondario",
    "requiresCostUpdate"
})
public class PosizioneDaAggiornareType {

    @XmlElement(name = "TipoAggiornamento", required = true)
    protected TipoAggiornamentoType tipoAggiornamento;
    @XmlElement(name = "IdPosizioneDebitoria", required = true)
    protected String idPosizioneDebitoria;
    @XmlElement(name = "DataScadenza")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataScadenza;
    @XmlElement(name = "DataInizioValidita")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataInizioValidita;
    @XmlElement(name = "DataFineValidita")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataFineValidita;
    @XmlElement(name = "ImportoTotale")
    protected BigDecimal importoTotale;

    //CSI_PAG-1887 INIZIO
    @XmlElement ( name = "ImportoPrincipale" )
    protected BigDecimal importoPrincipale;

    @XmlElement ( name = "ImportoSecondarioAltroEnte" )
    protected BigDecimal importoSecondarioAltroEnte;

    //CSI_PAG-1887 FINE
    @XmlElement(name = "ComponentiImporto")
    protected PosizioneDaAggiornareType.ComponentiImporto componentiImporto;
    @XmlElement(name = "DescrizioneCausaleVersamento")
    protected String descrizioneCausaleVersamento;
    @XmlElement(name = "Motivazione")
    protected String motivazione;
    @XmlElement ( name = "RiferimentiPagamento" )
    protected PosizioneDaAggiornareType.RiferimentiPagamento riferimentiPagamento;
    @XmlElement(name = "SoggettoPagatore")
    protected SoggettoType soggettoPagatore;

    //CSI_PAG-1888 INIZIO
    @XmlElement ( name = "ComponentiImportoSecondario" )
    protected PosizioneDaAggiornareType.ComponentiImporto componentiImportoSecondario;

    //CSI_PAG-1888 FINE
    
    @XmlElement ( name = "RequiresCostUpdate" )
    @XmlSchemaType ( name = "boolean" )
    protected Boolean requiresCostUpdate;
    /**
     * Gets the value of the tipoAggiornamento property.
     *
     * @return
     *     possible object is
     *     {@link TipoAggiornamentoType }
     *
     */
    public TipoAggiornamentoType getTipoAggiornamento() {
        return tipoAggiornamento;
    }

    /**
     * Sets the value of the tipoAggiornamento property.
     *
     * @param value
     *     allowed object is
     *     {@link TipoAggiornamentoType }
     *
     */
    public void setTipoAggiornamento(TipoAggiornamentoType value) {
        this.tipoAggiornamento = value;
    }

    /**
     * Gets the value of the idPosizioneDebitoria property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getIdPosizioneDebitoria() {
        return idPosizioneDebitoria;
    }

    /**
     * Sets the value of the idPosizioneDebitoria property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setIdPosizioneDebitoria(String value) {
        this.idPosizioneDebitoria = value;
    }

    /**
     * Gets the value of the dataScadenza property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getDataScadenza() {
        return dataScadenza;
    }

    /**
     * Sets the value of the dataScadenza property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setDataScadenza(XMLGregorianCalendar value) {
        this.dataScadenza = value;
    }

    /**
     * Gets the value of the dataInizioValidita property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getDataInizioValidita() {
        return dataInizioValidita;
    }

    /**
     * Sets the value of the dataInizioValidita property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setDataInizioValidita(XMLGregorianCalendar value) {
        this.dataInizioValidita = value;
    }

    /**
     * Gets the value of the dataFineValidita property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getDataFineValidita() {
        return dataFineValidita;
    }

    /**
     * Sets the value of the dataFineValidita property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setDataFineValidita(XMLGregorianCalendar value) {
        this.dataFineValidita = value;
    }

    /**
     * Gets the value of the importoTotale property.
     *
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *
     */
    public BigDecimal getImportoTotale() {
        return importoTotale;
    }

    /**
     * Sets the value of the importoTotale property.
     *
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *
     */
    public void setImportoTotale(BigDecimal value) {
        this.importoTotale = value;
    }

    //CSI_PAG-1887 INIZIO
    /**
     * Gets the value of the importoPrincipale property.
     *
     * @return possible object is {@link BigDecimal }
     *
     */
    public BigDecimal getImportoPrincipale () {
        return importoPrincipale;
    }

    /**
     * Sets the value of the importoPrincipale property.
     *
     * @param value allowed object is {@link BigDecimal }
     *
     */
    public void setImportoPrincipale ( BigDecimal value ) {
        this.importoPrincipale = value;
    }

    /**
     * Gets the value of the importoSecondarioAltroEnte property.
     *
     * @return possible object is {@link BigDecimal }
     *
     */
    public BigDecimal getImportoSecondarioAltroEnte () {
        return importoSecondarioAltroEnte;
    }

    /**
     * Sets the value of the importoSecondarioAltroEnte property.
     *
     * @param value allowed object is {@link BigDecimal }
     *
     */
    public void setImportoSecondarioAltroEnte ( BigDecimal value ) {
        this.importoSecondarioAltroEnte = value;
    }

    //CSI_PAG-1887 FINE
    /**
     * Gets the value of the componentiImporto property.
     *
     * @return
     *     possible object is
     *     {@link PosizioneDaAggiornareType.ComponentiImporto }
     *
     */
    public PosizioneDaAggiornareType.ComponentiImporto getComponentiImporto() {
        return componentiImporto;
    }

    /**
     * Sets the value of the componentiImporto property.
     *
     * @param value
     *     allowed object is
     *     {@link PosizioneDaAggiornareType.ComponentiImporto }
     *
     */
    public void setComponentiImporto(PosizioneDaAggiornareType.ComponentiImporto value) {
        this.componentiImporto = value;
    }

    /**
     * Gets the value of the descrizioneCausaleVersamento property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getDescrizioneCausaleVersamento () {
        return descrizioneCausaleVersamento;
    }

    /**
     * Sets the value of the descrizioneCausaleVersamento property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setDescrizioneCausaleVersamento ( String value ) {
        this.descrizioneCausaleVersamento = value;
    }

    /**
     * Gets the value of the motivazione property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getMotivazione () {
        return motivazione;
    }

    /**
     * Sets the value of the motivazione property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setMotivazione ( String value ) {
        this.motivazione = value;
    }

    /**
     * Gets the value of the riferimentiPagamento property.
     *
     * @return possible object is {@link PosizioneDaAggiornareType.RiferimentiPagamento }
     *
     */
    public PosizioneDaAggiornareType.RiferimentiPagamento getRiferimentiPagamento () {
        return riferimentiPagamento;
    }

    /**
     * Sets the value of the riferimentiPagamento property.
     *
     * @param value allowed object is {@link PosizioneDaAggiornareType.RiferimentiPagamento }
     *
     */
    public void setRiferimentiPagamento ( PosizioneDaAggiornareType.RiferimentiPagamento value ) {
        this.riferimentiPagamento = value;
    }

    //CSI_PAG-1888 INIZIO
    /**
     * Gets the value of the componentiImportoSecondario property.
     *
     * @return possible object is {@link PosizioneDaAggiornareType.ComponentiImportoSecondario }
     *
     */
    public PosizioneDaAggiornareType.ComponentiImporto getComponentiImportoSecondario () {
        return componentiImportoSecondario;
    }

    /**
     * Sets the value of the componentiImportoSecondario property.
     *
     * @param value allowed object is {@link PosizioneDaAggiornareType.ComponentiImportoSecondario }
     *
     */
    public void setComponentiImportoSecondario ( PosizioneDaAggiornareType.ComponentiImporto value ) {
        this.componentiImportoSecondario = value;
    }

    //CSI_PAG-1888 FINE


    /**
     * <p>
     * Java class for anonymous complex type.
     *
     * <p>
     * The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="ComponenteImporto" type="{http://www.csi.it/epay/epaywso/enti2epaywso/types}ComponenteImportoType" maxOccurs="5"/>
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
        "componenteImporto"
    })
    public static class ComponentiImporto {

        @XmlElement(name = "ComponenteImporto", required = true)
        protected List<ComponenteImportoType> componenteImporto;

        /**
         * Gets the value of the componenteImporto property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the componenteImporto property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getComponenteImporto().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ComponenteImportoType }
         *
         *
         */
        public List<ComponenteImportoType> getComponenteImporto() {
            if (componenteImporto == null) {
                componenteImporto = new ArrayList<ComponenteImportoType>();
            }
            return this.componenteImporto;
        }

    }


    /**
     * <p>
     * Java class for anonymous complex type.
     *
     * <p>
     * The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Riferimento" type="{http://www.csi.it/epay/epaywso/enti2epaywso/types}RiferimentoType" maxOccurs="5"/>
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
        "riferimento"
    })
    public static class RiferimentiPagamento {

        @XmlElement(name = "Riferimento", required = true)
        protected List<RiferimentoType> riferimento;

        /**
         * Gets the value of the riferimento property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the riferimento property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getRiferimento().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link RiferimentoType }
         *
         *
         */
        public List<RiferimentoType> getRiferimento() {
            if (riferimento == null) {
                riferimento = new ArrayList<RiferimentoType>();
            }
            return this.riferimento;
        }

    }


    /**
     * Gets the value of the soggettoPagatore property.
     *
     * @return
     *     possible object is
     *     {@link SoggettoType }
     *
     */
    public SoggettoType getSoggettoPagatore() {
        return soggettoPagatore;
    }

    /**
     * Sets the value of the soggettoPagatore property.
     *
     * @param value
     *     allowed object is
     *     {@link SoggettoType }
     *
     */
    public void setSoggettoPagatore(SoggettoType value) {
        this.soggettoPagatore = value;
    }

    /**
     * @return the requiresCostUpdate
     */
    public Boolean getRequiresCostUpdate () {
        return requiresCostUpdate;
    }

    /**
     * @param requiresCostUpdate the requiresCostUpdate to set
     */
    public void setRequiresCostUpdate ( Boolean requiresCostUpdate ) {
        this.requiresCostUpdate = requiresCostUpdate;
    }
    
    //CSI_PAG-1888 INIZIO
//    /**
//     * <p>
//     * Java class for anonymous complex type.
//     *
//     * <p>
//     * The following schema fragment specifies the expected content contained within this class.
//     *
//     * <pre>
//     * &lt;complexType>
//     *   &lt;complexContent>
//     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
//     *       &lt;sequence>
//     *         &lt;element name="ComponenteImporto" type="{http://www.csi.it/epay/epaywso/enti2epaywso/types}ComponenteImportoType" maxOccurs="5"/>
//     *       &lt;/sequence>
//     *     &lt;/restriction>
//     *   &lt;/complexContent>
//     * &lt;/complexType>
//     * </pre>
//     *
//     *
//     */
//    @XmlAccessorType ( XmlAccessType.FIELD )
//    @XmlType ( name = "", propOrder = {
//        "componenteImporto"
//    } )
//    public static class ComponentiImportoSecondario {
//
//        @XmlElement ( name = "ComponenteImporto", required = true )
//        protected List<ComponenteImportoType> componenteImporto;
//
//        /**
//         * Gets the value of the componenteImporto property.
//         *
//         * <p>
//         * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present
//         * inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the componenteImporto property.
//         *
//         * <p>
//         * For example, to add a new item, do as follows:
//         *
//         * <pre>
//         * getComponenteImporto ().add ( newItem );
//         * </pre>
//         *
//         *
//         * <p>
//         * Objects of the following type(s) are allowed in the list {@link ComponenteImportoType }
//         *
//         *
//         */
//        public List<ComponenteImportoType> getComponenteImporto () {
//            if ( componenteImporto == null ) {
//                componenteImporto = new ArrayList<ComponenteImportoType> ();
//            }
//            return this.componenteImporto;
//        }
//
//    }
    //CSI_PAG-1888 FINE
}
