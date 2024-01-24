/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.ws.business;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

import it.csi.epay.epayservices.integration.db.manager.ErManager;
import it.csi.epay.epayservices.integration.db.manager.PagamentoManager;
import it.csi.epay.epayservices.integration.db.manager.RegistroVersamentiManager;
import it.csi.epay.epayservices.integration.db.manager.RrManager;
import it.csi.epay.epayservices.integration.db.manager.RtManager;
import it.csi.epay.epayservices.interfaces.rs.CodiciEsito;
import it.csi.epay.epayservices.model.DatiSingolaRevoca;
import it.csi.epay.epayservices.model.Er;
import it.csi.epay.epayservices.model.Pagamento;
import it.csi.epay.epayservices.model.Rr;
import it.csi.epay.epayservices.model.Rt;
import it.csi.epay.epayservices.utilities.LogUtil;
import it.csi.epay.epayservices.utilities.XmlUtil;
import it.csi.epay.epayservices.ws.interfaces.EpayFruitoreRiceviRrService;
import it.csi.epay.epayservices.ws.ricevirichiestarevoca.CtDatiEsitoRevoca;
import it.csi.epay.epayservices.ws.ricevirichiestarevoca.CtDatiRevoca;
import it.csi.epay.epayservices.ws.ricevirichiestarevoca.CtDatiSingolaRevoca;
import it.csi.epay.epayservices.ws.ricevirichiestarevoca.CtDatiSingoloEsitoRevoca;
import it.csi.epay.epayservices.ws.ricevirichiestarevoca.CtEsitoRevoca;
import it.csi.epay.epayservices.ws.ricevirichiestarevoca.CtIstitutoAttestante;
import it.csi.epay.epayservices.ws.ricevirichiestarevoca.CtRichiestaRevoca;
import it.csi.epay.epayservices.ws.ricevirichiestarevoca.CtRichiestaRevocaRequest;
import it.csi.epay.epayservices.ws.ricevirichiestarevoca.ParametriRiceviRT;
import it.csi.epay.epayservices.ws.ricevirichiestarevoca.ResponseType;
import it.csi.epay.epayservices.ws.ricevirichiestarevoca.ResultType;


@Stateless
@WebService (
                targetNamespace = "http://www.csi.it/epay/epaywso/ricevirichiestarevoca",
                portName = "RiceviRichiestaRevoca",
                serviceName = "RiceviRichiestaRevoca",
                endpointInterface = "it.csi.epay.epayservices.ws.interfaces.EpayFruitoreRiceviRrService" )

public class EpayFruitoreRiceviRr implements EpayFruitoreRiceviRrService {

    protected LogUtil log = new LogUtil ( this.getClass () );

    static final private int maxErrorMessageWidth = 200;

//    @EJB
//    TestResourcesOperation testResourcesOperation;
//
   @EJB
   RegistroVersamentiManager pagRisManager;
//
    @EJB
    RrManager rrManager;
    
    @EJB
    RtManager rtManager;
    
    @EJB
    ErManager erManager;
    
