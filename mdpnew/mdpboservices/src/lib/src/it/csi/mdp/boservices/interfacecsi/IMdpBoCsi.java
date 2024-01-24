/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.boservices.interfacecsi;

import it.csi.csi.wrapper.CSIException;
import it.csi.mdp.boservices.dto.CodiciEsitoPagamentoDTO;
import it.csi.mdp.boservices.dto.EntiDTO;
import it.csi.mdp.boservices.dto.FlussoRiversamentoDTO;
import it.csi.mdp.boservices.dto.FlussoSingoloPagamentoDTO;
import it.csi.mdp.boservices.dto.GiornaleEventoDTO;
import it.csi.mdp.boservices.dto.ParametroNodoDTO;
import it.csi.mdp.boservices.dto.RPTDTO;
import it.csi.mdp.boservices.dto.RTDTO;
import it.csi.mdp.boservices.dto.StatiRptDTO;
import it.csi.mdp.boservices.dto.TipoVersamentoDTO;
import it.csi.mdp.boservices.exception.AuthException;
import it.csi.mdp.boservices.exception.MissingParameterException;
import it.csi.mdp.boservices.util.Credentials;
import it.csi.mdp.boservices.util.VtransazioneResult;
import it.csi.mdp.core.business.dto.*;
import it.csi.mdp.core.business.exceptions.DaoException;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

public interface IMdpBoCsi {
	
	public List<Application> listApplicationByFlussoApplicazione(Credentials auth) throws RemoteException, DaoException, AuthException, MissingParameterException;

	public void setApplication(Credentials auth, Application app, boolean overwrite) throws RemoteException, DaoException, AuthException, MissingParameterException;

	public List<Application> getApplication(Credentials auth) throws RemoteException, DaoException, AuthException, MissingParameterException;

	public Application getApplicationById(Credentials auth, String appId) throws RemoteException, DaoException, AuthException, MissingParameterException;

	public void setApplicationDetail(Credentials auth, ApplicationDetail app) throws RemoteException, DaoException, AuthException, MissingParameterException;

	public List<ApplicationDetail> getApplicationDetailById(Credentials auth, String appid) throws RemoteException, DaoException, AuthException, MissingParameterException;

	public List<ApplicationDetail> getApplicationDetail(Credentials auth) throws RemoteException, DaoException, AuthException, MissingParameterException;

	public List<Applicationcustomfields> getApplicationCustomFields(Credentials auth, String appId) throws RemoteException, DaoException, AuthException, MissingParameterException;

	public void setApplicationCustomFields(Credentials auth, Applicationcustomfields[] app) throws RemoteException, DaoException, AuthException, MissingParameterException;

	public Gateway setGateway(Credentials auth, Gateway app) throws RemoteException, DaoException, AuthException, MissingParameterException;

	public Gateway getGatewayById(Credentials auth, String gwId) throws RemoteException, DaoException, AuthException, MissingParameterException;

	public List<Gateway> getGateway(Credentials auth) throws RemoteException, DaoException, AuthException;

	public Paymentmode setPaymentMode(Credentials auth, Paymentmode paymode) throws RemoteException, DaoException, AuthException, MissingParameterException;

	public List<Paymentmode> getPaymentMode(Credentials auth) throws RemoteException, DaoException, AuthException, MissingParameterException;

	public Paymentmode getPaymentModeById(Credentials auth, String payModeId) throws RemoteException, DaoException, AuthException, MissingParameterException;

	public Gatewaydetail setGatewayDetail(Credentials auth, Gatewaydetail app) throws RemoteException, DaoException, AuthException, MissingParameterException;

	public Gatewaydetail getGatewayDetailByIds(Credentials auth, String gwId, String payId) throws RemoteException, DaoException, AuthException, MissingParameterException, NamingException,
			CreateException, CSIException;

	public List<Gatewaydetail> getGatewayDetailById(Credentials auth, String gwId) throws RemoteException, DaoException, AuthException, MissingParameterException, NamingException, CreateException,
			CSIException;

