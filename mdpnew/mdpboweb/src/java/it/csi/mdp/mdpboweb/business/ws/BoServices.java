/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public interface BoServices extends java.rmi.Remote {
    public it.csi.mdp.mdpboweb.business.ws.InsertIbanEnteApplicationResponse insertIbanEnteApplication(it.csi.mdp.mdpboweb.business.ws.InsertIbanEnteApplication parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.Gatewaydetail[] getGatewayDetail(it.csi.mdp.mdpboweb.business.ws.GetGatewayDetail parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.SetApplicationDetailResponse setApplicationDetail(it.csi.mdp.mdpboweb.business.ws.SetApplicationDetail parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.SetBoConfigResponse setBoConfig(it.csi.mdp.mdpboweb.business.ws.SetBoConfig parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.DelRelEnteApplicationResponse delRelEnteApplication(it.csi.mdp.mdpboweb.business.ws.DelRelEnteApplication parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.Vtransazione[] getTransazioneViewById(it.csi.mdp.mdpboweb.business.ws.GetTransazioneViewById parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.Applicationcustomfields[] getApplicationCustomFields(it.csi.mdp.mdpboweb.business.ws.GetApplicationCustomFields parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.Application[] listApplicationByFlussoApplicazione(it.csi.mdp.mdpboweb.business.ws.ListApplicationByFlussoApplicazione parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.DeleteGatewayConfigurationResponse deleteGatewayConfiguration(it.csi.mdp.mdpboweb.business.ws.DeleteGatewayConfiguration parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.Auditactions[] getAuditActionsList(it.csi.mdp.mdpboweb.business.ws.GetAuditActionsList parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.GiornaleEventoDTO[] getGiornaleEventoByParam(it.csi.mdp.mdpboweb.business.ws.GetGiornaleEventoByParam parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.UploadMethodForApplicationGatewayResponse uploadMethodForApplicationGateway(it.csi.mdp.mdpboweb.business.ws.UploadMethodForApplicationGateway parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.IOException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.SetMdpGroupResponse setMdpGroup(it.csi.mdp.mdpboweb.business.ws.SetMdpGroup parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.SetMdpCurrencyResponse setMdpCurrency(it.csi.mdp.mdpboweb.business.ws.SetMdpCurrency parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.IsAliveResponse isAlive(it.csi.mdp.mdpboweb.business.ws.IsAlive parameters) throws java.rmi.RemoteException;
    public it.csi.mdp.mdpboweb.business.ws.GetTransazioneViewPagedResponse getTransazioneViewPaged(it.csi.mdp.mdpboweb.business.ws.GetTransazioneViewPaged parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.EntiDTO[] findEntiAll(it.csi.mdp.mdpboweb.business.ws.FindEntiAll parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.DeleteApplicationDetailResponse deleteApplicationDetail(it.csi.mdp.mdpboweb.business.ws.DeleteApplicationDetail parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.ApplicationDetail[] getApplicationDetailById(it.csi.mdp.mdpboweb.business.ws.GetApplicationDetailById parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.MdpCurrency[] getMdpCurrency(it.csi.mdp.mdpboweb.business.ws.GetMdpCurrency parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.SetMdpRoleResponse setMdpRole(it.csi.mdp.mdpboweb.business.ws.SetMdpRole parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.IbanEnteApplicationDTO[] getIbanEnteApplicationByParam(it.csi.mdp.mdpboweb.business.ws.GetIbanEnteApplicationByParam parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.Application[] getApplication(it.csi.mdp.mdpboweb.business.ws.GetApplication parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.Transazione[] getTransazioneByApp(it.csi.mdp.mdpboweb.business.ws.GetTransazioneByApp parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.Rtdto[] getRTByParam(it.csi.mdp.mdpboweb.business.ws.GetRTByParam parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.GetGatewayByIdResponse getGatewayById(it.csi.mdp.mdpboweb.business.ws.GetGatewayById parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.UpdateEnteResponse updateEnte(it.csi.mdp.mdpboweb.business.ws.UpdateEnte parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.Transazione[] getTransazione(it.csi.mdp.mdpboweb.business.ws.GetTransazione parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.SetPaymentModeResponse setPaymentMode(it.csi.mdp.mdpboweb.business.ws.SetPaymentMode parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.DeleteMdpBckUserResponse deleteMdpBckUser(it.csi.mdp.mdpboweb.business.ws.DeleteMdpBckUser parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.DeleteApplicationCustomFieldsResponse deleteApplicationCustomFields(it.csi.mdp.mdpboweb.business.ws.DeleteApplicationCustomFields parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.Paymentmode[] getPaymentMode(it.csi.mdp.mdpboweb.business.ws.GetPaymentMode parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.FlussoRiversamentoDTO[] estraiFlussiDaServizio(it.csi.mdp.mdpboweb.business.ws.EstraiFlussiDaServizio parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.TestResourcesResponse testResources(it.csi.mdp.mdpboweb.business.ws.TestResources parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.SystemException;
    public it.csi.mdp.mdpboweb.business.ws.GetMdpCurrencyByKeyResponse getMdpCurrencyByKey(it.csi.mdp.mdpboweb.business.ws.GetMdpCurrencyByKey parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.Verrori[] getErrorList(it.csi.mdp.mdpboweb.business.ws.GetErrorList parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.UpdateIbanEnteApplicationResponse updateIbanEnteApplication(it.csi.mdp.mdpboweb.business.ws.UpdateIbanEnteApplication parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.SetMdpUserResponse setMdpUser(it.csi.mdp.mdpboweb.business.ws.SetMdpUser parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.DeleteEnteResponse deleteEnte(it.csi.mdp.mdpboweb.business.ws.DeleteEnte parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.SetLanguageResponse setLanguage(it.csi.mdp.mdpboweb.business.ws.SetLanguage parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.InsRelEnteApplicationResponse insRelEnteApplication(it.csi.mdp.mdpboweb.business.ws.InsRelEnteApplication parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.CodiciEsitoPagamentoDTO[] getCodiciEsitoPagamentoAll(it.csi.mdp.mdpboweb.business.ws.GetCodiciEsitoPagamentoAll parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.TipoVersamentoDTO[] getListaTipoversamento(it.csi.mdp.mdpboweb.business.ws.GetListaTipoversamento parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.StatoTransazione[] getStatiTransazione(it.csi.mdp.mdpboweb.business.ws.GetStatiTransazione parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.SetGatewayResponse setGateway(it.csi.mdp.mdpboweb.business.ws.SetGateway parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.GetApplicationConfigurationResponse getApplicationConfiguration(it.csi.mdp.mdpboweb.business.ws.GetApplicationConfiguration parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.ApplicationDetail[] getApplicationDetail(it.csi.mdp.mdpboweb.business.ws.GetApplicationDetail parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.RefundPaymentResponse refundPayment(it.csi.mdp.mdpboweb.business.ws.RefundPayment parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.SetGatewayCustomFieldsResponse setGatewayCustomFields(it.csi.mdp.mdpboweb.business.ws.SetGatewayCustomFields parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.DeleteGatewayDetailResponse deleteGatewayDetail(it.csi.mdp.mdpboweb.business.ws.DeleteGatewayDetail parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.GetTransazioneViewWithFiltersPagedResponse getTransazioneViewWithFiltersPaged(it.csi.mdp.mdpboweb.business.ws.GetTransazioneViewWithFiltersPaged parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.UploadMethodForApplicationResponse uploadMethodForApplication(it.csi.mdp.mdpboweb.business.ws.UploadMethodForApplication parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.IOException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.DeleteApplicationResponse deleteApplication(it.csi.mdp.mdpboweb.business.ws.DeleteApplication parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.SetApplicationCustomFieldsResponse setApplicationCustomFields(it.csi.mdp.mdpboweb.business.ws.SetApplicationCustomFields parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.MdpCurrency[] getMdpCurrencyByGatewayId(it.csi.mdp.mdpboweb.business.ws.GetMdpCurrencyByGatewayId parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.DeleteApplicationConfigurationResponse deleteApplicationConfiguration(it.csi.mdp.mdpboweb.business.ws.DeleteApplicationConfiguration parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.Language[] getLanguages(it.csi.mdp.mdpboweb.business.ws.GetLanguages parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.EntiDTO[] getEntiByApplicationId(it.csi.mdp.mdpboweb.business.ws.GetEntiByApplicationId parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.Gatewaydetail[] getGatewayDetailById(it.csi.mdp.mdpboweb.business.ws.GetGatewayDetailById parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.MdpBckofficegroups[] getMdpBckGroupsByCfisc(it.csi.mdp.mdpboweb.business.ws.GetMdpBckGroupsByCfisc parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.GetPaymentModeByIdResponse getPaymentModeById(it.csi.mdp.mdpboweb.business.ws.GetPaymentModeById parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.ConfirmPaymentResponse confirmPayment(it.csi.mdp.mdpboweb.business.ws.ConfirmPayment parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException;
    public it.csi.mdp.mdpboweb.business.ws.Transazione[] getTransazioneById(it.csi.mdp.mdpboweb.business.ws.GetTransazioneById parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.Vtransazione[] getTransazioneWithFilters(it.csi.mdp.mdpboweb.business.ws.GetTransazioneWithFilters parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.DeleteMdpBckGroupResponse deleteMdpBckGroup(it.csi.mdp.mdpboweb.business.ws.DeleteMdpBckGroup parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.StatisticaApplicazioneTransazioneDTO[] getStatisticaApplicazioneTransazione(it.csi.mdp.mdpboweb.business.ws.GetStatisticaApplicazioneTransazione parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.Commission[] getTipoCommissione(it.csi.mdp.mdpboweb.business.ws.GetTipoCommissione parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.GetMdpUsersByIdResponse getMdpUsersById(it.csi.mdp.mdpboweb.business.ws.GetMdpUsersById parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.GetMdpUsersByCfiscResponse getMdpUsersByCfisc(it.csi.mdp.mdpboweb.business.ws.GetMdpUsersByCfisc parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.GetGatewayDetailByIdsResponse getGatewayDetailByIds(it.csi.mdp.mdpboweb.business.ws.GetGatewayDetailByIds parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.Language[] getLanguagesByGatewayId(it.csi.mdp.mdpboweb.business.ws.GetLanguagesByGatewayId parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.DeleteGatewayResponse deleteGateway(it.csi.mdp.mdpboweb.business.ws.DeleteGateway parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.DeleteGatewayCustomFieldsResponse deleteGatewayCustomFields(it.csi.mdp.mdpboweb.business.ws.DeleteGatewayCustomFields parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.MdpBckusers[] getMdpUsers(it.csi.mdp.mdpboweb.business.ws.GetMdpUsers parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.Gateway[] getGateways(it.csi.mdp.mdpboweb.business.ws.GetGateways parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.Gatewaycustomfields[] getGatewayCustomFields(it.csi.mdp.mdpboweb.business.ws.GetGatewayCustomFields parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.FlussoSingoloPagamentoDTO[] getFlussoSingoloPagamentoByParam(it.csi.mdp.mdpboweb.business.ws.GetFlussoSingoloPagamentoByParam parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.Applicationcustomfields[] getApplicationCustomFieldsByGateway(it.csi.mdp.mdpboweb.business.ws.GetApplicationCustomFieldsByGateway parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.InsertEnteResponse insertEnte(it.csi.mdp.mdpboweb.business.ws.InsertEnte parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.SetApplicationResponse setApplication(it.csi.mdp.mdpboweb.business.ws.SetApplication parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.UploadMethodResponse uploadMethod(it.csi.mdp.mdpboweb.business.ws.UploadMethod parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.IOException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.DeleteBoConfigResponse deleteBoConfig(it.csi.mdp.mdpboweb.business.ws.DeleteBoConfig parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.Rptdto[] getRPTByParam(it.csi.mdp.mdpboweb.business.ws.GetRPTByParam parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.GetApplicationByIdResponse getApplicationById(it.csi.mdp.mdpboweb.business.ws.GetApplicationById parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.SetTransazioneResponse setTransazione(it.csi.mdp.mdpboweb.business.ws.SetTransazione parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.GetGiornaleEventoByIdResponse getGiornaleEventoById(it.csi.mdp.mdpboweb.business.ws.GetGiornaleEventoById parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.GetMdpBckGroupsByIdResponse getMdpBckGroupsById(it.csi.mdp.mdpboweb.business.ws.GetMdpBckGroupsById parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.MdpBckofficegroups[] getMdpBckGroups(it.csi.mdp.mdpboweb.business.ws.GetMdpBckGroups parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.StatiRptDTO[] getStatiRptAll(it.csi.mdp.mdpboweb.business.ws.GetStatiRptAll parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.InformativePSPDTO[] getInformativePSPByParam(it.csi.mdp.mdpboweb.business.ws.GetInformativePSPByParam parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.FlussoRiversamentoDTO[] getFlussoRiversamentoByParam(it.csi.mdp.mdpboweb.business.ws.GetFlussoRiversamentoByParam parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.CsiLogAudit[] getAuditing(it.csi.mdp.mdpboweb.business.ws.GetAuditing parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.Config[] getBoConfig(it.csi.mdp.mdpboweb.business.ws.GetBoConfig parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.SetGatewayDetailResponse setGatewayDetail(it.csi.mdp.mdpboweb.business.ws.SetGatewayDetail parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.MdpBckroles[] getMdpBckRoles(it.csi.mdp.mdpboweb.business.ws.GetMdpBckRoles parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
    public it.csi.mdp.mdpboweb.business.ws.EntiDTO[] getEntiByParam(it.csi.mdp.mdpboweb.business.ws.GetEntiByParam parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException;
}
