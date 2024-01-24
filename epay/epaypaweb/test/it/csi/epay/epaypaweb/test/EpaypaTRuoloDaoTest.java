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

import it.csi.epay.epaypaweb.dto.RuoloDto;
import it.csi.epay.epaypaweb.persistence.dao.impl.EpaypaTRuoloDaoImpl;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTRuoloDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTRuolo;

public class EpaypaTRuoloDaoTest extends DaoBaseTest {

	@Before
	public void setup() throws IOException {
		super.setup();
	}

	@Test
	public void testGetRuoliByEnteAndIdEnte() {
		try {
			EpaypaTRuoloDao dao = new EpaypaTRuoloDaoImpl();
			setEntityManager(dao);

			Long idUtente = 1L;
			Integer idEnte = 2;
			
			transaction.begin();
			List<EpaypaTRuolo> entityList = dao.findAllByIdUtenteAndIdEnte(idUtente, idEnte);
			transaction.commit();

			List<RuoloDto> dtoList = Util.toRuoloDtoList(entityList);
			System.out.println(">> " + dtoList);
			
			Assert.assertEquals(2, dtoList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
