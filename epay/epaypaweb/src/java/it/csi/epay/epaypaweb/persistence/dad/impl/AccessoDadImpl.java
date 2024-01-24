/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dad.impl;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import it.csi.epay.epaypaweb.dto.CduDto;
import it.csi.epay.epaypaweb.dto.CodiceVersamentoDto;
import it.csi.epay.epaypaweb.dto.EnteDto;
import it.csi.epay.epaypaweb.dto.ProfiloDto;
import it.csi.epay.epaypaweb.dto.RuoloDto;
import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dad.EPaypaDadBaseImpl;
import it.csi.epay.epaypaweb.persistence.dad.interf.AccessoDad;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaDTipoPagamentoDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTCduDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTCodiceVersamentoDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTEnteDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTProfiloDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTRuoloDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaDTipoPagamento;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTCdu;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTCodiceVersamento;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTEnte;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTProfilo;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTRuolo;


public class AccessoDadImpl extends EPaypaDadBaseImpl implements AccessoDad {
    static private final String CLASSNAME = AccessoDadImpl.class.getSimpleName();

    @Inject
    private EpaypaTCduDao epaypaTCduDao;

    @Inject
    private EpaypaTCodiceVersamentoDao epaypaTCodiceVersamentoDao;

    @Inject
    private EpaypaTEnteDao epaypaTEnteDao;

    @Inject
    private EpaypaTRuoloDao epaypaTRuoloDao;

    @Inject
    private EpaypaTProfiloDao epaypaTProfiloDao;

    @Inject
    private EpaypaDTipoPagamentoDao epaypaDTipoPagamentoDao;

    //	@Inject
    //	private EpaypaTUtenteDao epaypaTUtenteDao;

    //	@Override
    //	public boolean existsUtenteById(Long id) throws PersistenceException {
    //		String methodName = "existsUtenteById";
    //		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
    //		lw.addParam("id", id);
    //
    //		boolean exists = false;
    //
    //		try {
    //			lw.start();
    //
    //			exists = (epaypaTUtenteDao.findCodUtenteById(id) != null);
    //
    //		} finally {
    //			lw.stop();
    //		}
    //
    //		return exists;
    //	}

    //	@Override
    //	public boolean existsUtenteByCod(String cod) throws PersistenceException {
    //		String methodName = "existsUtenteByCod";
    //		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
    //		lw.addParam("cod", cod);
    //
    //		boolean exists = false;
    //
    //		try {
    //			lw.start();
    //
    //			exists = (epaypaTUtenteDao.findIdUtenteByCod(cod) != null);
    //
    //		} finally {
    //			lw.stop();
    //		}
    //
    //		return exists;
    //	}

    //	@Override
    //	public UtenteDto findUtenteById(Long id) throws PersistenceException {
    //		String methodName = "findUtenteById";
    //		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
    //		lw.addParam("id", id);
    //
    //		UtenteDto dto = null;
    //
    //		try {
    //			lw.start();
    //
    //			EpaypaTUtente entity = epaypaTUtenteDao.findOne(id);
    //			dto = toUtenteDto(entity);
    //
    //		} finally {
    //			lw.stop();
    //		}
    //
    //		return dto;
    //	}

    //	@Override
    //	public UtenteDto findUtenteByCod(String cod) throws PersistenceException {
    //		String methodName = "findUtenteByCod";
    //		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
    //		lw.addParam("cod", cod);
    //
    //		UtenteDto dto = null;
    //
    //		try {
    //			lw.start();
    //
    //			EpaypaTUtente entity = epaypaTUtenteDao.findOneByCod(cod);
    //			dto = toUtenteDto(entity);
    //
    //		} finally {
    //			lw.stop();
    //		}
    //
    //		return dto;
    //	}

    @Override
    public EnteDto findEnteById(Integer idEnte) throws PersistenceException {
        String methodName = "findEnteById";
        
        

        EnteDto dto = null;

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            EpaypaTEnte entityEnte = epaypaTEnteDao.findOne(idEnte);
            dto = toEnteDto(entityEnte);

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return dto;
    }

    @Override
    public List<EnteDto> findAllEnteByCodUtente(String cod) throws PersistenceException {
        String methodName = "findAllEnteByCodUtente";
        
        

        List<EnteDto> dtoList = null;

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            List<EpaypaTEnte> entityList = epaypaTEnteDao.findAllByCodUtente(cod);
            dtoList = toEnteDtoList(entityList);

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return dtoList;
    }

    @Override
    public RuoloDto findRuoloById(Integer idRuolo) throws PersistenceException {
        String methodName = "findRuoloById";
        
        

        RuoloDto dtoRuolo = null;

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            EpaypaTRuolo entityRuolo = epaypaTRuoloDao.findOne(idRuolo);
            dtoRuolo = toRuoloDto(entityRuolo);

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return dtoRuolo;
    }

