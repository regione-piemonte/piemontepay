/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.boservices.interfacecxf;

import it.csi.csi.wrapper.SystemException;
import it.csi.mdp.boservices.business.manager.bean.BoServicesBeanImpl;
import it.csi.mdp.boservices.dto.CodiciEsitoPagamentoDTO;
import it.csi.mdp.boservices.dto.EntiDTO;
import it.csi.mdp.boservices.dto.FlussoRiversamentoDTO;
import it.csi.mdp.boservices.dto.FlussoSingoloPagamentoDTO;
import it.csi.mdp.boservices.dto.GiornaleEventoDTO;
import it.csi.mdp.boservices.dto.IbanEnteApplicationDTO;
import it.csi.mdp.boservices.dto.InformativePSPDTO;
import it.csi.mdp.boservices.dto.ParametroNodoDTO;
import it.csi.mdp.boservices.dto.RPTDTO;
import it.csi.mdp.boservices.dto.RTDTO;
import it.csi.mdp.boservices.dto.StatiRptDTO;
import it.csi.mdp.boservices.dto.StatisticaApplicazioneTransazioneDTO;
import it.csi.mdp.boservices.dto.TipoVersamentoDTO;
import it.csi.mdp.boservices.exception.AuthException;
import it.csi.mdp.boservices.util.Credentials;
import it.csi.mdp.boservices.util.VtransazioneResult;
import it.csi.mdp.core.business.dto.Application;
import it.csi.mdp.core.business.dto.ApplicationConfiguration;
import it.csi.mdp.core.business.dto.ApplicationDetail;
import it.csi.mdp.core.business.dto.Applicationcustomfields;
import it.csi.mdp.core.business.dto.Auditactions;
import it.csi.mdp.core.business.dto.Commission;
import it.csi.mdp.core.business.dto.Config;
import it.csi.mdp.core.business.dto.CsiLogAudit;
import it.csi.mdp.core.business.dto.Gateway;
import it.csi.mdp.core.business.dto.Gatewaycustomfields;
import it.csi.mdp.core.business.dto.Gatewaydetail;
import it.csi.mdp.core.business.dto.IbanEnteApplication;
import it.csi.mdp.core.business.dto.Language;
import it.csi.mdp.core.business.dto.MdpBckofficegroupappmapping;
import it.csi.mdp.core.business.dto.MdpBckofficegroups;
import it.csi.mdp.core.business.dto.MdpBckroles;
import it.csi.mdp.core.business.dto.MdpBckrolesgroupmap;
import it.csi.mdp.core.business.dto.MdpBckusers;
import it.csi.mdp.core.business.dto.MdpBckusersgroup;
import it.csi.mdp.core.business.dto.MdpCurrency;
import it.csi.mdp.core.business.dto.Paymentmode;
import it.csi.mdp.core.business.dto.StatoTransazione;
import it.csi.mdp.core.business.dto.TipoVersamento;
import it.csi.mdp.core.business.dto.Transazione;
import it.csi.mdp.core.business.dto.Verrori;
import it.csi.mdp.core.business.dto.Vtransazione;
import it.csi.mdp.core.business.exceptions.DaoException;
import it.csi.mdp.boservices.exception.MissingParameterException;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import javax.jws.*;
import javax.jws.soap.*;
import javax.jws.soap.SOAPBinding.*;
import javax.naming.NamingException;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;


@WebService(name="BoServices", targetNamespace = "http://interfacecxf.boservices.mdp.csi.it/")
@SOAPBinding(style=Style.DOCUMENT, use=Use.LITERAL)
public interface IMdpBoServicesCxf
{
	
	@WebMethod(operationName="listApplicationByFlussoApplicazione")
	public List<Application> listApplicationByFlussoApplicazione(@WebParam(name = "auth")Credentials auth) throws RemoteException, DaoException, AuthException, MissingParameterException;

	@WebMethod(operationName="setApplication")
	public void setApplication (@WebParam(name = "auth")Credentials auth ,@WebParam(name = "app") Application app,@WebParam(name = "overwrite")boolean overwrite) throws RemoteException , DaoException, AuthException,MissingParameterException;
	
	@WebMethod(operationName="getApplication")
	@WebResult( name="applicationList")
	public List<Application> getApplication (@WebParam(name = "auth")Credentials auth) throws RemoteException , DaoException, AuthException,MissingParameterException;
	
	@WebMethod(operationName="getApplicationById")
	@WebResult( name="application")
	public Application getApplicationById (@WebParam(name = "auth")Credentials auth, @WebParam(name = "appId")String appId) throws RemoteException , DaoException, AuthException,MissingParameterException;
	
	@WebMethod(operationName="setApplicationDetail")
	public void setApplicationDetail (@WebParam(name = "auth")Credentials auth , @WebParam(name = "app")ApplicationDetail app) throws RemoteException , DaoException, AuthException,MissingParameterException;
	
	@WebMethod(operationName="getApplicationDetailById")
	@WebResult( name="applicationDetailList")
	public List<ApplicationDetail> getApplicationDetailById (@WebParam(name = "auth")Credentials auth , @WebParam(name = "appId")String appid) throws RemoteException , DaoException, AuthException,MissingParameterException;
	
