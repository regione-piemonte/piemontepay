/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.facade;

import static it.csi.epay.epaywsosrv.util.Util.APPLICATION_CODE;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import it.csi.epay.epaywsosrv.dto.ConfigAppDto;
import it.csi.epay.epaywsosrv.dto.CriterioOrdinamentoDto;
import it.csi.epay.epaywsosrv.dto.EndpointDto;
import it.csi.epay.epaywsosrv.dto.EnteDto;
import it.csi.epay.epaywsosrv.dto.EsitoCallbackDto;
import it.csi.epay.epaywsosrv.dto.EsitoInvioDto;
import it.csi.epay.epaywsosrv.dto.EsitoRichiestaDto;
import it.csi.epay.epaywsosrv.dto.EsitoStatoAggregatoDto;
import it.csi.epay.epaywsosrv.dto.PaginazioneDto;
import it.csi.epay.epaywsosrv.dto.RicercaStatoAggregatoDto;
import it.csi.epay.epaywsosrv.dto.RichiestaBaseDto;
import it.csi.epay.epaywsosrv.dto.RichiestaDto;
import it.csi.epay.epaywsosrv.dto.RichiestaLightDto;
import it.csi.epay.epaywsosrv.dto.RichiestaLightFilterDto;
import it.csi.epay.epaywsosrv.enumeration.ColumnNameRichiestaEnum;
import it.csi.epay.epaywsosrv.enumeration.IssueEnum;
import it.csi.epay.epaywsosrv.enumeration.SortTypeEnum;
import it.csi.epay.epaywsosrv.enumeration.StatoRichiestaEnum;
import it.csi.epay.epaywsosrv.exception.BusinessException;
import it.csi.epay.epaywsosrv.exception.FacadeException;
import it.csi.epay.epaywsosrv.exception.SystemException;
import it.csi.epay.epaywsosrv.facade.dto.common.EndpointType;
import it.csi.epay.epaywsosrv.facade.dto.common.EndpointType.CredenzialiAutenticazione;
import it.csi.epay.epaywsosrv.facade.dto.common.ResultType;
import it.csi.epay.epaywsosrv.facade.dto.common.StatoRichiestaType;
import it.csi.epay.epaywsosrv.facade.dto.integrationservice.AggiornaRichiestaESingoloEsitoInvioRequestType;
import it.csi.epay.epaywsosrv.facade.dto.integrationservice.AggiornaRichiestaRequestType;
import it.csi.epay.epaywsosrv.facade.dto.integrationservice.CodiceVersamentoTypeList;
import it.csi.epay.epaywsosrv.facade.dto.integrationservice.ConfigurazioneApplicativoType;
import it.csi.epay.epaywsosrv.facade.dto.integrationservice.ConfigurazioneApplicativoTypeList;
import it.csi.epay.epaywsosrv.facade.dto.integrationservice.CriterioOrdinamentoRichiestaType;
import it.csi.epay.epaywsosrv.facade.dto.integrationservice.CriterioOrdinamentoRichiestaTypeList;
import it.csi.epay.epaywsosrv.facade.dto.integrationservice.EnteType;
import it.csi.epay.epaywsosrv.facade.dto.integrationservice.EsitoInvioType;
import it.csi.epay.epaywsosrv.facade.dto.integrationservice.EsitoInvioTypeList;
import it.csi.epay.epaywsosrv.facade.dto.integrationservice.EsitoRicercaStatiAggregatiWsoListTypeList;
import it.csi.epay.epaywsosrv.facade.dto.integrationservice.EsitoRicercaStatiAggregatiWsoType;
import it.csi.epay.epaywsosrv.facade.dto.integrationservice.FiltroSelezioneRichiesteType;
import it.csi.epay.epaywsosrv.facade.dto.integrationservice.InserisciNuovaRichiestaRequestType;
import it.csi.epay.epaywsosrv.facade.dto.integrationservice.PaginazioneType;
import it.csi.epay.epaywsosrv.facade.dto.integrationservice.RicercaStatoAggregatoWsoType;
import it.csi.epay.epaywsosrv.facade.dto.integrationservice.RichiestaLightType;
import it.csi.epay.epaywsosrv.facade.dto.integrationservice.RichiestaLightTypeList;
import it.csi.epay.epaywsosrv.facade.dto.integrationservice.RichiestaType;
import it.csi.epay.epaywsosrv.facade.dto.integrationservice.StatoRichiestaTypeList;
import it.csi.epay.epaywsosrv.util.LogAndWatch;

