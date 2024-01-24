/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

//
// Questo file  stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a>
// Qualsiasi modifica a questo file andr persa durante la ricompilazione dello schema di origine.
// Generato il: 2018.08.29 alle 11:18:11 AM CEST
//

package it.csi.epay.epaysimweb.integration.generated;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>
 * Classe Java per anonymous complex type.
 *
 * <p>
 * Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="testata_messaggio"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="codice_ABI_BT" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                   &lt;element name="data_ora_creazione_flusso" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="codice_ente" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="descrizione_ente" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="codice_istat_ente" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                   &lt;element name="codice_fiscale_ente" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                   &lt;element name="codice_tramite_ente" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="codice_tramite_BT" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="codice_ente_BT" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="identificativo_flusso_BT" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="esercizio" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="data_inizio_periodo_riferimento" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="data_fine_periodo_riferimento" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="informazioni_conto_evidenza" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="conto_evidenza" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                   &lt;element name="descrizione_conto_evidenza" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="movimento_conto_evidenza" maxOccurs="unbounded"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="tipo_movimento" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="tipo_documento" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="tipo_operazione" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="numero_documento" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                             &lt;element name="progressivo_documento" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                             &lt;element name="importo" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                             &lt;element name="numero_bolletta_quietanza" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                             &lt;element name="data_movimento" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *                             &lt;element name="data_valuta_ente" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *                             &lt;element name="tipo_esecuzione" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="codice_riferimento_interno" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="tipo_contabilita" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="destinazione" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="assoggettamento_bollo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="assoggettamento_spese" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="cliente"&gt;
 *                               &lt;complexType&gt;
 *                                 &lt;complexContent&gt;
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                     &lt;sequence&gt;
 *                                       &lt;element name="anagrafica_cliente" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                                     &lt;/sequence&gt;
 *                                   &lt;/restriction&gt;
 *                                 &lt;/complexContent&gt;
 *                               &lt;/complexType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="causale" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="numero_sospeso" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="saldo_precedente_conto_evidenza" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *                   &lt;element name="totale_entrate_conto_evidenza" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *                   &lt;element name="totale_uscite_conto_evidenza" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *                   &lt;element name="saldo_finale_conto_evidenza" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="saldo_complessivo_precedente" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="totale_complessivo_entrate" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="totale_complessivo_uscite" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="saldo_complessivo_finale" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="totali_esercizio"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="fondo_di_cassa" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *                   &lt;element name="totale_reversali_riscosse" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *                   &lt;element name="totale_sospesi_entrata" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *                   &lt;element name="totale_entrate" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *                   &lt;element name="deficit_di_cassa" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                   &lt;element name="totale_mandati_pagati" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                   &lt;element name="totale_sospesi_uscita" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *                   &lt;element name="totale_uscite" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *                   &lt;element name="saldo_esercizio" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="totali_disponibilita_liquide"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="saldo_conti_correnti" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                   &lt;element name="saldo_conti_BI" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *                   &lt;element name="totale_conti" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *                   &lt;element name="vincoli_conti_correnti" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                   &lt;element name="vincoli_conti_BI" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                   &lt;element name="totale_vincoli" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                   &lt;element name="anticipazione_accordata" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                   &lt;element name="anticipazione_utilizzata" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                   &lt;element name="disponibilita" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
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
@XmlAccessorType ( XmlAccessType.FIELD )
@XmlType ( name = "", propOrder = {
    "testataMessaggio",
    "identificativoFlussoBT",
    "esercizio",
    "dataInizioPeriodoRiferimento",
    "dataFinePeriodoRiferimento",
    "informazioniContoEvidenza",
    "saldoComplessivoPrecedente",
    "totaleComplessivoEntrate",
    "totaleComplessivoUscite",
    "saldoComplessivoFinale",
    "totaliEsercizio",
    "totaliDisponibilitaLiquide"
} )
@XmlRootElement ( name = "flusso_giornale_di_cassa" )
public class FlussoGiornaleDiCassa {

    @XmlElement ( name = "testata_messaggio", required = true )
    protected FlussoGiornaleDiCassa.TestataMessaggio testataMessaggio;

    @XmlElement ( name = "identificativo_flusso_BT", required = true )
    protected String identificativoFlussoBT;

    protected int esercizio;

    @XmlElement ( name = "data_inizio_periodo_riferimento", required = true )
    @XmlSchemaType ( name = "date" )
    protected XMLGregorianCalendar dataInizioPeriodoRiferimento;

    @XmlElement ( name = "data_fine_periodo_riferimento", required = true )
    @XmlSchemaType ( name = "date" )
    protected XMLGregorianCalendar dataFinePeriodoRiferimento;

    @XmlElement ( name = "informazioni_conto_evidenza", required = true )
    protected List<FlussoGiornaleDiCassa.InformazioniContoEvidenza> informazioniContoEvidenza;

    @XmlElement ( name = "saldo_complessivo_precedente" )
    protected double saldoComplessivoPrecedente;

    @XmlElement ( name = "totale_complessivo_entrate" )
    protected double totaleComplessivoEntrate;

    @XmlElement ( name = "totale_complessivo_uscite" )
    protected double totaleComplessivoUscite;

    @XmlElement ( name = "saldo_complessivo_finale" )
    protected double saldoComplessivoFinale;

    @XmlElement ( name = "totali_esercizio", required = true )
    protected FlussoGiornaleDiCassa.TotaliEsercizio totaliEsercizio;

    @XmlElement ( name = "totali_disponibilita_liquide", required = true )
    protected FlussoGiornaleDiCassa.TotaliDisponibilitaLiquide totaliDisponibilitaLiquide;

