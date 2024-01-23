/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.service.external;

import static it.csi.epay.epayfeapi.dto.PaymentStatus.ALL;
import static it.csi.epay.epayfeapi.dto.PaymentStatus.NOT_PAYED;
import static it.csi.epay.epayfeapi.dto.PaymentStatus.PAYED;
import static it.csi.epay.epayfeapi.util.Constants.CODICE_PAGAMENTO__ANNULLATO_DALL_ENTE;
import static it.csi.epay.epayfeapi.util.Constants.CODICE_PAGAMENTO__NON_PAGATO;
import static it.csi.epay.epayfeapi.util.Constants.CODICE_PAGAMENTO__PAGATO;
import static it.csi.epay.epayfeapi.util.Constants.DESCRIZIONE_PAGAMENTO__ANNULLATO_DALL_ENTE;
import static it.csi.epay.epayfeapi.util.Constants.DESCRIZIONE_PAGAMENTO__NON_PAGATO;
import static it.csi.epay.epayfeapi.util.Constants.DESCRIZIONE_PAGAMENTO__PAGATO;
import static it.csi.epay.epayfeapi.util.Constants.END;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_ENTE_NOT_FOUND;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_PAYMENT_TYPE_NOT_FOUND;
import static it.csi.epay.epayfeapi.util.Constants.REGEX_IUV_CF;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_FIND_DEBT_POSITIONS__CITIZEN_FISCAL_CODE;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_FIND_DEBT_POSITIONS__CODICE_AVVISO;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_FIND_DEBT_POSITIONS__CODICE_STATO_PAGAMENTO;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_FIND_DEBT_POSITIONS__CODICE_VERSAMENTO;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_FIND_DEBT_POSITIONS__COMPONENTI_PAGAMENTO;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_FIND_DEBT_POSITIONS__DATA_ESITO_PAGAMENTO;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_FIND_DEBT_POSITIONS__DATA_ORA_OPERAZIONE;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_FIND_DEBT_POSITIONS__DATA_SCADENZA;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_FIND_DEBT_POSITIONS__DESCRIZIONE_PAGAMENTO;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_FIND_DEBT_POSITIONS__DESCRIZIONE_PORTALE;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_FIND_DEBT_POSITIONS__DESCRIZIONE_STATO_PAGAMENTO;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_FIND_DEBT_POSITIONS__IDENTIFICATIVO_UNIVOCO_RISCOSSIONE;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_FIND_DEBT_POSITIONS__IMPORTO;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_FIND_DEBT_POSITIONS__INFO_AGGIUNTIVE;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_FIND_DEBT_POSITIONS__IUV;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_FIND_DEBT_POSITIONS__NUMERO_TRANSAZIONE;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_FIND_DEBT_POSITIONS__ORGANIZATION_FISCAL_CODE;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_FIND_DEBT_POSITIONS__PRESENZA_QUIETANZA;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_FIND_DEBT_POSITIONS__PRESTATORE_SERVIZI_PAGAMENTO;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_FIND_DEBT_POSITIONS__STATUS;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_LIST_DEBT_POSITION;
import static it.csi.epay.epayfeapi.util.FieldsUtil.validateCommonFields;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateBusinessErrorResponse;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateForbiddenResponse;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateNotFoundErrorResponse;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateUnauthorizedResponse;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateValidationErrorResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.openapitools.model.ComponenteImporto;
import org.openapitools.model.DebtPosition;
import org.openapitools.model.DebtPositionDetail;
import org.openapitools.model.Error;

