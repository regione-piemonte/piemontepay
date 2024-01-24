/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.business.service.impl;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.common.util.CollectionUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.business.service.ProfilazioneService;
import it.csi.epay.epaypacatalogsrv.business.util.EntityUtils;
import it.csi.epay.epaypacatalogsrv.business.util.SecurityUtils;
import it.csi.epay.epaypacatalogsrv.dto.CallerInputDto;
import it.csi.epay.epaypacatalogsrv.exception.BadRequestException;
import it.csi.epay.epaypacatalogsrv.exception.NotAuthorizedException;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteCdu;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteCduPK;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteCduPK_;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteCdu_;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteCodiceVersamento;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteEnte;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteEntePK;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteEntePK_;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteEnte_;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteTematica;
import it.csi.epay.epaypacatalogsrv.model.Cdu;
import it.csi.epay.epaypacatalogsrv.model.Cdu_;
import it.csi.epay.epaypacatalogsrv.model.CodiceVersamento;
import it.csi.epay.epaypacatalogsrv.model.Ente;
import it.csi.epay.epaypacatalogsrv.model.Ente_;
import it.csi.epay.epaypacatalogsrv.model.Fruitore;
import it.csi.epay.epaypacatalogsrv.model.TematicaPpay;
import it.csi.epay.epaypacatalogsrv.model.Utente;
import it.csi.epay.epaypacatalogsrv.model.Utente_;
import it.csi.epay.epaypacatalogsrv.model.VisibilitaCodiceVersamento;
import it.csi.epay.epaypacatalogsrv.model.VisibilitaTematica;
import it.csi.epay.epaypacatalogsrv.repository.AssociazioneUtenteCduRepository;
import it.csi.epay.epaypacatalogsrv.repository.AssociazioneUtenteCodiceVersamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.AssociazioneUtenteEnteRepository;
import it.csi.epay.epaypacatalogsrv.repository.AssociazioneUtenteTematicaRepository;
import it.csi.epay.epaypacatalogsrv.repository.CduRepository;
import it.csi.epay.epaypacatalogsrv.repository.CodiceVersamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.EnteRepository;
import it.csi.epay.epaypacatalogsrv.repository.FruitoreRepository;
import it.csi.epay.epaypacatalogsrv.repository.TematicaPpayRepository;
import it.csi.epay.epaypacatalogsrv.repository.UtenteRepository;
import it.csi.epay.epaypacatalogsrv.repository.VisibilitaCodiceVersamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.VisibilitaTematicaRepository;
import it.csi.epay.epaypacatalogsrv.vo.Constants;
import it.csi.epay.epaypacatalogsrv.vo.security.PrincipalCduVO;
import it.csi.epay.epaypacatalogsrv.vo.security.PrincipalCodiceVersamentoVO;
import it.csi.epay.epaypacatalogsrv.vo.security.PrincipalEnteVO;
import it.csi.epay.epaypacatalogsrv.vo.security.PrincipalFruitoreVO;
import it.csi.epay.epaypacatalogsrv.vo.security.PrincipalProfiloVO;
import it.csi.epay.epaypacatalogsrv.vo.security.PrincipalRuoloVO;
import it.csi.epay.epaypacatalogsrv.vo.security.PrincipalScopeVO;
import it.csi.epay.epaypacatalogsrv.vo.security.PrincipalTematicaVO;
import it.csi.epay.epaypacatalogsrv.vo.security.PrincipalUtenteVO;
import it.csi.epay.epaypacatalogsrv.vo.security.PrincipalVO;


@Component
@Transactional
public class ProfilazioneServiceImpl implements ProfilazioneService {

	private final Logger logger = LogManager.getLogger ( this.getClass () );

	@Autowired
	private UtenteRepository utenteRepository;

	@Autowired
	private CduRepository cduRepository;

	@Autowired
	private FruitoreRepository fruitoreRepository;

	@Autowired
	private TematicaPpayRepository tematicaRepository;

	@Autowired
	private AssociazioneUtenteCduRepository associazioneUtenteCduRepository;

	@Autowired
	private AssociazioneUtenteTematicaRepository associazioneUtenteTematicaRepository;

	@Autowired
	private AssociazioneUtenteCodiceVersamentoRepository associazioneUtenteCodiceVersamentoRepository;

	@Autowired
	private CodiceVersamentoRepository codiceVersamentoRepository;

	@Autowired
	private EnteRepository enteRepository;

	@Autowired
	private AssociazioneUtenteEnteRepository associazioneUtenteEnteRepository;

	@Autowired
	private VisibilitaCodiceVersamentoRepository visibilitaCodiceVersamentoRepository;

	@Autowired
	private VisibilitaTematicaRepository visibilitaTematicaRepository;

	@Override
	public List<String> getCodiciTematicheConVisibilitaTotale ( PrincipalVO principal ) {
		if ( principal == null || principal.getTematiche () == null ) {
			throw new InvalidParameterException ();
		}

		List<String> output = new LinkedList<> ();

		for ( PrincipalTematicaVO tematica: principal.getTematiche () ) {
			if ( EntityUtils.isTrue ( tematica.getFlagVisibilitaTotale () ) ) {
				output.add ( tematica.getCodice () );
			}
		}

		return output;
	}

	@Override
	public List<String> getCodiciTematicheConVisibilitaParziale ( PrincipalVO principal ) {
		if ( principal == null || principal.getTematiche () == null ) {
			throw new InvalidParameterException ();
		}

		List<String> output = new LinkedList<> ();

		for ( PrincipalTematicaVO tematica: principal.getTematiche () ) {
			if ( !EntityUtils.isTrue ( tematica.getFlagVisibilitaTotale () ) ) {
				output.add ( tematica.getCodice () );
			}
		}

		return output;
	}

	@Override
	public List<Long> getIdCodiciVersamentoConVisibilita ( PrincipalVO principal ) {
		if ( principal == null || principal.getTematiche () == null ) {
			throw new InvalidParameterException ();
		}

		List<Long> output = new LinkedList<> ();

		for ( PrincipalTematicaVO tematica: principal.getTematiche () ) {
			if ( !EntityUtils.isTrue ( tematica.getFlagVisibilitaTotale () ) && tematica.getCodiciVersamento () != null ) {
				for ( PrincipalCodiceVersamentoVO codiceVersamento: tematica.getCodiciVersamento () ) {
					output.add ( codiceVersamento.getId () );
				}
			}
		}

		return output;
	}

