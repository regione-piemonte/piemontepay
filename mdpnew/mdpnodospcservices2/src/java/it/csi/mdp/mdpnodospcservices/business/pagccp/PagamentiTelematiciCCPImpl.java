/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpnodospcservices.business.pagccp;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.ejb.CreateException;
import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.util.CollectionUtils;
import org.xml.sax.SAXException;

import it.csi.mdp.adapters.business.BusinessException;
import it.csi.mdp.adapters.business.CoreUtilities;
import it.csi.mdp.adapters.business.PersistenzaDatiNodoUtility;
import it.csi.mdp.clientmod3.ChiediDatiPagamentoException;
import it.csi.mdp.clientmod3.EsitoChiediDatiPagamento;
import it.csi.mdp.clientmod3.EsitoVerificaDatiPagamento;
import it.csi.mdp.clientmod3.ParametriChiediDatiPagamento;
import it.csi.mdp.clientmod3.ParametriVerificaDatiPagamento;
import it.csi.mdp.clientmod3.Serviziorissrvspc;
import it.csi.mdp.clientmod3.TransazioneExtraAttribute;
import it.csi.mdp.clientmod3.UnrecoverableException;
import it.csi.mdp.clientmod3.VerificaDatiPagamentoException;
import it.csi.mdp.core.business.dto.ElementoMultiversamento;
import it.csi.mdp.core.business.dto.InformativePSP;
import it.csi.mdp.core.business.dto.IuvOtticoFruitore;
import it.csi.mdp.core.business.dto.NodoErrore;
import it.csi.mdp.core.business.dto.RPT;
import it.csi.mdp.core.business.dto.RT;
import it.csi.mdp.core.business.dto.StatiRPTEnum;
import it.csi.mdp.core.business.dto.Transazione;
import it.csi.mdp.core.business.dto.multicarrello.DatiAccertamentoRPT;
import it.csi.mdp.core.business.dto.multicarrello.DatiMarcaBolloDigitale;
import it.csi.mdp.core.business.exceptions.DaoException;
import it.csi.mdp.core.business.paymentmanager.Payment;
import it.csi.mdp.core.business.paymentmanager.PaymentHome;
import it.csi.mdp.core.interfacecxf.MissingParameterException;
import it.csi.mdp.generatedvo.multiversamento.DatiSingoloVersamentoRPT;
import it.csi.mdp.generatedvo.multiversamento.ElencoDatiVersamento;
import it.csi.mdp.generatedvo.pagamenti.CtRichiestaPagamentoTelematico;
import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.NodoInviaRPT;
import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.NodoInviaRPTRisposta;
import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.PagamentiTelematiciRPT;
import it.csi.mdp.mdpnodospcservices.business.pagccp.PagamentiTelematiciCCPImpl.InviaRPTThread;
import it.csi.mdp.mdpnodospcservices.integration.pagccp.interfacews.CtEnteBeneficiario;
import it.csi.mdp.mdpnodospcservices.integration.pagccp.interfacews.CtIdentificativoUnivocoPersonaG;
import it.csi.mdp.mdpnodospcservices.integration.pagccp.interfacews.EsitoAttivaRPT;
import it.csi.mdp.mdpnodospcservices.integration.pagccp.interfacews.EsitoVerificaRPT;
import it.csi.mdp.mdpnodospcservices.integration.pagccp.interfacews.FaultBean;
import it.csi.mdp.mdpnodospcservices.integration.pagccp.interfacews.IntestazionePPT;
import it.csi.mdp.mdpnodospcservices.integration.pagccp.interfacews.PaaAttivaRPT;
import it.csi.mdp.mdpnodospcservices.integration.pagccp.interfacews.PaaAttivaRPTRisposta;
import it.csi.mdp.mdpnodospcservices.integration.pagccp.interfacews.PaaTipoDatiPagamentoPA;
import it.csi.mdp.mdpnodospcservices.integration.pagccp.interfacews.PaaVerificaRPT;
import it.csi.mdp.mdpnodospcservices.integration.pagccp.interfacews.PaaVerificaRPTRisposta;
import it.csi.mdp.mdpnodospcservices.integration.pagccp.interfacews.PagamentiTelematiciCCP;
import it.csi.mdp.mdpnodospcservices.integration.pagccp.interfacews.StTipoIdentificativoUnivocoPersG;
import it.csi.mdp.mdpnodospcservices.util.Constants;
import it.csi.mdp.mdpnodospcservices.util.FaultBeanEnum;
import it.csi.mdp.mdpnodospcservices.util.LoggerUtil;
import it.csi.mdp.mdpnodospcservices.util.SSLUtils;
import it.csi.mdp.mdpnodospcservices.util.Utils;
import it.csi.mdp.utility.CostantiNodoSpc;
import it.csi.mdp.utility.DatiInterscambioNodoUtility;
import it.csi.util.performance.StopWatch;

@WebService(serviceName = "PagamentiTelematiciCCP", portName = "PPTPort", endpointInterface = "it.csi.mdp.mdpnodospcservices.integration.pagccp.interfacews.PagamentiTelematiciCCP")
public class PagamentiTelematiciCCPImpl implements PagamentiTelematiciCCP {

	@Autowired
    private Environment environment;
	
    /**
     * Verifica del pagamento per il modello 3, primo passo.
     */
    public PaaVerificaRPTRisposta paaVerificaRPT(PaaVerificaRPT bodyrichiesta,
        IntestazionePPT header) {

        StopWatch watcher = new StopWatch ( Constants.APP_CODE_PERF );
        watcher.start ();

        LoggerUtil.begin();
        LoggerUtil.dumpObject("RICHIESTA ", bodyrichiesta);
        LoggerUtil.dumpObject("HEADER ", header);
        PaaVerificaRPTRisposta risposta = new PaaVerificaRPTRisposta();
        EsitoVerificaRPT esito = new EsitoVerificaRPT();
        esito.setEsito("KO");
        risposta.setPaaVerificaRPTRisposta(esito);
        Payment p = null;
        IuvOtticoFruitore datiIuvFruitore = null;
        String parametriSpecificiGiornale = "";

        try {
            p = reperisciPaymentInterface();

            datiIuvFruitore = p.recuperaDatiFruitorePerIuvOttico(header.getIdentificativoUnivocoVersamento(), null);

            if (datiIuvFruitore == null) {
                parametriSpecificiGiornale = "Pagamento " + header.getIdentificativoUnivocoVersamento() + " sconosciuto";
                esito = componiEsitoVerificaFallimento(esito, FaultBeanEnum.PAA_PAGAMENTO_SCONOSCIUTO.name(), FaultBeanEnum.PAA_PAGAMENTO_SCONOSCIUTO.getFaultString(), "Pagamento " + header.getIdentificativoUnivocoVersamento() + " sconosciuto", header.getIdentificativoDominio());
                return risposta;
            }

            LoggerUtil.dumpObject("DATI IUV FRUITORE: ", datiIuvFruitore);

            Map<String, String> mappaAppCustomFields = CoreUtilities.costruisciMappaApplicationCustomFields(datiIuvFruitore.getElencoAcf());

            if ( !header.getIdentificativoDominio ()
                .equalsIgnoreCase ( mappaAppCustomFields.get ( CostantiNodoSpc.APP_PARAM_IDENTIFICATIVO_DOMINIO ) ) ) {
                parametriSpecificiGiornale = FaultBeanEnum.PAA_ID_DOMINIO_ERRATO.getDescription () + "Valore MDP: "
                    + mappaAppCustomFields.get ( CostantiNodoSpc.APP_PARAM_IDENTIFICATIVO_DOMINIO ) + "Valore parametro: " + header.getIdentificativoDominio ();
                esito = componiEsitoVerificaFallimento ( esito, FaultBeanEnum.PAA_ID_DOMINIO_ERRATO.name (),
                    FaultBeanEnum.PAA_ID_DOMINIO_ERRATO.getFaultString (), FaultBeanEnum.PAA_ID_DOMINIO_ERRATO.getDescription (),
                    header.getIdentificativoDominio () );
                return risposta;
            }
            
            EsitoVerificaDatiPagamento datiPag = verificaPagamentoAttesoFruitore(bodyrichiesta, header, datiIuvFruitore, mappaAppCustomFields);

            if(datiPag != null) {
                LoggerUtil.info ( "Esito DATI PAG :"  + datiPag.getEsito ());
                LoggerUtil.info ( "CodErrore DATI PAG :"  + datiPag.getCodiceErrore ());
                LoggerUtil.info ( "Messaggio errore DATI PAG :"  + datiPag.getMessaggioErrore ());
                LoggerUtil.info ( "Timestamp DATI PAG :"  + datiPag.getTimestamp ());

            if (datiPag.getEsito().equals("OK")){
                PaaTipoDatiPagamentoPA tipoDatiPagamento = new PaaTipoDatiPagamentoPA();
					tipoDatiPagamento.setBicAccredito(
							StringUtils.isNotEmpty(datiPag.getBicAccredito()) ? datiPag.getBicAccredito()
									: mappaAppCustomFields.get(CostantiNodoSpc.APP_PARAM_BIC_ACCREDITO));
                tipoDatiPagamento.setCausaleVersamento(datiPag.getCausaleVersamento());
                tipoDatiPagamento.setCredenzialiPagatore(datiPag.getCredenzialiPagatore());
                
                //Fix per campo con minlen 1 che se passato a empty causa un errore
					if (StringUtils.isEmpty(tipoDatiPagamento.getCredenzialiPagatore()))
						tipoDatiPagamento.setCredenzialiPagatore(null);
                
					tipoDatiPagamento.setIbanAccredito(
							StringUtils.isNotEmpty(datiPag.getIbanAccredito()) ? datiPag.getIbanAccredito()
									: mappaAppCustomFields.get(CostantiNodoSpc.APP_PARAM_IBAN_ACCREDITO));
                tipoDatiPagamento.setImportoSingoloVersamento(datiPag.getImportoDovuto());
                CtEnteBeneficiario ente = new CtEnteBeneficiario();
					it.csi.mdp.generatedvo.pagamenti.CtEnteBeneficiario enteSorgente = DatiInterscambioNodoUtility
							.creaEnteBeneficiario(null, mappaAppCustomFields);
                BeanUtils.copyProperties(enteSorgente, ente, new String[] {"identificativoUnivocoBeneficiario"});
                CtIdentificativoUnivocoPersonaG identificativoUnivocoPersonaG = new CtIdentificativoUnivocoPersonaG();
					identificativoUnivocoPersonaG.setCodiceIdentificativoUnivoco(
							enteSorgente.getIdentificativoUnivocoBeneficiario().getCodiceIdentificativoUnivoco());
					identificativoUnivocoPersonaG
							.setTipoIdentificativoUnivoco(StTipoIdentificativoUnivocoPersG.valueOf(enteSorgente
									.getIdentificativoUnivocoBeneficiario().getTipoIdentificativoUnivoco().value()));
                ente.setIdentificativoUnivocoBeneficiario(identificativoUnivocoPersonaG);
                tipoDatiPagamento.setEnteBeneficiario(ente);
                esito.setDatiPagamentoPA(tipoDatiPagamento);
                esito.setEsito("OK");
            } else{
					parametriSpecificiGiornale = "Il Fruitore ha rifiutato il pagamento: "
							+ datiPag.getMessaggioErrore();
					esito = componiEsitoVerificaFallimento(esito, datiPag.getCodiceErrore(),
							datiPag.getMessaggioErrore(), datiPag.getMessaggioErrore(),
							header.getIdentificativoDominio());
				}
			}else {
				risposta.setPaaVerificaRPTRisposta(componiEsitoVerificaFallimento(esito, FaultBeanEnum.PAA_SYSTEM_ERROR.name(), FaultBeanEnum.PAA_SYSTEM_ERROR.getFaultString(), parametriSpecificiGiornale, header.getIdentificativoDominio()));
				StringBuilder errorMessage = new StringBuilder("La chiamata al servizio remoto per la verifica del pagamento ha restituito un valore nullo relativamente ai dati del seguente pagamento:");
				errorMessage.append(System.getProperty("line.separator"));
				errorMessage.append("IUV: ").append(header.getIdentificativoUnivocoVersamento());
				errorMessage.append(System.getProperty("line.separator"));
				errorMessage.append("MDP APPLICAZIONE: ").append(datiIuvFruitore.getApplicationDetail().getApplicationid());
				errorMessage.append(System.getProperty("line.separator"));
				errorMessage.append("END POINT: ").append(mappaAppCustomFields.get(CostantiNodoSpc.APP_PARAM_ENDPONTI_SERVIZI_FRUITORE));
				LoggerUtil.error(errorMessage.toString());
				parametriSpecificiGiornale = errorMessage.toString();
				Utils.inviaEmailErrore(header.getIdentificativoUnivocoVersamento(), header.getCodiceContestoPagamento(), p, null, null, "paaVerificaRPT", errorMessage.toString());
            }

        } catch (Exception e) {
            LoggerUtil.error("ERRORE GENERICO: ", e);
            LoggerUtil.info ( "ERRORE GENERICO: ");
            parametriSpecificiGiornale = Utils.concatenaMessaggiCausa(e).toString();
            risposta.setPaaVerificaRPTRisposta(componiEsitoVerificaFallimento(esito, FaultBeanEnum.PAA_SYSTEM_ERROR.name(), FaultBeanEnum.PAA_SYSTEM_ERROR.getFaultString(), FaultBeanEnum.PAA_SYSTEM_ERROR.getFaultString(), header.getIdentificativoDominio()));
            Utils.inviaEmailErrore(header.getIdentificativoUnivocoVersamento(), header.getCodiceContestoPagamento(), p, null, e, "paaVerificaRPT");
        } finally {
            if (datiIuvFruitore != null)
                registraEventoGiornale(datiIuvFruitore.getApplicationDetail().getApplicationid(),"N/A", datiIuvFruitore.getApplicationDetail().getGatewayid(), esito.getEsito(), "paaVerificaRPT", header.getIdentificativoUnivocoVersamento(), "PO",
                    header.getIdentificativoStazioneIntermediarioPA(), header.getIdentificativoDominio(), bodyrichiesta.getIdentificativoPSP(),
                    "N/A", parametriSpecificiGiornale, header.getCodiceContestoPagamento());
            else
                registraEventoGiornale("N/A","N/A", "N/A", esito.getEsito(), "paaVerificaRPT", header.getIdentificativoUnivocoVersamento(), "PO",
                    header.getIdentificativoStazioneIntermediarioPA(), header.getIdentificativoDominio(), bodyrichiesta.getIdentificativoPSP(),
                    "N/A", parametriSpecificiGiornale, header.getCodiceContestoPagamento());
        }
        LoggerUtil.dumpObject("COSA RISPONDIAMO NOI? ", risposta);
        LoggerUtil.end();

        watcher.stop ();
        watcher.dumpElapsed ( "PagamentiTelematiciCCPImpl", "paaVerificaRPT()", "[PagamentiTelematiciCCPImpl]::[paaVerificaRPT]", "(valore input omesso)" );

        return risposta;
    }


