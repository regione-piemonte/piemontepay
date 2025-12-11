/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.service;

import io.quarkus.logging.Log;
import it.csi.epay.epayfeapi.dto.AnagraficaDTO;
import it.csi.epay.epayfeapi.dto.CodiceAvvisoDTO;
import it.csi.epay.epayfeapi.dto.PagamentoComponentiDTO;
import it.csi.epay.epayfeapi.dto.PagamentoDTO;
import it.csi.epay.epayfeapi.dto.PagamentoRiferimentiDTO;
import it.csi.epay.epayfeapi.dto.PagamentoSecondarioComponentiDTO;
import it.csi.epay.epayfeapi.dto.PagedListResultDTO;
import it.csi.epay.epayfeapi.enumeration.PaymentStatus;
import it.csi.epay.epayfeapi.enumeration.StatoPagamento;
import it.csi.epay.epayfeapi.entity.EpayDStatoPagamento;
import it.csi.epay.epayfeapi.entity.EpayTEnti;
import it.csi.epay.epayfeapi.entity.EpayTPagamento;
import it.csi.epay.epayfeapi.entity.EpayTPagamentoSecondario;
import it.csi.epay.epayfeapi.entity.EpayTRegistroVersamenti;
import it.csi.epay.epayfeapi.entity.EpayTTipoPagamento;
import it.csi.epay.epayfeapi.exception.TassonomiaServiceException;
import it.csi.epay.epayfeapi.mapper.AnagraficaMapper;
import it.csi.epay.epayfeapi.mapper.EnteMapper;
import it.csi.epay.epayfeapi.mapper.PagamentoComponentiMapper;
import it.csi.epay.epayfeapi.mapper.PagamentoMapper;
import it.csi.epay.epayfeapi.mapper.PagamentoRiferimentiMapper;
import it.csi.epay.epayfeapi.mapper.PagamentoSecondarioComponentiMapper;
import it.csi.epay.epayfeapi.mapper.TipoPagamentoMapper;
import it.csi.epay.epayfeapi.model.epaypacatalogsrv.DatiSpecificiRiscossioneOutput;
import it.csi.epay.epayfeapi.model.mdppagopacheckout.Cart;
import it.csi.epay.epayfeapi.model.mdppagopacheckout.PaymentNotice;
import it.csi.epay.epayfeapi.repository.PagamentoRepository;
import it.csi.epay.epayfeapi.rest.client.TassonomiaAdapterClient;
import it.csi.epay.epayfeapi.soap.client.mdpmultiiuv.IuvComplex;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static it.csi.epay.epayfeapi.service.external.PaymentService.getDatiSpecificiRiscossioneOutput;
import static it.csi.epay.epayfeapi.service.external.PaymentService.getPagamentoComponentiDTO;
import static it.csi.epay.epayfeapi.util.Constants.MAX_ERROR_MESSAGE_WIDTH;
import static it.csi.epay.epayfeapi.util.Constants.SYSTEM_USER_DEFAULT;
import static it.csi.epay.epayfeapi.util.DateUtil.currentTimestamp;


@ApplicationScoped
@Transactional
public class PagamentoService {

	@Inject
	TipoPagamentoService tipoPagamentoService;

	@Inject
	StatoPagamentoService statoPagamentoService;

	@Inject
	AnagraficaService anagraficaService;

	@Inject
	RegistroVersamentiService registroVersamentiService;

	@Inject
	ConfigurazioneService configurazioneService;

	@Inject
	PagamentoRepository pagamentoRepository;

	@Inject
	PagamentoMapper pagamentoMapper;

	@Inject
	AnagraficaMapper anagraficaMapper;

	@Inject
	PagamentoComponentiMapper pagamentoComponentiMapper;

	@Inject
	PagamentoRiferimentiMapper pagamentoRiferimentiMapper;

	@Inject
	TipoPagamentoMapper tipoPagamentoMapper;

	@Inject
	EnteMapper enteMapper;

	@Inject
	PagamentoSecondarioComponentiMapper pagamentoSecondarioComponentiMapper;

	@Inject
	TassonomiaAdapterClient tassonomiaAdapterClient;