	/**
	 * metodo wrapper per dispatchare l'implementazione al vecchio o al nuovo algoritmo
	 */
	@Override
	public PrincipalVO getPrincipal ( CallerInputDto caller ) {
		return getPrincipalV2(caller);
	}

	@Deprecated
	public PrincipalVO getPrincipalLegacy ( CallerInputDto caller ) {
		if ( logger.isDebugEnabled () ) {
			logger.debug ( "calcolo del principal dal caller: " + caller );
		}

		PrincipalVO principal;

		try {
			logger.debug ( "verifico input per profilazione");
			validaInputMinimo ( caller );

			logger.debug ( "carico le entities del principal da db");
			PrincipalDatabaseBundle databaseBundle = loadFromDatabase ( caller );

			logger.debug ( "valido i dati caricati da db");
			validaCoerenza ( databaseBundle );

			logger.debug ( "carico il principal v1");
			principal = buildPrincipal ( databaseBundle, caller );

			logger.debug ( "calcolo dei CDU virtuali");
			calcolaCDUVirtuali ( principal );

			//EPAY-80 check validita utente (date trasversali e current)
			if ( null != principal && !principal.getUtente ().isValid () ) {
				throw new NotAuthorizedException ( Constants.MESSAGES.UTENTE_NOT_VALID );
			}

			logger.debug ( "finished building principal");

		} catch ( Exception e ) {
			logger.error ( "ERROR GETTING PRINCIPAL", e );
			throw e;
		}

		return principal;
	}

	private void calcolaCDUVirtuali ( PrincipalVO principal ) {

		//EPAY-80
		if ( null != principal.getEnte () && null != principal.getUtente () ) {
			AssociazioneUtenteEntePK rUtenteEnte = new AssociazioneUtenteEntePK ();
			rUtenteEnte.setIdEnte ( principal.getEnte ().getId ().intValue () );
			rUtenteEnte.setIdUtente ( principal.getUtente ().getId () );
			AssociazioneUtenteEnte assUE = associazioneUtenteEnteRepository.findOne ( rUtenteEnte );
			if ( null != assUE && assUE.getFlagAdmin () ) {
				if ( principal.getUtente ().getCdu () == null ) {
					principal.getUtente ().setCdu ( new LinkedList<> () );
				}

				for ( String cduVirtuale: Constants.USE_CASES.CDU_VIRTUALI_AMMINISTRATORE_ENTE ) {
					principal.getUtente ().getCdu ().add ( new PrincipalCduVO (
						0L,
						"VIRTUALI", "Casi d'uso virtuali",
						cduVirtuale, "Caso d'uso virtuale: " + cduVirtuale ) );
				}
			}
		}

		//EPAY-80
		//        if ( principal.getEnte () != null && principal.getEnte ().getIdUtenteAmministratore () != null && principal.getUtente () != null ) {
		//            if ( principal.getEnte ().getIdUtenteAmministratore ().equals ( principal.getUtente ().getId () ) ) {
		//                if ( principal.getUtente ().getCdu () == null ) {
		//                    principal.getUtente ().setCdu ( new LinkedList<> () );
		//                }
		//
		//                // aggiungo CDU derivati
		//                for ( String cduVirtuale: Constants.USE_CASES.CDU_VIRTUALI_AMMINISTRATORE_ENTE ) {
		//                    principal.getUtente ().getCdu ().add ( new PrincipalCduVO (
		//                        0L,
		//                        "VIRTUALI", "Casi d'uso virtuali",
		//                        cduVirtuale, "Caso d'uso virtuale: " + cduVirtuale ) );
		//                }
		//
		//            }
		//        }

	}

	@Deprecated
	private PrincipalVO buildPrincipal ( PrincipalDatabaseBundle databaseBundle, CallerInputDto caller ) {

		PrincipalVO principal = new PrincipalVO ();

		principal.setFruitore ( buildFruitore ( databaseBundle.dtoFruitore ) );
		principal.setUtente ( buildUtente ( databaseBundle.dtoUtente, databaseBundle.dtoEnte, databaseBundle.dataInizioValiditaAssociazioneEnteUtente,
			databaseBundle.dataFineValiditaAssociazioneEnteUtente ) );
		principal.setEnte ( buildEnte ( databaseBundle.dtoUtente, databaseBundle.dtoEnte ) );
		// principal.setTematiche ( getTematicheAssociate ( databaseBundle.dtoUtente, databaseBundle.dtoEnte ) );
		principal.setTematicheLoader ( new Supplier<List<PrincipalTematicaVO>>() {

			@Override
			public List<PrincipalTematicaVO> get () {
				return getTematicheAssociate ( databaseBundle.dtoUtente, databaseBundle.dtoEnte );
			}

		});
		principal.setRuolo ( buildRuoloMock () );
		principal.setProfilo ( buildProfiloMock () );
		principal.setIpOrigine ( caller.getIp () );
		return principal;
	}