    /**
     * Recupera il valore della propriet testataMessaggio.
     *
     * @return possible object is {@link FlussoGiornaleDiCassa.TestataMessaggio }
     *
     */
    public FlussoGiornaleDiCassa.TestataMessaggio getTestataMessaggio () {
        return testataMessaggio;
    }

    /**
     * Imposta il valore della propriet testataMessaggio.
     *
     * @param value allowed object is {@link FlussoGiornaleDiCassa.TestataMessaggio }
     *
     */
    public void setTestataMessaggio ( FlussoGiornaleDiCassa.TestataMessaggio value ) {
        this.testataMessaggio = value;
    }

    /**
     * Recupera il valore della propriet identificativoFlussoBT.
     *
     * @return possible object is {@link String }
     *
     */
    public String getIdentificativoFlussoBT () {
        return identificativoFlussoBT;
    }

    /**
     * Imposta il valore della propriet identificativoFlussoBT.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setIdentificativoFlussoBT ( String value ) {
        this.identificativoFlussoBT = value;
    }

    /**
     * Recupera il valore della propriet esercizio.
     *
     */
    public int getEsercizio () {
        return esercizio;
    }

    /**
     * Imposta il valore della propriet esercizio.
     *
     */
    public void setEsercizio ( int value ) {
        this.esercizio = value;
    }

    /**
     * Recupera il valore della propriet dataInizioPeriodoRiferimento.
     *
     * @return possible object is {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getDataInizioPeriodoRiferimento () {
        return dataInizioPeriodoRiferimento;
    }

    /**
     * Imposta il valore della propriet dataInizioPeriodoRiferimento.
     *
     * @param value allowed object is {@link XMLGregorianCalendar }
     *
     */
    public void setDataInizioPeriodoRiferimento ( XMLGregorianCalendar value ) {
        this.dataInizioPeriodoRiferimento = value;
    }

    /**
     * Recupera il valore della propriet dataFinePeriodoRiferimento.
     *
     * @return possible object is {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getDataFinePeriodoRiferimento () {
        return dataFinePeriodoRiferimento;
    }

    /**
     * Imposta il valore della propriet dataFinePeriodoRiferimento.
     *
     * @param value allowed object is {@link XMLGregorianCalendar }
     *
     */
    public void setDataFinePeriodoRiferimento ( XMLGregorianCalendar value ) {
        this.dataFinePeriodoRiferimento = value;
    }

    /**
     * Gets the value of the informazioniContoEvidenza property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present
     * inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the informazioniContoEvidenza property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     *
     * <pre>
     * getInformazioniContoEvidenza ().add ( newItem );
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link FlussoGiornaleDiCassa.InformazioniContoEvidenza }
     *
     *
     */
    public List<FlussoGiornaleDiCassa.InformazioniContoEvidenza> getInformazioniContoEvidenza () {
        if ( informazioniContoEvidenza == null ) {
            informazioniContoEvidenza = new ArrayList<> ();
        }
        return this.informazioniContoEvidenza;
    }

    /**
     * Recupera il valore della propriet saldoComplessivoPrecedente.
     *
     */
    public double getSaldoComplessivoPrecedente () {
        return saldoComplessivoPrecedente;
    }

    /**
     * Imposta il valore della propriet saldoComplessivoPrecedente.
     *
     */
    public void setSaldoComplessivoPrecedente ( double value ) {
        this.saldoComplessivoPrecedente = value;
    }

    /**
     * Recupera il valore della propriet totaleComplessivoEntrate.
     *
     */
    public double getTotaleComplessivoEntrate () {
        return totaleComplessivoEntrate;
    }

    /**
     * Imposta il valore della propriet totaleComplessivoEntrate.
     *
     */
    public void setTotaleComplessivoEntrate ( double value ) {
        this.totaleComplessivoEntrate = value;
    }

    /**
     * Recupera il valore della propriet totaleComplessivoUscite.
     *
     */
    public double getTotaleComplessivoUscite () {
        return totaleComplessivoUscite;
    }

    /**
     * Imposta il valore della propriet totaleComplessivoUscite.
     *
     */
    public void setTotaleComplessivoUscite ( double value ) {
        this.totaleComplessivoUscite = value;
    }

    /**
     * Recupera il valore della propriet saldoComplessivoFinale.
     *
     */
    public double getSaldoComplessivoFinale () {
        return saldoComplessivoFinale;
    }

    /**
     * Imposta il valore della propriet saldoComplessivoFinale.
     *
     */
    public void setSaldoComplessivoFinale ( double value ) {
        this.saldoComplessivoFinale = value;
    }

    /**
     * Recupera il valore della propriet totaliEsercizio.
     *
     * @return possible object is {@link FlussoGiornaleDiCassa.TotaliEsercizio }
     *
     */
    public FlussoGiornaleDiCassa.TotaliEsercizio getTotaliEsercizio () {
        return totaliEsercizio;
    }

    /**
     * Imposta il valore della propriet totaliEsercizio.
     *
     * @param value allowed object is {@link FlussoGiornaleDiCassa.TotaliEsercizio }
     *
     */
    public void setTotaliEsercizio ( FlussoGiornaleDiCassa.TotaliEsercizio value ) {
        this.totaliEsercizio = value;
    }

    /**
     * Recupera il valore della propriet totaliDisponibilitaLiquide.
     *
     * @return possible object is {@link FlussoGiornaleDiCassa.TotaliDisponibilitaLiquide }
     *
     */
    public FlussoGiornaleDiCassa.TotaliDisponibilitaLiquide getTotaliDisponibilitaLiquide () {
        return totaliDisponibilitaLiquide;
    }

