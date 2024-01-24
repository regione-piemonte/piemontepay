/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.interfacews.epaymodric;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputVuoto;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsAggiornaProvvisori;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsAggiornaStatoReport;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsCancellaProvvisori;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsElaborazione;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsInserisciFlussiDaRielaborare;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsInserisciPrenotazioneReport;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsRicercaCodiciVersamento;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsRicercaElaborazionePrecedente;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsRicercaFlussiDaEsportare;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsRicercaFlussoDettaglio;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsRicercaFlussoOrigine;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsRicercaFlussoSintesi;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsRicercaPrenotazioneReport;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsRicercaProvvisori;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsRicercaStatoFlusso;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsSalvaListaDiCarico;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputRicercaElaborazionePrecedente;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsAggiornaStatoReport;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsCodiciVersamento;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsEnti;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsEsito;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsFlussoDettaglio;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsFlussoOrigine;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsFlussoSintesi;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsInserisciPrenotazioneReport;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsProvvisori;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsRicecaLimiteEsportazione;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsRicercaFlussiDaEsportare;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsRicercaLimiteElaborazioneReport;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsRicercaPrenotazioneReport;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsStatiElaborazione;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsStatoFlusso;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.EpaymodricException;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.UnrecoverableException;


@WebService ( name = "Epaymodric" )
public interface Epaymodric {

    @WebMethod
    public DTOOutputWsEnti elencaEnti ( @WebParam ( name = "param" ) DTOInputVuoto input ) throws EpaymodricException, Exception, UnrecoverableException;

    @WebMethod
    public DTOOutputWsStatiElaborazione elencaStatiElaborazione ( @WebParam ( name = "param" ) DTOInputVuoto input )
                    throws EpaymodricException, Exception, UnrecoverableException;

    @WebMethod
    public void emailDequeue ( @WebParam ( name = "param" ) DTOInputVuoto input ) throws EpaymodricException, Exception, UnrecoverableException;

    // Caso d'uso : 2.2.11
    @WebResult ( name = "result" )
    @WebMethod
    public DTOOutputWsEsito inserisciElaborazione ( @WebParam ( name = "inserisciElaborazioneInput" ) DTOInputWsElaborazione in )
                    throws EpaymodricException, Exception, UnrecoverableException;

    // Caso d'uso : 2.2.19
    @WebResult ( name = "result" )
    @WebMethod
    public DTOOutputWsFlussoOrigine ricercaFlussoOrigine (
        @WebParam ( name = "ricercaFlussoOrigine" ) DTOInputWsRicercaFlussoOrigine ricercaFlussoOrigine )
                        throws EpaymodricException, UnrecoverableException, Exception;

    @WebResult ( name = "result" )
    @WebMethod
    public ArrayList<DTOOutputWsFlussoSintesi> ricercaFlussiSintesi (
        @WebParam ( name = "ricercaFlussoSintesi" ) DTOInputWsRicercaFlussoSintesi ricercaFlussoSintesi )
                        throws EpaymodricException, Exception, UnrecoverableException;

    @WebResult ( name = "result" )
    @WebMethod
    public ArrayList<DTOOutputWsFlussoDettaglio> ricercaFlussiDettaglio (
        @WebParam ( name = "ricercaFlussoDettaglio" ) DTOInputWsRicercaFlussoDettaglio ricercaFlussoDettaglio )
                        throws EpaymodricException, Exception, UnrecoverableException;

    @WebResult ( name = "result" )
    @WebMethod
    public ArrayList<DTOOutputWsStatoFlusso> ricercaStatoFlusso (
        @WebParam ( name = "ricercaStatoFlusso" ) DTOInputWsRicercaStatoFlusso ricercaStatoFlusso )
                        throws EpaymodricException, Exception, UnrecoverableException;

    @WebResult ( name = "result" )
    @WebMethod
    public DTOOutputWsEsito aggiornaProvvisori (
        @WebParam ( name = "listaProvvisoriDaAggiornare" ) DTOInputWsAggiornaProvvisori dtoInputWsAggiornaProvvisori )
                        throws EpaymodricException, Exception, UnrecoverableException;

    @WebResult ( name = "result" )
    @WebMethod
    public DTOOutputWsEsito cancellaProvvisori (
        @WebParam ( name = "listaProvvisoriDaCancellare" ) DTOInputWsCancellaProvvisori dtoInputWsCancellaProvvisori )
                        throws EpaymodricException, Exception, UnrecoverableException;

