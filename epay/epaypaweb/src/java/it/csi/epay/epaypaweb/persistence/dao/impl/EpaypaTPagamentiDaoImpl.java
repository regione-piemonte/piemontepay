/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.impl;

import it.csi.epay.epaypaweb.dto.*;
import it.csi.epay.epaypaweb.enumeration.ColumnNamePagamentiEnum;
import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.exception.RecordNumberGreaterThanThresholdException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBaseImpl;
import it.csi.epay.epaypaweb.persistence.dao.common.EpaypaTPagamentoCommon;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTPagamentiDao;
import it.csi.epay.epaypaweb.persistence.entity.*;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.*;

import static it.csi.epay.epaypaweb.enumeration.ColumnNamePagamentiEnum.IUV;

public class EpaypaTPagamentiDaoImpl extends EpaypaDaoBaseImpl<Long, EpaypaTPagamenti> implements EpaypaTPagamentiDao {

    private static final String CLASSNAME = EpaypaTPagamentiDaoImpl.class.getName();

    @Override
    public TotalSizeAndLightListDto<EpaypaTPagamenti> ricerca(PagamentiFilterDto filter,
        List<CriterioOrdinamentoDto<ColumnNamePagamentiEnum>> ord, PaginazioneDto pag, int thresholdRecordsNum)
                        throws PersistenceException,RecordNumberGreaterThanThresholdException {
        String methodName = "ricerca";
        
        
        
        

        List<EpaypaTPagamenti> entityList = new ArrayList<>();

        Long countResult = 0L;

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            if (pag != null && (pag.getNumeroPagina() != null && pag.getNumeroPagina() < 1)) {
                log.warn("numero di pagina richiesto zero o negativo");

            } else if (pag != null && (pag.getNumeroRighePerPagina() != null && pag.getNumeroRighePerPagina() < 1)) {
                log.warn("numero di righe per pagina richieste zero o negativo");

            } else {
                // costruisco la query di count
                CriteriaBuilder cbuilderCount = entityManager.getCriteriaBuilder();
                CriteriaQuery<Long> cqueryCount = cbuilderCount.createQuery(Long.class);
                Root<EpaypaTPagamenti> rootCount = cqueryCount.from(EpaypaTPagamenti.class);
                cqueryCount.select(cbuilderCount.count(rootCount));

                List<Predicate> predicatesCount = null;

                if (filter.getPagamentiSpontanei() == null) {
                    predicatesCount = buildPredicatesPerTuttiPagamenti(filter, cbuilderCount, rootCount, false);
                } else {
                    predicatesCount = buildPredicates(filter, cbuilderCount, rootCount, false);
                }
                /*if (filter.getCostiNotifica() == null) {
                	predicatesCount = buildPredicatesPerTuttiPagamenti(filter, cbuilderCount, rootCount, false);
                } else {
                	predicatesCount = buildPredicates(filter, cbuilderCount, rootCount, false);
                }*/

                if (predicatesCount != null && !predicatesCount.isEmpty()) {
                    cqueryCount
                    .where(cbuilderCount.and(predicatesCount.toArray(new Predicate[predicatesCount.size()])));
                }

                // esegue la query per il count
                countResult = entityManager.createQuery(cqueryCount).getSingleResult();

				if ( countResult > 0 ) {
					if ( ( thresholdRecordsNum > 0 ) && ( countResult > thresholdRecordsNum ) ) {
						String msg = "Numero di record trovati (" + countResult + ") superiore al limite consentito (" + thresholdRecordsNum + ")";
						log.error ( msg );
						throw new RecordNumberGreaterThanThresholdException ( msg );
					} else {
						// costruisco la select
						CriteriaBuilder cbuilder = entityManager.getCriteriaBuilder ();
						CriteriaQuery<EpaypaTPagamenti> cquery = cbuilder.createQuery ( EpaypaTPagamenti.class );
						Root<EpaypaTPagamenti> root = cquery.from ( EpaypaTPagamenti.class );
						cquery.select ( root );
						CriteriaBuilder cbuilderSafe = entityManager.getCriteriaBuilder ();
						CriteriaQuery<EpaypaTPagamenti> cquerySafe = cbuilder.createQuery ( EpaypaTPagamenti.class );
						Root<EpaypaTPagamenti> rootSafe = cquerySafe.from ( EpaypaTPagamenti.class );
						cquerySafe.select ( rootSafe );

						List<Predicate> predicates;
						List<Predicate> predicatesSafe;

						if ( filter.getPagamentiSpontanei () == null ) {
							predicates = buildPredicatesPerTuttiPagamenti ( filter, cbuilder, root, true );
							predicatesSafe = buildPredicatesPerTuttiPagamenti ( filter, cbuilderSafe, rootSafe, true );
						} else {
							predicates = buildPredicates ( filter, cbuilder, root, true );
							predicatesSafe = buildPredicates ( filter, cbuilderSafe, rootSafe, true );
						}

						if ( !predicates.isEmpty () ) {
							cquery.where ( cbuilder.and ( predicates.toArray ( new Predicate[0] ) ) );
							cquerySafe.where ( cbuilderSafe.and ( predicatesSafe.toArray ( new Predicate[0] ) ) );
						}

						List<Order> orderBys = buildOrderBy ( ord, cbuilder, root, filter.getPagamentiSpontanei () );
						if ( !orderBys.isEmpty () ) {
							cquery.orderBy ( orderBys );
						}

						TypedQuery<EpaypaTPagamenti> qr = entityManager.createQuery ( cquery );

						// assegna i parametri di paginazione
						if ( pag != null ) {
							if ( pag.getNumeroPagina () != null ) {
								qr.setFirstResult ( ( pag.getNumeroPagina () - 1 ) * pag.getNumeroRighePerPagina () );
							}
							if ( pag.getNumeroRighePerPagina () != null ) {
								qr.setMaxResults ( pag.getNumeroRighePerPagina () );
							}
						}
						// esegue la query per il fetch
						entityList = qr.getResultList ();
						if ( entityList.isEmpty () ) {
							TypedQuery<EpaypaTPagamenti> qrSafe = entityManager.createQuery ( cquerySafe );
							if ( pag != null ) {
								if ( pag.getNumeroPagina () != null ) {
									qrSafe.setFirstResult ( ( pag.getNumeroPagina () - 1 ) * pag.getNumeroRighePerPagina () );
								}
								if ( pag.getNumeroRighePerPagina () != null ) {
									qrSafe.setMaxResults ( pag.getNumeroRighePerPagina () );
								}
							}
							entityList = qrSafe.getResultList ();
						}
					}
				}
			}
        }catch (RecordNumberGreaterThanThresholdException e) {
            throw e;

        }
        catch (Throwable e) {
            log.error("Errore imprevisto", e);
            throw new PersistenceException("Errore imprevisto in " + CLASSNAME + "::" + methodName, e);

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return new TotalSizeAndLightListDto<>(countResult, entityList);
    }

