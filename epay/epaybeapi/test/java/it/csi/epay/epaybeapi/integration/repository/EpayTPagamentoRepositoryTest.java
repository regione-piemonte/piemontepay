/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.repository;

import it.csi.epay.epaybeapi.integration.domain.EpayTPagamento;
import it.csi.epay.epaybeapi.integration.repository.EpayTPagamentoRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.apache.commons.collections.IteratorUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaybeapi.testbed.config.UnitTestDB;
import it.csi.epay.epaybeapi.testbed.model.ParentHttpCallTest;

/**
 * Spring data Jpa repository test for "EpayTPagamento" <br>
 *
 * @author EII
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { UnitTestDB.class })
@Transactional
public class EpayTPagamentoRepositoryTest extends ParentHttpCallTest {

	@Autowired
	private EpayTPagamentoRepository repository;

	@Test
	public void countShouldWork() {

		repository.count();
	}

	@Test
	public void findByStatoPagamentoShouldWork() {
		Page<EpayTPagamento> records = repository.findByStatoPagamento(new Short((short) 0),
				new PageRequest(0, 10, new Sort(Sort.Direction.DESC, "idPagamento")));
		assertNotNull(records);
	}

	@Test
	public void findByIuvNumeroAvvisoShouldWork() {
		Page<EpayTPagamento> records = repository.findByStatoPagamento(new Short((short) 0),
				new PageRequest(0, 3, new Sort(Sort.Direction.DESC, "idPagamento")));
		assertNotNull(records);
		assertTrue(records.getSize() > 0);

		for (EpayTPagamento record : records) {
			EpayTPagamento pagamento = repository.findByIuvNumeroAvviso(record.getIuvNumeroAvviso());
			assertNotNull(pagamento);

		}
	}
	
	@Test
	public void findByCodiceRiferimentoEnteNotNullShouldWork() {
		Page<EpayTPagamento> records = repository.findByCodiceRiferimentoEnteNotNull( new PageRequest ( 0, 5 ) );
		assertNotNull(records);
		EpayTPagamento pg = (EpayTPagamento)IteratorUtils.toList(records.iterator()).get(0);
		assertNotNull(pg.getCodicePagamentoRifEnte());
	}

	@Test
	public void findByCodiceVersamentoAndCodiceFiscaleEnteAndCodiceRiferimentoEnteShouldWork() {
		
		Page<EpayTPagamento> records = repository.findByCodiceRiferimentoEnteNotNull( new PageRequest ( 0, 5 ) );
		assertNotNull(records);
		EpayTPagamento pg = (EpayTPagamento)IteratorUtils.toList(records.iterator()).get(0);
		
		String codiceFiscaleEnte = pg.getEpayTTipoPagamento().getEpayTEnti().getCodiceFiscale();
		String codiceRiferiemntoEnte = pg.getCodicePagamentoRifEnte();
		String codiceVersamento = pg.getEpayTTipoPagamento().getCodiceVersamento();

		EpayTPagamento epayTPagamento = repository.findByCodiceRiferimentoEnteAndCodiceFiscaleEnteAndCodiceVersamento(
				codiceRiferiemntoEnte, codiceFiscaleEnte, codiceVersamento);

		assertNotNull(epayTPagamento);
		assertEquals(codiceRiferiemntoEnte, epayTPagamento.getCodicePagamentoRifEnte());
		assertEquals(codiceVersamento, epayTPagamento.getEpayTTipoPagamento().getCodiceVersamento());
		assertEquals(codiceFiscaleEnte, epayTPagamento.getEpayTTipoPagamento().getEpayTEnti().getCodiceFiscale());
	}

	@Test
	public void findByCodiceVersamentoAndCodiceFiscaleEnteAndCodiceRiferimentoEnteShouldNotWork() {
		String codiceFiscaleEnte = "UNK";
		String codiceRiferiemntoEnte = "UNK";
		String codiceVersamento = "UNK";

		EpayTPagamento epayTPagamento = repository.findByCodiceRiferimentoEnteAndCodiceFiscaleEnteAndCodiceVersamento(
				codiceRiferiemntoEnte, codiceFiscaleEnte, codiceVersamento);

		assertNull(epayTPagamento);
	}

	@Test
	public void findByIdCodiceVersamentoAndCodiceRiferimentoEnteShouldWork() {
		Page<EpayTPagamento> records = repository.findByCodiceRiferimentoEnteNotNull( new PageRequest ( 0, 5 ) );
		assertNotNull(records);
		EpayTPagamento pg = (EpayTPagamento)IteratorUtils.toList(records.iterator()).get(0);
		
		String codiceRiferimentoEnte = pg.getCodicePagamentoRifEnte();
		Long idCodiceVersamento = pg.getEpayTTipoPagamento().getIdTipoPagamento();

		EpayTPagamento epayTPagamento = repository
				.findByIdCodiceRiferimentoEnteAndCodiceVersamento(codiceRiferimentoEnte, idCodiceVersamento);

		assertNotNull(epayTPagamento);
		assertEquals(codiceRiferimentoEnte, epayTPagamento.getCodicePagamentoRifEnte());
		assertEquals(idCodiceVersamento, epayTPagamento.getEpayTTipoPagamento().getIdTipoPagamento());
	}

	@Test
	public void findByIdCodiceVersamentoAndCodiceRiferimentoEnteShoulNotdWork() {
		String codiceRiferimentoEnte = "UNK";
		Long idCodiceVersamento = 38276L;

		EpayTPagamento epayTPagamento = repository
				.findByIdCodiceRiferimentoEnteAndCodiceVersamento(codiceRiferimentoEnte, idCodiceVersamento);

		assertNull(epayTPagamento);

	}
}
