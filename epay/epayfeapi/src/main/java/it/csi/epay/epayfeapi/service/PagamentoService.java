/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.service;

import static it.csi.epay.epayfeapi.service.external.PaymentService.getDatiSpecificiRiscossioneOutput;
import static it.csi.epay.epayfeapi.service.external.PaymentService.getPagamentoComponentiDTO;
import static it.csi.epay.epayfeapi.util.Constants.MAX_ERROR_MESSAGE_WIDTH;
import static it.csi.epay.epayfeapi.util.Constants.SYSTEM_USER_DEFAULT;
import static it.csi.epay.epayfeapi.util.DateUtil.currentTimestamp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;

import io.quarkus.logging.Log;
import it.csi.epay.epayfeapi.dto.AnagraficaDTO;
import it.csi.epay.epayfeapi.dto.CodiceAvvisoDTO;
import it.csi.epay.epayfeapi.dto.EnteDTO;
import it.csi.epay.epayfeapi.dto.PagamentoComponentiDTO;
import it.csi.epay.epayfeapi.dto.PagamentoDTO;
import it.csi.epay.epayfeapi.dto.PagamentoRiferimentiDTO;
import it.csi.epay.epayfeapi.dto.PagamentoSecondarioComponentiDTO;
import it.csi.epay.epayfeapi.dto.PagedListResultDTO;
import it.csi.epay.epayfeapi.dto.PaymentStatus;
import it.csi.epay.epayfeapi.dto.StatoPagamento;
import it.csi.epay.epayfeapi.entity.EpayDStatoPagamento;
import it.csi.epay.epayfeapi.entity.EpayTAnagrafica;
import it.csi.epay.epayfeapi.entity.EpayTEnti;
import it.csi.epay.epayfeapi.entity.EpayTPagamento;
import it.csi.epay.epayfeapi.entity.EpayTPagamentoComponenti;
import it.csi.epay.epayfeapi.entity.EpayTPagamentoRiferimenti;
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
import it.csi.epay.epayfeapi.model.DatiSpecificiRiscossioneOutput;
import it.csi.epay.epayfeapi.repository.PagamentoRepository;
import it.csi.epay.epayfeapi.rest.client.TassonomiaAdapterClient;
import it.csi.epay.epayfeapi.soap.client.mdpmultiiuv.IuvComplex;


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
	public EpayTPagamento insertPagamento ( PagamentoDTO pagamento ) {
		EpayTPagamento tPagamento = pagamentoMapper.toEntity ( pagamento );
		tPagamento.setIuvNumeroAvviso ( pagamento.getIuv () );
		tPagamento.setFlagInviato ( false );

		Log.info ( "START MONITORAGGIO INSERIMENTO PAGAMENTO" );
		Log.info ( pagamento );
		Log.info ( ( null != pagamento.getTipoPagamento () ) ? pagamento.getTipoPagamento ().toString () : "null" );
		Log.info ( "END MONITORAGGIO INSERIMENTO PAGAMENTO" );

		if ( null == tPagamento.getEpayTTipoPagamento () ) {
			tPagamento.setEpayTTipoPagamento ( tipoPagamentoMapper.toEntity ( pagamento.getTipoPagamento () ) );
		}

		EpayTTipoPagamento tipoPagamento = tipoPagamentoService.findByTipoPagamento ( tPagamento.getEpayTTipoPagamento () );
		tPagamento.setEpayTTipoPagamento ( tipoPagamento );
		tPagamento.setDataInserimento ( currentTimestamp () );
		tPagamento.setUtenteUltimaModifica ( setUtenteNotEmpty ( pagamento.getUtenteUltimaModifica () ) );

		if ( pagamento.getPagatore () != null ) {
			tPagamento.setEpayTAnagrafica ( anagraficaService.getByAnagrafica ( anagraficaMapper.toEntity ( pagamento.getPagatore () ) ) );
		}
		EpayDStatoPagamento epayDStatoPagamento = statoPagamentoService.findById ( pagamento.getIdStatoCorrente () );
		tPagamento.setEpayDStatoPagamento ( epayDStatoPagamento );
		tPagamento.setCausale ( StringUtils.substring ( tPagamento.getCausale (), 0, 140 ) );

		if ( pagamento.getComponenti () != null && !pagamento.getComponenti ().isEmpty () ) {
			if ( tPagamento.getEpayTPagamentoComponentis () == null ) {
				tPagamento.setEpayTPagamentoComponentis ( new ArrayList<> () );
			}
			for ( PagamentoComponentiDTO componenti: pagamento.getComponenti () ) {
				EpayTPagamentoComponenti pagamentoComponenti = pagamentoComponentiMapper.toEntity ( componenti );
				pagamentoComponenti.setCausale ( StringUtils.substring ( pagamentoComponenti.getCausale (), 0, 140 ) );
				pagamentoComponenti.setUtenteUltimaModifica ( setUtenteNotEmpty ( componenti.getUtenteUltimaModifica () ) );
				tPagamento.addEpayTPagamentoComponenti ( pagamentoComponenti );
			}
		}

		if ( pagamento.getRiferimenti () != null && !pagamento.getRiferimenti ().isEmpty () ) {
			if ( tPagamento.getEpayTPagamentoRiferimentis () == null ) {
				tPagamento.setEpayTPagamentoRiferimentis ( new ArrayList<> () );
			}
			for ( PagamentoRiferimentiDTO riferimenti: pagamento.getRiferimenti () ) {
				EpayTPagamentoRiferimenti pagamentoRiferimenti = pagamentoRiferimentiMapper.toEntity ( riferimenti );
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
		PagamentoDTO pagamento = mapPagamentoBase ( tPagamento );

		if ( tPagamento.getEpayTPagamentoComponentis () != null && !tPagamento.getEpayTPagamentoComponentis ().isEmpty () ) {
			pagamento.setComponenti ( pagamentoComponentiMapper.toDtoList ( tPagamento.getEpayTPagamentoComponentis () ) );
		}

		// gestione importi secondari e componenti secondari
		List<PagamentoSecondarioComponentiDTO> componentiSecondariFinale = new ArrayList<> ();
		if ( tPagamento.getEpayTPagamentoSecondarios () != null && !tPagamento.getEpayTPagamentoSecondarios ().isEmpty () ) {
			// per ogni pagamento secondario devo mappare i componenti secondari
			for ( EpayTPagamentoSecondario pagamentoSecondario: tPagamento.getEpayTPagamentoSecondarios () ) {
				List<PagamentoSecondarioComponentiDTO> tmp
					= pagamentoSecondarioComponentiMapper.toDtoList ( pagamentoSecondario.getEpayTPagamentoSecondarioComponentis () );
				for ( PagamentoSecondarioComponentiDTO componenteSecondario: tmp ) {
					componenteSecondario.setApplicationId (
						null != pagamentoSecondario.getEpayTTipoPagamento () ? pagamentoSecondario.getEpayTTipoPagamento ().getIdApplicazione () : "" );
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
		PagamentoDTO pagamento = pagamentoMapper.toDto ( tPagamento );

		pagamento.setAuxDigit ( tPagamento.getAuxDigit () );
		pagamento.setIuv ( tPagamento.getIuvNumeroAvviso () );
		pagamento.setApplicationCode ( tPagamento.getApplicationCode () );

		Log.info ( "AUX DIGIT IS " + pagamento.getAuxDigit () );
		Log.info ( "IUV IS " + pagamento.getIuv () );
		Log.info ( "APP CODE IS " + pagamento.getApplicationCode () );

		pagamento.setPagatore ( anagraficaMapper.toDto ( tPagamento.getEpayTAnagrafica () ) );
		pagamento.setTipoPagamento ( tipoPagamentoMapper.toDto ( tPagamento.getEpayTTipoPagamento () ) );
		pagamento.setEnte ( enteMapper.toDto ( tPagamento.getEpayTTipoPagamento ().getEpayTEnti () ) );
		pagamento.setIdStatoCorrente ( tPagamento.getEpayDStatoPagamento ().getIdStato () );

		if ( tPagamento.getEpayTRegistroVersamentis () == null || tPagamento.getEpayTRegistroVersamentis ().isEmpty () ) {
			pagamento.setIuvRegistroVersamenti ( pagamento.getIuv () );
			pagamento.setDataStatoCorrente ( pagamento.getDataInserimento () );
		} else {
			pagamento.setIuvRegistroVersamenti ( getMostRecentFromRegistry ( tPagamento.getEpayTRegistroVersamentis () ) );
			for ( EpayTRegistroVersamenti rv: tPagamento.getEpayTRegistroVersamentis () ) {
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
		EpayTRegistroVersamenti recente = new EpayTRegistroVersamenti ();
		recente.setIdRegistro ( -1L );
		for ( EpayTRegistroVersamenti epayTRegistroVersamenti: epayTRegistroVersamentis ) {
			Log.info ( "mappaPagamento.recente  IuvRegistroVersamenti:" + epayTRegistroVersamenti.getIuv () );
			if ( epayTRegistroVersamenti.getIdRegistro () > recente.getIdRegistro () ) {
				recente = epayTRegistroVersamenti;
			}
		}
		Log.info ( "mappaPagamento.recente SELEZIONATO IuvRegistroVersamenti:" + recente.getIuv () );
		return recente.getIuv ();
	}

	public void updateCodiceAvvisoAndStatoCorrente ( Long idPagamento, CodiceAvvisoDTO codiceAvviso, Integer idStatoCorrente ) {
		EpayTPagamento tPagamento = pagamentoRepository.findById ( idPagamento );
		tPagamento.setAuxDigit ( codiceAvviso.getAuxDigit () );
		tPagamento.setApplicationCode ( codiceAvviso.getApplicationCode () );
		tPagamento.setIuvNumeroAvviso ( codiceAvviso.getIuv () );
		if ( idStatoCorrente != null ) {
			EpayDStatoPagamento statoPagamento = new EpayDStatoPagamento ();
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
		String [] sortableFields,
		String inputSortString,
		int pageIndex, // N.B zero-based
		int pageSize ) {

		return pagamentoRepository.search ( enteEntity, citizenFiscalCode, status, iuv, sortableFields, inputSortString, pageIndex, pageSize );
	}

	public PagamentoDTO buildPayment ( String cfPagatore, String nome, String cognome, String ragioneSociale, BigDecimal importo, String email, String note,
		EpayTTipoPagamento tipoPagamento, IuvComplex iuv, String cfEnte, CodiceAvvisoDTO codiceAvviso ) throws Exception {
		AnagraficaDTO pagatore = new AnagraficaDTO ();
		Log.info ( "Started buildPayment" );
		pagatore.setCodiceFiscale ( cfPagatore );
		pagatore.setCognome ( cognome );
		pagatore.setEmail ( email );
		pagatore.setNome ( nome );
		pagatore.setRagioneSociale ( ragioneSociale );
		pagatore.setFlagPersonaFisica ( StringUtils.isEmpty ( ragioneSociale ) );

		EpayTAnagrafica epayTAnagrafica = anagraficaService.save ( anagraficaMapper.toEntity ( pagatore ) );
		AnagraficaDTO anagraficaInserita = anagraficaMapper.toDto ( epayTAnagrafica );
		pagatore.setIdAnagrafica ( anagraficaInserita.getIdAnagrafica () );
		Log.info ( "Inserted anagrafica pagatore:" + anagraficaInserita );

		PagamentoDTO pagamento = new PagamentoDTO ();
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
		pagamento.setIuv ( iuv.getIuvOttico () );
		pagamento.setIuvRegistroVersamenti ( iuv.getIuvOttico () );

		List<PagamentoComponentiDTO> listaComponenti = new ArrayList<> ();

		if ( null == codiceAvviso ) {
			DatiSpecificiRiscossioneOutput dsr = getDatiSpecificiRiscossione ( tipoPagamento, cfEnte );
			pagamento.getComponenti ().add ( creaComponenteDiDefault ( dsr, importo ) );
			pagamento.setComponenti ( listaComponenti );
			pagamento = mapPagamentoEsteso ( insertPagamento ( pagamento ) );

			updateCodiceAvvisoAndStatoCorrente ( pagamento.getIdPagamento (), new CodiceAvvisoDTO ( iuv.getAuxDigit (), null, iuv.getIuvOttico (), Boolean.TRUE ),
				StatoPagamento.DA_PAGARE.getId () );

			registroVersamentiService.tracciaRegistroPagamento ( pagamento, StatoPagamento.DA_PAGARE, null, null );

			byte [] oldLogo = pagamento.getEnte ().getLogo ();
			EnteDTO oldEnti = pagamento.getTipoPagamento ().getEpayTEnti ();
			pagamento.getEnte ().setLogo ( null );
			pagamento.getTipoPagamento ().setEpayTEnti ( null );
			Log.info ( "Costruito pagamento : " + pagamento );
			pagamento.getEnte ().setLogo ( oldLogo );
			pagamento.getTipoPagamento ().setEpayTEnti ( oldEnti );

		} else {
			DatiSpecificiRiscossioneOutput dsr = getDatiSpecificiRiscossione ( tipoPagamento, cfEnte );
			pagamento.getComponenti ().add ( creaComponenteDiDefault ( dsr, importo ) );

			pagamento.setComponenti ( listaComponenti );

			EpayTPagamento entityPagamento = insertPagamento ( pagamento );
			updateCodiceAvvisoAndStatoCorrente ( entityPagamento.getIdPagamento (), codiceAvviso, StatoPagamento.DA_PAGARE.getId () );

			pagamento = mapPagamentoEsteso ( entityPagamento );

			registroVersamentiService.tracciaRegistroPagamento ( pagamento, StatoPagamento.DA_PAGARE, null, null );
		}
		return pagamento;
	}

	private DatiSpecificiRiscossioneOutput getDatiSpecificiRiscossione ( EpayTTipoPagamento tipoPagamento, String organizationFiscalCode ) throws Exception {
		return getDatiSpecificiRiscossioneOutput ( tipoPagamento, organizationFiscalCode, configurazioneService, tassonomiaAdapterClient );
	}

	private PagamentoComponentiDTO creaComponenteDiDefault ( DatiSpecificiRiscossioneOutput dsr, BigDecimal importo ) throws TassonomiaServiceException {
		return getPagamentoComponentiDTO ( dsr, importo, MAX_ERROR_MESSAGE_WIDTH );
	}
}
