/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.manager;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import it.csi.epay.epayservices.integration.db.entities.EpayDStatoPagamento;
import it.csi.epay.epayservices.integration.db.entities.EpayTAnagrafica;
import it.csi.epay.epayservices.integration.db.entities.EpayTDatiSingolaRevoca;
import it.csi.epay.epayservices.integration.db.entities.EpayTPagamento;
import it.csi.epay.epayservices.integration.db.entities.EpayTPagamentoComponenti;
import it.csi.epay.epayservices.integration.db.entities.EpayTPagamentoRiferimenti;
import it.csi.epay.epayservices.integration.db.entities.EpayTPagamentoSecondario;
import it.csi.epay.epayservices.integration.db.entities.EpayTPagamentoSecondarioComponenti;
import it.csi.epay.epayservices.integration.db.entities.EpayTRegistroVersamenti;
import it.csi.epay.epayservices.integration.db.entities.EpayTRr;
import it.csi.epay.epayservices.integration.db.entities.EpayTRt;
import it.csi.epay.epayservices.integration.db.entities.EpayTTipoPagamento;
import it.csi.epay.epayservices.interfaces.exception.MoreResultException;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.Anagrafica;
import it.csi.epay.epayservices.model.CodiceAvviso;
import it.csi.epay.epayservices.model.Configurazione;
import it.csi.epay.epayservices.model.DatiSingolaRevoca;
import it.csi.epay.epayservices.model.Ente;
import it.csi.epay.epayservices.model.EsitiRicevuti;
import it.csi.epay.epayservices.model.JobContext;
import it.csi.epay.epayservices.model.Pagamento;
import it.csi.epay.epayservices.model.PagamentoComponenti;
import it.csi.epay.epayservices.model.PagamentoInvioPEC;
import it.csi.epay.epayservices.model.PagamentoRiferimenti;
import it.csi.epay.epayservices.model.PagamentoSecondario;
import it.csi.epay.epayservices.model.PagamentoSecondarioComponenti;
import it.csi.epay.epayservices.model.ParamNameXPdf;
import it.csi.epay.epayservices.model.RegistroVersamenti;
import it.csi.epay.epayservices.model.RevocaPerWso2;
import it.csi.epay.epayservices.model.Rr;
import it.csi.epay.epayservices.model.Rt;
import it.csi.epay.epayservices.model.StatistichePagamenti;
import it.csi.epay.epayservices.model.StatoPagamento;
import it.csi.epay.epayservices.model.TipoPagamento;
import it.csi.epay.epayservices.utilities.LogUtil;

@Stateless(name="PagamentoManager", mappedName = "PagamentoManager")
public class PagamentoManager extends _Manager {

    private static final String CONFIG_MAX_PAGAMENTI_INVIO_NOTIFY = "CONFIG_MAX_PAGAMENTI_INVIO_NOTIFY";

    private static final String COD_ENTE_DEFALUT = "PagoPa";

    private static final String CONFIG_MAX_MESI_INVIO_NOTIFY = "CONFIG_MAX_MESI_INVIO_NOTIFY";

    // 2020-01-15 10:33 - @FF: RIMOSSO LOCK PER DEBUG PROBLEMA CODE WSO2 - TODO APPROFONDIRE MOTIVAZIONE LOG ITFP
    private final static boolean DO_LOCK_PAGAMENTO_ON_UPDATE_STATO = true;

    @PersistenceContext
    private EntityManager entityManager;

    @EJB
    private EsitiRicevutiManager esitiRicevutiManager;

    @EJB
    private RtManager rtManager;

    private static LogUtil log = new LogUtil(PagamentoManager.class);

    @EJB
    private PagamentoComponentiManager pagamentoComponentiManager;

    @EJB
    private RegistroVersamentiManager registroVersamentiManager;

    @EJB
    private PagamentoRiferimentiManager pagamentoRiferimentiManager;

    @EJB
    private MailManager mailManager;

    @EJB
    private ConfigurazioneManager configurazioneManager;

    public Pagamento getPagamento(Long id) {
        try {
            EpayTPagamento tPagamento = entityManager.find(EpayTPagamento.class, id);
            Pagamento pagamento = mappaPagamentoEsteso(tPagamento);
            return pagamento;
        } catch (NoResultException e) {
            return null;
        }
    }

    public Pagamento getPagamento(String iuv) {
        try {
            TypedQuery<EpayTPagamento> query = entityManager.createNamedQuery("EpayTPagamento.findByIuvOttimizzata", EpayTPagamento.class);
            query.setParameter("iuv", iuv);
            EpayTPagamento tPagamento = query.getSingleResult();
            Pagamento pagamento = mappaPagamentoEsteso(tPagamento);
            return pagamento;
        } catch (NoResultException e) {
            return null;
        }
    }

    // finire di ottimizzare o sistemare meglio.
    public Pagamento getPagamentoOttimizzata(String iuv) {
        try {
            TypedQuery<EpayTPagamento> query = entityManager.createNamedQuery("EpayTPagamento.findByIuvOttimizzata", EpayTPagamento.class);
            query.setParameter("iuv", iuv);
            EpayTPagamento tPagamento = query.getSingleResult();
            Pagamento pagamento = mappaPagamentoBase(tPagamento);
            return pagamento;
        } catch (NoResultException e) {
            return null;
        }
    }

    public Pagamento getPagamentoByIdRegistroVersamento(Long idRegistro) {
        try {
            EpayTRegistroVersamenti tRegistroVersamenti = entityManager.find(EpayTRegistroVersamenti.class, idRegistro);
            Pagamento pagamento = mappaPagamentoEsteso(tRegistroVersamenti.getEpayTPagamento());
            return pagamento;
        } catch (NoResultException e) {
            return null;
        }
    }

    public Pagamento getPagamentoAttivo(String iuv) {
        try {
            TypedQuery<EpayTPagamento> query = entityManager.createNamedQuery("EpayTPagamento.findByIuvAndActive", EpayTPagamento.class);
            query.setParameter("iuv", iuv);
            EpayTPagamento tPagamento = query.getSingleResult();
            Pagamento pagamento = mappaPagamentoEsteso(tPagamento);
            return pagamento;
        } catch (NoResultException e) {
            return null;
        }
    }

    public Pagamento getPagamentoAttivoAndPagabile(String iuv) {
        try {
            TypedQuery<EpayTPagamento> query = entityManager.createNamedQuery("EpayTPagamento.findByIuvAndActiveAndPagabile", EpayTPagamento.class);
            query.setParameter("iuv", iuv);
            EpayTPagamento tPagamento = query.getSingleResult();
            Pagamento pagamento = mappaPagamentoEsteso(tPagamento);
            return pagamento;
        } catch (NoResultException e) {
            return null;
        }
    }

    public Pagamento getPagamentoPerCodRifEnte(final String codiceFiscaleEnte, final String codiceVersamento, final String codicePagamentoRifEnte) throws NoDataException, MoreResultException {
        try {
            TypedQuery<EpayTPagamento> query = entityManager.createNamedQuery("EpayTPagamento.findByCodicePagamentoRifEnte", EpayTPagamento.class);
            query.setParameter ( "codicePagamentoRifEnte", StringUtils.trim ( codicePagamentoRifEnte ) );
            query.setParameter("codiceVersamento", codiceVersamento);
            query.setParameter("codiceFiscale", codiceFiscaleEnte);
            EpayTPagamento tPagamento = query.getSingleResult();
            return mappaPagamentoEsteso ( tPagamento );
        } catch (NonUniqueResultException e ) {
            throw new MoreResultException();
        } catch (NoResultException e) {
            throw new NoDataException();
        }
    }
    
    public Long getIdPagamentoPerCodRifEnte(final String codiceFiscaleEnte, final String codiceVersamento, final String codicePagamentoRifEnte) throws NoDataException, MoreResultException {
        try {
            TypedQuery<Long> query = entityManager.createNamedQuery("EpayTPagamento.findIdByCodicePagamentoRifEnte", Long.class);
            query.setParameter ( "codicePagamentoRifEnte", StringUtils.trim ( codicePagamentoRifEnte ) );
            query.setParameter("codiceVersamento", codiceVersamento);
            query.setParameter("codiceFiscale", codiceFiscaleEnte);
            return query.getSingleResult();
        } catch (NonUniqueResultException e ) {
            throw new MoreResultException();
        } catch (NoResultException e) {
            throw new NoDataException();
        }
    }
    
    public boolean existPagamentoPerCodRifEnte(final String codiceFiscaleEnte, final String codiceVersamento, final String codicePagamentoRifEnte) throws NoDataException, MoreResultException {
        try {
            TypedQuery<Integer> query = entityManager.createNamedQuery("EpayTPagamento.existsPagamentoCodicePagamentoRifEnte", Integer.class);
            query.setParameter ( "codicePagamentoRifEnte", StringUtils.trim ( codicePagamentoRifEnte ) );
            query.setParameter("codiceVersamento", codiceVersamento);
            query.setParameter("codiceFiscale", codiceFiscaleEnte);
            Integer result=  query.getSingleResult();
            return result != null;
        } catch (NonUniqueResultException e ) {
            throw new MoreResultException();
        } catch (NoResultException e) {
            return false;
        }
    }

