/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.repository.custom;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.RicercaRiferimentoContabileInput;
import it.csi.epay.epaypacatalogsrv.model.CodiceVersamento;
import it.csi.epay.epaypacatalogsrv.model.CodiceVersamento_;
import it.csi.epay.epaypacatalogsrv.model.Ente_;
import it.csi.epay.epaypacatalogsrv.model.MacrotipoPpay_;
import it.csi.epay.epaypacatalogsrv.model.RiferimentoContabile;
import it.csi.epay.epaypacatalogsrv.model.RiferimentoContabile_;
import it.csi.epay.epaypacatalogsrv.model.TematicaPpay_;
import it.csi.epay.epaypacatalogsrv.model.VoceEntrata_;
import it.csi.epay.epaypacatalogsrv.repository.RiferimentoContabileRepository;
import it.csi.epay.epaypacatalogsrv.repository.util.CriteriaBuilderUtil;


public class RiferimentoContabileRepositoryImpl implements RiferimentoContabileRepositoryCustom {

	@Autowired
	private RiferimentoContabileRepository baseRepository;

	@Override
	public List<RiferimentoContabile> ricerca ( RicercaRiferimentoContabileInput input, Long idEnte ) {

		List<Specification<RiferimentoContabile>> filters = new ArrayList<> ();

		filters.add ( new Specification<RiferimentoContabile> () {

			@Override
			public Predicate toPredicate ( Root<RiferimentoContabile> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
				return builder.or (
					builder.isNull ( root.get ( RiferimentoContabile_.flagAnnullato ) ),
					builder.isFalse ( root.get ( RiferimentoContabile_.flagAnnullato ) )
								);
			}
		} );

		filters.add ( new Specification<RiferimentoContabile> () {

			@Override
			public Predicate toPredicate ( Root<RiferimentoContabile> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
				return builder.equal (
					root.get ( RiferimentoContabile_.codiceVersamento ).get ( CodiceVersamento_.ente ).get ( Ente_.id ), idEnte );
			}
		} );

		if ( input.getIdCodiceVersamento () != null ) {
			filters.add ( new Specification<RiferimentoContabile> () {

				@Override
				public Predicate toPredicate ( Root<RiferimentoContabile> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
					return builder.equal (
						root.get ( RiferimentoContabile_.codiceVersamento ).get ( CodiceVersamento_.id ), input.getIdCodiceVersamento () );
				}
			} );
		}

		if ( !StringUtils.isBlank ( input.getCodiceVoceEntrata () ) ) {
			filters.add ( new Specification<RiferimentoContabile> () {

				@Override
				public Predicate toPredicate ( Root<RiferimentoContabile> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
					return builder.like (
						builder.lower (
							root.get ( RiferimentoContabile_.codiceVersamento ).get ( CodiceVersamento_.voceEntrata ).get ( VoceEntrata_.codice ) ),
						"%" + input.getCodiceVoceEntrata ().toLowerCase () + "%" );
				}
			} );
		}

		if ( !StringUtils.isBlank ( input.getDescrizioneCodiceVersamento () ) ) {
			filters.add ( new Specification<RiferimentoContabile> () {

				@Override
				public Predicate toPredicate ( Root<RiferimentoContabile> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
					return builder.like (
						builder.lower (
							root.get ( RiferimentoContabile_.codiceVersamento ).get ( CodiceVersamento_.descrizione ) ),
						"%" + input.getDescrizioneCodiceVersamento ().toLowerCase () + "%" );
				}
			} );
		}

		if ( !StringUtils.isBlank ( input.getCodiceMacrotipo () ) ) {
			filters.add ( new Specification<RiferimentoContabile> () {

				@Override
				public Predicate toPredicate ( Root<RiferimentoContabile> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
					return builder.equal (
						root.get ( RiferimentoContabile_.codiceVersamento )
						.get ( CodiceVersamento_.voceEntrata )
						.get ( VoceEntrata_.macrotipo )
						.get ( MacrotipoPpay_.codice ),
						input.getCodiceMacrotipo () );
				}
			} );
		}

		if ( !StringUtils.isBlank ( input.getCodiceTematica () ) ) {
			filters.add ( new Specification<RiferimentoContabile> () {

				@Override
				public Predicate toPredicate ( Root<RiferimentoContabile> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
					return builder.equal (
						root.get ( RiferimentoContabile_.codiceVersamento )
						.get ( CodiceVersamento_.voceEntrata )
						.get ( VoceEntrata_.tematica )
						.get ( TematicaPpay_.codice ),
						input.getCodiceTematica () );
				}
			} );
		}
		
		if (  input.getAnnoEsercizio () != null  ) {
			filters.add ( new Specification<RiferimentoContabile> () {

				@Override
				public Predicate toPredicate ( Root<RiferimentoContabile> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
					return builder.equal (
						root.get ( RiferimentoContabile_.annoEsercizio ),
						input.getAnnoEsercizio () );
				}
			} );
		}

		if ( input.getSoloRiferimentiInVita () != null && input.getSoloRiferimentiInVita ().booleanValue () ) {

			filters.add ( new Specification<RiferimentoContabile> () {

				//                Date now = new Date ();

				//                @Override
				//                public Predicate toPredicate ( Root<RiferimentoContabile> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
				//                    return builder.and (
				//                        builder.isNotNull ( root.get ( RiferimentoContabile_.dataInizioValidita ) ),
				//                        builder.lessThanOrEqualTo ( root.get ( RiferimentoContabile_.dataInizioValidita ), now ),
				//                        builder.or (
				//                            builder.isNull ( root.get ( RiferimentoContabile_.dataFineValidita ) ),
				//                            builder.greaterThan ( root.get ( RiferimentoContabile_.dataFineValidita ), now ) )
				//                    );
				//                }

				@Override
				public Predicate toPredicate ( Root<RiferimentoContabile> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
					return builder.and (
						builder.isNotNull ( root.get ( RiferimentoContabile_.dataInizioValidita ) ),
						builder.isNull ( root.get ( RiferimentoContabile_.dataFineValidita ) )
									);
				}
			} );
		}

		List<RiferimentoContabile> records = baseRepository.findAll ( CriteriaBuilderUtil.groupAnd ( filters ),
			new Sort ( "codiceVersamento.codice", "id" ) );
		return records;
	}


