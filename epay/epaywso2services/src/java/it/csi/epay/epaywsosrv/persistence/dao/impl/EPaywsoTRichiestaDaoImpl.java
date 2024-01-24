/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.dao.impl;

import static it.csi.epay.epaywsosrv.enumeration.ColumnNameRichiestaEnum.CF_ENTE_ORIGINE;
import static it.csi.epay.epaywsosrv.enumeration.ColumnNameRichiestaEnum.CODICE_VERSAMENTO;
import static it.csi.epay.epaywsosrv.enumeration.ColumnNameRichiestaEnum.DATA_INSERIMENTO_RICHIESTA;
import static it.csi.epay.epaywsosrv.enumeration.ColumnNameRichiestaEnum.DATA_INVIO_AL_DESTINATARIO;
import static it.csi.epay.epaywsosrv.enumeration.ColumnNameRichiestaEnum.ID_MESSAGGIO;
import static it.csi.epay.epaywsosrv.enumeration.ColumnNameRichiestaEnum.ID_RICHIESTA;
import static it.csi.epay.epaywsosrv.enumeration.ColumnNameRichiestaEnum.ID_STATO_RICHIESTA;
import static it.csi.epay.epaywsosrv.enumeration.ColumnNameRichiestaEnum.ID_TIPO_RICHIESTA;
import static it.csi.epay.epaywsosrv.enumeration.ColumnNameRichiestaEnum.PAGAMENTO_SPONTANEO;
import static it.csi.epay.epaywsosrv.util.Util.getLaterDate;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import it.csi.epay.epaywsosrv.dto.CriterioOrdinamentoDto;
import it.csi.epay.epaywsosrv.dto.PaginazioneDto;
import it.csi.epay.epaywsosrv.dto.RichiestaLightFilterDto;
import it.csi.epay.epaywsosrv.enumeration.ColumnNameRichiestaEnum;
import it.csi.epay.epaywsosrv.enumeration.IssueEnum;
import it.csi.epay.epaywsosrv.enumeration.StatoRichiestaEnum;
import it.csi.epay.epaywsosrv.exception.PersistenceException;
import it.csi.epay.epaywsosrv.persistence.dao.EPaywsoDaoBaseImpl;
import it.csi.epay.epaywsosrv.persistence.dao.interf.EPaywsoTRichiestaDao;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoTRichiesta;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoTRichiestaLight;
import it.csi.epay.epaywsosrv.util.LogAndWatch;


public class EPaywsoTRichiestaDaoImpl extends EPaywsoDaoBaseImpl<Long, EPaywsoTRichiesta> implements EPaywsoTRichiestaDao {

	static private final String CLASSNAME = EPaywsoTRichiestaDaoImpl.class.getSimpleName ();

	@Override
	public String findMessageUUIDById ( Long id ) throws PersistenceException {
		String methodName = "findMessageUUIDById";
		LogAndWatch lw = new LogAndWatch ( log, CLASSNAME, methodName );
		lw.addParam ( "idRichiesta", id );

		String cod = null;

		try {
			lw.start ();

			TypedQuery<String> query = entityManager.createNamedQuery ( "EPaywsoTRichiesta.findMessageUUIDById", String.class );
			query.setParameter ( "idRichiesta", id );
			//
			List<String> list = query.getResultList ();
			switch ( list.size () ) {
			case 0 :
				lw.warn ( "nessun risultato trovato per idRichiesta:" + id );
				break;
			case 1 :
				cod = list.get ( 0 );
				break;
			default :
				// N.B. non puo mai verificarsi fintantoche idRichiesta chiave
				throw new PersistenceException ( IssueEnum.DB_MORE_THAN_ONE_RESULT_3ARGS, "messageUUID", "idRichiesta", "" + id );
			}
		} catch ( Throwable e ) {
			handlePersistenceException ( e );

		} finally {
			lw.stop ();
		}

		return cod;
	}