/** Classe padre lato facade */
public class EPaywsoFacadeBase {
	static private final String CLASSNAME = EPaywsoFacadeBase.class.getSimpleName();
	static protected Logger log = LogManager.getLogger(APPLICATION_CODE + ".facade");

	protected ResultType handleFacadeException(Throwable e) {
		String methodName = "handleFacadeException";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName, false);

		ResultType resultType = null;

		try {
			FacadeException f;

			if (e instanceof FacadeException) {
				f = (FacadeException) e;

			} else if (e instanceof BusinessException) {
				BusinessException b = (BusinessException) e;
				f = new FacadeException(b.getErrorCod(), b.getErrorDes(), b.getCause());

			} else if (e instanceof SystemException) {
				SystemException s = (SystemException) e;
				f = new FacadeException(s.getErrorCod(), s.getErrorDes(), s.getCause());

			} else {
				// eccezione imprevista e non trattata, ricostruisce la catena dei metodi invocati
				StackTraceElement[] stackTrace = e.getStackTrace();
				StringBuffer invokedMethods = new StringBuffer();
				for (int i = 0, n = stackTrace.length; i < n; i++) {
					invokedMethods.append(stackTrace[i].getClassName() + "::" + stackTrace[i].getMethodName());
					if (i < n - 1) {
						invokedMethods.append(" <- ");
					}
				}
				f = new FacadeException(IssueEnum.GENERIC_SYSTEM_ERROR.getCod(), "Errore generico in: " + invokedMethods.toString(), e);
			}

			lw.error("errorCod:" + f.getErrorCod() + " errorDes:" + f.getErrorDes(), f.getCause());
			resultType = buildResult(f.getErrorCod(), f.getErrorDes());

		} finally {
			lw.stop();
		}

