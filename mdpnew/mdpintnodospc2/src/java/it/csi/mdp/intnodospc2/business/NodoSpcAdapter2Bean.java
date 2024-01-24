/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.intnodospc2.business;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.soap.util.mime.ByteArrayDataSource;
import org.xml.sax.SAXException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import it.csi.mdp.adapters.business.BankAdapterImpl;
import it.csi.mdp.adapters.business.BusinessException;
import it.csi.mdp.adapters.business.CoreUtilities;
import it.csi.mdp.adapters.business.PersistenzaDatiNodoUtility;
import it.csi.mdp.adapters.business.SystemException;
import it.csi.mdp.clientmod3.EsitoRiceviRT;
import it.csi.mdp.clientmod3.ParametriRiceviRT;
import it.csi.mdp.clientmod3.Serviziorissrvspc;
import it.csi.mdp.core.business.dto.ApplicationDetail;
import it.csi.mdp.core.business.dto.Applicationcustomfields;
import it.csi.mdp.core.business.dto.ElementoMultiversamento;
import it.csi.mdp.core.business.dto.Gatewaydetail;
import it.csi.mdp.core.business.dto.NodoErrore;
import it.csi.mdp.core.business.dto.RPT;
import it.csi.mdp.core.business.dto.RT;
import it.csi.mdp.core.business.dto.StatiPagamento;
import it.csi.mdp.core.business.dto.StatiRPTEnum;
import it.csi.mdp.core.business.dto.Transazione;
import it.csi.mdp.core.business.dto.TransazioneExtraAttribute;
import it.csi.mdp.core.business.dto.multicarrello.DatiSingoloVersamentoRPT;
import it.csi.mdp.core.business.dto.multicarrello.RPTData;
import it.csi.mdp.core.business.exceptions.DaoException;
import it.csi.mdp.core.business.paymentmanager.Payment;
import it.csi.mdp.core.interfacecxf.MissingParameterException;
import it.csi.mdp.dosignclient.DosignException_Exception;
import it.csi.mdp.dosignclient.DosignSignatureValidation;
import it.csi.mdp.dosignclient.VerifyDto;
import it.csi.mdp.dosignclient.VerifyReportDto;
import it.csi.mdp.generatedvo.multiversamento.ElencoDatiVersamento;
import it.csi.mdp.generatedvo.pagamenti.CtDatiMarcaBolloDigitale;
import it.csi.mdp.generatedvo.pagamenti.CtDatiSingoloVersamentoRPT;
import it.csi.mdp.generatedvo.pagamenti.CtDatiVersamentoRPT;
import it.csi.mdp.generatedvo.pagamenti.CtDominio;
import it.csi.mdp.generatedvo.pagamenti.CtEnteBeneficiario;
import it.csi.mdp.generatedvo.pagamenti.CtIdentificativoUnivocoPersonaFG;
import it.csi.mdp.generatedvo.pagamenti.CtIdentificativoUnivocoPersonaG;
import it.csi.mdp.generatedvo.pagamenti.CtRicevutaTelematica;
import it.csi.mdp.generatedvo.pagamenti.CtRichiestaPagamentoTelematico;
import it.csi.mdp.generatedvo.pagamenti.CtSoggettoPagatore;
import it.csi.mdp.generatedvo.pagamenti.CtSoggettoVersante;
import it.csi.mdp.generatedvo.pagamenti.StAutenticazioneSoggetto;
import it.csi.mdp.generatedvo.pagamenti.StTipoIdentificativoUnivocoPersFG;
import it.csi.mdp.generatedvo.pagamenti.StTipoIdentificativoUnivocoPersG;
import it.csi.mdp.generatedvo.pagamenti.StTipoVersamento;
import it.csi.mdp.intnodospc2.util.LoggerUtil;
import it.csi.mdp.intnodospc2.util.StatoEsitoRPTEnum;
import it.csi.mdp.intnodospc2.util.Utils;
import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.FaultBean;
import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.IntestazioneCarrelloPPT;
import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.NodoChiediCopiaRT;
import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.NodoChiediCopiaRTRisposta;
import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.NodoChiediStatoRPT;
import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.NodoChiediStatoRPTRisposta;
import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.NodoInviaCarrelloRPT;
import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.NodoInviaCarrelloRPTRisposta;
import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.PagamentiTelematiciRPT;
import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.TipoElementoListaRPT;
import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.TipoListaRPT;
import it.csi.mdp.utility.CostantiNodoSpc;
import it.csi.mdp.utility.DatiInterscambioNodoUtility;

public class NodoSpcAdapter2Bean extends BankAdapterImpl {

    private static final String IDENTIFICATIVO_PSP_CARRELLO_RPT = "AGID_01";
    private static final String IDENTIFICATIVO_INTERMEDIARIO_PSP_CARRELLO_RPT = "97735020584";
    private static final String IDENTIFICATIVO_CANALE_CARRELLO_RPT = "97735020584_02";
    private static final long serialVersionUID = 7399816128640098927L;
    private PagamentiTelematiciRPT pagamentiTelematiciService;