	public List<Gatewaydetail> getGatewayDetail(Credentials auth) throws RemoteException, DaoException, AuthException, MissingParameterException, NamingException, CreateException, CSIException;

	public List<MdpCurrency> getMdpCurrency(Credentials auth) throws RemoteException, DaoException, AuthException, MissingParameterException;

	public List<MdpBckroles> getMdpBckRoles(Credentials auth) throws RemoteException, DaoException, AuthException, MissingParameterException;

	public MdpBckroles setMdpRole(Credentials auth, MdpBckroles role) throws RemoteException, DaoException, AuthException, MissingParameterException;

	public MdpBckusers setMdpUser(Credentials auth, MdpBckusers user, List<MdpBckusersgroup> usergroup) throws RemoteException, DaoException, AuthException, MissingParameterException;

	public MdpBckofficegroups setMdpGroup(Credentials auth, MdpBckofficegroups group, List<MdpBckofficegroupappmapping> groupapp, MdpBckrolesgroupmap grouprole) throws RemoteException, DaoException,
			AuthException, MissingParameterException;

	public List<CsiLogAudit> getAuditing(Credentials auth, List<Application> apps, Date datestart, Date dateend, String transactionid, List<String> actions, List<String> users)
			throws RemoteException, DaoException, AuthException, MissingParameterException;

	public List<MdpBckusers> getMdpUsers(Credentials auth) throws RemoteException, DaoException, AuthException, MissingParameterException;

	// public List<CsiLogAudit> getAuditing(Credentials auth ,String app, Date
	// datestart, Date dateend, String transactionid, String user) throws
	// RemoteException , DaoException, AuthException,MissingParameterException;

	public void setMdpCurrency(Credentials auth, MdpCurrency ccy) throws RemoteException, DaoException, AuthException, MissingParameterException;

	public List<Transazione> getTransazione(Credentials auth) throws RemoteException, DaoException, AuthException, MissingParameterException;

	public List<Vtransazione> getTransazioneView(Credentials auth) throws RemoteException, DaoException, AuthException, MissingParameterException;

	public List<Transazione> getTransazioneByApp(Credentials auth, String appid) throws RemoteException, DaoException, AuthException, MissingParameterException;

	public List<Vtransazione> getTransazioneViewByApp(Credentials auth, String appid) throws RemoteException, DaoException, AuthException, MissingParameterException;

	public List<Transazione> getTransazioneById(Credentials auth, String idTransazione) throws RemoteException, DaoException, AuthException, MissingParameterException;

	public List<Vtransazione> getTransazioneViewById(Credentials auth, String idTransazione) throws RemoteException, DaoException, AuthException, MissingParameterException;

	public List<Vtransazione> getTransazioneWithFilters(Credentials auth, String appId, long codstato, Date datastart, Date dataend) throws RemoteException, DaoException, AuthException,
			MissingParameterException;

	public Transazione setTransazione(Credentials auth, Transazione transazione) throws RemoteException, DaoException, AuthException, MissingParameterException;

	public List<StatoTransazione> getStatiTransazione(Credentials auth) throws RemoteException, DaoException, AuthException, MissingParameterException;

	public MdpCurrency getMdpCurrencyByKey(Credentials auth, String gatewayid, String mdpcurrencycode, String gtwcurrencycode) throws RemoteException, DaoException, AuthException,
			MissingParameterException;

	public List<MdpCurrency> getMdpCurrencyByGatewayId(Credentials auth, String gatewayid) throws RemoteException, DaoException, AuthException, MissingParameterException;

	public List<Language> getLanguagesByGatewayId(Credentials auth, String gatewayid) throws RemoteException, DaoException, AuthException, MissingParameterException;

	public List<Language> getLanguages(Credentials auth) throws RemoteException, DaoException, AuthException, MissingParameterException;

	public void setLanguage(Credentials auth, Language lang) throws RemoteException, DaoException, AuthException, MissingParameterException;

	public MdpBckusers getMdpUsersById(Credentials auth, int userid) throws RemoteException, DaoException, AuthException, MissingParameterException;

