/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.business.v1;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import it.csi.epay.epayservices.business._BaseBean;
import it.csi.epay.epayservices.integration.db.entities.EpayDStatoPagamento;
import it.csi.epay.epayservices.integration.db.entities.EpayRTipoPagamentoCollegato;
import it.csi.epay.epayservices.integration.db.entities.EpayTPagamento;
import it.csi.epay.epayservices.integration.db.manager.AnagraficaManager;
import it.csi.epay.epayservices.integration.db.manager.ChiamataEsternaManager;
import it.csi.epay.epayservices.integration.db.manager.ChiamataEsternaNonValidaManager;
import it.csi.epay.epayservices.integration.db.manager.ChiamataEsternaSincronaSplitManager;
import it.csi.epay.epayservices.integration.db.manager.ConfigurazioneManager;
import it.csi.epay.epayservices.integration.db.manager.EnteManager;
import it.csi.epay.epayservices.integration.db.manager.EsitiRicevutiManager;
import it.csi.epay.epayservices.integration.db.manager.EsitoChiamataEsternaManager;
import it.csi.epay.epayservices.integration.db.manager.PagamentoManager;
import it.csi.epay.epayservices.integration.db.manager.PagamentoSecondarioManager;
import it.csi.epay.epayservices.integration.db.manager.RegistroVersamentiManager;
import it.csi.epay.epayservices.integration.db.manager.TipoPagamentoManager;
import it.csi.epay.epayservices.integration.db.manager.TransazioneMdpManager;
import it.csi.epay.epayservices.integration.mdp.MdpCore;
import it.csi.epay.epayservices.interfaces.ejb.DatiAvvisoPagamentoFacade;
import it.csi.epay.epayservices.interfaces.ejb.v1.ChiamataEsternaSincronaSplitFacade;
import it.csi.epay.epayservices.interfaces.ejb.v1.PagamentoFacade;
import it.csi.epay.epayservices.interfaces.exception.MoreResultException;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.interfaces.exception.PosizioneDebitoriaException;
import it.csi.epay.epayservices.interfaces.exception.TestataException;
import it.csi.epay.epayservices.interfaces.rs.CodiciEsito;
import it.csi.epay.epayservices.interfaces.rs.TassonomiaAdapterClient;
import it.csi.epay.epayservices.interfaces.rs.exception.TassonomiaServiceException;
import it.csi.epay.epayservices.model.Anagrafica;
import it.csi.epay.epayservices.model.ChiamanteEsterno;
import it.csi.epay.epayservices.model.ComponentiImporto;
import it.csi.epay.epayservices.model.ComponentiImportoSecondario;
import it.csi.epay.epayservices.model.Configurazione;
import it.csi.epay.epayservices.model.DatiAvvisoPagamento;
import it.csi.epay.epayservices.model.DatiPDFReport;
import it.csi.epay.epayservices.model.DatiSpecificiRiscossione;
import it.csi.epay.epayservices.model.DatiSpecificiRiscossioneInput;
import it.csi.epay.epayservices.model.DatiSpecificiRiscossioneOutput;
import it.csi.epay.epayservices.model.Ente;
import it.csi.epay.epayservices.model.EsitiRicevuti;
import it.csi.epay.epayservices.model.EsitoChiamataEsterna;
import it.csi.epay.epayservices.model.Pagamento;
import it.csi.epay.epayservices.model.PagamentoComponenti;
import it.csi.epay.epayservices.model.PagamentoRiferimenti;
import it.csi.epay.epayservices.model.PagamentoSecondario;
import it.csi.epay.epayservices.model.PagamentoSecondarioComponenti;
import it.csi.epay.epayservices.model.RegistroVersamenti;
import it.csi.epay.epayservices.model.RiferimentiPagamento;
import it.csi.epay.epayservices.model.Rt;
import it.csi.epay.epayservices.model.StatoPagamento;
import it.csi.epay.epayservices.model.TipoPagamento;
import it.csi.epay.epayservices.model.TipologiaPagamento;
import it.csi.epay.epayservices.model.TracciabilitaChiamanteEsterno;
import it.csi.epay.epayservices.model.TransazioneMdp;
import it.csi.epay.epayservices.model.v1.AggiornaPosizioneDebitoriaChiamanteEsternoInput;
import it.csi.epay.epayservices.model.v1.AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioInput;
import it.csi.epay.epayservices.model.v1.AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioOutput;
import it.csi.epay.epayservices.model.v1.AggiornaPosizioneDebitoriaChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.v1.ChiamanteEsternoCommonInput;
import it.csi.epay.epayservices.model.v1.ChiamataEsternaNonValida;
import it.csi.epay.epayservices.model.v1.EliminaPosizioneDebitoriaChiamanteEsternoInput;
import it.csi.epay.epayservices.model.v1.EliminaPosizioneDebitoriaChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.v1.Esito;
import it.csi.epay.epayservices.model.v1.GetDatiPagamentoChiamanteEsternoInput;
import it.csi.epay.epayservices.model.v1.GetDatiPagamentoChiamanteEsternoMultibeneficiarioOutput;
import it.csi.epay.epayservices.model.v1.GetDatiPagamentoChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.v1.GetStatoPosizioneDebitoriaInput;
import it.csi.epay.epayservices.model.v1.GetStatoPosizioneDebitoriaOutput;
import it.csi.epay.epayservices.model.v1.PagamentoIuvChiamanteEsternoInput;
import it.csi.epay.epayservices.model.v1.PagamentoIuvChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.v1.PagamentoPerStampaAvvisoInput;
import it.csi.epay.epayservices.model.v1.PagamentoPerStampaAvvisoOutput;
import it.csi.epay.epayservices.model.v1.Stato;
import it.csi.epay.epayservices.model.v1.TracciabilitaChiamataEsternaNonValida;
import it.csi.epay.epayservices.model.v1.Transaction;
import it.csi.epay.epayservices.utilities.LogUtil;
import it.csi.epay.epayservices.utilities.Messages;
import it.csi.epay.epayservices.utilities.XmlUtil;
import it.csi.mdpcore.DatiAccertamentoRPT;
import it.csi.mdpcore.DatiSingoloVersamentoRPT;
import it.csi.mdpcore.DatiVersamentoRPT;
import it.csi.mdpcore.ElencoRPT;
import it.csi.mdpcore.RPTData;
import it.csi.mdpcore.SoggettoPagatore;
import it.csi.mdpcore.Transazione;


/**
 *
 */
@Stateless ( name = "ChiamataEsternaSincronaSplitFacadeV1", mappedName = "ChiamataEsternaSincronaSplitV1" )
public class ChiamataEsternaSincronaSplitBean extends _BaseBean implements ChiamataEsternaSincronaSplitFacade {

    private static final LogUtil log = new LogUtil ( ChiamataEsternaSincronaSplitBean.class );

    private static final String REGISTRO_VERSAMENTO_ORIGINE_INSERIMENTO = "Gestionale esterno (chiamata sincrona IUV)";
    
    private static final int MAX_ERROR_MESSAGE_WIDTH = 200;
    
    private static final String ANONIMO = "ANONIMO";
    
    private static final String CONFIG_ENDPOINT_SERVICE_TASSONOMIA = "ENDPOINT_SERVICE_TASSONOMIA";
//    private static final String CONFIG_ENDPOINT_SERVICE_TASSONOMIA_LOCAL = "ENDPOINT_SERVICE_TASSONOMIA_LOCAL";

    private static final int MDP_NUMBER_RETRY = 3;
    
    private static final String COD_ENTE_DEFALUT = "PagoPa"; // codice 0
    
    private final Function<String, String> stringTrim =  s  -> s == null ? null : s.trim ();
    
    @EJB
    private EnteManager enteManager;

    @EJB
    private ChiamataEsternaManager chiamataEsternaManager;

	@EJB
	private ChiamataEsternaNonValidaManager chiamataEsternaNonValidaManager;

	@EJB
	private PagamentoManager pagamentoManager;

	@EJB
	private PagamentoSecondarioManager pagamentoSecondarioManager;

	@EJB
	private TipoPagamentoManager tipoPagamentoManager;

	@EJB
	private RegistroVersamentiManager registroVersamentiManager;

	@EJB
	private EsitiRicevutiManager esitiRicevutiManager;

	@EJB
	private AnagraficaManager anagraficaManager;

    @EJB
    private ConfigurazioneManager configurazioneManager;
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @EJB
    private ChiamataEsternaSincronaSplitManager chiamataEsternaSincronaSplitManager;

    @EJB
	private PagamentoFacade pagamentoFacade;

    @EJB
	private DatiAvvisoPagamentoFacade datiAvvisoPagamentoFacade;
    
    @EJB
    private EsitoChiamataEsternaManager esitoChiamataEsternaManager;

    @EJB
    private MdpCore mdpCore;
    
    @EJB
    private TransazioneMdpManager transazioneMdpManager;
    
    @TransactionAttribute ( TransactionAttributeType.REQUIRED )
    @Override
    public AggiornaPosizioneDebitoriaChiamanteEsternoOutput
        aggiornaPosizioneDebitoriaChiamanteEsterno ( AggiornaPosizioneDebitoriaChiamanteEsternoInput input ) {
        String nomeMetodo = "aggiornaPosizioneDebitoriaChiamanteEsterno";
        AggiornaPosizioneDebitoriaChiamanteEsternoOutput result;

        try {
            result = doAggiornaPosizioneDebitoriaChiamanteEsterno ( input );
        } catch ( TestataException te ) {
            result = AggiornaPosizioneDebitoriaChiamanteEsternoOutput.builder ()
                .withResult ( Esito.builder ().withCode ( te.getCode () ).withDescription ( te.getMessage () ).build () ).build ();
            tracciaFallimentoSuDB ( ChiamataEsternaNonValida.builder ().withCodiceChiamante ( input.getCodiceChiamante () )
                .withCodiceFiscale ( input.getSoggettoPagatore () != null ? input.getSoggettoPagatore ().getIdentificativoUnivocoFiscale () : null )
                .withDescrizioneErrore (
                    StringUtils.abbreviate ( ( StringUtils.isBlank ( te.getMessage () ) ? te.getMessage () : "Errore in fase di aggiornamento" )
                        .concat ( ExceptionUtils.getStackTrace ( te ) ), 500 ) )
                .withIuv ( input.getIuv () ).withRemoteHost ( input.getIpChiamante () )
                .withTimestampChiamata ( new Date () ).build (), nomeMetodo );
        } catch ( PosizioneDebitoriaException pde ) {
            result = AggiornaPosizioneDebitoriaChiamanteEsternoOutput.builder ()
                .withResult ( Esito.builder ().withCode ( pde.getCode () ).withDescription ( pde.getMessage () ).build () ).build ();
            tracciaFallimentoSuDB ( ChiamataEsternaNonValida.builder ().withCodiceChiamante ( input.getCodiceChiamante () )
                .withCodiceFiscale ( input.getSoggettoPagatore () != null ? input.getSoggettoPagatore ().getIdentificativoUnivocoFiscale () : null )
                .withDescrizioneErrore (
                    StringUtils.abbreviate ( ( StringUtils.isBlank ( pde.getMessage () ) ? pde.getMessage () : "Errore in fase di aggiornamento" )
                        .concat ( ExceptionUtils.getStackTrace ( pde ) ), 500 ) )
                .withIuv ( input.getIuv () ).withRemoteHost ( input.getIpChiamante () )
                .withTimestampChiamata ( new Date () ).build (), nomeMetodo );
        } catch ( Exception t ) {
            result = AggiornaPosizioneDebitoriaChiamanteEsternoOutput.builder ().withResult ( Esito.builder ().withCode ( EsitoChiamataEsterna.ERRORE_GENERICO )
                .withDescription ( "Errore in fase di aggiornamento della posizione debitoria." ).build () ).build ();
            tracciaFallimentoSuDB ( ChiamataEsternaNonValida.builder ().withCodiceChiamante ( input.getCodiceChiamante () )
                .withCodiceFiscale ( input.getSoggettoPagatore () != null ? input.getSoggettoPagatore ().getIdentificativoUnivocoFiscale () : null )
                .withDescrizioneErrore (
                    StringUtils.abbreviate ( ( StringUtils.isBlank ( t.getMessage () ) ? t.getMessage () : "Errore in fase di aggiornamento" )
                        .concat ( ExceptionUtils.getStackTrace ( t ) ), 500 ) )
                .withIuv ( input.getIuv () ).withRemoteHost ( input.getIpChiamante () )
                .withTimestampChiamata ( new Date () ).build (), nomeMetodo );
        }
        return result;
    }

    
    @TransactionAttribute ( TransactionAttributeType.REQUIRED )
    @Override
    public AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioOutput
        aggiornaPosizioneDebitoriaMultibeneficiarioChiamanteEsterno ( AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioInput input ) {
        String nomeMetodo = "aggiornaPosizioneDebitoriaChiamanteEsterno";
        AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioOutput result;
        
        try {
            result = doAggiornaPosizioneDebitoriaMultibeneficiarioChiamanteEsterno ( input );
        } catch ( TestataException te ) {
            result = AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioOutput.builder ()
                .withResult ( Esito.builder ().withCode ( te.getCode () ).withDescription ( te.getMessage () ).build () ).build ();
            tracciaFallimentoSuDB ( ChiamataEsternaNonValida.builder ().withCodiceChiamante ( input.getCodiceChiamante () )
                .withCodiceFiscale ( input.getSoggettoPagatore () != null ? input.getSoggettoPagatore ().getIdentificativoUnivocoFiscale () : null )
                .withDescrizioneErrore (
                    StringUtils.abbreviate ( ( StringUtils.isBlank ( te.getMessage () ) ? te.getMessage () : "Errore in fase di aggiornamento" )
                        .concat ( ExceptionUtils.getStackTrace ( te ) ), 500 ) )
                .withIuv ( input.getIuv () ).withRemoteHost ( input.getIpChiamante () )
                .withTimestampChiamata ( new Date () ).build (), nomeMetodo );
        } catch ( PosizioneDebitoriaException pde ) {
            result = AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioOutput.builder ()
                .withResult ( Esito.builder ().withCode ( pde.getCode () ).withDescription ( pde.getMessage () ).build () ).build ();
            tracciaFallimentoSuDB ( ChiamataEsternaNonValida.builder ().withCodiceChiamante ( input.getCodiceChiamante () )
                .withCodiceFiscale ( input.getSoggettoPagatore () != null ? input.getSoggettoPagatore ().getIdentificativoUnivocoFiscale () : null )
                .withDescrizioneErrore (
                    StringUtils.abbreviate ( ( StringUtils.isBlank ( pde.getMessage () ) ? pde.getMessage () : "Errore in fase di aggiornamento" )
                        .concat ( ExceptionUtils.getStackTrace ( pde ) ), 500 ) )
                .withIuv ( input.getIuv () ).withRemoteHost ( input.getIpChiamante () )
                .withTimestampChiamata ( new Date () ).build (), nomeMetodo );
        } catch ( Exception t ) {
            result = AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioOutput.builder ()
                .withResult ( Esito.builder ().withCode ( EsitoChiamataEsterna.ERRORE_GENERICO )
                    .withDescription ( "Errore in fase di aggiornamento della posizione debitoria." ).build () )
                .build ();
            tracciaFallimentoSuDB ( ChiamataEsternaNonValida.builder ().withCodiceChiamante ( input.getCodiceChiamante () )
                .withCodiceFiscale ( input.getSoggettoPagatore () != null ? input.getSoggettoPagatore ().getIdentificativoUnivocoFiscale () : null )
                .withDescrizioneErrore (
                    StringUtils.abbreviate ( ( StringUtils.isBlank ( t.getMessage () ) ? t.getMessage () : "Errore in fase di aggiornamento" )
                        .concat ( ExceptionUtils.getStackTrace ( t ) ), 500 ) )
                .withIuv ( input.getIuv () ).withRemoteHost ( input.getIpChiamante () )
                .withTimestampChiamata ( new Date () ).build (), nomeMetodo );
        }
        return result;
    }


    @TransactionAttribute ( TransactionAttributeType.REQUIRED )
    @Override
    public GetStatoPosizioneDebitoriaOutput getStatoPosizioneDebitoria ( GetStatoPosizioneDebitoriaInput input ) {
		String nomeMetodo = "getStatoPosizioneDebitoria";
        GetStatoPosizioneDebitoriaOutput result;
        try {
            result = doGetStatoPosizioneDebitoria ( input );
        } catch ( TestataException te ) {
            result = GetStatoPosizioneDebitoriaOutput.builder ()
                .withResult ( Esito.builder ().withCode ( te.getCode () ).withDescription ( te.getMessage () ).build () ).build ();
			tracciaFallimentoSuDB ( ChiamataEsternaNonValida.builder ().withCodiceChiamante ( input.getCodiceChiamante () )
							.withDescrizioneErrore (
											StringUtils.abbreviate ( ( StringUtils.isBlank ( te.getMessage () ) ? te.getMessage () : "Errore in fase di creazione testata" )
															.concat ( ExceptionUtils.getStackTrace ( te ) ), 500 ) )
							.withIuv ( input.getIuv () ).withRemoteHost ( input.getIpChiamante () )
							.withTimestampChiamata ( new Date () ).build (), nomeMetodo );
        } catch ( PosizioneDebitoriaException pde ) {
            result = GetStatoPosizioneDebitoriaOutput.builder ()
                            .withResult ( Esito.builder ().withCode ( pde.getCode () ).withDescription ( pde.getMessage () ).build () ).build ();
		} catch ( Exception t ) {
            result = GetStatoPosizioneDebitoriaOutput.builder ()
                .withResult ( Esito.builder ().withCode ( EsitoChiamataEsterna.ERRORE_GENERICO )
                    .withDescription ( "Errore in fase di ricerca stato posizione debitoria." ).build () )
                .build ();
			tracciaFallimentoSuDB ( ChiamataEsternaNonValida.builder ().withCodiceChiamante ( input.getCodiceChiamante () )
							.withDescrizioneErrore (
											StringUtils.abbreviate ( ( StringUtils.isBlank ( t.getMessage () ) ? t.getMessage () : "Errore ricerca stato posizione debitoria" )
															.concat ( ExceptionUtils.getStackTrace ( t ) ), 500 ) )
							.withIuv ( input.getIuv () ).withRemoteHost ( input.getIpChiamante () )
							.withTimestampChiamata ( new Date () ).build (), nomeMetodo );
        }
        return result;
    }

