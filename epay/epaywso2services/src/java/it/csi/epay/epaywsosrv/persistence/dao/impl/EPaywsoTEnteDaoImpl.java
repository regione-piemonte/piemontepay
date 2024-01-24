/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.dao.impl;

import static it.csi.epay.epaywsosrv.util.Util.date2strdatetime;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.TypedQuery;

import it.csi.epay.epaywsosrv.enumeration.IssueEnum;
import it.csi.epay.epaywsosrv.exception.PersistenceException;
import it.csi.epay.epaywsosrv.exception.PersistenceFoundMoreThanOneResultException;
import it.csi.epay.epaywsosrv.persistence.dao.EPaywsoDaoBaseImpl;
import it.csi.epay.epaywsosrv.persistence.dao.interf.EPaywsoTEnteDao;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoTEnte;
import it.csi.epay.epaywsosrv.util.LogAndWatch;

public class EPaywsoTEnteDaoImpl extends EPaywsoDaoBaseImpl<Integer, EPaywsoTEnte> implements EPaywsoTEnteDao {
	static private final String CLASSNAME = EPaywsoTEnteDaoImpl.class.getSimpleName();

	@Override
	public String findCodFiscaleById(Integer id, Timestamp timestamp) throws PersistenceException {
		String methodName = "findCodFiscaleById";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("id", id);
		lw.addParam("timestamp", timestamp);

		String cod = null;

		try {
			lw.start();

			TypedQuery<String> query = entityManager.createNamedQuery("EPaywsoTEnte.findCodFiscaleByIdEnte", String.class);
			query.setParameter("idEnte", id);
			query.setParameter("dt", timestamp);
			//
			List<String> list = query.getResultList();
			switch (list.size()) {
			case 0:
				lw.warn("nessun risultato trovato per idEnte:" + id + " che sia valido alla data/ora:" + date2strdatetime(timestamp));
				break;
			case 1:
				cod = list.get(0);
				break;
			default:
				throw new PersistenceException(IssueEnum.DB_MORE_THAN_ONE_RESULT_AT_TIME_4ARGS, "codFiscaleEnte", "idEnte", "" + id, date2strdatetime(timestamp));
			}
		} catch (Throwable e) {
			handlePersistenceException(e);

		} finally {
			lw.stop();
		}

		return cod;
	}

	public Integer findIdEnteMax () throws PersistenceException {
        String methodName = "findIdEnteMax";
        LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);

        Integer id = null;

        try {
            lw.start();

            TypedQuery<EPaywsoTEnte> query = entityManager.createNamedQuery("EPaywsoTEnte.findIdEnteMax", EPaywsoTEnte.class);
            query.setMaxResults ( 1 );
            EPaywsoTEnte item = query.getSingleResult ();
            
            if(item == null) id = 0;
            else 
                id = item.getIdEnte ();
            
        } catch (Throwable e) {
            lw.error("Errore imprevisto", e);
            throw new PersistenceException("Errore imprevisto in " + CLASSNAME, "METODO:" + methodName, e);

        } finally {
            lw.stop();
        }

        if(id == null) {
            id = 0;
        }
        