    @Override
    public List<RuoloDto> findAllRuoloByIdUtenteAndIdEnte(Long idUtente, Integer idEnte) throws PersistenceException {
        String methodName = "findAllRuoloByIdUtenteAndIdEnte";
        
        
        

        List<RuoloDto> dtoList = null;

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            List<EpaypaTRuolo> entityList = epaypaTRuoloDao.findAllByIdUtenteAndIdEnte(idUtente, idEnte);
            dtoList = toRuoloDtoList(entityList);

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return dtoList;
    }

    @Override
    public ProfiloDto findProfiloById(Integer idProfilo) throws PersistenceException {
        String methodName = "findProfiloById";
        
        

        ProfiloDto dto = null;

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            EpaypaTProfilo entityProfilo = epaypaTProfiloDao.findOne(idProfilo);
            dto = toProfiloDto(entityProfilo);

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return dto;
    }

    @Override
    public List<ProfiloDto> findAllProfiloByIdUtenteAndIdEnte(Long idUtente, Integer idEnte) throws PersistenceException {
        String methodName = "findAllProfiloByIdUtenteAndIdEnte";
        
        
        

        List<ProfiloDto> dtoList = null;

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            List<EpaypaTProfilo> entityList = epaypaTProfiloDao.findAllByIdUtenteAndIdEnte(idUtente, idEnte);
            dtoList = toProfiloDtoList(entityList);

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return dtoList;
    }

    @Override
    public List<CduDto> findAllCduByIdRuolo(Integer idRuolo) throws PersistenceException {
        String methodName = "findAllCduByIdRuolo";
        
        

        List<CduDto> dtoList = null;

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            List<EpaypaTCdu> entityList = epaypaTCduDao.findAllByIdRuolo(idRuolo);
            dtoList = toCduDtoList(entityList);

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return dtoList;
    }

    @Override
    public List<Integer> findAllIdCodVersamentoByIdProfiloAndIdEnte(Integer idProfilo, Integer idEnte) throws PersistenceException {
        String methodName = "findAllIdCodVersamentoByIdProfiloAndIdEnte";
        
        
        

        List<Integer> intList = null;

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            intList = epaypaTCodiceVersamentoDao.findAllIdCodVersamentoByIdProfiloAndIdEnte(idProfilo, idEnte);

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return intList;
    }

    @Override
    public List<CodiceVersamentoDto> findAllCodiceVersamentoByIdProfiloAndIdEnte(Integer idProfilo, Integer idEnte) throws PersistenceException {
        String methodName = "findAllCodiceVersamentoByIdProfiloAndIdEnte";
        
        
        

        List<CodiceVersamentoDto> dtoList = null;

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            List<EpaypaTCodiceVersamento> entityList = epaypaTCodiceVersamentoDao.findAllByIdProfiloAndIdEnte(idProfilo, idEnte);
            dtoList = toCodiceVersamentoDtoList(entityList);

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return dtoList;
    }

    @Override
    public EnteDto findEnteByCod ( String codEnte ) throws PersistenceException {
        String methodName = "findEnteByCod";
        
        

        EnteDto dto = null;

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            EpaypaTEnte entityEnte = epaypaTEnteDao.findOneByCodFiscale ( codEnte );
            dto = toEnteDto ( entityEnte );

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return dto;
    }

    @Override
    public List<CodiceVersamentoDto> findAllCodVersamentoByIdEnteAndCodiceIn ( Integer idEnte, Collection<String> codici ) throws PersistenceException {
        String methodName = "findAllCodVersamentoByIdEnteAndCodiceIn";
        
        
        

        List<CodiceVersamentoDto> dtoList = null;

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            List<EpaypaTCodiceVersamento> entityList = epaypaTCodiceVersamentoDao.findAllCodVersamentoByIdEnteAndCodVersamentoIn (
                idEnte, codici );

            dtoList = toCodiceVersamentoDtoList ( entityList );

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return dtoList;
    }

    @Override
    public List<CodiceVersamentoDto> findAllCodVersamentoByIdEnteAndCodiceLike ( Integer idEnte, String codiceExpression ) throws PersistenceException {
        String methodName = "findAllCodVersamentoByIdEnteAndCodiceIn";
        
        
        

        List<CodiceVersamentoDto> dtoList = null;

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            List<EpaypaTCodiceVersamento> entityList = epaypaTCodiceVersamentoDao.findAllCodVersamentoByIdEnteAndCodVersamentoLike (
                idEnte, codiceExpression );

            dtoList = toCodiceVersamentoDtoList ( entityList );

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return dtoList;
    }

    @Override
    public Integer findIdTipoPagamentoREDS () throws PersistenceException {
        EpaypaDTipoPagamento entity = epaypaDTipoPagamentoDao.findOneByCodice ( "REDS" );
        if ( entity == null ) {
            return null;
        }
        return entity.getId ();
    }
    
    @Override
    public Integer findIdTipoPagamento (String codice) throws PersistenceException {
        EpaypaDTipoPagamento entity = epaypaDTipoPagamentoDao.findOneByCodice ( codice);
        if ( entity == null ) {
            return null;
        }
        return entity.getId ();
    }

}
