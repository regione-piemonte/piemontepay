/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import it.csi.epay.epaypaweb.dto.CriterioOrdinamentoDto;
import it.csi.epay.epaypaweb.dto.PaginazioneDto;
import it.csi.epay.epaypaweb.dto.ReportFilterDto;
import it.csi.epay.epaypaweb.dto.TotalSizeAndLightListDto;
import it.csi.epay.epaypaweb.enumeration.ColumnNameReportEnum;
import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBaseImpl;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTReportDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaDTipoReport;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTReport;


public class EpaypaTReportDaoImpl extends EpaypaDaoBaseImpl<Long, EpaypaTReport> implements EpaypaTReportDao {
    private static  final String CLASSNAME = "EpaypaTReportDaoImpl";
    
    @Override
    public TotalSizeAndLightListDto<EpaypaTReport> findAllByIdEnteAndIdUtente ( Integer idEnte, Long idUtente, PaginazioneDto pag ) throws PersistenceException {
        String methodName = "findAllByIdEnteAndIdUtente";
        
        
        

        List<EpaypaTReport> entityList = null;
        Long countResult = 0L;

        try {

            log.info ( CLASSNAME + " " + methodName + " - START" );
            if ( pag != null && ( pag.getNumeroPagina () != null && pag.getNumeroPagina () < 1 ) ) {
                log.warn ( "numero di pagina richiesto zero o negativo" );

            } else if ( pag != null && ( pag.getNumeroRighePerPagina () != null && pag.getNumeroRighePerPagina () < 1 ) ) {
                log.warn ( "numero di righe per pagina richieste zero o negativo" );

            } else {

                // conteggio

                CriteriaBuilder cbuilderCount = entityManager.getCriteriaBuilder ();
                CriteriaQuery<Long> cqueryCount = cbuilderCount.createQuery ( Long.class );
                Root<EpaypaTReport> rootCount = cqueryCount.from ( EpaypaTReport.class );
                cqueryCount.select ( cbuilderCount.count ( rootCount ) );
                cqueryCount.where (
                    cbuilderCount.and (
                        cbuilderCount.equal ( rootCount.get ( "idEnte" ), idEnte ),
                        cbuilderCount.equal ( rootCount.get ( "idUtente" ), idUtente ) ) );

                // esegue la query per il count
                countResult = entityManager.createQuery ( cqueryCount ).getSingleResult ();
                if ( countResult > 0 ) {
                    TypedQuery<EpaypaTReport> query = entityManager.createNamedQuery ( "EpaypaTReport.findAllByIdEnteAndIdUtente", EpaypaTReport.class );
                    query.setParameter ( "idEnte", idEnte );
                    query.setParameter ( "idUtente", idUtente );

                    // assegna i parametri di paginazione
                    if ( pag != null ) {
                        if ( pag.getNumeroPagina () != null ) {
                            query.setFirstResult ( ( pag.getNumeroPagina () - 1 ) * pag.getNumeroRighePerPagina () );
                        }
                        if ( pag.getNumeroRighePerPagina () != null ) {
                            query.setMaxResults ( pag.getNumeroRighePerPagina () );
                        }
                    }

                    //
                    entityList = query.getResultList ();
                }
            }
        } catch ( Throwable e ) {
            log.error ( "Errore imprevisto", e );
            throw new PersistenceException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }
        return new TotalSizeAndLightListDto<> ( countResult, entityList );
    }

