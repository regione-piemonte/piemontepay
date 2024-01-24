/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.business.impl;

import it.csi.epay.epaypaweb.business.EPaypaBusinessBase;
import it.csi.epay.epaypaweb.business.interf.GestioneFlussiBusiness;
import it.csi.epay.epaypaweb.dto.*;
import it.csi.epay.epaypaweb.dto.common.*;
import it.csi.epay.epaypaweb.enumeration.*;
import it.csi.epay.epaypaweb.exception.*;
import it.csi.epay.epaypaweb.integration.enti2epaywso.impl.Enti2EPaywsoServiceMgrImpl;
import it.csi.epay.epaypaweb.integration.enti2epaywso.interf.Enti2EPaywsoServiceMgr;
import it.csi.epay.epaypaweb.integration.epaybeapi.impl.AvvisoPagamentoImpl;
import it.csi.epay.epaypaweb.persistence.dad.interf.*;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTTipoRevoca;
import it.csi.epay.epaypaweb.util.Util;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

import static it.csi.epay.epaypaweb.enumeration.ParseErrorEnum.*;
import static it.csi.epay.epaypaweb.util.Util.quote;


@Stateless
@TransactionManagement ( TransactionManagementType.BEAN )
public class GestioneFlussiBusinessImpl extends EPaypaBusinessBase implements GestioneFlussiBusiness {

	private static final String CLASSNAME = GestioneFlussiBusinessImpl.class.getSimpleName ();

	@Inject
	private CommonDad commonDad;

	@Inject
	private InvioMailDad invioMailDad;

	@Inject
	private AccessoDad accessoDad;

	@Inject
	private GestioneFlussiDad gestioneFlussiDad;

	@Inject
	private CsiLogAuditDad csiLogAuditDad;

	@Resource
	private UserTransaction transaction;

	private class SkipException extends Exception {

		private static final long serialVersionUID = 1L;
	};

	private static final String NINE_NINE = "999999999.99";