	public EpayTPagamento getPagamentoByiuvNumeroAvviso ( String iuv ) {
		return pagamentoRepository.findByiuvNumeroAvviso ( iuv );
	}

	@Transactional
	private EpayTPagamento insertPagamento ( PagamentoDTO pagamento ) {
		var tPagamento = pagamentoMapper.toEntity ( pagamento );
		tPagamento.setIuvNumeroAvviso ( pagamento.getIuv () );
		tPagamento.setFlagInviato ( false );

		Log.info ( "START insertPagamento" );
		Log.info ( pagamento );
		Log.info ( ( null != pagamento.getTipoPagamento () ) ? pagamento.getTipoPagamento ().toString () : "null" );

		if ( null == tPagamento.getEpayTTipoPagamento () ) {
			tPagamento.setEpayTTipoPagamento ( tipoPagamentoMapper.toEntity ( pagamento.getTipoPagamento () ) );
		}

		var tipoPagamento = tipoPagamentoService.findByTipoPagamento ( tPagamento.getEpayTTipoPagamento () );
		tPagamento.setEpayTTipoPagamento ( tipoPagamento );
		tPagamento.setDataInserimento ( currentTimestamp () );
		tPagamento.setUtenteUltimaModifica ( setUtenteNotEmpty ( pagamento.getUtenteUltimaModifica () ) );

		if ( pagamento.getPagatore () != null ) {
			tPagamento.setEpayTAnagrafica ( anagraficaService.getByAnagrafica ( anagraficaMapper.toEntity ( pagamento.getPagatore () ) ) );
		}
		var epayDStatoPagamento = statoPagamentoService.findById ( pagamento.getIdStatoCorrente () );
		tPagamento.setEpayDStatoPagamento ( epayDStatoPagamento );
		tPagamento.setCausale ( StringUtils.substring ( tPagamento.getCausale (), 0, 140 ) );

		if ( pagamento.getComponenti () != null && !pagamento.getComponenti ().isEmpty () ) {
			if ( tPagamento.getEpayTPagamentoComponentis () == null ) {
				tPagamento.setEpayTPagamentoComponentis ( new ArrayList<> () );
			}
			for ( PagamentoComponentiDTO componenti : pagamento.getComponenti () ) {
				var pagamentoComponenti = pagamentoComponentiMapper.toEntity ( componenti );
				pagamentoComponenti.setCausale ( StringUtils.substring ( pagamentoComponenti.getCausale (), 0, 140 ) );
				pagamentoComponenti.setUtenteUltimaModifica ( setUtenteNotEmpty ( componenti.getUtenteUltimaModifica () ) );
				tPagamento.addEpayTPagamentoComponenti ( pagamentoComponenti );
			}
		}

		if ( pagamento.getRiferimenti () != null && !pagamento.getRiferimenti ().isEmpty () ) {
			if ( tPagamento.getEpayTPagamentoRiferimentis () == null ) {
				tPagamento.setEpayTPagamentoRiferimentis ( new ArrayList<> () );
			}
			for ( PagamentoRiferimentiDTO riferimenti : pagamento.getRiferimenti () ) {
				var pagamentoRiferimenti = pagamentoRiferimentiMapper.toEntity ( riferimenti );
				pagamentoRiferimenti.setUtenteUltimaModifica ( setUtenteNotEmpty ( riferimenti.getUtenteUltimaModifica () ) );
				tPagamento.addEpayTPagamentoRiferimenti ( pagamentoRiferimenti );
			}
		}

		pagamentoRepository.persist ( tPagamento );
		return tPagamento;
	}

	private String setUtenteNotEmpty ( final String utente ) {
		if ( StringUtils.isBlank ( utente ) ) {
			return SYSTEM_USER_DEFAULT;
		} else {
			return utente;
		}
	}