	@Override
	public List<EpaypaTReport> findByStatusCode(String codiceStato, int maxResultSize) throws PersistenceException {
		String methodName = "findAllByIdEnteAndIdUtente";
        
        

        List<EpaypaTReport> entityList = null;

        try {
        	log.info ( CLASSNAME + " " + methodName + " - START" );
        	
        	TypedQuery<EpaypaTReport> query = entityManager.createNamedQuery ( "EpaypaTReport.findByStatusCode", EpaypaTReport.class );
            query.setParameter ( "codiceStato", codiceStato );
            query.setMaxResults(maxResultSize);
            
            entityList = query.getResultList();
        	
        }catch ( Throwable e ) {
            log.error ( "Errore imprevisto", e );
            throw new PersistenceException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );

        }finally {
        	log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }
		
		
		return entityList;
	}

	@Override
	public List<EpaypaTReport> findByStateCodesAndDataModificaBefore(String[] stateCodes, Date thresholdDate)
			throws PersistenceException {
		
		String methodName = "findByStateCodesAndDataModificaBefore";
        
        
        

        List<EpaypaTReport> entityList = null;

        try {
        	log.info ( CLASSNAME + " " + methodName + " - START" );
        	
        	List<String> stateCodesList =  Arrays.asList(stateCodes);
        	
        	TypedQuery<EpaypaTReport> query = entityManager.createNamedQuery ( "EpaypaTReport.findByStateCodesAndDataModificaBefore", EpaypaTReport.class );
            query.setParameter ( "stateCodesList", stateCodesList );
            query.setParameter ( "thresholdDate", thresholdDate );
            
            entityList = query.getResultList();
        	
        }catch ( Throwable e ) {
            log.error ( "Errore imprevisto", e );
            throw new PersistenceException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );
        }finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
        	
        	
		return entityList;
	}

	@Override
	public TotalSizeAndLightListDto<EpaypaTReport> findAllByIdEnteAndIdUtenteAndCodiceTipoReport(Integer idEnte, Long idUtente, String codiceTipoReport, PaginazioneDto pag) throws PersistenceException {

		String methodName = "findAllByIdEnteAndIdUtenteAndCodiceTipoReport";
        
        
        
        

        List<EpaypaTReport> entityList = null;
        Long countResult = 0L;

        try {

            log.info ( CLASSNAME + " " + methodName + " - START" );
            if ( pag != null && ( pag.getNumeroPagina () != null && pag.getNumeroPagina () < 1 ) ) {
                log.warn ( "numero di pagina richiesto zero o negativo" );

            } else if ( pag != null && ( pag.getNumeroRighePerPagina () != null && pag.getNumeroRighePerPagina () < 1 ) ) {
                log.warn ( "numero di righe per pagina richieste zero o negativo" );

            } else {

                // conteggio

                CriteriaBuilder cbuilderCount = entityManager.getCriteriaBuilder ();
                CriteriaQuery<Long> cqueryCount = cbuilderCount.createQuery ( Long.class );
                Root<EpaypaTReport> rootCount = cqueryCount.from ( EpaypaTReport.class );
                
                Join<EpaypaTReport,EpaypaDTipoReport> joinTipoReport = rootCount.join("epaypaDTipoReport" , JoinType.LEFT );
                                
                cqueryCount.select ( cbuilderCount.count ( rootCount ) );
                cqueryCount.where (
                    cbuilderCount.and (
                        cbuilderCount.equal ( rootCount.get ( "idEnte" ), idEnte ),
                        cbuilderCount.equal ( rootCount.get ( "idUtente" ), idUtente ),
                        cbuilderCount.equal ( joinTipoReport.get( "codice" ), codiceTipoReport ) ) );

                // esegue la query per il count
                countResult = entityManager.createQuery ( cqueryCount ).getSingleResult ();
                if ( countResult > 0 ) {
                    TypedQuery<EpaypaTReport> query = entityManager.createNamedQuery ( "EpaypaTReport.findAllByIdEnteAndIdUtenteAndCodiceTipoReport", EpaypaTReport.class );
                    query.setParameter ( "idEnte", idEnte );
                    query.setParameter ( "idUtente", idUtente );
                    query.setParameter ( "codiceTipoReport", codiceTipoReport );
                	
                    // assegna i parametri di paginazione
                    if ( pag != null ) {
                        if ( pag.getNumeroPagina () != null ) {
                            query.setFirstResult ( ( pag.getNumeroPagina () - 1 ) * pag.getNumeroRighePerPagina () );
                        }
                        if ( pag.getNumeroRighePerPagina () != null ) {
                            query.setMaxResults ( pag.getNumeroRighePerPagina () );
                        }
                    }

                    //
                    entityList = query.getResultList ();
                }
            }
        } catch ( Throwable e ) {
            log.error ( "Errore imprevisto", e );
            throw new PersistenceException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }
        return new TotalSizeAndLightListDto<> ( countResult, entityList );
	}	
	
	@Override
	public TotalSizeAndLightListDto<EpaypaTReport> findAllByFilter(ReportFilterDto reportFilterDto, List<CriterioOrdinamentoDto<ColumnNameReportEnum>> ord, PaginazioneDto pag)
			throws PersistenceException {
		String methodName = "findAllByIdEnteAndIdUtenteAndCodiceTipoReport";
        
        
        List<EpaypaTReport> entityList = null;
        Long countResult = 0L;

        try {

            log.info ( CLASSNAME + " " + methodName + " - START" );
            if ( pag != null && ( pag.getNumeroPagina () != null && pag.getNumeroPagina () < 1 ) ) {
                log.warn ( "numero di pagina richiesto zero o negativo" );

            } else if ( pag != null && ( pag.getNumeroRighePerPagina () != null && pag.getNumeroRighePerPagina () < 1 ) ) {
                log.warn ( "numero di righe per pagina richieste zero o negativo" );

            } else {

                // conteggio

                CriteriaBuilder cbuilderCount = entityManager.getCriteriaBuilder ();
                CriteriaQuery<Long> cqueryCount = cbuilderCount.createQuery ( Long.class );
                Root<EpaypaTReport> rootCount = cqueryCount.from ( EpaypaTReport.class );
                
                Join<EpaypaTReport,EpaypaDTipoReport> joinTipoReport = rootCount.join("epaypaDTipoReport" , JoinType.LEFT );
                                
                cqueryCount.select ( cbuilderCount.count ( rootCount ) );
                cqueryCount.where (
                    cbuilderCount.and (
                        cbuilderCount.equal ( rootCount.get ( "idEnte" ), reportFilterDto.getIdEnte()),
                        cbuilderCount.equal ( rootCount.get ( "idUtente" ), reportFilterDto.getIdUtente() ),
                        cbuilderCount.equal ( joinTipoReport.get( "codice" ), reportFilterDto.getTipoReportEnum().getCode() ) ) );
                
                
                // esegue la query per il count
                countResult = entityManager.createQuery ( cqueryCount ).getSingleResult ();
                if ( countResult > 0 ) {
                	CriteriaBuilder cbuilderSort = entityManager.getCriteriaBuilder ();
                    CriteriaQuery<EpaypaTReport> cquerySort = cbuilderSort.createQuery ( EpaypaTReport.class  );
                    Root<EpaypaTReport> rootSort = cquerySort.from ( EpaypaTReport.class );
                    
                    Join<EpaypaTReport,EpaypaDTipoReport> joinTipoReportSort = rootSort.join("epaypaDTipoReport" , JoinType.LEFT );
                                    
                    cquerySort.select (rootSort);
                    cquerySort.where (
                        cbuilderSort.and (
                            cbuilderSort.equal ( rootSort.get ( "idEnte" ), reportFilterDto.getIdEnte()),
                            cbuilderSort.equal ( rootSort.get ( "idUtente" ), reportFilterDto.getIdUtente() ),
                            cbuilderSort.equal ( joinTipoReportSort.get( "codice" ), reportFilterDto.getTipoReportEnum().getCode() ) ) );
                    List<Order> sort = buildOrderBy(ord, cbuilderSort, rootSort);
                    if(sort != null && !sort.isEmpty()) {
                    	cquerySort.orderBy(sort);
                    }
                    
                    TypedQuery<EpaypaTReport> query = entityManager.createQuery (cquerySort);

                    // assegna i parametri di paginazione
                    if ( pag != null ) {
                        if ( pag.getNumeroPagina () != null ) {
                            query.setFirstResult ( ( pag.getNumeroPagina () - 1 ) * pag.getNumeroRighePerPagina () );
                        }
                        if ( pag.getNumeroRighePerPagina () != null ) {
                            query.setMaxResults ( pag.getNumeroRighePerPagina () );
                        }
                    }
                    //
                    entityList = query.getResultList ();
                }
            }
        } catch ( Throwable e ) {
            log.error ( "Errore imprevisto", e );
            throw new PersistenceException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }
        return new TotalSizeAndLightListDto<> ( countResult, entityList );
	}
	
	private List<Order> buildOrderBy(List<CriterioOrdinamentoDto<ColumnNameReportEnum>> ord, CriteriaBuilder cbuilder, Root<EpaypaTReport> root) {
		List<Order> orderBys = new ArrayList<Order>();

		boolean existsId = false;
		if (ord != null) {
			for (CriterioOrdinamentoDto<ColumnNameReportEnum> cri : ord) {
				ColumnNameReportEnum columnEnum = cri.getColumnNameEnum();
				existsId = (columnEnum == ColumnNameReportEnum.ID) ? true : existsId;

				switch (cri.getSortTypeEnum()) {
				case ASC:
					orderBys.add(cbuilder.asc(getColumnPath(root, columnEnum)));
					break;
				case DESC:
					orderBys.add(cbuilder.desc(getColumnPath(root, columnEnum)));
					break;
				}
			}
		}

		// aggiunge come ultimo criterio l'ordinamento per id
		if (!existsId) {
			orderBys.add(cbuilder.asc(getColumnPath(root, ColumnNameReportEnum.ID)));
		}

		return orderBys;
	}
	
	@SuppressWarnings("rawtypes")
	static public Path getColumnPath(Root<EpaypaTReport> root, ColumnNameReportEnum columnEnum) {
		Path columnPath = null;

		if (columnEnum != null) {
			switch (columnEnum) {
			case ID:
				columnPath = root.get("id");
				break;
			case ID_STATO:
				columnPath = root.get("epaypaDStatoReport").get("id");
				break;
			case ID_ENTE:
				columnPath = root.get("epaypaTEnte").get("idEnte");
				break;
			case DATA_INSERIMENTO:
				columnPath = root.get("dataInserimento");
				break;
			case DATA_MODIFICA:
				columnPath = root.get("dataModifica");
				break;
			case ID_UTENTE:
				columnPath = root.get("idUtente");
				break;
			case CODICE_FISCALE_UTENTE:
				columnPath = root.get("codiceFiscaleUtente");
				break;
			case CODICE_FISCALE_ENTE:
				columnPath = root.get("codiceFiscaleEnte");
				break;
			case NOMINATIVO_EXPORT:
				columnPath = root.get("nominativoExport");
				break;
			case ID_TIPO_REPORT:
				columnPath = root.get("epaypaDTipoReport").get("id");
				break;
			case ID_FILE:
				columnPath = root.get("epaypaTFileReport").get("id");
				break;
			case ID_TIPO_FILE:
				columnPath = root.get("epaypaDTipoFileReport").get("id");
				break;
			default:
				break;
			}
		}

		return columnPath;
	}
}