    public EpayTPagamento getPagamentoPerCovERifEnte ( final String codiceFiscaleEnte, final String codiceVersamento, final String codicePagamentoRifEnte )
                    throws NoDataException, MoreResultException {
        try {
            TypedQuery<EpayTPagamento> query = entityManager.createNamedQuery ( "EpayTPagamento.findByCodicePagamentoRifEnte", EpayTPagamento.class );
            query.setParameter ( "codicePagamentoRifEnte", StringUtils.trim ( codicePagamentoRifEnte ) );
            query.setParameter ( "codiceVersamento", codiceVersamento );
            query.setParameter ( "codiceFiscale", codiceFiscaleEnte );
            return query.getSingleResult ();
        } catch ( NonUniqueResultException e ) {
            throw new MoreResultException ();
        } catch ( NoResultException e ) {
            throw new NoDataException ();
        }
    }

    public EpayTPagamento getPagamentoPerCFEnteECovEIuv ( final String codiceFiscaleEnte, final String codiceVersamento, final String iuv )
                    throws NoDataException, MoreResultException {
        try {
            TypedQuery<EpayTPagamento> query = entityManager.createNamedQuery ( "EpayTPagamento.findByIuvAndCovAndCFEnte", EpayTPagamento.class );
            query.setParameter ( "iuv", StringUtils.trim ( iuv ) );
            query.setParameter ( "codiceVersamento", codiceVersamento );
            query.setParameter ( "codiceFiscale", codiceFiscaleEnte );
            return query.getSingleResult ();
        } catch ( NonUniqueResultException e ) {
            throw new MoreResultException ();
        } catch ( NoResultException e ) {
            throw new NoDataException ();
        }
    }

	public EpayTPagamento getPagamentoPerCovERifEnteForStato ( String codiceFiscaleEnte, String codicePagamentoRifEnte )
					throws MoreResultException, NoDataException {
		try {
			TypedQuery<EpayTPagamento> query = entityManager.createNamedQuery ( "EpayTPagamento.findByCodicePagamentoRifEnteForStato", EpayTPagamento.class );
			query.setParameter ( "codicePagamentoRifEnte", StringUtils.trim ( codicePagamentoRifEnte ) );
			query.setParameter ( "codiceFiscale", codiceFiscaleEnte );
			return query.getSingleResult ();
		} catch ( NonUniqueResultException e ) {
			throw new MoreResultException ();
		} catch ( NoResultException e ) {
			throw new NoDataException ();
		}
	}

    private List<Pagamento> getPagamentiPerCF(String codiceFiscale, String nameQuery) {
        try {
            TypedQuery<EpayTPagamento> query = entityManager.createNamedQuery("EpayTPagamento." + nameQuery, EpayTPagamento.class);
            query.setParameter("codiceFiscale", codiceFiscale);
            List<EpayTPagamento> tPagamenti = query.getResultList();
            List<Pagamento> pagamentiEffettuati = new ArrayList<>();
            for (EpayTPagamento tPagamento : tPagamenti) {
                pagamentiEffettuati.add(mappaPagamentoEsteso(tPagamento));
            }
            return pagamentiEffettuati;
        } catch (NoResultException e) {
            return null;
        }
    }

    public Pagamento getPagamentoByIuvAndIdentificativoDominio ( String iuvNumeroAvviso, String identificativoDominio ) {
        try {
            TypedQuery<EpayTPagamento> query = entityManager.createNamedQuery ( "EpayTPagamento.findByIuvAndIdentificativoDominio", EpayTPagamento.class );
            query.setParameter ( "iuvNumeroAvviso", iuvNumeroAvviso );
            query.setParameter ( "identificativoDominio", identificativoDominio );
            EpayTPagamento tPagamento = query.getSingleResult ();
            Pagamento pagamento=  map(tPagamento, Pagamento.class);
            return pagamento;
        } catch ( NoResultException e ) {
            return null;
        }
    }

    public List<Pagamento> getPagamentiEffettuatiPerCF(String codiceFiscale) {
        return getPagamentiPerCF(codiceFiscale, "findEffettuatiByCF");
    }

    public List<Pagamento> getPagamentiPerCFAndActive(String codiceFiscale) {
        return getPagamentiPerCF(codiceFiscale, "findByCFAndActive");
    }

    public List<Pagamento> getPagamentiPerCFAndActiveAndNotSpontaneous(String codiceFiscale) {
        return getPagamentiPerCF(codiceFiscale, "findByCFAndActiveAndNotSpontaneous");
    }

    public List<Pagamento> getPagamentiAllPerCF(String codiceFiscale) {
        return getPagamentiPerCF(codiceFiscale, "findByCF");
    }

    public List<RevocaPerWso2> getListaPerInvioRevoche(Ente ente) throws NoDataException {
        try {
            TypedQuery<EpayTRr> queryRevoche = entityManager.createNamedQuery("EpayTRr.findToSend", EpayTRr.class);
            queryRevoche.setParameter("idDominio",ente.getCodiceFiscale ());

            List<EpayTRr> revoche = queryRevoche.getResultList();
            List<RevocaPerWso2> revocheWso2 = new ArrayList<>();

            for (EpayTRr revoca : revoche) {
                TypedQuery<EpayTDatiSingolaRevoca> queryDatiSingolaRevoca = entityManager.createNamedQuery("EpayTDatiSingolaRevoca.elencoDettagliPerInvioRevoche", EpayTDatiSingolaRevoca.class);
                queryDatiSingolaRevoca.setParameter("idRr",revoca.getIdRr ());

                List<EpayTDatiSingolaRevoca> dettaglio = queryDatiSingolaRevoca.getResultList();

                revocheWso2.add(mappaRevoca(revoca,dettaglio));
            }
            return revocheWso2;
        } catch (NoResultException nre) {
            throw new NoDataException("Nessun pagamento da inviare", nre.getCause());
        }
    }

    public List<Pagamento> getPagamentiValidiPerEnte(Long idEnte) throws NoDataException {
        try {

            TypedQuery<EpayTPagamento> query = entityManager.createNamedQuery("EpayTPagamento.elencoPagamentiValidiPerEnte", EpayTPagamento.class);
            query.setParameter("idEnte", idEnte);
            query.setParameter("limiteData", estraiLimiteDataPerNotify());
            query.setMaxResults(estraiNumeroMassimoRecord());


            List<EpayTPagamento> tPagamenti = query.getResultList();
            List<Pagamento> pagamentiEffettuati = new ArrayList<>();
            for (EpayTPagamento tPagamento : tPagamenti) {
                pagamentiEffettuati.add( mappaPagamentoValidoPerEnte(tPagamento));
            }
            return pagamentiEffettuati;
        } catch (NoResultException e) {
            throw new NoDataException("Nessun pagamento valido per ente  ",e.getCause());
        }
    }
    

    public List<Pagamento> getPagamentiValidiPerTipoPagamento(Long idTipoPagamento) throws NoDataException {
        try {

            TypedQuery<EpayTPagamento> query = entityManager.createNamedQuery("EpayTPagamento.elencoPagamentiValidiPerTipoPagamento", EpayTPagamento.class);
            query.setParameter("idTipoPagamento", idTipoPagamento);
            query.setParameter("limiteData", estraiLimiteDataPerNotify());
            query.setMaxResults(estraiNumeroMassimoRecord());


            List<EpayTPagamento> tPagamenti = query.getResultList();
            List<Pagamento> pagamentiEffettuati = new ArrayList<>();
            for (EpayTPagamento tPagamento : tPagamenti) {
                pagamentiEffettuati.add( mappaPagamentoValidoPerEnte(tPagamento));
            }
            return pagamentiEffettuati;
        } catch (NoResultException e) {
            throw new NoDataException("Nessun pagamento valido per tipoPagamento  ",e.getCause());
        }
    }

    
    public List<Pagamento> getPagamentiCittaFacileValidiPerTipoPagamento(Long idTipoPagamento) throws NoDataException {
        try {

            TypedQuery<EpayTPagamento> query = entityManager.createNamedQuery("EpayTPagamento.elencoPagamentiValidiPerTipoPagamentoCittafacile", EpayTPagamento.class);
            query.setParameter("idTipoPagamento", idTipoPagamento);
            query.setParameter("limiteData", estraiLimiteDataPerNotify());
            query.setMaxResults(estraiNumeroMassimoRecord());


            List<EpayTPagamento> tPagamenti = query.getResultList();
            List<Pagamento> pagamentiEffettuati = new ArrayList<>();
            for (EpayTPagamento tPagamento : tPagamenti) {
                pagamentiEffettuati.add( mappaPagamentoValidoPerEnte(tPagamento));
            }
            return pagamentiEffettuati;
        } catch (NoResultException e) {
            throw new NoDataException("Nessun pagamento valido per tipoPagamento  ",e.getCause());
        }
    }
    private Timestamp estraiLimiteDataPerNotify()  {

        Configurazione config= configurazioneManager.getConfigurazione(CONFIG_MAX_MESI_INVIO_NOTIFY, COD_ENTE_DEFALUT);
        int mesi= 0;
        if (null!= config && null !=config.getValore())
        {
            mesi= Integer.parseInt(config.getValore());
        }
        Calendar cal= Calendar.getInstance();
        cal.add(Calendar.MONTH, -mesi);
        Timestamp data= new Timestamp(cal.getTimeInMillis());
        return data;


    }

    private int estraiNumeroMassimoRecord() throws NoDataException {
        int numMaxPagamenti= 0;
        try
        {
            Configurazione config= configurazioneManager.getConfigurazione(CONFIG_MAX_PAGAMENTI_INVIO_NOTIFY, COD_ENTE_DEFALUT);

            if (null!= config && null !=config.getValore())
            {
                numMaxPagamenti= Integer.parseInt(config.getValore());
            }
        }
        catch (Exception e) {
            throw new NoDataException("Nessun valore valido per numero massimo di pagamenti",e.getCause());
        }
        return numMaxPagamenti;
    }