    @TransactionAttribute ( TransactionAttributeType.REQUIRED )
    @Override
    public GetDatiPagamentoChiamanteEsternoOutput getDatiPagamentoChiamanteEsterno ( GetDatiPagamentoChiamanteEsternoInput input ) {
		String nomeMetodo = "getDatiPagamentoChiamanteEsterno";
        GetDatiPagamentoChiamanteEsternoOutput result;
        try {
            result = doGetDatiPagamentoChiamanteEsterno ( input );
        } catch ( TestataException te ) {
            result = GetDatiPagamentoChiamanteEsternoOutput.builder ()
                .withResult ( Esito.builder ().withCode ( te.getCode () ).withDescription ( te.getMessage () ).build () ).build ();
			tracciaFallimentoSuDB ( ChiamataEsternaNonValida.builder ().withCodiceChiamante ( input.getCodiceChiamante () )
							.withDescrizioneErrore (
											StringUtils.abbreviate ( ( StringUtils.isBlank ( te.getMessage () ) ? te.getMessage () : "Errore in fase di creazione testata" )
															.concat ( ExceptionUtils.getStackTrace ( te ) ), 500 ) )
							.withIuv ( input.getIuv () ).withRemoteHost ( input.getIpChiamante () )
							.withTimestampChiamata ( new Date () ).build (), nomeMetodo );
        } catch ( PosizioneDebitoriaException pde ) {
            result = GetDatiPagamentoChiamanteEsternoOutput.builder ()
                .withResult ( Esito.builder ().withCode ( pde.getCode () ).withDescription ( pde.getMessage () ).build () ).build ();
        } catch ( Exception t ) {
            result = GetDatiPagamentoChiamanteEsternoOutput.builder ().withResult ( Esito.builder ().withCode ( EsitoChiamataEsterna.ERRORE_GENERICO )
                .withDescription ( "Errore in fase di aggiornamento della posizione debitoria." ).build () ).build ();
			tracciaFallimentoSuDB ( ChiamataEsternaNonValida.builder ().withCodiceChiamante ( input.getCodiceChiamante () )
							.withDescrizioneErrore (
											StringUtils.abbreviate ( ( StringUtils.isBlank ( t.getMessage () ) ? t.getMessage () : "Errore ricerca dati pagamento" )
															.concat ( ExceptionUtils.getStackTrace ( t ) ), 500 ) )
							.withIuv ( input.getIuv () ).withRemoteHost ( input.getIpChiamante () )
							.withTimestampChiamata ( new Date () ).build (), nomeMetodo );
        }
        return result;
    }

    @TransactionAttribute ( TransactionAttributeType.REQUIRED )
    @Override
    public EliminaPosizioneDebitoriaChiamanteEsternoOutput eliminaPosizioneDebitoriaChiamanteEsterno ( EliminaPosizioneDebitoriaChiamanteEsternoInput input ) {
        String methodName = "eliminaPosizioneDebitoriaChiamanteEsternoV1";

        log.debugStart ( methodName );

        EliminaPosizioneDebitoriaChiamanteEsternoOutput result;
        try {
            result = doEliminaPosizioneDebitoriaChiamanteEsterno ( input );
        } catch ( TestataException te ) {
            result = EliminaPosizioneDebitoriaChiamanteEsternoOutput.builder ()
                .withResult ( Esito.builder ().withCode ( te.getCode () ).withDescription ( te.getMessage () ).build () ).build ();
            tracciaFallimentoSuDB ( ChiamataEsternaNonValida.builder ().withCodiceChiamante ( input.getCodiceChiamante () )
                .withDescrizioneErrore (
                                StringUtils.abbreviate ( ( StringUtils.isBlank ( te.getMessage () ) ? te.getMessage () : "Errore in fase di creazione testata" )
                                                .concat ( ExceptionUtils.getStackTrace ( te ) ), 500 ) )
                .withIuv ( input.getIuv () ).withRemoteHost ( input.getIpChiamante () )
                .withTimestampChiamata ( new Date () ).build (), methodName );
        } catch ( PosizioneDebitoriaException pde ) {
            result = EliminaPosizioneDebitoriaChiamanteEsternoOutput.builder ()
                .withResult ( Esito.builder ().withCode ( pde.getCode () ).withDescription ( pde.getMessage () ).build () ).build ();
            tracciaFallimentoSuDB ( ChiamataEsternaNonValida.builder ().withCodiceChiamante ( input.getCodiceChiamante () )
                .withDescrizioneErrore (
                                StringUtils.abbreviate ( ( StringUtils.isBlank ( pde.getMessage () ) ? pde.getMessage () : "Errore con la posizione debitoria" )
                                                .concat ( ExceptionUtils.getStackTrace ( pde ) ), 500 ) )
                .withIuv ( input.getIuv () ).withRemoteHost ( input.getIpChiamante () )
                .withTimestampChiamata ( new Date () ).build (), methodName );
        } catch ( Exception t ) {
            result = EliminaPosizioneDebitoriaChiamanteEsternoOutput.builder ().withResult ( Esito.builder ().withCode ( EsitoChiamataEsterna.ERRORE_GENERICO )
                .withDescription ( "Errore in fase di cancellazione della posizione debitoria." ).build () ).build ();
            tracciaFallimentoSuDB ( ChiamataEsternaNonValida.builder ().withCodiceChiamante ( input.getCodiceChiamante () )
                .withDescrizioneErrore (
                                StringUtils.abbreviate ( ( StringUtils.isBlank ( t.getMessage () ) ? t.getMessage () : "Errore ricerca dati pagamento" )
                                                .concat ( ExceptionUtils.getStackTrace ( t ) ), 500 ) )
                .withIuv ( input.getIuv () ).withRemoteHost ( input.getIpChiamante () )
                .withTimestampChiamata ( new Date () ).build (), methodName );
        }
        return result;
    }