	public MdpBckusers getMdpUsersByCfisc(Credentials auth, String cfisc) throws RemoteException, DaoException, AuthException, MissingParameterException;

	public List<MdpBckofficegroups> getMdpBckGroupsByCfisc(Credentials auth, String cfisc) throws RemoteException, DaoException, AuthException, MissingParameterException;

	public List<MdpBckofficegroups> getMdpBckGroups(Credentials auth) throws RemoteException, DaoException, AuthException, MissingParameterException;

	public List<Verrori> getErrorList(Credentials auth, String appId, String transactionId, Date datastart, Date dataend, String gatewayId) throws DaoException, AuthException, RemoteException,
			MissingParameterException;

	public List<Auditactions> getAuditActionsList(Credentials auth) throws DaoException, AuthException, RemoteException, MissingParameterException;

	public VtransazioneResult getTransazioneViewPaged(Credentials auth, int pagina, int transazioniPerPagina) throws RemoteException, DaoException, AuthException, MissingParameterException;

	public VtransazioneResult getTransazioneViewWithFiltersPaged(Credentials auth, String appId, long codstato, Date datastart, Date dataend, int pagina, int transazioniPerPagina)
			throws RemoteException, DaoException, AuthException, MissingParameterException;

	public List<Gatewaycustomfields> getGatewayCustomFields(Credentials auth, String gwId) throws RemoteException, DaoException, MissingParameterException, AuthException;

	public void setGatewayCustomFields(Credentials auth, Gatewaycustomfields[] app) throws RemoteException, DaoException, MissingParameterException, AuthException;

	public ApplicationConfiguration getApplicationConfiguration(Credentials auth, String appId) throws DaoException, AuthException, RemoteException, MissingParameterException;

	public List<Applicationcustomfields> getApplicationCustomFieldsByGateway(Credentials auth, String appId, String gatewayId) throws RemoteException, DaoException, MissingParameterException,
	NamingException, AuthException;

	public List<Config> getBoConfig(Credentials auth) throws DaoException, AuthException, RemoteException, MissingParameterException;

	public void setBoConfig(Credentials auth, Config conf) throws DaoException, AuthException, RemoteException, MissingParameterException;

	public void deleteBoConfig(Credentials auth, Config conf) throws DaoException, AuthException, RemoteException, MissingParameterException;

	public List<Commission> getTipoCommissione(Credentials auth) throws DaoException, AuthException, RemoteException, MissingParameterException;

	public void uploadMethod(Credentials auth, String fileName, byte[] fileContent) throws IOException, DaoException, AuthException, RemoteException;

	public void uploadMethodForApplication(Credentials auth, String fileName, byte[] fileContent, String appId) throws IOException, DaoException, AuthException, RemoteException;

	public String uploadMethodForApplicationGateway(Credentials auth, String fileName, byte[] fileContent, String appId, String gatewayId) throws IOException, AuthException, DaoException;

	public Transazione confirmPayment(Credentials auth, String transactionid) throws RemoteException, DaoException, MissingParameterException, NamingException;

	public Transazione refundPayment(Credentials auth, String transactionid) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException;

	public void deleteGatewayConfiguration(Credentials auth, String gatewayId) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException;

	public void deleteApplicationDetail(Credentials auth, String applicationId, String gatewayId, String paymodeid) throws RemoteException, DaoException, MissingParameterException, NamingException,
			AuthException;

	public void deleteApplication(Credentials auth, String applicationId) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException;

	public void deleteApplicationCustomFields(Credentials auth, String applicationId, String gatewayid) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException;

	public void deleteGatewayCustomFields(Credentials auth, String gatewayid) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException;

	public void deleteGatewayDetail(Credentials auth, String gatewayid, String gatewaypaymodeid) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException;

	public void deleteGateway(Credentials auth, String gatewayid) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException;

	public void deleteMdpBckGroup(Credentials auth, int idgroup) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException;

	public void deleteMdpBckUser(Credentials auth, int userid) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException;

