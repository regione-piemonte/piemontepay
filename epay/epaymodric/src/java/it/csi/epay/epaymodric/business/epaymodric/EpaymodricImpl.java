/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.csi.epay.epaymodric.business.epaymodric.EpaymodricBusiness;
import it.csi.epay.epaymodric.business.epaymodric.security.RequestContextUtils;
import it.csi.epay.epaymodric.business.epaymodric.security.RequestParserHelper;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputVuoto;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsAcquisizioneFlussoRendicontazione;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsAggiornaProvvisori;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsAggiornaStatoFlusso;
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
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsProvvisori;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsRicecaLimiteEsportazione;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsRicercaFlussiDaEsportare;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsRicercaLimiteElaborazioneReport;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsRicercaPrenotazioneReport;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsStatiElaborazione;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsStatoFlusso;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsTrasmettiFlussiInErrorePagoPA;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsTrasmettiFlussiPagoPA;
import it.csi.epay.epaymodric.interfacews.epaymodric.Epaymodric;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.EpaymodricException;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.UnrecoverableException;


@WebService ( serviceName = "EpaymodricService", portName = "EpaymodricPort", endpointInterface = "it.csi.epay.epaymodric.interfacews.epaymodric.Epaymodric" )
public class EpaymodricImpl implements Epaymodric {

    @Resource
    WebServiceContext wsContext;

    @Autowired
    private EpaymodricBusiness epaymodricBusiness;

    public EpaymodricBusiness getEpaymodricBusiness () {
        return epaymodricBusiness;
    }

    public void setEpaymodricBusiness ( EpaymodricBusiness epaymodricBusiness ) {
        this.epaymodricBusiness = epaymodricBusiness;
    }

    @PostConstruct
    public void postConstruct () {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext ( this );
    }

    //    @Override
    public String testResources ( java.lang.String dummy )
                    throws it.csi.epay.epaymodric.interfacews.epaymodric.exception.EpaymodricException, Exception, UnrecoverableException {

        RequestContextUtils.getRequestContext ()
        .setInputData ( dummy )
        .setCaller ( RequestParserHelper.getCaller ( null ) )
        .setOutputDataType ( String.class );

        return getEpaymodricBusiness ().testResources ( wsContext, dummy );
    }

    @Override
    public DTOOutputWsEnti elencaEnti ( DTOInputVuoto input )
                    throws EpaymodricException, Exception, UnrecoverableException {

        RequestContextUtils.getRequestContext ()
        .setInputData ( input )
        .setCaller ( RequestParserHelper.getCaller ( input ) )
        .setOutputDataType ( DTOOutputWsEnti.class );

        return getEpaymodricBusiness ().elencaEnti ();
    }

    //    @Override
    public DTOOutputWsElaborazione spacchettaFlussiDaSpacchettareByEnte ( DTOInputWsAcquisizioneFlussoRendicontazione input )
                    throws EpaymodricException, Exception, UnrecoverableException {

        RequestContextUtils.getRequestContext ()
        .setInputData ( input )
        .setCaller ( RequestParserHelper.getCaller ( input ) )
        .setOutputDataType ( DTOOutputWsElaborazione.class );

        return getEpaymodricBusiness ().spacchettaFlussiRendicontazioneDaSpacchettareByEnte ( input );
    }

    //    @Override
    public DTOOutputWsElaborazione spacchettaFlussoDaSpacchettareByEnteByIdentificativoFlusso ( DTOInputWsSpacchettamentoFlussoRendicontazione input )
                    throws EpaymodricException, Exception, UnrecoverableException {

        RequestContextUtils.getRequestContext ()
        .setInputData ( input )
        .setCaller ( RequestParserHelper.getCaller ( input ) )
        .setOutputDataType ( DTOOutputWsElaborazione.class );

        return getEpaymodricBusiness ().spacchettaFlussoRendicontazioneDaSpacchettareByEnteByIdentificativoFlusso ( input );
    }

    //Rilanciando una elaborazione,inserisce una riga con lo stato FORZATO nella tabella cbl_t_elaborazione
    @Override
    public DTOOutputWsEsito inserisciElaborazione ( DTOInputWsElaborazione input ) throws EpaymodricException, Exception, UnrecoverableException {

        RequestContextUtils.getRequestContext ()
        .setInputData ( input )
        .setCaller ( RequestParserHelper.getCaller ( input ) )
        .setOutputDataType ( DTOOutputWsElaborazione.class );

        //        SecurityUtils.assertUseCase ( "NOME_CDU_DA_VERIFICARE" );

        return getEpaymodricBusiness ().inserisciElaborazione ( input );
    }

