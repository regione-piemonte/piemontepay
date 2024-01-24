/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.business;

import it.csi.mdp.adapters.business.CoreUtilities;
import it.csi.mdp.mdpboweb.business.ws.EstraiFlussiDaServizio;
import it.csi.mdp.mdpboweb.business.ws.TipoVersamentoDTO;
import it.csi.mdp.mdpboweb.business.ws.CodiciEsitoPagamentoDTO;
import it.csi.mdp.mdpboweb.business.ws.Credentials;
import it.csi.mdp.mdpboweb.business.ws.DelRelEnteApplication;
import it.csi.mdp.mdpboweb.business.ws.DelRelEnteApplicationResponse;
import it.csi.mdp.mdpboweb.business.ws.DeleteEnte;
import it.csi.mdp.mdpboweb.business.ws.EntiDTO;
import it.csi.mdp.mdpboweb.business.ws.FindEntiAll;
import it.csi.mdp.mdpboweb.business.ws.FlussoRiversamentoDTO;
import it.csi.mdp.mdpboweb.business.ws.FlussoSingoloPagamentoDTO;
import it.csi.mdp.mdpboweb.business.ws.GetCodiciEsitoPagamentoAll;
import it.csi.mdp.mdpboweb.business.ws.GetEntiByApplicationId;
import it.csi.mdp.mdpboweb.business.ws.GetEntiByParam;
import it.csi.mdp.mdpboweb.business.ws.GetFlussoRiversamentoByParam;
import it.csi.mdp.mdpboweb.business.ws.GetFlussoSingoloPagamentoByParam;
import it.csi.mdp.mdpboweb.business.ws.GetInformativePSPByParam;
import it.csi.mdp.mdpboweb.business.ws.GetListaTipoversamento;
import it.csi.mdp.mdpboweb.business.ws.GetRPTByParam;
import it.csi.mdp.mdpboweb.business.ws.GetRTByParam;
import it.csi.mdp.mdpboweb.business.ws.GetStatiRptAll;
import it.csi.mdp.mdpboweb.business.ws.InformativePSPDTO;
import it.csi.mdp.mdpboweb.business.ws.InsRelEnteApplication;
import it.csi.mdp.mdpboweb.business.ws.InsRelEnteApplicationResponse;
import it.csi.mdp.mdpboweb.business.ws.InsertEnte;
import it.csi.mdp.mdpboweb.business.ws.MdpBoServicesCxfServiceSoapBindingStub;
import it.csi.mdp.mdpboweb.business.ws.Rptdto;
import it.csi.mdp.mdpboweb.business.ws.Rtdto;
import it.csi.mdp.mdpboweb.business.ws.StatiRptDTO;
import it.csi.mdp.mdpboweb.business.ws.UpdateEnte;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.SingoloStatoRPT;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.SingoloStatoVersamento;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.StatoRPTRisposta;
//import it.csi.mdp.mdpboweb.dto.SingoloStatoRPT;
//import it.csi.mdp.mdpboweb.dto.SingoloStatoVersamento;
//import it.csi.mdp.mdpboweb.dto.StatoRPTRisposta;
import it.csi.mdp.mdpboweb.util.UtilDate;
import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.NodoChiediStatoRPT;
import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.NodoChiediStatoRPTRisposta;
import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.PagamentiTelematiciRPT;
import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.TipoStoricoRPT;
import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.TipoStoricoVersamento;
//import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.*;
//import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.*;