    /**
     * Invia la RPT al nodo
     */
    @Override
    public Transazione startTransazione ( Transazione t, Gatewaydetail gd, Applicationcustomfields [] acf, ApplicationDetail ad,
        TransazioneExtraAttribute [] tea, List<RPTData> elencoRPT, boolean multiBeneficiario )
                        throws RemoteException, SystemException, BusinessException {
        LoggerUtil.begin();

        String esito = "KO";

        Map<String, String> mappaAppCustomFields = CoreUtilities.costruisciMappaApplicationCustomFields(acf);

        String errorePerGiornale = "";

        TipoListaRPT listaRPT = new TipoListaRPT();

        //Siccome prima ci sono dei controlli formali, si registrano RPT ed evento giornale solo se si e' avviata la conversazione col nodo
        boolean controlliFormaliSuperati = false;
        String numeroAvviso = null;

        try {
            //crea intestazione SOAP del WS
            IntestazioneCarrelloPPT intestazionePPT = new IntestazioneCarrelloPPT();
            if ( elencoRPT != null )
                LoggerUtil.debug ( "RPT da gestire " + ( elencoRPT.size () ) );
            else
                LoggerUtil.debug ( "NO RPT da gestire" );

            String ccp = "n/a";
            for (RPTData rptInElaborazione : elencoRPT) {

                List<String> elencoErrori = controlliFormali(t, gd, ad, mappaAppCustomFields, rptInElaborazione);

                if ( !CollectionUtils.isEmpty ( elencoErrori ) ) {
                    throw creaBusinessExceptionConDettagli ( elencoErrori.toArray ( new String [0] ) );
                }

                controlliFormaliSuperati = true;


                TipoElementoListaRPT elementoRPT = new TipoElementoListaRPT();
                elementoRPT.setIdentificativoUnivocoVersamento(rptInElaborazione.getDatiVersamento().getIdentificativoUnivocoVersamento());

                // mappaAppCusomFieldsDaUsare.get(CostantiNodoSpc.APP_PARAM_IDENTIFICATIVO_DOMINIO) lollonio
                Map<String, String> mappaAppCusomFieldsDaUsare = null;
                if (StringUtils.isEmpty(rptInElaborazione.getApplicationId()) || t.getApplicationId().equalsIgnoreCase(rptInElaborazione.getApplicationId())) {
                    mappaAppCusomFieldsDaUsare = mappaAppCustomFields;
                } else {
                    Payment p = PersistenzaDatiNodoUtility.reperisciPaymentInterface();
                    mappaAppCusomFieldsDaUsare = CoreUtilities.costruisciMappaApplicationCustomFields(p.getCustomFields(rptInElaborazione.getApplicationId(), null));
                }

                StatiPagamento sp = getStatiPagamento ( rptInElaborazione.getDatiVersamento ().getIdentificativoUnivocoVersamento () );
                LoggerUtil.debug ( "flag pagato: " + sp.isPagato () );
                LoggerUtil.debug ( "flag spontaneo: " + sp.isSpontaneo () );
                LoggerUtil.debug ( "multiBeneficiario: " + multiBeneficiario );
                LoggerUtil.debug ( "getAllGestioneCodiceContestoPagamentoFlag (): " + getAllGestioneCodiceContestoPagamentoFlag () );
                if ( sp.isPagato () ) {
                    LoggerUtil.error ( "ERRORE: PAGAMENTO GIA' EFFETTUATO" );
                    throw new SystemException ( "ERRORE: PAGAMENTO GIA' EFFETTUATO" );
                }
                if ( (!getAllGestioneCodiceContestoPagamentoFlag () && !multiBeneficiario )|| sp.isSpontaneo () ) {
                    elementoRPT.setCodiceContestoPagamento ( ccp );
                    intestazionePPT.setIdentificativoCarrello ( t.getTransactionId () );
                    LoggerUtil.debug ( "1 - ccp settato: " + elementoRPT.getCodiceContestoPagamento () );
                } else {
                    numeroAvviso = getNumeroAvviso ( rptInElaborazione.getDatiVersamento ().getIdentificativoUnivocoVersamento (),
                        rptInElaborazione.getApplicationId (), gd.getGatewayId () );
                    if ( StringUtils.isBlank ( numeroAvviso ) ) {
                        elementoRPT.setCodiceContestoPagamento ( ccp );
                        intestazionePPT.setIdentificativoCarrello ( t.getTransactionId () );
                        LoggerUtil.debug ( "2 - ccp settato: " + elementoRPT.getCodiceContestoPagamento () );
                    } else {
                        //&lt;idDominio(11)&gt;&lt;numeroAvviso(18)&gt;&lt;-&gt;&lt;Progressivo(5)&gt
                        if ( "n/a".equals ( ccp ) ) {
                            ccp = mappaAppCustomFields.get ( CostantiNodoSpc.APP_PARAM_IDENTIFICATIVO_DOMINIO ) + numeroAvviso + "-"
                                + StringUtils.leftPad ( findNexValueIdDominio () + "", 5, '0' );
                        }
                        intestazionePPT.setIdentificativoCarrello ( ccp );
                        elementoRPT.setCodiceContestoPagamento ( ccp );
                        LoggerUtil.debug ( "3 - ccp settato: " + elementoRPT.getCodiceContestoPagamento () );
                    }
                }
                LoggerUtil.debug ( "Fine ccp settato: " + ccp );
                
                CtRichiestaPagamentoTelematico rpt = creazioneRPT(t, mappaAppCusomFieldsDaUsare, rptInElaborazione, ccp);
                
                LoggerUtil.debug("Dati dominio presenti " + (rpt.getDominio() != null));

                LoggerUtil.debug("RPT Creata " + (rpt != null));

                LoggerUtil.debug("Dati dominio presenti " + (rpt.getDominio() != null));

                elementoRPT.setIdentificativoDominio(rpt.getDominio().getIdentificativoDominio());
                
                LoggerUtil.dumpObject("OGGETTO RPT:", rpt);

                LoggerUtil.debug("Generazione XML Out ############################################################");

                ByteArrayOutputStream output = DatiInterscambioNodoUtility.generaXml(rpt);
                elementoRPT.setRpt(output.toByteArray());

                elementoRPT.setTipoFirma("");
                listaRPT.getElementoListaRPT().add(elementoRPT );
            }

            LoggerUtil.debug("Fine gesitone RPT #####################################");

            intestazionePPT.setIdentificativoIntermediarioPA(mappaAppCustomFields.get(CostantiNodoSpc.APP_PARAM_IDENTIFICATIVOINTERMEDIARIO_PA));
            intestazionePPT.setIdentificativoStazioneIntermediarioPA(mappaAppCustomFields.get(CostantiNodoSpc.APP_PARAM_IDENTIFICATIVO_STAZIONE_INTERMEDIARIO_PA));

            //crea body SOAP del WS
            NodoInviaCarrelloRPT nodoInviaCarrelloRPT = new NodoInviaCarrelloRPT();
            nodoInviaCarrelloRPT.setIdentificativoCanale(IDENTIFICATIVO_CANALE_CARRELLO_RPT);
            nodoInviaCarrelloRPT.setIdentificativoIntermediarioPSP(IDENTIFICATIVO_INTERMEDIARIO_PSP_CARRELLO_RPT);
            nodoInviaCarrelloRPT.setIdentificativoPSP(IDENTIFICATIVO_PSP_CARRELLO_RPT);
            nodoInviaCarrelloRPT.setPassword(mappaAppCustomFields.get(CostantiNodoSpc.APP_PARAM_PASSWORD_DOMINIO_NODO_SPC));
            if ( multiBeneficiario ) {
                nodoInviaCarrelloRPT.setMultiBeneficiario ( multiBeneficiario );
            }
            nodoInviaCarrelloRPT.setListaRPT(listaRPT);

            //Viene gia' fatto dalla gestioneMultiversamentoUniversale
            //spacchettaPagamentiModello3(t,tea);

            JaxWsProxyFactoryBean factory = CoreUtilities.inizializzaServizio(mappaAppCustomFields.get(CostantiNodoSpc.APP_PARAM_PORTA_DI_DOMINIO), "nodoInviaCarrelloRPT");

            pagamentiTelematiciService = (PagamentiTelematiciRPT)factory.create();

            // [MP] - 16/06/2020 check sull'applicazione per verificare il supporto dell'integrazione diretta pagoPA con il GAD
            // boolean gadEnabled = Boolean.getBoolean(mappaAppCustomFields.get(CostantiNodoSpc.APP_PARAM_GAD_ENABLED));
            LoggerUtil.info("Parametro gadEnabled letto da mappa: <"+mappaAppCustomFields.get("gadEnabled")+">");
            boolean gadEnabled = Boolean.parseBoolean(mappaAppCustomFields.get("gadEnabled"));
            LoggerUtil.info("Applicazione "+ad.getApplicationid()+" integrata direttamente con pagoPA: <"+gadEnabled+">");


            if(gadEnabled)
                it.csi.mdp.intnodospc2.util.SSLUtils.setTLSContext(pagamentiTelematiciService);

            NodoInviaCarrelloRPTRisposta risposta = pagamentiTelematiciService.nodoInviaCarrelloRPT(nodoInviaCarrelloRPT, intestazionePPT);
            LoggerUtil.dumpObject("RISPOSTA OTTENUTA DAL NODO:", risposta);
            if (risposta.getEsitoComplessivoOperazione().equalsIgnoreCase("KO")) {
                t.setPayurl(null);
                errorePerGiornale = estraiMotiviErrore(risposta);
                t.setErrcode(PersistenzaDatiNodoUtility.substring(errorePerGiornale, 0, 50));
                LoggerUtil.error(t.getErrcode());
                throw creaBusinessExceptionConDettagli(errorePerGiornale);
            } else {
                t.setPayurl(risposta.getUrl());
                String idSession = Utils.convertiUrlInMapParametri(risposta.getUrl()).get("idSession");
                aggiornaIdSessionTransazione(t.getTransactionId(), idSession);
                esito = "OK";
            }

        } catch (BusinessException e) {
            t.setErrcode("ERRORE DI VALIDAZIONE ATTRIBUTI");
            registraErrori(e.getDettagli(), t);
            throw e;
        } catch (Throwable e) {
            //Si logga l'eccezione qui e si rilancia col solo testo per evitare UndeclaredThrowableException
            LoggerUtil.error("ERRORE DI SISTEMA NELL'ADAPTER: ", e);
            t.setErrcode(PersistenzaDatiNodoUtility.substring(e.getMessage(), 0, 50));
            errorePerGiornale = Utils.concatenaMessaggiCausa(e).toString();
            throw new SystemException("ERRORE DI SISTEMA NELL'ADAPTER");
        } finally {
            //Registrazione dell'evento di giornale e di tutte le RPT inviate
            if (controlliFormaliSuperati) {
                Utils.registraEventoGiornale(t.getApplicationId(),t.getTransactionId(), gd.getGatewayId(), esito, "nodoInviaCarrelloRPT", t.getTransactionId(), IDENTIFICATIVO_PSP_CARRELLO_RPT,
                    mappaAppCustomFields.get(CostantiNodoSpc.APP_PARAM_IDENTIFICATIVO_STAZIONE_INTERMEDIARIO_PA), mappaAppCustomFields.get(CostantiNodoSpc.APP_PARAM_IDENTIFICATIVO_DOMINIO), IDENTIFICATIVO_PSP_CARRELLO_RPT,
                    IDENTIFICATIVO_INTERMEDIARIO_PSP_CARRELLO_RPT, errorePerGiornale);

                for (int i= 0; i<listaRPT.getElementoListaRPT().size(); i++) {
                    TipoElementoListaRPT elementoRPT = listaRPT.getElementoListaRPT().get(i);
                    RPTData rptRicevutaFruitore = elencoRPT.get(i);

                    //RDI-45 MULTIBENEFICIARIO. Si passa il codice contesto pagamento e i due nuovi campi Multibeneficiario (flag e il numero avviso
                    registraRPT(StringUtils.isNotEmpty(rptRicevutaFruitore.getApplicationId()) ? rptRicevutaFruitore.getApplicationId() : t.getApplicationId(),
                                    t.getTransactionId(),
                                    rptRicevutaFruitore.getAutenticazioneSoggetto(),
                                    IDENTIFICATIVO_CANALE_CARRELLO_RPT,
                                    IDENTIFICATIVO_PSP_CARRELLO_RPT,
                                    IDENTIFICATIVO_INTERMEDIARIO_PSP_CARRELLO_RPT,
                                    new String(elementoRPT.getRpt()),
                                    elementoRPT.getIdentificativoDominio(),
                                    mappaAppCustomFields.get(CostantiNodoSpc.APP_PARAM_IDENTIFICATIVOINTERMEDIARIO_PA),
                                    mappaAppCustomFields.get(CostantiNodoSpc.APP_PARAM_IDENTIFICATIVO_STAZIONE_INTERMEDIARIO_PA),
                                    rptRicevutaFruitore.getDatiVersamento().getIdentificativoUnivocoVersamento(),
                                    esito,
                                    //					                "n/a");
                                    elementoRPT.getCodiceContestoPagamento(),
                                    multiBeneficiario,
                                    numeroAvviso);
                }
            }

        }
        LoggerUtil.end();
        return t;
    }