	@Deprecated
	@Override
	public List<PrincipalTematicaVO> getTematicheAssociate ( Utente dtoUtente, Ente dtoEnte ) {

		List<AssociazioneUtenteTematica> associazioniTematiche = null;

		boolean isAdmin = false;
		if(dtoEnte != null && dtoUtente != null) {
			AssociazioneUtenteEntePK pk = new AssociazioneUtenteEntePK ();
			pk.setIdEnte ( dtoEnte.getId ().intValue ());
			pk.setIdUtente ( dtoUtente.getId () );

			AssociazioneUtenteEnte admin = associazioneUtenteEnteRepository.findOne ( pk  );
			if(admin != null) isAdmin = admin.getFlagAdmin ();
		}

		// amministratore: carico tutte le tematiche disponibili
		if ( dtoEnte != null && dtoUtente != null && isAdmin) {
			List<TematicaPpay> allTematiche =  tematicaRepository.findAll();
			if (allTematiche!=null) {
				associazioniTematiche =  new LinkedList<> ();
				for (TematicaPpay tematica : allTematiche) {
					AssociazioneUtenteTematica utenteTematica =  new AssociazioneUtenteTematica();
					utenteTematica.setTematica(tematica);
					utenteTematica.setFlagVisibilitaTotale(true);
					associazioniTematiche.add(utenteTematica);
				}
			}

		}
		else {
			associazioniTematiche  = associazioneUtenteTematicaRepository.findByIdUtenteAndIdEnte ( dtoUtente.getId (), dtoEnte.getId ().intValue () );
		}

		List<PrincipalTematicaVO> tematiche = new LinkedList<> ();
		//        associazioniTematiche  = associazioneUtenteTematicaRepository.findByIdUtenteAndIdEnte ( dtoUtente.getId (), dtoEnte.getId ().intValue () );
		List<AssociazioneUtenteCodiceVersamento> associazioniCodiciVersamento
		= associazioneUtenteCodiceVersamentoRepository.findByIdUtenteAndIdEnte ( dtoUtente.getId (), dtoEnte.getId ().intValue () );

		if ( associazioniTematiche != null ) {

			if ( associazioniTematiche.size () > 0 ) {
				for ( AssociazioneUtenteTematica associazioneTematica: associazioniTematiche ) {
					PrincipalTematicaVO dtoTematica = new PrincipalTematicaVO ();
					dtoTematica.setCodice ( associazioneTematica.getTematica ().getCodice () );
					dtoTematica.setDescrizione ( associazioneTematica.getTematica ().getDescrizione () );
					dtoTematica.setId ( associazioneTematica.getTematica ().getId ().longValue () );
					dtoTematica.setFlagVisibilitaTotale ( associazioneTematica.getFlagVisibilitaTotale () );
					dtoTematica.setCodiciVersamento ( new LinkedList<> () );

					if ( !EntityUtils.isTrue ( associazioneTematica.getFlagVisibilitaTotale () ) ) {

						if ( associazioniCodiciVersamento.size () > 0 ) {
							for ( AssociazioneUtenteCodiceVersamento associazioneCodiceVersamento: associazioniCodiciVersamento ) {
								if ( !associazioneCodiceVersamento.getCodiceVersamento ().getVoceEntrata ().getTematica ().getCodice ()
												.equals ( associazioneTematica.getTematica ().getCodice () ) ) {
									continue;
								}
								PrincipalCodiceVersamentoVO dtoCodiceVersamento = new PrincipalCodiceVersamentoVO ();
								dtoCodiceVersamento.setCodiceTematica ( dtoTematica.getCodice () );
								dtoCodiceVersamento.setCodice ( associazioneCodiceVersamento.getCodiceVersamento ().getCodice () );
								dtoCodiceVersamento.setDescrizione ( associazioneCodiceVersamento.getCodiceVersamento ().getDescrizione () );
								dtoCodiceVersamento.setId ( associazioneCodiceVersamento.getCodiceVersamento ().getId ().longValue () );
								dtoTematica.getCodiciVersamento ().add ( dtoCodiceVersamento );
							}
						}

					} else {

						// carico tutti i codici associati alla tematica

						List<CodiceVersamento> codiciPerTematica = codiceVersamentoRepository.findByCodiceTematicaAndIdEnte (
							dtoEnte.getId (), associazioneTematica.getTematica ().getCodice () );
						if ( null != codiciPerTematica ) {
							for ( CodiceVersamento codicePerTematica: codiciPerTematica ) {
								// Inserito filtro lato query
								//                                if ( codicePerTematica.getFlagAnnullato () != null && codicePerTematica.getFlagAnnullato () ) {
								//                                    continue;
								//                                }
								PrincipalCodiceVersamentoVO dtoCodiceVersamento = new PrincipalCodiceVersamentoVO ();
								dtoCodiceVersamento.setCodiceTematica ( associazioneTematica.getTematica ().getCodice () );
								dtoCodiceVersamento.setCodice ( codicePerTematica.getCodice () );
								dtoCodiceVersamento.setDescrizione ( codicePerTematica.getDescrizione () );
								dtoCodiceVersamento.setId ( codicePerTematica.getId () );
								dtoTematica.getCodiciVersamento ().add ( dtoCodiceVersamento );

								//ENG
								if ( null != codicePerTematica.getCodiciVersamentoCollegati () ) {
									for ( CodiceVersamento codVers: codicePerTematica.getCodiciVersamentoCollegati () ) {
										if ( codVers.getFlagAnnullato () != null && codVers.getFlagAnnullato () ) {
											continue;
										}
										PrincipalCodiceVersamentoVO dtoCodiceVersamentoChild = new PrincipalCodiceVersamentoVO ();
										dtoCodiceVersamentoChild.setCodiceTematica ( associazioneTematica.getTematica ().getCodice () );
										dtoCodiceVersamentoChild.setCodice ( codVers.getCodice () );
										dtoCodiceVersamentoChild.setDescrizione ( codVers.getDescrizione () );
										dtoCodiceVersamentoChild.setId ( codVers.getId () );
										dtoTematica.getCodiciVersamento ().add ( dtoCodiceVersamentoChild );
									}
								}
							}
						}
					}

					tematiche.add ( dtoTematica );
				}
			}

		}

		return tematiche;
	}

	@Deprecated
	private PrincipalUtenteVO buildUtente ( Utente dtoUtente, Ente dtoEnte, Date dataInizioValiditaAssociazioneEnteUtente,
		Date dataFineValiditaAssociazioneEnteUtente ) {
		PrincipalUtenteVO utente = new PrincipalUtenteVO ();
		utente.setCodiceFiscale ( dtoUtente.getCodiceFiscale () );
		utente.setCognome ( dtoUtente.getCognome () );
		utente.setId ( dtoUtente.getId () );
		utente.setNome ( dtoUtente.getNome () );
		utente.setDataInizioValidita ( dtoUtente.getDataInizioValidita () );
		utente.setDataFineValidita ( dtoUtente.getDataFineValidita () );

		utente.setDataInizioValiditaCurrent ( dataInizioValiditaAssociazioneEnteUtente );
		utente.setDataFineValiditaCurrent ( dataFineValiditaAssociazioneEnteUtente );
		caricaCdu ( utente, dtoEnte );
		return utente;
	}

