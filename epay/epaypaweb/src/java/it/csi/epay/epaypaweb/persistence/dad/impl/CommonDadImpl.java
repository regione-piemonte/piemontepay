/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dad.impl;

import java.util.List;

import javax.inject.Inject;

import it.csi.epay.epaypaweb.dto.CodiceVersamentoDto;
import it.csi.epay.epaypaweb.dto.ConfigurazioneDto;
import it.csi.epay.epaypaweb.dto.DatiAvvisoPagamentoDto;
import it.csi.epay.epaypaweb.dto.EnteDto;
import it.csi.epay.epaypaweb.dto.StatoFlussoDto;
import it.csi.epay.epaypaweb.dto.TipoAggiornamentoPosizioneDebitoriaDto;
import it.csi.epay.epaypaweb.dto.TipoFlussoDto;
import it.csi.epay.epaypaweb.dto.TipoFormatoFileDto;
import it.csi.epay.epaypaweb.enumeration.DirezioneEnum;
import it.csi.epay.epaypaweb.enumeration.StatoFlussoEnum;
import it.csi.epay.epaypaweb.enumeration.TipoAggiornamentoPosizioneDebitoriaEnum;
import it.csi.epay.epaypaweb.enumeration.TipoFlussoEnum;
import it.csi.epay.epaypaweb.enumeration.TipoFormatoFileEnum;
import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dad.EPaypaDadBaseImpl;
import it.csi.epay.epaypaweb.persistence.dad.interf.CommonDad;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaDStatoFlussoDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaDTipoAggPosizioneDebitoriaDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaDTipoFlussoDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaDTipoFormatoOutputDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTCodiceVersamentoDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTConfigurazioneDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTDatiAvvisoPagamentoDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTEnteDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaDStatoFlusso;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaDTipoAggPosizioneDebitoria;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaDTipoFlusso;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaDTipoFormatoOutput;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTCodiceVersamento;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTConfigurazione;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTDatiAvvisoPagamento;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTEnte;


public class CommonDadImpl extends EPaypaDadBaseImpl implements CommonDad {
	static private final String CLASSNAME = CommonDadImpl.class.getSimpleName();

	@Inject
	private EpaypaDTipoFlussoDao epaypaDTipoFlussoDao;

	@Inject
	private EpaypaDStatoFlussoDao epaypaDStatoFlussoDao;

	@Inject
	private EpaypaDTipoAggPosizioneDebitoriaDao epaypaDTipoAggPosizioneDebitoriaDao;

	@Inject
	private EpaypaDTipoFormatoOutputDao epaypaDTipoFormatoOutputDao;

	@Inject
	private EpaypaTEnteDao epaypaTEnteDao;

	@Inject
	private EpaypaTCodiceVersamentoDao epaypaTCodiceVersamentoDao;

    @Inject
    private EpaypaTDatiAvvisoPagamentoDao epaypaTDatiAvvisoPagamentoDao;

    @Inject
    private EpaypaTConfigurazioneDao epaypaTConfigurazioneDao;

	@Override
	public TipoFlussoDto findTipoFlussoByEnum(TipoFlussoEnum en) throws PersistenceException {
		String methodName = "findTipoFlussoByEnum";
		
		

		TipoFlussoDto dto = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			EpaypaDTipoFlusso entity = epaypaDTipoFlussoDao.findOne(en.getId());
			dto = toTipoFlussoDto(entity);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return dto;
	}

	@Override
	public StatoFlussoDto findStatoFlussoByEnum(StatoFlussoEnum en) throws PersistenceException {
		String methodName = "findStatoFlussoByEnum";
		
		

		StatoFlussoDto dto = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			EpaypaDStatoFlusso entity = epaypaDStatoFlussoDao.findOne(en.getId());
			dto = toStatoFlussoDto(entity);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return dto;
	}

	@Override
	public TipoAggiornamentoPosizioneDebitoriaDto findTipoAggiornamentoPosizioneDebitoria(TipoAggiornamentoPosizioneDebitoriaEnum en) throws PersistenceException {
		String methodName = "findTipoAggiornamentoPosizioneDebitoria";
		
		

		TipoAggiornamentoPosizioneDebitoriaDto dto = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			EpaypaDTipoAggPosizioneDebitoria entity = epaypaDTipoAggPosizioneDebitoriaDao.findOne(en.getId());
			dto = toTipoAggiornamentoPosizioneDebitoriaDto(entity);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return dto;
	}

	@Override
	public TipoFormatoFileDto findTipoFormatoFileByEnum(TipoFormatoFileEnum en) throws PersistenceException {
		String methodName = "findTipoFormatoFileByEnum";
		
		

		TipoFormatoFileDto dto = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			EpaypaDTipoFormatoOutput entity = epaypaDTipoFormatoOutputDao.findOne(en.getId());
			dto = toTipoFormatoFileDto(entity);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return dto;
	}

	@Override
	public List<TipoFlussoDto> findAllTipoFlusso() throws PersistenceException {
		String methodName = "findAllTipoFlusso";
		

		List<TipoFlussoDto> dtoList = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			List<EpaypaDTipoFlusso> entityList = epaypaDTipoFlussoDao.findAll();
			dtoList = toTipoFlussoDtoList(entityList);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return dtoList;
	}

	@Override
	public List<StatoFlussoDto> findAllStatoFlusso() throws PersistenceException {
		String methodName = "findAllStatoFlusso";
		

		List<StatoFlussoDto> dtoList = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			List<EpaypaDStatoFlusso> entityList = epaypaDStatoFlussoDao.findAll();
			dtoList = toStatoFlussoDtoList(entityList);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return dtoList;
	}

