/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.application;

import java.io.StringReader;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.NamingException;
import javax.sql.rowset.serial.SerialException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.common.util.CollectionUtils;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.xml.sax.SAXException;

import com.thoughtworks.xstream.XStream;

import it.csi.mdp.core.business.dto.FlussoRiversamento;
import it.csi.mdp.generatedvo.flussoriversamento.CtDatiSingoliPagamenti;
import it.csi.mdp.generatedvo.flussoriversamento.CtFlussoRiversamento;
import it.csi.mdp.mdpetl.dto.DatiRichiesta;
import it.csi.mdp.mdpetl.dto.IuvOttico;
import it.csi.mdp.mdpetl.dto.LoggingFlusso;
import it.csi.mdp.mdpetl.dto.LoggingFlussoBase;
import it.csi.mdp.mdpetl.integration.util.dao.EstraiApplicationIdDaIuvDAO;
import it.csi.mdp.mdpetl.integration.util.dao.EstraiFlussiRiversamento;
import it.csi.mdp.mdpetl.integration.util.dao.EstraiRPTPerIuvDAO;
import it.csi.mdp.mdpetl.integration.util.dao.FiltroFlussiFlagInvioEnum;
import it.csi.mdp.mdpetl.integration.util.dao.InserisciLoggingFlussoDAO;
import it.csi.mdp.mdpetl.integration.util.dao.ModificaStatoInvioFlussiDAO;
import it.csi.mdp.mdpetl.integration.util.dao.StatoInvioFlussoEnum;
import it.csi.mdp.mdpetl.util.Constants;
import it.csi.mdp.mdpetl.util.LogUtil;
import it.csi.mdp.mdpnodospcclient.integration.service.flussiesb.DatiAggiuntiviType;
import it.csi.mdp.mdpnodospcclient.integration.service.flussiesb.DatiAggiuntiviType.ElencoCodiciVersamento;
import it.csi.mdp.mdpnodospcclient.integration.service.flussiesb.ResponseType;
import it.csi.mdp.mdpnodospcclient.integration.service.flussiesb.ServiziRendicontazione;
import it.csi.mdp.mdpnodospcclient.integration.service.flussiesb.TestataFlussoRendicontazioneType;
import it.csi.mdp.mdpnodospcclient.integration.service.flussiesb.TrasmettiFlussoRendicontazioneRequestType;

public class InoltroFlussiRendicontazione {
    static LogUtil log = new LogUtil(InoltroFlussiRendicontazione.class);

    private XStream xs = new XStream();

