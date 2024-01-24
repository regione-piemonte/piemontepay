/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.paymentmanager.bean;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import it.csi.mdp.adapters.business.CoreUtilities;
import it.csi.mdp.core.business.dao.ApplicationDetailDao;
import it.csi.mdp.core.business.dao.ApplicationcustomfieldsDao;
import it.csi.mdp.core.business.dao.ConfigDao;
import it.csi.mdp.core.business.dao.ERDao;
import it.csi.mdp.core.business.dao.ElementoMultiVersamentoDao;
import it.csi.mdp.core.business.dao.FlussoSingoloPagamentoDao;
import it.csi.mdp.core.business.dao.GiornaleEventoDao;
import it.csi.mdp.core.business.dao.IbanEnteApplicationDao;
import it.csi.mdp.core.business.dao.InformativePSPDao;
import it.csi.mdp.core.business.dao.IuvOtticiDao;
import it.csi.mdp.core.business.dao.MdpReceiptDao;
import it.csi.mdp.core.business.dao.NodoErroriDao;
import it.csi.mdp.core.business.dao.RPTDao;
import it.csi.mdp.core.business.dao.RRDao;
import it.csi.mdp.core.business.dao.RTDao;
import it.csi.mdp.core.business.dao.TransazioneDao;
import it.csi.mdp.core.business.dao.TransazioneExtraAttributeDao;
import it.csi.mdp.core.business.dao.WispParamsDao;
import it.csi.mdp.core.business.dto.ApplicationDetail;
import it.csi.mdp.core.business.dto.Applicationcustomfields;
import it.csi.mdp.core.business.dto.ChiaveValore;
import it.csi.mdp.core.business.dto.Config;
import it.csi.mdp.core.business.dto.DettaglioFruitore;
import it.csi.mdp.core.business.dto.ER;
import it.csi.mdp.core.business.dto.ElementoMultiversamento;
import it.csi.mdp.core.business.dto.FlussoSingoloPagamento;
import it.csi.mdp.core.business.dto.GiornaleEvento;
import it.csi.mdp.core.business.dto.IbanEnteApplication;
import it.csi.mdp.core.business.dto.InformativePSP;
import it.csi.mdp.core.business.dto.InformativePSPScelto;
import it.csi.mdp.core.business.dto.IuvOttici;
import it.csi.mdp.core.business.dto.IuvOtticoFruitore;
import it.csi.mdp.core.business.dto.NodoErrore;
import it.csi.mdp.core.business.dto.ParametriUrlWisp;
import it.csi.mdp.core.business.dto.ParametroWisp;
import it.csi.mdp.core.business.dto.RPT;
import it.csi.mdp.core.business.dto.RR;
import it.csi.mdp.core.business.dto.RT;
import it.csi.mdp.core.business.dto.RicevutaTelematicaNodo;
import it.csi.mdp.core.business.dto.Transazione;
import it.csi.mdp.core.business.dto.TransazioneExtraAttribute;
import it.csi.mdp.core.business.exceptions.DaoException;
import it.csi.mdp.core.business.factory.DaoFactory;
import it.csi.mdp.core.business.paymentmanager.local.AppGatewayInformativa;
import it.csi.mdp.core.business.util.EncryptionDecryptionUtil;
import it.csi.mdp.core.interfacecxf.MissingParameterException;
import it.csi.mdp.core.util.LoggerUtil;
import it.csi.mdp.utility.CostantiNodoSpc;
public class NodoSpcBean {

    private Properties envProps = new Properties();

    public NodoSpcBean() {
        try{
            ClassPathResource cp = new ClassPathResource("/env.properties");
            envProps.load(cp.getInputStream());
            ConfigDao confdao = DaoFactory.createConfigDao();
            List<Config> lconf = confdao.findAll();
            for (int i = 0; i < lconf.size(); i++) {
                Config c = lconf.get(i);
                envProps.put(c.getKey(), c.getValue());
            }
        } catch (Exception e){
            LoggerUtil.error("ERRORE NELLA COSTRUZIONE DEI PARAMETRI GLOBALI", e);
        }
    }

    /**
     *
     * @return
     * @throws DaoException
     */
    public List<AppGatewayInformativa> getElencoPSPNodo(Transazione t, String appId) throws DaoException {
        LoggerUtil.begin();

        List<AppGatewayInformativa> gatewaysNodo = new ArrayList<AppGatewayInformativa>();

        try {

            InformativePSPDao infoPSPDao = DaoFactory.createInformativePSPDao();

            ApplicationDetailDao appDetDao = DaoFactory.createApplicationDetailDao();

            ApplicationDetail appDet = appDetDao.findGatewayForNodo(appId);

            if (appDet == null)
                return gatewaysNodo;

            List<InformativePSP> elenco = infoPSPDao.findAllAttive();

            for (InformativePSP elem : elenco) {
                AppGatewayInformativa gw = new AppGatewayInformativa();
                gw.setApplicationId(t.getApplicationId());
                gw.setCondizioniEconomicheMassime(elem.getCondizioniEconomicheMassime());
                gw.setDescrizioneServizio(elem.getDescrizioneServizio());
                gw.setDisponibilitaServizio(elem.getDisponibilitaServizio());
                gw.setGatewayDescription(elem.getRagioneSociale());
                gw.setGatewayId(appDet.getGatewayid());
                gw.setGatewayServiceName(elem.getDescrizioneServizio());
                gw.setIdentificativoCanale(elem.getIdentificativoCanale());
                gw.setIdentificativoFlusso(elem.getIdentificativoFlusso());
                gw.setIdentificativoIntermediario(elem.getIdentificativoIntermediario());
                gw.setIdentificativoPSP(elem.getIdentificativoPSP());
                gw.setIdInformativaPSP(elem.getIdinformativapsp());
                gw.setMerchantId(elem.getIdentificativoPSP());
                gw.setModelloPagamento(elem.getModelloPagamento());
                gw.setOrdinamento(elem.getOrdinamento());
                gw.setOrigine(elem.getOrigine());
                gw.setPaymentmodeDescription(elem.getIdentificativoPSP());
                gw.setPaymentmodeId(appDet.getPaymentmodeid());
                gw.setPriorita(elem.getPriorita());
                gw.setRagioneSociale(elem.getRagioneSociale());
                gw.setStornoPagamento(elem.getStornoPagamento());
                gw.setTipoCommissione(elem.getTipoVersamento());
                gw.setTipoVersamento(elem.getTipoVersamento());
                gw.setUrlInformazioniPSP(elem.getUrlInformazioniPSP());
                gatewaysNodo.add(gw);
            }
        } catch (DaoException e) {
            LoggerUtil.error("ERRORE DI ACCESSO AI DATI", e);
            throw e;
        }

        LoggerUtil.end();


        return gatewaysNodo;
    }