    public void aggiornaRevocaInviataWso2(RevocaPerWso2 rr) {
        EpayTRr revoca = entityManager.find ( EpayTRr.class, rr.getRr ().getIdRr ().intValue () );
        revoca.setDataOraInvioWso2(new Timestamp(new Date().getTime ()));
        entityManager.flush();
    }

    public List<Pagamento> getListaPerInvioPagamenti(TipoPagamento tipoPagamento, Boolean pagamentoSpontaneo) throws NoDataException {
        String methodName = "getListaPerInvioPagamenti";
        try {
            TypedQuery<EpayTPagamento> query = entityManager.createNamedQuery("EpayTPagamento.elencoPerInvioPagamenti", EpayTPagamento.class);
            query.setParameter("idTipoPagamento", tipoPagamento.getIdTipoPagamento());
            query.setParameter("pagamentoSpontaneo", pagamentoSpontaneo);

            query.setMaxResults ( 50 );
            List<EpayTPagamento> tPagamenti = query.getResultList();

            List<Pagamento> pagamenti = new ArrayList<>();
            for (EpayTPagamento tPagamento : tPagamenti) {

                log.debug ( methodName, "ENTITY" );
                log.debug ( methodName, "IUV:" + tPagamento.getIuvNumeroAvviso ());

                Pagamento pag = mappaPagamentoEsteso(tPagamento);
                pagamenti.add(pag);
                log.debug ( methodName, "DTO" );
                log.debug ( methodName, "IUV:" + pag.getIuv () + " IuvRegistroVersamenti:" + pag.getIuvRegistroVersamenti ());
            }

            try {
                aggiornaImportiPagamenti(pagamenti);
            } catch(Exception e) {
                log.error ( methodName,"AGGIORNAMENTO FALLITO",e);
            }


            return pagamenti;
        } catch (NoResultException nre) {
            throw new NoDataException("Nessun pagamento da inviare", nre.getCause());
        }
    }

    private void aggiornaImportiPagamenti ( List<Pagamento> tPagamenti )
                    throws NoDataException, ParserConfigurationException, SAXException, XPathExpressionException, IOException {
        if ( tPagamenti == null ) {
            return;
        }

        String methodName = "aggiornaImportiPagamenti";

        log.debug ( methodName, "aggiorno import per  " + tPagamenti.size () + " pagamenti" );

        if ( tPagamenti != null ) {
            for ( Pagamento pagamento: tPagamenti ) {

                Long idPagamento = pagamento.getIdPagamento ();

                log.debug ( methodName, "ID PAGAMENTO " + idPagamento );

                try {
                    EsitiRicevuti esiti = esitiRicevutiManager.ricercaEsitiRicevutiByIdPagamento ( idPagamento );

                    if ( esiti != null ) {
                        log.debug ( "aggiornaImportiPagamenti", "ESITI PER ID PAGAMENTO:" + esiti.toString () );

                        pagamento.setImporto ( esiti.getImporto () );
                        continue;
                    } else {
                        log.error ( "aggiornaImportiPagamenti", "NESSUN ESITO TROVATO" );
                    }
                } catch ( NoDataException e ) {
                    log.error ( methodName, "NESSUN ESITO TROVATO" );
                } catch ( Exception e ) {
                    log.error ( methodName, "Errore nella ricerca esiti", e );
                }

                try {
                    // CSI_PAG-350 START OLD
                    // Rt rt = rtManager.ricercaRtByIuv (pagamento.getIuv ());
                    // CSI_PAG-350 END OLD

                    // CSI_PAG-350 START
                    // get stato pagamento
                    RegistroVersamenti registro
                    = registroVersamentiManager.ricercaUltimaRegistrazioneByIdStato ( pagamento.getIdPagamento (), pagamento.getIdStatoCorrente () );

                    if ( registro != null ) {
                        log.debug ( methodName, "trovato registro versamento " + registro.getIdRegistro () );

                        Rt rt = rtManager.ricercaRtByIdRegistro ( registro.getIdRegistro () );

                        if ( rt != null ) {
                            log.debug ( methodName, "ESITI PER ID IUV:" + rt.toString () );

                            byte [] xmlArr = rt.getRtXml ();

                            if ( xmlArr != null ) {
                                log.debug ( methodName, "RICERCA XML" );
                                Document item = DocumentBuilderFactory.newInstance ().newDocumentBuilder ().parse ( new ByteArrayInputStream ( xmlArr ) );

                                if ( item != null ) {

                                    String importoStr = getNodeImportoPagato ( item );

                                    if ( importoStr != null ) {
                                        log.debug ( methodName, "RICERCA XML VALIDA:" + item.toString () );
                                        log.debug ( methodName, "IMPORTO TROVATLO:" + importoStr );
                                        pagamento.setImporto ( new BigDecimal ( importoStr ) );
                                    }
                                } else {
                                    log.debug ( methodName, "RICERCA XML FALLITA" );
                                }
                            }
                        } else {
                            log.error ( methodName, "NESSUNA RT TROVATA" );
                        }
                    } else {
                        log.error ( methodName, "NESSUN REGISTRO VERSAMENTO TROVATO" );
                    }
                    // CSI_PAG-350 END

                } catch ( NoDataException e ) {
                    log.error ( methodName, "NESSUNA RT TROVATA" );
                } catch ( Exception e ) {
                    log.error ( methodName, "Errore nella ricerca RT", e );
                }
            }
        }
    }

    private String getNodeImportoPagato(Document doc) throws XPathExpressionException {
        if (doc == null) {
            return null;
        }

        XPath xPath = XPathFactory.newInstance().newXPath();

        XPathExpression expr = xPath.compile("/RT/datiPagamento/importoTotalePagato");

        log.debug ( "aggiornaImportiPagamenti", "RICERCA XPATH");

        Element element = (Element) expr.evaluate(doc, XPathConstants.NODE);
        return element.getTextContent();
    }

    public void aggiornaCausale(Long id, String causale) {
        EpayTPagamento tPagamento = entityManager.find(EpayTPagamento.class, id);
        tPagamento.setCausale ( StringUtils.substring ( causale, 0, 140 ) );
    }

    public void aggiornaNote(Long id, String note) {
        EpayTPagamento tPagamento = entityManager.find(EpayTPagamento.class, id);
        tPagamento.setNote(note);
    }