    /**
     * Attivazione della RPT per il modello 3, tendenzialmente chiamato dopo paaVerificaRPT, ma non e' obbligatoria questa sequenza.
     * Si effettua quanto segue:
     * 1) si verifica che lo IUV in input afferisca ad un pagamento realmente atteso e si reperiscono i dati relativi all'applicazione
     * fruitrice e al PSP presso il quale il contribuente vuole effettuare il pagamento
     * 2) si verifica l'esistenza di una RPT precedentemente costruita per capire se il pagamento e' ancora da effettuare oopure debba essere rifiutato;
     * nel caso in cui sia presente e sia in uno stato che permette il paggamento viene riutilizzata, in modo da evitare dove possibile la richiesta dei 
     * dati all'applicazione fruitrice.
     * 3) Si aggiorna o inserisce un record nella tabella RPT (a seconda che sia una rpt riutilizzata o siano stati richiesti i dati al fruitore) valorizzando
     * il flag da_inviare a true in modo da effettuare l'invio differito della RPT. L'invio della RPT DEVE essere successivo alla risposta di questo servizio.
     */
    public PaaAttivaRPTRisposta paaAttivaRPT(PaaAttivaRPT bodyrichiesta,IntestazionePPT header) {

        StopWatch watcher = new StopWatch ( Constants.APP_CODE_PERF );
        watcher.start ();

        LoggerUtil.begin();
        LoggerUtil.dumpObject("RICHIESTA ", bodyrichiesta);
        LoggerUtil.dumpObject("HEADER ", header);
        PaaAttivaRPTRisposta risposta = new PaaAttivaRPTRisposta();
        EsitoAttivaRPT esito = new EsitoAttivaRPT();
        esito.setEsito("KO");
        risposta.setPaaAttivaRPTRisposta(esito);
        Payment p = null;
        IuvOtticoFruitore datiIuvFruitore = null;
        Transazione t = null;
        String parametriSpecificiGiornale = "";
        InformativePSP infoPSP = null;
        boolean nuovaTransazione = false;
        RPT rptTrovata = null;

        try {
            LoggerUtil.info ( "log inizio recupero datiIuvFruitore" );
            System.out.println ( "sys inizio recupero datiIuvFruitore" );
            p = reperisciPaymentInterface();

            /**
             * Si recuperano i dati dell'applicazione che ha generato lo iuv ottico in ingresso ed i dati del psp per comporre la RPT: la RPT potrebbe essere
             * gia' stata inserita nel DB a seguito di precedenti tentativi di pagamento lasciati appesi. Se nulla è cambiato si puo' riutilizzare
             */
            datiIuvFruitore = p.recuperaDatiFruitorePerIuvOttico(header.getIdentificativoUnivocoVersamento(), null);

            LoggerUtil.info("datiIuvFruitore recuperati");
            if (datiIuvFruitore == null) {
                parametriSpecificiGiornale = "Pagamento " + header.getIdentificativoUnivocoVersamento() + " sconosciuto";
                esito = componiEsitoAttivaFallimento(esito, FaultBeanEnum.PAA_PAGAMENTO_SCONOSCIUTO.name(), FaultBeanEnum.PAA_PAGAMENTO_SCONOSCIUTO.getFaultString(), parametriSpecificiGiornale, header.getIdentificativoDominio());
                return risposta;
            }

            //infoPSP = p.recuperaInformativaPerIdentificativoPSP(bodyrichiesta.getIdentificativoPSP()); 

            // Impostazione dei dati del PSP acquisiti dalla request

            infoPSP = new InformativePSP();

            // ID PSP
            infoPSP.setIdentificativoPSP(bodyrichiesta.getIdentificativoPSP());

            // ID INTERMEDIARIO PSP
            if (StringUtils.isNotEmpty(bodyrichiesta.getIdentificativoIntermediarioPSP()))
                infoPSP.setIdentificativoIntermediario(bodyrichiesta.getIdentificativoIntermediarioPSP());

            // ID IDENTIFICATIVO CANALE
            if (StringUtils.isNotEmpty(bodyrichiesta.getIdentificativoCanalePSP()))
                infoPSP.setIdentificativoCanale(bodyrichiesta.getIdentificativoCanalePSP());

            LoggerUtil.dumpObject("INFORMATIVA PSP ", infoPSP);

            /**
             * Reperimento di una eventuale RPT gia' inserita per verificarne lo stato e capire se il pagamento e' ancora da effettuare
             * o e' rda respingere. Inoltre se il pagamento e' effettuabile e la rpt esiste gia' con un codice contesto pagamento uguale a
             * quello passato in questa chiamata, la RPT precedentemente registrata deve essere riutilizzata senza chiederne i dati 
             * all'applicativo fruitore.
             */
            RPT filtro = new RPT();
            filtro.setIuv(header.getIdentificativoUnivocoVersamento());
            List<RPT> elencoTrovate = p.recuperaRichiestaPagamentoConFiltro(filtro);
            LoggerUtil.info("Recuperato tutti i dati adesso li controllo.");
            if(elencoTrovate != null) 
                LoggerUtil.dumpObject("ELENCO RPT ASSOCIATE ", elencoTrovate);
            else 
                LoggerUtil.info("ELENCO RPT ASSOCIATE NULL");

            Map<String, String> mappaAppCustomFields = CoreUtilities.costruisciMappaApplicationCustomFields(datiIuvFruitore.getElencoAcf());
            
            if ( !header.getIdentificativoDominio ()
                .equalsIgnoreCase ( mappaAppCustomFields.get ( CostantiNodoSpc.APP_PARAM_IDENTIFICATIVO_DOMINIO ) ) ) {
                parametriSpecificiGiornale = FaultBeanEnum.PAA_ID_DOMINIO_ERRATO.getDescription () + "Valore MDP: "
                                + mappaAppCustomFields.get ( CostantiNodoSpc.APP_PARAM_IDENTIFICATIVO_DOMINIO ) + "Valore parametro: " + header.getIdentificativoDominio ();
                esito = componiEsitoAttivaFallimento ( esito, FaultBeanEnum.PAA_ID_DOMINIO_ERRATO.name (),
                    FaultBeanEnum.PAA_ID_DOMINIO_ERRATO.getFaultString (), FaultBeanEnum.PAA_ID_DOMINIO_ERRATO.getDescription (),
                    header.getIdentificativoDominio () );
                return risposta;
            }
            
            Map<String, String> mappaTea = null;

            if (elencoTrovate == null || elencoTrovate.size() == 0) {//Se non si trova la RPT si chiedono i dati al fruitore e si genera una nuova transazione
                LoggerUtil.info ( "TROVATE NONE - ATTIVAZIONE RPT" );
                t = attivaRPTPressoFruitore(bodyrichiesta, header, risposta, esito, p, datiIuvFruitore, t, infoPSP, mappaAppCustomFields, mappaTea);
                nuovaTransazione = true;
            } else {//Casi in cui la RPT c'e': potrebbe essere in uno stato che non consente il pagamento oppure essere riutilizzata oppure scartata e richiesta una nuova
                rptTrovata = elencoTrovate.get(0);
                LoggerUtil.info("TROVATE RPT CON ID: " + rptTrovata.getId ());
                mappaTea = CoreUtilities.costruisciMappaTransazioneExtraAttributes(p.getTransazioneExtraAttributes(rptTrovata.getTransactionId()));
                RT rtTrovata = p.recuperaRichiestaPerIdTransazione(rptTrovata.getTransactionId());
                //TODO: stasso iuv, codice contesto diverso, ma RT pagata? Duplicato, vero?

                LoggerUtil.dumpObject ( "TROVATA RT", rtTrovata);

                if (rptTrovata.getIdStatiRpt().equals(80)) {//la RPT e' in stato PAGATO: si rifiuta il tentativo di pagamento se e' trovato una RT in stato pagato
                    LoggerUtil.info ( "RPT 80" );
                    if (rtTrovata != null &&rtTrovata.getIdEsitoPagamento().equals(1)) {
                        parametriSpecificiGiornale = "Pagamento " + header.getIdentificativoUnivocoVersamento() + " duplicato";
                        esito = componiEsitoAttivaFallimento(esito, FaultBeanEnum.PAA_PAGAMENTO_DUPLICATO.name(), FaultBeanEnum.PAA_PAGAMENTO_DUPLICATO.getFaultString(), "Pagamento gia' avvenuto", header.getIdentificativoDominio());
                    } else {
                        t = attivaRPTPressoFruitore(bodyrichiesta, header, risposta, esito, p, datiIuvFruitore, t, infoPSP, mappaAppCustomFields, mappaTea);
                        nuovaTransazione = true;
                    }
                } else {//se non siamo in condizioni non recuperabili (pagamento è  riconosciuto e atteso, non concluso e non rifiutato) invio la RPT esistente, sempre che gli identificativi siano uguali, sennò ne mando una nuova
                    LoggerUtil.info ( "RPT OPT" );

                    if (rptTrovata.getCodiceContestoPagamento().equals(header.getCodiceContestoPagamento()) && rptTrovata.getIdPsp().equals(bodyrichiesta.getIdentificativoPSP())) {
                        t = p.getTransazione(rptTrovata.getTransactionId());
                        ByteArrayOutputStream output = new ByteArrayOutputStream();
                        output.write(rptTrovata.getRptXml().getBytes());

                        //inviaRPT(output, t, mappaAppCustomFields, mappaTea, "", header, bodyrichiesta, infoPSP, false, rptTrovata);
                        
                        try {
                            Singleton instance = Singleton.getInstance ();

                            instance.addThread ( new InviaRPTThread (output, t, mappaAppCustomFields, mappaTea, "", header, bodyrichiesta, infoPSP, false, rptTrovata, p));
                        } catch ( InterruptedException e ) {
                            e.printStackTrace ();
                        }
                        
                        
                    } else {
                        t = attivaRPTPressoFruitore(bodyrichiesta, header, risposta, esito, p, datiIuvFruitore, t, infoPSP, mappaAppCustomFields, mappaTea);
                        nuovaTransazione = true;
                    }
                }
            }
        } catch (BusinessException e) {
            StringBuilder errori = new StringBuilder ();
            for (String singoloerrore : e.getDettagli())
                errori.append(singoloerrore).append("\n");
            LoggerUtil.info("Errori di validazione nella creazione della RPT per iuv:"  + header.getIdentificativoUnivocoVersamento() + "\n" + errori);

        } catch (Exception e) {

            LoggerUtil.error("ERRORE in attivazione RPT per IUV "  + header.getIdentificativoUnivocoVersamento(), e);
            if (t != null) {
                t.setOldstate(t.getCodStato());
                t.setCodStato(5);
            }
            parametriSpecificiGiornale = Utils.concatenaMessaggiCausa(e).toString();

            LoggerUtil.info ( "ATTIVA RPT RISPOSTA" );

            risposta.setPaaAttivaRPTRisposta(componiEsitoAttivaFallimento(esito, FaultBeanEnum.PAA_SYSTEM_ERROR.name(), FaultBeanEnum.PAA_SYSTEM_ERROR.getFaultString(), FaultBeanEnum.PAA_SYSTEM_ERROR.getFaultString(), header.getIdentificativoDominio()));

            LoggerUtil.info ( "EMAIL ERRORE" );

            Utils.inviaEmailErrore(header.getIdentificativoUnivocoVersamento(), header.getCodiceContestoPagamento(), p, null, e, "paaAttivaRPT");

        } finally {
            LoggerUtil.info("REGISTRAZIONE GIORNALE EVENTI E REGISTRAZIONE TRANSAZIONE - START");

            if (esito != null)
              LoggerUtil.info("REGISTRAZIONE GIORNALE EVENTI paaAttivaRPT - ESITO:" + esito.getEsito ());
            
            if (header != null)
              LoggerUtil.info("REGISTRAZIONE GIORNALE EVENTI paaAttivaRPT - IUVOTTICO:" + header.getIdentificativoUnivocoVersamento());
            LoggerUtil.info("REGISTRAZIONE GIORNALE EVENTI paaAttivaRPT - PARAM GIORNALE:" + parametriSpecificiGiornale);
            
            registrazioneGiornaleEventi(bodyrichiesta, header, esito, datiIuvFruitore, t, parametriSpecificiGiornale,"paaAttivaRPT");
            registrazioneTransazione(header, p, t, nuovaTransazione);

            LoggerUtil.info("REGISTRAZIONE GIORNALE EVENTI E REGISTRAZIONE TRANSAZIONE - END");
        }
        LoggerUtil.dumpObject("COSA RISPONDIAMO NOI? ", risposta);
        LoggerUtil.end();

        watcher.stop ();
        watcher.dumpElapsed ( "PagamentiTelematiciCCPImpl", "paaAttivaRPT()", "[PagamentiTelematiciCCPImpl]::[paaAttivaRPT]", "(valore input omesso)" );
        return risposta;
    }


