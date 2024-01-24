/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dad;

import it.csi.epay.epaypaweb.dto.AvvisoScadutoDto;
import it.csi.epay.epaypaweb.dto.CategoriaCduDto;
import it.csi.epay.epaypaweb.dto.CduDto;
import it.csi.epay.epaypaweb.dto.CodiceVersamentoDto;
import it.csi.epay.epaypaweb.dto.ColonnaTemplateDto;
import it.csi.epay.epaypaweb.dto.ComponenteImportoDto;
import it.csi.epay.epaypaweb.dto.ConfigurazioneDto;
import it.csi.epay.epaypaweb.dto.DatiAvvisoPagamentoDto;
import it.csi.epay.epaypaweb.dto.DettaglioPagamentiDto;
import it.csi.epay.epaypaweb.dto.DettaglioPagamentiDto.DettaglioComponenteImportoDto;
import it.csi.epay.epaypaweb.dto.DettaglioPagamentiDto.DettaglioPosizioneDebitoriaDto;
import it.csi.epay.epaypaweb.dto.DettaglioPagamentiDto.DettaglioSoggettoDebitoreDto;
import it.csi.epay.epaypaweb.dto.EnteDto;
import it.csi.epay.epaypaweb.dto.FlussoDto;
import it.csi.epay.epaypaweb.dto.FlussoLightDto;
import it.csi.epay.epaypaweb.dto.InvioMailDto;
import it.csi.epay.epaypaweb.dto.NotificaPagamentoDto;
import it.csi.epay.epaypaweb.dto.NotificaPagamentoLightDto;
import it.csi.epay.epaypaweb.dto.PagamentiDto;
import it.csi.epay.epaypaweb.dto.PagamentiExportDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaDaAggiornareDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaDaAggiornareLightDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaLightDto;
import it.csi.epay.epaypaweb.dto.ProfiloDto;
import it.csi.epay.epaypaweb.dto.RichiestaRevocheFilterDto;
import it.csi.epay.epaypaweb.dto.RiferimentoPagamentoDto;
import it.csi.epay.epaypaweb.dto.RiversamentoDto;
import it.csi.epay.epaypaweb.dto.RuoloDto;
import it.csi.epay.epaypaweb.dto.SingolaRevocaDto;
import it.csi.epay.epaypaweb.dto.SoggettoAnagraficoDto;
import it.csi.epay.epaypaweb.dto.StatoFlussoDto;
import it.csi.epay.epaypaweb.dto.TemplateDto;
import it.csi.epay.epaypaweb.dto.TipoAggiornamentoPosizioneDebitoriaDto;
import it.csi.epay.epaypaweb.dto.TipoFlussoDto;
import it.csi.epay.epaypaweb.dto.TipoFormatoFileDto;
import it.csi.epay.epaypaweb.enumeration.CampoFlussoEnum;
import it.csi.epay.epaypaweb.enumeration.EsitoRiversamentoEnum;
import it.csi.epay.epaypaweb.enumeration.StatoFlussoEnum;
import it.csi.epay.epaypaweb.enumeration.TipoAggiornamentoPosizioneDebitoriaEnum;
import it.csi.epay.epaypaweb.enumeration.TipoFlussoEnum;
import it.csi.epay.epaypaweb.enumeration.TipoFormatoFileEnum;
import it.csi.epay.epaypaweb.enumeration.TipoMailEnum;
import it.csi.epay.epaypaweb.enumeration.TipoMittenteEnum;
import it.csi.epay.epaypaweb.enumeration.TipologiaSoggettoEnum;
import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dao.interf.filter.EpaypaTPosizioneDebitoriaFilterDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaDStatoFlusso;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaDTipoAggPosizioneDebitoria;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaDTipoFlusso;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaDTipoFormatoOutput;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTAggPosizioneDebitoria;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTAggPosizioneDebitoriaLight;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTAvvisoScaduto;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTCdu;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTCodiceVersamento;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTColonnaTemplate;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTComponenteImporto;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTConfigurazione;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTDatiAvvisoPagamento;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTDatiSingolaRevoca;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTEnte;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTFlusso;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTFlussoLight;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTInvioMail;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTNotificaPagamento;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTNotificaPagamentoLight;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTNotificaPagamentoMedium;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTPagamenti;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTPosizioneDebitoria;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTPosizioneDebitoriaLight;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTPosizioneDebitoriaMedium;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTProfilo;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTRendicontazione;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTRendicontazioneLight;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTRiferimentoPagamento;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTRiversamento;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTRr;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTRuolo;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTSoggetto;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTTemplate;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTTransazionePsp;
import it.csi.epay.epaypaweb.persistence.entity.filter.EpaypaTFlussoFilter;
import it.csi.epay.epaypaweb.persistence.entity.filter.EpaypaTPosizioneDebitoriaFilter;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.persistence.EntityTransaction;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import static it.csi.epay.epaypaweb.util.Util.APPLICATION_CODE;


public class EPaypaDadBaseImpl implements EPaypaDadBase {

	@Inject
	private EpaypaTPosizioneDebitoriaFilterDao epaypaTPosizioneDebitoriaFilterDao;

	protected static Logger log = LogManager.getLogger ( APPLICATION_CODE + ".persistence" );

	protected EntityTransaction transaction;

	protected Timestamp getTimestampNow () {
		return new Timestamp ( System.currentTimeMillis () );
	}

	protected Timestamp toTimestamp ( Date date ) {
		Timestamp timestamp = null;
		if ( date != null ) {
			timestamp = new Timestamp ( date.getTime () );
		}
		return timestamp;
	}

	protected String trunc ( String s, int maxlength ) {
		String t = s;

		if ( s != null && s.length () > maxlength ) {
			t = s.substring ( 0, maxlength );
		}

		return t;
	}

	protected EnteDto toEnteDto ( EpaypaTEnte entity ) {
		EnteDto dto = null;

		if ( entity != null ) {
			dto = new EnteDto ( entity.getIdEnte (), entity.getCodFiscaleEnte () );
			dto.setDenominazione ( entity.getDenominazione () );
			dto.setEmail ( entity.getEmail () );
		}

		return dto;
	}

	protected List<EnteDto> toEnteDtoList ( List<EpaypaTEnte> entityList ) {
		List<EnteDto> dtoList = null;

		if ( entityList != null ) {
			dtoList = new ArrayList<> ();
			for ( EpaypaTEnte entity: entityList ) {
				dtoList.add ( toEnteDto ( entity ) );
			}
		}

		return dtoList;
	}

	protected CodiceVersamentoDto toCodiceVersamentoDto ( EpaypaTCodiceVersamento entity ) {
		CodiceVersamentoDto dto = null;

		if ( entity != null ) {
			dto = new CodiceVersamentoDto ( entity.getIdCodiceVersamento () );
			dto.setCod ( entity.getCodVersamento () );
			dto.setDes ( entity.getDescrizione () );
			dto.setEnteDto ( toEnteDto ( entity.getEpaypaTEnte () ) );
			dto.setIdTipoPagamento ( entity.getIdTipoPagamento () );
			dto.setFlagBollettinoPostale (entity.getBollettinoPostale());
			dto.setFlagMbPrimario ( entity.getFlagMbPrimario () );
			dto.setFlagMbSecondario ( entity.getFlagMbSecondario () );
		}

		return dto;
	}

	protected List<CodiceVersamentoDto> toCodiceVersamentoDtoList ( List<EpaypaTCodiceVersamento> entityList ) {
		List<CodiceVersamentoDto> dtoList = null;

		if ( entityList != null ) {
			dtoList = new ArrayList<> ();
			for ( EpaypaTCodiceVersamento entity: entityList ) {
				dtoList.add ( toCodiceVersamentoDto ( entity ) );
			}
		}

		return dtoList;
	}

	protected TipoFlussoDto toTipoFlussoDto ( EpaypaDTipoFlusso entity ) {
		TipoFlussoDto dto = null;

		if ( entity != null ) {
			dto = new TipoFlussoDto ( TipoFlussoEnum.fromId ( entity.getIdTipoFlusso () ), entity.getDirezioneFlusso () );
			dto.setDescrizione ( entity.getDescrizione () );
		}

		return dto;
	}

	protected List<TipoFlussoDto> toTipoFlussoDtoList ( List<EpaypaDTipoFlusso> entityList ) {
		List<TipoFlussoDto> dtoList = null;

		if ( entityList != null ) {
			dtoList = new ArrayList<> ();
			for ( EpaypaDTipoFlusso entity: entityList ) {
				dtoList.add ( toTipoFlussoDto ( entity ) );
			}
		}

		return dtoList;
	}

	protected StatoFlussoDto toStatoFlussoDto ( EpaypaDStatoFlusso entity ) {
		StatoFlussoDto dto = null;

		if ( entity != null ) {
			dto = new StatoFlussoDto ( StatoFlussoEnum.fromId ( entity.getIdStatoFlusso () ), entity.getDirezioneFlusso () );
			dto.setDescrizione ( entity.getDescrizione () );
		}

		return dto;
	}

	protected List<StatoFlussoDto> toStatoFlussoDtoList ( List<EpaypaDStatoFlusso> entityList ) {
		List<StatoFlussoDto> dtoList = null;

		if ( entityList != null ) {
			dtoList = new ArrayList<> ();
			for ( EpaypaDStatoFlusso entity: entityList ) {
				dtoList.add ( toStatoFlussoDto ( entity ) );
			}
		}

		return dtoList;
	}

	protected TipoAggiornamentoPosizioneDebitoriaDto toTipoAggiornamentoPosizioneDebitoriaDto ( EpaypaDTipoAggPosizioneDebitoria entity ) {
		TipoAggiornamentoPosizioneDebitoriaDto dto = null;

		if ( entity != null ) {
			dto = new TipoAggiornamentoPosizioneDebitoriaDto ( TipoAggiornamentoPosizioneDebitoriaEnum.fromId ( entity.getIdTipoAggPosizioneDebitoria () ) );
			dto.setDescrizione ( entity.getDescrizione () );
		}

		return dto;
	}

	protected List<TipoAggiornamentoPosizioneDebitoriaDto> toTipoAggiornamentoPosizioneDebitoriaDtoList ( List<EpaypaDTipoAggPosizioneDebitoria> entityList ) {
		List<TipoAggiornamentoPosizioneDebitoriaDto> dtoList = null;

		if ( entityList != null ) {
			dtoList = new ArrayList<> ();
			for ( EpaypaDTipoAggPosizioneDebitoria entity: entityList ) {
				dtoList.add ( toTipoAggiornamentoPosizioneDebitoriaDto ( entity ) );
			}
		}

		return dtoList;
	}