import io.quarkus.logging.Log;
import it.csi.epay.epayfeapi.dto.ConfigurazioneServiceElementsDTO;
import it.csi.epay.epayfeapi.dto.PagedListResultDTO;
import it.csi.epay.epayfeapi.dto.PaymentStatus;
import it.csi.epay.epayfeapi.entity.EpayDChiamanteEsterno;
import it.csi.epay.epayfeapi.entity.EpayTEnti;
import it.csi.epay.epayfeapi.entity.EpayTEsitiRicevuti;
import it.csi.epay.epayfeapi.entity.EpayTPagamento;
import it.csi.epay.epayfeapi.entity.EpayTPagamentoComponenti;
import it.csi.epay.epayfeapi.entity.EpayTRegistroVersamenti;
import it.csi.epay.epayfeapi.entity.EpayTRt;
import it.csi.epay.epayfeapi.entity.EpayTTipoPagamento;
import it.csi.epay.epayfeapi.model.filtrable.XDebtPositionDetail;
import it.csi.epay.epayfeapi.security.AuthenticationContext;
import it.csi.epay.epayfeapi.security.Scopes;
import it.csi.epay.epayfeapi.security.User;
import it.csi.epay.epayfeapi.service.ChiamanteAutorizzazioneChiamanteService;
import it.csi.epay.epayfeapi.service.ChiamanteEsternoService;
import it.csi.epay.epayfeapi.service.ChiamataEsternaNonValidaService;
import it.csi.epay.epayfeapi.service.ConfigurazioneService;
import it.csi.epay.epayfeapi.service.EnteService;
import it.csi.epay.epayfeapi.service.EsitiRicevutiService;
import it.csi.epay.epayfeapi.service.PagamentoService;
import it.csi.epay.epayfeapi.service.RegistroVersamentiService;
import it.csi.epay.epayfeapi.service.RtService;
import it.csi.epay.epayfeapi.service.TipoPagamentoService;
import it.csi.epay.epayfeapi.service.TracciabilitaChiamanteEsternoService;
import it.csi.epay.epayfeapi.util.FieldsUtil;


/*
 * servizio 1 di ricerca posizione debitorie
 */
@ApplicationScoped
@Transactional
public class DebtPositionService {

	private static final String [] SORTABLE_FIELDS = new String [] {
		SERVICE_FIELDS_FIND_DEBT_POSITIONS__IUV,
		SERVICE_FIELDS_FIND_DEBT_POSITIONS__CODICE_VERSAMENTO,
		SERVICE_FIELDS_FIND_DEBT_POSITIONS__DATA_SCADENZA,
		SERVICE_FIELDS_FIND_DEBT_POSITIONS__IMPORTO
	};

	private static final String [] VALID_FIELDS = new String [] {
		SERVICE_FIELDS_FIND_DEBT_POSITIONS__DESCRIZIONE_PAGAMENTO,
		SERVICE_FIELDS_FIND_DEBT_POSITIONS__CODICE_AVVISO,
		SERVICE_FIELDS_FIND_DEBT_POSITIONS__IUV,
		SERVICE_FIELDS_FIND_DEBT_POSITIONS__CODICE_VERSAMENTO,
		SERVICE_FIELDS_FIND_DEBT_POSITIONS__DESCRIZIONE_PORTALE,
		SERVICE_FIELDS_FIND_DEBT_POSITIONS__IMPORTO,
		SERVICE_FIELDS_FIND_DEBT_POSITIONS__COMPONENTI_PAGAMENTO,
		SERVICE_FIELDS_FIND_DEBT_POSITIONS__DATA_SCADENZA,
		SERVICE_FIELDS_FIND_DEBT_POSITIONS__DATA_ORA_OPERAZIONE,
		SERVICE_FIELDS_FIND_DEBT_POSITIONS__DATA_ESITO_PAGAMENTO,
		SERVICE_FIELDS_FIND_DEBT_POSITIONS__IDENTIFICATIVO_UNIVOCO_RISCOSSIONE,
		SERVICE_FIELDS_FIND_DEBT_POSITIONS__NUMERO_TRANSAZIONE,
		SERVICE_FIELDS_FIND_DEBT_POSITIONS__PRESTATORE_SERVIZI_PAGAMENTO,
		SERVICE_FIELDS_FIND_DEBT_POSITIONS__INFO_AGGIUNTIVE,
		SERVICE_FIELDS_FIND_DEBT_POSITIONS__PRESENZA_QUIETANZA,
		SERVICE_FIELDS_FIND_DEBT_POSITIONS__CODICE_STATO_PAGAMENTO,
		SERVICE_FIELDS_FIND_DEBT_POSITIONS__DESCRIZIONE_STATO_PAGAMENTO
	};

	@Inject
	AuthenticationContext authenticationContext;

	@Inject
	ChiamanteEsternoService chiamanteEsternoService;

	@Inject
	ChiamataEsternaNonValidaService chiamataEsternaNonValidaService;

	@Inject
	TracciabilitaChiamanteEsternoService tracciabilitaChiamanteEsternoService;

	@Inject
	ChiamanteAutorizzazioneChiamanteService chiamanteAutorizzazioneChiamanteService;

