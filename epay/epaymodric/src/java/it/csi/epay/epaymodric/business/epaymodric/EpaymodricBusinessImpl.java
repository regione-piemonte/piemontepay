/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.ws.WebServiceContext;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.epay.epaymodric.business.epaymodric.bo.Configurazione;
import it.csi.epay.epaymodric.business.epaymodric.bo.Elaborazione;
import it.csi.epay.epaymodric.business.epaymodric.manager.CodiciVersamentoManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.ConfigurazioneManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.ElaborazioneAttivazioneManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.ElaborazioneManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.EnteManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.FlussoOrigineManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.FlussoRendicontazioneManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.InvioMailManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.ListaDiCaricoManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.LockManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.LogAuditManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.PrenotazioneReportManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.ProvvisoriManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.RicercaElaborazionePrecedenteManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.RicercaFlussoManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.RicercaStatoFlussoManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.RiconciliazioneManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.StatoElaborazioneManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.TestResourcesManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.TrasmissioneFlussiManager;
import it.csi.epay.epaymodric.dto.epaymodric.base.DTOEnte;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputVuoto;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsAcquisizioneFlussoRendicontazione;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsAggiornaProvvisori;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsAggiornaStatoReport;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsCancellaProvvisori;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsElaborazione;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsInserisciFlussiDaRielaborare;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsInserisciPrenotazioneReport;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsLock;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsLogAudit;
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
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsInserisciPrenotazioneReport;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsLock;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsMotoreDiRiconciliazione;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsProvvisori;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsRicercaLimiteElaborazioneReport;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsRicecaLimiteEsportazione;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsRicercaFlussiDaEsportare;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsRicercaPrenotazioneReport;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsStatiElaborazione;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsStatoFlusso;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsTrasmettiFlussiInErrorePagoPA;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsTrasmettiFlussiPagoPA;
import it.csi.epay.epaymodric.interfacews.epaymodric.consts.CostantiErrori;
import it.csi.epay.epaymodric.interfacews.epaymodric.enums.EmailEnum;
import it.csi.epay.epaymodric.interfacews.epaymodric.enums.TipoAcquisizione;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.EpaymodricException;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.UnrecoverableException;
import it.csi.epay.epaymodric.util.wsdl.flussorendicontazione.ResponseType;
import it.csi.epay.epaymodric.util.wsdl.flussorendicontazione.TrasmettiFlussoRendicontazioneExtRequestType;


/**
 * @generated
 */
@Service
public class EpaymodricBusinessImpl implements EpaymodricBusiness {

    private static final Logger logger = LogManager.getLogger ( EpaymodricBusinessImpl.class );

    /**
     * @generated
     */
    public static final String LOGGER_PREFIX = "epaymodric";

    @Autowired
    private TestResourcesManager testResourcesManager;

    @Autowired
    private ElaborazioneManager elaborazioneManager;

    @Autowired
    private InvioMailManager invioMailManager;

    @Autowired
    private EnteManager enteManager;

    @Autowired
    private LockManager lockManager;

    @Autowired
    private ElaborazioneAttivazioneManager elaborazioneAttivazioneManager;

    @Autowired
    private FlussoRendicontazioneManager flussoRendicontazioneManager;

    @Autowired
    private FlussoOrigineManager flussoOrigineManager;

    @Autowired
    private RicercaFlussoManager ricercaFlussoManager;

    @Autowired
    private RicercaStatoFlussoManager ricercaStatoFlussoManager;

    @Autowired
    private LogAuditManager logAuditManager;

    @Autowired
    private RicercaElaborazionePrecedenteManager ricercaElaborazionePrecedenteManager;

    @Autowired
    ProvvisoriManager provvisoriManager;

    @Autowired
    private TrasmissioneFlussiManager trasmissioneFlussiManager;

    @Autowired
    private RiconciliazioneManager riconciliazioneManager;

    @Autowired
    StatoElaborazioneManager statoElaborazioneManager;

    @Autowired
    private ConfigurazioneManager configurazioneManager;

    @Autowired
    private ListaDiCaricoManager listaDiCaricoManager;
    