    @EJB
    PagamentoManager pagManager;
    
    
	   //Ricevo Lista di CtRichiestaRevoca,
	   //salvo ogni singola RR su EPAY_T_RR e su EPAY_T_DATI_SINGOLA_REVOCA
       // annullo tecnico per RR ricevuta
	   //salvo ogni singola ER su
	   //salvo RT su
    
    
	@Override
	public CtDatiEsitoRevoca riceviRichiestaRevoca(CtRichiestaRevocaRequest parameters) {
		
		   //RECUPERO RR 	
		   List<CtRichiestaRevoca> listaRevoche = parameters.getCtRichiestaRevoca();
		   List<CtDatiSingoloEsitoRevoca> listaSingoleRevoche = new ArrayList<CtDatiSingoloEsitoRevoca>();

		   Integer idRRInserito = null;
		   //salvataggio RR
		   for (Iterator iteratorOverRR = listaRevoche.iterator(); iteratorOverRR.hasNext();) {
			   CtRichiestaRevoca ctRichiestaRevoca = (CtRichiestaRevoca) iteratorOverRR.next();
			   Rr rrDaInserire =  preparaRrModel(ctRichiestaRevoca);
			   idRRInserito = rrManager.inserisci(rrDaInserire);
			   
			   if ((ctRichiestaRevoca.getDatiRevoca()!=null)&&((ctRichiestaRevoca.getDatiRevoca().getDatiSingolaRevoca()!=null) || ctRichiestaRevoca.getDatiRevoca().getDatiSingolaRevoca().size()>0)) {
			   
			   for (int i = 0; i < ctRichiestaRevoca.getDatiRevoca().getDatiSingolaRevoca().size(); i++) {
				   CtDatiSingolaRevoca datiSingolaRevoca = ctRichiestaRevoca.getDatiRevoca().getDatiSingolaRevoca().get(i);
				   DatiSingolaRevoca revoca = preparaSingolaRevocaModel(datiSingolaRevoca);
				   rrManager.inserisciSingolaRevoca(revoca, idRRInserito);
//				   Pagamento pagamento = preparaPagamentoPerAnnullo(parameters,datiSingolaRevoca,ctRichiestaRevoca);
//				   pagManager.inserisci(pagamento);
				   
				  // DA TOGLIERE 
//				   CtDatiSingoloEsitoRevoca singoloEsitoRevoca = new CtDatiSingoloEsitoRevoca();
//				   CtDatiSingolaRevoca singolaRevocaInput =  ctRichiestaRevoca.getDatiRevoca().getDatiSingolaRevoca().get(i);
//				   singoloEsitoRevoca.setCausaleEsito(singolaRevocaInput.getCausaleRevoca());
//				   singoloEsitoRevoca.setIdentificativoUnivocoRiscossione(singolaRevocaInput.getIdentificativoUnivocoRiscossione());
//				   singoloEsitoRevoca.setSingoloImportoRevocato(singolaRevocaInput.getSingoloImportoRevocato());
//				   listaSingoleRevoche.add(singoloEsitoRevoca);
				   //DA TOGLIERE
			   }
			   }
		   }
		   
		   //RECUPERO RT 	
		   ParametriRiceviRT parametriRiceviRT = parameters.getParametriRiceviRT();
		   Rt rt = preparaRtModel(parametriRiceviRT);
		   rt.setIdRR(idRRInserito);
		   rt.setIdRt(null);
		   //salvataggio RT
		   Long idRtInserito = rtManager.inserisci(rt);
		   
		   
		   //RECUPERO ER
		   List <CtEsitoRevoca> erList = parameters.getCtEsitoRevoca();
		   //SALVATAGGIO ER
		   for (Iterator iteratorOverER = erList.iterator(); iteratorOverER.hasNext();) {
			CtEsitoRevoca ctEsitoRevoca = (CtEsitoRevoca) iteratorOverER.next();
			Er er = prepareErModel(ctEsitoRevoca);
			er.setIdEr(null);
			Integer idErInserito = erManager.inserisci(er);
		}
		   
		   
		   String messaggio = "";
		   ResponseType responseType =new ResponseType();
		   ResultType resultType = new ResultType();
			resultType.setCodice(CodiciEsito.ESECUZIONE_OK.toString());
			resultType.setMessaggio(messaggio);
	
			responseType.setResult(resultType);
			CtDatiEsitoRevoca response = new CtDatiEsitoRevoca();
			response.setDatiSingolaRevoca(listaSingoleRevoche);
		
	        return response;
	    }
    
