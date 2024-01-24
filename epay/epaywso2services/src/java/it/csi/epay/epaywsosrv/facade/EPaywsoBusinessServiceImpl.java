/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.facade;

import java.math.BigInteger;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jws.HandlerChain;
import javax.jws.WebService;

import org.apache.commons.lang3.StringUtils;

import it.csi.epay.epaywsosrv.business.EPaywsoBusiness;
import it.csi.epay.epaywsosrv.dto.ConfigAppDto;
import it.csi.epay.epaywsosrv.dto.CriterioOrdinamentoDto;
import it.csi.epay.epaywsosrv.dto.EnteDto;
import it.csi.epay.epaywsosrv.dto.EsitoInvioDto;
import it.csi.epay.epaywsosrv.dto.EsitoStatoAggregatoDto;
import it.csi.epay.epaywsosrv.dto.PaginazioneDto;
import it.csi.epay.epaywsosrv.dto.RicercaStatoAggregatoDto;
import it.csi.epay.epaywsosrv.dto.RichiestaBaseDto;
import it.csi.epay.epaywsosrv.dto.RichiestaDto;
import it.csi.epay.epaywsosrv.dto.RichiestaLightDto;
import it.csi.epay.epaywsosrv.dto.RichiestaLightFilterDto;
import it.csi.epay.epaywsosrv.enumeration.ColumnNameRichiestaEnum;
import it.csi.epay.epaywsosrv.enumeration.IssueEnum;
import it.csi.epay.epaywsosrv.exception.FacadeException;
import it.csi.epay.epaywsosrv.facade.dto.common.ResponseType;
import it.csi.epay.epaywsosrv.facade.dto.integrationservice.AggiornaEsitoComplessivoRequestType;
import it.csi.epay.epaywsosrv.facade.dto.integrationservice.AggiornaEsitoInvioRequestType;
import it.csi.epay.epaywsosrv.facade.dto.integrationservice.AggiornaRichiestaESingoloEsitoInvioRequestType;
import it.csi.epay.epaywsosrv.facade.dto.integrationservice.AggiornaRichiestaRequestType;
import it.csi.epay.epaywsosrv.facade.dto.integrationservice.ContaNumeroRichiesteSelezionateRequestType;
import it.csi.epay.epaywsosrv.facade.dto.integrationservice.ContaNumeroRichiesteSelezionateResponseType;
import it.csi.epay.epaywsosrv.facade.dto.integrationservice.DeterminaProssimoEndpointRequestType;
import it.csi.epay.epaywsosrv.facade.dto.integrationservice.DeterminaProssimoEndpointResponseType;
import it.csi.epay.epaywsosrv.facade.dto.integrationservice.InserisciNuovaRichiestaRequestType;
import it.csi.epay.epaywsosrv.facade.dto.integrationservice.InserisciNuovaRichiestaResponseType;
import it.csi.epay.epaywsosrv.facade.dto.integrationservice.LeggiRichiestaRequestType;
import it.csi.epay.epaywsosrv.facade.dto.integrationservice.LeggiRichiestaResponseType;
import it.csi.epay.epaywsosrv.facade.dto.integrationservice.RicercaConfigurazioneApplicativoRequestType;
import it.csi.epay.epaywsosrv.facade.dto.integrationservice.RicercaConfigurazioneApplicativoResponseType;
import it.csi.epay.epaywsosrv.facade.dto.integrationservice.RicercaEnteRequestType;
import it.csi.epay.epaywsosrv.facade.dto.integrationservice.RicercaEnteResponseType;
import it.csi.epay.epaywsosrv.facade.dto.integrationservice.RicercaListaConfigurazioniApplicativiRequestType;
import it.csi.epay.epaywsosrv.facade.dto.integrationservice.RicercaListaConfigurazioniApplicativiResponseType;
import it.csi.epay.epaywsosrv.facade.dto.integrationservice.RicercaRichiesteRequestType;
import it.csi.epay.epaywsosrv.facade.dto.integrationservice.RicercaRichiesteResponseType;
import it.csi.epay.epaywsosrv.facade.dto.integrationservice.RicercaStatiAggregatiWsoRequestType;
import it.csi.epay.epaywsosrv.facade.dto.integrationservice.RicercaStatiAggregatiWsoResponseType;
import it.csi.epay.epaywsosrv.facade.dto.integrationservice.RicercaStatoAggregatoWsoType;
import it.csi.epay.epaywsosrv.facade.service.EPaywsoBusinessService;
import it.csi.epay.epaywsosrv.util.LogAndWatch;