    /**
     * Trova il gateway id per il nodo. Il concetto di gaeway id è inutile per il nodo, ma è necessaria all'infrastruttura MDP per funzionare.
     * @param t
     * @return
     * @throws DaoException
     */
    public String getGatewayNodo (String applicationId) throws DaoException {
        LoggerUtil.begin();

        String gatId = null;
        try {

            ApplicationDetailDao appDetDao = DaoFactory.createApplicationDetailDao();

            ApplicationDetail appDet = appDetDao.findGatewayForNodo(applicationId);

            gatId =  appDet.getGatewayid();
        } catch (DaoException e) {
            LoggerUtil.error("ERRORE DI ACCESSO AI DATI", e);
            throw e;
        }
        LoggerUtil.end();
        return gatId;

    }

    public void registraEventoGiornale(GiornaleEvento gde) throws DaoException, NamingException, CreateException {
        LoggerUtil.begin();

        try {

            GiornaleEventoDao gdeDao = DaoFactory.createGiornaleEventoDao();

            gdeDao.insert(gde);
        } catch (DaoException e) {
            LoggerUtil.error("ERRORE DI ACCESSO AI DATI", e);
            throw e;
        }

        LoggerUtil.end();
    }


    public void registraEsitoRevoca(ER er) throws DaoException, NamingException, CreateException{
        LoggerUtil.begin();
        try {
            ERDao erDao = DaoFactory.createERDao ();
            erDao.insert ( er );
        } catch (DaoException e) {
            LoggerUtil.error("ERRORE DI ACCESSO AI DATI", e);
            throw e;
        }
        LoggerUtil.end();
    }

    public void updateIdRrByIuv(String iuv, Integer idRR)  throws DaoException {
        LoggerUtil.begin();
        try {
            RTDao rtDao = DaoFactory.createRTDao ();
            rtDao.updateIdRrByIuv ( iuv, idRR );
        } catch (DaoException e) {
            LoggerUtil.error("ERRORE DI ACCESSO AI DATI", e);
            throw e;
        }
        LoggerUtil.end();
    }

    public Integer registraRichiestaRevoca(RR rr) throws DaoException, NamingException, CreateException{
        LoggerUtil.begin();
        Integer lastKey=0;
        try {
            RRDao rrDao = DaoFactory.createRRDao ();
            lastKey = rrDao.createRRData ( rr );
        } catch (DaoException e) {
            LoggerUtil.error("ERRORE DI ACCESSO AI DATI", e);
            throw e;
        }
        LoggerUtil.end();
        return lastKey;
    }


    public void registraRichiestaPagamentoTelematico(RPT rpt) throws DaoException, NamingException, CreateException {
        LoggerUtil.begin();

        try {

            RPTDao rptDao = DaoFactory.createRPTDao();

            rptDao.insert(rpt);

        } catch (DaoException e) {
            LoggerUtil.error("ERRORE DI ACCESSO AI DATI", e);
            throw e;
        }

        LoggerUtil.end();
    }


    public void aggiornaRichiestaPagamentoTelematico(RPT rpt) throws DaoException, NamingException, CreateException {
        LoggerUtil.begin();

        try {

            RPTDao rptDao = DaoFactory.createRPTDao();

            rptDao.insert(rpt);

        } catch (DaoException e) {
            LoggerUtil.error("ERRORE DI ACCESSO AI DATI", e);
            throw e;
        }

        LoggerUtil.end();
    }

    public String decode(String encoded) throws Exception {
        String sKey = null;

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        FileInputStream stream = null;
        try
        {
            stream = new FileInputStream(envProps.getProperty("sKeyDb"));
            int len = stream.available();
            byte[] b = new byte[len];
            stream.read(b);
            b = Base64.decodeBase64 ( new String(b));
            sKey = new String(b);
        } catch (Exception e)
        {
            e.printStackTrace(pw);
        } finally {
            if(stream != null) {
                try {
                    stream.close();
                } catch ( IOException e ) {
                    e.printStackTrace();
                }
            }
        }

        SecretKeySpec skeySpec = new SecretKeySpec ( sKey.getBytes (), "AES" );

        Cipher cipher = Cipher.getInstance ( "AES" );
        cipher.init ( Cipher.DECRYPT_MODE, skeySpec );
        byte[] decoded = cipher.doFinal ( Base64.decodeBase64 ( encoded ) );

        return new String(decoded);
    }

    public String recuperaApplicationIdFromIuv(String iuv) throws DaoException, NamingException, CreateException{
        LoggerUtil.begin();

        List<Transazione> trans = null;

        String appId = "";

        TransazioneDao tranDao = DaoFactory.createTransazioneDao ();

        trans = tranDao.recuperaApplicationIdFromIuv ( iuv );

        if ( ( trans != null ) && ( trans.size () > 0 ) ) {
            try {
                appId = trans.get ( 0 ).getApplicationId ();
            }catch(Exception e) {
                e.printStackTrace ();
            }
        }

        LoggerUtil.end ();

        return appId;
    }

    public List<Applicationcustomfields> recuperaApplicationCustomFieldDecoded(String applicationId, String fieldName) throws Exception{
        List<Applicationcustomfields> items = recuperaApplicationCustomFields(applicationId,fieldName);

        for(Applicationcustomfields item:items) {
            item.setFieldvalue ( decode(item.getFieldvalue ()));
        }

        return items;
    }

