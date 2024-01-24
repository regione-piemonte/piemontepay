/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.csi.epay.epaypaweb.dto.AvvisoScadutoDto;
import it.csi.epay.epaypaweb.dto.CduDto;
import it.csi.epay.epaypaweb.dto.CodiceVersamentoDto;
import it.csi.epay.epaypaweb.dto.ColonnaTemplateDto;
import it.csi.epay.epaypaweb.dto.ComponenteImportoDto;
import it.csi.epay.epaypaweb.dto.EnteDto;
import it.csi.epay.epaypaweb.dto.FlussoDto;
import it.csi.epay.epaypaweb.dto.FlussoLightDto;
import it.csi.epay.epaypaweb.dto.InvioMailDto;
import it.csi.epay.epaypaweb.dto.NotificaPagamentoDto;
import it.csi.epay.epaypaweb.dto.NotificaPagamentoLightDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaDaAggiornareDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaDaAggiornareLightDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaLightDto;
import it.csi.epay.epaypaweb.dto.ProfiloDto;
import it.csi.epay.epaypaweb.dto.RiversamentoDto;
import it.csi.epay.epaypaweb.dto.RuoloDto;
import it.csi.epay.epaypaweb.dto.SoggettoAnagraficoDto;
import it.csi.epay.epaypaweb.dto.StatoFlussoDto;
import it.csi.epay.epaypaweb.dto.TemplateDto;
import it.csi.epay.epaypaweb.dto.TipoAggiornamentoPosizioneDebitoriaDto;
import it.csi.epay.epaypaweb.dto.TipoFlussoDto;
import it.csi.epay.epaypaweb.dto.TipoFormatoFileDto;
import it.csi.epay.epaypaweb.dto.UtenteDto;
import it.csi.epay.epaypaweb.enumeration.CampoFlussoEnum;
import it.csi.epay.epaypaweb.enumeration.CategoriaCduEnum;
import it.csi.epay.epaypaweb.enumeration.EsitoRiversamentoEnum;
import it.csi.epay.epaypaweb.enumeration.StatoFlussoEnum;
import it.csi.epay.epaypaweb.enumeration.TipoAggiornamentoPosizioneDebitoriaEnum;
import it.csi.epay.epaypaweb.enumeration.TipoFlussoEnum;
import it.csi.epay.epaypaweb.enumeration.TipoFormatoFileEnum;
import it.csi.epay.epaypaweb.enumeration.TipoMailEnum;
import it.csi.epay.epaypaweb.enumeration.TipoMittenteEnum;
import it.csi.epay.epaypaweb.enumeration.TipologiaSoggettoEnum;
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
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTEnte;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTFlusso;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTFlussoLight;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTInvioMail;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTNotificaPagamento;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTNotificaPagamentoLight;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTPosizioneDebitoria;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTPosizioneDebitoriaLight;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTProfilo;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTRendicontazione;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTRendicontazioneLight;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTRiversamento;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTRuolo;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTSoggetto;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTTemplate;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTTransazionePsp;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTUtente;

public class Util {

	static public Timestamp getTimestampNow() {
		return new Timestamp(System.currentTimeMillis());
	}

	static public Timestamp toTimestamp(Date date) {
		Timestamp timestamp = null;
		if (date != null) {
			timestamp = new Timestamp(date.getTime());
		}
		return timestamp;
	}

	static public String trunc(String s, int maxlength) {
		String t = s;

		if (s != null && s.length() > maxlength) {
			t = s.substring(0, maxlength);
		}

		return t;
	}

	static public EnteDto toEnteDto(EpaypaTEnte entity) {
		EnteDto dto = null;

		if (entity != null) {
			dto = new EnteDto(entity.getIdEnte(), entity.getCodFiscaleEnte());
			dto.setDenominazione(entity.getDenominazione());
			dto.setEmail(entity.getEmail());
		}

		return dto;
	}

	static public List<EnteDto> toEnteDtoList(List<EpaypaTEnte> entityList) {
		List<EnteDto> dtoList = null;

		if (entityList != null) {
			dtoList = new ArrayList<EnteDto>();
			for (EpaypaTEnte entity : entityList) {
				dtoList.add(toEnteDto(entity));
			}
		}

		return dtoList;
	}

	static public CodiceVersamentoDto toCodiceVersamentoDto(EpaypaTCodiceVersamento entity) {
		CodiceVersamentoDto dto = null;

		if (entity != null) {
			dto = new CodiceVersamentoDto(entity.getIdCodiceVersamento());
			dto.setCod(entity.getCodVersamento());
			dto.setDes(entity.getDescrizione());
			dto.setEnteDto(toEnteDto(entity.getEpaypaTEnte()));
		}

		return dto;
	}