@Stateless
@WebService(name = "EPaywsoBusinessService", portName = "EPaywsoBusinessServiceSOAP", targetNamespace = "http://www.csi.it/epay/epaywso/business")
@HandlerChain ( file = "/META-INF/handler-chain.xml" )
@TransactionManagement(TransactionManagementType.CONTAINER)
public class EPaywsoBusinessServiceImpl extends EPaywsoFacadeBase implements EPaywsoBusinessService {
	static private final String CLASSNAME = EPaywsoBusinessServiceImpl.class.getSimpleName();

	@EJB
	private EPaywsoBusiness business;

	@Override
	public RicercaEnteResponseType ricercaEnte(RicercaEnteRequestType requestType) {
		String methodName = "ricercaEnte";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);

		RicercaEnteResponseType responseType = new RicercaEnteResponseType();

		try {
			lw.start();

			// verifica
			EPaywsoFacadeValidator.notNull("RicercaEnteRequest", requestType);
			EPaywsoFacadeValidator.mandatoryField("CodiceFiscaleEnte", requestType.getCodiceFiscaleEnte());

			// business
			EnteDto dto = business.getEnte(requestType.getCodiceFiscaleEnte(), true);

			// response ok
			responseType.setResult(buildResultOK());
			responseType.setEnte(toEnteType(dto));

		} catch (Throwable e) {
			// response ko
			responseType.setResult(handleFacadeException(e));

		} finally {
			lw.stop();
		}