	@Deprecated
	private void caricaCdu ( PrincipalUtenteVO utente, Ente dtoEnte ) {

		List<PrincipalCduVO> cduUtente = new LinkedList<> ();

		//EPAY-80
		//        if ( dtoEnte != null &&
		//                        dtoEnte.getUtenteAmministratore () != null &&
		//                        utente != null &&
		//                        dtoEnte.getUtenteAmministratore ().getId ().equals ( utente.getId () ) ) {
		if ( null != utente && null != dtoEnte ) {
			AssociazioneUtenteEntePK rUtenteEnte = new AssociazioneUtenteEntePK ();
			rUtenteEnte.setIdEnte ( dtoEnte.getId ().intValue () );
			rUtenteEnte.setIdUtente ( utente.getId () );
			AssociazioneUtenteEnte assUE = associazioneUtenteEnteRepository.findOne ( rUtenteEnte );
			if ( null != assUE && assUE.getFlagAdmin () ) {
				// amministratore: carico tutti i CDU disponibili
				List<Cdu> cdus = cduRepository.findAll ();
				for ( Cdu cdu: cdus ) {
					cduUtente.add ( new PrincipalCduVO (
						cdu.getId ().longValue (),
						cdu.getCategoria ().getCodice (),
						cdu.getCategoria ().getDescrizione (),
						cdu.getCodice (),
						cdu.getDescrizione () ) );
				}
			}
			else {

				// non amministratore: carico CDU da tabella associazioni
				List<AssociazioneUtenteCdu> associazioniCdu
				= associazioneUtenteCduRepository.findByIdUtenteAndIdEnte ( utente.getId (), dtoEnte.getId ().intValue () );

				if ( associazioniCdu.size () > 0 ) {
					for ( AssociazioneUtenteCdu associazioneCdu: associazioniCdu ) {
						Cdu o = associazioneCdu.getCdu ();
						PrincipalCduVO dtoCdu = new PrincipalCduVO ();
						dtoCdu.setCodice ( o.getCodice () );
						dtoCdu.setCodiceCategoria ( o.getCategoria () != null ? o.getCategoria ().getCodice () : null );
						dtoCdu.setDescrizioneCategoria ( o.getCategoria () != null ? o.getCategoria ().getDescrizione () : null );
						dtoCdu.setDescrizione ( o.getDescrizione () );
						dtoCdu.setId ( o.getId ().longValue () );
						cduUtente.add ( dtoCdu );
					}
				}

			}
		}
		utente.setCdu ( cduUtente );
	}

	private PrincipalProfiloVO buildProfiloMock () {
		PrincipalProfiloVO profilo = new PrincipalProfiloVO ();

		profilo.setId ( 1L );
		profilo.setCodice ( "DEFAULT" );
		profilo.setDescrizione ( "Default system profile" );

		return profilo;
	}

	private PrincipalFruitoreVO buildFruitore ( Fruitore dtoFruitore ) {
		PrincipalFruitoreVO fruitore = new PrincipalFruitoreVO ();

		fruitore.setId ( dtoFruitore.getId ().longValue () );
		fruitore.setCodice ( dtoFruitore.getCodice () );
		fruitore.setDescrizione ( dtoFruitore.getDescrizione () );
		fruitore.setDataInizioValidita ( dtoFruitore.getDataInizioValidita () );
		fruitore.setDataFineValidita ( dtoFruitore.getDataFineValidita () );
		fruitore.getScope ().addAll ( caricaScopePerCodiceFruitore ( dtoFruitore.getCodice () ) );

		return fruitore;
	}

	@Deprecated
	private PrincipalEnteVO buildEnte ( Utente utente, Ente ente ) {
		PrincipalEnteVO entePrincipal = new PrincipalEnteVO ();

		boolean isAdmin = false;
		if(utente != null && ente != null) {
			AssociazioneUtenteEntePK pk = new AssociazioneUtenteEntePK ();
			pk.setIdEnte ( ente.getId ().intValue ());
			pk.setIdUtente ( utente.getId ());
			AssociazioneUtenteEnte admin = associazioneUtenteEnteRepository.findOne ( pk  );
			if(admin != null) isAdmin = admin.getFlagAdmin ();
		}

		entePrincipal.setCodiceFiscale ( ente.getCodiceFiscale () );
		entePrincipal.setDenominazione ( ente.getDenominazione () );

		if(isAdmin) {
			entePrincipal.setDescrizioneUtenteAmministratore (utente.getCodiceFiscale ());

			List<AssociazioneUtenteEnte> admins = getAdmins(ente.getId ());

			entePrincipal.getAdmins().clear ();

			for(AssociazioneUtenteEnte admin:admins) {
				entePrincipal.getAdmins().add(admin.getId ().getIdUtente ());
			}
		}

		entePrincipal.setId ( ente.getId () );
		entePrincipal.setIndirizzo ( ente.getIndirizzo () );
		entePrincipal.setLocalita ( ente.getLocalita () );
		entePrincipal.setEmail ( ente.getEmail () );

		return entePrincipal;
	}

	@Deprecated
	private List<AssociazioneUtenteEnte> getAdmins(Long idEnte) {

		Ente ente = enteRepository.findOneById(idEnte);

		AssociazioneUtenteEntePK assoc = new AssociazioneUtenteEntePK ();
		assoc.setIdEnte ( ente.getId ().intValue () );

		List<AssociazioneUtenteEnte> admin = associazioneUtenteEnteRepository.findByIdEnteAndFlagAdmin ( idEnte.intValue (), true );

		return admin;
	}

	private PrincipalRuoloVO buildRuoloMock () {

		PrincipalRuoloVO ruolo = new PrincipalRuoloVO ();
		ruolo.setCodice ( "DEFAULT" );
		ruolo.setDescrizione ( "Default system role" );
		ruolo.setId ( 1L );
		ruolo.setCdu ( new LinkedList<> () );

		return ruolo;
	}

	private void validaInputMinimo ( CallerInputDto caller ) {

		if ( caller == null || caller.getPrincipal () == null ) {
			throw new BadRequestException ( Constants.MESSAGES.INVALID_CALLER );
		}

		if ( StringUtils.isBlank ( caller.getPrincipal ().getCodiceFiscale () ) ) {
			throw new BadRequestException ( Constants.MESSAGES.INVALID_CF );
		}

		if ( StringUtils.isBlank ( caller.getPrincipal ().getCodiceEnte () ) ) {
			throw new BadRequestException ( Constants.MESSAGES.INVALID_CODICE_ENTE );
		}

	}

	private void validaCoerenza ( PrincipalDatabaseBundle databaseBundle ) {

		if ( databaseBundle.dtoFruitore == null ) {
			throw new NotAuthorizedException ( Constants.MESSAGES.FRUITORE_NOT_FOUND );
		}

		if ( !EntityUtils.isValid ( databaseBundle.dtoFruitore ) ) {
			throw new NotAuthorizedException ( Constants.MESSAGES.FRUITORE_NOT_VALID );
		}

		if ( databaseBundle.dtoUtente == null ) {
			throw new NotAuthorizedException ( Constants.MESSAGES.USER_NOT_FOUND );
		}

		if ( databaseBundle.dtoEnte == null ) {
			throw new NotAuthorizedException ( Constants.MESSAGES.ENTE_NOT_FOUND );
		}

	}

