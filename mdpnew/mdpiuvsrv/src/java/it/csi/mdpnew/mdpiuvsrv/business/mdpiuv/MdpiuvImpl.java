/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpiuvsrv.business.mdpiuv;

import java.math.BigInteger;
import java.util.HashMap;

import it.csi.csi.wrapper.*;

import it.csi.mdpnew.mdpiuvsrv.dto.mdpiuv.*;
import it.csi.mdpnew.mdpiuvsrv.integration.mdpiuv.dao.interfacce.MdpDao;
import it.csi.mdpnew.mdpiuvsrv.interfacecsi.mdpiuv.*;
import it.csi.mdpnew.mdpiuvsrv.util.CreditorReference;
import it.csi.mdpnew.mdpiuvsrv.util.MdpiuvConstants;
import it.csi.mdpnew.mdpiuvsrv.util.ObjectStreamer;
import it.csi.mdpnew.mdpiuvsrv.util.Utility;
import it.csi.mdpnew.mdpiuvsrv.exception.mdpiuv.*;

import it.csi.coopdiag.api.*;
import it.csi.coopdiag.engine.utils.*;

import javax.sql.DataSource;
import javax.xml.soap.SOAPException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.*;
import org.springframework.remoting.soap.SoapFaultException;



public class MdpiuvImpl implements MdpiuvSrv{

	
	
	/// Implementazione operazioni esposte dal servizio
	
	MdpDao mdpDao;
	

	
  public String getDataSistema(it.csi.mdpnew.mdpiuvsrv.dto.mdpiuv.ProvaEntita p1)  
  				throws it.csi.csi.wrapper.CSIException, 
  					   it.csi.csi.wrapper.SystemException, 
  					   it.csi.csi.wrapper.UnrecoverableException,
  					   it.csi.mdpnew.mdpiuvsrv.exception.mdpiuv.MdpIuvSrvException