  private Pagamento preparaPagamentoPerAnnullo(CtRichiestaRevocaRequest parameters, CtDatiSingolaRevoca singolaRevoca,CtRichiestaRevoca ctRichiestaRevoca) {
	  Pagamento pagamentoDaAnnullare = new Pagamento();
	//  pagamentoDaAnnullare.setIdPagamento(idPagamento);
//	  pagamentoDaAnnullare.setDataInserimento(Timestamp.);
	  pagamentoDaAnnullare.setCausale(singolaRevoca.getCausaleRevoca());
	  pagamentoDaAnnullare.setImporto(ctRichiestaRevoca.getDatiRevoca().getImportoTotaleRevocato());
	 // pagamentoDaAnnullare.setNote(singolaRevoca.get););
	 // pagamentoDaAnnullare.setConsensoPrivacy(consensoPrivacy);
	//  pagamentoDaAnnullare.setDataInserimento(ctRichiestaRevoca.getDataOraMessaggioRevoca());
	//  pagamentoDaAnnullare.setDataScadenza(ctRichiestaRevoca.getDatiRevoca().get);
//	  pagamentoDaAnnullare.setIuv(ctRichiestaRevoca.getDatiRevoca().getIdentificativoUnivocoVersamento());
//	  pagamentoDaAnnullare.setAuxDigit(auxDigit);
//	  pagamentoDaAnnullare.setApplicationCode(applicationCode);
//	  pagamentoDaAnnullare.setCodicePagamentoRifEnte(codicePagamentoRifEnte);
//	  pagamentoDaAnnullare.setAnnoRiferimento(annoRiferimento);
//	  pagamentoDaAnnullare.setDataScadenza(dataScadenza);
	  pagamentoDaAnnullare.setIdStatoCorrente(10);
//	  pagamentoDaAnnullare.setNumeroRata(numeroRata);
//	  pagamentoDaAnnullare.setIdStatoCorrente(idStatoCorrente);
//	  pagamentoDaAnnullare.setNumeroRata(numeroRata);
//	  pagamentoDaAnnullare.setPagatore(pagatore);
	  pagamentoDaAnnullare.setPagamentoSpontaneo(false);
	  pagamentoDaAnnullare.setFlagPagamentoAutenticato(false);
	  pagamentoDaAnnullare.setUtenteUltimaModifica("SYSTEM");
	  
	  
	  
	  

		return pagamentoDaAnnullare;
	}

private Rr preparaRrModel(CtRichiestaRevoca richiestaRevocaInput) {
	   Rr rr = new Rr(); 
	   CtDatiRevoca datiRevoca = richiestaRevocaInput.getDatiRevoca();
	   CtIstitutoAttestante istitutoAtt = richiestaRevocaInput.getIstitutoAttestante();  
	   rr.setIdDominio(richiestaRevocaInput.getDominio().getIdentificativoDominio());
	   rr.setApplicationId("epay");
	   rr.setIdentificativoMessaggioRevoca(richiestaRevocaInput.getIdentificativoMessaggioRevoca());
	   rr.setDataOraMessaggioRevoca(rr.getDataOraMessaggioRevoca());   
	   rr.setCodiceIdentificativoUnivocoAttestante(istitutoAtt.getIdentificativoUnivocoMittente().getCodiceIdentificativoUnivoco());
	   rr.setDenominazioneIstitutoAttestante(istitutoAtt.getDenominazioneMittente());
	   rr.setImportoTotaleRevocato(datiRevoca.getImportoTotaleRevocato());
	   rr.setIuv(datiRevoca.getIdentificativoUnivocoVersamento()); 
	   rr.setCodiceContestoPagamento(datiRevoca.getCodiceContestoPagamento());
	//   rr.setTipoRevoca( new BigDecimal(1));   
	   
	   rr.setIdRr(null);
	   byte[] rrXml = new byte[256];
	   String s = XmlUtil.obj2Xml(richiestaRevocaInput);
	   rrXml = s.getBytes();
	   rr.setRrXml(rrXml);
	   return rr;
  }
  
  private DatiSingolaRevoca preparaSingolaRevocaModel(CtDatiSingolaRevoca singolaRevoca) {
	  DatiSingolaRevoca datiSingolaRevoca = new DatiSingolaRevoca();
	  datiSingolaRevoca.setCausaleRevoca(singolaRevoca.getCausaleRevoca());
	  datiSingolaRevoca.setDatiAggiuntiviRevoca(singolaRevoca.getDatiAggiuntiviRevoca());
	  datiSingolaRevoca.setIur(singolaRevoca.getIdentificativoUnivocoRiscossione());
	  datiSingolaRevoca.setSingoloImportoRevocato(singolaRevoca.getSingoloImportoRevocato());
	  return datiSingolaRevoca;
  }
  
  
  private Rt preparaRtModel(ParametriRiceviRT parametriRiceviRT) {
	  Rt rt = new Rt();
	  //rt.setDataoraMsgRicevuta(parametriRiceviRT.getDataOraMsgR);
	  //rt.setCodEsitoPagamento(parametriRiceviRT.getCodEsitoPagamento());
	  rt.setDescEsitoPagamento(parametriRiceviRT.getDescEsitoPagamento());
	  rt.setIdApplicazione(parametriRiceviRT.getApplicationId());
	  rt.setIdMsgRicevuta(parametriRiceviRT.getIdMsgRicevuta());
	  rt.setIdMsgRichiesta(parametriRiceviRT.getIdMsgRichiesta());
	  rt.setIdRegistro(new Long(152));
	  rt.setIdTransazione(parametriRiceviRT.getTransactionId());
	  rt.setIuv(parametriRiceviRT.getIuv());
//	  rt.setRicevutaPdf(parametriRiceviRT.getIdMsgRicevuta());
	  rt.setIdRt(null);
	  byte[] rtXml = new byte[256];
	  String s = XmlUtil.obj2Xml(parametriRiceviRT);
	  rtXml = s.getBytes();
	  rt.setRtXml(rtXml);
	  rt.setTipoFirma(parametriRiceviRT.getTipoFirma());
	  return rt;
  }
  
