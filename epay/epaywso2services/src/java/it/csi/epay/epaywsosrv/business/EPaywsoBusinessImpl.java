/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.business;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import it.csi.epay.epaywsosrv.dto.AppDto;
import it.csi.epay.epaywsosrv.dto.ApplicationEsito;
import it.csi.epay.epaywsosrv.dto.CodVersDto;
import it.csi.epay.epaywsosrv.dto.ConfigAppDto;
import it.csi.epay.epaywsosrv.dto.CriterioOrdinamentoDto;
import it.csi.epay.epaywsosrv.dto.EnteDto;
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
import it.csi.epay.epaywsosrv.enumeration.StatoRichiestaEnum;
import it.csi.epay.epaywsosrv.enumeration.TipoRichiestaEnum;
import it.csi.epay.epaywsosrv.exception.BusinessException;
import it.csi.epay.epaywsosrv.exception.PersistenceException;
import it.csi.epay.epaywsosrv.persistence.dad.EPaywsoDad;
import it.csi.epay.epaywsosrv.util.Costanti;
import it.csi.epay.epaywsosrv.util.LogAndWatch;

@Stateless
public class EPaywsoBusinessImpl extends EPaywsoBusinessBase implements EPaywsoBusiness {
	static private final String CLASSNAME = EPaywsoBusinessImpl.class.getSimpleName();

	@Inject
	private EPaywsoDad dad;

	@Override
	public EnteDto getEnte(String codFiscale, boolean throwsExceptionIfNotFound) throws BusinessException {
		String methodName = "getEnte";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("codFiscale", codFiscale);
		lw.addParam("throwsExceptionIfNotFound", new Boolean(throwsExceptionIfNotFound));

		EnteDto resDto = null;

		try {
			lw.start();

			Timestamp timestampNow = getTimestampNow();

			resDto = dad.findEnteByCodFiscale(codFiscale, timestampNow);
			if (resDto == null || resDto.getId() == 0) {
				// nel caso l'ente non sia ancora censito o non sia abilitato, si puo decidere di restituire una eccezione oppure l'ente speciale "non censito"
				if (throwsExceptionIfNotFound) {
					throw new BusinessException(IssueEnum.COD_FISCALE_ENTE_NON_CENSITO_O_NON_VALIDO_1ARG, codFiscale);
				} else {
					resDto = dad.findEnteById(0, timestampNow);
				}
			}
		} catch (Throwable e) {
			handleBusinessException(e);

		} finally {
			lw.addResult("result", resDto);
			lw.stop();
		}

		return resDto;
	}

	@Override
	//@formatter:off
	/*
	 * N.B. La ricerca "standard" della configuraziene applicativo (cioe com'era prima dell'introduzione dei flusso rendicontazione)
	 *      era indipendente dal tipo di richiesta, infatti non c'era tra i parametri del servizio ricercaConfigurazioneApplicativo,
	 *      pertanto al business vengono forniti tutti i tipi di richiesta, a eccezione di: TRASMETTI_FLUSSO_RENDICONTAZIONE.
	 */
	public ConfigAppDto getConfigApp(String codFiscaleEnte, String codVersamento, TipoRichiestaEnum tipoRichiesta) throws BusinessException {
		String methodName = "getConfigApp";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("codFiscaleEnte", codFiscaleEnte);
		lw.addParam("codVersamento", codVersamento);
		lw.addParam("tipoRichiesta", tipoRichiesta);

		List<ConfigAppDto> resDto = null;

		try {
			lw.start();

			// invova il business generico
			resDto = getConfigAppList(codFiscaleEnte, Arrays.asList(codVersamento), tipoRichiesta);
			
			if (resDto.size() > 1) {
				throw new BusinessException(IssueEnum.CONFIG_APP_NON_UNIVOCA_2ARG, codFiscaleEnte, codVersamento);
			} 
			
		} finally {
			lw.addResult("result", resDto);
			lw.stop();
		}

		return resDto.get(0);
	}
	//@formatter:on