    private void registrazioneGiornaleEventi(PaaAttivaRPT bodyrichiesta, IntestazionePPT header, EsitoAttivaRPT esito, IuvOtticoFruitore datiIuvFruitore, Transazione t,
        String parametriSpecificiGiornale,String azione) {
        if (datiIuvFruitore != null) {
            LoggerUtil.info("REGISTRAZIONE GIORNALE EVENTI paaAttivaRPT - DATI IUV FRUITORE IS NOT NULL");
            LoggerUtil.info("REGISTRAZIONE GIORNALE EVENTI paaAttivaRPT - APP ID " + datiIuvFruitore.getApplicationDetail().getApplicationid());
            LoggerUtil.info("REGISTRAZIONE GIORNALE EVENTI paaAttivaRPT - AZIONE " + azione);
            LoggerUtil.info("REGISTRAZIONE GIORNALE EVENTI paaAttivaRPT - ID INTERMEDIARIO " +  header.getIdentificativoStazioneIntermediarioPA());
            LoggerUtil.info("REGISTRAZIONE GIORNALE EVENTI paaAttivaRPT - DOMINIO " + header.getIdentificativoDominio());
            LoggerUtil.info("REGISTRAZIONE GIORNALE EVENTI paaAttivaRPT - PSP " + bodyrichiesta.getIdentificativoPSP());
            LoggerUtil.info("REGISTRAZIONE GIORNALE EVENTI paaAttivaRPT - IUV " + header.getIdentificativoUnivocoVersamento());
            
            registraEventoGiornale(datiIuvFruitore.getApplicationDetail().getApplicationid(),t != null ? t.getTransactionId() : "N/A", "N/A", esito.getEsito(), azione, header.getIdentificativoUnivocoVersamento(), "PO",
                            header.getIdentificativoStazioneIntermediarioPA(), header.getIdentificativoDominio(), bodyrichiesta.getIdentificativoPSP(),
                            "N/A", parametriSpecificiGiornale, header.getCodiceContestoPagamento());
        } else {
            LoggerUtil.info("REGISTRAZIONE GIORNALE EVENTI paaAttivaRPT - DATI IUV FRUITORE IS NULL");
            
            registraEventoGiornale("N/A","N/A", "N/A", esito.getEsito(), "paaAttivaRPT", header.getIdentificativoUnivocoVersamento(), "PO",
                header.getIdentificativoStazioneIntermediarioPA(), header.getIdentificativoDominio(), bodyrichiesta.getIdentificativoPSP(),
                "N/A", parametriSpecificiGiornale, header.getCodiceContestoPagamento());
        }
    }

    private void registrazioneTransazione(IntestazionePPT header, Payment p, Transazione t, boolean nuovaTransazione) {
        if (t != null) {
            registraStatoTransazione(t, p, header.getIdentificativoUnivocoVersamento());
            if (nuovaTransazione)
                try {
                    PersistenzaDatiNodoUtility.registraTransazioneIuv(t.getTransactionId(), header.getIdentificativoUnivocoVersamento());
                } catch (RemoteException e) {
                    LoggerUtil.error("ERRORE NELLA REGISTRAZIONE DELL'ASSOCIAZIONE IUV TRRANSAZIONE " + t.getTransactionId(), e);
                } catch (DaoException e) {
                    LoggerUtil.error("ERRORE NELLA REGISTRAZIONE DELL'ASSOCIAZIONE IUV TRRANSAZIONE " + t.getTransactionId(), e);
                } catch (MissingParameterException e) {
                    LoggerUtil.error("ERRORE NELLA REGISTRAZIONE DELL'ASSOCIAZIONE IUV TRRANSAZIONE " + t.getTransactionId(), e);
                } catch (NamingException e) {
                    LoggerUtil.error("ERRORE NELLA REGISTRAZIONE DELL'ASSOCIAZIONE IUV TRRANSAZIONE " + t.getTransactionId(), e);
                } catch (CreateException e) {
                    LoggerUtil.error("ERRORE NELLA REGISTRAZIONE DELL'ASSOCIAZIONE IUV TRRANSAZIONE " + t.getTransactionId(), e);
                }
        }
    }

    private Transazione attivaRPTPressoFruitore(PaaAttivaRPT bodyrichiesta, IntestazionePPT header, PaaAttivaRPTRisposta risposta, EsitoAttivaRPT esito, Payment p,
        IuvOtticoFruitore datiIuvFruitore, Transazione t, InformativePSP infoPSP, Map<String, String> mappaAppCustomFields, Map<String, String> mappaTea) throws ChiediDatiPagamentoException,
    UnrecoverableException, RemoteException, DaoException, NamingException, CreateException, MissingParameterException, DatatypeConfigurationException, JAXBException,
    BusinessException, SAXException {

        StopWatch watcher = new StopWatch ( Constants.APP_CODE_PERF );
        watcher.start ();

        LoggerUtil.info ( "[PagamentiTelematiciCCPImpl::attivaRPTPressoFruitore] - INIT" );

        t = p.initTransazione(datiIuvFruitore.getApplicationDetail().getApplicationid(), null);
        t.setCcy("EUR");
        t.setAmount(bodyrichiesta.getDatiPagamentoPSP().getImportoSingoloVersamento().doubleValue());
        t.setGatewayId(datiIuvFruitore.getApplicationDetail().getGatewayid());
        t.setGatewaypaymodeid(datiIuvFruitore.getApplicationDetail().getPaymentmodeid());

        EsitoChiediDatiPagamento datiPag = attivaPagamentoFruitore(bodyrichiesta, header, datiIuvFruitore, t.getTransactionId(), mappaAppCustomFields);

        t.setOldstate(t.getCodStato());
        t.setCodStato(3); //STARTED

        p.setTransazione(t);

        LoggerUtil.info ( "ESITO IS :"  + datiPag.getEsito ());

        if ( datiPag.getEsito ().equals ( "OK" ) || ( !datiPag.getEsito ().equals ( "OK" )
                        && FaultBeanEnum.PAA_ATTIVA_RPT_IMPORTO_NON_VALIDO.name ().equals ( datiPag.getCodErrore () ) ) ) {
            mappaTea = costruisciMAppaTea(datiPag.getTea());
            it.csi.mdp.core.business.dto.TransazioneExtraAttribute[] tea = costruisciArrayTeaDaRegistrare(datiPag.getTea(),t.getTransactionId());
            p.registraTransazioneExtraAttributes(tea);

            List<String> errori = controlliFormali(mappaTea, mappaAppCustomFields);

            if (errori != null && errori.size() > 0) {

                LoggerUtil.info ( "ERRORI NEI CONTROLLI FORMALI" );

                esito = componiEsitoAttivaFallimento(esito, FaultBeanEnum.PAA_SEMANTICA.name(), FaultBeanEnum.PAA_SEMANTICA.getFaultString(), "Dati non corretti", header.getIdentificativoDominio());
                registraErrori(errori, t);
                t.setOldstate(t.getCodStato());
                t.setCodStato(5);
                watcher.stop ();
                watcher.dumpElapsed ( "PagamentiTelematiciCCPImpl", "attivaRPTPressoFruitore()",
                    "[PagamentiTelematiciCCPImpl]::[attivaRPTPressoFruitore]", "ERRORE NEI CONTROLLI FOMRALI" );
                return t;
            }

            LoggerUtil.info ( "COMPOSIZIONE DATI PAGAMENTO" );

            PaaTipoDatiPagamentoPA tipoDatiPagamento = componiDatiPagamento(bodyrichiesta, esito, mappaAppCustomFields, mappaTea, header, datiPag, datiIuvFruitore);
            t.setAmount ( tipoDatiPagamento.getImportoSingoloVersamento ().doubleValue () );
            String ibanToPass = PersistenzaDatiNodoUtility.recuperaIbanParametricoPerPsp(t.getApplicationId(), bodyrichiesta.getIdentificativoPSP(), "PO");
            mappaTea.put(CostantiNodoSpc.TEA_FIRMA_RICEVUTA, "0");//Vademecum attuazione modifiche 1.1: scheda 45 la firma ricevuta puo' essere richiesta solo per i pagamenti attivati presso l'Ente Creditore

            //LF - MDPNEW-76 - Numero di cifre dopo la virgola - inizio

            BigDecimal bigDecimalAmount = BigDecimal.valueOf(t.getAmount());

            if (null != bigDecimalAmount && bigDecimalAmount.scale() > 2) {
                throw new UnrecoverableException(String.format("Importo  [%s] non valido. Ricevute %d cifre dopo la virgola (massimo = 2)", bigDecimalAmount.toString(), bigDecimalAmount.scale()));
            }

            if (null != bigDecimalAmount && bigDecimalAmount.scale() < 2) {
                bigDecimalAmount.setScale ( 2);
                LoggerUtil.warn(String.format("Importo  [%s] non valido. Ricevute %d cifre dopo la virgola (minimo = 2)", bigDecimalAmount.toString(), bigDecimalAmount.scale()));
            }
            //LF - MDPNEW-76 - Numero di cifre dopo la virgola - fine

            LoggerUtil.info ( "CREO OGGETTO RPT" );

            CtRichiestaPagamentoTelematico rpt = DatiInterscambioNodoUtility.creaOggettoRPT(t.getTransactionId(), t.getAmount(), "PO", mappaTea, mappaAppCustomFields, ibanToPass, header.getCodiceContestoPagamento(), bodyrichiesta.getDatiPagamentoPSP().getSoggettoPagatore(), bodyrichiesta.getDatiPagamentoPSP().getSoggettoVersante());
            LoggerUtil.dumpObject("OGGETTO RPT:", rpt);

            

            if(mappaTea != null) { 
                LoggerUtil.dumpObject ( "MAPPA TEA:", mappaTea );
            }else { 
                LoggerUtil.info ( "MAPPA TEA IS NULL");
            }
            String muv = null;
            String iuv = null;

            if(mappaTea != null) {
                muv = mappaTea.get(CostantiNodoSpc.TEA_MULTIVERSAMENTO);
                iuv = mappaTea.get(CostantiNodoSpc.TEA_IDENTIFICATIVO_UNIVOCO_VERSAMENTO);
            }

            LoggerUtil.info ( "MULTIVERSAMENTO UNIVERSALE" );

            gestioneMultiversamentoUniversale(rpt, muv, t, iuv, 
                null != mappaTea? mappaTea.get(CostantiNodoSpc.APP_PARAM_DATI_SPECIFICI_RISCOSSIONE): "");
            
//            Spostato in quanto i dati specifici riscossione potrebbero cambiare in base a quanto si trova nei dati versamento
            
            ByteArrayOutputStream output = DatiInterscambioNodoUtility.generaXml(rpt);

            //inviaRPT(output, t, mappaAppCustomFields, mappaTea, "", header, bodyrichiesta, infoPSP, true, null);

            try {
                Singleton instance = Singleton.getInstance ();

                instance.addThread ( new InviaRPTThread (output, t, mappaAppCustomFields, mappaTea, "", header, bodyrichiesta, infoPSP, true, null, p));
            } catch ( InterruptedException e ) {
                e.printStackTrace ();
            }
            
            
            esito.setEsito("OK");
            esito.setDatiPagamentoPA(tipoDatiPagamento);

        } else{
            t.setOldstate(t.getCodStato());
            t.setCodStato(5);
            if (FaultBeanEnum.PAA_PAGAMENTO_ANNULLATO.name().equals(datiPag.getCodErrore())) {
                t.setCodStato(6);
            }
            esito = componiEsitoAttivaFallimento(esito, datiPag.getCodErrore(), datiPag.getMessaggioErrore(), datiPag.getMessaggioErrore(), header.getIdentificativoDominio());
        }

        LoggerUtil.info ( "[PagamentiTelematiciCCPImpl::attivaRPTPressoFruitore] - END" );

        watcher.stop ();
        watcher.dumpElapsed ( "PagamentiTelematiciCCPImpl", "attivaRPTPressoFruitore()",
            "[PagamentiTelematiciCCPImpl]::[attivaRPTPressoFruitore]", "(valore input omesso)" );

        return t;
    }