    @Autowired
    private PrenotazioneReportManager prenotazioneReportManager;

    @Autowired
    private CodiciVersamentoManager codiciVersamentoManager;

    // Caso d'uso 2.2.3
    @Override
    public ResponseType acquisciFlussoRendicontazione ( TrasmettiFlussoRendicontazioneExtRequestType in, TipoAcquisizione tipoAcquisizione )
                    throws EpaymodricException, Exception, UnrecoverableException {
        return flussoRendicontazioneManager.acquisciFlussoRendicontazione ( in, tipoAcquisizione );
    }

    /*
     * Implementazione operazioni esposte dal servizio
     */

    @Override
    public DTOOutputWsEsito aggiornaProvvisori ( DTOInputWsAggiornaProvvisori dtoInputWsAggiornaProvvisori )
                    throws EpaymodricException, Exception, UnrecoverableException {
        return provvisoriManager.aggiornaProvvisori ( dtoInputWsAggiornaProvvisori );
    }

    // Nuru 2.2.12 Aggiorna stato Flusso
    @Override
    public DTOOutputWsCambioStatoFlusso aggiornaStatoFlusso ( List<String> identificativoFlusso, String idEnte,
        String nuovoStatoFlusso ) throws EpaymodricException, Exception, UnrecoverableException {
        DTOOutputWsCambioStatoFlusso dtoOutput = flussoOrigineManager.aggiornaStatoFlusso ( identificativoFlusso, idEnte, nuovoStatoFlusso );

        return dtoOutput;
    }

    // Caso d'uso 2.2.6
//    
    @Override
    public DTOOutputWsMotoreDiRiconciliazione attivaElaborazione (List<String>  statiDaEscludere)
                    throws EpaymodricException, Exception, UnrecoverableException {

        DTOOutputWsMotoreDiRiconciliazione retVal = new DTOOutputWsMotoreDiRiconciliazione ();

        try {

            elaborazioneAttivazioneManager.attivaElaborazione (statiDaEscludere);

            retVal.setEsito ( new DTOOutputWsEsito () );

            retVal.getEsito ().setEsito ( "000" );

            retVal.getEsito ().setDescrizione ( "Elaborazione attivata" );

        } catch ( Exception e ) {
            String message = "Errore di sistema durante l'elaborazione, si prega di controllare i log";
            logger.error ( message, e );
            invioMailManager.invioMail ( EmailEnum.ERRORE_DI_SISTEMA, CostantiErrori.ERRORE_DI_SISTEMA, null, null, message );
        }

        return retVal;
    }
    
  

    @Override
    public DTOOutputWsEsito cancellaProvvisori ( DTOInputWsCancellaProvvisori dtoInputWsCancellaProvvisori )
                    throws EpaymodricException, Exception, UnrecoverableException {
        return provvisoriManager.cancellaProvvisori ( dtoInputWsCancellaProvvisori );
    }

    // Caso d'uso 2.2.7
    @Override
    public DTOOutputWsMotoreDiRiconciliazione elaborazioneEsegui ( Long idElaborazione )
                    throws EpaymodricException, Exception, UnrecoverableException {

        Elaborazione elaborazione = elaborazioneManager.leggi ( idElaborazione );

        DTOOutputWsMotoreDiRiconciliazione output = new DTOOutputWsMotoreDiRiconciliazione ();

        if ( null != elaborazione ) {
            Map<String, Configurazione> conf = configurazioneManager.recuperaConfigurazionePerElaborazione ( elaborazione.getIdEnte () );
            //output = riconciliazioneManager.eseguiRieseguiElaborazione ( conf, elaborazione ,null,  0, new Integer("0"), true, true); // DA RIVEDERE
            output = riconciliazioneManager.elaborazioneEsegui ( conf, elaborazione ,null); 
            
        } else {
            DTOOutputWsEsito esito = new DTOOutputWsEsito ();

            esito.setCodiceErrore ( CostantiErrori.WS_ESITO_KO_DEFAULT );
            esito.setDescrizione ( "Nessuna elaborazione valida" );
            output.setEsito ( esito );
        }
        return output;
    }