    public void aggiornaConsensoPrivacy(Long id, boolean consensoPrivacy) {
        EpayTPagamento tPagamento = entityManager.find(EpayTPagamento.class, id);
        tPagamento.setConsensoPrivacy(consensoPrivacy);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void aggiornaStato(Long id, Integer idStato) {
        String methodName = "aggiornaStato";
        log.debugStart ( methodName );
        if ( log.isDebugEnabled () ) {
            log.debug ( methodName, "Richiesto aggiornamento di stato del pagamento " + id + " allo stato " + idStato );
        }

        try {
            // 2020-01-15 10:33 - @FF: RIMOSSO LOCK PER DEBUG PROBLEMA CODE WSO2 - TODO APPROFONDIRE MOTIVAZIONE LOG ITFP
            EpayTPagamento tPagamento;
            if ( DO_LOCK_PAGAMENTO_ON_UPDATE_STATO ) {
                tPagamento = entityManager.find ( EpayTPagamento.class, id, LockModeType.PESSIMISTIC_WRITE );
            } else {
                tPagamento = entityManager.find ( EpayTPagamento.class, id );
            }

            EpayDStatoPagamento stato = tPagamento.getEpayDStatoPagamento ();

            if ( !stato.getModificabile () ) {
                log.warn ( methodName, "Richiesta modifica di pagamento " + id +
                    " in stato attualmente non modificabile. Lo stato attuale e' #" +
                    stato.getIdStato () + " - " + stato.getDescStato () +
                    ", lo stato richiesto sarebbe stato #" + idStato + ". Modifica di stato ANNULLATA" );
                return;
            }

            // se stato new e' "In attesa", lo inserisco solo
            // se lo stato old NON e' "Fallito", "Successo", "Annullato"
            if ( StatoPagamento.IN_ATTESA.getId ().equals ( idStato ) &&
                            ( StatoPagamento.FALLITO.getId ().equals ( stato.getIdStato () ) ||
                                            StatoPagamento.SUCCESSO.getId ().equals ( stato.getIdStato () ) ||
                                            StatoPagamento.ANNULLATO.getId ().equals ( stato.getIdStato () ) ) ) {
                log.warn ( methodName, "Richiesta modifica di pagamento " + id +
                    " in stato non consentito rispetto allo stato attuale. Lo stato attuale e' #" +
                    stato.getIdStato () + " - " + stato.getDescStato () +
                    ", lo stato richiesto sarebbe stato #" + idStato + ". Modifica di stato ANNULLATA" );
                return;
            }

            tPagamento.setEpayDStatoPagamento ( entityManager.find ( EpayDStatoPagamento.class, idStato ) );

            /*
             * Query spquery = entityManager.createNativeQuery("SELECT aggiorna_stato_pagamento(:id_pagamento,:stato_pagamento)");
             * spquery.setParameter("id_pagamento", id); spquery.setParameter("stato_pagamento", idStato); Boolean rc = (Boolean) spquery.getSingleResult();
             */

        } catch ( Throwable t ) {
            log.error ( methodName, "Errore nell'aggiornamento dello stato del pagamento", t );
            throw t;
        } finally {
            log.debugEnd ( methodName );
        }
    }

    public void aggiornaStato(Long id, StatoPagamento stato) {
        aggiornaStato(id, stato.getId());
    }

    public void aggiornaStato(Long id, String  descStato) {
        aggiornaStato(id, StatoPagamento.findByDescr(descStato));
    }

    /*@Deprecated
	public void aggiornaCodiceAvviso(Long id, String auxDigit, String applicationCode, String iuv) {
		EpayTPagamento tPagamento = entityManager.find(EpayTPagamento.class, id);
		if (!StringUtils.isEmpty(auxDigit)) {
			tPagamento.setAuxDigit(auxDigit);
		}
		if (!StringUtils.isEmpty(applicationCode)) {
			tPagamento.setApplicationCode(applicationCode);
		}
		if (!StringUtils.isEmpty(iuv)) {
			tPagamento.setIuvNumeroAvviso(iuv);
		}
	}*/

    public void aggiornaCodiceAvviso(Long idPagamento, CodiceAvviso codiceAvviso) {
        EpayTPagamento tPagamento = entityManager.find(EpayTPagamento.class, idPagamento);
        tPagamento.setAuxDigit(codiceAvviso.getAuxDigit());
        tPagamento.setApplicationCode(codiceAvviso.getApplicationCode());
        tPagamento.setIuvNumeroAvviso(codiceAvviso.getIuv());
        entityManager.flush();
    }

    public void aggiornaDateValidita(Long id, Date inizio, Date fine) {
        EpayTPagamento tPagamento = entityManager.find(EpayTPagamento.class, id);
        tPagamento.setInizioValidita(inizio);
        tPagamento.setFineValidita(fine);
    }

    public Long inserisci(Pagamento pagamento) {
        return inserisciAndRetEntity ( pagamento ).getIdPagamento ();
    }

    //@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public EpayTPagamento inserisciAndRetEntity ( Pagamento pagamento ) {
        String methodName = "inserisciAndRetEntity";
        EpayTPagamento tPagamento = map ( pagamento, EpayTPagamento.class );
        tPagamento.setIuvNumeroAvviso ( pagamento.getIuv () );
        tPagamento.setFlagInviato ( false );

        log.info ( methodName, "START MONITORAGGIO INSERIMENTO PAGAMENTO" );
        log.debug ( methodName, pagamento );
        log.debug ( methodName, ( null != pagamento.getTipoPagamento () ) ? pagamento.getTipoPagamento ().toString () : "null" );
        log.info ( methodName, "END MONITORAGGIO INSERIMENTO PAGAMENTO" );

        tPagamento.setEpayTTipoPagamento ( entityManager.find ( EpayTTipoPagamento.class, pagamento.getTipoPagamento ().getIdTipoPagamento () ) );
        tPagamento.setDataInserimento ( currentTimestamp () );
        tPagamento.setUtenteUltimaModifica ( setUtenteNotEmpty ( pagamento.getUtenteUltimaModifica () ) );

        if ( pagamento.getPagatore () != null ) {
            tPagamento.setEpayTAnagrafica ( entityManager.find ( EpayTAnagrafica.class, pagamento.getPagatore ().getIdAnagrafica () ) );
        }
        tPagamento.setEpayDStatoPagamento ( entityManager.find ( EpayDStatoPagamento.class, pagamento.getIdStatoCorrente () ) );
        tPagamento.setCausale ( StringUtils.substring ( tPagamento.getCausale (), 0, 140 ) );
        //Lollo inserisci qui nuova logica reperimento DSR spontanei
        if ( pagamento.getComponenti () != null && !pagamento.getComponenti ().isEmpty () ) {
            if(tPagamento.getEpayTPagamentoComponentis() == null) {
                tPagamento.setEpayTPagamentoComponentis(new ArrayList<EpayTPagamentoComponenti>());
            }
            for ( PagamentoComponenti componenti: pagamento.getComponenti () ) {
                EpayTPagamentoComponenti pagamentoComponenti = map ( componenti, EpayTPagamentoComponenti.class );
                pagamentoComponenti.setCausale ( StringUtils.substring ( pagamentoComponenti.getCausale (), 0, 140 ) );
                pagamentoComponenti.setUtenteUltimaModifica ( setUtenteNotEmpty ( componenti.getUtenteUltimaModifica () ) );
                tPagamento.addEpayTPagamentoComponenti ( pagamentoComponenti );
            }
        }

        if ( pagamento.getRiferimenti () != null && !pagamento.getRiferimenti ().isEmpty () ) {
            if(tPagamento.getEpayTPagamentoRiferimentis () == null) {
                tPagamento.setEpayTPagamentoRiferimentis(new ArrayList<EpayTPagamentoRiferimenti>());
            }
            for ( PagamentoRiferimenti riferimenti: pagamento.getRiferimenti () ) {
                EpayTPagamentoRiferimenti pagamentoRiferimenti = map ( riferimenti, EpayTPagamentoRiferimenti.class );
                pagamentoRiferimenti.setUtenteUltimaModifica ( setUtenteNotEmpty ( riferimenti.getUtenteUltimaModifica () ) );
                tPagamento.addEpayTPagamentoRiferimenti ( pagamentoRiferimenti );
            }
        }
        
        tPagamento.setRequiresCostUpdate(pagamento.getRequiresCostUpdate());

        entityManager.persist ( tPagamento );
        entityManager.flush();
        return tPagamento;
    }


    public EpayTPagamento inviato(Pagamento pagamento, final Boolean flagInviato) {
        EpayTPagamento tPagamento = entityManager.find(EpayTPagamento.class, pagamento.getIdPagamento());
        tPagamento.setFlagInviato(flagInviato);
        return tPagamento;
    }

    private RevocaPerWso2 mappaRevoca(EpayTRr revIn,List<EpayTDatiSingolaRevoca> reInList) {
        RevocaPerWso2 revOut = new RevocaPerWso2 ();

        List<DatiSingolaRevoca> rrDetail = new ArrayList<>();

        Rr rr = new Rr ();

        rr.setApplicationId ( revIn.getApplicationId () );
        rr.setCodiceContestoPagamento ( revIn.getCodiceContestoPagamento () );
        rr.setCodiceIdentificativoUnivocoAttestante ( revIn.getCodiceIdentificativoUnivocoAttestante () );
        rr.setDataOraMessaggioRevoca ( revIn.getDataOraMessaggioRevoca () );
        rr.setDenominazioneIstitutoAttestante ( revIn.getDenominazioneIstitutoAttestante () );
        rr.setIdDominio (revIn.getIdDominio () );
        rr.setIdentificativoMessaggioRevoca ( revIn.getIdentificativoMessaggioRevoca ());
        rr.setIdRr (revIn.getIdRr ().longValue () );
        rr.setImportoTotaleRevocato ( revIn.getImportoTotaleRevocato ());
        rr.setIuv ( revIn.getIuv ());
        rr.setRrXml ( revIn.getXmlRr () );
        rr.setTipoRevoca ( revIn.getTipoRevoca ()!= null ? "" + revIn.getTipoRevoca().intValue () : null);

        revOut.setRr ( rr );

        for(EpayTDatiSingolaRevoca item:reInList) {
            DatiSingolaRevoca newItem = new DatiSingolaRevoca ();

            newItem.setCausaleRevoca ( item.getCausaleRevoca () );
            newItem.setDatiAggiuntiviRevoca ( item.getDatiAggiuntiviRevoca () );
            newItem.setId ( item.getId () );
            newItem.setIdRr ( item.getIdRr () );
            newItem.setIur ( item.getIur () );
            newItem.setSingoloImportoRevocato ( item.getSingoloImportoRevocato () );

            rrDetail.add ( newItem );
        }

        revOut.setDati ( rrDetail );
        revOut.setRt ( null );
        revOut.setEr ( null );

        return revOut;
    }

    public Pagamento mappaPagamentoEsteso(EpayTPagamento tPagamento) {
        Pagamento pagamento = mappaPagamentoBase(tPagamento);

        if (tPagamento.getEpayTPagamentoComponentis() != null && !tPagamento.getEpayTPagamentoComponentis().isEmpty())  {
            pagamento.setComponenti(mapList(tPagamento.getEpayTPagamentoComponentis(), PagamentoComponenti.class));
        }

        //gestione importi secondari e componenti secondari
        List<PagamentoSecondarioComponenti> componentiSecondariFinale = new ArrayList<> ();
        if ( tPagamento.getEpayTPagamentoSecondarios () != null && !tPagamento.getEpayTPagamentoSecondarios ().isEmpty () ) {
            // per ogni pagamento secondario devo mappare i componenti secondari
            List<Ente> listEnti= new LinkedList<Ente> ();
            for ( EpayTPagamentoSecondario pagamentoSecondario: tPagamento.getEpayTPagamentoSecondarios () ) {
                
                Ente enteSec= map (  pagamentoSecondario.getEpayTTipoPagamento ().getEpayTEnti (), Ente.class );
                listEnti.add ( enteSec );
                
                List<PagamentoSecondarioComponenti> tmp
                = mapList ( pagamentoSecondario.getEpayTPagamentoSecondarioComponentis (), PagamentoSecondarioComponenti.class );
                for ( PagamentoSecondarioComponenti componenteSecondario: tmp ) {
                    componenteSecondario.setApplicationId ( null!= pagamentoSecondario.getEpayTTipoPagamento ()? pagamentoSecondario.getEpayTTipoPagamento ().getIdApplicazione ():"" );
                    componentiSecondariFinale.add ( componenteSecondario );
                }
            }
            pagamento.setEntiSecondari ( listEnti );
        }
        pagamento.setComponentiSecondari ( componentiSecondariFinale );
        

        if ( tPagamento.getEpayTPagamentoRiferimentis () != null && !tPagamento.getEpayTPagamentoRiferimentis ().isEmpty () ) {
            pagamento.setRiferimenti ( mapList ( tPagamento.getEpayTPagamentoRiferimentis (), PagamentoRiferimenti.class ) );
        }

        return pagamento;
    }