	@Override
	public Long findIdByMessageUUID ( String messageUUID ) throws PersistenceException {
		String methodName = "findIdByMessageUUID";
		LogAndWatch lw = new LogAndWatch ( log, CLASSNAME, methodName );
		lw.addParam ( "messageUUID", messageUUID );

		Long id = null;

		try {
			lw.start ();

			TypedQuery<Long> query = entityManager.createNamedQuery ( "EPaywsoTRichiesta.findIdByMessageUUID", Long.class );
			query.setParameter ( "messageUUID", messageUUID );
			//
			List<Long> list = query.getResultList ();
			switch ( list.size () ) {
			case 0 :
				lw.warn ( "nessun risultato trovato per messageUUID:" + messageUUID );
				break;
			case 1 :
				id = list.get ( 0 );
				break;
			default :
				// N.B. non puo mai verificarsi fintantoche messageUUID UNIQUE
				throw new PersistenceException ( IssueEnum.DB_MORE_THAN_ONE_RESULT_3ARGS, "idRichiesta", "messageUUID", messageUUID );
			}
		} catch ( Throwable e ) {
			handlePersistenceException ( e );

		} finally {
			lw.stop ();
		}

		return id;
	}

	@Override
	public EPaywsoTRichiesta findOneByMessageUUID ( String messageUUID ) throws PersistenceException {
		String methodName = "findOneByMessageUUID";
		LogAndWatch lw = new LogAndWatch ( log, CLASSNAME, methodName );
		lw.addParam ( "messageUUID", messageUUID );

		EPaywsoTRichiesta entity = null;

		try {
			lw.start ();

			TypedQuery<EPaywsoTRichiesta> query = entityManager.createNamedQuery ( "EPaywsoTRichiesta.findOneByMessageUUID", entityClass );
			query.setParameter ( "messageUUID", messageUUID );
			//
			List<EPaywsoTRichiesta> entityList = query.getResultList ();
			switch ( entityList.size () ) {
			case 0 :
				lw.warn ( "nessun risultato trovato per messageUUID:" + messageUUID );
				break;
			case 1 :
				entity = entityList.get ( 0 );
				break;
			default :
				// N.B. non puo mai verificarsi fintantoche messageUUID UNIQUE
				throw new PersistenceException ( IssueEnum.DB_MORE_THAN_ONE_RESULT_3ARGS, "richiesta", "messageUUID", messageUUID );
			}
		} catch ( Throwable e ) {
			handlePersistenceException ( e );

		} finally {
			lw.stop ();
		}

		return entity;
	}
	
	@Override
    public EPaywsoTRichiestaLight findOneByMessageUUIDLight ( String messageUUID ) throws PersistenceException {
        String methodName = "findOneByMessageUUIDLight";
        LogAndWatch lw = new LogAndWatch ( log, CLASSNAME, methodName );
        lw.addParam ( "messageUUID", messageUUID );

        EPaywsoTRichiestaLight entity = null;

        try {
            lw.start ();

            TypedQuery<EPaywsoTRichiestaLight> query = entityManager.createNamedQuery ( "EPaywsoTRichiestaLight.findOneByMessageUUIDLight", EPaywsoTRichiestaLight.class );
            query.setParameter ( "messageUUID", messageUUID );
            //
            List<EPaywsoTRichiestaLight> entityList = query.getResultList ();
            switch ( entityList.size () ) {
            case 0 :
                lw.warn ( "nessun risultato trovato per messageUUID:" + messageUUID );
                break;
            case 1 :
                entity = entityList.get ( 0 );
                break;
            default :
                // N.B. non puo mai verificarsi fintantoche messageUUID UNIQUE
                throw new PersistenceException ( IssueEnum.DB_MORE_THAN_ONE_RESULT_3ARGS, "richiesta", "messageUUID", messageUUID );
            }
        } catch ( Throwable e ) {
            handlePersistenceException ( e );

        } finally {
            lw.stop ();
        }

        return entity;
    }
	
	