    public List<Applicationcustomfields> recuperaApplicationCustomFields(String applicationId, String fieldName) throws DaoException, NamingException, CreateException{
        LoggerUtil.begin();

        List<Applicationcustomfields> elenco = null;

        try {

            ApplicationcustomfieldsDao appCustomFieldDao = DaoFactory.createApplicationcustomfieldsDao ();

            elenco = appCustomFieldDao.findWhereApplicationidAndFieldNameEquals ( applicationId, fieldName );

        } catch (DaoException e) {
            LoggerUtil.error("ERRORE DI ACCESSO AI DATI", e);
            throw e;
        }

        LoggerUtil.end();

        return elenco;
    }

    public List<RPT> recuperaRichiestaPagamentoConFiltro(RPT filtro) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException {
        LoggerUtil.begin();

        List<RPT> elenco = null;

        try {

            RPTDao rptDao = DaoFactory.createRPTDao();

            elenco = rptDao.findWhereFiltro(filtro);

        } catch (DaoException e) {
            LoggerUtil.error("ERRORE DI ACCESSO AI DATI", e);
            throw e;
        }

        LoggerUtil.end();

        return elenco;
    }

    public void aggiornaRPT(RPT datiAggiornati) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException {
        LoggerUtil.begin();

        try {

            RPTDao rptDao = DaoFactory.createRPTDao();

            rptDao.update(datiAggiornati);

        } catch (DaoException e) {
            LoggerUtil.error("ERRORE DI ACCESSO AI DATI", e);
            throw e;
        }

        LoggerUtil.end();
    }


    public void aggiornaRicevutaTelematica(RT rt) throws DaoException, NamingException, CreateException {
        LoggerUtil.begin();

        try {
            LoggerUtil.info ( "AGGIORNA RT");

            RTDao rtDao = DaoFactory.createRTDao();

            rtDao.update ( rt );

            LoggerUtil.info ( "STATO INVIO FRUITORE:" + rt.getStatoInvioFruitore());
            LoggerUtil.debug ( "IUV:" + rt.getIuv () );
            LoggerUtil.debug ( "APPLICATION_ID:" + rt.getApplicationId () );
            LoggerUtil.debug ( "TRANSACTION_ID:" + rt.getTransactionId () );

            if (!"OK".equalsIgnoreCase(rt.getStatoInvioFruitore())) {
                rtDao.insertCoda(rt.getIuv(), rt.getApplicationId (), rt.getTransactionId ());
            }

        } catch (DaoException e) {
            LoggerUtil.error("ERRORE DI ACCESSO AI DATI", e);
            throw e;
        }

        LoggerUtil.end();
    }

    public void registraRicevutaTelematica(RT rt) throws DaoException, NamingException, CreateException {
        LoggerUtil.begin();

        try {

            RTDao rtDao = DaoFactory.createRTDao();

            //			List<RT> elencoTrovati = rtDao.findWhereIUVEquals(rt.getIuv());

            //			if (elencoTrovati != null && elencoTrovati.size() > 0)
            //				return;

            rtDao.insert(rt);

            //			if (!"OK".equalsIgnoreCase(rt.getStatoInvioFruitore())) {
            //				rtDao.insertCoda(rt.getIuv());
            //			}
            //
        } catch (DaoException e) {
            LoggerUtil.error("ERRORE DI ACCESSO AI DATI", e);
            throw e;
        }

        LoggerUtil.end();
    }

    public RT recuperaRichiestaPerIdTransazione(String idTransazione) throws DaoException {

        LoggerUtil.begin();

        RT rt = null;

        try {

            RTDao rtDao = DaoFactory.createRTDao();

            List<RT> elencoRT = rtDao.findWhereTransactionIdEquals(idTransazione);

            if (elencoRT != null && elencoRT.size() > 0)
                rt = elencoRT.get(0);

        } catch (DaoException e) {
            LoggerUtil.error("ERRORE DI ACCESSO AI DATI", e);
            throw e;
        }

        LoggerUtil.end();

        return rt;
    }

    public RicevutaTelematicaNodo recuperaRTperIUV(String iuv) throws DaoException {
        LoggerUtil.begin();

        try {

            RTDao rtDao = DaoFactory.createRTDao();

            List<RT> rt = rtDao.findWhereIUVEquals(iuv);

            LoggerUtil.end();

            if (rt.size()==0) {
                throw new DaoException("Parametro IUV non presente in archivio");
            }else{
                RicevutaTelematicaNodo ricevuta = new RicevutaTelematicaNodo();
                ricevuta.setApplicationId(rt.get(0).getApplicationId());
                ricevuta.setDataMsgRicevuta(rt.get(0).getDataMsgRicevuta());
                ricevuta.setDescEsitoPagamento(rt.get(0).getDescEsitoPagamento());
                ricevuta.setId(rt.get(0).getId());
                ricevuta.setIdEsitoPagamento(rt.get(0).getIdEsitoPagamento());
                ricevuta.setIdMsgRicevuta(rt.get(0).getIdMsgRicevuta());
                ricevuta.setIdMsgRichiesta(rt.get(0).getIdMsgRichiesta());
                ricevuta.setInsertDate(rt.get(0).getInsertDate());
                ricevuta.setIuv(rt.get(0).getIuv());
                ricevuta.setLastUpdate(rt.get(0).getLastUpdate());
                ricevuta.setRtData(rt.get(0).getRtData());
                ricevuta.setTipoFirma(rt.get(0).getTipoFirma());
                ricevuta.setTransactionId(rt.get(0).getTransactionId());
                LoggerUtil.dumpObject("OGGETTO TRAVASATO", ricevuta);
                return ricevuta;
            }

        } catch (DaoException e) {
            LoggerUtil.error("ERRORE DI ACCESSO AI DATI", e);
            throw e;
        }

    }


