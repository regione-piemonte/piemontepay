/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.ws.EPaywso2SportelloService;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Classe Java per TestataListaCarico complex type.
 *
 * <p>
 * Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="TestataListaCarico">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdMessaggio" type="{http://www.csi.it/epay/epaywso/types}String35Type"/>
 *         &lt;element name="CFEnteCreditore" type="{http://www.csi.it/epay/epaywso/types}String35Type"/>
 *         &lt;element name="CodiceVersamento" type="{http://www.csi.it/epay/epaywso/types}CodiceVersamentoType"/>
 *         &lt;element name="MultiBeneficiario" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="NumeroPosizioniDebitorie" type="{http://www.csi.it/epay/epaywso/types}Numero6CifreType"/>
 *         &lt;element name="ImportoTotaleListaDiCarico" type="{http://www.csi.it/epay/epaywso/types}ImportoType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TestataListaCarico", propOrder = {
    "idMessaggio",
    "cfEnteCreditore",
    "codiceVersamento",
    "multiBeneficiario",
    "numeroPosizioniDebitorie",
    "importoTotaleListaDiCarico"
})
public class TestataListaCarico {

    @XmlElement(name = "IdMessaggio", required = true)
    protected String idMessaggio;
    @XmlElement(name = "CFEnteCreditore", required = true)
    protected String cfEnteCreditore;
    @XmlElement(name = "CodiceVersamento", required = true)
    protected String codiceVersamento;

    //CSI_PAG-1888 INIZIO
    @XmlElement ( name = "MultiBeneficiario" )
    @XmlSchemaType ( name = "boolean" )
    protected Boolean multiBeneficiario;

    //CSI_PAG-1888 FINE
    @XmlElement(name = "NumeroPosizioniDebitorie")
    @XmlSchemaType(name = "integer")
    protected int numeroPosizioniDebitorie;
    @XmlElement(name = "ImportoTotaleListaDiCarico", required = true)
    protected BigDecimal importoTotaleListaDiCarico;

    /**
     * Recupera il valore della proprieta' idMessaggio.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getIdMessaggio() {
        return idMessaggio;
    }

    /**
     * Imposta il valore della proprieta' idMessaggio.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setIdMessaggio(String value) {
        this.idMessaggio = value;
    }

    /**
     * Recupera il valore della proprieta' cfEnteCreditore.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCFEnteCreditore() {
        return cfEnteCreditore;
    }

    /**
     * Imposta il valore della proprieta' cfEnteCreditore.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCFEnteCreditore(String value) {
        this.cfEnteCreditore = value;
    }

    /**
     * Recupera il valore della proprieta' codiceVersamento.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCodiceVersamento() {
        return codiceVersamento;
    }

    /**
     * Imposta il valore della proprieta' codiceVersamento.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCodiceVersamento(String value) {
        this.codiceVersamento = value;
    }

    //CSI_PAG-1888 INIZIO
    /**
     * Recupera il valore della proprieta' multiBeneficiario.
     *
     * @return possible object is {@link Boolean }
     *
     */
    public Boolean getMultiBeneficiario () {
        return multiBeneficiario;
    }

    /**
     * Imposta il valore della proprieta' multiBeneficiario.
     *
     * @param value allowed object is {@link Boolean }
     *
     */
    public void setMultiBeneficiario ( Boolean value ) {
        this.multiBeneficiario = value;
    }

    //CSI_PAG-1888 FINE
    /**
     * Recupera il valore della proprieta' numeroPosizioniDebitorie.
     *
     */
    public int getNumeroPosizioniDebitorie() {
        return numeroPosizioniDebitorie;
    }

    /**
     * Imposta il valore della proprieta' numeroPosizioniDebitorie.
     *
     */
    public void setNumeroPosizioniDebitorie(int value) {
        this.numeroPosizioniDebitorie = value;
    }

    /**
     * Recupera il valore della proprieta' importoTotaleListaDiCarico.
     *
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *
     */
    public BigDecimal getImportoTotaleListaDiCarico() {
        return importoTotaleListaDiCarico;
    }

    /**
     * Imposta il valore della proprieta' importoTotaleListaDiCarico.
     *
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *
     */
    public void setImportoTotaleListaDiCarico(BigDecimal value) {
        this.importoTotaleListaDiCarico = value;
    }

}
