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

import it.csi.epay.epaypaweb.dto.CduDto;
import it.csi.epay.epaypaweb.persistence.dao.impl.EpaypaTCduDaoImpl;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTCduDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTCdu;

public class EpaypaTCduDaoTest extends DaoBaseTest {

	@Before
	public void setup() throws IOException {
		super.setup();
	}

	@Test
	public void testGetCasidusoByIdRuolo() {
		try {
			EpaypaTCduDao dao = new EpaypaTCduDaoImpl();
			setEntityManager(dao);

			Integer idRuolo = 1;
			
			transaction.begin();
			List<EpaypaTCdu> entityList = dao.findAllByIdRuolo(idRuolo);
			transaction.commit();

			List<CduDto> dtoList = Util.toCduDtoList(entityList);
			System.out.println(">> " + dtoList);
			
			Assert.assertEquals(3, dtoList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
