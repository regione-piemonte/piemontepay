/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dad.impl;

import it.csi.epay.epaypaweb.dto.AvvisoScadutoDto;
import it.csi.epay.epaypaweb.dto.ComponenteImportoDto;
import it.csi.epay.epaypaweb.dto.CriterioOrdinamentoDto;
import it.csi.epay.epaypaweb.dto.DatiSingolaRevocaDto;
import it.csi.epay.epaypaweb.dto.FlussoCompletoDto;
import it.csi.epay.epaypaweb.dto.FlussoDto;
import it.csi.epay.epaypaweb.dto.FlussoLightDto;
import it.csi.epay.epaypaweb.dto.FlussoLightFilterDto;
import it.csi.epay.epaypaweb.dto.NotificaPagamentoDto;
import it.csi.epay.epaypaweb.dto.NotificaPagamentoLightDto;
import it.csi.epay.epaypaweb.dto.PaginazioneDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaAutocompleteDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaAutocompleteElementDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaAutocompleteIUVDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaAutocompleteIUVElementDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaDaAggiornareDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaDaAggiornareLightDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaLightDto;
import it.csi.epay.epaypaweb.dto.RichiestaDiRevocaDto;
import it.csi.epay.epaypaweb.dto.RichiestaRevocheFilterDto;
import it.csi.epay.epaypaweb.dto.RiferimentoPagamentoDto;
import it.csi.epay.epaypaweb.dto.RiversamentoDto;
import it.csi.epay.epaypaweb.dto.SingolaRevocaDto;
import it.csi.epay.epaypaweb.dto.SoggettoAnagraficoDto;
import it.csi.epay.epaypaweb.dto.StatoFlussoAggregatoDto;
import it.csi.epay.epaypaweb.dto.TipoAggiornamentoPosizioneDebitoriaDto;
import it.csi.epay.epaypaweb.enumeration.ColumnNameAggPosizioneDebitoriaEnum;
import it.csi.epay.epaypaweb.enumeration.ColumnNameAvvisoScadutoEnum;
import it.csi.epay.epaypaweb.enumeration.ColumnNameFlussoEnum;
import it.csi.epay.epaypaweb.enumeration.ColumnNameNotificaPagamentoEnum;
import it.csi.epay.epaypaweb.enumeration.ColumnNamePosizioneDebitoriaEnum;
import it.csi.epay.epaypaweb.enumeration.ColumnNameRiversamentoEnum;
import it.csi.epay.epaypaweb.enumeration.TipoAggiornamentoPosizioneDebitoriaEnum;
import it.csi.epay.epaypaweb.enumeration.TipoFlussoEnum;
import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.integration.epaywsosrv.impl.EpayPaEpywsoSrvServiceImpl;
import it.csi.epay.epaypaweb.integration.epaywsosrv.interfaces.EpayPaEpaywsoSrvService;
import it.csi.epay.epaypaweb.persistence.dad.EPaypaDadBaseImpl;
import it.csi.epay.epaypaweb.persistence.dad.interf.GestioneFlussiDad;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaDStatoFlussoDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaDTipoAggPosizioneDebitoriaDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaDTipoFlussoDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaDTipoVersamentoDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTAggPosizioneDebitoriaDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTAggPosizioneDebitoriaLightDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTAvvisoScadutoDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTCodiceVersamentoDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTDatiSingolaRevocaDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTEnteDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTFlussoDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTFlussoLightDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTFlussoNotificaPagamentoDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTNotificaPagamentoDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTNotificaPagamentoLightDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTPosizioneDebitoriaDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTPosizioneDebitoriaForAutocompleteDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTPosizioneDebitoriaForAutocompleteIUVDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTPosizioneDebitoriaLightDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTRendicontazioneDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTRiversamentoDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTRrDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTTipoRevocaDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTAggPosizioneDebitoria;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTAggPosizioneDebitoriaLight;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTAvvisoScaduto;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTComponenteImporto;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTDatiSingolaRevoca;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTDatiSingolaRevocaId;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTFlusso;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTFlussoLight;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTNotificaPagamento;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTNotificaPagamentoLight;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTPosizioneDebitoria;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTPosizioneDebitoriaForAutocomplete;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTPosizioneDebitoriaLight;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTRendicontazione;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTRiferimentoPagamento;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTRiversamento;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTRr;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTSoggetto;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTTipoRevoca;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTTransazionePsp;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTposizioneDebitoriaForAutocompleteIUV;
import it.csi.epay.epaypaweb.persistence.entity.filter.EpaypaTFlussoFilter;
import it.csi.epay.epaypaweb.util.Costanti;
import it.csi.epay.epaypaweb.util.Util;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;

import static it.csi.epay.epaypaweb.enumeration.TipoFlussoEnum.AGGIORNAMENTO_POSIZIONI_DEBITORIE;


public class GestioneFlussiDadImpl extends EPaypaDadBaseImpl implements GestioneFlussiDad {

	private static final String CLASSNAME = GestioneFlussiDadImpl.class.getSimpleName ();

	private Properties config;
	@Inject
	private EpaypaTEnteDao epaypaTEnteDao;

	@Inject
	private EpaypaTCodiceVersamentoDao epaypaTCodiceVersamentoDao;

	@Inject
	private EpaypaDStatoFlussoDao epaypaDStatoFlussoDao;

	@Inject
	private EpaypaDTipoFlussoDao epaypaDTipoFlussoDao;

	@Inject
	private EpaypaDTipoVersamentoDao epaypaDTipoVersamentoDao;

	@Inject
	private EpaypaTFlussoDao epaypaTFlussoDao;

	//csi - 184
	@Inject
	private EpaypaTRrDao epaypaTRrDao;

	@Inject
	private EpaypaTFlussoNotificaPagamentoDao epaypaTFlussoNotificaPagamentoDao;

	@Inject
	private EpaypaTFlussoLightDao epaypaTFlussoLightDao;

	@Inject
	private EpaypaTNotificaPagamentoDao epaypaTNotificaPagamentoDao;

	@Inject
	private EpaypaTDatiSingolaRevocaDao epaypaTDatiSingolaRevocaDao;

	@Inject
	private EpaypaTNotificaPagamentoLightDao epaypaTNotificaPagamentoLightDao;

	@Inject
	private EpaypaTPosizioneDebitoriaDao epaypaTPosizioneDebitoriaDao;

	@Inject
	private EpaypaTPosizioneDebitoriaLightDao epaypaTPosizioneDebitoriaLightDao;

	@Inject
	private EpaypaDTipoAggPosizioneDebitoriaDao epaypaDTipoAggPosizioneDebitoriaDao;

	@Inject
	private EpaypaTAggPosizioneDebitoriaDao epaypaTAggPosizioneDebitoriaDao;

	@Inject
	private EpaypaTAggPosizioneDebitoriaLightDao epaypaTAggPosizioneDebitoriaLightDao;

	@Inject
	private EpaypaTAvvisoScadutoDao epaypaTAvvisoScadutoDao;

	@Inject
	private EpaypaTRendicontazioneDao epaypaTRendicontazioneDao;

	@Inject
	private EpaypaTRiversamentoDao epaypaTRiversamentoDao;

	@Inject
	private EpaypaTTipoRevocaDao epaypaTTipoRevocaDao;

	@Inject
	private EpaypaTPosizioneDebitoriaForAutocompleteDao epaypaTPosizioneDebitoriaForAutocompleteDao;

	@Inject
	private EpaypaTPosizioneDebitoriaForAutocompleteIUVDao epaypaTPosizioneDebitoriaForAutocompleteIUVDao;