	protected TipoFormatoFileDto toTipoFormatoFileDto ( EpaypaDTipoFormatoOutput entity ) {
		TipoFormatoFileDto dto = null;

		if ( entity != null ) {
			dto = new TipoFormatoFileDto ( TipoFormatoFileEnum.fromId ( entity.getIdTipoFormatoOutput () ) );
			dto.setDescrizione ( entity.getDescrizione () );
		}

		return dto;
	}

	protected List<TipoFormatoFileDto> toTipoFormatoFileDtoList ( List<EpaypaDTipoFormatoOutput> entityList ) {
		List<TipoFormatoFileDto> dtoList = null;

		if ( entityList != null ) {
			dtoList = new ArrayList<> ();
			for ( EpaypaDTipoFormatoOutput entity: entityList ) {
				dtoList.add ( toTipoFormatoFileDto ( entity ) );
			}
		}

		return dtoList;
	}

	protected InvioMailDto toInvioMailDto ( EpaypaTInvioMail entity ) {
		InvioMailDto dto = null;

		if ( entity != null ) {
			dto = new InvioMailDto ( entity.getIdInvioMail () );
			dto.setTo ( entity.getDestinatarioTo () );
			dto.setCc ( entity.getDestinatarioCc () );
			dto.setBcc ( null );
			if ( entity.getEpaypaDTipoInvioMail () != null ) {
				dto.setTipoMailEnum ( TipoMailEnum.fromId ( entity.getEpaypaDTipoInvioMail ().getIdTipoInvioMail () ) );
			}
			dto.setDataInserimento ( new Date ( entity.getDtInserimento ().getTime () ) );
			dto.setIdFlusso ( entity.getIdFlusso () );
			if ( entity.getDtUltimoTentativo () != null ) {
				dto.setDataUltimoTentativo ( new Date ( entity.getDtUltimoTentativo ().getTime () ) );
			}
			dto.setEsitoUltimoTentativo ( entity.getEsitoUltimoTentativo () );
			dto.setErroreInvio ( entity.getErroreInvio () );
		}

		return dto;
	}

	protected RuoloDto toRuoloDto ( EpaypaTRuolo entity ) {
		RuoloDto dto = null;

		if ( entity != null ) {
			dto = new RuoloDto ( entity.getIdRuolo (), entity.getCodRuolo () );
			dto.setDes ( entity.getDescrizione () );
		}

		return dto;
	}

	protected List<RuoloDto> toRuoloDtoList ( List<EpaypaTRuolo> entityList ) {
		List<RuoloDto> dtoList = null;

		if ( entityList != null ) {
			dtoList = new ArrayList<> ();
			for ( EpaypaTRuolo entity: entityList ) {
				dtoList.add ( toRuoloDto ( entity ) );
			}
		}

		return dtoList;
	}

	protected ProfiloDto toProfiloDto ( EpaypaTProfilo entity ) {
		ProfiloDto dto = null;

		if ( entity != null ) {
			dto = new ProfiloDto ( entity.getIdProfilo () );
			dto.setDes ( entity.getDescrizione () );
		}

		return dto;
	}

	protected List<ProfiloDto> toProfiloDtoList ( List<EpaypaTProfilo> entityList ) {
		List<ProfiloDto> dtoList = null;

		if ( entityList != null ) {
			dtoList = new ArrayList<> ();
			for ( EpaypaTProfilo entity: entityList ) {
				dtoList.add ( toProfiloDto ( entity ) );
			}
		}

		return dtoList;
	}

	protected CduDto toCduDto ( EpaypaTCdu entity ) {
		CduDto dto = null;

		if ( entity != null ) {
			dto = new CduDto ( entity.getIdCdu (), entity.getCodCdu (),
				new CategoriaCduDto ( entity.getEpaypaDCategoriaCdu ().getIdCategoriaCdu (),
					entity.getEpaypaDCategoriaCdu ().getCodCategoriaCdu () ) );
			dto.setDes ( entity.getDescrizione () );
		}

		return dto;
	}

	protected List<CduDto> toCduDtoList ( List<EpaypaTCdu> entityList ) {
		List<CduDto> dtoList = null;

		if ( entityList != null ) {
			dtoList = new ArrayList<> ();
			for ( EpaypaTCdu entity: entityList ) {
				dtoList.add ( toCduDto ( entity ) );
			}
		}

		return dtoList;
	}

	protected FlussoLightDto toFlussoLightDto ( EpaypaTFlussoLight entity ) {
		FlussoLightDto dto = null;

		if ( entity != null ) {
			dto = new FlussoLightDto ( entity.getIdFlusso () );
			if ( entity.getEpaypaDTipoFlusso () != null ) {
				dto.setTipoFlusso ( TipoFlussoEnum.fromId ( entity.getEpaypaDTipoFlusso ().getIdTipoFlusso () ) );
				dto.setDesTipoFlusso ( entity.getEpaypaDTipoFlusso ().getDescrizione () );
			}
			if ( entity.getEpaypaDStatoFlusso () != null ) {
				dto.setStatoFlusso ( StatoFlussoEnum.fromId ( entity.getEpaypaDStatoFlusso ().getIdStatoFlusso () ) );
				dto.setDesStatoFlusso ( entity.getEpaypaDStatoFlusso ().getDescrizione () );
			}
			dto.setIdMessaggio ( entity.getIdMessaggio () );
			if ( entity.getEpaypaTEnte () != null ) {
				dto.setCodFiscaleEnte ( entity.getEpaypaTEnte ().getCodFiscaleEnte () );
				dto.setDenominazioneEnte ( entity.getEpaypaTEnte ().getDenominazione () );
			}
			if ( entity.getEpaypaTCodiceVersamento () != null ) {
				dto.setCodVersamento ( entity.getEpaypaTCodiceVersamento ().getCodVersamento () );
				dto.setDesCodVersamento ( entity.getEpaypaTCodiceVersamento ().getDescrizione () );
			}
			dto.setNumeroElementi ( entity.getNumeroElementi () );
			dto.setImportoTotale ( entity.getImportoTotale () );
			dto.setPagamentiSpontanei ( entity.getPagamentiSpontanei () );
			dto.setDataInserimento ( entity.getDtInserimento () );
			dto.setDataUltimaVariazione ( entity.getDtUltimaVariazione () );
			dto.setUtenteUltimaVariazione ( entity.getUtenteUltimaVariazione () );
			dto.setCodEsito ( entity.getCodEsito () );
			dto.setDetEsito ( entity.getDettaglioEsito () );
			if ( entity.getEpaypaTRendicontazione () != null ) {
				EpaypaTRendicontazioneLight rendEntity = entity.getEpaypaTRendicontazione ();
				dto.setIdFlussoRendicontazione ( rendEntity.getIdFlussoRendicontazione () );
				dto.setDataOraCreazioneFlusso ( rendEntity.getDtOraFlusso () );
				dto.setIdUnivocoRegolamento ( rendEntity.getIdUnivocoRegolamento () );
				dto.setDataRegolamento ( rendEntity.getDtRegolamento () );
				dto.setTipoMittente ( TipoMittenteEnum.fromId ( rendEntity.getTipoIdMittente () ) );
				dto.setCodIdUnivocoMittente ( rendEntity.getCodIdUnivocoMittente () );
				dto.setDenominazioneMittente ( rendEntity.getDenominazioneMittente () );
				dto.setCodBicPsp ( rendEntity.getCodBicPsp () );
			}
		}

		return dto;
	}

	protected RichiestaRevocheFilterDto toRevocheDto ( EpaypaTRr entity ) {

		RichiestaRevocheFilterDto dto = null;
		if ( entity != null ) {
			dto = new RichiestaRevocheFilterDto ();
			dto.setApplicationId ( entity.getApplicationId () );
			dto.setCodiceContestoPagamento ( entity.getCodiceContestoPagamento () );
			dto.setCodiceIdentificativoUnivocoAttestante ( entity.getCodiceIdentificativoUnivocoAttestante () );
			dto.setDataOraMessaggioRevocaAl ( entity.getDataOraMessaggioRevoca () );
			dto.setDenominazioneIstitutoAttestante ( entity.getDenominazioneIstitutoAttestante () );
			dto.setIdentificativoDominio ( entity.getIdentificativoDominio () );
			dto.setIdentificativoMessaggioRevoca ( entity.getIdentificativoMessaggioRevoca () );
			dto.setIdRr ( entity.getIdRr () );
			dto.setImportoTotaleTevocato ( entity.getImportoTotaleTevocato () );
			dto.setIuv ( entity.getIuv () );
			dto.setTipoRevoca ( entity.getTipoRevoca () );
			dto.setNomeCognome ( entity.getNomeCognome () );
			dto.setCodiceFiscale ( entity.getCodiceFiscale () );
		}
		return dto;
	}