    /**
     * Imposta il valore della propriet totaliDisponibilitaLiquide.
     *
     * @param value allowed object is {@link FlussoGiornaleDiCassa.TotaliDisponibilitaLiquide }
     *
     */
    public void setTotaliDisponibilitaLiquide ( FlussoGiornaleDiCassa.TotaliDisponibilitaLiquide value ) {
        this.totaliDisponibilitaLiquide = value;
    }

    /**
     * <p>
     * Classe Java per anonymous complex type.
     *
     * <p>
     * Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     *
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="conto_evidenza" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *         &lt;element name="descrizione_conto_evidenza" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="movimento_conto_evidenza" maxOccurs="unbounded"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="tipo_movimento" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="tipo_documento" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="tipo_operazione" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="numero_documento" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *                   &lt;element name="progressivo_documento" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *                   &lt;element name="importo" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *                   &lt;element name="numero_bolletta_quietanza" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *                   &lt;element name="data_movimento" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
     *                   &lt;element name="data_valuta_ente" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
     *                   &lt;element name="tipo_esecuzione" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="codice_riferimento_interno" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="tipo_contabilita" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="destinazione" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="assoggettamento_bollo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="assoggettamento_spese" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="cliente"&gt;
     *                     &lt;complexType&gt;
     *                       &lt;complexContent&gt;
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                           &lt;sequence&gt;
     *                             &lt;element name="anagrafica_cliente" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                           &lt;/sequence&gt;
     *                         &lt;/restriction&gt;
     *                       &lt;/complexContent&gt;
     *                     &lt;/complexType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="causale" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="numero_sospeso" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="saldo_precedente_conto_evidenza" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
     *         &lt;element name="totale_entrate_conto_evidenza" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
     *         &lt;element name="totale_uscite_conto_evidenza" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
     *         &lt;element name="saldo_finale_conto_evidenza" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     *
     *
     */
    @XmlAccessorType ( XmlAccessType.FIELD )
    @XmlType ( name = "", propOrder = {
        "contoEvidenza",
        "descrizioneContoEvidenza",
        "movimentoContoEvidenza",
        "saldoPrecedenteContoEvidenza",
        "totaleEntrateContoEvidenza",
        "totaleUsciteContoEvidenza",
        "saldoFinaleContoEvidenza"
    } )
    public static class InformazioniContoEvidenza {

        @XmlElement ( name = "conto_evidenza" )
        protected int contoEvidenza;

        @XmlElement ( name = "descrizione_conto_evidenza", required = true )
        protected String descrizioneContoEvidenza;

        @XmlElement ( name = "movimento_conto_evidenza", required = true )
        protected List<FlussoGiornaleDiCassa.InformazioniContoEvidenza.MovimentoContoEvidenza> movimentoContoEvidenza;

        @XmlElement ( name = "saldo_precedente_conto_evidenza" )
        protected double saldoPrecedenteContoEvidenza;

        @XmlElement ( name = "totale_entrate_conto_evidenza" )
        protected double totaleEntrateContoEvidenza;

        @XmlElement ( name = "totale_uscite_conto_evidenza" )
        protected double totaleUsciteContoEvidenza;

        @XmlElement ( name = "saldo_finale_conto_evidenza" )
        protected int saldoFinaleContoEvidenza;

        /**
         * Recupera il valore della propriet contoEvidenza.
         *
         */
        public int getContoEvidenza () {
            return contoEvidenza;
        }

        /**
         * Imposta il valore della propriet contoEvidenza.
         *
         */
        public void setContoEvidenza ( int value ) {
            this.contoEvidenza = value;
        }

        /**
         * Recupera il valore della propriet descrizioneContoEvidenza.
         *
         * @return possible object is {@link String }
         *
         */
        public String getDescrizioneContoEvidenza () {
            return descrizioneContoEvidenza;
        }

        /**
         * Imposta il valore della propriet descrizioneContoEvidenza.
         *
         * @param value allowed object is {@link String }
         *
         */
        public void setDescrizioneContoEvidenza ( String value ) {
            this.descrizioneContoEvidenza = value;
        }

        /**
         * Gets the value of the movimentoContoEvidenza property.
         *
         * <p>
         * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present
         * inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the movimentoContoEvidenza property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         *
         * <pre>
         * getMovimentoContoEvidenza ().add ( newItem );
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list {@link FlussoGiornaleDiCassa.InformazioniContoEvidenza.MovimentoContoEvidenza }
         *
         *
         */
        public List<FlussoGiornaleDiCassa.InformazioniContoEvidenza.MovimentoContoEvidenza> getMovimentoContoEvidenza () {
            if ( movimentoContoEvidenza == null ) {
                movimentoContoEvidenza = new ArrayList<> ();
            }
            return this.movimentoContoEvidenza;
        }

        /**
         * Recupera il valore della propriet saldoPrecedenteContoEvidenza.
         *
         */
        public double getSaldoPrecedenteContoEvidenza () {
            return saldoPrecedenteContoEvidenza;
        }

        /**
         * Imposta il valore della propriet saldoPrecedenteContoEvidenza.
         *
         */
        public void setSaldoPrecedenteContoEvidenza ( double value ) {
            this.saldoPrecedenteContoEvidenza = value;
        }

        /**
         * Recupera il valore della propriet totaleEntrateContoEvidenza.
         *
         */
        public double getTotaleEntrateContoEvidenza () {
            return totaleEntrateContoEvidenza;
        }

        /**
         * Imposta il valore della propriet totaleEntrateContoEvidenza.
         *
         */
        public void setTotaleEntrateContoEvidenza ( double value ) {
            this.totaleEntrateContoEvidenza = value;
        }