	@Override
	public List<ConfigAppDto> getConfigAppList(String codFiscaleEnte, List<String> codVersamentoList, TipoRichiestaEnum tipoRichiesta) throws BusinessException {
		String methodName = "getConfigAppList";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("codFiscaleEnte", codFiscaleEnte);
		lw.addParam("codVersamentoList", codVersamentoList);
		lw.addParam("tipoRichiesta", tipoRichiesta);

		List<ConfigAppDto> resList = new ArrayList<ConfigAppDto>();

		try {
			lw.start();

			Timestamp timestampNow = getTimestampNow();

			// verifica la validita del codice fiscale ente
			EnteDto enteDto = null;
			try {
				enteDto = dad.findEnteByCodFiscale(codFiscaleEnte, timestampNow);
				if (enteDto == null || enteDto.getId() == 0) {
					throw new BusinessException(IssueEnum.COD_FISCALE_ENTE_NON_CENSITO_O_NON_VALIDO_1ARG, codFiscaleEnte);
				}
			} catch (PersistenceException e) {
				if (e.getErrorCod().equals(IssueEnum.DB_MORE_THAN_ONE_RESULT_3ARGS.getCod())) {
					throw new BusinessException(IssueEnum.COD_FISCALE_ENTE_NON_UNIVOCO_1ARG, codFiscaleEnte);
				}
				throw e;
			}

			// verifica la validita degli applicativi associati all'ente
			List<AppDto> appDtoList = dad.findAllAppByIdEnte(enteDto.getId(), timestampNow);
			if (appDtoList.isEmpty()) {
				throw new BusinessException(IssueEnum.APPLICATIVO_NON_CENSITO_O_NON_VALIDO_1ARG, codFiscaleEnte);
			}

			// verifica la validita dei codici versamento
			List<CodVersDto> codVersDtoList = new ArrayList<CodVersDto>();
			for (String codVersamento : codVersamentoList) {
				List<CodVersDto> codVersDtoListByCod = dad.findAllCodVersByCod(codVersamento, timestampNow);
				if (codVersDtoListByCod.isEmpty()) {
					throw new BusinessException(IssueEnum.COD_VERSAMENTO_NON_CENSITO_O_NON_VALIDO_1ARG, codVersamento);
				}
				codVersDtoList.addAll(codVersDtoListByCod);
			}

			resList = dad.findAllConfigAppBy(codFiscaleEnte, codVersamentoList, tipoRichiesta, timestampNow);
			if (resList.size() == 0) {
				throw new BusinessException(IssueEnum.CONFIG_APP_NON_CENSITA_O_NON_VALIDA_2ARG, codFiscaleEnte, codVersamentoList.toString());
			}
		} catch (Throwable e) {
			handleBusinessException(e);

		} finally {
			lw.addResult("result", resList);
			lw.stop();
		}

		return resList;
	}