	//--------------------------------//
	//RDI-048 - START
	//--------------------------------//
	protected FlussoLightDto toFlussoLightDto ( EpaypaTNotificaPagamento entity ) {
		FlussoLightDto dto = null;

		if ( entity != null ) {
			dto = new FlussoLightDto ( entity.getEpaypaTFlusso ().getIdFlusso () );
			if ( entity.getEpaypaTFlusso ().getEpaypaDTipoFlusso () != null ) {
				dto.setTipoFlusso ( TipoFlussoEnum.fromId ( entity.getEpaypaTFlusso ().getEpaypaDTipoFlusso ().getIdTipoFlusso () ) );
				dto.setDesTipoFlusso ( entity.getEpaypaTFlusso ().getEpaypaDTipoFlusso ().getDescrizione () );
			}
			if ( entity.getEpaypaTFlusso ().getEpaypaDStatoFlusso () != null ) {
				dto.setStatoFlusso ( StatoFlussoEnum.fromId ( entity.getEpaypaTFlusso ().getEpaypaDStatoFlusso ().getIdStatoFlusso () ) );
				dto.setDesStatoFlusso ( entity.getEpaypaTFlusso ().getEpaypaDStatoFlusso ().getDescrizione () );
			}
			dto.setIdMessaggio ( entity.getEpaypaTFlusso ().getIdMessaggio () );
			if ( entity.getEpaypaTFlusso ().getEpaypaTEnte () != null ) {
				dto.setCodFiscaleEnte ( entity.getEpaypaTFlusso ().getEpaypaTEnte ().getCodFiscaleEnte () );
				dto.setDenominazioneEnte ( entity.getEpaypaTFlusso ().getEpaypaTEnte ().getDenominazione () );
			}
			if ( entity.getEpaypaTFlusso ().getEpaypaTCodiceVersamento () != null ) {
				dto.setCodVersamento ( entity.getEpaypaTFlusso ().getEpaypaTCodiceVersamento ().getCodVersamento () );
				dto.setDesCodVersamento ( entity.getEpaypaTFlusso ().getEpaypaTCodiceVersamento ().getDescrizione () );
			}
			dto.setNumeroElementi ( entity.getEpaypaTFlusso ().getNumeroElementi () );
			dto.setImportoTotale ( entity.getEpaypaTFlusso ().getImportoTotale () );
			dto.setPagamentiSpontanei ( entity.getEpaypaTFlusso ().getPagamentiSpontanei () );
			dto.setDataInserimento ( entity.getEpaypaTFlusso ().getDtInserimento () );
			dto.setDataUltimaVariazione ( entity.getEpaypaTFlusso ().getDtUltimaVariazione () );
			dto.setUtenteUltimaVariazione ( entity.getEpaypaTFlusso ().getUtenteUltimaVariazione () );
			dto.setCodEsito ( entity.getEpaypaTFlusso ().getCodEsito () );
			dto.setDetEsito ( entity.getEpaypaTFlusso ().getDettaglioEsito () );

			if ( entity.getEpaypaTFlusso ().getEpaypaTRendicontazione () != null ) {
				EpaypaTRendicontazione rendEntity = entity.getEpaypaTFlusso ().getEpaypaTRendicontazione ();
				dto.setIdFlussoRendicontazione ( rendEntity.getIdFlussoRendicontazione () );
				dto.setDataOraCreazioneFlusso ( rendEntity.getDtOraFlusso () );
				dto.setIdUnivocoRegolamento ( rendEntity.getIdUnivocoRegolamento () );
				dto.setDataRegolamento ( rendEntity.getDtRegolamento () );
				dto.setTipoMittente ( TipoMittenteEnum.fromId ( rendEntity.getTipoIdMittente () ) );
				dto.setCodIdUnivocoMittente ( rendEntity.getCodIdUnivocoMittente () );
				dto.setDenominazioneMittente ( rendEntity.getDenominazioneMittente () );
				dto.setCodBicPsp ( rendEntity.getCodBicPsp () );
			}

			if ( entity.getEpaypaTSoggettoDebitore () != null ) {
				dto.setCognome ( entity.getEpaypaTSoggettoDebitore ().getCognomeRagSoc () + " " + entity.getEpaypaTSoggettoDebitore ().getNome () );
				dto.setCfPivaSoggettoDebitore ( entity.getEpaypaTSoggettoDebitore ().getIdUnivocoFiscale () );
			}

			dto.setIuv ( entity.getIuv () );
			dto.setPosizioneDebitoria ( entity.getIdPosizioneDebitoria () );
			dto.setImportoPagato ( entity.getImportoPagato () );
			dto.setDataEsitoPagamento ( entity.getDtEsitoPagamento () );
			dto.setCausaleVersamento ( entity.getDesCausaleVersamento () );
			dto.setIdNotificaPagamento ( entity.getIdNotificaPagamento () );
			dto.setRevoca ( entity.getRevoca () );
		}

		return dto;
	}
	//--------------------------------//
	//RDI-048 - STOP
	//--------------------------------//

	protected List<FlussoLightDto> toFlussoLightDtoList ( List<EpaypaTFlussoLight> entityList ) {
		List<FlussoLightDto> dtoList = null;

		if ( entityList != null ) {
			dtoList = new ArrayList<> ();
			for ( EpaypaTFlussoLight entity: entityList ) {
				dtoList.add ( toFlussoLightDto ( entity ) );
			}
		}

		return dtoList;
	}

	protected List<EpaypaTFlussoLight> toEpaypaTFlussoLightList ( List<EpaypaTFlussoFilter> epaypaTFlussoFilters ) throws PersistenceException {
		List<EpaypaTFlussoLight> epaypaTFlussoLights = null;

		if ( null != epaypaTFlussoFilters ) {
			epaypaTFlussoLights = new ArrayList<> ();
			for ( EpaypaTFlussoFilter epaypaTFlussoFilter : epaypaTFlussoFilters ) {
				epaypaTFlussoLights.add ( toFlussoLight ( epaypaTFlussoFilter ) );
			}
		}

		return epaypaTFlussoLights;
	}

	private EpaypaTFlussoLight toFlussoLight ( EpaypaTFlussoFilter e ) throws PersistenceException {
		EpaypaTFlussoLight epaypaTFlussoLight = null;

		if ( null != e ) {
			epaypaTFlussoLight = new EpaypaTFlussoLight ();
			epaypaTFlussoLight.setIdFlusso ( e.getIdFlusso () );
			epaypaTFlussoLight.setEpaypaDTipoFlusso ( e.getEpaypaDTipoFlusso () );
			epaypaTFlussoLight.setEpaypaDStatoFlusso ( e.getEpaypaDStatoFlusso () );
			epaypaTFlussoLight.setIdMessaggio ( e.getIdMessaggio () );
			epaypaTFlussoLight.setEpaypaTEnte ( e.getEpaypaTEnte () );
			epaypaTFlussoLight.setEpaypaTCodiceVersamento ( e.getEpaypaTCodiceVersamento () );
			epaypaTFlussoLight.setNumeroElementi ( e.getNumeroElementi () );
			epaypaTFlussoLight.setImportoTotale ( e.getImportoTotale () );
			epaypaTFlussoLight.setPagamentiSpontanei ( e.getPagamentiSpontanei () );
			epaypaTFlussoLight.setDtInserimento ( e.getDtInserimento () );
			epaypaTFlussoLight.setDtUltimaVariazione ( e.getDtUltimaVariazione () );
			epaypaTFlussoLight.setUtenteUltimaVariazione ( e.getUtenteUltimaVariazione () );
			epaypaTFlussoLight.setCodEsito ( e.getCodEsito () );
			epaypaTFlussoLight.setDettaglioEsito ( e.getDettaglioEsito () );
			if ( null != e.getEpaypaTRendicontazione () ) {
				EpaypaTRendicontazioneLight epaypaTRendicontazioneLight = new EpaypaTRendicontazioneLight ();
				epaypaTRendicontazioneLight.setIdFlusso ( e.getEpaypaTRendicontazione ().getIdFlusso () );
				epaypaTRendicontazioneLight.setCodBicPsp ( e.getEpaypaTRendicontazione ().getCodBicPsp () );
				epaypaTRendicontazioneLight.setIdFlussoRendicontazione ( e.getEpaypaTRendicontazione ().getIdFlussoRendicontazione () );
				epaypaTRendicontazioneLight.setDenominazioneMittente ( e.getEpaypaTRendicontazione ().getDenominazioneMittente () );
				epaypaTRendicontazioneLight.setDtOraFlusso ( e.getEpaypaTRendicontazione ().getDtOraFlusso () );
				epaypaTRendicontazioneLight.setDtRegolamento ( e.getEpaypaTRendicontazione ().getDtRegolamento () );
				epaypaTRendicontazioneLight.setIdFlussoRendicontazione ( e.getEpaypaTRendicontazione ().getIdFlussoRendicontazione () );
				epaypaTRendicontazioneLight.setIdUnivocoRegolamento ( e.getEpaypaTRendicontazione ().getIdUnivocoRegolamento () );
				epaypaTRendicontazioneLight.setTipoIdMittente ( e.getEpaypaTRendicontazione ().getTipoIdMittente () );
				epaypaTFlussoLight.setEpaypaTRendicontazione ( epaypaTRendicontazioneLight );
			}
			if ( null != e.getEpaypaTPosizioneDebitoriaLight () ) {
				List<EpaypaTPosizioneDebitoriaLight> epdList = new ArrayList<> ();
				List<EpaypaTPosizioneDebitoriaFilter> list = epaypaTPosizioneDebitoriaFilterDao.findAllByIdFlusso ( e.getIdFlusso () );
				for ( EpaypaTPosizioneDebitoriaFilter f : list ) {
					EpaypaTPosizioneDebitoriaLight epd = new EpaypaTPosizioneDebitoriaLight ();
					epd.setIdPosizioneDebitoria ( f.getIdPosizioneDebitoria () );
					//					epd.setIdFlusso ( f.getIdFlusso () );
					epd.setIuv ( f.getIuv () );
					epd.setImportoTotale ( f.getImportoTotale () );
					epd.setDesCausaleVersamento ( f.getDesCausaleVersamento () );
					epd.setDtScadenza ( f.getDtScadenza () );
					if ( null != f.getEpaypaTSoggettoDebitore () ) {
						EpaypaTSoggetto epaypaTSoggetto = new EpaypaTSoggetto ();
						epaypaTSoggetto.setIdSoggetto ( f.getEpaypaTSoggettoDebitore ().getIdSoggetto () );
						epaypaTSoggetto.setCap ( f.getEpaypaTSoggettoDebitore ().getCap () );
						epaypaTSoggetto.setCivico ( f.getEpaypaTSoggettoDebitore ().getCivico () );
						epaypaTSoggetto.setIdUnivocoFiscale ( f.getEpaypaTSoggettoDebitore ().getIdUnivocoFiscale () );
						epaypaTSoggetto.setIndirizzo ( f.getEpaypaTSoggettoDebitore ().getIndirizzo () );
						epaypaTSoggetto.setLocalita ( f.getEpaypaTSoggettoDebitore ().getLocalita () );
						epaypaTSoggetto.setNazione ( f.getEpaypaTSoggettoDebitore ().getNazione () );
						epaypaTSoggetto.setNome ( f.getEpaypaTSoggettoDebitore ().getNome () );
						epaypaTSoggetto.setProvincia ( f.getEpaypaTSoggettoDebitore ().getProvincia () );
						epaypaTSoggetto.setTipologiaSoggetto ( f.getEpaypaTSoggettoDebitore ().getTipologiaSoggetto () );
						epd.setEpaypaTSoggettoDebitore ( epaypaTSoggetto );
					}
					epdList.add ( epd );
				}
				epaypaTFlussoLight.setEpaypaTPosizioneDebitoriaLight ( epdList );
			}
		}

		return epaypaTFlussoLight;
	}

	protected List<FlussoLightDto> toFlussoLightDtoListNotificaPagamento ( List<EpaypaTNotificaPagamento> entityList ) {
		List<FlussoLightDto> dtoList = null;

		if ( entityList != null ) {
			dtoList = new ArrayList<> ();
			for ( EpaypaTNotificaPagamento entity: entityList ) {
				dtoList.add ( toFlussoLightDto ( entity ) );
			}
		}

		return dtoList;
	}