        /**
         * Recupera il valore della propriet totaleUsciteContoEvidenza.
         *
         */
        public double getTotaleUsciteContoEvidenza () {
            return totaleUsciteContoEvidenza;
        }

        /**
         * Imposta il valore della propriet totaleUsciteContoEvidenza.
         *
         */
        public void setTotaleUsciteContoEvidenza ( double value ) {
            this.totaleUsciteContoEvidenza = value;
        }

        /**
         * Recupera il valore della propriet saldoFinaleContoEvidenza.
         *
         */
        public int getSaldoFinaleContoEvidenza () {
            return saldoFinaleContoEvidenza;
        }

        /**
         * Imposta il valore della propriet saldoFinaleContoEvidenza.
         *
         */
        public void setSaldoFinaleContoEvidenza ( int value ) {
            this.saldoFinaleContoEvidenza = value;
        }

        /**
         * <p>
         * Classe Java per anonymous complex type.
         *
         * <p>
         * Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
         *
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;sequence&gt;
         *         &lt;element name="tipo_movimento" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="tipo_documento" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="tipo_operazione" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="numero_documento" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
         *         &lt;element name="progressivo_documento" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
         *         &lt;element name="importo" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
         *         &lt;element name="numero_bolletta_quietanza" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
         *         &lt;element name="data_movimento" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
         *         &lt;element name="data_valuta_ente" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
         *         &lt;element name="tipo_esecuzione" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="codice_riferimento_interno" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="tipo_contabilita" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="destinazione" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="assoggettamento_bollo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="assoggettamento_spese" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="cliente"&gt;
         *           &lt;complexType&gt;
         *             &lt;complexContent&gt;
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                 &lt;sequence&gt;
         *                   &lt;element name="anagrafica_cliente" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *                 &lt;/sequence&gt;
         *               &lt;/restriction&gt;
         *             &lt;/complexContent&gt;
         *           &lt;/complexType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="causale" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="numero_sospeso" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         *
         *
         */
        @XmlAccessorType ( XmlAccessType.FIELD )
        @XmlType ( name = "", propOrder = {
            "tipoMovimento",
            "tipoDocumento",
            "tipoOperazione",
            "numeroDocumento",
            "progressivoDocumento",
            "importo",
            "numeroBollettaQuietanza",
            "dataMovimento",
            "dataValutaEnte",
            "tipoEsecuzione",
            "codiceRiferimentoInterno",
            "tipoContabilita",
            "destinazione",
            "assoggettamentoBollo",
            "assoggettamentoSpese",
            "cliente",
            "causale",
            "numeroSospeso"
        } )
        public static class MovimentoContoEvidenza {

            @XmlElement ( name = "tipo_movimento", required = true )
            protected String tipoMovimento;

            @XmlElement ( name = "tipo_documento", required = true )
            protected String tipoDocumento;

            @XmlElement ( name = "tipo_operazione", required = true )
            protected String tipoOperazione;

            @XmlElement ( name = "numero_documento" )
            protected int numeroDocumento;

            @XmlElement ( name = "progressivo_documento" )
            protected int progressivoDocumento;

            protected BigDecimal importo;

            @XmlElement ( name = "numero_bolletta_quietanza" )
            protected int numeroBollettaQuietanza;

            @XmlElement ( name = "data_movimento", required = true )
            @XmlSchemaType ( name = "date" )
            protected XMLGregorianCalendar dataMovimento;

            @XmlElement ( name = "data_valuta_ente", required = true )
            @XmlSchemaType ( name = "date" )
            protected XMLGregorianCalendar dataValutaEnte;

            @XmlElement ( name = "tipo_esecuzione", required = true )
            protected String tipoEsecuzione;

            @XmlElement ( name = "codice_riferimento_interno", required = true )
            protected String codiceRiferimentoInterno;

            @XmlElement ( name = "tipo_contabilita", required = true )
            protected String tipoContabilita;

            @XmlElement ( required = true )
            protected String destinazione;

            @XmlElement ( name = "assoggettamento_bollo", required = true )
            protected String assoggettamentoBollo;

            @XmlElement ( name = "assoggettamento_spese", required = true )
            protected String assoggettamentoSpese;

            @XmlElement ( required = true )
            protected FlussoGiornaleDiCassa.InformazioniContoEvidenza.MovimentoContoEvidenza.Cliente cliente;

            @XmlElement ( required = true )
            protected String causale;

            @XmlElement ( name = "numero_sospeso" )
            protected int numeroSospeso;

            /**
             * Recupera il valore della propriet tipoMovimento.
             *
             * @return possible object is {@link String }
             *
             */
            public String getTipoMovimento () {
                return tipoMovimento;
            }

            /**
             * Imposta il valore della propriet tipoMovimento.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setTipoMovimento ( String value ) {
                this.tipoMovimento = value;
            }

            /**
             * Recupera il valore della propriet tipoDocumento.
             *
             * @return possible object is {@link String }
             *
             */
            public String getTipoDocumento () {
                return tipoDocumento;
            }

            /**
             * Imposta il valore della propriet tipoDocumento.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setTipoDocumento ( String value ) {
                this.tipoDocumento = value;
            }

            /**
             * Recupera il valore della propriet tipoOperazione.
             *
             * @return possible object is {@link String }
             *
             */
            public String getTipoOperazione () {
                return tipoOperazione;
            }

            /**
             * Imposta il valore della propriet tipoOperazione.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setTipoOperazione ( String value ) {
                this.tipoOperazione = value;
            }

            /**
             * Recupera il valore della propriet numeroDocumento.
             *
             */
            public int getNumeroDocumento () {
                return numeroDocumento;
            }