	@Override
	public Long countAllRichiestaByFilter ( RichiestaLightFilterDto filter ) throws PersistenceException {
		String methodName = "countAllRichiestaByFilter";
		LogAndWatch lw = new LogAndWatch ( log, CLASSNAME, methodName );
		lw.addParam ( "filter", filter );

		Long num = null;

		try {
			lw.start ();

			// costruisce la query dinamicamente
			CriteriaBuilder cbuilder = entityManager.getCriteriaBuilder ();
			CriteriaQuery<Long> cquery = cbuilder.createQuery ( Long.class );
			Root<EPaywsoTRichiestaLight> root = cquery.from ( EPaywsoTRichiestaLight.class );
			cquery.select ( cbuilder.count ( root ) );
			List<Predicate> predicates = buildPredicates ( filter, cbuilder, root );
			if ( predicates != null && !predicates.isEmpty () ) {
				cquery.where ( cbuilder.and ( predicates.toArray ( new Predicate [predicates.size ()] ) ) );
			}

			// esegue la query
			TypedQuery<Long> tq = entityManager.createQuery ( cquery );
			num = tq.getSingleResult ();

		} catch ( Throwable e ) {
			handlePersistenceException ( e );

		} finally {
			lw.stop ();
		}
		return num;
	}

	@Override
	public List<EPaywsoTRichiesta> findAllRichiesteInErrore ( Timestamp fromTime, Timestamp toTime ) throws PersistenceException {
		LogAndWatch lw = new LogAndWatch ( log, CLASSNAME, "findAllRichiesteInErrore" );
		lw.addParam ( "fromTime", fromTime );
		lw.addParam ( "toTime", toTime );

		List<EPaywsoTRichiesta> entityList = null;
		try {
			lw.start ();

			TypedQuery<EPaywsoTRichiesta> query = entityManager.createNamedQuery ( "EPaywsoTRichiesta.findAllRichiesteInErrore", entityClass );
			query.setParameter ( "idStatoRichiestaErroreInFaseDiElaborazione", StatoRichiestaEnum.ERRORE_IN_FASE_DI_ELABORAZIONE.getId () );
			query.setParameter ( "fromTime", fromTime );
			query.setParameter ( "toTime", toTime );
			//
			entityList = query.getResultList ();

		} finally {
			lw.stop ();
		}

		return entityList;
	}

	@Override
	public List<EPaywsoTRichiesta> findAllRichiesteScartate ( Timestamp fromTimeIns, Timestamp toTimeIns, Timestamp fromTimeExe ) throws PersistenceException {
		LogAndWatch lw = new LogAndWatch ( log, CLASSNAME, "findAllRichiesteScartate" );
		lw.addParam ( "fromTimeIns", fromTimeIns );
		lw.addParam ( "toTimeIns", toTimeIns );
		lw.addParam ( "fromTimeExe", fromTimeExe );

		List<EPaywsoTRichiesta> entityList = null;
		try {
			lw.start ();

			TypedQuery<EPaywsoTRichiesta> query = entityManager.createNamedQuery ( "EPaywsoTRichiesta.findAllRichiesteScartate", entityClass );
			query.setParameter ( "idStatoRichiestaInCorsoDiAcquisizione", StatoRichiestaEnum.IN_CORSO_DI_ACQUISIZIONE.getId () );
			query.setParameter ( "fromTimeIns", fromTimeIns );
			query.setParameter ( "toTimeIns", toTimeIns );
			query.setParameter ( "idStatoRichiestaErroreInFaseDiAcquisizione", StatoRichiestaEnum.ERRORE_IN_FASE_DI_ACQUISIZIONE.getId () );
			query.setParameter ( "fromTimeExe", fromTimeExe );
			//
			entityList = query.getResultList ();

		} finally {
			lw.stop ();
		}

		return entityList;
	}