    private String estraiMotiviErrore(NodoInviaCarrelloRPTRisposta risposta) {
        StringBuilder sb = new StringBuilder();
        if (risposta.getFault() != null) {
            return risposta.getFault().getFaultCode() + " - " + risposta.getFault().getFaultString() + " - " + risposta.getFault().getDescription();
        }
        if (CollectionUtils.isNotEmpty(risposta.getListaErroriRPT().getFault())) {
            for (FaultBean fault : risposta.getListaErroriRPT().getFault()) {
                sb.append(fault.getFaultCode()).append(" - ").append(fault.getFaultString()).append(" - ").append(fault.getDescription());
            }
        }
        return sb.toString();
    }

    /**
     * Crea ogni singola RPT del carrello
     * @param t
     * @param mappaAppCustomFields
     * @param rptInElaborazione
     * @param ccp 
     * @return
     * @throws DatatypeConfigurationException
     * @throws CreateException
     * @throws NamingException
     * @throws RemoteException
     * @throws MissingParameterException
     * @throws DaoException
     * @throws JAXBException
     */
    private CtRichiestaPagamentoTelematico creazioneRPT(Transazione t,Map<String, String> mappaAppCusomFieldsDaUsare, RPTData rptInElaborazione, String ccp) throws DatatypeConfigurationException, RemoteException, NamingException, CreateException, DaoException, MissingParameterException, JAXBException {

        //L'RPT generato qui e' STANDARD di GOV.IT per cui **NON MODIFICABILE**

        XMLGregorianCalendar dataEsecuzionePagamento = DatatypeFactory.newInstance().newXMLGregorianCalendar();
        GregorianCalendar gc = new GregorianCalendar();
        dataEsecuzionePagamento.setDay(gc.get(Calendar.DAY_OF_MONTH));
        dataEsecuzionePagamento.setMonth(gc.get(Calendar.MONTH)+1);
        dataEsecuzionePagamento.setYear(gc.get(Calendar.YEAR));

        CtRichiestaPagamentoTelematico rptNodo = new CtRichiestaPagamentoTelematico();
        rptNodo.setVersioneOggetto("6.2");
        rptNodo.setAutenticazioneSoggetto(StAutenticazioneSoggetto.fromValue(rptInElaborazione.getAutenticazioneSoggetto()));
        rptNodo.setDataOraMessaggioRichiesta(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar()));
        rptNodo.setIdentificativoMessaggioRichiesta(t.getTransactionId());

        CtDominio dominio = new CtDominio();
        dominio.setIdentificativoDominio(mappaAppCusomFieldsDaUsare.get(CostantiNodoSpc.APP_PARAM_IDENTIFICATIVO_DOMINIO));
        dominio.setIdentificativoStazioneRichiedente(rptInElaborazione.getIdStazioneRichiedente());

        rptNodo.setDominio(dominio);
        CtEnteBeneficiario enteBeneficiario = new CtEnteBeneficiario(); // obb

        CtIdentificativoUnivocoPersonaG identificativoEnte = new CtIdentificativoUnivocoPersonaG();
        // obb cf ente beneficiario
        identificativoEnte.setCodiceIdentificativoUnivoco(mappaAppCusomFieldsDaUsare.get(CostantiNodoSpc.APP_PARAM_CODICE_IDENTIFICATIVO_UNIVOCO_BENEFICIARIO));
        identificativoEnte.setTipoIdentificativoUnivoco(StTipoIdentificativoUnivocoPersG.G); // obb e' g per forza
        enteBeneficiario.setIdentificativoUnivocoBeneficiario(identificativoEnte);
        enteBeneficiario.setDenominazioneBeneficiario(mappaAppCusomFieldsDaUsare.get(CostantiNodoSpc.APP_PARAM_DENOMINAZIONE_BENEFICIARIO));
        enteBeneficiario.setCodiceUnitOperBeneficiario(mappaAppCusomFieldsDaUsare.get(CostantiNodoSpc.APP_PARAM_CODICE_UNIT_OPER_BENEFICIARIO));
        enteBeneficiario.setDenomUnitOperBeneficiario(mappaAppCusomFieldsDaUsare.get(CostantiNodoSpc.APP_PARAM_DENOM_UNIT_OPER_BENEFICIARIO));
        enteBeneficiario.setIndirizzoBeneficiario(mappaAppCusomFieldsDaUsare.get(CostantiNodoSpc.APP_PARAM_INDIRIZZO_BENEFICIARIO));
        enteBeneficiario.setCivicoBeneficiario(mappaAppCusomFieldsDaUsare.get(CostantiNodoSpc.APP_PARAM_CIVICO_BENEFICIARIO));
        enteBeneficiario.setCapBeneficiario(mappaAppCusomFieldsDaUsare.get(CostantiNodoSpc.APP_PARAM_CAP_BENEFICIARIO));
        enteBeneficiario.setLocalitaBeneficiario(mappaAppCusomFieldsDaUsare.get(CostantiNodoSpc.APP_PARAM_LOCALITA_BENEFICIARIO));
        enteBeneficiario.setProvinciaBeneficiario(mappaAppCusomFieldsDaUsare.get(CostantiNodoSpc.APP_PARAM_PROVINCIA_BENEFICIARIO));
        enteBeneficiario.setNazioneBeneficiario(mappaAppCusomFieldsDaUsare.get(CostantiNodoSpc.APP_PARAM_NAZIONE_BENEFICIARIO));

        rptNodo.setEnteBeneficiario(enteBeneficiario);

        if (rptInElaborazione.getSoggettoVersante() != null) {
            CtSoggettoVersante versante = new CtSoggettoVersante();
            versante.setAnagraficaVersante(rptInElaborazione.getSoggettoVersante().getAnagraficaVersante());
            versante.setCapVersante(rptInElaborazione.getSoggettoVersante().getCapVersante());
            versante.setCivicoVersante(rptInElaborazione.getSoggettoVersante().getCivicoVersante());
            versante.setEMailVersante(rptInElaborazione.getSoggettoVersante().geteMailVersante());
            CtIdentificativoUnivocoPersonaFG idVersante = new CtIdentificativoUnivocoPersonaFG();
            if (rptInElaborazione.getSoggettoVersante().getIdentificativoUnivocoVersante() != null) {
                idVersante.setCodiceIdentificativoUnivoco(rptInElaborazione.getSoggettoVersante().getIdentificativoUnivocoVersante());
                idVersante.setTipoIdentificativoUnivoco(StTipoIdentificativoUnivocoPersFG.valueOf(rptInElaborazione.getSoggettoVersante().getTipoIdentificativoUnivocoVersante()));
                versante.setIdentificativoUnivocoVersante(idVersante);
            }
            versante.setIndirizzoVersante(rptInElaborazione.getSoggettoVersante().getIndirizzoVersante());
            versante.setLocalitaVersante(rptInElaborazione.getSoggettoVersante().getLocalitaVersante());
            versante.setNazioneVersante(rptInElaborazione.getSoggettoVersante().getNazioneVersante());
            versante.setProvinciaVersante(rptInElaborazione.getSoggettoVersante().getProvinciaVersante());
            rptNodo.setSoggettoVersante(versante);
        }

        CtSoggettoPagatore pagatore = new CtSoggettoPagatore();
        pagatore.setAnagraficaPagatore(rptInElaborazione.getSoggettoPagatore().getAnagraficaPagatore());
        pagatore.setCapPagatore(rptInElaborazione.getSoggettoPagatore().getCapPagatore());
        pagatore.setCivicoPagatore(rptInElaborazione.getSoggettoPagatore().getCivicoPagatore());
        pagatore.setEMailPagatore(rptInElaborazione.getSoggettoPagatore().geteMailPagatore());
        CtIdentificativoUnivocoPersonaFG idPagatore = new CtIdentificativoUnivocoPersonaFG();
        if (rptInElaborazione.getSoggettoPagatore().getIdentificativoUnivocoPagatore() != null) {
            idPagatore.setCodiceIdentificativoUnivoco(rptInElaborazione.getSoggettoPagatore().getIdentificativoUnivocoPagatore());
            idPagatore.setTipoIdentificativoUnivoco(StTipoIdentificativoUnivocoPersFG.valueOf(rptInElaborazione.getSoggettoPagatore().getTipoIdentificativoUnivocoPagatore()));
            pagatore.setIdentificativoUnivocoPagatore(idPagatore);
        }
        pagatore.setIndirizzoPagatore(rptInElaborazione.getSoggettoPagatore().getIndirizzoPagatore());
        pagatore.setLocalitaPagatore(rptInElaborazione.getSoggettoPagatore().getLocalitaPagatore());
        pagatore.setNazionePagatore(rptInElaborazione.getSoggettoPagatore().getNazionePagatore());
        pagatore.setProvinciaPagatore(rptInElaborazione.getSoggettoPagatore().getProvinciaPagatore());
        rptNodo.setSoggettoPagatore(pagatore);

