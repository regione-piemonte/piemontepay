/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayapi.api.impl.v1;

import it.csi.epay.epayapi.api.util.AvvisoPagamentoPdfGenerator;
import it.csi.epay.epayapi.api.util.SpringSupportedResource;
import it.csi.epay.epayapi.api.v1.ChiamataEsternaSincronaV1Api;
import it.csi.epay.epayapi.business.v1.AvvisoPagamentoReportService;
import it.csi.epay.epayapi.business.v1.EpayServicesClientService;
import it.csi.epay.epayapi.dto.response.v1.StampaAvvisoPagamentoResponse;
import it.csi.epay.epayapi.dto.v1.AggiornaPosizioneDebitoriaChiamanteEsternoInputDto;
import it.csi.epay.epayapi.dto.v1.AggiornaPosizioneDebitoriaMultiBeneficiarioChiamanteEsternoInputDto;
import it.csi.epay.epayapi.dto.v1.DestinatarioAvvisoPagamentoDTO;
import it.csi.epay.epayapi.dto.v1.EnteCreditoreAvvisoPagamentoDTO;
import it.csi.epay.epayapi.dto.v1.InfoPagamentoAvvisoPagamentoDTO;
import it.csi.epay.epayapi.integration.dto.EpayTPdfReportDTO;
import it.csi.epay.epayapi.util.Constants;
import it.csi.epay.epayapi.util.Messages;
import it.csi.epay.epayapi.util.SecurityUtils;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.AccessoChiamanteEsternoSincronoSplitInput;
import it.csi.epay.epayservices.model.Anagrafica;
import it.csi.epay.epayservices.model.DatiAvvisoPagamento;
import it.csi.epay.epayservices.model.Ente;
import it.csi.epay.epayservices.model.GetIuvChiamanteEsternoInput;
import it.csi.epay.epayservices.model.GetIuvChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.v1.GetIuvMultibeneficiarioChiamanteEsternoInput;
import it.csi.epay.epayservices.model.GetIuvMultibeneficiarioChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.GetRTChiamanteEsternoInput;
import it.csi.epay.epayservices.model.GetRTChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.Pagamento;
import it.csi.epay.epayservices.model.PagamentoRiferimenti;
import it.csi.epay.epayservices.model.Rt;
import it.csi.epay.epayservices.model.v1.AccessoChiamanteEsternoSincronoInput;
import it.csi.epay.epayservices.model.v1.AccessoChiamanteEsternoSincronoOutput;
import it.csi.epay.epayservices.model.v1.AggiornaPosizioneDebitoriaChiamanteEsternoInput;
import it.csi.epay.epayservices.model.v1.AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioInput;
import it.csi.epay.epayservices.model.v1.AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioOutput;
import it.csi.epay.epayservices.model.v1.AggiornaPosizioneDebitoriaChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.v1.EliminaPosizioneDebitoriaChiamanteEsternoInput;
import it.csi.epay.epayservices.model.v1.EliminaPosizioneDebitoriaChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.v1.Esito;
import it.csi.epay.epayservices.model.v1.GetDatiPagamentoChiamanteEsternoInput;
import it.csi.epay.epayservices.model.v1.GetDatiPagamentoChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.v1.GetStatoPosizioneDebitoriaInput;
import it.csi.epay.epayservices.model.v1.GetStatoPosizioneDebitoriaOutput;
import it.csi.epay.epayservices.model.v1.PagamentoIuvChiamanteEsternoInput;
import it.csi.epay.epayservices.model.v1.PagamentoIuvChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.v1.PagamentoMarcaBolloChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.v1.PagamentoMarcaDaBolloChiamanteEsternoInput;
import it.csi.epay.epayservices.model.v1.PagamentoPerStampaAvvisoInput;
import it.csi.epay.epayservices.model.v1.PagamentoPerStampaAvvisoOutput;
import it.csi.epay.epayservices.model.v1.StampTaxAttachmentChiamanteEsternoInput;
import it.csi.epay.epayservices.model.v1.StampTaxAttachmentChiamanteEsternoOutput;
import net.sf.jasperreports.engine.JRException;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


/**
 * Risorsa RestEasy per il reperimento di informazioni sull'utente corrente.
 * <p>
 * Nota: questa risorsa vale anche come esempio per l'introduzione al supporto di InitializingBean
 */
