/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Cache;
import org.hibernate.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.business.service.ProfilazioneService;
import it.csi.epay.epaypacatalogsrv.business.util.ComparatoreDiListeDiverse;
import it.csi.epay.epaypacatalogsrv.business.util.ComparatoreDiListeDiverse.ComparatoreDiElementiDiversi;
import it.csi.epay.epaypacatalogsrv.business.util.ComparatoreDiListeDiverse.Coppia;
import it.csi.epay.epaypacatalogsrv.business.util.ComparatoreDiListeDiverse.DifferenzaFraListeDiverse;
import it.csi.epay.epaypacatalogsrv.dto.CallerInputDto;
import it.csi.epay.epaypacatalogsrv.dto.PrincipalInputDto;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteEnte;
import it.csi.epay.epaypacatalogsrv.model.Ente;
import it.csi.epay.epaypacatalogsrv.model.Utente;
import it.csi.epay.epaypacatalogsrv.repository.AssociazioneUtenteEnteRepository;
import it.csi.epay.epaypacatalogsrv.repository.EnteRepository;
import it.csi.epay.epaypacatalogsrv.repository.UtenteRepository;
import it.csi.epay.epaypacatalogsrv.test.config.EpaycatalogWsUnitTestConfigPostgre;
import it.csi.epay.epaypacatalogsrv.test.model.ParentOperationTest;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EpaycatalogWsUnitTestConfigPostgre.class })
@Transactional
public class ProfilazioneServiceMigrationTest extends ParentOperationTest {

	@Autowired
	private ProfilazioneService profilazioneService;

	@Autowired
	private AssociazioneUtenteEnteRepository associazioneUtenteEnteRepository;

	@Autowired
	private UtenteRepository utenteRepository;

	@Autowired
	private EnteRepository enteRepository;

	@PersistenceContext
	private EntityManager entityManager;

	

	@Test
	public void test() throws Exception {
		Session session = (Session) this.entityManager.getDelegate();

		List<List<String>> testCases = new ArrayList<>();

		testCases.add(Arrays.asList("AAA", "BBB"));
		
		for (List<String> testCase : testCases) {
			Utente utente = utenteRepository.findOneByCodiceFiscale(testCase.get(0));
			Ente ente = enteRepository.findOneByCodiceFiscale(testCase.get(1));

			CallerInputDto caller = new CallerInputDto();
			caller.setCodiceApplicativo("EPAYPACATALOGWEB");
			PrincipalInputDto principal = new PrincipalInputDto();
			principal.setCodiceEnte(ente.getCodiceFiscale());
			principal.setCodiceFiscale(utente.getCodiceFiscale());
			caller.setPrincipal(principal);

			System.out.println("processing " + utente.getCodiceFiscale() + " per ente "
					+ ente.getCodiceFiscale());

			System.out.println("stats before test");
			entityManager.flush();
			session.getSessionFactory().getStatistics().logSummary();
			Cache cache = session.getSessionFactory().getCache();
			if (cache != null) {
			    cache.evictCollectionRegions();
			    cache.evictDefaultQueryRegion();
			    cache.evictEntityRegions();
			    cache.evictNaturalIdRegions();
			    cache.evictQueryRegions();
			}

			long t0 = System.currentTimeMillis();
			PrincipalVO profilazioneNew = profilazioneService.getPrincipalV2(caller);
			long t1 = System.currentTimeMillis();

			System.out.println("stats after new fetch");
			entityManager.flush();
			session.getSessionFactory().getStatistics().logSummary();
			cache = session.getSessionFactory().getCache();
			if (cache != null) {
			    cache.evictCollectionRegions();
			    cache.evictDefaultQueryRegion();
			    cache.evictEntityRegions();
			    cache.evictNaturalIdRegions();
			    cache.evictQueryRegions();
			}
			
			System.out.println( "completed in" + (t1 - t0));
			
			PrincipalVO profilazioneOld = profilazioneService.getPrincipal(caller);

			System.out.println("comparing versions and validating testcase");
			try {
				assertDeepEquals(profilazioneOld, profilazioneNew);
			} catch (Throwable t) {
				t.printStackTrace();
				System.out.println("old " + profilazioneOld);
				System.out.println("new " + profilazioneNew);
				throw t;
			}
		}
		
	}
	
