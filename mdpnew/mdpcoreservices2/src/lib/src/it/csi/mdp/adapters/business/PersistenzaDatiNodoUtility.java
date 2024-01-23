/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.adapters.business;

import it.csi.mdp.core.business.dto.Applicationcustomfields;
import it.csi.mdp.core.business.dto.ER;
import it.csi.mdp.core.business.dto.GiornaleEvento;
import it.csi.mdp.core.business.dto.IbanEnteApplication;
import it.csi.mdp.core.business.dto.InformativePSP;
import it.csi.mdp.core.business.dto.NodoErrore;
import it.csi.mdp.core.business.dto.RPT;
import it.csi.mdp.core.business.dto.RR;
import it.csi.mdp.core.business.dto.RT;
import it.csi.mdp.core.business.dto.StatiRPTEnum;
import it.csi.mdp.core.business.exceptions.DaoException;
import it.csi.mdp.core.business.paymentmanager.Payment;
import it.csi.mdp.core.business.paymentmanager.PaymentHome;
import it.csi.mdp.core.interfacecxf.MissingParameterException;
import it.csi.mdp.generatedvo.pagamenti.CtRicevutaTelematica;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.datatype.XMLGregorianCalendar;

public class PersistenzaDatiNodoUtility {
	
    /**
     * Recupera lista cutom fields
     * @param applicationId
     * @param fieldName
     * @throws CreateException 
     * @throws NamingException 
     * @throws RemoteException 
     * @throws MissingParameterException 
     * @throws DaoException 
     */
    public static List<Applicationcustomfields> recuperaCustomFields(String applicationId, String fieldName) throws RemoteException, NamingException, CreateException, DaoException, MissingParameterException{
        Payment p = reperisciPaymentInterface ();
        return p.recuperaApplicationCustomFields ( applicationId, fieldName );
    }
	
    public static List<Applicationcustomfields> recuperaCustomFieldDecoded(String applicationId, String fieldName) throws RemoteException, NamingException, CreateException, DaoException, MissingParameterException{
        Payment p = reperisciPaymentInterface ();
        return p.recuperaApplicationCustomFieldDecoded ( applicationId, fieldName );
    }
    
    public static String recuperaApplicationIdFromIuv(String iuv)  throws RemoteException, NamingException, CreateException, DaoException, MissingParameterException{
        Payment p = reperisciPaymentInterface ();
        return p.recuperaApplicationIdFromIuv ( iuv );
    }
    
    /**
     * Registra la RR ricevuta
     * @param RR
     * @throws CreateException 
     * @throws NamingException 
     * @throws RemoteException 
     * @throws MissingParameterException 
     * @throws DaoException 
     */
    public static Integer registraRR(RR rr) throws RemoteException, NamingException, CreateException, DaoException, MissingParameterException {
        Payment p = reperisciPaymentInterface();
        Integer lastKey = p.registraRichiestaRevoca ( rr );
        return lastKey;
    }
    
    /**
     * Registra id_RR su RT
     * @param iuv
     * @param idRR
     * @throws CreateException 
     * @throws NamingException 
     * @throws RemoteException 
     * @throws MissingParameterException 
     * @throws DaoException 
     */
    public static void updateIdRrByIuv(String iuv, Integer idRR)  throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException{
        Payment p = reperisciPaymentInterface();
        p.updateIdRrByIuv ( iuv, idRR );
    }
    
    /**
     * Recupera applicationDetails
     * @param applicationid
     * @param gatewayid
     * @param paymentmodeid
     * @throws CreateException 
     * @throws NamingException 
     * @throws RemoteException 
     * @throws MissingParameterException 
     * @throws DaoException 
     */
    public static void getApplicationDetails(String applicationid, String gatewayid, String paymentmodeid) throws RemoteException, NamingException, CreateException, DaoException, MissingParameterException {
        Payment p = reperisciPaymentInterface();
        p.getApplicationDetail ( applicationid, gatewayid, paymentmodeid );
    }
    
    /**
     * Registra la RR ricevuta
     * @param rr
     * @param TBD
     * @throws CreateException 
     * @throws NamingException 
     * @throws RemoteException 
     * @throws MissingParameterException 
     * @throws DaoException 
     */
    public static void registraER(ER er) throws RemoteException, NamingException, CreateException, DaoException, MissingParameterException {
        Payment p = reperisciPaymentInterface();
        p.registraEsitoRevoca ( er );
    }