	@Override
	public List<RiferimentoContabile> ricercaInScadenza ( Long idEnte, Long maxNumDay ) {

		Specification<RiferimentoContabile> spec = new Specification<RiferimentoContabile> () {

			@Override
			public Predicate toPredicate ( Root<RiferimentoContabile> root, CriteriaQuery<?> query, CriteriaBuilder cb ) {
				Join<RiferimentoContabile, CodiceVersamento> codiceVersamento = root.join ( RiferimentoContabile_.codiceVersamento );

				Date now = new Date ();
				Date dataMax = DateUtils.addDays ( now, maxNumDay.intValue () );

				return  cb.and (
					cb.or (
						cb.isFalse ( codiceVersamento.get ( CodiceVersamento_.flagAnnullato ) ),
						cb.isNull ( codiceVersamento.get ( CodiceVersamento_.flagAnnullato ) ) ),
					cb.or (
						cb.isFalse ( root.get ( RiferimentoContabile_.flagAnnullato ) ),
						cb.isNull ( root.get ( RiferimentoContabile_.flagAnnullato ) ) ),
					cb.lessThan ( root.get ( RiferimentoContabile_.dataInizioValidita ), now ),
					cb.between ( root.get ( RiferimentoContabile_.dataFineValidita ), now, dataMax ),
					cb.equal (root.get ( RiferimentoContabile_.codiceVersamento ).get ( CodiceVersamento_.ente ).get ( Ente_.id ), idEnte  )

								);
			}
		};
		return baseRepository.findAll ( spec, new Sort ( "codiceVersamento.codice", "id" ) );
	}