	static public List<CodiceVersamentoDto> toCodiceVersamentoDtoList(List<EpaypaTCodiceVersamento> entityList) {
		List<CodiceVersamentoDto> dtoList = null;

		if (entityList != null) {
			dtoList = new ArrayList<CodiceVersamentoDto>();
			for (EpaypaTCodiceVersamento entity : entityList) {
				dtoList.add(toCodiceVersamentoDto(entity));
			}
		}

		return dtoList;
	}

	static public TipoFlussoDto toTipoFlussoDto(EpaypaDTipoFlusso entity) {
		TipoFlussoDto dto = null;

		if (entity != null) {
			dto = new TipoFlussoDto(TipoFlussoEnum.fromId(entity.getIdTipoFlusso()), entity.getDirezioneFlusso());
			dto.setDescrizione(entity.getDescrizione());
		}

		return dto;
	}

	static public List<TipoFlussoDto> toTipoFlussoDtoList(List<EpaypaDTipoFlusso> entityList) {
		List<TipoFlussoDto> dtoList = null;

		if (entityList != null) {
			dtoList = new ArrayList<TipoFlussoDto>();
			for (EpaypaDTipoFlusso entity : entityList) {
				dtoList.add(toTipoFlussoDto(entity));
			}
		}

		return dtoList;
	}

	static public StatoFlussoDto toStatoFlussoDto(EpaypaDStatoFlusso entity) {
		StatoFlussoDto dto = null;

		if (entity != null) {
			dto = new StatoFlussoDto(StatoFlussoEnum.fromId(entity.getIdStatoFlusso()), entity.getDirezioneFlusso());
			dto.setDescrizione(entity.getDescrizione());
		}

		return dto;
	}

	static public List<StatoFlussoDto> toStatoFlussoDtoList(List<EpaypaDStatoFlusso> entityList) {
		List<StatoFlussoDto> dtoList = null;

		if (entityList != null) {
			dtoList = new ArrayList<StatoFlussoDto>();
			for (EpaypaDStatoFlusso entity : entityList) {
				dtoList.add(toStatoFlussoDto(entity));
			}
		}

		return dtoList;
	}

	static public TipoAggiornamentoPosizioneDebitoriaDto toTipoAggiornamentoPosizioneDebitoriaDto(EpaypaDTipoAggPosizioneDebitoria entity) {
		TipoAggiornamentoPosizioneDebitoriaDto dto = null;

		if (entity != null) {
			dto = new TipoAggiornamentoPosizioneDebitoriaDto(TipoAggiornamentoPosizioneDebitoriaEnum.fromId(entity.getIdTipoAggPosizioneDebitoria()));
			dto.setDescrizione(entity.getDescrizione());
		}

		return dto;
	}

	static public List<TipoAggiornamentoPosizioneDebitoriaDto> toTipoAggiornamentoPosizioneDebitoriaDtoList(List<EpaypaDTipoAggPosizioneDebitoria> entityList) {
		List<TipoAggiornamentoPosizioneDebitoriaDto> dtoList = null;

		if (entityList != null) {
			dtoList = new ArrayList<TipoAggiornamentoPosizioneDebitoriaDto>();
			for (EpaypaDTipoAggPosizioneDebitoria entity : entityList) {
				dtoList.add(toTipoAggiornamentoPosizioneDebitoriaDto(entity));
			}
		}

		return dtoList;
	}

	static public TipoFormatoFileDto toTipoFormatoFileDto(EpaypaDTipoFormatoOutput entity) {
		TipoFormatoFileDto dto = null;

		if (entity != null) {
			dto = new TipoFormatoFileDto(TipoFormatoFileEnum.fromId(entity.getIdTipoFormatoOutput()));
			dto.setDescrizione(entity.getDescrizione());
		}

		return dto;
	}

	static public List<TipoFormatoFileDto> toTipoFormatoFileDtoList(List<EpaypaDTipoFormatoOutput> entityList) {
		List<TipoFormatoFileDto> dtoList = null;

		if (entityList != null) {
			dtoList = new ArrayList<TipoFormatoFileDto>();
			for (EpaypaDTipoFormatoOutput entity : entityList) {
				dtoList.add(toTipoFormatoFileDto(entity));
			}
		}

		return dtoList;
	}

	static public InvioMailDto toInvioMailDto(EpaypaTInvioMail entity) {
		InvioMailDto dto = null;

		if (entity != null) {
			dto = new InvioMailDto(entity.getIdInvioMail());
			dto.setTo(entity.getDestinatarioTo());
			dto.setCc(entity.getDestinatarioCc());
			dto.setBcc(null);
			if (entity.getEpaypaDTipoInvioMail() != null) {
				dto.setTipoMailEnum(TipoMailEnum.fromId(entity.getEpaypaDTipoInvioMail().getIdTipoInvioMail()));
			}
			dto.setDataInserimento(new Date(entity.getDtInserimento().getTime()));
			dto.setIdFlusso(entity.getIdFlusso());
			if (entity.getDtUltimoTentativo() != null) {
				dto.setDataUltimoTentativo(new Date(entity.getDtUltimoTentativo().getTime()));
			}
			dto.setEsitoUltimoTentativo(entity.getEsitoUltimoTentativo());
			dto.setErroreInvio(entity.getErroreInvio());
		}

		return dto;
	}

