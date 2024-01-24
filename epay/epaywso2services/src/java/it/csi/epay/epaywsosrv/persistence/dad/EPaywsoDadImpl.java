/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.dad;

import static it.csi.epay.epaywsosrv.util.Util.SPECIAL_COD_VERSAMENTO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;

import it.csi.epay.epaywsosrv.dto.AppDto;
import it.csi.epay.epaywsosrv.dto.CodVersDto;
import it.csi.epay.epaywsosrv.dto.ConfigAppDto;
import it.csi.epay.epaywsosrv.dto.CriterioOrdinamentoDto;
import it.csi.epay.epaywsosrv.dto.EndpointAppDto;
import it.csi.epay.epaywsosrv.dto.EndpointDto;
import it.csi.epay.epaywsosrv.dto.EnteDto;
import it.csi.epay.epaywsosrv.dto.EsitoCallbackDto;
import it.csi.epay.epaywsosrv.dto.EsitoInvioDto;
import it.csi.epay.epaywsosrv.dto.EsitoRichiestaDto;
import it.csi.epay.epaywsosrv.dto.PaginazioneDto;
import it.csi.epay.epaywsosrv.dto.RichiestaBaseDto;
import it.csi.epay.epaywsosrv.dto.RichiestaDto;
import it.csi.epay.epaywsosrv.dto.RichiestaLightDto;
import it.csi.epay.epaywsosrv.dto.RichiestaLightFilterDto;
import it.csi.epay.epaywsosrv.enumeration.ColumnNameRichiestaEnum;
import it.csi.epay.epaywsosrv.enumeration.IssueEnum;
import it.csi.epay.epaywsosrv.enumeration.TipoRichiestaEnum;
import it.csi.epay.epaywsosrv.exception.PersistenceException;
import it.csi.epay.epaywsosrv.persistence.dao.interf.EPaywsoDEsitoDao;
import it.csi.epay.epaywsosrv.persistence.dao.interf.EPaywsoDStatoRichiestaDao;
import it.csi.epay.epaywsosrv.persistence.dao.interf.EPaywsoDTipoRichiestaDao;
import it.csi.epay.epaywsosrv.persistence.dao.interf.EPaywsoRAppRicEsitoInvioDao;
import it.csi.epay.epaywsosrv.persistence.dao.interf.EPaywsoRAppTiporicEpDao;
import it.csi.epay.epaywsosrv.persistence.dao.interf.EPaywsoTApplicativoDao;
import it.csi.epay.epaywsosrv.persistence.dao.interf.EPaywsoTCodiceVersamentoDao;
import it.csi.epay.epaywsosrv.persistence.dao.interf.EPaywsoTConfigApplicativoDao;
import it.csi.epay.epaywsosrv.persistence.dao.interf.EPaywsoTEndpointDao;
import it.csi.epay.epaywsosrv.persistence.dao.interf.EPaywsoTEnteDao;
import it.csi.epay.epaywsosrv.persistence.dao.interf.EPaywsoTEsitoInvioDao;
import it.csi.epay.epaywsosrv.persistence.dao.interf.EPaywsoTRichiestaDao;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoDEsito;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoDStatoRichiesta;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoDTipoRichiesta;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoRAppRicEsitoInvio;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoRAppRicEsitoInvioPK;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoRAppTiporicEp;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoRAppTiporicEpPK;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoTApplicativo;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoTCodiceVersamento;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoTConfigApplicativo;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoTEndpoint;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoTEnte;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoTEsitoInvio;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoTRichiesta;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoTRichiestaLight;
import it.csi.epay.epaywsosrv.util.LogAndWatch;

public class EPaywsoDadImpl extends EPaywsoDadBase implements EPaywsoDad {
	static private final String CLASSNAME = EPaywsoDadImpl.class.getSimpleName();

	@Inject
	private EPaywsoDEsitoDao ePaywsoDEsitoDao;

	@Inject
	private EPaywsoTEsitoInvioDao ePaywsoTEsitoInvioDao;

	@Inject
	private EPaywsoTEnteDao ePaywsoTEnteDao;

	@Inject
	EPaywsoTApplicativoDao ePaywsoTApplicativoDao;

	@Inject
	private EPaywsoTCodiceVersamentoDao ePaywsoTCodiceVersamentoDao;

	@Inject
	private EPaywsoTConfigApplicativoDao ePaywsoTConfigApplicativoDao;

	@Inject
	private EPaywsoRAppRicEsitoInvioDao ePaywsoRAppRicEsitoInvioDao;

	@Inject
	private EPaywsoRAppTiporicEpDao ePaywsoRAppTiporicEpDao;

	@Inject
	private EPaywsoTEndpointDao ePaywsoTEndpointDao;

	@Inject
	private EPaywsoTRichiestaDao ePaywsoTRichiestaDao;

	@Inject
	private EPaywsoDStatoRichiestaDao ePaywsoDStatoRichiestaDao;

	@Inject
	private EPaywsoDTipoRichiestaDao ePaywsoDTipoRichiestaDao;
	
	@PersistenceContext(unitName = "epaywso")
    protected EntityManager entityManager;

	@Override
	public void ping() throws PersistenceException {
		String methodName = "ping";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);