	public MdpBckofficegroups getMdpBckGroupsById(Credentials auth, int groupid) throws RemoteException, DaoException, AuthException, MissingParameterException;

	// metodo di estrazione dati dalla tabella gde (giornale degli eventi)
	public List<GiornaleEventoDTO> getGiornaleEventoByParam(Credentials auth, String iuv, String dataOraEvento, String identificativodominio, String identificativofruitore, String codiceContesto)
			throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException;

	public GiornaleEventoDTO getGiornaleEventoById(Credentials auth, Integer id) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException;

	public List<RPTDTO> getRPTByParam(Credentials auth, Integer id, String applicationId, String transactionId, Date lastUpdateDa, Date lastUpdateA, Date insertDateDa, Date insertDateA, String iuv,
			String idStatiRpt, String idMsgRichiesta) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException;

	public List<RTDTO> getRTByParam(Credentials auth, Integer id, String applicationId, String transactionId, Date lastUpdateDa, Date lastUpdateA, Date insertDateDa, Date insertDateA, String iuv,
			String idEsitoPagamento, String idMsgRichiesta) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException;

	public List<StatiRptDTO> getStatiRptAll(Credentials auth) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException;

	public List<CodiciEsitoPagamentoDTO> getCodiciEsitoPagamentoAll(Credentials auth) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException;

	public void insertEnte(Credentials auth, String EnteId, String partitaIva, String descrizione, String attivo) throws RemoteException, DaoException, MissingParameterException, NamingException,
			AuthException;

	public void updateEnte(Credentials auth, String EnteId, String partitaIva, String descrizione, String attivo) throws RemoteException, DaoException, MissingParameterException, NamingException,
			AuthException;

	public void deleteEnte(Credentials auth, String enteId) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException;

	public List<EntiDTO> findEntiAll(Credentials auth) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException;

	public List<EntiDTO> getEntiByParam(Credentials auth, String EnteId, String partitaIva, String descrizione, String attivo) throws RemoteException, DaoException, MissingParameterException,
			NamingException, AuthException;

	public List<EntiDTO> getEntiByApplicationId(Credentials auth, String idApplicazione) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException;

	public Integer insRelEnteApplication(Credentials auth, String idApplicazione, String enteId) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException;

	public Integer delRelEnteApplication(Credentials auth, String idApplicazione, String enteId) throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException;

	public List<InformativePSP> getInformativePSPByParam(Credentials auth, Integer idinformativapsp, String identificativoFlusso, String identificativoPSP, String ragioneSociale,
			Date dataPubblicazione, Date dataInizioValidita, String urlInformazioniPSP, Integer stornoPagamento, String identificativoIntermediario, String identificativoCanale,
			String tipoVersamento, Integer modelloPagamento, Integer priorita, String disponibilitaServizio, String descrizioneServizio, String condizioniEconomicheMassime,
			String urlInformazioniCanale, Date datainserimento, String statoinserimento, Integer ordinamento, String origine) throws RemoteException, DaoException, MissingParameterException,
			NamingException, AuthException;

	public List<TipoVersamentoDTO> getListaTipoversamento(Credentials auth) throws RemoteException, DaoException, MissingParameterException,
	NamingException, AuthException;

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
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws NamingException
	 * @throws AuthException
	 */
	public List<FlussoRiversamentoDTO> getFlussoRiversamentoByParam(Credentials auth, Integer id, String identificativopsp, String identificativoflusso, String versioneoggetto,
			String identificativounivocoregolamento, String identificativoistitutomittente, String identificativoistitutoricevente, Integer numerototalepagamenti, Double importototalepagamenti,
			Date dataoraflusso, Date dataregolamentoDa, Date dataregolamentoA, Date datainserimento, Date datamodifica, String xmlflusso, String denominazionemittente, String denominazionericevente)
			throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException;

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
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 * @throws NamingException
	 * @throws AuthException
	 */
	public List<FlussoSingoloPagamentoDTO> getFlussoSingoloPagamentoByParam(Credentials auth,Integer id, Integer idFlusso, String iuv, String identificativounivocoriscossione, Double singoloimportopagato,
			String codiceesitosingolopagamento, Date dataesitosingolopagamento, Date datainserimento, Date datamodifica, String applicationId, Date dataregolamentoDa, Date dataregolamentoA)
			throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException;

