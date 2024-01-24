/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebParam;
import javax.xml.ws.WebServiceContext;

import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputVuoto;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsAcquisizioneFlussoRendicontazione;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsAggiornaProvvisori;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsAggiornaStatoReport;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsCancellaProvvisori;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsElaborazione;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsInserisciFlussiDaRielaborare;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsLock;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsLogAudit;
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
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsSpacchettamentoFlussoRendicontazione;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsTrasmettiFlussiInErrorePagoPA;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsTrasmettiFlussiPagoPA;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputRicercaElaborazionePrecedente;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsAggiornaStatoReport;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsCambioStatoFlusso;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsCodiciVersamento;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsElaborazione;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsEnti;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsEsito;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsFlussoDettaglio;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsFlussoOrigine;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsFlussoSintesi;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsLock;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsMotoreDiRiconciliazione;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsInserisciPrenotazioneReport;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsRicercaPrenotazioneReport;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsProvvisori;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsRicercaLimiteElaborazioneReport;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsRicecaLimiteEsportazione;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsRicercaFlussiDaEsportare;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsStatiElaborazione;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsStatoFlusso;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsTrasmettiFlussiInErrorePagoPA;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsTrasmettiFlussiPagoPA;
import it.csi.epay.epaymodric.interfacews.epaymodric.enums.TipoAcquisizione;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.EpaymodricException;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.UnrecoverableException;
import it.csi.epay.epaymodric.util.wsdl.flussorendicontazione.ResponseType;
import it.csi.epay.epaymodric.util.wsdl.flussorendicontazione.TrasmettiFlussoRendicontazioneExtRequestType;


public interface EpaymodricBusiness {

    public String testResources ( WebServiceContext wsContext, String dummy )
                    throws EpaymodricException, Exception, UnrecoverableException;

    public DTOOutputWsStatiElaborazione elencaStatiElaborazione ( DTOInputVuoto input )
                    throws EpaymodricException, Exception, UnrecoverableException;

    public void emailDequeue () throws EpaymodricException, Exception, UnrecoverableException;

    // Caso d'uso : 2.2.6
//    public DTOOutputWsMotoreDiRiconciliazione attivaElaborazione ()
//                    throws EpaymodricException, Exception, UnrecoverableException;
    
    public DTOOutputWsMotoreDiRiconciliazione attivaElaborazione (List<String>  statiDaEscludere)
            throws EpaymodricException, Exception, UnrecoverableException;

    // Caso d'uso : 2.2.7
    public DTOOutputWsMotoreDiRiconciliazione elaborazioneEsegui ( Long idElaborazione )
                    throws EpaymodricException, Exception, UnrecoverableException;

    // Caso d'uso : 2.2.3
    public ResponseType acquisciFlussoRendicontazione ( TrasmettiFlussoRendicontazioneExtRequestType in, TipoAcquisizione tipoAcquisizione )
                    throws EpaymodricException, Exception, UnrecoverableException;

    // Caso d'uso : 2.2.3
    public DTOOutputWsElaborazione spacchettaFlussiRendicontazioneDaSpacchettareByEnte ( DTOInputWsAcquisizioneFlussoRendicontazione in )
                    throws EpaymodricException, Exception, UnrecoverableException;

    // Caso d'uso : 2.2.3
    public DTOOutputWsElaborazione
    spacchettaFlussoRendicontazioneDaSpacchettareByEnteByIdentificativoFlusso ( DTOInputWsSpacchettamentoFlussoRendicontazione in )
                    throws EpaymodricException, Exception, UnrecoverableException;

    //Nuru aggiorna stato flusso 2.2.10
    public DTOOutputWsCambioStatoFlusso aggiornaStatoFlusso ( List<String> identificativoFlusso, String idEnte, String nuovoStatoFlusso )
                    throws EpaymodricException, Exception, UnrecoverableException;

    // Caso d'uso : 2.2.11
    //Rilanciando una elaborazione,inserisce una riga con lo stato FORZATO nella tabella cbl_t_elaborazione
    public DTOOutputWsEsito inserisciElaborazione ( DTOInputWsElaborazione in )
                    throws EpaymodricException, Exception, UnrecoverableException;

    //Caso d'uso: 2.2.19
    public DTOOutputWsFlussoOrigine ricercaFlussoOrigine (
        DTOInputWsRicercaFlussoOrigine dtoInputWsRicercaFlussoOrigine )
                        throws EpaymodricException, UnrecoverableException, Exception;

    //Caso d'uso: 2.2.19
    public ArrayList<DTOOutputWsFlussoSintesi> ricercaFlussiSintesi (
        DTOInputWsRicercaFlussoSintesi dtoInputWsRicercaFlussoSintesi )
                        throws EpaymodricException, UnrecoverableException, Exception;

    //Caso d'uso: 2.2.19
    public ArrayList<DTOOutputWsFlussoDettaglio> ricercaFlussiDettaglio (
        DTOInputWsRicercaFlussoDettaglio dtoInputWsRicercaFlussoDettaglio )
                        throws EpaymodricException, UnrecoverableException, Exception;