	@Deprecated
	private PrincipalDatabaseBundle loadFromDatabase ( CallerInputDto caller ) {
		PrincipalDatabaseBundle output = new PrincipalDatabaseBundle ();
		output.dtoFruitore = fruitoreRepository.findOneByCodice ( caller.getCodiceApplicativo () );
		output.dtoUtente = utenteRepository.findOneCompletoByCodiceFiscale ( caller.getPrincipal ().getCodiceFiscale () );
		output.dtoEnte = enteRepository.findOne ( enteAssociatoDaCF ( caller.getPrincipal ().getCodiceEnte () ) );
		List<AssociazioneUtenteEnte> associazioniUtenteEnte
		= associazioneUtenteEnteRepository.findByIdUtenteAndIdEnte ( output.dtoUtente.getId (), output.dtoEnte.getId ().intValue () );
		if ( null != associazioniUtenteEnte ) {
			for ( AssociazioneUtenteEnte aue: associazioniUtenteEnte ) {
				output.dataInizioValiditaAssociazioneEnteUtente = aue.getDataInizioValidita ();
				output.dataFineValiditaAssociazioneEnteUtente = aue.getDataFineValidita ();
			}
		}
		return output;
	}

	private Specification<Ente> enteAssociatoDaCF(String cfEnte) {
		return new Specification<Ente>() {
			@Override
			public Predicate toPredicate(Root<Ente> root,
				CriteriaQuery<?> query,
				CriteriaBuilder cb) {
				Join<Ente, AssociazioneUtenteEnte> joinAssociazioneUtenteEnte = root.join ( Ente_.utenteEnteList, JoinType.LEFT );

				Predicate p = cb.and ( cb.equal ( joinAssociazioneUtenteEnte.get ( AssociazioneUtenteEnte_.ente ).get ( Ente_.codiceFiscale ), cfEnte ),
					cb.and (
						cb.or ( cb.greaterThan ( joinAssociazioneUtenteEnte.get ( AssociazioneUtenteEnte_.dataFineValidita ), Calendar.getInstance ().getTime () ),
							cb.isNull ( joinAssociazioneUtenteEnte.get ( AssociazioneUtenteEnte_.dataFineValidita ) ) ) ) );

				return query.where ( p ).getRestriction ();
			}
		};
	}

	private List<PrincipalScopeVO> caricaScopePerCodiceFruitore ( String codice ) {
		List<PrincipalScopeVO> output = new LinkedList<> ();

		if ( codice.equals ( Constants.FRUITORI.EPAYPACATALOGWEB ) ) {

			output.add ( new PrincipalScopeVO ( 1L, Constants.SCOPES.WEBAPP, "Client Web Application" ) );
			output.add ( new PrincipalScopeVO ( 2L, Constants.SCOPES.CONFIGURATORE, "Configuratore" ) );
			output.add ( new PrincipalScopeVO ( 3L, Constants.SCOPES.PROFILAZIONE_UTENTE_CORRENTE, "Profilazione utente corrente" ) );

		} else if ( codice.equals ( Constants.FRUITORI.EPAYMODRICWEB ) ) {
			output.add ( new PrincipalScopeVO ( 1L, Constants.SCOPES.WEBAPP, "Client Web Application" ) );
			output.add ( new PrincipalScopeVO ( 3L, Constants.SCOPES.PROFILAZIONE_UTENTE_CORRENTE, "Profilazione utente corrente" ) );

		} else if ( codice.equals ( Constants.FRUITORI.EPAYMODRIC ) ) {
			output.add ( new PrincipalScopeVO ( 10L, Constants.SCOPES.CLIENT_SERVICE, "Client Service" ) );
			output.add ( new PrincipalScopeVO ( 4L, Constants.SCOPES.PROFILAZIONE_UTENTE, "Profilazione utente" ) );

		} else if ( codice.equals ( Constants.FRUITORI.EPAYSIMWEB ) ) {
			output.add ( new PrincipalScopeVO ( 1L, Constants.SCOPES.WEBAPP, "Client Web Application" ) );
			output.add ( new PrincipalScopeVO ( 3L, Constants.SCOPES.PROFILAZIONE_UTENTE_CORRENTE, "Profilazione utente corrente" ) );

		} else if ( codice.equals ( Constants.FRUITORI.EPAYPA ) ) {
			output.add ( new PrincipalScopeVO ( 10L, Constants.SCOPES.CLIENT_SERVICE, "Client Service" ) );
			output.add ( new PrincipalScopeVO ( 4L, Constants.SCOPES.PROFILAZIONE_UTENTE, "Profilazione utente" ) );

		} else if ( codice.equals ( Constants.FRUITORI.INTERNAL ) ) {
			output.add ( new PrincipalScopeVO ( 100L, Constants.SCOPES.INTERNAL, "Internal caller" ) );

		} else if ( codice.equals ( Constants.FRUITORI.UNIT_TEST ) ) {
			output.add ( new PrincipalScopeVO ( 1L, Constants.SCOPES.WEBAPP, "Client Web Application" ) );
			output.add ( new PrincipalScopeVO ( 2L, Constants.SCOPES.CONFIGURATORE, "Configuratore" ) );
			output.add ( new PrincipalScopeVO ( 3L, Constants.SCOPES.PROFILAZIONE_UTENTE_CORRENTE, "Profilazione utente corrente" ) );
			output.add ( new PrincipalScopeVO ( 4L, Constants.SCOPES.PROFILAZIONE_UTENTE, "Profilazione utente" ) );

		}

		return output;
	}

	@Override
	public PrincipalVO createImpersonatedPrincipal ( String cf ) {
		PrincipalVO principal = new PrincipalVO ();

		principal.setEnte ( null );
		principal.setIpOrigine ( null );
		principal.setProfilo ( buildProfiloMock () );
		principal.setRuolo ( buildRuoloMock () );
		principal.setTematiche ( Collections.emptyList () );

		PrincipalFruitoreVO fruitore = new PrincipalFruitoreVO ();

		fruitore.setId ( 0L );
		fruitore.setCodice ( Constants.FRUITORI.INTERNAL );
		fruitore.setDescrizione ( "Internal caller" );
		fruitore.setDataInizioValidita ( EntityUtils.toDate ( LocalDate.of ( 2000, 1, 1 ) ) );
		fruitore.setDataFineValidita ( null );
		fruitore.getScope ().addAll ( caricaScopePerCodiceFruitore ( Constants.FRUITORI.INTERNAL ) );

		principal.setFruitore ( fruitore );

		PrincipalUtenteVO utente = new PrincipalUtenteVO ();

		utente.setId ( 0L );
		utente.setCodiceFiscale ( cf );
		utente.setNome ( Constants.FRUITORI.INTERNAL );
		utente.setCognome ( Constants.FRUITORI.INTERNAL );
		utente.setDataFineValidita ( null );
		utente.setDataInizioValidita ( fruitore.getDataInizioValidita () );

		utente.setCdu ( new LinkedList<> () );
		utente.getCdu ().add ( new PrincipalCduVO ( 0L, "INTERNAL", "INTERNAL CDUs",
			Constants.USE_CASES.IMPERSONATED, "Utente impersonato a livello di sistema" ) );

		principal.setUtente ( utente );

		return principal;
	}