	static public RuoloDto toRuoloDto(EpaypaTRuolo entity) {
		RuoloDto dto = null;

		if (entity != null) {
			dto = new RuoloDto(entity.getIdRuolo(), entity.getCodRuolo());
			dto.setDes(entity.getDescrizione());
		}

		return dto;
	}

	static public List<RuoloDto> toRuoloDtoList(List<EpaypaTRuolo> entityList) {
		List<RuoloDto> dtoList = null;

		if (entityList != null) {
			dtoList = new ArrayList<RuoloDto>();
			for (EpaypaTRuolo entity : entityList) {
				dtoList.add(toRuoloDto(entity));
			}
		}

		return dtoList;
	}

	static public ProfiloDto toProfiloDto(EpaypaTProfilo entity) {
		ProfiloDto dto = null;

		if (entity != null) {
			dto = new ProfiloDto(entity.getIdProfilo());
			dto.setDes(entity.getDescrizione());
		}

		return dto;
	}

	static public List<ProfiloDto> toProfiloDtoList(List<EpaypaTProfilo> entityList) {
		List<ProfiloDto> dtoList = null;

		if (entityList != null) {
			dtoList = new ArrayList<ProfiloDto>();
			for (EpaypaTProfilo entity : entityList) {
				dtoList.add(toProfiloDto(entity));
			}
		}

		return dtoList;
	}

	static public UtenteDto toUtenteDto(EpaypaTUtente entity) {
		UtenteDto dto = null;

		if (entity != null) {
			dto = new UtenteDto(entity.getIdUtente(), entity.getCodUtente());
			dto.setNome(entity.getNome());
		}

		return dto;
	}

	static public CduDto toCduDto(EpaypaTCdu entity) {
		CduDto dto = null;

		if (entity != null) {
			dto = new CduDto(entity.getIdCdu(), entity.getCodCdu(), CategoriaCduEnum.fromId(entity.getEpaypaDCategoriaCdu().getIdCategoriaCdu()));
			dto.setDes(entity.getDescrizione());
		}

		return dto;
	}

	static public List<CduDto> toCduDtoList(List<EpaypaTCdu> entityList) {
		List<CduDto> dtoList = null;

		if (entityList != null) {
			dtoList = new ArrayList<CduDto>();
			for (EpaypaTCdu entity : entityList) {
				dtoList.add(toCduDto(entity));
			}
		}

		return dtoList;
	}

	static public FlussoLightDto toFlussoLightDto(EpaypaTFlussoLight entity) {
		FlussoLightDto dto = null;

		if (entity != null) {
			dto = new FlussoLightDto(entity.getIdFlusso());
			if (entity.getEpaypaDTipoFlusso() != null) {
				dto.setTipoFlusso(TipoFlussoEnum.fromId(entity.getEpaypaDTipoFlusso().getIdTipoFlusso()));
				dto.setDesTipoFlusso(entity.getEpaypaDTipoFlusso().getDescrizione());
			}
			if (entity.getEpaypaDStatoFlusso() != null) {
				dto.setStatoFlusso(StatoFlussoEnum.fromId(entity.getEpaypaDStatoFlusso().getIdStatoFlusso()));
				dto.setDesStatoFlusso(entity.getEpaypaDStatoFlusso().getDescrizione());
			}
			dto.setIdMessaggio(entity.getIdMessaggio());
			if (entity.getEpaypaTEnte() != null) {
				dto.setCodFiscaleEnte(entity.getEpaypaTEnte().getCodFiscaleEnte());
				dto.setDenominazioneEnte(entity.getEpaypaTEnte().getDenominazione());
			}
			if (entity.getEpaypaTCodiceVersamento() != null) {
				dto.setCodVersamento(entity.getEpaypaTCodiceVersamento().getCodVersamento());
				dto.setDesCodVersamento(entity.getEpaypaTCodiceVersamento().getDescrizione());
			}
			dto.setNumeroElementi(entity.getNumeroElementi());
			dto.setImportoTotale(entity.getImportoTotale());
			dto.setPagamentiSpontanei(entity.getPagamentiSpontanei());
			dto.setDataInserimento(entity.getDtInserimento());
			dto.setDataUltimaVariazione(entity.getDtUltimaVariazione());
			dto.setUtenteUltimaVariazione(entity.getUtenteUltimaVariazione());
			dto.setCodEsito(entity.getCodEsito());
			dto.setDetEsito(entity.getDettaglioEsito());
			if (entity.getEpaypaTRendicontazione() != null) {
				EpaypaTRendicontazioneLight rendEntity = entity.getEpaypaTRendicontazione();
				dto.setIdFlussoRendicontazione(rendEntity.getIdFlussoRendicontazione());
				dto.setDataOraCreazioneFlusso(rendEntity.getDtOraFlusso());
				dto.setIdUnivocoRegolamento(rendEntity.getIdUnivocoRegolamento());
				dto.setDataRegolamento(rendEntity.getDtRegolamento());
				dto.setTipoMittente(TipoMittenteEnum.fromId(rendEntity.getTipoIdMittente()));
				dto.setCodIdUnivocoMittente(rendEntity.getCodIdUnivocoMittente());
				dto.setDenominazioneMittente(rendEntity.getDenominazioneMittente());
				dto.setCodBicPsp(rendEntity.getCodBicPsp());
			}
		}

		return dto;
	}