    public RicevutaTelematicaNodo recuperaRTperIUVTransazione(String iuv, String transactionId) throws DaoException {
        LoggerUtil.begin();

        try {

            RTDao rtDao = DaoFactory.createRTDao();

            List<RT> rt = rtDao.findWhereIUVEquals(iuv);

            LoggerUtil.end();

            if (rt.size()==0) {
                throw new DaoException("Nessuna RT trovata per i filtri impostati");
            }else{
                RT selezionata = null;
                for (RT ricevuta : rt) {
                    if (transactionId.equalsIgnoreCase(ricevuta.getTransactionId())) {
                        selezionata = ricevuta;
                        break;
                    }
                }
                if (selezionata == null)
                    throw new DaoException("Nessuna RT trovata per i filtri impostati");

                RicevutaTelematicaNodo ricevuta = new RicevutaTelematicaNodo();
                ricevuta.setApplicationId(selezionata.getApplicationId());
                ricevuta.setDataMsgRicevuta(selezionata.getDataMsgRicevuta());
                ricevuta.setDescEsitoPagamento(selezionata.getDescEsitoPagamento());
                ricevuta.setId(selezionata.getId());
                ricevuta.setIdEsitoPagamento(selezionata.getIdEsitoPagamento());
                ricevuta.setIdMsgRicevuta(selezionata.getIdMsgRicevuta());
                ricevuta.setIdMsgRichiesta(selezionata.getIdMsgRichiesta());
                ricevuta.setInsertDate(selezionata.getInsertDate());
                ricevuta.setIuv(selezionata.getIuv());
                ricevuta.setLastUpdate(selezionata.getLastUpdate());
                ricevuta.setRtData(selezionata.getRtData());
                ricevuta.setTipoFirma(selezionata.getTipoFirma());
                ricevuta.setTransactionId(selezionata.getTransactionId());
                LoggerUtil.dumpObject("OGGETTO TRAVASATO", ricevuta);
                return ricevuta;
            }

        } catch (DaoException e) {
            LoggerUtil.error("ERRORE DI ACCESSO AI DATI", e);
            throw e;
        }

    }


    public List<RicevutaTelematicaNodo> recuperaListaRTperIUV(String iuv) throws DaoException {
        LoggerUtil.begin();

        List<RicevutaTelematicaNodo> elencoRt = null;

        try {

            RTDao rtDao = DaoFactory.createRTDao();

            List<RT> rt = rtDao.findWhereIUVEquals(iuv);

            LoggerUtil.end();

            if (rt.size()==0) {
                throw new DaoException("Nessuna RT trovata per iuv " + iuv);
            }else{
                elencoRt = new ArrayList<RicevutaTelematicaNodo>(rt.size());
                for (RT singolaRt : rt) {
                    RicevutaTelematicaNodo ricevuta = new RicevutaTelematicaNodo();
                    ricevuta.setApplicationId(rt.get(0).getApplicationId());
                    ricevuta.setDataMsgRicevuta(singolaRt.getDataMsgRicevuta());
                    ricevuta.setDescEsitoPagamento(singolaRt.getDescEsitoPagamento());
                    ricevuta.setId(singolaRt.getId());
                    ricevuta.setIdEsitoPagamento(singolaRt.getIdEsitoPagamento());
                    ricevuta.setIdMsgRicevuta(singolaRt.getIdMsgRicevuta());
                    ricevuta.setIdMsgRichiesta(singolaRt.getIdMsgRichiesta());
                    ricevuta.setInsertDate(singolaRt.getInsertDate());
                    ricevuta.setIuv(singolaRt.getIuv());
                    ricevuta.setLastUpdate(singolaRt.getLastUpdate());
                    ricevuta.setRtData(singolaRt.getRtData());
                    ricevuta.setTipoFirma(singolaRt.getTipoFirma());
                    ricevuta.setTransactionId(singolaRt.getTransactionId());
                    LoggerUtil.dumpObject("OGGETTO TRAVASATO", ricevuta);
                    elencoRt.add(ricevuta);
                }
            }

        } catch (DaoException e) {
            LoggerUtil.error("ERRORE DI ACCESSO AI DATI", e);
            throw e;
        }
        return elencoRt;
    }


    public InformativePSP recuperaInformativaPerId(Integer id) throws DaoException {
        LoggerUtil.begin();

        InformativePSP info = null;

        try {

            InformativePSPDao infoPSPDao = DaoFactory.createInformativePSPDao();

            info = infoPSPDao.findWhereKeyidEquals(id);

        } catch (DaoException e) {
            LoggerUtil.error("ERRORE DI ACCESSO AI DATI", e);
            throw e;
        }

        LoggerUtil.end();

        return info;

    }
    public InformativePSP recuperaInformativaPerIdentificativoPSP(String identificativoPSP) throws DaoException {
        LoggerUtil.begin();

        InformativePSP info = null;

        try {

            InformativePSPDao infoPSPDao = DaoFactory.createInformativePSPDao();

            info = infoPSPDao.getInformativePSPByParam(null, null, identificativoPSP, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, "NEW", null, null).get(0);

        } catch (DaoException e) {
            LoggerUtil.error("ERRORE DI ACCESSO AI DATI", e);
            throw e;
        }

        LoggerUtil.end();

        return info;

    }

    public Transazione recuperaIdTransazionePerIdSession(String idSession) throws DaoException {
        LoggerUtil.begin();

        Transazione t = null;

        try {

            TransazioneDao transazioneDao = DaoFactory.createTransazioneDao();

            t = transazioneDao.getTransazioneByIdSession(idSession);

        } catch (DaoException e) {
            LoggerUtil.error("ERRORE DI ACCESSO AI DATI", e);
            throw e;
        }

        LoggerUtil.end();

        return t;

    }

    public void setIdSessionTransazione(String transacionId, String idSession)  throws DaoException{
        LoggerUtil.begin();

        try {

            TransazioneDao transazioneDao = DaoFactory.createTransazioneDao();

            transazioneDao.updateIdSession(transacionId, idSession);

        } catch (DaoException e) {
            LoggerUtil.error("ERRORE DI ACCESSO AI DATI", e);
            throw e;
        }


        LoggerUtil.end();

    }