        return id;
    }	
	
    @Override
    public Integer findIdEnteByCodFiscale(String codFiscaleEnte) throws PersistenceException {
        String methodName = "findIdEnteByCodFiscale";
        LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
        lw.addParam("codFiscaleEnte", codFiscaleEnte);

        Integer id = null;

        try {
            lw.start();

            TypedQuery<Integer> query = entityManager.createNamedQuery("EPaywsoTEnte.findIdEnteByCodFiscaleEnte", Integer.class);
            query.setParameter("codFiscaleEnte", codFiscaleEnte);
            //
            List<Integer> resultList = query.getResultList();
            switch (resultList.size()) {
            case 0:
                lw.warn("nessun risultato trovato per codFiscaleEnte:".concat(codFiscaleEnte));
                break;
            case 1:
                id = resultList.get(0);
                break;
            default:
                throw new PersistenceFoundMoreThanOneResultException("idEnte", "codFiscaleEnte", codFiscaleEnte);
            }
        } catch (Throwable e) {
            lw.error("Errore imprevisto", e);
            throw new PersistenceException("000","Errore imprevisto in " + CLASSNAME + "::" + methodName, e);

        } finally {
            lw.stop();
        }

        return id;
    }
    
    @Override
    public List<EPaywsoTEnte> findAllByCodUtente(String codUtente) throws PersistenceException {
        String methodName = "findAllByCodUtente";
        LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
        lw.addParam("codUtente", codUtente);

        List<EPaywsoTEnte> entityList = null;

        try {
            lw.start();

            TypedQuery<EPaywsoTEnte> query = entityManager.createNamedQuery("EPaywsoTEnte.findAllByCodUtente", EPaywsoTEnte.class);
            query.setParameter("codUtente", codUtente);
            //
            entityList = query.getResultList();

        } catch (Throwable e) {
            lw.error("Errore imprevisto", e);
            throw new PersistenceException("000","Errore imprevisto in " + CLASSNAME + "::" + methodName, e);

        } finally {
            lw.stop();
        }

        return entityList;
    }
    
    @Override
    public EPaywsoTEnte findOneByCodFiscale(String codFiscaleEnte) throws PersistenceException {
        String methodName = "findOneByCodFiscale";
        LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
        lw.addParam("codFiscaleEnte", codFiscaleEnte);

        EPaywsoTEnte entity = null;

        try {
            lw.start();

            TypedQuery<EPaywsoTEnte> query = entityManager.createNamedQuery("EPaywsoTEnte.findOneByCodFiscaleEnte", EPaywsoTEnte.class);
            query.setParameter("codFiscaleEnte", codFiscaleEnte);
            //
            List<EPaywsoTEnte> resultList = query.getResultList();
            switch (resultList.size()) {
            case 0:
                lw.warn("nessun risultato trovato per codFiscaleEnte:".concat(codFiscaleEnte));
                break;
            case 1:
                entity = resultList.get(0);
                break;
            default:
                throw new PersistenceFoundMoreThanOneResultException("ente", "codFiscaleEnte", codFiscaleEnte);
            }
        } catch (Throwable e) {
            lw.error("Errore imprevisto", e);
            throw new PersistenceException("000","Errore imprevisto in " + CLASSNAME + "::" + methodName, e);

        } finally {
            lw.stop();
        }

        return entity;
    }
    
    @Override
    public String findCodFiscaleEnteById(Integer idEnte) throws PersistenceException {
        String methodName = "findCodFiscaleEnteById";
        LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
        lw.addParam("idEnte", idEnte);

        String cod = null;

        try {
            lw.start();

            TypedQuery<String> query = entityManager.createNamedQuery("EPaywsoTEnte.findCodFiscaleEnteByIdEnte", String.class);
            query.setParameter("idEnte", idEnte);
            //
            List<String> resultList = query.getResultList();
            switch (resultList.size()) {
            case 0:
                lw.warn("nessun risultato trovato per idEnte:".concat(idEnte.toString()));
                break;
            case 1:
                cod = resultList.get(0);
                break;
            default:
                throw new PersistenceFoundMoreThanOneResultException("codFiscaleEnte", "idEnte", idEnte);
            }
        } catch (Throwable e) {
            lw.error("Errore imprevisto", e);
            throw new PersistenceException("000","Errore imprevisto in " + CLASSNAME + "::" + methodName, e);

        } finally {
            lw.stop();
        }

        return cod;
    }
    
	@Override
	public Integer findIdByCodFiscale(String codFiscale, Timestamp timestamp) throws PersistenceException {
		String methodName = "findIdByCodFiscale";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("codFiscale", codFiscale);
		lw.addParam("timestamp", timestamp);

		Integer id = null;

		try {
			lw.start();

			TypedQuery<Integer> query = entityManager.createNamedQuery("EPaywsoTEnte.findIdEnteByCodFiscale", Integer.class);
			query.setParameter("codFiscaleEnte", codFiscale);
			query.setParameter("dt", timestamp);
			//
			List<Integer> list = query.getResultList();
			switch (list.size()) {
			case 0:
				lw.warn("nessun risultato trovato per codFiscaleEnte:" + codFiscale + " che sia valido alla data/ora:" + date2strdatetime(timestamp));
				break;
			case 1:
				id = list.get(0);
				break;
			default:
				throw new PersistenceException(IssueEnum.DB_MORE_THAN_ONE_RESULT_AT_TIME_4ARGS, "idEnte", "codFiscaleEnte", codFiscale, date2strdatetime(timestamp));
			}
		} catch (Throwable e) {
			handlePersistenceException(e);

		} finally {
			lw.stop();
		}

		return id;
	}

	@Override
	public EPaywsoTEnte findOneById(Integer id, Timestamp timestamp) throws PersistenceException {
		String methodName = "findOneById";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("id", id);
		lw.addParam("timestamp", timestamp);

		EPaywsoTEnte entity = null;

		try {
			lw.start();

			TypedQuery<EPaywsoTEnte> query = entityManager.createNamedQuery("EPaywsoTEnte.findOneByIdEnte", EPaywsoTEnte.class);
			query.setParameter("idEnte", id);
			query.setParameter("dt", timestamp);
			//
			List<EPaywsoTEnte> entityList = query.getResultList();
			switch (entityList.size()) {
			case 0:
				lw.warn("nessun risultato trovato per idEnte:" + id + " che sia valido alla data/ora:" + date2strdatetime(timestamp));
				break;
			case 1:
				entity = entityList.get(0);
				break;
			default:
				throw new PersistenceException(IssueEnum.DB_MORE_THAN_ONE_RESULT_AT_TIME_4ARGS, "ente", "idEnte", "" + id, date2strdatetime(timestamp));
			}
		} catch (Throwable e) {
			handlePersistenceException(e);

		} finally {
			lw.stop();
		}

		return entity;
	}

	@Override
	public EPaywsoTEnte findOneByCodFiscale(String codFiscale, Timestamp timestamp) throws PersistenceException {
		String methodName = "findOneByCodFiscale";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("codFiscale", codFiscale);
		lw.addParam("timestamp", timestamp);

		EPaywsoTEnte entity = null;

		try {
			lw.start();

			TypedQuery<EPaywsoTEnte> query = entityManager.createNamedQuery("EPaywsoTEnte.findOneByCodFiscale", EPaywsoTEnte.class);
			query.setParameter("codFiscaleEnte", codFiscale);
			query.setParameter("dt", timestamp);
			//
			List<EPaywsoTEnte> entityList = query.getResultList();
			switch (entityList.size()) {
			case 0:
				lw.warn("nessun risultato trovato per codFiscaleEnte:" + codFiscale + " che sia valido alla data/ora:" + date2strdatetime(timestamp));
				break;
			case 1:
				entity = entityList.get(0);
				break;
			default:
				throw new PersistenceException(IssueEnum.DB_MORE_THAN_ONE_RESULT_AT_TIME_4ARGS, "ente", "codFiscaleEnte", codFiscale, date2strdatetime(timestamp));
			}
		} catch (Throwable e) {
			handlePersistenceException(e);

		} finally {
			lw.stop();
		}

		return entity;
	}

}
