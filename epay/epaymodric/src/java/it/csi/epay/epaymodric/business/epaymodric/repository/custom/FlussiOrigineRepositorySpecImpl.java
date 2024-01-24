/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.repository.custom;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.codehaus.jackson.map.ser.StdSerializers.UtilDateSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;

import it.csi.epay.epaymodric.business.epaymodric.manager.utils.DTOFlussoOrigineUtility;
import it.csi.epay.epaymodric.business.epaymodric.model.CblDStatoFlusso;
import it.csi.epay.epaymodric.business.epaymodric.model.CblDStatoFlusso_;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTElaborazione;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTElaborazione_;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTEnte;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTEnte_;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTFlussoDettaglio;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTFlussoDettaglio_;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTFlussoOrigine;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTFlussoOrigine_;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTFlussoSintesi;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTFlussoSintesi_;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTPsp;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTPsp_;
import it.csi.epay.epaymodric.business.epaymodric.repository.FlussoOrigineRepository;
import it.csi.epay.epaymodric.business.epaymodric.utils.ConversionUtils;
import it.csi.epay.epaymodric.business.epaymodric.utils.Costanti;
import it.csi.epay.epaymodric.business.epaymodric.utils.DateUtils;
import it.csi.epay.epaymodric.dto.epaymodric.base.DTOFlussoOrigine;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsRicercaFlussoOrigine;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsFlussoOrigine;


public class FlussiOrigineRepositorySpecImpl implements FlussiOrigineRepositorySpecCustom {

    @Autowired
    private FlussoOrigineRepository flussoOrigineRepository;

    @PersistenceContext
    private EntityManager em;
    
    Logger logger = LogManager.getLogger ( this.getClass () );