    //Nuru 2.2.10
    //    @Override
    public DTOOutputWsCambioStatoFlusso aggiornaStatoFlusso ( DTOInputWsAggiornaStatoFlusso input )
                    throws EpaymodricException, Exception, UnrecoverableException {

        RequestContextUtils.getRequestContext ()
        .setInputData ( input )
        .setCaller ( RequestParserHelper.getCaller ( input ) )
        .setOutputDataType ( DTOOutputWsCambioStatoFlusso.class );

        return getEpaymodricBusiness ().aggiornaStatoFlusso ( input.getIdentificativoFlusso (), input.getIdEnte (), input.getNuovoStatoFlusso () );
    }

    //Caso d'uso: 2.2.19
    @Override
    public DTOOutputWsFlussoOrigine ricercaFlussoOrigine (
        DTOInputWsRicercaFlussoOrigine dtoInputWsRicercaFlusso )
                        throws EpaymodricException, UnrecoverableException, Exception {

        RequestContextUtils.getRequestContext ()
        .setInputData ( dtoInputWsRicercaFlusso )
        .setCaller ( RequestParserHelper.getCaller ( dtoInputWsRicercaFlusso ) )
        .setOutputDataType ( DTOOutputWsFlussoOrigine.class );

        return getEpaymodricBusiness ().ricercaFlussoOrigine ( dtoInputWsRicercaFlusso );
    }

    @Override
    public ArrayList<DTOOutputWsFlussoSintesi> ricercaFlussiSintesi (
        DTOInputWsRicercaFlussoSintesi dtoInputWsRicercaFlussoSintesi )
                        throws EpaymodricException, UnrecoverableException, Exception {

        RequestContextUtils.getRequestContext ()
        .setInputData ( dtoInputWsRicercaFlussoSintesi )
        .setCaller ( RequestParserHelper.getCaller ( dtoInputWsRicercaFlussoSintesi ) )
        .setOutputDataType ( DTOOutputWsFlussoSintesi.class );

        return getEpaymodricBusiness ().ricercaFlussiSintesi ( dtoInputWsRicercaFlussoSintesi );
    }

    @Override
    public ArrayList<DTOOutputWsFlussoDettaglio> ricercaFlussiDettaglio (
        DTOInputWsRicercaFlussoDettaglio dtoInputWsRicercaFlussoDettaglio )
                        throws EpaymodricException, UnrecoverableException, Exception {

        RequestContextUtils.getRequestContext ()
        .setInputData ( dtoInputWsRicercaFlussoDettaglio )
        .setCaller ( RequestParserHelper.getCaller ( dtoInputWsRicercaFlussoDettaglio ) )
        .setOutputDataType ( DTOOutputWsFlussoDettaglio.class );

        return getEpaymodricBusiness ().ricercaFlussiDettaglio ( dtoInputWsRicercaFlussoDettaglio );
    }

    @Override
    public ArrayList<DTOOutputWsStatoFlusso> ricercaStatoFlusso (
        DTOInputWsRicercaStatoFlusso dtoInputWsRicercaStatoFlusso )
                        throws EpaymodricException, Exception, UnrecoverableException {

        RequestContextUtils.getRequestContext ()
        .setInputData ( dtoInputWsRicercaStatoFlusso )
        .setCaller ( RequestParserHelper.getCaller ( dtoInputWsRicercaStatoFlusso ) )
        .setOutputDataType ( DTOOutputWsStatoFlusso.class );

        return getEpaymodricBusiness ().ricercaStatoFlusso ( dtoInputWsRicercaStatoFlusso );
    }

    @Override
    public DTOOutputWsEsito aggiornaProvvisori ( DTOInputWsAggiornaProvvisori dtoInputWsAggiornaProvvisori )
                    throws EpaymodricException, Exception, UnrecoverableException {

        RequestContextUtils.getRequestContext ()
        .setInputData ( dtoInputWsAggiornaProvvisori )
        .setCaller ( RequestParserHelper.getCaller ( dtoInputWsAggiornaProvvisori ) )
        .setOutputDataType ( DTOOutputWsEsito.class );

        return getEpaymodricBusiness ().aggiornaProvvisori ( dtoInputWsAggiornaProvvisori );
    }