    private void gestioneMultiversamentoUniversale(CtRichiestaPagamentoTelematico rpt, String multiversamentoXml, Transazione t, String iuv, 
        String datiSpecificiRiscossione) throws RemoteException, NamingException, CreateException, JAXBException, DaoException, MissingParameterException, SAXException {

        StopWatch watcher = new StopWatch ( Constants.APP_CODE_PERF );
        watcher.start ();

        List<it.csi.mdp.core.business.dto.multicarrello.DatiSingoloVersamentoRPT> elencoSingoliPagamentiSpacchettati = new LinkedList<> ();         

        LoggerUtil.info ( "MULTIVERSAMENTO :" + multiversamentoXml);

        if(!StringUtils.isEmpty ( multiversamentoXml )) {

            //
            LoggerUtil.info ("MULTIVERSAMENTO XML BEFORE:" + multiversamentoXml);
            //multiversamentoXml arriva in base 64
            multiversamentoXml = new String(Base64.decodeBase64 ( multiversamentoXml ));
            //
            LoggerUtil.info("MULTIVERSAMENTO XML AFTER:" + multiversamentoXml);
            //      
            JAXBContext contextFlussoJAXB = JAXBContext.newInstance(ElencoDatiVersamento.class);
            Unmarshaller unmarshaller = contextFlussoJAXB.createUnmarshaller();
            unmarshaller.setEventHandler(new ValidationEventHandler() {public boolean handleEvent(ValidationEvent event ) {throw new RuntimeException(event.getMessage(),event.getLinkedException());}});

            SchemaFactory sf = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
            Source so = new StreamSource(this.getClass().getResourceAsStream("/META-INF/MultiVersamento_1_0_0.xsd"));
            Schema s = sf.newSchema(so);
            unmarshaller.setSchema(s);
            //Se non posso fare l'unmarshall del multiversamento allora faccio lo skip della verifica dei totali
            //quindi -> elencoSingoliPagamentiSpacchettati sara' vuoto, attenzione

            LoggerUtil.info ( "VERIFICA CORRETTEZZA FORMALE" );

            ElencoDatiVersamento verificaCorrettezzaFormale = (ElencoDatiVersamento)unmarshaller.unmarshal(new StringReader(multiversamentoXml));


            if ( null != verificaCorrettezzaFormale && !CollectionUtils.isEmpty ( verificaCorrettezzaFormale.getDatiSingoloVersamento () ) ) {
                BigDecimal tot = BigDecimal.ZERO;
                LoggerUtil.info ( "DatiSingoloVersamentoRPT SIZE:" + elencoSingoliPagamentiSpacchettati.size () );
                LoggerUtil.info ( "verificaCorrettezzaFormale.getDatiSingoloVersamento () SIZE:" + verificaCorrettezzaFormale.getDatiSingoloVersamento ().size () );

                for ( DatiSingoloVersamentoRPT elem: verificaCorrettezzaFormale.getDatiSingoloVersamento () ) {
                    it.csi.mdp.core.business.dto.multicarrello.DatiSingoloVersamentoRPT singoloPag
                        = new it.csi.mdp.core.business.dto.multicarrello.DatiSingoloVersamentoRPT ();

                    singoloPag.setCausaleVersamento ( elem.getCausaleVersamento () );
                    singoloPag.setCommissioneCaricoPA ( elem.getCommissioneCaricoPA () );
                    singoloPag.setCredenzialiPagatore ( elem.getCredenzialiPagatore () );

                    //Fix per campo con minlen 1 che se passato a empty causa un errore
                    if ( StringUtils.isEmpty ( singoloPag.getCredenzialiPagatore () ) )
                        singoloPag.setCredenzialiPagatore ( null );

                    it.csi.mdp.core.business.dto.multicarrello.DatiMarcaBolloDigitale toCopyMarcaDaBollo
                        = new it.csi.mdp.core.business.dto.multicarrello.DatiMarcaBolloDigitale ();
                    DatiAccertamentoRPT toCopyDatiAccertamento = new DatiAccertamentoRPT ();

                    if ( elem.getDatiMarcaBolloDigitale () != null ) {
                        if ( elem.getDatiMarcaBolloDigitale ().getTipoBollo () != null ) {
                            if ( elem.getDatiMarcaBolloDigitale ().getProvinciaResidenza () != null ) {
                                BeanUtils.copyProperties ( elem.getDatiMarcaBolloDigitale (), toCopyMarcaDaBollo );
                                singoloPag.setDatiMarcaBolloDigitale ( toCopyMarcaDaBollo );
                            }
                        }
                    }

                    if ( elem.getDatiAccertamento () != null ) {
                        if ( elem.getDatiAccertamento ().getAnnoAccertamento () != null ) {
                            if ( elem.getDatiAccertamento ().getNumeroAccertamento () != null ) {
                                BeanUtils.copyProperties ( elem.getDatiAccertamento (), toCopyDatiAccertamento );
                                singoloPag.setDatiAccertamento ( toCopyDatiAccertamento );
                            }
                        }
                    }

                    singoloPag.setDatiSpecificiRiscossione ( elem.getDatiSpecificiRiscossione () );
                    singoloPag.setImportoSingoloVersamento ( elem.getImportoSingoloVersamento () );
                    
                    LoggerUtil.dumpObject ( "SINGOLO PAGAMENTO", singoloPag );

                    elencoSingoliPagamentiSpacchettati.add ( singoloPag );
                    tot = tot.add ( elem.getImportoSingoloVersamento () );
                }
                
                if (StringUtils.isEmpty ( datiSpecificiRiscossione ))
                {
                    if (!CollectionUtils.isEmpty ( elencoSingoliPagamentiSpacchettati ) 
                                    && !StringUtils.isEmpty ( elencoSingoliPagamentiSpacchettati.get ( 0 ).getDatiSpecificiRiscossione () )
                                    )
                    {
                        rpt.getDatiVersamento ().getDatiSingoloVersamento ().get ( 0 ).setDatiSpecificiRiscossione (  elencoSingoliPagamentiSpacchettati.get ( 0 ).getDatiSpecificiRiscossione ()  );
                    }
                   
                }
                
                if ( tot.doubleValue () != t.getAmount () )
                    throw new DaoException ( "Multiversamento: importo totale somma singoli versamenti =" + tot + " Mentre amount = " + t.getAmount () );
                
                
            } else {
                LoggerUtil.info ( "Nessun Elemento DatiSingoloVersamento presente sulla Multiversamento" );
            }
        } else {
            //SINGOLO PAGAMENTO - MULTIVERSAMENTO XML

            LoggerUtil.info ( "SINGOLO RPT" );

            ElencoDatiVersamento datiVersamento = new ElencoDatiVersamento();
            DatiSingoloVersamentoRPT datiSingoloVersamentoRPT = new DatiSingoloVersamentoRPT ();

            if(rpt.getDatiVersamento ().getDatiSingoloVersamento ().size () <= 0) {
                throw new DaoException("Multiversamento: RPT senza dati singolo versamento");
            }

            it.csi.mdp.generatedvo.multiversamento.DatiAccertamentoRPT dAccertamento = null;

            if(rpt.getDatiVersamento ().getDatiSingoloVersamento ().get ( 0 ).getDatiAccertamento () != null) {
                dAccertamento = new it.csi.mdp.generatedvo.multiversamento.DatiAccertamentoRPT ();
                LoggerUtil.info ( "DACC NOT NULL" );

                dAccertamento.setAnnoAccertamento ( rpt.getDatiVersamento ().getDatiSingoloVersamento ().get ( 0 ).getDatiAccertamento ().getAnnoAccertamento ());
                dAccertamento.setNumeroAccertamento ( rpt.getDatiVersamento ().getDatiSingoloVersamento ().get ( 0 ).getDatiAccertamento ().getNumeroAccertamento ());
            }

            it.csi.mdp.generatedvo.multiversamento.DatiMarcaBolloDigitale datiMarcadabollo = null;

            if(rpt.getDatiVersamento ().getDatiSingoloVersamento ().get ( 0 ).getDatiMarcaBolloDigitale () != null) {
                datiMarcadabollo = new it.csi.mdp.generatedvo.multiversamento.DatiMarcaBolloDigitale ();
                LoggerUtil.info ( "MDBOLLO NOT NULL" );

                datiMarcadabollo.setHashDocumento ( rpt.getDatiVersamento ().getDatiSingoloVersamento ().get ( 0 ).getDatiMarcaBolloDigitale ().getHashDocumento () );
                datiMarcadabollo.setProvinciaResidenza ( rpt.getDatiVersamento ().getDatiSingoloVersamento ().get ( 0 ).getDatiMarcaBolloDigitale ().getProvinciaResidenza () );
                datiMarcadabollo.setTipoBollo ( rpt.getDatiVersamento ().getDatiSingoloVersamento ().get ( 0 ).getDatiMarcaBolloDigitale ().getTipoBollo ());
            }

            datiSingoloVersamentoRPT.setCausaleVersamento ( rpt.getDatiVersamento ().getDatiSingoloVersamento ().get ( 0 ).getCausaleVersamento ());
            datiSingoloVersamentoRPT.setCommissioneCaricoPA ( BigDecimal.ZERO);
            datiSingoloVersamentoRPT.setCredenzialiPagatore ( rpt.getSoggettoPagatore ().getAnagraficaPagatore () );

            //Fix per campo con minlen 1 che se passato a empty causa un errore
            if(StringUtils.isEmpty (datiSingoloVersamentoRPT.getCredenzialiPagatore ())) datiSingoloVersamentoRPT.setCredenzialiPagatore ( null );
            
            if(dAccertamento    != null) datiSingoloVersamentoRPT.setDatiAccertamento ( dAccertamento );
            if(datiMarcadabollo != null) datiSingoloVersamentoRPT.setDatiMarcaBolloDigitale (datiMarcadabollo );

            datiSingoloVersamentoRPT.setDatiSpecificiRiscossione ( rpt.getDatiVersamento ().getDatiSingoloVersamento ().get ( 0 ).getDatiSpecificiRiscossione ());
            datiSingoloVersamentoRPT.setImportoSingoloVersamento ( rpt.getDatiVersamento ().getImportoTotaleDaVersare () );

            LoggerUtil.dumpObject ("SINGOLO VERSAMENTO RPT", datiSingoloVersamentoRPT );

            datiVersamento.getDatiSingoloVersamento().add (datiSingoloVersamentoRPT);

            JAXBContext contextFlussoJAXB = JAXBContext.newInstance(ElencoDatiVersamento.class);
            Marshaller marshaller = contextFlussoJAXB.createMarshaller ();
            marshaller.setEventHandler(new ValidationEventHandler() {public boolean handleEvent(ValidationEvent event ) {throw new RuntimeException(event.getMessage(),event.getLinkedException());}});

            SchemaFactory sf = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
            Source so = new StreamSource(this.getClass().getResourceAsStream("/META-INF/MultiVersamento_1_0_0.xsd"));
            Schema s = sf.newSchema(so);
            marshaller.setSchema(s);

            LoggerUtil.info ( "MASHALLING" );

            StringWriter sw = new StringWriter();
            marshaller.marshal (datiVersamento , sw);

            multiversamentoXml = sw.toString();          

            LoggerUtil.info ( "MULTIVERSAMENTO CREATO " + multiversamentoXml);

            //SINGOLO PAGAMENTO - PAGAMENTO SPACCHETTATO

            it.csi.mdp.core.business.dto.multicarrello.DatiSingoloVersamentoRPT singoloPagamento = new it.csi.mdp.core.business.dto.multicarrello.DatiSingoloVersamentoRPT ();
            elencoSingoliPagamentiSpacchettati = new ArrayList<it.csi.mdp.core.business.dto.multicarrello.DatiSingoloVersamentoRPT>();

            LoggerUtil.info ( "CREAZIONE SPACCHETTATI" );

            DatiAccertamentoRPT dacc = null;
            DatiMarcaBolloDigitale datm = null;

            if(datiSingoloVersamentoRPT.getDatiAccertamento () != null) {
                dacc = new DatiAccertamentoRPT ();
                LoggerUtil.info ( "DACC NOT NULL" );

                dacc.setAnnoAccertamento ( datiSingoloVersamentoRPT.getDatiAccertamento ().getAnnoAccertamento () );
                dacc.setNumeroAccertamento ( datiSingoloVersamentoRPT.getDatiAccertamento ().getNumeroAccertamento () );
            }

            if(datiSingoloVersamentoRPT.getDatiMarcaBolloDigitale () != null) {
                datm = new DatiMarcaBolloDigitale ();
                LoggerUtil.info ( "MARCADABOLLO NOT NULL" );

                datm.setHashDocumento ( datiSingoloVersamentoRPT.getDatiMarcaBolloDigitale ().getHashDocumento () );
                datm.setProvinciaResidenza ( datiSingoloVersamentoRPT.getDatiMarcaBolloDigitale ().getProvinciaResidenza () );
                datm.setTipoBollo ( datiSingoloVersamentoRPT.getDatiMarcaBolloDigitale ().getTipoBollo ());
            }

            singoloPagamento.setCausaleVersamento ( datiSingoloVersamentoRPT.getCausaleVersamento () );
            singoloPagamento.setCommissioneCaricoPA ( datiSingoloVersamentoRPT.getCommissioneCaricoPA () );
            singoloPagamento.setCredenzialiPagatore ( datiSingoloVersamentoRPT.getCredenzialiPagatore () );

            //Fix per campo con minlen 1 che se passato a empty causa un errore
            if(StringUtils.isEmpty (singoloPagamento.getCredenzialiPagatore ())) singoloPagamento.setCredenzialiPagatore ( null );
                        
            singoloPagamento.setDatiAccertamento(null);
            singoloPagamento.setDatiMarcaBolloDigitale ( null );

            if(dacc != null) singoloPagamento.setDatiAccertamento ( dacc );
            if(datm != null) singoloPagamento.setDatiMarcaBolloDigitale ( datm );

            singoloPagamento.setDatiSpecificiRiscossione ( datiSingoloVersamentoRPT.getDatiSpecificiRiscossione () );
            singoloPagamento.setImportoSingoloVersamento ( datiSingoloVersamentoRPT.getImportoSingoloVersamento () );

            LoggerUtil.dumpObject ( "SINGOLO PAGAMENTO SPACCHETTATO", singoloPagamento );

            elencoSingoliPagamentiSpacchettati.add ( singoloPagamento  );            
        }

        LoggerUtil.info ( "STORE DEL PAGAMENTO" );

        Payment p = PersistenzaDatiNodoUtility.reperisciPaymentInterface();
        ElementoMultiversamento elemento = new ElementoMultiversamento();
        elemento.setApplicationId(t.getApplicationId());
        elemento.setTransactionId(t.getTransactionId());
        elemento.setPosizione(0);
        elemento.setIuv(iuv);
        elemento.setXmlElemento(multiversamentoXml);
        elemento.setModelloPagamento("3");
        elemento.setElencoSingoliPagamentiSpacchettati(elencoSingoliPagamentiSpacchettati);
        p.inserisciMultiVersamento(elemento);

        watcher.stop ();
        watcher.dumpElapsed ( "PagamentiTelematiciCCPImpl", "gestioneMultiversamentoUniversale()",
            "[PagamentiTelematiciCCPImpl]::[gestioneMultiversamentoUniversale]", "(valore input omesso)" );
    }