	@Test
	public void testNoRegressions() throws Exception {
		List<AssociazioneUtenteEnte> associazioni = associazioneUtenteEnteRepository.findAll(new PageRequest(0, 999999)).getContent();

		Integer numTests = 0;
		Long totalTimeOld = 0L;
		Long totalTimeNew = 0L;

		List<AssociazioneUtenteEnte> falliti = new ArrayList<>();
		
		Session session = (Session) this.entityManager.getDelegate();

		for (AssociazioneUtenteEnte associazione : associazioni) {
			Utente utente = utenteRepository.findOne(associazione.getId().getIdUtente());

			CallerInputDto caller = new CallerInputDto();
			caller.setCodiceApplicativo("EPAYPACATALOGWEB");
			PrincipalInputDto principal = new PrincipalInputDto();
			principal.setCodiceEnte(associazione.getEnte().getCodiceFiscale());
			principal.setCodiceFiscale(utente.getCodiceFiscale());
			caller.setPrincipal(principal);

			System.out.println("processing " + utente.getCodiceFiscale() + " per ente "
					+ associazione.getEnte().getCodiceFiscale());

			System.out.println("stats before test");
			entityManager.flush();
			session.getSessionFactory().getStatistics().logSummary();
			Cache cache = session.getSessionFactory().getCache();
			if (cache != null) {
			    cache.evictCollectionRegions();
			    cache.evictDefaultQueryRegion();
			    cache.evictEntityRegions();
			    cache.evictNaturalIdRegions();
			    cache.evictQueryRegions();
			}

			long t0 = System.currentTimeMillis();
			PrincipalVO profilazioneOld;
			try {
				profilazioneOld = profilazioneService.getPrincipal(caller);
			} catch (Exception e) {
				profilazioneOld = null;
			}
			long t1 = System.currentTimeMillis();

			System.out.println("stats after old fetch");
			entityManager.flush();
			session.getSessionFactory().getStatistics().logSummary();
			cache = session.getSessionFactory().getCache();
			if (cache != null) {
			    cache.evictCollectionRegions();
			    cache.evictDefaultQueryRegion();
			    cache.evictEntityRegions();
			    cache.evictNaturalIdRegions();
			    cache.evictQueryRegions();
			}
			
			long t2 = System.currentTimeMillis();
			PrincipalVO profilazioneNew;
			try {
				profilazioneNew = profilazioneService.getPrincipalV2(caller);
			} catch (Exception e) {
				profilazioneNew = null;
			}
			long t3 = System.currentTimeMillis();

			System.out.println("stats after new fetch");
			entityManager.flush();
			session.getSessionFactory().getStatistics().logSummary();
			cache = session.getSessionFactory().getCache();
			if (cache != null) {
			    cache.evictCollectionRegions();
			    cache.evictDefaultQueryRegion();
			    cache.evictEntityRegions();
			    cache.evictNaturalIdRegions();
			    cache.evictQueryRegions();
			}
			
			totalTimeOld += (t1 - t0);
			totalTimeNew += (t3 - t2);

			System.out.println("comparing versions and validating testcase");
			try {
				assertDeepEquals(profilazioneOld, profilazioneNew);
			} catch (Throwable t) {
				t.printStackTrace();
				System.out.println("old " + profilazioneOld);
				System.out.println("new " + profilazioneNew);
				falliti.add(associazione);
				// throw t;
			}

			numTests++;
			System.out.println(numTests + " tests finished, " + falliti.size()  + " failed, avg times old=" + Math.round(totalTimeOld / numTests)
					+ ", new=" + Math.round(totalTimeNew / numTests));
		}
		
		for (AssociazioneUtenteEnte failed : falliti) {

			System.out.println("FALLITO: utente " + failed.getUtente().getId() + " " + failed.getUtente().getCodiceFiscale() + 
					" per ente " + failed.getEnte().getId() + " " + failed.getEnte().getCodiceFiscale());
		}
	}

	private void assertDeepEquals(PrincipalVO profilazioneOld, PrincipalVO profilazioneNew) {
		if (profilazioneOld == null && profilazioneNew == null) {
			return;
		}
		assertNotNull(profilazioneOld);
		assertNotNull(profilazioneNew);
		assertEqualsExt(profilazioneOld.getIpOrigine(), profilazioneNew.getIpOrigine());
		assertDeepEquals(profilazioneOld.getEnte(), profilazioneNew.getEnte());
		assertDeepEquals(profilazioneOld.getFruitore(), profilazioneNew.getFruitore());
		assertDeepEquals(profilazioneOld.getProfilo(), profilazioneNew.getProfilo());
		assertDeepEquals(profilazioneOld.getRuolo(), profilazioneNew.getRuolo());
		assertDeepEquals(profilazioneOld.getUtente(), profilazioneNew.getUtente());
		assertDeepEquals1(profilazioneOld.getTematiche(), profilazioneNew.getTematiche());
	}