    @Override
    public DTOOutputWsEnti elencaEnti () {

        List<DTOEnte> enti = enteManager.elencaEnti ();

        DTOOutputWsEnti entiList = new DTOOutputWsEnti ();

        for ( Iterator<DTOEnte> iterator = enti.iterator (); iterator.hasNext (); ) {
            DTOEnte ente = iterator.next ();

            entiList.getEnti ().add ( ente );
        }

        return entiList;
    }

    @Override
    public DTOOutputWsStatiElaborazione elencaStatiElaborazione ( DTOInputVuoto input ) throws EpaymodricException, Exception, UnrecoverableException {

        DTOOutputWsStatiElaborazione dtoOutput = new DTOOutputWsStatiElaborazione ();
        dtoOutput.setStatiElaborazione ( this.statoElaborazioneManager.recuperaStatiElaborazione () );
        return dtoOutput;
    }

    @Override
    public void emailDequeue () throws EpaymodricException, Exception, UnrecoverableException {
        invioMailManager.emailDequeue ();

    }

    @Override
    public DTOOutputWsEsito esitoFlussiPagoPA () throws EpaymodricException, Exception, UnrecoverableException {

        flussoOrigineManager.esitoFlussiPagoPA ();
        DTOOutputWsEsito response = new DTOOutputWsEsito ();

        response.setEsito ( "000" );
        response.setDescrizione ( "Operazione completata correttamente" );

        return response;
    }

    protected Logger getLogger ( String subsystem ) {
        if ( subsystem != null ) {
            return LogManager.getLogger ( LOGGER_PREFIX + "." + subsystem );
        } else {
            return LogManager.getLogger ( LOGGER_PREFIX );
        }
    }

    // Caso d'uso 2.2.11
    //Rilanciando una elaborazione,inserisce una riga con lo stato FORZATO nella tabella cbl_t_elaborazione
    @Override
    public DTOOutputWsEsito inserisciElaborazione ( DTOInputWsElaborazione in ) throws EpaymodricException, Exception, UnrecoverableException {
        return elaborazioneManager.inserisciElaborazione ( in );
    }

    @Override
    public DTOOutputWsEsito inserisciFlussiDaRielaborare ( DTOInputWsInserisciFlussiDaRielaborare input )
                    throws EpaymodricException, Exception, UnrecoverableException {

        return elaborazioneManager.inserisciElaborazionePerRielaborareFlussi ( input );
    }

    @Override
    public Boolean lockBreak ( Long idLock ) {
        return lockManager.lockBreak ( idLock );
    }

    @Override
    public List<DTOOutputWsLock> lockFind ( DTOInputWsLock input ) {
        return lockManager.lockFind ( input );
    }

    @Override
    public List<DTOOutputRicercaElaborazionePrecedente> ricercaElaborazionePrecedente(DTOInputWsRicercaElaborazionePrecedente input)
                    throws EpaymodricException, Exception, UnrecoverableException {
        return ricercaElaborazionePrecedenteManager.ricercaElaborazionePrecedente(input);
    }

    // Caso d'uso: 2.2.19
    @Override
    public ArrayList<DTOOutputWsFlussoDettaglio> ricercaFlussiDettaglio (
        DTOInputWsRicercaFlussoDettaglio dtoInputWsRicercaFlussoDettaglio )
                        throws EpaymodricException, UnrecoverableException, Exception {
        ArrayList<DTOOutputWsFlussoDettaglio> ricercaFlussiDettaglio = new ArrayList<> ();
        logger.info ( "EpaymodricBusinessImpl.ricercaFlussiSintesi: INIZIO" );
        DTOOutputWsFlussoDettaglio outputRicercaFlussiDettaglio = ricercaFlussoManager
                        .ricercaFlussiDettaglio ( dtoInputWsRicercaFlussoDettaglio );
        ricercaFlussiDettaglio.add ( outputRicercaFlussiDettaglio );
        logger.info ( "EpaymodricBusinessImpl.ricercaFlussiSintesi: FINE" );
        return ricercaFlussiDettaglio;
    }