    @Override
    public EpaypaTPagamenti findPagamentoByFilter(PagamentiFilterDto filter) throws PersistenceException {
        String methodName = "findPagamentoByFilter";
        
        
        

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            CriteriaBuilder cbuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<EpaypaTPagamenti> cquery = cbuilder.createQuery(EpaypaTPagamenti.class);
            Root<EpaypaTPagamenti> root = cquery.from(EpaypaTPagamenti.class);
            cquery.select(root);

            List<Predicate> predicates = buildPredicates(filter, cbuilder, root, true);

            if (predicates != null && !predicates.isEmpty()) {
                cquery.where(cbuilder.and(predicates.toArray(new Predicate[predicates.size()])));
            }

            TypedQuery<EpaypaTPagamenti> query = entityManager.createQuery(cquery);
            query.setFirstResult(0);
            query.setMaxResults(2);

            List<EpaypaTPagamenti> entityList = entityManager.createQuery(cquery).getResultList();

            if (entityList == null || entityList.isEmpty()) {
                return null;
            } else if (entityList.size() > 1) {
                throw new PersistenceException(
                    "Risultati multipli per un singolo IUV: " + filter.getIdCodiceVersamento());
            } else {
                return entityList.get(0);
            }

        } catch (Throwable e) {
            log.error("Errore imprevisto", e);
            throw new PersistenceException("Errore imprevisto in " + CLASSNAME + "::" + methodName, e);

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }
    }

    @Override
    public List<EpaypaTPagamenti> findPagamentiByIuvIn(Collection<String> iuv) throws PersistenceException {
        String methodName = "findPagamentiByIuvIn";
        
        

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            TypedQuery<EpaypaTPagamenti> query = entityManager.createNamedQuery("EpaypaTPagamenti.findByIuvIn",
                EpaypaTPagamenti.class);
            query.setParameter("iuv", iuv);
            //
            return  query.getResultList();
        } catch (Throwable e) {
            log.error("Errore imprevisto", e);
            throw new PersistenceException("Errore imprevisto in " + CLASSNAME + "::" + methodName, e);

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    static public List<Predicate> buildPredicates(PagamentiFilterDto filter, CriteriaBuilder cbuilder,
        Root<EpaypaTPagamenti> root, boolean fetch) {
        List<Predicate> predicates = null;

        if (filter == null) {
            filter = new PagamentiFilterDto();
        }

        Integer idEnte = null;
        String cfEnte = null; // Taccone per trattare i record gestiti da applicativi esterni, vedi gerica
        List<Integer> codiciVersamentoVisibili = null;
        List<String> codVersVisibili = null; // Taccone per trattare i record gestiti da applicativi esterni, vedi gerica

        if (filter.getProfilazioneUtente() != null) {
            idEnte = filter.getProfilazioneUtente().getEnte() != null ? filter.getProfilazioneUtente().getEnte().getId()
                            : -999;
            cfEnte = filter.getProfilazioneUtente ().getEnte () != null ?
                            filter.getProfilazioneUtente ().getEnte ().getCodFiscale () : "";// capire cosa mettere
            if (filter.getProfilazioneUtente().getCodiciVersamentoVisibili() == null
                            || filter.getProfilazioneUtente().getCodiciVersamentoVisibili().isEmpty()) {
                // nessun codice versamento visibile, ne aggiungo uno fittizio per filtrare
                // comunque quelli visibili
                codiciVersamentoVisibili = Arrays.asList(-999);
                codVersVisibili = Arrays.asList();
            } else {
                codiciVersamentoVisibili = new ArrayList<>();
                codVersVisibili = new ArrayList<>();
                for (CodiceVersamentoDto cv : filter.getProfilazioneUtente().getCodiciVersamentoVisibili()) {
                    codiciVersamentoVisibili.add(cv.getId());
                    codVersVisibili.add(cv.getCod ());
                }
            }
        }

        Join<EpaypaTPagamenti, EpaypaTPosizioneDebitoriaMedium> joinPosizioneDebitoria = null;
        Join<?, EpaypaTSoggetto> joinSoggetto;
        Join<?, EpaypaTFlusso> joinFlusso;
        Join<EpaypaTFlusso, EpaypaTCodiceVersamento> joinCodiceVersamento;
        Join<EpaypaTCodiceVersamento, EpaypaTEnte> joinEnte;
        Join<EpaypaTFlusso, EpaypaTNotificaPagamentoMedium> joinNotificaPagamento = null;
        Join<EpaypaTFlusso, EpaypaTAggPosizioneDebitoria> joinFlussoAggPos;

        if (fetch) {

            if (Boolean.TRUE.equals(filter.getPagamentiSpontanei())) {
                // cose di test
                joinNotificaPagamento = (Join) root.fetch("notificaPagamento", JoinType.LEFT);
                joinFlusso = (Join) joinNotificaPagamento.fetch("epaypaTFlusso", JoinType.LEFT);
                joinSoggetto = (Join) joinNotificaPagamento.fetch("epaypaTSoggettoDebitore", JoinType.LEFT);
                // fine cose di test
            } else {
                joinPosizioneDebitoria = (Join) root.fetch("posizioneDebitoria", JoinType.LEFT);
                joinFlusso = (Join) joinPosizioneDebitoria.fetch("epaypaTFlusso", JoinType.LEFT);
                joinSoggetto = (Join) joinPosizioneDebitoria.fetch("epaypaTSoggettoDebitore", JoinType.LEFT);
            }
            // inserimento tabella aggiornamento
            //joinFlussoAggPos = (Join) joinFlusso.fetch ( "epaypaTAggPosizioneDebitorias", JoinType.LEFT );
            joinCodiceVersamento = (Join) joinFlusso.fetch("epaypaTCodiceVersamento", JoinType.LEFT);
            joinEnte = (Join) joinCodiceVersamento.fetch("epaypaTEnte", JoinType.LEFT);

        } else {

            if (Boolean.TRUE.equals(filter.getPagamentiSpontanei())) {
                joinNotificaPagamento = root.join("notificaPagamento", JoinType.LEFT);
                joinFlusso = joinNotificaPagamento.join("epaypaTFlusso", JoinType.LEFT);
                joinSoggetto = joinNotificaPagamento.join("epaypaTSoggettoDebitore", JoinType.LEFT);
            } else {
                joinPosizioneDebitoria = root.join("posizioneDebitoria", JoinType.LEFT);
                joinFlusso = joinPosizioneDebitoria.join("epaypaTFlusso", JoinType.LEFT);
                joinSoggetto = joinPosizioneDebitoria.join("epaypaTSoggettoDebitore", JoinType.LEFT);
            }
            // inserimento tabella aggiornamento
            //joinFlussoAggPos = joinFlusso.join ( "epaypaTAggPosizioneDebitorias", JoinType.LEFT );
            joinCodiceVersamento = joinFlusso.join("epaypaTCodiceVersamento", JoinType.LEFT);
            joinEnte = joinCodiceVersamento.join("epaypaTEnte", JoinType.LEFT);
        }

        predicates = new ArrayList<>();

        if (filter.getPagamentiSpontanei() != null) {
            predicates.add(cbuilder.equal(root.get("pagamentoSpontaneo"), filter.getPagamentiSpontanei()));
        }
        
        // costi di notifica
        if (Boolean.TRUE.equals(filter.getCostiNotifica())) {    
        	predicates.add(cbuilder.equal(root.get("requiresCostUpdate"), filter.getCostiNotifica()));
        }
        if (Boolean.FALSE.equals(filter.getCostiNotifica())) {    
        	predicates.add(
        		cbuilder.or(
        			cbuilder.or(cbuilder.isFalse(root.get("requiresCostUpdate"))),
        			cbuilder.or(cbuilder.isNull(root.get("requiresCostUpdate")))
        		)
        	);
        }

        //        if (!StringUtils.isBlank(filter.getCodiceFiscale())) {
        //            predicates.add(cbuilder.like(cbuilder.upper(joinSoggetto.get("idUnivocoFiscale")),
        //                "%" + filter.getCodiceFiscale().toUpperCase() + "%"));
        //        }
        //CSI_PAG_AM-5296 occorre mettere in or anche il codice fiscale della tabella pagamenti
        if ( !StringUtils.isBlank ( filter.getCodiceFiscale () ) ) {
            predicates.add (
                cbuilder.or (
                    cbuilder.like (
                        cbuilder.upper ( joinSoggetto.get ( "idUnivocoFiscale" ) ),
                        "%" + filter.getCodiceFiscale ().toUpperCase () + "%" ),
                    cbuilder.like (
                        cbuilder.upper ( root.get ( "idUnicoFiscale" ) ),
                        "%" + filter.getCodiceFiscale ().toUpperCase () + "%" ) ) );
        }


        // forse bisogna mettere in or anche CFente?
        //        if (!StringUtils.isBlank(filter.getCognome())) {
        //            predicates.add(cbuilder.like(cbuilder.upper(joinSoggetto.get("cognomeRagSoc")),
        //                "%" + filter.getCognome().toUpperCase() + "%"));
        //        }
        //CSI_PAG_AM-5296 occorre mettere in or anche il cognome/ragione sociale della tabella pagamenti
        if ( !StringUtils.isBlank ( filter.getCognome () ) ) {
            predicates.add (
                cbuilder.or (
                    cbuilder.like (
                        cbuilder.upper ( joinSoggetto.get ( "cognomeRagSoc" ) ),
                        "%" + filter.getCognome ().toUpperCase () + "%" ) ,
                    cbuilder.like (
                        cbuilder.upper ( root.get ( "cognomeRagioneSociale" ) ),
                        "%" + filter.getCognome ().toUpperCase () + "%" )
                                )
                            );
        }

        if (!StringUtils.isBlank(filter.getIuvEsatto())) {
            predicates.add(cbuilder.equal(cbuilder.upper(root.get("iuv")), filter.getIuvEsatto()));
        }

        if (!StringUtils.isBlank(filter.getIuv())) {
            predicates.add(cbuilder.like(cbuilder.upper(root.get("iuv")), "%" + filter.getIuv().toUpperCase() + "%"));
        }

        if (filter.getDataPagamentoInizio() != null) {
            predicates.add(cbuilder.greaterThanOrEqualTo(root.get("dataPagamento"), filter.getDataPagamentoInizio()));
        }

        if (filter.getDataPagamentoFine() != null) {
            predicates.add(cbuilder.lessThan(root.get("dataPagamento"), filter.getDataPagamentoFine()));
        }

        if (filter.getDataPagamentoScadenzaInizio() != null) {
            //			predicates.add(cbuilder.greaterThanOrEqualTo( joinFlusso.get("dtScadenza"), filter.getDataPagamentoScadenzaInizio()));

            if (Boolean.TRUE.equals(filter.getPagamentiSpontanei()))
            {
                predicates.add (
                    cbuilder.or (
                        cbuilder.greaterThanOrEqualTo(joinNotificaPagamento.get("dtScadenza"), filter.getDataPagamentoScadenzaInizio()),
                        cbuilder.greaterThanOrEqualTo(root.get("dataScadenza"), filter.getDataPagamentoScadenzaInizio())
                                    ) );
            }

            else
            {
                predicates.add (
                    cbuilder.or (
                        cbuilder.greaterThanOrEqualTo(joinPosizioneDebitoria.get("dtScadenza"), filter.getDataPagamentoScadenzaInizio()),
                        cbuilder.greaterThanOrEqualTo(root.get("dataScadenza"), filter.getDataPagamentoScadenzaInizio())
                                    ));
            }
        }


        if (filter.getDataPagamentoScadenzaFine() != null) {
            //			predicates.add(cbuilder.lessThan( joinFlusso.get("dtScadenza"), filter.getDataPagamentoScadenzaFine()));

            if (Boolean.TRUE.equals(filter.getPagamentiSpontanei()))
            {
                predicates.add (
                    cbuilder.or (
                        cbuilder.lessThan(joinNotificaPagamento.get("dtScadenza"), filter.getDataPagamentoScadenzaFine()),
                        cbuilder.lessThan(root.get("dataScadenza"), filter.getDataPagamentoScadenzaFine())
                                    ) );
            }

            else
            {
                predicates.add (
                    cbuilder.or (
                        cbuilder.lessThan( joinPosizioneDebitoria.get("dtScadenza"), filter.getDataPagamentoScadenzaFine()),
                        cbuilder.lessThan(root.get("dataScadenza"), filter.getDataPagamentoScadenzaFine())
                                    ));
            }
        }
        
        if (null !=  filter.getIdStatoPagamento ()) {
            predicates.add(cbuilder.equal(root.get("idStato"), filter.getIdStatoPagamento ()));
        }

        //capire come sistemarlo in un secondo momento. Per il momento la ricerca puntuale per codice versamento non trova i codici versamento non gestiti da pec
        if ( filter.getIdCodiceVersamento () != null ) {
            predicates.add (
                cbuilder.or (
                    cbuilder.equal ( joinCodiceVersamento.get ( "idCodiceVersamento" ), filter.getIdCodiceVersamento () ),
                    cbuilder.equal ( root.get ( "codVersamento" ), filter.getCodiceVersamento () ) ) );
        } else if ( ( codiciVersamentoVisibili != null ) && ( !codiciVersamentoVisibili.isEmpty () ) ) {
            predicates.add ( cbuilder.or (
                joinCodiceVersamento.get ( "idCodiceVersamento" ).in ( codiciVersamentoVisibili ),
                root.get ( "codVersamento" ).in ( codVersVisibili ) ) );
        }
        if ( idEnte != null ) {
            predicates.add (
                cbuilder.or (
                    cbuilder.equal ( joinEnte.get ( "idEnte" ), idEnte ),
                    cbuilder.and ( root.get ( "codFiscaleEnte" ).isNotNull (), cbuilder.equal ( root.get ( "codFiscaleEnte" ), cfEnte ) ) ) );
        }

        return predicates;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    static public List<Predicate> buildPredicatesPerTuttiPagamenti(PagamentiFilterDto filter, CriteriaBuilder cbuilder,
        Root<EpaypaTPagamenti> root, boolean fetch) {
        List<Predicate> predicates;

        if (filter == null) {
            filter = new PagamentiFilterDto();
        }

        Integer idEnte = null;
        String cfEnte = null;// Taccone per trattare i record gestiti da applicativi esterni, vedi gerica
        List<Integer> codiciVersamentoVisibili = null;
        List<String> codVersVisibili = null; // Taccone per trattare i record gestiti da applicativi esterni, vedi gerica

        if (filter.getProfilazioneUtente() != null) {
            idEnte = filter.getProfilazioneUtente().getEnte() != null ? filter.getProfilazioneUtente().getEnte().getId()
                            : -999;
            cfEnte = filter.getProfilazioneUtente ().getEnte () != null ?
                            filter.getProfilazioneUtente ().getEnte ().getCodFiscale () : "";// capire cosa mettere

            if (filter.getProfilazioneUtente().getCodiciVersamentoVisibili() == null
                            || filter.getProfilazioneUtente().getCodiciVersamentoVisibili().isEmpty()) {
                // nessun codice versamento visibile, ne aggiungo uno fittizio per filtrare
                // comunque quelli visibili
                codiciVersamentoVisibili = Arrays.asList(-999);
                codVersVisibili = Arrays.asList("");
            } else {
                codiciVersamentoVisibili = new ArrayList<>();
                codVersVisibili = new ArrayList<>();
                for (CodiceVersamentoDto cv : filter.getProfilazioneUtente().getCodiciVersamentoVisibili()) {
                    codiciVersamentoVisibili.add(cv.getId());
                    codVersVisibili.add(cv.getCod ());
                }
            }
        }

        //		fino qui i due sono uguali
        Join<EpaypaTPagamenti, EpaypaTPosizioneDebitoriaMedium> joinPosizioneDebitoria; // vale solo per i dovuti
        Join<EpaypaTFlusso, EpaypaTNotificaPagamentoMedium> joinNotificaPagamento; // vale solo per gli spontanei

        Join<EpaypaTPosizioneDebitoriaMedium, EpaypaTSoggetto> joinSoggettoDovuti;
        Join<EpaypaTNotificaPagamentoMedium, EpaypaTSoggetto> joinSoggettoSpontanei;

        Join<EpaypaTPosizioneDebitoriaMedium, EpaypaTFlusso> joinFlussoDovuti;
        Join<EpaypaTNotificaPagamentoMedium, EpaypaTFlusso> joinFlussoSpontanei;

        Join<EpaypaTFlusso, EpaypaTCodiceVersamento> joinCodiceVersamentoDovuti;
        Join<EpaypaTFlusso, EpaypaTCodiceVersamento> joinCodiceVersamentoSpontanei;

        Join<EpaypaTCodiceVersamento, EpaypaTEnte> joinEnteDovuti;
        Join<EpaypaTCodiceVersamento, EpaypaTEnte> joinEnteSpontanei;

        if (fetch) {
            joinPosizioneDebitoria = (Join) root.fetch("posizioneDebitoria", JoinType.LEFT);
            joinNotificaPagamento = (Join) root.fetch("notificaPagamento", JoinType.LEFT);
            joinFlussoSpontanei = (Join) joinNotificaPagamento.fetch("epaypaTFlusso", JoinType.LEFT);
            joinSoggettoSpontanei = (Join) joinNotificaPagamento.fetch("epaypaTSoggettoDebitore", JoinType.LEFT);

            joinFlussoDovuti = (Join) joinPosizioneDebitoria.fetch("epaypaTFlusso", JoinType.LEFT);
            joinSoggettoDovuti = (Join) joinPosizioneDebitoria.fetch("epaypaTSoggettoDebitore", JoinType.LEFT);

            joinCodiceVersamentoDovuti = (Join) joinFlussoDovuti.fetch("epaypaTCodiceVersamento", JoinType.LEFT);
            joinCodiceVersamentoSpontanei = (Join) joinFlussoSpontanei.fetch("epaypaTCodiceVersamento", JoinType.LEFT);
            joinEnteDovuti = (Join) joinCodiceVersamentoDovuti.fetch("epaypaTEnte", JoinType.LEFT);
            joinEnteSpontanei = (Join) joinCodiceVersamentoSpontanei.fetch("epaypaTEnte", JoinType.LEFT);
        } else {
            joinPosizioneDebitoria = root.join("posizioneDebitoria", JoinType.LEFT);
            joinNotificaPagamento = root.join("notificaPagamento", JoinType.LEFT);

            joinFlussoDovuti = joinPosizioneDebitoria.join("epaypaTFlusso", JoinType.LEFT);
            joinFlussoSpontanei = joinNotificaPagamento.join("epaypaTFlusso", JoinType.LEFT);

            joinSoggettoDovuti = joinPosizioneDebitoria.join("epaypaTSoggettoDebitore", JoinType.LEFT);
            joinSoggettoSpontanei = joinNotificaPagamento.join("epaypaTSoggettoDebitore", JoinType.LEFT);

            joinCodiceVersamentoDovuti = joinFlussoDovuti.join("epaypaTCodiceVersamento", JoinType.LEFT);
            joinCodiceVersamentoSpontanei = joinFlussoSpontanei.join("epaypaTCodiceVersamento", JoinType.LEFT);

            joinEnteDovuti = joinCodiceVersamentoDovuti.join("epaypaTEnte", JoinType.LEFT);
            joinEnteSpontanei = joinCodiceVersamentoSpontanei.join("epaypaTEnte", JoinType.LEFT);
        }

        predicates = new ArrayList<>();

        if (filter.getPagamentiSpontanei() != null) {
            predicates.add(cbuilder.equal(root.get("pagamentoSpontaneo"), filter.getPagamentiSpontanei()));
        }

        if ( !StringUtils.isBlank ( filter.getCodiceFiscale () ) ) {
            predicates.add (
                cbuilder.or (
                    cbuilder.like (
                        cbuilder.upper ( joinSoggettoDovuti.get ( "idUnivocoFiscale" ) ),
                        "%" + filter.getCodiceFiscale ().toUpperCase () + "%" ) ,
                    cbuilder.like (
                        cbuilder.upper ( joinSoggettoSpontanei.get ( "idUnivocoFiscale" ) ),
                        "%" + filter.getCodiceFiscale ().toUpperCase () + "%" ),
                    //CSI_PAG_AM-5296 occorre mettere in or anche il codice fiscale della tabella pagamenti
                    cbuilder.like (
                        cbuilder.upper ( root.get ( "idUnicoFiscale" ) ),
                        "%" + filter.getCodiceFiscale ().toUpperCase () + "%" )
                                )
                            );
        }

        if ( !StringUtils.isBlank ( filter.getCognome () ) ) {
            predicates.add (
                cbuilder.or (
                    cbuilder.like (
                        cbuilder.upper ( joinSoggettoDovuti.get ( "cognomeRagSoc" ) ),
                        "%" + filter.getCognome ().toUpperCase () + "%" ) ,
                    cbuilder.like (
                        cbuilder.upper ( joinSoggettoSpontanei.get ( "cognomeRagSoc" ) ),
                        "%" + filter.getCognome ().toUpperCase () + "%" ),
                    //CSI_PAG_AM-5296 occorre mettere in or anche il cognome/ragione sociale della tabella pagamenti
                    cbuilder.like (
                        cbuilder.upper ( root.get ( "cognomeRagioneSociale" ) ),
                        "%" + filter.getCognome ().toUpperCase () + "%" )
                                )
                            );
        }

        if (!StringUtils.isBlank(filter.getIuvEsatto())) {
            predicates.add(cbuilder.equal(cbuilder.upper(root.get("iuv")), filter.getIuvEsatto()));
        }

        if (!StringUtils.isBlank(filter.getIuv())) {
            predicates.add(cbuilder.like(cbuilder.upper(root.get("iuv")), "%" + filter.getIuv().toUpperCase() + "%"));
        }

        if (filter.getDataPagamentoInizio() != null) {
            predicates.add(cbuilder.greaterThanOrEqualTo(root.get("dataPagamento"), filter.getDataPagamentoInizio()));
        }

        if (filter.getDataPagamentoFine() != null) {
            predicates.add(cbuilder.lessThanOrEqualTo (root.get("dataPagamento"), filter.getDataPagamentoFine()));
        }

        if (filter.getDataPagamentoScadenzaInizio() != null && filter.getDataPagamentoScadenzaFine() != null) {
            predicates.add (
                cbuilder.or (
                    cbuilder.between(
                        joinPosizioneDebitoria.get("dtScadenza"), filter.getDataPagamentoScadenzaInizio(),filter.getDataPagamentoScadenzaFine() ),
                    cbuilder.between(
                        joinNotificaPagamento.get("dtScadenza"), filter.getDataPagamentoScadenzaInizio(),filter.getDataPagamentoScadenzaFine()),
                    cbuilder.between(
                        root.get("dataScadenza"), filter.getDataPagamentoScadenzaInizio(),filter.getDataPagamentoScadenzaFine())
                                )
                            );
        } else {
        if (filter.getDataPagamentoScadenzaInizio() != null) {
            predicates.add (
                cbuilder.or (
                    cbuilder.greaterThanOrEqualTo(
                        joinPosizioneDebitoria.get("dtScadenza"), filter.getDataPagamentoScadenzaInizio()),
                    cbuilder.greaterThanOrEqualTo(
                        joinNotificaPagamento.get("dtScadenza"), filter.getDataPagamentoScadenzaInizio()),
                    cbuilder.greaterThanOrEqualTo(
                        root.get("dataScadenza"), filter.getDataPagamentoScadenzaInizio())
                                )
                            );
        }

        if (filter.getDataPagamentoScadenzaFine() != null) {
            predicates.add (
                cbuilder.or (
                    cbuilder.lessThanOrEqualTo (
                        joinPosizioneDebitoria.get("dtScadenza"), filter.getDataPagamentoScadenzaFine()),
                    cbuilder.lessThanOrEqualTo (
                        joinNotificaPagamento.get("dtScadenza"), filter.getDataPagamentoScadenzaFine()),
                    cbuilder.lessThanOrEqualTo (
                        root.get("dataScadenza"), filter.getDataPagamentoScadenzaFine())
                                )
                            );
        }
        }
        
        if (null !=  filter.getIdStatoPagamento ()) {
            predicates.add(cbuilder.equal(root.get("idStato"), filter.getIdStatoPagamento ()));
        }

        //capire come sistemarlo in un secondo momento. Per il momento la ricerca puntuale per codice versamento non trova i codici versamento non gestiti da pec
        if ( filter.getIdCodiceVersamento () != null ) {
            predicates.add (
                cbuilder.or ( cbuilder.equal ( joinCodiceVersamentoDovuti.get ( "idCodiceVersamento" ), filter.getIdCodiceVersamento () ),
                    cbuilder.equal ( joinCodiceVersamentoSpontanei.get ( "idCodiceVersamento" ), filter.getIdCodiceVersamento () ),
                    cbuilder.equal ( root.get ( "codVersamento" ), filter.getCodiceVersamento () ) ) );
        } else if ( ( codiciVersamentoVisibili != null ) && ( !codiciVersamentoVisibili.isEmpty () ) ) {
            predicates.add (
                cbuilder.or (
                    joinCodiceVersamentoDovuti.get ( "idCodiceVersamento" ).in ( codiciVersamentoVisibili ),
                    joinCodiceVersamentoSpontanei.get ( "idCodiceVersamento" ).in ( codiciVersamentoVisibili ),
                    root.get ( "codVersamento" ).in ( codVersVisibili ) ) );
        }

        if (idEnte != null) {
            predicates.add(cbuilder.or(
                cbuilder.equal(joinEnteDovuti.get("idEnte"), idEnte),
                cbuilder.equal(joinEnteSpontanei.get("idEnte"), idEnte),
                // verificare che fine fanno i record con codFiscaleEnte non valorizzati, vedi pregresso
                cbuilder.equal(root.get("codFiscaleEnte"), cfEnte)
                            ));
        }
        
     // costi di notifica
        if (Boolean.TRUE.equals(filter.getCostiNotifica())) {    
        	predicates.add(cbuilder.equal(root.get("requiresCostUpdate"), filter.getCostiNotifica()));
        }
        if (Boolean.FALSE.equals(filter.getCostiNotifica())) {    
        	predicates.add(
        		cbuilder.or(
        			cbuilder.or(cbuilder.isFalse(root.get("requiresCostUpdate"))),
        			cbuilder.or(cbuilder.isNull(root.get("requiresCostUpdate")))
        		)
        	);
        }
        
        return predicates;
    }

    @Override
    public EpaypaTPagamenti findOneByIuv(String iuv) throws PersistenceException {
        String methodName = "findOneByIuv";
        
        

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            TypedQuery<EpaypaTPagamenti> query = entityManager.createNamedQuery("EpaypaTPagamenti.findByIuv",
                EpaypaTPagamenti.class);
            query.setParameter("iuv", iuv);

            return query.getSingleResult();
        } catch (Throwable e) {
            log.error("Errore imprevisto", e);
            throw new PersistenceException("Errore imprevisto in " + CLASSNAME + "::" + methodName, e);

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }
    }

	private List<Order> buildOrderBy ( List<CriterioOrdinamentoDto<ColumnNamePagamentiEnum>> ord, CriteriaBuilder cbuilder, Root<EpaypaTPagamenti> root, Boolean ispagamentoSpontaneo ) {
		List<Order> orderBys = new ArrayList<Order> ();

		boolean existsId = false;
		if ( ord != null ) {
			for ( CriterioOrdinamentoDto<ColumnNamePagamentiEnum> cri : ord ) {
				ColumnNamePagamentiEnum columnEnum = cri.getColumnNameEnum ();
				existsId = ( columnEnum == IUV ) || existsId;
				switch ( cri.getSortTypeEnum () ) {
				case ASC:
					if ( ispagamentoSpontaneo == null ) {
						orderBys.add ( cbuilder.asc ( EpaypaTPagamentoCommon.getColumnPath ( root, columnEnum, Boolean.FALSE ) ) );
						orderBys.add ( cbuilder.asc ( EpaypaTPagamentoCommon.getColumnPath ( root, columnEnum, Boolean.TRUE ) ) );
					} else {
						orderBys.add ( cbuilder.asc ( EpaypaTPagamentoCommon.getColumnPath ( root, columnEnum, ispagamentoSpontaneo ) ) );
					}
					break;
				case DESC:
					if ( ispagamentoSpontaneo == null ) {
						orderBys.add ( cbuilder.desc ( EpaypaTPagamentoCommon.getColumnPath ( root, columnEnum, Boolean.FALSE ) ) );
						orderBys.add ( cbuilder.desc ( EpaypaTPagamentoCommon.getColumnPath ( root, columnEnum, Boolean.TRUE ) ) );
					} else {
						orderBys.add ( cbuilder.desc ( EpaypaTPagamentoCommon.getColumnPath ( root, columnEnum, ispagamentoSpontaneo ) ) );
					}
					break;
				}
			}
		}
		// aggiunge come ultimo criterio l'ordinamento per id
		if ( !existsId ) {
			if ( ispagamentoSpontaneo == null ) {
				orderBys.add ( cbuilder.asc ( EpaypaTPagamentoCommon.getColumnPath ( root, IUV, Boolean.FALSE ) ) );
				orderBys.add ( cbuilder.asc ( EpaypaTPagamentoCommon.getColumnPath ( root, IUV, Boolean.TRUE ) ) );
			} else {
				orderBys.add ( cbuilder.asc ( EpaypaTPagamentoCommon.getColumnPath ( root, IUV, ispagamentoSpontaneo ) ) );
			}
		}
		return orderBys;
	}

}