  {
     Logger logger = getLogger("ejb");
	 logger.debug("[MdpiuvImpl::getDataSistema] - START");
	 
	 it.csi.util.performance.StopWatch watcher= new it.csi.util.performance.StopWatch("mdpiuvsrv");
	 // inizio misurazione
	 watcher.start();
	 
	 String ris = "";
	 try {
		 
		 
		Utility utility = new Utility();
		
	    
		CreditorReference cr = new CreditorReference();
		logger.debug("[MdpiuvImpl::getDataSistema] creditorReference "+cr.getMappaValori());
		
		ris = mdpDao.dataOdierna();
	 
        logger.debug("[MdpiuvImpl::getDataSistema] - END "+ris );
     
     }
     
     catch(Throwable ex){
        if (CSIException.class.isAssignableFrom(ex.getClass())){
        	logger.error("[MdpiuvImpl::getDataSistema] - Errore CSI occorso durante l'esecuzione del metodo:"+ex, ex);
     		throw (CSIException)ex;
        }
        else{
			logger.error("[MdpiuvImpl::getDataSistema] - Errore imprevisto occorso durante l'esecuzione del metodo:"+ex, ex);
			throw new UnrecoverableException("Errore imprevisto occorso durante l'esecuzione del metodo:"+ex,ex);
		}        
     }
     finally{
       // fine misurazione
       watcher.stop();
	   watcher.dumpElapsed("MdpiuvImpl", "getDataSistema()", "invocazione servizio [mdpiuv]::[getDataSistema]", "(valore input omesso)");
     }
     return ris;
  }

  
  private DescrizioneApplicativoDTO verificaDatiDB(String idApplicazione) throws SOAPException
  {
	  Logger logger = getLogger("ejb");
	  logger.debug("[MdpiuvImpl::verificaDatiDB] - START");
	  
	  logger.debug(" il valore id_application----> "+idApplicazione);
	  if(StringUtils.isEmpty(idApplicazione) || idApplicazione.equals("")){
	  	 logger.error("Errore: "+MdpiuvConstants.ERRORE_ID_APPLICATION_IS_NECESSARY);
		 throw new SOAPException(MdpiuvConstants.ERRORE_ID_APPLICATION_IS_NECESSARY);
	  }
		
	  // verifico la presenza dell id applicativo
		
	  if(!mdpDao.isPresentIDApplication(idApplicazione)){
		  logger.error("Errore: "+MdpiuvConstants.ERRORE_ID_APPLICATION_NOT_EXIST);
		  throw new SOAPException(MdpiuvConstants.ERRORE_ID_APPLICATION_NOT_EXIST);
	  }
		
	  // verifico la corretta configurazione delle tavole enti, r_application_enti
		
	  DescrizioneApplicativoDTO descrizioneApplicativoDTO =null;
	
	  try {
		  descrizioneApplicativoDTO = mdpDao.isPresentIDEnte(idApplicazione);
	  } catch (Exception e) {
		  	logger.error("Errore Di accesso ai dati ", e);
		    logger.error("Errore: "+MdpiuvConstants.ERRORE_CONFIGURATION_APPLICATION);
			throw new SOAPException(MdpiuvConstants.ERRORE_CONFIGURATION_APPLICATION);
	  }
	  
	  	   	   
	  logger.debug("[MdpiuvImpl::verificaDatiDB] - END");
	  
	  return descrizioneApplicativoDTO;
  }
  
  
  private IuvAttributeDTO recoverySequence(DescrizioneApplicativoDTO descrizioneApplicativoDTO)throws SOAPException
  {
	    Logger log = getLogger("ejb");
	    log.debug("[MdpiuvImpl::recoverySequence] - START");
	    BigInteger progressivo = null;
	    // cerco la sequence per il giorno attuale e poi
		
		// e' presente una sequence per id nella data di oggi ?
		IuvAttributeDTO iuvAttributeDTO = null;
		try {
			//mdpDao.updateProva(descrizioneApplicativoDTO.getIdEnte());
			iuvAttributeDTO = mdpDao.getIuvCorrente(descrizioneApplicativoDTO.getIdEnte());
			
			if(null==iuvAttributeDTO){
				// vuol dire che non c'e' il record quindi
				// devo inserirlo
				log.error("[MdpiuvImpl::getSingleIUV] - Eccezione controllata, quindi vado in inserimento poiche' manca il record del giorno");
				mdpDao.insertNewDay(descrizioneApplicativoDTO.getIdEnte());
				iuvAttributeDTO = new IuvAttributeDTO();
				iuvAttributeDTO.setDataValidita(mdpDao.dataOdierna());
				iuvAttributeDTO.setIdEnte(descrizioneApplicativoDTO.getIdEnte());
				iuvAttributeDTO.setProgressivo(new BigInteger("1"));
				progressivo = iuvAttributeDTO.getProgressivo();
				
				// storicizzo la sequence precedente
				mdpDao.updateStoricizza(descrizioneApplicativoDTO.getIdEnte());
			
			}else{
			
				// reperito il numero sequence attuale eseguo update per incremento
				progressivo = iuvAttributeDTO.getProgressivo().add(new BigInteger("1"));
			
				// setto il progressivo successivo
				iuvAttributeDTO.setProgressivo(progressivo);
				
				mdpDao.updateIncremento(iuvAttributeDTO.getIdEnte(), 
						progressivo,
						iuvAttributeDTO.getDataValidita());
				
			}
			
			
			if(log.isDebugEnabled()){
				ObjectStreamer os = new ObjectStreamer();
				log.debug(" IuvAttributeDTO \n "+os.getObjectAsStringXml(iuvAttributeDTO));
			}
			
			
			
		} catch (MdpIuvSrvException me) {
           log.error(" Errore stack "+me.getMessage());
           me.printStackTrace();
		   log.error("Errore: "+MdpiuvConstants.ERRORE_IUV_ATTRIBUTE_ASSIGN);
		   throw new SOAPException(MdpiuvConstants.ERRORE_IUV_ATTRIBUTE_ASSIGN);

		}catch (Exception e) {
			 log.error("Errore: "+MdpiuvConstants.ERRORE_IUV_ATTRIBUTE_ASSIGN);
			 throw new SOAPException(MdpiuvConstants.ERRORE_IUV_ATTRIBUTE_ASSIGN);
		}
	  log.debug("[MdpiuvImpl::recoverySequence] - END");
	  
	  return iuvAttributeDTO;
  }
  