    public void registraTransazioneIuv(String transacionId, String iuv)  throws DaoException{
        LoggerUtil.begin();

        try {

            TransazioneDao transazioneDao = DaoFactory.createTransazioneDao();

            transazioneDao.insertTransazioneIuv(transacionId, iuv);

        } catch (DaoException e) {
            LoggerUtil.error("ERRORE DI ACCESSO AI DATI", e);
            throw e;
        }


        LoggerUtil.end();

    }

    public List<NodoErrore> getErroriNodo(String applicationId, String transactionId) throws DaoException{

        try {

            NodoErroriDao dao = DaoFactory.createNodoErroriDao();

            NodoErrore filtro = new NodoErrore();
            filtro.setApplicationId(applicationId);
            filtro.setTransactionId(transactionId);

            return dao.find(filtro);

        } catch (DaoException e) {
            LoggerUtil.error("ERRORE DI ACCESSO AI DATI", e);
            throw e;
        }
    }

    public void registraErroriNodo(List<NodoErrore> elencoErrori) throws DaoException {
        LoggerUtil.begin();

        try {

            NodoErroriDao dao = DaoFactory.createNodoErroriDao();

            for (NodoErrore dto : elencoErrori)
                dao.insert(dto);

        } catch (DaoException e) {
            LoggerUtil.error("ERRORE DI ACCESSO AI DATI", e);
            throw e;
        }

        LoggerUtil.end();
    }

    public IuvOtticoFruitore recuperaDatiFruitorePerIuvOttico(String iuvOttico, String iuvStandard) throws DaoException {
        LoggerUtil.begin();

        IuvOtticoFruitore iuv = new IuvOtticoFruitore();

        try {

            IuvOtticiDao dao = DaoFactory.createIuvOtticiDao();
            IuvOttici filtro = new IuvOttici();
            filtro.setIuvOttico(iuvOttico);
            filtro.setIuvStandard(iuvStandard);
            IuvOttici iuvDb = dao.find(filtro);

            LoggerUtil.debug ( "RICERCA IUV OTTICO: " + iuvOttico);
            LoggerUtil.debug ( "RICERCA IUV STANDARD: " + iuvStandard);
            LoggerUtil.debug ( "RISULTATTO: " + iuvDb);

            if (iuvDb == null)
                return null;

            iuv.setIuvOttici(iuvDb);
            LoggerUtil.debug ( "RICERCA APP DETAIL CON IUV OTTICO: " + iuvDb);

            ApplicationDetailDao appDetDao = DaoFactory.createApplicationDetailDao();

            LoggerUtil.debug ( "RICERCA APP DETAIL CON ID: " + iuvDb.getApplicationId());

            ApplicationDetail ad = appDetDao.findGatewayForNodo(iuvDb.getApplicationId());

            LoggerUtil.debug ( "RICERCA APP DETAIL RESULT: " + ad);

            iuv.setApplicationDetail(ad);

        } catch (DaoException e) {
            LoggerUtil.error("ERRORE DI ACCESSO AI DATI", e);
            throw e;
        }

        LoggerUtil.end();
        return iuv;
    }

    public DettaglioFruitore recuperaDatiFruitorePerApplicationId(String applicationId) throws DaoException {
        LoggerUtil.begin();

        DettaglioFruitore dettaglioFruitore = new DettaglioFruitore();

        try {
            LoggerUtil.debug ( "RECUPERO DATI FUITORE PER APPID" + applicationId );
            ApplicationDetailDao appDetDao = DaoFactory.createApplicationDetailDao();
            ApplicationDetail ad = appDetDao.findGatewayForNodo(applicationId);
            dettaglioFruitore.setApplicationDetail(ad);

            LoggerUtil.debug ( "DETTAGLIO" + ad);

        } catch (DaoException e) {
            LoggerUtil.error("ERRORE DI ACCESSO AI DATI", e);
            throw e;
        }

        LoggerUtil.end();
        return dettaglioFruitore;
    }

    public IbanEnteApplication recuperaIbanEnteApplication(IbanEnteApplication filtro) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException {
        LoggerUtil.begin();

        IbanEnteApplication ibanEnteApplication = new IbanEnteApplication();

        try {

            IbanEnteApplicationDao ibanEnteApplicationDao = DaoFactory.createIbanEnteApplicationDao();
            List<IbanEnteApplication> elenco = ibanEnteApplicationDao.getIbanEnteApplicationByParam(null, filtro.getApplicationId(), null, filtro.getTipoversamento(), filtro.getIdentificativopsp(), null, null, null, "ATTIVO");

            for (IbanEnteApplication elem : elenco) {
                if (elem.getDataFineValidita() == null || new Timestamp(System.currentTimeMillis()).compareTo(elem.getDataFineValidita()) < 0) {
                    ibanEnteApplication = elem;
                    break;
                }
            }
        } catch (DaoException e) {
            LoggerUtil.error("ERRORE DI ACCESSO AI DATI", e);
            throw e;
        }

        LoggerUtil.end();
        return ibanEnteApplication;
    }

    public void registraTransazioneExtraAttributes(TransazioneExtraAttribute[] tea) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException {
        LoggerUtil.begin();
        try {
            TransazioneExtraAttributeDao daoTransExtraAttr = DaoFactory.createTransazioneExtraAttributeDao();
            for (TransazioneExtraAttribute attr : tea)
            {
                if (attr != null)
                    daoTransExtraAttr.insert(attr);
            }
        } catch (Exception e) {
            LoggerUtil.error("ERRORE DI ACCESSO AI DATI", e);
            throw new DaoException("",e);
        }
        LoggerUtil.end();
    }

