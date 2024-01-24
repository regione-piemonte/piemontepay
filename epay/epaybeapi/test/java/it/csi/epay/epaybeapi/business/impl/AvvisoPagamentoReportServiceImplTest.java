/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.business.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaybeapi.business.AvvisoPagamentoReportService;
import it.csi.epay.epaybeapi.integration.dto.EpayTPdfReportDTO;
import it.csi.epay.epaybeapi.testbed.config.UnitTestDB;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { UnitTestDB.class })
@Transactional
public class AvvisoPagamentoReportServiceImplTest {
	
	@Autowired
	private AvvisoPagamentoReportService avvisoPagamentoReportService;

	@Test
	public void shouldExist() {
		assertNotNull(avvisoPagamentoReportService);
	}

	@Test
	public void getJasperReportShouldWork() {
		EpayTPdfReportDTO epayTPdfReportDTO = avvisoPagamentoReportService.getJasperReport();
		assertNotNull(epayTPdfReportDTO);
	}

}
