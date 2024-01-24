/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.util.wsdl.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for parentOutput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="parentOutput">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codiceEsito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceMessaggio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceStato" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="descrizioneEsito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "parentOutput", propOrder = {
    "codiceEsito",
    "codiceMessaggio",
    "codiceStato",
    "descrizioneEsito"
})
@XmlSeeAlso({
    AggiornaCodiceVersamentoOutput.class,
    EliminaUtenteOutput.class,
    InserisciCodiceVersamentoOutput.class,
    GetOpzioniAssociazioneUtenteTematicaOutput.class,
    GetCodiceVersamentoOutput.class,
    GetEnteOutput.class,
    ChiudiRiferimentoContabileOutput.class,
    InserisciUtenteOutput.class,
    AggiornaUtenteOutput.class,
    RicercaUtenteOutput.class,
    GetProfilazioneUtenteOutput.class,
    GetOpzioniAssociazioneUtenteCduOutput.class,
    EliminaCodiceVersamentoOutput.class,
    GetRiferimentoContabileOutput.class,
    AutorizzaEsportazioneDatiOutput.class,
    RicercaCodiceVersamentoOutput.class,
    RicercaRiferimentoContabileOutput.class,
    AggiornaRiferimentoContabileOutput.class,
    AggiornaEnteOutput.class,
    GetEntiAssociatiOutput.class,
    InserisciRiferimentoContabileOutput.class,
    RicercaVoceEntrataOutput.class,
    EseguiMigrazioneLogOutputDto.class,
    AggiornaTematicheUtenteOutput.class,
    GetMessaggiOutput.class,
    AggiornaCduUtenteOutput.class,
    EseguiMigrazioneOutput.class,
    GetUtenteOutput.class,
    TestResourcesOutput.class,
    GetProfilazioneUtenteCorrenteOutput.class,
    ElaboraCodaEmailOutput.class,
    EliminaRiferimentoContabileOutput.class,
    RicercaVoceEntrataDisponibileOutput.class,
    GetOpzioniCodiceVersamentoOutput.class,
    GetOpzioniOutput.class
})
public class ParentOutput {

    protected String codiceEsito;
    protected String codiceMessaggio;
    protected Integer codiceStato;
    protected String descrizioneEsito;

    /**
     * Gets the value of the codiceEsito property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceEsito() {
        return codiceEsito;
    }

    /**
     * Sets the value of the codiceEsito property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceEsito(String value) {
        this.codiceEsito = value;
    }

    /**
     * Gets the value of the codiceMessaggio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceMessaggio() {
        return codiceMessaggio;
    }

    /**
     * Sets the value of the codiceMessaggio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceMessaggio(String value) {
        this.codiceMessaggio = value;
    }

    /**
     * Gets the value of the codiceStato property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCodiceStato() {
        return codiceStato;
    }

    /**
     * Sets the value of the codiceStato property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCodiceStato(Integer value) {
        this.codiceStato = value;
    }

    /**
     * Gets the value of the descrizioneEsito property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneEsito() {
        return descrizioneEsito;
    }

    /**
     * Sets the value of the descrizioneEsito property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneEsito(String value) {
        this.descrizioneEsito = value;
    }

}
