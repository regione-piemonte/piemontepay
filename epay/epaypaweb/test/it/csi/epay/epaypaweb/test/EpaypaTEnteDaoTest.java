/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.test;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.csi.epay.epaypaweb.dto.EnteDto;
import it.csi.epay.epaypaweb.persistence.dao.impl.EpaypaTEnteDaoImpl;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTEnteDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTEnte;

public class EpaypaTEnteDaoTest extends DaoBaseTest {

	@Before
	public void setup() throws IOException {
		super.setup();
	}

	@Test
	public void testGetEntiByCodUtente() {
		try {
			EpaypaTEnteDao dao = new EpaypaTEnteDaoImpl();
			setEntityManager(dao);

			String codUtente = "AAAAAA00B77B000F";
			
			transaction.begin();
			List<EpaypaTEnte> entityList = dao.findAllByCodUtente(codUtente);
			transaction.commit();

			List<EnteDto> dtoList = Util.toEnteDtoList(entityList);
			System.out.println(">> " + dtoList);
			
			Assert.assertEquals(2, dtoList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