@SpringSupportedResource
public class ChiamataEsternaSincronaV1ApiImpl implements ChiamataEsternaSincronaV1Api {

	private static final Logger logger = LogManager.getLogger ( ChiamataEsternaSincronaV1ApiImpl.class );

	private static final String UNKNOWN = "UNKNOWN";

	@Autowired
	@Qualifier("EpayServicesClientServiceV1")
	private EpayServicesClientService service;

	@Autowired
	private AvvisoPagamentoReportService avvisoPagamentoReportService;

	@Override
	public Response access ( String organization, String paymentType, AccessoChiamanteEsternoSincronoInput input ) {
		logger.debug ( "[access]:: start" );
		HttpServletRequest request = SecurityUtils.getCurrentRequest ();

		input.setPassword ( "REMOVED" );
		input.setTimestampChiamata ( new Timestamp ( new java.util.Date ().getTime () ) );
		input.setCodiceChiamante ( SecurityUtils.getCurrentClient ().getCodice () );

		if ( request != null ) {
			input.setIpChiamante ( request.getRemoteAddr () );
		} else {
			input.setIpChiamante ( UNKNOWN );
		}

		AccessoChiamanteEsternoSincronoOutput result = service.accessoChiamanteEsternoSincrono ( organization, paymentType, input );

		logger.debug ( "[access]:: stop" );

		return manageResponse ( result, result.getCodiceEsito () );
	}

	@Override
	public Response getIUV ( String organization, String paymentType, GetIuvChiamanteEsternoInput input ) {
		logger.debug ( "[getIUV]:: start" );

		inputHelper( input );

		GetIuvChiamanteEsternoOutput result = service.getIUVChiamanteEsterno ( organization, paymentType, input );

		logger.debug ( "[getIUV]:: stop" );

		return manageResponse ( result, result.getCodiceEsito () );
	}

	@Override
	public Response getIUVMultiPayee ( String organization, String paymentType, GetIuvMultibeneficiarioChiamanteEsternoInput input ) {
		logger.debug ( "[getIUVMultiPayee]:: start" );

		inputHelper( input );

		GetIuvMultibeneficiarioChiamanteEsternoOutput result = service.getIUVMultibeneficiarioChiamanteEsterno ( organization, paymentType, input );

		logger.debug ( "[getIUVMultiPayee]:: stop" );

		return manageResponse ( result, result.getCodiceEsito () );
	}

	@Override
	public Response updateDebtPosition ( String organization, String paymentType, String iuv, AggiornaPosizioneDebitoriaChiamanteEsternoInputDto dto ) {
		logger.debug ( "[updateDebtPosition]:: start" );
		AggiornaPosizioneDebitoriaChiamanteEsternoInput input = dto.getAggiornaPosizioneDebitoriaChiamanteEsternoInput ( organization, paymentType, iuv );
		inputHelper( input );
		AggiornaPosizioneDebitoriaChiamanteEsternoOutput result = service.aggiornaPosizioneChiamanteEsterno ( input );
		logger.debug ( "[updateDebtPosition]:: stop" );
		return manageResponse ( result, result.getResult ().getCode () );
	}

    @Override
    public Response updateDebtPositionMultypayee ( String organization, String paymentType, String iuv, AggiornaPosizioneDebitoriaMultiBeneficiarioChiamanteEsternoInputDto dto ) {
        logger.debug ( "[updateDebtPosition]:: start" );
        AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioInput input = dto.getAggiornaPosizioneDebitoriaChiamanteEsternoInput ( organization, paymentType, iuv );
        inputHelper( input );
        AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioOutput result = service.aggiornaPosizioneMultibeneficiarioChiamanteEsterno ( input );
        logger.debug ( "[updateDebtPosition]:: stop" );
        return manageResponse ( result, result.getResult ().getCode () );
    }
    
    @Override
    public Response deleteDebtPosition ( String organization, String paymentType, String iuv ) {
        logger.debug ( "[deleteDebtPosition]:: start" );
		EliminaPosizioneDebitoriaChiamanteEsternoInput input = new EliminaPosizioneDebitoriaChiamanteEsternoInput ();
		input.setCodiceFiscaleEnte ( organization );
		input.setTipoPagamento ( paymentType );
		input.setIuv ( iuv );
		inputHelper ( input );
        EliminaPosizioneDebitoriaChiamanteEsternoOutput result = service.eliminaPosizioneChiamanteEsterno ( input );

        logger.debug ( "[deleteDebtPosition]:: stop" );

        return manageResponse ( result, result.getResult ().getCode () );
    }