	@Override
	public void impersonateIfAnonymous ( String cf ) {
		SecurityUtils.impersonateIfAnonymous ( createImpersonatedPrincipal ( cf ) );
	}

	@Override
	public void impersonate ( String cf ) {
		SecurityUtils.impersonate ( createImpersonatedPrincipal ( cf ) );
	}

	@Override
	public PrincipalVO getPrincipalV2 ( CallerInputDto caller ) {
		if ( logger.isDebugEnabled () ) {
			logger.debug ( "calcolo del principal (v2) dal caller: " + caller );
		}

		PrincipalVO principal;

		try {
			logger.debug ( "verifico input per profilazione");
			validaInputMinimo ( caller );

			logger.debug ( "carico le entities del principal da db");
			PrincipalDatabaseBundle databaseBundle = loadFromDatabaseV2 ( caller );

			logger.debug ( "valido i dati caricati da db");
			validaCoerenza ( databaseBundle );

			logger.debug ( "carico il principal v2");
			principal = buildPrincipalV2 ( databaseBundle, caller );

			//EPAY-80 check validita utente (date trasversali e current)
			if ( null != principal && !principal.getUtente ().isValid () ) {
				throw new NotAuthorizedException ( Constants.MESSAGES.UTENTE_NOT_VALID );
			}

		} catch ( Exception e ) {
			logger.error ( "ERROR GETTING PRINCIPAL (v2)", e );
			throw e;
		}

		logger.debug ( "finished building principal");
		return principal;
	}

	private PrincipalDatabaseBundle loadFromDatabaseV2 ( CallerInputDto caller ) {
		PrincipalDatabaseBundle output = new PrincipalDatabaseBundle ();

		output.dtoFruitore = fruitoreRepository.findOneByCodice ( caller.getCodiceApplicativo () );

		output.dtoUtente = utenteRepository.findOneCompletoByCodiceFiscale ( caller.getPrincipal ().getCodiceFiscale () );

		List<Ente> enti = enteRepository.findAll(new Specification<Ente>() {
			@Override
			public Predicate toPredicate(Root<Ente> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<Ente, AssociazioneUtenteEnte> joinAssociazioneUtenteEnte = root.join ( Ente_.utenteEnteList, JoinType.LEFT );
				root.fetch(Ente_.statoAggiornamentoEnte);
				root.fetch ( Ente_.periodicitaSchedulazioneRiconciliazione.getName (), JoinType.LEFT );
				root.fetch ( Ente_.modalitaAcquisizioneProvvisori.getName (), JoinType.LEFT );
				root.fetch ( Ente_.tipologiaAccertamento.getName (), JoinType.LEFT );

				Predicate p = cb.and ( 
					cb.equal ( joinAssociazioneUtenteEnte.get ( AssociazioneUtenteEnte_.ente ).get ( Ente_.codiceFiscale ), caller.getPrincipal ().getCodiceEnte () ),
					cb.and (
						cb.or ( 
							cb.greaterThan ( joinAssociazioneUtenteEnte.get ( AssociazioneUtenteEnte_.dataFineValidita ), Calendar.getInstance ().getTime () ),
							cb.isNull ( joinAssociazioneUtenteEnte.get ( AssociazioneUtenteEnte_.dataFineValidita ) ) ) 
									) 
								);

				return query.where ( p ).getRestriction ();
			}

		});
		output.dtoEnte = enti.isEmpty() ? null : enti.get(0);

		List<AssociazioneUtenteEnte> associazioniUtenteEnte
		= associazioneUtenteEnteRepository.findByIdUtenteAndIdEnte ( output.dtoUtente.getId (), output.dtoEnte.getId ().intValue () );

		if ( null != associazioniUtenteEnte ) {
			for ( AssociazioneUtenteEnte aue: associazioniUtenteEnte ) {
				output.dataInizioValiditaAssociazioneEnteUtente = aue.getDataInizioValidita ();
				output.dataFineValiditaAssociazioneEnteUtente = aue.getDataFineValidita ();
				output.associazioneUtenteEnte = aue;
				output.isAdmin = Boolean.TRUE.equals(aue.getFlagAdmin());
			}
		}

		return output;
	}

	private PrincipalVO buildPrincipalV2 ( PrincipalDatabaseBundle databaseBundle, CallerInputDto caller ) {

		PrincipalVO principal = new PrincipalVO ();

		principal.setFruitoreLoader(new Supplier<PrincipalFruitoreVO>() {

			@Override
			public PrincipalFruitoreVO get () {
				logger.debug ( "fetch fruitore per il principal");
				PrincipalFruitoreVO r = buildFruitore ( databaseBundle.dtoFruitore );
				logger.debug ( "terminato fetch fruitore per il principal");
				return r;
			}

		});

		principal.setUtenteLoader(new Supplier<PrincipalUtenteVO>() {

			@Override
			public PrincipalUtenteVO get () {
				logger.debug ( "fetch dati utente per il principal");
				PrincipalUtenteVO r = buildUtenteV2 ( databaseBundle );
				logger.debug ( "terminato fetch dati utente per il principal");
				return r;
			}

		});

		principal.setEnteLoader(new Supplier<PrincipalEnteVO>() {

			@Override
			public PrincipalEnteVO get () {
				logger.debug ( "fetch ente per il principal");
				PrincipalEnteVO r = buildEnteV2 ( databaseBundle );
				logger.debug ( "terminato fetch ente per il principal");
				return r;
			}

		});

		principal.setTematicheLoader ( new Supplier<List<PrincipalTematicaVO>>() {

			@Override
			public List<PrincipalTematicaVO> get () {
				logger.debug ( "fetch tematiche e codici versamento associati per il principal");
				List<PrincipalTematicaVO> r = getTematicheAssociateV2 ( databaseBundle.dtoUtente, databaseBundle.dtoEnte );
				logger.debug ( "terminato fetch tematiche e codici versamento associati per il principal");
				return r;
			}

		});

		principal.setRuolo ( buildRuoloMock () );
		principal.setProfilo ( buildProfiloMock () );
		principal.setIpOrigine ( caller.getIp () );

		return principal;
	}