    @Override
    public DTOOutputWsEsito cancellaProvvisori ( DTOInputWsCancellaProvvisori dtoInputWsCancellaProvvisori )
                    throws EpaymodricException, Exception, UnrecoverableException {

        RequestContextUtils.getRequestContext ()
        .setInputData ( dtoInputWsCancellaProvvisori )
        .setCaller ( RequestParserHelper.getCaller ( dtoInputWsCancellaProvvisori ) )
        .setOutputDataType ( DTOOutputWsEsito.class );

        return getEpaymodricBusiness ().cancellaProvvisori ( dtoInputWsCancellaProvvisori );
    }

    @Override
    public DTOOutputWsProvvisori ricercaProvvisori ( DTOInputWsRicercaProvvisori dtoInputWsRicercaProvvisori )
                    throws EpaymodricException, Exception, UnrecoverableException {

        RequestContextUtils.getRequestContext ()
        .setInputData ( dtoInputWsRicercaProvvisori )
        .setCaller ( RequestParserHelper.getCaller ( dtoInputWsRicercaProvvisori ) )
        .setOutputDataType ( DTOOutputWsEsito.class );

        return getEpaymodricBusiness ().ricercaProvvisori ( dtoInputWsRicercaProvvisori );
    }

    public DTOOutputWsTrasmettiFlussiPagoPA testTrasmettiFlussiPagoPA ( DTOInputWsTrasmettiFlussiPagoPA input )
                    throws EpaymodricException, Exception, UnrecoverableException {

        RequestContextUtils.getRequestContext ()
        .setInputData ( input )
        .setCaller ( RequestParserHelper.getCaller ( input ) )
        .setOutputDataType ( DTOOutputWsTrasmettiFlussiPagoPA.class );

        return getEpaymodricBusiness ().trasmettiFlussiPagoPA ( input );
    }

    public DTOOutputWsTrasmettiFlussiInErrorePagoPA testTrasmettiFlussiInErrorePagoPA (
        DTOInputWsTrasmettiFlussiInErrorePagoPA input )
                        throws EpaymodricException, Exception, UnrecoverableException {

        RequestContextUtils.getRequestContext ()
        .setInputData ( input )
        .setCaller ( RequestParserHelper.getCaller ( input ) )
        .setOutputDataType ( DTOOutputWsTrasmettiFlussiInErrorePagoPA.class );

        return getEpaymodricBusiness ().trasmettiFlussiInErrorePagoPA ( input );
    }

    @Override
    public void emailDequeue ( DTOInputVuoto input ) throws EpaymodricException, Exception, UnrecoverableException {

        RequestContextUtils.getRequestContext ()
        .setInputData ( input )
        .setCaller ( RequestParserHelper.getCaller ( input ) )
        .setOutputDataType ( null );

        getEpaymodricBusiness ().emailDequeue ();

    }

    @Override
    public List<DTOOutputRicercaElaborazionePrecedente> ricercaElaborazionePrecedente ( DTOInputWsRicercaElaborazionePrecedente input )
                    throws EpaymodricException, UnrecoverableException, Exception {

        RequestContextUtils.getRequestContext ()
        .setInputData ( input )
        .setCaller ( RequestParserHelper.getCaller ( input ) )
        .setOutputDataType ( DTOOutputRicercaElaborazionePrecedente.class );

        return getEpaymodricBusiness ().ricercaElaborazionePrecedente ( input);
    }

    @Override
    public DTOOutputWsEsito inserisciFlussiDaRielaborare ( DTOInputWsInserisciFlussiDaRielaborare input )
                    throws EpaymodricException, UnrecoverableException, Exception {

        RequestContextUtils.getRequestContext ()
        .setInputData ( input )
        .setCaller ( RequestParserHelper.getCaller ( input ) )
        .setOutputDataType ( null );

        return getEpaymodricBusiness ().inserisciFlussiDaRielaborare ( input );
    }

    @Override
    public DTOOutputWsStatiElaborazione elencaStatiElaborazione ( DTOInputVuoto input ) throws EpaymodricException, Exception, UnrecoverableException {
        return getEpaymodricBusiness ().elencaStatiElaborazione ( input );
    }