	static public List<FlussoLightDto> toFlussoLightDtoList(List<EpaypaTFlussoLight> entityList) {
		List<FlussoLightDto> dtoList = null;

		if (entityList != null) {
			dtoList = new ArrayList<FlussoLightDto>();
			for (EpaypaTFlussoLight entity : entityList) {
				dtoList.add(toFlussoLightDto(entity));
			}
		}

		return dtoList;
	}

	static public FlussoDto toFlussoDto(EpaypaTFlusso entity) {
		FlussoDto dto = null;

		if (entity != null) {
			// testata flusso
			dto = new FlussoDto(entity.getIdFlusso());
			if (entity.getEpaypaDTipoFlusso() != null) {
				dto.setTipoFlusso(TipoFlussoEnum.fromId(entity.getEpaypaDTipoFlusso().getIdTipoFlusso()));
			}
			dto.setDesTipoFlusso(entity.getEpaypaDTipoFlusso().getDescrizione());
			if (entity.getEpaypaDStatoFlusso() != null) {
				dto.setStatoFlusso(StatoFlussoEnum.fromId(entity.getEpaypaDStatoFlusso().getIdStatoFlusso()));
			}
			dto.setDesStatoFlusso(entity.getEpaypaDStatoFlusso().getDescrizione());
			dto.setIdMessaggio(entity.getIdMessaggio());
			dto.setIdEnte(entity.getEpaypaTEnte().getIdEnte());
			dto.setCodFiscaleEnte(entity.getEpaypaTEnte().getCodFiscaleEnte());
			dto.setDenominazioneEnte(entity.getEpaypaTEnte().getDenominazione());
			if (entity.getEpaypaTCodiceVersamento() != null) {
				dto.setCodVersamento(entity.getEpaypaTCodiceVersamento().getCodVersamento());
				dto.setDesCodVersamento(entity.getEpaypaTCodiceVersamento().getDescrizione());
			}
			dto.setNumeroElementi(entity.getNumeroElementi());
			dto.setImportoTotale(entity.getImportoTotale());
			dto.setPagamentiSpontanei(entity.getPagamentiSpontanei());
			dto.setDataInserimento(entity.getDtInserimento());
			dto.setUtenteInserimento(entity.getUtenteInserimento());
			dto.setDataUltimaVariazione(entity.getDtUltimaVariazione());
			dto.setUtenteUltimaVariazione(entity.getUtenteUltimaVariazione());
			dto.setCodEsito(entity.getCodEsito());
			dto.setDetEsito(entity.getDettaglioEsito());
			dto.setDataInoltro(entity.getDtInoltro());
		}

		return dto;
	}

	static public FlussoDto toFlussoDto(EpaypaTRendicontazione entity) {
		FlussoDto dto = null;

		if (entity != null) {
			dto = toFlussoDto(entity.getEpaypaTFlusso());
			dto.setDataOraCreazioneFlusso(entity.getDtOraFlusso());
			dto.setIdUnivocoRegolamento(entity.getIdUnivocoRegolamento());
			dto.setDataRegolamento(entity.getDtRegolamento());
			dto.setTipoMittente(TipoMittenteEnum.fromId(entity.getTipoIdMittente()));
			dto.setCodIdUnivocoMittente(entity.getCodIdUnivocoMittente());
			dto.setDenominazioneMittente(entity.getDenominazioneMittente());
			dto.setCodBicPsp(entity.getCodBicPsp());
		}

		return dto;
	}