	private PrincipalEnteVO buildEnteV2 ( PrincipalDatabaseBundle databaseBundle ) {
		Utente utente = databaseBundle.dtoUtente;
		Ente ente = databaseBundle.dtoEnte;

		PrincipalEnteVO entePrincipal = new PrincipalEnteVO ();

		entePrincipal.setCodiceFiscale ( ente.getCodiceFiscale () );
		entePrincipal.setDenominazione ( ente.getDenominazione () );

		if (databaseBundle.isAdmin) {
			entePrincipal.setDescrizioneUtenteAmministratore (utente.getCodiceFiscale ());

			List<AssociazioneUtenteEnte> admins = getAdminsV2(ente.getId ());

			entePrincipal.getAdmins().clear ();

			for(AssociazioneUtenteEnte admin:admins) {
				entePrincipal.getAdmins().add(admin.getUtente().getId());
			}
		}

		entePrincipal.setId ( ente.getId () );
		entePrincipal.setIndirizzo ( ente.getIndirizzo () );
		entePrincipal.setLocalita ( ente.getLocalita () );
		entePrincipal.setEmail ( ente.getEmail () );

		return entePrincipal;
	}

	private PrincipalUtenteVO buildUtenteV2 (PrincipalDatabaseBundle databaseBundle) {
		Utente dtoUtente = databaseBundle.dtoUtente;

		PrincipalUtenteVO utente = new PrincipalUtenteVO ();
		utente.setCodiceFiscale ( dtoUtente.getCodiceFiscale () );
		utente.setCognome ( dtoUtente.getCognome () );
		utente.setId ( dtoUtente.getId () );
		utente.setNome ( dtoUtente.getNome () );
		utente.setDataInizioValidita ( dtoUtente.getDataInizioValidita () );
		utente.setDataFineValidita ( dtoUtente.getDataFineValidita () );

		utente.setDataInizioValiditaCurrent ( databaseBundle.dataInizioValiditaAssociazioneEnteUtente );
		utente.setDataFineValiditaCurrent ( databaseBundle.dataFineValiditaAssociazioneEnteUtente );

		utente.setCduLoader(new Supplier<List<PrincipalCduVO>>() {
			@Override
			public List<PrincipalCduVO> get() {
				logger.debug ( "fetch CDU associati al principal");
				List<PrincipalCduVO> r = caricaCduV2 ( databaseBundle );
				logger.debug ( "terminato fetch CDU associati al principal");
				return r;
			}
		});

		return utente;
	}

	public List<PrincipalTematicaVO> getTematicheAssociateV2 ( Utente dtoUtente, Ente dtoEnte ) {
		List<PrincipalTematicaVO> tematiche = new ArrayList<> ();

		List<VisibilitaCodiceVersamento> associazioniCV = visibilitaCodiceVersamentoRepository.findByIdUtenteIdEnte(dtoUtente.getId(), dtoEnte.getId());
		List<VisibilitaTematica> associazioniTematiche = visibilitaTematicaRepository.findByIdUtenteIdEnte(dtoUtente.getId(), dtoEnte.getId());

		Map<String, PrincipalTematicaVO> tematicheMap = new HashMap<>();
		Map<Long, PrincipalCodiceVersamentoVO> codiciVersamentoMap = new HashMap<>();
		List<String> codiciTematicheDaArricchire = new ArrayList<>();

		for (VisibilitaTematica associazione : associazioniTematiche) {


			PrincipalTematicaVO tematica = tematicheMap.getOrDefault(associazione.getPk().getCodice(), null);
			if (tematica == null) {
				tematica = new PrincipalTematicaVO();
				tematica.setCodice(associazione.getPk().getCodice());
				tematica.setCodiciVersamento(new ArrayList<>());
				tematica.setFlagVisibilitaTotale(Boolean.TRUE.equals(associazione.getVisibilitaTotale()));
				tematica.setDescrizione(associazione.getDescrizione());
				tematica.setId(associazione.getPk().getId());

				tematicheMap.put(associazione.getPk().getCodice(), tematica);
				tematiche.add(tematica);
			}

			if (Boolean.TRUE.equals(associazione.getVisibilitaTotale()) && !(Boolean.TRUE.equals(tematica.getFlagVisibilitaTotale()))) {
				tematica.setFlagVisibilitaTotale(Boolean.TRUE);
			}
		}

		for (VisibilitaCodiceVersamento associazione : associazioniCV) {
			if (associazione == null || associazione.getPk() == null) {
				//wth
				throw new RuntimeException("errore di configurazione");
			}
			if (associazione.getPk().getLivello() >= 99) {
				throw new RuntimeException("e' presente un riferimento ciclico nella configurazione della visibilita' per l'utente");
			}
			if (Boolean.TRUE.equals(associazione.getAnnullato())) {
				continue;
			}

			PrincipalTematicaVO tematica = tematicheMap.getOrDefault(associazione.getCodiceTematica(), null);
			if (tematica == null) {
				tematica = new PrincipalTematicaVO();
				tematica.setCodice(associazione.getCodiceTematica());
				tematica.setCodiciVersamento(new ArrayList<>());
				tematica.setFlagVisibilitaTotale(Boolean.TRUE.equals(associazione.getVisibilitaTotale()));
				tematica.setDescrizione(null); // TODO
				tematica.setId(null); // TODO

				tematicheMap.put(associazione.getCodiceTematica(), tematica);
				tematiche.add(tematica);
				codiciTematicheDaArricchire.add(associazione.getCodiceTematica());
			}

			if (Boolean.TRUE.equals(associazione.getVisibilitaTotale()) && !(Boolean.TRUE.equals(tematica.getFlagVisibilitaTotale()))) {
				tematica.setFlagVisibilitaTotale(Boolean.TRUE);
			}

			PrincipalCodiceVersamentoVO codiceVersamento = codiciVersamentoMap.getOrDefault(associazione.getPk().getId(), null);
			if (codiceVersamento == null) {
				codiceVersamento = new PrincipalCodiceVersamentoVO();
				codiceVersamento.setCodice(associazione.getCodice());
				codiceVersamento.setCodiceTematica(associazione.getCodiceTematica());
				codiceVersamento.setDescrizione(associazione.getDescrizione());
				codiceVersamento.setId(associazione.getPk().getId());
				codiciVersamentoMap.put(associazione.getPk().getId(), codiceVersamento);

				tematica.getCodiciVersamento().add(codiceVersamento);
			}
		}

		if (!codiciTematicheDaArricchire.isEmpty()) {
			List<TematicaPpay> tematicheFromDB = tematicaRepository.findByCodiceIn(codiciTematicheDaArricchire);
			Map<String, TematicaPpay> tematicheFromDBMap = new HashMap<>();
			for (TematicaPpay tematicaFromDB : tematicheFromDB) {
				tematicheFromDBMap.put(tematicaFromDB.getCodice(), tematicaFromDB);
			}

			for (PrincipalTematicaVO tematica : tematiche) {
				if (tematica.getId() != null) {
					continue;
				}
				TematicaPpay tematicaFromDB = tematicheFromDBMap.get(tematica.getCodice());
				if (tematicaFromDB == null) {
					throw new RuntimeException("tematica non trovata: " + tematica.getCodice());
				}
				tematica.setId(tematicaFromDB.getId().longValue());
				tematica.setDescrizione(tematicaFromDB.getDescrizione());
			}
		}

		return tematiche;
	}