    @Override
    public DTOOutputWsFlussoOrigine cercaPerFiltro ( final DTOInputWsRicercaFlussoOrigine ricerca ) {

        Specification<CblTFlussoOrigine> spec = new Specification<CblTFlussoOrigine> () {

            @Override
            public Predicate toPredicate ( Root<CblTFlussoOrigine> root, CriteriaQuery<?> query, CriteriaBuilder cb ) {
            	
            	
            	
            	Subquery<CblTFlussoOrigine> subqueryDistinct = query.subquery(CblTFlussoOrigine.class).distinct(true);
            	Root<CblTFlussoOrigine> subRoot = subqueryDistinct.from(CblTFlussoOrigine.class);
            	subqueryDistinct.select(subRoot);


                //inserire prime 3 and conditions
                /*
                 * and (orig.identificativo_istituto_ricevente=ente.codice_fiscale or id_elaborazione is null)
                 */
            	//query.distinct(true);
            	  Predicate predicate = cb.	conjunction ();
                Predicate predicateSub = cb.	conjunction ();

                //-----------------------------------------------------------
                //Identificativo flusso
                //-----------------------------------------------------------
                //and (orig.identificativo_flusso like '%' || upper( '2018-09-01ITBBITM1-ENG10-OK' ) || '%')--?
                if ( !StringUtils.isEmpty ( ricerca.getIdentificativoFlusso () ) ) {
                	
                	String identificativoFlusso = estraiIdentificativoFLusso(ricerca.getIdentificativoFlusso ());
                    predicateSub = cb.and ( predicateSub, cb.like (
                        cb.lower (
                            subRoot.get ( CblTFlussoOrigine_.identificativoFlusso ) ),
                        "%" + identificativoFlusso.toLowerCase () + "%" ) );
                }
                /*
                 *
                 */
                //and (orig.identificativo_istituto_ricevente='00000000003')--?
                //-----------------------------------------------------------
                //Istituto ricevente e idElaborazione non nullo
                //-----------------------------------------------------------
                if ( !StringUtils.isEmpty ( ricerca.getIdentificativoIstitutoRicevente () ) ) {
                    predicateSub = cb.and ( predicateSub, cb.equal ( cb.lower ( subRoot.get ( CblTFlussoOrigine_.identificativoIstitutoRicevente ) ),
                        ricerca.getIdentificativoIstitutoRicevente ().toLowerCase () ) );
                }

                if ( !StringUtils.isEmpty ( ricerca.getIdStatoFlusso () ) ) {
                    Join<CblTFlussoOrigine, CblDStatoFlusso> jointFlussoOrigineStatoFlusso = subRoot.join( CblTFlussoOrigine_.cblDStatoFlusso);
                    predicateSub = cb.and ( predicateSub, cb.equal ( jointFlussoOrigineStatoFlusso.get ( CblDStatoFlusso_.id ), ricerca.getIdStatoFlusso () ) );
                }
                //predicate = cb.and ( predicate, cb.isNotNull ( root.get ( CblTFlussoOrigine_.cblTElaborazione ) ) );

                /*
                 * EXISTS (SELECT data_elaborazione FROM epaymodric.cbl_t_elaborazione WHERE id_ente = (SELECT e.id_ente FROM epaymodric.cbl_t_ente e WHERE
                 * e.codice_fiscale = ?) AND id = o.id_elaborazione AND data_elaborazione IS NOT NULL AND data_elaborazione BETWEEN TO_DATE( ?,'DD/MM/YYYY') AND
                 * TO_DATE( ?,'DD/MM/YYYY'))
                 */
                if ( null != ricerca.getDataElaborazioneA () || null != ricerca.getDataElaborazioneDa () ) {
                    if ( null == ricerca.getDataElaborazioneDa () ) {
                        ricerca.setDataElaborazioneDa ( ConversionUtils.convertDateToXmlGregorianCalendar(new Date ( 0l )) );
                    }
                    if ( null == ricerca.getDataElaborazioneA () ) {
                        ricerca.setDataElaborazioneA (ConversionUtils.convertDateToXmlGregorianCalendar(new Date ()) );
                    }
                    Subquery<CblTElaborazione> subqueryElaborazione = subqueryDistinct.subquery ( CblTElaborazione.class );
                    Root<CblTElaborazione> rootElaborazione = subqueryElaborazione.from ( CblTElaborazione.class );
                    Root<CblTEnte> rootEnte = subqueryElaborazione.from ( CblTEnte.class );

                    subqueryElaborazione.select ( rootElaborazione.get ( CblTElaborazione_.dataElaborazione.getName () ) );

                    Predicate subqueryPredicate = cb.conjunction ();
                    if ( !StringUtils.isEmpty ( ricerca.getIdentificativoIstitutoRicevente () ) )
                        subqueryPredicate = cb.and ( cb.equal ( rootEnte.get ( CblTEnte_.codiceFiscale ), ricerca.getIdentificativoIstitutoRicevente () ) );

                    subqueryPredicate = cb.and ( subqueryPredicate,
                        cb.equal ( rootElaborazione.get ( CblTElaborazione_.cblTEnte ), rootEnte.get ( CblTEnte_.idEnte.getName () ) ),
                        cb.equal ( subRoot.get ( CblTFlussoOrigine_.cblTElaborazione ), rootElaborazione.get ( CblTElaborazione_.id ) ),
                        cb.isNotNull ( rootElaborazione.get ( CblTElaborazione_.dataElaborazione ) ),
                        cb.between ( rootElaborazione.get ( CblTElaborazione_.dataElaborazione ), DateUtils.xmlGregorianCalendarToTimestamp(ricerca.getDataElaborazioneDa ()),
                        		 DateUtils.dateToTimestamp(DateUtils.addDay(DateUtils.xmlGregorianCalendarToDate(ricerca.getDataElaborazioneA ()), 1))) );
//                        		DateUtils.xmlGregorianCalendarToTimestamp(ricerca.getDataElaborazioneA ())));
                    subqueryElaborazione.where ( subqueryPredicate );

                    predicateSub = cb.and( predicateSub, cb.exists ( subqueryElaborazione ) );

                }

                //PSP
                if ( !StringUtils.isEmpty ( ricerca.getPsp () ) ) {

                    Join<CblTFlussoOrigine, CblTPsp> psp = subRoot.join ( CblTFlussoOrigine_.cblTPsp );
                    Predicate predicateIdentificativoPSP = cb.like ( cb.lower ( psp.get ( CblTPsp_.identificativoPsp ) ),
                        "%" + ricerca.getPsp ().toLowerCase () + "%" );
                    Predicate predicateDescrizionePSP = cb.like ( cb.lower ( psp.get ( CblTPsp_.denominazionePsp ) ),
                        "%" + ricerca.getPsp ().toLowerCase () + "%" );
                    Predicate predicateOrPsp = cb.or ( predicateIdentificativoPSP, predicateDescrizionePSP );
                    predicateSub = cb.and ( predicateSub, predicateOrPsp );

                }
                
                if ( !StringUtils.isEmpty ( ricerca.getIdCodVersamento () ) ) {
                    Join<CblTFlussoOrigine, CblTFlussoSintesi> joinFlussoOrigineFlussoSintesi = subRoot.join ( CblTFlussoOrigine_.cblTFlussoSintesis );
                    
                    Predicate predicateIdentificativoCodVers
                        = cb.equal ( joinFlussoOrigineFlussoSintesi.get ( CblTFlussoSintesi_.codiceVersamento ), ricerca.getIdCodVersamento ());
                    predicateSub = cb.and ( predicateSub, predicateIdentificativoCodVers );
                }
                
                if ( !StringUtils.isEmpty ( ricerca.getIuv() ) ) 
                {
                	
                	 Root<CblTFlussoSintesi> rootflussoSintesi = subqueryDistinct.from ( CblTFlussoSintesi.class );
                	 Join<CblTFlussoSintesi, CblTFlussoDettaglio> iuvFlussoSintesi = rootflussoSintesi.join( CblTFlussoSintesi_.cblTFlussoDettaglios );
                	 
                	 Predicate predicateIuvFlussoSintesi = cb.equal(  iuvFlussoSintesi.get ( CblTFlussoDettaglio_.identificativoUnicoVersamento), ricerca.getIuv() );
                	 
                	 Join<CblTFlussoOrigine, CblTFlussoSintesi> iuvFlussoOrigine = subRoot.join( CblTFlussoOrigine_.cblTFlussoSintesis );
                     Predicate predicateIuvFlussoOrigine = cb.equal(  iuvFlussoOrigine.get (CblTFlussoSintesi_.id), 
                    		 rootflussoSintesi.get(CblTFlussoSintesi_.id ) );
                     
                    Predicate predicateIuvAnd= cb.and(predicateIuvFlussoSintesi, predicateIuvFlussoOrigine);
                    predicateSub = cb.and ( predicateSub, predicateIuvAnd );
                	 
                	
                }

                //Data ricezione
                if ( null != ricerca.getDataRicezioneA () || null != ricerca.getDataRicezioneDa () ) {
                    
                    predicate = cb.and ( predicate, cb.isNotNull ( root.get ( CblTFlussoOrigine_.dataoraFlusso ) ),
                            cb.between ( root.get ( CblTFlussoOrigine_.dataoraFlusso ), DateUtils.xmlGregorianCalendarToTimestamp ( ricerca.getDataRicezioneDa () ),
                            		 DateUtils.dateToTimestamp(DateUtils.addDay(DateUtils.xmlGregorianCalendarToDate(ricerca.getDataRicezioneA ()), 1))) );
                }
                predicateSub = cb.and(predicateSub, cb.equal(subRoot.get ( CblTFlussoOrigine_.id), root.get( CblTFlussoOrigine_.id)));
                
                subqueryDistinct.where ( predicateSub);
                predicate = cb.and ( predicate, cb.exists ( subqueryDistinct ) );
                

                return predicate;
            }

            private String estraiIdentificativoFLusso(String identificativoFlusso) {
            			identificativoFlusso = estraiSottoStringaIdentificativoFlusso(identificativoFlusso,Costanti.CAUSALE_PROVVISORIO_FORMATO_URI, " ");
            			identificativoFlusso = estraiSottoStringaIdentificativoFlusso(identificativoFlusso,Costanti.CAUSALE_PROVVISORIO_FORMATO_RFS, "/");
            			identificativoFlusso = estraiSottoStringaIdentificativoFlusso(identificativoFlusso,Costanti.CAUSALE_PROVVISORIO_FORMATO_RFB, "/");
            		
            	return identificativoFlusso;
            }

            private String estraiSottoStringaIdentificativoFlusso(String identificativoFlusso, String inizio, String fine) {
            	if (StringUtils.isNotEmpty(identificativoFlusso) && identificativoFlusso.contains(inizio))
            	{
            		identificativoFlusso= StringUtils.substringAfter(identificativoFlusso, inizio+"/");
            		if (StringUtils.isNotEmpty(identificativoFlusso))
            		{
            			identificativoFlusso= StringUtils.substringBefore(identificativoFlusso, fine);
            		}
            		if (StringUtils.isNotEmpty(identificativoFlusso))
            		{
            			identificativoFlusso= StringUtils.substringBefore(identificativoFlusso, " ");
            		}
            	}
            	return identificativoFlusso;
            }
        };
        //-----------------------------------------------------------
        //Query start
        //-----------------------------------------------------------
        DTOOutputWsFlussoOrigine dtoOutputdtoOutputWs = new DTOOutputWsFlussoOrigine ();
        Pageable pageable = new PageRequest ( ricerca.getStart (), ricerca.getLength (), "asc".equals(ricerca.getSortingDir()) ? Direction.ASC : Direction.DESC, getColumnPath(ricerca.getSortingCol()));
        Page<CblTFlussoOrigine> items = flussoOrigineRepository.findAll ( spec, pageable);
        List<DTOFlussoOrigine> flussi = new LinkedList<>();
        if ( items != null && items.hasContent () ) {
            for ( CblTFlussoOrigine item: items ) {
                flussi.add ( DTOFlussoOrigineUtility.getFlusso ( item ) );
            }
            dtoOutputdtoOutputWs.setFlussiOrigine ( flussi );
            dtoOutputdtoOutputWs.setTotalElements ( items.getTotalElements () );
            dtoOutputdtoOutputWs.setStart (  ricerca.getStart () );
            dtoOutputdtoOutputWs.setLength ( ricerca.getLength () );
        }
        else
        {
        	 dtoOutputdtoOutputWs.setFlussiOrigine ( flussi );
             dtoOutputdtoOutputWs.setTotalElements ( new Long(0) );
             dtoOutputdtoOutputWs.setStart (  0 );
             dtoOutputdtoOutputWs.setLength ( 0 );
        }
        return dtoOutputdtoOutputWs;
    }