    private Pagamento mappaPagamentoBase(EpayTPagamento tPagamento) {
        Pagamento pagamento = map(tPagamento, Pagamento.class);

        pagamento.setAuxDigit ( tPagamento.getAuxDigit () );
        pagamento.setIuv(tPagamento.getIuvNumeroAvviso ());
        pagamento.setApplicationCode ( tPagamento.getApplicationCode () );
        
        pagamento.setRequiresCostUpdate(tPagamento.getRequiresCostUpdate());

        // log.debug ( "##############################################################################","#################################");
        log.debug ( "AUX DIGIT IS" , pagamento.getAuxDigit ());
        log.debug ( "IUV IS" , pagamento.getIuv());
        log.debug ( "APP CODE IS" , pagamento.getApplicationCode ());
        /* Pagamento model emette IllegalArgumentException che viene ora gestita - BEGIN */
        String codiceAvviso;
		try {
			codiceAvviso = pagamento.getCodiceAvviso ();
		} catch ( IllegalArgumentException e ) {
			codiceAvviso = null;
		}
        log.debug ( "COD AVVISO IS" , codiceAvviso );
        /* Pagamento model emette IllegalArgumentException che viene ora gestita - END */
        // log.debug ( "##############################################################################","#################################");

        pagamento.setPagatore(map(tPagamento.getEpayTAnagrafica(), Anagrafica.class));
        pagamento.setTipoPagamento(map(tPagamento.getEpayTTipoPagamento(), TipoPagamento.class));
        pagamento.setEnte(map(tPagamento.getEpayTTipoPagamento().getEpayTEnti(), Ente.class));
        pagamento.setIdStatoCorrente(tPagamento.getEpayDStatoPagamento().getIdStato());

        //        if (tPagamento.getEpayTPagamentoComponentis() != null && !tPagamento.getEpayTPagamentoComponentis().isEmpty())  {
        //            pagamento.setComponenti(mapList(tPagamento.getEpayTPagamentoComponentis(), PagamentoComponenti.class));
        //        }
        //
        //        if ( tPagamento.getEpayTPagamentoRiferimentis () != null && !tPagamento.getEpayTPagamentoRiferimentis ().isEmpty () ) {
        //            pagamento.setRiferimenti ( mapList ( tPagamento.getEpayTPagamentoRiferimentis (), PagamentoRiferimenti.class ) );
        //        }

        if (tPagamento.getEpayTRegistroVersamentis() == null || tPagamento.getEpayTRegistroVersamentis().isEmpty()) {
            pagamento.setIuvRegistroVersamenti(pagamento.getIuv());
            pagamento.setDataStatoCorrente(pagamento.getDataInserimento());
        } else {
            pagamento.setIuvRegistroVersamenti(recente(tPagamento.getEpayTRegistroVersamentis()));
            for (EpayTRegistroVersamenti rv : tPagamento.getEpayTRegistroVersamentis()) {
                if (rv.getEpayDStatoPagamento().getIdStato().equals(pagamento.getIdStatoCorrente()) &&
                                (pagamento.getDataStatoCorrente() == null ||
                                pagamento.getDataStatoCorrente().before(rv.getDataOperazione())
                                                )
                                ) {
                    pagamento.setDataStatoCorrente(rv.getDataOperazione());
                }
            }
        }

        return pagamento;
    }

    private Pagamento mappaPagamentoValidoPerEnte(EpayTPagamento tPagamento) {
        Pagamento pagamento = map(tPagamento, Pagamento.class);

        pagamento.setIuv(tPagamento.getIuvNumeroAvviso ());
        pagamento.setPagatore(map(tPagamento.getEpayTAnagrafica(), Anagrafica.class));
        pagamento.setTipoPagamento(map(tPagamento.getEpayTTipoPagamento(), TipoPagamento.class));
        pagamento.setEnte(map(tPagamento.getEpayTTipoPagamento().getEpayTEnti(), Ente.class));
        pagamento.setIdStatoCorrente(tPagamento.getEpayDStatoPagamento().getIdStato());

        return pagamento;
    }


    private String recente(List<EpayTRegistroVersamenti> epayTRegistroVersamentis) {
        if (epayTRegistroVersamentis == null || epayTRegistroVersamentis.isEmpty()) {
            return null;
        }
        EpayTRegistroVersamenti recente = new EpayTRegistroVersamenti();
        recente.setIdRegistro(-1L);
        for (EpayTRegistroVersamenti epayTRegistroVersamenti : epayTRegistroVersamentis) {
            log.debug ( "mappaPagamento.recente", " IuvRegistroVersamenti:" + epayTRegistroVersamenti.getIuv ());
            if (epayTRegistroVersamenti.getIdRegistro() > recente.getIdRegistro()) {
                recente = epayTRegistroVersamenti;
            }
        }
        log.debug ( "mappaPagamento.recente", " SELEZIONATO IuvRegistroVersamenti:" + recente.getIuv ());
        return recente.getIuv();
    }

    private Timestamp currentTimestamp() {
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        Timestamp currentTimestamp = new Timestamp(now.getTime());
        return currentTimestamp;
    }

    public void aggiornaImporti(Pagamento pagamento) {
        EpayTPagamento tPagamento = entityManager.find(EpayTPagamento.class, pagamento.getIdPagamento());
        tPagamento.setImporto(pagamento.getImporto());
        tPagamento.setUtenteUltimaModifica(setUtenteNotEmpty(pagamento.getUtenteUltimaModifica()));
        if ( null != pagamento.getRequiresCostUpdate () ) {
            tPagamento.setRequiresCostUpdate ( pagamento.getRequiresCostUpdate () );
        }
        tPagamento.setEpayTPagamentoComponentis(null);
        if (!pagamento.getComponenti().isEmpty()) {
            pagamentoComponentiManager.deleteAllPagamentoComponente ( pagamento.getIdPagamento () );
            int progressivo = 0;
            tPagamento.setEpayTPagamentoComponentis(new ArrayList<EpayTPagamentoComponenti>());
            for (PagamentoComponenti componenti : pagamento.getComponenti()) {
                EpayTPagamentoComponenti epayTPagamentoComponenti = new EpayTPagamentoComponenti();
                epayTPagamentoComponenti.setEpayTPagamento(tPagamento);
                epayTPagamentoComponenti.setProgressivo(++progressivo);
                epayTPagamentoComponenti.setImporto(componenti.getImporto());
                epayTPagamentoComponenti.setCausale(StringUtils.substring (componenti.getCausale(), 0, 140));
                epayTPagamentoComponenti.setDatiSpecificiRiscossione(componenti.getDatiSpecificiRiscossione());
                epayTPagamentoComponenti.setCodiceTributo (  componenti.getCodiceTributo () );
                epayTPagamentoComponenti
                .setAnnoAccertamento ( componenti.getAnnoAccertamento () != null ? Integer.valueOf ( componenti.getAnnoAccertamento () ) : null );
                epayTPagamentoComponenti.setNumeroAccertamento ( componenti.getNumeroAccertamento () );
                epayTPagamentoComponenti.setUtenteUltimaModifica(setUtenteNotEmpty(componenti.getUtenteUltimaModifica()));
                tPagamento.addEpayTPagamentoComponenti(epayTPagamentoComponenti);
            }
        }
    }

    /**
     * 
     * @param tPagamento
     * @param componenti
     */
    public void aggiornaImporti ( EpayTPagamento tPagamento, List<PagamentoComponenti> componenti ) {
        tPagamento.setEpayTPagamentoComponentis ( null );
        if ( !componenti.isEmpty () ) {
            pagamentoComponentiManager.deleteAllPagamentoComponente ( tPagamento.getIdPagamento () );
            int progressivo = 0;
            tPagamento.setEpayTPagamentoComponentis ( new ArrayList<EpayTPagamentoComponenti> () );
            for ( PagamentoComponenti componente: componenti ) {
                EpayTPagamentoComponenti epayTPagamentoComponenti = new EpayTPagamentoComponenti ();
                epayTPagamentoComponenti.setEpayTPagamento ( tPagamento );
                epayTPagamentoComponenti.setProgressivo ( ++progressivo );
                epayTPagamentoComponenti.setImporto ( componente.getImporto () );
                epayTPagamentoComponenti.setCausale ( StringUtils.substring ( componente.getCausale (), 0, 140 ) );
                epayTPagamentoComponenti.setDatiSpecificiRiscossione ( componente.getDatiSpecificiRiscossione () );
                epayTPagamentoComponenti.setCodiceTributo (  componente.getCodiceTributo () );
                epayTPagamentoComponenti
                    .setAnnoAccertamento ( componente.getAnnoAccertamento () != null ? Integer.valueOf ( componente.getAnnoAccertamento () ) : null );
                epayTPagamentoComponenti.setNumeroAccertamento ( componente.getNumeroAccertamento () );
                epayTPagamentoComponenti.setUtenteUltimaModifica ( setUtenteNotEmpty ( componente.getUtenteUltimaModifica () ) );
                tPagamento.addEpayTPagamentoComponenti ( epayTPagamentoComponenti );
            }
        }
    }