	@Override
	public Response getIUVPayment ( String organization, String iuv ) {
		logger.debug ( "[pagamentoIUV]:: start" );
		PagamentoIuvChiamanteEsternoInput input = new PagamentoIuvChiamanteEsternoInput ( organization, iuv );

		inputHelper ( input );

		PagamentoIuvChiamanteEsternoOutput result = service.getPagamentoIUVChiamanteEsterno ( input );

		logger.debug ( "[pagamentoIUV]:: stop" );

		return manageResponse ( result, result.getCodiceEsito () );
	}

	@Override
	public Response stampTaxPayment ( String organization, String paymentType, PagamentoMarcaDaBolloChiamanteEsternoInput input ) {
		logger.debug ( "[stampTaxPayment]:: start" );
		input.setCodiceFiscaleEnte ( organization );
		input.setCodiceMarca ( paymentType );
		inputHelper( input );

		PagamentoMarcaBolloChiamanteEsternoOutput result = service.getPagamentoMarcaBolloChiamanteEsterno ( input );

		logger.debug ( "[stampTaxPayment]:: stop" );

		return manageResponse ( result, result.getCodiceEsito () );
	}

	@Override
	public Response getRT ( GetRTChiamanteEsternoInput input ) {
		logger.debug ( "[getRt]:: start" );

		inputHelper( input );

		GetRTChiamanteEsternoOutput result = service.getRTChiamanteEsterno ( input );

		logger.debug ( "[getRt]:: stop" );

		return manageResponse ( result, result.getCodiceEsito () );
	}

	@Override public Response getStatoPosizioneDebitoria ( String organization, String paymentType, String iuv ) {
		logger.debug ( "[getStatoPosizioneDebitoria]:: start" );
		GetStatoPosizioneDebitoriaInput input = new GetStatoPosizioneDebitoriaInput ( organization, paymentType, iuv );
		inputHelper ( input );
		GetStatoPosizioneDebitoriaOutput result = service.getStatoPosizioneDebitoriaChiamanteEsterno ( input );
		logger.debug ( "[getStatoPosizioneDebitoria]:: stop" );
		return manageResponse ( result, result.getResult ().getCode () );
	}

	@Override public Response getDatiPagamento ( String organization, String paymentType, String iuv ) {
		logger.debug ( "[getDatiPagamento]:: start" );
		GetDatiPagamentoChiamanteEsternoInput input = new GetDatiPagamentoChiamanteEsternoInput ( organization, paymentType, iuv );
		inputHelper ( input );
		GetDatiPagamentoChiamanteEsternoOutput result = service.getDatiPagamentoChiamanteEsterno ( input );
		logger.debug ( "[getDatiPagamento]:: stop" );
		return manageResponse ( result, result.getResult ().getCode () );
	}