    public void inoltraFlussi(
        String urlEndpointServizio, byte key[]) throws SerialException,
    SQLException, NamingException, Exception {

        List<FlussoRiversamento> elencoFlussi = reperisciFlussiPerPArametri();
        log.info("inoltraFlussi", "ELENCO FLUSSI BASE ESTRATTI" + elencoFlussi.size());

        if (!CollectionUtils.isEmpty(elencoFlussi)) {

            ServiziRendicontazione iServizio = inizializzaServizio(urlEndpointServizio);

            Unmarshaller unmarshallerFlusso = inizializzaUnMarshallerFlusso();


            for (FlussoRiversamento singoloFlusso : elencoFlussi) {
                List<String> elencoIuvOtticiNonTrovati = new ArrayList<String>();

                LoggingFlusso logFlusso = new LoggingFlussoBase();
                log.info("inoltraFlussi", "\n\nINIZIO ELABORAZIONE FLUSSO BASE" + singoloFlusso.getIdentificativoistitutomittente());
                try {
                    logFlusso.setDataOraInvio(new Timestamp(System.currentTimeMillis()));
                    logFlusso.setIdFlusso(singoloFlusso.getIdentificativoflusso());
                    logFlusso.setIstitutoMittente(singoloFlusso.getDenominazionemittente());

                    Set<String> distictCodiciVersamento = new HashSet<String>();
                    TrasmettiFlussoRendicontazioneRequestType trasmetti = new TrasmettiFlussoRendicontazioneRequestType();
                    TestataFlussoRendicontazioneType testata = new TestataFlussoRendicontazioneType();
                    trasmetti.setTestata(testata);
                    testata.setIdentificativoFlusso(singoloFlusso.getIdentificativoflusso());
                    testata.setIdMessaggio(""+System.currentTimeMillis());
                    testata.setIdPSP(singoloFlusso.getIdentificativoistitutomittente());
                    testata.setCFEnteCreditore(singoloFlusso.getIdentificativoistitutoricevente());
                    
                    logFlusso.setIdMessaggio ( testata.getIdMessaggio () );
                    CtFlussoRiversamento flusso = (CtFlussoRiversamento) unmarshallerFlusso.unmarshal(new StringReader(singoloFlusso.getXmlflusso()));

                    DatiAggiuntiviType datiAggiuntivi = new DatiAggiuntiviType();

                    for (CtDatiSingoliPagamenti singoloPagamento : flusso.getDatiSingoliPagamenti()) {
                        if (singoloPagamento.getIdentificativoUnivocoVersamento().length() > 17) {
                            //AO soluzione workaround per codice_Versamento estratto dagli iuv di modello1
                            //andrebbe fatto su auxDigit 3 ma e' un dato di configurazione
                            try {
                                //se il carattere al tredicesimo posto e' un numero si tratta del nuovo formato
                                Integer.parseInt(singoloPagamento.getIdentificativoUnivocoVersamento().substring(13, 14));
                                distictCodiciVersamento.add(singoloPagamento.getIdentificativoUnivocoVersamento().substring(15, 19));
                            } catch (NumberFormatException e) {
                                distictCodiciVersamento.add(singoloPagamento.getIdentificativoUnivocoVersamento().substring(13, 17));
                            }

                        } else {
                            if ( singoloPagamento.getIdentificativoUnivocoVersamento ().startsWith ( "RF" ) ) {
                                DatiRichiesta dati = new EstraiRPTPerIuvDAO ( singoloPagamento.getIdentificativoUnivocoVersamento () ).executeQuery ();
                                if ( null != dati ) {
                                    distictCodiciVersamento.add ( "0000" );
                                } else {
                                    elencoIuvOtticiNonTrovati.add ( singoloPagamento.getIdentificativoUnivocoVersamento () );
                                }
                            } else {
                                IuvOttico iuvDto = new EstraiApplicationIdDaIuvDAO ( singoloPagamento.getIdentificativoUnivocoVersamento () ).executeQuery ();
                                if ( null != iuvDto ) {
                                    distictCodiciVersamento.add ( iuvDto.getCodiceVersamento () );
                                } else {
                                    elencoIuvOtticiNonTrovati.add ( singoloPagamento.getIdentificativoUnivocoVersamento () );
                                }
                            }
                        }
                    }

                    ElencoCodiciVersamento elencoCodiciVersamento = new ElencoCodiciVersamento();
                    elencoCodiciVersamento.getCodiceVersamento().addAll(distictCodiciVersamento);
                    datiAggiuntivi.setElencoCodiciVersamento(elencoCodiciVersamento);
                    trasmetti.setTestata(testata);
                    trasmetti.setDatiAggiuntivi(datiAggiuntivi);
                    trasmetti.setFlussoRiversamento(singoloFlusso.getXmlflusso().getBytes());
                    if ( null == distictCodiciVersamento || distictCodiciVersamento.isEmpty () ) {
                        String msg = "ATTENZIONE! NON SONO STATI TROVATI CODICI VERSAMENTO PER IL FLUSSO " + singoloFlusso.getIdentificativoflusso();
                        log.warn("inoltraFlussi", msg);
                        logFlusso.setEsito(Constants.ESITO_KO);
                        logFlusso.setErrori(msg);
                        new ModificaStatoInvioFlussiDAO ( singoloFlusso.getId (), StatoInvioFlussoEnum.NESSUNA_OPERAZIONE.getCodStato (),
                            FiltroFlussiFlagInvioEnum.FLUSSO_BASE ).executeUpdate ();
                        logFlusso.setWarning ( "Nessun IUV trovato, lo stato del flusso: " + trasmetti.getTestata ().getIdentificativoFlusso () + " viene impostato a 0." );
                    } else {
                        ResponseType res = iServizio.trasmettiFlussoRendicontazione(trasmetti);
                        Thread.sleep(10000);
                        //log.info("inoltraFlussi", "RISPOSTA DALL'ESB: " + xs.toXML(res))
                        if (Constants.ESITO_OK.equalsIgnoreCase(res.getResult().getMessaggio())) {

                            //Lorenzo: se l'invio e' avvenuto correttamente aggiorno il relativo attributo a INVIATO
                            new ModificaStatoInvioFlussiDAO(singoloFlusso.getId(), StatoInvioFlussoEnum.INVIATO.getCodStato(), FiltroFlussiFlagInvioEnum.FLUSSO_BASE).executeUpdate();

                            logFlusso.setEsito(Constants.ESITO_OK);
                        }

                        else {
                            logFlusso.setEsito(Constants.ESITO_KO);

                            //Lorenzo: se l'invio e' avvenuto in modo erroneo aggiorno il relativo attributo a NON_INVIATO
                            new ModificaStatoInvioFlussiDAO(singoloFlusso.getId(), StatoInvioFlussoEnum.NON_INVIATO.getCodStato(), FiltroFlussiFlagInvioEnum.FLUSSO_BASE).executeUpdate();

                            logFlusso.setWarning(res.getResult().getMessaggio());
                        }
                    }
                }catch (Exception e) {
                    log.error("inoltraFlussi", "ERRORE", e);

                    //Lorenzo: se l'invio e' avvenuto in modo erroneo aggiorno il relativo attributo a NON_INVIATO
//                    MDPNEW- 162 in presenza di eccezione per fault non gestito i flussi non devo cambiare 
//                    "stato_invio_flusso_base" che deve permanere uguale a 1. In questo modo l'invio del flussi puo' essere riprocessato senza  trattamento dati manuale.
//                    new ModificaStatoInvioFlussiDAO(singoloFlusso.getId(), StatoInvioFlussoEnum.NON_INVIATO.getCodStato(), FiltroFlussiFlagInvioEnum.FLUSSO_BASE).executeUpdate()

                    logFlusso.setErrori(StringUtils.substring(e.getMessage(), 0, 255));
                } finally {
                    if (!CollectionUtils.isEmpty(elencoIuvOtticiNonTrovati)) {
                        logFlusso.setWarning(StringUtils.substring("IUV NON TROVATI: " + xs.toXML(elencoIuvOtticiNonTrovati), 0, 255));
                    }
                    new InserisciLoggingFlussoDAO(logFlusso).executeUpdate();
                }
            }

        }

    }