	@Override 
	public PagamentoPerStampaAvvisoOutput ricercaPagamentoByIUV ( PagamentoPerStampaAvvisoInput input ) {
		String methodName = "ricercaPagamentoByIUV";
		log.debugStart ( methodName );
		Pagamento pagamento;
		// controllo iuv
		try {
			pagamentoFacade.ricerca ( input.getIuv () );
		} catch ( NoDataException e ) {
			return setErrorPerStampaAvviso ( Messages.IUV_INESISTENTE.getMessage () );
		}
		// controllo pagamento
		try {
			pagamento = pagamentoFacade.ricercaSoloAttivi ( input.getIuv () );
			if (null == pagamento) {
			    return setErrorPerStampaAvviso ( Messages.PAGAMENTO_NON_EFFETTUABILE.getMessage () );
			}
		} catch ( NoDataException e ) {
			return setErrorPerStampaAvviso ( Messages.PAGAMENTO_NON_EFFETTUABILE.getMessage () );
		}
		// controllo ente
		Ente ente;
		try {
			ente = verificaEsistenzaEnte ( input.getOrganization () );
			log.info ( methodName, "recuperato ente : " + input.getOrganization () );
		} catch ( TestataException e ) {
			return setErrorPerStampaAvviso ( CodiciEsito.CF_ENTE_ERRORE.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH, input.getOrganization () ) );
		}
		// controllo tipo pagamento
		TipoPagamento tipoPagamento;
		try {
			tipoPagamento = verificaTipoPagamento ( ente, input.getPaymentType (), true, false );
		} catch ( TestataException e ) {
			return setErrorPerStampaAvviso ( e.getMessage () );
		}
		// controllo codice chiamante esterno
		try {
			validaChiamanteEsterno ( input.getCodiceChiamante (), "STAMPA_AVVISO_PAGAMENTO", tipoPagamento.getIdTipoPagamento () );
		} catch ( PosizioneDebitoriaException e ) {
			return setErrorPerStampaAvviso ( e.getMessage () );
		}
		PagamentoPerStampaAvvisoOutput pagamentoPerStampaAvvisoOutput = new PagamentoPerStampaAvvisoOutput ();
		pagamentoPerStampaAvvisoOutput.setPagamento ( pagamento );
		log.debugEnd ( methodName );
		return pagamentoPerStampaAvvisoOutput;
	}

	private PagamentoPerStampaAvvisoOutput setErrorPerStampaAvviso ( String message ) {
		PagamentoPerStampaAvvisoOutput pagamentoPerStampaAvvisoOutput = new PagamentoPerStampaAvvisoOutput ();
		pagamentoPerStampaAvvisoOutput.setMessage ( message );
		pagamentoPerStampaAvvisoOutput.setError ( true );
		return pagamentoPerStampaAvvisoOutput;
	}

	@Override public Rt ricercaRtByIuv ( String iuv ) throws NoDataException {
		String methodName = "ricercaRtByIuv";
		log.debugStart ( methodName );
		Rt rt = pagamentoFacade.ricercaRtByIuv ( iuv );
		log.debugEnd ( methodName );
		return rt;
	}

	@Override public DatiAvvisoPagamento ricercaDatiAvvisoPagamentoByIdTipoPagamento ( Long idTipoPagamento ) throws NoDataException {
		String methodName = "ricercaDatiAvvisoPagamentoByIdTipoPagamento";
		log.debugStart ( methodName );
		DatiAvvisoPagamento datiAvvisoPagamento = datiAvvisoPagamentoFacade.ricerca ( idTipoPagamento );
		log.debugEnd ( methodName );
		return datiAvvisoPagamento;
	}

    @Override
    public DatiPDFReport ricercaTemplatePDFStampaAvviso ( String CodiceTemplate ) throws NoDataException {
        String methodName = "ricercaTemplatePDFStampaAvviso";
        log.debugStart ( methodName );
        DatiPDFReport datiPdf = datiAvvisoPagamentoFacade.getDatiPDFReport ( CodiceTemplate );
        log.debugEnd ( methodName );
        return datiPdf;
    }

    @Override
    public void aggiornaTemplateCompilato ( Long id, byte [] templateCompilato ) throws NoDataException {
        String methodName = "ricercaTemplatePDFStampaAvviso";
        log.debugStart ( methodName );
        datiAvvisoPagamentoFacade.aggiornaTemplateCompilato ( id, templateCompilato );
        log.debugEnd ( methodName );
    }

	/**
	 * Validazioni comuni del chiamante esterno.
	 *
	 * @param codiceChiamante         String
	 * @param autorizzazioneChiamante String
	 * @param idTipoPagamento         Long
	 * @throws PosizioneDebitoriaException PosizioneDebitoriaException
	 */
    private void validaChiamanteEsterno ( String codiceChiamante,  String autorizzazioneChiamante, Long idTipoPagamento )
                    throws PosizioneDebitoriaException {
        ChiamanteEsterno chiamante;
        try {
            chiamante = chiamataEsternaManager.recuperaChiamanteEsterno ( codiceChiamante );
            log.info ( "validaChiamanteEsterno", "recuperato chiamante : " + codiceChiamante );
        } catch ( IllegalAccessException | NoDataException e1 ) {
            throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_GESTIONALE_NON_AUTORIZZATO_AL_TIPO_PAGAMENTO,
                "Chiamante [" + codiceChiamante + "] non trovato" );
        }

        Date today = Date.from ( LocalDate.now ().atStartOfDay ( ZoneId.systemDefault () ).toInstant () );

        if ( chiamante.getDataInizioValidita () == null || chiamante.getDataInizioValidita ().after ( today ) ) {
            throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                "Chiamante [" + codiceChiamante + "] non ancora in periodo di validita'" );
        }

        if ( chiamante.getDataFineValidita () != null && chiamante.getDataFineValidita ().before ( today ) ) {
            throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                "Chiamante [" + codiceChiamante + "] non piu' valido" );
        }
        try {
            if ( !chiamataEsternaSincronaSplitManager.verificaAutorizzazioneChiamanteEsternoAutorizzazioneChiamante ( codiceChiamante,
                autorizzazioneChiamante ) ) {
                throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_GESTIONALE_NON_AUTORIZZATO_AL_TIPO_PAGAMENTO,
                    "Chiamante [" + codiceChiamante + "] non autorizzato." );
            }
        } catch ( IllegalArgumentException | NoDataException e1 ) {
            throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_GESTIONALE_NON_AUTORIZZATO_AL_TIPO_PAGAMENTO,
                "Errore nella verifica delle autorizzazioni del chiamante e tipo autorizzazioni." );
        }
        if (idTipoPagamento != null)
        {
            try {
                if ( !chiamataEsternaSincronaSplitManager.verificaAutorizzazioneChiamanteEsterno ( codiceChiamante, idTipoPagamento ) ) {
                    throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_GESTIONALE_NON_AUTORIZZATO_AL_TIPO_PAGAMENTO,
                        "Chiamante [" + codiceChiamante + "] non autorizzato al tipo pagamento." );
                }
            } catch ( IllegalArgumentException | NoDataException e1 ) {
                throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_GESTIONALE_NON_AUTORIZZATO_AL_TIPO_PAGAMENTO,
                    "Errore nella verifica delle autorizzazioni del gestionale per i tipi pagamento." );
            }
        }
        
	}
    
    /**
     * 
     * @param input EliminaPosizioneDebitoriaChiamanteEsternoInput
     * @return EliminaPosizioneDebitoriaChiamanteEsternoOutput
     * @throws TestataException TestataException
     * @throws PosizioneDebitoriaException PosizioneDebitoriaException
     */
    private EliminaPosizioneDebitoriaChiamanteEsternoOutput
        doEliminaPosizioneDebitoriaChiamanteEsterno ( EliminaPosizioneDebitoriaChiamanteEsternoInput input ) throws TestataException, PosizioneDebitoriaException {
        final String methodName = "EliminaPosizioneDebitoriaChiamanteEsternoOutput";
        log.debugStart ( methodName );

        Ente ente;
        TipoPagamento tipoPagamento;
        ente = verificaEsistenzaEnte ( input.getCodiceFiscaleEnte () );
        log.info ( methodName, "recuperato ente : " + ente.getCodiceFiscale () );

        tipoPagamento = verificaTipoPagamento ( ente, input.getTipoPagamento (), true, true );

        testField ( input.getIuv (), CodiciEsito.CODICE_ID_POSIZIONE_DEBITORIA_OBBLIGATORIO );

        validaChiamanteEsterno ( input.getCodiceChiamante (), "CREAZIONE_IUV", tipoPagamento.getIdTipoPagamento () );
        
        Pagamento pagamento;
        EpayTPagamento tPagamento;

        try {
            tPagamento = pagamentoManager.getPagamentoPerCFEnteECovEIuv ( ente.getCodiceFiscale (), tipoPagamento.getCodiceVersamento (),
                input.getIuv () );
            if ( tPagamento == null ) {
                throw new NoDataException ();
            }
            pagamento = pagamentoManager.mappaPagamentoEsteso ( tPagamento );
        } catch ( NoDataException e ) {
            CodiciEsito ce = CodiciEsito.PAGAMENTO_NON_TROVATO_PER_POSIZIONE_DEBITORIA;
            throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH, ente.getCodiceFiscale (),
                    tipoPagamento.getCodiceVersamento (), input.getIuv () ) );
        } catch ( MoreResultException e ) {
            CodiciEsito ce = CodiciEsito.CODICE_ID_POSIZIONE_DEBITORIA_DUPLICATA;
            throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH, ente.getCodiceFiscale (),
                    tipoPagamento.getCodiceVersamento (), input.getIuv () ) );
        }

        verificaStato ( pagamento );
        pagamentoManager.aggiornaStato ( pagamento.getIdPagamento (), StatoPagamento.INVALIDATO );

        RegistroVersamenti registroVersamenti = new RegistroVersamenti ();
        registroVersamenti.setIdPagamento ( pagamento.getIdPagamento () );
        registroVersamenti.setDataOperazione ( null );
        registroVersamenti.setIdStato ( StatoPagamento.INVALIDATO.getId () );
        registroVersamenti.setRisultato ( StatoPagamento.INVALIDATO.getDescrizione () );
        registroVersamenti.setOrigineInserimento ( this.getClass ().getName () );
        registroVersamentiManager.inserisci ( registroVersamenti );
        
        try {
            ChiamanteEsterno chiamante = new ChiamanteEsterno ();
            chiamante.setCodiceChiamante ( input.getCodiceChiamante () );
            tracciaChiamataSuDB ( TracciabilitaChiamanteEsterno.builder ().withChiamanteEsterno ( chiamante )
                            .withIuv ( input.getIuv () )
                            .withIdentificativoPagamento ( tPagamento.getCodicePagamentoRifEnte () )
                            .withRemoteHost ( input.getIpChiamante () )
                            .withTimestampChiamata ( new Date () ).build (), methodName );
        } catch ( Exception e ) {
            log.warn ( methodName, "Errore in fase di tracciatura del pagamento: "+ input.getIuv ());
        }
        
        log.debugEnd ( methodName );
        return EliminaPosizioneDebitoriaChiamanteEsternoOutput.builder ().withResult ( Esito.builder ().withCode ( CodiciEsito.ESECUZIONE_OK.getCodice () )
            .withDescription ( CodiciEsito.ESECUZIONE_OK.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH ) ).build () ).build ();
    }
    /**
     * 
     * @param input GetDatiPagamentoChiamanteEsternoInput
     * @return GetDatiPagamentoChiamanteEsternoOutput
     * @throws PosizioneDebitoriaException, TestataException
     */
	private GetDatiPagamentoChiamanteEsternoOutput doGetDatiPagamentoChiamanteEsterno ( GetDatiPagamentoChiamanteEsternoInput input )
					throws PosizioneDebitoriaException, TestataException {
		final String methodName = "doGetDatiPagamentoChiamanteEsterno";
		log.debugStart ( methodName );
		GetDatiPagamentoChiamanteEsternoOutput res = GetDatiPagamentoChiamanteEsternoOutput.builder ().build ();
		Ente ente = verificaEsistenzaEnte ( input.getCodiceFiscaleEnte () );
		log.info ( methodName, "recuperato ente : " + ente.getCodiceFiscale () );
		testField ( input.getIuv (), CodiciEsito.CODICE_ID_POSIZIONE_DEBITORIA_OBBLIGATORIO );
		verificaTipoPagamento ( ente, input.getPaymentType (), false, false );
		Pagamento pagamento;
		EpayTPagamento tPagamento;
		try {
			tPagamento = pagamentoManager.getPagamentoPerCFEnteECovEIuv ( ente.getCodiceFiscale (), input.getPaymentType (), input.getIuv () );
			if ( tPagamento == null ) {
				tracciaErroreChiamataEsterna ( input, "Pagamento non trovato" );
				throw new NoDataException ();
			}
			pagamento = pagamentoManager.mappaPagamentoEsteso ( tPagamento );
		} catch ( NoDataException e ) {
			CodiciEsito ce = CodiciEsito.PAGAMENTO_NON_TROVATO_PER_IDENTIFICATIVO_PAGAMENTO;
			String messaggio = ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH, ente.getCodiceFiscale (), input.getPaymentType (), input.getIuv () );
			tracciaErroreChiamataEsterna ( input, messaggio );
			throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, messaggio );
		} catch ( MoreResultException e ) {
			CodiciEsito ce = CodiciEsito.CODICE_ID_POSIZIONE_DEBITORIA_DUPLICATA;
			String messaggio = ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH, ente.getCodiceFiscale (), input.getIuv () );
			tracciaErroreChiamataEsterna ( input, messaggio );
			throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, messaggio );
		}
		TipoPagamento tipoPagamento = tipoPagamentoManager.getByIdPagamento ( pagamento.getIdPagamento () );
		validaChiamanteEsterno ( input.getCodiceChiamante (), "RICHIESTA_DATI_PAGAMENTO", tipoPagamento.getIdTipoPagamento () );
		res.setImportoPagatoTotale ( pagamento.getImporto () );
		res.setEnteBeneficiario ( ente.getNome () );
		res.setCodiceFiscaleEnteBeneficiario ( ente.getCodiceFiscale () );
		res.setTipologiaVersamento ( tipoPagamento.getDescrizionePortale () );
		res.setImportoPagato ( pagamento.getImportoPrincipale () );
		// gestione multibeneficiario
		if ( tipoPagamento.getFlagMultibeneficiario () != null && tipoPagamento.getFlagMultibeneficiario () ) {
			PagamentoSecondario pagamentoSecondario = pagamentoSecondarioManager.getPagamentoSecondario ( pagamento.getIdPagamento () );
			List<GetDatiPagamentoChiamanteEsternoMultibeneficiarioOutput> list = new ArrayList<> ();
			GetDatiPagamentoChiamanteEsternoMultibeneficiarioOutput el = new GetDatiPagamentoChiamanteEsternoMultibeneficiarioOutput ();
			try {
				Ente enteSecondario = enteManager.getByIdPagamentoSecondario ( pagamentoSecondario.getIdPagamentoSecondario () );
				el.setEnteBeneficiario ( enteSecondario.getNome () );
			} catch ( NoDataException e ) {
				CodiciEsito ce = CodiciEsito.PAGAMENTO_SECONDARIO_ENTE_NON_TROVATO;
				String messaggio = ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH, ente.getCodiceFiscale (), input.getIuv () );
				tracciaErroreChiamataEsterna ( input, messaggio );
				throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, messaggio );
			}
			el.setCodiceFiscaleEnteBeneficiario ( pagamentoSecondario.getIdentificativoDominio () );
			el.setTipologiaVersamento ( pagamentoSecondario.getCausale () );
			el.setImportoPagato ( pagamentoSecondario.getImportoComplessivo () );
			list.add ( el );
			res.setDatiMultibeneficiario ( list );
		}
		// gestione anagrafica
		Anagrafica anagrafica = anagraficaManager.getAnagrafica ( pagamento.getPagatore ().getIdAnagrafica () );
		if ( StringUtils.isNoneEmpty ( anagrafica.getRagioneSociale () ) ) {
			res.setNomeECognomeRagioneSociale ( anagrafica.getRagioneSociale () );
		} else {
			res.setNomeECognomeRagioneSociale ( anagrafica.getNome () + " " + anagrafica.getCognome () );
		}
		res.setCodiceFiscalePIva ( anagrafica.getCodiceFiscale () );
		res.setCodiceAvviso ( tPagamento.getAuxDigit () + tPagamento.getIuvNumeroAvviso () );
		res.setIdentificativoUnicoVersamento ( tPagamento.getIuvNumeroAvviso () );
		// gestione registro versamenti
		try {
			// registroVersamentiManager.ricercaUltimoByIdPagamentoPrimario ( pagamento.getIdPagamento () ); controllo tolto, perche'?
			EsitiRicevuti esitiRicevuti = esitiRicevutiManager.ricercaEsitiRicevutiByIdPagamento ( pagamento.getIdPagamento () );
			if ( null != esitiRicevuti ) {
				res.setNumeroTransazione ( esitiRicevuti.getIdTransazione () );
				res.setPrestatoreDiServiziDiPagamento ( esitiRicevuti.getRagioneSocialePsp () );
				res.setDataEOraOperazione ( esitiRicevuti.getDataOraOperazione () );
				res.setDataEsitoPagamento ( esitiRicevuti.getDataPagamento () );
				res.setIdentificativoUnicoRiscossione ( esitiRicevuti.getIur () );
			}
		} catch ( NoDataException e ) {
			log.info ( methodName, "registro versamento non trovato per pagamento id : " + pagamento.getIdPagamento () );
		}
		res.setInfoAggiuntive ( pagamento.getCausale () );
        res.setResult ( Esito.builder ().withCode ( CodiciEsito.ESECUZIONE_OK.getCodice () )
            .withDescription ( CodiciEsito.ESECUZIONE_OK.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH ) ).build () );
        try {
            ChiamanteEsterno chiamante = new ChiamanteEsterno ();
            chiamante.setCodiceChiamante ( input.getCodiceChiamante () );
            tracciaChiamataSuDB ( TracciabilitaChiamanteEsterno.builder ().withChiamanteEsterno ( chiamante )
                            .withIuv ( input.getIuv () )
                            .withIdentificativoPagamento ( tPagamento.getCodicePagamentoRifEnte () )
                            .withRemoteHost ( input.getIpChiamante () )
                            .withTimestampChiamata ( new Date () ).build (), methodName );
        } catch ( Exception e ) {
            log.warn ( methodName, "Errore in fase di tracciatura del pagamento: "+ input.getIuv ());
        }
		log.debugEnd ( methodName );
		return res;
	}

    /**
     * Implementazione della logica di business del metodo
     * @param input GetStatoPosizioneDebitoriaInput
     * @return GetStatoPosizioneDebitoriaOutput
     * @throws TestataException TestataException
     * @throws PosizioneDebitoriaException PosizioneDebitoriaException
     */
    private GetStatoPosizioneDebitoriaOutput doGetStatoPosizioneDebitoria ( GetStatoPosizioneDebitoriaInput input )
                    throws TestataException, PosizioneDebitoriaException {
        final String methodName = "doGetStatoPosizioneDebitoria";
        log.debugStart ( methodName );
        Ente ente = verificaEsistenzaEnte ( input.getCodiceFiscaleEnte () );
        log.info ( methodName, "recuperato ente : " + ente.getCodiceFiscale () );
        testField ( input.getIuv (), CodiciEsito.CODICE_IUV_OBBLIGATORIO );
		verificaTipoPagamento ( ente, input.getPaymentType (), false, false );
        EpayTPagamento tPagamento;
        try {
            tPagamento = pagamentoManager.getPagamentoPerCFEnteECovEIuv ( ente.getCodiceFiscale (), input.getPaymentType (), input.getIuv () );
            if ( tPagamento == null ) {
                tracciaErroreChiamataEsterna ( input, "Pagamento non trovato" );
                throw new NoDataException ();
            }
        } catch ( NoDataException e ) {
            CodiciEsito ce = CodiciEsito.PAGAMENTO_NON_TROVATO_PER_IDENTIFICATIVO_PAGAMENTO;
            String messaggio = ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH, ente.getCodiceFiscale (), input.getPaymentType (), input.getIuv () );
            tracciaErroreChiamataEsterna ( input, messaggio );
            throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, messaggio );
        } catch ( MoreResultException e ) {
            CodiciEsito ce = CodiciEsito.CODICE_ID_POSIZIONE_DEBITORIA_DUPLICATA;
            String messaggio = ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH, ente.getCodiceFiscale (), input.getIuv () );
            tracciaErroreChiamataEsterna ( input, messaggio );
            throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, messaggio );
        }
        validaChiamanteEsterno ( input.getCodiceChiamante (), "RICHIESTA_STATO_POS_DEB", tPagamento.getEpayTTipoPagamento ().getIdTipoPagamento () );
        int idStato = tPagamento.getEpayDStatoPagamento ().getIdStato ();
        
        Stato stato;
        switch ( idStato ) {
        case 4 :
        case 11 :
            stato = Stato.builder ().withCode ( "0" ).withDescription ( "Pagato" ).build ();
            break;
        case -1 :
        case 0 :
        case 1 :
        case 2 :
        case 3 :
        case 5 :
        case 6 :
        case 7 :
        case 8 :
        case 10 :
//            se modificabile e data inizio maggiore di oggi-> 3

            if (Boolean.TRUE.equals ( tPagamento.getEpayDStatoPagamento ().getModificabile () )   
                            && null!= tPagamento.getInizioValidita () && 
                            tPagamento.getInizioValidita ().after (Date.from ( LocalDate.now ().atStartOfDay ( ZoneId.systemDefault () ).toInstant () )))
            {
                stato = Stato.builder ().withCode ( "3" ).withDescription ( "Non ancora attiva" ).build ();
            }
//          se modificabile e data fine minore di oggi -> 4
            else if (Boolean.TRUE.equals ( tPagamento.getEpayDStatoPagamento ().getModificabile () )   
                            && null!= tPagamento.getFineValidita () && 
                            tPagamento.getFineValidita ().before (Date.from ( LocalDate.now ().atStartOfDay ( ZoneId.systemDefault () ).toInstant () )))
            {
                stato = Stato.builder ().withCode ( "4" ).withDescription ( "Non piu' attiva" ).build ();
            }
            else
            {
                stato = Stato.builder ().withCode ( "1" ).withDescription ( "Non Pagato" ).build ();
            }
            break;
        case 9 :
            stato = Stato.builder ().withCode ( "2" ).withDescription ( "Annullato dall'ente" ).build ();
            break;
        default :
            stato = Stato.builder ().withCode ( "-1" ).withDescription ( "Indefinito" ).build ();
            break;
        }
        
        try {
            ChiamanteEsterno chiamante = new ChiamanteEsterno ();
            chiamante.setCodiceChiamante ( input.getCodiceChiamante () );
            tracciaChiamataSuDB ( TracciabilitaChiamanteEsterno.builder ().withChiamanteEsterno ( chiamante )
                            .withIuv ( input.getIuv () )
                            .withIdentificativoPagamento ( tPagamento.getCodicePagamentoRifEnte () )
                            .withRemoteHost ( input.getIpChiamante () )
                            .withTimestampChiamata ( new Date () ).build (), methodName );
        } catch ( Exception e ) {
            log.warn ( methodName, "Errore in fase di tracciatura del pagamento: "+ input.getIuv ());
        }
        
        
        log.debugEnd ( methodName );
        return GetStatoPosizioneDebitoriaOutput.builder ().withCode ( stato.getCode () )
                        .withDescription  ( stato.getDescription () )
            .withResult ( Esito.builder ().withCode ( CodiciEsito.ESECUZIONE_OK.getCodice () )
                .withDescription ( CodiciEsito.ESECUZIONE_OK.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH ) ).build () )
            .build ();
    }

    /**
     */
    private void verificaCompatibilitaFlagMultibeneficiario ( TipoPagamento tipoPagamento, Boolean flagMultiBeneficiario ) throws TestataException {
        final String methodName = "verificaCompatibilitaFlagMultibeneficiario";
        log.debug ( methodName, "OK" );
        log.debugEnd ( methodName );
        //Il pagamento principale deve avere il flagMultibeneficiario
        //Uguale a quello passato nella richiesta
        boolean btipoPagFlagMulti = tipoPagamento.getFlagMultibeneficiario () != null && tipoPagamento.getFlagMultibeneficiario ();
        boolean bFlagMultiDaRequest = flagMultiBeneficiario != null && flagMultiBeneficiario;
        if ( bFlagMultiDaRequest && !btipoPagFlagMulti ) {
            log.debug ( methodName, "KO2" );
            log.debugEnd ( methodName );
            //Da scatenare solo per i principali
            CodiciEsito ce = CodiciEsito.ERRORE_TIPO_PAGAMENTO_NON_MULTIBENEFICIARIO;
            throw new TestataException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH, tipoPagamento.getCodiceVersamento () ) );
        }
        if ( btipoPagFlagMulti && !bFlagMultiDaRequest ) {
            log.debug ( methodName, "KO2" );
            log.debugEnd ( methodName );
            //Da scatenare solo per i principali
            CodiciEsito ce = CodiciEsito.ERRORE_TIPO_PAGAMENTO_MULTIBENEFICIARIO;
            throw new TestataException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH, tipoPagamento.getCodiceVersamento () ) );
        }
    }
    
    /**
     * Metodo verifica tipo pagamento multibeneficiario.
     */
    private void verificaEsistenzaPagamentoCollegato ( TipoPagamento tipoPagamentoPrincipale ) throws TestataException {
        final String methodName = "verificaEsistenzaPagamentoCollegato";

        log.debugStart ( methodName );
        List<EpayRTipoPagamentoCollegato> listaTipoPagamentoCollegato
            = tipoPagamentoManager.getPagamentoSecondarioByidPagamentoPrincipale ( tipoPagamentoPrincipale );
        if ( listaTipoPagamentoCollegato == null || listaTipoPagamentoCollegato.isEmpty () ) {
            log.debug ( methodName, "KO1" );
            log.debugEnd ( methodName );
            CodiciEsito ce = CodiciEsito.TIPO_PAGAMENTO_SECONDARIO_NON_TROVATO_ERRORE;
            throw new TestataException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH, tipoPagamentoPrincipale.getCodiceVersamento () ) );
        }
        if ( listaTipoPagamentoCollegato.size () > 1 ) {
            log.debug ( methodName, "KO2" );
            log.debugEnd ( methodName );
            CodiciEsito ce = CodiciEsito.TIPO_PAGAMENTO_SECONDARIO_NON_UNIVOCO_ERRORE;
            throw new TestataException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH, tipoPagamentoPrincipale.getCodiceVersamento () ) );
        }
        log.debug ( methodName, "OK" );
        log.debugEnd ( methodName );
    }
    
    /**
     * metodo per aggiornare una posizione debitoria multibeneficiario.
     */
    private AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioOutput
        doAggiornaPosizioneDebitoriaMultibeneficiarioChiamanteEsterno ( AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioInput input ) throws TestataException, PosizioneDebitoriaException, TassonomiaServiceException {
        final String methodName = "aggiornaPosizioneDeboitoriaChiamanteEsternoV1";
        log.debugStart ( methodName );
        
        if (null!= input.getImportoTotale () || null!= input.getImportoPrincipale () ||null!= input.getImportoSecondario () ) {
            testField (  input.getImportoTotale ()  , CodiciEsito.POSIZIONI_DEBITORIE_IMPORTO_TOTALE_OBBLIGATORIO );
            testField (  input.getImportoPrincipale ()  , CodiciEsito.POSIZIONI_DEBITORIE_IMPORTO_PRINCIPALE_OBBLIGATORIO );
            testField (  input.getImportoSecondario ()  , CodiciEsito.POSIZIONI_DEBITORIE_IMPORTO_SECONDARIO_OBBLIGATORIO );
            
            if (input.getImportoTotale ().compareTo ( new BigDecimal ( 0 ) )<=0
                            ||input.getImportoPrincipale ().compareTo ( new BigDecimal ( 0 ) )<=0
                            ||input.getImportoSecondario ().compareTo ( new BigDecimal ( 0 ) )<=0)
            {
                throw new PosizioneDebitoriaException ( CodiciEsito.IMPORTI_OBBLIGATORI.getCodice (), CodiciEsito.IMPORTI_OBBLIGATORI.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH ) );
            }
            
        }

        Ente ente;
        TipoPagamento tipoPagamento;
        ente = verificaEsistenzaEnte ( input.getCodiceFiscaleEnte () );
        log.info ( methodName, "recuperato ente : " + ente.getCodiceFiscale () );
        tipoPagamento = verificaTipoPagamento ( ente, input.getTipoPagamento (), false, true );
        testField ( input.getIuv (), CodiciEsito.CODICE_IUV_OBBLIGATORIO );
        validaChiamanteEsterno ( input.getCodiceChiamante (), "CREAZIONE_IUV", tipoPagamento.getIdTipoPagamento () );
        verificaCompatibilitaFlagMultibeneficiario ( tipoPagamento, Boolean.TRUE );
        if ( Boolean.TRUE.equals ( tipoPagamento.getFlagMultibeneficiario () ) ) {
            verificaEsistenzaPagamentoCollegato ( tipoPagamento );
        }
        Pagamento pagamento;
        EpayTPagamento tPagamento;

        try {
            tPagamento = pagamentoManager.getPagamentoPerCFEnteECovEIuv ( ente.getCodiceFiscale (), tipoPagamento.getCodiceVersamento (), input.getIuv () );
            if ( tPagamento == null ) {
                throw new NoDataException ();
            }
            pagamento = pagamentoManager.mappaPagamentoEsteso ( tPagamento );
        } catch ( NoDataException e ) {
            CodiciEsito ce = CodiciEsito.PAGAMENTO_NON_TROVATO_PER_POSIZIONE_DEBITORIA;
            throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH, ente.getCodiceFiscale (),
                    tipoPagamento.getCodiceVersamento (), input.getIuv () ) );
        } catch ( MoreResultException e ) {
            CodiciEsito ce = CodiciEsito.CODICE_ID_POSIZIONE_DEBITORIA_DUPLICATA;
            throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH, ente.getCodiceFiscale (),
                    tipoPagamento.getCodiceVersamento (), input.getIuv () ) );
        }

        verificaStato ( pagamento ); //TODO AF da saltare

        List<Date> dateValidita = verificaDataIniziFineValidita ( ente, tipoPagamento, pagamento, input.getDataInizioValidita (), input.getDataFineValidita ()); //TODO AF saltare
        boolean hasComponenti = verificaComponentiPagamento ( ente, tipoPagamento, input.getComponentiImporto (),
            input.getImportoPrincipale ()!= null?  input.getImportoPrincipale ():input.getImportoTotale (),pagamento.getCodicePagamentoRifEnte () );
        boolean hasRiferimenti = verificaRiferimentiPagamento ( input.getRiferimentiPagamento () );
        verificaValiditaPagamento ( tipoPagamento, dateValidita );
        verificaAnagrafica ( input.getSoggettoPagatore (), pagamento, true );

        if ( StringUtils.isNoneEmpty ( input.getDescrizioneCausaleVersamento () ) ) {
            tPagamento.setCausale ( StringUtils.substring ( input.getDescrizioneCausaleVersamento (), 0, 140 ) );
        }

        if ( StringUtils.isNoneEmpty ( input.getMotivazione () ) ) {
            tPagamento.setNote ( input.getMotivazione () );
        }

        if ( hasRiferimenti ) {
            Pagamento pagamentoImporti = new Pagamento ();
            pagamentoImporti.setIdPagamento ( pagamento.getIdPagamento () );
            pagamentoImporti.setUtenteUltimaModifica ( methodName );
            for ( RiferimentiPagamento riferimentoType: input.getRiferimentiPagamento () ) {
                PagamentoRiferimenti riferimento = new PagamentoRiferimenti ();
                riferimento.setNome ( riferimentoType.getNome () );
                riferimento.setValore ( riferimentoType.getValore () );
                riferimento.setUtenteUltimaModifica ( methodName );
                pagamentoImporti.getRiferimenti ().add ( riferimento );
            }
            pagamentoManager.aggiornaRiferimenti ( tPagamento, pagamentoImporti.getRiferimenti () );
        }
        if ( null != input.getImportoTotale () ) {
            tPagamento.setImporto ( input.getImportoTotale () );
        }
        
        if ( null != input.getImportoPrincipale ()) {
            tPagamento.setImportoPrincipale ( input.getImportoPrincipale () );
        }
        tPagamento.setUtenteUltimaModifica ( methodName );
        tPagamento.setRequiresCostUpdate(input.getRequiresCostUpdate());
        DatiSpecificiRiscossioneOutput dsr;
        DatiSpecificiRiscossioneOutput dsrSec;
        if ( hasComponenti ) {
            try {
                dsr = getDatiSpecificiRiscossione ( input.getCodiceFiscaleEnte (), input.getTipoPagamento () );
            } catch ( TassonomiaServiceException e1 ) {
                throw new TestataException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, e1.getMessage () );
            }
            pagamento.setComponenti ( new LinkedList<> () ); // Istruzione per eliminare i componenti precedenti.
            for ( ComponentiImporto componenteImportoType: input.getComponentiImporto () ) {
                if ( null != componenteImportoType.getImporto () ) {
                    PagamentoComponenti componente = new PagamentoComponenti ();
                    componente.setImporto ( componenteImportoType.getImporto () );
                    componente.setCausale ( componenteImportoType.getCausaleDescrittiva () );
                    componente.setAnnoAccertamento (
                        componenteImportoType.getAnnoAccertamento () != null ? componenteImportoType.getAnnoAccertamento () : null );
                    componente.setNumeroAccertamento (
                        StringUtils.isBlank ( componenteImportoType.getNumeroAccertamento () ) ? null
                                        : componenteImportoType.getNumeroAccertamento () );
                    componente.setUtenteUltimaModifica ( methodName );
                    inserisciDatoSpecificoRiscossioneInComponente ( componente, dsr );

                    pagamento.getComponenti ().add ( componente );
                }
            }
            pagamentoManager.aggiornaImporti ( tPagamento, pagamento.getComponenti () );
        } else if ( CollectionUtils.isEmpty ( pagamento.getComponenti () ) ) {
            try {
                dsr = getDatiSpecificiRiscossione ( input.getCodiceFiscaleEnte (), input.getTipoPagamento () );
            } catch ( TassonomiaServiceException e1 ) {
                throw new TestataException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, e1.getMessage () );
            }
            pagamento.getComponenti ().add ( creaComponenteDiDefault ( dsr, input.getDescrizioneCausaleVersamento (),
                ( null != input.getImportoPrincipale () ? input.getImportoPrincipale () : pagamento.getImportoPrincipale  () ), input.getIuv (),
                methodName ) );
            pagamentoManager.aggiornaImporti ( tPagamento, pagamento.getComponenti () );
        } else {
            if ( pagamento.getComponenti ().size () == 1 && null != input.getImportoPrincipale () ) {
                pagamento.getComponenti ().get ( 0 ).setImporto (input.getImportoPrincipale () );
                pagamentoManager.aggiornaImporti ( tPagamento, pagamento.getComponenti () );
            }
        }

      //c) Aggiornamento dell'importo secondario
        if ( Boolean.TRUE.equals ( tipoPagamento.getFlagMultibeneficiario () ) ) {

            //Nelle verifiche fatte in precedenza i componenti degli importi e i riferimenti erano a posto.

			PagamentoSecondario pagamentoImportiSecondario;
            try {
                pagamentoImportiSecondario = pagamentoManager.getPagamentoSecondarioByIdPagamentoPrincipale ( pagamento.getIdPagamento () );
            } catch ( NoDataException | MoreResultException e ) {
                throw new TestataException ( EsitoChiamataEsterna.ERRORE_GENERICO,
                    CodiciEsito.PAGAMENTO_SECONDARIO_NON_TROVATO.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH ) );
            }
            if (input.getImportoSecondario ()!= null)
            {
                pagamentoImportiSecondario.setImportoComplessivo ( input.getImportoSecondario () );
            }
            
            pagamentoImportiSecondario.setCausale ( pagamentoImportiSecondario.getCausale () );
            boolean hasComponentiSecondari =verificaComponentiSecondariPagamento ( ente, tipoPagamento, input.getComponentiImportoSecondario (), input.getImportoSecondario (), pagamento.getCodicePagamentoRifEnte ());
            
            if ( hasComponentiSecondari ) { //ce ne deve essere solo uno
				try {
					dsrSec = getDatiSpecificiRiscossione ( input.getCodiceFiscaleEnte (), input.getTipoPagamento () );
				} catch ( TassonomiaServiceException e1 ) {
					throw new TestataException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, e1.getMessage () );
				}
				pagamentoImportiSecondario.setComponenti ( new LinkedList<> () );
                for ( ComponentiImportoSecondario componenteImporto: input.getComponentiImportoSecondario () ) {

					pagamentoImportiSecondario.getComponenti ().add (  creaComponenteDiDefault ( dsrSec,  componenteImporto.getCausale () ,
                        componenteImporto.getImporto (), input.getIuv (), methodName )  );
                }
            }
            else  if (input.getImportoSecondario ()!= null)
            {
                PagamentoComponenti componenteImportoSec = 
                pagamentoImportiSecondario.getComponenti ().get ( 0 );
                componenteImportoSec.setImporto ( input.getImportoSecondario () );
            }
            pagamentoManager.aggiornaImportiSecondari ( pagamentoImportiSecondario );
        }
        // TODO AF finire qui
        if ( input.getDataInizioValidita () != null ) {
            tPagamento.setInizioValidita ( input.getDataInizioValidita () );
        }
        if ( input.getDataFineValidita () != null ) {
            tPagamento.setFineValidita ( input.getDataFineValidita () );
        }
        if ( input.getDataScadenza () != null ) {
            tPagamento.setDataScadenza ( input.getDataScadenza () );
        }

        if ( null != input.getSoggettoPagatore () && ( null != input.getSoggettoPagatore ().getPersonaFisica ()
            || null != input.getSoggettoPagatore ().getPersonaGiuridica () ) ) {
            Anagrafica anagraficaNew = toAnagraficaDto (pagamento.getPagatore (), input.getSoggettoPagatore () );
            anagraficaManager.aggiorna ( tPagamento.getIdPagamento (), anagraficaNew );
        }
        
        try {
            ChiamanteEsterno chiamante = new ChiamanteEsterno ();
            chiamante.setCodiceChiamante ( input.getCodiceChiamante () );
            tracciaChiamataSuDB ( TracciabilitaChiamanteEsterno.builder ().withChiamanteEsterno ( chiamante )
                .withCodiceFiscale ( input.getSoggettoPagatore () != null ? input.getSoggettoPagatore ().getIdentificativoUnivocoFiscale () : null )
                .withIuv ( input.getIuv () )
                .withIdentificativoPagamento ( tPagamento.getCodicePagamentoRifEnte () )
                .withRemoteHost ( input.getIpChiamante () )
                .withTimestampChiamata ( new Date () ).build (), methodName );
        } catch ( Exception e ) {
            log.warn ( methodName, "Errore in fase di tracciatura del pagamento: "+ input.getIuv ());
        }
        
        log.debugEnd ( methodName );
        return AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioOutput.builder ()
            .withResult ( Esito.builder ().withCode ( CodiciEsito.ESECUZIONE_OK.getCodice () )
                .withDescription ( CodiciEsito.ESECUZIONE_OK.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH ) ).build () )
            .build ();
    }

	private boolean verificaComponentiSecondariPagamento ( Ente ente, TipoPagamento tipoPagamento, List<ComponentiImportoSecondario> componenti,
					BigDecimal importoSecondario, String codicePagamentoRifEnte ) throws PosizioneDebitoriaException {
		final String methodName = "verificaComponentiSecondariPagamento";

		log.debugStart ( methodName );
		try {
			if ( CollectionUtils.isEmpty ( componenti ) ) {
				log.debug ( methodName, "OK" );
				return false;
			}
			BigDecimal importoCalcolato = BigDecimal.ZERO;
			for ( ComponentiImportoSecondario componente : componenti ) {

				if ( null == componente.getImporto () ) {
					CodiciEsito ce = CodiciEsito.IMPORTO_COMPONENTE_OBBLIGATORIO;
					throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
									ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH ) );

				}
				if ( StringUtils.isEmpty ( componente.getCausale () ) ) {
					CodiciEsito ce = CodiciEsito.CAUSALE_OBBLIGATORIA;
					throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
									ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH ) );

				}
				importoCalcolato = importoCalcolato.add ( componente.getImporto () );

				if ( ( !StringUtils.isBlank (
								componente.getNumeroAccertamento () ) && ( componente.getAnnoAccertamento () == null || componente.getAnnoAccertamento () == 0 ) ) ) {
					CodiciEsito ce = CodiciEsito.COMPONENTE_PAGAMENTO_ANNO_ACCERTAMENTO;
					throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
									ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH ) );
				}
				if ( StringUtils.isBlank (
								componente.getNumeroAccertamento () ) && componente.getAnnoAccertamento () != null && componente.getAnnoAccertamento () > 0 ) {
					CodiciEsito ce = CodiciEsito.COMPONENTE_PAGAMENTO_NUMERO_ACCETAMENTO;
					throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
									ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH ) );
				}

			}

			if ( importoCalcolato.compareTo ( importoSecondario ) != 0 ) {
				log.debug ( methodName, "KO" );
				CodiciEsito ce = CodiciEsito.ERRORE_TOTALE_IMPORTO_COMPONENTI;
				throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
								ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH, ente.getCodiceFiscale (), tipoPagamento.getCodiceVersamento (),
												codicePagamentoRifEnte, importoSecondario.toString (), importoCalcolato.toString () ) );
			}
			log.debug ( methodName, "OK" );
		} finally {
			log.debugEnd ( methodName );
		}
		return true;
	}

	/**
     * metodo per aggiornare una posizione debitoria.
     */
    private AggiornaPosizioneDebitoriaChiamanteEsternoOutput
        doAggiornaPosizioneDebitoriaChiamanteEsterno ( AggiornaPosizioneDebitoriaChiamanteEsternoInput input )
                        throws PosizioneDebitoriaException, TassonomiaServiceException, TestataException {
        final String methodName = "aggiornaPosizioneDebitoriaChiamanteEsternoV1";
        log.debugStart ( methodName );

        Ente ente;
        TipoPagamento tipoPagamento;
        ente = verificaEsistenzaEnte ( input.getCodiceFiscaleEnte () );
        log.info ( methodName, "recuperato ente : " + ente.getCodiceFiscale () );
        tipoPagamento = verificaTipoPagamento ( ente, input.getTipoPagamento (), false, true );
        testField ( input.getIuv (), CodiciEsito.CODICE_IUV_OBBLIGATORIO );
        validaChiamanteEsterno ( input.getCodiceChiamante (), "CREAZIONE_IUV", tipoPagamento.getIdTipoPagamento () );
        verificaCompatibilitaFlagMultibeneficiario ( tipoPagamento, Boolean.FALSE );
        Pagamento pagamento;
        EpayTPagamento tPagamento;

        try {
            tPagamento = pagamentoManager.getPagamentoPerCFEnteECovEIuv ( ente.getCodiceFiscale (), tipoPagamento.getCodiceVersamento (), input.getIuv () );
            if ( tPagamento == null ) {
                throw new NoDataException ();
            }
            pagamento = pagamentoManager.mappaPagamentoEsteso ( tPagamento );
        } catch ( NoDataException e ) {
            CodiciEsito ce = CodiciEsito.PAGAMENTO_NON_TROVATO_PER_POSIZIONE_DEBITORIA;
            throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH, ente.getCodiceFiscale (),
                    tipoPagamento.getCodiceVersamento (), input.getIuv () ) );
        } catch ( MoreResultException e ) {
            CodiciEsito ce = CodiciEsito.CODICE_ID_POSIZIONE_DEBITORIA_DUPLICATA;
            throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH, ente.getCodiceFiscale (),
                    tipoPagamento.getCodiceVersamento (), input.getIuv () ) );
        }

        verificaStato ( pagamento );

        List<Date> dateValidita = verificaDataIniziFineValidita ( ente, tipoPagamento, pagamento, input.getDataInizioValidita (), input.getDataFineValidita ());
        boolean hasComponenti = verificaComponentiPagamento ( ente, tipoPagamento, input.getComponentiImporto (),  input.getImportoTotale (), pagamento.getCodicePagamentoRifEnte () );
        boolean hasRiferimenti = verificaRiferimentiPagamento ( input.getRiferimentiPagamento () );
        verificaValiditaPagamento ( tipoPagamento, dateValidita );
        verificaAnagrafica ( input.getSoggettoPagatore (), pagamento, false );

        if ( StringUtils.isNoneEmpty ( input.getDescrizioneCausaleVersamento () ) ) {
            tPagamento.setCausale ( StringUtils.substring ( input.getDescrizioneCausaleVersamento (), 0, 140 ) );
        }

        if ( StringUtils.isNoneEmpty ( input.getMotivazione () ) ) {
            tPagamento.setNote ( input.getMotivazione () );
        }

        if ( hasRiferimenti ) {
            Pagamento pagamentoImporti = new Pagamento ();
            pagamentoImporti.setIdPagamento ( pagamento.getIdPagamento () );
            pagamentoImporti.setUtenteUltimaModifica ( methodName );
            for ( RiferimentiPagamento riferimentoType: input.getRiferimentiPagamento () ) {
                PagamentoRiferimenti riferimento = new PagamentoRiferimenti ();
                riferimento.setNome ( riferimentoType.getNome () );
                riferimento.setValore ( riferimentoType.getValore () );
                riferimento.setUtenteUltimaModifica ( methodName );
                pagamentoImporti.getRiferimenti ().add ( riferimento );
            }
            pagamentoManager.aggiornaRiferimenti ( tPagamento, pagamentoImporti.getRiferimenti () );
        }
        if ( null != input.getImportoTotale () ) {
            tPagamento.setImporto ( input.getImportoTotale () );
        }
        tPagamento.setUtenteUltimaModifica ( methodName );
        tPagamento.setRequiresCostUpdate(input.getRequiresCostUpdate());
        DatiSpecificiRiscossioneOutput dsr;
        if ( hasComponenti ) {
			try {
				dsr = getDatiSpecificiRiscossione ( input.getCodiceFiscaleEnte (), input.getTipoPagamento () );
			} catch ( TassonomiaServiceException e1 ) {
				throw new TestataException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, e1.getMessage () );
			}
			pagamento.setComponenti ( new LinkedList<> () ); // Istruzione per eliminare i componenti precedenti.
            for ( ComponentiImporto componenteImportoType: input.getComponentiImporto () ) {
                if ( null != componenteImportoType.getImporto () ) {
                    PagamentoComponenti componente = new PagamentoComponenti ();
                    componente.setImporto ( componenteImportoType.getImporto () );
                    componente.setCausale ( componenteImportoType.getCausaleDescrittiva () );
                    componente.setAnnoAccertamento (
                        componenteImportoType.getAnnoAccertamento () != null ? componenteImportoType.getAnnoAccertamento () : null );
                    componente.setNumeroAccertamento (
                        StringUtils.isBlank ( componenteImportoType.getNumeroAccertamento () ) ? null
                                        : componenteImportoType.getNumeroAccertamento () );
                    componente.setUtenteUltimaModifica ( methodName );
                    inserisciDatoSpecificoRiscossioneInComponente ( componente, dsr );

                    pagamento.getComponenti ().add ( componente );
                }
            }
            pagamentoManager.aggiornaImporti ( tPagamento, pagamento.getComponenti () );
        } else if ( CollectionUtils.isEmpty ( pagamento.getComponenti () ) ) {
			try {
				dsr = getDatiSpecificiRiscossione ( input.getCodiceFiscaleEnte (), input.getTipoPagamento () );
			} catch ( TassonomiaServiceException e1 ) {
				throw new TestataException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, e1.getMessage () );
			}
			pagamento.getComponenti ().add ( creaComponenteDiDefault ( dsr, input.getDescrizioneCausaleVersamento (),
                (null != input.getImportoTotale ()?input.getImportoTotale ():pagamento.getImporto ()), input.getIuv (), methodName ) );
            pagamentoManager.aggiornaImporti ( tPagamento, pagamento.getComponenti () );
        } else {
            if ( pagamento.getComponenti ().size () == 1 && null != input.getImportoTotale ()) {
                pagamento.getComponenti ().get ( 0 ).setImporto ( input.getImportoTotale () );
                pagamentoManager.aggiornaImporti ( tPagamento, pagamento.getComponenti () );
            }
        }

        if ( input.getDataInizioValidita () != null ) {
            tPagamento.setInizioValidita ( input.getDataInizioValidita () );
        }
        if ( input.getDataFineValidita () != null ) {
            tPagamento.setFineValidita ( input.getDataFineValidita () );
        }
        if ( input.getDataScadenza () != null ) {
            tPagamento.setDataScadenza ( input.getDataScadenza () );
        }

        if ( null != input.getSoggettoPagatore () && ( null != input.getSoggettoPagatore ().getPersonaFisica ()
            || null != input.getSoggettoPagatore ().getPersonaGiuridica () ) ) {
            Anagrafica anagraficaNew = toAnagraficaDto (pagamento.getPagatore (), input.getSoggettoPagatore () );
            anagraficaManager.aggiorna ( tPagamento.getIdPagamento (), anagraficaNew );
//            tPagamento.setEpayTAnagrafica ( anagraficaNew );

        }
        try {
            ChiamanteEsterno chiamante = new ChiamanteEsterno ();
            chiamante.setCodiceChiamante ( input.getCodiceChiamante () );
            tracciaChiamataSuDB ( TracciabilitaChiamanteEsterno.builder ()
                .withChiamanteEsterno ( chiamante )
                .withCodiceFiscale ( input.getSoggettoPagatore () != null ? input.getSoggettoPagatore ().getIdentificativoUnivocoFiscale () : null )
                .withIuv ( input.getIuv () )
                .withIdentificativoPagamento ( tPagamento.getCodicePagamentoRifEnte () )
                .withRemoteHost ( input.getIpChiamante () )
                .withTimestampChiamata ( new Date () ).build (), methodName );
        } catch ( Exception e ) {
            log.warn ( methodName, "Errore in fase di tracciatura del pagamento: "+ input.getIuv ());
        }
        log.debugEnd ( methodName );
        return AggiornaPosizioneDebitoriaChiamanteEsternoOutput.builder ().withResult ( Esito.builder ().withCode ( CodiciEsito.ESECUZIONE_OK.getCodice () )
            .withDescription ( CodiciEsito.ESECUZIONE_OK.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH ) ).build () ).build ();
    }
    
    /**
     * Inserisce errore su database
     */
    private void tracciaErroreChiamataEsterna ( ChiamanteEsternoCommonInput input, String descrizioneErrore ) {
        TracciabilitaChiamataEsternaNonValida tracciabilitaChiamataEsternaNonValida = new TracciabilitaChiamataEsternaNonValida ();
        tracciabilitaChiamataEsternaNonValida.setCodiceChiamante ( input.getCodiceChiamante () );
        tracciabilitaChiamataEsternaNonValida.setCodiceFiscale ( input.getCodiceFiscaleEnte () );
        tracciabilitaChiamataEsternaNonValida.setTimestampChiamata ( new Timestamp ( System.currentTimeMillis () ) );
        tracciabilitaChiamataEsternaNonValida.setRemoteHost ( input.getIpChiamante () );
        tracciabilitaChiamataEsternaNonValida.setDescrizioneErrore ( descrizioneErrore );
        tracciabilitaChiamataEsternaNonValida.setIuv ( input.getIuv () );
        chiamataEsternaNonValidaManager.inserisci ( tracciabilitaChiamataEsternaNonValida );
    }
    /**
     * 
     */
    private TipoPagamento verificaTipoPagamento ( Ente ente, String codiceVersamento, Boolean checkMultibeneficiario,
					Boolean checkEpay347 ) throws TestataException {
        final String methodName = "verificaTestataTipoPagamento";

        log.debugStart ( methodName );
        List<TipoPagamento> listaTipoPagamento = tipoPagamentoManager.getByEnteECodiceVersamento ( ente, codiceVersamento );
        if ( listaTipoPagamento == null || listaTipoPagamento.isEmpty () ) {
            log.debug ( methodName, "KO1" );
            log.debugEnd ( methodName );
            CodiciEsito ce = CodiciEsito.CODICE_VERSAMENTO_NON_TROVATO_ERRORE;
            throw new TestataException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH, codiceVersamento ) );
        }
        if ( listaTipoPagamento.size () > 1 ) {
            log.debug ( methodName, "KO2" );
            log.debugEnd ( methodName );
            CodiciEsito ce = CodiciEsito.CODICE_VERSAMENTO_NON_UNIVOCO_ERRORE;
            throw new TestataException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH, codiceVersamento ) );
        }
        TipoPagamento tipoPagamento = listaTipoPagamento.get ( 0 );
        if ( tipoPagamento.getFineValidita () != null && tipoPagamento.getFineValidita ().before ( new Date () ) ) {
            log.debug ( methodName, "KO3" );
            log.debugEnd ( methodName );
            CodiciEsito ce = CodiciEsito.TIPO_PAGAMENTO_NON_ATTIVO_ERRORE;
            throw new TestataException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH, codiceVersamento ) );
        }

        if ( Boolean.TRUE.equals ( checkMultibeneficiario ) && Boolean.TRUE.equals ( tipoPagamento.getFlagMultibeneficiario () ) ) {
            log.debug ( methodName, "KO5" );
            log.debugEnd ( methodName );
            CodiciEsito ce = CodiciEsito.ERRORE_TIPO_PAGAMENTO_MULTIBENEFICIARIO;
            throw new TestataException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH, codiceVersamento ) );
        }
        if ( Boolean.TRUE.equals ( checkMultibeneficiario ) && tipoPagamentoManager.countByIdPagamentoSecondario ( tipoPagamento ) > 0 ) {
            log.debug ( methodName, "KO5" );
            log.debugEnd ( methodName );
            CodiciEsito ce = CodiciEsito.ERRORE_TIPO_PAGAMENTO_MULTIBENEFICIARIO;
            throw new TestataException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH, codiceVersamento ) );
        }

        //EPAY-347
        if ( checkEpay347 && tipoPagamento.getIdTipoPagamento () != null && tipoPagamento.getTipologiaPagamento () != null ) {

            if ( TipologiaPagamento.TipoPagamentoType.REDS.name ().equalsIgnoreCase ( tipoPagamento.getTipologiaPagamento ().getCodice () ) ) {
                log.debug ( methodName, "KO4" );
                log.debugEnd ( methodName );
                CodiciEsito ce = CodiciEsito.TIPO_PAGAMENTO_NON_PERMESSO_ERRORE;
                throw new TestataException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                    ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH, codiceVersamento ) );
            }

            if ( TipologiaPagamento.TipoPagamentoType.MABL.name ().equalsIgnoreCase ( tipoPagamento.getTipologiaPagamento ().getCodice () ) ) {
                log.debug ( methodName, "KO4" );
                log.debugEnd ( methodName );
                CodiciEsito ce = CodiciEsito.TIPO_PAGAMENTO_MARCA_ERRORE;
                throw new TestataException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                    ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH, codiceVersamento ) );
            }

            if ( TipologiaPagamento.TipoPagamentoType.PS.name ().equalsIgnoreCase ( tipoPagamento.getTipologiaPagamento ().getCodice () ) ) {
                log.debug ( methodName, "KO4" );
                log.debugEnd ( methodName );
                CodiciEsito ce = CodiciEsito.TIPO_PAGAMENTO_SPONTANEO_ERRORE;
                throw new TestataException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                    ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH, codiceVersamento ) );
            }

        }
        log.debug ( methodName, "OK" );
        log.debugEnd ( methodName );
        return tipoPagamento;
    }


    /**
     * 
     */
    private Ente verificaEsistenzaEnte ( String codiceFiscaleEnte ) throws TestataException {
        final String methodName = "verificaEsistenzaEnte";

        log.debugStart ( methodName );

        try {
            Ente ente = enteManager.getByCF ( codiceFiscaleEnte );
            if ( ente == null ) {
                log.debug ( methodName, "KO" );
                throw new TestataException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                    CodiciEsito.CF_ENTE_ERRORE.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH, codiceFiscaleEnte ) );
            }
            log.debug ( methodName, "OK" );
            return ente;
        } catch ( NoDataException e ) {
            log.debug ( methodName, "KO" );
            CodiciEsito ce = CodiciEsito.CF_ENTE_ERRORE;
            throw new TestataException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH, codiceFiscaleEnte ) );
        } finally {
            log.debugEnd ( methodName );
        }
    }

    /**
     * 
     */
    private void testField ( final String str, final CodiciEsito ce ) throws PosizioneDebitoriaException {
        if ( StringUtils.isBlank ( str ) ) {
            throw new PosizioneDebitoriaException ( ce.getCodice (), ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH ) );
        }
    }
    
    /**
     * 
     */
    private void testField ( final Object obj, final CodiciEsito ce ) throws PosizioneDebitoriaException {
        if ( obj== null ) {
            throw new PosizioneDebitoriaException ( ce.getCodice (), ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH ) );
        }
    }
    
    
    /**
     */
    private void verificaStato ( Pagamento pagamento ) throws PosizioneDebitoriaException {
        StatoPagamento statoPagamento = StatoPagamento.findById ( pagamento.getIdStatoCorrente () );
        CodiciEsito ce;
        switch ( statoPagamento ) {
        case IN_ATTESA :
        case TRANSAZIONE_INIZIALIZZATA :
        case TRANSAZIONE_AVVIATA :
            ce = CodiciEsito.PAGAMENTO_IN_ATTESA;
            throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH ) );
        case SUCCESSO :
            ce = CodiciEsito.PAGAMENTO_GIA_EFFETTUATO;
            throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH ) );
        case INVALIDATO :
            ce = CodiciEsito.PAGAMENTO_GIA_ANNULLATO;
            throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH ) );
        default :
            EpayDStatoPagamento sp = entityManager.find ( EpayDStatoPagamento.class, statoPagamento.getId () );
            if ( !Boolean.TRUE.equals ( sp.getModificabile () ) ) {
                ce = CodiciEsito.PAGAMENTO_NON_MODIFICABILE;
                throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                    ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH, pagamento.getCodicePagamentoRifEnte () ) );
            }
            break;
        }
    }
    
    /**
     * 
     */
    private List<Date> verificaDataIniziFineValidita ( Ente ente, TipoPagamento tipoPagamento, Pagamento pagamento, Date dataInizioValidita, Date dataFineValidita )
                    throws PosizioneDebitoriaException {
        final String methodName = "verificaDataIniziFineValidita";
        log.debugStart ( methodName );

        List<Date> dateValidita = new ArrayList<> ();
        dateValidita.add ( dataInizioValidita == null ? pagamento.getInizioValidita () : dataInizioValidita );
        dateValidita.add ( dataFineValidita == null ? pagamento.getFineValidita () : dataFineValidita );

        if ( dateValidita.get ( 0 ) == null ) {
            log.debug ( methodName, "OK" );
            return dateValidita;
        }

        if ( dateValidita.get ( 1 ) == null ) {
            log.debug ( methodName, "OK" );
            return dateValidita;
        }

        if ( dateValidita.get ( 1 ).before ( dateValidita.get ( 0 ) ) ) {
            log.debug ( methodName, "KO" );
            CodiciEsito ce = CodiciEsito.ERRORE_DATE_VALIDITA;
            throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH, ente.getCodiceFiscale (),
                    tipoPagamento.getCodiceVersamento (), pagamento.getCodicePagamentoRifEnte () ) );
        }

        log.debug ( methodName, "OK" );
        log.debugEnd ( methodName );
        return dateValidita;
    }
    
    
    /**
     *
	 */
    private void verificaValiditaPagamento ( TipoPagamento tipoPagamento, List<Date> dateValidita ) throws PosizioneDebitoriaException {
        final String methodName = "verificaValiditaPagamento";
        log.debugStart ( methodName );
        if ( dateValidita.get ( 1 ) != null &&
            dateValidita.get ( 1 ).before ( new Date () ) ) {
            CodiciEsito ce = CodiciEsito.PAGAMENTO_SCADUTO;
            throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH ) );
        }

        if ( tipoPagamento.getInizioValidita () != null &&
            dateValidita.get ( 1 ) != null &&
            dateValidita.get ( 1 ).before ( tipoPagamento.getInizioValidita () ) ) {
            CodiciEsito ce = CodiciEsito.PAGAMENTO_MAI_ATTIVO;
            throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH ) );
        }

        if ( tipoPagamento.getFineValidita () != null &&
            dateValidita.get ( 0 ) != null &&
            dateValidita.get ( 0 ).after ( tipoPagamento.getInizioValidita () ) ) {
            CodiciEsito ce = CodiciEsito.PAGAMENTO_MAI_ATTIVO;
            throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH ) );
        }

        log.debugEnd ( methodName );
    }
    
    /**
     * 
     */
    private boolean verificaComponentiPagamento ( Ente ente, TipoPagamento tipoPagamento, List<ComponentiImporto> componenti,  BigDecimal importoTotaleAtteso , String codicePagamentoRifEnte )
                    throws PosizioneDebitoriaException {
        final String methodName = "verificaComponentiPagamento";

        log.debugStart ( methodName );
        try {
            if ( CollectionUtils.isEmpty ( componenti ) ) {
                log.debug ( methodName, "OK" );
                return false;
            }
			BigDecimal importoCalcolato = BigDecimal.ZERO;
            for ( ComponentiImporto componente: componenti ) {
                
                if (null==  componente.getImporto ())
                {
                    CodiciEsito ce = CodiciEsito.IMPORTO_COMPONENTE_OBBLIGATORIO;
                    throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                        ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH ) );
                    
                }
                if (StringUtils.isEmpty ( componente.getCausaleDescrittiva () ))
                {
                    CodiciEsito ce = CodiciEsito.CAUSALE_OBBLIGATORIA;
                    throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                        ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH ) );
                    
                }
                importoCalcolato = importoCalcolato.add ( componente.getImporto () );

                if ( ( !StringUtils.isBlank ( componente.getNumeroAccertamento () )
                                && ( componente.getAnnoAccertamento () == null || componente.getAnnoAccertamento () == 0 ) ) ) {
                    CodiciEsito ce = CodiciEsito.COMPONENTE_PAGAMENTO_ANNO_ACCERTAMENTO;
                    throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                        ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH ) );
                }
                if ( StringUtils.isBlank ( componente.getNumeroAccertamento () ) && componente.getAnnoAccertamento () != null
                                && componente.getAnnoAccertamento () > 0 ) {
                    CodiciEsito ce = CodiciEsito.COMPONENTE_PAGAMENTO_NUMERO_ACCETAMENTO;
                    throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                        ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH ) );
                }
               
            }

			if ( importoCalcolato.compareTo ( importoTotaleAtteso ) != 0 ) {
                log.debug ( methodName, "KO" );
                CodiciEsito ce = CodiciEsito.ERRORE_TOTALE_IMPORTO_COMPONENTI;
                throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, ce.getMessaggio (
                    MAX_ERROR_MESSAGE_WIDTH,
                    ente.getCodiceFiscale (),
                    tipoPagamento.getCodiceVersamento (),
                    codicePagamentoRifEnte,
                    importoTotaleAtteso.toString (),
                    importoCalcolato.toString () ) );
            }
            log.debug ( methodName, "OK" );
        } finally {
            log.debugEnd ( methodName );
        }
        return true;
    }
    
    /**
     * 
     */
    private boolean verificaRiferimentiPagamento ( List<RiferimentiPagamento> riferimenti ) throws PosizioneDebitoriaException {
        final String methodName = "verificaRiferimentiPagamento";

        log.debugStart ( methodName );

        try {
            if ( CollectionUtils.isEmpty ( riferimenti ) ) {
                return false;
            }
            for ( RiferimentiPagamento riferimento: riferimenti ) {
                if ( ( !StringUtils.isBlank ( riferimento.getNome () ) && StringUtils.isBlank ( riferimento.getValore () ) )
                    || ( StringUtils.isBlank ( riferimento.getNome () ) && !StringUtils.isBlank ( riferimento.getValore () ) ) ) {
                    CodiciEsito ce = CodiciEsito.RIFERIMENTO_PAGAMENTO_VUOTO;
                    throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                        ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH ) );
                }
            }
            log.debug ( methodName, "OK" );
        } finally {
            log.debugEnd ( methodName );
        }
        return true;
    }

    /**
     * 
     */
    private void verificaAnagrafica ( it.csi.epay.epayservices.model.SoggettoPagatore soggettoType, Pagamento pagamento, boolean isMutibeneficiario ) throws PosizioneDebitoriaException {

        if ( null != soggettoType && ( null != soggettoType.getPersonaFisica () || null != soggettoType.getPersonaGiuridica () ) ) {
            if (!isMutibeneficiario)
            {
                if ( !ANONIMO.equals ( pagamento.getPagatore ().getCodiceFiscale () )
                                || !ANONIMO.equals ( pagamento.getPagatore ().getCognome () )
                                || !ANONIMO.equals ( pagamento.getPagatore ().getNome () ) ) {

                    throw new PosizioneDebitoriaException ( CodiciEsito.SOGGETTO_PAGATORE_NON_ANONIMO.getCodice (),
                        CodiciEsito.SOGGETTO_PAGATORE_NON_ANONIMO.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH ) );
                }
			}
            
            if ( null != soggettoType.getPersonaFisica () ) {
                testField ( soggettoType.getPersonaFisica ().getNome (), CodiciEsito.NOME_OBBLIGATORIO );
                testField ( soggettoType.getPersonaFisica ().getCognome (), CodiciEsito.COGNOME_OBBLIGATORIO );

            } else if ( null != soggettoType.getPersonaGiuridica () ) {
                testField ( soggettoType.getPersonaGiuridica ().getRagioneSociale (), CodiciEsito.RAGIONESOCIALE_OBBLIGATORIA );
            }
            testField ( soggettoType.getIdentificativoUnivocoFiscale (), CodiciEsito.CODICE_FISCALE_OBBLIGATORIO );
        }
    }
    
    /**
     * 
     */
    private DatiSpecificiRiscossioneOutput getDatiSpecificiRiscossione ( String codiceFiscaleEnte, String tipoPagamento ) throws TassonomiaServiceException {
		DatiSpecificiRiscossioneOutput dsr;
		try {
			TassonomiaAdapterClient client = new TassonomiaAdapterClient ( configurazioneManager );
			DatiSpecificiRiscossioneInput request = client.buildDatiSpecificiRiscossioneInput ( codiceFiscaleEnte, tipoPagamento );
			dsr = client.getDatiSpecificiRiscossione ( request );

		} catch ( TassonomiaServiceException e ) {
			log.error ( "Errore in fase di reperimento del dato specifico riscossione", e );
			throw e;
		} catch ( Exception e ) { // DA FARE gestire correttamente eccezioni e stati
			log.error ( "Errore in fase di reperimento del dato specifico riscossione", e );
			throw new RuntimeException ( "Errore in fase di reperimento del dato specifico riscossione", e );
		}
		return dsr;
	}
    
    /**
     * 
     */
    private void inserisciDatoSpecificoRiscossioneInComponente ( PagamentoComponenti componente, DatiSpecificiRiscossioneOutput dsr )
                    throws TassonomiaServiceException {
        if ( componente.getImporto () != null ) {
            //:TODO ottimizzare le performance
            Optional<DatiSpecificiRiscossione> trovato = dsr.getElencoDatiSpecifici ().stream ()
                .filter ( new Predicate<DatiSpecificiRiscossione> () {

                    @Override
                    public boolean test ( DatiSpecificiRiscossione s ) {
                        // Cerco il dato specifico riscossione con anno accertamento e numero accertamento per evitare che l'equals vada in errore
                        if ( null == s.getAnnoAccertamento () && StringUtils.isEmpty ( s.getNumeroAccertamento () ) ) {
                            return ( null == componente.getAnnoAccertamento () && StringUtils.isEmpty ( componente.getNumeroAccertamento () ) );
                        }
                        return s.getAnnoAccertamento ().equals ( componente.getAnnoAccertamento () ) &&
                            s.getNumeroAccertamento ().equals ( componente.getNumeroAccertamento () );
                    }
                } )
                .findFirst ();
            if ( trovato.isPresent () ) {
                componente.setDatiSpecificiRiscossione ( trovato.get ().getCodice () );
                componente.setCodiceTributo (  trovato.get ().getCodiceTributo () );
            } else {
                // Cerco il DSR di default
                Optional<DatiSpecificiRiscossione> trovatoDefault = dsr.getElencoDatiSpecifici ().stream ()
                    .filter ( new Predicate<DatiSpecificiRiscossione> () {

                        @Override
                        public boolean test ( DatiSpecificiRiscossione s ) {
                            return null == s.getAnnoAccertamento () && StringUtils.isEmpty ( s.getNumeroAccertamento () );
                        }
                    } )
                    .findFirst ();
                if ( trovatoDefault.isPresent () ) {
                    componente.setDatiSpecificiRiscossione ( trovatoDefault.get ().getCodice () );
                    componente.setCodiceTributo (  trovatoDefault.get ().getCodiceTributo () );
                } else {
                    CodiciEsito ce = CodiciEsito.ERRORE_DATI_SPECIFICI_RISCOSSIONE;
                    throw new TassonomiaServiceException ( CodiciEsito.ERRORE_DATI_SPECIFICI_RISCOSSIONE.getCodice (),
                        ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH ) + ", per la componente con anno e numero accertamento: " + componente.getAnnoAccertamento ()
                            + " - " + componente.getNumeroAccertamento () + " non sono presenti valori su Catalogo!" );
                }
            }
        }
    }
    
    /**
     * 
     */
    private PagamentoComponenti creaComponenteDiDefault ( DatiSpecificiRiscossioneOutput dsr, String causale, BigDecimal importo,
        String identificativoPagamento, String utenteModifica )
                    throws TassonomiaServiceException {

        PagamentoComponenti componente = new PagamentoComponenti ();
        componente.setProgressivo ( 1 );
        componente.setImporto ( importo );
        componente.setUtenteUltimaModifica ( utenteModifica );
        
        List<DatiSpecificiRiscossione> listDati = new LinkedList<> ();
        for ( DatiSpecificiRiscossione dato: dsr.getElencoDatiSpecifici () ) {
            if ( null != dato.getAnnoAccertamento () && null != dato.getNumeroAccertamento () ) {
                listDati.add ( dato );
            }

        }
        if ( CollectionUtils.isEmpty ( listDati ) || listDati.size () > 1 ) {
            CodiciEsito ce = CodiciEsito.ERRORE_DATI_SPECIFICI_RISCOSSIONE;
            throw new TassonomiaServiceException ( ce.getCodice (),
                ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH ) + " , per la posizione debitoria: " + identificativoPagamento
                    + ", non essendo specificate componenti viene richiesto un'unico riferimento contabile, con anno e numero accertamento specificato, valido per l'anno in corso su Catalogo!" );
        } else {
            componente.setAnnoAccertamento ( listDati.get ( 0 ).getAnnoAccertamento () );
            componente.setDatiSpecificiRiscossione ( listDati.get ( 0 ).getCodice () );
            componente.setNumeroAccertamento ( listDati.get ( 0 ).getNumeroAccertamento () );
            componente.setCodiceTributo (  listDati.get ( 0 ).getCodiceTributo () );
            if ( StringUtils.isNotBlank ( causale ) ) {
                componente.setCausale ( causale );
            } else {
                componente.setCausale ( listDati.get ( 0 ).getDescrizione () );
            }
        }
        return componente;
    }

	private Anagrafica toAnagraficaDto (Anagrafica anagrafica,  it.csi.epay.epayservices.model.SoggettoPagatore soggettoPagatore ) {
        final String methodName = "inserisciAnagrafica";

        log.debugStart ( methodName );
        try {
            if ( soggettoPagatore.getPersonaFisica () != null && soggettoPagatore.getPersonaGiuridica () == null ) {
                anagrafica.setFlagPersonaFisica ( Boolean.TRUE );
                anagrafica.setNome ( stringTrim.apply ( soggettoPagatore.getPersonaFisica ().getNome () ) );
                anagrafica.setCognome ( stringTrim.apply ( soggettoPagatore.getPersonaFisica ().getCognome () ) );
            }
            if ( soggettoPagatore.getPersonaFisica () == null && soggettoPagatore.getPersonaGiuridica () != null ) {
                anagrafica.setFlagPersonaFisica ( Boolean.FALSE );
                anagrafica.setRagioneSociale ( stringTrim.apply ( soggettoPagatore.getPersonaGiuridica ().getRagioneSociale () ) );
            }
            anagrafica.setCodiceFiscale ( stringTrim.apply ( soggettoPagatore.getIdentificativoUnivocoFiscale () ) );
            if ( StringUtils.isNotEmpty ( soggettoPagatore.getIndirizzo () ) ) {
                anagrafica.setIndirizzo ( stringTrim.apply ( soggettoPagatore.getIndirizzo () ) );
            }
            if ( StringUtils.isNotEmpty ( soggettoPagatore.getCivico () ) ) {
                anagrafica.setCivico ( stringTrim.apply ( soggettoPagatore.getCivico () ) );
            }
            if ( StringUtils.isNotEmpty ( soggettoPagatore.getCap () ) ) {
                anagrafica.setCap ( stringTrim.apply ( soggettoPagatore.getCap () ) );
            }
            if ( StringUtils.isNotEmpty ( soggettoPagatore.getLocalita () ) ) {
                anagrafica.setLocalita ( stringTrim.apply ( soggettoPagatore.getLocalita () ) );
            }
            if ( StringUtils.isNotEmpty ( soggettoPagatore.getProvincia () ) ) {
                anagrafica.setProvincia ( stringTrim.apply ( soggettoPagatore.getProvincia () ) );
            }
            if ( StringUtils.isNotEmpty ( soggettoPagatore.getNazione () ) ) {
                anagrafica.setNazione ( stringTrim.apply ( soggettoPagatore.getNazione () ) );
            }
            anagrafica.setEmail ( stringTrim.apply ( soggettoPagatore.getEmail () ) );

            return anagrafica;
        } finally {
            log.debugEnd ( methodName );
        }
    }
    
    /**
     * @param entity, nomeChiamante
     */
    private void tracciaFallimentoSuDB ( ChiamataEsternaNonValida entity, String nomeChiamante ) {
        try {
            Long id = chiamataEsternaManager.inserisci ( entity );
            entity.setIdChiamata ( id );
        } catch ( Exception e ) {
            log.error ( nomeChiamante, "Errore in fase di tracciamento dell'errore: " + ( ( null != entity ) ? entity.toString () : "" ), e );
        }
    }
    
    /**
     * @param entity, nomeChiamante
     */
    private void tracciaChiamataSuDB ( TracciabilitaChiamanteEsterno entity, String nomeChiamante ) {
        try {
            Long id = chiamataEsternaManager.inserisci ( entity );
            entity.setIdChiamata ( id );
        } catch ( Exception e ) {
            log.error ( nomeChiamante, "Errore in fase di tracciamento dell'errore: " + ( ( null != entity ) ? entity.toString () : "" ), e );
        }
    }
  
    
    public PagamentoIuvChiamanteEsternoOutput getPagamentoIUVChiamanteEsterno(PagamentoIuvChiamanteEsternoInput input) {
        String methodName = "getPagamentoIUVChiamanteEsterno";
        if ( log.isDebugEnabled () ) {
            log.debug ( methodName, "input : " + XmlUtil.obj2Xml ( input ) );
        }
        PagamentoIuvChiamanteEsternoOutput result = new PagamentoIuvChiamanteEsternoOutput ();

        try {
            result = doGetPagamentoIUVChiamanteEsterno ( input );
        } catch ( PosizioneDebitoriaException pde ) {
            result.setCodiceEsito ( pde.getCode () );
            result.setDescrizioneEsito ( pde.getMessage () );
            tracciaFallimentoSuDB ( ChiamataEsternaNonValida.builder ().withCodiceChiamante ( input.getCodiceChiamante () )
                .withDescrizioneErrore (
                    StringUtils.abbreviate ( ( StringUtils.isBlank ( pde.getMessage () ) ? pde.getMessage () : "Errore in fase di pagamento" )
                        .concat ( ExceptionUtils.getStackTrace ( pde ) ), 500 ) )
                .withIuv ( input.getIuv () ).withRemoteHost ( input.getIpChiamante () )
                .withTimestampChiamata ( new Date () ).build (), methodName );
        } catch ( Exception t ) {
            result.setCodiceEsito ( EsitoChiamataEsterna.ERRORE_GENERICO );
            result.setDescrizioneEsito ("Errore in fase di pagamento della posizione debitoria");
            tracciaFallimentoSuDB ( ChiamataEsternaNonValida.builder ().withCodiceChiamante ( input.getCodiceChiamante () )
                .withDescrizioneErrore (
                    StringUtils.abbreviate ( ( StringUtils.isBlank ( t.getMessage () ) ? t.getMessage () : "Errore in fase di aggiornamento" )
                        .concat ( ExceptionUtils.getStackTrace ( t ) ), 500 ) )
                .withIuv ( input.getIuv () ).withRemoteHost ( input.getIpChiamante () )
                .withTimestampChiamata ( new Date () ).build (), methodName );
        }
        if ( log.isDebugEnabled () ) {
            log.debug ( methodName, "output : " + XmlUtil.obj2Xml ( result ) );
        }
        return result;
    }
    
    private PagamentoIuvChiamanteEsternoOutput doGetPagamentoIUVChiamanteEsterno (
        PagamentoIuvChiamanteEsternoInput input ) throws PosizioneDebitoriaException, TestataException {

    	String methodName = "doGetPagamentoIUVChiamanteEsterno";
		log.info ( methodName, "BEGIN" );

        ChiamanteEsterno chiamante;
        Transazione transazione;
        TransazioneMdp transazioneMdp;

        // Verifica dell'input della chiamata
//        String risultatoValidazione = validaInputPagamenti ( input );
        testField ( input.getCodiceFiscale (), CodiciEsito.CODICE_FISCALE_OBBLIGATORIO );
        testField ( input.getIuv (), CodiciEsito.CODICE_IUV_OBBLIGATORIO );
        
//        if ( risultatoValidazione != null ) {
//            log.warn ( methodName, "validazione input fallita : " + risultatoValidazione );
//            return mapGetUrlPagamentoFailOrSuccess ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, risultatoValidazione,
//                input.getIdentificativoPagamento (), null );
//        }

        try {
            chiamante = chiamataEsternaManager.recuperaChiamanteEsterno ( input.getCodiceChiamante () );
 //          log.info(methodName, "recuperato chiamante : " + chiamante.getCodiceChiamante());
        } catch ( IllegalAccessException | NoDataException e1 ) {
            throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                "Chiamante [" + input.getCodiceChiamante () + "] non trovato" );
        }
        verificaEsistenzaEnte ( input.getCodiceFiscale () );
        //lollo finisci