            /**
             * Imposta il valore della propriet numeroDocumento.
             *
             */
            public void setNumeroDocumento ( int value ) {
                this.numeroDocumento = value;
            }

            /**
             * Recupera il valore della propriet progressivoDocumento.
             *
             */
            public int getProgressivoDocumento () {
                return progressivoDocumento;
            }

            /**
             * Imposta il valore della propriet progressivoDocumento.
             *
             */
            public void setProgressivoDocumento ( int value ) {
                this.progressivoDocumento = value;
            }

            /**
             * Recupera il valore della propriet importo.
             *
             */
            public BigDecimal getImporto () {
                return importo;
            }

            /**
             * Imposta il valore della propriet importo.
             *
             */
            public void setImporto ( BigDecimal value ) {
                this.importo = value;
            }

            /**
             * Recupera il valore della propriet numeroBollettaQuietanza.
             *
             */
            public int getNumeroBollettaQuietanza () {
                return numeroBollettaQuietanza;
            }

            /**
             * Imposta il valore della propriet numeroBollettaQuietanza.
             *
             */
            public void setNumeroBollettaQuietanza ( int value ) {
                this.numeroBollettaQuietanza = value;
            }

            /**
             * Recupera il valore della propriet dataMovimento.
             *
             * @return possible object is {@link XMLGregorianCalendar }
             *
             */
            public XMLGregorianCalendar getDataMovimento () {
                return dataMovimento;
            }

            /**
             * Imposta il valore della propriet dataMovimento.
             *
             * @param value allowed object is {@link XMLGregorianCalendar }
             *
             */
            public void setDataMovimento ( XMLGregorianCalendar value ) {
                this.dataMovimento = value;
            }

            /**
             * Recupera il valore della propriet dataValutaEnte.
             *
             * @return possible object is {@link XMLGregorianCalendar }
             *
             */
            public XMLGregorianCalendar getDataValutaEnte () {
                return dataValutaEnte;
            }

            /**
             * Imposta il valore della propriet dataValutaEnte.
             *
             * @param value allowed object is {@link XMLGregorianCalendar }
             *
             */
            public void setDataValutaEnte ( XMLGregorianCalendar value ) {
                this.dataValutaEnte = value;
            }

            /**
             * Recupera il valore della propriet tipoEsecuzione.
             *
             * @return possible object is {@link String }
             *
             */
            public String getTipoEsecuzione () {
                return tipoEsecuzione;
            }

            /**
             * Imposta il valore della propriet tipoEsecuzione.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setTipoEsecuzione ( String value ) {
                this.tipoEsecuzione = value;
            }

            /**
             * Recupera il valore della propriet codiceRiferimentoInterno.
             *
             * @return possible object is {@link String }
             *
             */
            public String getCodiceRiferimentoInterno () {
                return codiceRiferimentoInterno;
            }

            /**
             * Imposta il valore della propriet codiceRiferimentoInterno.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setCodiceRiferimentoInterno ( String value ) {
                this.codiceRiferimentoInterno = value;
            }

            /**
             * Recupera il valore della propriet tipoContabilita.
             *
             * @return possible object is {@link String }
             *
             */
            public String getTipoContabilita () {
                return tipoContabilita;
            }

            /**
             * Imposta il valore della propriet tipoContabilita.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setTipoContabilita ( String value ) {
                this.tipoContabilita = value;
            }

            /**
             * Recupera il valore della propriet destinazione.
             *
             * @return possible object is {@link String }
             *
             */
            public String getDestinazione () {
                return destinazione;
            }

            /**
             * Imposta il valore della propriet destinazione.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setDestinazione ( String value ) {
                this.destinazione = value;
            }

            /**
             * Recupera il valore della propriet assoggettamentoBollo.
             *
             * @return possible object is {@link String }
             *
             */
            public String getAssoggettamentoBollo () {
                return assoggettamentoBollo;
            }

            /**
             * Imposta il valore della propriet assoggettamentoBollo.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setAssoggettamentoBollo ( String value ) {
                this.assoggettamentoBollo = value;
            }

            /**
             * Recupera il valore della propriet assoggettamentoSpese.
             *
             * @return possible object is {@link String }
             *
             */
            public String getAssoggettamentoSpese () {
                return assoggettamentoSpese;
            }

            /**
             * Imposta il valore della propriet assoggettamentoSpese.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setAssoggettamentoSpese ( String value ) {
                this.assoggettamentoSpese = value;
            }

            /**
             * Recupera il valore della propriet cliente.
             *
             * @return possible object is {@link FlussoGiornaleDiCassa.InformazioniContoEvidenza.MovimentoContoEvidenza.Cliente }
             *
             */
            public FlussoGiornaleDiCassa.InformazioniContoEvidenza.MovimentoContoEvidenza.Cliente getCliente () {
                return cliente;
            }

            /**
             * Imposta il valore della propriet cliente.
             *
             * @param value allowed object is {@link FlussoGiornaleDiCassa.InformazioniContoEvidenza.MovimentoContoEvidenza.Cliente }
             *
             */
            public void setCliente ( FlussoGiornaleDiCassa.InformazioniContoEvidenza.MovimentoContoEvidenza.Cliente value ) {
                this.cliente = value;
            }

            /**
             * Recupera il valore della propriet causale.
             *
             * @return possible object is {@link String }
             *
             */
            public String getCausale () {
                return causale;
            }

            /**
             * Imposta il valore della propriet causale.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setCausale ( String value ) {
                this.causale = value;
            }

            /**
             * Recupera il valore della propriet numeroSospeso.
             *
             */
            public int getNumeroSospeso () {
                return numeroSospeso;
            }