	public PagamentoDTO mapPagamentoEsteso ( EpayTPagamento tPagamento ) {
		var pagamento = mapPagamentoBase ( tPagamento );

		if ( tPagamento.getEpayTPagamentoComponentis () != null && !tPagamento.getEpayTPagamentoComponentis ().isEmpty () ) {
			pagamento.setComponenti ( pagamentoComponentiMapper.toDtoList ( tPagamento.getEpayTPagamentoComponentis () ) );
		}

		// gestione importi secondari e componenti secondari
		List<PagamentoSecondarioComponentiDTO> componentiSecondariFinale = new ArrayList<> ();
		if ( tPagamento.getEpayTPagamentoSecondarios () != null && !tPagamento.getEpayTPagamentoSecondarios ().isEmpty () ) {
			// per ogni pagamento secondario devo mappare i componenti secondari
			for ( EpayTPagamentoSecondario pagamentoSecondario : tPagamento.getEpayTPagamentoSecondarios () ) {
				var list = pagamentoSecondarioComponentiMapper.toDtoList ( pagamentoSecondario.getEpayTPagamentoSecondarioComponentis () );
				for ( PagamentoSecondarioComponentiDTO componenteSecondario : list ) {
					componenteSecondario.setApplicationId (
									null != pagamentoSecondario.getEpayTTipoPagamento () ? pagamentoSecondario.getEpayTTipoPagamento ().getIdApplicazione () :
													"" );
					componentiSecondariFinale.add ( componenteSecondario );
				}
			}
		}
		pagamento.setComponentiSecondari ( componentiSecondariFinale );

		if ( tPagamento.getEpayTPagamentoRiferimentis () != null && !tPagamento.getEpayTPagamentoRiferimentis ().isEmpty () ) {
			pagamento.setRiferimenti ( pagamentoRiferimentiMapper.toDtoList ( tPagamento.getEpayTPagamentoRiferimentis () ) );
		}

		return pagamento;
	}

	private PagamentoDTO mapPagamentoBase ( EpayTPagamento tPagamento ) {
		var pagamento = pagamentoMapper.toDto ( tPagamento );

		pagamento.setAuxDigit ( tPagamento.getAuxDigit () );
		pagamento.setIuv ( tPagamento.getIuvNumeroAvviso () );
		pagamento.setApplicationCode ( tPagamento.getApplicationCode () );

		Log.infof ( "AUX DIGIT IS %s", pagamento.getAuxDigit () );
		Log.infof ( "IUV IS %s", pagamento.getIuv () );
		Log.infof ( "APP CODE IS %s", pagamento.getApplicationCode () );

		pagamento.setPagatore ( anagraficaMapper.toDto ( tPagamento.getEpayTAnagrafica () ) );
		pagamento.setTipoPagamento ( tipoPagamentoMapper.toDto ( tPagamento.getEpayTTipoPagamento () ) );
		pagamento.setEnte ( enteMapper.toDto ( tPagamento.getEpayTTipoPagamento ().getEpayTEnti () ) );
		pagamento.setIdStatoCorrente ( tPagamento.getEpayDStatoPagamento ().getIdStato () );

		if ( tPagamento.getEpayTRegistroVersamentis () == null || tPagamento.getEpayTRegistroVersamentis ().isEmpty () ) {
			pagamento.setIuvRegistroVersamenti ( pagamento.getIuv () );
			pagamento.setDataStatoCorrente ( pagamento.getDataInserimento () );
		} else {
			pagamento.setIuvRegistroVersamenti ( getMostRecentFromRegistry ( tPagamento.getEpayTRegistroVersamentis () ) );
			for ( EpayTRegistroVersamenti rv : tPagamento.getEpayTRegistroVersamentis () ) {
				if ( rv.getEpayDStatoPagamento ().getIdStato ().equals ( pagamento.getIdStatoCorrente () ) &&
								( pagamento.getDataStatoCorrente () == null ||
												pagamento.getDataStatoCorrente ().before ( rv.getDataOperazione () ) ) ) {
					pagamento.setDataStatoCorrente ( rv.getDataOperazione () );
				}
			}
		}

		return pagamento;
	}

	private String getMostRecentFromRegistry ( List<EpayTRegistroVersamenti> epayTRegistroVersamentis ) {
		if ( epayTRegistroVersamentis == null || epayTRegistroVersamentis.isEmpty () ) {
			return null;
		}
		var recente = new EpayTRegistroVersamenti ();
		recente.setIdRegistro ( -1L );
		for ( EpayTRegistroVersamenti epayTRegistroVersamenti : epayTRegistroVersamentis ) {
			Log.infof ( "mappaPagamento.recente  IuvRegistroVersamenti:%s", epayTRegistroVersamenti.getIuv () );
			if ( epayTRegistroVersamenti.getIdRegistro () > recente.getIdRegistro () ) {
				recente = epayTRegistroVersamenti;
			}
		}
		Log.infof ( "mappaPagamento.recente SELEZIONATO IuvRegistroVersamenti:%s", recente.getIuv () );
		return recente.getIuv ();
	}