	@Inject
	EnteService enteService;

	@Inject
	PagamentoService pagamentoService;

	@Inject
	TipoPagamentoService tipoPagamentoService;

	@Inject
	EsitiRicevutiService esitiRicevutiService;

	@Inject
	RegistroVersamentiService registroVersamentiService;

	@Inject
	ConfigurazioneService configurazioneService;

	@Inject
	RtService rtService;

	public Response findDebtPositions ( String organizationFiscalCode, String citizenFiscalCode, String status, String iuv, Integer currentPage,
		Integer elements, String sort, String fields ) {
		String methodName = "[findDebtPositions] ";
		Log.info ( methodName + "BEGIN" );
		Log.info ( methodName + "param organizationFiscalCode:" + organizationFiscalCode );
		Log.info ( methodName + "param citizenFiscalCode:" + citizenFiscalCode );
		Log.info ( methodName + "param status:" + status );
		Log.info ( methodName + "param iuv:" + iuv );
		Log.info ( methodName + "param currentPage:" + currentPage );
		Log.info ( methodName + "param elements:" + elements );
		Log.info ( methodName + "param sort:" + sort );
		Log.info ( methodName + "param fields:" + fields );

		User user = authenticationContext.getCurrentUser ();
		Log.info ( methodName + "user:" + user );

		/* --- validazione --- */
		// 1. ottiene il chiamante esterno per il tracciamento della chiamata e la validazione dell'autorizzazione
		EpayDChiamanteEsterno chiamanteEsternoEntity = chiamanteEsternoService.findByCodiceChiamante ( user.getName () );
		if ( chiamanteEsternoEntity == null ) {
			Response response = generateForbiddenResponse ( SERVICE_LIST_DEBT_POSITION, user.getName () );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		Log.info ( methodName + "chiamanteEsternoEntity:" + chiamanteEsternoEntity );
		//
		// 2. tracciamento della chiamata
		tracciabilitaChiamanteEsternoService.trackExternalCall ( null, organizationFiscalCode, chiamanteEsternoEntity, null, user, null );
		Log.info ( methodName + "chiamanteEsterno tracciato" );
		//
		// 3. validazione autorizzazione
		if ( chiamanteAutorizzazioneChiamanteService.countByCodiceChiamanteAndCodiceAutorizzazioneChiamante ( user.getName (),
			Scopes.DEBT_POSITION.name () ) < 1 ) {
			Response response = generateUnauthorizedResponse ( SERVICE_LIST_DEBT_POSITION );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		Log.info ( methodName + "authorization OK" );

		// validazione input
		List<String> notValids = getNotValidInputs ( organizationFiscalCode, citizenFiscalCode, status, iuv, currentPage, elements, sort, fields );
		if ( !notValids.isEmpty () ) {
			Response response = generateValidationErrorResponse ( SERVICE_LIST_DEBT_POSITION, notValids );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		Log.info ( methodName + "validation OK" );

		/* --- logica di business --- */

		// verifica e recupero ente
		PaymentStatus paymentStatus = status == null ? ALL : status.equalsIgnoreCase ( PAYED.name () ) ? PAYED : NOT_PAYED;

		EpayTEnti enteEntity = enteService.findByCodiceFiscale ( organizationFiscalCode );
		if ( null == enteEntity ) {
			Response response = generateNotFoundErrorResponse ( SERVICE_LIST_DEBT_POSITION, ERROR_ENTE_NOT_FOUND, organizationFiscalCode );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		Log.info ( methodName + "ente:" + enteEntity );

		// recupera lista id_tipo_pagamento corretta derivati dall'ente
		List<EpayTTipoPagamento> epayTTipoPagamentoList = tipoPagamentoService.findByEnte ( enteEntity );
		if ( null == epayTTipoPagamentoList || epayTTipoPagamentoList.isEmpty () ) {
			Log.error ( "Lista tipi pagamento non trovata!" );
			Response response = generateBusinessErrorResponse ( SERVICE_LIST_DEBT_POSITION, ERROR_PAYMENT_TYPE_NOT_FOUND, organizationFiscalCode );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail () );
			return response;
		} else {
			Log.info ( methodName + "trovati " + epayTTipoPagamentoList.size () + " tipi pagamento" );
		}

		// ottiene il numero massimo di elementi per pagina di default se non specificato il parametro di input elements
		if ( elements == null ) {
			ConfigurazioneServiceElementsDTO confServiceResponse
				= configurazioneService.getMaxElementPerPage ( SERVICE_LIST_DEBT_POSITION, user, organizationFiscalCode );
			if ( confServiceResponse.isOk () ) {
				elements = confServiceResponse.getElements ();
			} else {
				Response response = confServiceResponse.getResponse ();
				chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail () );
				return response;
			}
		}

		// ricerca vera e propria
		PagedListResultDTO<EpayTPagamento> pagedListResult = pagamentoService.search (
			enteEntity,
			citizenFiscalCode,
			paymentStatus,
			iuv,
			SORTABLE_FIELDS,
			sort,
			currentPage != null ? currentPage - 1 : 0, // N.B zero-based
			elements );
		Log.info ( methodName + "ricerca eseguita con successo" );

		// costruisco la response
		DebtPosition debtPosition = new DebtPosition ();
		debtPosition.setDebtPositions ( buildDebtPositionList ( pagedListResult.getList (), fields ) );
		debtPosition.setCurrentPage ( pagedListResult.getCurrentPage () );
		debtPosition.setPageSize ( pagedListResult.getPageSize () );
		debtPosition.setTotalElements ( (int) pagedListResult.getTotalElements () );
		debtPosition.setTotPages ( pagedListResult.getTotalPages () );

		Log.info ( methodName + END );
		return Response.status ( Response.Status.OK )
			.entity ( debtPosition )
			.build ();
	}

	private List<String> getNotValidInputs ( String organizationFiscalCode, String citizenFiscalCode, String status, String iuv, Integer currentPage,
		Integer elements, String sort, String fields ) {
		List<String> notValids = new LinkedList<> ();

		if ( StringUtils.isBlank ( organizationFiscalCode ) ) {
			notValids.add ( SERVICE_FIELDS_FIND_DEBT_POSITIONS__ORGANIZATION_FISCAL_CODE );
		} else if ( !organizationFiscalCode.matches ( REGEX_IUV_CF ) ) {
			notValids.add ( SERVICE_FIELDS_FIND_DEBT_POSITIONS__ORGANIZATION_FISCAL_CODE );
		}

		if ( StringUtils.isBlank ( citizenFiscalCode ) ) {
			notValids.add ( SERVICE_FIELDS_FIND_DEBT_POSITIONS__CITIZEN_FISCAL_CODE );
		} else if ( !citizenFiscalCode.matches ( REGEX_IUV_CF ) ) {
			notValids.add ( SERVICE_FIELDS_FIND_DEBT_POSITIONS__CITIZEN_FISCAL_CODE );
		}

		if ( status != null && ( !status.equalsIgnoreCase ( PAYED.name () ) && !status.equalsIgnoreCase ( NOT_PAYED.name () ) ) ) {
			notValids.add ( SERVICE_FIELDS_FIND_DEBT_POSITIONS__STATUS );
		}

		if ( iuv != null && ( !iuv.matches ( REGEX_IUV_CF ) ) ) {
			notValids.add ( SERVICE_FIELDS_FIND_DEBT_POSITIONS__IUV );
		}

		return validateCommonFields ( notValids, currentPage, elements, sort, fields, SORTABLE_FIELDS, VALID_FIELDS );
	}

	private List<DebtPositionDetail> buildDebtPositionList ( List<EpayTPagamento> entityList, String fields ) {
		String methodName = "[buildDebtPositionList] ";
		Log.info ( methodName + "BEGIN" );
		Log.info ( methodName + "fields:" + fields );

		Set<String> fieldSet = FieldsUtil.getInputFieldSet ( VALID_FIELDS, fields );
		Log.info ( methodName + "fieldSet:" + fieldSet );

		List<DebtPositionDetail> debtPositionList = new ArrayList<> ();
		for ( EpayTPagamento entity: entityList ) {
			XDebtPositionDetail xDebtPositionDetail = new XDebtPositionDetail ();

			// campi obbligatori
			xDebtPositionDetail.setCodiceAvviso ( StringUtils.trimToEmpty ( entity.getAuxDigit () ) + entity.getIuvNumeroAvviso () );
			xDebtPositionDetail.setCodiceVersamento ( entity.getEpayTTipoPagamento ().getCodiceVersamento () );
			xDebtPositionDetail.setImporto ( entity.getImporto () );

			// campi opzionali
			if ( !entity.getEpayTRegistroVersamentis ().isEmpty () ) {
				EpayTRegistroVersamenti rv = entity.getEpayTRegistroVersamentis ().get ( 0 );
				if ( fieldSet.contains ( SERVICE_FIELDS_FIND_DEBT_POSITIONS__DATA_ORA_OPERAZIONE ) ) {
					xDebtPositionDetail.setDataOraOperazione ( rv.getDataOperazione () );
				}
				if ( fieldSet.contains ( SERVICE_FIELDS_FIND_DEBT_POSITIONS__DATA_ESITO_PAGAMENTO ) ) {
					if ( idStatoIs4or11 ( entity ) ) { // solo per i pagati
						if ( !rv.getEpayTEsitiRicevutis ().isEmpty () ) {
							xDebtPositionDetail.setDataEsitoPagamento ( rv.getEpayTEsitiRicevutis ().get ( 0 ).getDataPagamento () );
						}
					}
				}
				if ( fieldSet.contains ( SERVICE_FIELDS_FIND_DEBT_POSITIONS__IDENTIFICATIVO_UNIVOCO_RISCOSSIONE ) ) {
					if ( idStatoIs4or11 ( entity ) ) { // solo per i pagati
						if ( !rv.getEpayTEsitiRicevutis ().isEmpty () ) {
							xDebtPositionDetail.identificativoUnicoRiscossione ( rv.getEpayTEsitiRicevutis ().get ( 0 ).getIur () );
						}
					}
				}
				if ( fieldSet.contains ( SERVICE_FIELDS_FIND_DEBT_POSITIONS__NUMERO_TRANSAZIONE ) ) {
					if ( idStatoIs4or11 ( entity ) ) { // solo per i pagati
						for ( EpayTEsitiRicevuti esitoRicevuto: rv.getEpayTEsitiRicevutis () ) {
							if ( Objects.equals ( rv.getIdRegistro (), esitoRicevuto.getEpayTRegistroVersamenti ().getIdRegistro () )
								&& esitoRicevuto.getIdModalitaRicezione () == 5 ) {
								xDebtPositionDetail.setNumeroTransazione ( esitoRicevuto.getIdTransazione () );
								break;
							}
						}
					}
				}
				if ( fieldSet.contains ( SERVICE_FIELDS_FIND_DEBT_POSITIONS__PRESTATORE_SERVIZI_PAGAMENTO ) ) {
					if ( idStatoIs4or11 ( entity ) ) { // solo per i pagati
						if ( !rv.getEpayTEsitiRicevutis ().isEmpty () ) {
							xDebtPositionDetail.setPrestatoreServiziPagamento ( rv.getEpayTEsitiRicevutis ().get ( 0 ).getRagioneSocialePsp () );
						}
					}
				}
				if ( fieldSet.contains ( SERVICE_FIELDS_FIND_DEBT_POSITIONS__PRESENZA_QUIETANZA ) ) {
					Long maxIdRegistro = registroVersamentiService.findMaxIdRegistro ( entity.getIdPagamento () );
					EpayTRt epayTRt = rtService.findByIdRegistroAndCodEsitoPagamento ( maxIdRegistro, 0 );
					if ( null != epayTRt ) {
						xDebtPositionDetail.setPresenzaQuietanza ( true );
					} else {
						EpayTEsitiRicevuti epayTEsitiRicevuti = esitiRicevutiService.findByIdRegistro ( rv.getIdRegistro () );
						xDebtPositionDetail.setPresenzaQuietanza ( null != epayTEsitiRicevuti );
					}
				}
			}
			if ( fieldSet.contains ( SERVICE_FIELDS_FIND_DEBT_POSITIONS__DESCRIZIONE_PAGAMENTO ) ) {
				xDebtPositionDetail.setDescrizionePagamento ( entity.getCausale () );
			}
			if ( fieldSet.contains ( SERVICE_FIELDS_FIND_DEBT_POSITIONS__IUV ) ) {
				xDebtPositionDetail.setIuv ( entity.getIuvNumeroAvviso () );
			}
			if ( fieldSet.contains ( SERVICE_FIELDS_FIND_DEBT_POSITIONS__DESCRIZIONE_PORTALE ) ) {
				xDebtPositionDetail.setDescrizionePortale ( entity.getEpayTTipoPagamento ().getDescrizionePortale () );
			}
			if ( fieldSet.contains ( SERVICE_FIELDS_FIND_DEBT_POSITIONS__COMPONENTI_PAGAMENTO ) ) {
				if ( entity.getEpayTPagamentoComponentis () != null && !entity.getEpayTPagamentoComponentis ().isEmpty () ) {
					List<ComponenteImporto> componenteImportos = new ArrayList<> ();
					for ( EpayTPagamentoComponenti c: entity.getEpayTPagamentoComponentis () ) {
						ComponenteImporto componenteImporto = new ComponenteImporto ();
						componenteImporto.setImporto ( c.getImporto () );
						componenteImporto.setCausale ( c.getCausale () );
						componenteImportos.add ( componenteImporto );
					}
					xDebtPositionDetail.setComponentiPagamento ( componenteImportos );
				}
			}
			if ( fieldSet.contains ( SERVICE_FIELDS_FIND_DEBT_POSITIONS__DATA_SCADENZA ) ) {
				xDebtPositionDetail.setDataScadenza ( entity.getDataScadenza () );
			}
			if ( fieldSet.contains ( SERVICE_FIELDS_FIND_DEBT_POSITIONS__INFO_AGGIUNTIVE ) ) {
				xDebtPositionDetail.setInfoAggiuntive ( entity.getCausale () );
			}
			if ( fieldSet.contains ( SERVICE_FIELDS_FIND_DEBT_POSITIONS__PRESENZA_QUIETANZA ) ) {
				Long maxIdRegistro = registroVersamentiService.findMaxIdRegistro ( entity.getIdPagamento () );
				EpayTRt epayTRt = rtService.findByIdRegistroAndCodEsitoPagamento ( maxIdRegistro, 0 );
				if ( null != epayTRt ) {
					xDebtPositionDetail.setPresenzaQuietanza ( true );
				}
			}
			if ( fieldSet.contains ( SERVICE_FIELDS_FIND_DEBT_POSITIONS__CODICE_STATO_PAGAMENTO ) ) {
				switch ( entity.getEpayDStatoPagamento ().getIdStato () ) {
				case 4 :
				case 6 :
				case 7 :
				case 11 :
					xDebtPositionDetail.setCodiceStatoPagamento ( CODICE_PAGAMENTO__PAGATO ); // 0
					break;
				case 0 :
				case 3 :
				case 5 :
					xDebtPositionDetail.setCodiceStatoPagamento ( CODICE_PAGAMENTO__NON_PAGATO ); // 1
					break;
				case 9 :
					xDebtPositionDetail.setCodiceStatoPagamento ( CODICE_PAGAMENTO__ANNULLATO_DALL_ENTE ); // 2
					break;
				default :
					// se esce uno stato diverso da quelli precedenti significa che si tratta di un errore nella query in quanto il filtro non lo deve permettere
				}
			}
			if ( fieldSet.contains ( SERVICE_FIELDS_FIND_DEBT_POSITIONS__DESCRIZIONE_STATO_PAGAMENTO ) ) {
				switch ( entity.getEpayDStatoPagamento ().getIdStato () ) {
				case 4 :
				case 6 :
				case 7 :
				case 11 :
					xDebtPositionDetail.setDescrizioneStatoPagamento ( DESCRIZIONE_PAGAMENTO__PAGATO ); // 0
					break;
				case 0 :
				case 3 :
				case 5 :
					xDebtPositionDetail.setDescrizioneStatoPagamento ( DESCRIZIONE_PAGAMENTO__NON_PAGATO ); // 1
					break;
				case 9 :
					xDebtPositionDetail.setDescrizioneStatoPagamento ( DESCRIZIONE_PAGAMENTO__ANNULLATO_DALL_ENTE ); // 2
					break;
				default :
					// se esce uno stato diverso da quelli precedenti significa che si tratta di un errore nella query in quanto il filtro non lo deve permettere
				}
			}

			debtPositionList.add ( xDebtPositionDetail );
		}
		Log.info ( methodName + "END" );
		return debtPositionList;
	}

	private boolean idStatoIs4or11 ( EpayTPagamento entity ) {
		return entity.getEpayDStatoPagamento ().getIdStato () == 4 || entity.getEpayDStatoPagamento ().getIdStato () == 11;
	}
}
