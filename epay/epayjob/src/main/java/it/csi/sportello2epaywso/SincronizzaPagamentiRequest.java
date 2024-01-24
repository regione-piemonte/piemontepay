/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.sportello2epaywso;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Pagamento" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="IdPagamento" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *                   &lt;element name="IuvNumeroAvviso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="IdStato" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *                   &lt;element name="DescStato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="DataPagamento" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *                   &lt;element name="ImportoPagato" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *                   &lt;element name="PagamentoSpontaneo" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "pagamento"
})
@XmlRootElement(name = "SincronizzaPagamentiRequest")
public class SincronizzaPagamentiRequest {

    @XmlElement(name = "Pagamento")
    protected List<SincronizzaPagamentiRequest.Pagamento> pagamento;

    /**
     * Gets the value of the pagamento property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pagamento property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPagamento().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SincronizzaPagamentiRequest.Pagamento }
     * 
     * 
     */
    public List<SincronizzaPagamentiRequest.Pagamento> getPagamento() {
        if (pagamento == null) {
            pagamento = new ArrayList<SincronizzaPagamentiRequest.Pagamento>();
        }
        return this.pagamento;
    }


    /**
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="IdPagamento" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
     *         &lt;element name="IuvNumeroAvviso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="IdStato" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
     *         &lt;element name="DescStato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="DataPagamento" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
     *         &lt;element name="ImportoPagato" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
     *         &lt;element name="PagamentoSpontaneo" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "idPagamento",
        "iuvNumeroAvviso",
        "idStato",
        "descStato",
        "dataPagamento",
        "importoPagato",
        "pagamentoSpontaneo"
    })
    public static class Pagamento {

        @XmlElement(name = "IdPagamento")
        protected long idPagamento;
        @XmlElementRef(name = "IuvNumeroAvviso", type = JAXBElement.class, required = false)
        protected JAXBElement<String> iuvNumeroAvviso;
        @XmlElementRef(name = "IdStato", type = JAXBElement.class, required = false)
        protected JAXBElement<Integer> idStato;
        @XmlElementRef(name = "DescStato", type = JAXBElement.class, required = false)
        protected JAXBElement<String> descStato;
        @XmlElementRef(name = "DataPagamento", type = JAXBElement.class, required = false)
        protected JAXBElement<XMLGregorianCalendar> dataPagamento;
        @XmlElementRef(name = "ImportoPagato", type = JAXBElement.class, required = false)
        protected JAXBElement<BigDecimal> importoPagato;
        @XmlElementRef(name = "PagamentoSpontaneo", type = JAXBElement.class, required = false)
        protected JAXBElement<Boolean> pagamentoSpontaneo;

        /**
         * Recupera il valore della proprieta idPagamento.
         * 
         */
        public long getIdPagamento() {
            return idPagamento;
        }

        /**
         * Imposta il valore della proprieta idPagamento.
         * 
         */
        public void setIdPagamento(long value) {
            this.idPagamento = value;
        }

        /**
         * Recupera il valore della proprieta iuvNumeroAvviso.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public JAXBElement<String> getIuvNumeroAvviso() {
            return iuvNumeroAvviso;
        }

        /**
         * Imposta il valore della proprieta iuvNumeroAvviso.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public void setIuvNumeroAvviso(JAXBElement<String> value) {
            this.iuvNumeroAvviso = value;
        }

        /**
         * Recupera il valore della proprieta idStato.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
         *     
         */
        public JAXBElement<Integer> getIdStato() {
            return idStato;
        }

        /**
         * Imposta il valore della proprieta idStato.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
         *     
         */
        public void setIdStato(JAXBElement<Integer> value) {
            this.idStato = value;
        }

        /**
         * Recupera il valore della proprieta descStato.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public JAXBElement<String> getDescStato() {
            return descStato;
        }

        /**
         * Imposta il valore della proprieta descStato.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public void setDescStato(JAXBElement<String> value) {
            this.descStato = value;
        }

        /**
         * Recupera il valore della proprieta dataPagamento.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
         *     
         */
        public JAXBElement<XMLGregorianCalendar> getDataPagamento() {
            return dataPagamento;
        }

        /**
         * Imposta il valore della proprieta dataPagamento.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
         *     
         */
        public void setDataPagamento(JAXBElement<XMLGregorianCalendar> value) {
            this.dataPagamento = value;
        }

        /**
         * Recupera il valore della proprieta importoPagato.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *     
         */
        public JAXBElement<BigDecimal> getImportoPagato() {
            return importoPagato;
        }

        /**
         * Imposta il valore della proprieta importoPagato.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         *     
         */
        public void setImportoPagato(JAXBElement<BigDecimal> value) {
            this.importoPagato = value;
        }

        /**
         * Recupera il valore della proprieta pagamentoSpontaneo.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
         *     
         */
        public JAXBElement<Boolean> getPagamentoSpontaneo() {
            return pagamentoSpontaneo;
        }

        /**
         * Imposta il valore della proprieta pagamentoSpontaneo.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
         *     
         */
        public void setPagamentoSpontaneo(JAXBElement<Boolean> value) {
            this.pagamentoSpontaneo = value;
        }

    }

}