  /*
  [MdpiuvImpl::recoverySequence] - END>
  15:04:01,813 INFO  [STDOUT] <DEBUG> <mdpiuvsrv.ejb> < Progressivo generato ---> 904>
  15:04:01,813 INFO  [STDOUT] <DEBUG> <mdpiuvsrv.ejb> <[MdpiuvImpl::getCheckDigits] - START>
  15:04:01,813 INFO  [STDOUT] <DEBUG> <mdpiuvsrv.ejb> < il reference ---> 131410001Ak4800000904>
  15:04:01,813 INFO  [STDOUT] <DEBUG> <mdpiuvsrv.ejb> <aggancia valori -> 13141000110204800000904>
  */
  /*
  public static void main(String[] args) {
	System.out.println(" start ");
	
	MdpiuvImpl test = new MdpiuvImpl();
	final String  reference ="131410001Ak4800000904";
	try {
		System.out.println("CHK --> "+test.getCheckDigits(reference));
	} catch (SOAPException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	System.out.println(" END ");
}*/
  
  private String getCheckDigits(String reference)throws SOAPException
  {
	  Logger log = getLogger("ejb");
	  log.debug("[MdpiuvImpl::getCheckDigits] - START");
	  String ris = "";

	  log.debug ( "Calcolo checkdigit - reference " + reference);
	  
	  CreditorReference cr = new CreditorReference();
	  try {
		
		  log.debug(" il reference ---> "+reference);
		  int lunghezzaRef = reference.length();
		  
		  StringBuffer agganciaValori = new StringBuffer();
		 
		  for (int i = 0; i < lunghezzaRef; i++) {
			   // valore i-esimo della stringa reference
			   // e convertito con l'intero del creditorReference
			   agganciaValori.append(cr.getMappaValori().get(String.valueOf(reference.charAt(i))));
			 
		  }
		  
	      log.debug ( "Calcolo checkdigit - lunghezza " + lunghezzaRef);
	      
		  log.debug("Risultato conversione di reference su mappa valori - aggancia valori -> "+agganciaValori);
		  
		  // quota fissa da mettere in coda
		  BigInteger bi = new BigInteger(agganciaValori.toString()+"271500");
		  BigInteger[] array = bi.divideAndRemainder(new BigInteger("97"));
		  //System.out.println("compare to "+array[1].compareTo(new BigInteger("10")));

		  log.debug ( "Resto della divisione per 97" );
		  
		  BigInteger primo =  array[0];
		  BigInteger secondo =  array[1];
		  log.debug("PRIMO  "+primo);
		  log.debug("SECONDO "+secondo);
		  
		  
		  BigInteger sottrazione = new BigInteger("98");
		  sottrazione = sottrazione.subtract(secondo);
		  log.debug("SOTTRAZIONE (98-resto della divisione ossia SECONDO)"+sottrazione);
		  
		  if(sottrazione.compareTo(new BigInteger("10"))>=0){
		      log.debug ( "Sottrazione maggiore di 10" );
			  // maggiore di 10
			  //ris = array[1].toString();
			  ris = sottrazione.toString();
		  }else if(sottrazione.compareTo(new BigInteger("10"))==-1){
              log.debug ( "Sottrazione minore di 10" );
			  //minore di 10 aggiungo lo zero davanti
			  //ris = "0"+ array[1].toString();
			  ris = "0"+ sottrazione.toString();
		  }
		  
		  log.debug("CHECK DIGITS "+ris);
		  
	  } catch (Exception e) {
			
			log.error("Errore: "+MdpiuvConstants.ERRORE_CKDGTS_ASSIGN);
			throw new SOAPException(MdpiuvConstants.ERRORE_CKDGTS_ASSIGN);
	  }
	  
	  log.debug("[MdpiuvImpl::getCheckDigits] - END");
	  return ris;
  }
  
