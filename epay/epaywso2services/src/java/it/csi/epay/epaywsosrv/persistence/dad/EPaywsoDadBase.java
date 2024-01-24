/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.dad;

import it.csi.epay.epaywsosrv.dto.*;
import it.csi.epay.epaywsosrv.enumeration.IssueEnum;
import it.csi.epay.epaywsosrv.enumeration.StatoRichiestaEnum;
import it.csi.epay.epaywsosrv.enumeration.TipoRichiestaEnum;
import it.csi.epay.epaywsosrv.exception.PersistenceException;
import it.csi.epay.epaywsosrv.persistence.entity.*;
import it.csi.epay.epaywsosrv.util.LogAndWatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static it.csi.epay.epaywsosrv.util.Util.APPLICATION_CODE;

public class EPaywsoDadBase {
	static private final String CLASSNAME = EPaywsoDadBase.class.getSimpleName();
	static protected Logger log = LogManager.getLogger(APPLICATION_CODE + ".persistence");

	protected void handlePersistenceException(Throwable e) throws PersistenceException {
		String methodName = "handlePersistenceException";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName, false);

		try {
			lw.start();

			PersistenceException p;

			if (e instanceof PersistenceException) {
				p = (PersistenceException) e;

			} else {
				// eccezione imprevista
				p = new PersistenceException(IssueEnum.GENERIC_SYSTEM_ERROR.getCod(), e.getMessage(), e);
			}

			lw.error("errorCod:" + p.getErrorCod() + " errorDes:" + p.getErrorDes(), p.getCause());
			throw p;

		} finally {
			lw.stop();
		}
	}

	protected Timestamp getTimestampNow() {
		return new Timestamp(System.currentTimeMillis());
	}

	protected Timestamp toTimestamp(Date date) {
		Timestamp timestamp = null;
		if (date != null) {
			timestamp = new Timestamp(date.getTime());
		}
		return timestamp;
	}

	protected Timestamp toTimestampBeforeNSeconds(long seconds, Date date) {
		return toTimestampAddSeconds(-seconds, date);
	}

	protected Timestamp toTimestampAfterNSeconds(long seconds, Date date) {
		return toTimestampAddSeconds(+seconds, date);
	}

	private Timestamp toTimestampAddSeconds(long seconds, Date date) {
		Timestamp timestamp = null;
		if (date != null) {
			timestamp = new Timestamp(1000 * seconds + date.getTime());
		}
		return timestamp;
	}

	protected String trunc(String s, int maxlength) {
		String t = s;

		if (s != null && s.length() > maxlength) {
			t = s.substring(0, maxlength);
		}

		return t;
	}

	protected AppDto toAppDto(EPaywsoTApplicativo entity) {
		AppDto dto = null;

		if (entity != null) {
			dto = new AppDto(entity.getIdApplicativo());
			dto.setDes(entity.getDescrizione());
			if (entity.getEPaywsoTEnte() != null) {
				dto.setIdEnte(entity.getEPaywsoTEnte().getIdEnte());
			}
			dto.setUtente(entity.getUtente());
			dto.setMsInbound(entity.getMsInbound());
			dto.setMsOutbound(entity.getMsOutbound());
			dto.setDataInizioValidita(entity.getDtInizioValidita());
			dto.setDataFineValidita(entity.getDtFineValidita());
			dto.setCodiceModalitaIntegrazione(entity.getCodiceModalitaIntegrazione ());
		}

		return dto;
	}

	protected List<AppDto> toAppDtoList(List<EPaywsoTApplicativo> entityList) {
		List<AppDto> dtoList = null;

		if (entityList != null) {
			dtoList = new ArrayList<AppDto>();
			for (EPaywsoTApplicativo entity : entityList) {
				dtoList.add(toAppDto(entity));
			}
		}

		return dtoList;
	}

	protected EnteDto toEnteDto(EPaywsoTEnte entity) {
		EnteDto dto = null;

		if (entity != null) {
			dto = new EnteDto(entity.getIdEnte(), entity.getCodFiscaleEnte());
			dto.setDenominazione(entity.getDenominazione());
			dto.setDataInizioValidita(entity.getDtInizioValidita());
			dto.setDataFineValidita(entity.getDtFineValidita());
		}

		return dto;
	}

	protected CodVersDto toCodVersDto(EPaywsoTCodiceVersamento entity) {
		CodVersDto dto = null;

		if (entity != null) {
			dto = new CodVersDto(entity.getIdCodiceVersamento());
			dto.setCod(entity.getCodVersamento());
			dto.setDes(entity.getDescrizione());
			if (entity.getEpaywsoTApplicativo() != null) {
				dto.setIdApp(entity.getEpaywsoTApplicativo().getIdApplicativo());
			}
			dto.setDataInizioValidita(entity.getDtInizioValidita());
			dto.setDataFineValidita(entity.getDtFineValidita());
		}

		return dto;
	}

	protected List<CodVersDto> toCodVersDtoList(List<EPaywsoTCodiceVersamento> entityList) {
		List<CodVersDto> dtoList = null;

		if (entityList != null) {
			dtoList = new ArrayList<CodVersDto>();
			for (EPaywsoTCodiceVersamento entity : entityList) {
				dtoList.add(toCodVersDto(entity));
			}
		}

		return dtoList;
	}

	protected EndpointAppDto toEndpointAppDto(EPaywsoRAppTiporicEp entity) {
		EndpointAppDto dto = null;

		if (entity != null) {
			dto = new EndpointAppDto();
			if (entity.getEPaywsoDTipoRichiesta() != null) {
				dto.setTipoRichiestaEnum(TipoRichiestaEnum.fromId(entity.getEPaywsoDTipoRichiesta().getIdTipoRichiesta()));
			}
			dto.setAppDto(toAppDto(entity.getEPaywsoTApplicativo()));
			dto.setEndpointDto(toEndpointDto(entity.getEPaywsoTEndpoint()));
		}

		return dto;
	}

	protected List<EndpointAppDto> toEndpointAppDtoList(List<EPaywsoRAppTiporicEp> entityList) {
		List<EndpointAppDto> dtoList = null;

		if (entityList != null) {
			dtoList = new ArrayList<EndpointAppDto>();
			for (EPaywsoRAppTiporicEp entity : entityList) {
				dtoList.add(toEndpointAppDto(entity));
			}
		}

		return dtoList;
	}

	protected ConfigAppDto toConfigAppDto(EPaywsoTConfigApplicativo entity) {
		ConfigAppDto dto = null;

		if (entity != null) {
			dto = new ConfigAppDto();

			AppDto appDto = new AppDto(entity.getIdApplicativo());
			appDto.setDes(entity.getDescrizioneApplicativo());
			appDto.setIdEnte(entity.getIdEnte());
			appDto.setUtente(entity.getUtente());
			appDto.setMsInbound(entity.getMsInbound());
			appDto.setMsOutbound(entity.getMsOutbound());
			dto.setAppDto(appDto);

			EnteDto enteDto = new EnteDto(entity.getIdEnte(), entity.getCodFiscaleEnte());
			enteDto.setDenominazione(entity.getDenominazioneEnte());
			dto.setEnteDto(enteDto);

			CodVersDto codVersDto = new CodVersDto(entity.getIdCodiceVersamento());
			codVersDto.setCod(entity.getCodVersamento());
			codVersDto.setDes(entity.getDescrizioneCodVersamento());
			codVersDto.setIdApp(entity.getIdApplicativo());
			dto.setCodVersDto(codVersDto);

			EndpointDto endpointDto = new EndpointDto(entity.getIdEndpoint());
			endpointDto.setProtocol(entity.getProtocollo());
			endpointDto.setHost(entity.getHost());
			endpointDto.setPort(entity.getPort());
			endpointDto.setContext(entity.getContext());
			endpointDto.setNomeUtente(entity.getNomeUtente());
			endpointDto.setPassword(entity.getPassword());
			endpointDto.setAutenticato(entity.getAutenticato());
			endpointDto.setApiManager(entity.getApiManager());
			dto.setEndpointDto(endpointDto);
		}

		return dto;
	}

	protected List<ConfigAppDto> toConfigAppDtoList(List<EPaywsoTConfigApplicativo> entityList) {
		List<ConfigAppDto> dtoList = null;

		if (entityList != null) {
			dtoList = new ArrayList<ConfigAppDto>();
			for (EPaywsoTConfigApplicativo entity : entityList) {
				dtoList.add(toConfigAppDto(entity));
			}
		}

		return dtoList;
	}

	protected EndpointDto toEndpointDto(EPaywsoTEndpoint entity) {
		EndpointDto dto = null;

		if (entity != null) {
			dto = new EndpointDto(entity.getIdEndpoint());
			dto.setProtocol(entity.getProtocollo());
			dto.setHost(entity.getHost());
			dto.setPort(entity.getPort());
			dto.setContext(entity.getContext());
			dto.setAutenticato(entity.getAutenticato());
			dto.setNomeUtente(entity.getNomeUtente());
			dto.setPassword(entity.getPassword());
			dto.setApiManager(entity.getApiManager());
			dto.setDataInizioValidita(entity.getDtInizioValidita());
			dto.setDataFineValidita(entity.getDtFineValidita());
		}

		return dto;
	}

	protected List<EndpointDto> toEndpointDtoList(List<EPaywsoTEndpoint> entityList) {
		List<EndpointDto> dtoList = null;

		if (entityList != null) {
			dtoList = new ArrayList<EndpointDto>();
			for (EPaywsoTEndpoint entity : entityList) {
				dtoList.add(toEndpointDto(entity));
			}
		}

		return dtoList;
	}

	protected EsitoRichiestaDto toEsitoRichiestaDto(EPaywsoDEsito entity, String dettaglio) {
		EsitoRichiestaDto dto = null;

		if (entity != null) {
			dto = new EsitoRichiestaDto(entity.getCodEsito(), entity.getDescrizione(), dettaglio);
		}

		return dto;
	}

	protected RichiestaDto toRichiestaDto(EPaywsoTRichiesta entity) {
		RichiestaDto dto = null;
		if (entity != null) {
			dto = new RichiestaDto(entity.getIdRichiesta());
			if (entity.getEPaywsoTEnte() != null) {
				dto.setCodFiscaleEnte(entity.getEPaywsoTEnte().getCodFiscaleEnte());
				dto.setDenominazioneEnte(entity.getEPaywsoTEnte().getDenominazione());
			}
			if (entity.getEPaywsoDTipoRichiesta() != null) {
				dto.setTipoRichiestaEnum(TipoRichiestaEnum.fromId(entity.getEPaywsoDTipoRichiesta().getIdTipoRichiesta()));
			}
			if (entity.getEPaywsoDStatoRichiesta() != null) {
				dto.setStatoRichiestaEnum(StatoRichiestaEnum.fromId(entity.getEPaywsoDStatoRichiesta().getIdStatoRichiesta()));
			}
			dto.setIdMessaggio(entity.getIdMessaggio());
			dto.setCodFiscaleEnteOrigine(entity.getCfEnteOrigine());
			dto.setPagamentoSpontaneo(entity.getPagamentoSpontaneo());
			dto.setCodVersamento(entity.getCodVersamento());
			dto.setMessageUUID(entity.getMessageUUID());
			dto.setDataInserimentoRichiesta(entity.getDtInserimentoRichiesta());
			dto.setDataUltimaVariazione(entity.getDtUltimaVariazione());
			dto.setEsitoRichiestaDto(toEsitoRichiestaDto(entity.getEPaywsoDEsito(), entity.getDettaglioEsito()));
			dto.setDataCallback(entity.getDtCallback());
			dto.setEsitoCallbackDto(new EsitoCallbackDto(entity.getCodEsitoCallback(), entity.getDettaglioEsitoCallback()));
			dto.setNumTotaleElementi(entity.getNumTotaleElementi());
			dto.setImportoTotale(entity.getImportoTotale());
			dto.setDimMessaggioMB(entity.getDimMessaggioMb());
			dto.setContenutoMessaggio(entity.getContenutoMessaggio());
			dto.setContenutoCallback(entity.getContenutoCallback());
			dto.setIdPSP(entity.getIdPSP());
			dto.setIdFlussoRendicontazione(entity.getIdFlussoRendicontazione());
		}

		return dto;
	}

	protected List<RichiestaDto> toRichiestaDtoList(List<EPaywsoTRichiesta> entityList) {
		List<RichiestaDto> dtoList = null;

		if (entityList != null) {
			dtoList = new ArrayList<RichiestaDto>();
			for (EPaywsoTRichiesta entity : entityList) {
				dtoList.add(toRichiestaDto(entity));
			}
		}

		return dtoList;
	}

	protected RichiestaLightDto toRichiestaLightDto(EPaywsoTRichiestaLight entity) {
		RichiestaLightDto dto = null;

		if (entity != null) {
			dto = new RichiestaLightDto(entity.getIdRichiesta());
			dto.setMessageUUID(entity.getMessageUUID());
			dto.setIdMessaggio(entity.getIdMessaggio());
			if (entity.getEPaywsoTEnte() != null) {
				dto.setCodFiscaleEnte(entity.getEPaywsoTEnte().getCodFiscaleEnte());
			}
			if (entity.getEPaywsoDTipoRichiesta() != null) {
				dto.setTipoRichiestaEnum(TipoRichiestaEnum.fromId(entity.getEPaywsoDTipoRichiesta().getIdTipoRichiesta()));
			}
			if (entity.getEPaywsoDStatoRichiesta() != null) {
				dto.setStatoRichiestaEnum(StatoRichiestaEnum.fromId(entity.getEPaywsoDStatoRichiesta().getIdStatoRichiesta()));
			}
			dto.setPagamentoSpontaneo(entity.getPagamentoSpontaneo());
			dto.setCodVersamento(entity.getCodVersamento());
			dto.setDataInvioAlDestinatario(entity.getDtInvioAlDestinatario());
			if (entity.getEPaywsoDEsito() != null) {
				dto.setCodEsito(entity.getEPaywsoDEsito().getCodEsito());
			}
			dto.setNumTotaleElementi(entity.getNumTotaleElementi());
			dto.setImportoTotale(entity.getImportoTotale());
		}

		return dto;
	}

	protected List<RichiestaLightDto> toRichiestaLightDtoList(List<EPaywsoTRichiestaLight> entityList) {
		List<RichiestaLightDto> dtoList = null;

		if (entityList != null) {
			dtoList = new ArrayList<RichiestaLightDto>();
			for (EPaywsoTRichiestaLight entity : entityList) {
				dtoList.add(toRichiestaLightDto(entity));
			}
		}

		return dtoList;
	}

}