    //Caso d'uso: 2.2.19
    public ArrayList<DTOOutputWsStatoFlusso> ricercaStatoFlusso (
        DTOInputWsRicercaStatoFlusso dtoInputWsRicercaStatoFlusso )
                        throws EpaymodricException, Exception, UnrecoverableException;

    //Caso d'uso: 2.2.5
    public DTOOutputWsEsito aggiornaProvvisori ( DTOInputWsAggiornaProvvisori dtoInputWsAggiornaProvvisori ) throws EpaymodricException, Exception, UnrecoverableException;

    //Caso d'uso: 2.2.5
    public DTOOutputWsEsito cancellaProvvisori ( DTOInputWsCancellaProvvisori dtoInputWsCancellaProvvisori )
                    throws EpaymodricException, Exception, UnrecoverableException;

    //Caso d'uso: 2.2.5
    public DTOOutputWsProvvisori ricercaProvvisori ( DTOInputWsRicercaProvvisori dtoInputWsRicercaProvvisori ) throws EpaymodricException, Exception, UnrecoverableException;

    // Nuru 2.2.18
    public List<DTOOutputRicercaElaborazionePrecedente> ricercaElaborazionePrecedente(DTOInputWsRicercaElaborazionePrecedente input)
                    throws EpaymodricException, Exception, UnrecoverableException;

    //############ Nuru test logAudit


    public DTOInputWsLogAudit salvaLogAudit ( @WebParam ( name = "logAudit" ) DTOInputWsLogAudit logAudit )
                    throws EpaymodricException, Exception, UnrecoverableException;

    //    // Nuru getAllLogAudit
    //    @WebResult ( name = "result" ) //.--TEST
    //    @WebMethod
    //    public List<DTOInputWsLogAudit> getAllCsiLogAudit () throws EpaymodricException, UnrecoverableException, Exception;
    //
    //    // Nuru getCsiLogAudit
    //    @WebResult ( name = "result" ) //---TEST
    //    @WebMethod
    //    public List<DTOInputWsLogAudit> getCsiLogAudit ( @WebParam ( name = "idAction" ) String idAction )
    //                    throws EpaymodricException, UnrecoverableException, Exception;

    //##########Fine LogAudit

    public DTOOutputWsTrasmettiFlussiPagoPA trasmettiFlussiPagoPA ( DTOInputWsTrasmettiFlussiPagoPA input )
                    throws EpaymodricException, Exception, UnrecoverableException;

    public DTOOutputWsTrasmettiFlussiInErrorePagoPA trasmettiFlussiInErrorePagoPA (
        DTOInputWsTrasmettiFlussiInErrorePagoPA input ) throws EpaymodricException, Exception, UnrecoverableException;

    public List<DTOOutputWsLock> lockFind ( DTOInputWsLock input );

    public Boolean lockBreak ( Long idLock );

    public DTOOutputWsEnti elencaEnti ();

    public DTOOutputWsEsito inserisciFlussiDaRielaborare ( DTOInputWsInserisciFlussiDaRielaborare input )
                    throws EpaymodricException, Exception, UnrecoverableException;

    //Nuru
    public DTOOutputWsEsito esitoFlussiPagoPA () throws EpaymodricException, Exception, UnrecoverableException;

    public DTOOutputWsEsito salvaListaDiCarico ( DTOInputWsSalvaListaDiCarico input ) throws EpaymodricException, Exception, UnrecoverableException;
    
    /**
     * Ricerca codici versamento
     * @param input
     * @return
     */
    public DTOOutputWsCodiciVersamento ricercaCodiciVersamentoPerCodice ( DTOInputWsRicercaCodiciVersamento input );
    
    
    /**
     * Ricerca codici versamento
     * @param input
     * @return
     */
    public DTOOutputWsInserisciPrenotazioneReport inserisciPrenotazioneReport ( DTOInputWsInserisciPrenotazioneReport input );

    public List<DTOOutputWsRicercaPrenotazioneReport> ricercaPrenotazioneReport ( DTOInputWsRicercaPrenotazioneReport input ) throws EpaymodricException, Exception, UnrecoverableException;
    
    public DTOOutputWsAggiornaStatoReport aggiornaStatoReport(DTOInputWsAggiornaStatoReport input) throws EpaymodricException, Exception, UnrecoverableException;
    
    public DTOOutputWsRicecaLimiteEsportazione ricercaLimiteEsportazione() throws EpaymodricException, Exception, UnrecoverableException;
    
    
    public DTOOutputWsRicercaLimiteElaborazioneReport ricercaLimiteElaborazioneReport()throws EpaymodricException, Exception, UnrecoverableException ;
    public DTOOutputWsRicercaFlussiDaEsportare ricercaFlussiDaEsportare ( DTOInputWsRicercaFlussiDaEsportare ricercaFlussiDaEsportare ) throws EpaymodricException, UnrecoverableException, Exception;

	public List<DTOOutputWsCodiciVersamento> ricercaCodiciVersamento(
			DTOInputWsRicercaCodiciVersamento ricercaCodiciVersanento );
}