		try {
			lw.start();

			ePaywsoDEsitoDao.findOneByCod(IssueEnum.OK.getCod());

		} finally {
			lw.stop();
		}
	}

	@Override
	public boolean existsEnteById(Integer idEnte, Timestamp timestamp) throws PersistenceException {
		String methodName = "existsEnteById";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("id", idEnte);
		lw.addParam("timestamp", timestamp);

		boolean exists = false;

		try {
			lw.start();

			exists = (ePaywsoTEnteDao.findCodFiscaleById(idEnte, timestamp) != null);

		} finally {
			lw.stop();
		}

		return exists;
	}

	@Override
	public boolean existsEnteByCodFiscale(String codFiscaleEnte, Timestamp timestamp) throws PersistenceException {
		String methodName = "existsEnteByCodFiscale";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("codFiscaleEnte", codFiscaleEnte);
		lw.addParam("timestamp", timestamp);

		boolean exists = false;

		try {
			lw.start();

			exists = (ePaywsoTEnteDao.findIdByCodFiscale(codFiscaleEnte, timestamp) != null);

		} finally {
			lw.stop();
		}

		return exists;
	}

	@Override
	public EnteDto findEnteById(Integer idEnte, Timestamp timestamp) throws PersistenceException {
		String methodName = "findEnteById";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("idEnte", idEnte);
		lw.addParam("timestamp", timestamp);

		EnteDto dto = null;

		try {
			lw.start();

			EPaywsoTEnte entity = ePaywsoTEnteDao.findOneById(idEnte, timestamp);
			dto = toEnteDto(entity);

		} finally {
			lw.stop();
		}

		return dto;
	}

	@Override
	public EnteDto findEnteByCodFiscale(String codFiscaleEnte, Timestamp timestamp) throws PersistenceException {
		String methodName = "findEnteByCodFiscale";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("codFiscaleEnte", codFiscaleEnte);
		lw.addParam("timestamp", timestamp);

		EnteDto dto = null;

		try {
			lw.start();

			EPaywsoTEnte entity = ePaywsoTEnteDao.findOneByCodFiscale(codFiscaleEnte, timestamp);
			dto = toEnteDto(entity);

		} finally {
			lw.stop();
		}

		return dto;
	}

	@Override
	public boolean existsAppById(Integer idApp, Timestamp timestamp) throws PersistenceException {
		String methodName = "existsAppById";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("idApp", idApp);
		lw.addParam("timestamp", timestamp);

		boolean exists = false;

		try {
			lw.start();

			exists = (ePaywsoTApplicativoDao.findOneById(idApp, timestamp) != null);

		} finally {
			lw.stop();
		}

		return exists;
	}

	@Override
	public boolean existsAppByIdEnte(Integer idEnte, Timestamp timestamp) throws PersistenceException {
		String methodName = "existsAppByIdEnte";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("idEnte", idEnte);
		lw.addParam("timestamp", timestamp);

		boolean exists = false;

		try {
			lw.start();

			exists = (!ePaywsoTApplicativoDao.findAllByIdEnte(idEnte, timestamp).isEmpty());

		} finally {
			lw.stop();
		}

		return exists;
	}

	@Override
	public AppDto findAppById(Integer idApp, Timestamp timestamp) throws PersistenceException {
		String methodName = "findAppById";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("idApp", idApp);
		lw.addParam("timestamp", timestamp);

		AppDto dto = null;

		try {
			lw.start();

			EPaywsoTApplicativo entity = ePaywsoTApplicativoDao.findOneById(idApp, timestamp);
			dto = toAppDto(entity);

		} finally {
			lw.stop();
		}

		return dto;
	}

	@Override
	public List<AppDto> findAllAppByIdEnte(Integer idEnte, Timestamp timestamp) throws PersistenceException {
		String methodName = "findAllAppByIdEnte";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("idEnte", idEnte);
		lw.addParam("timestamp", timestamp);

		List<AppDto> dtoList = new ArrayList<AppDto>();

		try {
			lw.start();

			List<EPaywsoTApplicativo> entityList = ePaywsoTApplicativoDao.findAllByIdEnte(idEnte, timestamp);
			dtoList = toAppDtoList(entityList);

		} finally {
			lw.stop();
		}

		return dtoList;
	}

	@Override
	public boolean existsCodVerById(Integer idCodVers, Timestamp timestamp) throws PersistenceException {
		String methodName = "existsCodVerById";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("idCodVers", idCodVers);
		lw.addParam("timestamp", timestamp);

		boolean exists = false;

		try {
			lw.start();

			exists = (ePaywsoTCodiceVersamentoDao.findCodById(idCodVers, timestamp) != null);

		} finally {
			lw.stop();
		}

		return exists;
	}

	@Override
	public boolean existsCodVerByCod(String codVers, Timestamp timestamp) throws PersistenceException {
		String methodName = "existsCodVerByCod";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("codVers", codVers);
		lw.addParam("timestamp", timestamp);

		boolean exists = false;

		try {
			lw.start();

			exists = (!ePaywsoTCodiceVersamentoDao.findAllIdByCod(codVers, timestamp).isEmpty());

		} finally {
			lw.stop();
		}

		return exists;
	}

	@Override
	public CodVersDto findCodVersById(Integer idCodVers, Timestamp timestamp) throws PersistenceException {
		String methodName = "findCodVersById";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("idCodVers", idCodVers);
		lw.addParam("timestamp", timestamp);

		CodVersDto dto = null;

		try {
			lw.start();

			EPaywsoTCodiceVersamento entity = ePaywsoTCodiceVersamentoDao.findOneById(idCodVers, timestamp);
			dto = toCodVersDto(entity);

		} finally {
			lw.stop();
		}

		return dto;
	}

	@Override
	public List<CodVersDto> findAllCodVersByCod(String codVers, Timestamp timestamp) throws PersistenceException {
		String methodName = "findAllCodVersByCod";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("codVers", codVers);
		lw.addParam("timestamp", timestamp);

		List<CodVersDto> dtoList = new ArrayList<CodVersDto>();

		try {
			lw.start();

			List<EPaywsoTCodiceVersamento> entityList = ePaywsoTCodiceVersamentoDao.findAllByCod(codVers, timestamp);
			dtoList = toCodVersDtoList(entityList);

		} finally {
			lw.stop();
		}

		return dtoList;
	}

	@Override
	public List<CodVersDto> findAllSpecialCodVers(Timestamp timestamp) throws PersistenceException {
		return findAllCodVersByCod(SPECIAL_COD_VERSAMENTO, timestamp);
	}

	@Override
	public boolean existsEndpointById(Integer idEndpoint, Timestamp timestamp) throws PersistenceException {
		String methodName = "existsEndpointById";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("idEndpoint", idEndpoint);
		lw.addParam("timestamp", timestamp);

		boolean exists = false;

		try {
			lw.start();

			exists = (ePaywsoTEndpointDao.findOneById(idEndpoint, timestamp) != null);

		} finally {
			lw.stop();
		}

		return exists;
	}

	@Override
	public EndpointDto findEndpointById(Integer idEndpoint, Timestamp timestamp) throws PersistenceException {
		String methodName = "findEndpointById";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("idEndpoint", idEndpoint);
		lw.addParam("timestamp", timestamp);

		EndpointDto dto = null;

		try {
			lw.start();

			EPaywsoTEndpoint entity = ePaywsoTEndpointDao.findOneById(idEndpoint, timestamp);
			dto = toEndpointDto(entity);

		} finally {
			lw.stop();
		}

		return dto;
	}

	@Override
	public boolean existsEndpointAppByKey(TipoRichiestaEnum tipoRichiestaEnum, Integer idApp, Integer idEndpoint) throws PersistenceException {
		String methodName = "existsEndpointAppByKey";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("tipoRichiestaEnum", tipoRichiestaEnum);
		lw.addParam("idApp", idApp);
		lw.addParam("idEndpoint", idEndpoint);

		boolean exists = false;

		try {
			lw.start();

			EPaywsoRAppTiporicEpPK entityKey = new EPaywsoRAppTiporicEpPK(idApp, idEndpoint, tipoRichiestaEnum.getId());
			exists = (ePaywsoRAppTiporicEpDao.findOne(entityKey) != null);

		} finally {
			lw.stop();
		}

		return exists;
	}

	@Override
	public EndpointAppDto findEndpointAppByKey(TipoRichiestaEnum tipoRichiestaEnum, Integer idApp, Integer idEndpoint) throws PersistenceException {
		String methodName = "findEndpointAppByKey";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("tipoRichiestaEnum", tipoRichiestaEnum);
		lw.addParam("idApp", idApp);
		lw.addParam("idEndpoint", idEndpoint);

		EndpointAppDto dto;

		try {
			lw.start();

			EPaywsoRAppTiporicEpPK entityKey = new EPaywsoRAppTiporicEpPK(idApp, idEndpoint, tipoRichiestaEnum.getId());
			EPaywsoRAppTiporicEp entity = ePaywsoRAppTiporicEpDao.findOne(entityKey);
			dto = toEndpointAppDto(entity);

		} finally {
			lw.stop();
		}

		return dto;
	}

	@Override
	public List<EndpointAppDto> findAllEndpointAppByIdApp(Integer idApp, Timestamp timestamp) throws PersistenceException {
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, "findAllEndpointAppByIdApp");
		lw.addParam("idApp", idApp);
		lw.addParam("timestamp", timestamp);

		List<EndpointAppDto> dtoList = null;
		try {
			lw.start();

			List<EPaywsoRAppTiporicEp> entityList = ePaywsoRAppTiporicEpDao.findAllByIdApp(idApp, timestamp);
			dtoList = toEndpointAppDtoList(entityList);

		} finally {
			lw.stop();
		}

		return dtoList;
	}

	@Override
	public List<EndpointAppDto> findAllEndpointAppByTipoRichiesta(TipoRichiestaEnum tipoRichiestaEnum, Timestamp timestamp) throws PersistenceException {
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, "findAllEndpointAppByTipoRichiesta");
		lw.addParam("tipoRichiestaEnum", tipoRichiestaEnum);
		lw.addParam("timestamp", timestamp);

		List<EndpointAppDto> dtoList = null;
		try {
			lw.start();

			List<EPaywsoRAppTiporicEp> entityList = ePaywsoRAppTiporicEpDao.findAllByIdTipoRichiesta(tipoRichiestaEnum.getId(), timestamp);
			dtoList = toEndpointAppDtoList(entityList);

		} finally {
			lw.stop();
		}

		return dtoList;
	}

	@Override
	public List<EndpointAppDto> findAllEndpointAppByTipoRichiestaAndIdApp(TipoRichiestaEnum tipoRichiestaEnum, Integer idApp, Timestamp timestamp) throws PersistenceException {
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, "findAllEndpointAppByTipoRichiestaAndIdApp");
		lw.addParam("tipoRichiestaEnum", tipoRichiestaEnum);
		lw.addParam("idApp", idApp);
		lw.addParam("timestamp", timestamp);

		List<EndpointAppDto> dtoList = null;
		try {
			lw.start();

			List<EPaywsoRAppTiporicEp> entityList = ePaywsoRAppTiporicEpDao.findAllByIdTipoRichiestaAndIdApp(tipoRichiestaEnum.getId(), idApp, timestamp);
			dtoList = toEndpointAppDtoList(entityList);

		} finally {
			lw.stop();
		}

		return dtoList;
	}

	@Override
	//@formatter:off
	public List<EndpointAppDto> findAllEndpointAppByTipoRichiestaAndIdApps(TipoRichiestaEnum tipoRichiestaEnum, List<Integer> idApps, Timestamp timestamp)
	throws PersistenceException
	//@formatter:on
	{
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, "findAllEndpointAppByTipoRichiestaAndIdApps");
		lw.addParam("tipoRichiestaEnum", tipoRichiestaEnum);
		lw.addParam("idApps", idApps);
		lw.addParam("timestamp", timestamp);

		List<EndpointAppDto> dtoList = null;
		try {
			lw.start();

			List<EPaywsoRAppTiporicEp> entityList = ePaywsoRAppTiporicEpDao.findAllByIdTipoRichiestaAndIdApps(tipoRichiestaEnum.getId(), idApps, timestamp);
			dtoList = toEndpointAppDtoList(entityList);

		} finally {
			lw.stop();
		}

		return dtoList;
	}

	@Override
	//@formatter:off
	public List<ConfigAppDto> findAllConfigAppBy(String codFiscaleEnte, List<String> codVersamentoList, TipoRichiestaEnum tipoRichiesta , Timestamp timestamp)
	throws PersistenceException
	//@formatter:on
	{
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, "findAllConfigAppBy");
		lw.addParam("codFiscaleEnte", codFiscaleEnte);
		lw.addParam("codVersamentoList", codVersamentoList);
		lw.addParam("tipoRichiesta", tipoRichiesta);
		lw.addParam("timestamp", timestamp);

		List<ConfigAppDto> dtoList = null;
		try {
			lw.start();

			List<EPaywsoTConfigApplicativo> entityList = ePaywsoTConfigApplicativoDao.findAllByParams(codFiscaleEnte, codVersamentoList, tipoRichiesta.getId(), timestamp);
			dtoList = toConfigAppDtoList(entityList);

		} finally {
			lw.stop();
		}

		return dtoList;
	}

	@Override
	public boolean existsRichiestaById(Long id) throws PersistenceException {
		String methodName = "existsRichiestaById";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("id", id);

		boolean exists = false;

		try {
			lw.start();

			exists = (ePaywsoTRichiestaDao.findOne(id) != null);

		} finally {
			lw.stop();
		}

		return exists;
	}

	@Override
	public boolean existsRichiestaByMessageUUID(String messageUUID) throws PersistenceException {
		String methodName = "existsRichiestaByMessageUUID";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("messageUUID", messageUUID);

		boolean exists = false;

		try {
			lw.start();

			exists = (ePaywsoTRichiestaDao.findOneByMessageUUID(messageUUID) != null);

		} finally {
			lw.stop();
		}

		return exists;
	}

	@Override
	public Long findIdRichiestaByMessageUUID(String messageUUID) throws PersistenceException {
		String methodName = "findIdRichiestaByMessageUUID";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("messageUUID", messageUUID);

		Long id;

		try {
			lw.start();

			id = ePaywsoTRichiestaDao.findIdByMessageUUID(messageUUID);

		} finally {
			lw.stop();
		}

		return id;
	}

	@Override
	public RichiestaDto findRichiestaById(Long id) throws PersistenceException {
		String methodName = "findRichiestaById";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("id", id);

		RichiestaDto dto;

		try {
			lw.start();

			EPaywsoTRichiesta entity = ePaywsoTRichiestaDao.findOne(id);
			dto = toRichiestaDto(entity);

		} finally {
			lw.stop();
		}

		return dto;
	}

	@Override
	public RichiestaDto findRichiestaByMessageUUID(String messageUUID) throws PersistenceException {
		String methodName = "findRichiestaByMessageUUID";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("messageUUID", messageUUID);

		RichiestaDto dto;

		try {
			lw.start();

			EPaywsoTRichiesta entity = ePaywsoTRichiestaDao.findOneByMessageUUID(messageUUID);
			dto = toRichiestaDto(entity);

		} finally {
			lw.stop();
		}

		return dto;
	}
	
	
	
	@Override
    public RichiestaLightDto findRichiestaByMessageUUIDLight(String messageUUID) throws PersistenceException {
        String methodName = "findRichiestaByMessageUUIDLight";
        LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
        lw.addParam("messageUUID", messageUUID);

        RichiestaLightDto dto;

        try {
            lw.start();

            EPaywsoTRichiestaLight entity = ePaywsoTRichiestaDao.findOneByMessageUUIDLight(messageUUID);
            dto = toRichiestaLightDto(entity);
            

        } finally {
            lw.stop();
        }

        return dto;
    }

	@Override
	public List<RichiestaDto> findAllRichiesteInErrore(Date dataUltimaEsecuzioneJob, Long secondi) throws PersistenceException {
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, "findAllRichiesteInErrore");
		lw.addParam("dataUltimaEsecuzioneJob", dataUltimaEsecuzioneJob);
		lw.addParam("secondi", secondi);

		List<RichiestaDto> dtoList = null;
		try {
			lw.start();

			if (dataUltimaEsecuzioneJob == null)
				dataUltimaEsecuzioneJob = new Timestamp(0);
			//
			if (secondi == null)
				secondi = 0L;
			//
			Timestamp fromTime = toTimestampBeforeNSeconds(secondi, dataUltimaEsecuzioneJob);
			Timestamp __toTime = toTimestampBeforeNSeconds(secondi, new Date());

			List<EPaywsoTRichiesta> entityList = ePaywsoTRichiestaDao.findAllRichiesteInErrore(fromTime, __toTime);
			dtoList = toRichiestaDtoList(entityList);

		} finally {
			lw.stop();
		}

		return dtoList;
	}

	@Override
	public List<RichiestaDto> findAllRichiesteScartate(Date dataUltimaEsecuzioneJob, Long secondi) throws PersistenceException {
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, "findAllRichiesteScartate");
		lw.addParam("dataUltimaEsecuzioneJob", dataUltimaEsecuzioneJob);
		lw.addParam("secondi", secondi);

		List<RichiestaDto> dtoList = null;
		try {
			lw.start();

			if (dataUltimaEsecuzioneJob == null)
				dataUltimaEsecuzioneJob = new Timestamp(0);
			//
			if (secondi == null)
				secondi = 0L;
			//
			Timestamp fromTimeIns = toTimestampBeforeNSeconds(secondi, dataUltimaEsecuzioneJob);
			Timestamp __toTimeIns = toTimestampBeforeNSeconds(secondi, new Date());
			Timestamp fromTimeExe = toTimestamp(dataUltimaEsecuzioneJob);

			List<EPaywsoTRichiesta> entityList = ePaywsoTRichiestaDao.findAllRichiesteScartate(fromTimeIns, __toTimeIns, fromTimeExe);
			dtoList = toRichiestaDtoList(entityList);

		} finally {
			lw.stop();
		}

		return dtoList;
	}

	@Override
	public List<RichiestaDto> findAllRichiesteNonInviate(Long secondiRDE, Long secondiREE) throws PersistenceException {
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, "findAllRichiesteNonInviate");
		lw.addParam("secondiRDE", secondiRDE);
		lw.addParam("secondiREE", secondiREE);

		List<RichiestaDto> dtoList = null;
		try {
			lw.start();

			if (secondiRDE == null)
				secondiRDE = 0L;
			if (secondiREE == null)
				secondiREE = 0L;
			//
			Date currentTime = new Date();
			Timestamp beforeTimeRDE = toTimestampBeforeNSeconds(secondiRDE, currentTime);
			Timestamp beforeTimeREE = toTimestampBeforeNSeconds(secondiREE, currentTime);

			List<EPaywsoTRichiesta> entityList = ePaywsoTRichiestaDao.findAllRichiesteNonInviate(beforeTimeRDE, beforeTimeREE);
			dtoList = toRichiestaDtoList(entityList);

		} finally {
			lw.stop();
		}

		return dtoList;
	}

	@Override
	public Long insertRichiesta(RichiestaBaseDto dto) throws PersistenceException {
		String methodName = "insertRichiesta";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("dto", dto);

		Long id = null;

		try {
			lw.start();

			if (dto != null) {

				if (existsRichiestaByMessageUUID(dto.getMessageUUID())) {
					throw new PersistenceException(IssueEnum.DB_ERROR_INSERT_RICHIESTA_1ARG, "- messageUUID non univoco: " + dto.getMessageUUID());
				}

				Timestamp timestampNow = getTimestampNow();

				EPaywsoTRichiesta entity = new EPaywsoTRichiesta();
				entity.setEPaywsoTEnte(ePaywsoTEnteDao.findOneByCodFiscale(dto.getCodFiscaleEnte(), timestampNow)); // N.B. se sconosciuto > NON_CENSITO
				entity.setEPaywsoDTipoRichiesta(ePaywsoDTipoRichiestaDao.findOne(dto.getTipoRichiestaEnum().getId()));
				entity.setEPaywsoDStatoRichiesta(ePaywsoDStatoRichiestaDao.findOne(dto.getStatoRichiestaEnum().getId()));
				entity.setIdMessaggio(trunc(dto.getIdMessaggio(), 35)); // normalizzazione alla lunghezza
				entity.setCfEnteOrigine(dto.getCodFiscaleEnteOrigine());
				entity.setPagamentoSpontaneo(dto.getPagamentoSpontaneo());
				entity.setCodVersamento(trunc(dto.getCodVersamento(), 4)); // normalizzazione alla lunghezza
				entity.setMessageUUID(dto.getMessageUUID());
				entity.setContenutoMessaggio(dto.getContenutoMessaggio());
				entity.setDtInserimentoRichiesta(timestampNow);
				entity.setDtUltimaVariazione(timestampNow);
				entity.setNumTotaleElementi(dto.getNumTotaleElementi());
				entity.setImportoTotale(dto.getImportoTotale());
				entity.setDimMessaggioMb(dto.getDimMessaggioMB());
				entity.setIdPSP(dto.getIdPSP());
				entity.setIdFlussoRendicontazione(dto.getIdFlussoRendicontazione());
				ePaywsoTRichiestaDao.persist(entity);
				id = entity.getIdRichiesta();
			}
		} finally {
			lw.stop();
		}

		return id;
	}

	
	
	@Override
	public boolean updateRichiesta(RichiestaDto dto) throws PersistenceException {
		String methodName = "updateRichiesta";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("dto", dto);

		boolean updateDone = false;

		try {
			lw.start();

			if (dto != null) {
				EPaywsoTRichiesta entity = ePaywsoTRichiestaDao.findOneByMessageUUID(dto.getMessageUUID());

				if (entity != null) {
					Timestamp timestampNow = getTimestampNow();

					if (dto.getId() != null) {
						entity.setIdRichiesta(dto.getId());
					}
					if (dto.getCodFiscaleEnte() != null) {
						entity.setEPaywsoTEnte(ePaywsoTEnteDao.findOneByCodFiscale(dto.getCodFiscaleEnte(), timestampNow)); // N.B. se sconosciuto > NON_CENSITO
					}
					if (dto.getTipoRichiestaEnum() != null) {
						entity.setEPaywsoDTipoRichiesta(ePaywsoDTipoRichiestaDao.findOne(dto.getTipoRichiestaEnum().getId()));
					}
					if (dto.getStatoRichiestaEnum() != null) {
						entity.setEPaywsoDStatoRichiesta(ePaywsoDStatoRichiestaDao.findOne(dto.getStatoRichiestaEnum().getId()));
					}
					if (dto.getIdMessaggio() != null) {
						entity.setIdMessaggio(dto.getIdMessaggio());
					}
					if (dto.getCodFiscaleEnteOrigine() != null) {
						entity.setCfEnteOrigine(dto.getCodFiscaleEnteOrigine());
					}
					if (dto.getPagamentoSpontaneo() != null) {
						entity.setPagamentoSpontaneo(dto.getPagamentoSpontaneo());
					}
					if (dto.getCodVersamento() != null) {
						entity.setCodVersamento(dto.getCodVersamento());
					}
					if (dto.getMessageUUID() != null) {
						entity.setMessageUUID(dto.getMessageUUID());
					}
					if (dto.getDataInserimentoRichiesta() != null) {
						entity.setDtInserimentoRichiesta(toTimestamp(dto.getDataInserimentoRichiesta()));
					}
					if (dto.getEsitoRichiestaDto() != null) {
						EsitoRichiestaDto esitoRichiestaDto = dto.getEsitoRichiestaDto();
						if (esitoRichiestaDto.getCod() != null) {
							entity.setEPaywsoDEsito(ePaywsoDEsitoDao.findOneByCod(esitoRichiestaDto.getCod()));
						}
						if (esitoRichiestaDto.getDet() != null) {
							entity.setDettaglioEsito(trunc(esitoRichiestaDto.getDet(), 500)); // normalizzazione alla lunghezza
						} else {
							// N.B. se dettaglio esito null, ma codice valorizzato -> assume l'intenzione di cancellare il dettaglio preesistente
							if (esitoRichiestaDto.getCod() != null) {
								entity.setDettaglioEsito(null);
							}
						}
					}
					if (dto.getDataCallback() != null) {
						entity.setDtCallback(toTimestamp(dto.getDataCallback()));
					}
					if (dto.getEsitoCallbackDto() != null) {
						EsitoCallbackDto esitoCallbackDto = dto.getEsitoCallbackDto();
						if (esitoCallbackDto.getCod() != null) {
							entity.setCodEsitoCallback(esitoCallbackDto.getCod());
						}
						if (esitoCallbackDto.getDet() != null) {
							entity.setDettaglioEsitoCallback(trunc(esitoCallbackDto.getDet(), 500)); // normalizzazione alla lunghezza
						}
					}
					if (dto.getNumTotaleElementi() != null) {
						entity.setNumTotaleElementi(dto.getNumTotaleElementi());
					}
					if (dto.getImportoTotale() != null) {
						entity.setImportoTotale(dto.getImportoTotale());
					}
					if (dto.getDimMessaggioMB() != null) {
						entity.setDimMessaggioMb(dto.getDimMessaggioMB());
					}
					if (dto.getContenutoMessaggio() != null) {
						entity.setContenutoMessaggio(dto.getContenutoMessaggio());
					}
					if (dto.getContenutoCallback() != null) {
						entity.setContenutoCallback(dto.getContenutoCallback());
					}
					entity.setDtUltimaVariazione(timestampNow);

					ePaywsoTRichiestaDao.merge(entity);
					updateDone = true;
				}
			}
		} finally {
			lw.stop();
		}

		return updateDone;
	}
	
	@Override
    public boolean updateRichiestaLight(RichiestaDto dto) throws PersistenceException {
        String methodName = "updateRichiesta";
        LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
        lw.addParam("dto", dto);

        boolean updateDone = false;

        try {
            lw.start();

            if (dto != null) {
                Long idRichiesta= ePaywsoTRichiestaDao.findIdByMessageUUID ( dto.getMessageUUID() );

                if (idRichiesta != null) {
                    List<Object> obj = new ArrayList<Object> ();
                    int numParametro = 1;


                    Timestamp timestampNow = getTimestampNow();

                    StringBuilder sb = new StringBuilder ();
                    sb.append ( " update epaywso_t_richiesta set ");

                    if (dto.getCodFiscaleEnte() != null) {
                        EPaywsoTEnte  ente=  ePaywsoTEnteDao.findOneByCodFiscale(dto.getCodFiscaleEnte(), timestampNow);
                        sb.append ( "id_ente= ?, ");
                        obj.add ( ente.getIdEnte () );
                    }

                    if (dto.getTipoRichiestaEnum() != null) {

                        EPaywsoDTipoRichiesta tipoRichiesta=   ePaywsoDTipoRichiestaDao.findOne(dto.getTipoRichiestaEnum().getId());
                        sb.append ( "id_tipo_richiesta= ? , ");
                        obj.add ( tipoRichiesta.getIdTipoRichiesta () );
                    }
                    
                    if (dto.getStatoRichiestaEnum() != null) {

                        EPaywsoDStatoRichiesta statoRichiesta=   ePaywsoDStatoRichiestaDao.findOne(dto.getStatoRichiestaEnum().getId());
                        sb.append ( "id_stato_richiesta= ? , ");
                        obj.add ( statoRichiesta.getIdStatoRichiesta ());
                    }
                    if (dto.getIdMessaggio() != null) {
                        sb.append ( "id_messaggio= ? , ");
                        obj.add ( dto.getIdMessaggio());
                    }
                    
                    if (dto.getCodFiscaleEnteOrigine() != null) {
                        sb.append ( "cf_ente_origine= ? , ");
                        obj.add ( dto.getCodFiscaleEnteOrigine());
                    }
                    
                    if (dto.getPagamentoSpontaneo() != null) {
                        sb.append ( "pagamento_spontaneo= ? , ");
                        obj.add ( dto.getPagamentoSpontaneo());
                    }
                    
                    if (dto.getCodVersamento() != null) {
                        sb.append ( "cod_versamento= ? , ");
                        obj.add ( dto.getCodVersamento());
                    }
                    
                    if (dto.getMessageUUID() != null) {
                        sb.append ( "message_uuid= ? , ");
                        obj.add ( dto.getMessageUUID());
                    }
                    
                    if (dto.getDataInserimentoRichiesta() != null) {
                        sb.append ( "dt_inserimento_richiesta= ? , ");
                        obj.add ( toTimestamp(dto.getDataInserimentoRichiesta()));
                    }
                    
                    if (dto.getEsitoRichiestaDto() != null) {
                        EsitoRichiestaDto esitoRichiestaDto = dto.getEsitoRichiestaDto();
                        if (esitoRichiestaDto.getCod() != null) {
                            EPaywsoDEsito esito=  ePaywsoDEsitoDao.findOneByCod(esitoRichiestaDto.getCod());
                            sb.append ( "cod_esito = ? , ");
                            obj.add ( esito.getCodEsito ());

                        }
                        if (esitoRichiestaDto.getDet() != null)
                        {
                            sb.append ( "dettaglio_esito = ? , ");
                            obj.add ( trunc(esitoRichiestaDto.getDet(), 500));

                        }
                        else  if (esitoRichiestaDto.getCod() != null)
                        {
                         // N.B. se dettaglio esito null, ma codice valorizzato -> assume l'intenzione di cancellare il dettaglio preesistente
                            sb.append ( "dettaglio_esito = ? , ");
                            obj.add ( null);  
                        }
                    }
                    
                    if (dto.getDataCallback() != null) {
                        sb.append ( "dt_callback= ? , ");
                        obj.add ( toTimestamp(dto.getDataCallback()));
                        
                    }
                    
                    if (dto.getEsitoCallbackDto() != null) {
                        EsitoCallbackDto esitoCallbackDto = dto.getEsitoCallbackDto();
                        if (esitoCallbackDto.getCod() != null) {
                            sb.append ( "cod_esito_callback = ? , ");
                            obj.add ( esitoCallbackDto.getCod());

                        }
                        if (esitoCallbackDto.getDet() != null)
                        {
                            sb.append ( "dettaglio_esito_callback = ? , ");
                            obj.add ( trunc(esitoCallbackDto.getDet(), 500));

                        }
                    }
                    
                    if (dto.getNumTotaleElementi() != null) {
                        sb.append ( "num_totale_elementi= ? , ");
                        obj.add ( dto.getNumTotaleElementi());
                        
                    }
                    
                    if (dto.getImportoTotale() != null) {
                        sb.append ( "importo_totale= ? , ");
                        obj.add ( dto.getImportoTotale());
                        
                    }
                    
                    if (dto.getDimMessaggioMB() != null) {
                        sb.append ( "dim_messaggio_mb= ? , ");
                        obj.add ( dto.getDimMessaggioMB());
                        
                    }
                    
                    if (dto.getContenutoMessaggio() != null) {
                        sb.append ( "contenuto_messaggio= ? , ");
                        obj.add ( dto.getContenutoMessaggio());
                        
                    }
                    
                    if (dto.getContenutoCallback() != null) {
                        sb.append ( "contenuto_callback = ? ,");
                        obj.add ( dto.getContenutoCallback());
                        
                    }
                    
                    sb.append ( "dt_ultima_variazione = ? ");
                    sb.append ( "  where id_richiesta = ? ");


                    Query query
                    = entityManager.createNativeQuery (sb.toString () , Integer.class );
                    for (Object o: obj )
                    {
                        query.setParameter ( numParametro, o );
                        numParametro++;
                    }

                    query.setParameter ( numParametro, timestampNow );
                    numParametro++;
                    query.setParameter ( numParametro, idRichiesta );
                   
                    int prova= query.executeUpdate ();
                    updateDone = true;
                }
            }
        } finally {
            lw.stop();
        }

        return updateDone;
    }

	@Override
	/*
	 * N.B. se si verifica un problema durante una delle due persist, occorre annullare le modifiche parziali per garantire l'atomicita
	 * dell'intera operazione. Le operazioni di Hibernate come persist(entity) producono eccezioni derivate da RuntimeException.
	 * EPaywsoDaoBaseImpl::handlePersistenceException emette una PersistenceException con la causa. EPaywsoBusinessBase::handleBusinessException
	 * a fronte di una PersistenceException, emette una SystemException che deriva da EJBException. L'EJBException determina il rollback
	 * dell'operazione, garantendo cosi l'atomicita dell'operazione.
	 */
	public boolean saveEsitoInvio(Long idRichiesta, EsitoInvioDto dto) throws PersistenceException {
		String methodName = "saveEsitoInvio";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("idRichiesta", idRichiesta);
		lw.addParam("dto", dto);

		boolean updateDone = false;

		try {
			lw.start();

			if (dto != null) {
				Integer idApp = dto.getIdApplicativo();
				EPaywsoRAppRicEsitoInvio entityR = ePaywsoRAppRicEsitoInvioDao.findOneByIdRichiestaAndIdApp(idRichiesta, idApp);
				if (entityR != null) {
					// la relazione esiste, ne aggiorna i contenuti
					lw.info("aggiorna un esito invio esistente");

					EPaywsoTEsitoInvio entity = entityR.getEpaywsoTEsitoInvio();
					if (dto.getDesApplicativo() != null) {
						entity.setDesApplicativo(dto.getDesApplicativo());
					}
					if (dto.getMessageStore() != null) {
						entity.setMessageStore(dto.getMessageStore());
					}
					if (dto.getEsitoInvioRichiestaDto() != null) {
						EsitoRichiestaDto esitoInvioRichiestaDto = dto.getEsitoInvioRichiestaDto();
						if (esitoInvioRichiestaDto.getCod() != null) {
							entity.setEPaywsoDEsito(ePaywsoDEsitoDao.findOneByCod(esitoInvioRichiestaDto.getCod()));
						}
						if (esitoInvioRichiestaDto.getDet() != null) {
							entity.setDettaglioEsito(trunc(esitoInvioRichiestaDto.getDet(), 500)); // normalizzazione alla lunghezza
						} else {
							// N.B. se dettaglio esito null, ma codice valorizzato -> assume l'intenzione di cancellare il dettaglio preesistente
							if (esitoInvioRichiestaDto.getCod() != null) {
								entity.setDettaglioEsito(null);
							}
						}
					}
					if (dto.getDataEsitoInvio() != null) {
						entity.setDtEsitoInvio(toTimestamp(dto.getDataEsitoInvio()));
					}

					ePaywsoTEsitoInvioDao.merge(entity);
					updateDone = true;

				} else {
					// la relazione non esiste, crea l'esito invio e la relazione
					lw.info("crea un nuovo esito invio");
					
					EPaywsoTEsitoInvio entity = new EPaywsoTEsitoInvio();
					entity.setIdRichiesta(idRichiesta);
					entity.setDesApplicativo(dto.getDesApplicativo());
					entity.setMessageStore(dto.getMessageStore());
					if (dto.getEsitoInvioRichiestaDto() != null) {
						EsitoRichiestaDto esitoInvioRichiestaDto = dto.getEsitoInvioRichiestaDto();
						if (esitoInvioRichiestaDto.getCod() != null) {
							entity.setEPaywsoDEsito(ePaywsoDEsitoDao.findOneByCod(esitoInvioRichiestaDto.getCod()));
						}
						entity.setDettaglioEsito(trunc(esitoInvioRichiestaDto.getDet(), 500)); // normalizzazione alla lunghezza
					}
					entity.setDtEsitoInvio(toTimestamp(dto.getDataEsitoInvio()));
					ePaywsoTEsitoInvioDao.persist(entity);
					Long idEsitoInvio = entity.getIdEsitoInvio();

					EPaywsoRAppRicEsitoInvio newEntityR = new EPaywsoRAppRicEsitoInvio();
					newEntityR.setId(new EPaywsoRAppRicEsitoInvioPK(idRichiesta, idEsitoInvio, idApp));
					ePaywsoRAppRicEsitoInvioDao.persist(newEntityR);

					updateDone = true;
				}
			}
		} finally {
			lw.stop();
		}

		return updateDone;
	}

	@Override
	public Long countAllRichiestaByFilter(RichiestaLightFilterDto filter) throws PersistenceException {
		String methodName = "countAllRichiestaByFilter";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("filter", filter);

		Long num = null;

		try {
			lw.start();

			num = ePaywsoTRichiestaDao.countAllRichiestaByFilter(filter);

		} finally {
			lw.stop();
		}

		return num;
	}
	
	@Override
    public boolean existRichiestaByFilter(RichiestaLightFilterDto filter) throws PersistenceException {
        String methodName = "countAllRichiestaByFilter";
        LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
        lw.addParam("filter", filter);
        boolean exist= false;

        try {
            lw.start();

            exist=  ePaywsoTRichiestaDao.existRichieste(filter);

        } finally {
            lw.stop();
        }
        return exist;

    }

	@Override
	//@formatter:off 
	public List<RichiestaLightDto> findAllRichiestaLightByFilter(RichiestaLightFilterDto filter, List<CriterioOrdinamentoDto<ColumnNameRichiestaEnum>> criList, PaginazioneDto pag)
	throws PersistenceException
	//@formatter:on
	{
		String methodName = "findAllRichiestaLightByFilter";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("filter", filter);
		lw.addParam("criList", criList);
		lw.addParam("pag", pag);

		List<RichiestaLightDto> dtoList = new ArrayList<RichiestaLightDto>();

		try {
			lw.start();

			List<EPaywsoTRichiestaLight> entityList = ePaywsoTRichiestaDao.findAllRichiestaLightByFilter(filter, criList, pag);
			dtoList = toRichiestaLightDtoList(entityList);

		} finally {
			lw.stop();
		}

		return dtoList;
	}

	protected EsitoInvioDto toEsitoInvioDto(EPaywsoTEsitoInvio entity) throws PersistenceException {
		EsitoInvioDto dto = null;

		if (entity != null) {
			dto = new EsitoInvioDto();
			dto.setDesApplicativo(entity.getDesApplicativo());
			dto.setMessageStore(entity.getMessageStore());
			dto.setEsitoInvioRichiestaDto(toEsitoRichiestaDto(entity.getEPaywsoDEsito(), entity.getDettaglioEsito()));
			dto.setDataEsitoInvio(entity.getDtEsitoInvio());
			
			EPaywsoRAppRicEsitoInvio rAppEsitoInvio = ePaywsoRAppRicEsitoInvioDao.findOneByIdRichiestaAndIdEsitoInvio(entity.getIdRichiesta(), entity.getIdEsitoInvio());
			if (rAppEsitoInvio != null) {
				dto.setIdApplicativo(rAppEsitoInvio.getEpaywsoTApplicativo().getIdApplicativo());
			}
		}

		return dto;
	}

	protected List<EsitoInvioDto> toEsitoInvioDtoList(List<EPaywsoTEsitoInvio> entityList) throws PersistenceException {
		List<EsitoInvioDto> dtoList = null;

		if (entityList != null) {
			dtoList = new ArrayList<EsitoInvioDto>();
			for (EPaywsoTEsitoInvio entity : entityList) {
				dtoList.add(toEsitoInvioDto(entity));
			}
		}

		return dtoList;
	}

	@Override
	public List<EsitoInvioDto> findAllEsitoInvioByIdRichiesta(Long idRichiesta) throws PersistenceException {
		String methodName = "findAllEsitoInvioByIdRichiesta";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("idRichiesta", idRichiesta);

		List<EsitoInvioDto> dtoList = new ArrayList<EsitoInvioDto>();

		try {
			lw.start();

			List<EPaywsoTEsitoInvio> entityList = ePaywsoTEsitoInvioDao.findAllByIdRichiesta(idRichiesta);
			dtoList = toEsitoInvioDtoList(entityList);
		} finally {
			lw.stop();
		}

		return dtoList;
	}
	
	@Override
	public List<RichiestaDto> findAllRichiesteByMessaggioEnteTipoRichiesta(List<String> idMessaggioCfEnteList, Integer idTipoRichiesta) throws PersistenceException {
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, "findAllRichiesteByMessaggioEnteTipoRichiesta");
		lw.addParam("idMessaggioCfEnteList", idMessaggioCfEnteList);
		lw.addParam("idTipoRichiesta", idTipoRichiesta);

		List<RichiestaDto> dtoList = null;
		try {
			lw.start();
			List<EPaywsoTRichiesta> entityList = ePaywsoTRichiestaDao.findAllRichiesteByMessaggioEnteTipoRichiesta(idMessaggioCfEnteList, idTipoRichiesta);
			dtoList = toRichiestaDtoList(entityList);

		} finally {
			lw.stop();
		}

		return dtoList;
	}
	
	@Override
	public List<RichiestaDto> findAllRichiesteByMessaggioEnte(List<String> idMessaggioCfEnteList) throws PersistenceException {
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, "findAllRichiesteByMessaggioEnte");
		lw.addParam("idMessaggioCfEnteList", idMessaggioCfEnteList);

		List<RichiestaDto> dtoList = null;
		try {
			lw.start();
			List<EPaywsoTRichiesta> entityList = ePaywsoTRichiestaDao.findAllRichiesteByMessaggioEnte(idMessaggioCfEnteList);
			dtoList = toRichiestaDtoList(entityList);

		} finally {
			lw.stop();
		}

		return dtoList;
	}

}