	@Override
	public List<EPaywsoTRichiesta> findAllRichiesteNonInviate ( Timestamp beforeTimeRDE, Timestamp beforeTimeREE ) throws PersistenceException {
		LogAndWatch lw = new LogAndWatch ( log, CLASSNAME, "findAllRichiesteNonInviate" );
		lw.addParam ( "beforeTimeRDE", beforeTimeRDE );
		lw.addParam ( "beforeTimeREE", beforeTimeREE );

		List<EPaywsoTRichiesta> entityList = null;
		try {
			lw.start ();

			TypedQuery<EPaywsoTRichiesta> query = entityManager.createNamedQuery ( "EPaywsoTRichiesta.findAllRichiesteNonInviate", entityClass );
			query.setParameter ( "idStatoRichiestaDaElaborare", StatoRichiestaEnum.DA_ELABORARE.getId () );
			query.setParameter ( "beforeTimeRDE", beforeTimeRDE );
			query.setParameter ( "idStatoRichiestaErroreInFaseDiElaborazione", StatoRichiestaEnum.ERRORE_IN_FASE_DI_ELABORAZIONE.getId () );
			query.setParameter ( "beforeTimeREE", beforeTimeREE );
			//
			entityList = query.getResultList ();

		} finally {
			lw.stop ();
		}

		return entityList;
	}

	@Override
	//@formatter:off
	public List<EPaywsoTRichiestaLight> findAllRichiestaLightByFilter ( RichiestaLightFilterDto filter,
		List<CriterioOrdinamentoDto<ColumnNameRichiestaEnum>> criList, PaginazioneDto pag )
						throws PersistenceException
						//@formatter:on
	{
		String methodName = "findAllRichiestaLightByFilter";
		LogAndWatch lw = new LogAndWatch ( log, CLASSNAME, methodName );
		lw.addParam ( "filter", filter );
		lw.addParam ( "criList", criList );
		lw.addParam ( "pag", pag );

		List<EPaywsoTRichiestaLight> entityList = new ArrayList<EPaywsoTRichiestaLight> ();

		try {
			lw.start ();

			if ( pag.getNumeroDiPagina () < 1 ) {
				lw.warn ( "numero di pagina richiesto zero o negativo" );

			} else if ( pag.getNumeroRighePerPagina () < 1 ) {
				lw.warn ( "numero di righe per pagina richieste zero o negativo" );

			} else {
				// costruisce la query dinamicamente
				CriteriaBuilder cbuilder = entityManager.getCriteriaBuilder ();
				CriteriaQuery<EPaywsoTRichiestaLight> cquery = cbuilder.createQuery ( EPaywsoTRichiestaLight.class );
				Root<EPaywsoTRichiestaLight> root = cquery.from ( EPaywsoTRichiestaLight.class );
				cquery.select ( root );
				List<Predicate> predicates = buildPredicates ( filter, cbuilder, root );
				if ( predicates != null && !predicates.isEmpty () ) {
					cquery.where ( cbuilder.and ( predicates.toArray ( new Predicate [predicates.size ()] ) ) );
				}
				List<Order> orderBys = buildOrderBy ( criList, cbuilder, root );
				if ( orderBys != null && !orderBys.isEmpty () ) {
					cquery.orderBy ( orderBys );
				}

				// assegna i parametri di paginazione
				TypedQuery<EPaywsoTRichiestaLight> query = entityManager.createQuery ( cquery );
				query.setFirstResult ( ( pag.getNumeroDiPagina () - 1 ) * pag.getNumeroRighePerPagina () );
				query.setMaxResults ( pag.getNumeroRighePerPagina () );

				// esegue la query
				entityList = query.getResultList ();
			}
		} catch ( Throwable e ) {
			handlePersistenceException ( e );

		} finally {
			lw.stop ();
		}

		return entityList;
	}

