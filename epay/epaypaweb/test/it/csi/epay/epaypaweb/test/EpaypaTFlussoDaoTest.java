/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.test;

import java.io.IOException;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.csi.epay.epaypaweb.dto.FlussoDto;
import it.csi.epay.epaypaweb.dto.FlussoLightFilterDto;
import it.csi.epay.epaypaweb.persistence.dao.impl.EpaypaTFlussoDaoImpl;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTFlussoDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTFlusso;

public class EpaypaTFlussoDaoTest extends DaoBaseTest {

	@Before
	public void setup() throws IOException {
		super.setup();
	}

	@Test
	public void testFindOneFlusso() {
		try {
			EpaypaTFlussoDao dao = new EpaypaTFlussoDaoImpl();
			setEntityManager(dao);

			Long idFlusso = 1L;
			System.out.println(">> idFlusso: " + idFlusso);

			transaction.begin();
			EpaypaTFlusso entity = dao.findOne(idFlusso);
			FlussoDto dto = Util.toFlussoDto(entity);
			transaction.commit();

			System.out.println(">> dto: " + dto);

			Assert.assertEquals(dto != null, true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	public void testCountAllFlusso() {
		try {
			EpaypaTFlussoDao dao = new EpaypaTFlussoDaoImpl();
			setEntityManager(dao);
			
			FlussoLightFilterDto filter = new FlussoLightFilterDto();
			System.out.println(">> filter: " + filter);
	
			transaction.begin();
			Long num = dao.countAllByFilter(filter, null);
			transaction.commit();

			System.out.println(">> " + num);
			
			Assert.assertEquals(21, num.longValue());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//@Test
	public void testCountFlussoByFilter() {
		try {
			EpaypaTFlussoDao dao = new EpaypaTFlussoDaoImpl();
			setEntityManager(dao);
			
			FlussoLightFilterDto filter = new FlussoLightFilterDto();
			filter.setPagamentiSpontanei(false);
//			filter.setIdCodVersamento(1);
//			filter.setDataInserimentoDa(new GregorianCalendar(2017, GregorianCalendar.JANUARY, 19).getTime());
			filter.setDataInserimentoA(new GregorianCalendar(2017, GregorianCalendar.JANUARY, 18).getTime());
			System.out.println(">> filter: " + filter);
	
			transaction.begin();
			Long num = dao.countAllByFilter(filter, null);
			transaction.commit();

			System.out.println(">> " + num);
			
			Assert.assertEquals(7, num.longValue());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