//        TipoPagamento tipoPagamento = verificaTipoPagamento ( ente, input.getTipoPagamento (), true, true );
        
        validaChiamanteEsterno ( input.getCodiceChiamante (), "CREAZIONE_IUV", null );
      
        // Verifica dello IUV e del codiceFiscale

        Pagamento pagamento = pagamentoManager.getPagamentoAttivoAndPagabile ( input.getIuv () );
        if ( pagamento == null ) {
            throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                "Codice Iuv riferito a pagamento effettuato, annullato, in attesa di ricevuta, non piu' valido perche' scaduto oppure non trovato." );
        }

//        if ( !pagamento.getPagatore ().getCodiceFiscale ().equalsIgnoreCase ( input.getCodiceFiscale () ) ) {
//            throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
//                            "Codice fiscale del pagatore non coincidente con codice fiscale inviato" );
//        }
        //controllo se il pagamento e' stato gia' tentato
        StatoPagamento enumStatoPagamento = StatoPagamento.findById ( pagamento.getIdStatoCorrente () );
        if ( enumStatoPagamento == StatoPagamento.ANNULLATO || /*
                                                                * enumStatoPagamento == StatoPagamento.FALLITO ||
                                                                */enumStatoPagamento == StatoPagamento.TRANSAZIONE_ERRORE ) {
            throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                            "Codice Iuv riferito a pagamento annullato, fallito o non piu' valido." );
        }

		/* RDI-54 MODELLO UNICO - BEGIN */
		// prepara i parametri per il getTransaction
		List<Pagamento> pagamentoList = new ArrayList<Pagamento> ();
		pagamentoList.add ( pagamento );
		String email = pagamento.getPagatore ().getEmail ();
		TracciabilitaChiamanteEsterno chiamanteEsterno = TracciabilitaChiamanteEsterno.builder ()
			.withChiamanteEsterno ( chiamante )
			.withIuv ( input.getIuv () )
			.withIdentificativoPagamento ( input.getIuv () )
			.withRemoteHost ( input.getIpChiamante () )
			.withTimestampChiamata ( new Date () ).build ();
		//
        Transaction transaction;
		try {
			transaction = pagamentoFacade.getTransaction ( pagamentoList, email, REGISTRO_VERSAMENTO_ORIGINE_INSERIMENTO, chiamanteEsterno );

		} catch ( Exception e ) {
			tracciaRegistroPagamento ( pagamento, StatoPagamento.TRANSAZIONE_ERRORE, null );
			throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_GENERICO, e.getMessage () );			
		}
        String paymentUrl = transaction.getPaymentUrl ();
		/* RDI-54 MODELLO UNICO - END */