    @Override
    public DTOOutputWsEsito esitoFlussiPagoPA () throws EpaymodricException, Exception, UnrecoverableException {
        return getEpaymodricBusiness ().esitoFlussiPagoPA ();
    }

    @Override
    public DTOOutputWsEsito salvaListaDiCarico ( DTOInputWsSalvaListaDiCarico input ) throws EpaymodricException, Exception, UnrecoverableException {
        RequestContextUtils.getRequestContext ()
        .setInputData ( input )
        .setCaller ( RequestParserHelper.getCaller ( input ) )
        .setOutputDataType ( null );
        return getEpaymodricBusiness ().salvaListaDiCarico ( input );
    }


	@Override
	public List<DTOOutputWsRicercaPrenotazioneReport> prenotazioneReport(DTOInputWsRicercaPrenotazioneReport input)
			throws EpaymodricException, Exception, UnrecoverableException {
		
		RequestContextUtils.getRequestContext()
		.setInputData( input )
		.setCaller( RequestParserHelper.getCaller( input ) )
		.setOutputDataType( DTOOutputWsRicercaPrenotazioneReport.class );
		
		return getEpaymodricBusiness().ricercaPrenotazioneReport( input );
	}

    @Override
    public DTOOutputWsCodiciVersamento ricercaCodiciVersamentoPerCodice ( DTOInputWsRicercaCodiciVersamento input )
                    throws EpaymodricException, Exception, UnrecoverableException {
    	RequestContextUtils.getRequestContext ()
        .setInputData ( input )
        .setCaller ( RequestParserHelper.getCaller ( input ) )
        .setOutputDataType ( DTOOutputWsCodiciVersamento.class );
        return getEpaymodricBusiness ().ricercaCodiciVersamentoPerCodice ( input );
    }


    @Override
    public DTOOutputWsInserisciPrenotazioneReport inserisciPrenotazioneReport ( DTOInputWsInserisciPrenotazioneReport inputPrenotazioneReport )
                    throws EpaymodricException, Exception, UnrecoverableException {
        return getEpaymodricBusiness ().inserisciPrenotazioneReport ( inputPrenotazioneReport );
    }

	@Override
	public DTOOutputWsAggiornaStatoReport aggiornaStatoReport(DTOInputWsAggiornaStatoReport input) throws EpaymodricException, Exception, UnrecoverableException{
		
		RequestContextUtils.getRequestContext ()
        .setInputData ( input )
        .setCaller ( RequestParserHelper.getCaller ( input ) )
        .setOutputDataType ( DTOOutputWsAggiornaStatoReport.class );
		return getEpaymodricBusiness().aggiornaStatoReport(input);
	}

	@Override
	public DTOOutputWsRicecaLimiteEsportazione ricercaLimiteEsportazione()
			throws EpaymodricException, Exception, UnrecoverableException {
		
		return getEpaymodricBusiness().ricercaLimiteEsportazione();
	}
	
	@Override
	public DTOOutputWsRicercaLimiteElaborazioneReport ricercaLimiteElaborazioneReport()
			throws EpaymodricException, Exception, UnrecoverableException {
		
		return getEpaymodricBusiness().ricercaLimiteElaborazioneReport();
	}

    @Override
    public DTOOutputWsRicercaFlussiDaEsportare ricercaFlussiDaEsportare ( DTOInputWsRicercaFlussiDaEsportare ricercaFlussiDaEsportare )
                    throws EpaymodricException, UnrecoverableException, Exception {
    	RequestContextUtils.getRequestContext ()
        .setInputData ( ricercaFlussiDaEsportare )
        .setCaller ( RequestParserHelper.getCaller ( ricercaFlussiDaEsportare ) )
        .setOutputDataType ( DTOOutputWsRicercaFlussiDaEsportare.class );
    	
        return getEpaymodricBusiness ().ricercaFlussiDaEsportare ( ricercaFlussiDaEsportare );
    }

	@Override
	public List<DTOOutputWsCodiciVersamento> ricercaCodiciVersamento (
			DTOInputWsRicercaCodiciVersamento ricercaCodiciVersanento )
			throws EpaymodricException, Exception, UnrecoverableException {
		return getEpaymodricBusiness ().ricercaCodiciVersamento( ricercaCodiciVersanento );
	}

}