	/**
	 * Registra la RPT inviata
	 * @param rpt
	 * @param applicationId
	 * @param transactionId
	 * @param autenticazioneSoggetto
	 * @param identificativoCanale
	 * @param idPsp
	 * @param identificativoIntermediarioPSP
	 * @param rptXml
	 * @param idStazionePa 
	 * @param idIntermediarioPa 
	 * @param idDominio 
	 * @param codiceContestoPagamento 
	 * @throws CreateException 
	 * @throws NamingException 
	 * @throws RemoteException 
	 * @throws MissingParameterException 
	 * @throws DaoException 
	 */
	public static void registraRPT(String applicationId, String transactionId, String autenticazioneSoggetto, String identificativoCanale, String idPsp, String identificativoIntermediarioPSP, String rptXml, String idDominio, String idIntermediarioPa, String idStazionePa, String iuv, String esito, String codiceContestoPagamento, boolean daInviare) throws RemoteException, NamingException, CreateException, DaoException, MissingParameterException {
		
		RPT richiestaDaRegistrare = new RPT();
		richiestaDaRegistrare.setApplicationId(applicationId);
		richiestaDaRegistrare.setAuthSoggetto(autenticazioneSoggetto);
		richiestaDaRegistrare.setIdCanale(identificativoCanale);
		richiestaDaRegistrare.setIdIntermPsp(identificativoIntermediarioPSP);
		richiestaDaRegistrare.setIdMsgRichiesta(transactionId);
		richiestaDaRegistrare.setIdPsp(idPsp);
		richiestaDaRegistrare.setDataMsgRichiesta(new Timestamp(System.currentTimeMillis()));
		richiestaDaRegistrare.setRptXml(rptXml);
		richiestaDaRegistrare.setTransactionId(transactionId);
		richiestaDaRegistrare.setIdentificativoDominio(idDominio);
		richiestaDaRegistrare.setIdentificativoIntermediarioPa(idIntermediarioPa);
		richiestaDaRegistrare.setIdentificativoStazioneIntermediarioPa(idStazionePa);
		richiestaDaRegistrare.setIuv(iuv);
		richiestaDaRegistrare.setIdStatiRpt("OK".equals(esito) ? StatiRPTEnum.ATTESA_RT.id() : StatiRPTEnum.RIFIUTATA.id());
		richiestaDaRegistrare.setCodiceContestoPagamento(codiceContestoPagamento);
		richiestaDaRegistrare.setDaInviare(daInviare);
		richiestaDaRegistrare.setDataInvio(daInviare ? null : new Timestamp(System.currentTimeMillis()));
		
		Payment p = reperisciPaymentInterface();
		p.registraRichiestaTelematicaPagamento(richiestaDaRegistrare);
	}
	
	/**
	 * Aggiorna la RPT inviata
	 * @param rpt
	 * @throws CreateException 
	 * @throws NamingException 
	 * @throws RemoteException 
	 * @throws MissingParameterException 
	 * @throws DaoException 
	 */
	public static void aggiornaRPT(RPT richiestaDaAggiornare) throws RemoteException, NamingException, CreateException, DaoException, MissingParameterException {
		
		Payment p = reperisciPaymentInterface();
		p.aggiornaRPT(richiestaDaAggiornare);
	}

	/**
	 * Registra un evento nel giornale degli eventi
	 * @param applicationId
	 * @param transactionId
	 * @param gatewayId
	 * @param esito
	 * @param tipoEvento
	 * @param iuv
	 * @param tipoVersamento
	 * @param identificativoStazioneIntermediarioPA
	 * @param identificativoDominio
	 * @param idPsp
	 * @param idIntermediarioPsp
	 * @throws RemoteException 
	 * @throws CreateException 
	 * @throws NamingException 
	 * @throws MissingParameterException 
	 * @throws DaoException 
	 */
	public static void registraEventoGiornale(String applicationId, String transactionId, String gatewayId, String esito, String tipoEvento, String iuv, String tipoVersamento, String identificativoStazioneIntermediarioPA, String identificativoDominio, String idPsp, String idIntermediarioPsp, String parametriSpecificiInterfaccia, String componente, String codiceContestoPagamento) throws RemoteException, NamingException, CreateException, DaoException, MissingParameterException {
		
		GiornaleEvento gde = new GiornaleEvento();
		gde.setApplicationId(applicationId);
		gde.setCategoriaevento("INTERFACCIA");// DUE OPZIONI: INTERNO / INFERFACCIA
		gde.setCodiceContesto(codiceContestoPagamento);
		gde.setComponente(componente);
		gde.setEsito(esito);
		gde.setIdentificativodominio(identificativoDominio);
		gde.setIdentificativoerogatore(gatewayId);
		gde.setIdentificativofruitore(applicationId);
		gde.setIdPsp(idPsp);
		gde.setIdentificativostazioneintermediariopa(identificativoStazioneIntermediarioPA);
		gde.setIuv(iuv);
		gde.setIdIntPsp(idIntermediarioPsp);
		gde.setParametrispecificiinterfaccia(substring(parametriSpecificiInterfaccia, 0 , 512));
		gde.setTipoevento(tipoEvento);
		gde.setSottotipoevento(tipoEvento);
		gde.setTipoversamento(tipoVersamento);
		gde.setDataoraevento(new Timestamp(System.currentTimeMillis()));
		gde.setTransactionId(transactionId);
	
		Payment p = reperisciPaymentInterface();
		p.registraEventoGiornale(gde);
		
	}
	