            /**
             * Imposta il valore della propriet numeroSospeso.
             *
             */
            public void setNumeroSospeso ( int value ) {
                this.numeroSospeso = value;
            }

            /**
             * <p>
             * Classe Java per anonymous complex type.
             *
             * <p>
             * Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
             *
             * <pre>
             * &lt;complexType&gt;
             *   &lt;complexContent&gt;
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
             *       &lt;sequence&gt;
             *         &lt;element name="anagrafica_cliente" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
             *       &lt;/sequence&gt;
             *     &lt;/restriction&gt;
             *   &lt;/complexContent&gt;
             * &lt;/complexType&gt;
             * </pre>
             *
             *
             */
            @XmlAccessorType ( XmlAccessType.FIELD )
            @XmlType ( name = "", propOrder = {
                "anagraficaCliente"
            } )
            public static class Cliente {

                @XmlElement ( name = "anagrafica_cliente", required = true )
                protected String anagraficaCliente;

                /**
                 * Recupera il valore della propriet anagraficaCliente.
                 *
                 * @return possible object is {@link String }
                 *
                 */
                public String getAnagraficaCliente () {
                    return anagraficaCliente;
                }

                /**
                 * Imposta il valore della propriet anagraficaCliente.
                 *
                 * @param value allowed object is {@link String }
                 *
                 */
                public void setAnagraficaCliente ( String value ) {
                    this.anagraficaCliente = value;
                }

            }

        }

    }

    /**
     * <p>
     * Classe Java per anonymous complex type.
     *
     * <p>
     * Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     *
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="codice_ABI_BT" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *         &lt;element name="data_ora_creazione_flusso" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="codice_ente" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="descrizione_ente" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="codice_istat_ente" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *         &lt;element name="codice_fiscale_ente" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *         &lt;element name="codice_tramite_ente" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="codice_tramite_BT" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="codice_ente_BT" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     *
     *
     */
    @XmlAccessorType ( XmlAccessType.FIELD )
    @XmlType ( name = "", propOrder = {
        "codiceABIBT",
        "dataOraCreazioneFlusso",
        "codiceEnte",
        "descrizioneEnte",
        "codiceIstatEnte",
        "codiceFiscaleEnte",
        "codiceTramiteEnte",
        "codiceTramiteBT",
        "codiceEnteBT"
    } )
    public static class TestataMessaggio {

        @XmlElement ( name = "codice_ABI_BT" )
        protected int codiceABIBT;

        @XmlElement ( name = "data_ora_creazione_flusso", required = true )
        protected String dataOraCreazioneFlusso;

        @XmlElement ( name = "codice_ente", required = true )
        protected String codiceEnte;

        @XmlElement ( name = "descrizione_ente", required = true )
        protected String descrizioneEnte;

        @XmlElement ( name = "codice_istat_ente" )
        protected String codiceIstatEnte;

        @XmlElement ( name = "codice_fiscale_ente" )
        protected String codiceFiscaleEnte;

        @XmlElement ( name = "codice_tramite_ente", required = true )
        protected String codiceTramiteEnte;

        @XmlElement ( name = "codice_tramite_BT", required = true )
        protected String codiceTramiteBT;

        @XmlElement ( name = "codice_ente_BT" )
        protected int codiceEnteBT;

        /**
         * Recupera il valore della propriet codiceABIBT.
         *
         */
        public int getCodiceABIBT () {
            return codiceABIBT;
        }

        /**
         * Imposta il valore della propriet codiceABIBT.
         *
         */
        public void setCodiceABIBT ( int value ) {
            this.codiceABIBT = value;
        }

        /**
         * Recupera il valore della propriet dataOraCreazioneFlusso.
         *
         * @return possible object is {@link String }
         *
         */
        public String getDataOraCreazioneFlusso () {
            return dataOraCreazioneFlusso;
        }

        /**
         * Imposta il valore della propriet dataOraCreazioneFlusso.
         *
         * @param value allowed object is {@link String }
         *
         */
        public void setDataOraCreazioneFlusso ( String value ) {
            this.dataOraCreazioneFlusso = value;
        }

        /**
         * Recupera il valore della propriet codiceEnte.
         *
         * @return possible object is {@link String }
         *
         */
        public String getCodiceEnte () {
            return codiceEnte;
        }

        /**
         * Imposta il valore della propriet codiceEnte.
         *
         * @param value allowed object is {@link String }
         *
         */
        public void setCodiceEnte ( String value ) {
            this.codiceEnte = value;
        }

        /**
         * Recupera il valore della propriet descrizioneEnte.
         *
         * @return possible object is {@link String }
         *
         */
        public String getDescrizioneEnte () {
            return descrizioneEnte;
        }

        /**
         * Imposta il valore della propriet descrizioneEnte.
         *
         * @param value allowed object is {@link String }
         *
         */
        public void setDescrizioneEnte ( String value ) {
            this.descrizioneEnte = value;
        }

        /**
         * Recupera il valore della propriet codiceIstatEnte.
         *
         */
        public String getCodiceIstatEnte () {
            return codiceIstatEnte;
        }

        /**
         * Imposta il valore della propriet codiceIstatEnte.
         *
         */
        public void setCodiceIstatEnte ( String value ) {
            this.codiceIstatEnte = value;
        }

        /**
         * Recupera il valore della propriet codiceFiscaleEnte.
         *
         */
        public String getCodiceFiscaleEnte () {
            return codiceFiscaleEnte;
        }

        /**
         * Imposta il valore della propriet codiceFiscaleEnte.
         *
         */
        public void setCodiceFiscaleEnte ( String value ) {
            this.codiceFiscaleEnte = value;
        }