	static public SoggettoAnagraficoDto toSoggettoAnagraficoDto(EpaypaTSoggetto entity) {
		SoggettoAnagraficoDto dto = null;

		if (entity != null) {
			dto = new SoggettoAnagraficoDto();
			dto.setIdUnivocoFiscale(entity.getIdUnivocoFiscale());
			dto.setTipologiaSoggettoEnum(TipologiaSoggettoEnum.fromId(entity.getTipologiaSoggetto()));
			if (dto.getTipologiaSoggettoEnum() != null) {
				switch (dto.getTipologiaSoggettoEnum()) {
				case PERSONA_FISICA:
					dto.setCognome(entity.getCognomeRagSoc());
					break;
				case PERSONA_GIURIDICA:
					dto.setRagioneSociale(entity.getCognomeRagSoc());
					break;
				}
			}
			dto.setNome(entity.getNome());
			dto.setIndirizzo(entity.getIndirizzo());
			dto.setCivico(entity.getCivico());
			dto.setCap(entity.getCap());
			dto.setLocalita(entity.getLocalita());
			dto.setProvincia(entity.getProvincia());
			dto.setNazione(entity.getNazione());
			dto.setEmail(entity.getEmail());
		}

		return dto;
	}

	static public NotificaPagamentoLightDto toNotificaPagamentoLightDto(EpaypaTNotificaPagamentoLight entity) {
		NotificaPagamentoLightDto dto = null;

		if (entity != null) {
			dto = new NotificaPagamentoLightDto(entity.getIdNotificaPagamento());
			dto.setIdPosizioneDebitoria(entity.getIdPosizioneDebitoria());
			dto.setIUV(entity.getIuv());
			dto.setImportoPagato(entity.getImportoPagato());
			dto.setDesCausaleVersamento(entity.getDesCausaleVersamento());
			if (entity.getDtEsitoPagamento() != null) {
				dto.setDataEsitoPagamento(new Date(entity.getDtEsitoPagamento().getTime()));
			}
			dto.setSoggettoDebitore(toSoggettoAnagraficoDto(entity.getEpaypaTSoggettoDebitore()));
		}

		return dto;
	}

	static public List<NotificaPagamentoLightDto> toNotificaPagamentoLightDtoList(List<EpaypaTNotificaPagamentoLight> entityList) {
		List<NotificaPagamentoLightDto> dtoList = null;

		if (entityList != null) {
			dtoList = new ArrayList<NotificaPagamentoLightDto>();
			for (EpaypaTNotificaPagamentoLight entity : entityList) {
				dtoList.add(toNotificaPagamentoLightDto(entity));
			}
		}

		return dtoList;
	}

	static public NotificaPagamentoDto toNotificaPagamentoDto(EpaypaTNotificaPagamento notificaPagamentoEntity) {
		NotificaPagamentoDto dto = null;

		if (notificaPagamentoEntity != null) {
			// notifica pagamento
			dto = new NotificaPagamentoDto(notificaPagamentoEntity.getIdNotificaPagamento());
			//
			if (notificaPagamentoEntity.getEpaypaTFlusso() != null) {
				dto.setIdFlusso(notificaPagamentoEntity.getEpaypaTFlusso().getIdFlusso());
			}
			dto.setIdPosizioneDebitoria(notificaPagamentoEntity.getIdPosizioneDebitoria());
			dto.setAnnoRiferimento(notificaPagamentoEntity.getAnnoRiferimento());
			dto.setIUV(notificaPagamentoEntity.getIuv());
			dto.setImportoPagato(notificaPagamentoEntity.getImportoPagato());
			if (notificaPagamentoEntity.getDtScadenza() != null) {
				dto.setDataScadenza(new Date(notificaPagamentoEntity.getDtScadenza().getTime()));
			}
			dto.setDesCausaleVersamento(notificaPagamentoEntity.getDesCausaleVersamento());
			if (notificaPagamentoEntity.getDtEsitoPagamento() != null) {
				dto.setDataEsitoPagamento(new Date(notificaPagamentoEntity.getDtEsitoPagamento().getTime()));
			}
			dto.setSoggettoDebitore(toSoggettoAnagraficoDto(notificaPagamentoEntity.getEpaypaTSoggettoDebitore()));
			dto.setSoggettoVersante(toSoggettoAnagraficoDto(notificaPagamentoEntity.getEpaypaTSoggettoVersante()));
			dto.setDatiSpecificiRiscossione(notificaPagamentoEntity.getDatiSpecificiRiscossione());
			dto.setNote(notificaPagamentoEntity.getNote());
			dto.setCodAvviso(notificaPagamentoEntity.getCodiceAvviso());
			//
			// transazione psp
			EpaypaTTransazionePsp transazionePspEntity = notificaPagamentoEntity.getEpaypaTTransazionePsp();
			dto.setIdPsp(transazionePspEntity.getIdPsp());
			dto.setRagioneSocialePsp(transazionePspEntity.getRagioneSocialePsp());
			if (transazionePspEntity.getEpaypaDTipoVersamento() != null) {
				dto.setCodTipoVersamento(transazionePspEntity.getEpaypaDTipoVersamento().getCodTipoVersamento());
				dto.setDesTipoVersamento(transazionePspEntity.getEpaypaDTipoVersamento().getDescrizione());
			}
			dto.setIdFlussoRendicontazionePsp(transazionePspEntity.getIdFlussoRendicontazione());
			if (transazionePspEntity.getDtAvvioTransazione() != null) {
				dto.setDataAvvioTransazione(new Date(transazionePspEntity.getDtAvvioTransazione().getTime()));
			}
			dto.setIUR(transazionePspEntity.getIur());
			if (transazionePspEntity.getDtAutorizzazione() != null) {
				dto.setDataOraAutorizzazione(new Date(transazionePspEntity.getDtAutorizzazione().getTime()));
			}
			dto.setTipoSicurezza(transazionePspEntity.getTipoSicurezza());
			dto.setImportoTransato(transazionePspEntity.getImportoTransato());
			dto.setImportoCommissioni(transazionePspEntity.getImportoCommissioni());
		}

		return dto;
	}