	public static void registraErroriNodo(List<NodoErrore> elencoErrori) throws RemoteException, NamingException, CreateException, DaoException, MissingParameterException {
		
		Payment p = reperisciPaymentInterface();
		p.registraErroriNodo(elencoErrori);
		
	}
	
	/**
	 * Registra una RT sul DB
	 * @param rtXml
	 * @param t
	 * @param tipoFirma
	 * @param identificativoMessaggioRicevuta
	 * @throws RemoteException 
	 * @throws CreateException 
	 * @throws NamingException 
	 * @throws MissingParameterException 
	 * @throws DaoException 
	 */
	public static void registraRT(byte[] rtXml, String applicationId, String transactionId, String tipoFirma, CtRicevutaTelematica rt, int codiceEsitoPagamento, String statoInvio, String sorgenteInvio, Timestamp dataOraInserimento) throws RemoteException, NamingException, CreateException, DaoException, MissingParameterException {
		RT ricevutaDaRegistrare = new RT();
		ricevutaDaRegistrare.setApplicationId(applicationId);
		ricevutaDaRegistrare.setIdMsgRicevuta(rt == null ? " - " : rt.getIdentificativoMessaggioRicevuta());
		ricevutaDaRegistrare.setRtData(rtXml);
		ricevutaDaRegistrare.setTipoFirma(tipoFirma);
		ricevutaDaRegistrare.setTransactionId(transactionId);
		ricevutaDaRegistrare.setIuv(rt == null ? " - " : rt.getDatiPagamento().getIdentificativoUnivocoVersamento());
		ricevutaDaRegistrare.setIdMsgRichiesta(rt == null ? " - " : rt.getRiferimentoMessaggioRichiesta());
		ricevutaDaRegistrare.setIdEsitoPagamento(codiceEsitoPagamento);
		ricevutaDaRegistrare.setDataMsgRicevuta(rt == null ? null : new Timestamp(rt.getDataOraMessaggioRicevuta().toGregorianCalendar().getTime().getTime()));
        ricevutaDaRegistrare.setDataInvioFruitore ( "OK".equals ( statoInvio ) ? dataOraInserimento : null );
		ricevutaDaRegistrare.setStatoInvioFruitore(statoInvio);
		ricevutaDaRegistrare.setSogenteInvioFruitore(sorgenteInvio);
		ricevutaDaRegistrare.setInsertDate(dataOraInserimento);
		
		if (rt != null && rt.getDatiPagamento().getDatiSingoloPagamento()!= null && rt.getDatiPagamento().getDatiSingoloPagamento().size() > 0 && rt.getDatiPagamento().getDatiSingoloPagamento().get(0).getCommissioniApplicatePSP()!= null)
				ricevutaDaRegistrare.setCommissioniApplicatePSP(rt.getDatiPagamento().getDatiSingoloPagamento().get(0).getCommissioniApplicatePSP().doubleValue());
		Payment p = reperisciPaymentInterface();
		p.registraRicevutaTelematica(ricevutaDaRegistrare);
	}
	
    /**
     * Aggiorna una RT sul DB
     * @param rtXml
     * @param t
     * @param tipoFirma
     * @param identificativoMessaggioRicevuta
     * @throws RemoteException 
     * @throws CreateException 
     * @throws NamingException 
     * @throws MissingParameterException 
     * @throws DaoException 
     */
    public static void aggiornaRT(byte[] rtXml, String applicationId, String transactionId, String tipoFirma, CtRicevutaTelematica rt, int codiceEsitoPagamento, String statoInvio, String sorgenteInvio, Timestamp dataOraInserimento) throws RemoteException, NamingException, CreateException, DaoException, MissingParameterException {
        RT ricevutaDaAggiornare = new RT();
        ricevutaDaAggiornare.setApplicationId(applicationId);
        ricevutaDaAggiornare.setIdMsgRicevuta(rt == null ? " - " : rt.getIdentificativoMessaggioRicevuta());
        ricevutaDaAggiornare.setRtData(rtXml);
        ricevutaDaAggiornare.setTipoFirma(tipoFirma);
        ricevutaDaAggiornare.setTransactionId(transactionId);
        ricevutaDaAggiornare.setIuv(rt == null ? " - " : rt.getDatiPagamento().getIdentificativoUnivocoVersamento());
        ricevutaDaAggiornare.setIdMsgRichiesta(rt == null ? " - " : rt.getRiferimentoMessaggioRichiesta());
        ricevutaDaAggiornare.setIdEsitoPagamento(codiceEsitoPagamento);
        ricevutaDaAggiornare.setDataInvioFruitore(rt == null ? null : new Timestamp(new Date().getTime ()));
        ricevutaDaAggiornare.setStatoInvioFruitore(statoInvio);
        
        ricevutaDaAggiornare.setSogenteInvioFruitore(sorgenteInvio);
        ricevutaDaAggiornare.setInsertDate(dataOraInserimento);
        
        if (rt != null && rt.getDatiPagamento().getDatiSingoloPagamento()!= null && rt.getDatiPagamento().getDatiSingoloPagamento().size() > 0 && rt.getDatiPagamento().getDatiSingoloPagamento().get(0).getCommissioniApplicatePSP()!= null)
            ricevutaDaAggiornare.setCommissioniApplicatePSP(rt.getDatiPagamento().getDatiSingoloPagamento().get(0).getCommissioniApplicatePSP().doubleValue());
        Payment p = reperisciPaymentInterface();
        p.aggiornaRicevutaTelematica(ricevutaDaAggiornare);
        
    }	

