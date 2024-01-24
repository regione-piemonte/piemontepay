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

import it.csi.epay.epaypaweb.dto.CodiceVersamentoDto;
import it.csi.epay.epaypaweb.persistence.dao.impl.EpaypaTCodiceVersamentoDaoImpl;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTCodiceVersamentoDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTCodiceVersamento;

public class EpaypaTCodiceVersamentoDaoTest extends DaoBaseTest {

	@Before
	public void setup() throws IOException {
		super.setup();
	}

	@Test
	public void testGetCodiciVersamentoByIdProfiloAndIdEnte() {
		try {
			EpaypaTCodiceVersamentoDao dao = new EpaypaTCodiceVersamentoDaoImpl();
			setEntityManager(dao);

			Integer idProfilo = 3;
			Integer idEnte = 2;

			transaction.begin();
			List<EpaypaTCodiceVersamento> entityList = dao.findAllByIdProfiloAndIdEnte(idProfilo, idEnte);
			transaction.commit();

			List<CodiceVersamentoDto> dtoList = Util.toCodiceVersamentoDtoList(entityList);
			System.out.println(">> " + dtoList);
			
			Assert.assertEquals(2, dtoList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