	private List<AssociazioneUtenteEnte> getAdminsV2(Long idEnte) {

		return associazioneUtenteEnteRepository.findAll(new Specification<AssociazioneUtenteEnte>() {
			@Override
			public Predicate toPredicate(Root<AssociazioneUtenteEnte> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				Fetch<AssociazioneUtenteEnte, Utente> fetchU = root.fetch(AssociazioneUtenteEnte_.utente);
				fetchU.fetch(Utente_.statoAggiornamento);

				// root.fetch(AssociazioneUtenteEnte_.ente); // non dovrebbe servire

				return builder.and(
					builder.equal(
						root.get(AssociazioneUtenteEnte_.id).get(AssociazioneUtenteEntePK_.idEnte),
						idEnte.intValue()
									),
					builder.isNotNull(
						root.get(AssociazioneUtenteEnte_.flagAdmin)
									),
					builder.isTrue(
						root.get(AssociazioneUtenteEnte_.flagAdmin)
									)
								);
			}
		});
	}

	private List<PrincipalCduVO> caricaCduV2 ( PrincipalDatabaseBundle databaseBundle ) {

		List<PrincipalCduVO> cduUtente = new LinkedList<> ();

		if ( null != databaseBundle.dtoUtente ) {
			if ( databaseBundle.isAdmin ) {
				// amministratore: carico tutti i CDU disponibili
				List<Cdu> cdus = cduRepository.findAll (new Specification<Cdu>() {
					@Override
					public Predicate toPredicate(Root<Cdu> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
						root.fetch(Cdu_.categoria);
//						return builder.conjunction();
						return builder.and(
                            builder.notEqual(
                                root.get(Cdu_.codice),
                                "ASSISTENZA" )
                                        );
					}
				});

   // Aggiungo solo se il cdu di assistenza si trova nella tabella di associazione
				AssociazioneUtenteCduPK aeucpk = new AssociazioneUtenteCduPK ();
				aeucpk.setCodCdu ( "ASSISTENZA" );
				aeucpk.setIdEnte ( databaseBundle.dtoEnte.getId ().intValue () );
				aeucpk.setIdUtente ( databaseBundle.dtoUtente.getId () );
			    AssociazioneUtenteCdu associazioniCduAss = associazioneUtenteCduRepository.findOne(aeucpk);
			    if (associazioniCduAss != null )
			    {
			        cdus.add ( associazioniCduAss.getCdu () );
			    }
				for ( Cdu cdu: cdus ) {

					cduUtente.add ( new PrincipalCduVO (
						cdu.getId ().longValue (),
						cdu.getCategoria().getCodice(),
						cdu.getCategoria().getDescrizione(),
						cdu.getCodice (),
						cdu.getDescrizione () ) );
				}
			}
			else {
				List<AssociazioneUtenteCdu> associazioniCdu = associazioneUtenteCduRepository.findAll(new Specification<AssociazioneUtenteCdu>() {
					@Override
					public Predicate toPredicate(Root<AssociazioneUtenteCdu> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
						Fetch<AssociazioneUtenteCdu, Cdu> fetch = root.fetch(AssociazioneUtenteCdu_.cdu);
						fetch.fetch(Cdu_.categoria);

						return builder.and(
							builder.equal(
								root.get(AssociazioneUtenteCdu_.id).get(AssociazioneUtenteCduPK_.idEnte),
								databaseBundle.dtoEnte.getId ().intValue ()
											),
							builder.equal(
								root.get(AssociazioneUtenteCdu_.id).get(AssociazioneUtenteCduPK_.idUtente),
								databaseBundle.dtoUtente.getId ()
											)
										);
					}
				});

				// non amministratore: carico CDU da tabella associazioni
				if ( !associazioniCdu.isEmpty() ) {
					for ( AssociazioneUtenteCdu associazioneCdu: associazioniCdu ) {
						Cdu o = associazioneCdu.getCdu ();

						PrincipalCduVO dtoCdu = new PrincipalCduVO ();
						dtoCdu.setCodice ( o.getCodice () );
						dtoCdu.setCodiceCategoria ( o.getCategoria().getCodice() );
						dtoCdu.setDescrizioneCategoria (o.getCategoria().getDescrizione());
						dtoCdu.setDescrizione ( o.getDescrizione () );
						dtoCdu.setId ( o.getId ().longValue () );
						cduUtente.add ( dtoCdu );
					}
				}

			}
		}

		calcolaCDUVirtualiV2(databaseBundle, cduUtente);
		return cduUtente;
	}

	private void calcolaCDUVirtualiV2 ( PrincipalDatabaseBundle databaseBundle, List<PrincipalCduVO> cduUtente ) {
		if ( databaseBundle.isAdmin ) {

			for ( String cduVirtuale: Constants.USE_CASES.CDU_VIRTUALI_AMMINISTRATORE_ENTE ) {
				cduUtente.add ( new PrincipalCduVO (
					0L,
					"VIRTUALI", "Casi d'uso virtuali",
					cduVirtuale, "Caso d'uso virtuale: " + cduVirtuale ) );
			}
		}
	}

	private static class PrincipalDatabaseBundle {

		protected Fruitore dtoFruitore;

		protected Utente dtoUtente;

		protected Ente dtoEnte;

		protected Date dataInizioValiditaAssociazioneEnteUtente;

		protected Date dataFineValiditaAssociazioneEnteUtente;

		protected AssociazioneUtenteEnte associazioneUtenteEnte;

		protected boolean isAdmin = false;
	}

}