  /*
  public static void main(String[] args) {
	System.out.println("start");
	String reference ="101313000001000023456";
	
	 CreditorReference cr = new CreditorReference();
	 int lunghezzaRef = reference.length();
	 Integer contaPunteggio = new Integer(0);
	 
	 StringBuffer agganciaValori = new StringBuffer();
	 
	
	 System.out.println("lubghezza "+reference.length());
	 System.out.println("alla posizione "+reference.charAt(0));
	 String cd = "";
	 for (int i = 0; i < lunghezzaRef; i++) {
		  
		  System.out.println("Analizzo --> "+reference.charAt(i));
		  //contaPunteggio = contaPunteggio + cr.getMappaValori().get(reference.charAt(i));
		
		  System.out.println("punteggio --> "+ cr.getMappaValori().get(String.valueOf(reference.charAt(i))));
		  agganciaValori.append(cr.getMappaValori().get(String.valueOf(reference.charAt(i))));
		  
		  
	  }
	 
	  System.out.println("CONTA       "+agganciaValori);
	  BigInteger bi = new BigInteger(agganciaValori.toString()+"271500");
	  System.out.println("BIG INTEGER "+bi);
	  BigInteger[] array = bi.divideAndRemainder(new BigInteger("97"));
	  
	  System.out.println("divisione   "+array[0]);
	  System.out.println("resto       "+array[1]);
	  System.out.println("compare     "+array[1].compareTo(new BigInteger("10")));
	 
	  if(array[1].compareTo(new BigInteger("10"))==1){
		  // maggiore di 10
		  cd = array[1].toString();
	  }else{
		  //minore di 10 aggiungo lo zero davanti
		  cd = "0"+ array[1].toString();
	  }
	  System.out.println(" CD "+cd);
	
	System.out.println("fine");
}
  
  */
  
  //RF61 2013 1410 0010 0000 0006
  //RF43 1314 1000 1 000 0000 07
  
  public String getSingleIUV(String idApplicazione, String codicePagamento)	throws it.csi.csi.wrapper.CSIException, 
										   	   it.csi.csi.wrapper.SystemException, 
											   it.csi.csi.wrapper.UnrecoverableException,
											   it.csi.mdpnew.mdpiuvsrv.exception.mdpiuv.MdpIuvSrvException,
											   SOAPException

	{
		Logger log = getLogger("ejb");
		log.debug("[MdpiuvImpl::getSingleIUV] - START");
		String ris = new String();
		StringBuffer reference = new StringBuffer();
		
		
		String dettaglioPagamVerificato = verificaDettaglioPagamento(codicePagamento);
		
		log.debug("DETTAGLIO PAGAMENTO VERIFICATO "+dettaglioPagamVerificato.toUpperCase());
		
		// verifico la robustezza del dato id application
		DescrizioneApplicativoDTO descrizioneApplicativoDTO = verificaDatiDB(idApplicazione);
		
		log.debug(" Descrizone ente codice --> "+descrizioneApplicativoDTO.getIdEnte());
		
		// recupero e gestione della sequence
		
		//IuvAttributeDTO iuvAttributeDTO = recoverySequence(descrizioneApplicativoDTO);
		//log.debug(" Progressivo generato ---> "+iuvAttributeDTO.getProgressivo());
		//ris.append("RF");

		try {
			
			ris = makeIuv(descrizioneApplicativoDTO, dettaglioPagamVerificato);
		
		} catch (Exception e) {
			throw new MdpIuvSrvException(e);
		}
		
	    
		log.debug("[MdpiuvImpl::getSingleIUV] - END");
		return ris;
	}
  
	public String[] getMultIUV(String idApplicazione, Integer numeroOccorrenzeIUV, String codicePagamento)throws SOAPException,it.csi.csi.wrapper.CSIException, 
																		   it.csi.csi.wrapper.SystemException, 
																		   it.csi.csi.wrapper.UnrecoverableException,
																		   it.csi.mdpnew.mdpiuvsrv.exception.mdpiuv.MdpIuvSrvException

