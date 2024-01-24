/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.test;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.csi.epay.epaypaweb.dto.FlussoCompletoDto;
import it.csi.epay.epaypaweb.dto.FlussoDto;
import it.csi.epay.epaypaweb.dto.NotificaPagamentoDto;
import it.csi.epay.epaypaweb.dto.SoggettoAnagraficoDto;
import it.csi.epay.epaypaweb.enumeration.TipologiaSoggettoEnum;
import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dad.impl.GestioneFlussiDadImpl;
import it.csi.epay.epaypaweb.persistence.dad.interf.GestioneFlussiDad;
import it.csi.epay.epaypaweb.persistence.dao.impl.EpaypaDStatoFlussoDaoImpl;
import it.csi.epay.epaypaweb.persistence.dao.impl.EpaypaDTipoFlussoDaoImpl;
import it.csi.epay.epaypaweb.persistence.dao.impl.EpaypaTCodiceVersamentoDaoImpl;
import it.csi.epay.epaypaweb.persistence.dao.impl.EpaypaTEnteDaoImpl;
import it.csi.epay.epaypaweb.persistence.dao.impl.EpaypaTFlussoDaoImpl;
import it.csi.epay.epaypaweb.persistence.dao.impl.EpaypaTNotificaPagamentoDaoImpl;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaDStatoFlussoDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaDTipoFlussoDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTCodiceVersamentoDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTEnteDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTFlussoDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTNotificaPagamentoDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTNotificaPagamento;

public class EpaypaTNotificaPagamentoDaoTest extends DaoBaseTest {

	@Before
	public void setup() throws IOException {
		super.setup();
	}

