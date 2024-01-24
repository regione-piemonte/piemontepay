/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.impl;

import java.util.Collection;
import java.util.List;

import javax.persistence.TypedQuery;

import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.exception.PersistenceFoundMoreThanOneResultException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBaseImpl;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTCodiceVersamentoDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTCodiceVersamento;


public class EpaypaTCodiceVersamentoDaoImpl extends EpaypaDaoBaseImpl<Integer, EpaypaTCodiceVersamento> implements EpaypaTCodiceVersamentoDao {
	static private final String CLASSNAME = EpaypaTCodiceVersamentoDaoImpl.class.getSimpleName();

	@Override
	public String findCodVersamentoById(Integer idCodVersamento) throws PersistenceException {
		String methodName = "findCodVersamentoById";
		
		

		String cod = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			TypedQuery<String> query = entityManager.createNamedQuery("EpaypaTCodiceVersamento.findCodVersamentoByIdCodVersamento", String.class);
			query.setParameter("idCodiceVersamento", idCodVersamento);
			//
			List<String> resultList = query.getResultList();
			switch (resultList.size()) {
			case 0:
				log.warn("nessun risultato trovato per idCodVersamento:".concat(idCodVersamento.toString()));
				break;
			case 1:
				cod = resultList.get(0);
				break;
			default:
				throw new PersistenceFoundMoreThanOneResultException("codVersamento", "idCodVersamento", idCodVersamento);
			}
		} catch (Throwable e) {
			log.error("Errore imprevisto", e);
			throw new PersistenceException("Errore imprevisto in " + CLASSNAME + "::" + methodName, e);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return cod;
	}

	@Override
	public Integer findIdCodVersamentoByCodAndEnte(String codVersamento, Integer idEnte) throws PersistenceException {
		String methodName = "findIdCodVersamentoByCod";
		
		
		

		Integer id = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			TypedQuery<Integer> query = entityManager.createNamedQuery("EpaypaTCodiceVersamento.findIdCodVersamentoByCodVersamentoAndIdEnte", Integer.class);
			query.setParameter("codVersamento", codVersamento);
			query.setParameter("idEnte", idEnte);
			//
			List<Integer> resultList = query.getResultList();
			switch (resultList.size()) {
			case 0:
				log.warn("nessun risultato trovato per codVersamento:".concat(codVersamento).concat(" e idEnte:").concat("" + idEnte));
				break;
			case 1:
				id = resultList.get(0);
				break;
			default:
				throw new PersistenceFoundMoreThanOneResultException("idCodVersamento", "(codVersamento,idEnte)", "(" + codVersamento + "," + idEnte + ")");
			}
		} catch (Throwable e) {
			throw new PersistenceException("Errore imprevisto in " + CLASSNAME + "::" + methodName, e);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return id;
	}

	@Override
	public EpaypaTCodiceVersamento findOneByCodAndEnte(String codVersamento, Integer idEnte) throws PersistenceException {
		String methodName = "findOneByCod";
		
		
		

		EpaypaTCodiceVersamento entity = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			TypedQuery<EpaypaTCodiceVersamento> query = entityManager.createNamedQuery("EpaypaTCodiceVersamento.findOneByCodVersamentoAndIdEnte", EpaypaTCodiceVersamento.class);
			query.setParameter("codVersamento", codVersamento);
			query.setParameter("idEnte", idEnte);
			//
			List<EpaypaTCodiceVersamento> resultList = query.getResultList();
			switch (resultList.size()) {
			case 0:
				log.warn("nessun risultato trovato per codVersamento:".concat(codVersamento).concat(" e idEnte:").concat("" + idEnte));
				break;
			case 1:
				entity = resultList.get(0);
				break;
			default:
				throw new PersistenceFoundMoreThanOneResultException("idCodVersamento", "(codVersamento,idEnte)", "(" + codVersamento + "," + idEnte + ")");
			}
		} catch (Throwable e) {
			throw new PersistenceException("Errore imprevisto in " + CLASSNAME + "::" + methodName, e);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return entity;
	}

	@Override
	public List<Integer> findAllIdCodVersamentoByIdProfiloAndIdEnte(Integer idProfilo, Integer idEnte) throws PersistenceException {
		String methodName = "findAllIdCodVersamentoByIdProfiloAndIdEnte";
		
		
		

		List<Integer> entityList = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			TypedQuery<Integer> query = entityManager.createNamedQuery("EpaypaTCodiceVersamento.findAllIdCodVersamentoByIdProfiloAndIdEnte", Integer.class);
			query.setParameter("idProfilo", idProfilo);
			query.setParameter("idEnte", idEnte);
			//
			entityList = query.getResultList();

		} catch (Throwable e) {
			log.error("Errore imprevisto", e);
			throw new PersistenceException("Errore imprevisto in " + CLASSNAME + "::" + methodName, e);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return entityList;
	}