	@WebMethod(operationName="getApplicationDetail")
	@WebResult( name="applicationDetailList")
	public List<ApplicationDetail> getApplicationDetail (@WebParam(name = "auth")Credentials auth) throws RemoteException , DaoException, AuthException,MissingParameterException;
	 
	@WebMethod(operationName="getApplicationCustomFields")
	@WebResult( name="applicationCustomFieldsList")
	public List<Applicationcustomfields> getApplicationCustomFields (@WebParam(name = "auth")Credentials auth , @WebParam(name = "appId")String appId) throws RemoteException , DaoException, AuthException,MissingParameterException;
	
	@WebMethod(operationName="setApplicationCustomFields")
	public void setApplicationCustomFields (@WebParam(name = "auth")Credentials auth , @WebParam(name = "appcustfields")Applicationcustomfields [] app) throws RemoteException , DaoException, AuthException,MissingParameterException;
	
	@WebMethod(operationName="setGateway")
	@WebResult( name="gateway")
	public Gateway setGateway (@WebParam(name = "auth")Credentials auth , @WebParam(name = "gateway")Gateway app) throws RemoteException , DaoException, AuthException,MissingParameterException;
	
	@WebMethod(operationName="getGatewayById")
	@WebResult( name="gateway")
	public Gateway getGatewayById (@WebParam(name = "auth")Credentials auth ,@WebParam(name = "gatewayId") String gwId) throws RemoteException , DaoException, AuthException,MissingParameterException;
	
	@WebMethod(operationName="getGateways")
	@WebResult( name="gatewayList")
	public List<Gateway> getGateway (@WebParam(name = "auth")Credentials auth ) throws RemoteException , DaoException, AuthException;
	
	@WebMethod(operationName="setPaymentMode")
	@WebResult( name="paymentmode")
	public Paymentmode setPaymentMode (@WebParam(name = "auth")Credentials auth , @WebParam(name = "paymentmode")Paymentmode paymode) throws RemoteException , DaoException, AuthException,MissingParameterException;
	
	@WebMethod(operationName="getPaymentMode")
	@WebResult( name="paymentmodeList")	
	public List<Paymentmode> getPaymentMode (@WebParam(name = "auth")Credentials auth) throws RemoteException , DaoException, AuthException,MissingParameterException;
	
	@WebMethod(operationName="getPaymentModeById")
	@WebResult( name="paymentmode")	
	public Paymentmode getPaymentModeById (@WebParam(name = "auth")Credentials auth, @WebParam(name = "payModeId")String payModeId) throws RemoteException , DaoException, AuthException,MissingParameterException;
	
	@WebMethod(operationName="setGatewayDetail")
	@WebResult( name="gatewayDetail")	
	public Gatewaydetail setGatewayDetail (@WebParam(name = "auth")Credentials auth , @WebParam(name = "gatewayDetail")Gatewaydetail app) throws RemoteException , DaoException, AuthException,MissingParameterException;
	
	@WebMethod(operationName="getGatewayDetailByIds")
	@WebResult( name="gatewayDetail")
	public Gatewaydetail getGatewayDetailByIds (@WebParam(name = "auth")Credentials auth , @WebParam(name = "gatewayId")String gwId, @WebParam(name = "paymentModeId")String payId) throws RemoteException , DaoException, AuthException,MissingParameterException;
	
	@WebMethod(operationName="getGatewayDetailById")
	@WebResult( name="gatewayDetailList")
	public List<Gatewaydetail> getGatewayDetailById (@WebParam(name = "auth")Credentials auth ,@WebParam(name = "gatewayId") String gwId) throws RemoteException , DaoException, AuthException,MissingParameterException;
	
	@WebMethod(operationName="getGatewayDetail")
	@WebResult( name="gatewayDetailList")
	public List<Gatewaydetail> getGatewayDetail (@WebParam(name = "auth")Credentials auth ) throws RemoteException , DaoException, AuthException,MissingParameterException;
	
	@WebMethod(operationName="getMdpCurrency")
	@WebResult( name="mdpCurrencyList")
	public List<MdpCurrency> getMdpCurrency(@WebParam(name = "auth")Credentials auth) throws RemoteException , DaoException, AuthException,MissingParameterException;
	
	@WebMethod(operationName="getMdpBckRoles")
	@WebResult( name="mdpRolesList")
	public List<MdpBckroles> getMdpBckRoles (@WebParam(name = "auth")Credentials auth) throws RemoteException , DaoException, AuthException,MissingParameterException;
	
	@WebMethod(operationName="setMdpRole")
	@WebResult( name="mdpRole")
	public MdpBckroles setMdpRole (@WebParam(name = "auth")Credentials auth, @WebParam(name = "role")MdpBckroles role) throws RemoteException , DaoException, AuthException,MissingParameterException;
	
	@WebMethod(operationName="setMdpUser")
	@WebResult( name="mdpUser")
	public MdpBckusers setMdpUser (@WebParam(name = "auth")Credentials auth, @WebParam(name = "user")MdpBckusers user, @WebParam(name = "usergrouplist")List<MdpBckusersgroup> usergroup) throws RemoteException , DaoException, AuthException,MissingParameterException;
	