	public List<StatisticaApplicazioneTransazione> getStatisticaApplicazioneTransazione(Credentials auth,String applicationId, Date dateDa,Date dateA)  throws RemoteException, DaoException, MissingParameterException,
	NamingException, AuthException;

	public List<FlussoRiversamentoDTO> estraiFlussiDaServizio(Credentials auth) throws RemoteException, DaoException,
	MissingParameterException, AuthException;

	public List<IbanEnteApplication> getIbanEnteApplicationByParam(
			Credentials auth,
			Integer id,
			String applicationId,
			String idEnte,
			String tipoversamento,
			String identificativopsp,
			String iban,
			Date dataInizioValidita,
			Date dataFineValidita,
			String attivo) throws RemoteException, DaoException, MissingParameterException,
			NamingException, AuthException;

	
	public void insertIbanEnteApplication(
			Credentials auth,
			String applicationId,
			String idEnte,
			String tipoversamento,
			String identificativopsp,
			String iban,
			Date dataInizioValidita,
			Date dataFineValidita,
			String attivo) throws RemoteException, DaoException, MissingParameterException,
			NamingException, AuthException;


	public void updateIbanEnteApplication(
			Credentials auth,
			Integer id,
			String applicationId,
			String idEnte,
			String tipoversamento,
			String identificativopsp,
			String iban,
			Date dataInizioValidita,
			Date dataFineValidita,
			String attivo) throws RemoteException, DaoException, MissingParameterException,
			NamingException, AuthException;
	
	/*
	 * 
	 * public void setMdpCurrency (Credentials auth , MdpCurrency ccy) throws
	 * RemoteException , DaoException, AuthException,MissingParameterException;
	 * public List<MdpBckusers> getMdpUsers (Credentials auth) throws
	 * RemoteException , DaoException, AuthException,MissingParameterException;
	 * public MdpBckusers getMdpUsers (Credentials auth, String userid) throws
	 * RemoteException , DaoException, AuthException,MissingParameterException;
	 * public List<MdpBckusers> getMdpUsers (Credentials auth,
	 * MdpBckofficegroups group) throws RemoteException , DaoException,
	 * AuthException,MissingParameterException; public List<MdpBckusers>
	 * getMdpUsers (Credentials auth, Application app) throws RemoteException ,
	 * DaoException, AuthException,MissingParameterException; public
	 * List<MdpBckusers> getMdpUsers (Credentials auth, Application app,
	 * MdpBckofficegroups group) throws RemoteException , DaoException,
	 * AuthException,MissingParameterException; public List<MdpBckofficegroups>
	 * getMdpGroups (Credentials auth, String groupid) throws RemoteException ,
	 * DaoException, AuthException,MissingParameterException; public
	 * List<MdpBckofficegroups> getMdpGroups (Credentials auth) throws
	 * RemoteException , DaoException, AuthException,MissingParameterException;
	 * public List<MdpBckofficegroups> getMdpGroups (Credentials auth,
	 * Application app) throws RemoteException , DaoException,
	 * AuthException,MissingParameterException; public List<MdpBckofficegroups>
	 * getMdpGroups (Credentials auth, MdpBckroles role) throws RemoteException
	 * , DaoException, AuthException,MissingParameterException; public
	 * MdpBckusers setMdpBckuser (Credentials auth) throws RemoteException ,
	 * DaoException, AuthException,MissingParameterException; public MdpBckroles
	 * setMdpAppRole (Credentials auth, MdpBckroles role, Application app)
	 * throws RemoteException , DaoException,
	 * AuthException,MissingParameterException;
	 */
	public boolean testResources() throws RemoteException, it.csi.csi.wrapper.SystemException;

	public boolean isAlive() throws RemoteException;
}
