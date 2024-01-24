/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.test;

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTPagamentiDao;
import it.csi.epay.epaypaweb.dto.CriterioOrdinamentoDto;

import it.csi.epay.epaypaweb.dto.PagamentiFilterDto;
import it.csi.epay.epaypaweb.dto.PaginazioneDto;
import it.csi.epay.epaypaweb.enumeration.ColumnNamePagamentiEnum;
import it.csi.epay.epaypaweb.enumeration.SortTypeEnum;

import it.csi.epay.epaypaweb.persistence.dao.impl.EpaypaTPagamentiDaoImpl;

import it.csi.epay.epaypaweb.persistence.entity.EpaypaTPagamenti;

public class EpaypaTPagamentiDaoTest extends DaoBaseTest {

	@Before
	public void setup() throws IOException {
		super.setup();
	}

	@Test
	public void testFindPagamenti() {
		try {
			EpaypaTPagamentiDao daoPagamenti = new EpaypaTPagamentiDaoImpl();

			PagamentiFilterDto filter = new PagamentiFilterDto() ;
			
			//
			List<CriterioOrdinamentoDto<ColumnNamePagamentiEnum>> ord = new ArrayList<CriterioOrdinamentoDto<ColumnNamePagamentiEnum>>();
			ord.add(new CriterioOrdinamentoDto<ColumnNamePagamentiEnum>(ColumnNamePagamentiEnum.IUV, SortTypeEnum.DESC));
//			ord.add(new CriterioOrdinamentoDto<ColumnNamePagamentiEnum>(ColumnNamePagamentiEnum.DESCRIZIONE_CAUSALE_VERSAMENTO, SortTypeEnum.DESC));
//			ord.add(new CriterioOrdinamentoDto<ColumnNamePagamentiEnum>(ColumnNamePagamentiEnum.IMPORTO_DOVUTO, SortTypeEnum.ASC));
//			ord.add(new CriterioOrdinamentoDto<ColumnNamePagamentiEnum>(ColumnNamePagamentiEnum.IMPORTO_PAGATO, SortTypeEnum.ASC));
//			ord.add(new CriterioOrdinamentoDto<ColumnNamePagamentiEnum>(ColumnNamePagamentiEnum.ID_FLUSSO, SortTypeEnum.ASC));

			System.out.println(">> ord: " + ord);
			//
			PaginazioneDto pag = new PaginazioneDto(2, 2);
			System.out.println(">> pag: " + pag);
			//filter.setCodiceFiscale(null);
			filter.setPagamentiSpontanei(false);
			//filter.setCognome(cognome);
			filter.setIuv("1111");
			//filter.setDataInserimentoDa(dataInserimentoDa);
			//filter.setDataInserimentoA(dataInserimentoA);
//			transaction.begin();
//			EpaypaTFlusso entity = dao.findOne(idFlusso);
//			FlussoDto dto = Util.toFlussoDto(entity);
//			transaction.commit();
			List<EpaypaTPagamenti> entityList = daoPagamenti.findAllLightByFilter(filter, ord, pag, "");

			System.out.println(">> entityList: " + entityList);
			Assert.assertEquals(entityList.isEmpty(), false);
			Assert.assertEquals(entityList != null, true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//@Test
//	public void testCountAllFlusso() {
//		try {
//			EpaypaTFlussoDao dao = new EpaypaTFlussoDaoImpl();
//			setEntityManager(dao);
//			
//			FlussoLightFilterDto filter = new FlussoLightFilterDto();
//			System.out.println(">> filter: " + filter);
//	
//			transaction.begin();
//			Long num = dao.countAllByFilter(filter, null);
//			transaction.commit();
//
//			System.out.println(">> " + num);
//			
//			Assert.assertEquals(21, num.longValue());
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

//	//@Test
//	public void testCountFlussoByFilter() {
//		try {
//			EpaypaTFlussoDao dao = new EpaypaTFlussoDaoImpl();
//			setEntityManager(dao);
//			
//			FlussoLightFilterDto filter = new FlussoLightFilterDto();
//			filter.setPagamentiSpontanei(false);
////			filter.setIdCodVersamento(1);
////			filter.setDataInserimentoDa(new GregorianCalendar(2017, GregorianCalendar.JANUARY, 19).getTime());
//			filter.setDataInserimentoA(new GregorianCalendar(2017, GregorianCalendar.JANUARY, 18).getTime());
//			System.out.println(">> filter: " + filter);
//	
//			transaction.begin();
//			Long num = dao.countAllByFilter(filter, null);
//			transaction.commit();
//
//			System.out.println(">> " + num);
//			
//			Assert.assertEquals(7, num.longValue());
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
}
