/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.business.mgr;

import it.csi.mdp.mdpboweb.business.MDPNODONAZIONALEServiceHelper;
import it.csi.mdp.mdpboweb.business.MDPServiceHelper;
import it.csi.mdp.mdpboweb.business.ws.CodiciEsitoPagamentoDTO;
import it.csi.mdp.mdpboweb.business.ws.Credentials;
import it.csi.mdp.mdpboweb.business.ws.EntiDTO;
import it.csi.mdp.mdpboweb.business.ws.FlussoRiversamentoDTO;
import it.csi.mdp.mdpboweb.business.ws.FlussoSingoloPagamentoDTO;
import it.csi.mdp.mdpboweb.business.ws.GetGiornaleEventoByIdResponse;
import it.csi.mdp.mdpboweb.business.ws.GiornaleEventoDTO;
import it.csi.mdp.mdpboweb.business.ws.InformativePSPDTO;
import it.csi.mdp.mdpboweb.business.ws.Rptdto;
import it.csi.mdp.mdpboweb.business.ws.Rtdto;
import it.csi.mdp.mdpboweb.business.ws.StatiRptDTO;
import it.csi.mdp.mdpboweb.business.ws.TipoVersamentoDTO;
import it.csi.mdp.mdpboweb.dto.RicevuteTelematicheExt;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.CodiciEsitoPagamento;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.Enti;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.FlussoRiversamento;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.FlussoSingoloPagamento;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.GiornaleEventi;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.InformativePsp;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.RicevuteTelematiche;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.RichiestaPagamentoTelematico;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.StatiRpt;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.StatoRPTRisposta;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.TipoVersamento;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt;
import it.csi.mdp.mdpboweb.util.Constants;
import it.csi.mdp.mdpboweb.util.UtilDate;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.apache.axis.AxisFault;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