	public static InformativePSP recuperaInformativaPSP(String paymentmodeId) throws RemoteException, NamingException, CreateException, NumberFormatException, DaoException, MissingParameterException {
		
		Payment p = reperisciPaymentInterface();
		return p.recuperaInformativaPerId(Integer.parseInt(paymentmodeId));
	}
	
	public static InformativePSP recuperaInformativaPerIdentificativoPSP(String identificativoPSP) throws RemoteException, NamingException, CreateException, NumberFormatException, DaoException, MissingParameterException {
		
		Payment p = reperisciPaymentInterface();
		return p.recuperaInformativaPerIdentificativoPSP(identificativoPSP);
	}

	public static void aggiornaIdSessionTransazione(String idTransazione, String idSession) throws RemoteException, NamingException, CreateException, DaoException, MissingParameterException {

		Payment p = reperisciPaymentInterface();
		p.setIdSessionTransazione(idTransazione, idSession);
	}
	
	public static void registraTransazioneIuv(String idTransazione, String iuv) throws RemoteException, NamingException, CreateException, DaoException, MissingParameterException {
		
		Payment p = reperisciPaymentInterface();
		p.registraTransazioneIuv(idTransazione, iuv);
	}
	
	public static Timestamp getSqlTimestampFromCalendar(Calendar original) {
		
		if (original == null)
			return null;
		
		return new Timestamp(original.getTimeInMillis());
	}
	

	public static Timestamp getSqlTimestampFromXmlGregorianCalendar(XMLGregorianCalendar original) {
		
		if (original == null)
			return null;
		
		return new Timestamp(original.toGregorianCalendar().getTimeInMillis());
	}
	
	public static Date getDateFromXmlGregorianCalendar(XMLGregorianCalendar original) {
		
		if (original == null)
			return null;
		
		return new Date(original.toGregorianCalendar().getTimeInMillis());
	}
	

	public static String substring(String inputString, int start, int forChars) {
		if (inputString == null) return null;
		
		int end = (start + forChars) > inputString.length() ? inputString.length() : (start + forChars);
		return inputString.substring(start, end);
	}




	/**
	 * Reperisce l'interfaccia al servizio core che fornisce l'accesso al DB
	 * @return
	 * @throws NamingException
	 * @throws CreateException
	 * @throws RemoteException
	 */
	public static Payment reperisciPaymentInterface() throws NamingException, CreateException, RemoteException {
		InitialContext ctx = new InitialContext();
		Object o = ctx.lookup("java:global/mdpcoreservicessrv2/mdpcorecontext-business/PaymentManager!it.csi.mdp.core.business.paymentmanager.PaymentHome");
		PaymentHome home = (PaymentHome) o;
		Payment p = home.create();
		return p;
	}
	
	/**
	 * Recupera l'iban parametrizzato per psp e tipo versamento di una specifica applicazione fruitrice
	 * @param t
	 * @param infoPSP
	 * @return
	 * @throws NamingException
	 * @throws CreateException
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 */
	public static String recuperaIbanParametricoPerPsp(String appId, String identificativoPSP, String tipoVersamento) throws NamingException, CreateException, RemoteException, DaoException, MissingParameterException {
		Payment p = PersistenzaDatiNodoUtility.reperisciPaymentInterface();
		IbanEnteApplication filtro = new IbanEnteApplication();
		filtro.setApplicationId(appId);
		filtro.setIdentificativopsp(identificativoPSP);
		filtro.setTipoversamento(tipoVersamento);
		IbanEnteApplication iban = p.recuperaIbanEnteApplication(filtro);
		String ibanToPass = null;
		if (iban != null) {
			ibanToPass = iban.getIban();
		}
		return ibanToPass;
	}



}