        CtDatiVersamentoRPT versamento = new CtDatiVersamentoRPT();
        versamento.setBicAddebito(rptInElaborazione.getDatiVersamento().getBicAddebito());
        versamento.setCodiceContestoPagamento(ccp);
        versamento.setDataEsecuzionePagamento(dataEsecuzionePagamento);
        versamento.setFirmaRicevuta(!StringUtils.isEmpty(rptInElaborazione.getDatiVersamento().getFirmaRicevuta()) ? rptInElaborazione.getDatiVersamento().getFirmaRicevuta() : mappaAppCusomFieldsDaUsare.get(CostantiNodoSpc.APP_PARAM_FIRMA_RT));
        versamento.setIbanAddebito(rptInElaborazione.getDatiVersamento().getIbanAddebito());
        versamento.setBicAddebito(rptInElaborazione.getDatiVersamento().getBicAddebito());
        versamento.setIdentificativoUnivocoVersamento(rptInElaborazione.getDatiVersamento().getIdentificativoUnivocoVersamento());
        versamento.setImportoTotaleDaVersare(rptInElaborazione.getDatiVersamento().getImportoTotaleDaVersare());
        versamento.setTipoVersamento(StTipoVersamento.BBT);

        CtDatiSingoloVersamentoRPT datiSingoloVersamento = new CtDatiSingoloVersamentoRPT();
        datiSingoloVersamento.setImportoSingoloVersamento(rptInElaborazione.getDatiVersamento().getImportoTotaleDaVersare());
        if (!StringUtils.isEmpty(mappaAppCusomFieldsDaUsare.get(CostantiNodoSpc.APP_PARAM_COMMISSIONE_CARICO_PA)))
            datiSingoloVersamento.setCommissioneCaricoPA(BigDecimal.valueOf(Double.parseDouble(mappaAppCusomFieldsDaUsare.get(CostantiNodoSpc.APP_PARAM_COMMISSIONE_CARICO_PA))));

        datiSingoloVersamento.setIbanAccredito(StringUtils.isNotEmpty(mappaAppCusomFieldsDaUsare.get(CostantiNodoSpc.APP_PARAM_IBAN_ACCREDITO)) ? mappaAppCusomFieldsDaUsare.get(CostantiNodoSpc.APP_PARAM_IBAN_ACCREDITO) : "");
        datiSingoloVersamento.setBicAccredito(mappaAppCusomFieldsDaUsare.get(CostantiNodoSpc.APP_PARAM_BIC_ACCREDITO));

        datiSingoloVersamento.setIbanAppoggio(mappaAppCusomFieldsDaUsare.get(CostantiNodoSpc.APP_PARAM_IBAN_APPOGGIO));
        datiSingoloVersamento.setBicAppoggio(mappaAppCusomFieldsDaUsare.get(CostantiNodoSpc.APP_PARAM_BIC_APPOGGIO));
        datiSingoloVersamento.setCredenzialiPagatore(rptInElaborazione.getDatiVersamento().getDatiSingoloVersamento().get(0).getCredenzialiPagatore());

        if(rptInElaborazione.getDatiVersamento().getDatiSingoloVersamento().get(0).getDatiMarcaBolloDigitale() != null) {
            CtDatiMarcaBolloDigitale marcaDaBolloDigitale = new CtDatiMarcaBolloDigitale();

            marcaDaBolloDigitale.setTipoBollo(rptInElaborazione.getDatiVersamento().getDatiSingoloVersamento().get(0).getDatiMarcaBolloDigitale().getTipoBollo());
            marcaDaBolloDigitale.setHashDocumento(rptInElaborazione.getDatiVersamento().getDatiSingoloVersamento().get(0).getDatiMarcaBolloDigitale().getHashDocumento());
            marcaDaBolloDigitale.setProvinciaResidenza(rptInElaborazione.getDatiVersamento().getDatiSingoloVersamento().get(0).getDatiMarcaBolloDigitale().getProvinciaResidenza());

            //La struttura e obbligatoria qualora l'informazione ibanAccredito NON sia presente.
            //In tutti gli altri casi NON deve essere popolata

            //Ribalto la logica ed elimino i dati non richiesti di accredito
            datiSingoloVersamento.setIbanAccredito(null);
            datiSingoloVersamento.setBicAccredito(null);

            datiSingoloVersamento.setDatiMarcaBolloDigitale(marcaDaBolloDigitale);
        }

        //per preservare il testo facoltativo ("/TXT") in caso di singolo pagamento per RPT si usa quella fornita dal client, altrimenti va composta con l'aggregazione.
        datiSingoloVersamento.setCausaleVersamento(rptInElaborazione.getDatiVersamento().getDatiSingoloVersamento().get(0).getCausaleVersamento());
        if (rptInElaborazione.getDatiVersamento().getDatiSingoloVersamento().size() >1) {
            datiSingoloVersamento.setCausaleVersamento(componiCausaleVersamento(rptInElaborazione.getDatiVersamento().getIdentificativoUnivocoVersamento(), rptInElaborazione.getDatiVersamento().getImportoTotaleDaVersare(), ""));
        }
        datiSingoloVersamento.setDatiSpecificiRiscossione(!StringUtils.isEmpty(rptInElaborazione.getDatiVersamento().getDatiSingoloVersamento().get(0).getDatiSpecificiRiscossione()) ? rptInElaborazione.getDatiVersamento().getDatiSingoloVersamento().get(0).getDatiSpecificiRiscossione() : mappaAppCusomFieldsDaUsare.get(CostantiNodoSpc.APP_PARAM_DATI_SPECIFICI_RISCOSSIONE));

        versamento.getDatiSingoloVersamento().add(datiSingoloVersamento);

        gestioneMultiversamentoModello1(rptInElaborazione, t);

        rptNodo.setDatiVersamento(versamento);