	{
		Logger log = getLogger("ejb");
		log.debug("[MdpiuvImpl::getMultIUV] - START");
		
	    log.debug(" RICHIESTE DI IUV --> "+numeroOccorrenzeIUV);
		if(null==numeroOccorrenzeIUV || numeroOccorrenzeIUV.intValue()<=0){
			
			log.error("Errore: "+MdpiuvConstants.ERRORE_NO_MULTI_IUV);
			throw new SOAPException(MdpiuvConstants.ERRORE_NO_MULTI_IUV);
		}
		
		if(null!=numeroOccorrenzeIUV && numeroOccorrenzeIUV.intValue()>100){
			log.error("Errore: "+MdpiuvConstants.ERRORE_MAX_MULTI_IUV);
			throw new SOAPException(MdpiuvConstants.ERRORE_MAX_MULTI_IUV);
		}
		
		String dettaglioPagamVerificato = verificaDettaglioPagamento(codicePagamento);
		
		log.debug("DETTAGLIO PAGAMENTO VERIFICATO "+dettaglioPagamVerificato);
		
		// verifico la robustezza del dato id application
		DescrizioneApplicativoDTO descrizioneApplicativoDTO = verificaDatiDB(idApplicazione);
		
		String[] arrayIuv = new String[numeroOccorrenzeIUV];
		                     		
		// ciclo per il numero di richieste
		for (int i = 0; i < numeroOccorrenzeIUV; i++) {
			
			String ris = new String();
			
			try {
				
				ris = makeIuv(descrizioneApplicativoDTO, dettaglioPagamVerificato);
			
			} catch (Exception e) {
				throw new MdpIuvSrvException(e);
			}
			
			arrayIuv[i] = ris;
		}
	
		log.debug("[MdpiuvImpl::getMultIUV] - END");
		
		return arrayIuv;
	}
	
	private String makeIuv(DescrizioneApplicativoDTO descrizioneApplicativoDTO,
						   String dettaglioPagamVerificato )throws Exception{
		Logger log = getLogger("ejb");
		log.debug("[MdpiuvImpl::makeIuv] - START");
		
		int numeroPadding = StringUtils.isEmpty(descrizioneApplicativoDTO.getCodiceSegregazione()) ? 8 : 6;
		
		String ris = new String("RF");
		
		IuvAttributeDTO iuvAttributeDTO = recoverySequence(descrizioneApplicativoDTO);
		
		StringBuffer reference = new StringBuffer(StringUtils.trimToEmpty(descrizioneApplicativoDTO.getCodiceSegregazione()));

		log.debug("REFERENCE (con codice segregazione) " + reference);
		
		// aggiungo data giuliana
		reference.append(Utility.getDataGiuliana(iuvAttributeDTO.getDataValidita()));
        log.debug("REFERENCE (con data giuliana) " + reference);
        
		// aggiungo id ente
		reference.append(descrizioneApplicativoDTO.getIdEnte());		
        log.debug("REFERENCE (con data idEnte) " + reference);
        
		// aggiungo codice dettaglio di pagamento
		reference.append(dettaglioPagamVerificato);
        log.debug("REFERENCE (con codice pagamento) " + reference);
		
		// aggiungo gli zeri nel progressivo		
		reference.append(Utility.mettiZero(iuvAttributeDTO.getProgressivo().toString(), numeroPadding));
        log.debug("REFERENCE (con progressivo paddato a " + numeroPadding + ") " + reference);

		String upperReference =  reference.toString().toUpperCase();
        log.debug("REFERENCE FINALE " + reference);
		
		// RF + CHKDGT + Reference
		ris = ris + (getCheckDigits(upperReference)) + upperReference;
        log.debug("RISULTATO (Check digit + reference uppercase) " + ris);
		
		log.debug("[MdpiuvImpl::makeIuv] - END");
		return ris;
		
	}
	