	protected List<RichiestaRevocheFilterDto> toFlussoLightDtoListRevochePagamento ( List<EpaypaTRr> entityList ) {
		List<RichiestaRevocheFilterDto> dtoList = null;

		if ( entityList != null ) {
			dtoList = new ArrayList<> ();
			for ( EpaypaTRr entity: entityList ) {
				dtoList.add ( toRevocheDto ( entity ) );
			}
		}

		return dtoList;
	}

	protected FlussoDto toFlussoDto ( EpaypaTFlusso entity ) {
		FlussoDto dto = null;

		if ( entity != null ) {
			// testata flusso
			dto = new FlussoDto ( entity.getIdFlusso () );
			if ( entity.getEpaypaDTipoFlusso () != null ) {
				dto.setTipoFlusso ( TipoFlussoEnum.fromId ( entity.getEpaypaDTipoFlusso ().getIdTipoFlusso () ) );
			}
			dto.setDesTipoFlusso ( entity.getEpaypaDTipoFlusso ().getDescrizione () );
			if ( entity.getEpaypaDStatoFlusso () != null ) {
				dto.setStatoFlusso ( StatoFlussoEnum.fromId ( entity.getEpaypaDStatoFlusso ().getIdStatoFlusso () ) );
			}
			dto.setDesStatoFlusso ( entity.getEpaypaDStatoFlusso ().getDescrizione () );
			dto.setIdMessaggio ( entity.getIdMessaggio () );
			dto.setIdEnte ( entity.getEpaypaTEnte ().getIdEnte () );
			dto.setCodFiscaleEnte ( entity.getEpaypaTEnte ().getCodFiscaleEnte () );
			dto.setDenominazioneEnte ( entity.getEpaypaTEnte ().getDenominazione () );
            if ( entity.getEpaypaTCodiceVersamento () != null ) {
                dto.setCodVersamento ( entity.getEpaypaTCodiceVersamento ().getCodVersamento () );
                dto.setDesCodVersamento ( entity.getEpaypaTCodiceVersamento ().getDescrizione () );
                dto.setFlagPrimario ( Boolean.TRUE.equals ( entity.getEpaypaTCodiceVersamento ().getFlagMbPrimario () )
                    || Boolean.TRUE.equals ( entity.getEpaypaTCodiceVersamento ().getFlagMbSecondario () ) );
            }
			dto.setNumeroElementi ( entity.getNumeroElementi () );
			dto.setImportoTotale ( entity.getImportoTotale () );
			dto.setPagamentiSpontanei ( entity.getPagamentiSpontanei () );
			dto.setDataInserimento ( entity.getDtInserimento () );
			dto.setUtenteInserimento ( entity.getUtenteInserimento () );
			dto.setDataUltimaVariazione ( entity.getDtUltimaVariazione () );
			dto.setUtenteUltimaVariazione ( entity.getUtenteUltimaVariazione () );
			dto.setCodEsito ( entity.getCodEsito () );
			dto.setDetEsito ( entity.getDettaglioEsito () );
			dto.setDataInoltro ( entity.getDtInoltro () );
		}

		return dto;
	}

	protected FlussoDto toFlussoDto ( EpaypaTRendicontazione entity ) {
		FlussoDto dto = null;

		if ( entity != null ) {
			dto = toFlussoDto ( entity.getEpaypaTFlusso () );
			dto.setIdFlussoRendicontazione ( entity.getIdFlussoRendicontazione () );
			dto.setDataOraCreazioneFlusso ( entity.getDtOraFlusso () );
			dto.setIdUnivocoRegolamento ( entity.getIdUnivocoRegolamento () );
			dto.setDataRegolamento ( entity.getDtRegolamento () );
			dto.setTipoMittente ( TipoMittenteEnum.fromId ( entity.getTipoIdMittente () ) );
			dto.setCodIdUnivocoMittente ( entity.getCodIdUnivocoMittente () );
			dto.setDenominazioneMittente ( entity.getDenominazioneMittente () );
			dto.setCodBicPsp ( entity.getCodBicPsp () );
		}

		return dto;
	}

	private static SoggettoAnagraficoDto toSoggettoAnagraficoDto ( EpaypaTSoggetto entity ) {
		SoggettoAnagraficoDto dto = null;

		if ( entity != null ) {
			dto = new SoggettoAnagraficoDto ();
			dto.setIdUnivocoFiscale ( entity.getIdUnivocoFiscale () );
			dto.setTipologiaSoggettoEnum ( TipologiaSoggettoEnum.fromId ( entity.getTipologiaSoggetto () ) );
			if ( dto.getTipologiaSoggettoEnum () != null ) {
				switch ( dto.getTipologiaSoggettoEnum () ) {
				case PERSONA_FISICA :
					dto.setCognome ( entity.getCognomeRagSoc () );
					break;
				case PERSONA_GIURIDICA :
					dto.setRagioneSociale ( entity.getCognomeRagSoc () );
					break;
				}
			}
			dto.setNome ( entity.getNome () );
			dto.setIndirizzo ( entity.getIndirizzo () );
			dto.setCivico ( entity.getCivico () );
			dto.setCap ( entity.getCap () );
			dto.setLocalita ( entity.getLocalita () );
			dto.setProvincia ( entity.getProvincia () );
			dto.setNazione ( entity.getNazione () );
			dto.setEmail ( entity.getEmail () );
		}

		return dto;
	}

	protected NotificaPagamentoLightDto toNotificaPagamentoLightDto ( EpaypaTNotificaPagamentoLight entity ) {
		NotificaPagamentoLightDto dto = null;

		if ( entity != null ) {
			dto = new NotificaPagamentoLightDto ( entity.getIdNotificaPagamento () );
			dto.setIdPosizioneDebitoria ( entity.getIdPosizioneDebitoria () );
			dto.setIUV ( entity.getIuv () );
			dto.setImportoPagato ( entity.getImportoPagato () );
			dto.setDesCausaleVersamento ( entity.getDesCausaleVersamento () );
			if ( entity.getDtEsitoPagamento () != null ) {
				dto.setDataEsitoPagamento ( new Date ( entity.getDtEsitoPagamento ().getTime () ) );
			}
			dto.setSoggettoDebitore ( toSoggettoAnagraficoDto ( entity.getEpaypaTSoggettoDebitore () ) );
		}

		return dto;
	}

	protected List<NotificaPagamentoLightDto> toNotificaPagamentoLightDtoList ( List<EpaypaTNotificaPagamentoLight> entityList ) {
		List<NotificaPagamentoLightDto> dtoList = null;

		if ( entityList != null ) {
			dtoList = new ArrayList<> ();
			for ( EpaypaTNotificaPagamentoLight entity: entityList ) {
				dtoList.add ( toNotificaPagamentoLightDto ( entity ) );
			}
		}

		return dtoList;
	}

	protected NotificaPagamentoDto toNotificaPagamentoDto ( EpaypaTNotificaPagamento notificaPagamentoEntity ) {
		NotificaPagamentoDto dto = null;

		if ( notificaPagamentoEntity != null ) {
			// notifica pagamento
			dto = new NotificaPagamentoDto ( notificaPagamentoEntity.getIdNotificaPagamento () );
			//
			if ( notificaPagamentoEntity.getEpaypaTFlusso () != null ) {
				dto.setIdFlusso ( notificaPagamentoEntity.getEpaypaTFlusso ().getIdFlusso () );
			}
			dto.setIdPosizioneDebitoria ( notificaPagamentoEntity.getIdPosizioneDebitoria () );
			dto.setAnnoRiferimento ( notificaPagamentoEntity.getAnnoRiferimento () );
			dto.setIUV ( notificaPagamentoEntity.getIuv () );
			dto.setImportoPagato ( notificaPagamentoEntity.getImportoPagato () );
			if ( notificaPagamentoEntity.getDtScadenza () != null ) {
				dto.setDataScadenza ( new Date ( notificaPagamentoEntity.getDtScadenza ().getTime () ) );
			}
			dto.setDesCausaleVersamento ( notificaPagamentoEntity.getDesCausaleVersamento () );
			if ( notificaPagamentoEntity.getDtEsitoPagamento () != null ) {
				dto.setDataEsitoPagamento ( new Date ( notificaPagamentoEntity.getDtEsitoPagamento ().getTime () ) );
			}
			dto.setSoggettoDebitore ( toSoggettoAnagraficoDto ( notificaPagamentoEntity.getEpaypaTSoggettoDebitore () ) );
			dto.setSoggettoVersante ( toSoggettoAnagraficoDto ( notificaPagamentoEntity.getEpaypaTSoggettoVersante () ) );
			//            dto.setDatiSpecificiRiscossione ( notificaPagamentoEntity.getDatiSpecificiRiscossione () );
			dto.setNote ( notificaPagamentoEntity.getNote () );
			dto.setCodAvviso ( notificaPagamentoEntity.getCodiceAvviso () );
			//
			// transazione psp
			EpaypaTTransazionePsp transazionePspEntity = notificaPagamentoEntity.getEpaypaTTransazionePsp ();
			dto.setIdPsp ( transazionePspEntity.getIdPsp () );
			dto.setRagioneSocialePsp ( transazionePspEntity.getRagioneSocialePsp () );
			if ( transazionePspEntity.getEpaypaDTipoVersamento () != null ) {
				dto.setCodTipoVersamento ( transazionePspEntity.getEpaypaDTipoVersamento ().getCodTipoVersamento () );
				dto.setDesTipoVersamento ( transazionePspEntity.getEpaypaDTipoVersamento ().getDescrizione () );
			}
			dto.setIdFlussoRendicontazionePsp ( transazionePspEntity.getIdFlussoRendicontazione () );
			if ( transazionePspEntity.getDtAvvioTransazione () != null ) {
				dto.setDataAvvioTransazione ( new Date ( transazionePspEntity.getDtAvvioTransazione ().getTime () ) );
			}
			dto.setIUR ( transazionePspEntity.getIur () );
			if ( transazionePspEntity.getDtAutorizzazione () != null ) {
				dto.setDataOraAutorizzazione ( new Date ( transazionePspEntity.getDtAutorizzazione ().getTime () ) );
			}
			dto.setTipoSicurezza ( transazionePspEntity.getTipoSicurezza () );
			dto.setImportoTransato ( transazionePspEntity.getImportoTransato () );
			dto.setImportoCommissioni ( transazionePspEntity.getImportoCommissioni () );
		}

		return dto;
	}