	private void eventuallyThrowBusinessException ( Throwable t, String methodName ) throws BusinessException {
		if ( t instanceof SkipException ) {
			// esce senza emettere eccezioni
		} else {
			if ( t instanceof BusinessException ) {
				throw (BusinessException) t;
			}

			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );
		}
	}

	@Override
	public List<TipoAggiornamentoPosizioneDebitoriaDto> getTipoAggiornamentoPosizioneDebitoriaList () throws BusinessException {
		String methodName = "getTipoAggiornamentoPosizioneDebitoriaList";
		

		List<TipoAggiornamentoPosizioneDebitoriaDto> resList = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			resList = commonDad.findAllTipoAggiornamentoPosizioniDebitorie ();

		} catch ( Throwable t ) {
			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );

		} finally {
			log.info ( "result: " + resList );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return resList;
	}

	@Override
	public List<StatoFlussoDto> getStatoFlussoList ( DirezioneEnum direzione ) throws BusinessException {
		String methodName = "getStatoFlussoList";
		
		

		List<StatoFlussoDto> resList = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			resList = commonDad.findAllStatoFlussoByDirezione ( direzione );

		} catch ( Throwable t ) {
			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );

		} finally {
			log.info ( "result: " + resList );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return resList;
	}

	@Override
	public void trasmettiFlussoNotifichePagamento(TrasmettiFlussoNotifichePagamentoRequestDto trasmettiFlussoNotifichePagamentoRequestDto) throws BusinessException{
		String methodName = "trasmettiFlussoNotifichePagamento";
		
		FlussoCompletoDto<NotificaPagamentoDto> flussoCompletoDto = trasmettiFlussoNotifichePagamentoRequestDto.getFlussoCompletoDto();
		

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			// controlli
			FlussoDto flussoDto = checkFlussoTrasmesso ( TipoFlussoEnum.NOTIFICHE_PAGAMENTO, flussoCompletoDto, true, methodName );

			transaction.begin ();

			// inserisce testata e notifiche
			Long idFlusso = gestioneFlussiDad.insertFlussoNotifichePagamento ( flussoCompletoDto, getTimestampNow () );
			log.info ( "trasmissione flusso di notifiche di pagamento, idFlusso:" + idFlusso + " effettuata" );

			// se l'ente risulta configurato per l'invio delle mail, inserisce in coda la mail da inviare
			accodaInvioMailFlusso ( idFlusso, flussoDto.getCodFiscaleEnte (), TipoMailEnum.TRASMISSIONE_NOTIFICHE_PAGAMENTO );

			transaction.commit ();

			logAuditOperation(CsiLogAuditOperationEnum.INSERT, trasmettiFlussoNotifichePagamentoRequestDto, "epaypa_t_flusso", "ID FLUSSO=" + idFlusso, "Inserimento Flusso Notifiche di Pagamento");

		} catch ( Throwable t ) {
			try {
				transaction.rollback ();

			} catch ( IllegalStateException | SecurityException | SystemException e ) {
				throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );
			}
			eventuallyThrowBusinessException ( t, methodName );

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
	}

	public FlussoCompletoDto<NotificaPagamentoDto> fromRevocaToNotifiche ( FlussoCompletoDto<RichiestaDiRevocaDto> flussoCompletoDto ) {

		FlussoCompletoDto<NotificaPagamentoDto> flussoCompletoNotificheDto = new FlussoCompletoDto<> ();
		NotificaPagamentoDto nt = new NotificaPagamentoDto ();

		List<NotificaPagamentoDto> listNotifiche = new ArrayList<> ();
		for ( RichiestaDiRevocaDto richiesteRevoca: flussoCompletoDto.getItemList () ) {
			nt.setIUV ( richiesteRevoca.getIUV () );
			nt.setImportoPagato ( richiesteRevoca.getImportoPagato () );
			nt.setCodAvviso ( richiesteRevoca.getCodiceContestoPagamento () );
			nt.setDataEsitoPagamento ( richiesteRevoca.getDataEsitoPagamento () );
			nt.setSoggettoDebitore ( richiesteRevoca.getSoggettoDebitore () );
			nt.setIdFlusso ( richiesteRevoca.getId () );

			for ( DatiSingolaRevocaDto item: richiesteRevoca.getListaDatiSingolaRevoca () ) {
				nt.setIUR ( item.getIur () );
				listNotifiche.add ( nt );
			}
		}

		flussoCompletoNotificheDto.setItemList ( listNotifiche );

		return flussoCompletoNotificheDto;
	}

	@Override
	public void trasmettiFlussoRichiesteDiRevoca(TrasmettiFlussoRichiesteDiRevocaRequestDto trasmettiFlussoRichiesteDiRevocaRequestDto) throws BusinessException{
		//public void trasmettiFlussoRichiesteDiRevoca ( FlussoCompletoDto<RichiestaDiRevocaDto> flussoCompletoDto ) throws BusinessException {
		String methodName = "trasmettiFlussoRichiesteDiRevoca";
		
		FlussoCompletoDto<RichiestaDiRevocaDto> flussoCompletoDto = trasmettiFlussoRichiesteDiRevocaRequestDto.getFlussoCompletoDto();
		

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			// controlli
			FlussoDto flussoDto = checkFlussoTrasmesso ( TipoFlussoEnum.RICHIESTE_DI_REVOCA, flussoCompletoDto, true, methodName );

			transaction.begin ();

			// inserisce testata e notifiche come notifiche di pagamento

			//         FlussoCompletoDto<NotificaPagamentoDto> perNotifiche = new FlussoCompletoDto<> ();

			//          perNotifiche = fromRevocaToNotifiche(flussoCompletoDto);
			//         perNotifiche.setFlusso ( flussoCompletoDto.getFlusso () );

			//Timestamp t = new Timestamp ( System.currentTimeMillis() );

			List<Long> idRRList = new ArrayList<Long>();
			for ( RichiestaDiRevocaDto richiestaRevoca: flussoCompletoDto.getItemList () ) {
				EpaypaTTipoRevoca tipoRevocaEntity = gestioneFlussiDad.getEpaypaTTipoRevocaById ( richiestaRevoca.getTipoRevoca () );
				Long idRR = gestioneFlussiDad.insertRR ( richiestaRevoca, tipoRevocaEntity );
				idRRList.add(idRR);

				//inserire singola revoca
				List<DatiSingolaRevocaDto> dettagliRevoca = richiestaRevoca.getListaDatiSingolaRevoca ();

				for ( Iterator<DatiSingolaRevocaDto> iterator = dettagliRevoca.iterator (); iterator.hasNext (); ) {
					DatiSingolaRevocaDto datiSingolaRevocaDto = iterator.next ();
					gestioneFlussiDad.insertDettaglioRR ( datiSingolaRevocaDto, idRR );

				}

			}

			//  gestioneFlussiDad.insertER(flussoCompletoDto);//<---------------- TODO
			// se l'ente risulta configurato per l'invio delle mail, inserisce in coda la mail da inviare
			accodaInvioMailFlusso ( null, flussoDto.getCodFiscaleEnte (), TipoMailEnum.TRASMISSIONE_RICHIESTE_DI_REVOCA );

			transaction.commit ();
			logAuditOperation(CsiLogAuditOperationEnum.INSERT, trasmettiFlussoRichiesteDiRevocaRequestDto, "epaypa_t_rr", "ID RR=" + idRRList, "Inserimento Richieste di Revoca");

		} catch ( Throwable t ) {
			try {
				transaction.rollback ();

			} catch ( IllegalStateException | SecurityException | SystemException e ) {
				throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );
			}
			eventuallyThrowBusinessException ( t, methodName );

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
	}

	@Override
	public void trasmettiFlussoAvvisoScaduto(TrasmettiFlussoAvvisoScadutoRequestDto trasmettiFlussoAvvisoScadutoRequestDto) throws BusinessException{
		//public void trasmettiFlussoAvvisoScaduto ( FlussoCompletoDto<AvvisoScadutoDto> flussoCompletoDto ) throws BusinessException {
		String methodName = "trasmettiFlussoAvvisoScaduto";
		
		FlussoCompletoDto<AvvisoScadutoDto> flussoCompletoDto = trasmettiFlussoAvvisoScadutoRequestDto.getFlussoCompletoDto();
		

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			// controlli
			FlussoDto flussoDto = checkFlussoTrasmesso ( TipoFlussoEnum.AVVISI_SCADUTI, flussoCompletoDto, true, methodName );

			transaction.begin ();

			// inserisce testata e notifiche
			Long idFlusso = gestioneFlussiDad.insertFlussoAvvisiScaduti ( flussoCompletoDto, getTimestampNow () );
			log.info ( "trasmissione flusso di avvisi scaduti, idFlusso:" + idFlusso + " effettuata" );

			// se l'ente risulta configurato per l'invio delle mail, inserisce in coda la mail da inviare
			accodaInvioMailFlusso ( idFlusso, flussoDto.getCodFiscaleEnte (), TipoMailEnum.TRASMISSIONE_AVVISI_SCADUTI );

			transaction.commit ();

			logAuditOperation(CsiLogAuditOperationEnum.INSERT, trasmettiFlussoAvvisoScadutoRequestDto, "epaypa_t_flusso", "ID FLUSSO=" + idFlusso, "Inserimento Flusso Avvisi Scaduti");

		} catch ( Throwable t ) {
			try {
				transaction.rollback ();

			} catch ( IllegalStateException | SecurityException | SystemException e ) {
				throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );
			}
			eventuallyThrowBusinessException ( t, methodName );

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
	}

	@Override
	public void trasmettiFlussoRendicontazione ( FlussoCompletoDto<RiversamentoDto> flussoCompletoDto ) throws BusinessException {
		String methodName = "trasmettiFlussoRendicontazione";
		
		

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			transaction.begin ();

			// controlli
			FlussoDto flussoDto = checkFlussoTrasmesso ( TipoFlussoEnum.TRASMETTI_FLUSSO_RENDICONTAZIONE, flussoCompletoDto, false, methodName );

			// inserisce testata e rendicontazioni
			Long idFlusso = gestioneFlussiDad.insertFlussoRendicontazioni ( flussoCompletoDto, getTimestampNow () );
			log.info ( "trasmissione flusso di rendicontazioni, idFlusso:" + idFlusso + " effettuata" );

			// se l'ente risulta configurato per l'invio delle mail, inserisce in coda la mail da inviare
			accodaInvioMailFlusso ( idFlusso, flussoDto.getCodFiscaleEnte (), TipoMailEnum.TRASMISSIONE_FLUSSO_RENDICONTAZIONE );

			transaction.commit ();
		} catch ( Throwable t ) {
			try {
				transaction.rollback ();

			} catch ( IllegalStateException | SecurityException | SystemException e ) {
				throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );
			}
			eventuallyThrowBusinessException ( t, methodName );

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
	}

	//@formatter:off
	private FlussoDto checkFlussoTrasmesso ( TipoFlussoEnum tipoFlusso, FlussoCompletoDto<?> flussoCompletoDto, boolean mandatoryCodVersamento,
		String methodName )
						throws BusinessException {
		FlussoDto flussoDto;

		try {
			flussoDto = flussoCompletoDto.getFlusso ();
			checkNotNullTestataFlusso ( flussoDto, tipoFlusso );

			List<?> itemList = flussoCompletoDto.getItemList ();
			checkNotNullCorpoFlusso ( itemList, tipoFlusso );

			String codFiscaleEnte = flussoDto.getCodFiscaleEnte ();
			Integer idEnte = checkCodFiscaleEnte ( codFiscaleEnte );

			Integer idCodVersamento = null;
			String codVersamento = null;
			if ( mandatoryCodVersamento ) {
				codVersamento = flussoDto.getCodVersamento ();
				idCodVersamento = checkUniqueCodiceVersamento ( codVersamento, idEnte ).getId ();
			}

			// verifica che non esista gia un altro flusso con stesso id messaggio, id ente, eventuale codice versamento, e tipo flusso
			String idMessaggio = flussoDto.getIdMessaggio ();
			FlussoLightFilterDto filter = new FlussoLightFilterDto ();
			filter.setIdMessaggio ( idMessaggio );
			filter.setIdEnte ( idEnte );
			if ( mandatoryCodVersamento ) {
				filter.setIdCodVersamentoList ( Collections.singletonList ( idCodVersamento ) );
			}
			filter.setTipoFlusso ( tipoFlusso );
			//
			if ( gestioneFlussiDad.countAllFlussoByFilter ( filter, null ) > 0 ) {
				log.error ( "idMessaggio:" + filter.getIdMessaggio () + " non univoco per "
								+ "codFiscaleEnte:" + codFiscaleEnte + ", "
								+ ( mandatoryCodVersamento ? "codVersamento:" + codVersamento + ", " : "" )
								+ "tipoFlusso:" + filter.getTipoFlusso () );
				throw new BusinessTrasmettiFlussoExistsException ( idMessaggio, codFiscaleEnte, codVersamento, tipoFlusso );
			}

		} catch ( Throwable t ) {
			if ( t instanceof BusinessException ) {
				throw (BusinessException) t;

			}

			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );
		}

		return flussoDto;
	}
	//@formatter:on

	private void accodaInvioMailFlusso ( Long idFlusso, String codFiscaleEnte, TipoMailEnum tipoMail ) throws PersistenceException {
		EnteDto enteDto = commonDad.findEnteByCodFiscale ( codFiscaleEnte );
		if ( !StringUtils.isBlank ( enteDto.getEmail () ) ) {
			InvioMailDto invioMailDto = new InvioMailDto ();
			invioMailDto.setTo ( enteDto.getEmail () );
			invioMailDto.setCc ( null );
			invioMailDto.setTipoMailEnum ( tipoMail );
			invioMailDto.setIdFlusso ( idFlusso );
			//
			invioMailDad.insertInvioMail ( invioMailDto );
			log.info ( "accodamento email effettuato per l'ente CF:" + enteDto.getCodFiscale () + " a:" + enteDto.getEmail () );

		} else {
			log.info ( "l'ente CF:" + enteDto.getCodFiscale () + " non risulta configurato a ricevere email" );
		}
	}

	@Override
	public boolean existsFlusso ( Long idFlusso ) throws BusinessException {
		String methodName = "existsFlusso";

		boolean exists = false;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			// verifica
			exists = gestioneFlussiDad.existsFlussoById ( idFlusso );

		} catch ( Throwable t ) {
			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );

		} finally {
			log.info ( "result:" + exists );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return exists;
	}

	@Override
	public Long countFlussi ( FlussoLightFilterDto filter, String codUtente, List<CodiceVersamentoDto> listCodiciVersamento ) throws BusinessException {
		String methodName = "countFlussi";

		Long num = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			// imposta i codici di versamento sul filtro
			setIdCodVersamentoList ( filter, listCodiciVersamento );

			// conteggia
			num = gestioneFlussiDad.countAllFlussoByFilter ( filter, codUtente );

		} catch ( Throwable t ) {
			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );

		} finally {
			log.info ( "result:" + num );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return num;
	}

	@Override
	//@formatter:off
	public List<FlussoLightDto> getFlussoLightList ( FlussoLightFilterDto filter, List<CriterioOrdinamentoDto<ColumnNameFlussoEnum>> ord, PaginazioneDto pag,
		String codUtente, List<CodiceVersamentoDto> listCodiciVersamento )
						throws BusinessException {
		//@formatter:on
		String methodName = "getFlussoLightList";

		List<FlussoLightDto> resList = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			// imposta i codici di versamento sul filtro
			setIdCodVersamentoList ( filter, listCodiciVersamento );

			// ricerca
			resList = gestioneFlussiDad.findAllFlussoLightByFilter ( filter, ord, pag, codUtente );

		} catch ( Throwable t ) {
			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );

		} finally {
			log.info ( "result: " + resList );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return resList;
	}

	//--------------------------------//
	//RDI-048 - START
	//--------------------------------//
	@Override
	//@formatter:off
	public TotalSizeAndLightListDto<FlussoLightDto> getTotalSizeAndFlussoNotificheList ( TotalSizeAndFlussoNotificheListRequestDto totalSizeAndFlussoNotificheListRequestDto) throws BusinessException{
		/*public TotalSizeAndLightListDto<FlussoLightDto> getTotalSizeAndFlussoNotificheList ( FlussoLightFilterDto filter,
        List<CriterioOrdinamentoDto<ColumnNameNotificaPagamentoEnum>> ord, PaginazioneDto pag, String codUtente,
        List<CodiceVersamentoDto> listCodiciVersamento ) throws BusinessException {*/
		String methodName = "getTotalSizeAndFlussoNotificheList";

		TotalSizeAndLightListDto<FlussoLightDto> resDto = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			// imposta i codici di versamento sul filtro
			setIdCodVersamentoList ( totalSizeAndFlussoNotificheListRequestDto.getFilter(), totalSizeAndFlussoNotificheListRequestDto.getListCodiciVersamento());
			if (null == totalSizeAndFlussoNotificheListRequestDto.getFilter().getIdCodVersamentoList() 
							|| totalSizeAndFlussoNotificheListRequestDto.getFilter().getIdCodVersamentoList().isEmpty() )
			{
				//            	 se mancano i caodici versamento restituisco lista vuuota. EPAY 351
				List<FlussoLightDto> dtoList = new ArrayList<FlussoLightDto>();
				resDto = new TotalSizeAndLightListDto<> (new Long (0),dtoList );
				log.warn("assenza codici versamento");
			}

			else
			{
				// conteggia e ricerca
				resDto = new TotalSizeAndLightListDto<> (
								gestioneFlussiDad.countAllFlussoNotificheByFilter ( totalSizeAndFlussoNotificheListRequestDto.getFilter(), totalSizeAndFlussoNotificheListRequestDto.getCodiceFiscaleUtente()),
								gestioneFlussiDad.findAllFlussoNotificheByFilter ( totalSizeAndFlussoNotificheListRequestDto.getFilter(), totalSizeAndFlussoNotificheListRequestDto.getOrd(), totalSizeAndFlussoNotificheListRequestDto.getPag(), totalSizeAndFlussoNotificheListRequestDto.getCodiceFiscaleUtente() ) );

			}

			logAuditOperation(CsiLogAuditOperationEnum.EXTRACT, totalSizeAndFlussoNotificheListRequestDto, "epaypa_t_flusso", "FILTRO="+ totalSizeAndFlussoNotificheListRequestDto.getFilter().getAuditMessage(), "Ricerca Flusso Notifiche di Pagamento");

		} catch ( Throwable t ) {
			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );

		} finally {
			log.info ( "result:" +  resDto );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return resDto;
	}

	@Override
	public TotalSizeAndLightListDto<RichiestaRevocheFilterDto> getTotalSizeAndFlussoRevocheList ( TotalSizeAndFlussoRevocheListRequestDto totalSizeAndFlussoRevocheListRequestDto ) throws BusinessException{
		String methodName = "getTotalSizeAndFlussoRevocheList";

		TotalSizeAndLightListDto<RichiestaRevocheFilterDto> resDto = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			// conteggia e ricerca
			resDto = new TotalSizeAndLightListDto<> (
							gestioneFlussiDad.countAllFlussoRevocheByFilter ( totalSizeAndFlussoRevocheListRequestDto.getFilter(), totalSizeAndFlussoRevocheListRequestDto.getCodiceFiscaleUtente() ),
							gestioneFlussiDad.findAllFlussoRevocheByFilter ( totalSizeAndFlussoRevocheListRequestDto.getFilter(),  totalSizeAndFlussoRevocheListRequestDto.getPag(), totalSizeAndFlussoRevocheListRequestDto.getCodiceFiscaleUtente() ) );

			logAuditOperation(CsiLogAuditOperationEnum.EXTRACT, totalSizeAndFlussoRevocheListRequestDto, "epaypa_t_rr", "FILTRO="+ totalSizeAndFlussoRevocheListRequestDto.getFilter().getAuditMessage(), "Ricerca Flussi Revoche");

		} catch ( Throwable t ) {
			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );

		} finally {
			log.info ( "result:" +  resDto );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return resDto;

	}

	//@formatter:on
	//--------------------------------//
	//RDI-048 - STOP
	//--------------------------------//

	@Override
	public TotalSizeAndLightListDto<FlussoLightDto> getTotalSizeAndFlussoLightList ( FlussoLightFilterDto filter,
		List<CriterioOrdinamentoDto<ColumnNameFlussoEnum>> ord, PaginazioneDto pag, String codUtente, List<CodiceVersamentoDto> listCodiciVersamento )
						throws BusinessException {
		String methodName = "getTotalSizeAndFlussoLightList";
		TotalSizeAndLightListDto<FlussoLightDto> resDto = null;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );
			// imposta i codici di versamento sul filtro
			setIdCodVersamentoList ( filter, listCodiciVersamento );
			// conteggia e ricerca
			resDto = new TotalSizeAndLightListDto<> (
							gestioneFlussiDad.countAllFlussoByFilter ( filter, codUtente ),
							gestioneFlussiDad.findAllFlussoLightByFilter ( filter, ord, pag, codUtente ) );
		} catch ( Throwable t ) {
			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );
		} finally {
			log.info ( "result:" +  resDto );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
		return resDto;
	}

	private void setIdCodVersamentoList ( FlussoLightFilterDto filter, List<CodiceVersamentoDto> listCodiciVersamento )
					throws PersistenceException, BusinessMatchException {
		List<Integer> idCodVersamentoListInInput = filter.getIdCodVersamentoList ();

		//ENG : fix gestione codiciVersamento associati all'ente, passati dalla action, salvati in obj SessionContext
		List<Integer> idCodVersamentoListByIdProfiloAndIdEnte = new ArrayList<> ();
		if ( null != listCodiciVersamento && listCodiciVersamento.size () > 0 ) {
			for ( CodiceVersamentoDto codiceVersamentoDto: listCodiciVersamento ) {
				idCodVersamentoListByIdProfiloAndIdEnte.add ( codiceVersamentoDto.getId () );
			}
		}

		List<Integer> idCodVersamentoListInInputNew = null;
		if ( idCodVersamentoListInInput == null || idCodVersamentoListInInput.size () == 0 ) {
			if ( filter.getTipoFlusso () != TipoFlussoEnum.TRASMETTI_FLUSSO_RENDICONTAZIONE ) {
				// nessun filtro sui codici versamento equivale a filtro per tutti i codici versamento ammessi per idProfilo e idEnte
				idCodVersamentoListInInputNew = idCodVersamentoListByIdProfiloAndIdEnte;
			}

		} else {
			// verifica che i codici versamento nel filtro siano tutti contenuti nella lista di quelli ottenuti per idProfilo e idEnte
			if ( !idCodVersamentoListByIdProfiloAndIdEnte.containsAll ( idCodVersamentoListInInput ) ) {
				BusinessMatchException matchException = new BusinessMatchException ();
				matchException.setIdList1 ( idCodVersamentoListInInput );
				matchException.setIdList2 ( idCodVersamentoListByIdProfiloAndIdEnte );
				throw matchException;
			}

			idCodVersamentoListInInputNew = idCodVersamentoListInInput;
		}
		filter.setIdCodVersamentoList ( idCodVersamentoListInInputNew );
	}

	@Override
	public FlussoLightDto getFlussoLight ( Long idFlusso ) throws BusinessException {
		String methodName = "getFlussoLight";
		FlussoLightDto resDto = null;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );
			resDto = gestioneFlussiDad.findFlussoLightById ( idFlusso );
		} catch ( Throwable t ) {
			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );
		} finally {
			log.info ( "result:" +  resDto );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
		return resDto;
	}

	@Override
	public FlussoCompletoDto<NotificaPagamentoDto> getFlussoNotifichePagamento(FlussoNotifichePagamentoRequestDto flussoNotifichePagamentoRequestDto, boolean isDownload) throws BusinessException{
		String methodName = "getFlussoNotifichePagamento";
		FlussoCompletoDto<NotificaPagamentoDto> resDto = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			resDto = gestioneFlussiDad.findFlussoNotifichePagamento ( flussoNotifichePagamentoRequestDto.getIdFlusso() );

			String descrizione = String.format ( "Visualizzazione Flusso Notifiche di Pagamento%s", isDownload ? " (download file)" : "" );
			logAuditOperation(CsiLogAuditOperationEnum.EXTRACT, flussoNotifichePagamentoRequestDto, "epaypa_t_notifica_pagamento", "ID FLUSSO=" + flussoNotifichePagamentoRequestDto.getIdFlusso(), descrizione);
		} catch ( Throwable t ) {
			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );

		} finally {
			log.info ( "result:" +  resDto );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return resDto;
	}

	@Override
	public FlussoCompletoDto<PosizioneDebitoriaDto> getFlussoPosizioniDebitorie(FlussoPosizioniDebitorieRequestDto flussoPosizioniDebitorieRequestDto, boolean isDownload) throws BusinessException{

		String descrizione = String.format ( "Visualizzazione Flusso Posizioni Debitorie%s", isDownload ? " (download file)" : "" );

		FlussoCompletoDto<PosizioneDebitoriaDto> resDto = getFlussoPosizioniDebitorie(flussoPosizioniDebitorieRequestDto.getIdFlusso());
		logAuditOperation(CsiLogAuditOperationEnum.EXTRACT, flussoPosizioniDebitorieRequestDto, "epaypa_t_flusso", "ID FLUSSO=" + flussoPosizioniDebitorieRequestDto.getIdFlusso(), descrizione);
		return resDto;
	}


	@Override
	public FlussoCompletoDto<PosizioneDebitoriaDto> getFlussoPosizioniDebitorie ( Long idFlusso ) throws BusinessException {
		String methodName = "getFlussoPosizioniDebitorie";

		FlussoCompletoDto<PosizioneDebitoriaDto> resDto = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			resDto = gestioneFlussiDad.findFlussoPosizioniDebitorie ( idFlusso);

		} catch ( Throwable t ) {
			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );

		} finally {
			log.info ( "result:" +  resDto );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return resDto;
	}

	@Override
	public FlussoCompletoDto<PosizioneDebitoriaDaAggiornareDto> getFlussoPosizioniDebitorieDaAggiornare ( Long idFlusso ) throws BusinessException {
		String methodName = "getFlussoPosizioniDebitorieDaAggiornare";

		FlussoCompletoDto<PosizioneDebitoriaDaAggiornareDto> resDto = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			resDto = gestioneFlussiDad.findFlussoPosizioniDebitorieDaAggiornare ( idFlusso );

		} catch ( Throwable t ) {
			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );

		} finally {
			log.info ( "result:" +  resDto );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return resDto;
	}

	@Override
	public FlussoCompletoDto<AvvisoScadutoDto> getFlussoAvvisiScaduti ( Long idFlusso ) throws BusinessException {
		String methodName = "getFlussoAvvisiScaduti";

		FlussoCompletoDto<AvvisoScadutoDto> resDto = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			resDto = gestioneFlussiDad.findFlussoAvvisiScaduti ( idFlusso );

		} catch ( Throwable t ) {
			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );

		} finally {
			log.info ( "result:" +  resDto );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return resDto;
	}

	@Override
	public FlussoCompletoDto<RiversamentoDto> getFlussoRendicontazione ( Long idFlusso ) throws BusinessException {
		String methodName = "getFlussoRendicontazione";
		
		

		FlussoCompletoDto<RiversamentoDto> resDto = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			resDto = gestioneFlussiDad.findFlussoRendicontazione ( idFlusso);

		} catch ( Throwable t ) {
			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );

		} finally {
			log.info ( "result:" +  resDto );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return resDto;
	}

	@Override
	public Long countNotifichePagamento ( Long idFlusso ) throws BusinessException {
		String methodName = "countNotifichePagamento";
		
		

		Long num = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			num = gestioneFlussiDad.countAllNotificaPagamentoByIdFlusso ( idFlusso );

		} catch ( Throwable t ) {
			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );

		} finally {
			log.info ( "result:" + num );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return num;
	}

	@Override
	//@formatter:off
	public List<NotificaPagamentoLightDto> getNotificaPagamentoLightList ( Long idFlusso, List<CriterioOrdinamentoDto<ColumnNameNotificaPagamentoEnum>> ord,
		PaginazioneDto pag )
						throws BusinessException {
		//@formatter:on
		String methodName = "getNotificaPagamentoLightList";
		
		
		
		

		List<NotificaPagamentoLightDto> resList = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			resList = gestioneFlussiDad.findAllNotificaPagamentoLightByIdFlusso ( idFlusso, ord, pag );

		} catch ( Throwable t ) {
			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );

		} finally {
			log.info ( "result: " + resList );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return resList;
	}

	@Override
	//@formatter:off
	public TotalSizeAndLightListDto<NotificaPagamentoLightDto> getTotalSizeAndNotificaPagamentoLightList ( Long idFlusso,
		List<CriterioOrdinamentoDto<ColumnNameNotificaPagamentoEnum>> ord, PaginazioneDto pag )
						throws BusinessException {
		String methodName = "getTotalSizeAndNotificaPagamentoLightList";
		
		
		
		

		TotalSizeAndLightListDto<NotificaPagamentoLightDto> resDto = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			// conteggia e ricerca
			resDto = new TotalSizeAndLightListDto<> (
							gestioneFlussiDad.countAllNotificaPagamentoByIdFlusso ( idFlusso ),
							gestioneFlussiDad.findAllNotificaPagamentoLightByIdFlusso ( idFlusso, ord, pag ) );

		} catch ( Throwable t ) {
			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );

		} finally {
			log.info ( "result:" +  resDto );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return resDto;
	}
	//@formatter:on

	@Override
	public NotificaPagamentoLightDto getNotificaPagamentoLight(NotificaPagamentoRequestDto notificaPagamentoRequestDto) throws BusinessException{
		//public NotificaPagamentoLightDto getNotificaPagamentoLight ( Long idNotificaPagamento ) throws BusinessException {
		String methodName = "getNotificaPagamentoLight";
		
		

		NotificaPagamentoLightDto resDto = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			resDto = gestioneFlussiDad.findNotificaPagamentoLightById ( notificaPagamentoRequestDto.getIdNotificaPagamento() );

			logAuditOperation(CsiLogAuditOperationEnum.EXTRACT, notificaPagamentoRequestDto, "epaypa_t_notifica_pagamento", "ID NOTIFICA=" + notificaPagamentoRequestDto.getIdNotificaPagamento(), "Visualizzazione Dettaglio Notifica Pagamento");

		} catch ( Throwable t ) {
			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );

		} finally {
			log.info ( "result:" +  resDto );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return resDto;
	}

	@Override
	public NotificaPagamentoDto getNotificaPagamento(NotificaPagamentoRequestDto notificaPagamentoRequestDto) throws BusinessException{
		//public NotificaPagamentoDto getNotificaPagamento ( Long idNotificaPagamento ) throws BusinessException {
		String methodName = "getNotificaPagamento";
		
		

		NotificaPagamentoDto resDto = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			resDto = gestioneFlussiDad.findNotificaPagamentoById ( notificaPagamentoRequestDto.getIdNotificaPagamento() );

			logAuditOperation(CsiLogAuditOperationEnum.EXTRACT, notificaPagamentoRequestDto, "epaypa_t_notifica_pagamento", "ID NOTIFICA=" + notificaPagamentoRequestDto.getIdNotificaPagamento(), "Visualizzazione Dettaglio Notifica Pagamento");

		} catch ( Throwable t ) {
			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );

		} finally {
			log.info ( "result:" +  resDto );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return resDto;
	}

	@Override
	public List<SingolaRevocaDto> getDettaglioRevoca ( Long idRr ) throws BusinessException {
		String methodName = "getDettaglioRevoca";
		
		

		List<SingolaRevocaDto> resDto = null;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			resDto = gestioneFlussiDad.singolaRevocaByIdRr ( idRr );

		} catch ( Throwable t ) {
			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );

		} finally {
			log.info ( "result:" +  resDto );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return resDto;
	}

	@Override
	public Long countPosizioniDebitorie ( Long idFLusso ) throws BusinessException {
		String methodName = "countPosizioniDebitorie";
		
		

		Long num = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			num = gestioneFlussiDad.countAllPosizioneDebitoriaByIdFlusso ( idFLusso );

		} catch ( Throwable t ) {
			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );

		} finally {
			log.info ( "result:" + num );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return num;
	}

	@Override
	//@formatter:off
	public List<PosizioneDebitoriaLightDto> getPosizioneDebitoriaLightList ( Long idFlusso, List<CriterioOrdinamentoDto<ColumnNamePosizioneDebitoriaEnum>> ord,
		PaginazioneDto pag )
						throws BusinessException {
		//@formatter:on
		String methodName = "getPosizioneDebitoriaLightList";
		
		
		
		

		List<PosizioneDebitoriaLightDto> resList = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			resList = gestioneFlussiDad.findAllPosizioneDebitoriaLightByIdFlusso ( idFlusso, ord, pag );

		} catch ( Throwable t ) {
			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );

		} finally {
			log.info ( "result: " + resList );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return resList;
	}

	@Override
	//@formatter:off
	public TotalSizeAndLightListDto<PosizioneDebitoriaLightDto> getTotalSizeAndPosizioneDebitoriaLightList(TotalSizeAndPosizioneDebitoriaLightListRequestDto totalSizeAndPosizioneDebitoriaLightListRequestDto) throws BusinessException{
		String methodName = "getTotalSizeAndPosizioneDebitoriaLightList";
		
		
		
		

		TotalSizeAndLightListDto<PosizioneDebitoriaLightDto> resDto = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			// conteggia e ricerca
			resDto = new TotalSizeAndLightListDto<> (
							gestioneFlussiDad.countAllPosizioneDebitoriaByIdFlusso ( totalSizeAndPosizioneDebitoriaLightListRequestDto.getIdFlusso() ),
							gestioneFlussiDad.findAllPosizioneDebitoriaLightByIdFlusso ( totalSizeAndPosizioneDebitoriaLightListRequestDto.getIdFlusso(), totalSizeAndPosizioneDebitoriaLightListRequestDto.getOrd(), totalSizeAndPosizioneDebitoriaLightListRequestDto.getPag()) );

			logAuditOperation(CsiLogAuditOperationEnum.EXTRACT, totalSizeAndPosizioneDebitoriaLightListRequestDto, "epaypa_t_flusso", "ID FLUSSO=" + totalSizeAndPosizioneDebitoriaLightListRequestDto.getIdFlusso(), "Visualizzazione Flusso Posizioni Debitorie");

		} catch ( Throwable t ) {
			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );

		} finally {
			log.info ( "result:" +  resDto );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return resDto;
	}
	//@formatter:on

	@Override
	public PosizioneDebitoriaLightDto getPosizioneDebitoriaLight ( Long idPosizioneDebitoria ) throws BusinessException {
		String methodName = "getPosizioneDebitoriaLight";
		
		

		PosizioneDebitoriaLightDto resDto = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			resDto = gestioneFlussiDad.findPosizioneDebitoriaLightById ( idPosizioneDebitoria );

		} catch ( Throwable t ) {
			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );

		} finally {
			log.info ( "result:" +  resDto );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return resDto;
	}

	@Override
	public PosizioneDebitoriaDto getPosizioneDebitoria(PosizioneDebitoriaRequestDto posizioneDebitoriaRequestDto) throws BusinessException{
		String methodName = "getPosizioneDebitoria";
		PosizioneDebitoriaDto resDto = null;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			resDto = gestioneFlussiDad.findPosizioneDebitoriaById ( posizioneDebitoriaRequestDto.getIdPosizioneDebitoria());

			logAuditOperation(CsiLogAuditOperationEnum.EXTRACT, posizioneDebitoriaRequestDto, "epaypa_t_posizione_debitoria", "ID POSIZIONE=" + posizioneDebitoriaRequestDto.getIdPosizioneDebitoria(), "Visualizzazione Dettaglio Posizione Debitoria");
		} catch ( Throwable t ) {
			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );
		} finally {
			log.info ( "result:" +  resDto );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
		return resDto;
	}

	@Override public PosizioneDebitoriaDto getPosizioneDebitoria ( PosizioneDebitoriaCaricaRequestDto posizioneDebitoriaCaricaRequestDto )
					throws BusinessException {
		String methodName = "getPosizioneDebitoria";
		PosizioneDebitoriaDto resDto = null;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			resDto = gestioneFlussiDad.findPosizioneDebitoriaByIdPosizioneDebitoriaEsternaAndIUV (
							posizioneDebitoriaCaricaRequestDto.getPosizioneDebitoriaEsterna (),
							posizioneDebitoriaCaricaRequestDto.getIuv (),
							posizioneDebitoriaCaricaRequestDto.getIdEnte (),
							posizioneDebitoriaCaricaRequestDto.getIdCov ()
							);

			logAuditOperation ( CsiLogAuditOperationEnum.EXTRACT, posizioneDebitoriaCaricaRequestDto, "epaypa_t_posizione_debitoria",
							"ID POSIZIONE ESTERNA=" + posizioneDebitoriaCaricaRequestDto.getPosizioneDebitoriaEsterna (), "Visualizzazione Dettaglio Posizione Debitoria" );
		} catch ( Throwable t ) {
			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );
		} finally {
			log.info ( "result:" + resDto );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
		return resDto;
	}

	@Override
	public Long countPosizioniDebitorieDaAggiornare ( Long idFLusso ) throws BusinessException {
		String methodName = "countPosizioniDebitorieDaAggiornare";
		
		

		Long num = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			num = gestioneFlussiDad.countAllPosizioneDebitoriaDaAggiornareByIdFlusso ( idFLusso );

		} catch ( Throwable t ) {
			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );

		} finally {
			log.info ( "result:" + num );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return num;
	}

	@Override
	//@formatter:off
	public List<PosizioneDebitoriaDaAggiornareLightDto> getPosizioneDebitoriaDaAggiornareLightList ( Long idFlusso,
		List<CriterioOrdinamentoDto<ColumnNameAggPosizioneDebitoriaEnum>> ord, PaginazioneDto pag )
						throws BusinessException {
		//@formatter:on
		String methodName = "getPosizioneDebitoriaDaAggiornareLightList";
		
		
		
		

		List<PosizioneDebitoriaDaAggiornareLightDto> resList = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			resList = gestioneFlussiDad.findAllPosizioneDebitoriaDaAggiornareLightByIdFlusso ( idFlusso, ord, pag );

		} catch ( Throwable t ) {
			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );

		} finally {
			log.info ( "result: " + resList );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return resList;
	}

	@Override
	//@formatter:off
	public TotalSizeAndLightListDto<PosizioneDebitoriaDaAggiornareLightDto> getTotalSizeAndPosizioneDebitoriaDaAggiornareLightList ( Long idFlusso,
		List<CriterioOrdinamentoDto<ColumnNameAggPosizioneDebitoriaEnum>> ord, PaginazioneDto pag )
						throws BusinessException {
		String methodName = "getTotalSizeAndPosizioneDebitoriaDaAggiornareLightList";
		
		
		
		

		TotalSizeAndLightListDto<PosizioneDebitoriaDaAggiornareLightDto> resDto = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			// conteggia e ricerca
			resDto = new TotalSizeAndLightListDto<> (
							gestioneFlussiDad.countAllPosizioneDebitoriaDaAggiornareByIdFlusso ( idFlusso ),
							gestioneFlussiDad.findAllPosizioneDebitoriaDaAggiornareLightByIdFlusso ( idFlusso, ord, pag ) );

		} catch ( Throwable t ) {
			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );

		} finally {
			log.info ( "result:" +  resDto );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return resDto;
	}
	//@formatter:on

	@Override
	public PosizioneDebitoriaDaAggiornareLightDto getPosizioneDebitoriaDaAggiornareLight ( Long idPosizioneDebitoriaDaAggiornare ) throws BusinessException {
		String methodName = "getPosizioneDebitoriaDaAggiornareLight";
		
		

		PosizioneDebitoriaDaAggiornareLightDto resDto = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			resDto = gestioneFlussiDad.findPosizioneDebitoriaDaAggiornareLightById ( idPosizioneDebitoriaDaAggiornare );

		} catch ( Throwable t ) {
			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );

		} finally {
			log.info ( "result:" +  resDto );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return resDto;
	}

	@Override
	public PosizioneDebitoriaDaAggiornareDto getPosizioneDebitoriaDaAggiornare ( PosizioneDebitoriaDaAggiornareRequestDto posizioneDebitoriaDaAggiornareRequestDto) throws BusinessException {
		String methodName = "getPosizioneDebitoriaDaAggiornare";
		
		

		PosizioneDebitoriaDaAggiornareDto resDto = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			resDto = gestioneFlussiDad.findPosizioneDebitoriaDaAggiornareById ( posizioneDebitoriaDaAggiornareRequestDto.getIdPosizioneDebitoriaDaAggiornare() );
			logAuditOperation(CsiLogAuditOperationEnum.EXTRACT, posizioneDebitoriaDaAggiornareRequestDto, "epaypa_t_agg_posizione_debitoria", 
				"ID =" + posizioneDebitoriaDaAggiornareRequestDto.getIdPosizioneDebitoriaDaAggiornare(), "Visualizzazione posizione debitoria da aggiornare");


		} catch ( Throwable t ) {
			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );

		} finally {
			log.info ( "result:" +  resDto );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return resDto;
	}

	@Override
	public EsitoAvvisoPagamentoDto getAvvisoPagamento(AvvisoPagamentoRequestDto avvisoPagamentoRequestDto) throws BusinessException{	
		String methodName = "getAvvisoPagamento";
		
		

		EsitoAvvisoPagamentoDto resDto = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			// ottiene il dettaglio della posizione debitoria per preparare la richiesta per il servizio
			PosizioneDebitoriaDto posdeb = getPosizioneDebitoria ( new PosizioneDebitoriaRequestDto(avvisoPagamentoRequestDto.getIpAddress(),avvisoPagamentoRequestDto.getIdUtente(),avvisoPagamentoRequestDto.getCodiceFiscaleUtente(),avvisoPagamentoRequestDto.getCodiceApplicazione(),avvisoPagamentoRequestDto.getIdPosizioneDebitoria()) );

			if ( posdeb.getIUV () == null ) {
				resDto = new EsitoAvvisoPagamentoDto ( "400", "IUV ancora non assegnato, non e' possibile eseguire la stampa" );
			}

			RichiestaAvvisoPagamentoDto request = new RichiestaAvvisoPagamentoDto ();
			request.setCodAvviso ( posdeb.getCodAvviso () );
			request.setIuv ( posdeb.getIUV () );

			AvvisoPagamentoImpl serviceMgr = new AvvisoPagamentoImpl ( avvisoPagamentoRequestDto.getServiceEndpoint (), avvisoPagamentoRequestDto.getAuth () );
			resDto = serviceMgr.getAvvisoPagamento ( request );

		} catch ( Throwable t ) {
			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );
		} finally {
			log.info ( "result:" +  resDto );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return resDto;
	}

	@Override
	public Long countAvvisiScaduti ( Long idFLusso ) throws BusinessException {
		String methodName = "countAvvisiScaduti";
		
		

		Long num = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			num = gestioneFlussiDad.countAllAvvisoScadutoByIdFlusso ( idFLusso );

		} catch ( Throwable t ) {
			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );

		} finally {
			log.info ( "result:" + num );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return num;
	}

	@Override
	//@formatter:off
	public List<AvvisoScadutoDto> getAvvisoScadutoList ( Long idFlusso, List<CriterioOrdinamentoDto<ColumnNameAvvisoScadutoEnum>> ord, PaginazioneDto pag )
					throws BusinessException {
		//@formatter:on
		String methodName = "getAvvisoScadutoList";
		
		
		
		

		List<AvvisoScadutoDto> resList = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			resList = gestioneFlussiDad.findAllAvvisoScadutoByIdFlusso ( idFlusso, ord, pag );

		} catch ( Throwable t ) {
			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );

		} finally {
			log.info ( "result: " + resList );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return resList;
	}

	@Override
	//@formatter:off
	public TotalSizeAndLightListDto<AvvisoScadutoDto> getTotalSizeAndAvvisoScadutoList ( Long idFlusso,
		List<CriterioOrdinamentoDto<ColumnNameAvvisoScadutoEnum>> ord, PaginazioneDto pag )
						throws BusinessException {
		String methodName = "getTotalSizeAndAvvisoScadutoList";
		
		
		
		

		TotalSizeAndLightListDto<AvvisoScadutoDto> resDto = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			// conteggia e ricerca
			resDto = new TotalSizeAndLightListDto<> (
							gestioneFlussiDad.countAllAvvisoScadutoByIdFlusso ( idFlusso ),
							gestioneFlussiDad.findAllAvvisoScadutoByIdFlusso ( idFlusso, ord, pag ) );

		} catch ( Throwable t ) {
			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );

		} finally {
			log.info ( "result:" +  resDto );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return resDto;
	}
	//@formatter:on

	@Override
	public AvvisoScadutoDto getAvvisoScaduto ( Long idAvvisoScaduto ) throws BusinessException {
		String methodName = "getAvvisoScaduto";
		
		

		AvvisoScadutoDto resDto = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			resDto = gestioneFlussiDad.findAvvisoScadutoById ( idAvvisoScaduto );

		} catch ( Throwable t ) {
			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );

		} finally {
			log.info ( "result:" +  resDto );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return resDto;
	}

	@Override
	public Long countRiversamenti ( Long idFLusso ) throws BusinessException {
		String methodName = "countRiversamenti";
		
		

		Long num = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			num = gestioneFlussiDad.countAllRiversamentoByIdFlusso ( idFLusso );

		} catch ( Throwable t ) {
			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );

		} finally {
			log.info ( "result:" + num );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return num;
	}

	@Override
	//@formatter:off
	public List<RiversamentoDto> getRiversamentoList ( Long idFlusso, List<CriterioOrdinamentoDto<ColumnNameRiversamentoEnum>> ord, PaginazioneDto pag )
					throws BusinessException {
		//@formatter:on
		String methodName = "getRiversamentoList";
		
		
		
		

		List<RiversamentoDto> resList = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			resList = gestioneFlussiDad.findAllRiversamentoByIdFlusso ( idFlusso, ord, pag );

		} catch ( Throwable t ) {
			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );

		} finally {
			log.info ( "result: " + resList );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return resList;
	}

	@Override
	//@formatter:on
	public TotalSizeAndLightListDto<RiversamentoDto> getTotalSizeAndRiversamentoList ( Long idFlusso,
		List<CriterioOrdinamentoDto<ColumnNameRiversamentoEnum>> ord,
		PaginazioneDto pag ) throws BusinessException {
		String methodName = "getTotalSizeAndRiversamentoList";
		
		
		
		

		TotalSizeAndLightListDto<RiversamentoDto> resDto = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			// conteggia e ricerca
			resDto = new TotalSizeAndLightListDto<> ( gestioneFlussiDad.countAllRiversamentoByIdFlusso ( idFlusso ),
							gestioneFlussiDad.findAllRiversamentoByIdFlusso ( idFlusso, ord, pag ) );

		} catch ( Throwable t ) {
			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );

		} finally {
			log.info ( "result:" +  resDto );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return resDto;
	}
	//@formatter:on

	@Override
	public RiversamentoDto getRiversamento ( Long idRiversamento ) throws BusinessException {
		String methodName = "getRiversamento";
		
		

		RiversamentoDto resDto = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			resDto = gestioneFlussiDad.findRiversamentoById ( idRiversamento );

		} catch ( Throwable t ) {
			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );

		} finally {
			log.info ( "result:" +  resDto );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return resDto;
	}

	@Override
	public boolean eliminaFlusso(EliminaFlussoRequestDto eliminaFlussoRequestDto) throws BusinessException{
		//public boolean eliminaFlusso ( Long idFlusso ) throws BusinessException {
		String methodName = "eliminaFlusso";
		
		

		boolean removeDone = false;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			transaction.begin ();

			if ( ! ( removeDone = gestioneFlussiDad.removeFlussoById ( eliminaFlussoRequestDto.getIdFlusso() ) ) ) {
				log.warn ( "errore o elemento da rimuovere non trovato per idFlusso:" + eliminaFlussoRequestDto.getIdFlusso() );
				throw new SkipException ();
			}

			transaction.commit ();
			logAuditOperation(CsiLogAuditOperationEnum.DELETE, eliminaFlussoRequestDto, 
				"epaypa_t_flusso", "ID FLUSSO=" + eliminaFlussoRequestDto.getIdFlusso(), 
				(StringUtils.isNotEmpty(eliminaFlussoRequestDto.getTipoFlusso()) && eliminaFlussoRequestDto.getTipoFlusso().equals("AGPD") )?"Cancellazione Flusso Aggiornamento Posizioni Debitorie": "Cancellazione Flusso Posizioni Debitorie");

		} catch ( Throwable t ) {
			try {
				transaction.rollback ();

			} catch ( IllegalStateException | SecurityException | SystemException e ) {
				throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );
			}
			eventuallyThrowBusinessException ( t, methodName );

		} finally {
			log.info ( "result:" + removeDone );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return removeDone;
	}

	@Override
	public boolean eliminaPosizioneDebitoria(EliminaPosizioneDebitoriaRequestDto eliminaPosizioneDebitoriaRequestDto) throws BusinessException{
		//public boolean eliminaPosizioneDebitoria ( Long idFlusso, Long idPosizioneDebitoria ) throws BusinessException {
		String methodName = "eliminaPosizioneDebitoria";
		
		
		


		boolean removeDone = false;
		boolean removeFlusso = false;
		boolean removePosizioneDebitoria = false;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			// ottiene il flusso
			FlussoLightDto flussoDto = getFlussoLight ( eliminaPosizioneDebitoriaRequestDto.getIdFlusso() );

			transaction.begin ();

			// logica dell'elimina
			switch ( flussoDto.getNumeroElementi () ) {
			case 0 :
				// N.B. caso impossibile
				log.error ( "flusso idFlusso:" + eliminaPosizioneDebitoriaRequestDto.getIdFlusso() + " senza posizioni debitorie" );
				throw new BusinessException ( "Flusso (idLotto: " + flussoDto.getIdMessaggio () + " senza posizioni debitorie" );

			case 1 :
				// elimina l'intero flusso dato che non puo esistere senza posizioni
				if ( ! ( removeDone = gestioneFlussiDad.removeFlussoById ( eliminaPosizioneDebitoriaRequestDto.getIdFlusso() ) ) ) {
					log.warn ( "errore o elemento da rimuovere non trovato per idFlusso:" + eliminaPosizioneDebitoriaRequestDto.getIdFlusso() );
					throw new SkipException ();
				}
				removeFlusso=removeDone;
				removePosizioneDebitoria= removeDone;
				break;

			default :
				// elimina la singola posizione e aggiorna i totali nella testata
				PosizioneDebitoriaDto posizioneRimossa = gestioneFlussiDad.removePosizioneDebitoriaById ( eliminaPosizioneDebitoriaRequestDto.getIdPosizioneDebitoria() );
				if ( removeDone = ( posizioneRimossa != null ) ) {
					FlussoDto newFlussoDto = new FlussoDto ( eliminaPosizioneDebitoriaRequestDto.getIdFlusso() );
					newFlussoDto.setNumeroElementi ( flussoDto.getNumeroElementi () - 1 );
					newFlussoDto.setImportoTotale ( flussoDto.getImportoTotale ().subtract ( posizioneRimossa.getImportoTotale () ) );
					if ( !gestioneFlussiDad.updateFlusso ( newFlussoDto ) ) {
						log.error ( "mancato aggiornamento dell'importo totale e del numero totale degli elementi del flusso idFlusso:" + eliminaPosizioneDebitoriaRequestDto.getIdFlusso() );
					}

				} else {
					log.error ( "errore o elemento da rimuovere non trovato per idPosizioneDebitoria:" + eliminaPosizioneDebitoriaRequestDto.getIdPosizioneDebitoria() );
				}
				removePosizioneDebitoria= removeDone;
				break;
			}

			transaction.commit ();

			//Log audit
			if(removePosizioneDebitoria) {
				logAuditOperation(CsiLogAuditOperationEnum.DELETE, eliminaPosizioneDebitoriaRequestDto, "epaypa_t_posizione_debitoria", "ID POSIZIONE=" + eliminaPosizioneDebitoriaRequestDto.getIdPosizioneDebitoria(), "Cancellazione Posizione Debitoria");
			}
			if(removeFlusso) {
				logAuditOperation(CsiLogAuditOperationEnum.DELETE, eliminaPosizioneDebitoriaRequestDto, "epaypa_t_flusso", "ID FLUSSO=" + eliminaPosizioneDebitoriaRequestDto.getIdFlusso(), "Cancellazione Flusso Posizioni Debitorie"); 
			}
		} catch ( Throwable t ) {
			try {
				transaction.rollback ();

			} catch ( IllegalStateException | SecurityException | SystemException e ) {
				throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );
			}
			eventuallyThrowBusinessException ( t, methodName );

		} finally {
			log.info ( "result:" + removeDone );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return removeDone;
	}

	@Override
	public boolean eliminaPosizioneDebitoriaDaAggiornare(EliminaPosizioneDebitoriaDaAggiornareRequestDto eliminaPosizioneDebitoriaDaAggiornareRequestDto) throws BusinessException{
		//public boolean eliminaPosizioneDebitoriaDaAggiornare ( Long idFlusso, Long idPosizioneDebitoriaDaAggiornare ) throws BusinessException {
		String methodName = "eliminaPosizioneDebitoriaDaAggiornare";
		
		
		

		boolean removeDone = false;
		boolean removeFlusso = false;
		boolean removePosizioneDebitoria = false;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			// ottiene il flusso
			FlussoLightDto flussoDto = getFlussoLight ( eliminaPosizioneDebitoriaDaAggiornareRequestDto.getIdFlusso() );

			transaction.begin ();

			// logica dell'elimina
			switch ( flussoDto.getNumeroElementi () ) {
			case 0 :
				// N.B. caso impossibile
				log.error ( "flusso idFlusso:" + eliminaPosizioneDebitoriaDaAggiornareRequestDto.getIdFlusso() + " senza posizioni debitorie da aggiornare" );
				throw new BusinessException ( "Flusso (idLotto: " + flussoDto.getIdMessaggio () + " senza posizioni debitorie da aggiornare" );

			case 1 :
				// elimina l'intero flusso dato che non puo esistere senza posizioni
				if ( ! ( removeDone = gestioneFlussiDad.removeFlussoById ( eliminaPosizioneDebitoriaDaAggiornareRequestDto.getIdFlusso() ) ) ) {
					log.warn ( "errore o elemento da rimuovere non trovato per idFlusso:" + eliminaPosizioneDebitoriaDaAggiornareRequestDto.getIdFlusso() );
					throw new SkipException ();
				}
				removeFlusso=removeDone;
				removePosizioneDebitoria= removeDone;
				break;

			default :
				// elimina la singola posizione e aggiorna i totali nella testata
				PosizioneDebitoriaDaAggiornareDto posizioneRimossa
				= gestioneFlussiDad.removePosizioneDebitoriaDaAggiornareById ( eliminaPosizioneDebitoriaDaAggiornareRequestDto.getIdPosizioneDebitoria() );
				if ( removeDone = ( posizioneRimossa != null ) ) {
					FlussoDto newFlussoDto = new FlussoDto ( eliminaPosizioneDebitoriaDaAggiornareRequestDto.getIdFlusso() );
					newFlussoDto.setNumeroElementi ( flussoDto.getNumeroElementi () - 1 );
					if ( !gestioneFlussiDad.updateFlusso ( newFlussoDto ) ) {
						log.error ( "mancato aggiornamento del numero totale degli elementi del flusso idFlusso:" + eliminaPosizioneDebitoriaDaAggiornareRequestDto.getIdFlusso() );
					}

				} else {
					log.error ( "errore o elemento da rimuovere non trovato per idPosizioneDebitoriaDaAggiornare:" + eliminaPosizioneDebitoriaDaAggiornareRequestDto.getIdPosizioneDebitoria() );
				}
				removePosizioneDebitoria= removeDone;
				break;
			}

			transaction.commit ();

			//Log audit
			if(removePosizioneDebitoria) {
				logAuditOperation(CsiLogAuditOperationEnum.DELETE, eliminaPosizioneDebitoriaDaAggiornareRequestDto, "epaypa_t_posizione_debitoria", "ID=" + eliminaPosizioneDebitoriaDaAggiornareRequestDto.getIdPosizioneDebitoria(), "Cancellazione Posizione Debitoria");
			}
			if(removeFlusso) {
				logAuditOperation(CsiLogAuditOperationEnum.DELETE, eliminaPosizioneDebitoriaDaAggiornareRequestDto, "epaypa_t_flusso", "ID FLUSSO=" + eliminaPosizioneDebitoriaDaAggiornareRequestDto.getIdFlusso(), "Cancellazione Flusso"); 
			}
		} catch ( Throwable t ) {
			try {
				transaction.rollback ();

			} catch ( IllegalStateException | SecurityException | SystemException e ) {
				throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );
			}
			eventuallyThrowBusinessException ( t, methodName );

		} finally {
			log.info ( "result:" + removeDone );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return removeDone;
	}

	@Override
	public EsitoDto inviaListadicarico(InviaListadicaricoRequestDto inviaListadicaricoRequestDto) throws BusinessException{
		//public EsitoDto inviaListadicarico ( Long idFlusso, String codUtente, String serviceEndpoint ) throws BusinessException {
		String methodName = "inviaListadicarico";
		
		
		
		

		EsitoDto resDto = null;
		FlussoCompletoDto<PosizioneDebitoriaDto> flussoDto = null;

		// ottiene la lista di carico da inviare
		flussoDto = getFlussoPosizioniDebitorie ( new FlussoPosizioniDebitorieRequestDto(inviaListadicaricoRequestDto.getIpAddress(), inviaListadicaricoRequestDto.getIdUtente(),inviaListadicaricoRequestDto.getCodiceFiscaleUtente(),inviaListadicaricoRequestDto.getCodiceApplicazione() ,inviaListadicaricoRequestDto.getIdFlusso()), false );

		try {
			// invoca il servizio di inserimento
			Enti2EPaywsoServiceMgr serviceMgr = new Enti2EPaywsoServiceMgrImpl ( inviaListadicaricoRequestDto.getServiceEndpoint() );
			resDto = serviceMgr.inserisciListaCarico ( flussoDto );

		} catch ( Throwable t ) {
			resDto = new EsitoDto ( "200", buildErrorMessage ( "Non e' stato possibile inviare il flusso", t.getMessage (), methodName ) );
		}

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			transaction.begin ();

			// aggiorna il flusso in base all'esito
			String idMessaggio = flussoDto.getFlusso ().getIdMessaggio ();
			if ( !updateEsitoInvioListadicarico ( inviaListadicaricoRequestDto.getIdFlusso(), idMessaggio, inviaListadicaricoRequestDto.getCodiceFiscaleUtente(), resDto ) ) {
				throw new SkipException ();
			}

			transaction.commit ();

		} catch ( Throwable t ) {
			try {
				transaction.rollback ();

			} catch ( IllegalStateException | SecurityException | SystemException e ) {
				throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );
			}
			eventuallyThrowBusinessException ( t, methodName );

		} finally {
			log.info ( "result:" +  resDto );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return resDto;
	}

	@Override
	public EsitoDto inviaFlussoAggiornamentoPosizioniDebitorie ( Long idFlusso, String codUtente, String serviceEndpoint ) throws BusinessException {
		String methodName = "inviaFlussoAggiornamentoPosizioniDebitorie";
		
		
		
		

		EsitoDto resDto = null;
		FlussoCompletoDto<PosizioneDebitoriaDaAggiornareDto> flussoDto = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			transaction.begin ();

			// ottiene la lista di aggiornamento delle posizioni debitorie
			flussoDto = getFlussoPosizioniDebitorieDaAggiornare ( idFlusso );

			try {
				// invoca il servizio di inserimento
				Enti2EPaywsoServiceMgr serviceMgr = new Enti2EPaywsoServiceMgrImpl ( serviceEndpoint );
				resDto = serviceMgr.aggiornaPosizioniDebitorie ( flussoDto );

			} catch ( Throwable t ) {
				resDto = new EsitoDto ( "200", buildErrorMessage ( "Non e' stato possibile inviare il flusso", t.getMessage (), methodName ) );
			}

			// aggiorna il flusso in base all'esito
			String idMessaggio = flussoDto.getFlusso ().getIdMessaggio ();
			if ( !updateEsitoInvioListadicarico ( idFlusso, idMessaggio, codUtente, resDto ) ) {
				throw new SkipException ();
			}

			transaction.commit ();

		} catch ( Throwable t ) {
			try {
				transaction.rollback ();

			} catch ( IllegalStateException | SecurityException | SystemException e ) {
				throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );
			}
			eventuallyThrowBusinessException ( t, methodName );

		} finally {
			log.info ( "result:" +  resDto );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return resDto;
	}

	private boolean updateEsitoInvioListadicarico ( Long idFlusso, String idMessaggio, String codUtente, EsitoDto esito )
					throws BusinessException {
		// costruisce l'oggetto flusso coi dati da aggiornare
		Date dataInoltroEffettuato = new Date ();
		FlussoDto flussoDto = new FlussoDto ( idFlusso );
		flussoDto.setCodEsito ( esito.getCodEsito () );
		String desEsito = esito.getDesEsito ();
		if ( desEsito != null && desEsito.length () > 200 ) {
			desEsito = desEsito.substring ( 0, 200 );
		}
		flussoDto.setDetEsito ( desEsito );
		flussoDto.setDataInoltro ( dataInoltroEffettuato );
		flussoDto.setDataUltimaVariazione ( dataInoltroEffettuato );
		flussoDto.setUtenteUltimaVariazione ( codUtente );
		String codEsito = esito.getCodEsito ();
		if ( codEsito != null ) {
			if ( Integer.valueOf ( codEsito ) < 100 ) {
				flussoDto.setStatoFlusso ( StatoFlussoEnum.INVIATO );
			} else if ( Integer.valueOf ( codEsito ) < 300 ) {
				flussoDto.setStatoFlusso ( StatoFlussoEnum.ERRORE_IN_FASE_DI_INVIO );
				flussoDto.setIdMessaggio ( Util.buildIdMessaggioIncrementato ( idMessaggio ) );
			}
		}

		// aggiorna
		boolean updateDone = false;
		try {
			if ( ! ( updateDone = gestioneFlussiDad.updateFlusso ( flussoDto ) ) ) {
				log.warn ( "errore o elemento da aggiornare non trovato per idFlusso:" + flussoDto.getId () );
				throw new BusinessException ( "errore o elemento da aggiornare non trovato per idFlusso:" + flussoDto.getId () );
			}

		} catch ( Throwable t ) {
			if ( t instanceof BusinessException ) {
				throw (BusinessException) t;
			}

			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::updateEsitoInvioListadicarico", t );
		}

		return updateDone;
	}

	private String buildErrorMessage ( String genericMessage, String exceptionMessage, String methodName ) {
		String desEsito = genericMessage;
		if ( exceptionMessage != null ) {
			desEsito += ", si e' verificato l'errore: " + exceptionMessage;
		} else {
			desEsito += ", si e' verificato un errore imprevisto";
		}
		desEsito += " in " + CLASSNAME + "::" + methodName;
		return desEsito;
	}

	@Override
	//@formatter:off
	public List<ParseResultDto> inserisciListadicarico (InserisciListadicaricoRequestDto inserisciListadicaricoRequestDto) throws BusinessException{
		String methodName = "inserisciListadicarico";
		
		
		

		List<ParseResultDto> parseResultList = new ArrayList<> ();
		Map<ParseErrorEnum, List<ParseErrorFieldDto>> parseErrorFieldMap = new Hashtable<> ();

		FlussoCompletoDto<PosizioneDebitoriaDto> flussoCompleto = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			List<PosizioneDebitoriaDto> posdebList = null;

			checkParamsForLinesNotNull ( inserisciListadicaricoRequestDto.getParams(), inserisciListadicaricoRequestDto.getLines(), methodName );

			posdebList = new ArrayList<> ();
			BigDecimal sommaImportiPosizioniDebitorie = BigDecimal.valueOf ( 0.0 ).setScale ( 2 );

			switch ( inserisciListadicaricoRequestDto.getLines().size () ) {
			case 0 :
				addParseErrorFieldToMap ( PARSE_ERROR_FILE_VUOTO, null, null, null, null, parseErrorFieldMap );
				break;
			case 1 :
				addParseErrorFieldToMap ( PARSE_ERROR_ASSENZA_DATI, null, null, null, null, parseErrorFieldMap );
				break;
			default :
				/* DEV/CSI_PAG-2411 - BEGIN2 */
				ParametriInserimentoFlussoDto params = inserisciListadicaricoRequestDto.getParams ();
				/* DEV/CSI_PAG-2411 - END2 */

				int numeroPosizioniDebitoriePresenti = inserisciListadicaricoRequestDto.getLines().size () - 1; // N.B. il -1 sottrae la riga riservata alla intestazione
				if ( numeroPosizioniDebitoriePresenti > 0
								&& checkForNumeroPosizioniDebitorie ( inserisciListadicaricoRequestDto.getParams().getNumeroPosizioniDebitorie (), numeroPosizioniDebitoriePresenti, parseErrorFieldMap )
								//EPAY-48
								// controllo che siano presenti solo posizioni debitorie univoche
								/* DEV/CSI_PAG-2411 - BEGIN3 */
								// non serve piu fare questo controllo perche la posizione debitoria viene ora generata automaticamente al volo
//								&& checkPosizioneDebitoriaUnivoca ( inserisciListadicaricoRequestDto.getLines(), parseErrorFieldMap,
//								inserisciListadicaricoRequestDto.getTipoFormato() )
								) {
								/* DEV/CSI_PAG-2411 - END3 */

					final int NUM_COLUMNS = 52; // per v2.9 vs 53 per v2.8
					List<Object> line0 = inserisciListadicaricoRequestDto.getLines().get ( 0 );
					if ( checkIntestazione ( line0.size (), NUM_COLUMNS, 0, parseErrorFieldMap ) ) { // NB. controlla l'intestazione che deve esserci sempre
						BigDecimal impSecTot = new BigDecimal ( 0 );
						for ( int row = 1, nrows = numeroPosizioniDebitoriePresenti; row <= nrows; row++ ) { // NB. salta la prima riga (la zero), riservata all'intestazione
							log.debug ( "LINE:" + row );
							List<Object> line = inserisciListadicaricoRequestDto.getLines().get ( row );
							if ( line != null ) {
							    int lineSize = line.size ();
							    checkNumeroMaxColonne ( lineSize, NUM_COLUMNS, row, parseErrorFieldMap );
							    PosizioneDebitoriaDto posdeb = new PosizioneDebitoriaDto ();

							    // verifiche puntuali sui campi della posizione debitoria
								/* DEV/CSI_PAG-2411 - BEGIN4 */
							    posdeb.setIdPosizioneDebitoriaEsterna ( Util.buildIdPosioneDebitoriaEsterna( params.getCodFiscaleEnte (), params.getIdUtente () ) );
							    posdeb.setAnnoRiferimento (
							    	checkForInteger ( line, row, 0, "Anno di riferimento", false, 1900, 2900, parseErrorFieldMap ) );
							    BigDecimal importoTotale = checkForBigDecimal ( line, row, 1, "Importo totale da pagare", true, BigDecimal.valueOf ( 0.0 ),
							        new BigDecimal ( NINE_NINE ), parseErrorFieldMap );
							    posdeb.setImportoTotale ( importoTotale );
							    posdeb.setDataScadenza ( checkForDate ( line, row, 2, "Data scadenza", false, parseErrorFieldMap ) );
							    posdeb.setDataInizioValidita ( inserisciListadicaricoRequestDto.getParams().getDataInizioValidita () );
							    posdeb.setDataFineValidita ( inserisciListadicaricoRequestDto.getParams().getDataFineValidita () );
							    posdeb.setDesCausaleVersamento (
							        checkForString ( line, row, 3, "Descrizione causale versamento", true, 100, parseErrorFieldMap ) );
							    posdeb.setDesRata ( checkForString ( line, row, 4, "Descrizione rata", false, 16, parseErrorFieldMap ) );
							    // verifiche puntuali sui campi della componente importo, per ogni componente devono essere valorizzati i suoi tre campi o nessuno
							    posdeb.setComponenteImportoDtoList ( checkForComponentiImporto ( line, row, lineSize, 5, 24, parseErrorFieldMap ) );
							    posdeb.setRiferimentoPagamentoDtoList ( checkForRiferimentiPagamento ( line, row, lineSize, 25, 34, parseErrorFieldMap ) );
							    // verifiche puntuali sui campi del soggetto pagatore
							    SoggettoAnagraficoDto soggetto = new SoggettoAnagraficoDto ();
							    soggetto.setTipologiaSoggettoEnum (
							        checkForTipologiaSoggetto ( line, row, 35, "Tipo soggetto debitore", true, parseErrorFieldMap ) );
							    soggetto.setIdUnivocoFiscale (
							        checkForString ( line, row, 36, "Codice fiscale o Partita Iva del soggetto debitore", true, 30, parseErrorFieldMap ) );
							    checkValueAsAnonimo ( soggetto.getIdUnivocoFiscale (), posdeb.getRiferimentoPagamentoDtoList (), row,
							        36, "Codice fiscale o Partita Iva del soggetto debitore", parseErrorFieldMap );
							    soggetto.setCognome ( checkForCognomeNomeSoggetto ( line, row, 37, "Cognome soggetto debitore",
							        soggetto.getTipologiaSoggettoEnum (), parseErrorFieldMap ) );
							    checkValueAsAnonimo ( soggetto.getCognome (), posdeb.getRiferimentoPagamentoDtoList (), row,
							        37, "Cognome soggetto debitore", parseErrorFieldMap );
							    soggetto
							    .setNome ( checkForCognomeNomeSoggetto ( line, row, 38, "Nome soggetto debitore", soggetto.getTipologiaSoggettoEnum (),
												parseErrorFieldMap ) );
							    checkValueAsAnonimo ( soggetto.getNome (), posdeb.getRiferimentoPagamentoDtoList (), row,
							        38, "Nome soggetto debitore", parseErrorFieldMap );
							    soggetto.setRagioneSociale ( checkForRagioneSocialeSoggetto ( line, row, 39, "Ragione sociale soggetto debitore",
							        soggetto.getTipologiaSoggettoEnum (), 70, parseErrorFieldMap ) );
							    checkValueAsAnonimo ( soggetto.getRagioneSociale (), posdeb.getRiferimentoPagamentoDtoList (), row,
							        39, "Ragione sociale soggetto debitore", parseErrorFieldMap );
							    soggetto.setIndirizzo ( checkForString ( line, row, 40, "Indirizzo debitore", false, 70, parseErrorFieldMap ) );
							    soggetto.setCivico ( checkForString ( line, row, 41, "Civico debitore", false, 16, parseErrorFieldMap ) );
							    soggetto.setCap ( checkForString ( line, row, 42, "CAP debitore", false, 16, parseErrorFieldMap ) );
							    soggetto.setLocalita ( checkForString ( line, row, 43, "Localita' debitore", false, 35, parseErrorFieldMap ) );
							    soggetto.setProvincia ( checkForString ( line, row, 44, "Provincia debitore", false, 35, parseErrorFieldMap ) );
								String nB = checkForString ( line, row, 45, "Nazione debitore", false, 2, parseErrorFieldMap ).toUpperCase ();
							    soggetto.setNazione ( nB );
							    soggetto.setEmail ( checkForEMail ( line, row, 46, "Email debitore", false, 256, parseErrorFieldMap ) );
							    posdeb.setSoggettoDebitore ( soggetto );
							    posdeb.setNotePerPagatore ( checkForString ( line, row, 47, "Note per il pagatore", false, 1000, parseErrorFieldMap ) );
							    // gestione nuova multibeneficiario
							    BigDecimal impTotPrin = ( checkForBigDecimal ( line, row, 48, "Importo totale principale",inserisciListadicaricoRequestDto.getParams().isMultibeneficiario (), BigDecimal.valueOf ( 0.0 ),
							        new BigDecimal ( NINE_NINE ), parseErrorFieldMap ) );
							    posdeb.setImportoPrincipale ( impTotPrin );
							    impSecTot = checkForBigDecimal ( line, row, 49, "Importo totale secondario", inserisciListadicaricoRequestDto.getParams().isMultibeneficiario (), BigDecimal.valueOf ( 0.0 ),
							        new BigDecimal ( NINE_NINE ), parseErrorFieldMap );
							    BigDecimal impSecComp = checkForBigDecimal ( line, row, 50, "Importo componente secondario", inserisciListadicaricoRequestDto.getParams().isMultibeneficiario (), BigDecimal.valueOf ( 0.0 ),
							        new BigDecimal ( NINE_NINE ), parseErrorFieldMap );
							    posdeb.setImportoSecondarioAltroEnte ( impSecTot );
								checkForImportoTotaleSecondario ( impSecTot, impSecComp, parseErrorFieldMap, row );
							    checkForImportoTotaleMulti ( importoTotale, impTotPrin, impSecTot, parseErrorFieldMap, row );
							    ComponenteImportoDto secondario= checkForComponenteImportoSecondario( line, row, 50, parseErrorFieldMap ) ;
								/* DEV/CSI_PAG-2411 - END4 */
							    if (null!= secondario)
							    {
							        if (CollectionUtils.isEmpty ( posdeb.getComponenteImportoDtoList () )) {
							            posdeb.setComponenteImportoDtoList ( new LinkedList<ComponenteImportoDto> () );
							        }
                                    posdeb.getComponenteImportoDtoList ().add (secondario);
							    }

								checkImportiPrincipali ( posdeb, row, parseErrorFieldMap );

							    posdebList.add ( posdeb );

							    if ( posdeb.getImportoTotale () != null ) {
							        sommaImportiPosizioniDebitorie = sommaImportiPosizioniDebitorie.add ( posdeb.getImportoTotale () );
							    }
							}
						}
						checkForImportoTotalePosizioniDebitorie ( inserisciListadicaricoRequestDto.getParams().getTotaleImportoPosizioniDebitorie (), sommaImportiPosizioniDebitorie,
							parseErrorFieldMap );
					}
				}
				break;
			}

			if ( parseErrorFieldMap.size () > 0 ) {
				parseResultList = toParseResultList ( parseErrorFieldMap );

			} else {
				Timestamp timestampNow = getTimestampNow ();
				long millis = timestampNow.getTime ();
				Date dateNow = new Date ( millis );

				// inserimento con id messaggio temporaneo
				FlussoDto flusso = new FlussoDto ();
				flusso.setIdMessaggio ( Util.buildIdMessaggio ( dateNow, inserisciListadicaricoRequestDto.getParams().getCodVersamento (), millis ) );
				flusso.setTipoFlusso ( TipoFlussoEnum.LISTE_DI_CARICO );
				flusso.setStatoFlusso ( StatoFlussoEnum.IN_CORSO_DI_REDAZIONE );
				flusso.setUtenteInserimento ( inserisciListadicaricoRequestDto.getParams().getCodUtente () );
				flusso.setUtenteUltimaVariazione ( inserisciListadicaricoRequestDto.getParams().getCodUtente () );
				flusso.setCodFiscaleEnte ( inserisciListadicaricoRequestDto.getParams().getCodFiscaleEnte () );
				flusso.setCodVersamento ( inserisciListadicaricoRequestDto.getParams().getCodVersamento () );
				flusso.setNumeroElementi ( inserisciListadicaricoRequestDto.getParams().getNumeroPosizioniDebitorie () );
				flusso.setImportoTotale ( inserisciListadicaricoRequestDto.getParams().getTotaleImportoPosizioniDebitorie () );
				flusso.setPagamentiSpontanei ( false );
				//
				flussoCompleto = new FlussoCompletoDto<> ();
				flussoCompleto.setFlusso ( flusso );
				flussoCompleto.setItemList ( posdebList );
				//
				log.debug ( "listadicarico:" + flussoCompleto );

				try {
					transaction.begin ();

					// inserimento della lista di carico
					Long idFlusso = gestioneFlussiDad.insertFlussoPosizioniDebitorie ( flussoCompleto, timestampNow );

					// aggiorna l'id-messaggio con l'idFlusso reale
					FlussoDto flussoUpd = new FlussoDto ( idFlusso );
					flussoUpd.setIdMessaggio ( Util.buildIdMessaggio ( dateNow, inserisciListadicaricoRequestDto.getParams().getCodVersamento (), idFlusso ) );
					gestioneFlussiDad.updateFlusso ( flussoUpd );

					transaction.commit ();

					//Log audit
					logAuditOperation(CsiLogAuditOperationEnum.INSERT, inserisciListadicaricoRequestDto, "epaypa_t_flusso", "ID FLUSSO=" + idFlusso, "Inserimento Nuovo Flusso Posizioni Debitorie"); 

				} catch ( Throwable t ) {
					try {
						transaction.rollback ();

					} catch ( IllegalStateException | SecurityException | SystemException e ) {
						throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );
					}
					eventuallyThrowBusinessException ( t, methodName );
				}
			}
		} finally {
			log.info ( "result:" +  parseResultList );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return parseResultList;
	}
	//@formatter:on

	private void checkImportiPrincipali ( PosizioneDebitoriaDto posdeb, int row,
					Map<ParseErrorEnum, List<ParseErrorFieldDto>> parseErrorFieldMap ) {

		BigDecimal sommaImporti = new BigDecimal ( "0.0" );
		if (!CollectionUtils.isEmpty ( posdeb.getComponenteImportoDtoList () ))
		{
		    for ( ComponenteImportoDto ci: posdeb.getComponenteImportoDtoList () ) {
	            if (null!= ci &&  null != ci.getImporto () && ( ci.getFlagComponenteSecondario () == null || !ci.getFlagComponenteSecondario () ) ) {
	                sommaImporti = sommaImporti.add ( ci.getImporto () );
	            }
	        } 
		}
		
		if ( sommaImporti.compareTo ( posdeb.getImportoPrincipale ()!= null? posdeb.getImportoPrincipale ():new BigDecimal ( 0 ) ) != 0 ) {
			addParseErrorFieldToMap ( PARSE_ERROR_TOTALE_IMPORTO_POSIZIONI_DEBITORIE, row, null, "Importo principale: " + posdeb.getImportoPrincipale (),
				"Somma importi principali: " + sommaImporti + ", alla riga: " + row, parseErrorFieldMap );
		}
	}

	@Override
	//@formatter:off
	public List<ParseResultDto> inserisciFlussoPosizioniDebitorieDaAggiornare ( ParametriInserimentoFlussoDto params, List<List<Object>> lines )
					throws BusinessException {
		String methodName = "inserisciFlussoPosizioniDebitorieDaAggiornare";
		
		
		

		List<ParseResultDto> parseResultList = new ArrayList<> ();
		Map<ParseErrorEnum, List<ParseErrorFieldDto>> parseErrorFieldMap = new Hashtable<> ();

		FlussoCompletoDto<PosizioneDebitoriaDaAggiornareDto> flussoCompleto = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			List<PosizioneDebitoriaDaAggiornareDto> aggposdebList;

			checkParamsForLinesNotNull ( params, lines, methodName );

			aggposdebList = new ArrayList<> ();

			switch ( lines.size () ) {
			case 0 :
				addParseErrorFieldToMap ( PARSE_ERROR_FILE_VUOTO, null, null, null, null, parseErrorFieldMap );
				break;
			case 1 :
				addParseErrorFieldToMap ( PARSE_ERROR_ASSENZA_DATI, null, null, null, null, parseErrorFieldMap );
				break;
			default :
				int numeroPosizioniDebitoriePresenti = lines.size () - 1; // N.B. il -1 sottrae la riga riservata alla intestazione
				if ( numeroPosizioniDebitoriePresenti > 0
								&& checkForNumeroPosizioniDebitorie ( params.getNumeroPosizioniDebitorie (), numeroPosizioniDebitoriePresenti, parseErrorFieldMap ) ) {
					boolean altraGestione = params.getDataInizioValidita () == null;
					final int NUM_COLUMNS = altraGestione ? 54 : 51;//v2.7
					List<Object> line0 = lines.get ( 0 );
					if ( checkIntestazione ( line0.size (), NUM_COLUMNS, 0, parseErrorFieldMap ) ) { // NB. controlla l'intestazione che deve avere sempre
						BigDecimal impSecTot = new BigDecimal ( 0 );
						for ( int row = 1, nrows = numeroPosizioniDebitoriePresenti; row <= nrows; row++ ) { // NB. salta la prima riga (la zero), riservata all'intestazione
							log.debug ( "LINE:" + row );
							List<Object> line = lines.get ( row );
							if ( line != null ) {
								int lineSize = line.size ();
								checkNumeroMaxColonne ( line.size (), NUM_COLUMNS, row, parseErrorFieldMap );
								PosizioneDebitoriaDaAggiornareDto aggposdeb = new PosizioneDebitoriaDaAggiornareDto ();

								// verifiche puntuali sui campi della posizione debitoria
								TipoAggiornamentoPosizioneDebitoriaDto tipoAggiornamento
								= checkForTipoAggiornamentoPosizioneDebitoria ( line, row, 0, "Tipo aggiornamento", true, parseErrorFieldMap );
								aggposdeb.setTipoAggiornamento ( tipoAggiornamento );
								if ( tipoAggiornamento.getTipoAggiornamentoEnum () != null ) {
									aggposdeb.setIdPosizioneDebitoriaEsterna (
										checkForStringAndTrim ( line, row, 1, "Posizione debitoria esterna", true, 50, parseErrorFieldMap ) ); // N.B. necessario trim dato chee lato sportello fa il trim
									BigDecimal importoTotale = BigDecimal.valueOf ( 0.0 );
									switch ( tipoAggiornamento.getTipoAggiornamentoEnum () ) {
									case ANNULLAMENTO :
										checkForNotRequestedField ( line, row, 2, "Data scadenza", parseErrorFieldMap );
										checkForNotRequestedField ( line, row, 3, "Data inizio validita'", parseErrorFieldMap );
										checkForNotRequestedField ( line, row, 4, "Data fine validita'", parseErrorFieldMap );
										checkForNotRequestedField ( line, row, 5, "Importo totale da pagare", parseErrorFieldMap );
										for ( int i = 1, ic = 5; ic <= 25; i++, ic += 4 ) {
											checkForNotRequestedField ( line, row, ic, "Importo componente " + i, parseErrorFieldMap );
											checkForNotRequestedField ( line, row, ic + 1, "Causale componente " + i, parseErrorFieldMap );
											checkForNotRequestedField ( line, row, ic + 2, "Anno accertamento " + i, parseErrorFieldMap );
											checkForNotRequestedField ( line, row, ic + 3, "Numero accertamento " + i, parseErrorFieldMap );
										}
										for ( int i = 1, ic = 31; ic <= 35; i++, ic += 2 ) {
											checkForNotRequestedField ( line, row, ic, "Nome " + i, parseErrorFieldMap );
											checkForNotRequestedField ( line, row, ic + 1, "Valore " + i, parseErrorFieldMap );
										}
										checkForNotRequestedField ( line, row, 36, "Descrizione causale versamento", parseErrorFieldMap );
										break;
									case MODIFICA :
										boolean noValues = true;
										aggposdeb.setDataScadenza ( checkForDate ( line, row, 2, "Data scadenza", false, parseErrorFieldMap ) );
										noValues &= aggposdeb.getDataScadenza () == null;
										aggposdeb
											.setDataInizioValidita ( checkForDate ( line, row, 3, "Data inizio validita'", false, parseErrorFieldMap ) );
										noValues &= aggposdeb.getDataInizioValidita () == null;
										aggposdeb.setDataFineValidita ( checkForDate ( line, row, 4, "Data fine validita'", false, parseErrorFieldMap ) );
										noValues &= aggposdeb.getDataFineValidita () == null;
										importoTotale = checkForBigDecimal ( line, row, 5, "Importo totale da pagare", false, BigDecimal.valueOf ( 0.0 ),
											new BigDecimal ( NINE_NINE ), parseErrorFieldMap );
										aggposdeb.setImportoTotale ( importoTotale );
										noValues &= aggposdeb.getImportoTotale () == null;
										// verifiche puntuali sui campi della componente importo, per ogni componente devono essere valorizzati i suoi cinque campi o nessuno
										aggposdeb
										.setComponenteImportoDtoList ( checkForComponentiImporto ( line, row, lineSize, 6, 25, parseErrorFieldMap ) );
										noValues &= aggposdeb.getComponenteImportoDtoList ().size () == 0;
										// verifiche puntuali sui campi del riferimento pagamento, per ogni riferimento devono essere valorizzati i suoi 2 campi o nessuno
										aggposdeb
										.setRiferimentoPagamentoDtoList (
											checkForRiferimentiPagamento ( line, row, lineSize, 26, 35, parseErrorFieldMap ) );
										noValues &= aggposdeb.getRiferimentoPagamentoDtoList ().size () == 0;
										aggposdeb.setDesCausaleVersamento (
											checkForString ( line, row, 36, "Descrizione causale versamento", false, 100, parseErrorFieldMap ) );
										noValues &= aggposdeb.getDesCausaleVersamento () == null;

										if (checkForTipoSoggettoPresente(line, row,parseErrorFieldMap ))
										{
											SoggettoAnagraficoDto soggetto = new SoggettoAnagraficoDto ();
											soggetto.setTipologiaSoggettoEnum (
												checkForTipologiaSoggetto ( line, row, 38, "Tipo soggetto debitore", true, parseErrorFieldMap ) );
											soggetto.setIdUnivocoFiscale (
												checkForString ( line, row, 39, "Codice fiscale o Partita Iva del soggetto debitore", true, 35, parseErrorFieldMap ) );
											soggetto.setCognome ( checkForCognomeNomeSoggetto ( line, row, 40, "Cognome del soggetto debitore",
												soggetto.getTipologiaSoggettoEnum (), parseErrorFieldMap ) );
											soggetto
											.setNome ( checkForCognomeNomeSoggetto ( line, row, 41, "Nome del soggetto debitore", soggetto.getTipologiaSoggettoEnum (),
															parseErrorFieldMap ) );
											soggetto.setRagioneSociale ( checkForRagioneSocialeSoggetto ( line, row, 42, "Ragione sociale del soggetto debitore",
												soggetto.getTipologiaSoggettoEnum (), 70, parseErrorFieldMap ) );
											soggetto.setIndirizzo ( checkForString ( line, row, 43, "Indirizzo debitore", false, 70, parseErrorFieldMap ) );
											soggetto.setCivico ( checkForString ( line, row, 44, "Civico debitore", false, 16, parseErrorFieldMap ) );
											soggetto.setCap ( checkForString ( line, row, 45, "CAP debitore", false, 16, parseErrorFieldMap ) );
											soggetto.setLocalita ( checkForString ( line, row, 46, "Localita' debitore", false, 35, parseErrorFieldMap ) );
											soggetto.setProvincia ( checkForString ( line, row, 47, "Provincia debitore", false, 35, parseErrorFieldMap ) );
											String nB = checkForString ( line, row, 48, "Nazione debitore", false, 2, parseErrorFieldMap ).toUpperCase ();
											soggetto.setNazione ( nB );
											soggetto.setEmail ( checkForEMail ( line, row, 49, "Email debitore", false, 256, parseErrorFieldMap ) );
											aggposdeb.setSoggettoAnagraficoDto(soggetto);
										}

										noValues &= aggposdeb.getSoggettoAnagraficoDto() == null;

										// gestione nuova multibeneficiario
										BigDecimal impTotPrin
											= ( checkForBigDecimal ( line, row, 50, "Importo totale principale", params.isMultibeneficiario (), BigDecimal.valueOf ( 0.0 ),
												new BigDecimal ( NINE_NINE ), parseErrorFieldMap ) );
										aggposdeb.setImportoPrincipale ( impTotPrin );
										impSecTot = checkForBigDecimal ( line, row, 51, "Importo totale secondario", params.isMultibeneficiario (), BigDecimal.valueOf ( 0.0 ),
											new BigDecimal ( NINE_NINE ), parseErrorFieldMap );
										BigDecimal impSecComp
											= checkForBigDecimal ( line, row, 52, "Importo componente secondario", params.isMultibeneficiario (), BigDecimal.valueOf ( 0.0 ),
												new BigDecimal ( NINE_NINE ), parseErrorFieldMap );
										aggposdeb.setImportoSecondarioAltroEnte ( impSecTot );
										checkForImportoTotaleSecondario ( impSecTot, impSecComp, parseErrorFieldMap, row );
										checkForImportoTotaleMulti ( importoTotale, impTotPrin, impSecTot, parseErrorFieldMap, row );

										ComponenteImportoDto secondario=  checkForComponenteImportoSecondario ( line, row, 52, parseErrorFieldMap ) ;
										 if (null!= secondario)
										{
										     if ( CollectionUtils.isEmpty ( aggposdeb.getComponenteImportoDtoList () ) ) {
										         aggposdeb.setComponenteImportoDtoList ( new LinkedList<ComponenteImportoDto> () );
										     }
										     aggposdeb.getComponenteImportoDtoList ()
                                             .add ( secondario );
										}

										if ( noValues ) {
											addParseErrorFieldToMap ( PARSE_ERROR_AT_LEAST_ONE_FIELD_REQUESTED, row, null, null, null, parseErrorFieldMap );
										}

										break;
									}
									//
									aggposdeb.setMotivazione ( checkForString ( line, row, 37, "Motivazione", true, 400, parseErrorFieldMap ) );
								}

								aggposdebList.add ( aggposdeb );
							}
						}
					}
				}
				break;
			}

			if ( parseErrorFieldMap.size () > 0 ) {
				parseResultList = toParseResultList ( parseErrorFieldMap );

			} else {
				Timestamp timestampNow = getTimestampNow ();
				long millis = timestampNow.getTime ();
				Date dateNow = new Date ( millis );

				// inserimento con id messaggio temporaneo
				FlussoDto flusso = new FlussoDto ();
				flusso.setIdMessaggio ( Util.buildIdMessaggio ( dateNow, params.getCodVersamento (), millis ) );
				flusso.setTipoFlusso ( TipoFlussoEnum.AGGIORNAMENTO_POSIZIONI_DEBITORIE );
				flusso.setStatoFlusso ( StatoFlussoEnum.IN_CORSO_DI_REDAZIONE );
				flusso.setUtenteInserimento ( params.getCodUtente () );
				flusso.setUtenteUltimaVariazione ( params.getCodUtente () );
				flusso.setCodFiscaleEnte ( params.getCodFiscaleEnte () );
				flusso.setCodVersamento ( params.getCodVersamento () );
				flusso.setNumeroElementi ( params.getNumeroPosizioniDebitorie () );
				//
				flussoCompleto = new FlussoCompletoDto<> ();
				flussoCompleto.setFlusso ( flusso );
				flussoCompleto.setItemList ( aggposdebList );
				//
				log.debug ( "listadicarico:" + flussoCompleto );
				//
				try {
					transaction.begin ();

					// inserimento della lista di carico
					Long idFlusso = gestioneFlussiDad.insertFlussoPosizioniDebitorieDaAggiornare ( flussoCompleto, timestampNow );

					// aggiorna l'id-messaggio con l'idFlusso reale
					FlussoDto flussoUpd = new FlussoDto ( idFlusso );
					flussoUpd.setIdMessaggio ( Util.buildIdMessaggio ( dateNow, params.getCodVersamento (), idFlusso ) );
					gestioneFlussiDad.updateFlusso ( flussoUpd );

					transaction.commit ();

				} catch ( Throwable t ) {
					try {
						transaction.rollback ();

					} catch ( IllegalStateException | SecurityException | SystemException e ) {
						throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );
					}
					eventuallyThrowBusinessException ( t, methodName );
				}
			}
		} finally {
			log.info ( "result:" +  parseResultList );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return parseResultList;
	}
	//@formatter:on

	private boolean checkForTipoSoggettoPresente(List<Object> line, int row,
		Map<ParseErrorEnum, List<ParseErrorFieldDto>> parseErrorFieldMap ) {
		//    	Darichiesta : se e' presente il tipo soggetto faccio i controlli sugli altri campi, 
		//    	se non e' presente, scarto anche gli eventuali altri campi compilati

		return (isFieldNotNull(line, 43 ));
	}

	private void checkParamsForLinesNotNull ( ParametriInserimentoFlussoDto params, List<List<Object>> lines, String methodName ) throws BusinessException {
		if ( params == null && lines == null ) {
			String errMessage = "parametri file: null e file: null";
			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, new Exception ( errMessage ) );
		}
		if ( params == null ) {
			String errMessage = "parametri file: null";
			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, new Exception ( errMessage ) );
		}
		if ( lines == null ) {
			String errMessage = "file: null";
			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, new Exception ( errMessage ) );
		}
	}

	//@formatter:off
	private boolean checkIntestazione (
		int numColsReale, int numColsAtteso, int row,
		Map<ParseErrorEnum, List<ParseErrorFieldDto>> parseErrorFieldMap ) {
		//@formatter:on
		log.debug ( "verifing numColsReale:" + numColsReale + " numColsAtteso:" + numColsAtteso );
		boolean ok;
		if ( numColsReale != numColsAtteso ) {
			addParseErrorFieldToMap ( PARSE_ERROR_INTESTAZIONE, row, null, "Numero colonne intestazione", numColsReale + "", parseErrorFieldMap );
			ok = false;
		} else {
			ok = true;
		}

		return ok;
	}

	//@formatter:off
	private boolean checkNumeroMaxColonne (
		int numColsReale, int numColsAtteso, int row,
		Map<ParseErrorEnum, List<ParseErrorFieldDto>> parseErrorFieldMap ) {
		//@formatter:on
		log.debug ( "verifing numColsReale:" + numColsReale + " numColsAtteso:" + numColsAtteso );
		boolean ok;
		if ( numColsReale > numColsAtteso ) {
			addParseErrorFieldToMap ( PARSE_ERROR_NUMERO_COLONNE, row, null, "Numero colonne flusso", numColsReale + "", parseErrorFieldMap );
			ok = false;
		} else {
			ok = true;
		}

		return ok;
	}

	//@formatter:off
	private void checkForNotRequestedField (
		List<Object> line, int row, int column, String name,
		Map<ParseErrorEnum, List<ParseErrorFieldDto>> parseErrorFieldMap ) {
		//@formatter:on
		if ( column < line.size () ) {
			Object objval = line.get ( column );

			if ( objval != null && objval.toString ().trim ().length () > 0 ) {
				addParseErrorFieldToMap ( PARSE_ERROR_FIELD_NOT_REQUESTED, row, column, name, objval, parseErrorFieldMap );
			}
		}
	}

	//@formatter:off
	private TipologiaSoggettoEnum checkForTipologiaSoggetto (
		List<Object> line, int row, int column, String name, boolean mandatory,
		Map<ParseErrorEnum, List<ParseErrorFieldDto>> parseErrorFieldMap ) {
		//@formatter:on
		int numErrs1 = parseErrorFieldMap.size ();
		TipologiaSoggettoEnum valueEnum = TipologiaSoggettoEnum.fromId ( checkForString ( line, row, column, name, mandatory, 1, parseErrorFieldMap ) );
		int numErrs2 = parseErrorFieldMap.size ();
		if ( numErrs2 == numErrs1 ) {
			if ( valueEnum == null ) {
				Object objval = ( column < line.size () ) ? line.get ( column ) : null;
				addParseErrorFieldToMap ( PARSE_ERROR_TIPO_SOGGETTO_DEBITORE, row, column, name, objval, parseErrorFieldMap );
			}
		}

		return valueEnum;
	}

	//@formatter:off
	private List<ComponenteImportoDto> checkForComponentiImporto (
		List<Object> line, int row, int lineSize, int firstCol, int lastColumn,
		Map<ParseErrorEnum, List<ParseErrorFieldDto>> parseErrorFieldMap ) {
		List<ComponenteImportoDto> comimpList = new ArrayList<> ();
		for ( int i = 1, ic = firstCol; ic <= lastColumn; i++, ic += 4 ) {
			ComponenteImportoDto comimpI = new ComponenteImportoDto ();

			Object objval1 = ic < lineSize ? line.get ( ic ) : null;
			Object objval2 = ic + 1 < lineSize ? line.get ( ic + 1 ) : null;
			//            Object objval3 = ic + 2 < lineSize ? line.get ( ic + 2 ) : null;
			Object objval4 = ic + 2 < lineSize ? line.get ( ic + 2 ) : null;
			Object objval5 = ic + 3 < lineSize ? line.get ( ic + 3 ) : null;
			boolean annoAndNumeroAccertamentoMandatory = checkAnnoAndNumeroAccertamento ( objval4, objval5 );
			boolean mandatoryI = ( objval1 != null && objval1.toString ().trim ().length () > 0 )
							|| ( objval2 != null && objval2.toString ().trim ().length () > 0 );
			//                || ( objval3 != null && objval3.toString ().trim ().length () > 0 );

			annoAndNumeroAccertamentoMandatory = mandatoryI && annoAndNumeroAccertamentoMandatory;
			comimpI.setImporto ( checkForBigDecimal ( line, row, ic, "Importo componente " + i, mandatoryI, new BigDecimal ("0.0"),
				new BigDecimal ( NINE_NINE ), parseErrorFieldMap ) );
			comimpI.setCausale ( checkForString ( line, row, ic + 1, "Causale componente " + i, mandatoryI, 80, parseErrorFieldMap ) );
			comimpI.setAnnoAccertamento (
				checkForInteger ( line, row, ic + 2, "Anno accertamento " + i, annoAndNumeroAccertamentoMandatory, parseErrorFieldMap ) );
			comimpI.setNumeroAccertamento (
				checkForInteger ( line, row, ic + 3, "Numero accertamento " + i, annoAndNumeroAccertamentoMandatory, parseErrorFieldMap ) );

			if ( mandatoryI ) {
				comimpList.add ( comimpI );
			}
		}
		return comimpList;
	}
	
	   private ComponenteImportoDto checkForComponenteImportoSecondario (
	        List<Object> line, int row,  int column,
	        Map<ParseErrorEnum, List<ParseErrorFieldDto>> parseErrorFieldMap ) {
	        
	            ComponenteImportoDto comimpI = new ComponenteImportoDto ();

				Object objval1 = null;
				Object objval2 = null;
				try {
					objval1 = line.get ( column );
					objval2 = line.get ( column + 1 );
				} catch ( ArrayIndexOutOfBoundsException ex ) {

				} finally {

				}
	            boolean mandatoryI = ( objval1 != null && objval1.toString ().trim ().length () > 0 )
	                            || ( objval2 != null && objval2.toString ().trim ().length () > 0 );

	            comimpI.setImporto ( checkForBigDecimal ( line, row, column, "Importo componente secondario ", mandatoryI, new BigDecimal ("0.0"),
	                new BigDecimal ( NINE_NINE ), parseErrorFieldMap ) );
	            comimpI.setCausale ( checkForString ( line, row, column + 1, "Causale componente secondario" , mandatoryI, 80, parseErrorFieldMap ) );
	            comimpI.setFlagComponenteSecondario ( Boolean.TRUE );

	            if ( mandatoryI ) {
	                return comimpI;
	            }
	        return null;
	    }

	private List<RiferimentoPagamentoDto> checkForRiferimentiPagamento (
		List<Object> line, int row, int lineSize, int firstCol, int lastColumn,
		Map<ParseErrorEnum, List<ParseErrorFieldDto>> parseErrorFieldMap ) {
		List<RiferimentoPagamentoDto> rifpagList = new ArrayList<> ();
		for ( int i = 1, ic = firstCol; ic <= lastColumn; i++, ic += 2 ) {
			RiferimentoPagamentoDto rifpagI = new RiferimentoPagamentoDto ();

			Object objval1 = ic < lineSize ? line.get ( ic ) : null;
			Object objval2 = ic + 1 < lineSize ? line.get ( ic + 1 ) : null;
			boolean mandatoryI = ( objval1 != null && objval1.toString ().trim ().length () > 0 )
							|| ( objval2 != null && objval2.toString ().trim ().length () > 0 );

			rifpagI.setNome ( checkForString ( line, row, ic, "Nome " + i, mandatoryI, 70, parseErrorFieldMap ) );
			rifpagI.setValore (
				checkForString ( line, row, ic + 1, "Valore " + i, mandatoryI, 70, parseErrorFieldMap ) );

			if ( mandatoryI ) {
				rifpagList.add ( rifpagI );
			}
		}
		return rifpagList;
	}
	//@formatter:on

	private boolean checkAnnoAndNumeroAccertamento ( Object annoAccertamento, Object numeroAccertamento ) {

		if ( ( annoAccertamento != null && annoAccertamento.toString ().trim ().length () > 0 )
						&& ( numeroAccertamento == null || numeroAccertamento.toString ().trim ().length () == 0 ) ) {
			return true;
		} else if ( ( annoAccertamento == null || annoAccertamento.toString ().trim ().length () == 0 )
						&& ( numeroAccertamento != null && numeroAccertamento.toString ().trim ().length () > 0 ) ) {
			return true;
		} else {
			return false;
		}
	}

	//@formatter:off
	private TipoAggiornamentoPosizioneDebitoriaDto checkForTipoAggiornamentoPosizioneDebitoria (
		List<Object> line, int row, int column, String name, boolean mandatory,
		Map<ParseErrorEnum, List<ParseErrorFieldDto>> parseErrorFieldMap ) {
		//
		// N.B. verifica che il tipo di aggiornamento sia tra quelli ammessi in ParseErrorEnum,
		//      ma prima occorre fare altre verifiche, ad es. che il dato sia obbligatorio, abbia lunghezza 1, ...
		//      se si verifica uno di questi errori preliminari, non si procede a verificare i valori ammessi.
		//
		Map<ParseErrorEnum, List<ParseErrorFieldDto>> tempParseErrorFieldMap = new Hashtable<> ();
		TipoAggiornamentoPosizioneDebitoriaEnum valueEnum
		= TipoAggiornamentoPosizioneDebitoriaEnum.fromId ( checkForString ( line, row, column, name, mandatory, 1, tempParseErrorFieldMap ) );
		TipoAggiornamentoPosizioneDebitoriaDto valueDto = new TipoAggiornamentoPosizioneDebitoriaDto ( valueEnum );
		if ( tempParseErrorFieldMap.isEmpty () ) {
			if ( valueEnum == null ) {
				Object objval = ( column < line.size () ) ? line.get ( column ) : null;
				addParseErrorFieldToMap ( PARSE_ERROR_TIPO_AGGIORNAMENTO_POSIZIONE_DEBITORIA, row, column, name, objval, parseErrorFieldMap );
			}
		} else {
			addAllParseErrorFields ( tempParseErrorFieldMap, parseErrorFieldMap );
		}

		return valueDto;
	}
	//@formatter:on

	//@formatter:off
	private String checkForCognomeNomeSoggetto (
		List<Object> line, int row, int column, String name,
		TipologiaSoggettoEnum tipologiaSoggettoEnum,
					Map<ParseErrorEnum, List<ParseErrorFieldDto>> parseErrorFieldMap ) {
		//@formatter:on
		String value = null;

		if ( tipologiaSoggettoEnum != null ) {
			switch ( tipologiaSoggettoEnum ) {
			case PERSONA_FISICA :
				value = checkForString ( line, row, column, name, true, 70, parseErrorFieldMap );
				break;

			case PERSONA_GIURIDICA :
				if ( column < line.size () ) {
					Object objval = line.get ( column );

					if ( objval != null ) {
						if ( objval instanceof String ) {
							String strval = (String) objval;

							log.debug ( "parsing row:" + row + " col:" + column + " name:" + name + " strval:" + quote ( strval ) + " maxlength:" + 70 );

							strval = strval.trim ();
							if ( strval.length () > 0 ) {
								addParseErrorFieldToMap ( PARSE_ERROR_COGNOME_NOME_SOGGETTO_DEBITORE, row, column, name, strval, parseErrorFieldMap );
							}
						}
					}

				} else {
					addParseErrorFieldToMap ( PARSE_ERROR_MANDATORY, row, column, name, null, parseErrorFieldMap );
				}

				break;
			}
		} else {
			// non fa nulla. N.B. si assume che il controllo sulla tipologia del soggetto sia stato fatto prima
		}

		return value;
	}

	//@formatter:off
	private String checkForRagioneSocialeSoggetto (
		List<Object> line, int row, int column, String name,
		TipologiaSoggettoEnum tipologiaSoggettoEnum, int maxlength,
		Map<ParseErrorEnum, List<ParseErrorFieldDto>> parseErrorFieldMap ) {
		//@formatter:on
		String value = null;

		if ( tipologiaSoggettoEnum != null ) {
			switch ( tipologiaSoggettoEnum ) {
			case PERSONA_FISICA :
				if ( column < line.size () ) {
					Object objval = line.get ( column );

					if ( objval != null ) {
						if ( objval instanceof String ) {
							String strval = (String) objval;

							log.debug ( "parsing row:" + row + " col:" + column + " name:" + name + " strval:" + quote ( strval ) + " maxlength:" + maxlength );

							strval = strval.trim ();
							if ( strval.length () > 0 ) {
								addParseErrorFieldToMap ( PARSE_ERROR_RAGIONE_SOCIALE_SOGGETTO_DEBITORE, row, column, name, strval, parseErrorFieldMap );
							}
						}
					}

				} else {
					addParseErrorFieldToMap ( PARSE_ERROR_MANDATORY, row, column, name, null, parseErrorFieldMap );
				}

				break;

			case PERSONA_GIURIDICA :
				value = checkForString ( line, row, column, name, true, maxlength, parseErrorFieldMap );
				break;
			}
		} else {
			// non fa nulla. N.B. si assume che il controllo sulla tipologia del soggetto sia stato fatto prima
		}

		return value;
	}
	//@formatter:off
	private String checkForStringAndTrim (
		List<Object> line, int row, int column, String name, boolean mandatory,
		int maxlength,
		Map<ParseErrorEnum, List<ParseErrorFieldDto>> parseErrorFieldMap ) {
		//@formatter:on
		String value = checkForString ( line, row, column, name, mandatory, maxlength, parseErrorFieldMap );
		return ( value != null ) ? value.trim () : null;
	}

	//@formatter:off
	private String checkForString (
		List<Object> line, int row, int column, String name, boolean mandatory,
		int maxlength,
		Map<ParseErrorEnum, List<ParseErrorFieldDto>> parseErrorFieldMap ) {
		//@formatter:on
		String value = null;

		if ( column < line.size () ) {
			Object objval = line.get ( column );

			if ( objval != null ) {
				if ( objval instanceof String ) {
					String strval = (String) objval;

					log.debug ( "parsing row:" + row + " col:" + column + " name:" + name + " strval:" + quote ( strval ) + " mandatory:" + mandatory
						+ " maxlength:" + maxlength );

					if ( strval.length () > 0 ) {
						if ( strval.trim ().length () > 0 ) {
							if ( strval.length () > maxlength ) {
								addParseErrorFieldToMap ( PARSE_ERROR_MIN_MAX_LENGTH, row, column, name, strval, parseErrorFieldMap );
							} else {
								value = strval;
							}

						} else {
							// se dato obbligatorio, segnala errore anche se dato costituito solo da spazi, se dato non obbligatorio, lo annulla (Jira EPAY-11)
							value = null;
							if ( mandatory ) {
								addParseErrorFieldToMap ( PARSE_ERROR_MANDATORY, row, column, name, strval, parseErrorFieldMap );
							}
						}
					} else {
						value = null;
						if ( mandatory ) {
							addParseErrorFieldToMap ( PARSE_ERROR_MANDATORY, row, column, name, strval, parseErrorFieldMap );
						}
					}
				} else {
					addParseErrorFieldToMap ( PARSE_ERROR_TYPE_OR_FORMAT, row, column, name, objval, parseErrorFieldMap );
				}

			} else if ( mandatory ) {
				addParseErrorFieldToMap ( PARSE_ERROR_MANDATORY, row, column, name, objval, parseErrorFieldMap );
			}

		} else if ( mandatory ) {
			addParseErrorFieldToMap ( PARSE_ERROR_MANDATORY, row, column, name, null, parseErrorFieldMap );
		}

		return value;

	}
	private boolean isFieldNotNull( List<Object> line, int column )
	{
		if ( column < line.size () ) {
			Object objval = line.get ( column );
			if ( objval instanceof String ) {
				String strval = (String) objval;
				return StringUtils.isNotBlank(strval);

			}
			return  ( objval != null ) ;
		}
		return false;
	}

	//@formatter:off
	private Integer checkForInteger (
		List<Object> line, int row, int column, String name, boolean mandatory,
		Integer minval, Integer maxval,
		Map<ParseErrorEnum, List<ParseErrorFieldDto>> parseErrorFieldMap ) {
		Integer value = null;

		if ( column < line.size () ) {
			Object objval = line.get ( column );

			if ( objval != null ) {
				if ( objval instanceof String ) {
					String strval = (String) objval;

					log.debug ( "parsing row:" + row + " col:" + column + " name:" + name + " strval:" + quote ( strval ) + " mandatory:" + mandatory
						+ " minval:" + minval + " maxval:" + maxval );

					strval = strval.trim ();
					if ( strval.length () > 0 ) {
						try {
							value = Integer.parseInt ( strval );

						} catch ( NumberFormatException e ) {
							addParseErrorFieldToMap ( PARSE_ERROR_TYPE_OR_FORMAT, row, column, name, strval, parseErrorFieldMap );
						}
					}

				} else if ( objval instanceof Double ) {
					double numval = (Double) objval;

					log.debug ( "parsing row:" + row + " col:" + column + " name:" + name + " numval:" + numval + " mandatory:" + mandatory + " minval:" + minval
						+ " maxval:" + maxval );

					if ( ( numval % 1 ) == 0 ) {
						value = (int) numval;
					}

				} else {
					addParseErrorFieldToMap ( PARSE_ERROR_TYPE_OR_FORMAT, row, column, name, value, parseErrorFieldMap );
				}

				if ( value != null && ( value.compareTo ( minval ) < 0 || value.compareTo ( maxval ) > 0 ) ) {
					addParseErrorFieldToMap ( PARSE_ERROR_MIN_MAX_LENGTH, row, column, name, value, parseErrorFieldMap );
				}

			} else if ( mandatory ) {
				addParseErrorFieldToMap ( PARSE_ERROR_MANDATORY, row, column, name, objval, parseErrorFieldMap );
			}

		} else if ( mandatory ) {
			addParseErrorFieldToMap ( PARSE_ERROR_MANDATORY, row, column, name, null, parseErrorFieldMap );
		}

		return value;
	}
	//@formatter:on

	//@formatter:off
	private BigDecimal checkForBigDecimal (
		List<Object> line, int row, int column, String name, boolean mandatory,
		BigDecimal minval, BigDecimal maxval,
		Map<ParseErrorEnum, List<ParseErrorFieldDto>> parseErrorFieldMap ) {
		BigDecimal value = null;

		if ( column < line.size () ) {
			Object objval = line.get ( column );

			if ( objval != null ) {
				if ( objval instanceof String ) {
					String strval = (String) objval;

					log.debug ( "parsing row:" + row + " col:" + column + " name:" + name + " strval:" + quote ( strval ) + " mandatory:" + mandatory
						+ " minval:" + minval + " maxval:" + maxval );

					strval = strval.trim ();
					if ( strval.length () > 0 ) {
						try {
							// formato atteso nel caso di stringa: NN...NDD dove le cifre DD rappresentano i due decimali (N.B. non ci sono virgole decimali)
							value = ( new BigDecimal ( strval ).setScale ( 2 ) ).divide ( new BigDecimal ( 100 ).setScale ( 2 ) );

						} catch ( NumberFormatException e ) {
							addParseErrorFieldToMap ( PARSE_ERROR_TYPE_OR_FORMAT, row, column, name, strval, parseErrorFieldMap );
						}
					}

				} else if ( objval instanceof Double ) {
					double numval = (Double) objval;

					log.debug ( "parsing row:" + row + " col:" + column + " name:" + name + " numval:" + numval + " mandatory:" + mandatory + " minval:" + minval
						+ " maxval:" + maxval );

					value = BigDecimal.valueOf ( numval ).setScale ( 2, RoundingMode.HALF_DOWN );

				} else {
					addParseErrorFieldToMap ( PARSE_ERROR_TYPE_OR_FORMAT, row, column, name, value, parseErrorFieldMap );
				}

				if ( value != null && ( value.compareTo ( minval ) < 0 || value.compareTo ( maxval ) > 0 ) ) {
					addParseErrorFieldToMap ( PARSE_ERROR_MIN_MAX_LENGTH, row, column, name, value, parseErrorFieldMap );
				}

			} else if ( mandatory ) {
				addParseErrorFieldToMap ( PARSE_ERROR_MANDATORY, row, column, name, objval, parseErrorFieldMap );
			}

		} else if ( mandatory ) {
			addParseErrorFieldToMap ( PARSE_ERROR_MANDATORY, row, column, name, null, parseErrorFieldMap );
		}

		return value;
	}
	//@formatter:on

	//@formatter:off
	private Integer checkForInteger (
		List<Object> line, int row, int column, String name, boolean mandatory,
		Map<ParseErrorEnum, List<ParseErrorFieldDto>> parseErrorFieldMap ) {
		Integer value = null;

		if ( column < line.size () ) {
			Object objval = line.get ( column );

			if ( objval != null ) {
				if ( objval instanceof String ) {
					String strval = (String) objval;

					log.debug ( "parsing row:" + row + " col:" + column + " name:" + name + " strval:" + quote ( strval ) + " mandatory:" + mandatory );

					strval = strval.trim ();
					if ( strval.length () > 0 ) {
						try {
							// formato atteso nel caso di stringa: NN...NDD dove le cifre DD rappresentano i due decimali (N.B. non ci sono virgole decimali)
							value = ( new Integer ( strval ) );

						} catch ( NumberFormatException e ) {
							addParseErrorFieldToMap ( PARSE_ERROR_TYPE_OR_FORMAT, row, column, name, strval, parseErrorFieldMap );
						}
					}

				} else if ( objval instanceof Double ) {
					Long numval = ( (Double) objval ).longValue ();

					log.debug ( "parsing row:" + row + " col:" + column + " name:" + name + " numval:" + numval + " mandatory:" + mandatory );

					value = numval != null ? numval.intValue () : null;

				} else {
					addParseErrorFieldToMap ( PARSE_ERROR_TYPE_OR_FORMAT, row, column, name, value, parseErrorFieldMap );
				}

			} else if ( mandatory ) {
				addParseErrorFieldToMap ( PARSE_ERROR_MANDATORY, row, column, name, objval, parseErrorFieldMap );
			}

		} else if ( mandatory ) {
			addParseErrorFieldToMap ( PARSE_ERROR_MANDATORY, row, column, name, null, parseErrorFieldMap );
		}

		return value;
	}
	//@formatter:on

	//@formatter:off
	private Date checkForDate (
		List<Object> line, int row, int column, String name, boolean mandatory,
		Map<ParseErrorEnum, List<ParseErrorFieldDto>> parseErrorFieldMap ) {
		Date value = null;

		if ( column < line.size () ) {
			Object objval = line.get ( column );

			if ( objval != null ) {
				if ( objval instanceof String ) {
					String strval = (String) objval;

					log.debug ( "parsing row:" + row + " col:" + column + " name:" + name + " strval:" + quote ( strval ) + " mandatory:" + mandatory );

					strval = strval.trim ();
					switch ( strval.length () ) {
					case 0 :
						if ( mandatory ) {
							addParseErrorFieldToMap ( PARSE_ERROR_MANDATORY, row, column, name, objval, parseErrorFieldMap );
						}
						break;
					case 8 :
						try {
							// formato atteso nel caso di stringa: yyyyMMdd
							GregorianCalendar gcal = new GregorianCalendar (
								Integer.parseInt ( strval.substring ( 0, 4 ) ),
								Integer.parseInt ( strval.substring ( 4, 6 ) ) - 1,
								Integer.parseInt ( strval.substring ( 6, 8 ) ) );
							Date parsedDate = gcal.getTime ();
							//
							if ( strval.equals ( ( new SimpleDateFormat ( Util.DATE_Y4M2D2 ) ).format ( parsedDate ) ) ) {
								value = parsedDate;
							} else {
								addParseErrorFieldToMap ( PARSE_ERROR_DATE, row, column, name, strval, parseErrorFieldMap );
							}

						} catch ( NumberFormatException e ) {
							addParseErrorFieldToMap ( PARSE_ERROR_DATE, row, column, name, strval, parseErrorFieldMap );
						}
						break;
					default :
						addParseErrorFieldToMap ( PARSE_ERROR_DATE, row, column, name, strval, parseErrorFieldMap );
						break;
					}

				} else if ( objval instanceof Date ) {
					Date dateval = (Date) objval;

					log.debug ( "parsing row:" + row + " col:" + column + " name:" + name + " dateval:" + dateval + " mandatory:" + mandatory );

					value = dateval;

				} else {
					addParseErrorFieldToMap ( PARSE_ERROR_TYPE_OR_FORMAT, row, column, name, value, parseErrorFieldMap );
				}

			} else if ( mandatory ) {
				addParseErrorFieldToMap ( PARSE_ERROR_MANDATORY, row, column, name, objval, parseErrorFieldMap );
			}

		} else if ( mandatory ) {
			addParseErrorFieldToMap ( PARSE_ERROR_MANDATORY, row, column, name, null, parseErrorFieldMap );
		}

		return value;
	}
	//@formatter:on

	//@formatter:off
	private String checkForEMail (
		List<Object> line, int row, int column, String name, boolean mandatory,
		int maxlength,
		Map<ParseErrorEnum, List<ParseErrorFieldDto>> parseErrorFieldMap ) {
		//@formatter:on
		String value = checkForString ( line, row, column, name, mandatory, maxlength, parseErrorFieldMap );
		if ( value != null && !value.matches ( Util.EMAIL_FORMAT ) ) {
			addParseErrorFieldToMap ( PARSE_ERROR_EMAIL, row, column, name, value, parseErrorFieldMap );
		}
		return value;
	}

	//@formatter:off
	private boolean checkForNumeroPosizioniDebitorie (
		Integer numeroPosizioniDebitorieAttese, Integer numeroPosizioniDebitoriePresenti,
		Map<ParseErrorEnum, List<ParseErrorFieldDto>> parseErrorFieldMap ) {
		boolean ok = true;
		log.debug ( "verifing numeroPosizioniDebitorieAttese:" + numeroPosizioniDebitorieAttese + " numeroPosizioniDebitoriePresenti:"
						+ numeroPosizioniDebitoriePresenti );
		if ( numeroPosizioniDebitorieAttese.compareTo ( numeroPosizioniDebitoriePresenti ) != 0 ) {
			addParseErrorFieldToMap ( PARSE_ERROR_NUMERO_POSIZIONI_DEBITORIE, null, null, "Numero posizioni debitorie contate",
				numeroPosizioniDebitoriePresenti + "", parseErrorFieldMap );
			ok = false;
		}
		return ok;
	}
	//@formatter:on

	//@formatter:off
	private void checkForImportoTotalePosizioniDebitorie (
		BigDecimal totaleImporto, BigDecimal sommaImporti,
		Map<ParseErrorEnum, List<ParseErrorFieldDto>> parseErrorFieldMap ) {
		// N.B. totaleImporto: dato obbligatorio che arriva dallo Step 1; sommaImporti fa parte dello Step 2 e viene inizializzato a zero
		log.debug ( "verifing totaleImporto:" + totaleImporto + " sommaImporti:" + sommaImporti );
		if ( totaleImporto.compareTo ( sommaImporti ) != 0 ) {
			addParseErrorFieldToMap ( PARSE_ERROR_TOTALE_IMPORTO_POSIZIONI_DEBITORIE, null, null, "Totale importo posizioni debitorie",
				Util.bd2str ( sommaImporti ), parseErrorFieldMap );
		}
	}
	//@formatter:on

	private void checkForImportoTotaleSecondario (
		BigDecimal totaleImportoSecondario, BigDecimal sommaImportiSecondario,
		Map<ParseErrorEnum, List<ParseErrorFieldDto>> parseErrorFieldMap, int row ) {
		log.debug ( "verifing totaleImportoSecondario:" + totaleImportoSecondario + " sommaImportiSecondario:" + sommaImportiSecondario );
		if ( null == totaleImportoSecondario || null == sommaImportiSecondario ) {
			return;
		}
		if ( totaleImportoSecondario.compareTo ( sommaImportiSecondario ) != 0 ) {
			addParseErrorFieldToMap ( PARSE_ERROR_TOTALE_IMPORTO_POSIZIONE_DEBITORIE_SECONDARIE, row, null, "Totale importo posizioni debitorie secondarie",
				Util.bd2str ( sommaImportiSecondario ), parseErrorFieldMap );
		}
	}

	private void checkForImportoTotaleMulti (
		BigDecimal importoTotale, BigDecimal impTotPrin, BigDecimal impSecTot,
		Map<ParseErrorEnum, List<ParseErrorFieldDto>> parseErrorFieldMap, int row ) {
		log.debug ( "verifing totaleImportoSecondario:" + importoTotale + " sommaImportiSecondario:" + impTotPrin + " impSecTot:" + impSecTot );
		if ( impTotPrin == null || impSecTot == null ) {
			return;
		}
		if ( importoTotale.compareTo ( impTotPrin.add ( impSecTot ) ) != 0 ) {
			addParseErrorFieldToMap ( PARSE_ERROR_TOTALE_IMPORTO_POSIZIONE_DEBITORIE_PRINCIPALI_E_SECONDARIE, row, null,
				"Totale importo posizioni debitorie principali e secondarie",
				Util.bd2str ( impTotPrin.add ( impSecTot ) ), parseErrorFieldMap );
		}
	}

	private void checkValueAsAnonimo ( String value, List<RiferimentoPagamentoDto> rifPagamento,
		int row, int column, String name, Map<ParseErrorEnum, List<ParseErrorFieldDto>> parseErrorFieldMap ) {
		if ( rifPagamento != null && rifPagamento.size () > 0 && StringUtils.isNotBlank ( value ) ) {
			if ( !value.trim ().equals ( "ANONIMO" ) ) {
				addParseErrorFieldToMap ( PARSE_ERROR_ANONIMO_POS_DEBITORIA, row, column, name, value, parseErrorFieldMap );
			}
		}
	}

	//@formatter:off
	private void addParseErrorFieldToMap (
		ParseErrorEnum errorKey, Integer row, Integer column,
		String name, Object objval,
		Map<ParseErrorEnum, List<ParseErrorFieldDto>> parseErrorFieldMap )
	//@formatter:on
	{
		List<ParseErrorFieldDto> fields = parseErrorFieldMap.get ( errorKey );
		if ( fields == null ) {
			fields = new ArrayList<> ();
			parseErrorFieldMap.put ( errorKey, fields );
		}
		fields.add ( new ParseErrorFieldDto ( row, column, name, objval ) );
	}

	private void addParseErrorFieldToMap ( ParseErrorEnum errorKey, ParseErrorFieldDto field,
		Map<ParseErrorEnum, List<ParseErrorFieldDto>> parseErrorFieldMap ) {
		addParseErrorFieldToMap ( errorKey, field.getFieldRow (), field.getFieldColumn (), field.getFieldName (), field.getFieldValue (), parseErrorFieldMap );
	}

	private void addAllParseErrorFields ( Map<ParseErrorEnum, List<ParseErrorFieldDto>> fromMap, Map<ParseErrorEnum, List<ParseErrorFieldDto>> toMap ) {
		for ( ParseErrorEnum errorKey: fromMap.keySet () ) {
			List<ParseErrorFieldDto> fromFields = fromMap.get ( errorKey );
			for ( ParseErrorFieldDto field: fromFields ) {
				addParseErrorFieldToMap ( errorKey, field, toMap );
			}
		}
	}

	private List<ParseResultDto> toParseResultList ( Map<ParseErrorEnum, List<ParseErrorFieldDto>> parseErrorFieldMap ) {
		List<ParseResultDto> parseErrorList = new ArrayList<> ();

		for ( Entry<ParseErrorEnum, List<ParseErrorFieldDto>> parseErrorFieldEntry: parseErrorFieldMap.entrySet () ) {
			ParseErrorEnum parseErrorFieldEnum = parseErrorFieldEntry.getKey ();
			List<ParseErrorFieldDto> parseErrorFieldList = parseErrorFieldEntry.getValue ();
			parseErrorList.add ( new ParseResultDto ( parseErrorFieldEnum, parseErrorFieldList ) );
			log.warn ( "Parse Error: " + parseErrorFieldEnum + " - " + parseErrorFieldList );
		}

		return parseErrorList;
	}

	@Override
	public Long salvaPosizioneDebitoria(SalvaPosizioneDebitoriaRequestDto salvaPosizioneDebitoriaRequestDto) throws BusinessException{
		String methodName = "salvaPosizioneDebitoria";
		
		

		Long idFlusso = null;
		List<Long> idPosizioniDebitorieModificate = new ArrayList<> ();
		List<String> idPosizioniDebitorieInserite = new ArrayList<> ();
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			// inserimento con id messaggio temporaneo
			Timestamp timestampNow = getTimestampNow ();
			long millis = timestampNow.getTime ();
			Date dateNow = new Date ( millis );
			List<PosizioneDebitoriaDto> posizioniDebitorieDto = salvaPosizioneDebitoriaRequestDto.getFlussoCompleto().getItemList();
			for(PosizioneDebitoriaDto pd : posizioniDebitorieDto) {
				if(pd.getId()==null) {
					idPosizioniDebitorieInserite.add(pd.getIdPosizioneDebitoriaEsterna());
				}else {
					PosizioneDebitoriaLightDto pdCorrente = gestioneFlussiDad.findPosizioneDebitoriaLightById(pd.getId());
					if(modificaDatiSensibili(pdCorrente.getSoggettoDebitore(), pd.getSoggettoDebitore())) {
						idPosizioniDebitorieModificate.add(pd.getId());
					}
				}
			}

			transaction.begin ();

			FlussoDto testataFlusso = salvaPosizioneDebitoriaRequestDto.getFlussoCompleto().getFlusso ();

			// se l'id flusso esiste, si va in modifica, altrimenti in inserimento
			if ( testataFlusso.getId () != null ) {
				// ricalcola l'id messaggio
				String idMessaggio = testataFlusso.getIdMessaggio ();
				String codVersamento = testataFlusso.getCodVersamento ();
				if ( idMessaggio != null && codVersamento != null ) {
					testataFlusso.setIdMessaggio ( Util.buildIdMessaggio ( dateNow, codVersamento, testataFlusso.getId (), idMessaggio ) );
				}

				// aggiorna
				if ( gestioneFlussiDad.updateFlussoPosizioniDebitorie ( salvaPosizioneDebitoriaRequestDto.getFlussoCompleto(), true ) < 2L ) {
					log.warn ( "errore durante il salvataggio (in modifica) del flusso:" + salvaPosizioneDebitoriaRequestDto.getFlussoCompleto() );
					throw new SkipException ();
				}
				idFlusso = testataFlusso.getId ();

			} else {
				// all'inizio imposta un id-messaggio fittizio
				String idMessaggioTemp = Util.buildIdMessaggio ( dateNow, testataFlusso.getCodVersamento (), millis );
				testataFlusso.setIdMessaggio ( idMessaggioTemp );

				// inserimento della flusso completo
				if ( ( idFlusso = gestioneFlussiDad.insertFlussoPosizioniDebitorie ( salvaPosizioneDebitoriaRequestDto.getFlussoCompleto(), timestampNow ) ) == null ) {
					log.warn ( "errore durante il salvataggio (in inserimento) del flusso completo:" + salvaPosizioneDebitoriaRequestDto.getFlussoCompleto() );
					throw new SkipException ();
				}

				// alla fine aggiorna l'id-messaggio in base all'idFlusso reale ottenuto dall'inserimento
				String idMessaggio = Util.buildIdMessaggio ( dateNow, testataFlusso.getCodVersamento (), idFlusso );
				FlussoDto flussoUpd = new FlussoDto ( idFlusso );
				flussoUpd.setIdMessaggio ( idMessaggio );
				if ( !gestioneFlussiDad.updateFlusso ( flussoUpd ) ) {
					log.warn ( "errore durante il salvataggio (in inserimento) del flusso nell'aggiornamento dell'idMessaggio:" + idMessaggio );
					throw new SkipException ();
				}
			}

			transaction.commit ();

			// Log audit
			for(Long idPdModificata:idPosizioniDebitorieModificate) {
				logAuditOperation(CsiLogAuditOperationEnum.UPDATE, salvaPosizioneDebitoriaRequestDto, "epaypa_t_posizione_debitoria", "ID POSIZIONE =" + idPdModificata,"Modifica Posizione Debitoria" );
			}
			for(String IdPosizioneDebitoriaEsterna: idPosizioniDebitorieInserite) {
				logAuditOperation(CsiLogAuditOperationEnum.INSERT, salvaPosizioneDebitoriaRequestDto, "epaypa_t_posizione_debitoria", "ID POSIZIONE ESTERNA="+IdPosizioneDebitoriaEsterna + " ID FLUSSO=" + idFlusso, "Inserimento Nuova Posizione Debitoria");
			}

		} catch ( Throwable t ) {
			try {
				transaction.rollback ();

			} catch ( IllegalStateException | SecurityException | SystemException e ) {
				throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );
			}
			eventuallyThrowBusinessException ( t, methodName );

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return idFlusso;
	}

	@Override
	public Long salvaPosizioneDebitoriaDaAggiornare ( SalvaPosizioneDebitoriaDaAggiornareRequestDto salvaPosizioneDebitoriaDaAggiornareRequestDto ) throws BusinessException {
		String methodName = "salvaPosizioneDebitoriaDaAggiornare";
		
		

		Long idFlusso = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			// inserimento con id messaggio temporaneo
			Timestamp timestampNow = getTimestampNow ();
			long millis = timestampNow.getTime ();
			Date dateNow = new Date ( millis );

			transaction.begin ();

			FlussoDto testataFlusso = salvaPosizioneDebitoriaDaAggiornareRequestDto.getFlussoCompleto().getFlusso ();

			// se l'id flusso esiste, si va in modifica, altrimenti in inserimento
			if ( testataFlusso.getId () != null ) {
				// ricalcola l'id messaggio
				String idMessaggio = testataFlusso.getIdMessaggio ();
				String codVersamento = testataFlusso.getCodVersamento ();
				if ( idMessaggio != null && codVersamento != null ) {
					testataFlusso.setIdMessaggio ( Util.buildIdMessaggio ( dateNow, codVersamento, testataFlusso.getId (), idMessaggio ) );
				}

				// aggiorna
				if ( gestioneFlussiDad.updateFlussoPosizioniDebitorieDaAggiornare ( salvaPosizioneDebitoriaDaAggiornareRequestDto.getFlussoCompleto(), true ) < 2L ) {
					log.warn ( "errore durante il salvataggio (in modifica) del flusso:" + salvaPosizioneDebitoriaDaAggiornareRequestDto.getFlussoCompleto() );
					throw new SkipException ();
				}
				idFlusso = testataFlusso.getId ();

			} else {
				// all'inizio imposta un id-messaggio fittizio
				String idMessaggioTemp = Util.buildIdMessaggio ( dateNow, testataFlusso.getCodVersamento (), millis );
				testataFlusso.setIdMessaggio ( idMessaggioTemp );

				// inserimento della flusso completo
				if ( ( idFlusso = gestioneFlussiDad.insertFlussoPosizioniDebitorieDaAggiornare ( salvaPosizioneDebitoriaDaAggiornareRequestDto.getFlussoCompleto(), timestampNow ) ) == null ) {
					log.warn ( "errore durante il salvataggio (in inserimento) del flusso completo:" + salvaPosizioneDebitoriaDaAggiornareRequestDto.getFlussoCompleto() );
					throw new SkipException ();
				}

				// alla fine aggiorna l'id-messaggio in base all'idFlusso reale ottenuto dall'inserimento
				String idMessaggio = Util.buildIdMessaggio ( dateNow, testataFlusso.getCodVersamento (), idFlusso );
				FlussoDto flussoUpd = new FlussoDto ( idFlusso );
				flussoUpd.setIdMessaggio ( idMessaggio );
				if ( !gestioneFlussiDad.updateFlusso ( flussoUpd ) ) {
					log.warn ( "errore durante il salvataggio (in inserimento) del flusso nell'aggiornamento dell'idMessaggio:" + idMessaggio );
					throw new SkipException ();
				}
			}

			transaction.commit ();

			logAuditOperation( testataFlusso.getId () != null ?CsiLogAuditOperationEnum.UPDATE:CsiLogAuditOperationEnum.INSERT
							, salvaPosizioneDebitoriaDaAggiornareRequestDto, "epaypa_t_agg_posizione_debitoria", "ID FLUSSO =" + idFlusso, "Inserimento Nuova Posizione Debitoria Da Aggiornare");



		} catch ( Throwable t ) {
			try {
				transaction.rollback ();

			} catch ( IllegalStateException | SecurityException | SystemException e ) {
				throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );
			}
			eventuallyThrowBusinessException ( t, methodName );

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return idFlusso;
	}

	@Override
	public boolean salvaTestataFlussoPosizioniDebitorie(SalvaTestataFlussoPosizioniDebitorieRequestDto salvaTestataFlussoPosizioniDebitorieRequestDto) throws BusinessException{
		String methodName = "salvaTestataFlussoPosizioniDebitorie";
		
		
		
		

		boolean saveDone = false;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			transaction.begin ();

			// ricalcola l'id messaggio
			String idMessaggio = salvaTestataFlussoPosizioniDebitorieRequestDto.getTestataFlusso().getIdMessaggio ();
			String codVersamento = salvaTestataFlussoPosizioniDebitorieRequestDto.getTestataFlusso().getCodVersamento ();
			if ( idMessaggio != null && codVersamento != null ) {
				Date dateNow = new Date ();
				salvaTestataFlussoPosizioniDebitorieRequestDto.getTestataFlusso().setIdMessaggio ( Util.buildIdMessaggio ( dateNow, codVersamento, salvaTestataFlussoPosizioniDebitorieRequestDto.getTestataFlusso().getId (), idMessaggio ) );
			}
			//
			Timestamp timestampInizioValidita = Util.date2timestap ( salvaTestataFlussoPosizioniDebitorieRequestDto.getDataInizioValidita() );
			Timestamp timestampFineValidita = Util.date2timestap ( salvaTestataFlussoPosizioniDebitorieRequestDto.getDataFineValidita() );

			if ( ! ( saveDone
							= ( gestioneFlussiDad.updateFlussoPosizioniDebitorie ( salvaTestataFlussoPosizioniDebitorieRequestDto.getTestataFlusso(), timestampInizioValidita, timestampFineValidita ) ) > 0L ) ) {
				log.warn ( "errore durante il salvataggio della testata del flusso:" + salvaTestataFlussoPosizioniDebitorieRequestDto.getTestataFlusso() );
				throw new SkipException ();
			}

			transaction.commit ();

			//logAuditOperation(CsiLogAuditOperationEnum.UPDATE, salvaTestataFlussoPosizioniDebitorieRequestDto.getCodUtente(), salvaTestataFlussoPosizioniDebitorieRequestDto.getIpAddress(), "epaypa_t_flusso", "Modifica Testata Flusso [ID FLUSSO=" + salvaTestataFlussoPosizioniDebitorieRequestDto.getTestataFlusso().getIdFlussoRendicontazione() +"]");

		} catch ( Throwable t ) {
			try {
				transaction.rollback ();

			} catch ( IllegalStateException | SecurityException | SystemException e ) {
				throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );
			}
			eventuallyThrowBusinessException ( t, methodName );

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return saveDone;
	}

	@Override
	public boolean salvaTestataFlussoPosizioniDebitorieDaAggiornare ( FlussoDto testataFlusso ) throws BusinessException {
		String methodName = "salvaTestataFlussoPosizioniDebitorieDaAggiornare";
		
		

		boolean saveDone = false;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			transaction.begin ();

			// ricalcola l'id messaggio
			String idMessaggio = testataFlusso.getIdMessaggio ();
			String codVersamento = testataFlusso.getCodVersamento ();
			if ( idMessaggio != null && codVersamento != null ) {
				Date dateNow = new Date ();
				testataFlusso.setIdMessaggio ( Util.buildIdMessaggio ( dateNow, codVersamento, testataFlusso.getId (), idMessaggio ) );
			}

			if ( ! ( saveDone = gestioneFlussiDad.updateFlussoPosizioniDebitorieDaAggiornare ( testataFlusso ) ) ) {
				log.warn ( "errore durante il salvataggio della testata del flusso:" + testataFlusso );
				throw new SkipException ();
			}

			transaction.commit ();

		} catch ( Throwable t ) {
			try {
				transaction.rollback ();

			} catch ( IllegalStateException | SecurityException | SystemException e ) {
				throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );
			}
			eventuallyThrowBusinessException ( t, methodName );

		} finally {
			log.info ( "result:" + saveDone );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return saveDone;
	}

	@Override
	public void registraEsitoInserimentoListaDiCarico(RegistraEsitoInserimentoListaDiCaricoRequestDto registraEsitoInserimentoListaDiCaricoRequestDto) throws BusinessException{
		//public void registraEsitoInserimentoListaDiCarico ( EsitoPosizioniDebitorieDto esitoDto ) throws BusinessException {
		String methodName = "registraEsitoInserimentoListaDiCarico";
		
		EsitoPosizioniDebitorieDto esitoDto = registraEsitoInserimentoListaDiCaricoRequestDto.getEsitoDto();
		

		FlussoCompletoDto<PosizioneDebitoriaDto> resDto = null;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			Long idFlusso = null;
			int numEsiti = 0;
			try {
				// controlli di testata
				TestataEsitoPosizioniDebitorieDto testataEsitoDto = esitoDto.getTestataDto ();
				Integer idEnte = checkEnteInTestataEsito ( testataEsitoDto );
				CodiceVersamentoDto codiceVersamentoDto = checkUniqueCodiceVersamento ( testataEsitoDto.getCodVersamento (), idEnte );
				checkCodiceVersamentoPermesso ( codiceVersamentoDto );
				FlussoLightDto flussoLightDto = checkUniqueFlusso ( testataEsitoDto.getIdMessaggioFlusso (), idEnte, codiceVersamentoDto.getId () );
				checkStatoEsitoNonAncoraRicevuto ( flussoLightDto );
				//
				idFlusso = flussoLightDto.getId ();

				// prepara l'oggetto per l'aggiornamento
				Timestamp timestampNow = getTimestampNow ();
				FlussoDto flussoUpdDto = new FlussoDto ( idFlusso );
				flussoUpdDto.setCodEsito ( testataEsitoDto.getCodEsitoGenerale () );
				flussoUpdDto.setDetEsito ( testataEsitoDto.getDetEsitoGenerale () );
				flussoUpdDto.setStatoFlusso ( StatoFlussoEnum.ESITO_RICEVUTO );
				flussoUpdDto.setDataUltimaVariazione ( timestampNow );
				flussoUpdDto.setUtenteUltimaVariazione ( "SYSTEM" );
				//
				List<PosizioneDebitoriaDto> posdebUpdList = null;
				List<EsitoPosizioneDebitoriaDto> esitoList = esitoDto.getEsitoList ();
				if ( esitoList != null ) {
					// controlli sugli esiti
					numEsiti = esitoList.size ();
					checkNumeroEsitiAttesiERicevuti ( idFlusso, flussoLightDto.getNumeroElementi (), numEsiti );
					checkRipetetizioneIdPosizioneDebitoriaEstInEsitoList ( idFlusso, esitoList );

					posdebUpdList = new ArrayList<> ();
					for ( EsitoPosizioneDebitoriaDto posdebItem: esitoList ) {
						PosizioneDebitoriaDto posdebUpdDto = new PosizioneDebitoriaDto ();
						posdebUpdDto.setIdPosizioneDebitoriaEsterna ( posdebItem.getIdPosizioneDebitoriaEsterna () );
						posdebUpdDto.setIUV ( posdebItem.getIUV () );
						posdebUpdDto.setCodAvviso ( posdebItem.getCodAvviso () );
						posdebUpdDto.setCodEsito ( posdebItem.getCodEsito () );
						posdebUpdDto.setDetEsito ( posdebItem.getDetEsito () );
						//
						posdebUpdList.add ( posdebUpdDto );
					}
				}

				resDto = new FlussoCompletoDto<> ();
				resDto.setFlusso ( flussoUpdDto );
				resDto.setItemList ( posdebUpdList );

			} catch ( Throwable t ) {
				eventuallyThrowBusinessException ( t, methodName );
			}

			try {
				transaction.begin ();

				long numUpdatesPosizioni = gestioneFlussiDad.updateFlussoPosizioniDebitorie ( resDto, false ) - 1; // N.B. sottrae l'update della testata (-1)
				if ( numEsiti == numUpdatesPosizioni ) {
					log.info ( "aggiornamento esito elaborazione lista di carico effettuata" );

				} else {
					log.error ( "numero di esiti ricevuti:" + numEsiti + " diverso dal numero posizioni debitorie aggiornabili:" + numUpdatesPosizioni );
					throw new BusinessMismatchInNumberOfUpdatesException ( idFlusso, numUpdatesPosizioni, numEsiti );
				}

				transaction.commit ();

				logAuditOperation(CsiLogAuditOperationEnum.UPDATE, registraEsitoInserimentoListaDiCaricoRequestDto, "epaypa_t_flusso", "ID FLUSSO=" + idFlusso, "Registrazione Esito Inserimento Liste di Carico");

			} catch ( Throwable t ) {
				try {
					transaction.rollback ();

				} catch ( IllegalStateException | SecurityException | SystemException e ) {
					throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );
				}
				eventuallyThrowBusinessException ( t, methodName );
			}

		} finally {
			log.info ( "result:" +  resDto );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
	}

	private void checkCodiceVersamentoPermesso ( CodiceVersamentoDto codiceVersamentoDto ) throws BusinessException {

		// RDI 02 2019
		if ( codiceVersamentoDto != null && codiceVersamentoDto.getIdTipoPagamento () != null ) {
			Integer idTipoPagamentoREDS;
			try {
				idTipoPagamentoREDS = accessoDad.findIdTipoPagamentoREDS ();
			} catch ( PersistenceException e ) {

				throw new BusinessException ( "Errore nella validazione del codice versamento", e );
			}

			if ( idTipoPagamentoREDS != null && idTipoPagamentoREDS.equals ( codiceVersamentoDto.getIdTipoPagamento () ) ) {
				throw new BusinessException ( "Codice versamento non permesso" );
			}
		}
	}

	@Override
	public void registraEsitoPosizioniDebitorieAggiornate(RegistraEsitoPosizioniDebitorieAggiornateRequestDto registraEsitoPosizioniDebitorieAggiornateRequestDto) throws BusinessException{
		//public void registraEsitoPosizioniDebitorieAggiornate ( EsitoPosizioniDebitorieDto esitoDto ) throws BusinessException {
		String methodName = "registraEsitoPosizioniDebitorieAggiornate";
		
		EsitoPosizioniDebitorieDto esitoDto = registraEsitoPosizioniDebitorieAggiornateRequestDto.getEsitoDto();
		

		FlussoCompletoDto<PosizioneDebitoriaDaAggiornareDto> resDto = null;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			Long idFlusso = null;
			int numEsiti = 0;
			try {
				// controlli di testata
				TestataEsitoPosizioniDebitorieDto testataEsitoDto = esitoDto.getTestataDto ();
				Integer idEnte = checkEnteInTestataEsito ( testataEsitoDto );
				CodiceVersamentoDto codiceVersamentoDto = checkUniqueCodiceVersamento ( testataEsitoDto.getCodVersamento (), idEnte );
				FlussoLightDto flussoLightDto = checkUniqueFlusso ( testataEsitoDto.getIdMessaggioFlusso (), idEnte, codiceVersamentoDto.getId () );
				checkStatoEsitoNonAncoraRicevuto ( flussoLightDto );
				//
				idFlusso = flussoLightDto.getId ();

				// prepara l'oggetto per l'aggiornamento
				Timestamp timestampNow = getTimestampNow ();
				FlussoDto flussoUpdDto = new FlussoDto ( idFlusso );
				flussoUpdDto.setCodEsito ( testataEsitoDto.getCodEsitoGenerale () );
				flussoUpdDto.setDetEsito ( testataEsitoDto.getDetEsitoGenerale () );
				flussoUpdDto.setStatoFlusso ( StatoFlussoEnum.ESITO_RICEVUTO );
				flussoUpdDto.setDataUltimaVariazione ( timestampNow );
				flussoUpdDto.setUtenteUltimaVariazione ( "SYSTEM" );
				//
				List<PosizioneDebitoriaDaAggiornareDto> posdebaggUpdList = null;
				List<EsitoPosizioneDebitoriaDto> esitoList = esitoDto.getEsitoList ();
				if ( esitoList != null ) {
					// controlli sugli esiti
					numEsiti = esitoList.size ();
					checkNumeroEsitiAttesiERicevuti ( idFlusso, flussoLightDto.getNumeroElementi (), numEsiti );
					checkRipetetizioneIUVInEsitoList ( idFlusso, esitoList );

					posdebaggUpdList = new ArrayList<> ();
					for ( EsitoPosizioneDebitoriaDto posdebaggItem: esitoList ) {
						PosizioneDebitoriaDaAggiornareDto posdebaggUpdDto = new PosizioneDebitoriaDaAggiornareDto ();
						posdebaggUpdDto.setIdPosizioneDebitoriaEsterna ( posdebaggItem.getIdPosizioneDebitoriaEsterna () );
						posdebaggUpdDto.setCodAvviso ( posdebaggItem.getCodAvviso () );
						posdebaggUpdDto.setCodEsito ( posdebaggItem.getCodEsito () );
						posdebaggUpdDto.setDetEsito ( posdebaggItem.getDetEsito () );
						//
						posdebaggUpdList.add ( posdebaggUpdDto );
					}
				}

				resDto = new FlussoCompletoDto<> ();
				resDto.setFlusso ( flussoUpdDto );
				resDto.setItemList ( posdebaggUpdList );

			} catch ( Throwable t ) {
				eventuallyThrowBusinessException ( t, methodName );
			}

			try {
				transaction.begin ();

				long numUpdatesPosizioni = gestioneFlussiDad.updateFlussoPosizioniDebitorieDaAggiornare ( resDto, false ) - 1; // sottrae l'update della testata
				// (-1)
				if ( numEsiti == numUpdatesPosizioni ) {
					log.info ( "aggiornamento esito elaborazione lista di carico effettuata" );

				} else {
					log.error ( "numero di esiti ricevuti:" + numEsiti + " diverso dal numero posizioni debitorie aggiornabili:" + numUpdatesPosizioni );
					throw new BusinessMismatchInNumberOfUpdatesException ( idFlusso, numUpdatesPosizioni, numEsiti );
				}

				transaction.commit ();
				logAuditOperation(CsiLogAuditOperationEnum.UPDATE, registraEsitoPosizioniDebitorieAggiornateRequestDto, "epaypa_t_flusso", "ID FLUSSO=" + idFlusso, "Registrazione Esito Aggiornamento Liste di Carico");

			} catch ( Throwable t ) {
				try {
					transaction.rollback ();

				} catch ( IllegalStateException | SecurityException | SystemException e ) {
					throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );
				}
				eventuallyThrowBusinessException ( t, methodName );
			}

		} finally {
			log.info ( "result:" +  resDto );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
	}

	@Override
	public Boolean isNumeroFlussiElaborabile(FlussoLightFilterDto filter, String codiceUtente)throws BusinessException
	{
		Long numeroTotaleFlussi=new Long(0);

		Long numeroMassimoFlussiElaborabili = getCountNumMaxFlussiReport();
		numeroTotaleFlussi= getCountTotaleFlussi(filter, codiceUtente);

		return new Boolean(numeroTotaleFlussi.compareTo(numeroMassimoFlussiElaborabili)<=0);

	}

	@Override
	public Long getCountNumMaxFlussiReport() throws BusinessException {
		Long numeroMassimoFlussiElaborabili=new Long(0);
		ConfigurazioneDto configurazioneDto;
		try {
			configurazioneDto = commonDad.findConfigurazioneByCodiceAndCodFiscaleEnte("NUM_MAX_RECORDS_REPORT", "PagoPa");
			if(( configurazioneDto!=null ) && ( configurazioneDto.getValore()!=null ) && ( NumberUtils.isNumber(configurazioneDto.getValore()) ) ) {
				numeroMassimoFlussiElaborabili = NumberUtils.toLong(configurazioneDto.getValore());
			}
		}
		catch (PersistenceException e) {
			throw new BusinessException ( "Errore nella ricerca del numero totale report elaborabili" );
		}
		return numeroMassimoFlussiElaborabili;

	}

	@Override
	public Long getCountTotaleFlussi(FlussoLightFilterDto filter, String codiceUtente)throws BusinessException {


		int flussiTotali =0;
		List<Long> idFlussi;
		try {
			idFlussi = gestioneFlussiDad.findIdFlussoByFilter(filter, codiceUtente);


			if(!CollectionUtils.isEmpty(idFlussi))
			{
				for (Long idFlusso : idFlussi) {
					flussiTotali = flussiTotali+countRiversamenti(idFlusso).intValue();
				}
			}
		} 
		catch (PersistenceException e) {
			throw new BusinessException ( "Errore nella ricerca dei flussi" );
		}
		return new Long(flussiTotali);
	}


	//@formatter:off
	private void checkNotNullTestataFlusso ( FlussoLightDto testataFlussoDto, TipoFlussoEnum tipoFlusso )
					throws BusinessTrasmettiFlussoTestataNullException {
		if ( testataFlussoDto == null ) {
			log.error ( "flusso " + tipoFlusso + " senza testata" );
			throw new BusinessTrasmettiFlussoTestataNullException ();
		}
	}

	private void checkNotNullCorpoFlusso ( List<?> corpoFlusso, TipoFlussoEnum tipoFlusso )
					throws BusinessTrasmettiFlussoCorpoNullException {
		if ( corpoFlusso == null ) {
			log.error ( "flusso" + tipoFlusso + " senza corpo" );
			throw new BusinessTrasmettiFlussoCorpoNullException ();
		}
	}

	private Integer checkCodFiscaleEnte ( String codFiscaleEnte )
					throws PersistenceException, BusinessNotFoundException {
		EnteDto enteDto = commonDad.findEnteByCodFiscale ( codFiscaleEnte );
		if ( enteDto == null ) {
			log.error ( "ente non trovato per codFiscaleEnte:" + codFiscaleEnte );
			throw new BusinessNotFoundException ( codFiscaleEnte, "Ente", "codFiscaleEnte" );
		}
		return enteDto.getId ();
	}

	private Integer checkEnteInTestataEsito ( TestataEsitoPosizioniDebitorieDto testataEsitoDto )
					throws BusinessTestataNullException, PersistenceException, BusinessNotFoundException {
		if ( testataEsitoDto == null ) {
			log.error ( "esito senza testata" );
			throw new BusinessTestataNullException ();
		}
		return checkCodFiscaleEnte ( testataEsitoDto.getCodFiscaleEnte () );
	}

	private CodiceVersamentoDto checkUniqueCodiceVersamento ( String codVersamento, Integer idEnte )
					throws PersistenceException, BusinessNotFoundException {
		CodiceVersamentoDto codiceVersamentoDto = commonDad.findCodiceVersamentoByCodAndEnte ( codVersamento, idEnte );
		if ( !commonDad.existsCodiceVersamentoByCodAndEnte ( codVersamento, idEnte ) ) {
			log.error ( "codice versamento non trovato per codVersamento:" + codVersamento + " e idEnte:" + idEnte );
			throw new BusinessNotFoundException ( "(" + codVersamento + "," + idEnte + ")", "CodiceVersamento", "(codVersamento,idEnte)" );
		}
		return codiceVersamentoDto;
	}

	private FlussoLightDto checkUniqueFlusso ( String idMessaggio, Integer idEnte, Integer idCodVersamento )
					throws PersistenceException, BusinessNotFoundException, BusinessFoundMoreThanOneResultException {
		FlussoLightFilterDto filter = new FlussoLightFilterDto ();
		filter.setIdMessaggio ( idMessaggio );
		filter.setIdEnte ( idEnte );
		filter.setIdCodVersamentoList ( Collections.singletonList ( idCodVersamento ) );

		List<FlussoLightDto> flussoLightList = gestioneFlussiDad.findAllFlussoLightByFilter ( filter, null, null, null );
		if ( flussoLightList.size () == 0 ) {
			log.error ( "flusso non trovato per idEnte:" + idEnte + ", idMessaggio:" + idMessaggio + ", idCodVersamento:" + idCodVersamento );
			throw new BusinessNotFoundException ( "[" + idEnte + ", " + idMessaggio + ", " + idCodVersamento + "]", "Flusso",
							"[idEnte, idMessaggio, idCodVersamento]" );
		}
		if ( flussoLightList.size () > 1 ) {
			log.error ( "flusso non univoco per idEnte:" + idEnte + ", idMessaggio:" + idMessaggio + ", idCodVersamento:" + idCodVersamento );
			throw new BusinessFoundMoreThanOneResultException ( "idEnte, idMessaggio, idCodVersamento", "Flusso",
				idEnte + ", " + idMessaggio + ", " + idCodVersamento );
		}

		return flussoLightList.get ( 0 );
	}

	private void checkStatoEsitoNonAncoraRicevuto ( FlussoLightDto flussoLightDto )
					throws PersistenceException, BusinessEsitoGiaRicevutoException {
		if ( flussoLightDto.getStatoFlusso () == StatoFlussoEnum.ESITO_RICEVUTO ) {
			Long idFlusso = flussoLightDto.getId ();
			log.error ( "ricevuto un esito che andrebbe a sovrascriverne uno preesistente per idFlusso:" + idFlusso );
			throw new BusinessEsitoGiaRicevutoException ( idFlusso );
		}
	}

	private void checkNumeroEsitiAttesiERicevuti ( Long idFlusso, int numEsitiAttesi, int numEsitiRicevuti )
					throws BusinessMismatchInNumberOfElementsException {
		if ( numEsitiRicevuti != numEsitiAttesi ) {
			log.error (
				"numero esiti ricevuti:" + numEsitiRicevuti + " diverso dal numero posizioni debitorie:" + numEsitiAttesi + " per idFlusso:" + idFlusso );
			throw new BusinessMismatchInNumberOfElementsException ( idFlusso, numEsitiAttesi, numEsitiRicevuti );
		}
	}

	private void checkRipetetizioneIdPosizioneDebitoriaEstInEsitoList ( Long idFlusso, List<EsitoPosizioneDebitoriaDto> esitoList )
					throws BusinessPosizioneDebitoriaRipetutaException {
		int numEsiti = esitoList.size ();
		for ( int i = 0; i < numEsiti; i++ ) {
			String idPosizioneDebitoriaEstI = esitoList.get ( i ).getIdPosizioneDebitoriaEsterna ();
			for ( int j = i + 1; j < numEsiti; j++ ) {
				String idPosizioneDebitoriaEstJ = esitoList.get ( j ).getIdPosizioneDebitoriaEsterna ();
				if ( idPosizioneDebitoriaEstJ.equals ( idPosizioneDebitoriaEstI ) ) {
					log.error ( "esiti ricevuti con idPosizioneDebitoriaEsterno ripetuto:" + idPosizioneDebitoriaEstJ + " per idFlusso:" + idFlusso );
					throw new BusinessPosizioneDebitoriaRipetutaException ( idFlusso, idPosizioneDebitoriaEstJ );
				}
			}
		}
	}

	private void checkRipetetizioneIUVInEsitoList ( Long idFlusso, List<EsitoPosizioneDebitoriaDto> esitoList )
					throws BusinessPosizioneDebitoriaRipetutaException {
		int numEsiti = esitoList.size ();
		for ( int i = 0; i < numEsiti; i++ ) {
			String idPosdebEstI = esitoList.get ( i ).getIdPosizioneDebitoriaEsterna ();
			for ( int j = i + 1; j < numEsiti; j++ ) {
				String idPosdebEstJ = esitoList.get ( j ).getIdPosizioneDebitoriaEsterna ();
				if ( idPosdebEstJ.equals ( idPosdebEstI ) ) {
					log.error ( "esiti ricevuti con idPosizioneDebitoriaEsterno ripetuto:" + idPosdebEstJ + " per idFlusso:" + idFlusso );
					throw new BusinessPosizioneDebitoriaRipetutaException ( idFlusso, idPosdebEstJ );
				}
			}
		}
	}
	//@formatter:on

	//@formatter:off
	/* DEV/CSI_PAG-2411 - BEGIN5 - non serve piu fare questo controllo perche la posizione debitoria viene ora generata automaticamente al volo
//	private boolean checkPosizioneDebitoriaUnivoca (
//		List<List<Object>> lines,
//		Map<ParseErrorEnum, List<ParseErrorFieldDto>> parseErrorFieldMap, String tipoFormato ) {
//		boolean ok = true;
//		log.debug ( "verifing unique posizione debitorie" );
//		if ( "CSV".equalsIgnoreCase ( tipoFormato ) ) {
//			String title = ( (String) lines.get ( 0 ).get ( 0 ) ).split ( "," ) [0];
//			for ( int j = 1; j < lines.size (); j++ ) {
//				String singleRow = ( (String) lines.get ( j ).get ( 0 ) ).split ( "," ) [0];
//				for ( int i = 1; i < lines.size (); i++ ) {
//					String singleLine = ( (String) lines.get ( i ).get ( 0 ) ).split ( "," ) [0];
//					if ( singleRow.equalsIgnoreCase ( singleLine ) && i != j ) {
//						addParseErrorFieldToMap ( ParseErrorEnum.PARSE_ERROR_DUPLICATE_POS_DEBITORIA, i, 0, title,
//							singleRow + "", parseErrorFieldMap );
//						ok = false;
//					}
//				}
//			}
//		} else {
//			String title = (String) lines.get ( 0 ).get ( 0 );
//			for ( int j = 1; j < lines.size (); j++ ) {
//				String singleRow = (String) lines.get ( j ).get ( 0 );
//				for ( int i = 1; i < lines.size (); i++ ) {
//					if ( singleRow.equalsIgnoreCase ( (String) lines.get ( i ).get ( 0 ) ) && i != j ) {
//						addParseErrorFieldToMap ( ParseErrorEnum.PARSE_ERROR_DUPLICATE_POS_DEBITORIA, i, 0, title,
//							singleRow + "", parseErrorFieldMap );
//						ok = false;
//					}
//				}
//			}
//		}
//		return ok;
//	}
	/* DEV/CSI_PAG-2411 - BEGIN5 */

	//@formatter:on


	private void logAuditOperation(CsiLogAuditOperationEnum operation, PrincipalDto principalDto, String tabella, String oggetto, String descrizione){

		try {
			transaction.begin ();
			csiLogAuditDad.logAuditOperation(operation, principalDto, tabella, oggetto, descrizione);

			//			switch (operation) {
			//			case ACCESS:
			//				csiLogAuditDad.access(principalDto, tabella, oggetto, descrizione);
			//				break;
			//			case ACCESS_DENIED:
			//				csiLogAuditDad.accessDenied(principalDto, tabella, oggetto, descrizione);
			//				break;
			//			case EXTRACT:
			//				csiLogAuditDad.extract(principalDto, tabella, oggetto, descrizione);
			//				break;
			//			case DELETE:
			//				csiLogAuditDad.delete(principalDto, tabella, oggetto, descrizione);
			//				break;
			//			case INSERT:
			//				csiLogAuditDad.insert(principalDto, tabella, oggetto, descrizione);
			//				break;
			//			case UPDATE:
			//				csiLogAuditDad.update(principalDto, tabella, oggetto, descrizione);
			//				break;
			//			default:
			//				break;
			//			}
			transaction.commit();
		} catch (Throwable t ) {
			try {
				transaction.rollback ();
			} catch (IllegalStateException | SecurityException | SystemException e) {
				log.error("["+CLASSNAME + " :: logAuditOperation] Errore durante il rollback della transazione ",e);
			}
			log.error("["+CLASSNAME + " :: logAuditOperation] Errore durante la chiamata ai metodi della classe CsiLogAuditDad",t);
		}
	}

	//    private enum CsiLogAuditOperation {
	//        INSERT,
	//        UPDATE, 
	//        DELETE,
	//        EXTRACT,
	//        ACCESS,
	//        ACCESS_DENIED; 
	//    }

	private boolean modificaDatiSensibili(SoggettoAnagraficoDto oldDto,SoggettoAnagraficoDto newDto) {
		if(oldDto!=null && newDto!=null) {
			//cognome
			if((oldDto.getCognome()!=null && !oldDto.getCognome().equals(newDto.getCognome()))||
							(newDto.getCognome()!=null && !newDto.getCognome().equals(oldDto.getCognome()))) {
				return true;
			}
			//nome
			if((oldDto.getNome()!=null && !oldDto.getNome().equals(newDto.getNome()))||
							(newDto.getNome()!=null && !newDto.getNome().equals(oldDto.getNome()))) {
				return true;
			}
			//CF
			if((oldDto.getIdUnivocoFiscale()!=null && !oldDto.getIdUnivocoFiscale().equals(newDto.getIdUnivocoFiscale()))||
							(newDto.getIdUnivocoFiscale()!=null && !newDto.getIdUnivocoFiscale().equals(oldDto.getIdUnivocoFiscale()))) {
				return true;
			}
			//ragione sociale
			if((oldDto.getRagioneSociale()!=null && !oldDto.getRagioneSociale().equals(newDto.getRagioneSociale()))||
							(newDto.getRagioneSociale()!=null && !newDto.getRagioneSociale().equals(oldDto.getRagioneSociale()))) {
				return true;
			}
			//indirizzo
			if((oldDto.getIndirizzo()!=null && !oldDto.getIndirizzo().equals(newDto.getIndirizzo()))||
							(newDto.getIndirizzo()!=null && !newDto.getIndirizzo().equals(oldDto.getIndirizzo()))) {
				return true;
			}
			//civico
			if((oldDto.getCivico()!=null && !oldDto.getCivico().equals(newDto.getCivico()))||
							(newDto.getCivico()!=null && !newDto.getCivico().equals(oldDto.getCivico()))) {
				return true;
			}
			//CAP
			if((oldDto.getCap()!=null && !oldDto.getCap().equals(newDto.getCap()))||
							(newDto.getCap()!=null && !newDto.getCap().equals(oldDto.getCap()))) {
				return true;
			}
			//localita
			if((oldDto.getLocalita()!=null && !oldDto.getLocalita().equals(newDto.getLocalita()))||
							(newDto.getLocalita()!=null && !newDto.getLocalita().equals(oldDto.getLocalita()))) {
				return true;
			}
			//provincia
			if((oldDto.getProvincia()!=null && !oldDto.getProvincia().equals(newDto.getProvincia()))||
							(newDto.getProvincia()!=null && !newDto.getProvincia().equals(oldDto.getProvincia()))) {
				return true;
			}
			//nazione
			if((oldDto.getNazione()!=null && !oldDto.getNazione().equals(newDto.getNazione()))||
							(newDto.getNazione()!=null && !newDto.getNazione().equals(oldDto.getNazione()))) {
				return true;
			}
			//email
			if((oldDto.getEmail()!=null && !oldDto.getEmail().equals(newDto.getEmail()))||
							(newDto.getEmail()!=null && !newDto.getEmail().equals(oldDto.getEmail()))) {
				return true;
			}
		}
		return false;
	}

	@Override
	public PosizioneDebitoriaAutocompleteDto getPosizioneDebitoriaAutoComplete ( String partialPosizioneDebitoria, Integer enteId,
		Integer idCodVersamento ) throws BusinessException {
		String methodName = "getPosizioneDebitoriaAutoComplete";

		PosizioneDebitoriaAutocompleteDto resList = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );
			resList = gestioneFlussiDad.getPosizioneDebitoriaAutoComplete ( partialPosizioneDebitoria, enteId, idCodVersamento );
		} catch ( Throwable t ) {
			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );
		} finally {
			log.info ( "result: " + resList );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return resList;
	}

	@Override public PosizioneDebitoriaAutocompleteIUVDto getPosizioneDebitoriaAutoCompleteIUV ( String partialIUV, Integer enteId, Integer idCodVersamento )
					throws BusinessException {
		String methodName = "getPosizioneDebitoriaAutoCompleteIUV";
		PosizioneDebitoriaAutocompleteIUVDto resList = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );
			resList = gestioneFlussiDad.getPosizioneDebitoriaIUVAutoComplete ( partialIUV, enteId, idCodVersamento );
		} catch ( Throwable t ) {
			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );
		} finally {
			log.info ( "result: " + resList );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return resList;
	}

	@Override
	public Long countAllPosizioneDebitoriaByIdEnteIdCovPosDebEst ( String posizioneDebitoriaEst, Integer enteId, Integer idCodVersamento )
					throws BusinessException {
		String methodName = "countAllPosizioneDebitoriaByIdEnteIdCovPosDebEst";
		

		Long res = 0L;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );
			res = gestioneFlussiDad.countAllPosizioneDebitoriaByIdEnteIdCovPosDebEst ( posizioneDebitoriaEst, enteId, idCodVersamento );
		} catch ( Throwable t ) {
			throw new BusinessException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, t );

		} finally {
			log.info ( "result:" + res );
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return res;
	}
}