    private PaaTipoDatiPagamentoPA componiDatiPagamento(PaaAttivaRPT bodyrichiesta, EsitoAttivaRPT esito, Map<String, String> mappaAppCustomFields, Map<String, String> mappaTea, IntestazionePPT header, EsitoChiediDatiPagamento datiPag, IuvOtticoFruitore datiIuvFruitore) {
        StopWatch watcher = new StopWatch ( Constants.APP_CODE_PERF );
        watcher.start ();

        PaaTipoDatiPagamentoPA tipoDatiPagamento = new PaaTipoDatiPagamentoPA();
        tipoDatiPagamento.setBicAccredito(StringUtils.isNotEmpty(mappaTea.get(CostantiNodoSpc.APP_PARAM_BIC_ACCREDITO)) ? mappaTea.get(CostantiNodoSpc.APP_PARAM_BIC_ACCREDITO) : mappaAppCustomFields.get(CostantiNodoSpc.APP_PARAM_BIC_ACCREDITO));
        tipoDatiPagamento.setCausaleVersamento(DatiInterscambioNodoUtility.componiCausaleVersamento(datiIuvFruitore.getIuvOttici().getIuvOttico(), bodyrichiesta.getDatiPagamentoPSP().getImportoSingoloVersamento().doubleValue(), mappaTea.get(CostantiNodoSpc.TEA_DESCRIZIONE_CAUSALE_VERSAMENTO)));
        tipoDatiPagamento.setCredenzialiPagatore(mappaTea.get(CostantiNodoSpc.TEA_CREDENZIALI_PAGATORE));
        
        //Fix per campo con minlen 1 che se passato a empty causa un errore
        if(StringUtils.isEmpty (tipoDatiPagamento.getCredenzialiPagatore ())) tipoDatiPagamento.setCredenzialiPagatore ( null );                    
                
        CtEnteBeneficiario ente = new CtEnteBeneficiario();
        it.csi.mdp.generatedvo.pagamenti.CtEnteBeneficiario enteSorgente = DatiInterscambioNodoUtility.creaEnteBeneficiario(mappaTea, mappaAppCustomFields);
        BeanUtils.copyProperties(enteSorgente, ente, new String[] {"identificativoUnivocoBeneficiario"});
        CtIdentificativoUnivocoPersonaG identificativoUnivocoPersonaG = new CtIdentificativoUnivocoPersonaG();
        identificativoUnivocoPersonaG.setCodiceIdentificativoUnivoco(enteSorgente.getIdentificativoUnivocoBeneficiario().getCodiceIdentificativoUnivoco());
        identificativoUnivocoPersonaG.setTipoIdentificativoUnivoco(StTipoIdentificativoUnivocoPersG.valueOf(enteSorgente.getIdentificativoUnivocoBeneficiario().getTipoIdentificativoUnivoco().value()));
        ente.setIdentificativoUnivocoBeneficiario(identificativoUnivocoPersonaG);
        tipoDatiPagamento.setEnteBeneficiario(ente);
        tipoDatiPagamento.setIbanAccredito ( StringUtils.isNotEmpty ( mappaTea.get ( CostantiNodoSpc.TEA_IBAN_ADDEBITO ) )
                        ? mappaTea.get ( CostantiNodoSpc.TEA_IBAN_ADDEBITO ) : mappaAppCustomFields.get ( CostantiNodoSpc.APP_PARAM_IBAN_ACCREDITO ) );
        tipoDatiPagamento.setImportoSingoloVersamento ( StringUtils.isNotEmpty ( mappaTea.get ( CostantiNodoSpc.TEA_IMPORTO_TOTALE_DA_VERSARE ) )
                        ? new BigDecimal ( mappaTea.get ( CostantiNodoSpc.TEA_IMPORTO_TOTALE_DA_VERSARE ) ).setScale ( 2, RoundingMode.HALF_UP )
                        : bodyrichiesta.getDatiPagamentoPSP ().getImportoSingoloVersamento () );
        esito.setDatiPagamentoPA(tipoDatiPagamento);

        watcher.stop ();
        watcher.dumpElapsed ( "PagamentiTelematiciCCPImpl", "componiDatiPagamento()", "[PagamentiTelematiciCCPImpl]::[componiDatiPagamento]",
                        "(valore input omesso)" );
        return tipoDatiPagamento;
    }

    private it.csi.mdp.core.business.dto.TransazioneExtraAttribute[] costruisciArrayTeaDaRegistrare(List<TransazioneExtraAttribute> elencoValori, String idTransazione) {
        StopWatch watcher = new StopWatch ( Constants.APP_CODE_PERF );
        watcher.start ();

        it.csi.mdp.core.business.dto.TransazioneExtraAttribute[] tea = 
                        new it.csi.mdp.core.business.dto.TransazioneExtraAttribute[elencoValori.size()];
        int i= 0;
        for (TransazioneExtraAttribute elem : elencoValori) {
            it.csi.mdp.core.business.dto.TransazioneExtraAttribute elementoDaRegistrare = new it.csi.mdp.core.business.dto.TransazioneExtraAttribute(i,elem.getName(),idTransazione,  elem.getValue());
            tea[i] = elementoDaRegistrare;
            i++;
        }
        watcher.stop ();
        watcher.dumpElapsed ( "PagamentiTelematiciCCPImpl", "costruisciArrayTeaDaRegistrare()",
            "[PagamentiTelematiciCCPImpl]::[costruisciArrayTeaDaRegistrare]", "(valore input omesso)" );
        return tea;
    }

    /**
     * Effettua l'aggiornamento o l'inserimento della RPT in base a quanto inidicato nel paaAttivaRPT, impostando il flag "da_inviare" a true.
     * 
     * PATCH MOMENTANEA IRENE !!!
     * 
     */
    
    class InviaRPTThread implements Callable<String> {

        private ByteArrayOutputStream output;

        private Transazione t;

        private Map<String, String> mappaAppCustomFields;

        private Map<String, String> mappaTea;

        private String identificativoCanale;

        private IntestazionePPT header;

        private PaaAttivaRPT bodyrichiesta;

        private InformativePSP infoPSP;

        private boolean rptDainserire;

        private RPT rptTrovata;
        
        private Payment p;

        public InviaRPTThread ( ByteArrayOutputStream output, 
            Transazione t, 
            Map<String, String> mappaAppCustomFields, 
            Map<String, String> mappaTea,
            String identificativoCanale, 
            IntestazionePPT header, 
            PaaAttivaRPT bodyrichiesta, 
            InformativePSP infoPSP, 
            boolean rptDainserire, 
            RPT rptTrovata,
            Payment p)
                        throws DatatypeConfigurationException, JAXBException, BusinessException, RemoteException, NamingException, CreateException,
                        DaoException, MissingParameterException {
            this.output = output;
            this.t = t;
            this.mappaAppCustomFields = mappaAppCustomFields;
            this.mappaTea = mappaTea;
            this.identificativoCanale = identificativoCanale;
            this.header = header;
            this.bodyrichiesta = bodyrichiesta;
            this.infoPSP = infoPSP;
            this.rptDainserire = rptDainserire;
            this.rptTrovata = rptTrovata;
            this.p = p;
        }