	@Override 
	public Response getStampaAvvisoPagamento ( String organization, String paymentType, String iuv ) {
		logger.debug ( "[getStampaAvvisoPagamento]:: start" );
		StampaAvvisoPagamentoResponse response;

        if ( StringUtils.isBlank ( iuv ) ) {
            return manageResponse ( StampaAvvisoPagamentoResponse.builder ()
                .withResult ( Esito.builder ().withCode ( Constants.RETURN_KO ).withDescription ( Messages.IUV_NON_VALORIZZATO.getMessage () ).build () )
                .build (), Constants.RETURN_KO );
        }

		// EPAY_175: la ricerca ed i controlli sul pagamento sono identici a quella fatta in epayweb,
		// nel momento in cui si stampa l'avviso di un pagamento di cui si ha lo IUV, in quanto, da analisi e' richiesto che i due siano identici
		PagamentoPerStampaAvvisoOutput pagamentoPerStampaAvvisoOutput;
		PagamentoPerStampaAvvisoInput pagamentoPerStampaAvvisoInput = new PagamentoPerStampaAvvisoInput ( organization, paymentType, iuv, SecurityUtils.getCurrentClient ().getCodice ());
		try {
			pagamentoPerStampaAvvisoOutput = service.ricercaPagamentoByIUV ( pagamentoPerStampaAvvisoInput ); // ritorna: eventuale errore t/f, messaggio, pagamento
			if ( pagamentoPerStampaAvvisoOutput.isError () ) {
	            return manageResponse ( StampaAvvisoPagamentoResponse.builder ()
	                .withResult ( Esito.builder ().withCode ( Constants.RETURN_KO ).withDescription ( pagamentoPerStampaAvvisoOutput.getMessage () ).build () )
	                .build (), Constants.RETURN_KO );
			}
			Rt rt;
			try {
				rt = service.ricercaRtByIuv ( iuv );
				if ( null != rt && rt.getIdRR () != null ) {
	                return manageResponse ( StampaAvvisoPagamentoResponse.builder ()
	                    .withResult ( Esito.builder ().withCode ( Constants.RETURN_KO ).withDescription ( Messages.IUV_REVOCATO.getMessage () ).build () )
	                    .build (), Constants.RETURN_KO );
				}
			} catch ( NoDataException noDataExceptionRt ) {
				logger.debug ( "RT non trovata" );
			}
		} catch ( NoDataException e ) {
            return manageResponse ( StampaAvvisoPagamentoResponse.builder ()
                .withResult ( Esito.builder ().withCode ( Constants.RETURN_KO ).withDescription ( Messages.IUV_INESISTENTE.getMessage () ).build () )
                .build (), Constants.RETURN_KO );
		}

		Pagamento pagamento = pagamentoPerStampaAvvisoOutput.getPagamento ();
		if ( pagamento.getImporto ().doubleValue () > Constants.MAX_IMPORTO ) {
			logger.error ( "Importo dovuto superiore al max consentito {}", Constants.MAX_IMPORTO );
            return manageResponse ( StampaAvvisoPagamentoResponse.builder ()
                .withResult ( Esito.builder ().withCode ( Constants.RETURN_KO )
                    .withDescription ( String.format ( Messages.IMPORTO_SUPERIORE_AL_MAX_CONSENTITO.getMessage (), Constants.MAX_IMPORTO ) ).build () )
                .build (), Constants.RETURN_KO );
		}

		if ( pagamento.getTipoPagamento () == null || pagamento.getTipoPagamento ().getIdTipoPagamento () == null ) {
			logger.error ( "Tipo pagamento non identificato" );
            return manageResponse ( StampaAvvisoPagamentoResponse.builder ()
                .withResult ( Esito.builder ().withCode ( Constants.RETURN_KO ).withDescription ( Messages.TIPO_PAGAMENTO_NON_IDENTIFICATO.getMessage () ).build () )
                .build (), Constants.RETURN_KO );
		}

		EnteCreditoreAvvisoPagamentoDTO enteCreditore = buildEnteCreditoreAvvisoPagamentoDTO ( pagamento.getEnte (),
						pagamento.getTipoPagamento ().getFlagPresenzaBollettinoPostale (), pagamento.getTipoPagamento ().getIdTipoPagamento () );

		DestinatarioAvvisoPagamentoDTO destinatario = buildDestinatarioAvvisoPagamentoDTO ( pagamento.getPagatore () , pagamento.getRiferimenti () );

		InfoPagamentoAvvisoPagamentoDTO infoPagamento
						= buildInfoPagamentoAvvisoPagamentoDTO ( pagamento.getCausale (), pagamento.getDataScadenza (), pagamento.getImporto ().doubleValue (),
						pagamento.getIuv (), enteCreditore.getNumeroCCPostale (), enteCreditore.getAutorizzazione (), getCodiceAvviso ( iuv, pagamento ) );

		EpayTPdfReportDTO epayTPdfReportDTO = avvisoPagamentoReportService.getJasperReport ();

		AvvisoPagamentoPdfGenerator avvisoPagamentoPdfGenerator = new AvvisoPagamentoPdfGenerator ();

		try {
            response = StampaAvvisoPagamentoResponse.builder ()
                .withResult ( Esito.builder ().withCode ( Constants.RETURN_OK ).build () )
                .withPaymentnotice ( avvisoPagamentoPdfGenerator.creaPdf ( enteCreditore, destinatario, infoPagamento, epayTPdfReportDTO ) )
                .build ();
		} catch ( IOException | JRException e ) {
			logger.error ( "Errore nella compilazione del report PDF ", e );
            return manageResponse ( StampaAvvisoPagamentoResponse.builder ()
                .withResult ( Esito.builder ().withCode ( Constants.RETURN_SERVER_PROBLEM )
                    .withDescription ( "Errore nella compilazione del report PDF: " + e.getMessage () ).build () )
                .build (), Constants.RETURN_SERVER_PROBLEM );
		}

		logger.debug ( "[getStampaAvvisoPagamento]:: stop" );
		return manageResponse ( response, response.getResult ().getCode () );
	}
	