//		// OLD BY MDP BEGIN
//		transazione = mdpCore.initializzaTransazione ( pagamento, MDP_NUMBER_RETRY );
//		transazione.setAmount ( pagamento.getImporto ().doubleValue () );
//		log.info ( methodName, "inizializzata transazione : " + transazione.getTransactionId () );
//
//		transazioneMdp = new TransazioneMdp ();
//		transazioneMdp.setIdTransazione ( transazione.getTransactionId () );
//		transazioneMdp.setIuv ( pagamento.getIuv () );
//		transazioneMdpManager.inserisci ( transazioneMdp );
//		log.debug ( methodName, "inserita traccia transazione : \n" + XmlUtil.obj2Xml ( transazioneMdp ) );
//
//		tracciaChiamataSuDB ( TracciabilitaChiamanteEsterno.builder ().withChiamanteEsterno ( chiamante )
//			.withIuv ( input.getIuv () )
//			.withIdentificativoPagamento ( input.getIuv () )
//			.withRemoteHost ( input.getIpChiamante () )
//			.withTimestampChiamata ( new Date () ).build (), methodName );
//		tracciaRegistroPagamento ( pagamento, StatoPagamento.TRANSAZIONE_INIZIALIZZATA, transazioneMdp );
//
//		ElencoRPT datiRPT = costruisciRPT ( pagamento, transazione, pagamento.getTipoPagamento ().getFlagMultibeneficiario () );
//		log.debug ( methodName, "costruito payload RPT : " + XmlUtil.obj2Xml ( datiRPT ) );
//
//		try {
//			paymentUrl = mdpCore.paymentURL ( transazione, datiRPT, Boolean.TRUE.equals ( pagamento.getTipoPagamento ().getFlagMultibeneficiario () ) );
//			tracciaRegistroPagamento ( pagamento, StatoPagamento.TRANSAZIONE_AVVIATA, transazioneMdp );
//
//		} catch ( it.csi.epay.epayservices.integration.mdp.MdpException e ) {
//			tracciaRegistroPagamento ( pagamento, StatoPagamento.TRANSAZIONE_ERRORE, transazioneMdp );
//			if ( e.getCause () != null && e.getCause ().getMessage () != null && e.getCause ().getMessage ().contains ( "RPT duplicata" ) ) {
//				String message = "Il pagamento non puo' essere effettuato perche' risulta gia' una transazione di pagamento in corso.";
//				throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, message );
//			} else {
//				String message = "Errore temporaneo di comunicazione. Si prega di riprovare piu' tardi. Se l'errore dovesse persiste contattare l'assistenza.";
//				throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_GENERICO, message );
//			}
//		}
//		// OLD BY MDP END

		log.info ( methodName, "paymentUrl: " + paymentUrl );
		log.info ( methodName, "END" );
		return success ( EsitoChiamataEsterna.OPERAZIONE_COMPLETATA_CON_SUCCESSO, pagamento.getIuv (), paymentUrl );
	}

	private PagamentoIuvChiamanteEsternoOutput success ( String codiceEsito, String iuv, String paymentUrl ) {
		EsitoChiamataEsterna esito;
		try {
			esito = esitoChiamataEsternaManager.ricerca ( codiceEsito );
		} catch ( IllegalAccessException | NoDataException e ) {
			throw new RuntimeException ( "Esito chiamata esterna non trovato: " + codiceEsito, e );
		}

		PagamentoIuvChiamanteEsternoOutput output = new PagamentoIuvChiamanteEsternoOutput ();
		output.setCodiceEsito ( EsitoChiamataEsterna.OPERAZIONE_COMPLETATA_CON_SUCCESSO );
		output.setDescrizioneEsito ( esito.getDescrizione () );
		output.setIuv ( iuv );
		output.setPaymentUrl ( paymentUrl );

		return output;
	}

    private void tracciaRegistroPagamento ( Pagamento pagamento, StatoPagamento stato, TransazioneMdp transazioneMdp ) {
        tracciaRegistroPagamento ( pagamento, stato, transazioneMdp, null );
    }

    private void tracciaRegistroPagamento ( Pagamento pagamento, StatoPagamento stato, TransazioneMdp transazioneMdp, Long idPagamentoSecondario ) {

        RegistroVersamenti registroVersamenti = new RegistroVersamenti ();
        registroVersamenti.setIdPagamento ( pagamento.getIdPagamento () );
        registroVersamenti.setIdPagamentoSecondario ( idPagamentoSecondario );
        registroVersamenti.setRisultato ( stato.getDescrizione () );
        if ( transazioneMdp != null ) {
            registroVersamenti.setIdTransazione ( transazioneMdp.getIdTransazione () );
        }
        registroVersamenti.setIuv (
            StringUtils.isNotEmpty ( pagamento.getIuvRegistroVersamenti () ) ? pagamento.getIuvRegistroVersamenti ()
                            : pagamento.getIuv () );
        registroVersamenti.setIdStato ( stato.getId () );
        registroVersamenti.setOrigineInserimento ( REGISTRO_VERSAMENTO_ORIGINE_INSERIMENTO );

        if ( registroVersamenti.getAnagraficaVersante () != null ) {
            Anagrafica newAnagrafica = anagraficaManager.inserisci ( registroVersamenti.getAnagraficaVersante () );
            registroVersamenti.setAnagraficaVersante ( newAnagrafica );
        }

        Long result = registroVersamentiManager.inserisci ( registroVersamenti );
        registroVersamenti.setIdRegistro ( result );

        log.debug ( "tracciaRegistroPagamento",
            "inserita voce di tracciatura versamento : \n" + XmlUtil.obj2Xml ( registroVersamenti ) );
    }
    
    private ElencoRPT costruisciRPT ( Pagamento pagamento, Transazione transazione, Boolean multibeneficiario ) {

        //RDI-45 multibeneficiario
        if ( multibeneficiario != null && multibeneficiario ) {
            ElencoRPT elencoRPT = costruisciRPTMultibeneficiario ( pagamento, transazione );
            return elencoRPT;
        }

        SoggettoPagatore soggettoPagatore = new SoggettoPagatore ();
        soggettoPagatore.setIdentificativoUnivocoPagatore ( pagamento.getPagatore ().getCodiceFiscale () );
        if ( pagamento.getPagatore ().getFlagPersonaFisica () != null && pagamento.getPagatore ().getFlagPersonaFisica () ) {
            soggettoPagatore.setTipoIdentificativoUnivocoPagatore ( "F" );
            soggettoPagatore.setAnagraficaPagatore ( StringUtils.substring (StringUtils.join (
                new String [] { pagamento.getPagatore ().getNome (), pagamento.getPagatore ().getCognome () }, " " ), 0, 70) );
        } else {
            soggettoPagatore.setTipoIdentificativoUnivocoPagatore ( "G" );
            soggettoPagatore.setAnagraficaPagatore ( StringUtils.substring ( pagamento.getPagatore ().getRagioneSociale (), 0, 70) );
        }
        if ( StringUtils.isNotBlank ( pagamento.getPagatore ().getEmail () ) ) {
            soggettoPagatore.setEMailPagatore ( pagamento.getPagatore ().getEmail () );
        }

        DatiVersamentoRPT datiVersamentoRpt = new DatiVersamentoRPT ();
        datiVersamentoRpt.setImportoTotaleDaVersare ( pagamento.getImporto () );
        datiVersamentoRpt.setIdentificativoUnivocoVersamento ( pagamento.getIuvRegistroVersamenti () );

        if ( pagamento.getComponenti () == null || pagamento.getComponenti ().isEmpty () ) {
            String causaleVersamento = componiCausaleVersamento ( pagamento.getIuvRegistroVersamenti (),
                pagamento.getImporto (), pagamento.getTipoPagamento ().getDescrizionePortale () );
            DatiSingoloVersamentoRPT datiSingoloVersamentoRPT = new DatiSingoloVersamentoRPT ();
            datiSingoloVersamentoRPT.setCausaleVersamento ( causaleVersamento );
            datiSingoloVersamentoRPT.setImportoSingoloVersamento ( pagamento.getImporto () );
            datiSingoloVersamentoRPT
            .setDatiSpecificiRiscossione ( pagamento.getTipoPagamento ().getDatiSpecificiRiscossione () );

            DatiAccertamentoRPT datiAccertamentoRPT = new DatiAccertamentoRPT ();
            datiAccertamentoRPT.setAnnoAccertamento ( pagamento.getAnnoAccertamento () );
            try {
                datiAccertamentoRPT.setNumeroAccertamento (
                    pagamento.getNumeroAccertamento () != null ? pagamento.getNumeroAccertamento () : null );
            } catch ( NumberFormatException nfe ) {
                throw new RuntimeException ( "Numero pagamento " + pagamento.getIdPagamento ()
                + " ha il formato del numero accertamento errato" );
            }
            datiSingoloVersamentoRPT.setDatiAccertamento ( datiAccertamentoRPT );

            datiVersamentoRpt.getDatiSingoloVersamento ().add ( datiSingoloVersamentoRPT );
        } else {
            for ( PagamentoComponenti componente: pagamento.getComponenti () ) {
                String causaleVersamento = componiCausaleVersamento ( pagamento.getIuvRegistroVersamenti (),
                    componente.getImporto (), componente.getCausale () );
                DatiSingoloVersamentoRPT datiSingoloVersamentoRPT = new DatiSingoloVersamentoRPT ();
                datiSingoloVersamentoRPT.setCausaleVersamento ( causaleVersamento );
                datiSingoloVersamentoRPT.setImportoSingoloVersamento ( componente.getImporto () );
                datiSingoloVersamentoRPT.setDatiSpecificiRiscossione ( componente.getDatiSpecificiRiscossione () );
                DatiAccertamentoRPT datiAccertamentoRPT = new DatiAccertamentoRPT ();
                datiAccertamentoRPT.setAnnoAccertamento ( componente.getAnnoAccertamento () );
                try {
                    datiAccertamentoRPT.setNumeroAccertamento ( componente.getNumeroAccertamento () != null
                                    ? Integer.valueOf ( componente.getNumeroAccertamento () )
                                                    : null );
                } catch ( NumberFormatException nfe ) {
                    throw new RuntimeException ( "Numero pagamento " + pagamento.getIdPagamento ()
                    + " ha il formato del numero accertamento errato" );
                }
                datiSingoloVersamentoRPT.setDatiAccertamento ( datiAccertamentoRPT );

                datiVersamentoRpt.getDatiSingoloVersamento ().add ( datiSingoloVersamentoRPT );
            }
        }

        RPTData rptData = new RPTData ();
        rptData.setAutenticazioneSoggetto ( "N/A" );
        rptData.setSoggettoPagatore ( soggettoPagatore );
        rptData.setSoggettoVersante ( null );
        rptData.setDatiVersamento ( datiVersamentoRpt );
        rptData.setApplicationId ( transazione.getApplicationId () );

        ElencoRPT elencoRPT = new ElencoRPT ();
        elencoRPT.getRptdata ().add ( rptData );

        return elencoRPT;
    }
    
    
    private ElencoRPT costruisciRPTMultibeneficiario ( Pagamento pagamento, Transazione transazione ) {

        SoggettoPagatore soggettoPagatore = new SoggettoPagatore ();
        soggettoPagatore.setIdentificativoUnivocoPagatore ( pagamento.getPagatore ().getCodiceFiscale () );
        if ( pagamento.getPagatore ().getFlagPersonaFisica () != null && pagamento.getPagatore ().getFlagPersonaFisica () ) {
            soggettoPagatore.setTipoIdentificativoUnivocoPagatore ( "F" );
            soggettoPagatore.setAnagraficaPagatore (
                StringUtils.join ( new String [] { pagamento.getPagatore ().getNome (), pagamento.getPagatore ().getCognome () }, " " ) );
        } else {
            soggettoPagatore.setTipoIdentificativoUnivocoPagatore ( "G" );
            soggettoPagatore.setAnagraficaPagatore ( pagamento.getPagatore ().getRagioneSociale () );
        }
        if ( StringUtils.isNotBlank ( pagamento.getPagatore ().getEmail () ) ) {
            soggettoPagatore.setEMailPagatore ( pagamento.getPagatore ().getEmail () );
        }

        DatiVersamentoRPT datiVersamentoRpt = new DatiVersamentoRPT ();
        datiVersamentoRpt.setImportoTotaleDaVersare ( pagamento.getImportoPrincipale () );
        datiVersamentoRpt.setIdentificativoUnivocoVersamento ( pagamento.getIuvRegistroVersamenti () );

        for ( PagamentoComponenti componente: pagamento.getComponenti () ) {
            String causaleVersamento
                = componiCausaleVersamento ( pagamento.getIuvRegistroVersamenti (), componente.getImporto (), componente.getCausale () );
            DatiSingoloVersamentoRPT datiSingoloVersamentoRPT = new DatiSingoloVersamentoRPT ();
            datiSingoloVersamentoRPT.setCausaleVersamento ( causaleVersamento );
            datiSingoloVersamentoRPT.setImportoSingoloVersamento ( componente.getImporto () );
            datiSingoloVersamentoRPT.setDatiSpecificiRiscossione ( componente.getDatiSpecificiRiscossione () );
            DatiAccertamentoRPT datiAccertamentoRPT = new DatiAccertamentoRPT ();
            datiAccertamentoRPT.setAnnoAccertamento ( componente.getAnnoAccertamento () );
            try {
                datiAccertamentoRPT
                    .setNumeroAccertamento (
                        componente.getNumeroAccertamento () != null ? Integer.valueOf ( componente.getNumeroAccertamento () ) : null );
            } catch ( NumberFormatException nfe ) {
                throw new RuntimeException ( "Numero pagamento " + pagamento.getIdPagamento () + " ha il formato del numero accertamento errato" );
            }
            datiSingoloVersamentoRPT.setDatiAccertamento ( datiAccertamentoRPT );

            datiVersamentoRpt.getDatiSingoloVersamento ().add ( datiSingoloVersamentoRPT );
        }

        RPTData rptData1 = new RPTData ();
        rptData1.setAutenticazioneSoggetto ( "N/A" );
        rptData1.setSoggettoPagatore ( soggettoPagatore );
        rptData1.setSoggettoVersante ( null );
        rptData1.setDatiVersamento ( datiVersamentoRpt );
        rptData1.setApplicationId ( transazione.getApplicationId () );

        ElencoRPT elencoRPT = new ElencoRPT ();
        elencoRPT.getRptdata ().add ( rptData1 );

        //secondo blocco rptData

        //        SoggettoPagatore soggettoPagatore = new SoggettoPagatore ();
        //        soggettoPagatore.setIdentificativoUnivocoPagatore ( pagamento.getPagatore ().getCodiceFiscale () );
        //        if ( pagamento.getPagatore ().getFlagPersonaFisica () != null && pagamento.getPagatore ().getFlagPersonaFisica () ) {
        //            soggettoPagatore.setTipoIdentificativoUnivocoPagatore ( "F" );
        //            soggettoPagatore.setAnagraficaPagatore (
        //                StringUtils.join ( new String [] { pagamento.getPagatore ().getNome (), pagamento.getPagatore ().getCognome () }, " " ) );
        //        } else {
        //            soggettoPagatore.setTipoIdentificativoUnivocoPagatore ( "G" );
        //            soggettoPagatore.setAnagraficaPagatore ( pagamento.getPagatore ().getRagioneSociale () );
        //        }
        //        soggettoPagatore.setEMailPagatore ( pagamento.getPagatore ().getEmail () );

        //DatiVersamentoRPT datiVersamentoRpt = new DatiVersamentoRPT ();

        //recupero il pagamento secondario associato al principale
        try {
            DatiVersamentoRPT datiVersamentoRpt2 = new DatiVersamentoRPT ();
            PagamentoSecondario pagamentoSecondario = pagamentoManager.getPagamentoSecondarioByIdPagamentoPrincipale ( pagamento.getIdPagamento () );
            datiVersamentoRpt2.setImportoTotaleDaVersare ( pagamentoSecondario.getImportoComplessivo () );
            datiVersamentoRpt2.setIdentificativoUnivocoVersamento ( pagamento.getIuvRegistroVersamenti () );

            //recuperare record da epay_t_pagamento_secondario_componenti
            List<PagamentoSecondarioComponenti> pagamentoSecondarioComponenti
                = pagamentoManager.getPagamentoSecondarioComponentiById ( pagamentoSecondario.getIdPagamentoSecondario () );

            for ( PagamentoSecondarioComponenti componenteSecondaria: pagamentoSecondarioComponenti ) {
                String causaleVersamento
                    = componiCausaleVersamento ( pagamento.getIuvRegistroVersamenti (), componenteSecondaria.getImporto (),
                        componenteSecondaria.getCausale () );
                DatiSingoloVersamentoRPT datiSingoloVersamentoRPT = new DatiSingoloVersamentoRPT ();
                datiSingoloVersamentoRPT.setCausaleVersamento ( causaleVersamento );
                datiSingoloVersamentoRPT.setImportoSingoloVersamento ( componenteSecondaria.getImporto () );
                datiSingoloVersamentoRPT.setDatiSpecificiRiscossione ( componenteSecondaria.getDatiSpecificiRiscossione () );
                DatiAccertamentoRPT datiAccertamentoRPT = new DatiAccertamentoRPT ();
                datiAccertamentoRPT.setAnnoAccertamento ( componenteSecondaria.getAnnoAccertamento () );
                try {
                    datiAccertamentoRPT
                        .setNumeroAccertamento (
                            componenteSecondaria.getNumeroAccertamento () != null ? Integer.valueOf ( componenteSecondaria.getNumeroAccertamento () ) : null );
                } catch ( NumberFormatException nfe ) {
                    throw new RuntimeException ( "Numero pagamento " + pagamento.getIdPagamento () + " ha il formato del numero accertamento errato" );
                }
                datiSingoloVersamentoRPT.setDatiAccertamento ( datiAccertamentoRPT );

                datiVersamentoRpt2.getDatiSingoloVersamento ().add ( datiSingoloVersamentoRPT );
            }

            RPTData rptData2 = new RPTData ();
            rptData2.setAutenticazioneSoggetto ( "N/A" );
            rptData2.setSoggettoPagatore ( soggettoPagatore );
            rptData2.setSoggettoVersante ( null );
            rptData2.setDatiVersamento ( datiVersamentoRpt2 );

            rptData2.setApplicationId ( pagamentoSecondario.getTipoPagamento ().getIdApplicazione () );

            elencoRPT.getRptdata ().add ( rptData2 );
            return elencoRPT;
        } catch ( NoDataException | MoreResultException e ) {
            log.error ( "costruisciRPTMultibeneficiario", "Errore in fase di composizione del pagamento secondario con iuv : " + pagamento.getIuv (), e );
            throw new RuntimeException ( "Errore in fase di composizione del pagamento secondario con iuv : " + pagamento.getIuv () );
        }
    }
    
    
    private String componiCausaleVersamento ( String iuv, BigDecimal importo, String descrizione ) {
        StringBuilder composizioneCausale = new StringBuilder ( iuv.length () == 25 ? "/RFS/" : "/RFB/" );
        composizioneCausale.append ( iuv );
        composizioneCausale.append ( "/" );
        composizioneCausale.append ( importo.toString () );
        if ( StringUtils.isNotBlank ( descrizione ) ) {
            composizioneCausale.append ( "/TXT/" ).append ( descrizione );
        }
        return composizioneCausale.substring ( 0, Math.min ( composizioneCausale.length (), 140 ) );
    }
    
    
	public AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioOutput doAggiornaPosizioneDebitoriaMultibeneficiarioUsoInterno(
			AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioInput input, Pagamento pagamento)	
					throws TestataException, PosizioneDebitoriaException, TassonomiaServiceException {
		final String methodName = "aggiornaPosizioneDebitoriaMultibeneficiarioUsoInterno";
		
		log.debugStart(methodName);

		if (null != input.getImportoTotale() || null != input.getImportoPrincipale()
				|| null != input.getImportoSecondario()) {
			testField(input.getImportoTotale(), CodiciEsito.POSIZIONI_DEBITORIE_IMPORTO_TOTALE_OBBLIGATORIO);
			testField(input.getImportoPrincipale(), CodiciEsito.POSIZIONI_DEBITORIE_IMPORTO_PRINCIPALE_OBBLIGATORIO);
			testField(input.getImportoSecondario(), CodiciEsito.POSIZIONI_DEBITORIE_IMPORTO_SECONDARIO_OBBLIGATORIO);

			if (input.getImportoTotale().compareTo(new BigDecimal(0)) <= 0
					|| input.getImportoPrincipale().compareTo(new BigDecimal(0)) <= 0
					|| input.getImportoSecondario().compareTo(new BigDecimal(0)) <= 0) {
				throw new PosizioneDebitoriaException(CodiciEsito.IMPORTI_OBBLIGATORI.getCodice(),
						CodiciEsito.IMPORTI_OBBLIGATORI.getMessaggio(MAX_ERROR_MESSAGE_WIDTH));
			}

		}

		EpayTPagamento tPagamento;

        try {
            tPagamento = pagamentoManager.getPagamentoPerCFEnteECovEIuv ( input.getCodiceFiscaleEnte(), input.getTipoPagamento(), input.getIuv () );
            if ( tPagamento == null ) {
                throw new NoDataException ();
            }
        } catch ( NoDataException e ) {
            CodiciEsito ce = CodiciEsito.PAGAMENTO_NON_TROVATO_PER_POSIZIONE_DEBITORIA;
            throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH, input.getCodiceFiscaleEnte(),
                		input.getTipoPagamento(), input.getIuv () ) );
        } catch ( MoreResultException e ) {
            CodiciEsito ce = CodiciEsito.CODICE_ID_POSIZIONE_DEBITORIA_DUPLICATA;
            throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH, input.getCodiceFiscaleEnte(),
                		input.getTipoPagamento(), input.getIuv () ) );
        }

        if (  null != input.getRequiresCostUpdate() ) {
            tPagamento.setRequiresCostUpdate(input.getRequiresCostUpdate());
        }

		if (null != input.getImportoTotale()) {
			tPagamento.setImporto(input.getImportoTotale());
		}

		if (null != input.getImportoPrincipale()) {
			tPagamento.setImportoPrincipale(input.getImportoPrincipale());
		}
		tPagamento.setUtenteUltimaModifica(methodName);
		
		DatiSpecificiRiscossioneOutput dsr;
		DatiSpecificiRiscossioneOutput dsrSec;
		
		boolean hasComponenti = CollectionUtils.isNotEmpty(tPagamento.getEpayTPagamentoComponentis())  && tPagamento.getEpayTPagamentoComponentis().size()>1;
        
		if (hasComponenti) {
			try {
				dsr = getDatiSpecificiRiscossione(input.getCodiceFiscaleEnte(), input.getTipoPagamento());
			} catch (TassonomiaServiceException e1) {
				throw new TestataException(EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, e1.getMessage());
			}
			pagamento.setComponenti(new LinkedList<>()); // Istruzione per eliminare i componenti precedenti.
			for (ComponentiImporto componenteImportoType : input.getComponentiImporto()) {
				if (null != componenteImportoType.getImporto()) {
					PagamentoComponenti componente = new PagamentoComponenti();
					componente.setImporto(componenteImportoType.getImporto());
					componente.setCausale(componenteImportoType.getCausaleDescrittiva());
					componente.setAnnoAccertamento(componenteImportoType.getAnnoAccertamento() != null
							? componenteImportoType.getAnnoAccertamento()
							: null);
					componente.setNumeroAccertamento(
							StringUtils.isBlank(componenteImportoType.getNumeroAccertamento()) ? null
									: componenteImportoType.getNumeroAccertamento());
					componente.setUtenteUltimaModifica(methodName);
					inserisciDatoSpecificoRiscossioneInComponente(componente, dsr);

					pagamento.getComponenti().add(componente);
				}
			}
			pagamentoManager.aggiornaImporti(tPagamento, pagamento.getComponenti());
		} else if (pagamento.getComponenti().size() == 1 && null != input.getImportoPrincipale()) {
				pagamento.getComponenti().get(0).setImporto(input.getImportoPrincipale());
				pagamentoManager.aggiornaImporti(tPagamento, pagamento.getComponenti());
			}

		// c) Aggiornamento dell'importo secondario
		if (Boolean.TRUE.equals(pagamento.getTipoPagamento().getFlagMultibeneficiario())) {

			// Nelle verifiche fatte in precedenza i componenti degli importi e i
			// riferimenti erano a posto.

			PagamentoSecondario pagamentoImportiSecondario;
			try {
				pagamentoImportiSecondario = pagamentoManager
						.getPagamentoSecondarioByIdPagamentoPrincipale(pagamento.getIdPagamento());
			} catch (NoDataException | MoreResultException e) {
				throw new TestataException(EsitoChiamataEsterna.ERRORE_GENERICO,
						CodiciEsito.PAGAMENTO_SECONDARIO_NON_TROVATO.getMessaggio(MAX_ERROR_MESSAGE_WIDTH));
			}
			if (input.getImportoSecondario() != null) {
				pagamentoImportiSecondario.setImportoComplessivo(input.getImportoSecondario());
			}

			pagamentoImportiSecondario.setCausale(pagamentoImportiSecondario.getCausale());
			boolean hasComponentiSecondari = CollectionUtils.isNotEmpty(tPagamento.getEpayTPagamentoSecondarios())  && tPagamento.getEpayTPagamentoSecondarios().size()==1;
			if (hasComponentiSecondari) { // ce ne deve essere solo uno
				try {
					dsrSec = getDatiSpecificiRiscossione(input.getCodiceFiscaleEnte(), input.getTipoPagamento());
				} catch (TassonomiaServiceException e1) {
					throw new TestataException(EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
							e1.getMessage());
				}
				pagamentoImportiSecondario.setComponenti(new LinkedList<>());
				for (ComponentiImportoSecondario componenteImporto : input.getComponentiImportoSecondario()) {

					pagamentoImportiSecondario.getComponenti()
							.add(creaComponenteDiDefault(dsrSec, componenteImporto.getCausale(),
									componenteImporto.getImporto(), input.getIuv(), methodName));
				}
			} else if (input.getImportoSecondario() != null) {
				PagamentoComponenti componenteImportoSec = pagamentoImportiSecondario.getComponenti().get(0);
				componenteImportoSec.setImporto(input.getImportoSecondario());
			}
			pagamentoManager.aggiornaImportiSecondari(pagamentoImportiSecondario);
		}
		
		log.debugEnd(methodName);
		return AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioOutput.builder()
				.withResult(Esito.builder().withCode(CodiciEsito.ESECUZIONE_OK.getCodice())
						.withDescription(CodiciEsito.ESECUZIONE_OK.getMessaggio(MAX_ERROR_MESSAGE_WIDTH)).build())
				.build();
	}


	public AggiornaPosizioneDebitoriaChiamanteEsternoOutput doAggiornaPosizioneDebitoriaUsoInterno(
			AggiornaPosizioneDebitoriaChiamanteEsternoInput input, Pagamento pagamento) throws TestataException, PosizioneDebitoriaException, TassonomiaServiceException {
		
		final String methodName = "aggiornaPosizioneDebitoriaUsoInterno";
        log.debugStart ( methodName );

        EpayTPagamento tPagamento;

        try {
            tPagamento = pagamentoManager.getPagamentoPerCFEnteECovEIuv ( input.getCodiceFiscaleEnte(), input.getTipoPagamento(), input.getIuv () );
            if ( tPagamento == null ) {
                throw new NoDataException ();
            }
        } catch ( NoDataException e ) {
            CodiciEsito ce = CodiciEsito.PAGAMENTO_NON_TROVATO_PER_POSIZIONE_DEBITORIA;
            throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH, input.getCodiceFiscaleEnte(),
                		input.getTipoPagamento(), input.getIuv () ) );
        } catch ( MoreResultException e ) {
            CodiciEsito ce = CodiciEsito.CODICE_ID_POSIZIONE_DEBITORIA_DUPLICATA;
            throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH, input.getCodiceFiscaleEnte(),
                		input.getTipoPagamento(), input.getIuv () ) );
        }
        
        boolean hasComponenti = CollectionUtils.isNotEmpty(tPagamento.getEpayTPagamentoComponentis())  && tPagamento.getEpayTPagamentoComponentis().size()>1;
        
        if (  null != input.getRequiresCostUpdate() ) {
            tPagamento.setRequiresCostUpdate(input.getRequiresCostUpdate());
        }

        if ( null != input.getImportoTotale () ) {
            tPagamento.setImporto ( input.getImportoTotale () );
        }
        tPagamento.setUtenteUltimaModifica ( methodName );
        DatiSpecificiRiscossioneOutput dsr;
        if ( hasComponenti ) {
			try {
				dsr = getDatiSpecificiRiscossione ( input.getCodiceFiscaleEnte (), input.getTipoPagamento () );
			} catch ( TassonomiaServiceException e1 ) {
				throw new TestataException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, e1.getMessage () );
			}
			pagamento.setComponenti ( new LinkedList<> () ); // Istruzione per eliminare i componenti precedenti.
            for ( ComponentiImporto componenteImportoType: input.getComponentiImporto () ) {
                if ( null != componenteImportoType.getImporto () ) {
                    PagamentoComponenti componente = new PagamentoComponenti ();
                    componente.setImporto ( componenteImportoType.getImporto () );
                    componente.setCausale ( componenteImportoType.getCausaleDescrittiva () );
                    componente.setAnnoAccertamento (
                        componenteImportoType.getAnnoAccertamento () != null ? componenteImportoType.getAnnoAccertamento () : null );
                    componente.setNumeroAccertamento (
                        StringUtils.isBlank ( componenteImportoType.getNumeroAccertamento () ) ? null
                                        : componenteImportoType.getNumeroAccertamento () );
                    componente.setUtenteUltimaModifica ( methodName );
                    inserisciDatoSpecificoRiscossioneInComponente ( componente, dsr );

                    pagamento.getComponenti ().add ( componente );
                }
            }
            pagamentoManager.aggiornaImporti ( tPagamento, pagamento.getComponenti () );
        } else if ( CollectionUtils.isNotEmpty(pagamento.getComponenti ()) && pagamento.getComponenti ().size ()>1) {
            CodiciEsito ce = CodiciEsito.COOP_ERRORE_GENERICO;
            throw new PosizioneDebitoriaException ( EsitoChiamataEsterna.ERRORE_DATI_COMPONENTI_NON_CONGRUENTI,
                ce.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH, input.getCodiceFiscaleEnte(),
                    input.getTipoPagamento(), input.getIuv ()) );
            }else if ( pagamento.getComponenti ().size () == 1 && null != input.getImportoTotale ()) {
                pagamento.getComponenti ().get ( 0 ).setImporto ( input.getImportoTotale () );
                pagamentoManager.aggiornaImporti ( tPagamento, pagamento.getComponenti () );
            	}


        log.debugEnd ( methodName );
        return AggiornaPosizioneDebitoriaChiamanteEsternoOutput.builder ().withResult ( Esito.builder ().withCode ( CodiciEsito.ESECUZIONE_OK.getCodice () )
            .withDescription ( CodiciEsito.ESECUZIONE_OK.getMessaggio ( MAX_ERROR_MESSAGE_WIDTH ) ).build () ).build ();
	}


	@TransactionAttribute ( TransactionAttributeType.REQUIRED )
	@Override
	public AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioOutput aggiornaPosizioneDebitoriaMultibeneficiarioUsoInterno(
			AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioInput input, 
			Ente ente, 
			Pagamento pagamento
			){
		
		String nomeMetodo = "aggiornaPosizioneDebitoriaMultibeneficiarioUsoInterno";
        AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioOutput result;
        
        try {
            result = doAggiornaPosizioneDebitoriaMultibeneficiarioUsoInterno(input, pagamento);
        } catch ( TestataException te ) {
            result = AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioOutput.builder ()
                .withResult ( Esito.builder ().withCode ( te.getCode () ).withDescription ( te.getMessage () ).build () ).build ();
            tracciaFallimentoSuDB ( ChiamataEsternaNonValida.builder ().withCodiceChiamante ( input.getCodiceChiamante () )
                .withCodiceFiscale ( input.getSoggettoPagatore () != null ? input.getSoggettoPagatore ().getIdentificativoUnivocoFiscale () : null )
                .withDescrizioneErrore (
                    StringUtils.abbreviate ( ( StringUtils.isBlank ( te.getMessage () ) ? te.getMessage () : "Errore in fase di aggiornamento" )
                        .concat ( ExceptionUtils.getStackTrace ( te ) ), 500 ) )
                .withIuv ( input.getIuv () ).withRemoteHost ( input.getIpChiamante () )
                .withTimestampChiamata ( new Date () ).build (), nomeMetodo );
        } catch ( PosizioneDebitoriaException pde ) {
            result = AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioOutput.builder ()
                .withResult ( Esito.builder ().withCode ( pde.getCode () ).withDescription ( pde.getMessage () ).build () ).build ();
            tracciaFallimentoSuDB ( ChiamataEsternaNonValida.builder ().withCodiceChiamante ( input.getCodiceChiamante () )
                .withCodiceFiscale ( input.getSoggettoPagatore () != null ? input.getSoggettoPagatore ().getIdentificativoUnivocoFiscale () : null )
                .withDescrizioneErrore (
                    StringUtils.abbreviate ( ( StringUtils.isBlank ( pde.getMessage () ) ? pde.getMessage () : "Errore in fase di aggiornamento" )
                        .concat ( ExceptionUtils.getStackTrace ( pde ) ), 500 ) )
                .withIuv ( input.getIuv () ).withRemoteHost ( input.getIpChiamante () )
                .withTimestampChiamata ( new Date () ).build (), nomeMetodo );
        } catch ( Exception t ) {
            result = AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioOutput.builder ()
                .withResult ( Esito.builder ().withCode ( EsitoChiamataEsterna.ERRORE_GENERICO )
                    .withDescription ( "Errore in fase di aggiornamento della posizione debitoria." ).build () )
                .build ();
            tracciaFallimentoSuDB ( ChiamataEsternaNonValida.builder ().withCodiceChiamante ( input.getCodiceChiamante () )
                .withCodiceFiscale ( input.getSoggettoPagatore () != null ? input.getSoggettoPagatore ().getIdentificativoUnivocoFiscale () : null )
                .withDescrizioneErrore (
                    StringUtils.abbreviate ( ( StringUtils.isBlank ( t.getMessage () ) ? t.getMessage () : "Errore in fase di aggiornamento" )
                        .concat ( ExceptionUtils.getStackTrace ( t ) ), 500 ) )
                .withIuv ( input.getIuv () ).withRemoteHost ( input.getIpChiamante () )
                .withTimestampChiamata ( new Date () ).build (), nomeMetodo );
        }
        return result;
	}


	@TransactionAttribute ( TransactionAttributeType.REQUIRED )
	@Override
	public AggiornaPosizioneDebitoriaChiamanteEsternoOutput aggiornaPosizioneDebitoriaUsoInterno(
			AggiornaPosizioneDebitoriaChiamanteEsternoInput input, 
			Ente ente, 
			Pagamento pagamento
			) {
		String nomeMetodo = "aggiornaPosizioneDebitoriaChiamanteEsterno";
        AggiornaPosizioneDebitoriaChiamanteEsternoOutput result;

        try {
            result = doAggiornaPosizioneDebitoriaUsoInterno(input, pagamento);
        } catch ( TestataException te ) {
            result = AggiornaPosizioneDebitoriaChiamanteEsternoOutput.builder ()
                .withResult ( Esito.builder ().withCode ( te.getCode () ).withDescription ( te.getMessage () ).build () ).build ();
            tracciaFallimentoSuDB ( ChiamataEsternaNonValida.builder ().withCodiceChiamante ( input.getCodiceChiamante () )
                .withCodiceFiscale ( input.getSoggettoPagatore () != null ? input.getSoggettoPagatore ().getIdentificativoUnivocoFiscale () : null )
                .withDescrizioneErrore (
                    StringUtils.abbreviate ( ( StringUtils.isBlank ( te.getMessage () ) ? te.getMessage () : "Errore in fase di aggiornamento" )
                        .concat ( ExceptionUtils.getStackTrace ( te ) ), 500 ) )
                .withIuv ( input.getIuv () ).withRemoteHost ( input.getIpChiamante () )
                .withTimestampChiamata ( new Date () ).build (), nomeMetodo );
        } catch ( PosizioneDebitoriaException pde ) {
            result = AggiornaPosizioneDebitoriaChiamanteEsternoOutput.builder ()
                .withResult ( Esito.builder ().withCode ( pde.getCode () ).withDescription ( pde.getMessage () ).build () ).build ();
            tracciaFallimentoSuDB ( ChiamataEsternaNonValida.builder ().withCodiceChiamante ( input.getCodiceChiamante () )
                .withCodiceFiscale ( input.getSoggettoPagatore () != null ? input.getSoggettoPagatore ().getIdentificativoUnivocoFiscale () : null )
                .withDescrizioneErrore (
                    StringUtils.abbreviate ( ( StringUtils.isBlank ( pde.getMessage () ) ? pde.getMessage () : "Errore in fase di aggiornamento" )
                        .concat ( ExceptionUtils.getStackTrace ( pde ) ), 500 ) )
                .withIuv ( input.getIuv () ).withRemoteHost ( input.getIpChiamante () )
                .withTimestampChiamata ( new Date () ).build (), nomeMetodo );
        } catch ( Exception t ) {
            result = AggiornaPosizioneDebitoriaChiamanteEsternoOutput.builder ().withResult ( Esito.builder ().withCode ( EsitoChiamataEsterna.ERRORE_GENERICO )
                .withDescription ( "Errore in fase di aggiornamento della posizione debitoria." ).build () ).build ();
            tracciaFallimentoSuDB ( ChiamataEsternaNonValida.builder ().withCodiceChiamante ( input.getCodiceChiamante () )
                .withCodiceFiscale ( input.getSoggettoPagatore () != null ? input.getSoggettoPagatore ().getIdentificativoUnivocoFiscale () : null )
                .withDescrizioneErrore (
                    StringUtils.abbreviate ( ( StringUtils.isBlank ( t.getMessage () ) ? t.getMessage () : "Errore in fase di aggiornamento" )
                        .concat ( ExceptionUtils.getStackTrace ( t ) ), 500 ) )
                .withIuv ( input.getIuv () ).withRemoteHost ( input.getIpChiamante () )
                .withTimestampChiamata ( new Date () ).build (), nomeMetodo );
        }
        return result;
	}
}