	@Override
	public boolean existsFlussoById ( Long idFlusso ) throws PersistenceException {
		String methodName = "existsFlussoById";
		boolean exists;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );
			exists = ( epaypaTFlussoDao.findOne ( idFlusso ) != null );
		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
		return exists;
	}

	@Override
	public Long countAllFlussoByFilter ( FlussoLightFilterDto filter, String codUtente ) throws PersistenceException {
		String methodName = "countAllFlussoByFilter";
		Long num;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );
			num = epaypaTFlussoDao.countAllByFilter ( filter, codUtente );
		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
		return num;
	}

	@Override
	public Long countAllFlussoRevocheByFilter ( RichiestaRevocheFilterDto filter, String codUtente ) throws PersistenceException {
		String methodName = "countAllFlussoRevocheByFilter";
		Long num;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );
			num = epaypaTRrDao.countAllByFilter ( filter, codUtente );
		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
		return num;

	}

	//--------------------------------//
	//RDI-048 - START
	//--------------------------------//
	@Override
	public Long countAllFlussoNotificheByFilter ( FlussoLightFilterDto filter, String codUtente ) throws PersistenceException {
		String methodName = "countAllFlussoNotificheByFilter";
		Long num;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );
			num = epaypaTFlussoNotificaPagamentoDao.countAllByFilter ( filter, codUtente );
		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
		return num;
	}

	//--------------------------------//
	//RDI-048 - STOP
	//--------------------------------//
	@Override
	public List<RichiestaRevocheFilterDto> findAllFlussoRevocheByFilter ( RichiestaRevocheFilterDto filter, PaginazioneDto pag, String codUtente )
					throws PersistenceException {
		String methodName = "findAllFlussoRevocheByFilter";
		List<RichiestaRevocheFilterDto> dtoList;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );
			List<EpaypaTRr> entityList = epaypaTRrDao.findAllLightByFilter ( filter, pag, codUtente );
			dtoList = toFlussoLightDtoListRevochePagamento ( entityList );
		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
		return dtoList;
	}

	@Override
	public List<Long> findIdFlussoByFilter(FlussoLightFilterDto filter, String codUtente) throws PersistenceException {
		String methodName = "findIdFlussoByFilter";
		List<Long> idFlussi;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );
			idFlussi = epaypaTFlussoLightDao.findIdFlussoByFilter(filter, codUtente);
		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
		return idFlussi;
	}

	@Override
	public List<FlussoLightDto> findAllFlussoLightByFilter ( FlussoLightFilterDto filter, List<CriterioOrdinamentoDto<ColumnNameFlussoEnum>> ord,
		PaginazioneDto pag, String codUtente ) throws PersistenceException {
		String methodName = "findAllFlussoLightByFilter";
		List<FlussoLightDto> dtoList;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );
			List<EpaypaTFlussoLight> entityList;
			if ( null != filter.getTipoFlusso () && filter.getTipoFlusso ().equals ( AGGIORNAMENTO_POSIZIONI_DEBITORIE ) ) {
				List<EpaypaTFlussoFilter> epaypaTFlussoFilters = epaypaTFlussoLightDao.findAllbyFilter ( filter, ord, pag, codUtente );
				entityList = toEpaypaTFlussoLightList ( epaypaTFlussoFilters );
			} else {
				entityList = epaypaTFlussoLightDao.findAllLightByFilter ( filter, ord, pag, codUtente );
			}
			dtoList = toFlussoLightDtoList ( entityList );
		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
		return dtoList;
	}

	//--------------------------------//
	//RDI-048 - START
	//--------------------------------//
	@Override
	public List<FlussoLightDto> findAllFlussoNotificheByFilter ( FlussoLightFilterDto filter, List<CriterioOrdinamentoDto<ColumnNameNotificaPagamentoEnum>> ord,
		PaginazioneDto pag, String codUtente )
						throws PersistenceException {
		String methodName = "findAllFlussoLightByFilter";
		List<FlussoLightDto> dtoList;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );
			List<EpaypaTNotificaPagamento> entityList = epaypaTFlussoNotificaPagamentoDao.findAllLightByFilter ( filter, ord, pag, codUtente );
			dtoList = toFlussoLightDtoListNotificaPagamento ( entityList );
		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
		return dtoList;
	}

	//--------------------------------//
	//RDI-048 - STOP
	//--------------------------------//

	@Override
	public FlussoLightDto findFlussoLightById ( Long idFlusso ) throws PersistenceException {
		String methodName = "findFlussoLightById";
		FlussoLightDto dto;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );
			EpaypaTFlussoLight entity = epaypaTFlussoLightDao.findOne ( idFlusso );
			dto = toFlussoLightDto ( entity );
		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
		return dto;
	}

	@Override
	public FlussoCompletoDto<NotificaPagamentoDto> findFlussoNotifichePagamento ( Long idFlusso ) throws PersistenceException {
		String methodName = "findFlussoNotifichePagamento";
		FlussoCompletoDto<NotificaPagamentoDto> dto = null;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			List<EpaypaTNotificaPagamento> entityList = epaypaTNotificaPagamentoDao.findAllByIdFlusso ( idFlusso );
			log.debug ( "Notifiche pagamento trovate:" + entityList.size () );

			List<NotificaPagamentoDto> dtoList = new ArrayList<> ();

			if ( entityList.isEmpty () ) {
				dto = new FlussoCompletoDto<> ();
				dto.setFlusso ( toFlussoDto ( epaypaTFlussoDao.findOne ( idFlusso ) ) );

			} else {
				for ( EpaypaTNotificaPagamento entity: entityList ) {
					if ( dto == null ) {
						dto = new FlussoCompletoDto<> ();
						dto.setFlusso ( toFlussoDto ( entity.getEpaypaTFlusso () ) );
					}
					dtoList.add ( toNotificaPagamentoDto ( entity ) );
				}
			}

			dto.setItemList ( dtoList );

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return dto;
	}

	private void toFlussoEntity ( FlussoDto dto, EpaypaTFlusso entity, Timestamp timestamp ) throws PersistenceException {
		if ( dto != null && entity != null ) {
			entity.setIdMessaggio ( dto.getIdMessaggio () );
			entity.setEpaypaTEnte ( epaypaTEnteDao.findOneByCodFiscale ( dto.getCodFiscaleEnte () ) );
			if ( dto.getTipoFlusso () != null ) {
				entity.setEpaypaDTipoFlusso ( epaypaDTipoFlussoDao.findOne ( dto.getTipoFlusso ().getId () ) );
			}
			if ( dto.getStatoFlusso () != null ) {
				entity.setEpaypaDStatoFlusso ( epaypaDStatoFlussoDao.findOne ( dto.getStatoFlusso ().getId () ) );
			}
			if ( dto.getCodVersamento () != null ) {
				entity.setEpaypaTCodiceVersamento (
					epaypaTCodiceVersamentoDao.findOneByCodAndEnte ( dto.getCodVersamento (), entity.getEpaypaTEnte ().getIdEnte () ) );
			}
			entity.setNumeroElementi ( dto.getNumeroElementi () );
			entity.setImportoTotale ( dto.getImportoTotale () );
			entity.setPagamentiSpontanei ( dto.getPagamentiSpontanei () );
			entity.setDtInserimento ( timestamp );
			entity.setDtUltimaVariazione ( timestamp );
			entity.setUtenteInserimento ( dto.getUtenteInserimento () );
			entity.setUtenteUltimaVariazione ( dto.getUtenteUltimaVariazione () );

			// N.B. codEsito e dettaglioEsito vanno eventualmente impostati all'esterno, a seconda dei casi
		}
	}

	//@formatter:off
	private void toFlussoRendicontazioneEntity ( FlussoDto dto, EpaypaTFlusso flussoEntity, EpaypaTRendicontazione rendicontazioneEntity, Timestamp timestamp )
					throws PersistenceException {
		//@formatter:on
		if ( dto != null && flussoEntity != null && rendicontazioneEntity != null ) {
			toFlussoEntity ( dto, flussoEntity, timestamp );
			//
			rendicontazioneEntity.setEpaypaTFlusso ( flussoEntity );
			rendicontazioneEntity.setDtOraFlusso ( toTimestamp ( dto.getDataOraCreazioneFlusso () ) );
			rendicontazioneEntity.setIdUnivocoRegolamento ( dto.getIdUnivocoRegolamento () );
			rendicontazioneEntity.setDtRegolamento ( toTimestamp ( dto.getDataRegolamento () ) );
			if ( dto.getTipoMittente () != null ) {
				rendicontazioneEntity.setTipoIdMittente ( dto.getTipoMittente ().getId () );
			}
			rendicontazioneEntity.setCodIdUnivocoMittente ( dto.getCodIdUnivocoMittente () );
			rendicontazioneEntity.setDenominazioneMittente ( dto.getDenominazioneMittente () );
			rendicontazioneEntity.setCodBicPsp ( dto.getCodBicPsp () );
			rendicontazioneEntity.setIdFlussoRendicontazione ( dto.getIdFlussoRendicontazione () );
			//
			flussoEntity.setEpaypaTRendicontazione ( rendicontazioneEntity );
		}
	}

	@Override
	public Long insertFlussoNotifichePagamento ( FlussoCompletoDto<NotificaPagamentoDto> dto, Timestamp timestamp ) throws PersistenceException {
		String methodName = "insertFlussoNotifichePagamento";
		Long id = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			if ( dto != null ) {
				// crea e inserisce la testata
				EpaypaTFlusso flussoEntity = new EpaypaTFlusso ();
				toFlussoEntity ( dto.getFlusso (), flussoEntity, timestamp );
				flussoEntity.setCodEsito ( Util.COD_ESITO_OK );
				flussoEntity.setDettaglioEsito ( Util.DET_ESITO_OK );

				// inserisce il corpo
				if ( dto.getItemList () != null ) {
					List<EpaypaTNotificaPagamento> notificaPagamentoEntityList = new ArrayList<EpaypaTNotificaPagamento>();
					//
					for ( NotificaPagamentoDto notificaDto: dto.getItemList () ) {

						EpaypaTNotificaPagamento notificaPagamentoEntity = insertNotificaPagamento ( notificaDto, flussoEntity );
						if ( notificaPagamentoEntity != null ) {
							notificaPagamentoEntityList.add ( notificaPagamentoEntity );
						}
					}

					// collega il corpo alla testata, popola la testata e persiste il tutto
					flussoEntity.setEpaypaTNotificaPagamentos ( notificaPagamentoEntityList );
				}

				epaypaTFlussoDao.persist ( flussoEntity ); // N.B. in caso d'errore, fa rollback totale -> non ci saranno mai flussi con cod esito non ACQUISITO/OK
				id = flussoEntity.getIdFlusso ();
			}
		} catch ( Throwable e ) {
			throw new PersistenceException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return id;
	}

	@SuppressWarnings ( "deprecation" )
	@Override
	public Long insertRR ( RichiestaDiRevocaDto richiestaRevoca, EpaypaTTipoRevoca tipoRevocaEntity ) throws PersistenceException {
		EpaypaTRr transazioneTtRrEntity = new EpaypaTRr ();

		transazioneTtRrEntity.setApplicationId ( richiestaRevoca.getApplicationId () );
		transazioneTtRrEntity.setCodiceContestoPagamento ( richiestaRevoca.getCodiceContestoPagamento () );
		transazioneTtRrEntity.setCodiceIdentificativoUnivocoAttestante ( richiestaRevoca.getCodiceContestoPagamento () );

		XMLGregorianCalendar cal = richiestaRevoca.getDataOraMessaggioRevoca ();
		Timestamp ts = new Timestamp ( cal.getYear (), cal.getMonth (), cal.getDay (), cal.getHour (), cal.getMinute (), cal.getSecond (), 0 );
		transazioneTtRrEntity.setDataOraMessaggioRevoca ( ts );

		transazioneTtRrEntity.setDenominazioneIstitutoAttestante ( richiestaRevoca.getIstitutoAttestante ().getRagioneSociale () );
		transazioneTtRrEntity.setIdentificativoMessaggioRevoca ( richiestaRevoca.getIdentificativoMessaggioRevoca () );
		transazioneTtRrEntity.setIuv ( richiestaRevoca.getIUV () );
		transazioneTtRrEntity.setIdentificativoDominio ( richiestaRevoca.getIdentificativoDominio () );

		transazioneTtRrEntity.setTipoRevoca ( tipoRevocaEntity.getId () );
		transazioneTtRrEntity.setImportoTotaleTevocato ( richiestaRevoca.getImportoPagato () );
		transazioneTtRrEntity.setXmlRr ( richiestaRevoca.getXml () );

		//      transazioneTtRrEntity.setIdRr ( generateUniqueId()    );

		epaypaTRrDao.persist ( transazioneTtRrEntity );
		return transazioneTtRrEntity.getIdRr ();
	}

	@Override

	public void insertDettaglioRR ( DatiSingolaRevocaDto dettagliRevoca, Long idRr ) throws PersistenceException {
		EpaypaTDatiSingolaRevoca singolaRevoca = new EpaypaTDatiSingolaRevoca ();
		EpaypaTDatiSingolaRevocaId transazioneTtDettaglioRevoca = new EpaypaTDatiSingolaRevocaId ();
		transazioneTtDettaglioRevoca.setCasualeRevoca ( dettagliRevoca.getCausale () );
		transazioneTtDettaglioRevoca.setDatiAggiuntiRevoca ( dettagliRevoca.getDatiAggiuntivi () );
		transazioneTtDettaglioRevoca.setIur ( dettagliRevoca.getIur () );
		transazioneTtDettaglioRevoca.setSingoloImportoRevocato ( dettagliRevoca.getImporto () );
		transazioneTtDettaglioRevoca.setIdRr ( idRr );
		singolaRevoca.setEpaypaTDatiSingolaRevocaId ( transazioneTtDettaglioRevoca );
		epaypaTDatiSingolaRevocaDao.persist ( singolaRevoca );
	}

	public static long generateUniqueId () {
		UUID idOne = UUID.randomUUID ();
		String str = "" + idOne;
		long uid = str.hashCode ();
		String filterStr = "" + uid;
		str = filterStr.replaceAll ( "-", "" );
		return Long.parseLong ( str );
	}

	public static String generateUniqueIdString () {
		UUID idOne = UUID.randomUUID ();
		String str = "" + idOne;
		long uid = str.hashCode ();
		String filterStr = "" + uid;
		str = filterStr.replaceAll ( "-", "" );
		return str;
	}

	@Override
	public EpaypaTTipoRevoca getEpaypaTTipoRevocaById ( int id ) throws PersistenceException {
		return epaypaTTipoRevocaDao.findTipoRevocaById ( id );
	}

	private EpaypaTNotificaPagamento insertNotificaPagamento ( NotificaPagamentoDto notificaDto, EpaypaTFlusso flussoEntity ) throws PersistenceException {
		EpaypaTNotificaPagamento notificaPagamentoEntity = null;

        // se ho gia' registrato una notifica di pagamento non ne registro altre.
        if ( notificaDto != null && epaypaTNotificaPagamentoDao.countAllByIUV ( notificaDto.getIUV () ) <= 0l ) {
            // transazione PSP
            EpaypaTTransazionePsp transazionePspEntity = new EpaypaTTransazionePsp ();
            //            transazionePspEntity.setIdNotificaPagamento ( idNotifica );
            transazionePspEntity.setIdPsp ( notificaDto.getIdPsp () != null ? notificaDto.getIdPsp () : generateUniqueIdString () );
            transazionePspEntity.setRagioneSocialePsp ( notificaDto.getRagioneSocialePsp () );
            if ( notificaDto.getCodTipoVersamento () != null ) {
                transazionePspEntity.setEpaypaDTipoVersamento ( epaypaDTipoVersamentoDao.findOneByCod ( notificaDto.getCodTipoVersamento () ) );
            }
            transazionePspEntity.setIdFlussoRendicontazione ( notificaDto.getIdFlussoRendicontazionePsp () );
            if ( notificaDto.getDataAvvioTransazione () != null ) {
                transazionePspEntity.setDtAvvioTransazione ( toTimestamp ( notificaDto.getDataAvvioTransazione () ) );
            } else {
                transazionePspEntity.setDtAvvioTransazione ( new Timestamp ( System.currentTimeMillis () ) );
            }
            transazionePspEntity.setIur ( notificaDto.getIUR () );
            if ( notificaDto.getDataOraAutorizzazione () != null ) {
                transazionePspEntity.setDtAutorizzazione ( toTimestamp ( notificaDto.getDataOraAutorizzazione () ) );
            }
            transazionePspEntity.setTipoSicurezza ( notificaDto.getTipoSicurezza () );
            transazionePspEntity.setImportoTransato ( notificaDto.getImportoTransato () );
            transazionePspEntity.setImportoCommissioni ( notificaDto.getImportoCommissioni () );

            // notifica pagamento
            notificaPagamentoEntity = new EpaypaTNotificaPagamento ();
            notificaPagamentoEntity.setIdPosizioneDebitoria ( notificaDto.getIdPosizioneDebitoria () );
            notificaPagamentoEntity.setAnnoRiferimento ( notificaDto.getAnnoRiferimento () );
            notificaPagamentoEntity.setCodiceAvviso ( notificaDto.getCodAvviso () );
            notificaPagamentoEntity.setIuv ( notificaDto.getIUV () );
            notificaPagamentoEntity.setImportoPagato ( notificaDto.getImportoPagato () );
            if ( notificaDto.getDataScadenza () != null ) {
                notificaPagamentoEntity.setDtScadenza ( toTimestamp ( notificaDto.getDataScadenza () ) );
            }
            notificaPagamentoEntity.setDesCausaleVersamento ( notificaDto.getDesCausaleVersamento () );
            if ( notificaDto.getDataEsitoPagamento () != null ) {
                notificaPagamentoEntity.setDtEsitoPagamento ( toTimestamp ( notificaDto.getDataEsitoPagamento () ) );
            }
            notificaPagamentoEntity.setNote ( notificaDto.getNote () );
            //            notificaPagamentoEntity.setDatiSpecificiRiscossione ( notificaDto.getDatiSpecificiRiscossione () );
            notificaPagamentoEntity.setIdNotificaPagamento ( notificaDto.getId () );//csi 184

            notificaPagamentoEntity.setEpaypaTSoggettoDebitore ( newEpaypaTSoggetto ( notificaDto.getSoggettoDebitore () ) );
            notificaPagamentoEntity.setEpaypaTSoggettoVersante ( newEpaypaTSoggetto ( notificaDto.getSoggettoVersante () ) );
            notificaPagamentoEntity.setEpaypaTTransazionePsp ( transazionePspEntity );
            notificaPagamentoEntity.setEpaypaTFlusso ( flussoEntity );

            transazionePspEntity.setEpaypaTNotificaPagamento ( notificaPagamentoEntity );
        }
        return notificaPagamentoEntity;
    }

	private EpaypaTSoggetto newEpaypaTSoggetto ( SoggettoAnagraficoDto dto ) {
		EpaypaTSoggetto entity = null;
		if ( dto != null ) {
			entity = new EpaypaTSoggetto ();
			entity.setIdUnivocoFiscale ( dto.getIdUnivocoFiscale () );
			if ( dto.getTipologiaSoggettoEnum () != null ) {
				entity.setTipologiaSoggetto ( dto.getTipologiaSoggettoEnum ().getId () );
				switch ( dto.getTipologiaSoggettoEnum () ) {
				case PERSONA_FISICA :
					entity.setCognomeRagSoc ( dto.getCognome () );
					break;
				case PERSONA_GIURIDICA :
					entity.setCognomeRagSoc ( dto.getRagioneSociale () );
					break;
				}
			}
			entity.setNome ( dto.getNome () );
			entity.setIndirizzo ( dto.getIndirizzo () );
			entity.setCivico ( dto.getCivico () );
			entity.setCap ( dto.getCap () );
			entity.setLocalita ( dto.getLocalita () );
			entity.setProvincia ( dto.getProvincia () );
			entity.setNazione ( dto.getNazione () );
			entity.setEmail ( dto.getEmail () );
		}
		return entity;
	}

	@Override
	public long updateFlussoNotifichePagamento ( List<NotificaPagamentoDto> itemList ) throws PersistenceException {
		long numUpdates = 0;
		if ( itemList != null ) {
			for ( NotificaPagamentoDto notificaDtoI: itemList ) {

				Long idNotificaPagamentoI = notificaDtoI.getId ();

			}
		}

		return numUpdates;
	}

	@Override
	public long updateFlussoNotifichePagamento ( FlussoCompletoDto<NotificaPagamentoDto> dto ) throws PersistenceException {
		String methodName = "updateFlussoNotifichePagamento";
		
		

		long numUpdates = 0L; // numero degli aggiornamenti dato dall'aggiornamento della testata (1) + numero delle notifiche aggiornate

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			if ( dto != null ) {

				FlussoDto flussoDto = dto.getFlusso ();
				if ( flussoDto != null ) {

					EpaypaTFlusso flussoEntity = epaypaTFlussoDao.findOne ( flussoDto.getId () );
					if ( flussoEntity != null ) {

						// aggiorna la testata
						updateTestataFlusso ( flussoDto, flussoEntity );
						numUpdates++;

						// aggiorna il corpo (notifica pagamenti e transazioni psp)
						if ( dto.getItemList () != null ) {
							for ( NotificaPagamentoDto notificaDtoI: dto.getItemList () ) {

								if ( notificaDtoI != null ) {
									Long idNotificaPagamentoI = notificaDtoI.getId ();

									// ricerca degli entity e loro modifica
									boolean found = false;
									for ( EpaypaTNotificaPagamento notificaPagamentoEntityJ: flussoEntity.getEpaypaTNotificaPagamentos () ) {

										if ( idNotificaPagamentoI != null
														&& idNotificaPagamentoI.equals ( notificaPagamentoEntityJ.getIdNotificaPagamento () ) ) {
											found = true;
											updateNotificaPagamento ( notificaDtoI, notificaPagamentoEntityJ );
											numUpdates++;
											break;
										}
									}
									if ( !found ) {
										// notifica di pagamento nuova, da inserire
										EpaypaTNotificaPagamento notificaPagamentoEntityNew = insertNotificaPagamento ( notificaDtoI, flussoEntity );
										if ( notificaPagamentoEntityNew != null ) {
											flussoEntity.getEpaypaTNotificaPagamentos ().add ( notificaPagamentoEntityNew );
											numUpdates++;
										}
									}
								}
							}
						}
					}

					epaypaTFlussoDao.merge ( flussoEntity ); // N.B. in caso d'errore, fa rollback totale
				}
			}
		} catch ( Throwable e ) {
			throw new PersistenceException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return numUpdates;
	}

	private void updateNotificaPagamento ( NotificaPagamentoDto notificaDto, EpaypaTNotificaPagamento notificaPagamentoEntity ) throws PersistenceException {
		if ( notificaDto != null ) {
			if ( notificaDto.getIdPosizioneDebitoria () != null ) {
				notificaPagamentoEntity.setIdPosizioneDebitoria ( notificaDto.getIdPosizioneDebitoria () );
			}
			if ( notificaDto.getAnnoRiferimento () != null ) {
				notificaPagamentoEntity.setAnnoRiferimento ( notificaDto.getAnnoRiferimento () );
			}
			if ( notificaDto.getIUV () != null ) {
				notificaPagamentoEntity.setIuv ( notificaDto.getIUV () );
			}
			if ( notificaDto.getImportoPagato () != null ) {
				notificaPagamentoEntity.setImportoPagato ( notificaDto.getImportoPagato () );
			}
			if ( notificaDto.getDataScadenza () != null ) {
				notificaPagamentoEntity.setDtScadenza ( toTimestamp ( notificaDto.getDataScadenza () ) );
			}
			if ( notificaDto.getDesCausaleVersamento () != null ) {
				notificaPagamentoEntity.setDesCausaleVersamento ( notificaDto.getDesCausaleVersamento () );
			}
			if ( notificaDto.getDataEsitoPagamento () != null ) {
				notificaPagamentoEntity.setDtEsitoPagamento ( toTimestamp ( notificaDto.getDataEsitoPagamento () ) );
			}
			if ( notificaDto.getNote () != null ) {
				notificaPagamentoEntity.setNote ( notificaDto.getNote () );
			}

			if ( notificaPagamentoEntity.getEpaypaTSoggettoDebitore () == null ) {
				notificaPagamentoEntity.setEpaypaTSoggettoDebitore ( new EpaypaTSoggetto () );
			}
			updateEpaypaTSoggetto ( notificaDto.getSoggettoDebitore (), notificaPagamentoEntity.getEpaypaTSoggettoDebitore () );

			if ( notificaPagamentoEntity.getEpaypaTSoggettoDebitore () == null ) {
				notificaPagamentoEntity.setEpaypaTSoggettoDebitore ( new EpaypaTSoggetto () );
			}
			updateEpaypaTSoggetto ( notificaDto.getSoggettoVersante (), notificaPagamentoEntity.getEpaypaTSoggettoVersante () );

			EpaypaTTransazionePsp transazionePspEntityJ = notificaPagamentoEntity.getEpaypaTTransazionePsp ();
			if ( notificaDto.getIdPsp () != null ) {
				transazionePspEntityJ.setIdPsp ( notificaDto.getIdPsp () );
			}
			if ( notificaDto.getRagioneSocialePsp () != null ) {
				transazionePspEntityJ.setRagioneSocialePsp ( notificaDto.getRagioneSocialePsp () );
			}
			if ( notificaDto.getCodTipoVersamento () != null ) {
				transazionePspEntityJ.setEpaypaDTipoVersamento ( epaypaDTipoVersamentoDao.findOneByCod ( notificaDto.getCodTipoVersamento () ) );
			}
			if ( notificaDto.getIdFlussoRendicontazionePsp () != null ) {
				transazionePspEntityJ.setIdFlussoRendicontazione ( notificaDto.getIdFlussoRendicontazionePsp () );
			}
			if ( notificaDto.getDataAvvioTransazione () != null ) {
				transazionePspEntityJ.setDtAvvioTransazione ( toTimestamp ( notificaDto.getDataAvvioTransazione () ) );
			}
			if ( notificaDto.getIUR () != null ) {
				transazionePspEntityJ.setIur ( notificaDto.getIUR () );
			}
			if ( notificaDto.getDataOraAutorizzazione () != null ) {
				transazionePspEntityJ.setDtAutorizzazione ( toTimestamp ( notificaDto.getDataOraAutorizzazione () ) );
			}
			if ( notificaDto.getTipoSicurezza () != null ) {
				transazionePspEntityJ.setTipoSicurezza ( notificaDto.getTipoSicurezza () );
			}
			if ( notificaDto.getImportoTransato () != null ) {
				transazionePspEntityJ.setImportoTransato ( notificaDto.getImportoTransato () );
			}
			if ( notificaDto.getImportoCommissioni () != null ) {
				transazionePspEntityJ.setImportoCommissioni ( notificaDto.getImportoCommissioni () );
			}
		}
	}

	private void updateEpaypaTSoggetto ( SoggettoAnagraficoDto dto, EpaypaTSoggetto entity ) {
		if ( dto != null ) {
			if ( dto.getIdUnivocoFiscale () != null ) {
				entity.setIdUnivocoFiscale ( dto.getIdUnivocoFiscale () );
			}
			if ( dto.getTipologiaSoggettoEnum () != null ) {
				entity.setTipologiaSoggetto ( dto.getTipologiaSoggettoEnum ().getId () );
				switch ( dto.getTipologiaSoggettoEnum () ) {
				case PERSONA_FISICA :
					entity.setCognomeRagSoc ( dto.getCognome () );
					break;
				case PERSONA_GIURIDICA :
					entity.setCognomeRagSoc ( dto.getRagioneSociale () );
					break;
				}
			}
			if ( dto.getNome () != null ) {
				entity.setNome ( dto.getNome () );
			}
			if ( dto.getIndirizzo () != null ) {
				entity.setIndirizzo ( dto.getIndirizzo () );
			}
			if ( dto.getCivico () != null ) {
				entity.setCivico ( dto.getCivico () );
			}
			if ( dto.getCap () != null ) {
				entity.setCap ( dto.getCap () );
			}
			if ( dto.getLocalita () != null ) {
				entity.setLocalita ( dto.getLocalita () );
			}
			if ( dto.getProvincia () != null ) {
				entity.setProvincia ( dto.getProvincia () );
			}
			if ( dto.getNazione () != null ) {
				entity.setNazione ( dto.getNazione () );
			}
			if ( dto.getEmail () != null ) {
				entity.setEmail ( dto.getEmail () );
			}
		}
	}

	@Override
	public Long countAllNotificaPagamentoByIdFlusso ( Long idFlusso ) throws PersistenceException {
		String methodName = "countAllNotificaPagamentoByIdFlusso";
		
		

		Long num = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			num = epaypaTNotificaPagamentoDao.countAllByIdFlusso ( idFlusso );

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return num;
	}

	@Override
	//@formatter:off
	public List<NotificaPagamentoLightDto> findAllNotificaPagamentoLightByIdFlusso ( Long idFlusso,
		List<CriterioOrdinamentoDto<ColumnNameNotificaPagamentoEnum>> ord, PaginazioneDto pag )
						throws PersistenceException {
		//@formatter:on
		String methodName = "findAllNotificaPagamentoLightByIdFlusso";
		
		
		
		

		List<NotificaPagamentoLightDto> dtoList = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			List<EpaypaTNotificaPagamentoLight> entityList = epaypaTNotificaPagamentoLightDao.findAllLightByIdFlusso ( idFlusso, ord, pag );
			dtoList = toNotificaPagamentoLightDtoList ( entityList );

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return dtoList;
	}
	//@formatter:off

	@Override
	public NotificaPagamentoLightDto findNotificaPagamentoLightById ( Long idNotificaPagamento ) throws PersistenceException {
		String methodName = "findNotificaPagamentoLightById";
		
		

		NotificaPagamentoLightDto dto = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			EpaypaTNotificaPagamentoLight entity = epaypaTNotificaPagamentoLightDao.findOne ( idNotificaPagamento );
			dto = toNotificaPagamentoLightDto ( entity );

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return dto;
	}

	@Override
	public NotificaPagamentoDto findNotificaPagamentoById ( Long idNotificaPagamento ) throws PersistenceException {
		String methodName = "findNotificaPagamentoById";
		
		

		NotificaPagamentoDto dto = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			EpaypaTNotificaPagamento entity = epaypaTNotificaPagamentoDao.findOne ( idNotificaPagamento );
			dto = toNotificaPagamentoDto ( entity );

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return dto;
	}

	@Override
	public List<SingolaRevocaDto> singolaRevocaByIdRr ( Long idRr ) throws PersistenceException {
		String methodName = "singolaRevocaByIdRr";
		
		

		List<SingolaRevocaDto> dto = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			List<EpaypaTDatiSingolaRevoca> entity = epaypaTDatiSingolaRevocaDao.findAllByIdRr ( idRr );
			dto = toSingolaRevocaDtoList ( entity );

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return dto;

	}

	@Override
	public FlussoCompletoDto<PosizioneDebitoriaDto> findFlussoPosizioniDebitorie ( Long idFlusso ) throws PersistenceException {
		String methodName = "findFlussoPosizioniDebitorie";
		
		

		FlussoCompletoDto<PosizioneDebitoriaDto> dto = null;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			List<EpaypaTPosizioneDebitoria> entityList = epaypaTPosizioneDebitoriaDao.findAllByIdFlusso ( idFlusso );
			log.debug ( "Posizioni debitorie trovate:" + entityList.size () );

			List<PosizioneDebitoriaDto> dtoList = new ArrayList<PosizioneDebitoriaDto> ();

			if ( entityList.isEmpty () ) {
				dto = new FlussoCompletoDto<PosizioneDebitoriaDto>();
				dto.setFlusso ( toFlussoDto ( epaypaTFlussoDao.findOne ( idFlusso ) ) );

			} else {
				for ( EpaypaTPosizioneDebitoria entity: entityList ) {
					if ( dto == null ) {
						dto = new FlussoCompletoDto<PosizioneDebitoriaDto>();
						dto.setFlusso ( toFlussoDto ( entity.getEpaypaTFlusso () ) );
					}
					dtoList.add ( toPosizioneDebitoriaDto ( entity ) );
				}
			}

			dto.setItemList ( dtoList );
		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return dto;
	}

	@Override
	public Long insertFlussoPosizioniDebitorie ( FlussoCompletoDto<PosizioneDebitoriaDto> dto, Timestamp timestamp ) throws PersistenceException {
		String methodName = "insertFlussoPosizioniDebitorie";
		
		
		

		Long id = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			if ( dto != null ) {
				// crea e inserisce la testata
				EpaypaTFlusso flussoEntity = new EpaypaTFlusso ();
				toFlussoEntity ( dto.getFlusso (), flussoEntity, timestamp );

				// inserisce il corpo
				if ( dto.getItemList () != null ) {
					List<EpaypaTPosizioneDebitoria> posdebEntityList = new ArrayList<EpaypaTPosizioneDebitoria>();
					//
					for ( PosizioneDebitoriaDto posdebDto: dto.getItemList () ) {
						EpaypaTPosizioneDebitoria posdebEntity = insertPosizioneDebitoria ( posdebDto, flussoEntity );
						if ( posdebEntity != null ) {
							posdebEntityList.add ( posdebEntity );
						}
					}

					// collega il corpo alla testata, popola la testata e persiste il tutto
					flussoEntity.setEpaypaTPosizioneDebitorias ( posdebEntityList );
				}

				epaypaTFlussoDao.persist ( flussoEntity ); // N.B. in caso d'errore, fa rollback totale -> non ci saranno mai flussi con cod esito non ACQUISITO/OK
				id = flussoEntity.getIdFlusso ();
			}
		} catch ( Throwable e ) {
			throw new PersistenceException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return id;
	}

	private EpaypaTPosizioneDebitoria insertPosizioneDebitoria ( PosizioneDebitoriaDto posdebDto, EpaypaTFlusso flussoEntity ) {
		EpaypaTPosizioneDebitoria posdebEntity = null;
		if ( posdebDto != null ) {
			// posizione debitoria
			posdebEntity = new EpaypaTPosizioneDebitoria ();
			posdebEntity.setIdPosizioneDebitoriaEst ( posdebDto.getIdPosizioneDebitoriaEsterna () );
			posdebEntity.setAnnoRiferimento ( posdebDto.getAnnoRiferimento () );
			posdebEntity.setImportoTotale ( posdebDto.getImportoTotale () );
			if ( posdebDto.getDataScadenza () != null ) {
				posdebEntity.setDtScadenza ( toTimestamp ( posdebDto.getDataScadenza () ) );
			}
			if ( posdebDto.getDataInizioValidita () != null ) {
				posdebEntity.setDtInizioValidita ( toTimestamp ( posdebDto.getDataInizioValidita () ) );
			}
			if ( posdebDto.getDataFineValidita () != null ) {
				posdebEntity.setDtFineValidita ( toTimestamp ( posdebDto.getDataFineValidita () ) );
			}
			posdebEntity.setDesCausaleVersamento ( posdebDto.getDesCausaleVersamento () );
			posdebEntity.setDesRata ( posdebDto.getDesRata () );
			posdebEntity.setNotePerPagatore ( posdebDto.getNotePerPagatore () );
			posdebEntity.setIuv ( posdebDto.getIUV () );
			posdebEntity.setCodiceAvviso ( posdebDto.getCodAvviso () );
			posdebEntity.setCodEsito ( posdebDto.getCodEsito () );
			posdebEntity.setDettaglioEsito ( trunc ( posdebDto.getDetEsito (), 500 ) ); // normalizzazione alla lunghezza
			posdebEntity.setEpaypaTFlusso ( flussoEntity );

			// caso multibeneficiario
			if ( posdebDto.getImportoPrincipale () != null ) {
				posdebEntity.setImportoPrincipale ( posdebDto.getImportoPrincipale () );
			}
			if ( posdebDto.getImportoSecondarioAltroEnte () != null ) {
				posdebEntity.setImportoSecondarioAltroEnte ( posdebDto.getImportoSecondarioAltroEnte () );
			}

			// soggetto debitore
			posdebEntity.setEpaypaTSoggettoDebitore ( newEpaypaTSoggetto ( posdebDto.getSoggettoDebitore () ) );

			// componenti importo
			if ( !CollectionUtils.isEmpty ( posdebDto.getComponenteImportoDtoList () ) ) {
				posdebEntity.setEpaypaTComponenteImportos ( toComponenteImportoEntitySet ( posdebDto.getComponenteImportoDtoList () ) );
			}

			// riferimento pagamento
			if ( !CollectionUtils.isEmpty ( posdebDto.getRiferimentoPagamentoDtoList () ) ) {
				posdebEntity.setEpaypaTRiferimentoPagamentos ( toRiferimentoPagamentoEntitySet ( posdebDto.getRiferimentoPagamentoDtoList () ) );
			}
		}
		return posdebEntity;
	}

	@Override
	//@formatter:off
	public long updateFlussoPosizioniDebitorie ( FlussoCompletoDto<PosizioneDebitoriaDto> dto, boolean clearAllNullableModifiablesAndThenUpdate )
					throws PersistenceException {
		String methodName = "updateFlussoPosizioniDebitorie";
		
		

		long numUpdates = 0L; // numero degli aggiornamenti dato dall'aggiornamento della testata (1) + numero delle notifiche aggiornate

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			if ( dto != null ) {

				FlussoDto flussoDto = dto.getFlusso ();
				if ( flussoDto != null ) {

					EpaypaTFlusso flussoEntity = epaypaTFlussoDao.findOne ( flussoDto.getId () );
					if ( flussoEntity != null ) {

						// aggiorna la testata
						updateTestataFlusso ( flussoDto, flussoEntity );
						numUpdates++;

						// aggiorna il corpo (posizioni debitorie, soggetto pagatore e componenti importo)
						if ( dto.getItemList () != null ) {
							for ( PosizioneDebitoriaDto posdebDtoI: dto.getItemList () ) {

								//@formatter:off
								if ( posdebDtoI != null ) {
									Long idPosdebI = posdebDtoI.getId ();
									String idPosdebEstI = posdebDtoI.getIdPosizioneDebitoriaEsterna ();

									// ricerca degli entity e loro modifica
									boolean found = false;
									for ( EpaypaTPosizioneDebitoria posizioneDebitoriaEntityJ: flussoEntity.getEpaypaTPosizioneDebitorias () ) {

										if ( ( idPosdebI != null && idPosdebI.equals ( posizioneDebitoriaEntityJ.getIdPosizioneDebitoria () ) ) // N.B. nel caso di aggiornamento per data-entry abbiamo l'id fisico
														|| ( idPosdebEstI != null && idPosdebEstI.equals ( posizioneDebitoriaEntityJ.getIdPosizioneDebitoriaEst () ) ) ) // N.B. nel caso di aggiornamento dell'esito non abbiamo l'id fisico
										{
											found = true;
											updatePosizioneDebitoria ( posdebDtoI, posizioneDebitoriaEntityJ, clearAllNullableModifiablesAndThenUpdate );
											numUpdates++;
											break;
										}
									}
									if ( !found ) {
										// posizione debitoria nuova, da inserire
										EpaypaTPosizioneDebitoria posdebEntityNew = insertPosizioneDebitoria ( posdebDtoI, flussoEntity );
										if ( posdebEntityNew != null ) {
											flussoEntity.getEpaypaTPosizioneDebitorias ().add ( posdebEntityNew );
											numUpdates++;
										}
									}
								}
								//@formatter:on
							}
						}
					}

					epaypaTFlussoDao.merge ( flussoEntity ); // N.B. in caso d'errore, fa rollback totale
				}
			}
		} catch ( Throwable e ) {
			throw new PersistenceException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return numUpdates;
	}
	//@formatter:on

	@Override
	public Long countAllPosizioneDebitoriaByIdFlusso ( Long idFLusso ) throws PersistenceException {
		String methodName = "countAllPosizioneDebitoriaByIdFlusso";
		
		

		Long num = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			num = epaypaTPosizioneDebitoriaDao.countAllByIdFlusso ( idFLusso );

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return num;
	}

	//@formatter:off
	@Override
	public List<PosizioneDebitoriaLightDto> findAllPosizioneDebitoriaLightByIdFlusso ( Long idFlusso,
		List<CriterioOrdinamentoDto<ColumnNamePosizioneDebitoriaEnum>> ord, PaginazioneDto pag )
						throws PersistenceException {
		//@formatter:on
		String methodName = "findAllPosizioneDebitoriaLightByIdFlusso";
		
		
		
		

		List<PosizioneDebitoriaLightDto> dtoList = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			List<EpaypaTPosizioneDebitoriaLight> entityList = epaypaTPosizioneDebitoriaLightDao.findAllLightByIdFlusso ( idFlusso, ord, pag );
			dtoList = toPosizioneDebitoriaLightDtoList ( entityList );

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return dtoList;
	}

	@Override
	public PosizioneDebitoriaLightDto findPosizioneDebitoriaLightById ( Long idPosizioneDebitoria ) throws PersistenceException {
		String methodName = "findPosizioneDebitoriaLightById";
		
		

		PosizioneDebitoriaLightDto dto = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			EpaypaTPosizioneDebitoriaLight entity = epaypaTPosizioneDebitoriaLightDao.findOne ( idPosizioneDebitoria );
			dto = toPosizioneDebitoriaLightDto ( entity );

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return dto;
	}

	@Override
	public PosizioneDebitoriaDto findPosizioneDebitoriaById ( Long idPosizioneDebitoria ) throws PersistenceException {
		String methodName = "findPosizioneDebitoriaById";

		PosizioneDebitoriaDto dto;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			EpaypaTPosizioneDebitoria entity = epaypaTPosizioneDebitoriaDao.findOne ( idPosizioneDebitoria );
			dto = toPosizioneDebitoriaDto ( entity );

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return dto;
	}

	@Override public PosizioneDebitoriaDto findPosizioneDebitoriaByIdPosizioneDebitoriaEsternaAndIUV ( String iDposizioneDebitoriaEsterna, String iuv, Integer idEnte, Integer idCodiceVersamento )
					throws PersistenceException {
		String methodName = "findPosizioneDebitoriaByIdPosizioneDebitoriaEsternaAndIUV";

		PosizioneDebitoriaDto dto;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			EpaypaTPosizioneDebitoria entity = epaypaTPosizioneDebitoriaDao.findOneByIdPosizioneDebitoriaEsternaAndIUV ( iDposizioneDebitoriaEsterna, iuv, idEnte, idCodiceVersamento );
			dto = toPosizioneDebitoriaDto ( entity );

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return dto;
	}

	@Override
	public FlussoCompletoDto<PosizioneDebitoriaDaAggiornareDto> findFlussoPosizioniDebitorieDaAggiornare ( Long idFlusso ) throws PersistenceException {
		String methodName = "findFlussoPosizioniDebitorieDaAggiornare";
		FlussoCompletoDto<PosizioneDebitoriaDaAggiornareDto> dto = null;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );
			List<EpaypaTAggPosizioneDebitoria> entityList = epaypaTAggPosizioneDebitoriaDao.findAllByIdFlusso ( idFlusso );
			log.debug ( "Posizioni debitorie trovate:" + entityList.size () );

			List<PosizioneDebitoriaDaAggiornareDto> dtoList = new ArrayList<> ();

			if ( entityList.isEmpty () ) {
				dto = new FlussoCompletoDto<> ();
				dto.setFlusso ( toFlussoDto ( epaypaTFlussoDao.findOne ( idFlusso ) ) );
			} else {
				for ( EpaypaTAggPosizioneDebitoria entity: entityList ) {
					if ( dto == null ) {
						dto = new FlussoCompletoDto<> ();
						dto.setFlusso ( toFlussoDto ( entity.getEpaypaTFlusso () ) );
					}
					dtoList.add ( toPosizioneDebitoriaDaAggiornareDto ( entity ) );
				}
			}
			dto.setItemList ( dtoList );
		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
		return dto;
	}

	@Override
	public Long insertFlussoPosizioniDebitorieDaAggiornare ( FlussoCompletoDto<PosizioneDebitoriaDaAggiornareDto> dto, Timestamp timestamp )
					throws PersistenceException {
		String methodName = "insertFlussoPosizioniDebitorieDaAggiornare";
		
		
		

		Long id = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			if ( dto != null ) {
				// crea e inserisce la testata
				EpaypaTFlusso flussoEntity = new EpaypaTFlusso ();
				toFlussoEntity ( dto.getFlusso (), flussoEntity, timestamp );

				// inserisce il corpo
				if ( dto.getItemList () != null ) {
					List<EpaypaTAggPosizioneDebitoria> aggposdebEntityList = new ArrayList<> ();
					//
					for ( PosizioneDebitoriaDaAggiornareDto aggposdebDto: dto.getItemList () ) {
						EpaypaTAggPosizioneDebitoria aggposdebEntity = insertPosizioneDebitoriaDaAggiornare ( aggposdebDto, flussoEntity );
						if ( aggposdebEntity != null ) {
							aggposdebEntityList.add ( aggposdebEntity );
						}
					}

					// collega il corpo alla testata, popola la testata e persiste il tutto
					flussoEntity.setEpaypaTAggPosizioneDebitorias ( aggposdebEntityList );
				}

				epaypaTFlussoDao.persist ( flussoEntity );
				id = flussoEntity.getIdFlusso ();
			}
		} catch ( Throwable e ) {
			throw new PersistenceException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return id;
	}

	private EpaypaTAggPosizioneDebitoria insertPosizioneDebitoriaDaAggiornare ( PosizioneDebitoriaDaAggiornareDto dto, EpaypaTFlusso flussoEntity )
					throws PersistenceException {
		EpaypaTAggPosizioneDebitoria entity = null;
		if ( dto != null ) {
			// aggiorna la posizione debitoria
			entity = new EpaypaTAggPosizioneDebitoria ();
			TipoAggiornamentoPosizioneDebitoriaDto tipoAggiornaDto = dto.getTipoAggiornamento ();
			if ( tipoAggiornaDto != null ) {
				TipoAggiornamentoPosizioneDebitoriaEnum tipoAggiornamentoEnum = tipoAggiornaDto.getTipoAggiornamentoEnum ();
				if ( tipoAggiornamentoEnum != null ) {
					entity.setEpaypaDTipoAggPosizioneDebitoria ( epaypaDTipoAggPosizioneDebitoriaDao.findOne ( tipoAggiornamentoEnum.getId () ) );
				}
			}

			entity.setIdPosizioneDebitoriaEst ( dto.getIdPosizioneDebitoriaEsterna () );
			entity.setDtInizioValidita ( toTimestamp ( dto.getDataInizioValidita () ) );
			entity.setDtFineValidita ( toTimestamp ( dto.getDataFineValidita () ) );
			entity.setDtScadenza ( toTimestamp ( dto.getDataScadenza () ) );
			entity.setImportoTotale ( dto.getImportoTotale () );
			entity.setDesCausaleVersamento ( dto.getDesCausaleVersamento () );
			entity.setMotivazione ( dto.getMotivazione () );
			entity.setCodEsito ( dto.getCodEsito () );
			entity.setDettaglioEsito ( trunc ( dto.getDetEsito (), 500 ) ); // normalizzazione alla lunghezza
			entity.setCodiceAvviso ( dto.getCodAvviso () );
			entity.setEpaypaTFlusso ( flussoEntity );
			entity.setImportoPrincipale ( dto.getImportoPrincipale () );
			entity.setImportoSecondarioAltroEnte ( dto.getImportoSecondarioAltroEnte () );

			// componenti importo
			if ( dto.getComponenteImportoDtoList () != null ) {
				entity.setEpaypaTComponenteImportos ( toComponenteImportoEntitySet ( dto.getComponenteImportoDtoList () ) );
			}

			// riferimenti pagamento
			if ( dto.getRiferimentoPagamentoDtoList () != null ) {
				entity.setEpaypaTRiferimentoPagamentos ( toRiferimentoPagamentoEntitySet ( dto.getRiferimentoPagamentoDtoList () ) );
			}

			// riferimenti pagamento
			if ( dto.getSoggettoAnagraficoDto () != null ) {
				entity.setEpaypaTSoggetto ( newEpaypaTSoggetto( dto.getSoggettoAnagraficoDto () ) );
			}


		}
		return entity;
	}

	@Override
	//@formatter:off
	public long updateFlussoPosizioniDebitorieDaAggiornare ( FlussoCompletoDto<PosizioneDebitoriaDaAggiornareDto> dto,
		boolean clearAllNullableModifiablesAndThenUpdate )
						throws PersistenceException {
		String methodName = "updateFlussoPosizioniDebitorieDaAggiornare";
		
		

		long numUpdates = 0L; // numero degli aggiornamenti dato dall'aggiornamento della testata (1) + numero delle notifiche aggiornate

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			if ( dto != null ) {

				FlussoDto flussoDto = dto.getFlusso ();
				if ( flussoDto != null ) {

					EpaypaTFlusso flussoEntity = epaypaTFlussoDao.findOne ( flussoDto.getId () );
					if ( flussoEntity != null ) {

						// aggiorna la testata
						updateTestataFlusso ( flussoDto, flussoEntity );
						numUpdates++;

						// aggiorna il corpo (posizioni debitorie da aggiornare)
						if ( dto.getItemList () != null ) {
							for ( PosizioneDebitoriaDaAggiornareDto aggposdebDtoI: dto.getItemList () ) {

								//@formatter:off
								if ( aggposdebDtoI != null ) {
									Long idPosdebI = aggposdebDtoI.getId ();
									String idPosdebEstI = aggposdebDtoI.getIdPosizioneDebitoriaEsterna ();

									// ricerca degli entity e loro modifica
									boolean found = false;
									for ( EpaypaTAggPosizioneDebitoria aggPosizioneDebitoriaEntityJ: flussoEntity.getEpaypaTAggPosizioneDebitorias () ) {

										if ( ( idPosdebI != null && idPosdebI.equals ( aggPosizioneDebitoriaEntityJ.getIdAggPosizioneDebitoria () ) ) // N.B. nel caso di aggiornamento per data-entry abbiamo l'id fisico
														|| ( idPosdebEstI != null && idPosdebEstI.equals ( aggPosizioneDebitoriaEntityJ.getIdPosizioneDebitoriaEst () ) ) ) // N.B. nel caso di aggiornamento dell'esito non abbiamo l'id fisico
										{
											found = true;
											updatePosizioneDebitoriaDaAggiornare ( aggposdebDtoI, aggPosizioneDebitoriaEntityJ,
												clearAllNullableModifiablesAndThenUpdate );
											numUpdates++;
											break;
										}
									}
									if ( !found ) {
										// posizione debitoria da aggiornare nuova, da inserire
										EpaypaTAggPosizioneDebitoria posdebEntityDaAggiornareNew
										= insertPosizioneDebitoriaDaAggiornare ( aggposdebDtoI, flussoEntity );
										if ( posdebEntityDaAggiornareNew != null ) {
											flussoEntity.getEpaypaTAggPosizioneDebitorias ().add ( posdebEntityDaAggiornareNew );
											numUpdates++;
										}
									}
								}
								//@formatter:on
							}
						}
					}

					epaypaTFlussoDao.merge ( flussoEntity ); // N.B. in caso d'errore, fa rollback totale
				}
			}
		} catch ( Throwable e ) {
			throw new PersistenceException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return numUpdates;
	}
	//@formatter:on

	@Override
	public Long countAllPosizioneDebitoriaDaAggiornareByIdFlusso ( Long idFLusso ) throws PersistenceException {
		String methodName = "countAllPosizioneDebitoriaDaAggiornareByIdFlusso";
		
		

		Long num = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			num = epaypaTAggPosizioneDebitoriaDao.countAllByIdFlusso ( idFLusso );

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return num;
	}

	//@formatter:off
	@Override
	public List<PosizioneDebitoriaDaAggiornareLightDto> findAllPosizioneDebitoriaDaAggiornareLightByIdFlusso ( Long idFlusso,
		List<CriterioOrdinamentoDto<ColumnNameAggPosizioneDebitoriaEnum>> ord, PaginazioneDto pag )
						throws PersistenceException {
		//@formatter:on
		String methodName = "findAllPosizioneDebitoriaDaAggiornareLightByIdFlusso";
		
		
		
		

		List<PosizioneDebitoriaDaAggiornareLightDto> dtoList = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			List<EpaypaTAggPosizioneDebitoriaLight> entityList = epaypaTAggPosizioneDebitoriaLightDao.findAllLightByIdFlusso ( idFlusso, ord, pag );
			dtoList = toPosizioneDebitoriaDaAggiornareLightDtoList ( entityList );

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return dtoList;
	}

	@Override
	public PosizioneDebitoriaDaAggiornareLightDto findPosizioneDebitoriaDaAggiornareLightById ( Long idPosizioneDebitoriaDaAggiornare )
					throws PersistenceException {
		String methodName = "findPosizioneDebitoriaDaAggiornareLightById";
		
		

		PosizioneDebitoriaDaAggiornareLightDto dto = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			EpaypaTAggPosizioneDebitoriaLight entity = epaypaTAggPosizioneDebitoriaLightDao.findOne ( idPosizioneDebitoriaDaAggiornare );
			dto = toPosizioneDebitoriaDaAggiornareLightDto ( entity );

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return dto;
	}

	@Override
	public PosizioneDebitoriaDaAggiornareDto findPosizioneDebitoriaDaAggiornareById ( Long idPosizioneDebitoriaDaAggiornare ) throws PersistenceException {
		String methodName = "findPosizioneDebitoriaDaAggiornareById";
		
		

		PosizioneDebitoriaDaAggiornareDto dto = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			EpaypaTAggPosizioneDebitoria entity = epaypaTAggPosizioneDebitoriaDao.findOne ( idPosizioneDebitoriaDaAggiornare );
			dto = toPosizioneDebitoriaDaAggiornareDto ( entity );

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return dto;
	}

	@Override
	public FlussoCompletoDto<AvvisoScadutoDto> findFlussoAvvisiScaduti ( Long idFlusso ) throws PersistenceException {
		String methodName = "findFlussoAvvisiScaduti";
		
		

		FlussoCompletoDto<AvvisoScadutoDto> dto = null;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			List<EpaypaTAvvisoScaduto> entityList = epaypaTAvvisoScadutoDao.findAllByIdFlusso ( idFlusso );
			log.debug ( "Avvisi scaduti trovati:" + entityList.size () );

			List<AvvisoScadutoDto> dtoList = new ArrayList<AvvisoScadutoDto> ();

			if ( entityList.isEmpty () ) {
				dto = new FlussoCompletoDto<AvvisoScadutoDto>();
				dto.setFlusso ( toFlussoDto ( epaypaTFlussoDao.findOne ( idFlusso ) ) );

			} else {
				for ( EpaypaTAvvisoScaduto entity: entityList ) {
					if ( dto == null ) {
						dto = new FlussoCompletoDto<AvvisoScadutoDto>();
						dto.setFlusso ( toFlussoDto ( entity.getEpaypaTFlusso () ) );
					}
					dtoList.add ( toAvvisoScadutoDto ( entity ) );
				}
			}

			dto.setItemList ( dtoList );

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return dto;
	}

	@Override
	public Long insertFlussoAvvisiScaduti ( FlussoCompletoDto<AvvisoScadutoDto> dto, Timestamp timestamp ) throws PersistenceException {
		String methodName = "insertFlussoAvvisiScaduti";
		
		
		

		Long id = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			if ( dto != null ) {
				// crea e inserisce la testata
				EpaypaTFlusso flussoEntity = new EpaypaTFlusso ();
				toFlussoEntity ( dto.getFlusso (), flussoEntity, timestamp );
				flussoEntity.setCodEsito ( Util.COD_ESITO_OK );
				flussoEntity.setDettaglioEsito ( Util.DET_ESITO_OK );

				// inserisce il corpo
				if ( dto.getItemList () != null ) {
					List<EpaypaTAvvisoScaduto> avvisoScadutoEntityList = new ArrayList<EpaypaTAvvisoScaduto>();
					//
					for ( AvvisoScadutoDto avvisoDto: dto.getItemList () ) {
						EpaypaTAvvisoScaduto avvisoScadutoEntity = insertAvvisoScaduto ( avvisoDto );
						if ( avvisoScadutoEntity != null ) {
							avvisoScadutoEntityList.add ( avvisoScadutoEntity );
						}
					}

					// collega il corpo alla testata, popola la testata e persiste il tutto
					flussoEntity.setEpaypaTAvvisoScadutos ( avvisoScadutoEntityList );
				}

				epaypaTFlussoDao.persist ( flussoEntity ); // N.B. in caso d'errore, fa rollback totale -> non ci saranno mai flussi con cod esito non ACQUISITO/OK
				id = flussoEntity.getIdFlusso ();
			}
		} catch ( Throwable e ) {
			throw new PersistenceException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return id;
	}

	private EpaypaTAvvisoScaduto insertAvvisoScaduto ( AvvisoScadutoDto dto ) {
		EpaypaTAvvisoScaduto entity = null;
		if ( dto != null ) {
			// avviso scaduto
			entity = new EpaypaTAvvisoScaduto ();
			entity.setIuv ( dto.getIUV () );
			entity.setImporto ( dto.getImporto () );
			if ( dto.getDataScadenza () != null ) {
				entity.setDtScadenza ( toTimestamp ( dto.getDataScadenza () ) );
			}
		}
		return entity;
	}

	@Override
	public Long countAllAvvisoScadutoByIdFlusso ( Long idFLusso ) throws PersistenceException {
		String methodName = "countAllAvvisoScadutoByIdFlusso";
		
		

		Long num = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			num = epaypaTAvvisoScadutoDao.countAllByIdFlusso ( idFLusso );

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return num;
	}

	@Override
	//@formatter:off
	public List<AvvisoScadutoDto> findAllAvvisoScadutoByIdFlusso ( Long idFlusso, List<CriterioOrdinamentoDto<ColumnNameAvvisoScadutoEnum>> ord,
		PaginazioneDto pag )
						throws PersistenceException {
		//@formatter:on
		String methodName = "findAllAvvisoScadutoByIdFlusso";
		
		
		
		

		List<AvvisoScadutoDto> dtoList = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			List<EpaypaTAvvisoScaduto> entityList = epaypaTAvvisoScadutoDao.findAllByIdFlusso ( idFlusso, ord, pag );
			dtoList = toAvvisoScadutoDtoList ( entityList );

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return dtoList;
	}

	@Override
	public AvvisoScadutoDto findAvvisoScadutoById ( Long idAvvisiScaduto ) throws PersistenceException {
		String methodName = "findPosizioneDebitoriaById";
		
		

		AvvisoScadutoDto dto = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			EpaypaTAvvisoScaduto entity = epaypaTAvvisoScadutoDao.findOne ( idAvvisiScaduto );
			dto = toAvvisoScadutoDto ( entity );

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return dto;
	}

	private EpayPaEpaywsoSrvService getEpayPaEpayWsoService (String endpoint ) throws PersistenceException {

		EpayPaEpaywsoSrvService service;
		try {
			service = new EpayPaEpywsoSrvServiceImpl(endpoint);
		} catch ( Exception e ) {
			log.error ( "errore nella creazione del servizio remoto", e );
			throw new PersistenceException(e.getMessage());
		}

		return service;
	}
	//    output = getEpayPaCatalogSrvService ( endpoint ).getProfilazioneUtente ( codUtente, codEnte );

	@Override
	public FlussoCompletoDto<RiversamentoDto> findFlussoRendicontazione ( Long idFlusso ) throws PersistenceException {
		String methodName = "findFlussoRendicontazione";
		
		

		FlussoCompletoDto<RiversamentoDto> dto = null;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			List<EpaypaTRiversamento> entityList = epaypaTRiversamentoDao.findAllByIdFlusso ( idFlusso );
			log.debug ( "Dati di riversamento trovati:" + entityList.size () );

			if ( entityList != null ) {
				List<RiversamentoDto> dtoList = new ArrayList<RiversamentoDto>();

				if ( entityList.isEmpty () ) {
					dto = new FlussoCompletoDto<RiversamentoDto>();
					dto.setFlusso ( toFlussoDto ( epaypaTRendicontazioneDao.findOne ( idFlusso ) ) );

				} else {
					for ( EpaypaTRiversamento entity: entityList ) {
						if ( dto == null ) {
							dto = new FlussoCompletoDto<RiversamentoDto>();
							dto.setFlusso ( toFlussoDto ( entity.getEpaypaTRendicontazione () ) );
							calcolaStatoAggregato(dto);
						}
						dtoList.add ( toRiversamentoDto ( entity ) );
					}
				}

				dto.setItemList ( dtoList );
			}

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return dto;
	}

	private void calcolaStatoAggregato(FlussoCompletoDto<RiversamentoDto> dto ) throws PersistenceException {


		List<StatoFlussoAggregatoDto> response = getEpayPaEpayWsoService(getEpayWsoSrvEndpoint()).
						ricercaStatiAggregatiWso(dto.getFlusso().getIdFlussoRendicontazione(), dto.getFlusso().getIdMessaggio(), dto.getFlusso().getCodFiscaleEnte(), 
							BigInteger.valueOf(TipoFlussoEnum.TRASMETTI_FLUSSO_RENDICONTAZIONE.getId()) );
		if ( response != null && 
						!response.isEmpty() )
		{
			dto.getFlusso().setStatoFlussoAggregato(response.get(0).getStatoFlusso());
			dto.getFlusso().setDatiAggiuntiviStato(response.get(0).getDatoAggiuntivoCodEsito()+" "+
							response.get(0).getDatoAggiuntivoDescEsito());
		}
		else
		{
			dto.getFlusso().setStatoFlussoAggregato(Costanti.STATO_ELABORAZIONE_DEFAULT);
			dto.getFlusso().setDatiAggiuntiviStato(Costanti.DATO_AGGIUNTIVO_DEFAULT);
		}

	}

	@Override
	public FlussoCompletoDto<RiversamentoDto> findFlussoRendicontazione(Long idFlusso, List<CriterioOrdinamentoDto<ColumnNameRiversamentoEnum>> ord, PaginazioneDto pag) throws PersistenceException {
		String methodName = "findFlussoRendicontazione";
		
		
		
		

		FlussoCompletoDto<RiversamentoDto> dto = null;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			List<EpaypaTRiversamento> entityList = epaypaTRiversamentoDao.findAllByIdFlusso ( idFlusso, ord, pag);
			log.debug ( "Dati di riversamento trovati:" + entityList.size () );

			if ( entityList != null ) {
				List<RiversamentoDto> dtoList = new ArrayList<RiversamentoDto>();

				if ( entityList.isEmpty () ) {
					dto = new FlussoCompletoDto<RiversamentoDto>();
					dto.setFlusso ( toFlussoDto ( epaypaTRendicontazioneDao.findOne ( idFlusso ) ) );

				} else {
					for ( EpaypaTRiversamento entity: entityList ) {
						if ( dto == null ) {
							dto = new FlussoCompletoDto<RiversamentoDto>();
							dto.setFlusso ( toFlussoDto ( entity.getEpaypaTRendicontazione () ) );
							calcolaStatoAggregato(dto);
						}
						dtoList.add ( toRiversamentoDto ( entity ) );
					}
				}

				dto.setItemList ( dtoList );
			}

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return dto;
	}

	@Override
	public Long insertFlussoRendicontazioni ( FlussoCompletoDto<RiversamentoDto> dto, Timestamp timestamp ) throws PersistenceException {
		String methodName = "insertFlussoRendicontazioni";
		
		
		

		Long id = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			if ( dto != null ) {
				// crea e inserisce la testata
				EpaypaTFlusso flussoEntity = new EpaypaTFlusso ();
				EpaypaTRendicontazione rendicontazioneEntity = new EpaypaTRendicontazione ();
				toFlussoRendicontazioneEntity ( dto.getFlusso (), flussoEntity, rendicontazioneEntity, timestamp );
				flussoEntity.setCodEsito ( Util.COD_ESITO_OK );
				flussoEntity.setDettaglioEsito ( Util.DET_ESITO_OK );

				// inserisce il corpo
				if ( dto.getItemList () != null ) {
					List<EpaypaTRiversamento> riversamentoEntityList = new ArrayList<EpaypaTRiversamento>();
					//
					for ( RiversamentoDto riversamentoDto: dto.getItemList () ) {
						EpaypaTRiversamento riversamentoEntity = insertRiversamento ( riversamentoDto, rendicontazioneEntity );
						if ( riversamentoEntity != null ) {
							riversamentoEntityList.add ( riversamentoEntity );
						}
					}

					// collega il corpo alla testata, popola la testata e persiste il tutto
					rendicontazioneEntity.setEpaypaTRiversamentos ( riversamentoEntityList );
				}

				epaypaTFlussoDao.persist ( flussoEntity ); // N.B. in caso d'errore, fa rollback totale -> non ci saranno mai flussi con cod esito non ACQUISITO/OK
				id = flussoEntity.getIdFlusso ();
			}
		} catch ( Throwable e ) {
			throw new PersistenceException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return id;
	}

	private EpaypaTRiversamento insertRiversamento ( RiversamentoDto dto, EpaypaTRendicontazione rendicontazioneEntity ) {
		// dati di riversamento
		EpaypaTRiversamento entity = null;
		if ( dto != null ) {
			entity = new EpaypaTRiversamento ();
			entity.setEpaypaTRendicontazione ( rendicontazioneEntity );
			entity.setIuv ( dto.getIUV () );
			entity.setIur ( dto.getIUR () );
			entity.setIndiceDatiRt ( dto.getIndicePagamento () );
			entity.setImportoPagato ( dto.getImportoPagato () );
			if ( dto.getEsito () != null ) {
				entity.setCodEsito ( dto.getEsito ().getId () );
			}
			entity.setDtEsito ( toTimestamp ( dto.getDataEsito () ) );
		}

		return entity;
	}

	@Override
	public Long countAllRiversamentoByIdFlusso ( Long idFLusso ) throws PersistenceException {
		String methodName = "countAllRiversamentoByIdFlusso";
		
		

		Long num = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			num = epaypaTRiversamentoDao.countAllByIdFlusso ( idFLusso );

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return num;
	}

	@Override
	//@formatter:off
	public List<RiversamentoDto> findAllRiversamentoByIdFlusso ( Long idFlusso, List<CriterioOrdinamentoDto<ColumnNameRiversamentoEnum>> ord,
		PaginazioneDto pag )
						throws PersistenceException {
		//@formatter:on
		String methodName = "findAllRiversamentoByIdFlusso";
		
		
		
		

		List<RiversamentoDto> dtoList = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			List<EpaypaTRiversamento> entityList = epaypaTRiversamentoDao.findAllByIdFlusso ( idFlusso, ord, pag );
			dtoList = toRiversamentoDtoList ( entityList );

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return dtoList;
	}

	@Override
	public RiversamentoDto findRiversamentoById ( Long idRiversamento ) throws PersistenceException {
		String methodName = "findRiversamentoById";
		
		

		RiversamentoDto dto = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			EpaypaTRiversamento entity = epaypaTRiversamentoDao.findOne ( idRiversamento );
			dto = toRiversamentoDto ( entity );

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return dto;
	}

	@Override
	public boolean updateFlusso ( FlussoDto dto ) throws PersistenceException {
		String methodName = "updateFlusso";
		
		

		boolean updateDone = false;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			if ( dto != null ) {

				EpaypaTFlusso entity = epaypaTFlussoDao.findOne ( dto.getId () );
				if ( entity != null ) {

					// aggiorna la testata
					updateTestataFlusso ( dto, entity );

					epaypaTFlussoDao.merge ( entity ); // N.B. in caso d'errore, fa rollback
					updateDone = true;
				}
			}
		} catch ( Throwable e ) {
			throw new PersistenceException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return updateDone;
	}

	@Override
	public boolean removeFlussoById ( Long idFlusso ) throws PersistenceException {
		String methodName = "removeFlussoById";
		
		

		boolean removeDone = false;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			EpaypaTFlusso entity = epaypaTFlussoDao.findOne ( idFlusso );
			if ( entity != null ) {
				epaypaTFlussoDao.remove ( entity );
				removeDone = true;
			}

		} catch ( Throwable e ) {
			throw new PersistenceException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return removeDone;
	}

	@Override
	public long updateFlussoPosizioniDebitorie ( FlussoDto dto, Timestamp timestampInizioValidita, Timestamp timestampFineValidita )
					throws PersistenceException {
		String methodName = "updateFlussoPosizioniDebitorie";
		
		
		
		

		long numUpdates = 0L; // numero degli aggiornamenti dato dall'aggiornamento della testata (1) + numero delle posizioni aggiornate

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			if ( dto != null ) {

				EpaypaTFlusso flussoEntity = epaypaTFlussoDao.findOne ( dto.getId () );
				if ( flussoEntity != null ) {

					// aggiorna la testata
					updateTestataFlusso ( dto, flussoEntity );
					numUpdates++;

					// aggiorna le date di inizio e fine validita su tutte posizioni debitorie del flusso
					if ( timestampInizioValidita != null || timestampFineValidita != null ) {
						for ( EpaypaTPosizioneDebitoria posdebEntity: flussoEntity.getEpaypaTPosizioneDebitorias () ) {
							if ( timestampInizioValidita != null ) {
								posdebEntity.setDtInizioValidita ( timestampInizioValidita );
							}

							if ( timestampFineValidita != null ) {
								posdebEntity.setDtFineValidita ( timestampFineValidita );
							}

							numUpdates++;
						}
					}

					epaypaTFlussoDao.merge ( flussoEntity ); // N.B. in caso d'errore, fa rollback totale
				}
			}
		} catch ( Throwable e ) {
			throw new PersistenceException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return numUpdates;
	}

	@Override
	public PosizioneDebitoriaDto removePosizioneDebitoriaById ( Long idPosizioneDebitoria ) throws PersistenceException {
		String methodName = "removePosizioneDebitoriaById";
		
		

		PosizioneDebitoriaDto posdebEliminataDto = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			EpaypaTPosizioneDebitoria entity = epaypaTPosizioneDebitoriaDao.findOne ( idPosizioneDebitoria );
			if ( entity != null ) {
				posdebEliminataDto = toPosizioneDebitoriaDto ( entity );
				epaypaTPosizioneDebitoriaDao.remove ( entity );
			}

		} catch ( Throwable e ) {
			throw new PersistenceException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return posdebEliminataDto;
	}

	@Override
	public boolean updateFlussoPosizioniDebitorieDaAggiornare ( FlussoDto dto ) throws PersistenceException {
		String methodName = "updateFlussoPosizioniDebitorieDaAggiornare";
		
		

		boolean updateDone = false;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			updateDone = updateFlusso ( dto );

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return updateDone;
	}

	@Override
	public PosizioneDebitoriaDaAggiornareDto removePosizioneDebitoriaDaAggiornareById ( Long idPosizioneDebitoriaDaAggiornare ) throws PersistenceException {
		String methodName = "removePosizioneDebitoriaDaAggiornareById";
		
		

		PosizioneDebitoriaDaAggiornareDto aggposdebEliminataDto = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			EpaypaTAggPosizioneDebitoria entity = epaypaTAggPosizioneDebitoriaDao.findOne ( idPosizioneDebitoriaDaAggiornare );
			if ( entity != null ) {
				aggposdebEliminataDto = toPosizioneDebitoriaDaAggiornareDto ( entity );
				epaypaTAggPosizioneDebitoriaDao.remove ( entity );
			}

		} catch ( Throwable e ) {
			throw new PersistenceException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return aggposdebEliminataDto;
	}

	private void updateTestataFlusso ( FlussoDto dto, EpaypaTFlusso entity ) throws PersistenceException {
		if ( dto != null && entity != null ) {
			if ( dto.getId () != null ) {
				entity.setIdFlusso ( dto.getId () );
			}
			if ( dto.getTipoFlusso () != null ) {
				entity.setEpaypaDTipoFlusso ( epaypaDTipoFlussoDao.findOne ( dto.getTipoFlusso ().getId () ) );
			}
			if ( dto.getStatoFlusso () != null ) {
				entity.setEpaypaDStatoFlusso ( epaypaDStatoFlussoDao.findOne ( dto.getStatoFlusso ().getId () ) );
			}
			if ( dto.getIdMessaggio () != null ) {
				entity.setIdMessaggio ( dto.getIdMessaggio () );
			}
			if ( dto.getIdEnte () != null ) {
				entity.setEpaypaTEnte ( epaypaTEnteDao.findOne ( dto.getIdEnte () ) );
			}
			if ( dto.getCodFiscaleEnte () != null ) {
				entity.setEpaypaTEnte ( epaypaTEnteDao.findOneByCodFiscale ( dto.getCodFiscaleEnte () ) );
			}
			if ( dto.getCodVersamento () != null ) {
				entity.setEpaypaTCodiceVersamento (
					epaypaTCodiceVersamentoDao.findOneByCodAndEnte ( dto.getCodVersamento (), entity.getEpaypaTEnte ().getIdEnte () ) );
			}
			if ( dto.getNumeroElementi () != null ) {
				entity.setNumeroElementi ( dto.getNumeroElementi () );
			}
			if ( dto.getImportoTotale () != null ) {
				entity.setImportoTotale ( dto.getImportoTotale () );
			}
			if ( dto.getPagamentiSpontanei () != null ) {
				entity.setPagamentiSpontanei ( dto.getPagamentiSpontanei () );
			}
			if ( dto.getDataInserimento () != null ) {
				entity.setDtInserimento ( toTimestamp ( dto.getDataInserimento () ) );
			}
			if ( dto.getUtenteInserimento () != null ) {
				entity.setUtenteInserimento ( dto.getUtenteInserimento () );
			}
			entity.setDtUltimaVariazione ( getTimestampNow () );
			if ( dto.getUtenteUltimaVariazione () != null ) {
				entity.setUtenteUltimaVariazione ( dto.getUtenteUltimaVariazione () );
			}
			if ( dto.getCodEsito () != null ) {
				entity.setCodEsito ( dto.getCodEsito () );
			}
			if ( dto.getDetEsito () != null ) {
				entity.setDettaglioEsito ( trunc ( dto.getDetEsito (), 500 ) ); // normalizzazione alla lunghezza
			}
			if ( dto.getDataInoltro () != null ) {
				entity.setDtInoltro ( toTimestamp ( dto.getDataInoltro () ) );
			}
		}
	}

	private void updatePosizioneDebitoria ( PosizioneDebitoriaDto posdebDto, EpaypaTPosizioneDebitoria posdebEntity,
		boolean clearAllNullableModifiablesAndThenUpdate ) {
		if ( posdebDto != null ) {
			EpaypaTSoggetto sogdebEntity = posdebEntity.getEpaypaTSoggettoDebitore ();

			// pulisce per prima cosa tutti i campi nullabili che sono modificabili
			if ( clearAllNullableModifiablesAndThenUpdate ) {
				posdebEntity.setAnnoRiferimento ( null );
				posdebEntity.setDesRata ( null );
				posdebEntity.setNotePerPagatore ( null );
				posdebEntity.setEpaypaTComponenteImportos ( null );
				sogdebEntity.setIndirizzo ( null );
				sogdebEntity.setCivico ( null );
				sogdebEntity.setCap ( null );
				sogdebEntity.setLocalita ( null );
				sogdebEntity.setProvincia ( null );
				sogdebEntity.setNazione ( null );
				sogdebEntity.setEmail ( null );
			}

			// aggiorna il corpo
			if ( posdebDto.getIdPosizioneDebitoriaEsterna () != null ) {
				posdebEntity.setIdPosizioneDebitoriaEst ( posdebDto.getIdPosizioneDebitoriaEsterna () );
			}
			if ( posdebDto.getAnnoRiferimento () != null ) {
				posdebEntity.setAnnoRiferimento ( posdebDto.getAnnoRiferimento () );
			}
			if ( posdebDto.getDataInizioValidita () != null ) {
				posdebEntity.setDtInizioValidita ( toTimestamp ( posdebDto.getDataInizioValidita () ) );
			}
			if ( posdebDto.getDataFineValidita () != null ) {
				posdebEntity.setDtFineValidita ( toTimestamp ( posdebDto.getDataFineValidita () ) );
			}
			if ( posdebDto.getDataScadenza () != null ) {
				posdebEntity.setDtScadenza ( toTimestamp ( posdebDto.getDataScadenza () ) );
			}
			if ( posdebDto.getImportoTotale () != null ) {
				posdebEntity.setImportoTotale ( posdebDto.getImportoTotale () );
			}
			if ( posdebDto.getDesCausaleVersamento () != null ) {
				posdebEntity.setDesCausaleVersamento ( posdebDto.getDesCausaleVersamento () );
			}
			if ( posdebDto.getDesRata () != null ) {
				posdebEntity.setDesRata ( posdebDto.getDesRata () );
			}
			if ( posdebDto.getNotePerPagatore () != null ) {
				posdebEntity.setNotePerPagatore ( posdebDto.getNotePerPagatore () );
			}
			if ( posdebDto.getIUV () != null ) {
				posdebEntity.setIuv ( posdebDto.getIUV () );
			}
			if ( posdebDto.getCodAvviso () != null ) {
				posdebEntity.setCodiceAvviso ( posdebDto.getCodAvviso () );
			}
			if ( posdebDto.getCodEsito () != null ) {
				posdebEntity.setCodEsito ( posdebDto.getCodEsito () );
			}
			if ( posdebDto.getDetEsito () != null ) {
				posdebEntity.setDettaglioEsito ( trunc ( posdebDto.getDetEsito (), 500 ) ); 
			}
			
			if ( posdebDto.getImportoPrincipale () != null ) {
                posdebEntity.setImportoPrincipale ( posdebDto.getImportoPrincipale () ); 
            }
			
			if ( posdebDto.getImportoSecondarioAltroEnte () != null ) {
                posdebEntity.setImportoSecondarioAltroEnte ( posdebDto.getImportoSecondarioAltroEnte () ); 
            }

			// aggiorna il soggetto debitore
			SoggettoAnagraficoDto sogdebDto = posdebDto.getSoggettoDebitore ();
			if ( sogdebDto != null ) {
				if ( sogdebDto.getIdUnivocoFiscale () != null ) {
					sogdebEntity.setIdUnivocoFiscale ( sogdebDto.getIdUnivocoFiscale () );
				}
				if ( sogdebDto.getTipologiaSoggettoEnum () != null ) {
					sogdebEntity.setTipologiaSoggetto ( sogdebDto.getTipologiaSoggettoEnum ().getId () );
					switch ( sogdebDto.getTipologiaSoggettoEnum () ) {
					case PERSONA_FISICA :
						sogdebEntity.setCognomeRagSoc ( sogdebDto.getCognome () );
						break;
					case PERSONA_GIURIDICA :
						sogdebEntity.setCognomeRagSoc ( sogdebDto.getRagioneSociale () );
						break;
					}
				}
				if ( sogdebDto.getNome () != null ) {
					sogdebEntity.setNome ( sogdebDto.getNome () );
				}
				if ( sogdebDto.getIndirizzo () != null ) {
					sogdebEntity.setIndirizzo ( sogdebDto.getIndirizzo () );
				}
				if ( sogdebDto.getCivico () != null ) {
					sogdebEntity.setCivico ( sogdebDto.getCivico () );
				}
				if ( sogdebDto.getCap () != null ) {
					sogdebEntity.setCap ( sogdebDto.getCap () );
				}
				if ( sogdebDto.getLocalita () != null ) {
					sogdebEntity.setLocalita ( sogdebDto.getLocalita () );
				}
				if ( sogdebDto.getProvincia () != null ) {
					sogdebEntity.setProvincia ( sogdebDto.getProvincia () );
				}
				if ( sogdebDto.getNazione () != null ) {
					sogdebEntity.setNazione ( sogdebDto.getNazione () );
				}
				if ( sogdebDto.getEmail () != null ) {
					sogdebEntity.setEmail ( sogdebDto.getEmail () );
				}
			}

			// aggiorna le componenti importo sostituendo l'intera lista con quella nuova
			if ( posdebDto.getComponenteImportoDtoList () != null ) {
				posdebEntity.setEpaypaTComponenteImportos ( toComponenteImportoEntitySet ( posdebDto.getComponenteImportoDtoList () ) );
			}
			// aggiorna i riferimenti pagamento sostituendo l'intera lista con quella nuova
			if ( posdebDto.getRiferimentoPagamentoDtoList () != null ) {
				posdebEntity.setEpaypaTRiferimentoPagamentos ( toRiferimentoPagamentoEntitySet ( posdebDto.getRiferimentoPagamentoDtoList () ) );
			}
		}
	}

	//@formatter:off
	private void updatePosizioneDebitoriaDaAggiornare ( PosizioneDebitoriaDaAggiornareDto aggposdebDto, EpaypaTAggPosizioneDebitoria aggposdebEntity,
		boolean clearAllNullableModifiablesAndThenUpdate )
						throws PersistenceException {
		if ( aggposdebDto != null ) {
			// pulisce per prima cosa tutti i campi nullabili che sono modificabili
			if ( clearAllNullableModifiablesAndThenUpdate ) {
				aggposdebEntity.setMotivazione ( null );
				aggposdebEntity.setDtInizioValidita ( null );
				aggposdebEntity.setDtFineValidita ( null );
				aggposdebEntity.setDtScadenza ( null );
				aggposdebEntity.setImportoTotale ( null );
				aggposdebEntity.setDesCausaleVersamento ( null );
				aggposdebEntity.setEpaypaTComponenteImportos ( null );
				aggposdebEntity.setEpaypaTSoggetto(null);
			}

			// aggiorna il corpo
			if ( aggposdebDto.getTipoAggiornamento () != null ) {
				TipoAggiornamentoPosizioneDebitoriaEnum tipoAggiornamentoEnum = aggposdebDto.getTipoAggiornamento ().getTipoAggiornamentoEnum ();
				if ( tipoAggiornamentoEnum != null ) {
					aggposdebEntity.setEpaypaDTipoAggPosizioneDebitoria ( epaypaDTipoAggPosizioneDebitoriaDao.findOne ( tipoAggiornamentoEnum.getId () ) );
				}
			}
			if ( aggposdebDto.getIdPosizioneDebitoriaEsterna () != null ) {
				aggposdebEntity.setIdPosizioneDebitoriaEst ( aggposdebDto.getIdPosizioneDebitoriaEsterna () );
			}
			if ( aggposdebDto.getMotivazione () != null ) {
				aggposdebEntity.setMotivazione ( aggposdebDto.getMotivazione () );
			}
			if ( aggposdebDto.getDataInizioValidita () != null ) {
				aggposdebEntity.setDtInizioValidita ( toTimestamp ( aggposdebDto.getDataInizioValidita () ) );
			}
			if ( aggposdebDto.getDataFineValidita () != null ) {
				aggposdebEntity.setDtFineValidita ( toTimestamp ( aggposdebDto.getDataFineValidita () ) );
			}
			if ( aggposdebDto.getDataScadenza () != null ) {
				aggposdebEntity.setDtScadenza ( toTimestamp ( aggposdebDto.getDataScadenza () ) );
			}
			if ( aggposdebDto.getImportoTotale () != null ) {
				aggposdebEntity.setImportoTotale ( aggposdebDto.getImportoTotale () );
			}
			if ( aggposdebDto.getDesCausaleVersamento () != null ) {
				aggposdebEntity.setDesCausaleVersamento ( aggposdebDto.getDesCausaleVersamento () );
			}
			if ( aggposdebDto.getCodAvviso () != null ) {
				aggposdebEntity.setCodiceAvviso ( aggposdebDto.getCodAvviso () );
			}
			if ( aggposdebDto.getCodEsito () != null ) {
				aggposdebEntity.setCodEsito ( aggposdebDto.getCodEsito () );
			}
			if ( aggposdebDto.getDetEsito () != null ) {
				aggposdebEntity.setDettaglioEsito ( trunc ( aggposdebDto.getDetEsito (), 500 ) ); // normalizzazione alla lunghezza
			}

			// aggiorna le componenti importo sostituendo l'intera lista con quella nuova
			if ( aggposdebDto.getComponenteImportoDtoList () != null ) {
				aggposdebEntity.setEpaypaTComponenteImportos ( toComponenteImportoEntitySet ( aggposdebDto.getComponenteImportoDtoList () ) );
			}

			// aggiorna i riferimento pagamento sostituendo l'intera lista con quella nuova
			if ( aggposdebDto.getRiferimentoPagamentoDtoList () != null ) {
				aggposdebEntity.setEpaypaTRiferimentoPagamentos ( toRiferimentoPagamentoEntitySet ( aggposdebDto.getRiferimentoPagamentoDtoList () ) );
			}

			// soggetto pagatore
			if ( aggposdebDto.getSoggettoAnagraficoDto () != null ) {
				aggposdebEntity.setEpaypaTSoggetto ( newEpaypaTSoggetto( aggposdebDto.getSoggettoAnagraficoDto () ) );
			}

			if ( aggposdebDto.getImportoSecondarioAltroEnte () != null ) {
				aggposdebEntity.setImportoSecondarioAltroEnte ( aggposdebDto.getImportoSecondarioAltroEnte () );
			}
			
			if ( aggposdebDto.getImportoPrincipale () != null ) {
                aggposdebEntity.setImportoPrincipale ( aggposdebDto.getImportoPrincipale () );
            }
		}
	}
	//@formatter:on

	private EpaypaTComponenteImporto toComponenteImportoEntity ( ComponenteImportoDto dto ) {
		EpaypaTComponenteImporto entity = null;
		if ( dto != null ) {
			entity = new EpaypaTComponenteImporto ();
			entity.setImporto ( dto.getImporto () );
			entity.setCausale ( dto.getCausale () );
			entity.setDatiSpecRiscossione ( dto.getDatiSpecificiRiscossione () );
			entity.setAnnoAccertamento ( dto.getAnnoAccertamento () );
			entity.setNumeroAccertamento ( dto.getNumeroAccertamento () );
			entity.setFlagComponenteSecondario ( dto.getFlagComponenteSecondario () );
		}
		return entity;
	}

	private EpaypaTRiferimentoPagamento toRiferimentoPagamentoEntity ( RiferimentoPagamentoDto dto ) {
		EpaypaTRiferimentoPagamento entity = null;
		if ( dto != null ) {
			entity = new EpaypaTRiferimentoPagamento ();
			entity.setNome ( dto.getNome () );
			entity.setValore ( dto.getValore () );
		}
		return entity;
	}

	private Set<EpaypaTComponenteImporto> toComponenteImportoEntitySet ( List<ComponenteImportoDto> dtoList ) {
		Set<EpaypaTComponenteImporto> entitySet = new HashSet<> ();
		for ( ComponenteImportoDto dto: dtoList ) {
			entitySet.add ( toComponenteImportoEntity ( dto ) );
		}
		return entitySet;
	}

	private Set<EpaypaTRiferimentoPagamento> toRiferimentoPagamentoEntitySet ( List<RiferimentoPagamentoDto> dtoList ) {
		Set<EpaypaTRiferimentoPagamento> entitySet = new LinkedHashSet<> ();
		for ( RiferimentoPagamentoDto dto: dtoList ) {
			entitySet.add ( toRiferimentoPagamentoEntity ( dto ) );
		}
		return entitySet;
	}


	private String getEpayWsoSrvEndpoint () {
		String key = EpayPaEpaywsoSrvService.URL_PROPERTY;

		String endpoint = getStringProperty ( key );

		if ( StringUtils.isEmpty ( endpoint ) ) {
			log.error( "PARAMETRO DI CONFIGURAZIONE NON DISPONIBILE PER KEY='" + key + "'" );
			throw new RuntimeException ( "PARAMETRO DI CONFIGURAZIONE NON DISPONIBILE PER KEY='" + key + "'" );
		}

		return endpoint;
	}

	private String getStringProperty(String key) {
		if(config==null) {
			config = new Properties();
			loadProperties();
		}
		return config.getProperty(key);
	}

	private void loadProperties() {
		config = new Properties();
		try {
			InputStream configInputStream = GestioneFlussiDadImpl.class.getResourceAsStream("/WEB-INF/application-config.properties");
			config.load(configInputStream);

		} catch (IOException e) {
			log.error("EEORE DURANTE IL CARICAMENTO DEL FILE DI PROPERTIES: /WEB-INF/application-config.properties ", e);
			e.printStackTrace();
		}
	}

	@Override
	public PosizioneDebitoriaAutocompleteDto getPosizioneDebitoriaAutoComplete ( String partialPosizioneDebitoria, Integer enteId,
		Integer idCodVersamento ) throws PersistenceException {

		PosizioneDebitoriaAutocompleteDto result = new PosizioneDebitoriaAutocompleteDto ();
		List<PosizioneDebitoriaAutocompleteElementDto> posizioneDebitoriaAutocompleteElementDtoList = new ArrayList<> ();
		result.setPosizioneDebitoriaAutocompleteElementDtoList ( posizioneDebitoriaAutocompleteElementDtoList );

		List<EpaypaTPosizioneDebitoriaForAutocomplete> list
		= epaypaTPosizioneDebitoriaForAutocompleteDao.getAutocompleteData ( partialPosizioneDebitoria, enteId, idCodVersamento );

		for (EpaypaTPosizioneDebitoriaForAutocomplete element : list) {
			PosizioneDebitoriaAutocompleteElementDto dtoEl = new PosizioneDebitoriaAutocompleteElementDto ();
			dtoEl.setIdPosizioneDebitoria ( element.getIdPosizioneDebitoria () );
			dtoEl.setIdPosizioneDebitoriaEst ( element.getIdPosizioneDebitoriaEst () );
			posizioneDebitoriaAutocompleteElementDtoList.add ( dtoEl );
		}

		return result;
	}

	@Override public PosizioneDebitoriaAutocompleteIUVDto getPosizioneDebitoriaIUVAutoComplete ( String partialIUV, Integer enteId, Integer idCodVersamento )
					throws PersistenceException {
		PosizioneDebitoriaAutocompleteIUVDto result = new PosizioneDebitoriaAutocompleteIUVDto ();
		List<PosizioneDebitoriaAutocompleteIUVElementDto> posizioneDebitoriaAutocompleteIUVElementDtoList = new ArrayList<> ();
		result.setPosizioneDebitoriaAutocompleteIUVElementDtoList ( posizioneDebitoriaAutocompleteIUVElementDtoList );

		List<EpaypaTposizioneDebitoriaForAutocompleteIUV> list
		= epaypaTPosizioneDebitoriaForAutocompleteIUVDao.getAutocompleteData ( partialIUV, enteId, idCodVersamento );

		for (EpaypaTposizioneDebitoriaForAutocompleteIUV element : list) {
			PosizioneDebitoriaAutocompleteIUVElementDto dtoEl = new PosizioneDebitoriaAutocompleteIUVElementDto();
			dtoEl.setIdPosizioneDebitoria ( element.getIdPosizioneDebitoria () );
			dtoEl.setIuv ( element.getIuv () );
			posizioneDebitoriaAutocompleteIUVElementDtoList.add ( dtoEl );
		}

		return result;
	}

	@Override
	public Long countAllPosizioneDebitoriaByIdEnteIdCovPosDebEst ( String posizioneDebitoriaEst, Integer enteId, Integer idCodVersamento )
					throws PersistenceException {
		String methodName = "countAllPosizioneDebitoriaByIdEnteIdCovPosDebEst";
		Long num;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );
			num = epaypaTPosizioneDebitoriaDao.countAllPosizioneDebitoriaByIdEnteIdCovPosDebEst ( posizioneDebitoriaEst, enteId, idCodVersamento );
		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
		return num;
	}

}
