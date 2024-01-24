/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.csi.epay.epaypaweb.dto.CriterioOrdinamentoDto;
import it.csi.epay.epaypaweb.dto.FlussoLightDto;
import it.csi.epay.epaypaweb.dto.FlussoLightFilterDto;
import it.csi.epay.epaypaweb.dto.PaginazioneDto;
import it.csi.epay.epaypaweb.enumeration.ColumnNameFlussoEnum;
import it.csi.epay.epaypaweb.persistence.dao.impl.EpaypaTFlussoLightDaoImpl;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTFlussoLightDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTFlussoLight;

public class EpaypaTFlussoLightDaoTest extends DaoBaseTest {

	@Before
	public void setup() throws IOException {
		super.setup();
	}

	@Test
	public void testFindOneFlussoLight() {
		try {
			EpaypaTFlussoLightDao dao = new EpaypaTFlussoLightDaoImpl();
			setEntityManager(dao);

			Long idFlusso = 1L;
			System.out.println(">> idFlusso: " + idFlusso);

			transaction.begin();
			EpaypaTFlussoLight entity = dao.findOne(idFlusso);
			FlussoLightDto dto = Util.toFlussoLightDto(entity);
			transaction.commit();

			System.out.println(">> dto: " + dto);

			Assert.assertEquals(dto != null, true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// @Test
	public void testFindAllFlussoLightByFilter() {
		try {
			EpaypaTFlussoLightDao dao = new EpaypaTFlussoLightDaoImpl();
			setEntityManager(dao);
			
			FlussoLightFilterDto filter = new FlussoLightFilterDto();
			filter.setPagamentiSpontanei(false);
//			filter.setIdCodVersamento(1);
//			filter.setDataInserimentoDa(new GregorianCalendar(2017, GregorianCalendar.JANUARY, 19).getTime());
			filter.setDataInserimentoA(new GregorianCalendar(2017, GregorianCalendar.JANUARY, 18).getTime());
			System.out.println(">> filter: " + filter);
			//
			List<CriterioOrdinamentoDto<ColumnNameFlussoEnum>> ord = new ArrayList<CriterioOrdinamentoDto<ColumnNameFlussoEnum>>();
//			ord.add(new CriterioOrdinamentoDto<ColumnNameFlussoEnum>(ColumnNameFlussoEnum.ID_FLUSSO));
//			ord.add(new CriterioOrdinamentoDto<ColumnNameFlussoEnum>(ColumnNameFlussoEnum.ID_CODICE_VERSAMENTO));
//			ord.add(new CriterioOrdinamentoDto<ColumnNameFlussoEnum>(ColumnNameFlussoEnum.NUMERO_ELEMENTI));
			ord.add(new CriterioOrdinamentoDto<ColumnNameFlussoEnum>(ColumnNameFlussoEnum.IMPORTO_TOTALE));
//			ord.add(new CriterioOrdinamentoDto<ColumnNameFlussoEnum>(ColumnNameFlussoEnum.PAGAMENTI_SPONTANEI));
//			ord.add(new CriterioOrdinamentoDto<ColumnNameFlussoEnum>(ColumnNameFlussoEnum.DATA_INSERIMENTO));
			System.out.println(">> ord: " + ord);
			//
			PaginazioneDto pag = new PaginazioneDto(2, 5);
			System.out.println(">> pag: " + pag);
	
			transaction.begin();
			List<EpaypaTFlussoLight> entityList = dao.findAllLightByFilter(filter, ord, pag, null);
			List<FlussoLightDto> dtoList = Util.toFlussoLightDtoList(entityList);
			transaction.commit();

			System.out.println(">> dtoList: " + dtoList);
			
			Assert.assertEquals(dtoList.isEmpty(), false);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