    public void aggiornaRiferimenti ( Pagamento pagamento ) {
        pagamentoRiferimentiManager.deleteAllPagamentoRiferimento ( pagamento.getIdPagamento () );

        EpayTPagamento tPagamento = entityManager.find ( EpayTPagamento.class, pagamento.getIdPagamento () );
        tPagamento.setUtenteUltimaModifica ( setUtenteNotEmpty ( pagamento.getUtenteUltimaModifica () ) );
        tPagamento.setEpayTPagamentoRiferimentis ( null );

        if ( !pagamento.getRiferimenti ().isEmpty () ) {
            int progressivo = 0;
            tPagamento.setEpayTPagamentoRiferimentis ( new ArrayList<EpayTPagamentoRiferimenti> () );
            for ( PagamentoRiferimenti riferimenti: pagamento.getRiferimenti () ) {
                EpayTPagamentoRiferimenti epayTPagamentoRiferimenti = new EpayTPagamentoRiferimenti ();
                epayTPagamentoRiferimenti.setEpayTPagamento ( tPagamento );
                epayTPagamentoRiferimenti.setProgressivo ( ++progressivo );
                epayTPagamentoRiferimenti.setNome ( riferimenti.getNome () );
                epayTPagamentoRiferimenti.setValore ( riferimenti.getValore () );
                epayTPagamentoRiferimenti.setUtenteUltimaModifica ( setUtenteNotEmpty ( riferimenti.getUtenteUltimaModifica () ) );
                tPagamento.addEpayTPagamentoRiferimenti ( epayTPagamentoRiferimenti );
            }
        }
    }

    /**
     * 
     * @param tPagamento
     * @param riferimenti
     */
    public void aggiornaRiferimenti ( EpayTPagamento tPagamento, List<PagamentoRiferimenti> riferimenti ) {
        pagamentoRiferimentiManager.deleteAllPagamentoRiferimento ( tPagamento.getIdPagamento () );

        tPagamento.setUtenteUltimaModifica ( setUtenteNotEmpty ( tPagamento.getUtenteUltimaModifica () ) );
        tPagamento.setEpayTPagamentoRiferimentis ( null );

        if ( !riferimenti.isEmpty () ) {
            int progressivo = 0;
            tPagamento.setEpayTPagamentoRiferimentis ( new LinkedList<EpayTPagamentoRiferimenti> () );
            for ( PagamentoRiferimenti riferimento: riferimenti ) {
                EpayTPagamentoRiferimenti epayTPagamentoRiferimenti = new EpayTPagamentoRiferimenti ();
                epayTPagamentoRiferimenti.setEpayTPagamento ( tPagamento );
                epayTPagamentoRiferimenti.setProgressivo ( ++progressivo );
                epayTPagamentoRiferimenti.setNome ( riferimento.getNome () );
                epayTPagamentoRiferimenti.setValore ( riferimento.getValore () );
                epayTPagamentoRiferimenti.setUtenteUltimaModifica ( setUtenteNotEmpty ( riferimento.getUtenteUltimaModifica () ) );
                tPagamento.addEpayTPagamentoRiferimenti ( epayTPagamentoRiferimenti );
            }
        }
    }

    public EpayTPagamento aggiornaDate(Long idPagamento, XMLGregorianCalendar dataInizioValidita, XMLGregorianCalendar dataFineValidita, XMLGregorianCalendar dataScadenza) {
        EpayTPagamento tPagamento = entityManager.find(EpayTPagamento.class, idPagamento);
        if (dataInizioValidita != null) {
            tPagamento.setInizioValidita(dataInizioValidita.toGregorianCalendar().getTime());
        }
        if (dataFineValidita != null) {
            tPagamento.setFineValidita(dataFineValidita.toGregorianCalendar().getTime());
        }
        if (dataScadenza != null) {
            tPagamento.setDataScadenza(dataScadenza.toGregorianCalendar().getTime());
        }
        return tPagamento;
    }

    public EpayTPagamento aggiornaDate2 ( Long idPagamento, Date dataInizioValidita, Date dataFineValidita, Date dataScadenza ) {
        EpayTPagamento tPagamento = entityManager.find ( EpayTPagamento.class, idPagamento );
        if ( dataInizioValidita != null ) {
            tPagamento.setInizioValidita ( dataInizioValidita );
        }
        if ( dataFineValidita != null ) {
            tPagamento.setFineValidita ( dataFineValidita );
        }
        if ( dataScadenza != null ) {
            tPagamento.setDataScadenza ( dataScadenza );
        }
        return tPagamento;
    }

    private String setUtenteNotEmpty(final String utente) {
        if (StringUtils.isBlank(utente)) {
            return "System";
        } else {
            return utente;
        }
    }

    public StatistichePagamenti getStatistichePagamenti () {
        String methodName = "getStatistichePagamenti";
        log.infoStart ( methodName );

        try {
            StatistichePagamenti output = new StatistichePagamenti ();

            TypedQuery<Long> query = entityManager.createNamedQuery ( "EpayTPagamento.contaPerEsportazionePEC", Long.class );
            output.setNumeroRecord ( query.getSingleResult () );

            query = entityManager.createNamedQuery ( "EpayTPagamento.getIdMassimoPerEsportazionePEC", Long.class );
            output.setIdMassimo ( query.getSingleResult () );

            return output;
        } catch ( Throwable t ) {
            log.error ( methodName, "Errore nel calcolo delle statistiche dei pagamenti", t );
            throw t;
        } finally {
            log.infoEnd ( methodName );
        }
    }

    public List<PagamentoInvioPEC> ricercaPagamentiPerInvioPEC ( Long numLimit ) {
        JobContext context = new JobContext ( "SincronizzaPagamentiPEC" );
        String methodName = "ricercaPagamentiPerInvioPEC";
        log.infoStart ( methodName );

        context.infoStart ( methodName );
//        context.info ( methodName, "ricerco pagamenti da inviare nel range " + idFrom + " - " + idTo );

        try {
            Query query = entityManager.createNativeQuery ( "SELECT  " +
                            "                CAST(t.id_pagamento AS VARCHAR), " +
                            "                t.iuv_numero_avviso, " +
                            "                t.pagamento_spontaneo, " +
                            "                CAST(s.id_stato AS VARCHAR), " +
                            "                s.desc_stato, " +
                            "                CAST(rt.id_rt AS VARCHAR) as id_rt, " +
                            "                rt.dataora_msg_ricevuta as rt_dataora_msg_ricevuta, " +
                            "                CAST(rt.importo_totale_pagato AS VARCHAR) as rt_importo_totale_pagato, " +
                            "                er.data_pagamento as er_data_pagamento, " +
                            "                CAST(er.importo AS VARCHAR) as er_importo, " +
                            "                t.flg_invio_report_pec as flg_invio_report_pec, " +
                            "                tipo.codice_versamento as cod_versamento, " +
                            "                tipo.descrizione_portale as descr_cod_versamento, " +
                            "                ente.codice_fiscale as cod_fiscale_ente, " +
                            "                t.note, " +
                            "                anag.nome, " +
                            "                anag.cognome, " +
                            "                anag.ragione_sociale, " +
                            "                anag.codice_fiscale as cod_fiscale_pagatore, " +
                            "                anag.flag_persona_fisica, " +
                            "                t.fine_validita, " +
                            "                t.causale as causale, t.requires_cost_update" +
                            "            FROM epay.epay_t_pagamento t " +
                            "                left join epay_t_tipo_pagamento tipo on tipo.id_tipo_pagamento = t.id_tipo_pagamento " +
                            "                left join epay_t_enti ente on ente.id_ente = tipo.id_ente " +
                            "                left join epay_d_stato_pagamento s on s.id_stato = t.id_stato_corrente " +
                            "                left join epay_t_registro_versamenti rv on (rv.id_pagamento = t.id_pagamento and rv.id_stato = 4 and rv.origine_inserimento IN ('MDPSERVICES-riceviRT','MDPSERVICES-riceviEsito')) " +
                            "                left join epay_t_rt rt on rt.id_registro = rv.id_registro " +
                            "                left join epay_t_esiti_ricevuti er on er.id_registro = rv.id_registro " +
                            "                left join epay_t_anagrafica anag on anag.id_anagrafica = t.id_anagrafica_pagatore " 
//                            +"            WHERE t.id_pagamento >= :idFrom AND t.id_pagamento < :idTo "
                             +"               WHERE t.iuv_numero_avviso is not null "
                             + "              and (t.flg_invio_report_pec = false or flg_invio_report_pec is null) "
                             + "             order by t.id_pagamento desc "
                             + "              limit :numLimit" );

            query.setParameter ( "numLimit",   numLimit  );
//            query.setParameter ( "idTo", idTo );

            @SuppressWarnings ( "unchecked" )
            List<Object []> tPagamenti = query.getResultList ();

            List<PagamentoInvioPEC> pagamentiEffettuati = new ArrayList<> ();
            Set<String> listaIuvTrasmessi = new HashSet<> ();
            context.info ( methodName, "Numero pagamenti trovati: "  + tPagamenti.size ());
            log.info ( methodName, "Numero pagamenti trovati: "  + tPagamenti.size ());
            for ( Object [] tPagamento: tPagamenti ) {
                PagamentoInvioPEC mappato = mappaPagamentoPerInvioPEC ( tPagamento );
                context.info ( methodName, "Elaborazione IUV: " + null != mappato.getIuvNumeroAvviso () ? mappato.getIuvNumeroAvviso () : null );
                log.info ( methodName, "Elaborazione IUV: " + null != mappato.getIuvNumeroAvviso () ? mappato.getIuvNumeroAvviso () : null );
                if ( null != mappato && mappato.getIuvNumeroAvviso () != null ) {
                    if ( listaIuvTrasmessi.contains ( mappato.getIuvNumeroAvviso () ) ) {
                        // :TODO Quando si verranno bonificati i dati e risolta la problematica della RT doppia ristabilire i controlli.
                        context.warn ( methodName, "Esiste un pagamento con piu' RT oppure esiti ricevuti positivi: IUV = " + mappato.getIuvNumeroAvviso () );
                        // context.error ( methodName, "Esiste un pagamento con piu' RT oppure esiti ricevuti positivi: IUV = " + mappato.getIuvNumeroAvviso () )
                        // context.addProblem ( "Esiste un pagamento con piu' RT oppure esiti ricevuti positivi: IUV = " + mappato.getIuvNumeroAvviso () )
                        continue;

                    } else {
                        log.info ( methodName, "Trasmissione IUV: " + null != mappato.getIuvNumeroAvviso () ? mappato.getIuvNumeroAvviso () : null );
                        listaIuvTrasmessi.add ( mappato.getIuvNumeroAvviso () );
                        // verifica se manca il vecchio campo importo_totale_pagamento
                        if ( !StringUtils.isEmpty ( (String) tPagamento [5] ) && StringUtils.isEmpty ( (String) tPagamento [7] ) ) {
                            Boolean flgInvio = (Boolean) tPagamento [10];
                            if ( flgInvio == null || !flgInvio ) {
                                estraiCampiMancantiRT ( context, Long.valueOf ( (String) tPagamento [5] ), mappato );
                            }
                        }
                        pagamentiEffettuati.add ( mappato );
                    }
                }

            }
            context.info ( methodName, "Numero pagamenti elaborati e restituiti: "  + pagamentiEffettuati.size ());
            log.info ( methodName, "Numero pagamenti elaborati e restituiti: "  + pagamentiEffettuati.size ());
            return pagamentiEffettuati;
        } catch ( NoResultException e ) {
            return Collections.emptyList ();

        } catch ( Throwable t ) {
            context.error ( methodName, "Errore nella ricerca dei pagamenti per l'invio al PEC", t );
            throw t;
        } finally {
            if ( !context.getProblemi ().isEmpty () ) {
                try {
                    mailManager.inviaMailAssistenza ( context );
                } catch ( Throwable t ) {
                    context.error ( methodName, "Errore nell'invio della mail all'assistenza", t );
                }

            }
            log.infoEnd ( methodName );
            context.infoEnd ( methodName );
        }
    }