	public void updateCodiceAvvisoAndStatoCorrente ( Long idPagamento, CodiceAvvisoDTO codiceAvviso, Integer idStatoCorrente ) {
		var tPagamento = pagamentoRepository.findById ( idPagamento );
		tPagamento.setAuxDigit ( codiceAvviso.getAuxDigit () );
		tPagamento.setApplicationCode ( codiceAvviso.getApplicationCode () );
		tPagamento.setIuvNumeroAvviso ( codiceAvviso.getIuv () );
		if ( idStatoCorrente != null ) {
			var statoPagamento = new EpayDStatoPagamento ();
			statoPagamento.setIdStato ( idStatoCorrente );
			tPagamento.setEpayDStatoPagamento ( statoPagamento );
		}
		pagamentoRepository.persist ( tPagamento );
	}

	public EpayTPagamento findById ( Long idPagamento ) {
		return pagamentoRepository.findById ( idPagamento );
	}

	public EpayTPagamento getPagamentoActiveAndPayableByIUV ( String iuv ) {
		return pagamentoRepository.findByIUVAndIsActiveAndPayable ( iuv );
	}

	public PagedListResultDTO<EpayTPagamento> search (
					EpayTEnti enteEntity,
					String citizenFiscalCode,
					PaymentStatus status,
					String iuv,
					String[] sortableFields,
					String inputSortString,
					int pageIndex, // N.B zero-based
					int pageSize ) {

		return pagamentoRepository.search ( enteEntity, citizenFiscalCode, status, iuv, sortableFields, inputSortString, pageIndex, pageSize );
	}