	protected SingolaRevocaDto toSingolaRevocaDto(EpaypaTDatiSingolaRevoca epaypaTDatiSingolaRevocaEntity) {
		SingolaRevocaDto dto = null;
		if (epaypaTDatiSingolaRevocaEntity !=null) {
			dto = new SingolaRevocaDto ();
			dto.setCasualeRevoca ( epaypaTDatiSingolaRevocaEntity.getEpaypaTDatiSingolaRevocaId ().getCasualeRevoca ());
			dto.setDatiAggiuntiRevoca ( epaypaTDatiSingolaRevocaEntity.getEpaypaTDatiSingolaRevocaId ().getDatiAggiuntiRevoca ());
			dto.setIdRr ( epaypaTDatiSingolaRevocaEntity.getEpaypaTDatiSingolaRevocaId ().getIdRr() );
			dto.setIur ( epaypaTDatiSingolaRevocaEntity.getEpaypaTDatiSingolaRevocaId ().getIur() );
			dto.setSingoloImportoRevocato ( epaypaTDatiSingolaRevocaEntity.getEpaypaTDatiSingolaRevocaId ().getSingoloImportoRevocato ());
		}
		return dto;
	}

	protected List<SingolaRevocaDto> toSingolaRevocaDtoList ( List<EpaypaTDatiSingolaRevoca> entityList ) {
		List<SingolaRevocaDto> dtoList = null;

		if ( entityList != null ) {
			dtoList = new ArrayList<> ();
			for ( EpaypaTDatiSingolaRevoca entity: entityList ) {
				dtoList.add ( toSingolaRevocaDto ( entity ) );
			}
		}

		return dtoList;
	}

	protected static ComponenteImportoDto toComponenteImportoDto ( EpaypaTComponenteImporto entity ) {
		ComponenteImportoDto dto = null;

		if ( entity != null ) {
			dto = new ComponenteImportoDto ( entity.getIdComponente () );
			dto.setImporto ( entity.getImporto () );
			dto.setCausale ( entity.getCausale () );
			dto.setDatiSpecificiRiscossione ( entity.getDatiSpecRiscossione () );
			dto.setAnnoAccertamento ( entity.getAnnoAccertamento () );
			dto.setNumeroAccertamento ( entity.getNumeroAccertamento () );
			dto.setFlagComponenteSecondario ( entity.getFlagComponenteSecondario () );
		}

		return dto;
	}

	protected static RiferimentoPagamentoDto toRiferimentoPagamentoDto ( EpaypaTRiferimentoPagamento entity ) {
		RiferimentoPagamentoDto dto = null;

		if ( entity != null ) {
			dto = new RiferimentoPagamentoDto ( entity.getIdRiferimento () );
			dto.setNome ( entity.getNome () );
			dto.setValore ( entity.getValore () );
		}

		return dto;
	}

	protected static List<ComponenteImportoDto> toComponenteImportoDtoList ( Set<EpaypaTComponenteImporto> entityList ) {
		List<ComponenteImportoDto> dtoList = null;

		if ( entityList != null ) {
			dtoList = new ArrayList<> ();
			for ( EpaypaTComponenteImporto entity: entityList ) {
				dtoList.add ( toComponenteImportoDto ( entity ) );
			}
		}

		return dtoList;
	}

	protected static List<RiferimentoPagamentoDto> toRiferimentoPagamentoDtoList ( Set<EpaypaTRiferimentoPagamento> entityList ) {
		List<RiferimentoPagamentoDto> dtoList = null;

		if ( entityList != null ) {
			dtoList = new ArrayList<> ();
			for ( EpaypaTRiferimentoPagamento entity: entityList ) {
				dtoList.add ( toRiferimentoPagamentoDto ( entity ) );
			}

			// ordino nell'ordine di inserimento
			Collections.sort ( dtoList, new Comparator<RiferimentoPagamentoDto> () {

				@Override
				public int compare ( RiferimentoPagamentoDto r1, RiferimentoPagamentoDto r2 ) {
					return r1.getId ().compareTo ( r2.getId () );
				}
			} );
		}

		return dtoList;
	}

	protected PosizioneDebitoriaLightDto toPosizioneDebitoriaLightDto ( EpaypaTPosizioneDebitoriaLight entity ) {
		PosizioneDebitoriaLightDto dto = null;

		if ( entity != null ) {
			dto = new PosizioneDebitoriaLightDto ( entity.getIdPosizioneDebitoria () );
			dto.setIUV ( entity.getIuv () );
			dto.setImportoTotale ( entity.getImportoTotale () );
			dto.setDesCausaleVersamento ( entity.getDesCausaleVersamento () );
			if ( entity.getDtScadenza () != null ) {
				dto.setDataScadenza ( new Date ( entity.getDtScadenza ().getTime () ) );
			}
			dto.setSoggettoDebitore ( toSoggettoAnagraficoDto ( entity.getEpaypaTSoggettoDebitore () ) );
		}

		return dto;
	}

	protected List<PosizioneDebitoriaLightDto> toPosizioneDebitoriaLightDtoList ( List<EpaypaTPosizioneDebitoriaLight> entityList ) {
		List<PosizioneDebitoriaLightDto> dtoList = null;

		if ( entityList != null ) {
			dtoList = new ArrayList<> ();
			for ( EpaypaTPosizioneDebitoriaLight entity: entityList ) {
				dtoList.add ( toPosizioneDebitoriaLightDto ( entity ) );
			}
		}

		return dtoList;
	}

	public static DettaglioPagamentiDto toDettaglioPagamentiDto ( EpaypaTPagamenti entity,  List<EpaypaTAggPosizioneDebitoria> posDebitorie ) {
		if ( entity == null ) {
			return null;
		}
		DettaglioPagamentiDto pagamentiDto = new DettaglioPagamentiDto ();

		EpaypaTPosizioneDebitoriaMedium entityPD = entity.getPosizioneDebitoria ();

		EpaypaTNotificaPagamentoMedium entityNP = entity.getNotificaPagamento();

		EpaypaTSoggetto entitySoggetto = null;

		// e' un pagamento dovuto
		if ( entityPD != null ) {
			entitySoggetto = getSoggettoDebitoreRecente ( entityPD.getEpaypaTSoggettoDebitore (), posDebitorie );
		}
		// e' un pagamento spontaneo
		else if (entityNP !=null) {
			entitySoggetto = entityNP.getEpaypaTSoggettoDebitore ();
		}

		//RDI-23
		pagamentiDto.setCausale ( entity.getCausale () );

		DettaglioPosizioneDebitoriaDto dettaglioPosizione = pagamentiDto.getDettaglioPosizioneDebitoria ();
		dettaglioPosizione.setIuv ( entity.getIuv () );
		dettaglioPosizione
		.setImportoDovuto ( getImportoDovutoRecente ( entityPD != null ? entityPD.getImportoTotale () : entity.getImportoPagato (), posDebitorie ) );
		dettaglioPosizione.setImportoPagato ( entity.getImportoPagato () );
		dettaglioPosizione.setDescrizioneCausaleVersamento ( entityPD != null ? entityPD.getDesCausaleVersamento () : entity.getDescrCodVersamento () );

		Date dataScadenza = null;
		if ( entity.getDataScadenza () != null ) {
			dataScadenza = entity.getDataScadenza ();
		} else {
			if ( entityPD != null && entityPD.getDtFineValidita () != null ) {
				dataScadenza = getDataFineScadenzaRecente ( new java.util.Date ( entityPD.getDtFineValidita ().getTime () ), posDebitorie );
			} else if ( entityNP != null && entityNP.getDtScadenza () != null ) {
				dataScadenza = new java.util.Date ( entityNP.getDtScadenza ().getTime () );
			}
		}
		dettaglioPosizione .setDataScadenza(dataScadenza);

		dettaglioPosizione.setDataPagamento ( entity.getDataPagamento () );
		dettaglioPosizione.setDescrizioneStatoPagamento ( entity.getDescStato () );
		dettaglioPosizione.setIdStatoPagamento ( entity.getIdStato () );
		dettaglioPosizione.setPagamentoSpontaneo ( entity.getPagamentoSpontaneo () );
		dettaglioPosizione.setDescrizionePagamentoSpontaneo ( entity.getPagamentoSpontaneo () != null && entity.getPagamentoSpontaneo () ? "SI" : "NO" );
		dettaglioPosizione.setIdentificativoUnivocoDebitore ( entitySoggetto != null ? entitySoggetto.getIdUnivocoFiscale () : null );
		dettaglioPosizione.setPosizioneDebitoriaEsterna ( entityPD != null ? entityPD.getIdPosizioneDebitoriaEst () : null );
		dettaglioPosizione.setDescrizioneRata ( entityPD != null ? entityPD.getDesRata () : null );

		dettaglioPosizione.setAnnoRiferimento (
			entityPD != null ? entityPD.getAnnoRiferimento () : ( entity.getDataPagamento () != null ) ? entity.getDataPagamento ().toInstant ()
							.atZone ( ZoneId.systemDefault () )
							.toLocalDate ().getYear () : null );
		dettaglioPosizione.setNotePagatore ( entityPD != null ? entityPD.getNotePerPagatore () : entity.getNote() );
		dettaglioPosizione.setCostiDiNotifica(entity.getRequiresCostUpdate());

		if ( entitySoggetto != null ) {
			DettaglioSoggettoDebitoreDto dettaglioSoggetto = pagamentiDto.getDettaglioSoggettoDebitore ();

			dettaglioSoggetto.setCognome ( entitySoggetto.getCognomeRagSoc () );
			dettaglioSoggetto.setNome ( entitySoggetto.getNome () );
			dettaglioSoggetto.setIdentificativoUnivoco ( entitySoggetto.getIdUnivocoFiscale () );
			dettaglioSoggetto.setTipoSoggetto ( entitySoggetto.getTipologiaSoggetto () );
			dettaglioSoggetto.setIndirizzo ( entitySoggetto.getIndirizzo () );
			dettaglioSoggetto.setCivico ( entitySoggetto.getCivico () );
			dettaglioSoggetto.setCap ( entitySoggetto.getCap () );
			dettaglioSoggetto.setLocalita ( entitySoggetto.getLocalita () );
			dettaglioSoggetto.setProvincia ( entitySoggetto.getProvincia () );
			dettaglioSoggetto.setNazione ( entitySoggetto.getNazione () );
			dettaglioSoggetto.setEmail ( entitySoggetto.getEmail () );
		}
		else
		{
			DettaglioSoggettoDebitoreDto dettaglioSoggetto = pagamentiDto.getDettaglioSoggettoDebitore ();
			dettaglioSoggetto.setCognome(entity.getCognomeRagioneSociale ());
			dettaglioSoggetto.setNome(entity.getNome ());
			dettaglioSoggetto.setIdentificativoUnivoco ( entity.getIdUnicoFiscale () );
			dettaglioSoggetto.setTipoSoggetto ( entity.getFlagPersonaFisica()?TipologiaSoggettoEnum.PERSONA_FISICA.getId():TipologiaSoggettoEnum.PERSONA_GIURIDICA.getId());
			dettaglioPosizione.setIdentificativoUnivocoDebitore ( entity.getIdUnicoFiscale () );
		}

		if (entityPD!=null) {
			Set<EpaypaTComponenteImporto> componentiImporto = getComponentiRecente ( entityPD, posDebitorie );
			if ( componentiImporto != null && !componentiImporto.isEmpty () ) {
				for ( EpaypaTComponenteImporto entityComponenteImporto: componentiImporto ) {

					DettaglioComponenteImportoDto dettaglioComponenteDto = new DettaglioComponenteImportoDto ();
					dettaglioComponenteDto.setId ( entityComponenteImporto.getIdComponente () );
					dettaglioComponenteDto.setImporto ( entityComponenteImporto.getImporto () );
					dettaglioComponenteDto.setCausale ( entityComponenteImporto.getCausale () );
					dettaglioComponenteDto.setDatiSpecificiRiscossione ( entityComponenteImporto.getDatiSpecRiscossione () );
					dettaglioComponenteDto.setAnnoAccertamento ( entityComponenteImporto.getAnnoAccertamento () );
					dettaglioComponenteDto.setNumeroAccertamento ( entityComponenteImporto.getNumeroAccertamento () );
					pagamentiDto.getDettaglioComponentiImporto ().add ( dettaglioComponenteDto );
				}
			}
		}

		return pagamentiDto;
	}