	static public List<NotificaPagamentoDto> toNotificaPagamentoDtoList(List<EpaypaTNotificaPagamento> entityList) {
		List<NotificaPagamentoDto> dtoList = null;

		if (entityList != null) {
			dtoList = new ArrayList<NotificaPagamentoDto>();
			for (EpaypaTNotificaPagamento entity : entityList) {
				dtoList.add(toNotificaPagamentoDto(entity));
			}
		}

		return dtoList;
	}

	static public ComponenteImportoDto toComponenteImportoDto(EpaypaTComponenteImporto entity) {
		ComponenteImportoDto dto = null;

		if (entity != null) {
			dto = new ComponenteImportoDto(entity.getIdComponente());
			dto.setImporto(entity.getImporto());
			dto.setCausale(entity.getCausale());
			dto.setDatiSpecificiRiscossione(entity.getDatiSpecRiscossione());
			dto.setAnnoAccertamento ( entity.getAnnoAccertamento () );
			dto.setNumeroAccertamento ( entity.getNumeroAccertamento () );
		}

		return dto;
	}

	static public List<ComponenteImportoDto> toComponenteImportoDtoList(List<EpaypaTComponenteImporto> entityList) {
		List<ComponenteImportoDto> dtoList = null;

		if (entityList != null) {
			dtoList = new ArrayList<ComponenteImportoDto>();
			for (EpaypaTComponenteImporto entity : entityList) {
				dtoList.add(toComponenteImportoDto(entity));
			}
		}

		return dtoList;
	}

	static public PosizioneDebitoriaLightDto toPosizioneDebitoriaLightDto(EpaypaTPosizioneDebitoriaLight entity) {
		PosizioneDebitoriaLightDto dto = null;

		if (entity != null) {
			dto = new PosizioneDebitoriaLightDto(entity.getIdPosizioneDebitoria());
			dto.setIUV(entity.getIuv());
			dto.setImportoTotale(entity.getImportoTotale());
			dto.setDesCausaleVersamento(entity.getDesCausaleVersamento());
			if (entity.getDtScadenza() != null) {
				dto.setDataScadenza(new Date(entity.getDtScadenza().getTime()));
			}
			dto.setSoggettoDebitore(toSoggettoAnagraficoDto(entity.getEpaypaTSoggettoDebitore()));
		}

		return dto;
	}

	static public List<PosizioneDebitoriaLightDto> toPosizioneDebitoriaLightDtoList(List<EpaypaTPosizioneDebitoriaLight> entityList) {
		List<PosizioneDebitoriaLightDto> dtoList = null;

		if (entityList != null) {
			dtoList = new ArrayList<PosizioneDebitoriaLightDto>();
			for (EpaypaTPosizioneDebitoriaLight entity : entityList) {
				dtoList.add(toPosizioneDebitoriaLightDto(entity));
			}
		}

		return dtoList;
	}

	static public PosizioneDebitoriaDto toPosizioneDebitoriaDto(EpaypaTPosizioneDebitoria entity) {
		PosizioneDebitoriaDto dto = null;

		if (entity != null) {
			// posizione debitoria
			dto = new PosizioneDebitoriaDto(entity.getIdPosizioneDebitoria());
			//
			if (entity.getEpaypaTFlusso() != null) {
				dto.setIdFlusso(entity.getEpaypaTFlusso().getIdFlusso());
			}
			dto.setIdPosizioneDebitoriaEsterna(entity.getIdPosizioneDebitoriaEst());
			dto.setAnnoRiferimento(entity.getAnnoRiferimento());
			dto.setImportoTotale(entity.getImportoTotale());
			if (entity.getDtScadenza() != null) {
				dto.setDataScadenza(new Date(entity.getDtScadenza().getTime()));
			}
			if (entity.getDtInizioValidita() != null) {
				dto.setDataInizioValidita(new Date(entity.getDtInizioValidita().getTime()));
			}
			if (entity.getDtFineValidita() != null) {
				dto.setDataFineValidita(new Date(entity.getDtFineValidita().getTime()));
			}
			dto.setDesCausaleVersamento(entity.getDesCausaleVersamento());
			dto.setDesRata(entity.getDesRata());
			dto.setNotePerPagatore(entity.getNotePerPagatore());
			dto.setIUV(entity.getIuv());
			dto.setCodAvviso(entity.getCodiceAvviso());
			dto.setCodEsito(entity.getCodEsito());
			dto.setDetEsito(entity.getDettaglioEsito());
			//
			// soggetto debitore
			dto.setSoggettoDebitore(toSoggettoAnagraficoDto(entity.getEpaypaTSoggettoDebitore()));
			//
			// componenti importo
			dto.setComponenteImportoDtoList(toComponenteImportoDtoList(entity.getEpaypaTComponenteImportos()));
		}

		return dto;
	}