	@SuppressWarnings ( "unchecked" )
	private List<Predicate> buildPredicates ( RichiestaLightFilterDto filter, CriteriaBuilder cbuilder, Root<EPaywsoTRichiestaLight> root ) {
		List<Predicate> predicates = null;

		if ( filter != null ) {
			predicates = new ArrayList<> ();
            if ( filter.getTipoRichiestaEnum () != null ) {
                predicates.add ( cbuilder.equal ( getColumnPathRichiesta ( root, ID_TIPO_RICHIESTA ), filter.getTipoRichiestaEnum ().getId () ) );
            }
            if ( filter.getStatoRichiestaEnumList () != null ) {
                predicates.add ( getColumnPathRichiesta ( root, ID_STATO_RICHIESTA ).in ( filter.getIdStatoRichiestaList () ) );
            }
            if ( filter.getPagamentoSpontaneo () != null ) {
                predicates.add ( cbuilder.equal ( getColumnPathRichiesta ( root, PAGAMENTO_SPONTANEO ), filter.getPagamentoSpontaneo () ) );
            }
			if ( filter.getCodFiscaleEnte () != null ) {
			    predicates.add ( cbuilder.equal ( getColumnPathRichiesta ( root, CF_ENTE_ORIGINE ), filter.getCodFiscaleEnte () ) );
			}
			if ( filter.getCodVersamentoList () != null ) {
                if ( filter.getCodVersamentoList ().size () == 1 ) {
                    predicates.add (  cbuilder.equal (getColumnPathRichiesta ( root, CODICE_VERSAMENTO ), filter.getCodVersamentoList () ) );
                } else {
                    predicates.add ( getColumnPathRichiesta ( root, CODICE_VERSAMENTO ).in ( filter.getCodVersamentoList () ) );
                }
			}
            if ( filter.getIdMessaggio () != null ) {
                predicates.add ( cbuilder.equal ( getColumnPathRichiesta ( root, ID_MESSAGGIO ), filter.getIdMessaggio () ) );
            }
			if ( filter.getDataInserimentoRichiestaDa () != null ) {
				predicates.add (
					cbuilder.greaterThanOrEqualTo ( getColumnPathRichiesta ( root, DATA_INSERIMENTO_RICHIESTA ), filter.getDataInserimentoRichiestaDa () ) );
			}
			if ( filter.getDataInserimentoRichiestaA () != null ) {
				predicates.add ( cbuilder.lessThan ( getColumnPathRichiesta ( root, DATA_INSERIMENTO_RICHIESTA ),
					getLaterDate ( filter.getDataInserimentoRichiestaA () ) ) );
			}
			if ( filter.getDataInvioAlDestinatarioDa () != null ) {
				Predicate p1 = cbuilder.lessThanOrEqualTo ( getColumnPathRichiesta ( root, DATA_INVIO_AL_DESTINATARIO ),
					getLaterDate ( filter.getDataInvioAlDestinatarioDa () ) );
				Predicate p2 = cbuilder.isNull ( getColumnPathRichiesta ( root, DATA_INVIO_AL_DESTINATARIO ) );
				predicates.add ( cbuilder.or ( p1, p2 ) );
			}
			if ( filter.getDataInvioAlDestinatarioA () != null ) {
				Predicate p1 = cbuilder.lessThanOrEqualTo ( getColumnPathRichiesta ( root, DATA_INVIO_AL_DESTINATARIO ),
					getLaterDate ( filter.getDataInvioAlDestinatarioA () ) );
				Predicate p2 = cbuilder.isNull ( getColumnPathRichiesta ( root, DATA_INVIO_AL_DESTINATARIO ) );
				predicates.add ( cbuilder.or ( p1, p2 ) );
			}
		}

		return predicates;
	}
	