	private static Date getDataFineScadenzaRecente ( Date dataPagamento, List<EpaypaTAggPosizioneDebitoria> epaypaTAggPosizioneDebitoria ) {
		if ( !CollectionUtils.isEmpty ( epaypaTAggPosizioneDebitoria ) ) {
			Optional<EpaypaTAggPosizioneDebitoria> trovato = epaypaTAggPosizioneDebitoria.stream ().filter ( new Predicate<EpaypaTAggPosizioneDebitoria> () {

				@Override
				public boolean test ( EpaypaTAggPosizioneDebitoria t ) {
					return null != t.getDtFineValidita ();
				}
			} ).findFirst ();
			if ( trovato.isPresent () ) {
				Calendar scadenza = Calendar.getInstance ();
				scadenza.setTimeInMillis ( trovato.get ().getDtFineValidita ().getTime () );
				return scadenza.getTime ();
			}
		}
		return dataPagamento;
	}

	private static Date getDtInizioValiditaRecente ( Date dt, List<EpaypaTAggPosizioneDebitoria> epaypaTAggPosizioneDebitoria ) {
		if ( !CollectionUtils.isEmpty ( epaypaTAggPosizioneDebitoria ) ) {
			Optional<EpaypaTAggPosizioneDebitoria> trovato = epaypaTAggPosizioneDebitoria.stream ().filter ( new Predicate<EpaypaTAggPosizioneDebitoria> () {

				@Override
				public boolean test ( EpaypaTAggPosizioneDebitoria t ) {
					return null != t.getDtInizioValidita ();
				}
			} ).findFirst ();
			if ( trovato.isPresent () ) {
				Calendar scadenza = Calendar.getInstance ();
				scadenza.setTimeInMillis ( trovato.get ().getDtInizioValidita ().getTime () );
				return scadenza.getTime ();
			}
		}
		return dt;
	}

	private static Date getDataFineValiditaRecente ( Date dt, List<EpaypaTAggPosizioneDebitoria> epaypaTAggPosizioneDebitoria ) {
		if ( !CollectionUtils.isEmpty ( epaypaTAggPosizioneDebitoria ) ) {
			Optional<EpaypaTAggPosizioneDebitoria> trovato = epaypaTAggPosizioneDebitoria.stream ().filter ( new Predicate<EpaypaTAggPosizioneDebitoria> () {

				@Override
				public boolean test ( EpaypaTAggPosizioneDebitoria t ) {
					return null != t.getDtFineValidita ();
				}
			} ).findFirst ();
			if ( trovato.isPresent () ) {
				Calendar scadenza = Calendar.getInstance ();
				scadenza.setTimeInMillis ( trovato.get ().getDtFineValidita ().getTime () );
				return scadenza.getTime ();
			}
		}
		return dt;
	}

	private static String getDescrizioneCausaleVersamentoRecente ( String desCausaleVersamento,
		List<EpaypaTAggPosizioneDebitoria> epaypaTAggPosizioneDebitoria ) {
		if ( !CollectionUtils.isEmpty ( epaypaTAggPosizioneDebitoria ) ) {
			Optional<EpaypaTAggPosizioneDebitoria> trovato = epaypaTAggPosizioneDebitoria.stream ().filter ( new Predicate<EpaypaTAggPosizioneDebitoria> () {

				@Override
				public boolean test ( EpaypaTAggPosizioneDebitoria t ) {
					return null != t.getDesCausaleVersamento ();
				}
			} ).findFirst ();
			if ( trovato.isPresent () ) {
				return trovato.get ().getDesCausaleVersamento ();
			}
		}
		return desCausaleVersamento;
	}

	private static BigDecimal getImportoDovutoRecente ( BigDecimal importoTotale, List<EpaypaTAggPosizioneDebitoria> epaypaTAggPosizioneDebitoria ) {
		if ( !CollectionUtils.isEmpty ( epaypaTAggPosizioneDebitoria ) ) {
			Optional<EpaypaTAggPosizioneDebitoria> trovato = epaypaTAggPosizioneDebitoria.stream ().filter ( new Predicate<EpaypaTAggPosizioneDebitoria> () {

				@Override
				public boolean test ( EpaypaTAggPosizioneDebitoria t ) {
					return null != t.getImportoTotale ();
				}
			} ).findFirst ();
			if ( trovato.isPresent () ) {
				return trovato.get ().getImportoTotale ();
			}
		}
		return importoTotale;
	}

	private static EpaypaTSoggetto getSoggettoDebitoreRecente ( EpaypaTSoggetto soggetto, List<EpaypaTAggPosizioneDebitoria> epaypaTAggPosizioneDebitoria ) {
		if ( !CollectionUtils.isEmpty ( epaypaTAggPosizioneDebitoria ) ) {
			Optional<EpaypaTAggPosizioneDebitoria> trovato = epaypaTAggPosizioneDebitoria.stream ().filter ( new Predicate<EpaypaTAggPosizioneDebitoria> () {

				@Override
				public boolean test ( EpaypaTAggPosizioneDebitoria t ) {
					return null != t.getEpaypaTSoggetto ();
				}
			} ).findFirst ();
			if ( trovato.isPresent () ) {
				return trovato.get ().getEpaypaTSoggetto ();
			}
		}
		return soggetto;
	}

	private static Set<EpaypaTComponenteImporto> getComponentiRecente ( EpaypaTPosizioneDebitoriaMedium posizioneDebitoria,
		List<EpaypaTAggPosizioneDebitoria> epaypaTAggPosizioneDebitoria ) {
		if ( !CollectionUtils.isEmpty ( epaypaTAggPosizioneDebitoria ) ) {
			Optional<EpaypaTAggPosizioneDebitoria> trovato = epaypaTAggPosizioneDebitoria.stream ().filter ( new Predicate<EpaypaTAggPosizioneDebitoria> () {

				@Override
				public boolean test ( EpaypaTAggPosizioneDebitoria t ) {
					return null != t.getEpaypaTComponenteImportos ();
				}
			} ).findFirst ();
			if ( trovato.isPresent () ) {
				return trovato.get ().getEpaypaTComponenteImportos ();
			}
		}
		return posizioneDebitoria.getEpaypaTComponenteImportos ();
	}

	private static Set<EpaypaTRiferimentoPagamento> getRiferimentoPagamentoRecente ( EpaypaTPosizioneDebitoriaMedium posizioneDebitoria,
		List<EpaypaTAggPosizioneDebitoria> epaypaTAggPosizioneDebitoria ) {
		if ( !CollectionUtils.isEmpty ( epaypaTAggPosizioneDebitoria ) ) {
			Optional<EpaypaTAggPosizioneDebitoria> trovato = epaypaTAggPosizioneDebitoria.stream ().filter ( new Predicate<EpaypaTAggPosizioneDebitoria> () {

				@Override
				public boolean test ( EpaypaTAggPosizioneDebitoria t ) {
					return null != t.getEpaypaTRiferimentoPagamentos ();
				}
			} ).findFirst ();
			if ( trovato.isPresent () ) {
				return trovato.get ().getEpaypaTRiferimentoPagamentos ();
			}
		}
		return posizioneDebitoria.getEpaypaTRiferimentoPagamentos ();
	}

