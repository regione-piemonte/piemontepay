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

import it.csi.epay.epaypaweb.dto.CriterioOrdinamentoDto;
import it.csi.epay.epaypaweb.dto.NotificaPagamentoLightDto;
import it.csi.epay.epaypaweb.dto.PaginazioneDto;
import it.csi.epay.epaypaweb.enumeration.ColumnNameNotificaPagamentoEnum;
import it.csi.epay.epaypaweb.enumeration.SortTypeEnum;
import it.csi.epay.epaypaweb.persistence.dao.impl.EpaypaTNotificaPagamentoLightDaoImpl;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTNotificaPagamentoLightDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTNotificaPagamentoLight;

public class EpaypaTNotificaPagamentoLightDaoTest extends DaoBaseTest {

	@Before
	public void setup() throws IOException {
		super.setup();
	}

	@Test
	public void testFindAllNotificaPagamentoLightByIdFlusso() {
		try {
			EpaypaTNotificaPagamentoLightDao dao = new EpaypaTNotificaPagamentoLightDaoImpl();
			setEntityManager(dao);

			Long idFlusso = 33L;
			System.out.println(">> idFlusso: " + idFlusso);
			//
			List<CriterioOrdinamentoDto<ColumnNameNotificaPagamentoEnum>> ord = new ArrayList<CriterioOrdinamentoDto<ColumnNameNotificaPagamentoEnum>>();
			ord.add(new CriterioOrdinamentoDto<ColumnNameNotificaPagamentoEnum>(ColumnNameNotificaPagamentoEnum.ID_NOTIFICA_PAGAMENTO, SortTypeEnum.DESC));
			ord.add(new CriterioOrdinamentoDto<ColumnNameNotificaPagamentoEnum>(ColumnNameNotificaPagamentoEnum.ID_FLUSSO, SortTypeEnum.ASC));
			ord.add(new CriterioOrdinamentoDto<ColumnNameNotificaPagamentoEnum>(ColumnNameNotificaPagamentoEnum.ID_POSIZIONE_DEBITORIA, SortTypeEnum.DESC));
			ord.add(new CriterioOrdinamentoDto<ColumnNameNotificaPagamentoEnum>(ColumnNameNotificaPagamentoEnum.IMPORTO_PAGATO, SortTypeEnum.ASC));
			ord.add(new CriterioOrdinamentoDto<ColumnNameNotificaPagamentoEnum>(ColumnNameNotificaPagamentoEnum.DESCRIZIONE_CAUSALE_VERSAMENTO, SortTypeEnum.DESC));
			ord.add(new CriterioOrdinamentoDto<ColumnNameNotificaPagamentoEnum>(ColumnNameNotificaPagamentoEnum.DATA_ESITO_PAGAMENTO, SortTypeEnum.ASC));
			ord.add(new CriterioOrdinamentoDto<ColumnNameNotificaPagamentoEnum>(ColumnNameNotificaPagamentoEnum.ID_FISCALE_DEBITORE, SortTypeEnum.DESC));
			System.out.println(">> ord: " + ord);
			//
			PaginazioneDto pag = new PaginazioneDto(2, 2);
			System.out.println(">> pag: " + pag);

			transaction.begin();
			List<EpaypaTNotificaPagamentoLight> entityList = dao.findAllLightByIdFlusso(idFlusso, ord, pag);
			List<NotificaPagamentoLightDto> dtoList = Util.toNotificaPagamentoLightDtoList(entityList);
			transaction.commit();

			System.out.println(">> dtoList: " + dtoList);

			Assert.assertEquals(dtoList.isEmpty(), false);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