        /**
         * Recupera il valore della propriet codiceTramiteEnte.
         *
         * @return possible object is {@link String }
         *
         */
        public String getCodiceTramiteEnte () {
            return codiceTramiteEnte;
        }

        /**
         * Imposta il valore della propriet codiceTramiteEnte.
         *
         * @param value allowed object is {@link String }
         *
         */
        public void setCodiceTramiteEnte ( String value ) {
            this.codiceTramiteEnte = value;
        }

        /**
         * Recupera il valore della propriet codiceTramiteBT.
         *
         * @return possible object is {@link String }
         *
         */
        public String getCodiceTramiteBT () {
            return codiceTramiteBT;
        }

        /**
         * Imposta il valore della propriet codiceTramiteBT.
         *
         * @param value allowed object is {@link String }
         *
         */
        public void setCodiceTramiteBT ( String value ) {
            this.codiceTramiteBT = value;
        }

        /**
         * Recupera il valore della propriet codiceEnteBT.
         *
         */
        public int getCodiceEnteBT () {
            return codiceEnteBT;
        }

        /**
         * Imposta il valore della propriet codiceEnteBT.
         *
         */
        public void setCodiceEnteBT ( int value ) {
            this.codiceEnteBT = value;
        }

    }

    /**
     * <p>
     * Classe Java per anonymous complex type.
     *
     * <p>
     * Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     *
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="saldo_conti_correnti" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *         &lt;element name="saldo_conti_BI" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
     *         &lt;element name="totale_conti" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
     *         &lt;element name="vincoli_conti_correnti" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *         &lt;element name="vincoli_conti_BI" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *         &lt;element name="totale_vincoli" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *         &lt;element name="anticipazione_accordata" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *         &lt;element name="anticipazione_utilizzata" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *         &lt;element name="disponibilita" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     *
     *
     */
    @XmlAccessorType ( XmlAccessType.FIELD )
    @XmlType ( name = "", propOrder = {
        "saldoContiCorrenti",
        "saldoContiBI",
        "totaleConti",
        "vincoliContiCorrenti",
        "vincoliContiBI",
        "totaleVincoli",
        "anticipazioneAccordata",
        "anticipazioneUtilizzata",
        "disponibilita"
    } )
    public static class TotaliDisponibilitaLiquide {

        @XmlElement ( name = "saldo_conti_correnti" )
        protected int saldoContiCorrenti;

        @XmlElement ( name = "saldo_conti_BI" )
        protected double saldoContiBI;

        @XmlElement ( name = "totale_conti" )
        protected double totaleConti;

        @XmlElement ( name = "vincoli_conti_correnti" )
        protected int vincoliContiCorrenti;

        @XmlElement ( name = "vincoli_conti_BI" )
        protected int vincoliContiBI;

        @XmlElement ( name = "totale_vincoli" )
        protected int totaleVincoli;

        @XmlElement ( name = "anticipazione_accordata" )
        protected int anticipazioneAccordata;

        @XmlElement ( name = "anticipazione_utilizzata" )
        protected int anticipazioneUtilizzata;

        protected double disponibilita;

        /**
         * Recupera il valore della propriet saldoContiCorrenti.
         *
         */
        public int getSaldoContiCorrenti () {
            return saldoContiCorrenti;
        }

        /**
         * Imposta il valore della propriet saldoContiCorrenti.
         *
         */
        public void setSaldoContiCorrenti ( int value ) {
            this.saldoContiCorrenti = value;
        }

        /**
         * Recupera il valore della propriet saldoContiBI.
         *
         */
        public double getSaldoContiBI () {
            return saldoContiBI;
        }

        /**
         * Imposta il valore della propriet saldoContiBI.
         *
         */
        public void setSaldoContiBI ( double value ) {
            this.saldoContiBI = value;
        }

        /**
         * Recupera il valore della propriet totaleConti.
         *
         */
        public double getTotaleConti () {
            return totaleConti;
        }

        /**
         * Imposta il valore della propriet totaleConti.
         *
         */
        public void setTotaleConti ( double value ) {
            this.totaleConti = value;
        }

        /**
         * Recupera il valore della propriet vincoliContiCorrenti.
         *
         */
        public int getVincoliContiCorrenti () {
            return vincoliContiCorrenti;
        }

        /**
         * Imposta il valore della propriet vincoliContiCorrenti.
         *
         */
        public void setVincoliContiCorrenti ( int value ) {
            this.vincoliContiCorrenti = value;
        }

        /**
         * Recupera il valore della propriet vincoliContiBI.
         *
         */
        public int getVincoliContiBI () {
            return vincoliContiBI;
        }

        /**
         * Imposta il valore della propriet vincoliContiBI.
         *
         */
        public void setVincoliContiBI ( int value ) {
            this.vincoliContiBI = value;
        }

        /**
         * Recupera il valore della propriet totaleVincoli.
         *
         */
        public int getTotaleVincoli () {
            return totaleVincoli;
        }

        /**
         * Imposta il valore della propriet totaleVincoli.
         *
         */
        public void setTotaleVincoli ( int value ) {
            this.totaleVincoli = value;
        }

        /**
         * Recupera il valore della propriet anticipazioneAccordata.
         *
         */
        public int getAnticipazioneAccordata () {
            return anticipazioneAccordata;
        }

        /**
         * Imposta il valore della propriet anticipazioneAccordata.
         *
         */
        public void setAnticipazioneAccordata ( int value ) {
            this.anticipazioneAccordata = value;
        }

        /**
         * Recupera il valore della propriet anticipazioneUtilizzata.
         *
         */
        public int getAnticipazioneUtilizzata () {
            return anticipazioneUtilizzata;
        }