	@SuppressWarnings ( "unchecked" )
    private List<Predicate> buildPredicatesForExists ( RichiestaLightFilterDto filter, CriteriaBuilder cbuilder, Root<EPaywsoTRichiestaLight> root, Root<EPaywsoTRichiestaLight> subRoot ) {
        List<Predicate> predicates = null;

        if ( filter != null ) {
            predicates = new ArrayList<Predicate> ();

            if ( filter.getIdMessaggio () != null ) {
                predicates.add ( cbuilder.equal ( getColumnPathRichiesta ( subRoot, ID_MESSAGGIO ), filter.getIdMessaggio () ) );
                predicates.add (  cbuilder.equal ( getColumnPathRichiesta ( subRoot, ID_MESSAGGIO ), getColumnPathRichiesta ( root, ID_MESSAGGIO ))  );
            }
            if ( filter.getCodFiscaleEnte () != null ) {
                predicates.add ( cbuilder.equal ( getColumnPathRichiesta ( subRoot, CF_ENTE_ORIGINE ), filter.getCodFiscaleEnte () ) );
                predicates.add (  cbuilder.equal ( getColumnPathRichiesta ( subRoot, CF_ENTE_ORIGINE ), getColumnPathRichiesta ( root, CF_ENTE_ORIGINE ))  );
                
            }
            if ( filter.getTipoRichiestaEnum () != null ) {
                predicates.add ( cbuilder.equal ( getColumnPathRichiesta ( subRoot, ID_TIPO_RICHIESTA ), filter.getTipoRichiestaEnum ().getId () ) );
                predicates.add (  cbuilder.equal ( getColumnPathRichiesta ( subRoot, ID_TIPO_RICHIESTA ), getColumnPathRichiesta ( root, ID_TIPO_RICHIESTA ))  );
            }
            if ( filter.getCodVersamentoList () != null ) {
                predicates.add ( getColumnPathRichiesta ( subRoot, CODICE_VERSAMENTO ).in ( filter.getCodVersamentoList () ) );
                predicates.add (  cbuilder.equal ( getColumnPathRichiesta ( subRoot, CODICE_VERSAMENTO ), getColumnPathRichiesta ( root, CODICE_VERSAMENTO ))  );
            }
        }

        return predicates;
    }


	private List<Order> buildOrderBy ( List<CriterioOrdinamentoDto<ColumnNameRichiestaEnum>> criList, CriteriaBuilder cbuilder,
		Root<EPaywsoTRichiestaLight> root ) {
		List<Order> orderBys = new ArrayList<Order> ();

		boolean existsId = false;
		if ( criList != null ) {
			for ( CriterioOrdinamentoDto<ColumnNameRichiestaEnum> cri: criList ) {
				ColumnNameRichiestaEnum columnEnum = cri.getColumnNameEnum ();
				existsId = ( columnEnum == ID_RICHIESTA ) ? true : existsId;
				//
				switch ( cri.getSortTypeEnum () ) {
				case ASC :
					orderBys.add ( cbuilder.asc ( getColumnPathRichiesta ( root, columnEnum ) ) );
					break;
				case DESC :
					orderBys.add ( cbuilder.desc ( getColumnPathRichiesta ( root, columnEnum ) ) );
					break;
				}
			}
		}

		// aggiunge come ultimo criterio l'ordinamento per id
		if ( !existsId ) {
			orderBys.add ( cbuilder.asc ( getColumnPathRichiesta ( root, ID_RICHIESTA ) ) );
		}

		return orderBys;
	}

	@Override
	public List<EPaywsoTRichiesta> findAllRichiesteByMessaggioEnteTipoRichiesta ( List<String> idMessaggioCfEnteList, Integer idTipoRichiesta )
					throws PersistenceException {

		LogAndWatch lw = new LogAndWatch ( log, CLASSNAME, "findAllRichiesteByMessaggioEnteTipoRichiesta" );
		lw.addParam ( "idMessaggio", idMessaggioCfEnteList );
		lw.addParam ( "idTipoRichiesta", String.valueOf ( idTipoRichiesta ) );

		List<EPaywsoTRichiesta> entityList = null;
		try {
			lw.start ();

			TypedQuery<EPaywsoTRichiesta> query
			= entityManager.createNamedQuery ( "EPaywsoTRichiesta.findfindAllRichiesteByMessaggioEnteTipoRichiesta", entityClass );
			query.setParameter ( "idMessaggioCfEnteList", idMessaggioCfEnteList );
			query.setParameter ( "idTipoRichiesta", idTipoRichiesta );

			entityList = query.getResultList ();

		} finally {
			lw.stop ();
		}

		return entityList;
	}