	/* RDI-54 MODELLO UNICO - BEGIN */
	public PagamentoDTO buildPayment ( String cfPagatore, String nome, String cognome, String ragioneSociale, BigDecimal importo, String email, String note,
					EpayTTipoPagamento tipoPagamento, IuvComplex iuvComplex, String cfEnte, CodiceAvvisoDTO codiceAvviso, boolean isFromCreateDebtPosition ) throws Exception {

		var methodName = "[buildPayment] ";
		Log.infof ( "%sBEGIN", methodName );

		// costruisce il pagatore
		var pagatore = new AnagraficaDTO ();
		pagatore.setCodiceFiscale ( cfPagatore );
		pagatore.setCognome ( cognome );
		pagatore.setEmail ( email );
		pagatore.setNome ( nome );
		pagatore.setRagioneSociale ( ragioneSociale );
		pagatore.setFlagPersonaFisica ( StringUtils.isEmpty ( ragioneSociale ) );

		var epayTAnagrafica = anagraficaService.save ( anagraficaMapper.toEntity ( pagatore ) );
		var anagraficaInserita = anagraficaMapper.toDto ( epayTAnagrafica );
		pagatore.setIdAnagrafica ( anagraficaInserita.getIdAnagrafica () );
		Log.infof ( "%sinserita anagrafica pagatore:%s", methodName, anagraficaInserita );

		// costruisce il pagamento e lo inserisce
		var pagamento = new PagamentoDTO ();
		pagamento.setCausale ( tipoPagamento.getDescrizionePortale () );
		pagamento.setImporto ( importo );
		pagamento.setNote ( note );
		pagamento.setPagatore ( pagatore );
		pagamento.setTipoPagamento ( tipoPagamentoMapper.toDto ( tipoPagamento ) );
		pagamento.setConsensoPrivacy ( Boolean.TRUE );
		pagamento.setPagamentoSpontaneo ( Boolean.TRUE );
		pagamento.setIdStatoCorrente ( StatoPagamento.NON_DEFINITO.getId () );
		pagamento.setAnnoAccertamento ( null );
		pagamento.setNumeroAccertamento ( null );
		pagamento.setAuxDigit ( iuvComplex.getAuxDigit () );
		pagamento.setApplicationCode ( iuvComplex.getApplicationCode () );
		pagamento.setIuv ( iuvComplex.getIuvOttico () );
		pagamento.setIuvRegistroVersamenti ( iuvComplex.getIuvOttico () );

		var calendar = Calendar.getInstance ();
		if ( isFromCreateDebtPosition ) { // Imposta le date al 31 dicembre dell'anno corrente alle 23:59
			calendar.set ( Calendar.MONTH, Calendar.DECEMBER );
			calendar.set ( Calendar.DAY_OF_MONTH, 31 );
			calendar.set ( Calendar.HOUR_OF_DAY, 23 );
			calendar.set ( Calendar.MINUTE, 59 );
			calendar.set ( Calendar.SECOND, 59 );
			pagamento.setFineValidita ( calendar.getTime () );
		} else { // caso CDU#6 Payment - Pagamento spontaneo
			var currentDate = new Date ();
			calendar.setTime ( currentDate );
			calendar.set ( Calendar.HOUR_OF_DAY, 23 );
			calendar.set ( Calendar.MINUTE, 59 );
			calendar.set ( Calendar.SECOND, 59 );
			pagamento.setFineValidita( calendar.getTime () );
		}
		pagamento.setDataScadenza ( calendar.getTime () );
		
		var dsr = getDatiSpecificiRiscossione ( tipoPagamento.getCodiceVersamento (), cfEnte );
		List<PagamentoComponentiDTO> list = new ArrayList<> ();
		list.add ( creaComponenteDiDefault ( dsr, importo ) );
		pagamento.setComponenti ( list );
		var pagamentoEntity = insertPagamento ( pagamento );
		pagamento = mapPagamentoEsteso ( pagamentoEntity );
		Log.infof ( "%sinserito pagamento:%s", methodName, pagamento );

		// aggiorna il codice avviso e lo stato del pagamento
		if ( null == codiceAvviso ) {
			codiceAvviso = new CodiceAvvisoDTO ( iuvComplex.getAuxDigit (), iuvComplex.getAuxDigit (), iuvComplex.getIuvOttico (), true );
		}
		var statoPagamento = StatoPagamento.DA_PAGARE;
		updateCodiceAvvisoAndStatoCorrente ( pagamento.getIdPagamento (), codiceAvviso, statoPagamento.getId () );
		Log.infof ( "%saggiornato codiceAvviso:%s e statoPagamento:%s" + statoPagamento, methodName, codiceAvviso, statoPagamento );

		// tracciamento
		registroVersamentiService.tracciaRegistroPagamento ( pagamento, statoPagamento, null, null );

		Log.infof ( "%sEND", methodName );
		return pagamento;
	}

	public Cart buildCart ( PagamentoDTO pagamento ) throws IllegalArgumentException {
		var cart = new Cart ();

		List<PaymentNotice> pnList = new LinkedList<> ();
		//
		var paymentNotice = new PaymentNotice ();
		paymentNotice.setAmount ( pagamento.getImporto () );
		paymentNotice.setApplicationId ( pagamento.getTipoPagamento ().getIdApplicazione () );
		paymentNotice.setDescription ( pagamento.getCausale () );
		paymentNotice.setNoticeNumber ( pagamento.getCodiceAvviso () );
		//
		pnList.add ( paymentNotice );

		cart.setPaymentNotices ( pnList );
		cart.setEmailNotice ( pagamento.getPagatore () != null ? pagamento.getPagatore ().getEmail () : null );

		return cart;
	}
	/* RDI-54 MODELLO UNICO - END */

	private DatiSpecificiRiscossioneOutput getDatiSpecificiRiscossione ( String codiceVersamento, String organizationFiscalCode ) throws Exception {
		return getDatiSpecificiRiscossioneOutput ( codiceVersamento, organizationFiscalCode, configurazioneService, tassonomiaAdapterClient );
	}

	private PagamentoComponentiDTO creaComponenteDiDefault ( DatiSpecificiRiscossioneOutput dsr, BigDecimal importo ) throws TassonomiaServiceException {
		return getPagamentoComponentiDTO ( dsr, importo, MAX_ERROR_MESSAGE_WIDTH );
	}
}