public class GestoreMDPNODONAZIONALEBackOffice {
	// ///////////////////////////////////////////////////////////////////////
	// GESTORE_MDP_BACKOFFICE
	//
	// ///////////////////////////////////////////////////////////////////////
	protected static final Logger log = Logger
			.getLogger(Constants.APPLICATION_CODE + ".business");

	
	/**
	 * 
	 * @param user
	 * @param idGiornaleEvento
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public GiornaleEventi getDettaglioGiornaleEventoById(UserInfoExt user,Integer idGiornaleEvento)  throws AxisFault, MalformedURLException,
	RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::getDettaglioGiornaleEventoById]  BEGIN ");
		
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();
		
		GetGiornaleEventoByIdResponse risws = MDPHelper.getGiornaleEventoById(userCred, idGiornaleEvento);
		GiornaleEventoDTO geDto = risws.get_return();
		GiornaleEventi    ge    = new GiornaleEventi(); 
		BeanUtils.copyProperties(ge,geDto);
		ge.setDataoraevento(UtilDate.getConvertCalendarToString(geDto.getDataoraevento(),"dd/MM/yyyy HH:mm:ss"));
		ge.setInsertDate(UtilDate.getConvertCalendarToString(geDto.getInsertDate(),"dd/MM/yyyy HH:mm:ss"));				
		ge.setLastUpdate(UtilDate.getConvertCalendarToString(geDto.getLastUpdate(),"dd/MM/yyyy HH:mm:ss"));

		log.info("[GestoreMDPBackOffice::getDettaglioGiornaleEventoById]  END ");
		return ge;
		
	}

	/**
	 * 
	 * @param user
	 * @param iuv
	 * @param dataOraEvento
	 * @param identificativodominio
	 * @param identificativofruitore
	 * @param codiceContesto
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public ArrayList<GiornaleEventi> getGiornaleEventoByFiltro(UserInfoExt user,String iuv,
			String dataOraEvento, String identificativodominio,
			String identificativofruitore, String codiceContesto)throws AxisFault, MalformedURLException,
			RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::getGiornaleEventoByFiltro]  BEGIN ");
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();

		ArrayList<GiornaleEventi> listaRes = new ArrayList<GiornaleEventi>();
		log.info("[GestoreMDPBackOffice::getGiornaleEventoByFiltro]  dataOraEvento "+ dataOraEvento);
		
		GiornaleEventoDTO[] lista = MDPHelper.getGiornaleEventoByFiltro(userCred, iuv,
				 dataOraEvento,  identificativodominio,
				 identificativofruitore,  codiceContesto);
		
		if (lista == null) {
			log.info("[GestoreMDPBackOffice::getGiornaleEventoByFiltro]  lista giornali eventi null ");
		} else {
			for (int k = 0; k < lista.length; k++) {
				GiornaleEventoDTO geDto = lista[k]; 
				GiornaleEventi    ge    = new GiornaleEventi(); 
				BeanUtils.copyProperties( ge,geDto);
				
				ge.setId(geDto.getId());
				ge.setInsertDate(UtilDate.getConvertCalendarToString(geDto.getInsertDate(),"dd/MM/yyyy HH:mm:ss"));				
				ge.setLastUpdate(UtilDate.getConvertCalendarToString(geDto.getLastUpdate(),"dd/MM/yyyy HH:mm:ss"));
				ge.setDataoraevento(UtilDate.getConvertCalendarToString(geDto.getDataoraevento(),"dd/MM/yyyy HH:mm:ss"));
				ge.setIdentificativodominio(geDto.getIdentificativodominio());
				ge.setIuv(geDto.getIuv());
				ge.setCodiceContesto(geDto.getCodiceContesto());
				ge.setIdPsp(geDto.getIdPsp());
				ge.setTipoversamento(geDto.getTipoversamento());
				ge.setComponente(geDto.getComponente());
				ge.setCategoriaevento(geDto.getCategoriaevento());
				ge.setTipoevento(geDto.getTipoevento());
				ge.setSottotipoevento(geDto.getSottotipoevento());
				ge.setIdentificativofruitore(geDto.getIdentificativofruitore());
				ge.setIdentificativoerogatore(geDto.getIdentificativoerogatore());
				ge.setIdentificativostazioneintermediariopa(geDto.getIdentificativostazioneintermediariopa());
				ge.setIdIntPsp(geDto.getIdIntPsp());
				ge.setCanalepagamento(geDto.getCanalepagamento());
				ge.setParametrispecificiinterfaccia(geDto.getParametrispecificiinterfaccia());
				ge.setEsito(geDto.getEsito());
				ge.setApplicationId(geDto.getApplicationId());
				
				listaRes.add(ge);
			}
		}
		log.info("[GestoreMDPBackOffice::getGiornaleEventoByFiltro]  END ");
		return listaRes;
	}
	/**
	 * 
	 * @param user
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public ArrayList<CodiciEsitoPagamento> getCodiciEsitoPagamentoAll(UserInfoExt user)throws AxisFault, MalformedURLException,
			RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::getCodiciEsitoPagamentoAll]  BEGIN ");
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPServiceHelper MDPHelper = new MDPServiceHelper();

		ArrayList<CodiciEsitoPagamento> listaRes = new ArrayList<CodiciEsitoPagamento>();

		CodiciEsitoPagamentoDTO[] listaDTO = MDPHelper.getCodiciEsitoPagamentoAll(userCred);
		
		if (listaDTO==null || listaDTO.length==0) {
			log.info("[GestoreMDPBackOffice::getCodiciEsitoPagamentoAll]  lista null o vuota");
		} else {
			for (int k = 0; k < listaDTO.length; k++) {
				CodiciEsitoPagamentoDTO dto    = listaDTO[k]; 
				CodiciEsitoPagamento    cep    = new CodiciEsitoPagamento(); 
				cep.setIdEsitoPagamento(String.valueOf(dto.getIdEsitoPagamento()));
				cep.setDescrizione(dto.getDescrizione());
				listaRes.add(cep);
				//log.info("[GestoreMDPBackOffice::getCodiciEsitoPagamentoAll]  destcrizione " + dto.getDescrizione());
			}
		}
		log.info("[GestoreMDPBackOffice::getCodiciEsitoPagamentoAll]  END ");
		return listaRes;
	}
	
	
	/**
	 * 
	 * @param userCred
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public ArrayList<StatiRpt> getStatiRptAll(UserInfoExt user) throws AxisFault, MalformedURLException,RemoteException, Exception {
		log.info("[MDPServiceHelper::getStatiRptAll]  BEGIN ");
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPNODONAZIONALEServiceHelper MDPRPTRTServiceHelper = new MDPNODONAZIONALEServiceHelper();

		ArrayList<StatiRpt> listaRes = new ArrayList<StatiRpt>();

		StatiRptDTO[] listaDTO = MDPRPTRTServiceHelper.getStatiRptAll(userCred);
		
		if (listaDTO==null || listaDTO.length==0) {
			log.info("[GestoreMDPBackOffice::getStatiRptAll]  lista null o vuota");
		} else {
			for (int k = 0; k < listaDTO.length; k++) {
				StatiRptDTO dto = listaDTO[k]; 
				StatiRpt    cep = new StatiRpt(); 
				cep.setIdStatiRpt(String.valueOf(dto.getIdStatiRpt()));
				cep.setDescrizione(dto.getDescrizione());
				listaRes.add(cep);
			}
		}
		log.info("[MDPServiceHelper::getStatiRptAll]  END ");
		return listaRes;
	}

	/**
	 * 
	 * @param user
	 * @param id
	 * @param applicationId
	 * @param transactionId
	 * @param lastUpdate
	 * @param insertDate
	 * @param iuv
	 * @param idStatiRpt
	 * @param idMsgRichiesta
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public ArrayList<RichiestaPagamentoTelematico> getRPTByParam(UserInfoExt user, Integer id, String applicationId, String transactionId, String lastUpdateDa,String lastUpdateA,String insertDateDa,String insertDateA, String iuv, String idStatiRpt, String idMsgRichiesta) throws AxisFault, MalformedURLException,
	RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::getRPTByParam]  BEGIN ");
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPNODONAZIONALEServiceHelper MDPRPTRTServiceHelper = new MDPNODONAZIONALEServiceHelper();

		ArrayList<RichiestaPagamentoTelematico> listaRes = new ArrayList<RichiestaPagamentoTelematico>();

		Rptdto[] listaDTO = MDPRPTRTServiceHelper.getRPTByParam( userCred,  id,  applicationId,  transactionId,   lastUpdateDa, lastUpdateA, insertDateDa, insertDateA,iuv,  idStatiRpt,  idMsgRichiesta);
		
		if (listaDTO==null || listaDTO.length==0) {
			log.info("[GestoreMDPBackOffice::getRPTByParam]  lista null o vuota");
		} else {
			for (int k = 0; k < listaDTO.length; k++) {
				Rptdto dto = listaDTO[k]; 
				RichiestaPagamentoTelematico cep  = new RichiestaPagamentoTelematico(); 
				cep.setId(dto.getId());
				cep.setApplicationId(dto.getApplicationId());
				cep.setTransactionId(dto.getTransactionId());
				cep.setInsertDate(UtilDate.getConvertCalendarToString(dto.getInsertDate(),"dd/MM/yyyy HH:mm:ss"));
				cep.setLastUpdate(UtilDate.getConvertCalendarToString(dto.getLastUpdate(),"dd/MM/yyyy HH:mm:ss"));
				cep.setAuthSoggetto(dto.getAuthSoggetto());
				cep.setDataMsgRichiesta(UtilDate.getConvertCalendarToString(dto.getDataMsgRichiesta(),"dd/MM/yyyy HH:mm:ss"));
				cep.setIdCanale(dto.getIdCanale());
				cep.setIdIntermPsp(dto.getIdIntermPsp());
				cep.setIdMsgRichiesta(dto.getIdMsgRichiesta());
				cep.setIdPsp(dto.getIdPsp());
				cep.setIdentificativoDominio(dto.getIdentificativoDominio());
				cep.setIdentificativoIntermediarioPa(dto.getIdentificativoIntermediarioPa());
				cep.setIdentificativoStazioneIntermediarioPa(dto.getIdentificativoStazioneIntermediarioPa());
				cep.setRptXml(dto.getRptXml());
				cep.setIuv(dto.getIuv());
				cep.setIdStatiRpt(String.valueOf(dto.getIdStatiRpt()));
				cep.setDescStatiRpt(dto.getDescStatoRpt());

				listaRes.add(cep);
			}
		}
		log.info("[GestoreMDPBackOffice::getRPTByParam]  END ");
		return listaRes;
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
	public ArrayList<RicevuteTelematicheExt>getRTByParam(UserInfoExt user,
														Integer id,
														String applicationId,
														String transactionId,
														String lastUpdateDa,
														String lastUpdateA,
														String insertDateDa,
														String insertDateA,
														String iuv,
														String idEsitoPagamento,
														String idMsgRichiesta) throws AxisFault, MalformedURLException,
	RemoteException, Exception {
		log.info("[GestoreMDPBackOffice::getRTByParam]  BEGIN ");
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		MDPNODONAZIONALEServiceHelper MDPRPTRTServiceHelper = new MDPNODONAZIONALEServiceHelper();

		ArrayList<RicevuteTelematicheExt> listaRes = new ArrayList<RicevuteTelematicheExt>();

		Rtdto[] listaDTO = MDPRPTRTServiceHelper.getRTByParam( userCred,  id,  applicationId,  transactionId,   lastUpdateDa, lastUpdateA, insertDateDa, insertDateA,  iuv,idEsitoPagamento,idMsgRichiesta);
		
		
		if (listaDTO==null || listaDTO.length==0) {
			log.info("[GestoreMDPBackOffice::getRTByParam]  lista null o vuota");
		} else {
			for (int k = 0; k < listaDTO.length; k++) {
				Rtdto dto = listaDTO[k]; 
				RicevuteTelematicheExt rte  = new RicevuteTelematicheExt(); 
				RicevuteTelematiche     rt  = new RicevuteTelematiche();
				
				rt.setId(dto.getId());
				rt.setApplicationId(dto.getApplicationId());
				rt.setTransactionId(dto.getTransactionId());
				rt.setInsertDate(UtilDate.getConvertCalendarToString(dto.getInsertDate(),"dd/MM/yyyy HH:mm:ss"));
				rt.setLastUpdate(UtilDate.getConvertCalendarToString(dto.getLastUpdate(),"dd/MM/yyyy HH:mm:ss"));
				rt.setDataMsgRicevuta(UtilDate.getConvertCalendarToString(dto.getDataMsgRicevuta(),"dd/MM/yyyy HH:mm:ss"));
				rt.setIdMsgRicevuta(dto.getIdMsgRicevuta());
				rt.setIdMsgRichiesta(dto.getIdMsgRichiesta());
				rt.setTipoFirma(dto.getTipoFirma());
				rt.setIuv(dto.getIuv());
				rt.setIdEsitoPagamento(String.valueOf(dto.getIdEsitoPagamento()));
				rt.setDescEsitoPagamento(dto.getDescEsitoPagamento());

				rte.setRicevuteTelematiche(rt);
				rte.setRtDataBin(dto.getRtData());
				listaRes.add(rte);
			}
		}
		
		log.info("[GestoreMDPBackOffice::getRTByParam]  lista size " + listaRes.size());
		log.info("[GestoreMDPBackOffice::getRTByParam]  END ");
		return listaRes;
	}

	/**
	 * 
	 * @param user
	 * @param enteId
	 * @param partitaIva
	 * @param descrizione
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public ArrayList<Enti> getEntiByParam(UserInfoExt user, String enteId, String partitaIva, String descrizione,String attivo)  throws AxisFault, MalformedURLException,RemoteException, Exception {
		log.info("[GestoreMDPNODONAZIONALEBackOffice::getEntiByParam]  BEGIN ");
		MDPNODONAZIONALEServiceHelper MDPHelper = new MDPNODONAZIONALEServiceHelper();
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		ArrayList<Enti> listaRes = new ArrayList<Enti>();
		
		EntiDTO[] listaDTO = MDPHelper.getEntiByParam( userCred,  enteId,  partitaIva,  descrizione, attivo);
		if (listaDTO==null || listaDTO.length==0) {
			log.info("[GestoreMDPNODONAZIONALEBackOffice::getEntiByParam]  lista null o vuota");
		} else {
			for (int k = 0; k < listaDTO.length; k++) {
				EntiDTO dto = listaDTO[k];
				Enti enti = new Enti();
				enti.setEnteId(dto.getEnteId());
				enti.setPartitaIva(dto.getPartitaIva());
				enti.setDescrizione(dto.getDescrizione());
				enti.setStato(dto.getStato());
				enti.setAttivo(dto.getAttivo());
				listaRes.add(enti);
			}
		}
		log.info("[GestoreMDPNODONAZIONALEBackOffice::getEntiByParam]  END ");
		return listaRes;
	}
 
	/**
	  * 
	  * @param user
	  * @return
	  * @throws AxisFault
	  * @throws MalformedURLException
	  * @throws RemoteException
	  * @throws Exception
	  */
	public ArrayList<Enti> findEntiAll(UserInfoExt user)  throws AxisFault, MalformedURLException,RemoteException, Exception {
		log.info("[GestoreMDPNODONAZIONALEBackOffice::findEntiAll]  BEGIN ");
		MDPNODONAZIONALEServiceHelper MDPHelper = new MDPNODONAZIONALEServiceHelper();
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		ArrayList<Enti> listaRes = new ArrayList<Enti>();
		
		EntiDTO[] listaDTO = MDPHelper.findEntiAll( userCred);
		if (listaDTO==null || listaDTO.length==0) {
			log.info("[GestoreMDPNODONAZIONALEBackOffice::findEntiAll]  lista null o vuota");
		} else {
			for (int k = 0; k < listaDTO.length; k++) {
				EntiDTO dto = listaDTO[k];
				Enti enti = new Enti();
				enti.setEnteId(dto.getEnteId());
				enti.setPartitaIva(dto.getPartitaIva());
				enti.setDescrizione(dto.getDescrizione());
				enti.setAttivo(dto.getAttivo());
				listaRes.add(enti);
			}
		}
		log.info("[GestoreMDPNODONAZIONALEBackOffice::findEntiAll]  END ");
		return listaRes;
	}