	@Override
	public RichiestaDto getRichiesta(String messageUUID) throws BusinessException {
		String methodName = "getRichiesta";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("messageUUID", messageUUID);

		RichiestaDto resDto = null;

		try {
			lw.start();

			// ottiene la richiesta
			resDto = dad.findRichiestaByMessageUUID(messageUUID);
			if (resDto == null) {
				throw new BusinessException(IssueEnum.DB_NO_DATA_FOUND_3ARGS, "richiesta", "messageUUID", messageUUID);
			}

			// ottiene gli eventuali esiti di invio
			resDto.setEsitoInvioDtoList(dad.findAllEsitoInvioByIdRichiesta(resDto.getId()));

		} catch (Throwable e) {
			handleBusinessException(e);

		} finally {
			lw.addResult("result", resDto);
			lw.stop();
		}

		return resDto;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Long insertRichiesta(RichiestaBaseDto dto) throws BusinessException {
		String methodName = "insertRichiesta";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("dto", dto);

		Long id = null;

		try {
			lw.start();

			id = dad.insertRichiesta(dto);

		} catch (PersistenceException e) {
			throw new BusinessException(IssueEnum.DB_ERROR_INSERT_RICHIESTA_1ARG.getCod(), e.getErrorDes(), e);

		} catch (Throwable e) {
			handleBusinessException(e);

		} finally {
			lw.addResult("result id", id);
			lw.stop();
		}

		return id;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void updateRichiesta(RichiestaDto dto) throws BusinessException {
		String methodName = "updateRichiesta";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("dto", dto);

		try {
			lw.start();

			if (!dad.updateRichiesta(dto)) {
				throw new BusinessException(IssueEnum.DB_ERROR_UPDATE_RICHIESTA_1ARG, "- messageUUID: " + dto.getMessageUUID());
			}
		} catch (Throwable e) {
			handleBusinessException(e);

		} finally {
			lw.stop();
		}
	}
	
	@Override
    public void updateRichiestaLight(RichiestaDto dto) throws BusinessException {
        String methodName = "updateRichiesta";
        LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
        lw.addParam("dto", dto);

        try {
            lw.start();

            if (!dad.updateRichiestaLight(dto)) {
                throw new BusinessException(IssueEnum.DB_ERROR_UPDATE_RICHIESTA_1ARG, "- messageUUID: " + dto.getMessageUUID());
            }
        } catch (Throwable e) {
            handleBusinessException(e);

        } finally {
            lw.stop();
        }
    }

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void updateRichiestaESingoloEsitoInvio(RichiestaDto richiestaDto, EsitoInvioDto esitoInvioDto) throws BusinessException {
		String methodName = "updateRichiestaESingoloEsitoInvio";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("richiestaDto", richiestaDto);
		lw.addParam("esitoInvioDto", esitoInvioDto);

		try {
			lw.start();

			updateRichiesta(richiestaDto);

			// se id applicativo non presente, si assume che non ci sia da aggiornare l'esito esterno (esiti invio) ma solo quello interno (nella richiesta)
			if (esitoInvioDto.getIdApplicativo() != null) {
				saveEsitoInvio(richiestaDto.getMessageUUID(), esitoInvioDto);
			}

		} catch (Throwable e) {
			handleBusinessException(e);

		} finally {
			lw.stop();
		}
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void saveEsitoInvio(String messageUUID, EsitoInvioDto dto) throws BusinessException {
		String methodName = "saveEsitoInvio";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("messageUUID", messageUUID);
		lw.addParam("dto", dto);

		try {
			lw.start();

			// verifica la validita del messageUUID
			Long idRichiesta = dad.findIdRichiestaByMessageUUID(messageUUID);
			if (idRichiesta == null) {
				throw new BusinessException(IssueEnum.DB_NO_DATA_FOUND_3ARGS, "richiesta", "messageUUID", messageUUID);
			}

			// verifica la validita dell'idApp
			Integer idApplicativo = dto.getIdApplicativo();
			if (!dad.existsAppById(idApplicativo, getTimestampNow())) {
				throw new BusinessException(IssueEnum.DB_NO_DATA_FOUND_3ARGS, "applicativo", "idApplicativo", "" + idApplicativo);
			}

			// inserisce o aggiorna l'esito invio
			if (!dad.saveEsitoInvio(idRichiesta, dto)) {
				throw new BusinessException(IssueEnum.DB_ERROR_SAVE_ESITO_INVIO_1ARG, "- messageUUID: " + messageUUID + ", idApp:" + idApplicativo);
			}
		} catch (Throwable e) {
			handleBusinessException(e);

		} finally {
			lw.stop();
		}
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void aggiornaEsitoComplessivo(String messageUUID) throws BusinessException {
		String methodName = "aggiornaEsitoComplessivo";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("messageUUID", messageUUID);

		try {
			lw.start();

			// ottiene l'id della richiesta
			Long idRichiesta = dad.findIdRichiestaByMessageUUID(messageUUID);
			List<EsitoInvioDto> esitoInvioDtoList = dad.findAllEsitoInvioByIdRichiesta(idRichiesta);

			// determina l'esito complessivo come quello al momento peggiore tra gli esiti invio 
			int codEsitoMax = 0;
			EsitoRichiestaDto esitoMaxDto = new EsitoRichiestaDto("000", "OK", "OK");
			//
			for (EsitoInvioDto esitoInvioDto : esitoInvioDtoList) {
				int codEsito = Integer.parseInt(esitoInvioDto.getEsitoInvioRichiestaDto().getCod());
				if (codEsito > codEsitoMax) {
					codEsitoMax = codEsito;
					esitoMaxDto = esitoInvioDto.getEsitoInvioRichiestaDto();
				}
			}

			// registra lo stato complessivo e l'esito peggiore
			RichiestaDto richiestaDto = new RichiestaDto(idRichiesta);
			richiestaDto.setMessageUUID(messageUUID);
			richiestaDto.setEsitoRichiestaDto(esitoMaxDto);
			dad.updateRichiesta(richiestaDto);

		} catch (Throwable e) {
			handleBusinessException(e);

		} finally {
			lw.stop();
		}
	}

	@Override
	public Long countRichieste(RichiestaLightFilterDto filter) throws BusinessException {
		String methodName = "countRichieste";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("filter", filter);

		Long num = null;

		try {
			lw.start();

			num = dad.countAllRichiestaByFilter(filter);

		} catch (Throwable e) {
			handleBusinessException(e);

		} finally {
			lw.addResult("result", num);
			lw.stop();
		}

		return num;
	}
	
	
	@Override
    public boolean existRichieste(RichiestaLightFilterDto filter) throws BusinessException {
        String methodName = "countRichieste";
        LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
        lw.addParam("filter", filter);

        boolean  exist = false;

        try {
            lw.start();

            exist=   dad.existRichiestaByFilter(filter);

        } catch (Throwable e) {
            handleBusinessException(e);

        } finally {
            lw.addResult("result", exist);
            lw.stop();
        }

        return exist;
    }
    
	

	@Override
	//@formatter:off
	public List<RichiestaLightDto> getRichiestaLightList(RichiestaLightFilterDto filter, List<CriterioOrdinamentoDto<ColumnNameRichiestaEnum>> criList, PaginazioneDto pag)
	throws BusinessException
	//@formatter:on
	{
		String methodName = "getRichiestaLightList";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("filter", filter);
		lw.addParam("criList", criList);
		lw.addParam("pag", pag);

		List<RichiestaLightDto> resList = null;

		try {
			lw.start();

			resList = dad.findAllRichiestaLightByFilter(filter, criList, pag);

		} catch (Throwable e) {
			handleBusinessException(e);

		} finally {
			lw.addResult("result", resList);
			lw.stop();
		}

		return resList;
	}

	@Override
	public ConfigAppDto determinaProssimoEndpoint(String messageUUID, List<String> codVersamentoList, int tolleranzaSec) throws BusinessException {
		String methodName = "determinaProssimoEndpoint";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("messageUUID", messageUUID);

		ConfigAppDto resDto = null;
		try {
//			RichiestaDto richiesta = dad.findRichiestaByMessageUUID(messageUUID);
		    RichiestaLightDto richiesta= dad.findRichiestaByMessageUUIDLight(messageUUID);

			if (richiesta != null) {
//				richiesta.setEsitoInvioDtoList(dad.findAllEsitoInvioByIdRichiesta(richiesta.getId()));
				List<EsitoInvioDto> esitiInvioDto = dad.findAllEsitoInvioByIdRichiesta(richiesta.getId());
//
				List<ConfigAppDto> configurazioni = getConfigAppList(richiesta.getCodFiscaleEnte(), codVersamentoList, richiesta.getTipoRichiestaEnum());

//				List<ApplicationEsito> configurazioniSelezionate = abbinaConfigurazioniEdEsiti(configurazioni, richiesta.getEsitoInvioDtoList(), tolleranzaSec);
				List<ApplicationEsito> configurazioniSelezionate = abbinaConfigurazioniEdEsiti(configurazioni, esitiInvioDto, tolleranzaSec);

				if (configurazioniSelezionate.size() > 0) {
					resDto = configurazioniSelezionate.get(0).getConfig();
				} else {
					throw new BusinessException(IssueEnum.NO_ALTRI_ENDPOINT);
				}
			} else {
				throw new BusinessException(IssueEnum.DB_NO_DATA_FOUND_3ARGS, "richiesta", "messageUUID", messageUUID);
			}

		} catch (Throwable e) {
			handleBusinessException(e);

		} finally {
			lw.addResult("result", resDto);
			lw.stop();
		}

		return resDto;
	}
	
	@Override
	public List<EsitoStatoAggregatoDto> ricercaStatoAggregato(List<RicercaStatoAggregatoDto> listRicerca, Integer idTipoRichiesta) throws BusinessException {
		String methodName = "ricercaStatoAggregato";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);

		List<EsitoStatoAggregatoDto> listResult = new LinkedList<EsitoStatoAggregatoDto>(); 
		//		lw.addParam("idFlusso", idFlusso);
		//		lw.addParam("codFiscaleEnte", codFiscaleEnte);
		Map<String, String> idFlussiIdMessaggio = new HashMap<String, String>();
		List<String> idFlussiEnte = new LinkedList<String>();
		for (RicercaStatoAggregatoDto temp : listRicerca)
		{
//			String flussoEnte= temp.getIdFlusso()+temp.getCodiceFiscaleEnte();
			idFlussiEnte.add(temp.getIdFlusso()+temp.getCodiceFiscaleEnte());
			if (!StringUtils.isEmpty(temp.getIdMessaggio()))
			{
				idFlussiEnte.add(temp.getIdMessaggio()+temp.getCodiceFiscaleEnte());
				idFlussiIdMessaggio.put(temp.getIdMessaggio(), temp.getIdFlusso());
			}
			
		}
		try {
			List<RichiestaDto> lisRichiesteDto= dad.findAllRichiesteByMessaggioEnte(idFlussiEnte);
			String lastIdFlusso="";
			for (RichiestaDto tmp: lisRichiesteDto)
			{
				EsitoStatoAggregatoDto resDto= new EsitoStatoAggregatoDto();
				if (!tmp.getIdMessaggio().equals(lastIdFlusso))
				{
					resDto.setIdFlusso(StringUtils.isEmpty(idFlussiIdMessaggio.get(tmp.getIdMessaggio()))? tmp.getIdMessaggio(): idFlussiIdMessaggio.get(tmp.getIdMessaggio()));
					resDto.setCodFiscEnte(tmp.getCodFiscaleEnte());
					resDto.setCodEsito(IssueEnum.OK.getCod());
					resDto.setCodDatoAggiuntivo(tmp.getEsitoRichiestaDto().getCod());
					resDto.setDesDatoAggiuntivo(tmp.getEsitoRichiestaDto().getDes());
					
					if (StatoRichiestaEnum.ELABORATA.getId() == tmp.getStatoRichiestaEnum().getId() && 
							Costanti.COD_RESPONSE_OK.equals(tmp.getEsitoRichiestaDto().getCod()) )
					{
						
						resDto.setStatoFlusso(Costanti.STATO_FLUSSO_INVIATO);
						
//						if (!Costanti.RESPONSE_OK.equals(tmp.getEsitoRichiestaDto().getDet())	)
//						{
//							resDto.setDesDatoAggiuntivo(tmp.getEsitoRichiestaDto().getDet());
//						}
					}
					else
					{
						resDto.setStatoFlusso(Costanti.STATO_FLUSSO_NON_INVIATO);
						
						
					}
					
					
					listResult.add(resDto);
				}
				lastIdFlusso= tmp.getIdMessaggio();
			}

		} catch (Throwable e) {
			handleBusinessException(e);

		} finally {

			lw.stop();
		}

		return listResult;
	}


	//@formatter:off
	private List<ApplicationEsito> abbinaConfigurazioniEdEsiti(List<ConfigAppDto> configurazioni, List<EsitoInvioDto> esitoInvioList, int toleranceSecs) {
		boolean add = false;
		List<ApplicationEsito> applications = new ArrayList<ApplicationEsito>();
		
		Calendar tolerance = Calendar.getInstance();
		tolerance.add(Calendar.SECOND, -toleranceSecs);
		
		for (ConfigAppDto config : configurazioni) {
			ApplicationEsito application = new ApplicationEsito();
			application.setConfig(config);
			add = true;
			for (EsitoInvioDto esitoInvio : esitoInvioList) {
				if (ObjectUtils.compare ( config.getAppDto().getId(), esitoInvio.getIdApplicativo() ) == 0) {
					application.setEsito(esitoInvio);
					add = (esitoInvio.getEsitoInvioRichiestaDto().getCod() != null
							&& Integer.parseInt(esitoInvio.getEsitoInvioRichiestaDto().getCod()) >= 100
							&& esitoInvio.getDataEsitoInvio().before(tolerance.getTime()))
							|| esitoInvio.getEsitoInvioRichiestaDto().getCod() == null;
					break;
				}
			}
			if (add)
				applications.add(application);
		}
		Collections.sort(applications);

		return applications;
	}
	//@formatter:on
}