	private void assertDeepEquals(PrincipalUtenteVO utente, PrincipalUtenteVO utente2) {
		assertEqualsExt(utente.getCodiceFiscale(), utente2.getCodiceFiscale());
		assertEqualsExt(utente.getCognome(), utente2.getCognome());
		assertEqualsExt(utente.getDataFineValidita(), utente2.getDataFineValidita());
		assertEqualsExt(utente.getDataFineValiditaCurrent(), utente2.getDataFineValiditaCurrent());
		assertEqualsExt(utente.getDataInizioValidita(), utente2.getDataInizioValidita());
		assertEqualsExt(utente.getDataInizioValiditaCurrent(), utente2.getDataInizioValiditaCurrent());
		assertEqualsExt(utente.getId(), utente2.getId());
		assertEqualsExt(utente.getNome(), utente2.getNome());
		assertEqualsExt(utente.isValid(), utente2.isValid());
		assertDeepEquals2(utente.getCdu(), utente2.getCdu());
	}

	private void assertDeepEquals(PrincipalEnteVO ente, PrincipalEnteVO ente2) {
		assertEqualsExt(ente.getCodiceFiscale(), ente2.getCodiceFiscale());
		assertEqualsExt(ente.getDenominazione(), ente2.getDenominazione());
		assertEqualsExt(ente.getDescrizioneUtenteAmministratore(), ente2.getDescrizioneUtenteAmministratore());
		assertEqualsExt(ente.getEmail(), ente2.getEmail());
		assertEqualsExt(ente.getId(), ente2.getId());
		assertEqualsExt(ente.getIndirizzo(), ente2.getIndirizzo());
		assertEqualsExt(ente.getLocalita(), ente2.getLocalita());
		assertDeepEquals3(ente.getAdmins(), ente2.getAdmins());
	}

	private void assertDeepEquals(PrincipalRuoloVO ruolo, PrincipalRuoloVO ruolo2) {

		assertEqualsExt(ruolo.getId(), ruolo2.getId());
		assertEqualsExt(ruolo.getCodice(), ruolo2.getCodice());
		assertEqualsExt(ruolo.getDescrizione(), ruolo2.getDescrizione());
		assertDeepEquals2(ruolo.getCdu(), ruolo2.getCdu());
	}

	private void assertDeepEquals(PrincipalProfiloVO profilo, PrincipalProfiloVO profilo2) {

		assertEqualsExt(profilo.getId(), profilo2.getId());
		assertEqualsExt(profilo.getCodice(), profilo2.getCodice());
		assertEqualsExt(profilo.getDescrizione(), profilo2.getDescrizione());
	}

	private void assertDeepEquals(PrincipalFruitoreVO fruitore, PrincipalFruitoreVO fruitore2) {

		assertEqualsExt(fruitore.getId(), fruitore2.getId());
		assertEqualsExt(fruitore.getCodice(), fruitore2.getCodice());
		assertEqualsExt(fruitore.getDataFineValidita(), fruitore2.getDataFineValidita());
		assertEqualsExt(fruitore.getDataInizioValidita(), fruitore2.getDataInizioValidita());
		assertEqualsExt(fruitore.getDescrizione(), fruitore2.getDescrizione());
		assertEqualsExt(fruitore.isValid(), fruitore2.isValid());
		assertDeepEquals5(fruitore.getScope(), fruitore2.getScope());
	}

	private void assertDeepEquals1(List<PrincipalTematicaVO> tematiche, List<PrincipalTematicaVO> tematiche2) {
		DifferenzaFraListeDiverse<PrincipalTematicaVO, PrincipalTematicaVO> res = ComparatoreDiListeDiverse
				.compareLists(tematiche, tematiche2,
						new ComparatoreDiElementiDiversi<PrincipalTematicaVO, PrincipalTematicaVO>() {

							@Override
							public boolean compara(PrincipalTematicaVO v1, PrincipalTematicaVO v2) {
								return Objects.equals(v1.getId(), v2.getId())
										&& Objects.equals(v1.getCodice(), v2.getCodice())
										&& Objects.equals(v1.getDescrizione(), v2.getDescrizione())
										&& Objects.equals(v1.getFlagVisibilitaTotale(), v2.getFlagVisibilitaTotale());
							}

						});

		assertTrueExt(res.getElementiSoloNellaPrima().isEmpty());
		assertTrueExt(res.getElementiSoloNellaSeconda().isEmpty());

		for (Coppia<PrincipalTematicaVO, PrincipalTematicaVO> coppia : res.getElementiInTutteEDue()) {
			assertDeepEquals6(coppia.getPrimo(), coppia.getSecondo());
		}
	}