    /**
     * Salva i parametri per comporre l'url di redirect al WISP, genera la keyPA e restituisce
     * l'url alla pagina interna che genera la form per il submit via POST al nodo SPC.
     * @param applicationId
     * @param transaction
     * @param paramentriUrlWisp
     * @param prametriAggiuntivi
     * @param acf
     * @return
     * @throws RemoteException
     * @throws DaoException
     * @throws NamingException
     * @throws CreateException
     * @throws MissingParameterException
     */
    public String gestioneRedirectWispInterno(String applicationId, Transazione transaction,ParametriUrlWisp parametriUrlWisp, List<ChiaveValore> prametriAggiuntivi, Applicationcustomfields[] acf) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException {
        LoggerUtil.begin();
        StringBuilder url = new StringBuilder();
        if (transaction == null || transaction.getAmount() <= 0d) {
            throw new DaoException ("Valorizzare correttamente la transazione con un amount positivo");
        }
        if (StringUtils.isEmpty(applicationId))
            throw new DaoException("PARAMETRO applicationId OBBLIGATORIO");
        if (StringUtils.isEmpty(transaction.getTransactionId()))
            throw new DaoException("PARAMETRO transactionId OBBLIGATORIO");
        try {
            if (parametriUrlWisp == null) parametriUrlWisp = new ParametriUrlWisp();
            String keyPA = generaKeyPa(transaction.getTransactionId(), applicationId);
            url.append(envProps.get("urlWispForm")).append("?keypa=").append(keyPA);
            Map<String, String> mappaAppCustomFields = CoreUtilities.costruisciMappaApplicationCustomFields(acf);
            ParametroWisp daRegistrare = new ParametroWisp();
            daRegistrare.setKeyPA(keyPA);
            daRegistrare.setApplicationId(applicationId);
            daRegistrare.setTransactionId(transaction.getTransactionId());
            daRegistrare.setBolloDigitale(valoreRuntimeODefault(parametriUrlWisp.getBolloDigitale(), mappaAppCustomFields.get(CostantiNodoSpc.APP_PARAM_BOLLO_DIGITALE)));
            daRegistrare.setEnteCreditore(mappaAppCustomFields.get(CostantiNodoSpc.APP_PARAM_DENOMINAZIONE_BENEFICIARIO));
            daRegistrare.setIdDominio(mappaAppCustomFields.get(CostantiNodoSpc.APP_PARAM_IDENTIFICATIVO_DOMINIO));
            daRegistrare.setIdPsp(valoreRuntimeODefault(parametriUrlWisp.getIdPsp(), mappaAppCustomFields.get(CostantiNodoSpc.APP_PARAM_ID_PSP)));
            daRegistrare.setImportoTransazione(transaction.getAmount());
            daRegistrare.setNumPagamentiRPT(1);
            daRegistrare.setPrimitiva("nodoInviaRPT"); // FIXME: con il carrello di RPT questo diventa "nodoInviaCarrelloRPT"
            daRegistrare.setStornoPagamento(valoreRuntimeODefault(parametriUrlWisp.getStornoPagamento(), mappaAppCustomFields.get(CostantiNodoSpc.APP_PARAM_STORNO_PAGAMENTO)));
            daRegistrare.setTerzoModelloPagamento(valoreRuntimeODefault(parametriUrlWisp.getTerzoModelloPagamento(), mappaAppCustomFields.get(CostantiNodoSpc.APP_PARAM_TERZO_MODELLO_PAGAMENTO)));
            daRegistrare.setTipoVersamento(valoreRuntimeODefault(parametriUrlWisp.getTipoVersamento(), mappaAppCustomFields.get(CostantiNodoSpc.APP_PARAM_TIPO_VERSAMENTO)));
            daRegistrare.setTransactionId(transaction.getTransactionId());
            daRegistrare.setUrlGestione(envProps.getProperty("urlGestioneRitornoWisp"));
            daRegistrare.setUrlBack(valoreRuntimeODefault(parametriUrlWisp.getUrlBackWisp(), mappaAppCustomFields.get("urlBackWisp")));
            daRegistrare.setUrlReturn(valoreRuntimeODefault(parametriUrlWisp.getUrlReturnWisp(), mappaAppCustomFields.get("urlReturnWisp")));
            daRegistrare.setVersioneInterfacciaWisp("1.3");
            daRegistrare.setContoPoste(mappaAppCustomFields.get(CostantiNodoSpc.APP_PARAM_CONTO_POSTE));
            daRegistrare.setPagamentiModello2("SI".equalsIgnoreCase(parametriUrlWisp.getModello2()) ? "SI" : "NO");
            daRegistrare.setCodiceLingua(valoreRuntimeODefault(parametriUrlWisp.getCodiceLingua(), mappaAppCustomFields.get("codiceLingua")));

            WispParamsDao dao = DaoFactory.createWispParamsDao();
            dao.insert(daRegistrare);

        } catch (Exception e) {
            LoggerUtil.error("ERRORE DI ACCESSO AI DATI", e);
            throw new DaoException("",e);
        }
        LoggerUtil.end();
        return url.toString();
    }


    /**
     * Recupera i parametri WISP
     * @param filtro
     * @return
     * @throws RemoteException
     * @throws DaoException
     * @throws NamingException
     * @throws CreateException
     * @throws MissingParameterException
     */
    public List<ParametroWisp> reuperaParametriWisp(ParametroWisp filtro) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException {
        LoggerUtil.begin();
        List<ParametroWisp> elencoRisultati = null;
        try {

            WispParamsDao dao = DaoFactory.createWispParamsDao();
            elencoRisultati = dao.find(filtro);

        } catch (Exception e) {
            LoggerUtil.error("ERRORE DI ACCESSO AI DATI", e);
            throw new DaoException("",e);
        }
        LoggerUtil.end();
        return elencoRisultati;
    }

    public int aggiornaParametroWisp(ParametroWisp daAggiornare) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException {
        LoggerUtil.begin();
        int aggiornati = 0;
        try {

            WispParamsDao dao = DaoFactory.createWispParamsDao();
            aggiornati = dao.update(daAggiornare);

        } catch (Exception e) {
            LoggerUtil.error("ERRORE DI ACCESSO AI DATI", e);
            throw new DaoException("",e);
        }
        LoggerUtil.end();
        return aggiornati;
    }


    public int inserisciMultiVersamento(ElementoMultiversamento elem) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException {
        LoggerUtil.begin();
        int aggiornati = 0;
        try {

            ElementoMultiVersamentoDao dao = DaoFactory.createElementoMultiversamentoDao();
            dao.insert(elem);

        } catch (Exception e) {
            LoggerUtil.error("ERRORE DI ACCESSO AI DATI", e);
            throw new DaoException("",e);
        }
        LoggerUtil.end();
        return aggiornati;
    }