	@WebMethod(operationName="setMdpGroup")
	@WebResult( name="mdpGroup")
	public MdpBckofficegroups setMdpGroup (@WebParam(name = "auth")Credentials auth, @WebParam(name = "group")MdpBckofficegroups group, @WebParam(name = "appslist")List<MdpBckofficegroupappmapping> groupapp, @WebParam(name = "grouprole")MdpBckrolesgroupmap grouprole) throws RemoteException , DaoException, AuthException,MissingParameterException;
	
	
	
	@WebMethod(operationName="getAuditing")
	@WebResult( name="auditingList")
	public List<CsiLogAudit> getAuditing(@WebParam(name = "auth")Credentials auth ,@WebParam(name = "applicationListFilter")List<Application> apps, @WebParam(name = "datestartFilter")Date datestart, 
			@WebParam(name = "dateendFilter")Date dateend, @WebParam(name = "transactionidFilter")String transactionid, @WebParam(name = "actionsFilter")List<String> actions, @WebParam(name = "usersFilter")List<String> users) throws RemoteException , DaoException, AuthException,MissingParameterException;
	
	
	@WebMethod(operationName="getTransazione")
	@WebResult( name="transazioneList")
	public List<Transazione> getTransazione(@WebParam(name = "auth")Credentials auth) throws RemoteException , DaoException, AuthException,MissingParameterException;
	
	@WebMethod(operationName="getTransazioneByApp")
	@WebResult( name="transazioneList")
	public List<Transazione> getTransazioneByApp(@WebParam(name = "auth")Credentials auth, @WebParam(name = "applicationId")String appid) throws RemoteException , DaoException, AuthException,MissingParameterException;
	
	@WebMethod(operationName="getTransazioneById")
	@WebResult( name="transazioneList")
	public List<Transazione> getTransazioneById(@WebParam(name = "auth")Credentials auth, @WebParam(name = "idTransazione")String idTransazione) throws RemoteException , DaoException, AuthException,MissingParameterException;
	
	@WebMethod(operationName="getTransazioneViewById")
	@WebResult( name="transazioneViewList")
	public List<Vtransazione> getTransazioneViewById(@WebParam(name = "auth")Credentials auth, @WebParam(name = "idTransazione")String idTransazione) throws RemoteException , DaoException, AuthException,MissingParameterException;
	
	
	@WebMethod(operationName="getTransazioneWithFilters")
	@WebResult( name="transazioneViewList")
	public List<Vtransazione>getTransazioneWithFilters(@WebParam(name = "auth")Credentials auth, @WebParam(name = "applicationId")String appId, @WebParam(name = "codStatoTransazione")long codstato, @WebParam(name = "datastartFilter")Date datastart, @WebParam(name = "dataendFilter")Date dataend) throws RemoteException , DaoException, AuthException,MissingParameterException;
	
	@WebMethod(operationName="setTransazione")
	@WebResult( name="transazione")	
	public Transazione setTransazione (@WebParam(name = "auth")Credentials auth, @WebParam(name = "transazione")Transazione transazione) throws RemoteException , DaoException, AuthException,MissingParameterException;
	
	@WebMethod(operationName="getStatiTransazione")
	@WebResult( name="statiTransazioneList")	
	public List<StatoTransazione> getStatiTransazione(@WebParam(name = "auth")Credentials auth) throws RemoteException , DaoException, AuthException,MissingParameterException;

	@WebMethod(operationName="getMdpCurrencyByKey")
	@WebResult( name="mdpCurrency")	
	public MdpCurrency getMdpCurrencyByKey(@WebParam(name = "auth")Credentials auth, @WebParam(name = "gatewayid")String gatewayid, @WebParam(name = "mdpcurrencycode")String mdpcurrencycode, @WebParam(name = "gtwcurrencycode")String gtwcurrencycode ) throws RemoteException , DaoException, AuthException,MissingParameterException;

	@WebMethod(operationName="getMdpCurrencyByGatewayId")
	@WebResult( name="mdpCurrencyList")	
	public List<MdpCurrency> getMdpCurrencyByGatewayId(@WebParam(name = "auth")Credentials auth, @WebParam(name = "gatewayid")String gatewayid) throws RemoteException, DaoException, AuthException, MissingParameterException;
	
	@WebMethod(operationName="getLanguagesByGatewayId")
	@WebResult( name="mdpLanguageList")	
	public List<Language> getLanguagesByGatewayId(@WebParam(name = "auth")Credentials auth, @WebParam(name = "gatewayid")String gatewayid) throws RemoteException, DaoException, AuthException, MissingParameterException;


	@WebMethod(operationName="getLanguages")
	@WebResult( name="mdpLanguageList")	
	public List<Language> getLanguages(@WebParam(name = "auth")Credentials auth) throws RemoteException, DaoException, AuthException, MissingParameterException;


	@WebMethod(operationName="setLanguage")
	public void setLanguage(@WebParam(name = "auth")Credentials auth,@WebParam(name = "language")Language lang ) throws RemoteException, DaoException, AuthException, MissingParameterException;

	
	@WebMethod(operationName="setMdpCurrency")	
	public void setMdpCurrency (@WebParam(name = "auth")Credentials auth , @WebParam(name = "mdpCurrency")MdpCurrency ccy) throws RemoteException , DaoException, AuthException,MissingParameterException;
	