import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.axis.AxisFault;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class MDPNODONAZIONALEServiceHelper extends MDPBaseServiceHelper{

	public MDPNODONAZIONALEServiceHelper() throws Exception {
		log.info("[MDPServiceHelper::MDPServiceHelper]  BEGIN ");
		try {
			Properties props = new Properties();
			InputStream is = getClass().getResourceAsStream(MDPBO_SERVICE_RESOURCE);
			props.load(is);
			MDPBOWS_ENDPOINT = props.getProperty("service.endpoint");
		} catch (Exception ex) {
			log.error("[MDPServiceHelper::MDPServiceHelper]  ", ex);
			ex.printStackTrace();
			throw ex;
		} finally {
			log.info("[MDPServiceHelper::MDPServiceHelper]  END ");
		}
	}
	
	/**
	 * 
	 * @param userCred
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public CodiciEsitoPagamentoDTO[] getCodiciEsitoPagamentoAll(Credentials userCred) throws AxisFault, MalformedURLException,RemoteException {
		log.info("[MDPServiceHelper::getCodiciEsitoPagamentoAll]  BEGIN ");
		MdpBoServicesCxfServiceSoapBindingStub stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);

		GetCodiciEsitoPagamentoAll parameters = new GetCodiciEsitoPagamentoAll(userCred);	
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		CodiciEsitoPagamentoDTO[] lista = stub.getCodiciEsitoPagamentoAll(parameters);

		log.info("[MDPServiceHelper::getCodiciEsitoPagamentoAll]  END ");
		return lista;
	}
	
	/**
	 * 
	 * @param userCred
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public StatiRptDTO[] getStatiRptAll(Credentials userCred) throws AxisFault, MalformedURLException,RemoteException {
		log.info("[MDPServiceHelper::getStatiRptAll]  BEGIN ");
		MdpBoServicesCxfServiceSoapBindingStub stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);

		GetStatiRptAll parameters = new GetStatiRptAll(userCred);
		
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		StatiRptDTO[] lista = stub.getStatiRptAll(parameters);

		log.info("[MDPServiceHelper::getStatiRptAll]  END ");
		return lista;
	}

	/**
	 * 
	 * @param auth
	 * @param id
	 * @param applicationId
	 * @param transactionId
	 * @param lastUpdate
	 * @param iuv
	 * @param idStatiRpt
	 * @param idMsgRichiesta
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public Rptdto[] getRPTByParam(Credentials auth, Integer id, String applicationId, String transactionId, String lastUpdateDa,String lastUpdateA,String insertDateDa,String insertDateA, String iuv, String idStatiRpt, String idMsgRichiesta) throws AxisFault,MalformedURLException, RemoteException {
		log.info("[MDPServiceHelper::GetRPTByParam]  BEGIN ");

		MdpBoServicesCxfServiceSoapBindingStub stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);
		GetRPTByParam parameters = new GetRPTByParam(auth, id, applicationId, 
				transactionId,
				UtilDate.getConvertStringToCalendar(lastUpdateDa),
				UtilDate.getConvertStringToCalendar(lastUpdateA),
				UtilDate.getConvertStringToCalendar(insertDateDa),
				UtilDate.getConvertStringToCalendar(insertDateA),
				iuv, 
				idStatiRpt,
				idMsgRichiesta);

		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		Rptdto[] lista = stub.getRPTByParam(parameters);

		log.info("[MDPServiceHelper::GetRPTByParam]  END ");
		return lista;
	}
	

	/**
	 * 
	 * @param auth
	 * @param id
	 * @param applicationId
	 * @param transactionId
	 * @param lastUpdate
	 * @param iuv
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public Rtdto[] getRTByParam(Credentials auth, Integer id, String applicationId, String transactionId, 
			String lastUpdateDa,String lastUpdateA,String insertDateDa,String insertDateA, String iuv,String idEsitoPagamento,String idMsgRichiesta) throws AxisFault, MalformedURLException, RemoteException {
		log.info("[MDPServiceHelper::GetRTByParam]  BEGIN ");
		MdpBoServicesCxfServiceSoapBindingStub stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);
		
		GetRTByParam parameters = new GetRTByParam(auth, id, applicationId, transactionId, 
				UtilDate.getConvertStringToCalendar(lastUpdateDa),
				UtilDate.getConvertStringToCalendar(lastUpdateA),
				UtilDate.getConvertStringToCalendar(insertDateDa),
				UtilDate.getConvertStringToCalendar(insertDateA),
				iuv,idEsitoPagamento,idMsgRichiesta);

		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		Rtdto[] lista = stub.getRTByParam(parameters);

		log.info("[MDPServiceHelper::GetRTByParam]  lista che arriva dal servizio " + lista.length);

		log.info("[MDPServiceHelper::GetRTByParam]  END ");
		return lista;
	}

	/**
	 * 
	 * @param userCred
	 * @param enteId
	 * @param partitaIva
	 * @param descrizione
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public EntiDTO[] getEntiByParam(Credentials userCred, String enteId, String partitaIva, String descrizione,String attivo)  throws AxisFault, MalformedURLException, RemoteException {
		log.info("[MDPEntiServiceHelper::GetRTByParam]  BEGIN ");
		MdpBoServicesCxfServiceSoapBindingStub stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);
		GetEntiByParam parameters = new GetEntiByParam(userCred, enteId, partitaIva, descrizione,attivo);
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		EntiDTO[] lista = stub.getEntiByParam(parameters);
		log.info("[MDPEntiServiceHelper::getEntiByParam]  lista che arriva dal servizio " + lista.length);
		log.info("[MDPEntiServiceHelper::getEntiByParam]  END ");
		return lista;
	}
	
	/**
	 * 
	 * @param userCred
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public EntiDTO[] findEntiAll(Credentials userCred)  throws AxisFault, MalformedURLException, RemoteException {
		log.info("[MDPEntiServiceHelper::findEntiAll]  BEGIN ");
		MdpBoServicesCxfServiceSoapBindingStub stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);
		FindEntiAll parameters = new FindEntiAll(userCred);
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		EntiDTO[] lista = stub.findEntiAll(parameters);
		log.info("[MDPEntiServiceHelper::findEntiAll]  lista che arriva dal servizio " + lista.length);
		log.info("[MDPEntiServiceHelper::findEntiAll]  END ");
		return lista;
	}
	
	/**
	 * 
	 * @param userCred
	 * @param enteId
	 * @param partitaIva
	 * @param descrizione
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public void insertEnte(Credentials userCred, String enteId, String partitaIva, String descrizione,String attivo)  throws AxisFault, MalformedURLException, RemoteException {
		log.info("[MDPEntiServiceHelper::insertEnte]  BEGIN ");
		MdpBoServicesCxfServiceSoapBindingStub stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);
		InsertEnte parameters = new InsertEnte(userCred, enteId, partitaIva, descrizione,attivo);
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		stub.insertEnte(parameters);
		log.info("[MDPEntiServiceHelper::insertEnte]  END ");
	}
	
	/**
	 * 
	 * @param userCred
	 * @param enteId
	 * @param partitaIva
	 * @param descrizione
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public void updateEnte(Credentials userCred, String enteId, String partitaIva, String descrizione,String attivo)  throws AxisFault, MalformedURLException, RemoteException {
		log.info("[MDPEntiServiceHelper::updateEnte]  BEGIN ");
		MdpBoServicesCxfServiceSoapBindingStub stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);
		UpdateEnte parameters = new UpdateEnte(userCred, enteId, partitaIva, descrizione,attivo);
		log.info("[MDPEntiServiceHelper::updateEnte]  attivo " +attivo);
		
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		stub.updateEnte(parameters);
		log.info("[MDPEntiServiceHelper::updateEnte]  END ");
	}
	
	/**
	 * 
	 * @param userCred
	 * @param enteId
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public void deleteEnte(Credentials userCred, String enteId)  throws AxisFault, MalformedURLException, RemoteException {
		log.info("[MDPEntiServiceHelper::deleteEnte]  BEGIN ");
		MdpBoServicesCxfServiceSoapBindingStub stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);
		DeleteEnte parameters = new DeleteEnte(userCred, enteId);
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		stub.deleteEnte(parameters);
		log.info("[MDPEntiServiceHelper::deleteEnte]  END ");
	}

	/**
	 * 
	 * @param userCred
	 * @param idApplicazione
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public EntiDTO[] getEntiByApplicationId(Credentials userCred, String idApplicazione) throws AxisFault, MalformedURLException, RemoteException {
		log.info("[MDPEntiServiceHelper::getEntiByApplicationId]  BEGIN ");
		MdpBoServicesCxfServiceSoapBindingStub stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);
		GetEntiByApplicationId parameters = new GetEntiByApplicationId(userCred, idApplicazione);
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		EntiDTO[] ente = stub.getEntiByApplicationId(parameters);		
		log.info("[MDPEntiServiceHelper::getEntiByApplicationId]  END ");
		return ente;
	}
	
	/**
	 * 
	 * @param auth
	 * @param idApplicazione
	 * @param enteId
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public Integer insRelEnteApplication( Credentials auth,String idApplicazione,String enteId)  throws AxisFault, MalformedURLException, RemoteException {
		log.info("[MDPEntiServiceHelper::insRelEnteApplication]  BEGIN ");
		MdpBoServicesCxfServiceSoapBindingStub stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);
		InsRelEnteApplication parameters = new InsRelEnteApplication(auth, idApplicazione,enteId);
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		InsRelEnteApplicationResponse ris = stub.insRelEnteApplication(parameters);		
		log.info("[MDPEntiServiceHelper::insRelEnteApplication]  END ");
		return ris.get_return();
	}
	
	/**
	 * 
	 * @param auth
	 * @param idApplicazione
	 * @param enteId
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public Integer delRelEnteApplication( Credentials auth,String idApplicazione,String enteId)  throws AxisFault, MalformedURLException, RemoteException {
		log.info("[MDPEntiServiceHelper::delRelEnteApplication]  BEGIN ");
		MdpBoServicesCxfServiceSoapBindingStub stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);
		DelRelEnteApplication parameters = new DelRelEnteApplication(auth, idApplicazione,enteId);
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		DelRelEnteApplicationResponse ris = stub.delRelEnteApplication(parameters);		
		log.info("[MDPEntiServiceHelper::delRelEnteApplication]  END ");
		return ris.get_return();
	}
	
	/**
	 * 
	 * @param auth
	 * @param idinformativapsp
	 * @param identificativoFlusso
	 * @param identificativoPSP
	 * @param ragioneSociale
	 * @param dataPubblicazione
	 * @param dataInizioValidita
	 * @param urlInformazioniPSP
	 * @param stornoPagamento
	 * @param identificativoIntermediario
	 * @param identificativoCanale
	 * @param tipoVersamento
	 * @param modelloPagamento
	 * @param priorita
	 * @param disponibilitaServizio
	 * @param descrizioneServizio
	 * @param condizioniEconomicheMassime
	 * @param urlInformazioniCanale
	 * @param datainserimento
	 * @param statoinserimento
	 * @param ordinamento
	 * @param origine
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public InformativePSPDTO[] getInformativePSPByParam(Credentials auth,Integer idinformativapsp,
			String identificativoFlusso, String identificativoPSP, String ragioneSociale, String dataPubblicazione,
			String dataInizioValidita, String urlInformazioniPSP, Integer stornoPagamento, String identificativoIntermediario,
			String identificativoCanale, String tipoVersamento,
			Integer modelloPagamento, Integer priorita, String disponibilitaServizio, String descrizioneServizio,
			String condizioniEconomicheMassime, String urlInformazioniCanale,
			String datainserimento, String statoinserimento, Integer ordinamento, String origine) throws AxisFault, MalformedURLException, RemoteException{
		
		log.info("[MDPEntiServiceHelper::getInformativePSPByParam]  BEGIN ");
		MdpBoServicesCxfServiceSoapBindingStub stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);
		
		GetInformativePSPByParam parameters = new GetInformativePSPByParam(auth, 
																			idinformativapsp,
																			identificativoFlusso,
																			identificativoPSP,
																			ragioneSociale,
																			UtilDate.getConvertStringToCalendar(dataPubblicazione),
																			UtilDate.getConvertStringToCalendar(dataInizioValidita),
																			urlInformazioniPSP,
																			stornoPagamento,
																			identificativoIntermediario,
																			identificativoCanale,
																			tipoVersamento,
																			modelloPagamento,
																			priorita,
																			disponibilitaServizio,
																			descrizioneServizio,
																			condizioniEconomicheMassime,
																			urlInformazioniCanale,
																			UtilDate.getConvertStringToCalendar(datainserimento),
																			statoinserimento,
																			ordinamento,
																			origine);
																					
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		InformativePSPDTO[] info = stub.getInformativePSPByParam(parameters);		
		
		log.info("[MDPEntiServiceHelper::getInformativePSPByParam]  END ");
		return info;
	}

	/**
	 * 
	 * @param auth
	 * @param idFlusso
	 * @param iuv
	 * @param identificativounivocoriscossione
	 * @param singoloimportopagato
	 * @param codiceesitosingolopagamento
	 * @param dataesitosingolopagamento
	 * @param datainserimento
	 * @param datamodifica
	 * @param applicationId
	 * @param dataregolamentoDa
	 * @param dataregolamentoA
	 * @return
	 */
	public FlussoSingoloPagamentoDTO[] getFlussoSingoloPagamentoByParam(Credentials auth,Integer id, Integer idFlusso, String iuv,
			String identificativounivocoriscossione, Double singoloimportopagato,
			String codiceesitosingolopagamento, String dataesitosingolopagamento,
			String datainserimento, String datamodifica, String applicationId,
			String dataregolamentoDa, String dataregolamentoA) throws AxisFault, MalformedURLException, RemoteException{
		log.info("[MDPEntiServiceHelper::getFlussoSingoloPagamentoByParam]  BEGIN ");
		MdpBoServicesCxfServiceSoapBindingStub stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);

		log.info("[MDPEntiServiceHelper::getFlussoSingoloPagamentoByParam]  1 "+applicationId);
		log.info("[MDPEntiServiceHelper::getFlussoSingoloPagamentoByParam]  2 "+ UtilDate.getConvertStringToCalendar(dataregolamentoDa));
		log.info("[MDPEntiServiceHelper::getFlussoSingoloPagamentoByParam]  3 "+ UtilDate.getConvertStringToCalendar(dataregolamentoA));

		
		GetFlussoSingoloPagamentoByParam parameters = new GetFlussoSingoloPagamentoByParam( auth,id,  idFlusso,  iuv,  identificativounivocoriscossione,  singoloimportopagato,
				 codiceesitosingolopagamento,  UtilDate.getConvertStringToCalendar(dataesitosingolopagamento),  UtilDate.getConvertStringToCalendar(datainserimento),  UtilDate.getConvertStringToCalendar(datamodifica),  applicationId,  UtilDate.getConvertStringToCalendar(dataregolamentoDa),  UtilDate.getConvertStringToCalendar(dataregolamentoA));
																					
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		FlussoSingoloPagamentoDTO[] fsp = stub.getFlussoSingoloPagamentoByParam(parameters);		
		
		log.info("[MDPEntiServiceHelper::getFlussoSingoloPagamentoByParam]  END ");
		return fsp;
	}
	
	/**
	 * 
	 * @param auth
	 * @param id
	 * @param identificativopsp
	 * @param identificativoflusso
	 * @param versioneoggetto
	 * @param identificativounivocoregolamento
	 * @param identificativoistitutomittente
	 * @param identificativoistitutoricevente
	 * @param numerototalepagamenti
	 * @param importototalepagamenti
	 * @param dataoraflusso
	 * @param dataregolamentoDa
	 * @param dataregolamentoA
	 * @param datainserimento
	 * @param datamodifica
	 * @param xmlflusso
	 * @param denominazionemittente
	 * @param denominazionericevente
	 * @return
	 */
	public FlussoRiversamentoDTO[] getFlussoRiversamentoByParam(Credentials auth, Integer id, String identificativopsp, String identificativoflusso, String versioneoggetto,
			String identificativounivocoregolamento, String identificativoistitutomittente, String identificativoistitutoricevente, Integer numerototalepagamenti, Double importototalepagamenti,
			String dataoraflusso, String dataregolamentoDa, String dataregolamentoA, String datainserimento, String datamodifica, String xmlflusso, String denominazionemittente, String denominazionericevente) throws AxisFault, MalformedURLException, RemoteException{
		
		log.info("[MDPEntiServiceHelper::getFlussoRiversamentoByParam]  BEGIN ");
		MdpBoServicesCxfServiceSoapBindingStub stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);
		
		GetFlussoRiversamentoByParam parameters = new GetFlussoRiversamentoByParam(  auth,  
				id,  
				identificativopsp,  identificativoflusso,  versioneoggetto,
				 identificativounivocoregolamento,  identificativoistitutomittente,  identificativoistitutoricevente,  numerototalepagamenti,  importototalepagamenti,
				 UtilDate.getConvertStringToCalendar(dataoraflusso),  UtilDate.getConvertStringToCalendar(dataregolamentoDa),  UtilDate.getConvertStringToCalendar(dataregolamentoA),  UtilDate.getConvertStringToCalendar(datainserimento),  UtilDate.getConvertStringToCalendar(datamodifica),  xmlflusso,  denominazionemittente,  denominazionericevente);
																					
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		FlussoRiversamentoDTO[] fsp = stub.getFlussoRiversamentoByParam(parameters);		
		
		log.info("[MDPEntiServiceHelper::getFlussoRiversamentoByParam]  END ");
		return fsp;
		
	}
	
	/**
	 * 
	 * @param auth
	 * @param applicationId
	 * @param iuv
	 * @param codiceContestoPagamento
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public  StatoRPTRisposta getStatiRPT (Credentials auth,String applicationId, String iuv, String codiceContestoPagamento) throws AxisFault, MalformedURLException, RemoteException, Exception {
		log.info("[MDPEntiServiceHelper::getStatiRPT]  START ");
		
		it.csi.mdp.mdpboweb.business.ws.Applicationcustomfields[] elencoAcf1 = new MDPServiceHelper().getApplicationCustomFields(auth,applicationId);//DaoFactory.createApplicationcustomfieldsDao();

		Map<String, String> mappaAcf = costruisciMappaApplicationCustomFields(elencoAcf1);
		
		JaxWsProxyFactoryBean factory = CoreUtilities.inizializzaServizio(mappaAcf.get("portaDiDominio"), "nodoChiediStatoRPT");

		PagamentiTelematiciRPT pagamentiTelematiciService = (PagamentiTelematiciRPT)factory.create();
		
		NodoChiediStatoRPT nodoChiediStatoRPTRequest = new NodoChiediStatoRPT();
		nodoChiediStatoRPTRequest.setCodiceContestoPagamento(codiceContestoPagamento);
		nodoChiediStatoRPTRequest.setIdentificativoDominio(mappaAcf.get("identificativoDominio"));
		nodoChiediStatoRPTRequest.setIdentificativoIntermediarioPA(mappaAcf.get("identificativointermediarioPA"));
		nodoChiediStatoRPTRequest.setIdentificativoStazioneIntermediarioPA(mappaAcf.get("identificativoStazioneIntermediarioPA"));
		nodoChiediStatoRPTRequest.setIdentificativoUnivocoVersamento(iuv);
		nodoChiediStatoRPTRequest.setPassword(mappaAcf.get("passwordDominioNodoSpc"));
		
		NodoChiediStatoRPTRisposta risposta = pagamentiTelematiciService.nodoChiediStatoRPT(nodoChiediStatoRPTRequest);
		
		//valorizzato in caso di errori
		StatoRPTRisposta statoRPTRisposta = new StatoRPTRisposta();
		if (risposta.getFault() != null) {
			statoRPTRisposta.setEsito(risposta.getFault().getFaultCode() + " - " + risposta.getFault().getFaultString() + " - " + risposta.getFault().getDescription());
			return statoRPTRisposta;
		}
		
		//attributi semplici (String)
		statoRPTRisposta.setStatoAttuale(risposta.getEsito().getStato());
		statoRPTRisposta.setUrlPagamento(risposta.getEsito().getUrl()); 
		statoRPTRisposta.setRedirect(risposta.getEsito().getRedirect()); 
		
		//elenco degli stati della RPT, il primo elemento e' il piu' vecchio (invio della RPT) l'ultimo elemento e' il piu' recente
		List <TipoStoricoRPT> elencoStoricoRPT = risposta.getEsito().getElementoStoricoRPT();
		
		for (TipoStoricoRPT singoloStato : elencoStoricoRPT)  {
			SingoloStatoRPT singoloStatoRPT = new SingoloStatoRPT();
			
			singoloStatoRPT.setData( UtilDate.getConvertXMLGregorianCalendarToString(singoloStato.getData()));
			singoloStatoRPT.setStato(singoloStato.getStato());
			singoloStatoRPT.setDescrizione(singoloStato.getDescrizione());
			
			statoRPTRisposta.getListaSingoloStatoRPT().add(singoloStatoRPT);
		}
		
		
		List<TipoStoricoVersamento> elencoStoricoVersamento = risposta.getEsito().getElementoStoricoVersamento();
		
		for (TipoStoricoVersamento singoloStatoVersamento : elencoStoricoVersamento) {
			SingoloStatoVersamento singoloStatoVersamentoMio = new SingoloStatoVersamento();
			
			singoloStatoVersamentoMio.setProgressivo(singoloStatoVersamento.getProgressivo());
			singoloStatoVersamentoMio.setData(UtilDate.getConvertXMLGregorianCalendarToString(singoloStatoVersamento.getData()));
			singoloStatoVersamentoMio.setStato(singoloStatoVersamento.getStato());
			singoloStatoVersamentoMio.setDescrizione(singoloStatoVersamento.getDescrizione());
			
			statoRPTRisposta.getListaSingoloStatoVersamento().add(singoloStatoVersamentoMio);
		}
		log.info("[MDPEntiServiceHelper::getStatiRPT] END ");
		return statoRPTRisposta;
	}
	
	public static Map<String, String> costruisciMappaApplicationCustomFields(it.csi.mdp.mdpboweb.business.ws.Applicationcustomfields[] acf) {
		Map<String, String> mappa = new HashMap<String, String>(acf.length);

		for (it.csi.mdp.mdpboweb.business.ws.Applicationcustomfields c : acf) {
			mappa.put(c.getFieldname().trim(), StringUtils.trimToNull(c.getFieldvalue()));
		}

		return mappa;
	}

	/**
	 * 
	 * @param userCred
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public TipoVersamentoDTO[] getListaTipoversamento(Credentials userCred) throws AxisFault, MalformedURLException, RemoteException, Exception {
		log.info("[MDPEntiServiceHelper::getListaTipoversamento]  BEGIN ");
		MdpBoServicesCxfServiceSoapBindingStub stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);
		
		GetListaTipoversamento parameters = new GetListaTipoversamento(userCred);
																					
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		TipoVersamentoDTO[] fsp = stub.getListaTipoversamento(parameters);		
		
		log.info("[MDPEntiServiceHelper::getListaTipoversamento]  END ");
		return fsp;
	}

	/**
	 * 
	 * @param userCred
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public FlussoRiversamentoDTO[] estraiFlussiDaServizio(Credentials userCred) throws AxisFault, MalformedURLException, RemoteException, Exception {
		log.info("[MDPEntiServiceHelper::estraiFlussiDaServizio]  BEGIN ");
		MdpBoServicesCxfServiceSoapBindingStub stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);
		
		EstraiFlussiDaServizio parameters = new EstraiFlussiDaServizio(userCred);	
		stub = new MdpBoServicesCxfServiceSoapBindingStub(new URL(MDPBOWS_ENDPOINT), null);
		stub = autenticazione(stub);
		FlussoRiversamentoDTO[] fsp = stub.estraiFlussiDaServizio(parameters);		
		
		
		
		log.info("[MDPEntiServiceHelper::estraiFlussiDaServizio]  END ");
		return fsp;
	}
		 
//	public static void main(String[] args){
//		String applicationId="TEST_TEST";
//		String iuv="RF08150920003TEST00000002";
//		String codiceContestoPagamento="n/a";
//		
//		Credentials userCred = new Credentials();
//		userCred.setCodfisc("AAAAAA00A11D000L");
//		userCred.setPwdAuth("mypass");
//		
//		try {
//			
//			
//			getStatiRPT ( userCred, applicationId,  iuv,  codiceContestoPagamento);
//		} catch (AxisFault e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
}
