/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.dao.impl;

import static it.csi.epay.epaywsosrv.util.Util.date2strdatetime;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import it.csi.epay.epaywsosrv.enumeration.IssueEnum;
import it.csi.epay.epaywsosrv.exception.PersistenceException;
import it.csi.epay.epaywsosrv.persistence.dao.EPaywsoDaoBaseImpl;
import it.csi.epay.epaywsosrv.persistence.dao.interf.EPaywsoTApplicativoDao;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoTApplicativo;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoTEnte;
import it.csi.epay.epaywsosrv.util.LogAndWatch;

public class EPaywsoTApplicativoDaoImpl extends EPaywsoDaoBaseImpl<String, EPaywsoTApplicativo> implements EPaywsoTApplicativoDao {
	static private final String CLASSNAME = EPaywsoTApplicativoDaoImpl.class.getSimpleName();

	@Override
	public EPaywsoTApplicativo findOneById(Integer id, Timestamp timestamp) throws PersistenceException {
		String methodName = "findOneById";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("id", id);
		lw.addParam("timestamp", timestamp);

		EPaywsoTApplicativo entity = null;

		try {
			lw.start();

			TypedQuery<EPaywsoTApplicativo> query = entityManager.createNamedQuery("EPaywsoTApplicativo.findOneById", EPaywsoTApplicativo.class);
			query.setParameter("idApplicativo", id);
			query.setParameter("dt", timestamp);
			//
			List<EPaywsoTApplicativo> entityList = query.getResultList();
			switch (entityList.size()) {
			case 0:
				lw.warn("nessun risultato trovato per idApplicativo:" + id + " che sia valido alla data/ora:" + date2strdatetime(timestamp));
				break;
			case 1:
				entity = entityList.get(0);
				break;
			default:
				throw new PersistenceException(IssueEnum.DB_MORE_THAN_ONE_RESULT_AT_TIME_4ARGS, "applicativo", "idApplicativo", "" + id, date2strdatetime(timestamp));
			}
		} catch (Throwable e) {
			handlePersistenceException(e);

		} finally {
			lw.stop();
		}

		return entity;
	}

	@Override
	public List<EPaywsoTApplicativo> findAllByIdEnte(Integer idEnte, Timestamp timestamp) throws PersistenceException {
		String methodName = "findAllByIdEnte";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("idEnte", idEnte);
		lw.addParam("timestamp", timestamp);

		List<EPaywsoTApplicativo> entityList = new ArrayList<EPaywsoTApplicativo>();

		try {
			lw.start();

			TypedQuery<EPaywsoTApplicativo> query = entityManager.createNamedQuery("EPaywsoTApplicativo.findAllByIdEnte", EPaywsoTApplicativo.class);
			query.setParameter("idEnte", idEnte);
			query.setParameter("dt", timestamp);
			//
			entityList = query.getResultList();
			if (entityList.isEmpty()) {
				lw.warn("nessun risultato trovato per idEnte:" + idEnte + " che sia valido alla data/ora:" + date2strdatetime(timestamp));
			}
		} catch (Throwable e) {
			handlePersistenceException(e);

		} finally {
			lw.stop();
		}

		return entityList;
	}

	@Override
	public EPaywsoTApplicativo findOneByIdEnteAndDescrizioneAndCodiceModalitaIntegrazione(Integer idEnte, Timestamp timestamp, String descrizione, String codModInt)
			throws PersistenceException {
		String methodName = "findOneByIdEnteAndDescrizione";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("idEnte", idEnte);
		lw.addParam("timestamp", timestamp);
		lw.addParam("descrizione", descrizione);
        lw.addParam("codiceModalitaIntegrazione", codModInt);

		EPaywsoTApplicativo entity = new EPaywsoTApplicativo();

		try {
			lw.start();

			TypedQuery<EPaywsoTApplicativo> query = entityManager.createNamedQuery("EPaywsoTApplicativo.findOneByIdEnteAndDescrizioneLikeAndCodiceModalitaIntegrazione", EPaywsoTApplicativo.class);
			query.setParameter("idEnte", idEnte);
			query.setParameter("dt", timestamp);
			query.setParameter("descrizione", descrizione);
			query.setParameter("codiceModalitaIntegrazione", codModInt);
			//
            entity = query.getSingleResult();
			if (null == entity) {
				lw.warn("nessun risultato trovato per idEnte:" + idEnte + " che sia valido alla data/ora:" + date2strdatetime(timestamp) + " e abbia descrizione like " + descrizione);
			}
		} catch (Throwable e) {
			handlePersistenceException(e);

		} finally {
			lw.stop();
		}

		return entity;
	}
	
	@Override
	public EPaywsoTApplicativo findOneByIdEnteAndCodiceModalitaIntegrazione(Integer idEnte, Timestamp timestamp, String codiceModalitaIntegrazione)
			throws PersistenceException {
		String methodName = "findOneByIdEnteAndCodiceModalitaIntegrazione";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);
		lw.addParam("idEnte", idEnte);
		lw.addParam("timestamp", timestamp);
		lw.addParam("codiceModalitaIntegrazione", codiceModalitaIntegrazione);

		EPaywsoTApplicativo entity = new EPaywsoTApplicativo();

		try {
			lw.start();

			TypedQuery<EPaywsoTApplicativo> query = entityManager.createNamedQuery("EPaywsoTApplicativo.findOneByIdEnteAndCodiceModalitaIntegrazione", EPaywsoTApplicativo.class);
			query.setParameter("idEnte", idEnte);
			query.setParameter("dt", timestamp);
			query.setParameter("codiceModalitaIntegrazione", codiceModalitaIntegrazione);
			//
			entity = query.getSingleResult();
			if (null == entity) {
				lw.warn("nessun risultato trovato per idEnte:" + idEnte + " che sia valido alla data/ora:" + date2strdatetime(timestamp) + " e abbia codice modalita integrazione " + codiceModalitaIntegrazione);
			}
		} catch (Throwable e) {
			handlePersistenceException(e);

		} finally {
			lw.stop();
		}

		return entity;
	}
	
	@Override
	public Integer findIdApplicativoMax () throws PersistenceException {
        String methodName = "findIdEnteMax";
        LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);

        Integer id = null;

        try {
            lw.start();

            TypedQuery<EPaywsoTApplicativo> query = entityManager.createNamedQuery("EPaywsoTApplicativo.findIdApplicativoMax", EPaywsoTApplicativo.class);
            query.setMaxResults ( 1 );
            EPaywsoTApplicativo item = query.getSingleResult ();
            
            if(item == null) id = 0;
            else 
                id = item.getIdApplicativo ();
            
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

}