	@WebMethod(operationName="getMdpUsers")
	@WebResult( name="usersList")	
	public List<MdpBckusers> getMdpUsers (@WebParam(name = "auth")Credentials auth) throws RemoteException , DaoException, AuthException,MissingParameterException;
	
	@WebMethod(operationName="getMdpUsersById")
	@WebResult( name="user")	
	public MdpBckusers getMdpUsersById (@WebParam(name = "auth")Credentials auth, @WebParam(name = "userid")int userid) throws RemoteException , DaoException, AuthException,MissingParameterException;
	
	@WebMethod(operationName="getMdpUsersByCfisc")
	@WebResult( name="user")
	public MdpBckusers getMdpUsersByCfisc(@WebParam(name = "auth")Credentials auth, @WebParam(name = "codfisc")String cfisc) throws RemoteException, DaoException,
	AuthException, MissingParameterException;
	
	@WebMethod(operationName="getMdpBckGroupsByCfisc")
	@WebResult( name="groupList")
	public List<MdpBckofficegroups> getMdpBckGroupsByCfisc(@WebParam(name = "auth")Credentials auth, @WebParam(name = "codfisc")String cfisc) throws RemoteException, DaoException,
	AuthException, MissingParameterException;
	
	@WebMethod(operationName="getMdpBckGroups")
	@WebResult( name="groupList")
	public List<MdpBckofficegroups> getMdpBckGroups(@WebParam(name = "auth")Credentials auth) throws RemoteException, DaoException,
	AuthException, MissingParameterException;

	
	@WebMethod(operationName="getApplicationConfiguration")
    @WebResult( name="applicationConfiguration")
	public ApplicationConfiguration getApplicationConfiguration (@WebParam(name = "auth")Credentials auth, @WebParam(name = "applicationId")String appId) throws DaoException, AuthException, RemoteException, MissingParameterException;
	
	@WebMethod(operationName="getApplicationCustomFieldsByGateway")
    @WebResult( name="applicationCustomFieldsList")
	public List<Applicationcustomfields> getApplicationCustomFieldsByGateway(@WebParam(name = "auth")Credentials auth, @WebParam(name = "applicationId") String appId, @WebParam(name = "gatewayId")String gatewayId) 		throws RemoteException, DaoException, MissingParameterException, AuthException;
 
	
	@WebMethod(operationName="getErrorList")
    @WebResult( name="errorList")
	public List<Verrori> getErrorList (@WebParam(name = "auth")Credentials auth,@WebParam(name = "applicationId")String appId, 
			@WebParam(name = "transactionId")String transactionId, @WebParam(name = "datastart")Date datastart, @WebParam(name = "dataend")Date dataend, 
			@WebParam(name = "gatewayId")String gatewayId)
	throws DaoException, AuthException, RemoteException, MissingParameterException;
	
	@WebMethod(operationName="getAuditActionsList")
    @WebResult( name="actionsList")
	public List<Auditactions> getAuditActionsList(@WebParam(name = "auth")Credentials auth) throws DaoException, AuthException, RemoteException,
			MissingParameterException;
	
	@WebMethod(operationName="getTransazioneViewPaged")
    @WebResult( name="transactionResult")
	public VtransazioneResult getTransazioneViewPaged(@WebParam(name = "auth")Credentials auth, @WebParam(name = "numpagina")int pagina,
			@WebParam(name = "transazioniPerPagina")int transazioniPerPagina)
	throws RemoteException, DaoException, AuthException, MissingParameterException;
	
	@WebMethod(operationName="getTransazioneViewWithFiltersPaged")
	@WebResult( name="transactionResult")
	public VtransazioneResult getTransazioneViewWithFiltersPaged(@WebParam(name = "auth")Credentials auth, @WebParam(name = "applicationId")String appId, @WebParam(name = "codStatoTransazione")long codstato, @WebParam(name = "datastartFilter")Date datastart, @WebParam(name = "dataendFilter")Date dataend, @WebParam(name = "numpagina")int pagina,
			@WebParam(name = "transazioniPerPagina")int transazioniPerPagina) throws RemoteException, DaoException, AuthException, MissingParameterException;
	
	@WebMethod(operationName="getBoConfig")
	@WebResult( name="configList")
	public List<Config> getBoConfig(@WebParam(name = "auth")Credentials auth) 
		throws DaoException, AuthException, RemoteException, MissingParameterException;
	
	@WebMethod(operationName="setBoConfig")
	public void setBoConfig(@WebParam(name = "auth")Credentials auth,@WebParam(name = "config") Config conf) 
		throws DaoException, AuthException, RemoteException, MissingParameterException;
	
	
	@WebMethod(operationName="deleteBoConfig")
	public void deleteBoConfig(@WebParam(name = "auth")Credentials auth,@WebParam(name = "config") Config conf) 
		throws DaoException, AuthException, RemoteException, MissingParameterException;

	@WebMethod(operationName="getGatewayCustomFields")
	@WebResult( name="gatewayCustomFieldsList")
	public List<Gatewaycustomfields> getGatewayCustomFields(@WebParam(name = "auth")Credentials auth, @WebParam(name = "gatewayid")String gwId) throws RemoteException,
	DaoException, MissingParameterException, AuthException;
	