	@Override
	public long findRiferimentoDuplicato ( Long idCodiceVersamento, Date dataInizioValidita, Date dataFineValidita, String 
		codTipologiaDatoSpecificoRiscossione, String datoSpecificoRiscossione, 
		Integer annoEsercizio, Integer numeroAccertamento, Integer annoAccertamento) {

		Specification<RiferimentoContabile> spec = new Specification<RiferimentoContabile> () {

			@Override
			public Predicate toPredicate ( Root<RiferimentoContabile> root, CriteriaQuery<?> query, CriteriaBuilder cb ) {
				//                Join<RiferimentoContabile, CodiceVersamento> codiceVersamento = root.join ( RiferimentoContabile_.codiceVersamento );



				return  cb.and (
					cb.or (
						cb.isFalse ( root.get ( RiferimentoContabile_.flagAnnullato ) ),
						cb.isNull ( root.get ( RiferimentoContabile_.flagAnnullato ) ) 
									),
					//
					//    					 cb.or (
					//   							
					//    									cb.between( root.get ( RiferimentoContabile_.dataInizioValidita), dataInizioValidita,dataFineValidita),
					//    									cb.between( root.get ( RiferimentoContabile_.dataFineValidita), dataInizioValidita,dataFineValidita),
					//    									cb.and (
					//    											cb.lessThan( root.get ( RiferimentoContabile_.dataInizioValidita), dataInizioValidita ),
					//    											cb.greaterThan( root.get ( RiferimentoContabile_.dataFineValidita), dataFineValidita )
					//    											)
					//    							),

					cb.or(

						cb.between( root.get ( RiferimentoContabile_.dataInizioValidita), dataInizioValidita,dataFineValidita),
						cb.between( root.get ( RiferimentoContabile_.dataFineValidita), dataInizioValidita,dataFineValidita),
						cb.and (
							cb.lessThanOrEqualTo( root.get ( RiferimentoContabile_.dataInizioValidita), dataInizioValidita ),
							cb.greaterThanOrEqualTo( root.get ( RiferimentoContabile_.dataFineValidita), dataFineValidita )
										)
									),

					//    					cb.equal((root.get ( RiferimentoContabile_.tipologiaDatoSpecificoRiscossione).get(TipologiaDatoSpecificoRiscossione_.codice) ),codTipologiaDatoSpecificoRiscossione ),
					//    					cb.equal((root.get ( RiferimentoContabile_.tassonomia).get(tas.codice) ),codTipologiaDatoSpecificoRiscossione ),
					cb.equal(root.get ( RiferimentoContabile_.codiceTipologiaDatoSpecificoRiscossione) ,codTipologiaDatoSpecificoRiscossione ),
					cb.like(root.get ( RiferimentoContabile_.datoSpecificoRiscossione),datoSpecificoRiscossione+"%" ),
//					cb.equal(cb.substring(root.get ( RiferimentoContabile_.datoSpecificoRiscossione), 1,10 ),datoSpecificoRiscossione ),
					
					cb.equal(root.get ( RiferimentoContabile_.annoEsercizio),annoEsercizio ),
					cb.equal(root.get ( RiferimentoContabile_.annoAccertamento),annoAccertamento ),
					cb.equal(root.get ( RiferimentoContabile_.numeroAccertamento),numeroAccertamento ),

					cb.equal (root.get ( RiferimentoContabile_.codiceVersamento ).get ( CodiceVersamento_.id ),idCodiceVersamento )

								);
			}
		};
		return baseRepository.count ( spec );
	}



@Override
    public long findRiferimentoContabileDuplicatoPerCodiceVersamento ( Long idCodiceVersamento, Integer annoEsercizio, Date dataInizioValidita, Date dataFineValidita) {

    	Specification<RiferimentoContabile> spec = new Specification<RiferimentoContabile> () {

    		@Override
    		public Predicate toPredicate ( Root<RiferimentoContabile> root, CriteriaQuery<?> query, CriteriaBuilder cb ) {
    			//                Join<RiferimentoContabile, CodiceVersamento> codiceVersamento = root.join ( RiferimentoContabile_.codiceVersamento );

              Date now = new Date();

    			return  cb.and (
    					cb.or (
    							cb.isFalse ( root.get ( RiferimentoContabile_.flagAnnullato ) ),
    							cb.isNull ( root.get ( RiferimentoContabile_.flagAnnullato ) ) 
    							),
    					cb.or (
    	                      cb.between( root.get ( RiferimentoContabile_.dataInizioValidita), dataInizioValidita,dataFineValidita),
    	                      cb.between( root.get ( RiferimentoContabile_.dataFineValidita), dataInizioValidita,dataFineValidita),
    	                      cb.and (
    	                          cb.lessThanOrEqualTo( root.get ( RiferimentoContabile_.dataInizioValidita), dataInizioValidita ),
    	                          cb.greaterThanOrEqualTo( root.get ( RiferimentoContabile_.dataFineValidita), dataFineValidita )
    	                                      )
    	                      ),
    	                                  
//    					cb.equal(root.get ( RiferimentoContabile_.annoEsercizio),annoEsercizio ),

    					cb.equal (root.get ( RiferimentoContabile_.codiceVersamento ).get ( CodiceVersamento_.id ),idCodiceVersamento )

    					);
    		}
    	};
    	return baseRepository.count ( spec );
    }