	//@Test
	public void testCountAllNotificaPagamentoByIdFlusso() {
		try {
			EpaypaTNotificaPagamentoDao dao = new EpaypaTNotificaPagamentoDaoImpl();
			setEntityManager(dao);

			Long idFlusso = 33L;
			System.out.println(">> idFlusso: " + idFlusso);

			transaction.begin();
			Long num = dao.countAllByIdFlusso(idFlusso);
			transaction.commit();

			System.out.println(">> " + num);

			Assert.assertEquals(4, num.longValue());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//@Test
	public void testFindAllFlussoLightByIdFlusso() {
		try {
			EpaypaTNotificaPagamentoDao dao = new EpaypaTNotificaPagamentoDaoImpl();
			setEntityManager(dao);

			Long idFlusso = 33L;
			System.out.println(">> idFlusso: " + idFlusso);

			transaction.begin();
			List<EpaypaTNotificaPagamento> entityList = dao.findAllByIdFlusso(idFlusso);
			List<NotificaPagamentoDto> dtoList = Util.toNotificaPagamentoDtoList(entityList);
			transaction.commit();

			System.out.println(">> dtoList: " + dtoList);

			Assert.assertEquals(dtoList.isEmpty(), false);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//@Test
	public void testFindOneNotificaPagamento() {
		try {
			EpaypaTNotificaPagamentoDao dao = new EpaypaTNotificaPagamentoDaoImpl();
			setEntityManager(dao);

			Long idNotificaPagamento = 68L;
			System.out.println(">> idNotificaPagamento: " + idNotificaPagamento);

			transaction.begin();
			EpaypaTNotificaPagamento epaypaTNotificaPagamento = dao.findOne(idNotificaPagamento);
			NotificaPagamentoDto dto = Util.toNotificaPagamentoDto(epaypaTNotificaPagamento);
			transaction.commit();

			System.out.println(">> dto: " + dto);

			Assert.assertEquals(dto.getId().longValue(), 68L);

		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void completeCycleTest() {

		Long idFlusso = null;
		BigDecimal importoTotale = new BigDecimal(12736.0);
		EpaypaTEnteDao epaypaTEnteDao = null;
		EpaypaTFlussoDao epaypaTFlussoDao = null;
		EpaypaDTipoFlussoDao epaypaDTipoFlussoDao = null;
		EpaypaDStatoFlussoDao epaypaDStatoFlussoDao = null;
		EpaypaTCodiceVersamentoDao epaypaTCodiceVersamentoDao = null;
		EpaypaTNotificaPagamentoDao epaypaTNotificaPagamentoDao = null;

		GestioneFlussiDad dad = new GestioneFlussiDadImpl();

		// Inizializzazione della persistenza
		try {
			setEntityManager(epaypaTEnteDao = new EpaypaTEnteDaoImpl());
			setEntityManager(epaypaDTipoFlussoDao = new EpaypaDTipoFlussoDaoImpl());
			setEntityManager(epaypaDStatoFlussoDao = new EpaypaDStatoFlussoDaoImpl());
			setEntityManager(epaypaTCodiceVersamentoDao = new EpaypaTCodiceVersamentoDaoImpl());
			setEntityManager(epaypaTFlussoDao = new EpaypaTFlussoDaoImpl());
			setEntityManager(epaypaTNotificaPagamentoDao = new EpaypaTNotificaPagamentoDaoImpl());

			setDao(dad, epaypaDTipoFlussoDao);
			setDao(dad, epaypaDStatoFlussoDao);
			setDao(dad, epaypaTEnteDao);
			setDao(dad, epaypaTCodiceVersamentoDao);
			setDao(dad, epaypaTFlussoDao);
			setDao(dad, epaypaTNotificaPagamentoDao);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}

		// Inserimento flusso
		try {
			FlussoCompletoDto<NotificaPagamentoDto> flussoNotifiche = new FlussoCompletoDto<NotificaPagamentoDto>();
			FlussoDto flussoDto = new FlussoDto();

			flussoDto.setCodFiscaleEnte("CF1");
			flussoDto.setCodVersamento("CV1");
			flussoDto.setDenominazioneEnte("Ente unit test");
			flussoDto.setIdEnte(1);
			flussoDto.setIdMessaggio(UUID.randomUUID().toString().substring(0, 35));
			flussoDto.setImportoTotale(importoTotale);
			flussoDto.setNumeroElementi(1);
			flussoDto.setPagamentiSpontanei(false);
			flussoDto.setUtenteInserimento("JUNIT");
			flussoDto.setUtenteUltimaVariazione("JUNIT");

			flussoNotifiche.setFlusso(flussoDto);

			NotificaPagamentoDto notifica = new NotificaPagamentoDto();
			notifica.setIUV("IUV TEST");
			notifica.setImportoPagato(new BigDecimal(12736.0));
			notifica.setDataEsitoPagamento(new Date());
			notifica.setIdPsp("PSPID");
			notifica.setDataAvvioTransazione(new Date());
			notifica.setIUR("IUR TEST");
			
			SoggettoAnagraficoDto sogdeb = new SoggettoAnagraficoDto();
			sogdeb.setIdUnivocoFiscale("CFSCFS77H55L716T");
			sogdeb.setTipologiaSoggettoEnum(TipologiaSoggettoEnum.PERSONA_FISICA);
			sogdeb.setCognome("Cafesso");
			sogdeb.setNome("Cafasso");
			sogdeb.setIndirizzo("Piazza Como");
			sogdeb.setCivico("3");
			sogdeb.setCap("15121");
			sogdeb.setLocalita("Alessandria");
			sogdeb.setProvincia("AL");
			sogdeb.setNazione("IT");
			sogdeb.setEmail("cafass@gmail.com");
			notifica.setSoggettoDebitore(sogdeb);

			List<NotificaPagamentoDto> listaNotifiche = new ArrayList<NotificaPagamentoDto>();
			listaNotifiche.add(notifica);
			flussoNotifiche.setItemList(listaNotifiche);

			transaction.begin();
			idFlusso = dad.insertFlussoNotifichePagamento(flussoNotifiche, new Timestamp(System.currentTimeMillis()));
			transaction.commit();

			System.out.println("Inserito flusso con ID:" + idFlusso);

		} catch (PersistenceException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}

		// Lettura Flusso DAO
		try {
			epaypaTFlussoDao.findOne(idFlusso);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}

		// Lettura Flusso
		try {
			FlussoCompletoDto<NotificaPagamentoDto> flussoNP = dad.findFlussoNotifichePagamento(idFlusso);
			Assert.assertEquals(importoTotale, flussoNP.getFlusso().getImportoTotale());
		} catch (PersistenceException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
		
		// Elimina Flusso
		try {
			transaction.begin();
			dad.removeFlussoById(idFlusso);
			transaction.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
}