	@WebMethod(operationName="setGatewayCustomFields")
	public void setGatewayCustomFields(@WebParam(name = "auth")Credentials auth, @WebParam(name = "gatewayCustomFieldsList")Gatewaycustomfields[] app) throws RemoteException,
	DaoException, MissingParameterException, AuthException;
	
	
	@WebMethod(operationName="getTipoCommissione")
	@WebResult( name="tipocommissioneList")
	public List<Commission> getTipoCommissione(@WebParam(name = "auth")Credentials auth) throws DaoException, AuthException,
	RemoteException, MissingParameterException;
	
	/*
	public List<MdpBckusers> getMdpUsers (Credentials auth, MdpBckofficegroups group) throws RemoteException , DaoException, AuthException,MissingParameterException;
	public List<MdpBckusers> getMdpUsers (Credentials auth, Application app) throws RemoteException , DaoException, AuthException,MissingParameterException;
	public List<MdpBckusers> getMdpUsers (Credentials auth, Application app, MdpBckofficegroups group) throws RemoteException , DaoException, AuthException,MissingParameterException;
	public List<MdpBckofficegroups> getMdpGroups (Credentials auth, String groupid) throws RemoteException , DaoException, AuthException,MissingParameterException;
	public List<MdpBckofficegroups> getMdpGroups (Credentials auth) throws RemoteException , DaoException, AuthException,MissingParameterException;
	public List<MdpBckofficegroups> getMdpGroups (Credentials auth, Application app) throws RemoteException , DaoException, AuthException,MissingParameterException;
	public List<MdpBckofficegroups> getMdpGroups (Credentials auth, MdpBckroles role) throws RemoteException , DaoException, AuthException,MissingParameterException;
	
	*/
	public boolean testResources() throws RemoteException, SystemException;
	public boolean isAlive() throws RemoteException;

	 /**
     * File upload method: enables file transfer from client to server
     *
     * @param myFile
     * @param usr
     * @param pwd
     * @param repo
     * @throws IOException
     */
	
	
	@WebMethod(operationName="uploadMethod")
    void uploadMethod(@WebParam(name = "auth")Credentials auth, @WebParam(name="filename") String fileName
    		,@WebParam(name="fileContent")byte[] fileContent)
		throws  IOException, DaoException, AuthException,RemoteException;
	 
	@WebMethod(operationName="uploadMethodForApplication")
    void uploadMethodForApplication(@WebParam(name = "auth")Credentials auth, @WebParam(name="filename") String fileName,
    		@WebParam(name="fileContent")byte[] fileContent,
			 			  @WebParam(name="applicationId") String appId)
	throws IOException, DaoException, AuthException,RemoteException;

	
	@WebMethod(operationName="uploadMethodForApplicationGateway")
	@WebResult( name="filePath")
	public String  uploadMethodForApplicationGateway(@WebParam(name = "auth")Credentials auth, @WebParam(name="filename")String fileName,
			@WebParam(name="fileContent")byte[] fileContent, @WebParam(name="applicationId")String appId,
			@WebParam(name="gatewayId")String gatewayId) throws IOException, AuthException, DaoException;
	
	
	@WebMethod(operationName="confirmPayment")
	@WebResult( name="transazione")
	public Transazione confirmPayment(@WebParam(name = "auth")Credentials auth, @WebParam(name="transactionid")String transactionid) throws RemoteException, DaoException, MissingParameterException,	NamingException;

	@WebMethod(operationName="refundPayment")
	@WebResult( name="transazione")
	public Transazione refundPayment(@WebParam(name = "auth")Credentials auth, @WebParam(name="transactionid")String transactionid) throws RemoteException, DaoException, MissingParameterException,	NamingException,AuthException;
	
	@WebMethod(operationName="deleteApplicationConfiguration")
	public void deleteApplicationConfiguration (@WebParam(name = "auth")Credentials auth, @WebParam(name="applicationId")String applicationId) throws RemoteException, DaoException,
	MissingParameterException, NamingException, AuthException;
	
	@WebMethod(operationName="deleteGatewayConfiguration")
	public void deleteGatewayConfiguration (@WebParam(name = "auth")Credentials auth, @WebParam(name="gatewayId")String gatewayId) throws RemoteException, DaoException,
	MissingParameterException, NamingException, AuthException;
	
	@WebMethod(operationName="deleteApplicationDetail")
	public void deleteApplicationDetail(@WebParam(name = "auth")Credentials auth, @WebParam(name="applicationId")String applicationId, @WebParam(name="gatewayId")String gatewayId, @WebParam(name="paymodeid")String paymodeid)  throws RemoteException, DaoException,
	MissingParameterException, NamingException, AuthException;
	
	@WebMethod(operationName="deleteApplication")
	public void deleteApplication(@WebParam(name = "auth")Credentials auth, @WebParam(name="applicationId")String applicationId)  throws RemoteException, DaoException,
	MissingParameterException, NamingException, AuthException;
	
	@WebMethod(operationName="deleteApplicationCustomFields")
	public void deleteApplicationCustomFields(@WebParam(name = "auth")Credentials auth, @WebParam(name="gatewayId")String applicationId, @WebParam(name="gatewayId")String gatewayid)  throws RemoteException, DaoException,
	MissingParameterException, NamingException, AuthException;
	
