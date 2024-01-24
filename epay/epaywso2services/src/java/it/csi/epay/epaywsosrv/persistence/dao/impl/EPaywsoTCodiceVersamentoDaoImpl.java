/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.dao.impl;

import static it.csi.epay.epaywsosrv.util.Util.date2strdatetime;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import javax.persistence.TypedQuery;

import it.csi.epay.epaywsosrv.enumeration.IssueEnum;
import it.csi.epay.epaywsosrv.exception.PersistenceException;
import it.csi.epay.epaywsosrv.exception.PersistenceFoundMoreThanOneResultException;
import it.csi.epay.epaywsosrv.persistence.dao.EPaywsoDaoBaseImpl;
import it.csi.epay.epaywsosrv.persistence.dao.interf.EPaywsoTCodiceVersamentoDao;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoTCodiceVersamento;
import it.csi.epay.epaywsosrv.util.LogAndWatch;

public class EPaywsoTCodiceVersamentoDaoImpl extends EPaywsoDaoBaseImpl<Integer, EPaywsoTCodiceVersamento> implements EPaywsoTCodiceVersamentoDao {
	static private final String CLASSNAME = EPaywsoTCodiceVersamentoDaoImpl.class.getSimpleName();

	@Override
	public String findCodById(Integer id, Timestamp timestamp) throws PersistenceException {
		String methodName = "findCodById";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("id", id);
		lw.addParam("timestamp", timestamp);

		String cod = null;

		try {
			lw.start();

			TypedQuery<String> query = entityManager.createNamedQuery("EPaywsoTCodiceVersamento.findCodVersamentoByIdCodiceVersamento", String.class);
			query.setParameter("idCodiceVersamento", id);
			query.setParameter("dt", timestamp);
			//
			List<String> list = query.getResultList();
			switch (list.size()) {
			case 0:
				lw.warn("nessun risultato trovato per idCodiceVersamento:" + id + " che sia valido alla data/ora:" + date2strdatetime(timestamp));
				break;
			case 1:
				cod = list.get(0);
				break;
			default:
				throw new PersistenceException(IssueEnum.DB_MORE_THAN_ONE_RESULT_AT_TIME_4ARGS, "codVersamento", "idCodiceVersamento", "" + id, date2strdatetime(timestamp));
			}
		} catch (Throwable e) {
			handlePersistenceException(e);

		} finally {
			lw.stop();
		}

		return cod;
	}

	@Override
	public List<Integer> findAllIdByCod(String cod, Timestamp timestamp) throws PersistenceException {
		String methodName = "findAllIdByCod";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("cod", cod);
		lw.addParam("timestamp", timestamp);

		List<Integer> idList = new ArrayList<Integer>();

		try {
			lw.start();

			TypedQuery<Integer> query = entityManager.createNamedQuery("EPaywsoTCodiceVersamento.findIdCodiceVersamentoByCodVersamento", Integer.class);
			query.setParameter("codVersamento", cod);
			query.setParameter("dt", timestamp);
			//
			idList = query.getResultList();
			if (idList.isEmpty()) {
				lw.warn("nessun risultato trovato per codVersamento:" + cod + " che sia valido alla data/ora:" + date2strdatetime(timestamp));
			}
		} catch (Throwable e) {
			handlePersistenceException(e);

		} finally {
			lw.stop();
		}

		return idList;
	}

	@Override
	public EPaywsoTCodiceVersamento findOneById(Integer id, Timestamp timestamp) throws PersistenceException {
		String methodName = "findOneById";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("id", id);
		lw.addParam("timestamp", timestamp);

		EPaywsoTCodiceVersamento entity = null;

		try {
			lw.start();

			TypedQuery<EPaywsoTCodiceVersamento> query = entityManager.createNamedQuery("EPaywsoTCodiceVersamento.findOneByIdCodiceVersamento", EPaywsoTCodiceVersamento.class);
			query.setParameter("idCodiceVersamento", id);
			query.setParameter("dt", timestamp);
			//
			List<EPaywsoTCodiceVersamento> entityList = query.getResultList();
			switch (entityList.size()) {
			case 0:
				lw.warn("nessun risultato trovato per idCodiceVersamento:" + id + " che sia valido alla data/ora:" + date2strdatetime(timestamp));
				break;
			case 1:
				entity = entityList.get(0);
				break;
			default:
				throw new PersistenceException(IssueEnum.DB_MORE_THAN_ONE_RESULT_AT_TIME_4ARGS, "codiceVersamento", "idCodiceVersamento", "" + id, date2strdatetime(timestamp));
			}
		} catch (Throwable e) {
			handlePersistenceException(e);

		} finally {
			lw.stop();
		}

		return entity;
	}

	@Override
	public List<EPaywsoTCodiceVersamento> findAllByCod(String cod, Timestamp timestamp) throws PersistenceException {
		String methodName = "findAllByCod";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("cod", cod);
		lw.addParam("timestamp", timestamp);

		List<EPaywsoTCodiceVersamento> entityList = new ArrayList<EPaywsoTCodiceVersamento>();

		try {
			lw.start();

			TypedQuery<EPaywsoTCodiceVersamento> query = entityManager.createNamedQuery("EPaywsoTCodiceVersamento.findAllByCodVersamento", EPaywsoTCodiceVersamento.class);
			query.setParameter("codVersamento", cod);
			query.setParameter("dt", timestamp);
			//
			entityList = query.getResultList();
			if (entityList.isEmpty()) {
				lw.warn("nessun risultato trovato per codVersamento:" + cod + " che sia valido alla data/ora:" + date2strdatetime(timestamp));
			}
		} catch (Throwable e) {
			handlePersistenceException(e);

		} finally {
			lw.stop();
		}

		return entityList;
	}
	