        return rptNodo;
    }

    private void gestioneMultiversamentoModello1(RPTData rptInElaborazione, Transazione t) throws RemoteException, NamingException, CreateException, JAXBException, DaoException, MissingParameterException {

        //TAG_PPU - 2019 - RDI 004 - RDI 005
        //if (rptInElaborazione.getDatiVersamento().getDatiSingoloVersamento().size() > 1) {
        Payment p = PersistenzaDatiNodoUtility.reperisciPaymentInterface();
        ElementoMultiversamento elemento = new ElementoMultiversamento();
        elemento.setApplicationId(StringUtils.isNotEmpty(rptInElaborazione.getApplicationId()) ? rptInElaborazione.getApplicationId() : t.getApplicationId());
        elemento.setTransactionId(t.getTransactionId());
        elemento.setPosizione(0);
        elemento.setIuv(rptInElaborazione.getDatiVersamento().getIdentificativoUnivocoVersamento());
        elemento.setElencoSingoliPagamentiSpacchettati(rptInElaborazione.getDatiVersamento().getDatiSingoloVersamento());
        XStream xstream = new XStream(new DomDriver());
        elemento.setXmlElemento(xstream.toXML(rptInElaborazione));
        elemento.setModelloPagamento("1");
        p.inserisciMultiVersamento(elemento);
        //TAG_PPU - 2019 - RDI 004 - RDI 005
        //}
    }

    /**
     * Chiede la RT e verifica lo stato
     */
    @Override
    public Transazione getStatoTransazione(Transazione t, Gatewaydetail gd, Applicationcustomfields[] acf, long oldState) throws RemoteException {
        LoggerUtil.begin();

        String esito = "OK";
        Map<String, String> mappaAcf = CoreUtilities.costruisciMappaApplicationCustomFields(acf);
        Map<String, String> mappaTea = null;
        Payment p = null;
        RPT richiestaTrovata = null;
        CtRicevutaTelematica rt = null;
        try {
            if (t.getCodStato() < 3) {
                return t;
            }

            p = PersistenzaDatiNodoUtility.reperisciPaymentInterface ();
            TransazioneExtraAttribute [] tea = p.getTransazioneExtraAttributes ( t.getTransactionId () );
            mappaTea = CoreUtilities.costruisciMappaTransazioneExtraAttributes ( tea );
            t.setErrcode("");

            RT rtPresente = p.recuperaRichiestaPerIdTransazione(t.getTransactionId());
            LoggerUtil.dumpObject ( "RT recuperata: ", rtPresente );
            if (rtPresente == null) {

                RPT filtro = new RPT();
                filtro.setTransactionId(t.getTransactionId());
                List<RPT> elencoRpt = p.recuperaRichiestaPagamentoConFiltro(filtro);
                if (!CollectionUtils.isEmpty(elencoRpt)) {
                    richiestaTrovata = elencoRpt.get(0);
                } else {
                    return t;
                }

                NodoChiediCopiaRTRisposta rispostaRT = invocaChiediCopiaRT(mappaAcf, mappaTea, richiestaTrovata, gd);

                if (rispostaRT == null || rispostaRT.getRt() == null) {
                    LoggerUtil.warn ( "RT non presente: Funzione invocaChiediCopiaRT non effettuata, verra' restituito lo stato della transazione presente su MDP!");
                    return t;
                }

                byte[] rtData = IOUtils.toByteArray(rispostaRT.getRt().getInputStream());
                byte[] rtDataPerOggetto = rtData;
                if (!StringUtils.isEmpty(rispostaRT.getTipoFirma()) && !"0".equals(rispostaRT.getTipoFirma())) {
                    rtDataPerOggetto = estraiRTFirmata(rtData);
                }

                Unmarshaller unmarshaller = unMarshallerRT();

                rt = (CtRicevutaTelematica)unmarshaller.unmarshal(new ByteArrayInputStream(rtDataPerOggetto));
                String statoInvioFruitore = "OK";
                if ("Y".equalsIgnoreCase(mappaAcf.get("flagRiceviRT")))
                    statoInvioFruitore = spedisciRTAlFruitore(rt, richiestaTrovata, rtDataPerOggetto, rispostaRT.getTipoFirma(), p, mappaAcf);

                PersistenzaDatiNodoUtility.registraRT(rtData, t.getApplicationId(), t.getTransactionId(), rispostaRT.getTipoFirma(), rt, Integer.valueOf(rt.getDatiPagamento().getCodiceEsitoPagamento()) +1, statoInvioFruitore, "WEBSERVICES", new Timestamp ( new Date ().getTime () ), richiestaTrovata.getCodiceContestoPagamento(), richiestaTrovata.getIdentificativoDominio());

                t.setOldstate(t.getCodStato());
                t.setCodStato("0".equals(rt.getDatiPagamento().getCodiceEsitoPagamento()) ? 4 : 5);
                t.setPgresultcode(rt.getDatiPagamento().getCodiceEsitoPagamento());
                if (rt.getDatiPagamento().getDatiSingoloPagamento().get(0).getCommissioniApplicatePSP() != null)
                    t.setCommissioniApplicate(rt.getDatiPagamento().getDatiSingoloPagamento().get(0).getCommissioniApplicatePSP().doubleValue());
            } else {
                if (rtPresente.getIdEsitoPagamento().equals(BigDecimal.ONE.intValue())) {
                    t.setOldstate(t.getCodStato());
                    t.setCodStato(4);
                } else {
                    t.setOldstate(t.getCodStato());
                    t.setCodStato(5);
                }

            }
        } catch (Exception e) {
            LoggerUtil.error("ERRORE ", e);
            esito = "KO";
            throw new RemoteException(e.getMessage(),e);
        } finally {
            if ( rt != null ) {
                LoggerUtil.info ( "Aggiornamento RPT" );
                aggiornaRPT ( richiestaTrovata, esito );
            }
        }
        LoggerUtil.end();
        return t;

    }

    private NodoChiediCopiaRTRisposta invocaChiediCopiaRT ( Map<String, String> mappaAcf, Map<String, String> mappaTea, RPT richiesta, Gatewaydetail gd ) {
        LoggerUtil.begin ();
        JaxWsProxyFactoryBean factory = CoreUtilities.inizializzaServizio ( mappaAcf.get ( CostantiNodoSpc.APP_PARAM_PORTA_DI_DOMINIO ), "nodoChiediStatoRPT" );
        pagamentiTelematiciService = (PagamentiTelematiciRPT) factory.create ();
        NodoChiediStatoRPT bodyRichiestaRPT = new NodoChiediStatoRPT ();
        bodyRichiestaRPT.setCodiceContestoPagamento ( richiesta.getCodiceContestoPagamento () );
        bodyRichiestaRPT.setIdentificativoDominio ( mappaAcf.get ( CostantiNodoSpc.APP_PARAM_IDENTIFICATIVO_DOMINIO ) );
        bodyRichiestaRPT.setIdentificativoIntermediarioPA ( mappaAcf.get ( CostantiNodoSpc.APP_PARAM_IDENTIFICATIVOINTERMEDIARIO_PA ) );
        bodyRichiestaRPT.setIdentificativoStazioneIntermediarioPA ( mappaAcf.get ( CostantiNodoSpc.APP_PARAM_IDENTIFICATIVO_STAZIONE_INTERMEDIARIO_PA ) );
        bodyRichiestaRPT.setIdentificativoUnivocoVersamento ( richiesta.getIuv () );
        bodyRichiestaRPT.setPassword ( mappaAcf.get ( CostantiNodoSpc.APP_PARAM_PASSWORD_DOMINIO_NODO_SPC ) );
        NodoChiediStatoRPTRisposta rispostaRPT = null;
        StatoEsitoRPTEnum esitoStato = StatoEsitoRPTEnum.KO;
        // [MP] - 16/06/2020 check sull'applicazione per verificare il supporto dell'integrazione diretta pagoPA con il GAD
        // boolean gadEnabled = Boolean.getBoolean(mappaAcf.get(CostantiNodoSpc.APP_PARAM_GAD_ENABLED));
        boolean gadEnabled = Boolean.parseBoolean(mappaAcf.get("gadEnabled"));
        LoggerUtil.info("Applicazione "+CostantiNodoSpc.APP_PARAM_IDENTIFICATIVO_STAZIONE_INTERMEDIARIO_PA+" integrata direttamente con pagoPA: <"+gadEnabled+">");
        if ( gadEnabled ) {
            try {
                it.csi.mdp.intnodospc2.util.SSLUtils.setTLSContext ( pagamentiTelematiciService );
            } catch ( Exception e2 ) {
                LoggerUtil.error ( "IMPOSSIBILE REGISTRARE ERRORI ", e2 );
            }
        }
        try {
            LoggerUtil.dumpObject ( "invocazione servizio nodoChiediStatoRPT : ", bodyRichiestaRPT );
            rispostaRPT = pagamentiTelematiciService.nodoChiediStatoRPT ( bodyRichiestaRPT );
            LoggerUtil.dumpObject ( "RISPOSTA ", rispostaRPT );
            esitoStato = StatoEsitoRPTEnum.valueOf ( rispostaRPT.getEsito ().getStato () );
        } catch ( Exception e ) {
            LoggerUtil.error ( "Errore in fase di invocazione nodoChiediStatoRPT", e );
            try {
                Transazione t = new Transazione ();
                t.setTransactionId ( richiesta.getTransactionId () );
                t.setApplicationId ( richiesta.getApplicationId () );
                LoggerUtil.debug ( "Registrazione errore " );
                registraErrori ( Arrays.asList ( "Errore in fase di invocazione nodoChiediStatoRPT" ), t );
            } catch ( RemoteException e1 ) {
                LoggerUtil.error ( "IMPOSSIBILE REGISTRARE ERRORI ", e );
            }
        } finally {
            String errorePerGiornale = "";
            String esitoChiamataNodoRPT = "OK";
            if ( null != rispostaRPT && null != rispostaRPT.getFault () ) {
                errorePerGiornale = rispostaRPT.getFault ().getFaultCode () + " - " + rispostaRPT.getFault ().getFaultString () + " - "
                                + rispostaRPT.getFault ().getDescription ();
                esitoChiamataNodoRPT = "KO";
                LoggerUtil.debug ( "Evento KO su GDE" );
            } else {
                errorePerGiornale = ( null != rispostaRPT && null != rispostaRPT.getEsito () ) ? rispostaRPT.getEsito ().getStato () : "";
            }
            LoggerUtil.debug ( "Registrazione evento su GDE " );
            Utils.registraEventoGiornale ( richiesta.getApplicationId (), richiesta.getTransactionId (), gd.getGatewayId (),
                esitoChiamataNodoRPT, "nodoChiediStatoRPT",
                richiesta.getIuv (), IDENTIFICATIVO_PSP_CARRELLO_RPT,
                mappaAcf.get ( CostantiNodoSpc.APP_PARAM_IDENTIFICATIVO_STAZIONE_INTERMEDIARIO_PA ),
                mappaAcf.get ( CostantiNodoSpc.APP_PARAM_IDENTIFICATIVO_DOMINIO ), IDENTIFICATIVO_PSP_CARRELLO_RPT,
                IDENTIFICATIVO_INTERMEDIARIO_PSP_CARRELLO_RPT, errorePerGiornale );
        }

        if ( rispostaRPT != null && rispostaRPT.getEsito () != null ) {
            LoggerUtil.debug ( "Esito Ok controllo gli stati della rpt" );
            if ( esitoStato == StatoEsitoRPTEnum.RT_ACCETTATA_PA || esitoStato == StatoEsitoRPTEnum.RT_RIFIUTATA_PA
                            || esitoStato == StatoEsitoRPTEnum.RT_ESITO_SCONOSCIUTO_PA || esitoStato == StatoEsitoRPTEnum.RT_ERRORE_INVIO_A_PA ) {
                LoggerUtil.debug ( "Stato della rpt: " + rispostaRPT.getEsito ().getStato () );
                NodoChiediCopiaRT bodyrichiestaRT = new NodoChiediCopiaRT ();
                bodyrichiestaRT.setCodiceContestoPagamento ( richiesta.getCodiceContestoPagamento () );// obb
                bodyrichiestaRT.setIdentificativoDominio ( mappaAcf.get ( CostantiNodoSpc.APP_PARAM_IDENTIFICATIVO_DOMINIO ) ); // obb
                bodyrichiestaRT.setIdentificativoIntermediarioPA ( mappaAcf.get ( CostantiNodoSpc.APP_PARAM_IDENTIFICATIVOINTERMEDIARIO_PA ) ); // obb Identificativo dell'intermediario PA che puo' richiedere il flusso?????? CF sembra
                bodyrichiestaRT
                .setIdentificativoStazioneIntermediarioPA ( mappaAcf.get ( CostantiNodoSpc.APP_PARAM_IDENTIFICATIVO_STAZIONE_INTERMEDIARIO_PA ) ); // obb altro CF
                bodyrichiestaRT.setIdentificativoUnivocoVersamento ( richiesta.getIuv () );
                bodyrichiestaRT.setPassword ( mappaAcf.get ( CostantiNodoSpc.APP_PARAM_PASSWORD_DOMINIO_NODO_SPC ) ); // obb

                NodoChiediCopiaRTRisposta rispostaRT = null;
                try {
                    factory = CoreUtilities.inizializzaServizio ( mappaAcf.get ( CostantiNodoSpc.APP_PARAM_PORTA_DI_DOMINIO ), "nodoChiediCopiaRT" );
                    LoggerUtil.debug ( "Invocazione nodoChiediCopiaRT" );
                    rispostaRT = pagamentiTelematiciService.nodoChiediCopiaRT ( bodyrichiestaRT );
                    LoggerUtil.dumpObject ( "RISPOSTA ", rispostaRT );
                } catch ( Exception e ) {
                    LoggerUtil.error ( "Errore in fase di recupero della RT ", e );
                } finally {
                    String errorePerGiornale = "";
                    String esitoChiamataNodoRT = "OK";
                    if ( null != rispostaRT && null != rispostaRT.getFault () ) {
                        errorePerGiornale  = rispostaRT.getFault ().getFaultCode () + " - " + rispostaRT.getFault ().getFaultString () + " - "
                                        + rispostaRT.getFault ().getDescription ();
                        esitoChiamataNodoRT = "KO";
                        LoggerUtil.debug ( "Evento recupero RT su GDE" );
                    }
                    Utils.registraEventoGiornale ( richiesta.getApplicationId (), richiesta.getTransactionId (), gd.getGatewayId (),
                        esitoChiamataNodoRT, "nodoChiediCopiaRT",
                        richiesta.getIuv (), IDENTIFICATIVO_PSP_CARRELLO_RPT,
                        mappaAcf.get ( CostantiNodoSpc.APP_PARAM_IDENTIFICATIVO_STAZIONE_INTERMEDIARIO_PA ),
                        mappaAcf.get ( CostantiNodoSpc.APP_PARAM_IDENTIFICATIVO_DOMINIO ), IDENTIFICATIVO_PSP_CARRELLO_RPT,
                        IDENTIFICATIVO_INTERMEDIARIO_PSP_CARRELLO_RPT, errorePerGiornale );
                }
                LoggerUtil.end ();
                return rispostaRT;
            }
        }
        LoggerUtil.end ();
        return null;
    }


    private byte[] estraiRTFirmata(byte[] rtData) throws DosignException_Exception, IOException {
        JaxWsProxyFactoryBean fact = new JaxWsProxyFactoryBean();
        fact.getInInterceptors().add(new LoggingInInterceptor());
        fact.getOutInterceptors().add(new LoggingOutInterceptor());
        fact.setServiceClass(DosignSignatureValidation.class);
        Properties propsEnv = new Properties();
        propsEnv.load(this.getClass().getResourceAsStream("/META-INF/env.properties"));
        LoggerUtil.debug("PROPERTIES: " + propsEnv.getProperty("endpointDosign"));
        fact.setAddress(propsEnv.getProperty("endpointDosign"));
        Map<String,Object> props = new HashMap<String, Object>();
        props.put("mtom-enabled", Boolean.TRUE);
        fact.setProperties(props);
        DosignSignatureValidation service = (DosignSignatureValidation)fact.create();

        VerifyDto verifyDto = new VerifyDto();
        verifyDto.setEnvelopeArray(rtData);
        verifyDto.setExtractData(true);
        verifyDto.setProfileType(1);
        verifyDto.setVerificationScope(1);
        verifyDto.setVerificationType(1);

        VerifyReportDto response = service.verify(verifyDto);
        LoggerUtil.debug("RESPONSE: "+response);
        LoggerUtil.debug(""+response.getVerifyInfo());
        LoggerUtil.debug(""+response.getVerifyInfo());
        LoggerUtil.debug(""+response.getVerifyInfo().get(0));
        rtData = response.getVerifyInfo().get(0).getData();
        return rtData;
    }



    /**
     * Registra l'elenco di errori di validazione del nodo.
     * @param elencoErrori
     * @throws RemoteException
     */
    private void registraErrori(List<String> elencoMessaggi, Transazione t) throws RemoteException {

        try {
            List<NodoErrore> elencoErrori = new ArrayList<NodoErrore>(elencoMessaggi.size());
            for (String messaggio : elencoMessaggi) {
                elencoErrori.add(creaErrore(t, messaggio));
            }
            PersistenzaDatiNodoUtility.registraErroriNodo(elencoErrori);
        } catch (Exception e) {
            LoggerUtil.error("IMPOSSIBILE REGISTRARE ERRORI ", e);
        }
    }

    /**
     * Registra la RPT che viene inviata sulla tabella rpt, tramite l'invocazione all'EJB sulal core.
     * @param rpt
     * @param applicationId
     * @param transactionId
     * @param autenticazioneSoggetto
     * @param identificativoCanale
     * @param identificativoPSP
     * @param identificativoIntermediario
     * @param rptXml
     * @param idDominio
     * @param idIntermediarioPa
     * @param idStazionePa
     * @param iuv
     * @param esito
     * @throws RemoteException
     */
    //RDI-45 MULTIBENEFICIARIO Aggiunti 2 parametri in coda
    private void registraRPT(String applicationId, String transactionId, String autenticazioneSoggetto,
        String identificativoCanale, String identificativoPSP,
        String identificativoIntermediario, String rptXml, String idDominio,
        String idIntermediarioPa, String idStazionePa, String iuv, String esito, String codiceContestoPagamento, boolean multibeneficiario, String numeroAvviso) throws RemoteException {

        try {
            PersistenzaDatiNodoUtility.registraRPT(applicationId, transactionId, autenticazioneSoggetto, identificativoCanale, identificativoPSP, identificativoIntermediario, rptXml, idDominio, idIntermediarioPa, idStazionePa, iuv, esito, codiceContestoPagamento, false, multibeneficiario, numeroAvviso);
            PersistenzaDatiNodoUtility.registraTransazioneIuv(transactionId,iuv);
        } catch (Exception e) {
            LoggerUtil.error("IMPOSSIBILE REGISTRARE LA RICHIESTA DI PAGAMENTO TELEMATICA", e);
            throw new RemoteException("IMPOSSIBILE REGISTRARE LA RICHIESTA DI PAGAMENTO TELEMATICA", e);
        }

    }


    /**
     * Memorizza su DB l'idsession incluso nell'url restiruito dal nodo per il pagamento. L'idsession, che nulla ha a che fare con lo iuv o altri identificativi,
     * e' l'unico collegamento al pagamento quando il browser del pagatore ritorna su MDP.
     * @param idTransazione
     * @param idSession
     * @throws RemoteException
     */
    private void aggiornaIdSessionTransazione(String idTransazione,
        String idSession) throws RemoteException {
        try {
            PersistenzaDatiNodoUtility.aggiornaIdSessionTransazione(idTransazione, idSession);
        } catch (Exception e) {
            LoggerUtil.error("IMPOSSIBILE REGISTRARE L'ACCOPPIAMENTO ID TRANSAZIONE - ID SESSION DEL NODO: ", e);
            throw new RemoteException("IMPOSSIBILE REGISTRARE L'ACCOPPIAMENTO ID TRANSAZIONE - ID SESSION DEL NODO: ", e);
        }
    }

    /**
     * Aggiorna lo stato della RPT in base all'esito della RT ricevuta
     * @param richiestaTrovata
     * @param esito
     */
    private void aggiornaRPT(RPT richiestaTrovata, String esito) {

        richiestaTrovata.setIdStatiRpt("OK".equals(esito) ? StatiRPTEnum.RT_VERIFICATA.id() : StatiRPTEnum.RT_ERRATA.id());

        try {
            Payment p = PersistenzaDatiNodoUtility.reperisciPaymentInterface();
            p.aggiornaRPT(richiestaTrovata);
        } catch (Exception e) {
            LoggerUtil.error("IMPOSSIBILE REGISTRARE LA RICHIESTA DI PAGAMENTO TELEMATICA", e);
        }
    }


    public static String componiCausaleVersamento(String iuv, BigDecimal importo, String descrizioneCausaleVersamento) {
        StringBuilder composizioneCausale = new StringBuilder();
        if (iuv.length() == 25) {
            composizioneCausale.append("/RFS/");
        } else {
            composizioneCausale.append("/RFB/");
        }
        composizioneCausale.append(iuv);
        composizioneCausale.append("/");
        composizioneCausale.append(importo);

        if (StringUtils.isNotEmpty(descrizioneCausaleVersamento)) {
            composizioneCausale.append("/TXT/").append(descrizioneCausaleVersamento);
        }

        return composizioneCausale.toString();
    }



    private List<String> controlliFormali(Transazione t, Gatewaydetail gd, ApplicationDetail ad, Map<String, String> mappaAppCustomFields, RPTData rptInElaborazione) throws RemoteException {
        List<String> elencoErrori = new ErroriList<String>();

        if (t == null) {
            elencoErrori.add("TRANSAZIONE NULLA");
        }
        if (gd == null) {
            elencoErrori.add("GATEWAY DETAIL NULLO");
        }
        if (ad == null) {
            elencoErrori.add("APPLICATION DETAIL NULLO");
        }
        if (rptInElaborazione.getAutenticazioneSoggetto() == null) {
            elencoErrori.add("PARAMETRO  AUTENTICAZIONE SOGGETTO OBBLIGATORIO");
        } else if (
                        !"CNS".equals(rptInElaborazione.getAutenticazioneSoggetto()) &&
                        !"USR".equals(rptInElaborazione.getAutenticazioneSoggetto()) &&
                        !"OTH".equals(rptInElaborazione.getAutenticazioneSoggetto()) &&
                        !"N/A".equals(rptInElaborazione.getAutenticazioneSoggetto())
                        ){
            elencoErrori.add("PARAMETRO  AUTENTICAZIONE SOGGETTO DEVE AVERE UNO DEI SEGUENTI VALORI: CNS, USR, OTH, N/A ");
        }

        if (rptInElaborazione.getDatiVersamento() == null) {
            elencoErrori.add("LA SEZIONE DATI VERSAMENTO NON PUO' ESSERE VUOTA");
        }

        if (CollectionUtils.isEmpty(rptInElaborazione.getDatiVersamento().getDatiSingoloVersamento())) {
            elencoErrori.add("LA SEZIONE DATI VERSAMENTO - DATI SINGOLO PAGAMENTO NON PUO' ESSERE VUOTA");
        }

        if (rptInElaborazione.getSoggettoPagatore() == null) {
            elencoErrori.add("LA SEZIONE SOGGETTO PAGATORE NON PUO' ESSERE VUOTA");
        }

        elencoErrori.add(campoErrato(rptInElaborazione.getSoggettoPagatore().getAnagraficaPagatore(), true, 70,
                        "PARAMETRO  ANAGRAFICA PAGATORE"));

        elencoErrori.add(campoErrato(rptInElaborazione.getSoggettoPagatore().getTipoIdentificativoUnivocoPagatore(), true, 1,
                        "PARAMETRO  TIPO IDENTIFICATIVO UNIVOCO PAGATORE"));

        elencoErrori.add(campoErrato(rptInElaborazione.getSoggettoPagatore().getIdentificativoUnivocoPagatore(), true, 35,
                        "PARAMETRO  CODICE IDENTIFICATIVO UNIVOCO PAGATORE"));

        elencoErrori.add(campoErrato(rptInElaborazione.getSoggettoPagatore().getIndirizzoPagatore(), false, 70,
                        "PARAMETRO  INDIRIZZO PAGATORE"));

        elencoErrori.add(campoErrato(rptInElaborazione.getSoggettoPagatore().getCivicoPagatore(), false, 16,
                        "PARAMETRO  CIVICO PAGATORE"));

        elencoErrori.add(campoErrato(rptInElaborazione.getSoggettoPagatore().getCapPagatore(), false, 16,
                        "PARAMETRO  CAP PAGATORE"));

        elencoErrori.add(campoErrato(rptInElaborazione.getSoggettoPagatore().getLocalitaPagatore(), false, 35,
                        "PARAMETRO  LOCALITA' PAGATORE"));

        elencoErrori.add(campoErrato(rptInElaborazione.getSoggettoPagatore().getProvinciaPagatore(), false, 35,
                        "PARAMETRO  PROVINCIA PAGATORE"));

        elencoErrori.add(campoErrato(rptInElaborazione.getSoggettoPagatore().geteMailPagatore(), false, 256,
                        "PARAMETRO  E-MAIL PAGATORE"));

        if (rptInElaborazione.getSoggettoVersante() != null) {
            elencoErrori.add(campoErrato(rptInElaborazione.getSoggettoVersante().getAnagraficaVersante(), false, 70,
                            "PARAMETRO  ANAGRAFICA VERSANTE"));

            elencoErrori.add(campoErrato(rptInElaborazione.getSoggettoVersante().getTipoIdentificativoUnivocoVersante(), false, 1,
                            "PARAMETRO  TIPO IDENTIFICATIVO UNIVOCO VERSANTE"));

            elencoErrori.add(campoErrato(rptInElaborazione.getSoggettoVersante().getIdentificativoUnivocoVersante(), false, 35, //TODO
                            "PARAMETRO  CODICE IDENTIFICATIVO UNIVOCO VERSANTE"));

            elencoErrori.add(campoErrato(rptInElaborazione.getSoggettoVersante().getIndirizzoVersante(), false, 70,
                            "PARAMETRO  INDIRIZZO VERSANTE"));

            elencoErrori.add(campoErrato(rptInElaborazione.getSoggettoVersante().getCivicoVersante(), false, 16,
                            "PARAMETRO  CIVICO VERSANTE"));

            elencoErrori.add(campoErrato(rptInElaborazione.getSoggettoVersante().getCapVersante(), false, 16,
                            "PARAMETRO  CAP VERSANTE"));

            elencoErrori.add(campoErrato(rptInElaborazione.getSoggettoVersante().getLocalitaVersante(), false, 35,
                            "PARAMETRO  LOCALITA' VERSANTE"));

            elencoErrori.add(campoErrato(rptInElaborazione.getSoggettoVersante().getProvinciaVersante(), false, 35,
                            "PARAMETRO  PROVINCIA VERSANTE"));

            elencoErrori.add(campoErrato(rptInElaborazione.getSoggettoVersante().geteMailVersante(), false, 256,
                            "PARAMETRO  E-MAIL VERSANTE"));

        }
        elencoErrori.add(campoErrato(mappaAppCustomFields.get(CostantiNodoSpc.APP_PARAM_CODICE_IDENTIFICATIVO_UNIVOCO_BENEFICIARIO), true, 35,
                        "PARAMETRO APPLICAZIONE IDENTIFICATIVO UNIVOCO BENEFICIARIO "));

        elencoErrori.add(campoErrato(rptInElaborazione.getDatiVersamento().getBicAddebito(), false, 11,
                        "PARAMETRO  BIC ADDEBITO"));



        elencoErrori.add(campoErrato(rptInElaborazione.getDatiVersamento().getFirmaRicevuta(), false, 1,
                        "PARAMETRO  FIRMA RICEVUTA"));

        if (StringUtils.isNotEmpty(rptInElaborazione.getDatiVersamento().getFirmaRicevuta()) &&
                        !"0".equals(rptInElaborazione.getDatiVersamento().getFirmaRicevuta()) &&
                        !"1".equals(rptInElaborazione.getDatiVersamento().getFirmaRicevuta()) &&
                        !"3".equals(rptInElaborazione.getDatiVersamento().getFirmaRicevuta()) &&
                        !"4".equals(rptInElaborazione.getDatiVersamento().getFirmaRicevuta())
                        ){
            elencoErrori.add("PARAMETRO FIRMA RICEVUTA DEVE AVERE UNO DEI SEGUENTI VALORI: 0, 1, 3, 4");
        }

        elencoErrori.add(campoErrato(rptInElaborazione.getIdStazioneRichiedente(), false, 35,
                        "PARAMETRO STAZIONE RICHIEDENTE"));


        if (mappaAppCustomFields.get(CostantiNodoSpc.APP_PARAM_DATI_SPECIFICI_RISCOSSIONE) == null) {
            elencoErrori.add("PARAMETRO APPLICAZIONE DATI SPECIFICI RISCOSSIONE OBBLIGATORIO");
        } else if (!mappaAppCustomFields.get(CostantiNodoSpc.APP_PARAM_DATI_SPECIFICI_RISCOSSIONE).matches("[0129]{1}/\\S{3,138}")) {
            elencoErrori.add("PARAMETRO APPLICAZIONE DATI SPECIFICI RISCOSSIONE IN FORMATO NON CORRETTO; FORMATO CORRETTO: [0129]{1}/\\S{3,138}");
        }

        // Jira: #CSI_PAG-542 MDPNEW-108 invece di usare BigDecimal.ZERO bisogna usare quanto sotto per i confronti.
        BigDecimal sommaVersamenti = BigDecimal.valueOf ( 0l, rptInElaborazione.getDatiVersamento().getImportoTotaleDaVersare().scale () );
        //		String controlloCredenzialiSingoloPag = "";
        //		String controlloCausale;
        //
        for (DatiSingoloVersamentoRPT singoloVer : rptInElaborazione.getDatiVersamento().getDatiSingoloVersamento()) {
            //			if (StringUtils.isNotEmpty(controlloCredenzialiSingoloPag) && !controlloCredenzialiSingoloPag.equalsIgnoreCase(singoloVer.getCausaleVersamento())) {
            //				elencoErrori.add(campoErrato(singoloVer.getCausaleVersamento(), false , 140,
            //						"PARAMETRO CAUSALE VERSAMENTO DEI SINGOLI VERSAMENTI DEVE ESSERE UGUALE"));
            //			}
            elencoErrori.add(campoErrato(singoloVer.getCausaleVersamento(), true, 140,
                            "PARAMETRO  CAUSALE VERSAMENTO"));

            if (StringUtils.isNotEmpty(singoloVer.getCausaleVersamento()) && singoloVer.getCausaleVersamento().indexOf(rptInElaborazione.getDatiVersamento().getIdentificativoUnivocoVersamento()) == -1) {
                elencoErrori.add("VERIFICARE CAUSALE VERSAMENTO: " +singoloVer.getCausaleVersamento());
            }

            elencoErrori.add(campoErrato(singoloVer.getCredenzialiPagatore(), false, 35,
                            "PARAMETRO  CREDENZIALI PAGATORE"));

            sommaVersamenti = sommaVersamenti.add(singoloVer.getImportoSingoloVersamento());
        }

        if (sommaVersamenti.compareTo(rptInElaborazione.getDatiVersamento().getImportoTotaleDaVersare()) != 0)
            elencoErrori.add("Importo totale della RPT = " + rptInElaborazione.getDatiVersamento().getImportoTotaleDaVersare() + " Diverso dalla somma dei suoi versamenti: " + sommaVersamenti);

        return elencoErrori;
    }

    /**
     * Controlla che il campo rispetti i vincoli di obbligatorieta' e lunghezza massima specificati e compone il messaggio di errore specifico
     * Un campo puo' essere obbligatorio, in quel caso avra' anche una lunghezza massima
     * Un campo puo' essere facoltativo, ma in caso sia specificato la lunghezza massima deve essere rispettata
     * @param campo
     * @param obbligatorio
     * @param lunghezzaCampo
     * @param nomeCampoMessaggio
     * @return null se la validazione e' stata superata, il messaggio specifico se sono stati trovati errori
     */
    private String campoErrato (String campo, boolean obbligatorio, int lunghezzaCampo, String nomeCampoMessaggio) {

        if (obbligatorio && StringUtils.isEmpty(campo))
            return nomeCampoMessaggio + " OBBLIGATORIO";
        if (StringUtils.isNotEmpty(campo) && lunghezzaCampo > 0 && campo.length() > lunghezzaCampo)
            return " LUNGHEZZA CAMPO " + nomeCampoMessaggio + " SUPERA IL LIMITE DI " + lunghezzaCampo;

        return null;
    }

    private String spedisciRTAlFruitore(CtRicevutaTelematica rt,
        RPT richiestaTrovata, byte[] rtData, String tipoFirma, Payment p, Map<String, String> mappaAcf) {
        try {
            Map<String,Object> props = new HashMap<String, Object>();
            props.put("mtom-enabled", Boolean.TRUE);

            JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
            factory.setProperties(props);
            factory.getInInterceptors().add(new LoggingInInterceptor());
            factory.getOutInterceptors().add(new LoggingOutInterceptor());
            factory.setServiceClass(Serviziorissrvspc.class);
            factory.setAddress(mappaAcf.get("endpointServiziNodo"));
            Serviziorissrvspc iRiceviRT = (Serviziorissrvspc)factory.create();

            ParametriRiceviRT ricevuta = new ParametriRiceviRT();
            ricevuta.setApplicationId(richiestaTrovata.getApplicationId());
            ricevuta.setCodEsitoPagamento(rt.getDatiPagamento().getCodiceEsitoPagamento());
            ricevuta.setDataOraMsgRicevuta(rt.getDataOraMessaggioRicevuta());
            ricevuta.setDescEsitoPagamento(CostantiNodoSpc.mappaCodiciEsitoPagamento.get(rt.getDatiPagamento().getCodiceEsitoPagamento()));
            ricevuta.setIdMsgRicevuta(rt.getIdentificativoMessaggioRicevuta());
            ricevuta.setIdMsgRichiesta(richiestaTrovata.getIdMsgRichiesta());
            ricevuta.setIuv(rt.getDatiPagamento().getIdentificativoUnivocoVersamento());
            String timestamp = null;
            SimpleDateFormat sdf = new SimpleDateFormat("ddmmyyyy-hh:mm:ss:ms");
            timestamp = sdf.format(new Date());
            ricevuta.setMac(generaMac(mappaAcf.get(CostantiNodoSpc.APP_PARAM_PASSPHRASE_FRUITORE), richiestaTrovata.getIuv(), richiestaTrovata.getApplicationId(), rt.getIdentificativoMessaggioRicevuta(), timestamp));
            ByteArrayDataSource rawData = new ByteArrayDataSource(rtData, "application/octet-stream");
            ricevuta.setRtData(new DataHandler(rawData));
            ricevuta.setTimestamp(timestamp);
            ricevuta.setTipoFirma(tipoFirma);
            ricevuta.setTransactionId(richiestaTrovata.getTransactionId());
            EsitoRiceviRT esitoRT = iRiceviRT.riceviRT(ricevuta);

            LoggerUtil.dumpObject("COSA MI HA RISPOISTO?", esitoRT);
            return esitoRT.getEsito();
        }catch (Throwable t) {
            LoggerUtil.error("ERRORE DURANTE L'INVIO DELLA RT AL FRUITORE: ", t);
            return "KO";
        }
    }


    private String generaMac(String passphrase, String iuv, String applicationId, String idMsgRicevuta, String timestamp) {

        String sToDigest =  passphrase + "%%%%" + applicationId + iuv + idMsgRicevuta + timestamp + "%%%%" + passphrase;
        byte[] bMac = DigestUtils.sha256(sToDigest.getBytes());
        String mac = Base64.encodeBase64String (bMac);
        mac = mac.substring (0,35);
        return mac;
    }


    /**
     * Crea un oggetto di tipo Errore
     * @param t
     * @param messaggio
     * @return
     */
    private NodoErrore creaErrore(Transazione t, String messaggio) {
        NodoErrore errore = new NodoErrore();
        errore.setApplicationId(t.getApplicationId());
        errore.setTransactionId(t.getTransactionId());
        errore.setDescrizione(messaggio);

        return errore;
    }

    /**
     * Crea l'unmarshaller per la RT
     * @return
     * @throws JAXBException
     * @throws SAXException
     */
    private Unmarshaller unMarshallerRT() throws JAXBException, SAXException {
        JAXBContext jContext = JAXBContext.newInstance(CtRicevutaTelematica.class);
        Unmarshaller unmarshaller = jContext.createUnmarshaller();
        SchemaFactory sf = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        Source so = new StreamSource(this.getClass().getResourceAsStream("/META-INF/PagInf_RPT_RT_6_2_0.xsd"));
        Schema s = sf.newSchema(so);
        unmarshaller.setSchema(s);
        return unmarshaller;
    }

    /**
     * Crea una BusinessException con i dettagli valorizzati con l'elenco dei messaggi passato
     * @param elencoDettagli
     * @return
     */
    private BusinessException creaBusinessExceptionConDettagli (String... elencoDettagli) {
        BusinessException e = new BusinessException();
        e.setDettagli(Arrays.asList(elencoDettagli));
        return e;
    }



    @Override
    public Transazione initTransazione(Transazione t) throws RemoteException {
        return t;
    }

    public Transazione startTransazioneDifferita(Transazione t, Gatewaydetail gd, Applicationcustomfields[] acf, ApplicationDetail ad) throws RemoteException {
        return null;
    }

    @Override
    public Transazione getCommissioni(Transazione t, ApplicationDetail ad) throws RemoteException {

        return t;
    }
    //RDI-45-MULTIBENEFICIARIO INIZIO
    public String getNumeroAvviso(String iuv, String applicationId, String gatewayId)  throws RemoteException {
        try {
            return PersistenzaDatiNodoUtility.getNumeroAvviso(iuv, applicationId, gatewayId);
        } catch (Exception e) {
            LoggerUtil.error("IMPOSSIBILE OTTENERE IL NUMERO AVVISO", e);
            throw new RemoteException("IMPOSSIBILE OTTENERE IL NUMERO AVVISO", e);
        }
    }
    public int getCountIdCarrello(String identificativoDominio, String numeroAvviso)   throws RemoteException  {
        try {
            return PersistenzaDatiNodoUtility.getCountIdCarrello(identificativoDominio, numeroAvviso);
        } catch (Exception e) {
            LoggerUtil.error("IMPOSSIBILE OTTENERE IL COUNT ID CARRELLO", e);
            throw new RemoteException("IMPOSSIBILE OTTENERE IL COUNT ID CARRELLO", e);
        }
    }
    public int findNexValueIdDominio()   throws RemoteException  {
        try {
            return PersistenzaDatiNodoUtility.findNexValueIdDominio( );
        } catch (Exception e) {
            LoggerUtil.error("IMPOSSIBILE OTTENERE IL NEXT VALUE ID DOMINIO", e);
            throw new RemoteException("IMPOSSIBILE OTTENERE IL NEXT VALUE ID DOMINIO", e);
        }
    }
    public boolean getAllGestioneCodiceContestoPagamentoFlag()   throws RemoteException  {
        try {
            return PersistenzaDatiNodoUtility.getAllGestioneCodiceContestoPagamentoFlag();
        } catch (Exception e) {
            LoggerUtil.error("IMPOSSIBILE OTTENERE IL FLAG ALL GESTIONE CODICE PAGAMENTO", e);
            throw new RemoteException("IMPOSSIBILE OTTENERE IL COUNT ID CARRELLO", e);
        }
    }
    //RDI-45-MULTIBENEFICIARIO FINE

    //calcolo ccp modello 1
    public StatiPagamento getStatiPagamento ( String iuv ) throws RemoteException {
        try {
            return PersistenzaDatiNodoUtility.getStatiPagamento ( iuv );
        } catch ( Exception e ) {
            LoggerUtil.error ( "IMPOSSIBILE OTTENERE I FLAG SUGLI STATI DEL PAGAMENTO", e );
            throw new RemoteException ( "IMPOSSIBILE OTTENERE I FLAG SUGLI STATI DEL PAGAMENTO", e );
        }
    }

    //fine calcolo ccp modello 1


}