    private void estraiCampiMancantiRT ( JobContext context, Long idRT, PagamentoInvioPEC mappato ) {
        String methodName = "estraiCampiMancantiRT";
        context.infoStart ( methodName );

        try {
            context.debug ( methodName, "recupero XML della RT " + idRT );
            byte [] rtXml = rtManager.readXml ( idRT );

            context.debug ( methodName, "eseguo parsing XML della RT " + idRT );
            Document docRt = DocumentBuilderFactory.newInstance ().newDocumentBuilder ().parse ( new ByteArrayInputStream ( rtXml ) );

            context.debug ( methodName, "estraggo tag importoTotalePagato dalla RT " + idRT );
            XPath xPath = XPathFactory.newInstance ().newXPath ();
            Element element = (Element) xPath.compile ( "/RT/datiPagamento/importoTotalePagato" ).evaluate ( docRt, XPathConstants.NODE );
            String elementContent = element.getTextContent ();
            if ( !StringUtils.isEmpty ( elementContent ) ) {

                context.debug ( methodName, "tag trovato: " + elementContent );
                BigDecimal converted = new BigDecimal ( elementContent );
                context.debug ( methodName, "tag convertito: " + converted.toPlainString () );
                mappato.setImportoPagato ( converted );

                context.debug ( methodName, "aggiorno il campo nella RT originale" );
                EpayTRt rt = entityManager.find ( EpayTRt.class, idRT );
                rt.setImportoTotalePagato ( converted );
                entityManager.merge ( rt );
                entityManager.flush ();
                context.debug ( methodName, "aggiornato il campo nella RT originale" );
            }

        } catch ( Throwable t ) {
            context.error ( methodName, "Errore nell estrazione dei campi dalla RT", t );

        } finally {
            context.infoEnd ( methodName );
        }
    }

    private PagamentoInvioPEC mappaPagamentoPerInvioPEC ( Object [] row ) {
        if ( row == null ) {
            return null;
        }
        PagamentoInvioPEC output = new PagamentoInvioPEC(
            row [0] != null ? Long.valueOf ( (String) row [0] ) : null,
                            (String) row [1],
                            row [3] != null ? Integer.valueOf ( (String) row [3] ) : null,
                                            (String) row [4],
                                            null,
                                            null,
                                            (Timestamp) row [6],
                                            row [7] != null ? new BigDecimal ( (String) row [7] ) : null,
                                                            (Timestamp) row [8],
                                                            row [9] != null ? new BigDecimal ( (String) row [9] ) : null,
                                                                            (Boolean) row [2],
                                                                            (String) row [11],
                                                                            (String) row [12],
                                                                            (String) row [13],
                                                                            (String) row [14],
                                                                            (String) row [15],
                                                                            (String) row [16],
                                                                            (String) row [17],
                                                                            (String) row [18],
                                                                            (Boolean) row [19],
                                                                            (Date) row [20],
                                                                            (String) row[21],
                                                                            (Boolean) row[22]);

        if ( output.getDataPagamentoRt () != null || output.getImportoPagatoRt () != null ) {
            output.setDataPagamento ( output.getDataPagamentoRt () );
            output.setImportoPagato ( output.getImportoPagatoRt () );
        } else {
            output.setDataPagamento ( output.getDataPagamentoEr () );
            output.setImportoPagato ( output.getImportoPagatoEr () );
        }
//        log.info ( "mappaPagamentoPerInvioPEC", "codVersamento: " + output.getCodVersamento () + " descrCodVersamento: " + output.getDescrCodVersamento () + " codFiscaleEnte: " +output.getCodFiscaleEnte () );
//        log.info ( "mappaPagamentoPerInvioPEC", "NotePagamento: " + output.getNotePagamento () + " CodiceFiscalePagatore: " + output.getCodiceFiscalePagatore () + " Cognome: " +output.getCognome () );
        return output;
    }

    public void marcaInviatiAlPEC (  List<Long> idPagamenti  ) {
        String methodName = "marcaInviatiAlPEC";
        log.infoStart ( methodName );
//        log.info ( methodName, "marco come inviati nel range " + idFrom + " - " + idTo );

        try {
            Query query = entityManager.createNamedQuery ( "EpayTPagamento.marcaInviatiAlPECList" );
            
//            List<Long> idpagamentos = new LinkedList<Long> ();
//            
//            idpagamentos.add ( new Long(209240) );
//            idpagamentos.add ( new Long(209238) );
//            idpagamentos.add ( new Long(209237) );
            
            query.setParameter ( "idPagamentos", idPagamenti );
//            query.setParameter ( "idTo", idTo );
            query.executeUpdate ();
            //            entityManager.flush ();

        } catch ( Throwable t ) {
            log.error ( methodName, "Errore nel mark", t );
            throw t;
        } finally {
            log.infoEnd ( methodName );
        }
    }

    //CSI_PAG-1888 INIZIO
    public PagamentoSecondario getPagamentoSecondarioByIdPagamentoPrincipale ( Long idPagamentoPrincipale ) throws NoDataException, MoreResultException {
        try {
            TypedQuery<EpayTPagamentoSecondario> query
            = entityManager.createNamedQuery ( "EpayTPagamentoSecondario.findByIdPagamento", EpayTPagamentoSecondario.class );
            query.setParameter ( "idPagamento", idPagamentoPrincipale );
            EpayTPagamentoSecondario tPagamentoSecondario = query.getSingleResult ();
            return mappaPagamentoSecondarioEsteso ( tPagamentoSecondario );
        } catch ( NonUniqueResultException e ) {
            throw new MoreResultException ();
        } catch ( NoResultException e ) {
            throw new NoDataException ();
        }
    }

    private PagamentoSecondario mappaPagamentoSecondarioEsteso ( EpayTPagamentoSecondario tPagamentoSecondario ) {
        PagamentoSecondario pagamentoSecondario = mappaPagamentoSecondarioBase ( tPagamentoSecondario );

        if ( tPagamentoSecondario.getEpayTPagamentoSecondarioComponentis () != null
                        && !tPagamentoSecondario.getEpayTPagamentoSecondarioComponentis ().isEmpty () ) {
            pagamentoSecondario.setComponenti ( mapList ( tPagamentoSecondario.getEpayTPagamentoSecondarioComponentis (), PagamentoComponenti.class ) );
        }

        return pagamentoSecondario;
    }

    private PagamentoSecondario mappaPagamentoSecondarioBase ( EpayTPagamentoSecondario tPagamentoSecondario ) {
        PagamentoSecondario pagamentoSecondario = map ( tPagamentoSecondario, PagamentoSecondario.class );

        pagamentoSecondario.setTipoPagamento ( map ( tPagamentoSecondario.getEpayTTipoPagamento (), TipoPagamento.class ) );
        pagamentoSecondario.setIdPagamento ( tPagamentoSecondario.getEpayTPagamento ().getIdPagamento () );

        return pagamentoSecondario;
    }