	public static PagamentiDto toPagamentiDto ( EpaypaTPagamenti pagamento, List<EpaypaTAggPosizioneDebitoria> epaypaTAggPosizioneDebitoria ) {
		if ( pagamento == null ) {
			return null;
		}

		PagamentiDto pagDto = new PagamentiDto ();

		//RDI-23
		pagDto.setCausale ( pagamento.getCausale () );

		pagDto.setCodiceCodiceVersamento ( pagamento.getPosizioneDebitoria () != null && pagamento.getPosizioneDebitoria ().getEpaypaTFlusso () != null
						&& pagamento.getPosizioneDebitoria ().getEpaypaTFlusso ().getEpaypaTCodiceVersamento () != null
						? pagamento.getPosizioneDebitoria ().getEpaypaTFlusso ().getEpaypaTCodiceVersamento ().getCodVersamento () : pagamento.getCodVersamento () );

		pagDto.setDataPagamento ( pagamento.getDataPagamento () );
		pagDto.setDataScadenza ( getDataFineScadenzaRecente ( pagamento.getDataScadenza (), epaypaTAggPosizioneDebitoria ) );
		pagDto.setDescrizioneCausaleVersamento ( getDescrizioneCausaleVersamentoRecente (
			null != pagamento.getPosizioneDebitoria () ? pagamento.getPosizioneDebitoria ().getDesCausaleVersamento () : null, epaypaTAggPosizioneDebitoria ) );
		pagDto.setImportoDovuto ( getImportoDovutoRecente (
			null != pagamento.getPosizioneDebitoria () ? pagamento.getPosizioneDebitoria ().getImportoTotale () : null, epaypaTAggPosizioneDebitoria ) );


		pagDto.setDescrizioneCodiceVersamento (
			pagamento.getPosizioneDebitoria () != null && pagamento.getPosizioneDebitoria ().getEpaypaTFlusso () != null
			&& pagamento.getPosizioneDebitoria ().getEpaypaTFlusso ().getEpaypaTCodiceVersamento () != null
			? pagamento.getPosizioneDebitoria ().getEpaypaTFlusso ().getEpaypaTCodiceVersamento ().getDescrizione () : pagamento.getDescrCodVersamento () );


		pagDto.setDescrizioneStatoPagamento ( pagamento.getDescStato () );
		pagDto.setIdStatoPagamento ( pagamento.getIdStato () );

		pagDto.setImportoPagato ( pagamento.getImportoPagato () );
		pagDto.setIuv ( pagamento.getIuv () );
		pagDto.setIdentificativoUnivocoDebitore (
			pagamento.getPosizioneDebitoria () != null && pagamento.getPosizioneDebitoria ().getEpaypaTSoggettoDebitore () != null
			? pagamento.getPosizioneDebitoria ().getEpaypaTSoggettoDebitore ().getIdUnivocoFiscale () : null );

		if ( pagamento.getPagamentoSpontaneo () ) {
			if ( pagamento.getNotificaPagamento () != null && pagamento.getNotificaPagamento ().getEpaypaTFlusso () != null ) {
				EpaypaTNotificaPagamentoMedium notifica = pagamento.getNotificaPagamento ();
				EpaypaTFlusso flusso = pagamento.getNotificaPagamento ().getEpaypaTFlusso ();
				pagDto.setImportoDovuto ( null );
				pagDto.setImportoPagato ( notifica.getImportoPagato () );
				pagDto.setCodiceCodiceVersamento ( flusso.getEpaypaTCodiceVersamento ().getCodVersamento () );
				pagDto.setDescrizioneCodiceVersamento ( flusso.getEpaypaTCodiceVersamento ().getDescrizione () );
				pagDto.setDescrizioneCausaleVersamento ( notifica.getDesCausaleVersamento () );
				pagDto.setIuv ( notifica.getIuv () );
				pagDto.setDataScadenza (
					pagamento.getNotificaPagamento () != null && pagamento.getNotificaPagamento ().getDtScadenza () != null
					? new java.util.Date ( pagamento.getNotificaPagamento ().getDtScadenza ().getTime () ) : null );
			}
		}
		return pagDto;
	}

	// Export per estrazione xls
	/**
	 *
	 * @param pagamento EpaypaTPagamenti
	 * @param entity EpaypaTPosizioneDebitoriaMedium
	 * @return PagamentiExportDto
	 */
	public static PagamentiExportDto toPagamentiExportDto ( EpaypaTPagamenti pagamento, EpaypaTPosizioneDebitoriaMedium entity,
		List<EpaypaTAggPosizioneDebitoria> posDebitorie ) {
		if ( pagamento == null ) {
			return null;
		}

		PagamentiExportDto dto = new PagamentiExportDto ( entity != null ? entity.getIdPosizioneDebitoria () : null );

		if ( entity != null ) { // posizione debitoria
			if ( entity.getEpaypaTFlusso () != null ) {
				dto.setIdFlusso ( entity.getEpaypaTFlusso ().getIdFlusso () );
			}
			dto.setIdPosizioneDebitoriaEsterna ( entity.getIdPosizioneDebitoriaEst () );
			dto.setAnnoRiferimento ( entity.getAnnoRiferimento () );
			dto.setImportoTotale ( getImportoDovutoRecente ( entity.getImportoTotale (), posDebitorie ) );
			dto.setDataScadenza ( getDataFineScadenzaRecente ( entity.getDtFineValidita () != null ? new Date ( entity.getDtFineValidita ().getTime () ) : null, posDebitorie ) );
			dto.setDataInizioValidita ( getDtInizioValiditaRecente ( entity.getDtInizioValidita () != null ? new Date ( entity.getDtInizioValidita ().getTime () ) : null, posDebitorie ) );
			dto.setDataFineValidita ( getDataFineValiditaRecente ( entity.getDtFineValidita () != null ? new Date ( entity.getDtFineValidita ().getTime () ) : null, posDebitorie ) );
			dto.setDesCausaleVersamento ( getDescrizioneCausaleVersamentoRecente ( entity.getDesCausaleVersamento (), posDebitorie ) );
			dto.setDesRata ( entity.getDesRata () );
			dto.setNotePerPagatore ( entity.getNotePerPagatore () );
			dto.setIUV ( entity.getIuv () );
			dto.setCodAvviso ( entity.getCodiceAvviso () );
			dto.setCodEsito ( entity.getCodEsito () );
			dto.setDetEsito ( entity.getDettaglioEsito () );
			// soggetto debitore
			dto.setSoggettoDebitore ( toSoggettoAnagraficoDto ( getSoggettoDebitoreRecente ( entity.getEpaypaTSoggettoDebitore (), posDebitorie ) ) );
			// componenti importo
			dto.setComponenteImportoDtoList ( toComponenteImportoDtoList ( getComponentiRecente ( entity, posDebitorie ) ) );
			// riferimenti pagamento
			dto.setRiferimentoPagamentoDtoList ( toRiferimentoPagamentoDtoList ( getRiferimentoPagamentoRecente ( entity, posDebitorie ) ) );
			dto.setCodiceVersamento ( StringUtils.isNotBlank ( entity.getEpaypaTFlusso ().getEpaypaTCodiceVersamento ().getCodVersamento () )
				? entity.getEpaypaTFlusso ().getEpaypaTCodiceVersamento ().getCodVersamento () : pagamento.getCodVersamento () );
		}

		//RDI-23
		dto.setCausale ( pagamento.getCausale () );
		dto.setDataPagamento ( pagamento.getDataPagamento () );
		dto.setImportoPagato ( pagamento.getImportoPagato () );
		if (  entity == null ) {
			dto.setImportoTotale ( pagamento.getImportoPagato () );
		}
		dto.setStatoPagamento ( pagamento.getDescStato () );
		dto.setIUV ( pagamento.getIuv () );
		dto.setCodiceVersamento ( pagamento.getCodVersamento () );
		dto.setNotePerPagatore(pagamento.getNote());

		if ( Boolean.TRUE.equals ( pagamento.getPagamentoSpontaneo () ) && pagamento.getNotificaPagamento () != null && pagamento.getNotificaPagamento ().getEpaypaTFlusso () != null ) {
			EpaypaTNotificaPagamentoMedium notifica = pagamento.getNotificaPagamento ();
			EpaypaTFlusso flusso = pagamento.getNotificaPagamento ().getEpaypaTFlusso ();
			dto.setImportoTotale ( flusso.getImportoTotale () );
			dto.setDesCausaleVersamento ( notifica.getDesCausaleVersamento () );
			dto.setIUV ( notifica.getIuv () );
			dto.setSoggettoDebitore ( toSoggettoAnagraficoDto ( notifica.getEpaypaTSoggettoDebitore () ) );
			dto.setCodiceVersamento ( StringUtils.isNotBlank ( notifica.getEpaypaTFlusso ().getEpaypaTCodiceVersamento ().getCodVersamento () )
				? notifica.getEpaypaTFlusso ().getEpaypaTCodiceVersamento ().getCodVersamento () : pagamento.getCodVersamento () );
		}

		if ( null == dto.getSoggettoDebitore () ) {
			// e' successo qualcosa di male per cui ci siamo bruciati la posizione debitoria, recuperiamo il tutto dal pagamento
			SoggettoAnagraficoDto soggettoDebitore = new SoggettoAnagraficoDto ();
			soggettoDebitore.setCognome ( pagamento.getCognomeRagioneSociale () );
			soggettoDebitore.setNome ( pagamento.getNome () );
			soggettoDebitore.setIdUnivocoFiscale ( pagamento.getIdUnicoFiscale () );
			soggettoDebitore.setTipologiaSoggettoEnum ( pagamento.getFlagPersonaFisica () ? TipologiaSoggettoEnum.PERSONA_FISICA : TipologiaSoggettoEnum.PERSONA_GIURIDICA );
			dto.setSoggettoDebitore ( soggettoDebitore );
		}
		
		dto.setCostiDiNotifica((pagamento.getRequiresCostUpdate() == null || Boolean.FALSE.equals(pagamento.getRequiresCostUpdate())) ? "Non presenti" : "Presenti");

		return dto;
	}

	public static PosizioneDebitoriaDto toPosizioneDebitoriaDto ( EpaypaTPosizioneDebitoria entity ) {

		PosizioneDebitoriaDto dto = null;
		if ( entity != null ) {
			// posizione debitoria
			dto = new PosizioneDebitoriaDto ( entity.getIdPosizioneDebitoria () );
			//
			if ( entity.getEpaypaTFlusso () != null ) {
				dto.setIdFlusso ( entity.getEpaypaTFlusso ().getIdFlusso () );
			}
			dto.setIdPosizioneDebitoriaEsterna ( entity.getIdPosizioneDebitoriaEst () );
			dto.setAnnoRiferimento ( entity.getAnnoRiferimento () );
			dto.setImportoTotale ( entity.getImportoTotale () );
			if ( entity.getDtScadenza () != null ) {
				dto.setDataScadenza ( new Date ( entity.getDtScadenza ().getTime () ) );
			}
			if ( entity.getDtInizioValidita () != null ) {
				dto.setDataInizioValidita ( new Date ( entity.getDtInizioValidita ().getTime () ) );
			}
			if ( entity.getDtFineValidita () != null ) {
				dto.setDataFineValidita ( new Date ( entity.getDtFineValidita ().getTime () ) );
			}
			dto.setDesCausaleVersamento ( entity.getDesCausaleVersamento () );
			dto.setDesRata ( entity.getDesRata () );
			dto.setNotePerPagatore ( entity.getNotePerPagatore () );
			dto.setIUV ( entity.getIuv () );
			dto.setCodAvviso ( entity.getCodiceAvviso () );
			dto.setCodEsito ( entity.getCodEsito () );
			dto.setDetEsito ( entity.getDettaglioEsito () );
			//
			// soggetto debitore
			dto.setSoggettoDebitore ( toSoggettoAnagraficoDto ( entity.getEpaypaTSoggettoDebitore () ) );
			//
			// componenti importo
			dto.setComponenteImportoDtoList ( toComponenteImportoDtoList ( entity.getEpaypaTComponenteImportos () ) );

			// riferimenti pagamento
			dto.setRiferimentoPagamentoDtoList ( toRiferimentoPagamentoDtoList ( entity.getEpaypaTRiferimentoPagamentos () ) );

			dto.setImportoPrincipale ( entity.getImportoPrincipale () );
			dto.setImportoSecondarioAltroEnte ( entity.getImportoSecondarioAltroEnte () );

		}

		return dto;
	}