	@WebMethod(operationName="deleteGatewayCustomFields")
	public void deleteGatewayCustomFields(Credentials auth, @WebParam(name="gatewayId")String gatewayid)  throws RemoteException, DaoException,
	MissingParameterException, NamingException, AuthException;
	
	@WebMethod(operationName="deleteGatewayDetail")
	public void deleteGatewayDetail(@WebParam(name = "auth")Credentials auth, @WebParam(name="gatewayId")String gatewayid, @WebParam(name="gatewaypaymodeid")String gatewaypaymodeid)  throws RemoteException, DaoException,
	MissingParameterException, NamingException, AuthException;
	
	@WebMethod(operationName="deleteGateway")
	public void deleteGateway(@WebParam(name = "auth")Credentials auth, @WebParam(name="gatewayId")String gatewayid)  throws RemoteException, DaoException,
	MissingParameterException, NamingException, AuthException;
	
	@WebMethod(operationName="deleteMdpBckGroup")
	public void deleteMdpBckGroup (@WebParam(name = "auth")Credentials auth, @WebParam(name = "idgroup")int idgroup) throws RemoteException, DaoException,
	MissingParameterException, NamingException, AuthException;
	
	@WebMethod(operationName="deleteMdpBckUser")
	public void deleteMdpBckUser (@WebParam(name = "auth")Credentials auth, @WebParam(name = "userid")int userid) throws RemoteException, DaoException,
	MissingParameterException, NamingException, AuthException;
	
	@WebMethod(operationName="getMdpBckGroupsById")
	public MdpBckofficegroups getMdpBckGroupsById(@WebParam(name = "auth")Credentials auth, @WebParam(name = "idgroup") int groupid) throws RemoteException, DaoException,
	AuthException, MissingParameterException;
	
	@WebMethod(operationName="getGiornaleEventoByParam")
	public List<GiornaleEventoDTO> getGiornaleEventoByParam( 
			@WebParam(name = "auth")Credentials auth,
			@WebParam(name = "iuv")String iuv,
			@WebParam(name = "dataOraEvento")Date dataOraEvento, 
			@WebParam(name = "identificativodominio")String identificativodominio,
			@WebParam(name = "identificativofruitore")String identificativofruitore, 
			@WebParam(name = "codiceContesto")String codiceContesto) throws RemoteException, DaoException,
			MissingParameterException, NamingException, AuthException;
	
	@WebMethod(operationName="getGiornaleEventoById")
	public GiornaleEventoDTO getGiornaleEventoById( 
			@WebParam(name = "auth")Credentials auth,
			@WebParam(name = "id")Integer id) throws RemoteException, DaoException,
			MissingParameterException, NamingException, AuthException;
	
	///////////////////////////
	@WebMethod(operationName="getRPTByParam")
	public List<RPTDTO> getRPTByParam(@WebParam(name = "auth")Credentials auth,
								@WebParam(name = "id")Integer id ,
								@WebParam(name = "applicationId")String applicationId, 
								@WebParam(name = "transactionId")String transactionId, 
								@WebParam(name = "lastUpdateDa")Date lastUpdateDa, 
								@WebParam(name = "lastUpdateA")Date lastUpdateA, 
								@WebParam(name = "insertUpdateDa")Date insertDateDa,
								@WebParam(name = "insertUpdateA")Date insertDateA,
								@WebParam(name = "iuv")String iuv, 
								@WebParam(name = "idStatiRpt")String idStatiRpt ,
								@WebParam(name = "idMsgRichiesta")String idMsgRichiesta		
	) throws RemoteException, DaoException,
	MissingParameterException, NamingException, AuthException;

	@WebMethod(operationName="getRTByParam")
	public List<RTDTO> getRTByParam(@WebParam(name = "auth")Credentials auth,
								@WebParam(name = "id")Integer id ,
								@WebParam(name = "applicationId")String applicationId,
								@WebParam(name = "transactionId")String transactionId,
								@WebParam(name = "lastUpdateDa")Date lastUpdateDa,
								@WebParam(name = "lastUpdateA")Date lastUpdateA,
								@WebParam(name = "insertUpdateDa")Date insertDateDa,
								@WebParam(name = "insertUpdateA")Date insertDateA,
								@WebParam(name = "iuv")String iuv,
								@WebParam(name = "idEsitoPagamento")String idEsitoPagamento,
								@WebParam(name = "idMsgRichiesta")String idMsgRichiesta	
								
	) throws RemoteException, DaoException,
	MissingParameterException, NamingException, AuthException;
	
	@WebMethod(operationName="getStatiRptAll")
	public List<StatiRptDTO> getStatiRptAll(@WebParam(name = "auth")Credentials auth) throws RemoteException, DaoException,
	MissingParameterException, NamingException, AuthException;
	
	@WebMethod(operationName="getCodiciEsitoPagamentoAll")
	public List<CodiciEsitoPagamentoDTO> getCodiciEsitoPagamentoAll(@WebParam(name = "auth")Credentials auth) throws RemoteException, DaoException,
	MissingParameterException, NamingException, AuthException;

	
	@WebMethod(operationName="insertEnte")
	public void insertEnte(
			@WebParam(name = "auth")Credentials auth,
			@WebParam(name = "enteId")String enteId ,
			@WebParam(name = "partitaIva")String partitaIva, 
			@WebParam(name = "descrizione")String descrizione,
			@WebParam(name = "attivo")String attivo)
	throws RemoteException, DaoException,
	MissingParameterException, NamingException, AuthException;