	@Override
	public List<RiferimentoContabile> ricercaRiferimentiContabiliSecondariPerCov(Long idCov) {

    	Specification<RiferimentoContabile> spec = new Specification<RiferimentoContabile> () {

    		@Override
    		public Predicate toPredicate ( Root<RiferimentoContabile> root, CriteriaQuery<?> query, CriteriaBuilder cb ) {
    			//                Join<RiferimentoContabile, CodiceVersamento> codiceVersamento = root.join ( RiferimentoContabile_.codiceVersamento );

              Date now = new Date();

              Predicate p= cb.and (
  					cb.or (
							cb.isFalse ( root.get ( RiferimentoContabile_.flagAnnullato ) ),
							cb.isNull ( root.get ( RiferimentoContabile_.flagAnnullato ) ) 
							),
					cb.lessThanOrEqualTo( root.get ( RiferimentoContabile_.dataInizioValidita), now ),
					cb.or (
							 cb.greaterThanOrEqualTo( root.get ( RiferimentoContabile_.dataFineValidita), now ),
							cb.isNull ( root.get ( RiferimentoContabile_.dataFineValidita ) ) 
							),
					cb.isTrue(root.get ( RiferimentoContabile_.flagMbSecondario)),

					cb.equal (root.get ( RiferimentoContabile_.codiceVersamento ).get ( CodiceVersamento_.id ),idCov )

					);
              
              List<Order> orderBys = new ArrayList<Order>() ;
             
              orderBys.add(cb.asc( root.get ( RiferimentoContabile_.dataInizioValidita)));
              query.orderBy(orderBys) ;
    			return  p;
    		}
    	};
    	
    	
    	return baseRepository.findAll ( spec );
    };
    
    @Override
	public List<RiferimentoContabile> ricercaRiferimentiContabiliPrimariOSecondariPerCov(Long idCov) {

    	Specification<RiferimentoContabile> spec = new Specification<RiferimentoContabile> () {

    		@Override
    		public Predicate toPredicate ( Root<RiferimentoContabile> root, CriteriaQuery<?> query, CriteriaBuilder cb ) {
    			//                Join<RiferimentoContabile, CodiceVersamento> codiceVersamento = root.join ( RiferimentoContabile_.codiceVersamento );


              Predicate p= cb.and (
  					cb.or (
							cb.isTrue ( root.get ( RiferimentoContabile_.flagMbPrimario ) ),
							cb.isTrue ( root.get ( RiferimentoContabile_.flagMbSecondario ) ) 
							),
					cb.equal (root.get ( RiferimentoContabile_.codiceVersamento ).get ( CodiceVersamento_.id ),idCov )

					);
              
    			return  p;
    		}
    	};
    	
    	
    	return baseRepository.findAll ( spec );
    };
    
    
    
    