        /**
         * Imposta il valore della propriet anticipazioneUtilizzata.
         *
         */
        public void setAnticipazioneUtilizzata ( int value ) {
            this.anticipazioneUtilizzata = value;
        }

        /**
         * Recupera il valore della propriet disponibilita.
         *
         */
        public double getDisponibilita () {
            return disponibilita;
        }

        /**
         * Imposta il valore della propriet disponibilita.
         *
         */
        public void setDisponibilita ( double value ) {
            this.disponibilita = value;
        }

    }

    /**
     * <p>
     * Classe Java per anonymous complex type.
     *
     * <p>
     * Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     *
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="fondo_di_cassa" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
     *         &lt;element name="totale_reversali_riscosse" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
     *         &lt;element name="totale_sospesi_entrata" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
     *         &lt;element name="totale_entrate" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
     *         &lt;element name="deficit_di_cassa" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *         &lt;element name="totale_mandati_pagati" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *         &lt;element name="totale_sospesi_uscita" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
     *         &lt;element name="totale_uscite" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
     *         &lt;element name="saldo_esercizio" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     *
     *
     */
    @XmlAccessorType ( XmlAccessType.FIELD )
    @XmlType ( name = "", propOrder = {
        "fondoDiCassa",
        "totaleReversaliRiscosse",
        "totaleSospesiEntrata",
        "totaleEntrate",
        "deficitDiCassa",
        "totaleMandatiPagati",
        "totaleSospesiUscita",
        "totaleUscite",
        "saldoEsercizio"
    } )
    public static class TotaliEsercizio {

        @XmlElement ( name = "fondo_di_cassa" )
        protected double fondoDiCassa;

        @XmlElement ( name = "totale_reversali_riscosse" )
        protected double totaleReversaliRiscosse;

        @XmlElement ( name = "totale_sospesi_entrata" )
        protected double totaleSospesiEntrata;

        @XmlElement ( name = "totale_entrate" )
        protected double totaleEntrate;

        @XmlElement ( name = "deficit_di_cassa" )
        protected int deficitDiCassa;

        @XmlElement ( name = "totale_mandati_pagati" )
        protected int totaleMandatiPagati;

        @XmlElement ( name = "totale_sospesi_uscita" )
        protected double totaleSospesiUscita;

        @XmlElement ( name = "totale_uscite" )
        protected double totaleUscite;

        @XmlElement ( name = "saldo_esercizio" )
        protected double saldoEsercizio;

        /**
         * Recupera il valore della propriet fondoDiCassa.
         *
         */
        public double getFondoDiCassa () {
            return fondoDiCassa;
        }

        /**
         * Imposta il valore della propriet fondoDiCassa.
         *
         */
        public void setFondoDiCassa ( double value ) {
            this.fondoDiCassa = value;
        }

        /**
         * Recupera il valore della propriet totaleReversaliRiscosse.
         *
         */
        public double getTotaleReversaliRiscosse () {
            return totaleReversaliRiscosse;
        }

        /**
         * Imposta il valore della propriet totaleReversaliRiscosse.
         *
         */
        public void setTotaleReversaliRiscosse ( double value ) {
            this.totaleReversaliRiscosse = value;
        }

        /**
         * Recupera il valore della propriet totaleSospesiEntrata.
         *
         */
        public double getTotaleSospesiEntrata () {
            return totaleSospesiEntrata;
        }

        /**
         * Imposta il valore della propriet totaleSospesiEntrata.
         *
         */
        public void setTotaleSospesiEntrata ( double value ) {
            this.totaleSospesiEntrata = value;
        }

        /**
         * Recupera il valore della propriet totaleEntrate.
         *
         */
        public double getTotaleEntrate () {
            return totaleEntrate;
        }

        /**
         * Imposta il valore della propriet totaleEntrate.
         *
         */
        public void setTotaleEntrate ( double value ) {
            this.totaleEntrate = value;
        }

        /**
         * Recupera il valore della propriet deficitDiCassa.
         *
         */
        public int getDeficitDiCassa () {
            return deficitDiCassa;
        }

        /**
         * Imposta il valore della propriet deficitDiCassa.
         *
         */
        public void setDeficitDiCassa ( int value ) {
            this.deficitDiCassa = value;
        }

        /**
         * Recupera il valore della propriet totaleMandatiPagati.
         *
         */
        public int getTotaleMandatiPagati () {
            return totaleMandatiPagati;
        }

        /**
         * Imposta il valore della propriet totaleMandatiPagati.
         *
         */
        public void setTotaleMandatiPagati ( int value ) {
            this.totaleMandatiPagati = value;
        }

        /**
         * Recupera il valore della propriet totaleSospesiUscita.
         *
         */
        public double getTotaleSospesiUscita () {
            return totaleSospesiUscita;
        }

        /**
         * Imposta il valore della propriet totaleSospesiUscita.
         *
         */
        public void setTotaleSospesiUscita ( double value ) {
            this.totaleSospesiUscita = value;
        }

        /**
         * Recupera il valore della propriet totaleUscite.
         *
         */
        public double getTotaleUscite () {
            return totaleUscite;
        }

        /**
         * Imposta il valore della propriet totaleUscite.
         *
         */
        public void setTotaleUscite ( double value ) {
            this.totaleUscite = value;
        }

        /**
         * Recupera il valore della propriet saldoEsercizio.
         *
         */
        public double getSaldoEsercizio () {
            return saldoEsercizio;
        }

        /**
         * Imposta il valore della propriet saldoEsercizio.
         *
         */
        public void setSaldoEsercizio ( double value ) {
            this.saldoEsercizio = value;
        }

    }

}