      //@Override
        public String call () throws Exception {

            StopWatch watcher = new StopWatch ( Constants.APP_CODE_PERF );
            watcher.start ();

            LoggerUtil.info ( "INVIO RPT");

            inviaRPT(output, t, mappaAppCustomFields, mappaTea, identificativoCanale, header, bodyrichiesta, infoPSP, rptDainserire, rptTrovata);
            
            watcher.stop ();
            watcher.dumpElapsed ( "PagamentiTelematiciRTImpl", "inviaRTThread()", "[PagamentiTelematiciRTImpl]::[inviaRTThread]", "(valore input omesso)" );

            return "";
        }
        
        private void inviaRPT(ByteArrayOutputStream output, Transazione t, Map<String, String> mappaAppCustomFields, Map<String, String> mappaTea, String identificativoCanale,IntestazionePPT header, PaaAttivaRPT bodyrichiesta, InformativePSP infoPSP, boolean rptDainserire, RPT rptTrovata) throws DatatypeConfigurationException, JAXBException, BusinessException, RemoteException, NamingException, CreateException, DaoException, MissingParameterException {
            StopWatch watcher = new StopWatch ( Constants.APP_CODE_PERF );
            watcher.start ();

            String errorePerGiornale = null;
            String esito = "KO";
            try {
                //crea intestazione SOAP del WS
                it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.IntestazionePPT intestazionePPT = DatiInterscambioNodoUtility.creaIntestazionePPTWs(mappaAppCustomFields, mappaTea, header.getCodiceContestoPagamento());
                intestazionePPT.setCodiceContestoPagamento(header.getCodiceContestoPagamento());
                
                //crea body SOAP del WS
                NodoInviaRPT nodoInviaRPT = DatiInterscambioNodoUtility.creaNodoInviaRPTWs(mappaAppCustomFields, infoPSP.getIdentificativoCanale(), infoPSP.getIdentificativoIntermediario(), bodyrichiesta.getIdentificativoPSP(),  "");
        
                nodoInviaRPT.setRpt(output.toByteArray());
                
                JaxWsProxyFactoryBean factory = CoreUtilities.inizializzaServizio(mappaAppCustomFields.get(CostantiNodoSpc.APP_PARAM_PORTA_DI_DOMINIO), "nodoInviaRPT");
                
                PagamentiTelematiciRPT pagamentiTelematiciService = (PagamentiTelematiciRPT)factory.create();
                
                // [MP] - 14/07/2020 check sull'applicazione per verificare il supporto dell'integrazione diretta pagoPA con il GAD
    			LoggerUtil.info("Parametro gadEnabled letto da mappa: <"+mappaAppCustomFields.get("gadEnabled")+">");
    			boolean gadEnabled = Boolean.parseBoolean(mappaAppCustomFields.get("gadEnabled"));
    			LoggerUtil.info("Applicazione "+t.getApplicationId()+" integrata direttamente con pagoPA: <"+gadEnabled+">");
    			
    			if(gadEnabled)
    				SSLUtils.setTLSContext(pagamentiTelematiciService);
        
                NodoInviaRPTRisposta risposta = pagamentiTelematiciService.nodoInviaRPT(nodoInviaRPT, intestazionePPT);
                
                LoggerUtil.dumpObject("RISPOSTA OTTENUTA DAL NODO:", risposta);
                
                if (risposta.getFault() != null) {
                    
                    t.setPayurl(null);
                    errorePerGiornale = PersistenzaDatiNodoUtility.substring(risposta.getFault().getFaultCode() + " - " + risposta.getFault().getFaultString() + " - " + risposta.getFault().getDescription(), 0, 512);
                    t.setErrcode(PersistenzaDatiNodoUtility.substring(errorePerGiornale, 0, 50));
                    LoggerUtil.error(t.getErrcode());
                    throw creaBusinessExceptionConDettagli(errorePerGiornale);
                } else {
                    esito = "OK";
                }
            } catch ( Exception e ) {
                LoggerUtil.error ( "IMPOSSIBILE GESTIRE LA RPT", e );
                LoggerUtil.error("ERRORE in attivazione RPT per IUV "  + header.getIdentificativoUnivocoVersamento(), e);
                if (t != null) {
                    t.setOldstate(t.getCodStato());
                    t.setCodStato(5);
                }
                LoggerUtil.info ( "EMAIL ERRORE" );

                Utils.inviaEmailErrore(header.getIdentificativoUnivocoVersamento(), header.getCodiceContestoPagamento(), p, null, e, "paaAttivaRPT");
            } finally {
                try {
                    Timestamp ts = new Timestamp(System.currentTimeMillis());
                    LoggerUtil.info("###################################################");
                    LoggerUtil.infoObject("TRANSAZIONE", t);
                    LoggerUtil.infoObject("INFO PSP", infoPSP);
                    LoggerUtil.infoObject("BODYRICHIESTA ", bodyrichiesta);
                    LoggerUtil.infoObject("HEADER ", header);
                    LoggerUtil.infoObject("ESITO ", esito);
                    LoggerUtil.infoObject("RPT ", new String(output.toByteArray()));
                    LoggerUtil.infoObject("RPT DA INSERIRE",rptDainserire);
                    LoggerUtil.infoObject("RPT TROVATA IS NULL", (rptTrovata == null));
                    LoggerUtil.infoObject("TS", ts.getTime ());
                    
                    if (rptDainserire) {
                        LoggerUtil.info("########### REGISTRAZIONE RPT - START");
                        PersistenzaDatiNodoUtility.registraRPT(t.getApplicationId(), t.getTransactionId(), "n/a", infoPSP.getIdentificativoCanale(), bodyrichiesta.getIdentificativoPSP(), bodyrichiesta.getIdentificativoIntermediarioPSP(), new String(output.toByteArray()), header.getIdentificativoDominio(), header.getIdentificativoIntermediarioPA(), header.getIdentificativoStazioneIntermediarioPA(), header.getIdentificativoUnivocoVersamento(), esito, header.getCodiceContestoPagamento(), false);
                        LoggerUtil.info("REGISTRAZIONE RPT - END");
                    } else {
                        LoggerUtil.info("########### REGISTRAZIONE RPT UPDATE - START");
                        rptTrovata.setLastUpdate(ts);
                        rptTrovata.setIdStatiRpt("OK".equals(esito) ? StatiRPTEnum.ATTESA_RT.id() : StatiRPTEnum.RIFIUTATA.id());
                        PersistenzaDatiNodoUtility.aggiornaRPT(rptTrovata);
                        LoggerUtil.info("REGISTRAZIONE RPT UPDATE - END");
                    }
                    
                    LoggerUtil.info("########### INIZIO REGISTRAZIONE EVENTI");                
                    LoggerUtil.info("REGISTRAZIONE GIORNALE EVENTI inviaRPT - DATI IUV FRUITORE IS NOT NULL");
                    LoggerUtil.info("REGISTRAZIONE GIORNALE EVENTI inviaRPT - APP ID " + t.getApplicationId());
                    LoggerUtil.info("REGISTRAZIONE GIORNALE EVENTI inviaRPT - AZIONE " + "nodoInviaRPT");
                    LoggerUtil.info("REGISTRAZIONE GIORNALE EVENTI inviaRPT - ID INTERMEDIARIO " +  header.getIdentificativoStazioneIntermediarioPA());
                    LoggerUtil.info("REGISTRAZIONE GIORNALE EVENTI inviaRPT - DOMINIO " + header.getIdentificativoDominio());
                    LoggerUtil.info("REGISTRAZIONE GIORNALE EVENTI inviaRPT - PSP " + bodyrichiesta.getIdentificativoPSP());
                    LoggerUtil.info("REGISTRAZIONE GIORNALE EVENTI inviaRPT - IUV " + header.getIdentificativoUnivocoVersamento());
                    LoggerUtil.info("REGISTRAZIONE GIORNALE EVENTI inviaRPT - CODICECONTESTO " + header.getCodiceContestoPagamento());
                    LoggerUtil.info("FINE REGISTRAZIONE EVENTI");                
                    
                    LoggerUtil.info("########### INIZIO REGISTRAZIONE GIORNALE");                
                    registraEventoGiornale(t.getApplicationId(), t.getTransactionId(), t.getGatewayId(), esito, "nodoInviaRPT", header.getIdentificativoUnivocoVersamento(), "BO", header.getIdentificativoStazioneIntermediarioPA(), header.getIdentificativoDominio(), bodyrichiesta.getIdentificativoPSP(), bodyrichiesta.getIdentificativoIntermediarioPSP(), "OK".equals ( esito )? "" : errorePerGiornale, header.getCodiceContestoPagamento());
                    LoggerUtil.info("FINE REGISTRAZIONE GIORNALE");    
                } catch(Exception e) {
                    LoggerUtil.error ( "ERRORE IN INVIA RPT FINALLY", e);
                }
            }
            
            watcher.stop ();
            watcher.dumpElapsed ( "PagamentiTelematiciRTImpl", "inviaRPT()", "[PagamentiTelematiciRTImpl]::[inviaRPT]", "(valore input omesso)" );
        }
        
        private void registraEventoGiornale(String applicationId, String transactionId, String gatewayId, String esito, String tipoEvento, String iuv, String tipoVersamento, String identificativoStazioneIntermediarioPA, String identificativoDominio, String idPsp, String idIntermediarioPsp, String parametriSpecificiInterfaccia, String codiceContestoPagamento) {
            try {
                PersistenzaDatiNodoUtility.registraEventoGiornale(applicationId, transactionId, gatewayId, esito, tipoEvento, iuv, tipoVersamento, identificativoStazioneIntermediarioPA, identificativoDominio, idPsp, idIntermediarioPsp, parametriSpecificiInterfaccia,"mdpnodospcservices", codiceContestoPagamento);
            } catch (Exception e) {
                LoggerUtil.error("IMPOSSIBILE REGISTRARE EVENTO GIORNALE", e);
            }
        }
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









    /**
     * Aggiorna la transazione di riferimento
     * @param t
     * @param p
     */
    private void registraStatoTransazione(Transazione t, Payment p, String iuv) {

        LoggerUtil.dumpObject("TRANSAZIONE CHE VOGLIO REGISTRARE", t);
        try {
            p.setTransazione(t);
        } catch (RemoteException e) {
            LoggerUtil.error("ERRORE NELLA REGISTRAZIONE DELLO STATO TRANSAZIONE " + t.getTransactionId(), e);
        } catch (DaoException e) {
            LoggerUtil.error("ERRORE NELLA REGISTRAZIONE DELLO STATO TRANSAZIONE " + t.getTransactionId(), e);
        } catch (MissingParameterException e) {
            LoggerUtil.error("ERRORE NELLA REGISTRAZIONE DELLO STATO TRANSAZIONE " + t.getTransactionId(), e);
        } catch (NamingException e) {
            LoggerUtil.error("ERRORE NELLA REGISTRAZIONE DELLO STATO TRANSAZIONE " + t.getTransactionId(), e);
        } catch (CreateException e) {
            LoggerUtil.error("ERRORE NELLA REGISTRAZIONE DELLO STATO TRANSAZIONE " + t.getTransactionId(), e);
        }

    }


    /**
     * Costruisce la mappa dei TEA
     * @param elencoValori
     * @return
     */
    private Map<String, String> costruisciMAppaTea(List<TransazioneExtraAttribute> elencoValori) {
        Map<String, String> mappaTea = new HashMap<String, String>();
        for (TransazioneExtraAttribute elem : elencoValori) {
            mappaTea.put(elem.getName(), elem.getValue());
        }
        return mappaTea;
    }