	/*
	 public static void main(String[] args) {
			System.out.println("start");
			String reg = "^[a-zA-Z0-9]+$";
			String dettaglioPagamento="wQQS_SADAFAFF";
			if(!dettaglioPagamento.matches(reg)){
				
				System.out.println("ERRORE ");
				
			}
			
			System.out.println("END");
	 }		
	*/
	// <iuvGroup>RF80131410001Ak4800000709</iuvGroup>
    // <iuvGroup>RF010131410001Ak4800000710</iuvGroup>

	
	private String verificaDettaglioPagamento(String dettaglioPagamento)throws SOAPException{
		Logger log = getLogger("ejb");
		log.debug("[MdpiuvImpl::verificaDettaglioPagamento] - START");
		
		if(null==dettaglioPagamento || 
		   dettaglioPagamento.trim().equals("")||
		   dettaglioPagamento.equalsIgnoreCase("null")) dettaglioPagamento="0000";
		
		if(dettaglioPagamento.length()>4){
			log.error("Errore: "+MdpiuvConstants.ERRORE_LUNGH_DETTAGLIO_PAGAM);
			throw new SOAPException(MdpiuvConstants.ERRORE_LUNGH_DETTAGLIO_PAGAM);
		}
		
		// regualar experssione 
		// 
		log.debug("Analizzo il dato "+dettaglioPagamento);
		 
		if(!dettaglioPagamento.matches("^[a-zA-Z0-9]+$")){
			log.error("Errore: "+MdpiuvConstants.ERRORE_CARAT_NO_DETTAGLIO_PAGAM);
			throw new SOAPException(MdpiuvConstants.ERRORE_CARAT_NO_DETTAGLIO_PAGAM);
		}
		
		log.debug("[MdpiuvImpl::verificaDettaglioPagamento] - END");
		return dettaglioPagamento;
	}
	/*
	public String getSingleIUV(String id,
							   String dettaglioPagamento)	throws it.csi.csi.wrapper.CSIException, 
		   it.csi.csi.wrapper.SystemException, 
		   it.csi.csi.wrapper.UnrecoverableException,
		   it.csi.mdpnew.mdpiuvsrv.exception.mdpiuv.MdpIuvSrvException,
		   SOAPException
	
	{
		Logger log = getLogger("ejb");
		log.debug("[MdpiuvImpl::getSingleIUV] - START");
		
		
		verificaDettaglioPagamento(dettaglioPagamento);
		
		StringBuffer ris = new StringBuffer();
		StringBuffer reference = new StringBuffer();
		
		// verifico la robustezza del dato id application
		DescrizioneApplicativoDTO descrizioneApplicativoDTO = verificaDatiDB(id);
		
		log.debug(" Descrizone ente codice --> "+descrizioneApplicativoDTO.getIdEnte());
		
		// recupero e gestione della sequence
		
		IuvAttributeDTO iuvAttributeDTO = recoverySequence(descrizioneApplicativoDTO);
		log.debug(" Progressivo generato ---> "+iuvAttributeDTO.getProgressivo());
		ris.append("RF");
		
		
		// aggiungo data giuliana
		reference.append(Utility.getDataGiuliana(iuvAttributeDTO.getDataValidita()));
		
		// aggiungo id ente
		reference.append(descrizioneApplicativoDTO.getIdEnte());
		// aggiungo gli zeri nel progressivo
		
		reference.append(Utility.mettiZero(iuvAttributeDTO.getProgressivo().toString(), 9));
		
		
		// RF + CHKDGT + Reference
		ris.append(getCheckDigits(reference.toString()))
		.append(reference);
		
		
		log.debug("[MdpiuvImpl::getSingleIUV] - END");
		return ris.toString();
	}
	
	public String[] getMultIUV(String id, 
							   Integer numeroRichiesto,
							   String dettaglioPagamento)throws SOAPException,it.csi.csi.wrapper.CSIException, 
									   it.csi.csi.wrapper.SystemException, 
									   it.csi.csi.wrapper.UnrecoverableException,
									   it.csi.mdpnew.mdpiuvsrv.exception.mdpiuv.MdpIuvSrvException
	
	{
		Logger log = getLogger("ejb");
		log.debug("[MdpiuvImpl::getMultIUV] - START");
		
		
		
		log.debug(" RICHIESTE DI IUV --> "+numeroRichiesto);
		if(null==numeroRichiesto || numeroRichiesto.intValue()<=0){
		
			log.error("Errore: "+MdpiuvConstants.ERRORE_NO_MULTI_IUV);
			throw new SOAPException(MdpiuvConstants.ERRORE_NO_MULTI_IUV);
		}
		
		if(null!=numeroRichiesto && numeroRichiesto.intValue()>100){
			log.error("Errore: "+MdpiuvConstants.ERRORE_MAX_MULTI_IUV);
			throw new SOAPException(MdpiuvConstants.ERRORE_MAX_MULTI_IUV);
		}
		
		// verifico il dettaglio del pagamento
		verificaDettaglioPagamento(dettaglioPagamento);
		
		// verifico la robustezza del dato id application
		DescrizioneApplicativoDTO descrizioneApplicativoDTO = verificaDatiDB(id);
		
		String[] arrayIuv = new String[numeroRichiesto];
		
		// ciclo per il numero di richieste
		for (int i = 0; i < numeroRichiesto; i++) {
		
		StringBuffer ris = new StringBuffer();
		StringBuffer reference = new StringBuffer();
		
		IuvAttributeDTO iuvAttributeDTO = recoverySequence(descrizioneApplicativoDTO);
		log.debug(" Progressivo generato ---> "+iuvAttributeDTO.getProgressivo());
		
		ris.append("RF");
		
		// aggiungo data giuliana
		reference.append(Utility.getDataGiuliana(iuvAttributeDTO.getDataValidita()));
		
		// aggiungo id ente
		reference.append(descrizioneApplicativoDTO.getIdEnte());
		// aggiungo gli zeri nel progressivo
		reference.append(Utility.mettiZero(iuvAttributeDTO.getProgressivo().toString(), 9));
		
		// RF + CHKDGT + Reference
		ris.append(getCheckDigits(reference.toString()))
		.append(reference);
		
		arrayIuv[i] = ris.toString();
		}
		
		log.debug("[MdpiuvImpl::getMultIUV] - END");
		
		return arrayIuv;
	}
    */
	