	private void assertDeepEquals6(PrincipalTematicaVO primo, PrincipalTematicaVO secondo) {
		assertEqualsExt(primo.getCodice(), secondo.getCodice());
		assertEqualsExt(primo.getDescrizione(), secondo.getDescrizione());
		assertEqualsExt(primo.getFlagVisibilitaTotale(), secondo.getFlagVisibilitaTotale());
		assertEqualsExt(primo.getId(), secondo.getId());
		assertDeepEquals7(primo.getCodiciVersamento(), secondo.getCodiciVersamento());
	}

	private void assertDeepEquals7(List<PrincipalCodiceVersamentoVO> codiciVersamento,
			List<PrincipalCodiceVersamentoVO> codiciVersamento2) {
		DifferenzaFraListeDiverse<PrincipalCodiceVersamentoVO, PrincipalCodiceVersamentoVO> res = ComparatoreDiListeDiverse
				.compareLists(codiciVersamento, codiciVersamento2,
						new ComparatoreDiElementiDiversi<PrincipalCodiceVersamentoVO, PrincipalCodiceVersamentoVO>() {

							@Override
							public boolean compara(PrincipalCodiceVersamentoVO v1, PrincipalCodiceVersamentoVO v2) {
								return Objects.equals(v1.getId(), v2.getId())
										&& Objects.equals(v1.getCodice(), v2.getCodice())
										&& Objects.equals(v1.getCodiceTematica(), v2.getCodiceTematica())
										&& Objects.equals(v1.getDescrizione(), v2.getDescrizione());
							}

						});

		assertTrueExt(res.getElementiSoloNellaPrima().isEmpty());
		assertTrueExt(res.getElementiSoloNellaSeconda().isEmpty());
	}

	private void assertDeepEquals2(List<PrincipalCduVO> cdu, List<PrincipalCduVO> cdu2) {
		DifferenzaFraListeDiverse<PrincipalCduVO, PrincipalCduVO> res = ComparatoreDiListeDiverse.compareLists(cdu,
				cdu2, new ComparatoreDiElementiDiversi<PrincipalCduVO, PrincipalCduVO>() {

					@Override
					public boolean compara(PrincipalCduVO v1, PrincipalCduVO v2) {
						return Objects.equals(v1.getId(), v2.getId()) && Objects.equals(v1.getCodice(), v2.getCodice())
								&& Objects.equals(v1.getCodiceCategoria(), v2.getCodiceCategoria())
								&& Objects.equals(v1.getDescrizione(), v2.getDescrizione())
								&& Objects.equals(v1.getDescrizioneCategoria(), v2.getDescrizioneCategoria());
					}

				});

		assertTrueExt(res.getElementiSoloNellaPrima().isEmpty());
		assertTrueExt(res.getElementiSoloNellaSeconda().isEmpty());
	}

	private void assertDeepEquals3(List<Long> admins, List<Long> admins2) {
		DifferenzaFraListeDiverse<Long, Long> res = ComparatoreDiListeDiverse.compareLists(admins, admins2,
				new ComparatoreDiElementiDiversi<Long, Long>() {

					@Override
					public boolean compara(Long v1, Long v2) {
						return Objects.equals(v1, v2);
					}

				});

		assertTrueExt(res.getElementiSoloNellaPrima().isEmpty());
		assertTrueExt(res.getElementiSoloNellaSeconda().isEmpty());
	}

	private void assertDeepEquals5(List<PrincipalScopeVO> scope, List<PrincipalScopeVO> scope2) {
		DifferenzaFraListeDiverse<PrincipalScopeVO, PrincipalScopeVO> res = ComparatoreDiListeDiverse
				.compareLists(scope, scope2, new ComparatoreDiElementiDiversi<PrincipalScopeVO, PrincipalScopeVO>() {

					@Override
					public boolean compara(PrincipalScopeVO v1, PrincipalScopeVO v2) {
						return Objects.equals(v1.getId(), v2.getId()) && Objects.equals(v1.getCodice(), v2.getCodice())
								&& Objects.equals(v1.getDescrizione(), v2.getDescrizione());
					}

				});

		assertTrueExt(res.getElementiSoloNellaPrima().isEmpty());
		assertTrueExt(res.getElementiSoloNellaSeconda().isEmpty());
	}

	private void assertEqualsExt(Object o1, Object o2) {
		try {
			assertEquals(o1, o2);
		} catch (Throwable t) {
			throw t;
		}
	}

	private void assertTrueExt(boolean v) {
		try {
			assertTrue(v);
		} catch (Throwable t) {
			throw t;
		}
	}

}