	/**
	 * 
	 * @param user
	 * @param enteId
	 * @param partitaIva
	 * @param descrizione
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void insertEnte(UserInfoExt user, String enteId, String partitaIva, String descrizione,String attivo)  throws AxisFault, MalformedURLException,RemoteException, Exception {
		log.info("[GestoreMDPNODONAZIONALEBackOffice::insertEnte]  BEGIN ");
		MDPNODONAZIONALEServiceHelper MDPHelper = new MDPNODONAZIONALEServiceHelper();
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());		
		MDPHelper.insertEnte( userCred,  enteId,  partitaIva,  descrizione, attivo);
		log.info("[GestoreMDPNODONAZIONALEBackOffice::insertEnte]  END ");
	}

	/**
	 * 
	 * @param user
	 * @param enteId
	 * @param partitaIva
	 * @param descrizione
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void updateEnte(UserInfoExt user, String enteId, String partitaIva, String descrizione,String attivo)  throws AxisFault, MalformedURLException,RemoteException, Exception {
		log.info("[GestoreMDPNODONAZIONALEBackOffice::updateEnte]  BEGIN ");
		MDPNODONAZIONALEServiceHelper MDPHelper = new MDPNODONAZIONALEServiceHelper();
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());		
		MDPHelper.updateEnte( userCred,  enteId,  partitaIva,  descrizione, attivo);
		log.info("[GestoreMDPNODONAZIONALEBackOffice::updateEnte]  END ");
	}

	/**
	 * 
	 * @param user
	 * @param enteId
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void deleteEnte(UserInfoExt user, String enteId)  throws AxisFault, MalformedURLException,RemoteException, Exception {
		log.info("[GestoreMDPNODONAZIONALEBackOffice::deleteEnte]  BEGIN ");
		MDPNODONAZIONALEServiceHelper MDPHelper = new MDPNODONAZIONALEServiceHelper();
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());		
		MDPHelper.deleteEnte( userCred,  enteId);
		log.info("[GestoreMDPNODONAZIONALEBackOffice::deleteEnte]  END ");
	}

	/**
	 * 
	 * @param user
	 * @param idApplicazione
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public ArrayList<Enti> getEnteByApplicationId(UserInfoExt user, String idApplicazione) throws AxisFault, MalformedURLException,RemoteException, Exception {
		log.info("[GestoreMDPNODONAZIONALEBackOffice::getEnteByApplicationId]  BEGIN ");
		MDPNODONAZIONALEServiceHelper MDPHelper = new MDPNODONAZIONALEServiceHelper();
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		ArrayList<Enti> listaRes = new ArrayList<Enti>();
		
		EntiDTO[] listaDTO = MDPHelper.getEntiByApplicationId( userCred,  idApplicazione);
		if (listaDTO==null ) {
			log.info("[GestoreMDPNODONAZIONALEBackOffice::getEnteByApplicationId]  ente non associato");
		} else {
			for (int k = 0; k < listaDTO.length; k++) {
				EntiDTO dto = listaDTO[k];
				Enti enti = new Enti();
				enti.setEnteId(dto.getEnteId());
				enti.setPartitaIva(dto.getPartitaIva());
				enti.setDescrizione(dto.getDescrizione());
				enti.setAttivo(dto.getAttivo());
				listaRes.add(enti);
			}
			
		}
		log.info("[GestoreMDPNODONAZIONALEBackOffice::getEnteByApplicationId]  END ");
		return listaRes;
	}
	
	/**
	 * 
	 * @param user
	 * @param idApplicazione
	 * @param enteId
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void delRelEnteApplication(UserInfoExt user, String idApplicazione,String enteId)  throws AxisFault, MalformedURLException,RemoteException, Exception {
		log.info("[GestoreMDPNODONAZIONALEBackOffice::deleteEnte]  BEGIN ");
		MDPNODONAZIONALEServiceHelper MDPHelper = new MDPNODONAZIONALEServiceHelper();
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());		
		MDPHelper.delRelEnteApplication( userCred,idApplicazione,  enteId);
		log.info("[GestoreMDPNODONAZIONALEBackOffice::deleteEnte]  END ");
	}

	/**
	 * 
	 * @param user
	 * @param idApplicazione
	 * @param enteId
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void insRelEnteApplication(UserInfoExt user, String idApplicazione,String enteId)  throws AxisFault, MalformedURLException,RemoteException, Exception {
		log.info("[GestoreMDPNODONAZIONALEBackOffice::insRelEnteApplication]  BEGIN ");
		MDPNODONAZIONALEServiceHelper MDPHelper = new MDPNODONAZIONALEServiceHelper();
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());		
		MDPHelper.insRelEnteApplication( userCred,idApplicazione,  enteId);
		log.info("[GestoreMDPNODONAZIONALEBackOffice::insRelEnteApplication]  END ");
	}
	
	/**
	 * 
	 * @param user
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
	 * @throws Exception
	 */
	public ArrayList<InformativePsp> getInformativePSPByParam(UserInfoExt user,Integer idinformativapsp,
			String identificativoFlusso, String identificativoPSP, String ragioneSociale, String dataPubblicazione,
			String dataInizioValidita, String urlInformazioniPSP, Integer stornoPagamento, String identificativoIntermediario,
			String identificativoCanale, String tipoVersamento,
			Integer modelloPagamento, Integer priorita, String disponibilitaServizio, String descrizioneServizio,
			String condizioniEconomicheMassime, String urlInformazioniCanale,
			String datainserimento, String statoinserimento, Integer ordinamento, String origine)throws AxisFault, MalformedURLException,RemoteException, Exception {
				
		log.info("[GestoreMDPNODONAZIONALEBackOffice::getInformativePSPByParam]  BEGIN ");
		MDPNODONAZIONALEServiceHelper MDPHelper = new MDPNODONAZIONALEServiceHelper();
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		ArrayList<InformativePsp> listaRes = new ArrayList<InformativePsp>();
		
		InformativePSPDTO[] listaDTO = MDPHelper.getInformativePSPByParam( userCred,   idinformativapsp,
				 identificativoFlusso,  identificativoPSP,  ragioneSociale,  dataPubblicazione,
				 dataInizioValidita,  urlInformazioniPSP,  stornoPagamento,  identificativoIntermediario,
				 identificativoCanale,  tipoVersamento,
				 modelloPagamento,  priorita,  disponibilitaServizio,  descrizioneServizio,
				 condizioniEconomicheMassime,  urlInformazioniCanale,
				 datainserimento,  statoinserimento,  ordinamento,  origine);
				
				
		if (listaDTO==null ) {
			log.info("[GestoreMDPNODONAZIONALEBackOffice::getInformativePSPByParam]  lista null");
		} else {
			for (int k = 0; k < listaDTO.length; k++) {
				InformativePSPDTO dto = listaDTO[k];
				InformativePsp informative = new InformativePsp();
				
				informative.setIdinformativapsp(dto.getIdinformativapsp());
				informative.setIdentificativoflusso(dto.getIdentificativoFlusso());
				informative.setIdentificativopsp(dto.getIdentificativoPSP());
				informative.setRagionesociale(dto.getRagioneSociale());
				informative.setDatapubblicazione(UtilDate.getConvertCalendarToString(dto.getDataPubblicazione(),"dd/MM/yyyy HH:mm:ss"));
				informative.setDatainiziovalidita(UtilDate.getConvertCalendarToString(dto.getDataInizioValidita(),"dd/MM/yyyy HH:mm:ss"));
				informative.setUrlinformazionipsp(dto.getUrlInformazioniPSP());
				informative.setStornopagamento(dto.getStornoPagamento());
				informative.setIdentificativointermediario(dto.getIdentificativoIntermediario());
				informative.setIdentificativocanale(dto.getIdentificativoCanale());
				informative.setTipoversamento(dto.getTipoVersamento());
				informative.setModellopagamento(dto.getModelloPagamento());
				informative.setPriorita(dto.getPriorita());
				informative.setDisponibilitaservizio(dto.getDisponibilitaServizio());
				informative.setDescrizioneservizio(dto.getDescrizioneServizio());
				informative.setCondizionieconomichemassime(dto.getCondizioniEconomicheMassime());
				informative.setUrlinformazionicanale(dto.getUrlInformazioniCanale());
				informative.setDatainserimento(UtilDate.getConvertCalendarToString(dto.getDatainserimento(),"dd/MM/yyyy HH:mm:ss"));
				informative.setStatoinserimento(dto.getStatoinserimento());
				informative.setOrdinamento(dto.getOrdinamento());
				informative.setOrigine(dto.getOrigine());
				
				listaRes.add(informative);
			}
			
		}
		log.info("[GestoreMDPNODONAZIONALEBackOffice::getInformativePSPByParam]  END ");
		return listaRes;
				
	}
	

	
	public ArrayList<FlussoRiversamento> getFlussoRiversamentoByParam(UserInfoExt user, Integer id, String identificativopsp, String identificativoflusso, String versioneoggetto,
			String identificativounivocoregolamento, String identificativoistitutomittente, String identificativoistitutoricevente, Integer numerototalepagamenti, Double importototalepagamenti,
			String dataoraflusso, String dataregolamentoDa, String dataregolamentoA, String datainserimento, String datamodifica, String xmlflusso, String denominazionemittente, String denominazionericevente)
			throws AxisFault, MalformedURLException,RemoteException, Exception {
		log.info("[GestoreMDPNODONAZIONALEBackOffice::getFlussoRiversamentoByParam]  BEGIN ");
		MDPNODONAZIONALEServiceHelper MDPHelper = new MDPNODONAZIONALEServiceHelper();
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		ArrayList<FlussoRiversamento> listaRes = new ArrayList<FlussoRiversamento>();
		
		FlussoRiversamentoDTO[] listaDTO = MDPHelper.getFlussoRiversamentoByParam(
																 userCred,   id,  identificativopsp,  identificativoflusso,  versioneoggetto,
																	 identificativounivocoregolamento,  identificativoistitutomittente,  identificativoistitutoricevente,  numerototalepagamenti,  importototalepagamenti,
																	 dataoraflusso,  dataregolamentoDa,  dataregolamentoA,  datainserimento,  datamodifica,  xmlflusso,  denominazionemittente,  denominazionericevente);
				
				
		if (listaDTO==null ) {
			log.info("[GestoreMDPNODONAZIONALEBackOffice::getFlussoRiversamentoByParam]  lista null");
		} else {
			for (int k = 0; k < listaDTO.length; k++) {
				FlussoRiversamentoDTO dto = listaDTO[k];
				FlussoRiversamento fr = new FlussoRiversamento();
				
				fr.setId(dto.getId());
				fr.setIdentificativopsp(dto.getIdentificativopsp());
				fr.setIdentificativoflusso(dto.getIdentificativoflusso());
				fr.setVersioneoggetto(dto.getVersioneoggetto());
				fr.setIdentificativounivocoregolamento(dto.getIdentificativounivocoregolamento());
				fr.setIdentificativoistitutomittente(dto.getIdentificativoistitutomittente());
				fr.setIdentificativoistitutoricevente(dto.getIdentificativoistitutoricevente());
				fr.setNumerototalepagamenti(dto.getNumerototalepagamenti());
				fr.setImportototalepagamenti(dto.getImportototalepagamenti());
				fr.setDataoraflusso(UtilDate.getConvertCalendarToString(dto.getDataoraflusso(),"dd/MM/yyyy HH:mm:ss"));
				fr.setDataregolamento(UtilDate.getConvertCalendarToString(dto.getDataregolamento(),"dd/MM/yyyy HH:mm:ss"));
				fr.setDatainserimento(UtilDate.getConvertCalendarToString(dto.getDatainserimento(),"dd/MM/yyyy HH:mm:ss"));
				fr.setDatamodifica(UtilDate.getConvertCalendarToString(dto.getDatamodifica(),"dd/MM/yyyy HH:mm:ss"));
				fr.setXmlflusso(dto.getXmlflusso());
				fr.setDenominazionemittente(dto.getDenominazionemittente());
				fr.setDenominazionericevente(dto.getDenominazionericevente());
				
				listaRes.add(fr);
			}
			
		}
		log.info("[GestoreMDPNODONAZIONALEBackOffice::getFlussoRiversamentoByParam]  END ");
		return listaRes;
	}

/**
 * 
 * @param user
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
 * @throws AxisFault
 * @throws MalformedURLException
 * @throws RemoteException
 * @throws Exception
 */
	public ArrayList<FlussoSingoloPagamento> getFlussoSingoloPagamentoByParam(UserInfoExt user,Integer id, Integer idFlusso, String iuv, String identificativounivocoriscossione, Double singoloimportopagato,
			String codiceesitosingolopagamento, String dataesitosingolopagamento, String datainserimento, String datamodifica, String applicationId, String dataregolamentoDa, String dataregolamentoA)
			throws AxisFault, MalformedURLException,RemoteException, Exception {
		log.info("[GestoreMDPNODONAZIONALEBackOffice::getFlussoSingoloPagamentoByParam]  BEGIN ");
		MDPNODONAZIONALEServiceHelper MDPHelper = new MDPNODONAZIONALEServiceHelper();
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		ArrayList<FlussoSingoloPagamento> listaRes = new ArrayList<FlussoSingoloPagamento>();
		
		FlussoSingoloPagamentoDTO[] listaDTO = MDPHelper.getFlussoSingoloPagamentoByParam(
																 userCred,id,  idFlusso,  iuv, 
																 identificativounivocoriscossione,  singoloimportopagato,
																 codiceesitosingolopagamento,  dataesitosingolopagamento,
																 datainserimento,  datamodifica, applicationId,
																 dataregolamentoDa,  dataregolamentoA);
				
				
		if (listaDTO==null ) {
			log.info("[GestoreMDPNODONAZIONALEBackOffice::getFlussoSingoloPagamentoByParam]  lista null");
		} else {
			for (int k = 0; k < listaDTO.length; k++) {
				FlussoSingoloPagamentoDTO dto = listaDTO[k];
				FlussoSingoloPagamento fsp = new FlussoSingoloPagamento();
				
				fsp.setId(dto.getId());
				fsp.setIdentificativopsp(dto.getIdentificativopsp());
				fsp.setIdentificativoflusso(dto.getIdentificativoflusso());
				fsp.setVersioneoggetto(dto.getVersioneoggetto());
				fsp.setIdentificativounivocoregolamento(dto.getIdentificativounivocoregolamento());
				fsp.setIdentificativoistitutomittente(dto.getIdentificativoistitutomittente());
				fsp.setIdentificativoistitutoricevente(dto.getIdentificativoistitutoricevente());
				fsp.setNumerototalepagamenti(dto.getNumerototalepagamenti());
				fsp.setImportototalepagamenti(dto.getImportototalepagamenti());
				fsp.setDataoraflusso(UtilDate.getConvertCalendarToString(dto.getDataoraflusso(),"dd/MM/yyyy HH:mm:ss"));
				fsp.setDataregolamento(UtilDate.getConvertCalendarToString(dto.getDataregolamento(),"dd/MM/yyyy HH:mm:ss"));
				//fsp.setDatainserimento(UtilDate.getConvertCalendarToString(dto.getDatainserimento()));
				//fsp.setDatamodifica(UtilDate.getConvertCalendarToString(dto.getDatamodifica()));
				fsp.setXmlflusso(dto.getXmlflusso());
				fsp.setDenominazionemittente(dto.getDenominazionemittente());
				fsp.setDenominazionericevente(dto.getDenominazionericevente());
				fsp.setIdFlusso(dto.getIdFlusso());
				fsp.setIuv(dto.getIuv());
				fsp.setIdentificativounivocoriscossione(dto.getIdentificativounivocoriscossione());
				fsp.setSingoloimportopagato(dto.getSingoloimportopagato());
				fsp.setCodiceesitosingolopagamento(dto.getCodiceesitosingolopagamento());
				fsp.setDataesitosingolopagamento(UtilDate.getConvertCalendarToString(dto.getDataesitosingolopagamento(),"dd/MM/yyyy HH:mm:ss"));
				fsp.setDatainserimento(UtilDate.getConvertCalendarToString(dto.getDatainserimento(),"dd/MM/yyyy HH:mm:ss"));
				fsp.setDatamodifica(UtilDate.getConvertCalendarToString(dto.getDatamodifica(),"dd/MM/yyyy HH:mm:ss"));
				fsp.setApplicationId(dto.getApplicationId());
				fsp.setApplicationname(dto.getApplicationname());
//				informative.setIdinformativapsp(dto.getIdinformativapsp());
//				informative.setIdentificativoflusso(dto.getIdentificativoFlusso());
//				informative.setIdentificativopsp(dto.getIdentificativoPSP());
//				informative.setRagionesociale(dto.getRagioneSociale());
//				informative.setDatapubblicazione(UtilDate.getConvertCalendarToString(dto.getDataPubblicazione()));
//				informative.setDatainiziovalidita(UtilDate.getConvertCalendarToString(dto.getDataInizioValidita()));
//				informative.setUrlinformazionipsp(dto.getUrlInformazioniPSP());
//				informative.setStornopagamento(dto.getStornoPagamento());
//				informative.setIdentificativointermediario(dto.getIdentificativoIntermediario());
//				informative.setIdentificativocanale(dto.getIdentificativoCanale());
//				informative.setTipoversamento(dto.getTipoVersamento());
//				informative.setModellopagamento(dto.getModelloPagamento());
//				informative.setPriorita(dto.getPriorita());
//				informative.setDisponibilitaservizio(dto.getDisponibilitaServizio());
//				informative.setDescrizioneservizio(dto.getDescrizioneServizio());
//				informative.setCondizionieconomichemassime(dto.getCondizioniEconomicheMassime());
//				informative.setUrlinformazionicanale(dto.getUrlInformazioniCanale());
//				informative.setDatainserimento(UtilDate.getConvertCalendarToString(dto.getDatainserimento()));
//				informative.setStatoinserimento(dto.getStatoinserimento());
//				informative.setOrdinamento(dto.getOrdinamento());
//				informative.setOrigine(dto.getOrigine());	
				listaRes.add(fsp);
			}
		}
		log.info("[GestoreMDPNODONAZIONALEBackOffice::getFlussoSingoloPagamentoByParam]  END ");
		return listaRes;
	}