	 @Override
     public Response getStampTaxAttachment ( String organization, String paymentType, String iuv ) {
         logger.debug ( "[getStampTaxAttachment]:: start" );
         StampTaxAttachmentChiamanteEsternoInput input= new StampTaxAttachmentChiamanteEsternoInput ();
         input.setCodiceFiscaleEnte ( organization );
         input.setTipoPagamento ( paymentType );
         input.setIuv ( iuv );
         inputHelper ( input );
         StampTaxAttachmentChiamanteEsternoOutput result = service.getStampTaxAttachment ( input );
         logger.debug ( "[getStampTaxAttachment]:: start" );
         return manageResponse ( result, result.getCodiceEsito () );
     }

	private String getCodiceAvviso ( String iuv, Pagamento pagamento ) {
		return pagamento.getAuxDigit () + iuv;
	}

	private EnteCreditoreAvvisoPagamentoDTO buildEnteCreditoreAvvisoPagamentoDTO ( Ente ente, Boolean bollettinoPostale, Long idTipoPagamento ) {
		EnteCreditoreAvvisoPagamentoDTO enteCreditore = new EnteCreditoreAvvisoPagamentoDTO ();
		if ( ente != null ) {
			enteCreditore.setCbill ( ente.getCodiceInterbancario () );
			enteCreditore.setEcLogo ( ente.getLogo () );
			enteCreditore.setEnteCreditore ( ente.getNome () );
			enteCreditore.setCfEnte ( ente.getCodiceFiscale () );
			if ( Boolean.TRUE.equals ( bollettinoPostale ) ) {
				try {
					DatiAvvisoPagamento datiAvvisoPagamento = service.ricercaDatiAvvisoPagamentoByIdTipoPagamento ( idTipoPagamento );
					if ( datiAvvisoPagamento != null ) {
						enteCreditore.setInfoEnte ( createInfoEnte ( datiAvvisoPagamento ) );
						enteCreditore.setAutorizzazione ( datiAvvisoPagamento.getAutorizzazioneDaPosteIt () );
						enteCreditore.setSettoreEnte ( datiAvvisoPagamento.getSettore () );
						enteCreditore.setNumeroCCPostale ( datiAvvisoPagamento.getNumeroCCPostale () );
						enteCreditore.setIntestatarioCCPostale ( datiAvvisoPagamento.getIntestatarioCCPostale () );
					}
				} catch ( IllegalArgumentException | NoDataException e ) {
					logger.error ( "Errore nella ricerca dei dati per l'avviso del pagamento ", e );
				}
			}
		}
		return enteCreditore;
	}
	

	private String createInfoEnte ( DatiAvvisoPagamento infoEnte ) {
		StringBuilder info = new StringBuilder ();
		if ( StringUtils.isNotBlank ( infoEnte.getIndirizzo () ) ) {
			info.append ( infoEnte.getIndirizzo () );
			if ( StringUtils.isNotBlank ( infoEnte.getCap () ) ) {
				info.append ( ", " ).append ( infoEnte.getCap () );
			}
			if ( StringUtils.isNotBlank ( infoEnte.getLocalita () ) ) {
				info.append ( ( " - " ) ).append ( infoEnte.getLocalita () );
				if ( StringUtils.isNotBlank ( infoEnte.getSiglaProvincia () ) ) {
					info.append ( " (" ).append ( infoEnte.getSiglaProvincia () ).append ( ") " );
				}
			}
			if ( StringUtils.isNotBlank ( infoEnte.getEmail () ) ) {
				info.append ( "<br/>" ).append ( infoEnte.getEmail () );
			}
			if ( StringUtils.isNotBlank ( infoEnte.getInfoEnte () ) ) {
				info.append ( "<br/>" ).append ( infoEnte.getInfoEnte () );
			}
		}
		return info.toString ();
	}
	
	