    @WebMethod
    public DTOOutputWsProvvisori ricercaProvvisori (
        @WebParam ( name = "listaProvvisoriDaCercare" ) DTOInputWsRicercaProvvisori dtoInputWsRicercaProvvisori )
                        throws EpaymodricException, Exception, UnrecoverableException;

    //    @WebResult ( name = "result" )
    //    @WebMethod
    //    public DTOOutputWsTrasmettiFlussiPagoPA testTrasmettiFlussiPagoPA (
    //        @WebParam ( name = "input" ) DTOInputWsTrasmettiFlussiPagoPA input )
    //                        throws EpaymodricException, Exception, UnrecoverableException;

    //    @WebResult ( name = "result" )
    //    @WebMethod
    //    public DTOOutputWsTrasmettiFlussiInErrorePagoPA testTrasmettiFlussiInErrorePagoPA (
    //        @WebParam ( name = "input" ) DTOInputWsTrasmettiFlussiInErrorePagoPA input )
    //                        throws EpaymodricException, Exception, UnrecoverableException;

    // Nuru 2.2.18
    @WebResult ( name = "result" )
    @WebMethod
    public List<DTOOutputRicercaElaborazionePrecedente> ricercaElaborazionePrecedente (
        DTOInputWsRicercaElaborazionePrecedente input )
                        throws EpaymodricException, UnrecoverableException, Exception;

    @WebResult ( name = "result" )
    @WebMethod
    public DTOOutputWsEsito inserisciFlussiDaRielaborare ( DTOInputWsInserisciFlussiDaRielaborare input )
                    throws EpaymodricException, UnrecoverableException, Exception;

    @WebResult ( name = "result" )
    @WebMethod ( operationName = "richiediEsitoFlussiDaSistemaContabile" )
    public DTOOutputWsEsito esitoFlussiPagoPA ()
                    throws EpaymodricException, Exception, UnrecoverableException;

    @WebResult ( name = "result" )
    @WebMethod
    public DTOOutputWsEsito salvaListaDiCarico ( DTOInputWsSalvaListaDiCarico input ) throws EpaymodricException, Exception, UnrecoverableException;


    @WebResult( name ="result" )
    @WebMethod
    public List<DTOOutputWsRicercaPrenotazioneReport> prenotazioneReport ( @WebParam ( name = "param" ) DTOInputWsRicercaPrenotazioneReport input ) throws EpaymodricException, Exception, UnrecoverableException;

    @WebResult ( name = "result" )
    @WebMethod
    public DTOOutputWsCodiciVersamento ricercaCodiciVersamentoPerCodice ( @WebParam ( name = "param" ) DTOInputWsRicercaCodiciVersamento input )
                    throws EpaymodricException, Exception, UnrecoverableException;
    
    @WebResult ( name = "result" )
    @WebMethod
    public DTOOutputWsInserisciPrenotazioneReport inserisciPrenotazioneReport (  @WebParam ( name = "param" ) DTOInputWsInserisciPrenotazioneReport inputPrenotazioneReport )
            throws EpaymodricException, Exception, UnrecoverableException ;

    @WebResult ( name = "result" )
    @WebMethod
    public DTOOutputWsAggiornaStatoReport aggiornaStatoReport (@WebParam(name="aggiornaStatoReport") DTOInputWsAggiornaStatoReport input)  throws EpaymodricException, Exception, UnrecoverableException;
    
    @WebResult ( name = "result" )
    @WebMethod
    public DTOOutputWsRicecaLimiteEsportazione ricercaLimiteEsportazione () throws EpaymodricException, Exception, UnrecoverableException;

    @WebResult ( name = "result" )
    @WebMethod
    public DTOOutputWsRicercaLimiteElaborazioneReport ricercaLimiteElaborazioneReport () throws EpaymodricException, Exception, UnrecoverableException;

    @WebResult ( name = "result" )
    @WebMethod
    public DTOOutputWsRicercaFlussiDaEsportare ricercaFlussiDaEsportare (
        @WebParam ( name = "ricercaFlussiDaEsportare" ) DTOInputWsRicercaFlussiDaEsportare ricercaFlussiDaEsportare )
                        throws EpaymodricException, UnrecoverableException, Exception;
    
    @WebResult ( name = "result" )
    @WebMethod
    public List<DTOOutputWsCodiciVersamento> ricercaCodiciVersamento (
        @WebParam ( name = "ricercaCodiciVersamento" ) DTOInputWsRicercaCodiciVersamento ricercaCodiciVersamento )
                        throws EpaymodricException, Exception, UnrecoverableException;
}