    //    @Override
    //    public List<DTOInputWsLogAudit> getAllCsiLogAudit () throws EpaymodricException, UnrecoverableException, Exception {
    //        return logAuditManager.getAllCsiLogAudit ();
    //    }
    //
    //    @Override
    //    public List<DTOInputWsLogAudit> getCsiLogAudit ( String idAction ) throws EpaymodricException, UnrecoverableException, Exception {
    //        return logAuditManager.getLogAudit ( idAction );
    //    }

    //Fine LogAudit Nuru

    // Caso d'uso: 2.2.19
    @Override
    public ArrayList<DTOOutputWsFlussoSintesi> ricercaFlussiSintesi (
        DTOInputWsRicercaFlussoSintesi dtoInputWsRicercaFlussoSintesi )
                        throws EpaymodricException, UnrecoverableException, Exception {
        ArrayList<DTOOutputWsFlussoSintesi> ricercaFlussiSintesi = new ArrayList<> ();
        logger.info ( "EpaymodricBusinessImpl.ricercaFlussiSintesi: INIZIO" );
        DTOOutputWsFlussoSintesi outputRicercaFlussiSintesi = ricercaFlussoManager
                        .ricercaFlussiSintesi ( dtoInputWsRicercaFlussoSintesi );
        ricercaFlussiSintesi.add ( outputRicercaFlussiSintesi );
        logger.info ( "EpaymodricBusinessImpl.ricercaFlussiSintesi: FINE" );
        return ricercaFlussiSintesi;
    }

    // Caso d'uso: 2.2.19
    @Override
    public DTOOutputWsFlussoOrigine ricercaFlussoOrigine (
        DTOInputWsRicercaFlussoOrigine dtoInputWsRicercaFlussoOrigine )
                        throws EpaymodricException, UnrecoverableException, Exception {
        logger.info ( "EpaymodricBusinessImpl.ricercaFlussoOrigine: INIZIO" );
        DTOOutputWsFlussoOrigine outputRicercaFlussiOrigine = ricercaFlussoManager
                        .ricercaFlussoOrigine ( dtoInputWsRicercaFlussoOrigine );
        logger.info ( "EpaymodricBusinessImpl.ricercaFlussoOrigine: FINE" );
        return outputRicercaFlussiOrigine;
    }

    @Override
    public DTOOutputWsProvvisori ricercaProvvisori ( DTOInputWsRicercaProvvisori dtoInputWsRicercaProvvisori )
                    throws EpaymodricException, Exception, UnrecoverableException {
        return provvisoriManager.ricercaProvvisori ( dtoInputWsRicercaProvvisori );
    }

    // Caso d'uso: 2.2.19
    @Override
    public ArrayList<DTOOutputWsStatoFlusso> ricercaStatoFlusso (
        DTOInputWsRicercaStatoFlusso dtoInputWsRicercaStatoFlusso )
                        throws EpaymodricException, Exception, UnrecoverableException {
        ArrayList<DTOOutputWsStatoFlusso> dtoOutputWsStatiFlusso = new ArrayList<> ();
        logger.info ( "EpaymodricBusinessImpl.ricercaStatoFlusso: INIZIO" );
        DTOOutputWsStatoFlusso outputWsStatoFlusso = new DTOOutputWsStatoFlusso ();
        outputWsStatoFlusso = ricercaStatoFlussoManager.ricercaStatoFlusso ( dtoInputWsRicercaStatoFlusso );
        dtoOutputWsStatiFlusso.add ( outputWsStatoFlusso );
        logger.info ( "EpaymodricBusinessImpl.ricercaStatoFlusso: FINE" );
        return dtoOutputWsStatiFlusso;
    }

    @Override
    public DTOOutputWsEsito salvaListaDiCarico ( DTOInputWsSalvaListaDiCarico input ) throws EpaymodricException, Exception, UnrecoverableException {
        return listaDiCaricoManager.salvaListaDiCarico ( input );
    }

    //Nuru LogAudit
    @Override
    public DTOInputWsLogAudit salvaLogAudit ( DTOInputWsLogAudit audit ) throws EpaymodricException, UnrecoverableException, Exception {
        return logAuditManager.salvaLogAudit ( audit );
    }