	private DestinatarioAvvisoPagamentoDTO buildDestinatarioAvvisoPagamentoDTO ( Anagrafica anagrafica, List<PagamentoRiferimenti> riferimenti ) {
		if ( anagrafica == null ) {
			return null;
		}
		DestinatarioAvvisoPagamentoDTO destinatario = new DestinatarioAvvisoPagamentoDTO ();
		if ( StringUtils.isNotBlank ( anagrafica.getRagioneSociale () ) ) {
			destinatario.setAnagraficaDestinatario ( "<b>" + anagrafica.getRagioneSociale () + "</b>" );
			destinatario.setDatiDestinatario( anagrafica.getRagioneSociale () );
		} else {
			destinatario.setAnagraficaDestinatario ( "<b>" + anagrafica.getNome () + " " + anagrafica.getCognome () + "</b>" );
			destinatario.setDatiDestinatario( anagrafica.getNome () + " " + anagrafica.getCognome () );
		}
		destinatario.setIdUnivocoDestinatario ( anagrafica.getCodiceFiscale () );
		destinatario.setIndirizzoDestinatario ( createIndirizzoDebitore ( anagrafica ) );
		if( anagrafica.getFlagPersonaFisica() &&
						Constants.ANONIMO.equalsIgnoreCase ( anagrafica.getNome () ) &&
						Constants.ANONIMO.equalsIgnoreCase ( anagrafica.getCognome () ) &&
						Constants.ANONIMO.equalsIgnoreCase ( anagrafica.getCodiceFiscale () ) &&
						( riferimenti != null ) &&
						riferimenti.size () > 0) {
			destinatario.setAnagraficaDestinatario ( StringUtils.EMPTY );
			destinatario.setIndirizzoDestinatario ( StringUtils.EMPTY );
			destinatario.setDatiDestinatario(  StringUtils.EMPTY );
			destinatario.setIdUnivocoDestinatario ( "-" );
			riferimenti.sort ( new Comparator<PagamentoRiferimenti> () {
				@Override
				public int compare ( PagamentoRiferimenti r1, PagamentoRiferimenti r2 ) {
					return r1.getProgressivo ().compareTo ( r2.getProgressivo () );
				}
			} );
			for ( int i = 0 ; i < riferimenti.size () ; i++) {
				String rif = riferimenti.get(i).getNome() + " : " + riferimenti.get(i).getValore();
				if ( i  == 0) {
					destinatario.setAnagraficaDestinatario( rif );
					destinatario.setDatiDestinatario( riferimenti.get(i).getValore() );
				}else {
					if ( i  == 1) {
						destinatario.setDatiDestinatario( destinatario.getDatiDestinatario() + " - " + riferimenti.get(i).getValore() );
					}
					if ( StringUtils.isNotEmpty ( destinatario.getIndirizzoDestinatario () ) ) {
						destinatario.setIndirizzoDestinatario( destinatario.getIndirizzoDestinatario () +  "<br/>" );
					}
					destinatario.setIndirizzoDestinatario( destinatario.getIndirizzoDestinatario() + rif );
				}
			}
		}
		return destinatario;
	}

	private String createIndirizzoDebitore ( Anagrafica soggettoDebitore ) {
		StringBuilder indirizzo = new StringBuilder ();
		if ( StringUtils.isNotBlank ( soggettoDebitore.getIndirizzo () ) ) {
			indirizzo.append ( soggettoDebitore.getIndirizzo () );
			if ( StringUtils.isNotBlank ( soggettoDebitore.getCivico () ) ) {
				indirizzo.append ( ", " ).append ( soggettoDebitore.getCivico () ).append ( "<br/>" );
			} else {
				indirizzo.append ( "<br/>" );
			}
			if ( StringUtils.isNotBlank ( soggettoDebitore.getCap () ) ) {
				indirizzo.append ( soggettoDebitore.getCap () ).append ( ", " );
			}
			if ( StringUtils.isNotBlank ( soggettoDebitore.getLocalita () ) ) {
				indirizzo.append ( soggettoDebitore.getLocalita () );
				if ( StringUtils.isNotBlank ( soggettoDebitore.getProvincia () ) ) {
					indirizzo.append ( " (" ).append ( soggettoDebitore.getProvincia () ).append ( ") " );
				}
			}
			if ( StringUtils.isNotBlank ( soggettoDebitore.getNazione () ) ) {
				indirizzo.append ( soggettoDebitore.getNazione () );
			}
		}
		return indirizzo.toString ();
	}