    private ServiziRendicontazione inizializzaServizio(
        String urlEndpointServizio) {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.getInInterceptors().add(new LoggingInInterceptor());
        factory.getOutInterceptors().add(new LoggingOutInterceptor());
        factory.setServiceClass(ServiziRendicontazione.class);
        factory.setAddress(urlEndpointServizio);
        ServiziRendicontazione iServizio = (ServiziRendicontazione) factory.create();
        return iServizio;
    }

    private List<FlussoRiversamento> reperisciFlussiPerPArametri() throws SerialException, SQLException, NamingException,
    Exception {

        return new EstraiFlussiRiversamento(FiltroFlussiFlagInvioEnum.FLUSSO_BASE)
                        .executeQuery();
    }


    private Unmarshaller inizializzaUnMarshallerFlusso() throws JAXBException, SAXException {
        JAXBContext contextFlussoJAXB = JAXBContext.newInstance(CtFlussoRiversamento.class);
        Unmarshaller unmarshallerFlusso = contextFlussoJAXB.createUnmarshaller();
        unmarshallerFlusso.setEventHandler(
            new ValidationEventHandler() {

                @Override
                public boolean handleEvent(ValidationEvent event ) {
                    //System.out.println("ERRORE DI VALIDAZIONE " + event.getMessage() + " " + event.getSeverity() + " " + event.getLinkedException())
                    return true;
                }
            });
        SchemaFactory sf = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        Source so = new StreamSource(this.getClass().getResourceAsStream("/FlussoRiversamento_1_0_4.xsd"));
        Schema s = sf.newSchema(so);

        unmarshallerFlusso.setSchema(s);
        return unmarshallerFlusso;
    }

}