    /**
     * Reperisce l'interfaccia al servizio core che fornisce l'accesso al DB
     * @return
     * @throws NamingException
     * @throws CreateException
     * @throws RemoteException
     */
    private Payment reperisciPaymentInterface() throws NamingException, CreateException, RemoteException {
        InitialContext ctx = new InitialContext();
        Object o = ctx.lookup(Constants.PAYMENT_MANAGER_JNDI_PATH);
        PaymentHome home = (PaymentHome) o;
        Payment p = home.create();
        return p;
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
     * Compone l'oggetto esito in caso di fallimento per l'invocazione a paaVerificaRPT
     * @param esito
     * @param faultCode
     * @param faultString
     * @param faultDescription
     * @return
     */
    private EsitoVerificaRPT componiEsitoVerificaFallimento (EsitoVerificaRPT esito, String faultCode, String faultString, String faultDescription, String idDominio) {
        FaultBean fault = new FaultBean();

        LoggerUtil.info ( "FAULT CODE " + faultCode);

        try {
            if(faultCode == null) {
                fault.setFaultString(faultString + " - (REQUIRED FAULT CODE IS NULL)");
            } else {
                fault.setFaultString(FaultBeanEnum.valueOf(faultCode).getFaultString());
            }
            fault.setFaultCode(faultCode);
            fault.setDescription(faultDescription);
            fault.setId(idDominio);
        } catch (IllegalArgumentException e) {//irrobustimento nel caso il fruitore risponda KO senza specificare un errore valido
            LoggerUtil.warn("Il fruitore ha risposto con un errore " + faultCode + " non valido");
            fault.setFaultString(faultDescription);
        }
        esito.setFault(fault);
        return esito;
    }

    /**
     * Compone l'esito in caso di fallimento per la chiamata all'operazione paaAttivaRPT
     * @param esito
     * @param faultCode
     * @param faultString
     * @param faultDescription
     * @param idDominio 
     * @return
     */
    private EsitoAttivaRPT componiEsitoAttivaFallimento (EsitoAttivaRPT esito, String faultCode, String faultString, String faultDescription, String idDominio) {
        FaultBean fault = new FaultBean();
        try {
            fault.setFaultCode(faultCode);
            fault.setFaultString(faultString);
            fault.setDescription(faultDescription);
            fault.setId(idDominio);
        } catch (IllegalArgumentException e) {//irrobustimento nel caso il fruitore risponda KO senza specificare un errore valido
            LoggerUtil.warn("Il fruitore ha risposto con un errore " + faultCode + " non valido");
            fault.setFaultString(faultDescription);
        }
        esito.setFault(fault);

        return esito;
    }

    /**
     * Invoca il fruitore per la verifica RPT
     * @param bodyrichiesta
     * @param header
     * @param iuvFruitore
     * @param mappaAcf
     * @return
     * @throws VerificaDatiPagamentoException
     * @throws UnrecoverableException
     */
    private EsitoVerificaDatiPagamento verificaPagamentoAttesoFruitore(PaaVerificaRPT bodyrichiesta,
        IntestazionePPT header, IuvOtticoFruitore iuvFruitore, Map<String, String> mappaAcf) throws VerificaDatiPagamentoException, UnrecoverableException {

        StopWatch watcher = new StopWatch ( Constants.APP_CODE_PERF );
        watcher.start ();

        LoggerUtil.info ( "ISTANZIO PROXY :" + mappaAcf.get(CostantiNodoSpc.APP_PARAM_ENDPONTI_SERVIZI_FRUITORE));
        
        LoggerUtil.info ( "ISTANZIO PROXY :" + mappaAcf.get(CostantiNodoSpc.APP_PARAM_ENDPONTI_SERVIZI_FRUITORE));

        /*
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.getInInterceptors().add(new LoggingInInterceptor());
        factory.getOutInterceptors().add(new LoggingOutInterceptor());
        factory.setServiceClass(Serviziorissrvspc.class);
        factory.setAddress(mappaAcf.get(CostantiNodoSpc.APP_PARAM_ENDPONTI_SERVIZI_FRUITORE));
        Serviziorissrvspc iPagNodo = (Serviziorissrvspc)factory.create();
        
        Client client = ClientProxy.getClient(iPagNodo);
        if (client != null) {
            HTTPConduit conduit = (HTTPConduit) client.getConduit();
            HTTPClientPolicy policy = new HTTPClientPolicy();
            policy.setConnectionTimeout(5000L);
            policy.setReceiveTimeout(10000L);
            conduit.setClient(policy);
        }
        */
        
        ParametriVerificaDatiPagamento req = new ParametriVerificaDatiPagamento();
        req.setIuv(header.getIdentificativoUnivocoVersamento());
        String timestamp = null;
        SimpleDateFormat sdf = new SimpleDateFormat("ddmmyyyy-hh:mm:ss:ms");
        timestamp = sdf.format(new Date());
        req.setMac(generaMac(mappaAcf.get(CostantiNodoSpc.APP_PARAM_PASSPHRASE_FRUITORE),header.getIdentificativoUnivocoVersamento(), iuvFruitore.getApplicationDetail().getApplicationid(), timestamp));
        req.setTimestamp(timestamp);

        EsitoVerificaDatiPagamento verificaDatiPagamentoFruitore = null;

//        try {
            Serviziorissrvspc iPagNodo = (Serviziorissrvspc)Utils.getProxyAPIService(Serviziorissrvspc.class, mappaAcf.get(CostantiNodoSpc.APP_PARAM_ENDPONTI_SERVIZI_FRUITORE), null);
            LoggerUtil.info ( "ISTANZA IS " + iPagNodo );

            LoggerUtil.info ( "ISTANZA IS " + iPagNodo );
            
            verificaDatiPagamentoFruitore = iPagNodo.verificaDatiPagamento(req);

            LoggerUtil.dumpObject("COSA MI HA RISPOSTO?", verificaDatiPagamentoFruitore);
           

            //LF - MDPNEW-76 - Numero di cifre dopo la virgola - inizio

            if (null != verificaDatiPagamentoFruitore) {
                BigDecimal importo = verificaDatiPagamentoFruitore.getImportoDovuto();

                if (null != importo) {
                    if (importo.scale() > 2) {
                        throw new VerificaDatiPagamentoException(String.format("Importo dovuto [%s] relativo alla causale %s non valido. Ricevute %d cifre dopo la virgola (massimo = 2)", importo.toString(),verificaDatiPagamentoFruitore.getCausaleVersamento(), importo.scale()));
                    } else if (importo.scale() < 2) {
                        LoggerUtil.warn(String.format("Importo dovuto [%s] relativo alla causale %s non valido. Ricevute %d cifre dopo la virgola (minimo = 2)", importo.toString(),verificaDatiPagamentoFruitore.getCausaleVersamento(), importo.scale()));
                        verificaDatiPagamentoFruitore.setImportoDovuto(importo.setScale(2));
                    }
                }
            }
            //LF - MDPNEW-76 - Numero di cifre dopo la virgola - fine
//        }catch(Throwable e) {
//            // e.printStackTrace (); [MP - 17/04: eliminato, esiste gia' la chiamata di logging]
//            LoggerUtil.error ( "ERRORE", e );
//            throw
//        }

           
        watcher.stop ();
        watcher.dumpElapsed ( "PagamentiTelematiciCCPImpl", "verificaPagamentoAttesoFruitore()",
            "[PagamentiTelematiciCCPImpl]::[verificaPagamentoAttesoFruitore]", "(valore input omesso)" );


        return verificaDatiPagamentoFruitore;
    }


    private String generaMac(String passphrase, String identificativoUnivocoVersamento, String applicationId, String timestamp) {

        String sToDigest =  passphrase + "%%%%" + identificativoUnivocoVersamento + timestamp + "%%%%" + passphrase;
        LoggerUtil.debug("STRINGONA MAC: " + sToDigest);
        byte[] bMac = DigestUtils.sha256(sToDigest.getBytes());
        String mac = Base64.encodeBase64String (bMac);
        mac = mac.substring (0,35);
        return mac;
    }

    /**
     * Invoca il fruitore per l'attivazione del pagamento
     * @param bodyrichiesta
     * @param header
     * @param iuvFruitore
     * @param idTransazione
     * @param mappaAppCustomFields
     * @return
     * @throws ChiediDatiPagamentoException
     * @throws UnrecoverableException
     */
    private EsitoChiediDatiPagamento attivaPagamentoFruitore(PaaAttivaRPT bodyrichiesta,
        IntestazionePPT header, IuvOtticoFruitore iuvFruitore, String idTransazione, Map<String, String> mappaAppCustomFields) throws ChiediDatiPagamentoException, UnrecoverableException {

        StopWatch watcher = new StopWatch ( Constants.APP_CODE_PERF );
        watcher.start ();

        LoggerUtil.info ( "[PagamentiTelematiciCCPImpl::attivaPagamentoFruitore] - INIT" );

        /*
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.getInInterceptors().add(new LoggingInInterceptor());
        factory.getOutInterceptors().add(new LoggingOutInterceptor());
        factory.setServiceClass(Serviziorissrvspc.class);
        factory.setAddress(mappaAppCustomFields.get(CostantiNodoSpc.APP_PARAM_ENDPONTI_SERVIZI_FRUITORE));
        Serviziorissrvspc iPagNodo = (Serviziorissrvspc)factory.create();
        */

        ParametriChiediDatiPagamento datiPag = new ParametriChiediDatiPagamento();
        datiPag.setImportoVersamento(bodyrichiesta.getDatiPagamentoPSP().getImportoSingoloVersamento());
        datiPag.setIuv(header.getIdentificativoUnivocoVersamento());
        String timestamp = null;
        SimpleDateFormat sdf = new SimpleDateFormat("ddmmyyyy-hh:mm:ss:ms");
        timestamp = sdf.format(new Date());
        datiPag.setMac(generaMacVersamento(mappaAppCustomFields.get(CostantiNodoSpc.APP_PARAM_PASSPHRASE_FRUITORE),header.getIdentificativoUnivocoVersamento(), bodyrichiesta.getDatiPagamentoPSP().getImportoSingoloVersamento(), timestamp));
        datiPag.setTimestamp(timestamp);
        datiPag.setTransactionId(idTransazione);
        
        Serviziorissrvspc iPagNodo = (Serviziorissrvspc)Utils.getProxyAPIService(Serviziorissrvspc.class, mappaAppCustomFields.get(CostantiNodoSpc.APP_PARAM_ENDPONTI_SERVIZI_FRUITORE),null);
        EsitoChiediDatiPagamento verificaDatiPagamentoFruitore = iPagNodo.chiediDatiPagamento(datiPag);

        LoggerUtil.dumpObject("COSA MI HA RISPOSTO?", verificaDatiPagamentoFruitore);

        LoggerUtil.info ( "[PagamentiTelematiciCCPImpl::attivaPagamentoFruitore] - END" );

        watcher.stop ();
        watcher.dumpElapsed ( "PagamentiTelematiciCCPImpl", "attivaPagamentoFruitore()", "[PagamentiTelematiciCCPImpl]::[attivaPagamentoFruitore]",
                        "(valore input omesso)" );

        return verificaDatiPagamentoFruitore;
    }

    private String generaMacVersamento(String passphrase, String identificativoUnivocoVersamento, BigDecimal importoSingoloVersamento, String timestamp) {
        String sToDigest = passphrase + "%%%%" + identificativoUnivocoVersamento + importoSingoloVersamento.toString() + timestamp + "%%%%" + passphrase;
        LoggerUtil.debug("CALOCLO MAC PER CHIEDI DATI PAGAMENTO: " + sToDigest);
        byte[] bMac = DigestUtils.sha256(sToDigest.getBytes());
        String mac = Base64.encodeBase64String (bMac);
        mac = mac.substring (0,35);
        return mac;
    }


    /**
     * 
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
     * @param parametriSpecificiInterfaccia
     * @throws RemoteException
     */
    private void registraEventoGiornale(String applicationId, String transactionId, String gatewayId, String esito, String tipoEvento, String iuv, String tipoVersamento, String identificativoStazioneIntermediarioPA, String identificativoDominio, String idPsp, String idIntermediarioPsp, String parametriSpecificiInterfaccia, String codiceContestoPagamento) {


        try {
            PersistenzaDatiNodoUtility.registraEventoGiornale(applicationId, transactionId, gatewayId, esito, tipoEvento, iuv, tipoVersamento, identificativoStazioneIntermediarioPA, identificativoDominio, idPsp, idIntermediarioPsp, parametriSpecificiInterfaccia,"mdpnodospcservices", codiceContestoPagamento);
        } catch (Exception e) {
            LoggerUtil.error("IMPOSSIBILE REGISTRARE EVENTO GIORNALE", e);
        }
    }

    /**
     * Esegue i controlli formali sui parametri passati dal fruitore collezionando gli eventuali errori per una successiva gestione nei confronti del fruitore.
     * @param t
     * @param gd
     * @param ad
     * @param mappaTea
     * @param infoPSP 
     * @param mappaAppCustomFields 
     * @return Elenco con gli errori, vuoto se non ce ne sono stati
     * @throws RemoteException
     */
    private List<String> controlliFormali(Map<String, String> mappaTea, Map<String, String> mappaAppCustomFields) throws RemoteException {
        List<String> elencoErrori = new ErroriList<String>();


        if (mappaTea.get(CostantiNodoSpc.TEA_AUTENTICAZIONE_SOGGETTO) == null) {
            elencoErrori.add("PARAMETRO TRANSAZIONE EXTRA ATTRIBUTE AUTENTICAZIONE SOGGETTO OBBLIGATORIO");
        } else if (
                        !"CNS".equals(mappaTea.get(CostantiNodoSpc.TEA_AUTENTICAZIONE_SOGGETTO)) &&
                        !"USR".equals(mappaTea.get(CostantiNodoSpc.TEA_AUTENTICAZIONE_SOGGETTO)) &&
                        !"OTH".equals(mappaTea.get(CostantiNodoSpc.TEA_AUTENTICAZIONE_SOGGETTO)) &&
                        !"N/A".equals(mappaTea.get(CostantiNodoSpc.TEA_AUTENTICAZIONE_SOGGETTO)) 
                        ){
            elencoErrori.add("PARAMETRO TRANSAZIONE EXTRA ATTRIBUTE AUTENTICAZIONE SOGGETTO DEVE AVERE UNO DEI SEGUENTI VALORI: CNS, USR, OTH, N/A ");
        }

        elencoErrori.add(campoErrato(mappaTea.get(CostantiNodoSpc.TEA_ANAGRAFICA_PAGATORE), true, 70,
                        "PARAMETRO TRANSAZIONE EXTRA ATTRIBUTE ANAGRAFICA PAGATORE"));

        elencoErrori.add(campoErrato(mappaTea.get(CostantiNodoSpc.TEA_TIPO_IDENTIFICATIVO_UNIVOCO_PAGATORE), true, 1, 
                        "PARAMETRO TRANSAZIONE EXTRA ATTRIBUTE TIPO IDENTIFICATIVO UNIVOCO PAGATORE"));

        elencoErrori.add(campoErrato(mappaTea.get(CostantiNodoSpc.TEA_CODICE_IDENTIFICATIVO_UNIVOCO_PAGATORE), true, 35,
                        "PARAMETRO TRANSAZIONE EXTRA ATTRIBUTE CODICE IDENTIFICATIVO UNIVOCO PAGATORE"));

        elencoErrori.add(campoErrato(mappaTea.get(CostantiNodoSpc.TEA_INDIRIZZO_PAGATORE), false, 70,
                        "PARAMETRO TRANSAZIONE EXTRA ATTRIBUTE INDIRIZZO PAGATORE"));

        elencoErrori.add(campoErrato(mappaTea.get(CostantiNodoSpc.TEA_CIVICO_PAGATORE), false, 16,
                        "PARAMETRO TRANSAZIONE EXTRA ATTRIBUTE CIVICO PAGATORE"));

        elencoErrori.add(campoErrato(mappaTea.get(CostantiNodoSpc.TEA_CAP_PAGATORE), false, 16,
                        "PARAMETRO TRANSAZIONE EXTRA ATTRIBUTE CAP PAGATORE"));

        elencoErrori.add(campoErrato(mappaTea.get(CostantiNodoSpc.TEA_LOCALITA_PAGATORE), false, 35,
                        "PARAMETRO TRANSAZIONE EXTRA ATTRIBUTE LOCALITA' PAGATORE"));

        elencoErrori.add(campoErrato(mappaTea.get(CostantiNodoSpc.TEA_PROVINCIA_PAGATORE), false, 35,
                        "PARAMETRO TRANSAZIONE EXTRA ATTRIBUTE PROVINCIA PAGATORE"));

        elencoErrori.add(campoErrato(mappaTea.get(CostantiNodoSpc.TEA_MAIL_PAGATORE), false, 256,
                        "PARAMETRO TRANSAZIONE EXTRA ATTRIBUTE E-MAIL PAGATORE"));



        elencoErrori.add(campoErrato(mappaTea.get(CostantiNodoSpc.TEA_ANAGRAFICA_VERSANTE), false, 70,
                        "PARAMETRO TRANSAZIONE EXTRA ATTRIBUTE ANAGRAFICA VERSANTE"));

        elencoErrori.add(campoErrato(mappaTea.get(CostantiNodoSpc.TEA_TIPO_IDENTIFICATIVO_UNIVOCO_VERSANTE), false, 1, 
                        "PARAMETRO TRANSAZIONE EXTRA ATTRIBUTE TIPO IDENTIFICATIVO UNIVOCO VERSANTE"));

        elencoErrori.add(campoErrato(mappaTea.get(CostantiNodoSpc.TEA_CODICE_IDENTIFICATIVO_UNIVOCO_VERSANTE), false, 35,
                        "PARAMETRO TRANSAZIONE EXTRA ATTRIBUTE CODICE IDENTIFICATIVO UNIVOCO VERSANTE"));

        elencoErrori.add(campoErrato(mappaTea.get(CostantiNodoSpc.TEA_INDIRIZZO_VERSANTE), false, 70,
                        "PARAMETRO TRANSAZIONE EXTRA ATTRIBUTE INDIRIZZO VERSANTE"));

        elencoErrori.add(campoErrato(mappaTea.get(CostantiNodoSpc.TEA_CIVICO_VERSANTE), false, 16,
                        "PARAMETRO TRANSAZIONE EXTRA ATTRIBUTE CIVICO VERSANTE"));

        elencoErrori.add(campoErrato(mappaTea.get(CostantiNodoSpc.TEA_CAP_VERSANTE), false, 16,
                        "PARAMETRO TRANSAZIONE EXTRA ATTRIBUTE CAP VERSANTE"));

        elencoErrori.add(campoErrato(mappaTea.get(CostantiNodoSpc.TEA_LOCALITA_VERSANTE), false, 35,
                        "PARAMETRO TRANSAZIONE EXTRA ATTRIBUTE LOCALITA' VERSANTE"));

        elencoErrori.add(campoErrato(mappaTea.get(CostantiNodoSpc.TEA_PROVINCIA_VERSANTE), false, 35,
                        "PARAMETRO TRANSAZIONE EXTRA ATTRIBUTE PROVINCIA VERSANTE"));

        elencoErrori.add(campoErrato(mappaTea.get(CostantiNodoSpc.TEA_MAIL_VERSANTE), false, 256,
                        "PARAMETRO TRANSAZIONE EXTRA ATTRIBUTE E-MAIL VERSANTE"));


        elencoErrori.add(campoErrato(mappaAppCustomFields.get(CostantiNodoSpc.APP_PARAM_CODICE_IDENTIFICATIVO_UNIVOCO_BENEFICIARIO), true, 35,
                        "PARAMETRO APPLICAZIONE IDENTIFICATIVO UNIVOCO BENEFICIARIO "));

        elencoErrori.add(campoErrato(mappaTea.get(CostantiNodoSpc.TEA_CAUSALE_VERSAMENTO), true, 140,
                        "PARAMETRO TRANSAZIONE EXTRA ATTRIBUTE CAUSALE VERSAMENTO"));

        elencoErrori.add(campoErrato(mappaTea.get(CostantiNodoSpc.TEA_BIC_ADDEBITO), false, 11,
                        "PARAMETRO TRANSAZIONE EXTRA ATTRIBUTE BIC ADDEBITO"));

        elencoErrori.add(campoErrato(mappaTea.get(CostantiNodoSpc.TEA_CREDENZIALI_PAGATORE), false, 35,
                        "PARAMETRO TRANSAZIONE EXTRA ATTRIBUTE CREDENZIALI PAGATORE"));

        elencoErrori.add(campoErrato(mappaTea.get(CostantiNodoSpc.TEA_FIRMA_RICEVUTA), false, 1,
                        "PARAMETRO TRANSAZIONE EXTRA ATTRIBUTE FIRMA RICEVUTA"));

        if (StringUtils.isNotEmpty(mappaTea.get(CostantiNodoSpc.TEA_FIRMA_RICEVUTA)) &&
                        !"0".equals(mappaTea.get(CostantiNodoSpc.TEA_FIRMA_RICEVUTA)) &&
                        !"1".equals(mappaTea.get(CostantiNodoSpc.TEA_FIRMA_RICEVUTA)) &&
                        !"3".equals(mappaTea.get(CostantiNodoSpc.TEA_FIRMA_RICEVUTA)) &&
                        !"4".equals(mappaTea.get(CostantiNodoSpc.TEA_FIRMA_RICEVUTA)) 
                        ){
            elencoErrori.add("PARAMETRO TRANSAZIONE EXTRA ATTRIBUTE FIRMA DEVE AVERE UNO DEI SEGUENTI VALORI: 0, 1, 3, 4");
        }

        elencoErrori.add(campoErrato(mappaTea.get(CostantiNodoSpc.TEA_ID_STAZIONE_RICHIEDENTE), false, 35,
                        "PARAMETRO TRANSAZIONE EXTRA ATTRIBUTE STAZIONE RICHIEDENTE"));


        if (mappaAppCustomFields.get(CostantiNodoSpc.APP_PARAM_DATI_SPECIFICI_RISCOSSIONE) == null) {
            elencoErrori.add("PARAMETRO APPLICAZIONE DATI SPECIFICI RISCOSSIONE OBBLIGATORIO");
        } else if (!mappaAppCustomFields.get(CostantiNodoSpc.APP_PARAM_DATI_SPECIFICI_RISCOSSIONE).matches("[0129]{1}/\\S{3,138}")) {
            elencoErrori.add("PARAMETRO APPLICAZIONE DATI SPECIFICI RISCOSSIONE IN FORMATO NON CORRETTO; FORMATO CORRETTO: [0129]{1}/\\S{3,138}");
        }

        LoggerUtil.dumpObject("ELENCO DEGLI ERRORI ", elencoErrori);

        return elencoErrori;
    }


    /**
     * Controlla che il campo rispetti i vincoli di obbligatorieta' e lunghezza massima specificati e compone il messaggio di errore specifico
     * Un campo può essere obbligatorio, in quel caso avra' anche una lunghezza massima
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
            return " LUNGHEZZA CAMPO " + campo + "|" + nomeCampoMessaggio + " SUPERA IL LIMITE DI " + lunghezzaCampo;

        return null;
    }

}

class Singleton {

    private static volatile Singleton instance = null;

    private static ScheduledExecutorService executor = (ScheduledExecutorService) Executors.newScheduledThreadPool ( 80 );

    private static int poolSize = 5;    
    private static int poolTout = 5;
    private static int poolDelay = 100;
    
    private Singleton () {
        Properties propsEnv = new Properties ();

        try {
            propsEnv.load ( this.getClass ().getResourceAsStream ( "/META-INF/env.properties" ) );

            LoggerUtil.debug ( "THREAD - PROPERTIES: " + propsEnv.getProperty ( "poolSizeRPT" ) );
            LoggerUtil.debug ( "THREAD - PROPERTIES: " + propsEnv.getProperty ( "poolTimeoutRPT" ) );
            LoggerUtil.debug ( "THREAD - PROPERTIES: " + propsEnv.getProperty ( "poolDelayRPT" ) );

            String size  = propsEnv.getProperty ( "poolSizeRPT" );
            String tout  = propsEnv.getProperty ( "poolTimeoutRPT" );
            String delay = propsEnv.getProperty ( "poolDelayRPT" );

            poolSize  = Integer.parseInt ( size );
            poolTout  = Integer.parseInt ( tout );
            poolDelay = Integer.parseInt ( delay);
        } catch ( Exception e ) {
            e.printStackTrace ();
        }

        executor = (ScheduledExecutorService) Executors.newScheduledThreadPool ( poolSize );
    }

    public void addThread ( InviaRPTThread thread ) throws InterruptedException {

        executor.schedule ( thread, poolDelay, TimeUnit.MILLISECONDS );

//        LoggerUtil.debug ( "THREAD - SIZE POOL :" + executor.getPoolSize () );
//        LoggerUtil.debug ( "THREAD - SIZE CODA :" + executor.getQueue ().size () );
//        LoggerUtil.debug ( "THREAD - ATTIVI :" + executor.getActiveCount ());
//        LoggerUtil.debug ( "THREAD - COMPLETATI :" + executor.getCompletedTaskCount ());
//        LoggerUtil.debug ( "THREAD - SIMULTANEI :" + executor.getLargestPoolSize ());
//        LoggerUtil.debug ( "THREAD - MASSIMI :" + executor.getMaximumPoolSize ());
    }

    public static Singleton getInstance () {
        if ( instance == null ) {
            synchronized ( Singleton.class ) {
                if ( instance == null ) {
                    instance = new Singleton ();
                }
            }
        }
        return instance;
    }
}