		return responseType;
	}

	@Override
	public RicercaConfigurazioneApplicativoResponseType ricercaConfigurazioneApplicativo(RicercaConfigurazioneApplicativoRequestType requestType) {
		String methodName = "ricercaConfigurazioneApplicativo";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);

		RicercaConfigurazioneApplicativoResponseType responseType = new RicercaConfigurazioneApplicativoResponseType();

		try {
			lw.start();

			// verifica
			EPaywsoFacadeValidator.notNull("RicercaConfigurazioneApplicativoRequestType", requestType);
			EPaywsoFacadeValidator.mandatoryField("CodiceFiscaleEnteCreditore", requestType.getCodiceFiscaleEnte());
			EPaywsoFacadeValidator.mandatoryField("CodiceVersamento", requestType.getCodiceVersamento());
			EPaywsoFacadeValidator.mandatoryField("TipoRichiesta", requestType.getTipoRichiesta());

			// business
			ConfigAppDto dto = business.getConfigApp(requestType.getCodiceFiscaleEnte(), requestType.getCodiceVersamento(), EPaywsoFacadeAdapter.toTipoRichiestaEnum(requestType.getTipoRichiesta()));

			// response ok
			responseType.setResult(buildResultOK());
			responseType.setConfigurazioneApplicativo(toConfigurazioneApplicativoType(dto));

		} catch (Throwable e) {
			// response ko
			responseType.setResult(handleFacadeException(e));

		} finally {
			lw.stop();
		}

		return responseType;
	}

	@Override
	//@formatter:off
	public RicercaListaConfigurazioniApplicativiResponseType ricercaListaConfigurazioniApplicativi(RicercaListaConfigurazioniApplicativiRequestType requestType) {
	//@formatter:on
		String methodName = "ricercaConfigurazioniApplicativiPerFlussoRendicontazione";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);

		RicercaListaConfigurazioniApplicativiResponseType responseType = new RicercaListaConfigurazioniApplicativiResponseType();

		try {
			lw.start();

			// verifica
			EPaywsoFacadeValidator.notNull("RicercaListaConfigurazioniApplicativiRequestType", requestType);
			EPaywsoFacadeValidator.mandatoryField("CodiceFiscaleEnteCreditore", requestType.getCodiceFiscaleEnte());
			EPaywsoFacadeValidator.mandatoryField("CodiciVersamento", requestType.getCodiciVersamento());
			EPaywsoFacadeValidator.mandatoryField("TipoRichiesta", requestType.getTipoRichiesta());

			// business
			List<String> codVersamentoList = toCodVersamentoList(requestType.getCodiciVersamento());
			List<ConfigAppDto> dtoList = business.getConfigAppList(requestType.getCodiceFiscaleEnte(), codVersamentoList, EPaywsoFacadeAdapter.toTipoRichiestaEnum(requestType.getTipoRichiesta()));

			// response ok
			responseType.setResult(buildResultOK());
			responseType.setConfigurazioniApplicativi(toConfigurazioneApplicativoTypeList(dtoList));

		} catch (Throwable e) {
			// response ko
			responseType.setResult(handleFacadeException(e));

		} finally {
			lw.stop();
		}

		return responseType;
	}

	@Override
	public LeggiRichiestaResponseType leggiRichiesta(LeggiRichiestaRequestType requestType) {
		String methodName = "leggiRichiesta";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);

		LeggiRichiestaResponseType responseType = new LeggiRichiestaResponseType();

		try {
			lw.start();

			// verifica
			EPaywsoFacadeValidator.notNull("LeggiRichiestaRequest", requestType);
			EPaywsoFacadeValidator.mandatoryField("MessageUUID", requestType.getMessageUUID());

			// business
			RichiestaDto dto = business.getRichiesta(requestType.getMessageUUID());

			// response ok
			responseType.setResult(buildResultOK());
			responseType.setRichiesta(toRichiestaType(dto));

		} catch (Throwable e) {
			// response ko
			responseType.setResult(handleFacadeException(e));

		} finally {
			lw.stop();
		}

		return responseType;
	}

	@Override
	public InserisciNuovaRichiestaResponseType inserisciNuovaRichiesta(InserisciNuovaRichiestaRequestType requestType) {
		String methodName = "inserisciNuovaRichiesta";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);

		InserisciNuovaRichiestaResponseType responseType = new InserisciNuovaRichiestaResponseType();

		try {
			lw.start();

			// verifica
			EPaywsoFacadeValidator.stringLength("CodiceFiscaleEnte", requestType.getCodiceFiscaleEnte(), 1, 35, true);
			EPaywsoFacadeValidator.tipoRichiesta("TipoRichiesta", requestType.getTipoRichiesta(), true);
			EPaywsoFacadeValidator.statoRichiesta("StatoRichiesta", requestType.getStatoRichiesta(), true);
			EPaywsoFacadeValidator.stringLength("MessageUUID", requestType.getMessageUUID(), 1, 50, true);
			EPaywsoFacadeValidator.notNull("ContenutoMessaggio", requestType.getContenutoMessaggio());
			EPaywsoFacadeValidator.isAlphanumeric("CodiceFiscaleEnte", requestType.getCodiceFiscaleEnte(), true);
//			validazione codice fiscale
			// verifica doppio messaggio Jira EPAY-7
			RichiestaLightFilterDto filter = new RichiestaLightFilterDto();
			filter.setTipoRichiestaEnum(EPaywsoFacadeAdapter.toTipoRichiestaEnum(requestType.getTipoRichiesta()));
			filter.setCodFiscaleEnte(requestType.getCodiceFiscaleEnte());
			if (requestType.getCodiceVersamento() != null) {
                filter.setCodVersamentoList(Arrays.asList(requestType.getCodiceVersamento()));
            }
			filter.setIdMessaggio(requestType.getIdMessaggio());
//			TODO da implementare exist
//			boolean duplicateMessageId = business.countRichieste(filter) > 0L;
			boolean duplicateMessageId = business.existRichieste ( filter );

			// business con normalizzazione dei codici ente non censiti
			String codFiscaleEnte = business.getEnte(requestType.getCodiceFiscaleEnte(), false).getCodFiscale();
			
			RichiestaBaseDto richiestaBaseDto = toRichiestaBaseDto(requestType, codFiscaleEnte);
			Long id = business.insertRichiesta(richiestaBaseDto);

			// response
			responseType.setIdRichiesta(id);
			if (duplicateMessageId) {
				String cod = IssueEnum.ID_MESSAGGIO_DUPLICATO_1ARG_WARNING.getCod();
				String des = MessageFormat.format(IssueEnum.ID_MESSAGGIO_DUPLICATO_1ARG_WARNING.getDes(), requestType.getIdMessaggio());
				responseType.setResult(buildResult(cod, des));

			} else {
                responseType.setResult(buildResultOK());
            }

		} catch (Throwable e) {
			// response ko
			responseType.setResult(handleFacadeException(e));

		} finally {
			lw.stop();
		}

		return responseType;
	}

	@Override
	public ResponseType aggiornaRichiesta(AggiornaRichiestaRequestType requestType) {
		String methodName = "aggiornaRichiesta";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);

		ResponseType responseType = new ResponseType();

		try {
			lw.start();

			// verifica
			EPaywsoFacadeValidator.notNull("AggiornaRichiestaRequest", requestType);
			EPaywsoFacadeValidator.mandatoryField("MessageUUID", requestType.getMessageUUID());
			EPaywsoFacadeValidator.statoRichiesta("StatoRichiesta", requestType.getStatoRichiesta(), false);

			// business con normalizzazione dei codici ente non censiti
			RichiestaDto dto = toRichiestaDto(requestType);
			business.updateRichiestaLight(dto);

			// response ok
			responseType.setResult(buildResultOK());

		} catch (Throwable e) {
			// response ko
			responseType.setResult(handleFacadeException(e));

		} finally {
			lw.stop();
		}

		return responseType;
	}

	@Override
	public ResponseType aggiornaEsitoInvio(AggiornaEsitoInvioRequestType requestType) {
		String methodName = "aggiornaEsitoInvio";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);

		ResponseType responseType = new ResponseType();

		try {
			lw.start();

			// verifica
			EPaywsoFacadeValidator.notNull("AggiornaEsitoInvioRequestType", requestType);
			EPaywsoFacadeValidator.mandatoryField("MessageUUID", requestType.getMessageUUID());
			EPaywsoFacadeValidator.mandatoryField("IdApplicativo", requestType.getIdApplicativo());
			EPaywsoFacadeValidator.mandatoryField("EsitoInvio", requestType.getMessageUUID());

			// business con normalizzazione dei codici ente non censiti
			EsitoInvioDto dto = toEsitoInvioDto(requestType.getIdApplicativo(), requestType.getEsitoInvio());
			business.saveEsitoInvio(requestType.getMessageUUID(), dto);

			// response ok
			responseType.setResult(buildResultOK());

		} catch (Throwable e) {
			// response ko
			responseType.setResult(handleFacadeException(e));

		} finally {
			lw.stop();
		}

		return responseType;
	}

	@Override
	public ResponseType aggiornaRichiestaESingoloEsitoInvio(AggiornaRichiestaESingoloEsitoInvioRequestType requestType) {
		String methodName = "aggiornaRichiestaESingoloEsitoInvio";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);

		ResponseType responseType = new ResponseType();

		try {
			lw.start();

			// verifica
			EPaywsoFacadeValidator.notNull("AggiornaRichiestaESingoloEsitoInvioRequest", requestType);
			EPaywsoFacadeValidator.mandatoryField("MessageUUID", requestType.getMessageUUID());
			EPaywsoFacadeValidator.statoRichiesta("StatoRichiesta", requestType.getStatoRichiesta(), false);

			// business con normalizzazione dei codici ente non censiti
			RichiestaDto richiestaDto = toRichiestaDto(requestType);
			EsitoInvioDto esitoInvioDto = toEsitoInvioDto(requestType);
			business.updateRichiestaESingoloEsitoInvio(richiestaDto, esitoInvioDto);

			// response ok
			responseType.setResult(buildResultOK());

		} catch (Throwable e) {
			// response ko
			responseType.setResult(handleFacadeException(e));

		} finally {
			lw.stop();
		}

		return responseType;
	}

	@Override
	public ResponseType aggiornaEsitoComplessivo(AggiornaEsitoComplessivoRequestType requestType) {
		String methodName = "aggiornaEsitoComplessivo";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);

		ResponseType responseType = new ResponseType();

		try {
			// verifica
			EPaywsoFacadeValidator.notNull("AggiornaEsitoComplessivoRequest", requestType);
			EPaywsoFacadeValidator.mandatoryField("MessageUUID", requestType.getMessageUUID());

			// business
			business.aggiornaEsitoComplessivo(requestType.getMessageUUID());

			// response ok
			responseType.setResult(buildResultOK());

		} catch (Throwable e) {
			// response ko
			responseType.setResult(handleFacadeException(e));

		} finally {
			lw.stop();
		}

		return responseType;
	}

	@Override
	public DeterminaProssimoEndpointResponseType determinaProssimoEndpoint(DeterminaProssimoEndpointRequestType requestType) {
		String methodName = "ricercaConfigurazioniApplicativiPerFlussoRendicontazione";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);

		DeterminaProssimoEndpointResponseType responseType = new DeterminaProssimoEndpointResponseType();
		try {
			lw.start();

			// verifica
			EPaywsoFacadeValidator.mandatoryField("MessageUUID", requestType.getMessageUUID());
			EPaywsoFacadeValidator.mandatoryField("CodiciVersamento", requestType.getCodiciVersamento());

			// business
			List<String> codVersamentoList = toCodVersamentoList(requestType.getCodiciVersamento());
			
			ConfigAppDto config = business.determinaProssimoEndpoint(requestType.getMessageUUID(), codVersamentoList, requestType.getTolleranzaSecondi());

			// response
			responseType.setResult(buildResultOK());
			responseType.setConfigurazioneApplicativo(toConfigurazioneApplicativoType(config));

		} catch (Throwable e) {
			// response ko
			responseType.setResult(handleFacadeException(e));
		} finally {
			lw.stop();
		}

		return responseType;
	}

	@Override
	public ContaNumeroRichiesteSelezionateResponseType contaNumeroRichiesteSelezionate(ContaNumeroRichiesteSelezionateRequestType requestType) {
		String methodName = "contaNumeroRichiesteSelezionate";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);

		ContaNumeroRichiesteSelezionateResponseType responseType = new ContaNumeroRichiesteSelezionateResponseType();

		try {
			// verifica
			EPaywsoFacadeValidator.notNull("ContaNumeroRichiesteSelezionateRequestType", requestType);
			EPaywsoFacadeValidator.filtroSelezione(requestType.getFiltroSelezione());

			// business
			RichiestaLightFilterDto filter = toFiltroSelezioneRichiesteDto(requestType.getFiltroSelezione());
			Long num = business.countRichieste(filter);

			// response ok
			responseType.setResult(buildResultOK());
			responseType.setNumeroRichiesteSelezionate(BigInteger.valueOf(num));

		} catch (Throwable e) {
			// response ko
			responseType.setResult(handleFacadeException(e));

		} finally {
			lw.stop();
		}

		return responseType;
	}

	@Override
	public RicercaRichiesteResponseType ricercaRichieste(RicercaRichiesteRequestType requestType) {
		String methodName = "ricercaRichieste";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);

		RicercaRichiesteResponseType responseType = new RicercaRichiesteResponseType();

		try {
			// verifica
			EPaywsoFacadeValidator.notNull("RicercaRichiesteRequestType", requestType);
			EPaywsoFacadeValidator.filtroSelezione(requestType.getFiltroSelezione());

			// business
			RichiestaLightFilterDto filter = toFiltroSelezioneRichiesteDto(requestType.getFiltroSelezione());
			List<CriterioOrdinamentoDto<ColumnNameRichiestaEnum>> criList = toCriterioOrdinamentoDtoList(requestType.getCriteriOrdinamento());
			PaginazioneDto pag = toPaginazioneDto(requestType.getPaginazione());
			List<RichiestaLightDto> dtoList = business.getRichiestaLightList(filter, criList, pag);

			// response ok
			responseType.setResult(buildResultOK());
			responseType.setRichiesteEstratte(toRichiestaLightTypeList(dtoList));

		} catch (Throwable e) {
			// response ko
			responseType.setResult(handleFacadeException(e));

		} finally {
			lw.stop();
		}

		return responseType;
	}
	
	@Override
	public RicercaStatiAggregatiWsoResponseType ricercaStatiAggregatiWso(RicercaStatiAggregatiWsoRequestType requestType) {

		String methodName = "ricercaStatiAggregatiWso";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		RicercaStatiAggregatiWsoResponseType responseType = new RicercaStatiAggregatiWsoResponseType();

		try {
			lw.start();
			EPaywsoFacadeValidator.notNull("RicercaStatiAggregatiWsoRequestType", requestType);
			EPaywsoFacadeValidator.notNull("EsitoRicercaStatiAggregatiWsoListTypeList", requestType.getRicercaStatoAggregatoWsoList());
			EPaywsoFacadeValidator.notNull("RicercaStatoAggregatoWsoTypeList", requestType.getRicercaStatoAggregatoWsoList().getRicercaStatoAggregatoWso());
//			EPaywsoFacadeValidator.notNull("idTipoRicerca", requestType.getIdTipoRichiesta());
			for (RicercaStatoAggregatoWsoType t: requestType.getRicercaStatoAggregatoWsoList().getRicercaStatoAggregatoWso())
			{
				EPaywsoFacadeValidator.mandatoryField("idFlusso", t.getIdFlusso());
				EPaywsoFacadeValidator.mandatoryField("codFiscEnte", t.getCodiceFiscaleEnte());
//				EPaywsoFacadeValidator.mandatoryField("idTipoRicerca", t.getIdTipoRichiesta());
			}
			List<RicercaStatoAggregatoDto> dtoList= toRicercaStatoAggregatoDto(requestType.getRicercaStatoAggregatoWsoList().getRicercaStatoAggregatoWso());
			List<EsitoStatoAggregatoDto> esito= business.ricercaStatoAggregato(dtoList, requestType.getIdTipoRichiesta().intValue());
			responseType.setEsitoRicercaStatiAggregatiWsoList(toEsitoStatoAggregatoType(esito));
			
			
		}
		catch (Throwable e) {
			
			responseType.setResult(handleFacadeException(e));
		} finally {
			lw.stop();
		}



		return responseType;
	}

}