	private InfoPagamentoAvvisoPagamentoDTO buildInfoPagamentoAvvisoPagamentoDTO ( String causale, Date dataScadenza, Double importo, String iuv,
					String numeroContoCorrentePostale,
					String autorizzazione, String codAvviso ) {
		InfoPagamentoAvvisoPagamentoDTO infoPagamento = new InfoPagamentoAvvisoPagamentoDTO ();

		infoPagamento.setOggettoDelPagamento ( causale );
		infoPagamento.setData ( dataScadenza );
		infoPagamento.setImporto ( importo );
		infoPagamento.setCodiceAvviso ( codAvviso.replaceAll ( "(.{4})", "$1 " ) );

		infoPagamento.setPagamentoReteale ( null );
		infoPagamento.setRate ( Constants.UNA_UNICA_RATA );
		infoPagamento.setAllaRata ( Constants.UNICA_RATA );

		StringBuilder sb = new StringBuilder ();
		infoPagamento.setModalitaPagamentoEnteCreditore ( Constants.DEL_TUO_ENTE_CREDITORE );
		infoPagamento.setUrlPagamentoEnteCreditore ( Constants.URL_ENTE_CREDITORE );
		if ( StringUtils.isNotBlank ( numeroContoCorrentePostale )
						&& StringUtils.isNotBlank ( autorizzazione ) )
			sb.append ( Constants.DI_POSTE_ITALIANE );
		sb.append ( Constants.DELLA_TUA_BANCA );
		infoPagamento.setModalitaPagamento ( sb.toString () );
		infoPagamento.setIuv ( iuv );

		return infoPagamento;
	}

	private Response manageResponse ( Object result, String code ) {
		int status = 500;
		if ( result != null && code.contentEquals ( "000" ) ) {
			status = 200;
		} else {
			if ( code.equals ( "200" ) ) {
				status = 403;
			} else {
				if ( code.equals ( "300" ) ) {
					status = 400;
				}
			}
		}
		return Response.status ( status ).entity ( result ).build ();
	}
	
    

	private void inputHelper ( it.csi.epay.epayservices.model.v1.AccessoChiamanteEsternoSincronoSplitInput input ) {
		helper ( input );
	}

	private void inputHelper ( AccessoChiamanteEsternoSincronoSplitInput input ) {
		helper ( input );
	}
	
	private void inputHelper ( StampTaxAttachmentChiamanteEsternoInput input ) {
        helper ( input );
    }

	private void helper ( Object input ) {
		HttpServletRequest request = SecurityUtils.getCurrentRequest ();
		if ( input instanceof it.csi.epay.epayservices.model.v1.AccessoChiamanteEsternoSincronoSplitInput ) {
			it.csi.epay.epayservices.model.v1.AccessoChiamanteEsternoSincronoSplitInput i =
							(it.csi.epay.epayservices.model.v1.AccessoChiamanteEsternoSincronoSplitInput) input;
			i.setTimestampChiamata ( new Timestamp ( new java.util.Date ().getTime () ) );
			i.setCodiceChiamante ( SecurityUtils.getCurrentClient ().getCodice () );

			if ( request != null ) {
				i.setIpChiamante ( request.getRemoteAddr () );
			} else {
				i.setIpChiamante ( UNKNOWN );
			}
		} else {
			AccessoChiamanteEsternoSincronoSplitInput i = (AccessoChiamanteEsternoSincronoSplitInput) input;
			i.setTimestampChiamata ( new Timestamp ( new java.util.Date ().getTime () ) );
			i.setCodiceChiamante ( SecurityUtils.getCurrentClient ().getCodice () );

			if ( request != null ) {
				i.setIpChiamante ( request.getRemoteAddr () );
			} else {
				i.setIpChiamante ( UNKNOWN );
			}
		}
	}

   
}