    /**
     * Cancella parametro WISP
     * @param filtro
     * @throws RemoteException
     * @throws DaoException
     * @throws NamingException
     * @throws CreateException
     * @throws MissingParameterException
     */
    public void deleteParametroWisp(ParametroWisp filtro) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException {
        LoggerUtil.begin();
        try {

            WispParamsDao dao = DaoFactory.createWispParamsDao();
            dao.delete(filtro);

        } catch (Exception e) {
            LoggerUtil.error("ERRORE DI ACCESSO AI DATI", e);
            throw new DaoException("",e);
        }
        LoggerUtil.end();
    }

    public InformativePSPScelto getSceltaWisp(String applicationId, String transactionId) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException {
        LoggerUtil.begin();
        InformativePSPScelto infoPspScelto = new InformativePSPScelto();
        infoPspScelto.setIdinformativapsp(-1);
        infoPspScelto.setIdentificativoCanale("97735020584_02");
        infoPspScelto.setIdentificativoPSP("AGID_01");
        infoPspScelto.setIdentificativoIntermediario("97735020584");
        infoPspScelto.setTipoVersamento("BBT");
        if (StringUtils.isEmpty(applicationId))
            throw new DaoException("PARAMETRO applicationId OBBLIGATORIO");
        if (StringUtils.isEmpty(transactionId))
            throw new DaoException("PARAMETRO transactionId OBBLIGATORIO");
        try {
            InformativePSPDao dao = DaoFactory.createInformativePSPDao();
            ApplicationDetailDao appDetDao = DaoFactory.createApplicationDetailDao();
            ApplicationDetail appDet = appDetDao.findGatewayForNodo(applicationId);
            infoPspScelto.setGatewayId(appDet.getGatewayid());
            infoPspScelto.setPaymentModeId(appDet.getPaymentmodeid());
            InformativePSP infoPsp =  dao.recuperaInformativaPSPSceltaWisp(applicationId, transactionId);
            BeanUtils.copyProperties(infoPspScelto, infoPsp);
        } catch (DaoException e) {
            LoggerUtil.warn("PSP NON TROVATO: USO QUELLO DI DEFAULT");
        } catch (Exception e) {
            LoggerUtil.error("ERRORE DI ACCESSO AI DATI", e);
            throw new DaoException("",e);
        } finally {
            LoggerUtil.dumpObject("INFORMATIVA PSP CHE RESTITUISCO ", infoPspScelto);
            LoggerUtil.end();
        }
        return infoPspScelto;
    }

    private String generaKeyPa(String transactionId, String applicationId) {
        SimpleDateFormat sdf = new SimpleDateFormat("ddmmyyyy-hh:mm:ss:ms");
        String timestamp = sdf.format(new Date());;
        byte[] keyPABytes = DigestUtils.sha256(transactionId + applicationId + timestamp);
        String keyPA = Base64.encodeBase64URLSafeString(keyPABytes);
        LoggerUtil.debug("KEY PA: " + keyPA);
        return keyPA.substring(0,40);
    }
    /*
	private String componiParametroUrlSeRichiesto (String nome, String valoreDefault, String valoreRuntime) {
		if (StringUtils.isEmpty(valoreRuntime) && StringUtils.isEmpty(valoreDefault))
			return "";
		return nome + "=" + (StringUtils.isNotEmpty(valoreRuntime)? valoreRuntime : valoreDefault);
	}
     */
    private String valoreRuntimeODefault(String valoreRuntime, String valoreDefault) {
        return StringUtils.isNotEmpty(valoreRuntime) ? valoreRuntime : valoreDefault;
    }

    //RDI-45-MULTIBENEFICIARIO INIZIO
    public String getNumeroAvviso ( String iuv, String applicationId, String gatewayId )
                    throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException {
        LoggerUtil.begin ();
        String retValue = "";
        if ( StringUtils.isBlank ( iuv ) || iuv.startsWith ( "RF" ) ) {
            LoggerUtil.info ( "Iuv spontaneo non e' possibile ricavare il numero avviso!" );
            return null;
        }
        try {
            // TODO auxDigit va inserito nel file delle costanti.
            ApplicationcustomfieldsDao applDao = DaoFactory.createApplicationcustomfieldsDao ();
            LoggerUtil.info ( "applicationId: " + applicationId );
            LoggerUtil.info ( "gatewayId: " + gatewayId );

            List<Applicationcustomfields> applCustomFieldsAux
                = applDao.findWhereApplicationidAndGatewayIdAndFieldNameEquals ( applicationId, gatewayId, "auxDigit" );
            LoggerUtil.dumpObject ( "applCustomFieldsAux: ", applCustomFieldsAux );
            List<Applicationcustomfields> applCustomFieldsAppCode
                = applDao.findWhereApplicationidAndGatewayIdAndFieldNameEquals ( applicationId, gatewayId, "applicationCode" );
            LoggerUtil.dumpObject ( "applCustomFieldsAppCode: ", applCustomFieldsAppCode );
            StringBuffer sb = new StringBuffer ();
            String auxDigit = applCustomFieldsAux.get ( 0 ).getFieldvalue ();
            LoggerUtil.info ( "auxDigit: " + auxDigit );
            String applicationCode = applCustomFieldsAppCode.get ( 0 ).getFieldvalue ();
            LoggerUtil.info ( "applicationCode: " + applicationCode );
            if ( null != auxDigit ) {
                sb.append ( decode ( auxDigit ) );
                LoggerUtil.info ( "aggiunto auxDigit: " + sb.toString () );
            }
            if ( null != applicationCode ) {
                sb.append ( decode ( applicationCode ) );
                LoggerUtil.info ( "aggiunto applicationCode: " + sb.toString () );
            }

            retValue = sb.append ( iuv ).toString ();
            LoggerUtil.info ( "NumeroAvviso: " + retValue );

        } catch ( DaoException e ) {
            LoggerUtil.warn ( "RECORD TABELLA APPLICATION CUSTOM FIELDS NON TROVATO" );
            throw new DaoException ( "", e );
        } catch ( Exception e ) {
            LoggerUtil.error ( "ERRORE DI ACCESSO AI DATI", e );
            throw new DaoException ( "", e );
        } finally {
            LoggerUtil.end ();
        }
        return retValue;
    }