	static public List<PosizioneDebitoriaDto> toPosizioneDebitoriaDtoList(List<EpaypaTPosizioneDebitoria> entityList) {
		List<PosizioneDebitoriaDto> dtoList = null;

		if (entityList != null) {
			dtoList = new ArrayList<PosizioneDebitoriaDto>();
			for (EpaypaTPosizioneDebitoria entity : entityList) {
				dtoList.add(toPosizioneDebitoriaDto(entity));
			}
		}

		return dtoList;
	}

	static public PosizioneDebitoriaDaAggiornareLightDto toPosizioneDebitoriaDaAggiornareLightDto(EpaypaTAggPosizioneDebitoriaLight entity) {
		PosizioneDebitoriaDaAggiornareLightDto dto = null;

		if (entity != null) {
			dto = new PosizioneDebitoriaDaAggiornareLightDto(entity.getIdAggPosizioneDebitoria());
			//
			dto.setTipoAggiornamento(toTipoAggiornamentoPosizioneDebitoriaDto(entity.getEpaypaDTipoAggPosizioneDebitoria()));
			dto.setIdPosizioneDebitoriaEsterna(entity.getIdPosizioneDebitoriaEst());
			dto.setMotivazione(entity.getMotivazione());
			dto.setCodAvviso(entity.getCodiceAvviso());
			dto.setCodEsito(entity.getCodEsito());
			dto.setDetEsito(entity.getDettaglioEsito());
		}

		return dto;
	}

	static public List<PosizioneDebitoriaDaAggiornareLightDto> toPosizioneDebitoriaDaAggiornareLightDtoList(List<EpaypaTAggPosizioneDebitoriaLight> entityList) {
		List<PosizioneDebitoriaDaAggiornareLightDto> dtoList = null;

		if (entityList != null) {
			dtoList = new ArrayList<PosizioneDebitoriaDaAggiornareLightDto>();
			for (EpaypaTAggPosizioneDebitoriaLight entity : entityList) {
				dtoList.add(toPosizioneDebitoriaDaAggiornareLightDto(entity));
			}
		}

		return dtoList;
	}

	static public PosizioneDebitoriaDaAggiornareDto toPosizioneDebitoriaDaAggiornareDto(EpaypaTAggPosizioneDebitoria entity) {
		PosizioneDebitoriaDaAggiornareDto dto = null;

		if (entity != null) {
			dto = new PosizioneDebitoriaDaAggiornareDto(entity.getIdAggPosizioneDebitoria());
			//
			if (entity.getEpaypaTFlusso() != null) {
				dto.setIdFlusso(entity.getEpaypaTFlusso().getIdFlusso());
			}
			//
			dto.setTipoAggiornamento(toTipoAggiornamentoPosizioneDebitoriaDto(entity.getEpaypaDTipoAggPosizioneDebitoria()));
			dto.setIdPosizioneDebitoriaEsterna(entity.getIdPosizioneDebitoriaEst());
			dto.setMotivazione(entity.getMotivazione());
			dto.setCodAvviso(entity.getCodiceAvviso());
			dto.setCodEsito(entity.getCodEsito());
			dto.setDetEsito(entity.getDettaglioEsito());
			//
			if (entity.getDtScadenza() != null) {
				dto.setDataScadenza(new Date(entity.getDtScadenza().getTime()));
			}
			if (entity.getDtInizioValidita() != null) {
				dto.setDataInizioValidita(new Date(entity.getDtInizioValidita().getTime()));
			}
			if (entity.getDtFineValidita() != null) {
				dto.setDataFineValidita(new Date(entity.getDtFineValidita().getTime()));
			}
			dto.setImportoTotale(entity.getImportoTotale());
			dto.setDesCausaleVersamento(entity.getDesCausaleVersamento());
			//
			// componenti importo
			dto.setComponenteImportoDtoList(toComponenteImportoDtoList(entity.getEpaypaTComponenteImportos()));
		}

		return dto;
	}

