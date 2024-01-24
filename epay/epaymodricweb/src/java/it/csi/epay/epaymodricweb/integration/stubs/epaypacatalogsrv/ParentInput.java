/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for parentInput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="parentInput">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="caller" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}callerInputDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "parentInput", propOrder = {
    "caller"
})
@XmlSeeAlso({
    EliminaRiferimentoContabileInput.class,
    ElaboraCodaEmailInput.class,
    EliminaUtenteInput.class,
    GetEnteInput.class,
    RicercaRiferimentoContabileInput.class,
    GetUtenteInput.class,
    GetRiferimentoContabileInput.class,
    AggiornaRiferimentoContabileInput.class,
    InserisciRiferimentoContabileInput.class,
    GetProfilazioneUtenteCorrenteInput.class,
    AggiornaCodiceVersamentoInput.class,
    RicercaCodiceVersamentoInput.class,
    AggiornaTematicheUtenteInput.class,
    GetProfilazioneUtenteInput.class,
    GetCodiceVersamentoInput.class,
    InserisciCodiceVersamentoInput.class,
    RicercaUtenteInput.class,
    AutorizzaEsportazioneDatiInput.class,
    GetOpzioniCodiceVersamentoInput.class,
    AggiornaUtenteInput.class,
    TestResourcesInput.class,
    AggiornaEnteInput.class,
    AggiornaCduUtenteInput.class,
    EseguiMigrazioneInput.class,
    GetEntiAssociatiInput.class,
    RicercaVoceEntrataInput.class,
    EliminaCodiceVersamentoInput.class,
    InserisciUtenteInput.class,
    GetOpzioniInput.class,
    ChiudiRiferimentoContabileInput.class,
    GetMessaggiInput.class
})
public class ParentInput {

    protected CallerInputDto caller;

    /**
     * Gets the value of the caller property.
     * 
     * @return
     *     possible object is
     *     {@link CallerInputDto }
     *     
     */
    public CallerInputDto getCaller() {
        return caller;
    }

    /**
     * Sets the value of the caller property.
     * 
     * @param value
     *     allowed object is
     *     {@link CallerInputDto }
     *     
     */
    public void setCaller(CallerInputDto value) {
        this.caller = value;
    }

}