    public String getIdCarrello ( String applicationId, String gatewayId, String iuv )
                    throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException {
        LoggerUtil.begin ();
        String retValue = "";
        String idDominio = "";
        int progressivoCarrello;
        try {
            //auxDigit va inserito nel file delle costanti.
            ApplicationcustomfieldsDao applDao = DaoFactory.createApplicationcustomfieldsDao ();
            List<Applicationcustomfields> applCustomFields
            = applDao.findWhereApplicationidAndGatewayIdAndFieldNameEquals ( applicationId, gatewayId, CostantiNodoSpc.APP_PARAM_IDENTIFICATIVO_DOMINIO );
            for ( Applicationcustomfields appl: applCustomFields ) {
                if ( appl.getFieldname ().equals ( CostantiNodoSpc.APP_PARAM_IDENTIFICATIVO_DOMINIO ) ) {
                    idDominio = EncryptionDecryptionUtil.decrypt ( appl.getFieldname (), "yYoThaIwl7gff2no8krJ6g==" );
                }
                progressivoCarrello = getCountIdCarrello ( idDominio, iuv );
            }
            retValue = ( new StringBuffer () ).append ( decode ( applCustomFields.get ( 0 ).getFieldvalue () ) ).append ( iuv ).toString ();
        } catch ( DaoException e ) {
            LoggerUtil.warn ( "RECORD TABELLA APPLICATION CUSTOM FIELDS NON TROVATO" );
            throw new DaoException ( "", e );
        } catch ( Exception e ) {
            LoggerUtil.error ( "ERRORE DI ACCESSO AI DATI", e );
            throw new DaoException ( "", e );
        } finally {
            LoggerUtil.end ();
        }
        return retValue;
        //return (new StringBuffer()).append(identificativoDominio).append(iuv).append("-").append(count).toString();
    }
    public int getCountIdCarrello(String identificativoDominio, String iuv)   throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException  {
        LoggerUtil.begin();
        int retValue;
        try {
            RPTDao rptDao = DaoFactory.createRPTDao();
            retValue = rptDao.findCountForIdDominioAndIuv(identificativoDominio, iuv) + 1;
        } catch (Exception e) {
            LoggerUtil.error("ERRORE DI ACCESSO AI DATI", e);
            throw new DaoException("",e);
        } finally {
            LoggerUtil.end();
        }
        return retValue;
    }
    public int findNexValueIdDominio( )   throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException  {
        LoggerUtil.begin();
        int retValue;
        try {
            RPTDao rptDao = DaoFactory.createRPTDao();
            retValue = rptDao.findNexValueIdDominio();
        } catch (Exception e) {
            LoggerUtil.error("ERRORE DI ACCESSO AI DATI", e);
            throw new DaoException("",e);
        } finally {
            LoggerUtil.end();
        }
        return retValue;
    }
    public boolean getAllGestioneCodiceContestoPagamentoFlag()   throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException  {
        LoggerUtil.begin();
        boolean retValue = false;
        try {
            ConfigDao configDao = DaoFactory.createConfigDao();
            // TODO allGestioneCodiceContestoPagamento va inserito nel file delle costanti.
            List<Config> configList = configDao.findWhereKeyEquals("allGestioneCodiceContestoPagamento");
            if(!configList.get(0).getValue().equalsIgnoreCase("False")) {
                retValue = true;
            }
        } catch (DaoException e) {
            LoggerUtil.warn("RECORD TABELLA CONFIG NON TROVATO");
            throw new DaoException("",e);
        } catch (Exception e) {
            LoggerUtil.error("ERRORE DI ACCESSO AI DATI", e);
            throw new DaoException("",e);
        } finally {
            LoggerUtil.end();
        }
        return retValue;
    }
    //RDI-45-MULTIBENEFICIARIO FINE

    public boolean isSpontaneo ( String iuv ) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException {

        if ( iuv.startsWith ( "RF" ) ) {
            return true;
        }
        IuvOtticiDao dao = DaoFactory.createIuvOtticiDao ();
        IuvOttici filtro = new IuvOttici ();
        filtro.setIuvOttico ( iuv );
        try {
            IuvOttici iuvDb = dao.find ( filtro );
            if ( null == iuvDb )
                return true;
        } catch ( DaoException e ) {
            LoggerUtil.error ( "ERRORE DI ACCESSO AI DATI", e );
            throw e;
        }
        return false;
    }

    public boolean isPagato ( String iuv ) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException {
        RTDao rtDao = DaoFactory.createRTDao ();
        FlussoSingoloPagamentoDao fspDao = DaoFactory.createFlussoSingoloPagamentoDao ();
        MdpReceiptDao mrDao = DaoFactory.createMdpReceiptDao ();

        try {
            List<FlussoSingoloPagamento> foundFSP
            = fspDao.getFlussoSingoloPagamentoByParam ( null, null, iuv, null, null, null, null, null, null, null, null, null );
            if ( !foundFSP.isEmpty () ) {
                return true;
            }
        } catch ( DaoException e ) {
            LoggerUtil.error ( "ERRORE DI ACCESSO AI DATI", e );
            throw e;
        }

        try {
            int foundMR = mrDao.existMdpReceipt ( iuv );
            if ( foundMR > 0 ) {
                return true;
            }
        } catch ( DaoException e ) {
            LoggerUtil.error ( "ERRORE DI ACCESSO AI DATI", e );
            throw e;
        }

        try {
            List<RT> foundRT = rtDao.getRTByParam ( null, null, null, null, null, null, null, iuv, "1", null );
            if ( !foundRT.isEmpty () ) {
                return true;
            }
        } catch ( DaoException e ) {
            LoggerUtil.error ( "ERRORE DI ACCESSO AI DATI", e );
            throw e;
        }

        return false;
    }

}