    @Override
    public Page<CblTFlussoOrigine> cercaFlussiPerFiltro ( DTOInputWsRicercaFlussoOrigine ricerca ) {
        Specification<CblTFlussoOrigine> spec = new Specification<CblTFlussoOrigine> () {

            @Override
            public Predicate toPredicate ( Root<CblTFlussoOrigine> root, CriteriaQuery<?> query, CriteriaBuilder cb ) {
                query.distinct ( true );
                return predicateFlussiPerFiltro ( ricerca, root, query, cb );
            }
        };

        Pageable pageable = new PageRequest ( ricerca.getStart (), ricerca.getLength () );
        return flussoOrigineRepository.findAll ( spec, pageable );
    }

    @Override
    public Integer contaFlussiPerFiltro ( DTOInputWsRicercaFlussoOrigine ricerca ) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Integer> query = cb.createQuery(CblTFlussoOrigine_.numeroTotalePagamenti.getJavaType());
        Root<CblTFlussoOrigine> root = query.from ( CblTFlussoOrigine.class );
        query.where ( predicateFlussiPerFiltro ( ricerca, root, query, cb ) );
        query.select(cb.sum(root.get ( CblTFlussoOrigine_.numeroTotalePagamenti ) ));
        TypedQuery<Integer> typedQuery = em.createQuery(query);
        Integer resultNumber = typedQuery.getSingleResult();
        return  resultNumber != null ? resultNumber : 0;
    }
	
    private Predicate predicateFlussiPerFiltro ( DTOInputWsRicercaFlussoOrigine ricerca, Root<CblTFlussoOrigine> root, CriteriaQuery<?> query, CriteriaBuilder cb ) {
        //inserire prime 3 and conditions
        /*
         * and (orig.identificativo_istituto_ricevente=ente.codice_fiscale or id_elaborazione is null)
         */
        Predicate predicate = cb.conjunction ();

        //-----------------------------------------------------------
        //Identificativo flusso
        //-----------------------------------------------------------
        //and (orig.identificativo_flusso like '%' || upper( '2018-09-01ITBBITM1-ENG10-OK' ) || '%')--?
        if ( !StringUtils.isEmpty ( ricerca.getIdentificativoFlusso () ) ) {

            String identificativoFlusso = estraiIdentificativoFLusso ( ricerca.getIdentificativoFlusso () );
            predicate = cb.and ( predicate, cb.like (
                cb.lower (
                    root.get ( CblTFlussoOrigine_.identificativoFlusso ) ),
                "%" + identificativoFlusso.toLowerCase () + "%" ) );
        }
        
        
        
        /*
         *
         */
        //and (orig.identificativo_istituto_ricevente='00000000003')--?
        //-----------------------------------------------------------
        //Istituto ricevente e idElaborazione non nullo
        //-----------------------------------------------------------
        if ( !StringUtils.isEmpty ( ricerca.getIdentificativoIstitutoRicevente () ) ) {
            predicate = cb.and ( predicate, cb.equal ( cb.lower ( root.get ( CblTFlussoOrigine_.identificativoIstitutoRicevente ) ),
                ricerca.getIdentificativoIstitutoRicevente ().toLowerCase () ) );
        }

        if ( !StringUtils.isEmpty ( ricerca.getIdStatoFlusso () ) ) {
            Join<CblTFlussoOrigine, CblDStatoFlusso> jointFlussoOrigineStatoFlusso = root.join ( CblTFlussoOrigine_.cblDStatoFlusso );
            predicate = cb.and ( predicate, cb.equal ( jointFlussoOrigineStatoFlusso.get ( CblDStatoFlusso_.id ), ricerca.getIdStatoFlusso () ) );
        }
        //predicate = cb.and ( predicate, cb.isNotNull ( root.get ( CblTFlussoOrigine_.cblTElaborazione ) ) );

        /*
         * EXISTS (SELECT data_elaborazione FROM epaymodric.cbl_t_elaborazione WHERE id_ente = (SELECT e.id_ente FROM epaymodric.cbl_t_ente e WHERE
         * e.codice_fiscale = ?) AND id = o.id_elaborazione AND data_elaborazione IS NOT NULL AND data_elaborazione BETWEEN TO_DATE( ?,'DD/MM/YYYY') AND
         * TO_DATE( ?,'DD/MM/YYYY'))
         */
        if ( null != ricerca.getDataElaborazioneA () || null != ricerca.getDataElaborazioneDa () ) {
            if ( null == ricerca.getDataElaborazioneDa () ) {
                ricerca.setDataElaborazioneDa ( ConversionUtils.convertDateToXmlGregorianCalendar ( new Date ( 0l ) ) );
            }
            if ( null == ricerca.getDataElaborazioneA () ) {
                ricerca.setDataElaborazioneA ( ConversionUtils.convertDateToXmlGregorianCalendar ( new Date () ) );
            }
            Subquery<CblTElaborazione> subqueryElaborazione = query.subquery ( CblTElaborazione.class );
            Root<CblTElaborazione> rootElaborazione = subqueryElaborazione.from ( CblTElaborazione.class );
            Root<CblTEnte> rootEnte = subqueryElaborazione.from ( CblTEnte.class );

            subqueryElaborazione.select ( rootElaborazione.get ( CblTElaborazione_.dataElaborazione.getName () ) );

            Predicate subqueryPredicate = cb.conjunction ();
            if ( !StringUtils.isEmpty ( ricerca.getIdentificativoIstitutoRicevente () ) )
                subqueryPredicate = cb.and ( cb.equal ( rootEnte.get ( CblTEnte_.codiceFiscale ), ricerca.getIdentificativoIstitutoRicevente () ) );

            subqueryPredicate = cb.and ( subqueryPredicate,
                cb.equal ( rootElaborazione.get ( CblTElaborazione_.cblTEnte ), rootEnte.get ( CblTEnte_.idEnte.getName () ) ),
                cb.equal ( root.get ( CblTFlussoOrigine_.cblTElaborazione ), rootElaborazione.get ( CblTElaborazione_.id ) ),
                cb.isNotNull ( rootElaborazione.get ( CblTElaborazione_.dataElaborazione ) ),
                cb.between ( rootElaborazione.get ( CblTElaborazione_.dataElaborazione ),
                    DateUtils.xmlGregorianCalendarToTimestamp ( ricerca.getDataElaborazioneDa () ),
                    DateUtils.dateToTimestamp(DateUtils.addDay(DateUtils.xmlGregorianCalendarToDate(ricerca.getDataElaborazioneA ()), 1))) );
//                    DateUtils.xmlGregorianCalendarToTimestamp ( ricerca.getDataElaborazioneA () ) ) );
            subqueryElaborazione.where ( subqueryPredicate );

            predicate = cb.and ( predicate, cb.exists ( subqueryElaborazione ) );

        }

        //PSP
        if ( !StringUtils.isEmpty ( ricerca.getPsp () ) ) {

            Join<CblTFlussoOrigine, CblTPsp> psp = root.join ( CblTFlussoOrigine_.cblTPsp );
            Predicate predicateIdentificativoPSP = cb.like ( cb.lower ( psp.get ( CblTPsp_.identificativoPsp ) ),
                "%" + ricerca.getPsp ().toLowerCase () + "%" );
            Predicate predicateDescrizionePSP = cb.like ( cb.lower ( psp.get ( CblTPsp_.denominazionePsp ) ),
                "%" + ricerca.getPsp ().toLowerCase () + "%" );
            Predicate predicateOrPsp = cb.or ( predicateIdentificativoPSP, predicateDescrizionePSP );
            predicate = cb.and ( predicate, predicateOrPsp );

        }

        if ( !StringUtils.isEmpty ( ricerca.getIdCodVersamento () ) ) {
            Join<CblTFlussoOrigine, CblTFlussoSintesi> joinFlussoOrigineFlussoSintesi = root.join ( CblTFlussoOrigine_.cblTFlussoSintesis );

            Predicate predicateIdentificativoCodVers
                = cb.equal ( joinFlussoOrigineFlussoSintesi.get ( CblTFlussoSintesi_.codiceVersamento ), ricerca.getIdCodVersamento () );
            predicate = cb.and ( predicate, predicateIdentificativoCodVers );
        }

        if ( !StringUtils.isEmpty ( ricerca.getIuv () ) ) {

            Root<CblTFlussoSintesi> rootflussoSintesi = query.from ( CblTFlussoSintesi.class );
            Join<CblTFlussoSintesi, CblTFlussoDettaglio> iuvFlussoSintesi = rootflussoSintesi.join ( CblTFlussoSintesi_.cblTFlussoDettaglios );

            Predicate predicateIuvFlussoSintesi = cb.equal ( iuvFlussoSintesi.get ( CblTFlussoDettaglio_.identificativoUnicoVersamento ), ricerca.getIuv () );

            Join<CblTFlussoOrigine, CblTFlussoSintesi> iuvFlussoOrigine = root.join ( CblTFlussoOrigine_.cblTFlussoSintesis );
            Predicate predicateIuvFlussoOrigine = cb.equal ( iuvFlussoOrigine.get ( CblTFlussoSintesi_.id ),
                rootflussoSintesi.get ( CblTFlussoSintesi_.id ) );

            Predicate predicateIuvAnd = cb.and ( predicateIuvFlussoSintesi, predicateIuvFlussoOrigine );
            predicate = cb.and ( predicate, predicateIuvAnd );

        }

        //Data ricezione
        if ( null != ricerca.getDataRicezioneA () || null != ricerca.getDataRicezioneDa () ) {
        	
            predicate = cb.and ( predicate, cb.isNotNull ( root.get ( CblTFlussoOrigine_.dataoraFlusso ) ),
                cb.between ( root.get ( CblTFlussoOrigine_.dataoraFlusso ), DateUtils.xmlGregorianCalendarToTimestamp ( ricerca.getDataRicezioneDa () ),
                		 DateUtils.dateToTimestamp(DateUtils.addDay(DateUtils.xmlGregorianCalendarToDate(ricerca.getDataRicezioneA ()), 1))) );
            
            
            
           ;
            
        }

        return predicate;
    }
	    
    private String estraiIdentificativoFLusso ( String identificativoFlusso ) {
        identificativoFlusso = estraiSottoStringaIdentificativoFlusso ( identificativoFlusso, Costanti.CAUSALE_PROVVISORIO_FORMATO_URI, " " );
        identificativoFlusso = estraiSottoStringaIdentificativoFlusso ( identificativoFlusso, Costanti.CAUSALE_PROVVISORIO_FORMATO_RFS, "/" );
        identificativoFlusso = estraiSottoStringaIdentificativoFlusso ( identificativoFlusso, Costanti.CAUSALE_PROVVISORIO_FORMATO_RFB, "/" );

        return identificativoFlusso;
    }

    private String estraiSottoStringaIdentificativoFlusso ( String identificativoFlusso, String inizio, String fine ) {
        if ( StringUtils.isNotEmpty ( identificativoFlusso ) && identificativoFlusso.contains ( inizio ) ) {
            identificativoFlusso = StringUtils.substringAfter ( identificativoFlusso, inizio + "/" );
            if ( StringUtils.isNotEmpty ( identificativoFlusso ) ) {
                identificativoFlusso = StringUtils.substringBefore ( identificativoFlusso, fine );
            }
            if ( StringUtils.isNotEmpty ( identificativoFlusso ) ) {
                identificativoFlusso = StringUtils.substringBefore ( identificativoFlusso, " " );
            }
        }
        return identificativoFlusso;
    }
    
    private String getColumnPath(String sortingCol) {
    	
    	switch(sortingCol) {
    		case "identificativoFlusso":
    			return "identificativoFlusso";
    		case "dataOraFlusso":
    			return "dataoraFlusso";
    		case "identificativoPsp":
    			return "cblTPsp.identificativoPsp";
    		case "importoTotalePagamenti":
    			return "importoTotalePagamenti";
    		case "numeroTotalePagamenti":
    			return "numeroTotalePagamenti";
    		case "contatoreTentativi":
    			return "contatoreTentativi";
    		case "identificativoIstitutoRicevente":
    			return "identificativoIstitutoRicevente";
    		case "identificativoIstitutoRiceventeDescrizione":
    			return "identificativoIstitutoRiceventeDescrizione";
    		case "flussoPlurintermediato":
    			return "flagFlussoIntermediato";
    		case "descrizioneStato":
    			return "cblDStatoFlusso.descrizioneStato";
    		default:
    			return "identificativoFlusso";
    	}
    }
}