	static public List<PosizioneDebitoriaDaAggiornareDto> toPosizioneDebitoriaDaAggiornareDtoList(List<EpaypaTAggPosizioneDebitoria> entityList) {
		List<PosizioneDebitoriaDaAggiornareDto> dtoList = null;

		if (entityList != null) {
			dtoList = new ArrayList<PosizioneDebitoriaDaAggiornareDto>();
			for (EpaypaTAggPosizioneDebitoria entity : entityList) {
				dtoList.add(toPosizioneDebitoriaDaAggiornareDto(entity));
			}
		}

		return dtoList;
	}

	static public AvvisoScadutoDto toAvvisoScadutoDto(EpaypaTAvvisoScaduto entity) {
		AvvisoScadutoDto dto = null;

		if (entity != null) {
			dto = new AvvisoScadutoDto(entity.getIdAvvisoScaduto());
			//
			if (entity.getEpaypaTFlusso() != null) {
				dto.setIdFlusso(entity.getEpaypaTFlusso().getIdFlusso());
			}
			dto.setIUV(entity.getIuv());
			dto.setImporto(entity.getImporto());
			dto.setDataScadenza(entity.getDtScadenza());
		}

		return dto;
	}

	static public List<AvvisoScadutoDto> toAvvisoScadutoDtoList(List<EpaypaTAvvisoScaduto> entityList) {
		List<AvvisoScadutoDto> dtoList = null;

		if (entityList != null) {
			dtoList = new ArrayList<AvvisoScadutoDto>();
			for (EpaypaTAvvisoScaduto entity : entityList) {
				dtoList.add(toAvvisoScadutoDto(entity));
			}
		}

		return dtoList;
	}

	static public RiversamentoDto toRiversamentoDto(EpaypaTRiversamento entity) {
		RiversamentoDto dto = null;

		if (entity != null) {
			dto = new RiversamentoDto(entity.getIdRiversamento());
			//
			if (entity.getEpaypaTRendicontazione() != null) {
				dto.setIdFlusso(entity.getEpaypaTRendicontazione().getIdFlusso());
			}
			dto.setIUV(entity.getIuv());
			dto.setIUR(entity.getIur());
			dto.setIndicePagamento(entity.getIndiceDatiRt());
			dto.setImportoPagato(entity.getImportoPagato());
			dto.setEsito(EsitoRiversamentoEnum.fromId(entity.getCodEsito()));
			dto.setDataEsito(entity.getDtEsito());
		}

		return dto;
	}

	static public List<RiversamentoDto> toRiversamentoDtoList(List<EpaypaTRiversamento> entityList) {
		List<RiversamentoDto> dtoList = null;

		if (entityList != null) {
			dtoList = new ArrayList<RiversamentoDto>();
			for (EpaypaTRiversamento entity : entityList) {
				dtoList.add(toRiversamentoDto(entity));
			}
		}

		return dtoList;
	}

	static public ColonnaTemplateDto toColonnaTemplateDto(EpaypaTColonnaTemplate entity) {
		ColonnaTemplateDto colonnaTemplate = null;

		if (entity != null) {
			colonnaTemplate = new ColonnaTemplateDto();
			colonnaTemplate.setIntestazione(entity.getIntestazione());
			colonnaTemplate.setPosizioneOrdinale(entity.getPosizioneOrdinale());
			colonnaTemplate.setFormatoCSV(entity.getEpaypaTCampoFlusso().getFormatoCsv());
			colonnaTemplate.setFormatoXLSX(entity.getEpaypaTCampoFlusso().getFormatoXlsx());
			colonnaTemplate.setCampoFlusso(CampoFlussoEnum.valueOf(entity.getEpaypaTCampoFlusso().getNome()));
		}

		return colonnaTemplate;
	}

	static public List<ColonnaTemplateDto> toColonnaTemplateDtoList(List<EpaypaTColonnaTemplate> entityList) {
		List<ColonnaTemplateDto> dtoList = null;

		if (entityList != null) {
			dtoList = new ArrayList<ColonnaTemplateDto>();
			for (EpaypaTColonnaTemplate entity : entityList) {
				dtoList.add(toColonnaTemplateDto(entity));
			}
		}

		return dtoList;
	}

	static public TemplateDto toTemplateDto(EpaypaTTemplate entity) {
		TemplateDto dto = null;

		if (entity != null) {
			dto = new TemplateDto();
			dto.setNome(entity.getNome());
			dto.setDescrizione(entity.getDescrizione());
			dto.setColonneTemplate(toColonnaTemplateDtoList(entity.getEpaypaTColonnaTemplates()));
			if (entity.getEpaypaDTipoFormatoOutput() != null) {
				dto.setTipoFormato(TipoFormatoFileEnum.fromId(entity.getEpaypaDTipoFormatoOutput().getIdTipoFormatoOutput()));
			}
		}

		return dto;
	}

}