	@Override
	public List<EpaypaTCodiceVersamento> findAllByIdProfiloAndIdEnte(Integer idProfilo, Integer idEnte) throws PersistenceException {
		String methodName = "findAllByIdProfiloAndIdEnte";
		
		
		

		List<EpaypaTCodiceVersamento> entityList = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			TypedQuery<EpaypaTCodiceVersamento> query = entityManager.createNamedQuery("EpaypaTCodiceVersamento.findAllByIdProfiloAndIdEnte", EpaypaTCodiceVersamento.class);
			query.setParameter("idProfilo", idProfilo);
			query.setParameter("idEnte", idEnte);
			//
			entityList = query.getResultList();

		} catch (Throwable e) {
			log.error("Errore imprevisto", e);
			throw new PersistenceException("Errore imprevisto in " + CLASSNAME + "::" + methodName, e);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return entityList;
	}

    @Override
    public List<EpaypaTCodiceVersamento> findAllCodVersamentoByIdEnteAndCodVersamentoIn ( Integer idEnte, Collection<String> codici )
                    throws PersistenceException {
        String methodName = "findAllByIdProfiloAndIdEnte";
        
        
        

        List<EpaypaTCodiceVersamento> entityList = null;

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            TypedQuery<EpaypaTCodiceVersamento> query
                = entityManager.createNamedQuery ( "EpaypaTCodiceVersamento.findAllByIdEnteAndCodVersamentoIn", EpaypaTCodiceVersamento.class );
            query.setParameter ( "idEnte", idEnte );
            query.setParameter ( "codiciVersamento", codici );
            //
            entityList = query.getResultList ();

        } catch ( Throwable e ) {
            log.error ( "Errore imprevisto", e );
            throw new PersistenceException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return entityList;
    }

    @Override
    public List<EpaypaTCodiceVersamento> findAllCodVersamentoByIdEnteAndCodVersamentoLike ( Integer idEnte, String codiceExpression )
                    throws PersistenceException {
        String methodName = "findAllCodVersamentoByIdEnteAndCodVersamentoLike";
        
        
        

        List<EpaypaTCodiceVersamento> entityList = null;

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            TypedQuery<EpaypaTCodiceVersamento> query
                = entityManager.createNamedQuery ( "EpaypaTCodiceVersamento.findAllCodVersamentoByIdEnteAndCodVersamentoLike", EpaypaTCodiceVersamento.class );
            query.setParameter ( "idEnte", idEnte );
            query.setParameter ( "codiceExpression", codiceExpression );
            //
            entityList = query.getResultList ();

        } catch ( Throwable e ) {
            log.error ( "Errore imprevisto", e );
            throw new PersistenceException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return entityList;
    }

    @Override
    public List<EpaypaTCodiceVersamento> findAllByIdEnteAndCodiceVersamento ( Integer idEnte, String codiceVersamento ) throws PersistenceException {
        String methodName = "findAllByIdEnteAndCodiceVersamento";
        
        
        

        List<EpaypaTCodiceVersamento> entityList = null;

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            TypedQuery<EpaypaTCodiceVersamento> query
                = entityManager.createNamedQuery ( "EpaypaTCodiceVersamento.findAllByIdEnteAndCodiceVersamento", EpaypaTCodiceVersamento.class );
            query.setParameter ( "codVersamento", codiceVersamento );
            query.setParameter ( "idEnte", idEnte );
            //
            entityList = query.getResultList ();

        } catch ( Throwable e ) {
            log.error ( "Errore imprevisto", e );
            throw new PersistenceException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return entityList;
    }

    @Override
    public List<EpaypaTCodiceVersamento> findByIdEnte ( Integer idEnte ) throws PersistenceException {
        String methodName = "findByIdEnte";
        
        

        List<EpaypaTCodiceVersamento> entityList = null;

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            TypedQuery<EpaypaTCodiceVersamento> query
                = entityManager.createNamedQuery ( "EpaypaTCodiceVersamento.findByIdEnte", EpaypaTCodiceVersamento.class );
            query.setParameter ( "idEnte", idEnte );
            //
            entityList = query.getResultList ();

        } catch ( Throwable e ) {
            log.error ( "Errore imprevisto", e );
            throw new PersistenceException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return entityList;
    }
}
