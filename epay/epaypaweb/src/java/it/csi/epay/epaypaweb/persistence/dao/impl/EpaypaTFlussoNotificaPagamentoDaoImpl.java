/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.impl;

import static it.csi.epay.epaypaweb.enumeration.ColumnNameNotificaPagamentoEnum.COGNOME_OR_RAGIONE_SOCIALE_DEBITORE;
import static it.csi.epay.epaypaweb.enumeration.ColumnNameNotificaPagamentoEnum.DATA_INSERIMENTO;
import static it.csi.epay.epaypaweb.enumeration.ColumnNameNotificaPagamentoEnum.DATA_ULTIMA_VARIAZIONE;
import static it.csi.epay.epaypaweb.enumeration.ColumnNameNotificaPagamentoEnum.ID_CODICE_VERSAMENTO;
import static it.csi.epay.epaypaweb.enumeration.ColumnNameNotificaPagamentoEnum.ID_FLUSSO;
import static it.csi.epay.epaypaweb.enumeration.ColumnNameNotificaPagamentoEnum.IUV;
import static it.csi.epay.epaypaweb.enumeration.ColumnNameNotificaPagamentoEnum.PAGAMENTI_SPONTANEI;
import static it.csi.epay.epaypaweb.enumeration.ColumnNameNotificaPagamentoEnum.STATO_FLUSSO;
import static it.csi.epay.epaypaweb.enumeration.ColumnNameNotificaPagamentoEnum.TIPO_FLUSSO;
import static it.csi.epay.epaypaweb.enumeration.ColumnNameNotificaPagamentoEnum.UTENTE_ULTIMA_VARIAZIONE;
import static it.csi.epay.epaypaweb.util.Util.getLaterDate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import it.csi.epay.epaypaweb.dto.CriterioOrdinamentoDto;
import it.csi.epay.epaypaweb.dto.FlussoLightFilterDto;
import it.csi.epay.epaypaweb.dto.NotificheRevoca;
import it.csi.epay.epaypaweb.dto.PaginazioneDto;
import it.csi.epay.epaypaweb.enumeration.ColumnNameNotificaPagamentoEnum;
import it.csi.epay.epaypaweb.enumeration.StatoFlussoEnum;
import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBaseImpl;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTFlussoNotificaPagamentoDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTFlussoNotificaPagamento;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTNotificaPagamento;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTRr;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTTipoRevoca;


