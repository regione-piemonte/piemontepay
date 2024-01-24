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

import it.csi.epay.epaypaweb.dto.ProfiloDto;
import it.csi.epay.epaypaweb.persistence.dao.impl.EpaypaTProfiloDaoImpl;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTProfiloDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTProfilo;

public class EpaypaTProfiloDaoTest extends DaoBaseTest {

	@Before
	public void setup() throws IOException {
		super.setup();
	}

	@Test
	public void testGetProfiliByEnteAndIdEnte() {
		try {
			EpaypaTProfiloDao dao = new EpaypaTProfiloDaoImpl();
			setEntityManager(dao);

			Long idUtente = 1L;
			Integer idEnte = 2;

			transaction.begin();
			List<EpaypaTProfilo> entityList = dao.findAllByIdUtenteAndIdEnte(idUtente, idEnte);
			transaction.commit();

			List<ProfiloDto> dtoList = Util.toProfiloDtoList(entityList);
			System.out.println(">> " + dtoList);

			Assert.assertEquals(1, dtoList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