	@Override
	public List<RiferimentoContabile>  ricercaDatoSpecificoRiscossione (  Long idCodiceVersamento, Long idEnte,Integer numeroAccertamento, 
		Integer annoAccertamento, Integer annoEsercizio  ) {

		List<Specification<RiferimentoContabile>> filters = new ArrayList<> ();

		filters.add ( new Specification<RiferimentoContabile> () {

			@Override
			public Predicate toPredicate ( Root<RiferimentoContabile> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
				return builder.or (
					builder.isNull ( root.get ( RiferimentoContabile_.flagAnnullato ) ),
					builder.isFalse ( root.get ( RiferimentoContabile_.flagAnnullato ) )
								);
			}
		} );

		filters.add ( new Specification<RiferimentoContabile> () {

			@Override
			public Predicate toPredicate ( Root<RiferimentoContabile> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
				return builder.equal (
					root.get ( RiferimentoContabile_.codiceVersamento ).get ( CodiceVersamento_.ente ).get ( Ente_.id ), idEnte );
			}
		} );

		if ( idCodiceVersamento != null ) {
			filters.add ( new Specification<RiferimentoContabile> () {

				@Override
				public Predicate toPredicate ( Root<RiferimentoContabile> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
					return builder.equal (
						root.get ( RiferimentoContabile_.codiceVersamento ).get ( CodiceVersamento_.id ), idCodiceVersamento );
				}
			} );
		}

		if ( numeroAccertamento != null ) {
			filters.add ( new Specification<RiferimentoContabile> () {

				@Override
				public Predicate toPredicate ( Root<RiferimentoContabile> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
					return builder.equal (
						root.get ( RiferimentoContabile_.numeroAccertamento ), numeroAccertamento );
				}
			} );
		}

		if ( annoAccertamento != null ) {
			filters.add ( new Specification<RiferimentoContabile> () {

				@Override
				public Predicate toPredicate ( Root<RiferimentoContabile> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
					return builder.equal (
						root.get ( RiferimentoContabile_.annoAccertamento ), annoAccertamento );
				}
			} );
		}

		if ( annoEsercizio != null ) { // hotfix_annoEsercizio, erroneamente prima era: if ( annoAccertamento != null ) {
			filters.add ( new Specification<RiferimentoContabile> () {

				@Override
				public Predicate toPredicate ( Root<RiferimentoContabile> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
					return builder.equal (
						root.get ( RiferimentoContabile_.annoEsercizio ), annoEsercizio );
				}
			} );
		}


		List<RiferimentoContabile> records = baseRepository.findAll ( CriteriaBuilderUtil.groupAnd ( filters ));
		//RiferimentoContabile records = baseRepository.findOne( CriteriaBuilderUtil.groupAnd ( filters ) );
		return records;
	}

	@Override
	public List<RiferimentoContabile> ricercaDatiSpecificiRiscossioneEmptyAcc ( Long idCodiceVersamento, Long idEnte, Integer annoEsercizio ) {
		List<Specification<RiferimentoContabile>> filters = new ArrayList<> ();

		filters.add ( new Specification<RiferimentoContabile> () {

			@Override
			public Predicate toPredicate ( Root<RiferimentoContabile> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
				return builder.or (
					builder.isNull ( root.get ( RiferimentoContabile_.flagAnnullato ) ),
					builder.isFalse ( root.get ( RiferimentoContabile_.flagAnnullato ) ) );
			}
		} );

		filters.add ( new Specification<RiferimentoContabile> () {
			@Override
			public Predicate toPredicate ( Root<RiferimentoContabile> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
				return builder.equal (
					root.get ( RiferimentoContabile_.codiceVersamento ).get ( CodiceVersamento_.ente ).get ( Ente_.id ), idEnte );
			}
		} );

		if ( idCodiceVersamento != null ) {
			filters.add ( new Specification<RiferimentoContabile> () {
				@Override
				public Predicate toPredicate ( Root<RiferimentoContabile> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
					return builder.equal (
						root.get ( RiferimentoContabile_.codiceVersamento ).get ( CodiceVersamento_.id ), idCodiceVersamento );
				}
			} );
		}


		filters.add ( new Specification<RiferimentoContabile> () {
			@Override
			public Predicate toPredicate ( Root<RiferimentoContabile> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
				return builder.isNull (
					root.get ( RiferimentoContabile_.numeroAccertamento ) );
			}
		} );


		filters.add ( new Specification<RiferimentoContabile> () {
			@Override
			public Predicate toPredicate ( Root<RiferimentoContabile> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
				return builder.isNull (
					root.get ( RiferimentoContabile_.annoAccertamento ) );
			}
		} );


		if ( annoEsercizio != null ) {
			filters.add ( new Specification<RiferimentoContabile> () {
				@Override
				public Predicate toPredicate ( Root<RiferimentoContabile> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
					return builder.equal (
						root.get ( RiferimentoContabile_.annoEsercizio ), annoEsercizio );
				}
			} );
		}

		List<RiferimentoContabile> records = baseRepository.findAll ( CriteriaBuilderUtil.groupAnd ( filters ) );
		return records;
	}

