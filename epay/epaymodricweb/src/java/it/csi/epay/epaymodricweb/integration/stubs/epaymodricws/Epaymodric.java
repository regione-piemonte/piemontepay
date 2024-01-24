/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.integration.stubs.epaymodricws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.2.5
 * Tue Apr 06 11:45:19 CEST 2021
 * Generated source version: 2.2.5
 * 
 */
 
@WebService(targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", name = "Epaymodric")
@XmlSeeAlso({ObjectFactory.class})
public interface Epaymodric {

    @WebMethod
    @RequestWrapper(localName = "ricercaElaborazionePrecedente", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.RicercaElaborazionePrecedente")
    @ResponseWrapper(localName = "ricercaElaborazionePrecedenteResponse", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.RicercaElaborazionePrecedenteResponse")
    @WebResult(name = "result", targetNamespace = "")
    public java.util.List<it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputRicercaElaborazionePrecedente> ricercaElaborazionePrecedente(
        @WebParam(name = "arg0", targetNamespace = "")
        it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputWsRicercaElaborazionePrecedente arg0
    ) throws UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception;

    @WebMethod
    @RequestWrapper(localName = "inserisciElaborazione", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.InserisciElaborazione")
    @ResponseWrapper(localName = "inserisciElaborazioneResponse", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.InserisciElaborazioneResponse")
    @WebResult(name = "result", targetNamespace = "")
    public it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsEsito inserisciElaborazione(
        @WebParam(name = "inserisciElaborazioneInput", targetNamespace = "")
        it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputWsElaborazione inserisciElaborazioneInput
    ) throws UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception;

    @WebMethod
    @RequestWrapper(localName = "ricercaFlussiDettaglio", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.RicercaFlussiDettaglio")
    @ResponseWrapper(localName = "ricercaFlussiDettaglioResponse", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.RicercaFlussiDettaglioResponse")
    @WebResult(name = "result", targetNamespace = "")
    public java.util.List<it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsFlussoDettaglio> ricercaFlussiDettaglio(
        @WebParam(name = "ricercaFlussoDettaglio", targetNamespace = "")
        it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputWsRicercaFlussoDettaglio ricercaFlussoDettaglio
    ) throws UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception;

    @WebMethod
    @RequestWrapper(localName = "aggiornaStatoReport", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.AggiornaStatoReport")
    @ResponseWrapper(localName = "aggiornaStatoReportResponse", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.AggiornaStatoReportResponse")
    @WebResult(name = "result", targetNamespace = "")
    public it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsAggiornaStatoReport aggiornaStatoReport(
        @WebParam(name = "aggiornaStatoReport", targetNamespace = "")
        it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputWsAggiornaStatoReport aggiornaStatoReport
    ) throws UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception;

    @WebMethod
    @RequestWrapper(localName = "elencaEnti", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.ElencaEnti")
    @ResponseWrapper(localName = "elencaEntiResponse", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.ElencaEntiResponse")
    @WebResult(name = "return", targetNamespace = "")
    public it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsEnti elencaEnti(
        @WebParam(name = "param", targetNamespace = "")
        it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputVuoto param
    ) throws UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception;

    @WebMethod
    @RequestWrapper(localName = "ricercaProvvisori", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.RicercaProvvisori")
    @ResponseWrapper(localName = "ricercaProvvisoriResponse", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.RicercaProvvisoriResponse")
    @WebResult(name = "return", targetNamespace = "")
    public it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsProvvisori ricercaProvvisori(
        @WebParam(name = "listaProvvisoriDaCercare", targetNamespace = "")
        it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputWsRicercaProvvisori listaProvvisoriDaCercare
    ) throws UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception;

    @WebMethod
    @RequestWrapper(localName = "salvaListaDiCarico", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.SalvaListaDiCarico")
    @ResponseWrapper(localName = "salvaListaDiCaricoResponse", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.SalvaListaDiCaricoResponse")
    @WebResult(name = "result", targetNamespace = "")
    public it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsEsito salvaListaDiCarico(
        @WebParam(name = "arg0", targetNamespace = "")
        it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputWsSalvaListaDiCarico arg0
    ) throws UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception;

    @WebMethod
    @RequestWrapper(localName = "ricercaCodiciVersamento", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.RicercaCodiciVersamento")
    @ResponseWrapper(localName = "ricercaCodiciVersamentoResponse", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.RicercaCodiciVersamentoResponse")
    @WebResult(name = "result", targetNamespace = "")
    public java.util.List<it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsCodiciVersamento> ricercaCodiciVersamento(
        @WebParam(name = "ricercaCodiciVersamento", targetNamespace = "")
        it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputWsRicercaCodiciVersamento ricercaCodiciVersamento
    ) throws UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception;

    @WebMethod
    @RequestWrapper(localName = "ricercaLimiteEsportazione", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.RicercaLimiteEsportazione")
    @ResponseWrapper(localName = "ricercaLimiteEsportazioneResponse", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.RicercaLimiteEsportazioneResponse")
    @WebResult(name = "result", targetNamespace = "")
    public it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsRicercaLimiteEsportazione ricercaLimiteEsportazione() throws UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception;

    @WebMethod
    @RequestWrapper(localName = "aggiornaProvvisori", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.AggiornaProvvisori")
    @ResponseWrapper(localName = "aggiornaProvvisoriResponse", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.AggiornaProvvisoriResponse")
    @WebResult(name = "result", targetNamespace = "")
    public it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsEsito aggiornaProvvisori(
        @WebParam(name = "listaProvvisoriDaAggiornare", targetNamespace = "")
        it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputWsAggiornaProvvisori listaProvvisoriDaAggiornare
    ) throws UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception;

    @WebMethod
    @RequestWrapper(localName = "elencaStatiElaborazione", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.ElencaStatiElaborazione")
    @ResponseWrapper(localName = "elencaStatiElaborazioneResponse", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.ElencaStatiElaborazioneResponse")
    @WebResult(name = "return", targetNamespace = "")
    public it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsStatiElaborazione elencaStatiElaborazione(
        @WebParam(name = "param", targetNamespace = "")
        it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputVuoto param
    ) throws UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception;

    @WebMethod
    @RequestWrapper(localName = "ricercaFlussoOrigine", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.RicercaFlussoOrigine")
    @ResponseWrapper(localName = "ricercaFlussoOrigineResponse", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.RicercaFlussoOrigineResponse")
    @WebResult(name = "result", targetNamespace = "")
    public it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsFlussoOrigine ricercaFlussoOrigine(
        @WebParam(name = "ricercaFlussoOrigine", targetNamespace = "")
        it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputWsRicercaFlussoOrigine ricercaFlussoOrigine
    ) throws UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception;

    @WebMethod
    @RequestWrapper(localName = "emailDequeue", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.EmailDequeue")
    @ResponseWrapper(localName = "emailDequeueResponse", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.EmailDequeueResponse")
    public void emailDequeue(
        @WebParam(name = "param", targetNamespace = "")
        it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputVuoto param
    ) throws UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception;

    @WebMethod
    @RequestWrapper(localName = "eseguiElaborazione", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.EseguiElaborazione")
    @ResponseWrapper(localName = "eseguiElaborazioneResponse", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.EseguiElaborazioneResponse")
    @WebResult(name = "result", targetNamespace = "")
    public it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsMotoreDiRiconciliazione eseguiElaborazione(
        @WebParam(name = "input", targetNamespace = "")
        it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputWsEseguiElaborazione input
    ) throws UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception;

    @WebMethod
    @RequestWrapper(localName = "ricercaLimiteElaborazioneReport", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.RicercaLimiteElaborazioneReport")
    @ResponseWrapper(localName = "ricercaLimiteElaborazioneReportResponse", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.RicercaLimiteElaborazioneReportResponse")
    @WebResult(name = "result", targetNamespace = "")
    public it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsRicercaLimiteElaborazioneReport ricercaLimiteElaborazioneReport() throws UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception;

    @WebMethod
    @RequestWrapper(localName = "ricercaCodiciVersamentoPerCodice", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.RicercaCodiciVersamentoPerCodice")
    @ResponseWrapper(localName = "ricercaCodiciVersamentoPerCodiceResponse", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.RicercaCodiciVersamentoPerCodiceResponse")
    @WebResult(name = "result", targetNamespace = "")
    public it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsCodiciVersamento ricercaCodiciVersamentoPerCodice(
        @WebParam(name = "param", targetNamespace = "")
        it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputWsRicercaCodiciVersamento param
    ) throws UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception;

    @WebMethod
    @RequestWrapper(localName = "attivaElaborazione", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.AttivaElaborazione")
    @ResponseWrapper(localName = "attivaElaborazioneResponse", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.AttivaElaborazioneResponse")
    @WebResult(name = "result", targetNamespace = "")
    public it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsMotoreDiRiconciliazione attivaElaborazione(
        @WebParam(name = "param", targetNamespace = "")
        it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputVuoto param
    ) throws UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception;

    @WebMethod
    @RequestWrapper(localName = "ricercaStatoFlusso", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.RicercaStatoFlusso")
    @ResponseWrapper(localName = "ricercaStatoFlussoResponse", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.RicercaStatoFlussoResponse")
    @WebResult(name = "result", targetNamespace = "")
    public java.util.List<it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsStatoFlusso> ricercaStatoFlusso(
        @WebParam(name = "ricercaStatoFlusso", targetNamespace = "")
        it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputWsRicercaStatoFlusso ricercaStatoFlusso
    ) throws UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception;

    @WebMethod
    @RequestWrapper(localName = "cancellaProvvisori", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.CancellaProvvisori")
    @ResponseWrapper(localName = "cancellaProvvisoriResponse", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.CancellaProvvisoriResponse")
    @WebResult(name = "result", targetNamespace = "")
    public it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsEsito cancellaProvvisori(
        @WebParam(name = "listaProvvisoriDaCancellare", targetNamespace = "")
        it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputWsCancellaProvvisori listaProvvisoriDaCancellare
    ) throws UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception;

    @WebMethod
    @RequestWrapper(localName = "prenotazioneReport", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.PrenotazioneReport")
    @ResponseWrapper(localName = "prenotazioneReportResponse", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.PrenotazioneReportResponse")
    @WebResult(name = "result", targetNamespace = "")
    public java.util.List<it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsRicercaPrenotazioneReport> prenotazioneReport(
        @WebParam(name = "param", targetNamespace = "")
        it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputWsRicercaPrenotazioneReport param
    ) throws UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception;

    @WebMethod
    @RequestWrapper(localName = "inserisciFlussiDaRielaborare", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.InserisciFlussiDaRielaborare")
    @ResponseWrapper(localName = "inserisciFlussiDaRielaborareResponse", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.InserisciFlussiDaRielaborareResponse")
    @WebResult(name = "result", targetNamespace = "")
    public it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsEsito inserisciFlussiDaRielaborare(
        @WebParam(name = "arg0", targetNamespace = "")
        it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputWsInserisciFlussiDaRielaborare arg0
    ) throws UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception;

    @WebMethod
    @RequestWrapper(localName = "inserisciPrenotazioneReport", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.InserisciPrenotazioneReport")
    @ResponseWrapper(localName = "inserisciPrenotazioneReportResponse", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.InserisciPrenotazioneReportResponse")
    @WebResult(name = "result", targetNamespace = "")
    public it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsInserisciPrenotazioneReport inserisciPrenotazioneReport(
        @WebParam(name = "param", targetNamespace = "")
        it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputWsInserisciPrenotazioneReport param
    ) throws UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception;

    @WebMethod
    @RequestWrapper(localName = "ricercaFlussiDaEsportare", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.RicercaFlussiDaEsportare")
    @ResponseWrapper(localName = "ricercaFlussiDaEsportareResponse", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.RicercaFlussiDaEsportareResponse")
    @WebResult(name = "result", targetNamespace = "")
    public it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsRicercaFlussiDaEsportare ricercaFlussiDaEsportare(
        @WebParam(name = "ricercaFlussiDaEsportare", targetNamespace = "")
        it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputWsRicercaFlussiDaEsportare ricercaFlussiDaEsportare
    ) throws UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception;

    @WebMethod
    @RequestWrapper(localName = "richiediEsitoFlussiDaSistemaContabile", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.RichiediEsitoFlussiDaSistemaContabile")
    @ResponseWrapper(localName = "richiediEsitoFlussiDaSistemaContabileResponse", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.RichiediEsitoFlussiDaSistemaContabileResponse")
    @WebResult(name = "result", targetNamespace = "")
    public it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsEsito richiediEsitoFlussiDaSistemaContabile() throws UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception;

    @WebMethod
    @RequestWrapper(localName = "ricercaFlussiSintesi", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.RicercaFlussiSintesi")
    @ResponseWrapper(localName = "ricercaFlussiSintesiResponse", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", className = "it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.RicercaFlussiSintesiResponse")
    @WebResult(name = "result", targetNamespace = "")
    public java.util.List<it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsFlussoSintesi> ricercaFlussiSintesi(
        @WebParam(name = "ricercaFlussoSintesi", targetNamespace = "")
        it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputWsRicercaFlussoSintesi ricercaFlussoSintesi
    ) throws UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception;
}