    public void aggiornaImportiSecondari ( PagamentoSecondario pagamento ) {
        if ( null != pagamento.getIdPagamentoSecondario () ) {
            pagamentoComponentiManager.deleteAllPagamentoComponenteSecondario ( pagamento.getIdPagamentoSecondario () );
        }
        EpayTPagamentoSecondario tPagamentoSecondario = entityManager.find ( EpayTPagamentoSecondario.class, pagamento.getIdPagamentoSecondario () );
        tPagamentoSecondario.setImportoComplessivo ( pagamento.getImportoComplessivo () );
        tPagamentoSecondario.setEpayTPagamentoSecondarioComponentis ( null );

        if ( !pagamento.getComponenti ().isEmpty () ) {
            int progressivo = 0;
            tPagamentoSecondario.setEpayTPagamentoSecondarioComponentis ( new ArrayList<EpayTPagamentoSecondarioComponenti> () );
            for ( PagamentoComponenti componenti: pagamento.getComponenti () ) {
                EpayTPagamentoSecondarioComponenti epayTPagamentoComponenti = new EpayTPagamentoSecondarioComponenti ();
                epayTPagamentoComponenti.setEpayTPagamentoSecondario ( tPagamentoSecondario );
                epayTPagamentoComponenti.setProgressivo ( ++progressivo );
                epayTPagamentoComponenti.setImporto ( componenti.getImporto () );
                epayTPagamentoComponenti.setCausale ( StringUtils.substring ( componenti.getCausale (), 0, 140) );
                epayTPagamentoComponenti.setDatiSpecificiRiscossione ( componenti.getDatiSpecificiRiscossione () );
                epayTPagamentoComponenti.setCodiceTributo (  componenti.getCodiceTributo () );
                epayTPagamentoComponenti
                .setAnnoAccertamento ( componenti.getAnnoAccertamento () != null ? Integer.valueOf ( componenti.getAnnoAccertamento () ) : null );
                epayTPagamentoComponenti.setNumeroAccertamento ( componenti.getNumeroAccertamento () );
                epayTPagamentoComponenti.setUtenteUltimaModifica ( setUtenteNotEmpty ( componenti.getUtenteUltimaModifica () ) );
                tPagamentoSecondario.addEpayTPagamentoSecondarioComponenti ( epayTPagamentoComponenti );
                entityManager.persist ( epayTPagamentoComponenti );
            }
        }
    }

    //CSI_PAG-1888 FINE

    //CSI_PAG-1887 INIZIO
    public Long inserisciPagamentoSecondario ( PagamentoSecondario pagamentoSecondario, EpayTPagamento pagamento ) {
        String methodName = "inserisciPagamentoSecondario";
        log.debugStart ( methodName );
        EpayTPagamentoSecondario tPagamentoSecondario = map ( pagamentoSecondario, EpayTPagamentoSecondario.class );
        tPagamentoSecondario.setCausale ( StringUtils.substring (pagamentoSecondario.getCausale (), 0, 140) );
        tPagamentoSecondario.setEpayTPagamento ( pagamento );
        tPagamentoSecondario.setEpayTPagamentoSecondarioComponentis ( new LinkedList<EpayTPagamentoSecondarioComponenti>() );
        tPagamentoSecondario.setImportoComplessivo ( pagamentoSecondario.getImportoComplessivo () );

        log.debug ( methodName, ( null != pagamentoSecondario.getTipoPagamento () ) ? pagamentoSecondario.getTipoPagamento ().toString () : "null" );

        tPagamentoSecondario
        .setEpayTTipoPagamento ( entityManager.find ( EpayTTipoPagamento.class, pagamentoSecondario.getTipoPagamento ().getIdTipoPagamento () ) );

        entityManager.persist ( tPagamentoSecondario );

        if ( tPagamentoSecondario.getEpayTPagamentoSecondarioComponentis () != null ) {
            for ( PagamentoComponenti componenti: pagamentoSecondario.getComponenti () ) {
                EpayTPagamentoSecondarioComponenti pagamentoComponenti = map ( componenti, EpayTPagamentoSecondarioComponenti.class );
                pagamentoComponenti.setUtenteUltimaModifica ( setUtenteNotEmpty ( componenti.getUtenteUltimaModifica () ) );
                pagamentoComponenti.setEpayTPagamentoSecondario ( tPagamentoSecondario );
                entityManager.persist ( pagamentoComponenti );
                tPagamentoSecondario.getEpayTPagamentoSecondarioComponentis ().add ( pagamentoComponenti );
            }
        }
        entityManager.flush ();
        log.debugEnd ( methodName );
        return tPagamentoSecondario.getIdPagamentoSecondario ();
    }
    //CSI_PAG-1887 FINE

    public List<PagamentoSecondarioComponenti> getPagamentoSecondarioComponentiById ( Long idPagamentoSecondario ) throws NoDataException, MoreResultException {

        try {
            TypedQuery<EpayTPagamentoSecondarioComponenti> query
            = entityManager.createNamedQuery ( "EpayTPagamentoSecondarioComponenti.elencoComponentiByIdPagamentoSecondario",
                EpayTPagamentoSecondarioComponenti.class );
            query.setParameter ( "idPagamentoSecondario", idPagamentoSecondario );
            List<EpayTPagamentoSecondarioComponenti> tPagamentoSecondarioComponenti = query.getResultList ();
            return mappaPagamentoSecondarioComponenti ( tPagamentoSecondarioComponenti );
        } catch ( NonUniqueResultException e ) {
            throw new MoreResultException ();
        } catch ( NoResultException e ) {
            throw new NoDataException ();
        }
    }


    private List<PagamentoSecondarioComponenti> mappaPagamentoSecondarioComponenti ( List<EpayTPagamentoSecondarioComponenti> tPagamentoSecondarioComponenti ) {
        //        List<PagamentoSecondarioComponenti> componenti=new ArrayList<PagamentoSecondarioComponenti>();
        //        for(EpayTPagamentoSecondarioComponenti tComponente : tPagamentoSecondarioComponenti) {
        //            mapList ( listDa, classA )
        //        }
        return mapList ( tPagamentoSecondarioComponenti, PagamentoSecondarioComponenti.class );

        //pagamentoSecondario.setTipoPagamento ( map ( tPagamentoSecondario.getEpayTTipoPagamento (), TipoPagamento.class ) );

        //return pagamentoSecondario;
    }

    public TipoPagamento getTipoPagamento ( Long idTipoPagamento ) throws NoDataException, MoreResultException {
        try {
            TypedQuery<EpayTTipoPagamento> query
            = entityManager.createNamedQuery ( "EpayTTipoPagamento.findByIdTipoPagamento", EpayTTipoPagamento.class );
            query.setParameter ( "idTipoPagamento", idTipoPagamento );
            EpayTTipoPagamento tTipoPagamento = query.getSingleResult ();
            return mappaTipoPagamento ( tTipoPagamento );
        } catch ( NonUniqueResultException e ) {
            throw new MoreResultException ();
        } catch ( NoResultException e ) {
            throw new NoDataException ();
        }
    }

    private TipoPagamento mappaTipoPagamento ( EpayTTipoPagamento tTipoPagamento ) {
        return map ( tTipoPagamento, TipoPagamento.class );
    }
    
    public void sbloccaPosiszioniDebitorieInAttesa (Integer statoCorrente, Integer statoPagamento) {

        JobContext context = new JobContext ( "PagamentoManager" );
        String methodName = "chiudiPosiszioniDebitoriePagate";
        log.infoStart ( methodName );

        context.infoStart ( methodName );

        final Calendar cal = Calendar.getInstance ();
        cal.add ( Calendar.DATE, -1 );
        Date date = cal.getTime();             
        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
        String ieri = format1.format(date);   

        try {

            Query query = entityManager.createNativeQuery ( ""
                            + "update epay_t_pagamento "
                            + "set id_stato_corrente = :statoCorrente "
                            + " where "
                            + "id_pagamento in ( "
                            + "select "
                            + " id_pagamento "
                            + "from epay_t_registro_versamenti "
                            + " where "
                            + "id_pagamento in ( "
                            + "select "
                            + " id_pagamento "
                            + " from epay_t_pagamento "
                            + " where "
                            + " data_inserimento > to_date(:yesterday ,'DD-MM-YYYY')  "
                            + " and id_stato_corrente = 2 ) "
                            + " and id_stato = :statoPagamento"
                            + ") "
                            + ""
                            + ""

                            + "" );

            query.setParameter ( "statoCorrente",   statoCorrente  );
            query.setParameter ( "yesterday",   ieri  );
            query.setParameter ( "statoPagamento",   statoPagamento  );

            query.executeUpdate ();

        }
        catch ( Throwable t ) {
            log.error ( methodName, "Errore nel mark", t );
            throw t;
        } finally {
            log.infoEnd ( methodName );
        }
    }

	public void attualizzaImporto(Long idPagamento, BigDecimal importo, Boolean requiresCostUpdate) {
        EpayTPagamento tPagamento = entityManager.find(EpayTPagamento.class, idPagamento);
        tPagamento.setRequiresCostUpdate(requiresCostUpdate);
        tPagamento.setImporto(importo);
        entityManager.persist(tPagamento);
        entityManager.flush();
	}
    

    
    

}