	@Override
	public List<RiferimentoContabile> ricercaDatiSpecificiRiscossioneAnyAcc ( Long idCodiceVersamento, Long idEnte, Integer annoEsercizio ) {
		List<Specification<RiferimentoContabile>> filters = new ArrayList<> ();

		filters.add ( new Specification<RiferimentoContabile> () {

			@Override
			public Predicate toPredicate ( Root<RiferimentoContabile> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
				return builder.or (
					builder.isNull ( root.get ( RiferimentoContabile_.flagAnnullato ) ),
					builder.isFalse ( root.get ( RiferimentoContabile_.flagAnnullato ) ) );
			}
		} );

		filters.add ( new Specification<RiferimentoContabile> () {

			@Override
			public Predicate toPredicate ( Root<RiferimentoContabile> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
				return builder.equal (
					root.get ( RiferimentoContabile_.codiceVersamento ).get ( CodiceVersamento_.ente ).get ( Ente_.id ), idEnte );
			}
		} );

		if ( idCodiceVersamento != null ) {
			filters.add ( new Specification<RiferimentoContabile> () {

				@Override
				public Predicate toPredicate ( Root<RiferimentoContabile> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
					return builder.equal (
						root.get ( RiferimentoContabile_.codiceVersamento ).get ( CodiceVersamento_.id ), idCodiceVersamento );
				}
			} );
		}

		filters.add ( new Specification<RiferimentoContabile> () {

			@Override
			public Predicate toPredicate ( Root<RiferimentoContabile> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
				return builder.notEqual (
					root.get ( RiferimentoContabile_.numeroAccertamento ), "" );
			}
		} );

		filters.add ( new Specification<RiferimentoContabile> () {

			@Override
			public Predicate toPredicate ( Root<RiferimentoContabile> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
				return builder.notEqual (
					root.get ( RiferimentoContabile_.annoAccertamento ), "" );
			}
		} );

		if ( annoEsercizio != null ) {
			filters.add ( new Specification<RiferimentoContabile> () {

				@Override
				public Predicate toPredicate ( Root<RiferimentoContabile> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
					return builder.equal (
						root.get ( RiferimentoContabile_.annoEsercizio ), annoEsercizio );
				}
			} );
		}

		List<RiferimentoContabile> records = baseRepository.findAll ( CriteriaBuilderUtil.groupAnd ( filters ) );
		return records;
	}


    @Override
    public List<RiferimentoContabile> ricercaDatiSpecificiRiscossione ( Long idCodiceVersamento, Long idEnte, Integer annoEsercizio ) {
        
        List<Specification<RiferimentoContabile>> filters = new ArrayList<> ();
        
        Date now = new Date();

        filters.add ( new Specification<RiferimentoContabile> () {

            @Override
            public Predicate toPredicate ( Root<RiferimentoContabile> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
                return builder.or (
                    builder.isNull ( root.get ( RiferimentoContabile_.flagAnnullato ) ),
                    builder.isFalse ( root.get ( RiferimentoContabile_.flagAnnullato ) )
                                );
            }
        } );

        filters.add ( new Specification<RiferimentoContabile> () {

            @Override
            public Predicate toPredicate ( Root<RiferimentoContabile> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
                return builder.equal (
                    root.get ( RiferimentoContabile_.codiceVersamento ).get ( CodiceVersamento_.ente ).get ( Ente_.id ), idEnte );
            }
        } );

        if ( idCodiceVersamento != null ) {
            filters.add ( new Specification<RiferimentoContabile> () {

                @Override
                public Predicate toPredicate ( Root<RiferimentoContabile> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
                    return builder.equal (
                        root.get ( RiferimentoContabile_.codiceVersamento ).get ( CodiceVersamento_.id ), idCodiceVersamento );
                }
            } );
        }

       

        if ( annoEsercizio != null ) {
            filters.add ( new Specification<RiferimentoContabile> () {

                @Override
                public Predicate toPredicate ( Root<RiferimentoContabile> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
                    return builder.equal (
                        root.get ( RiferimentoContabile_.annoEsercizio ), annoEsercizio );
                }
            } );
        }
        

            filters.add ( new Specification<RiferimentoContabile> () {

                @Override
                public Predicate toPredicate ( Root<RiferimentoContabile> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
                    return builder.lessThanOrEqualTo (
                        root.get ( RiferimentoContabile_.dataInizioValidita), now );
                }
            } );
            
            filters.add ( new Specification<RiferimentoContabile> () {

                public Predicate toPredicate ( Root<RiferimentoContabile> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
                    return builder.or (
                        builder.greaterThanOrEqualTo ( root.get ( RiferimentoContabile_.dataFineValidita), now ),
                        builder.isNull ( root.get ( RiferimentoContabile_.dataFineValidita ) )
                                    );
                }
            } );
            


        List<RiferimentoContabile> records = baseRepository.findAll ( CriteriaBuilderUtil.groupAnd ( filters ));
        return records;
        
        
    }
    