	//--ADDED
    @Override
    public EPaywsoTCodiceVersamento findOneByCodAndEnte(String codVersamento, Integer idEnte) throws PersistenceException {
        return findOneByCodAndEnteAttivo(codVersamento,idEnte,new Timestamp ( new Date().getTime () ));
    }
	
    @Override
    public EPaywsoTCodiceVersamento findOneByCodAndEnteAttivo(String codVersamento, Integer idEnte,Timestamp now) throws PersistenceException {
        String methodName = "findOneByCodAndEnte";
        LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
        lw.addParam("codVersamento", codVersamento);
        lw.addParam("idEnte", idEnte);
        lw.addParam("timestamp", now);

        EPaywsoTCodiceVersamento entity = null;

        try {
            lw.start();

            TypedQuery<EPaywsoTCodiceVersamento> query = entityManager.createNamedQuery("EPaywsoTCodiceVersamento.findOneByCodVersamentoAndIdEnteAttivo", EPaywsoTCodiceVersamento.class);
            query.setParameter("codVersamento", codVersamento);
            query.setParameter("idEnte", idEnte);
            query.setParameter("dt", now);
            //
            List<EPaywsoTCodiceVersamento> resultList = query.getResultList();
            switch (resultList.size()) {
            case 0:
                lw.warn("nessun risultato trovato per codVersamento:".concat(codVersamento).concat(" e idEnte:").concat("" + idEnte).concat ( "" + now.toLocalDateTime ()));
                break;
            case 1:
                entity = resultList.get(0);
                break;
            default:
                throw new PersistenceFoundMoreThanOneResultException("idCodVersamento", "(codVersamento,idEnte)", "(" + codVersamento + "," + idEnte + ")");
            }
        } catch (Throwable e) {
            throw new PersistenceException("000","Errore imprevisto in " + CLASSNAME + "::" + methodName, e);

        } finally {
            lw.stop();
        }

        return entity;
    }
    
    @Override
    public List<EPaywsoTCodiceVersamento> findAllByCodAndEnteAttivo(String codVersamento, Integer idEnte) throws PersistenceException {
        String methodName = "findAllByCodAndEnteAttivo";
        LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
        lw.addParam("codVersamento", codVersamento);
        lw.addParam("idEnte", idEnte);
        Timestamp now = new Timestamp ( new Date().getTime () );
        List<EPaywsoTCodiceVersamento> entity = null;

        try {
            lw.start();

            TypedQuery<EPaywsoTCodiceVersamento> query = entityManager.createNamedQuery("EPaywsoTCodiceVersamento.findOneByCodVersamentoAndIdEnteAttivo", EPaywsoTCodiceVersamento.class);
            query.setParameter("codVersamento", codVersamento);
            query.setParameter("idEnte", idEnte);
            query.setParameter("dt", now);
            //
            List<EPaywsoTCodiceVersamento> resultList = query.getResultList();
            if(resultList == null || resultList.isEmpty () ) {
                lw.warn("nessun risultato trovato per codVersamento:".concat(codVersamento).concat(" e idEnte:").concat("" + idEnte).concat ( "" + now.toLocalDateTime ()));
            } else {
                entity = resultList;
            }
        } catch (Exception e) {
            throw new PersistenceException("000","Errore imprevisto in " + CLASSNAME + "::" + methodName, e);

        } finally {
            lw.stop();
        }

        return entity;
    }
	
    @Override
    public List<EPaywsoTCodiceVersamento> findAllByIdEnteAndCodiceVersamento ( Integer idEnte, String codiceVersamento ) throws PersistenceException {
        String methodName = "findAllByIdEnteAndCodiceVersamento";
        LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
        lw.addParam("codVersamento", codiceVersamento);
        lw.addParam("idEnte", idEnte);

        List<EPaywsoTCodiceVersamento> entityList = null;

        try {
            lw.start();

            TypedQuery<EPaywsoTCodiceVersamento> query = entityManager.createNamedQuery("EPaywsoTCodiceVersamento.findAllByIdEnteAndCodiceVersamento", EPaywsoTCodiceVersamento.class);
            query.setParameter("codVersamento", codiceVersamento);
            query.setParameter("idEnte", idEnte);
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
    public List<EPaywsoTCodiceVersamento> findByIdEnte ( Integer idEnte ) throws PersistenceException {
        String methodName = "findByIdEnte";
        LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
        lw.addParam("idEnte", idEnte);

        List<EPaywsoTCodiceVersamento> entityList = null;

        try {
            lw.start();

            TypedQuery<EPaywsoTCodiceVersamento> query = entityManager.createNamedQuery("EPaywsoTCodiceVersamento.findByIdEnte", EPaywsoTCodiceVersamento.class);
            query.setParameter("idEnte", idEnte);
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
    public int findCodVersIdMax ()throws PersistenceException {
        String methodName = "findByIdEnte";
        LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);

        List<EPaywsoTCodiceVersamento> entityList = null;

        try {
            lw.start();

            TypedQuery<EPaywsoTCodiceVersamento> query = entityManager.createNamedQuery("EPaywsoTCodiceVersamento.findAllDesc", EPaywsoTCodiceVersamento.class);
            query.setMaxResults ( 1 );
            //
            entityList = query.getResultList();

        } catch (Throwable e) {
            lw.error("Errore imprevisto", e);
            throw new PersistenceException("000","Errore imprevisto in " + CLASSNAME + "::" + methodName, e);

        } finally {
            lw.stop();
        }

        if((entityList == null) || (entityList.size () <= 0)) return 0;
        return entityList.get ( 0 ).getIdCodiceVersamento ();
    }

}