	/// dichiarazione del self checker (utilizzato in monitoraggio e diagnostica)
	String _codS = "mdp"; // e' corretto che sia il codice prodotto?
	String _codR = "mdpiuv";
	String [] _suppS = new String[]{
	
	};
	String [] _suppR = new String[]{
	
	};
	
	DefaultSelfChecker schk = new DefaultSelfChecker(_codS, _codR,
					getLogger(null).getName(), _suppS, _suppR, "/checked_resources_mdpiuv.xml");
	
	
	
	public boolean testResources() throws  it.csi.csi.wrapper.CSIException{
		Logger logger = getLogger(null);
		logger.debug("[MdpiuvImpl::testResources()] BEGIN");
		InvocationNode in = new InvocationNode();
		try {					
			logger.debug("[MdpiuvImpl::testResources()] END");
			return schk.testResources();
		} catch (CSIException ex) {
			logger
					.error("[MdpiuvImpl::testResources()] : si e' verificato un errore  "
							+ ex);
			throw ex;
		}
	}
	
	
	public it.csi.coopdiag.api.InvocationNode selfCheck( it.csi.coopdiag.api.CalledResource[] alreadyChecked ) throws it.csi.csi.wrapper.CSIException{
		Logger logger = getLogger(null);
	 	logger.debug("[MdpiuvImpl::selfCheck] - BEGIN");
		InvocationNode in = new InvocationNode();
		try {
			return schk.selfCheck(alreadyChecked);
		} catch (CSIException ex) {
			logger.error("[MdpiuvImpl::selfCheck()] si e' verificato un errore  "
					+ ex);
		}
		logger.debug("[MdpiuvImpl::selfCheck] - END");
		// restituisco l?invocation node
		return in;
	}
	
	public boolean hasSelfCheck() throws it.csi.csi.wrapper.CSIException{
		return true;
	}
	
	
	/// inizializzazione
	public void init(Object initOptions){
	
	}
	
	protected Logger getLogger(String subsystem){
	  if (subsystem!=null)
	  	return Logger.getLogger(MdpiuvConstants.LOGGER_PREFIX+"."+subsystem);
	  else
	    return Logger.getLogger(MdpiuvConstants.LOGGER_PREFIX);
	}


	public MdpDao getMdpDao() {
		return mdpDao;
	}


	public void setMdpDao(MdpDao mdpDao) {
		this.mdpDao = mdpDao;
	}
	
	
}
	