public class EpaypaTFlussoNotificaPagamentoDaoImpl extends EpaypaDaoBaseImpl<Long, EpaypaTFlussoNotificaPagamento>
implements EpaypaTFlussoNotificaPagamentoDao {

    static private final String CLASSNAME = EpaypaDaoBaseImpl.class.getSimpleName ();

    static final private long MAX_RESULTS = 500L;

    @Override
    public Long countAllByFilter(FlussoLightFilterDto filter, String codUtente) throws PersistenceException {
        String methodName = "countAllByFilter";
        
        
        

        Long num = null;
        

        try {
            // costruisce la query dinamicamente
            CriteriaBuilder cbuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Long> cquery = cbuilder.createQuery(Long.class);
            Root<EpaypaTNotificaPagamento> root = cquery.from(EpaypaTNotificaPagamento.class);
            cquery.select(cbuilder.count(root));
            List<Predicate> predicates = buildPredicates(filter, cbuilder, root);
            Predicate userRestrictions = buildUserRestrictions(codUtente, cbuilder, root);
            if (userRestrictions != null) {
                predicates.add(userRestrictions);
            }
            if (predicates != null && !predicates.isEmpty()) {
                cquery.where(cbuilder.and(predicates.toArray(new Predicate[predicates.size()])));
            }

            // esegue la query
            num = entityManager.createQuery(cquery).getSingleResult();

            //Non ha molto senso ma lo uso per i tests
            //EPAY-120-2
            //if(num > MAX_RESULTS) num = MAX_RESULTS;

        } catch (Throwable e) {
            log.error("Errore imprevisto", e);
            throw new PersistenceException("Errore imprevisto in " + CLASSNAME + "::" + methodName, e);

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }
        return num;
    }

    static public Predicate buildUserRestrictions(String codUtente, CriteriaBuilder cbuilder, Root<EpaypaTNotificaPagamento> root) {
        if (codUtente != null) {
            List<Predicate> orPredicates = new ArrayList<Predicate>();
            orPredicates.add(cbuilder.equal(getColumnPath(root, UTENTE_ULTIMA_VARIAZIONE), codUtente));
            orPredicates.add(cbuilder.notEqual(getColumnPath(root, STATO_FLUSSO), StatoFlussoEnum.BOZZA.getId()));
            return cbuilder.or(orPredicates.toArray((new Predicate[orPredicates.size()])));
        }
        return null;
    }

    static public List<Predicate> buildPredicates(FlussoLightFilterDto filter, CriteriaBuilder cbuilder, Root<EpaypaTNotificaPagamento> root) {
        List<Predicate> predicates = null;

        if (filter != null) {
            predicates = new ArrayList<Predicate>();

            if (filter.getPagamentiSpontanei() != null) {
                predicates.add(cbuilder.equal(getColumnPath(root, PAGAMENTI_SPONTANEI), filter.getPagamentiSpontanei()));
            }
            if (filter.getTipoFlusso() != null) {
                predicates.add(cbuilder.equal(getColumnPath(root, TIPO_FLUSSO), filter.getTipoFlusso().getId()));
            }
            if (filter.getDataInserimentoDa() != null) {
                predicates.add(cbuilder.greaterThanOrEqualTo(getColumnPath(root, DATA_INSERIMENTO), filter.getDataInserimentoDa()));
            }
            if (filter.getDataInserimentoA() != null) {
                predicates.add(cbuilder.lessThan(getColumnPath(root, DATA_INSERIMENTO), getLaterDate(filter.getDataInserimentoA())));
            }
            if ((filter.getCognome () != null) && (!filter.getCognome ().isEmpty ())) {
                Predicate p = cbuilder.like( cbuilder.upper ( getColumnPath(root, COGNOME_OR_RAGIONE_SOCIALE_DEBITORE) ), "%" + filter.getCognome ().toUpperCase () + "%");
                predicates.add(p);
            }
            if ((filter.getIuv () != null)  && (!filter.getIuv ().isEmpty ())) {
                predicates.add(cbuilder.equal(getColumnPath(root, IUV), filter.getIuv ()));
            }
            if (filter.getStatoFlusso() != null) {
                predicates.add(cbuilder.equal(getColumnPath(root, STATO_FLUSSO), filter.getStatoFlusso().getId()));
            }
            if (filter.getUtenteUltimaVariazione() != null) {
                predicates.add(cbuilder.equal(getColumnPath(root, UTENTE_ULTIMA_VARIAZIONE), filter.getUtenteUltimaVariazione()));
            }
            if (filter.getDataUltimaVariazioneDa() != null) {
                predicates.add(cbuilder.greaterThanOrEqualTo(getColumnPath(root, DATA_ULTIMA_VARIAZIONE), filter.getDataUltimaVariazioneDa()));
            }
            if (filter.getDataUltimaVariazioneA() != null) {
                predicates.add(cbuilder.lessThan(getColumnPath(root, DATA_ULTIMA_VARIAZIONE), getLaterDate(filter.getDataUltimaVariazioneA())));
            }
            if (filter.getIdCodVersamentoList() != null && !filter.getIdCodVersamentoList().isEmpty() ) {
                predicates.add(getColumnPath(root, ID_CODICE_VERSAMENTO).in(filter.getIdCodVersamentoList()));
            }

            //            if (filter.getIdEnte() != null) {
            //                predicates.add(cbuilder.equal(getColumnPath(root, ID_ENTE), filter.getIdEnte()));
            //            }
            //            if (filter.getCodEsito() != null) {
            //                predicates.add(cbuilder.equal(getColumnPath(root, CODICE_ESITO), filter.getCodEsito()));
            //            }
            //            if (filter.getDetEsito() != null) {
            //                predicates.add(cbuilder.equal(getColumnPath(root, DETTAGLIO_ESITO), filter.getDetEsito()));
            //            }
        }

        return predicates;
    }    

    static public Path getColumnPath(Root<EpaypaTNotificaPagamento> root, ColumnNameNotificaPagamentoEnum columnEnum) {
        Path columnPath = null;

        if (columnEnum != null) {
            switch (columnEnum) {

            case ID_POSIZIONE_DEBITORIA:
                columnPath = root.get("idPosizioneDebitoria");
                break;
            case PAGAMENTI_SPONTANEI:
                columnPath = root.get("epaypaTFlusso").get("pagamentiSpontanei");
                break;
            case TIPO_FLUSSO:
                columnPath = root.get("epaypaTFlusso").get ( "epaypaDTipoFlusso" ).get("idTipoFlusso");
                break;
            case DATA_INSERIMENTO:
                columnPath = root.get("epaypaTFlusso").get("dtInserimento");
                break;
            case COGNOME_OR_RAGIONE_SOCIALE_DEBITORE:
                columnPath = root.get("epaypaTSoggettoDebitore").get("cognomeRagSoc");
                break;
            case IUV:
                columnPath = root.get("iuv");
                break;                
            case STATO_FLUSSO:
                columnPath = root.get("epaypaTFlusso").get("epaypaDStatoFlusso").get("idStatoFlusso");              
                break;
            case UTENTE_ULTIMA_VARIAZIONE:
                columnPath = root.get("epaypaTFlusso").get("utenteUltimaVariazione");              
                break;
            case DATA_ULTIMA_VARIAZIONE:
                columnPath = root.get("epaypaTFlusso").get("dtUltimaVariazione");              
                break;
            case ID_CODICE_VERSAMENTO:
                columnPath = root.get("epaypaTFlusso").get("epaypaTCodiceVersamento").get("idCodiceVersamento");
                break;
            case IMPORTO_PAGATO:
                //EPAY-120
                //columnPath = root.get ( "epaypaTFlusso" ).get ( "importoTotale" );
                columnPath = root.get ( "importoPagato" );
                break;
            case DATA_ESITO_PAGAMENTO:
                columnPath = root.get("dtEsitoPagamento" );
                break;
            case DESCRIZIONE_CAUSALE_VERSAMENTO:
                columnPath = root.get("desCausaleVersamento" );
                break;
            case ID_FISCALE_DEBITORE:
                columnPath = root.get("epaypaTSoggettoDebitore" ).get ( "idUnivocoFiscale" );
                break;
            case COD_VERSAMENTO:
                columnPath = root.get("epaypaTFlusso").get ( "epaypaTCodiceVersamento" ).get("idCodiceVersamento");
                break;
            case ID_FLUSSO:
                columnPath = root.get("epaypaTFlusso").get ( "idFlusso");
                break;
            }
        }

        return columnPath;
    }


    @Override
    //@formatter:off
    public List<EpaypaTNotificaPagamento> findAllLightByFilter(FlussoLightFilterDto filter, List<CriterioOrdinamentoDto<ColumnNameNotificaPagamentoEnum>> ord, PaginazioneDto pag, String codUtente)
                    throws PersistenceException
                    //@formatter:on
    {
        String methodName = "findAllLightByFilter";
        
        
        
        
        

        List<EpaypaTNotificaPagamento> entityList = new ArrayList<EpaypaTNotificaPagamento>();

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            if (pag != null && pag.getNumeroPagina() != null && pag.getNumeroPagina() < 1) {
                log.warn("numero di pagina richiesto zero o negativo");

            } else if (pag != null && pag.getNumeroRighePerPagina() != null && pag.getNumeroRighePerPagina() < 1) {
                log.warn("numero di righe per pagina richieste zero o negativo");

          
        }else {               
                // costruisce la query dinamicamente
                CriteriaBuilder cbuilder = entityManager.getCriteriaBuilder();
                CriteriaQuery<EpaypaTNotificaPagamento> cquery = cbuilder.createQuery(EpaypaTNotificaPagamento.class);
                Root<EpaypaTNotificaPagamento> root = cquery.from(EpaypaTNotificaPagamento.class);
                cquery.select(root);
                List<Predicate> predicates = buildPredicates(filter, cbuilder, root);
                Predicate userRestrictions = buildUserRestrictions(codUtente, cbuilder, root);
                if (userRestrictions != null) {
                    predicates.add(userRestrictions);
                }
                if (predicates != null && !predicates.isEmpty()) {
                    cquery.where(cbuilder.and(predicates.toArray(new Predicate[predicates.size()])));
                }
                List<Order> orderBys = buildOrderBy(ord, cbuilder, root);
                if (orderBys != null && !orderBys.isEmpty()) {
                    cquery.orderBy(orderBys);
                }

                // assegna i parametri di paginazione
                TypedQuery<EpaypaTNotificaPagamento> query = entityManager.createQuery(cquery);
                if (pag != null) {
                    if (pag.getNumeroPagina() != null) {
                        query.setFirstResult((pag.getNumeroPagina() - 1) * pag.getNumeroRighePerPagina());
                    }
                    if (pag.getNumeroRighePerPagina() != null) {
                        //EPAY-120-2
                        //                        if(pag.getNumeroRighePerPagina() > 500)
                        //                            query.setMaxResults((int) MAX_RESULTS);
                        //                        else 
                        query.setMaxResults(pag.getNumeroRighePerPagina());
                    }
                }

                // esegue la query
                entityList = query.getResultList();

              //<!-- CSI_PAG-184 -->
                for (EpaypaTNotificaPagamento e : entityList) {
                    if(e.getIdRR ()!=null) {
                        TypedQuery<EpaypaTRr> queryRevoca = entityManager.createNamedQuery("EpaypaTRr.findRevocaByIdRr", EpaypaTRr.class);
                        queryRevoca.setParameter("idRr", e.getIdRR ());
                        //
                        List<EpaypaTRr> entityListRevoca = new ArrayList<EpaypaTRr>();
                        entityListRevoca = queryRevoca.getResultList();
                        for (EpaypaTRr tr:entityListRevoca) {
                               if(tr.getTipoRevoca ()!=null) {
                                   TypedQuery<EpaypaTTipoRevoca> queryDescrRevoca = entityManager.createNamedQuery( "EpaypaTTipoRevoca.findTipoRevocaById",EpaypaTTipoRevoca.class);
                                   queryDescrRevoca.setParameter("id", tr.getTipoRevoca ());
                                   List<EpaypaTTipoRevoca> entityListDescrRevoca = new ArrayList<EpaypaTTipoRevoca>();
                                   entityListDescrRevoca = queryDescrRevoca.getResultList();
                                   String descrizione=entityListDescrRevoca.get ( 0 ).getDescrizione ();
                                   e.setRevoca  ( descrizione );
                                   entityList.set ( entityList.indexOf ( e ) , e );
                                   
                               }
                        }
                    }
                }

            }
        } catch (Throwable e) {
            log.error("Errore imprevisto", e);
            throw new PersistenceException("Errore imprevisto in " + CLASSNAME + "::" + methodName, e);

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return entityList;
    }

    private List<Order> buildOrderBy(List<CriterioOrdinamentoDto<ColumnNameNotificaPagamentoEnum>> ord, CriteriaBuilder cbuilder, Root<EpaypaTNotificaPagamento> root) {
        List<Order> orderBys = new ArrayList<Order>();

        boolean existsId = false;

        if (ord != null) {
            for (CriterioOrdinamentoDto<ColumnNameNotificaPagamentoEnum> cri : ord) {
                ColumnNameNotificaPagamentoEnum columnEnum = cri.getColumnNameEnum();
                existsId = (columnEnum == ID_FLUSSO) ? true : existsId;

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
            orderBys.add(cbuilder.asc(getColumnPath(root, ID_FLUSSO)));
        }

        return orderBys;
    }




}