    // Caso d'uso 2.2.3
    @Override
    public DTOOutputWsElaborazione spacchettaFlussiRendicontazioneDaSpacchettareByEnte ( DTOInputWsAcquisizioneFlussoRendicontazione in )
                    throws EpaymodricException, Exception, UnrecoverableException {
        return flussoRendicontazioneManager.spacchettaFlussiRendicontazioneDaSpacchettareByEnte ( in );
    }

    // Caso d'uso 2.2.3
    @Override
    public DTOOutputWsElaborazione
    spacchettaFlussoRendicontazioneDaSpacchettareByEnteByIdentificativoFlusso ( DTOInputWsSpacchettamentoFlussoRendicontazione in )
                    throws EpaymodricException, Exception, UnrecoverableException {
        return flussoRendicontazioneManager.spacchettaFlussoRendicontazioneDaSpacchettareByEnteByIdentificativoFlusso ( in );
    }

    @Override
    public String testResources ( WebServiceContext wsContext, java.lang.String dummy

                    ) throws it.csi.epay.epaymodric.interfacews.epaymodric.exception.EpaymodricException, Exception, UnrecoverableException {
        return testResourcesManager.testResources ();
    }


    @Override
    public DTOOutputWsTrasmettiFlussiInErrorePagoPA trasmettiFlussiInErrorePagoPA (
        DTOInputWsTrasmettiFlussiInErrorePagoPA input )
                        throws EpaymodricException, Exception, UnrecoverableException {
        return trasmissioneFlussiManager.creaOutputTrasmettiFlussiInErrorePagoPA ( input );
    }

    @Override
    public DTOOutputWsTrasmettiFlussiPagoPA trasmettiFlussiPagoPA ( DTOInputWsTrasmettiFlussiPagoPA input )
                    throws EpaymodricException, Exception, UnrecoverableException {
        return trasmissioneFlussiManager.creaOutputTrasmettiFlussiPagoPA ( input );
    }


	@Override
	public List<DTOOutputWsRicercaPrenotazioneReport> ricercaPrenotazioneReport(DTOInputWsRicercaPrenotazioneReport input)
			throws EpaymodricException, Exception, UnrecoverableException {
		
		return prenotazioneReportManager.ricercaReport( input );

	}

    @Override
    public DTOOutputWsCodiciVersamento ricercaCodiciVersamentoPerCodice ( DTOInputWsRicercaCodiciVersamento input ) {
        return codiciVersamentoManager.ricercaCodiciVersamentoPerCodice ( input );
    }
    @Override
    public DTOOutputWsInserisciPrenotazioneReport inserisciPrenotazioneReport ( DTOInputWsInserisciPrenotazioneReport input ) {
        return prenotazioneReportManager.inserisciPrenotazioneReport( input );
    }

	@Override
	public DTOOutputWsAggiornaStatoReport aggiornaStatoReport(DTOInputWsAggiornaStatoReport input)
			throws EpaymodricException, Exception, UnrecoverableException {
		
		return prenotazioneReportManager.aggiornaStatoReport(input);
	}

	@Override
	public DTOOutputWsRicecaLimiteEsportazione ricercaLimiteEsportazione()
			throws EpaymodricException, Exception, UnrecoverableException {
		
		return configurazioneManager.ricercaLimiteEsportazione();
	}
	
	@Override
	public DTOOutputWsRicercaLimiteElaborazioneReport ricercaLimiteElaborazioneReport()
			throws EpaymodricException, Exception, UnrecoverableException {
		
		return configurazioneManager.ricercaLimiteElaborazioneReport();
	}

    @Override
    public DTOOutputWsRicercaFlussiDaEsportare ricercaFlussiDaEsportare ( DTOInputWsRicercaFlussiDaEsportare ricercaFlussiDaEsportare )
                    throws EpaymodricException, UnrecoverableException, Exception {
        return prenotazioneReportManager.ricercaFlussiDaEsportare ( ricercaFlussiDaEsportare );
    }

	@Override
	public List<DTOOutputWsCodiciVersamento> ricercaCodiciVersamento(
			DTOInputWsRicercaCodiciVersamento ricercaCodiciVersanento) {
		
		return codiciVersamentoManager.ricercaCodiciVersamento ( ricercaCodiciVersanento );
	}
    

}