		return resultType;
	}

	protected ResultType buildResultOK() {
		return buildResult(IssueEnum.OK.getCod(), null);
	}

	protected ResultType buildResult(String codice, String messaggio) {
		ResultType resultType = new ResultType();
		resultType.setCodice(codice);
		resultType.setMessaggio(messaggio);
		return resultType;
	}

	protected boolean isResultOk(ResultType resultType) {
		return resultType != null && resultType.getCodice() != null && resultType.getCodice().equals(IssueEnum.OK.getCod());
	}

	protected EnteType toEnteType(EnteDto dto) {
		EnteType type = null;

		if (dto != null) {
			type = new EnteType();
			type.setIdEnte(dto.getId());
			type.setDenominazione(dto.getDenominazione());
		}

		return type;
	}

	protected EndpointType toEndpointType(EndpointDto dto) {
		EndpointType type = null;

		if (dto != null) {
			type = new EndpointType();
			type.setProtocol(dto.getProtocol());
			type.setHost(dto.getHost());
			type.setContext(dto.getContext());
			type.setPort(dto.getPort());
			type.setCredenzialiAutenticazione(null);
			if (dto.getAutenticato() != null && dto.getAutenticato()) {
				CredenzialiAutenticazione credenzialiAutenticazione = new CredenzialiAutenticazione();
				credenzialiAutenticazione.setNomeUtente(dto.getNomeUtente());
				credenzialiAutenticazione.setPassword(dto.getPassword());
				type.setCredenzialiAutenticazione(credenzialiAutenticazione);
			}
		}

		return type;
	}

	protected ConfigurazioneApplicativoType toConfigurazioneApplicativoType(ConfigAppDto dto) {
		ConfigurazioneApplicativoType type = null;

		if (dto != null) {
			type = new ConfigurazioneApplicativoType();
			if (dto.getEnteDto() != null) {
				type.setCodiceFiscaleEnte(dto.getEnteDto().getCodFiscale());
				type.setDenominazioneEnte(dto.getEnteDto().getDenominazione());
			}
			if (dto.getCodVersDto() != null) {
				type.setDescrizioneCodiceVersamento(dto.getCodVersDto().getDes());
			}
			if (dto.getAppDto() != null) {
				type.setIdApplicativo(dto.getAppDto().getId());
				type.setDescrizioneApplicativo(dto.getAppDto().getDes());
				type.setUtenteApplicativo(dto.getAppDto().getUtente());
				type.setMSInbound(dto.getAppDto().getMsInbound());
				type.setMSOutbound(dto.getAppDto().getMsOutbound());
			}
			type.setEndpoint(toEndpointType(dto.getEndpointDto()));
		}

		return type;
	}

	protected ConfigurazioneApplicativoTypeList toConfigurazioneApplicativoTypeList(List<ConfigAppDto> dtoList) {
		ConfigurazioneApplicativoTypeList typeList = null;

		if (dtoList != null) {
			typeList = new ConfigurazioneApplicativoTypeList();
			for (ConfigAppDto dto : dtoList) {
				typeList.getConfigurazioneApplicativo().add(toConfigurazioneApplicativoType(dto));
			}
		}

		return typeList;
	}

	protected EsitoInvioType toEsitoInvioType(EsitoInvioDto dto) throws FacadeException {
		EsitoInvioType type = null;

		if (dto != null) {
			type = new EsitoInvioType();
			type.setDescrizioneApplicativo(dto.getDesApplicativo());
			type.setMessageStore(dto.getMessageStore());
			if (dto.getEsitoInvioRichiestaDto() != null) {
				EsitoRichiestaDto esitoRichiestaDto = dto.getEsitoInvioRichiestaDto();
				type.setCodiceEsitoInvio(esitoRichiestaDto.getCod());
				type.setDettaglioEsitoInvio(esitoRichiestaDto.getDet());
			}
			type.setDataEsitoInvio(EPaywsoFacadeAdapter.toXMLGregorianCalendar(dto.getDataEsitoInvio()));
		}

		return type;
	}

	protected EsitoInvioTypeList toEsitoInvioTypeList(List<EsitoInvioDto> dtoList) throws FacadeException {
		EsitoInvioTypeList typeList = null;

		if (dtoList != null) {
			typeList = new EsitoInvioTypeList();
			for (EsitoInvioDto dto : dtoList) {
				typeList.getEsitoInvio().add(toEsitoInvioType(dto));
			}
		}

		return typeList;
	}

	protected EsitoInvioDto toEsitoInvioDto(Integer idApplicativo, EsitoInvioType type) {
		EsitoInvioDto dto = null;

		if (type != null) {
			dto = new EsitoInvioDto();
			dto.setIdApplicativo(idApplicativo);
			dto.setDesApplicativo(type.getDescrizioneApplicativo());
			dto.setMessageStore(type.getMessageStore());
			dto.setEsitoInvioRichiestaDto(new EsitoRichiestaDto(type.getCodiceEsitoInvio(), null, type.getDettaglioEsitoInvio()));
			dto.setDataEsitoInvio(EPaywsoFacadeAdapter.toDate(type.getDataEsitoInvio()));
		}

		return dto;
	}

	protected EsitoInvioDto toEsitoInvioDto(AggiornaRichiestaESingoloEsitoInvioRequestType type) {
		EsitoInvioDto dto = null;

		if (type != null) {
			dto = new EsitoInvioDto();
			dto.setIdApplicativo(type.getIdApplicativo());
			dto.setDesApplicativo(type.getDescrizioneApplicativo());
			dto.setMessageStore(type.getMessageStore());
			dto.setEsitoInvioRichiestaDto(new EsitoRichiestaDto(type.getCodiceEsito(), null, type.getDettaglioEsito()));
			dto.setDataEsitoInvio(EPaywsoFacadeAdapter.toDate(type.getDataInvioAlDestinatario()));
		}

		return dto;
	}

	protected RichiestaType toRichiestaType(RichiestaDto dto) throws FacadeException {
		RichiestaType type = null;

		if (dto != null) {
			type = new RichiestaType();
			type.setIdRichiesta(dto.getId() != null ? BigInteger.valueOf(dto.getId()) : null);
			type.setCodiceFiscaleEnte(dto.getCodFiscaleEnte());
			type.setTipoRichiesta(EPaywsoFacadeAdapter.toTipoRichiestaType(dto.getTipoRichiestaEnum()));
			type.setStatoRichiesta(EPaywsoFacadeAdapter.toStatoRichiestaType(dto.getStatoRichiestaEnum()));
			type.setIdMessaggio(dto.getIdMessaggio());
			type.setCfEnteOrigine(dto.getCodFiscaleEnteOrigine());
			type.setPagamentoSpontaneo(dto.getPagamentoSpontaneo());
			type.setCodiceVersamento(dto.getCodVersamento());
			type.setMessageUUID(dto.getMessageUUID());
			type.setDataInserimentoRichiesta(EPaywsoFacadeAdapter.toXMLGregorianCalendar(dto.getDataInserimentoRichiesta()));
			type.setDataUltimaVariazione(EPaywsoFacadeAdapter.toXMLGregorianCalendar(dto.getDataUltimaVariazione()));
			if (dto.getEsitoRichiestaDto() != null) {
				EsitoRichiestaDto esitoRichiestaDto = dto.getEsitoRichiestaDto();
				type.setCodiceEsito(esitoRichiestaDto.getCod());
				type.setDettaglioEsito(esitoRichiestaDto.getDet());
			}
			type.setEsitiInvio(toEsitoInvioTypeList(dto.getEsitoInvioDtoList()));
			type.setDataCallback(EPaywsoFacadeAdapter.toXMLGregorianCalendar(dto.getDataCallback()));
			if (dto.getEsitoCallbackDto() != null) {
				EsitoCallbackDto esitoCallbackDto = dto.getEsitoCallbackDto();
				type.setCodiceEsitoCallback(esitoCallbackDto.getCod());
				type.setDettaglioEsitoCallback(esitoCallbackDto.getDet());
			}
			type.setNumeroTotaleElementi(dto.getNumTotaleElementi());
			type.setImportoTotale(dto.getImportoTotale());
			type.setDimensioneMessaggioMB(dto.getDimMessaggioMB());
			type.setContenutoMessaggio(EPaywsoFacadeAdapter.stringToEmbeddedXMLType(dto.getContenutoMessaggio()));
			type.setContenutoCallback(EPaywsoFacadeAdapter.stringToEmbeddedXMLType(dto.getContenutoCallback()));
		}

		return type;
	}

	protected RichiestaBaseDto toRichiestaBaseDto(InserisciNuovaRichiestaRequestType type, String codFiscaleEnte) throws FacadeException {
		RichiestaBaseDto base = null;

		if (type != null) {
			base = new RichiestaBaseDto();
			base.setCodFiscaleEnte(codFiscaleEnte);
			base.setTipoRichiestaEnum(EPaywsoFacadeAdapter.toTipoRichiestaEnum(type.getTipoRichiesta()));
			base.setStatoRichiestaEnum(EPaywsoFacadeAdapter.toStatoRichiestaEnum(type.getStatoRichiesta()));
			base.setIdMessaggio(type.getIdMessaggio());
			base.setCodFiscaleEnteOrigine(type.getCodiceFiscaleEnte());
			base.setCodVersamento(type.getCodiceVersamento());
			base.setPagamentoSpontaneo(type.isPagamentoSpontaneo());
			base.setMessageUUID(type.getMessageUUID());
			base.setContenutoMessaggio(EPaywsoFacadeAdapter.embeddedXMLTypeToString(type.getContenutoMessaggio()));
			base.setDataInserimentoRichiesta(null); // valorizzato all'atto della memorizzazione sul database
			base.setDataUltimaVariazione(null); // valorizzato all'atto della memorizzazione sul database
			base.setNumTotaleElementi(type.getNumeroTotaleElementi());
			base.setImportoTotale(type.getImportoTotale());
			base.setDimMessaggioMB(new BigDecimal(base.getContenutoMessaggio().length()).divide(new BigDecimal(1048576d)));
			base.setIdPSP(type.getIdPSP());
			base.setIdFlussoRendicontazione(type.getIdFlussoRendicontazione());
		}

		return base;
	}

	protected RichiestaDto toRichiestaDto(AggiornaRichiestaRequestType type) throws FacadeException {
		RichiestaDto dto = null;

		if (type != null) {
			dto = new RichiestaDto();
			dto.setStatoRichiestaEnum(EPaywsoFacadeAdapter.toStatoRichiestaEnum(type.getStatoRichiesta()));
			dto.setMessageUUID(type.getMessageUUID());
			dto.setEsitoRichiestaDto(new EsitoRichiestaDto(type.getCodiceEsito(), null, type.getDettaglioEsito()));
			dto.setDataCallback(EPaywsoFacadeAdapter.toDate(type.getDataCallback()));
			dto.setEsitoCallbackDto(new EsitoCallbackDto(type.getCodiceEsitoCallback(), type.getDettaglioEsitoCallback()));
			dto.setContenutoCallback(EPaywsoFacadeAdapter.embeddedXMLTypeToString(type.getContenutoCallback()));
		}

		return dto;
	}

	protected RichiestaDto toRichiestaDto(AggiornaRichiestaESingoloEsitoInvioRequestType type) throws FacadeException {
		RichiestaDto dto = null;

		if (type != null) {
			dto = new RichiestaDto();
			dto.setStatoRichiestaEnum(EPaywsoFacadeAdapter.toStatoRichiestaEnum(type.getStatoRichiesta()));
			dto.setMessageUUID(type.getMessageUUID());
			dto.setEsitoRichiestaDto(new EsitoRichiestaDto(type.getCodiceEsito(), null, type.getDettaglioEsito()));
			dto.setDataCallback(EPaywsoFacadeAdapter.toDate(type.getDataCallback()));
			dto.setEsitoCallbackDto(new EsitoCallbackDto(type.getCodiceEsitoCallback(), type.getDettaglioEsitoCallback()));
			dto.setContenutoCallback(EPaywsoFacadeAdapter.embeddedXMLTypeToString(type.getContenutoCallback()));
		}

		return dto;
	}

	protected RichiestaLightType toRichiestaLightType(RichiestaLightDto dto) throws FacadeException {
		RichiestaLightType type = null;

		if (dto != null) {
			type = new RichiestaLightType();
			type.setIdRichiesta(dto.getId() != null ? BigInteger.valueOf(dto.getId()) : null);
			type.setMessageUUID(dto.getMessageUUID());
			type.setCodiceFiscaleEnte(dto.getCodFiscaleEnte());
			type.setTipoRichiesta(EPaywsoFacadeAdapter.toTipoRichiestaType(dto.getTipoRichiestaEnum()));
			type.setStatoRichiesta(EPaywsoFacadeAdapter.toStatoRichiestaType(dto.getStatoRichiestaEnum()));
			type.setPagamentoSpontaneo(dto.getPagamentoSpontaneo());
			type.setCodiceVersamento(dto.getCodVersamento());
			type.setDataInvioAlDestinatario(EPaywsoFacadeAdapter.toXMLGregorianCalendar(dto.getDataInvioAlDestinatario()));
			type.setCodiceEsito(dto.getCodEsito());
			type.setNumeroTotaleElementi(dto.getNumTotaleElementi());
			type.setImportoTotale(dto.getImportoTotale());
		}

		return type;
	}

	protected List<String> toCodVersamentoList(CodiceVersamentoTypeList typeList) {
		List<String> strList = null;

		if (typeList != null) {
			strList = new ArrayList<String>();
			strList.addAll(typeList.getCodiceVersamento());
		}

		return strList;
	}

	protected List<StatoRichiestaEnum> toStatoRichiestaEnumList(StatoRichiestaTypeList typeList) {
		List<StatoRichiestaEnum> enList = null;

		if (typeList != null) {
			enList = new ArrayList<StatoRichiestaEnum>();
			for (StatoRichiestaType type : typeList.getStatoRichiesta()) {
				enList.add(EPaywsoFacadeAdapter.toStatoRichiestaEnum(type));
			}
		}

		return enList;
	}

	protected RichiestaLightFilterDto toFiltroSelezioneRichiesteDto(FiltroSelezioneRichiesteType type) {
		RichiestaLightFilterDto dto = null;

		if (type != null) {
			dto = new RichiestaLightFilterDto();
			dto.setCodFiscaleEnte(type.getCodiceFiscaleEnte());
			dto.setTipoRichiestaEnum(EPaywsoFacadeAdapter.toTipoRichiestaEnum(type.getTipoRichiesta()));
			dto.setPagamentoSpontaneo(type.isPagamentoSpontaneo());
			dto.setStatoRichiestaEnumList(toStatoRichiestaEnumList(type.getStatiRichiesta()));
			dto.setCodVersamentoList(toCodVersamentoList(type.getCodiciVersamento()));
			dto.setDataInserimentoRichiestaDa(EPaywsoFacadeAdapter.toDate(type.getDataInserimentoRichiestaDa()));
			dto.setDataInserimentoRichiestaA(EPaywsoFacadeAdapter.toDate(type.getDataInserimentoRichiestaA()));
			dto.setDataInvioAlDestinatarioDa(EPaywsoFacadeAdapter.toDate(type.getDataInvioAlDestinatarioDa()));
			dto.setDataInvioAlDestinatarioA(EPaywsoFacadeAdapter.toDate(type.getDataInvioAlDestinatarioA()));
		}

		return dto;
	}

	protected CriterioOrdinamentoDto<ColumnNameRichiestaEnum> toCriterioOrdinamentoRichiestaDto(CriterioOrdinamentoRichiestaType type) {
		CriterioOrdinamentoDto<ColumnNameRichiestaEnum> dto = null;

		if (type != null) {
			ColumnNameRichiestaEnum columnNameEnum = EPaywsoFacadeAdapter.toColumnNameRichiestaEnum(type.getColumnNameRichiesta());
			SortTypeEnum sortTypeEnum = EPaywsoFacadeAdapter.toSortTypeEnum(type.getSortType());
			dto = new CriterioOrdinamentoDto<ColumnNameRichiestaEnum>(columnNameEnum, sortTypeEnum);
		}

		return dto;
	}

	protected List<CriterioOrdinamentoDto<ColumnNameRichiestaEnum>> toCriterioOrdinamentoDtoList(CriterioOrdinamentoRichiestaTypeList typeList) {
		List<CriterioOrdinamentoDto<ColumnNameRichiestaEnum>> dtoList = null;

		if (typeList != null) {
			dtoList = new ArrayList<CriterioOrdinamentoDto<ColumnNameRichiestaEnum>>();
			for (CriterioOrdinamentoRichiestaType type : typeList.getCriterioOrdinamentoRichiesta()) {
				dtoList.add(toCriterioOrdinamentoRichiestaDto(type));
			}
		}

		return dtoList;
	}

	protected PaginazioneDto toPaginazioneDto(PaginazioneType type) {
		PaginazioneDto dto = null;

		if (type != null) {
			dto = new PaginazioneDto(type.getNumeroDiPagina(), type.getNumeroRighePerPagina());
		}

		return dto;
	}

	protected RichiestaLightTypeList toRichiestaLightTypeList(List<RichiestaLightDto> dtoList) throws FacadeException {
		RichiestaLightTypeList typeList = null;

		if (dtoList != null) {
			typeList = new RichiestaLightTypeList();
			for (RichiestaLightDto dto : dtoList) {
				typeList.getRichiesta().add(toRichiestaLightType(dto));
			}
		}

		return typeList;
	}
	
	protected List<RicercaStatoAggregatoDto> toRicercaStatoAggregatoDto(List<RicercaStatoAggregatoWsoType> typeList) {
		List<RicercaStatoAggregatoDto> dtoList = null;
		
		if (typeList != null) {
			dtoList = new LinkedList<RicercaStatoAggregatoDto>();
			for (RicercaStatoAggregatoWsoType temp: typeList)
			{
				if (temp != null) {
					RicercaStatoAggregatoDto dto = new RicercaStatoAggregatoDto();
					dto.setIdFlusso(temp.getIdFlusso());
					dto.setIdMessaggio(temp.getIdMessaggio());
					dto.setCodiceFiscaleEnte(temp.getCodiceFiscaleEnte());
//					dto.setIdTipoRichiesta(temp.getIdTipoRichiesta().intValue());
					dtoList.add(dto);
				}
			}
		}

		return dtoList;
	}
	
	protected EsitoRicercaStatiAggregatiWsoListTypeList toEsitoStatoAggregatoType(List<EsitoStatoAggregatoDto> dtoList) {
		EsitoRicercaStatiAggregatiWsoListTypeList typeList = null;
		
		if (dtoList != null) {
			typeList = new EsitoRicercaStatiAggregatiWsoListTypeList();
			for (EsitoStatoAggregatoDto temp: dtoList)
			{
				if (temp != null) {
					EsitoRicercaStatiAggregatiWsoType dto = new EsitoRicercaStatiAggregatiWsoType();
					dto.setIdFlusso(temp.getIdFlusso());
					dto.setCodFiscaleEnte(temp.getCodFiscEnte());
					
					dto.setStatoFlusso(temp.getStatoFlusso());
					dto.setDatoAggiuntivoCodEsito(temp.getCodDatoAggiuntivo());
					dto.setDatoAggiuntivoDescEsito(temp.getDesDatoAggiuntivo());
					
					typeList.getEsitoRicercaStatiAggregatiWso().add(dto);
					
//					
				}
			}
		}

		return typeList;
	}


	

}