  private Er prepareErModel(CtEsitoRevoca ctEsitoRevoca) {
	  Er er = new Er();
	  er.setApplicationId("epay");
	  er.setCodiceContestoPagamento(ctEsitoRevoca.getDatiRevoca().getCodiceContestoPagamento());
	  er.setCodiceIdentificativoUnivocoAttestante(ctEsitoRevoca.getIstitutoAttestante().getIdentificativoUnivocoMittente().getCodiceIdentificativoUnivoco());
	  //er.setDataOraMessaggioEsito(dataOraMessaggioEsito);
	  er.setDenominazioneIstitutoAttestante(ctEsitoRevoca.getIstitutoAttestante().getDenominazioneMittente());
	  er.setIdentificativoDominio(ctEsitoRevoca.getDominio().getIdentificativoDominio());
	  er.setIdentificativoMessaggioEsito(ctEsitoRevoca.getIdentificativoMessaggioEsito());
	  er.setImportoTotaleRevocato(ctEsitoRevoca.getDatiRevoca().getImportoTotaleRevocato());
	  er.setInvioOkRispostaRevoca(true);
	  er.setIuv(ctEsitoRevoca.getDatiRevoca().getIdentificativoUnivocoVersamento());
	//  er.setRiferimentoDataRevoca(riferimentoDataRevoca);
	  er.setRiferimentoMessaggioRevoca(ctEsitoRevoca.getRiferimentoMessaggioRevoca());
	  byte[] erXml = new byte[256];
	  String s = XmlUtil.obj2Xml(ctEsitoRevoca);
	  erXml = s.getBytes();
	  er.setXmlEr(erXml);
	
	  return er;
  }
  
  
  
  private String formatDateTimestamp ( Date timestamp ) {
      DateFormat sdf = new SimpleDateFormat ( "yyyy-mm-dd hh:mm:ss[.fffffffff]" );
      return sdf.format ( timestamp );
  }

    private <T> ResultType execute ( String methodName, T input, Callable<CodiciEsito> callable ) {
        log.debugStart ( methodName );
        log.debug ( methodName, "Request\n : " + XmlUtil.obj2Xml ( input ) );

        ResultType result = new ResultType ();
        CodiciEsito ce;

        try {
            ce = callable.call ();
            log.debug ( methodName, "Response\n : " + XmlUtil.obj2Xml ( ce ) );

            result.setCodice ( ce.getCodice () );
            result.setMessaggio ( ce.getMessaggio ( maxErrorMessageWidth ) );

        } catch ( Exception e ) {
            log.error ( methodName, "Errore servizio:" );
            log.error ( methodName, e.getMessage () );

            ce = CodiciEsito.COOP_ERRORE_GENERICO;
            result.setCodice ( ce.getCodice () );
            result.setMessaggio ( ce.getMessaggio ( maxErrorMessageWidth,
                getExceptionMessage ( getRealException ( e ) ) ) );
        }

        log.debugEnd ( methodName );

        return result;
    }

    private Throwable getRealException ( Throwable e ) {
        while ( e.getCause () != null ) {
            e = e.getCause ();
        }

        return e;
    }

    private String getExceptionMessage ( Throwable e ) {
        if ( e.getMessage () != null ) {
            return e.getMessage ();
        } else {
            return e.getClass ().getSimpleName ();
        }
    }






}