	public StatoRPTRisposta getStatiRPT (UserInfoExt user,String applicationId, String iuv, String codiceContestoPagamento) throws AxisFault, MalformedURLException,RemoteException, Exception {
		log.info("[GestoreMDPNODONAZIONALEBackOffice::getEnteByApplicationId]  BEGIN ");
		MDPNODONAZIONALEServiceHelper MDPHelper = new MDPNODONAZIONALEServiceHelper();
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		ArrayList<Enti> listaRes = new ArrayList<Enti>();
		
		StatoRPTRisposta statoRPTRisposta = MDPHelper.getStatiRPT( userCred,  applicationId,  iuv,  codiceContestoPagamento);
			
		log.info("[GestoreMDPNODONAZIONALEBackOffice::getEnteByApplicationId]  END ");
		return statoRPTRisposta;
	}

	public ArrayList<TipoVersamento> getListaTipoversamento(UserInfoExt user) throws AxisFault, MalformedURLException,RemoteException, Exception {
		log.info("[GestoreMDPNODONAZIONALEBackOffice::getListaTipoversamento]  BEGIN ");
		
		MDPNODONAZIONALEServiceHelper MDPHelper = new MDPNODONAZIONALEServiceHelper();
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		ArrayList<TipoVersamento> listaRes = new ArrayList<TipoVersamento>();
		
		TipoVersamentoDTO[] listaDTO = MDPHelper.getListaTipoversamento(userCred);
				
				
		if (listaDTO==null ) {
			log.info("[GestoreMDPNODONAZIONALEBackOffice::getListaTipoversamento]  lista null");
		} else {
			for (int k = 0; k < listaDTO.length; k++) {
				TipoVersamentoDTO dto = listaDTO[k];
				TipoVersamento tv = new TipoVersamento();
				
				tv.setId(dto.getId());
				tv.setDescrizione(dto.getDescrizione());

				listaRes.add(tv);
			}
		}
		log.info("[GestoreMDPNODONAZIONALEBackOffice::getListaTipoversamento]  END ");
		return listaRes;
	}

