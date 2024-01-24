/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per parentInput complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="parentInput"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="caller" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}callerInputDto" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "parentInput", propOrder = {
    "caller"
})
@XmlSeeAlso({
    ElaboraCodaEmailInput.class,
    GetProfilazioneUtenteCorrenteInput.class,
    GetOpzioniInput.class,
    GetUtenteInput.class,
    RicercaUtenteInput.class,
    GetMessaggiInput.class,
    RicercaRiferimentoContabileInput.class,
    AutorizzaEsportazioneDatiInput.class,
    GetRiferimentoContabileInput.class,
    GetEnteInput.class,
    GetCodiceVersamentoInput.class,
    InserisciCodiceVersamentoInput.class,
    EliminaUtenteInput.class,
    GetProfilazioneUtenteInput.class,
    EseguiMigrazioneInput.class,
    AggiornaRiferimentoContabileInput.class,
    ChiudiRiferimentoContabileInput.class,
    RicercaCodiceVersamentoInput.class,
    RicercaVoceEntrataInput.class,
    AggiornaTematicheUtenteInput.class,
    AggiornaEnteInput.class,
    InserisciRiferimentoContabileInput.class,
    InserisciUtenteInput.class,
    AggiornaCodiceVersamentoInput.class,
    AggiornaCduUtenteInput.class,
    EliminaRiferimentoContabileInput.class,
    TestResourcesInput.class,
    EliminaCodiceVersamentoInput.class,
    GetEntiAssociatiInput.class,
    GetOpzioniCodiceVersamentoInput.class,
    AggiornaUtenteInput.class
})
public class ParentInput {

    protected CallerInputDto caller;

    /**
     * Recupera il valore della propriet caller.
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
     * Imposta il valore della propriet caller.
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