	@WebMethod(operationName="updateEnte")
	public void updateEnte(
			@WebParam(name = "auth")Credentials auth,
			@WebParam(name = "enteId")String enteId ,
			@WebParam(name = "partitaIva")String partitaIva, 
			@WebParam(name = "descrizione")String descrizione,
			@WebParam(name = "attivo")String attivo)
	throws RemoteException, DaoException,
	MissingParameterException, NamingException, AuthException;

	@WebMethod(operationName="deleteEnte")
	public void deleteEnte(@WebParam(name = "auth")Credentials auth,@WebParam(name = "enteId")String enteId) throws RemoteException, DaoException,
	MissingParameterException, NamingException, AuthException;

	@WebMethod(operationName="findEntiAll")
	public List<EntiDTO> findEntiAll(@WebParam(name = "auth")Credentials auth) throws RemoteException, DaoException,MissingParameterException, NamingException, AuthException;

	
	@WebMethod(operationName="getEntiByParam")
	public List<EntiDTO> getEntiByParam(	
			@WebParam(name = "auth")Credentials auth,
			@WebParam(name = "enteId")String enteId ,
			@WebParam(name = "partitaIva")String partitaIva, 
			@WebParam(name = "descrizione")String descrizione,
			@WebParam(name = "attivo")String attivo)
			throws RemoteException, DaoException,
			MissingParameterException, NamingException, AuthException;
	
	
	@WebMethod(operationName="getEntiByApplicationId")
	public List<EntiDTO> getEntiByApplicationId( 
			@WebParam(name = "auth")Credentials auth,
			@WebParam(name = "idApplicazione")String idApplicazione) throws RemoteException, DaoException,
			MissingParameterException, NamingException, AuthException;

	
	@WebMethod(operationName="insRelEnteApplication")
	public Integer insRelEnteApplication( 
			@WebParam(name = "auth")Credentials auth,
			@WebParam(name = "idApplicazione")String idApplicazione,
			@WebParam(name = "enteId")String enteId) throws RemoteException, DaoException,
			MissingParameterException, NamingException, AuthException;

	
	@WebMethod(operationName="delRelEnteApplication")
	public Integer delRelEnteApplication(
			@WebParam(name = "auth")Credentials auth, 
			@WebParam(name = "idApplicazione")String idApplicazione,
			@WebParam(name = "enteId")String enteId) throws RemoteException, DaoException,
			MissingParameterException, NamingException, AuthException;

	
	@WebMethod(operationName="getInformativePSPByParam")
	public List<InformativePSPDTO> getInformativePSPByParam(
			@WebParam(name = "auth")Credentials auth,
			@WebParam(name = "idinformativapsp")Integer idinformativapsp,
			@WebParam(name = "identificativoFlusso")String identificativoFlusso,
			@WebParam(name = "identificativoPSP")String identificativoPSP,
			@WebParam(name = "ragioneSociale")String ragioneSociale,
			@WebParam(name = "dataPubblicazione")Date dataPubblicazione,
			@WebParam(name = "dataInizioValidita")Date dataInizioValidita,
			@WebParam(name = "urlInformazioniPSP")String urlInformazioniPSP,
			@WebParam(name = "stornoPagamento")Integer stornoPagamento,
			@WebParam(name = "identificativoIntermediario")String identificativoIntermediario,
			@WebParam(name = "identificativoCanale")String identificativoCanale,
			@WebParam(name = "tipoVersamento")String tipoVersamento,
			@WebParam(name = "modelloPagamento")Integer modelloPagamento,
			@WebParam(name = "priorita")Integer priorita,
			@WebParam(name = "disponibilitaServizio")String disponibilitaServizio,
			@WebParam(name = "descrizioneServizio")String descrizioneServizio,
			@WebParam(name = "condizioniEconomicheMassime")String condizioniEconomicheMassime,
			@WebParam(name = "urlInformazioniCanale")String urlInformazioniCanale,
			@WebParam(name = "datainserimento")Date datainserimento,
			@WebParam(name = "statoinserimento")String statoinserimento,
			@WebParam(name = "ordinamento")Integer ordinamento,
			@WebParam(name = "origine")String origine) throws RemoteException, DaoException,
			MissingParameterException, NamingException, AuthException;

		
	@WebMethod(operationName="getFlussoRiversamentoByParam")
	public List<FlussoRiversamentoDTO> getFlussoRiversamentoByParam(
			@WebParam(name = "auth")Credentials auth,
			@WebParam(name = "id")Integer id,
			@WebParam(name = "identificativopsp")String identificativopsp,
			@WebParam(name = "identificativoflusso")String identificativoflusso,
			@WebParam(name = "versioneoggetto")String versioneoggetto,
			@WebParam(name = "identificativounivocoregolamento")String identificativounivocoregolamento,
			@WebParam(name = "identificativoistitutomittente")String identificativoistitutomittente,
			@WebParam(name = "identificativoistitutoricevente")String identificativoistitutoricevente,
			@WebParam(name = "numerototalepagamenti")Integer numerototalepagamenti,
			@WebParam(name = "importototalepagamenti")Double importototalepagamenti,
			@WebParam(name = "dataoraflusso")Date dataoraflusso,
			@WebParam(name = "dataregolamentoDa")Date      dataregolamentoDa,
			@WebParam(name = "dataregolamentoA")Date      dataregolamentoA,
			@WebParam(name = "datainserimento")Date datainserimento,
			@WebParam(name = "datamodifica")Date datamodifica,
			@WebParam(name = "xmlflusso")String xmlflusso,
			@WebParam(name = "denominazionemittente")String denominazionemittente,
			@WebParam(name = "denominazionericevente")String denominazionericevente) throws RemoteException, DaoException,
			MissingParameterException, NamingException, AuthException;

	
	@WebMethod(operationName="getFlussoSingoloPagamentoByParam")
	public List<FlussoSingoloPagamentoDTO> getFlussoSingoloPagamentoByParam(
			@WebParam(name = "auth")Credentials auth,
			@WebParam(name = "id")Integer id,
			@WebParam(name = "idFlusso")Integer idFlusso,
			@WebParam(name = "iuv")String iuv,
			@WebParam(name = "identificativounivocoriscossione")String identificativounivocoriscossione,
			@WebParam(name = "singoloimportopagato")Double singoloimportopagato,
			@WebParam(name = "codiceesitosingolopagamento")String codiceesitosingolopagamento,
			@WebParam(name = "dataesitosingolopagamento")Date dataesitosingolopagamento,
			@WebParam(name = "datainserimento")Date datainserimento,
			@WebParam(name = "datamodifica")Date datamodifica,
			@WebParam(name = "applicationId")String applicationId,
			@WebParam(name = "dataregolamentoDa")Date      dataregolamentoDa,
			@WebParam(name = "dataregolamentoA")Date      dataregolamentoA) throws RemoteException, DaoException,
			MissingParameterException, NamingException, AuthException;
	
	
	@WebMethod(operationName="getListaTipoversamento")
	public List<TipoVersamentoDTO> getListaTipoversamento(
			@WebParam(name = "auth")Credentials auth) throws RemoteException, DaoException,
			MissingParameterException, NamingException, AuthException;
	
	
	@WebMethod(operationName="getStatisticaApplicazioneTransazione")
	public List<StatisticaApplicazioneTransazioneDTO> getStatisticaApplicazioneTransazione(
			@WebParam(name = "auth")Credentials auth,
			@WebParam(name = "applicationId")String applicationId,
			@WebParam(name = "dateDa")Date dateDa,
			@WebParam(name = "dateA")Date dateA)throws RemoteException, DaoException, MissingParameterException, NamingException, AuthException ;
			