	public ArrayList<FlussoRiversamento> estraiFlussiDaServizio(UserInfoExt user) throws AxisFault, MalformedURLException,RemoteException, Exception {
		log.info("[GestoreMDPNODONAZIONALEBackOffice::estraiFlussiDaServizio]  BEGIN ");
		MDPNODONAZIONALEServiceHelper MDPHelper = new MDPNODONAZIONALEServiceHelper();
		Credentials userCred = new Credentials();
		userCred.setCodfisc(user.getCodFisc());
		userCred.setPwdAuth(user.getPwBck());
		ArrayList<FlussoRiversamento> listaRes = new ArrayList<FlussoRiversamento>();
		
		FlussoRiversamentoDTO[] listaDTO = MDPHelper.estraiFlussiDaServizio( userCred);
				


		if (listaDTO==null ) {
			log.info("[GestoreMDPNODONAZIONALEBackOffice::estraiFlussiDaServizio]  lista null");
		} else {
			for (int k = 0; k < listaDTO.length; k++) {
				FlussoRiversamentoDTO dto = listaDTO[k];
				FlussoRiversamento fr = new FlussoRiversamento();
				//fr.setId(dto.getId());

				//log.info("[GestoreMDPNODONAZIONALEBackOffice::estraiFlussiDaServizio]  dto.getIdentificativopsp() " + dto.getIdentificativopsp());

				//fr.setIdentificativopsp(dto.getIdentificativopsp());
				fr.setIdentificativoflusso(dto.getIdentificativoflusso());
				fr.setVersioneoggetto(dto.getVersioneoggetto());
				fr.setIdentificativounivocoregolamento(dto.getIdentificativounivocoregolamento());
				fr.setIdentificativoistitutomittente(dto.getIdentificativoistitutomittente());
				fr.setIdentificativoistitutoricevente(dto.getIdentificativoistitutoricevente());
				//fr.setNumerototalepagamenti(dto.getNumerototalepagamenti());
				//fr.setImportototalepagamenti(dto.getImportototalepagamenti());
				fr.setDataoraflusso(UtilDate.getConvertCalendarToString(dto.getDataoraflusso(),"dd/MM/yyyy HH:mm:ss"));
				//fr.setDataregolamento(UtilDate.getConvertCalendarToString(dto.getDataregolamento(),"dd/MM/yyyy HH:mm:ss"));
				//fr.setDatainserimento(UtilDate.getConvertCalendarToString(dto.getDatainserimento(),"dd/MM/yyyy HH:mm:ss"));
				//fr.setDatamodifica(UtilDate.getConvertCalendarToString(dto.getDatamodifica(),"dd/MM/yyyy HH:mm:ss"));
				//fr.setXmlflusso(dto.getXmlflusso());
				fr.setDenominazionemittente(dto.getDenominazionemittente());
				fr.setDenominazionericevente(dto.getDenominazionericevente());
				
				listaRes.add(fr);
			}	
		}
		log.info("[GestoreMDPNODONAZIONALEBackOffice::estraiFlussiDaServizio]  listaRes " + listaRes.size());
		
		log.info("[GestoreMDPNODONAZIONALEBackOffice::estraiFlussiDaServizio]  END ");
		return listaRes;
	}
}