    @Override
    public List<RiferimentoContabile> ricercaRiferimentiContabiliInVitaPerCov(Long idCov) {

        Specification<RiferimentoContabile> spec = new Specification<RiferimentoContabile> () {

            @Override
            public Predicate toPredicate ( Root<RiferimentoContabile> root, CriteriaQuery<?> query, CriteriaBuilder cb ) {
                //                Join<RiferimentoContabile, CodiceVersamento> codiceVersamento = root.join ( RiferimentoContabile_.codiceVersamento );

              Date now = new Date();

              Predicate p= cb.and (
                    cb.or (
                            cb.isFalse ( root.get ( RiferimentoContabile_.flagAnnullato ) ),
                            cb.isNull ( root.get ( RiferimentoContabile_.flagAnnullato ) ) 
                            ),
                    cb.lessThanOrEqualTo( root.get ( RiferimentoContabile_.dataInizioValidita), now ),
                    cb.or (
                             cb.greaterThanOrEqualTo( root.get ( RiferimentoContabile_.dataFineValidita), now ),
                            cb.isNull ( root.get ( RiferimentoContabile_.dataFineValidita ) ) 
                            ),

                    cb.equal (root.get ( RiferimentoContabile_.codiceVersamento ).get ( CodiceVersamento_.id ),idCov )

                    );
              
              List<Order> orderBys = new ArrayList<Order>() ;
             
              orderBys.add(cb.asc( root.get ( RiferimentoContabile_.dataInizioValidita)));
              query.orderBy(orderBys) ;
                return  p;
            }
        };
        
        
        return baseRepository.findAll ( spec );
    };
    
    @Override
    public List<RiferimentoContabile> ricercaRiferimentiContabiliSovrappostiPerCov( Long idCodiceVersamento, Date dataInizioValidita, Date dataFineValidita) {

        Specification<RiferimentoContabile> spec = new Specification<RiferimentoContabile> () {

            @Override
            public Predicate toPredicate ( Root<RiferimentoContabile> root, CriteriaQuery<?> query, CriteriaBuilder cb ) {
                //                Join<RiferimentoContabile, CodiceVersamento> codiceVersamento = root.join ( RiferimentoContabile_.codiceVersamento );

              Date now = new Date();

              Predicate p= cb.and (
                  cb.or (
                      cb.isFalse ( root.get ( RiferimentoContabile_.flagAnnullato ) ),
                      cb.isNull ( root.get ( RiferimentoContabile_.flagAnnullato ) ) 
                                  ),
                  cb.or(cb.or (
                      cb.between( root.get ( RiferimentoContabile_.dataInizioValidita), dataInizioValidita,dataFineValidita),
                      cb.between( root.get ( RiferimentoContabile_.dataFineValidita), dataInizioValidita,dataFineValidita)),
                      cb.and (
                          cb.lessThan( root.get ( RiferimentoContabile_.dataInizioValidita), dataInizioValidita ),
                          cb.greaterThan( root.get ( RiferimentoContabile_.dataFineValidita), dataFineValidita )
                                      )
                                  ),

                  cb.equal (root.get ( RiferimentoContabile_.codiceVersamento ).get ( CodiceVersamento_.id ),idCodiceVersamento )

                              );
              
              List<Order> orderBys = new ArrayList<Order>() ;
             
              orderBys.add(cb.asc( root.get ( RiferimentoContabile_.dataInizioValidita)));
              query.orderBy(orderBys) ;
                return  p;
            }
        };
        
        
        return baseRepository.findAll ( spec );
    };
    
}