	@Override
	public List<StatoFlussoDto> findAllStatoFlussoByDirezione(DirezioneEnum direzione) throws PersistenceException {
		String methodName = "findAllStatoFlussoByDirezione";
		

		List<StatoFlussoDto> dtoList = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			List<EpaypaDStatoFlusso> entityList = epaypaDStatoFlussoDao.findAllByDirezione(direzione);
			dtoList = toStatoFlussoDtoList(entityList);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return dtoList;
	}

	@Override
	public List<TipoAggiornamentoPosizioneDebitoriaDto> findAllTipoAggiornamentoPosizioniDebitorie() throws PersistenceException {
		String methodName = "findAllTipoAggiornamentoPosizioniDebitorie";
		

		List<TipoAggiornamentoPosizioneDebitoriaDto> dtoList = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			List<EpaypaDTipoAggPosizioneDebitoria> entityList = epaypaDTipoAggPosizioneDebitoriaDao.findAll();
			dtoList = toTipoAggiornamentoPosizioneDebitoriaDtoList(entityList);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return dtoList;
	}

	@Override
	public List<TipoFormatoFileDto> findAllTipoFormatoFile() throws PersistenceException {
		String methodName = "findAllTipoFormatoFile";
		

		List<TipoFormatoFileDto> dtoList = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			List<EpaypaDTipoFormatoOutput> entityList = epaypaDTipoFormatoOutputDao.findAll();
			dtoList = toTipoFormatoFileDtoList(entityList);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return dtoList;
	}

	@Override
	public boolean existsEnteById(Integer id) throws PersistenceException {
		String methodName = "existsEnteById";
		
		

		boolean exists = false;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			exists = (epaypaTEnteDao.findCodFiscaleEnteById(id) != null);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return exists;
	}

	@Override
	public boolean existsEnteByCodFiscale(String cod) throws PersistenceException {
		String methodName = "existsEnteByCodFiscale";
		
		

		boolean exists = false;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			exists = (epaypaTEnteDao.findIdEnteByCodFiscale(cod) != null);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return exists;
	}

	@Override
	public EnteDto findEnteById(Integer id) throws PersistenceException {
		String methodName = "findEnteById";
		
		

		EnteDto dto = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			EpaypaTEnte entity = epaypaTEnteDao.findOne(id);
			dto = toEnteDto(entity);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return dto;
	}

	@Override
	public EnteDto findEnteByCodFiscale(String cod) throws PersistenceException {
		String methodName = "findEnteByCodFiscale";
		
		

		EnteDto dto = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			EpaypaTEnte entity = epaypaTEnteDao.findOneByCodFiscale(cod);
			dto = toEnteDto(entity);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return dto;
	}

	@Override
	public boolean existsCodiceVersamentoById(Integer id) throws PersistenceException {
		String methodName = "existsCodiceVersamentoById";
		
		

		boolean exists = false;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			exists = (epaypaTCodiceVersamentoDao.findCodVersamentoById(id) != null);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return exists;
	}

	@Override
	public boolean existsCodiceVersamentoByCodAndEnte(String codVersamento, Integer idEnte) throws PersistenceException {
		String methodName = "existsCodiceVersamentoByCodAndEnte";
		
		
		

		boolean exists = false;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			exists = (epaypaTCodiceVersamentoDao.findIdCodVersamentoByCodAndEnte(codVersamento, idEnte) != null);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return exists;
	}

	@Override
	public CodiceVersamentoDto findCodiceVersamentoById(Integer id) throws PersistenceException {
		String methodName = "findCodiceVersamentoById";
		
		

		CodiceVersamentoDto dto = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			EpaypaTCodiceVersamento entity = epaypaTCodiceVersamentoDao.findOne(id);
			dto = toCodiceVersamentoDto(entity);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return dto;
	}

	@Override
	public CodiceVersamentoDto findCodiceVersamentoByCodAndEnte(String codVersamento, Integer idEnte) throws PersistenceException {
		String methodName = "findCodiceVersamentoByCodAndEnte";
		
		
		

		CodiceVersamentoDto dto = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			EpaypaTCodiceVersamento entity = epaypaTCodiceVersamentoDao.findOneByCodAndEnte(codVersamento, idEnte);
			dto = toCodiceVersamentoDto(entity);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return dto;
	}

    @Override
    public DatiAvvisoPagamentoDto findDatiEnteAvvisoPagamentoByCodVersamento (String codFiscaleEnte, String codVersamento ) throws PersistenceException {
        String methodName = "findDatiEnteAvvisoPagamentoByCodVersamento";
        
        
        

        DatiAvvisoPagamentoDto dto = null;

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );
            EpaypaTDatiAvvisoPagamento entity = epaypaTDatiAvvisoPagamentoDao.findDatiAvvisoPagamentoByCodiceVersamento (codFiscaleEnte, codVersamento );
            dto = toDatiAvvisoPagamentoDto ( entity );

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return dto;
    }

    @Override
    public ConfigurazioneDto findConfigurazioneByCodiceAndCodFiscaleEnte ( String codice, String codFiscaleEnte ) throws PersistenceException {
        String methodName = "findConfigurazioneByCodiceAndCodFiscaleEnte";
        
        
        

        ConfigurazioneDto dto = null;

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );
            EpaypaTConfigurazione entity = epaypaTConfigurazioneDao.findConfigurazioneByCodiceAndCodFiscaleEnte ( codice, codFiscaleEnte );
            dto = toConfigurazioneDto ( entity );

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return dto;
    }

}