	@Override
	public List<EPaywsoTRichiesta> findAllRichiesteByMessaggioEnte ( List<String> idMessaggioCfEnteList ) throws PersistenceException {

		LogAndWatch lw = new LogAndWatch ( log, CLASSNAME, "findAllRichiesteByMessaggioEnte" );
		lw.addParam ( "idMessaggio", idMessaggioCfEnteList );

		List<EPaywsoTRichiesta> entityList = null;
		try {
			lw.start ();

			TypedQuery<EPaywsoTRichiesta> query = entityManager.createNamedQuery ( "EPaywsoTRichiesta.findfindAllRichiesteByMessaggioEnte", entityClass );
			query.setParameter ( "idMessaggioCfEnteList", idMessaggioCfEnteList );

			entityList = query.getResultList ();

		} finally {
			lw.stop ();
		}

		return entityList;
	}

	@SuppressWarnings ( "rawtypes" )
	private Path getColumnPathRichiesta ( Root<EPaywsoTRichiestaLight> root, ColumnNameRichiestaEnum columnEnum ) {
		Path columnPath = null;

		if ( columnEnum != null ) {
			switch ( columnEnum ) {
			case ID_RICHIESTA :
				columnPath = root.get ( "idRichiesta" );
				break;
			case ID_MESSAGGIO :
				columnPath = root.get ( "idMessaggio" );
				break;
			case CODICE_FISCALE_ENTE :
				columnPath = root.get ( "ePaywsoTEnte" ).get ( "codFiscaleEnte" );
				break;
			case CF_ENTE_ORIGINE :
			    columnPath = root.get ( "cfEnteOrigine" );
			    break;
			case ID_TIPO_RICHIESTA :
				columnPath = root.get ( "ePaywsoDTipoRichiesta" ).get ( "idTipoRichiesta" );
				break;
			case ID_STATO_RICHIESTA :
				columnPath = root.get ( "ePaywsoDStatoRichiesta" ).get ( "idStatoRichiesta" );
				break;
			case PAGAMENTO_SPONTANEO :
				columnPath = root.get ( "pagamentoSpontaneo" );
				break;
			case CODICE_VERSAMENTO :
				columnPath = root.get ( "codVersamento" );
				break;
			case DATA_INSERIMENTO_RICHIESTA :
				columnPath = root.get ( "dtInserimentoRichiesta" );
				break;
			case DATA_INVIO_AL_DESTINATARIO :
				columnPath = root.get ( "dtInvioAlDestinatario" );
				break;
			}
		}

		return columnPath;
	}
	
	@Override
    public Boolean existRichieste ( RichiestaLightFilterDto filter ) throws PersistenceException {
        String methodName = "existRichieste";
        LogAndWatch lw = new LogAndWatch ( log, CLASSNAME, methodName );
        lw.addParam ( "filter", filter );

        List<Long> num = null;

        try {
            lw.start ();

            // costruisce la query dinamicamente
            CriteriaBuilder cbuilder = entityManager.getCriteriaBuilder ();
            CriteriaQuery<Long> cquery = cbuilder.createQuery ( Long.class );
            Root<EPaywsoTRichiestaLight> root = cquery.from ( EPaywsoTRichiestaLight.class );
            cquery.select (root.get ( "idRichiesta" ));
            
            Subquery<EPaywsoTRichiestaLight> subquery = cquery.subquery ( EPaywsoTRichiestaLight.class );
            Root<EPaywsoTRichiestaLight> rootEPaywsoTRichiestaLight = subquery.from ( EPaywsoTRichiestaLight.class );
            subquery.select ( rootEPaywsoTRichiestaLight );
//            
            List<Predicate> subqueryPredicates = buildPredicatesForExists ( filter, cbuilder,  root , rootEPaywsoTRichiestaLight);
            if ( subqueryPredicates != null && !subqueryPredicates.isEmpty () ) {
                subquery.where ( cbuilder.and ( subqueryPredicates.toArray ( new Predicate [subqueryPredicates.size ()] ) ) );
            }
            Predicate predicate=   cbuilder.exists ( subquery );
            cquery.where ( predicate );
            // esegue la query
            TypedQuery<Long> tq = entityManager.createQuery ( cquery );
            num = tq.getResultList ();

        } catch ( Throwable e ) {
            handlePersistenceException ( e );

        } finally {
            lw.stop ();
        }
        return num.size ()>0;
    }

}