	protected PosizioneDebitoriaDaAggiornareLightDto toPosizioneDebitoriaDaAggiornareLightDto ( EpaypaTAggPosizioneDebitoriaLight entity ) {
		PosizioneDebitoriaDaAggiornareLightDto dto = null;

		if ( entity != null ) {
			dto = new PosizioneDebitoriaDaAggiornareLightDto ( entity.getIdAggPosizioneDebitoria () );
			//
			dto.setTipoAggiornamento ( toTipoAggiornamentoPosizioneDebitoriaDto ( entity.getEpaypaDTipoAggPosizioneDebitoria () ) );
			dto.setIdPosizioneDebitoriaEsterna ( entity.getIdPosizioneDebitoriaEst () );
			dto.setMotivazione ( entity.getMotivazione () );
			dto.setCodAvviso ( entity.getCodiceAvviso () );
			dto.setCodEsito ( entity.getCodEsito () );
			dto.setDetEsito ( entity.getDettaglioEsito () );
		}

		return dto;
	}

	protected List<PosizioneDebitoriaDaAggiornareLightDto> toPosizioneDebitoriaDaAggiornareLightDtoList ( List<EpaypaTAggPosizioneDebitoriaLight> entityList ) {
		List<PosizioneDebitoriaDaAggiornareLightDto> dtoList = null;

		if ( entityList != null ) {
			dtoList = new ArrayList<> ();
			for ( EpaypaTAggPosizioneDebitoriaLight entity: entityList ) {
				dtoList.add ( toPosizioneDebitoriaDaAggiornareLightDto ( entity ) );
			}
		}

		return dtoList;
	}

	protected PosizioneDebitoriaDaAggiornareDto toPosizioneDebitoriaDaAggiornareDto ( EpaypaTAggPosizioneDebitoria entity ) {
		PosizioneDebitoriaDaAggiornareDto dto = null;

		if ( entity != null ) {
			dto = new PosizioneDebitoriaDaAggiornareDto ( entity.getIdAggPosizioneDebitoria () );
			//
			if ( entity.getEpaypaTFlusso () != null ) {
				dto.setIdFlusso ( entity.getEpaypaTFlusso ().getIdFlusso () );
			}
			//
			dto.setTipoAggiornamento ( toTipoAggiornamentoPosizioneDebitoriaDto ( entity.getEpaypaDTipoAggPosizioneDebitoria () ) );
			dto.setIdPosizioneDebitoriaEsterna ( entity.getIdPosizioneDebitoriaEst () );
			dto.setMotivazione ( entity.getMotivazione () );
			dto.setCodAvviso ( entity.getCodiceAvviso () );
			dto.setCodEsito ( entity.getCodEsito () );
			dto.setDetEsito ( entity.getDettaglioEsito () );
			//
			if ( entity.getDtScadenza () != null ) {
				dto.setDataScadenza ( new Date ( entity.getDtScadenza ().getTime () ) );
			}
			if ( entity.getDtInizioValidita () != null ) {
				dto.setDataInizioValidita ( new Date ( entity.getDtInizioValidita ().getTime () ) );
			}
			if ( entity.getDtFineValidita () != null ) {
				dto.setDataFineValidita ( new Date ( entity.getDtFineValidita ().getTime () ) );
			}
			dto.setImportoTotale ( entity.getImportoTotale () );
			dto.setDesCausaleVersamento ( entity.getDesCausaleVersamento () );
			//
			// componenti importo
			dto.setComponenteImportoDtoList ( toComponenteImportoDtoList ( entity.getEpaypaTComponenteImportos () ) );
			// riferimento pagamento
			dto.setRiferimentoPagamentoDtoList ( toRiferimentoPagamentoDtoList ( entity.getEpaypaTRiferimentoPagamentos () ) );

			// soggetto pagatore
			dto.setSoggettoAnagraficoDto(toSoggettoAnagraficoDto(entity.getEpaypaTSoggetto()));

			// campi multibeneficiario
			dto.setImportoPrincipale ( entity.getImportoPrincipale () );
			dto.setImportoSecondarioAltroEnte ( entity.getImportoSecondarioAltroEnte () );
		}

		return dto;
	}

	protected AvvisoScadutoDto toAvvisoScadutoDto ( EpaypaTAvvisoScaduto entity ) {
		AvvisoScadutoDto dto = null;

		if ( entity != null ) {
			dto = new AvvisoScadutoDto ( entity.getIdAvvisoScaduto () );
			//
			if ( entity.getEpaypaTFlusso () != null ) {
				dto.setIdFlusso ( entity.getEpaypaTFlusso ().getIdFlusso () );
			}
			dto.setIUV ( entity.getIuv () );
			dto.setImporto ( entity.getImporto () );
			dto.setDataScadenza ( entity.getDtScadenza () );
		}

		return dto;
	}

	protected List<AvvisoScadutoDto> toAvvisoScadutoDtoList ( List<EpaypaTAvvisoScaduto> entityList ) {
		List<AvvisoScadutoDto> dtoList = null;

		if ( entityList != null ) {
			dtoList = new ArrayList<> ();
			for ( EpaypaTAvvisoScaduto entity: entityList ) {
				dtoList.add ( toAvvisoScadutoDto ( entity ) );
			}
		}

		return dtoList;
	}

	protected RiversamentoDto toRiversamentoDto ( EpaypaTRiversamento entity ) {
		RiversamentoDto dto = null;

		if ( entity != null ) {
			dto = new RiversamentoDto ( entity.getIdRiversamento () );
			//
			if ( entity.getEpaypaTRendicontazione () != null ) {
				dto.setIdFlusso ( entity.getEpaypaTRendicontazione ().getIdFlusso () );
			}
			dto.setIUV ( entity.getIuv () );
			dto.setIUR ( entity.getIur () );
			dto.setIndicePagamento ( entity.getIndiceDatiRt () );
			dto.setImportoPagato ( entity.getImportoPagato () );
			dto.setEsito ( EsitoRiversamentoEnum.fromId ( entity.getCodEsito () ) );
			dto.setDataEsito ( entity.getDtEsito () );
		}

		return dto;
	}

	protected List<RiversamentoDto> toRiversamentoDtoList ( List<EpaypaTRiversamento> entityList ) {
		List<RiversamentoDto> dtoList = null;

		if ( entityList != null ) {
			dtoList = new ArrayList<> ();
			for ( EpaypaTRiversamento entity: entityList ) {
				dtoList.add ( toRiversamentoDto ( entity ) );
			}
		}

		return dtoList;
	}

	private ColonnaTemplateDto toColonnaTemplateDto ( EpaypaTColonnaTemplate entity ) {
		ColonnaTemplateDto colonnaTemplate = null;

		if ( entity != null ) {
			colonnaTemplate = new ColonnaTemplateDto ();
			colonnaTemplate.setIntestazione ( entity.getIntestazione () );
			colonnaTemplate.setPosizioneOrdinale ( entity.getPosizioneOrdinale () );
			colonnaTemplate.setFormatoCSV ( entity.getEpaypaTCampoFlusso ().getFormatoCsv () );
			colonnaTemplate.setFormatoXLSX ( entity.getEpaypaTCampoFlusso ().getFormatoXlsx () );
			colonnaTemplate.setCampoFlusso ( CampoFlussoEnum.valueOf ( entity.getEpaypaTCampoFlusso ().getNome () ) );
		}

		return colonnaTemplate;
	}

	private List<ColonnaTemplateDto> toColonnaTemplateDtoList ( List<EpaypaTColonnaTemplate> entityList ) {
		List<ColonnaTemplateDto> dtoList = null;

		if ( entityList != null ) {
			dtoList = new ArrayList<> ();
			for ( EpaypaTColonnaTemplate entity: entityList ) {
				dtoList.add ( toColonnaTemplateDto ( entity ) );
			}
		}

		return dtoList;
	}

	protected TemplateDto toTemplateDto ( EpaypaTTemplate entity ) {
		TemplateDto dto = null;

		if ( entity != null ) {
			dto = new TemplateDto ();
			dto.setNome ( entity.getNome () );
			dto.setDescrizione ( entity.getDescrizione () );
			dto.setColonneTemplate ( toColonnaTemplateDtoList ( entity.getEpaypaTColonnaTemplates () ) );
			if ( entity.getEpaypaDTipoFormatoOutput () != null ) {
				dto.setTipoFormato ( TipoFormatoFileEnum.fromId ( entity.getEpaypaDTipoFormatoOutput ().getIdTipoFormatoOutput () ) );
			}
		}

		return dto;
	}

	protected DatiAvvisoPagamentoDto toDatiAvvisoPagamentoDto ( EpaypaTDatiAvvisoPagamento entity ) {
		DatiAvvisoPagamentoDto dto = null;

		if ( entity != null ) {
			dto = new DatiAvvisoPagamentoDto ();
			dto.setSettore ( entity.getSettore () );
			dto.setIndirizzo ( entity.getIndirizzo () );
			dto.setCap ( entity.getCap () );
			dto.setLocalita ( entity.getLocalita () );
			dto.setSiglaProvincia ( entity.getSiglaProvincia () );
			dto.setEmail ( entity.getEmail () );
			dto.setInfoEnte ( entity.getInfoEnte () );
			dto.setIntestatarioCCPostale ( entity.getIntestatarioCCPostale () );
			dto.setNumeroCCPostale ( entity.getNumeroCCPostale () );
			dto.setAutorizzazioneDiPosteIt ( entity.getAutorizzazioneDaPosteIt () );
		}

		return dto;
	}

	protected ConfigurazioneDto toConfigurazioneDto ( EpaypaTConfigurazione entity ) {
		ConfigurazioneDto dto = null;

		if ( entity != null ) {
			dto = new ConfigurazioneDto ();
			dto.setIdEnte ( entity.getEpaypaTEnte ().getIdEnte () );
			dto.setCodice ( entity.getCodice () );
			dto.setValore ( entity.getValore () );
			dto.setDescrizione ( entity.getDescrizione () );
		}

		return dto;
	}

}