	@WebMethod(operationName="estraiFlussiDaServizio")
	public List<FlussoRiversamentoDTO> estraiFlussiDaServizio(
			@WebParam(name = "auth")Credentials auth) throws RemoteException, DaoException,MissingParameterException,NamingException, AuthException;
	
	@WebMethod(operationName="getIbanEnteApplicationByParam")
	public List<IbanEnteApplicationDTO> getIbanEnteApplicationByParam(
			@WebParam(name = "auth")Credentials auth,
			@WebParam(name = "id")Integer id,
			@WebParam(name = "applicationId")String applicationId,
			@WebParam(name = "idEnte")String idEnte,
			@WebParam(name = "tipoversamento")String tipoversamento,
			@WebParam(name = "identificativopsp")String identificativopsp,
			@WebParam(name = "iban")String iban,
			@WebParam(name = "dataInizioValidita")Date dataInizioValidita,
			@WebParam(name = "dataFineValidita")Date dataFineValidita,
			@WebParam(name = "attivo")String attivo
			) throws RemoteException, DaoException, MissingParameterException,
			NamingException, AuthException;

	@WebMethod(operationName="insertIbanEnteApplication")
	public void insertIbanEnteApplication(
			Credentials auth,
			@WebParam(name = "applicationId")String applicationId,
			@WebParam(name = "idEnte")String idEnte,
			@WebParam(name = "tipoversamento")String tipoversamento,
			@WebParam(name = "identificativopsp")String identificativopsp,
			@WebParam(name = "iban")String iban,
			@WebParam(name = "dataInizioValidita")Date dataInizioValidita,
			@WebParam(name = "dataFineValidita")Date dataFineValidita,
			@WebParam(name = "attivo")String attivo
			) throws RemoteException, DaoException, MissingParameterException,
			NamingException, AuthException;

	@WebMethod(operationName="updateIbanEnteApplication")
	public void updateIbanEnteApplication(
			Credentials auth,
			@WebParam(name = "id")Integer id,
			@WebParam(name = "applicationId")String applicationId,
			@WebParam(name = "idEnte")String idEnte,
			@WebParam(name = "tipoversamento")String tipoversamento,
			@WebParam(name = "identificativopsp")String identificativopsp,
			@WebParam(name = "iban")String iban,
			@WebParam(name = "dataInizioValidita")Date dataInizioValidita,
			@WebParam(name = "dataFineValidita")Date dataFineValidita,
			@WebParam(name = "attivo")String attivo
			) throws RemoteException, DaoException, MissingParameterException,
			NamingException, AuthException;
	
	
}
