/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per eseguiMigrazioneInput complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="eseguiMigrazioneInput"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://interfacews.epaypacatalogsrv.epay.csi.it/}parentInput"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="anteprima" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="eliminaGiaMigrati" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="eliminaLogPrecedenti" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="eseguiMigrazione" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="importaCdu" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="importaCodiciVersamento" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="importaEnti" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="importaRiferimentiContabili" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="importaUtenti" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
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
     * Recupera il valore della propriet anteprima.
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
     * Imposta il valore della propriet anteprima.
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
     * Recupera il valore della propriet eliminaGiaMigrati.
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
     * Imposta il valore della propriet eliminaGiaMigrati.
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
     * Recupera il valore della propriet eliminaLogPrecedenti.
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
     * Imposta il valore della propriet eliminaLogPrecedenti.
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
     * Recupera il valore della propriet eseguiMigrazione.
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
     * Imposta il valore della propriet eseguiMigrazione.
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
     * Recupera il valore della propriet importaCdu.
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
     * Imposta il valore della propriet importaCdu.
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
     * Recupera il valore della propriet importaCodiciVersamento.
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
     * Imposta il valore della propriet importaCodiciVersamento.
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
     * Recupera il valore della propriet importaEnti.
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
     * Imposta il valore della propriet importaEnti.
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
     * Recupera il valore della propriet importaRiferimentiContabili.
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
     * Imposta il valore della propriet importaRiferimentiContabili.
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
     * Recupera il valore della propriet importaUtenti.
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
     * Imposta il valore della propriet importaUtenti.
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
