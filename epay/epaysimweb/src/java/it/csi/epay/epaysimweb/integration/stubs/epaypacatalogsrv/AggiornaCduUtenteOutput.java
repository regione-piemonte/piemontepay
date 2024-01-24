/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per aggiornaCduUtenteOutput complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="aggiornaCduUtenteOutput"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://interfacews.epaypacatalogsrv.epay.csi.it/}parentOutput"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="codiceRisultatoSincronizzazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="descrizioneRisultatoSincronizzazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aggiornaCduUtenteOutput", propOrder = {
    "codiceRisultatoSincronizzazione",
    "descrizioneRisultatoSincronizzazione"
})
public class AggiornaCduUtenteOutput
    extends ParentOutput
{

    protected String codiceRisultatoSincronizzazione;
    protected String descrizioneRisultatoSincronizzazione;

    /**
     * Recupera il valore della propriet codiceRisultatoSincronizzazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceRisultatoSincronizzazione() {
        return codiceRisultatoSincronizzazione;
    }

    /**
     * Imposta il valore della propriet codiceRisultatoSincronizzazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceRisultatoSincronizzazione(String value) {
        this.codiceRisultatoSincronizzazione = value;
    }

    /**
     * Recupera il valore della propriet descrizioneRisultatoSincronizzazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneRisultatoSincronizzazione() {
        return descrizioneRisultatoSincronizzazione;
    }

    /**
     * Imposta il valore della propriet descrizioneRisultatoSincronizzazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneRisultatoSincronizzazione(String value) {
        this.descrizioneRisultatoSincronizzazione = value;
    }

}
