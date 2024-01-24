/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.test;

import it.csi.epay.epaypaweb.dto.ComponenteImportoDto;
import it.csi.epay.epaypaweb.dto.FlussoCompletoDto;
import it.csi.epay.epaypaweb.dto.FlussoDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaDto;
import it.csi.epay.epaypaweb.dto.SoggettoAnagraficoDto;
import it.csi.epay.epaypaweb.enumeration.StatoFlussoEnum;
import it.csi.epay.epaypaweb.enumeration.TipologiaSoggettoEnum;
import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dad.impl.GestioneFlussiDadImpl;
import it.csi.epay.epaypaweb.persistence.dad.interf.GestioneFlussiDad;
import it.csi.epay.epaypaweb.persistence.dao.impl.EpaypaDStatoFlussoDaoImpl;
import it.csi.epay.epaypaweb.persistence.dao.impl.EpaypaDTipoFlussoDaoImpl;
import it.csi.epay.epaypaweb.persistence.dao.impl.EpaypaTCodiceVersamentoDaoImpl;
import it.csi.epay.epaypaweb.persistence.dao.impl.EpaypaTEnteDaoImpl;
import it.csi.epay.epaypaweb.persistence.dao.impl.EpaypaTFlussoDaoImpl;
import it.csi.epay.epaypaweb.persistence.dao.impl.EpaypaTPosizioneDebitoriaDaoImpl;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaDStatoFlussoDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaDTipoFlussoDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTCodiceVersamentoDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTEnteDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTFlussoDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTPosizioneDebitoriaDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTPosizioneDebitoria;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class _EpaypaTPosizioneDebitoriaDaoTest extends DaoBaseTest {

	@Before
	public void setup() throws IOException {
		super.setup();
	}

	// @Test
	public void testCountAllPosizioneDebitoriaByIdFlusso() {
		try {
			EpaypaTPosizioneDebitoriaDao dao = new EpaypaTPosizioneDebitoriaDaoImpl();
			setEntityManager(dao);

			Long idFlusso = 54L;
			System.out.println(">> idFlusso: " + idFlusso);

			transaction.begin();
			Long num = dao.countAllByIdFlusso(idFlusso);
			transaction.commit();

			System.out.println(">> " + num);

			Assert.assertEquals(2, num.longValue());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @Test
	public void testFindAllFlussoLightByIdFlusso() {
		try {
			EpaypaTPosizioneDebitoriaDao dao = new EpaypaTPosizioneDebitoriaDaoImpl();
			setEntityManager(dao);

			Long idFlusso = 54L;
			System.out.println(">> idFlusso: " + idFlusso);

			transaction.begin();
			List<EpaypaTPosizioneDebitoria> entityList = dao.findAllByIdFlusso(idFlusso);
			List<PosizioneDebitoriaDto> dtoList = Util.toPosizioneDebitoriaDtoList(entityList);
			transaction.commit();

			System.out.println(">> dtoList: " + dtoList);

			Assert.assertEquals(dtoList.isEmpty(), false);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @Test
	public void testFindOnePosizioneDebitoria() {
		try {
			EpaypaTPosizioneDebitoriaDao dao = new EpaypaTPosizioneDebitoriaDaoImpl();
			setEntityManager(dao);

			Long idPosizioneDebitoria = 1L;
			System.out.println(">> idPosizioneDebitoria: " + idPosizioneDebitoria);

			transaction.begin();
			EpaypaTPosizioneDebitoria entity = dao.findOne(idPosizioneDebitoria);
			PosizioneDebitoriaDto dto = Util.toPosizioneDebitoriaDto(entity);
			transaction.commit();

			System.out.println(">> dto: " + dto);

			Assert.assertEquals(dto != null, true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @Test
	public void testFindFlussoPosizioniDebitorie() {
		EpaypaTPosizioneDebitoriaDao epaypaTPosizioneDebitoriaDao = new EpaypaTPosizioneDebitoriaDaoImpl();
		EpaypaTFlussoDao epaypaTFlussoDao = new EpaypaTFlussoDaoImpl();

		// inizializzazione della persistenza
		try {
			setEntityManager(epaypaTPosizioneDebitoriaDao);
			setEntityManager(epaypaTFlussoDao);

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}

		// lettura della posizione debitoria
		try {
			Long idFlusso = 6L;
			System.out.println(">> idFlusso: " + idFlusso);

			FlussoCompletoDto<PosizioneDebitoriaDto> dto = null;

			transaction.begin();
			List<EpaypaTPosizioneDebitoria> entityList = epaypaTPosizioneDebitoriaDao.findAllByIdFlusso(idFlusso);
			if (entityList != null) {
				List<PosizioneDebitoriaDto> dtoList = new ArrayList<PosizioneDebitoriaDto>();

				if (entityList.isEmpty()) {
					dto = new FlussoCompletoDto<PosizioneDebitoriaDto>();
					dto.setFlusso(Util.toFlussoDto(epaypaTFlussoDao.findOne(idFlusso)));

				} else {
					for (EpaypaTPosizioneDebitoria entity : entityList) {
						if (dto == null) {
							dto = new FlussoCompletoDto<PosizioneDebitoriaDto>();
							dto.setFlusso(Util.toFlussoDto(entity.getEpaypaTFlusso()));
						}
						dtoList.add(Util.toPosizioneDebitoriaDto(entity));
					}
				}
				dto.setItemList(dtoList);
			}
			transaction.commit();

			System.out.println(">> dto: " + dto);

			Assert.assertEquals(dto != null, true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void completeCycleTest() {

		Long idFlusso = null;
		BigDecimal importoTotale = new BigDecimal(12736.0);
		EpaypaTEnteDao epaypaTEnteDao = null;
		EpaypaTFlussoDao epaypaTFlussoDao = null;
		EpaypaDTipoFlussoDao epaypaDTipoFlussoDao = null;
		EpaypaDStatoFlussoDao epaypaDStatoFlussoDao = null;
		EpaypaTCodiceVersamentoDao epaypaTCodiceVersamentoDao = null;
		EpaypaTPosizioneDebitoriaDao epaypaTPosizioneDebitoriaDao = null;

		GestioneFlussiDad dad = new GestioneFlussiDadImpl();

		// inizializzazione della persistenza
		try {
			setEntityManager(epaypaTEnteDao = new EpaypaTEnteDaoImpl());
			setEntityManager(epaypaDTipoFlussoDao = new EpaypaDTipoFlussoDaoImpl());
			setEntityManager(epaypaDStatoFlussoDao = new EpaypaDStatoFlussoDaoImpl());
			setEntityManager(epaypaTCodiceVersamentoDao = new EpaypaTCodiceVersamentoDaoImpl());
			setEntityManager(epaypaTFlussoDao = new EpaypaTFlussoDaoImpl());
			setEntityManager(epaypaTPosizioneDebitoriaDao = new EpaypaTPosizioneDebitoriaDaoImpl());

			setDao(dad, epaypaDTipoFlussoDao);
			setDao(dad, epaypaDStatoFlussoDao);
			setDao(dad, epaypaTEnteDao);
			setDao(dad, epaypaTCodiceVersamentoDao);
			setDao(dad, epaypaTFlussoDao);
			setDao(dad, epaypaTPosizioneDebitoriaDao);

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}

		// inserimento flusso
		try {
			for (int i = 0; i < 9; i++) {
				FlussoCompletoDto<PosizioneDebitoriaDto> flussoPosdeb = new FlussoCompletoDto<PosizioneDebitoriaDto>();
				FlussoDto flussoDto = new FlussoDto();

				flussoDto.setStatoFlusso(StatoFlussoEnum.ACQUISITO);
				flussoDto.setCodFiscaleEnte("00429440068");
				flussoDto.setCodVersamento("BI01");
				flussoDto.setDenominazioneEnte("Alessandria (JUNIT test)");
				flussoDto.setIdMessaggio(UUID.randomUUID().toString().substring(0, 35));
				flussoDto.setImportoTotale(importoTotale);
				flussoDto.setNumeroElementi(2);
				flussoDto.setPagamentiSpontanei(false);
				flussoDto.setUtenteInserimento("JUNIT");
				flussoDto.setUtenteUltimaVariazione("JUNIT");
				//
				flussoPosdeb.setFlusso(flussoDto);

				PosizioneDebitoriaDto posdeb1 = new PosizioneDebitoriaDto();
				posdeb1.setIUV("IUV TEST 1");
				posdeb1.setImportoTotale(new BigDecimal(3.6));
				posdeb1.setDesCausaleVersamento("descrizione causale di versamento uno");
				posdeb1.setDataScadenza(new Date());
				//
				posdeb1.setIdPosizioneDebitoriaEsterna("posizione debitoria estesa uno");
				posdeb1.setAnnoRiferimento(2017);
				posdeb1.setDataInizioValidita(new Date());
				posdeb1.setDataFineValidita(new Date());
				posdeb1.setDesRata("rata 1");
				posdeb1.setNotePerPagatore("note uno");
				posdeb1.setCodAvviso("codice avviso uno");
				posdeb1.setCodEsito("ok");
				posdeb1.setDetEsito("okay");
				//
				SoggettoAnagraficoDto sogdeb1 = new SoggettoAnagraficoDto();
				sogdeb1.setIdUnivocoFiscale("MRORSS69R19A182R");
				sogdeb1.setTipologiaSoggettoEnum(TipologiaSoggettoEnum.PERSONA_FISICA);
				sogdeb1.setCognome("Rossi");
				sogdeb1.setNome("Mario");
				sogdeb1.setIndirizzo("Piazza Verdi");
				sogdeb1.setCivico("1");
				sogdeb1.setCap("15121");
				sogdeb1.setLocalita("Alessandria");
				sogdeb1.setProvincia("AL");
				sogdeb1.setNazione("IT");
				sogdeb1.setEmail("mario.rossi9808@gmail.com");
				posdeb1.setSoggettoDebitore(sogdeb1);
				//
				ComponenteImportoDto comimp11 = new ComponenteImportoDto();
				comimp11.setImporto(new BigDecimal(1.1));
				comimp11.setCausale("causale 1.1");
				comimp11.setDatiSpecificiRiscossione("dati spec. 1.1");
                comimp11.setAnnoAccertamento ( 2017 );
                comimp11.setNumeroAccertamento ( 11 );
				ComponenteImportoDto comimp12 = new ComponenteImportoDto();
				comimp12.setImporto(new BigDecimal(1.2));
				comimp12.setCausale("causale 1.2");
				comimp12.setDatiSpecificiRiscossione("dati spec. 1.2");
				comimp12.setAnnoAccertamento ( 2017 );
				comimp12.setNumeroAccertamento ( 12 );
				ComponenteImportoDto comimp13 = new ComponenteImportoDto();
				comimp13.setImporto(new BigDecimal(1.3));
				comimp13.setCausale("causale 1.3");
				comimp13.setDatiSpecificiRiscossione("dati spec. 1.3");
				comimp13.setAnnoAccertamento ( 2017 );
				comimp13.setNumeroAccertamento ( 13 );
				List<ComponenteImportoDto> listaComponentiImporto1 = new ArrayList<ComponenteImportoDto>();
				listaComponentiImporto1.add(comimp11);
				listaComponentiImporto1.add(comimp12);
				listaComponentiImporto1.add(comimp13);
				//
				posdeb1.setComponenteImportoDtoList(listaComponentiImporto1);

				PosizioneDebitoriaDto posdeb2 = new PosizioneDebitoriaDto();
				posdeb2.setIUV("IUV TEST 2");
				posdeb2.setImportoTotale(new BigDecimal(12732.4));
				posdeb2.setDesCausaleVersamento("descrizione causale di versamento due");
				posdeb2.setDataScadenza(new Date());
				//
				posdeb2.setIdPosizioneDebitoriaEsterna("posizione debitoria estesa due");
				posdeb2.setAnnoRiferimento(2017);
				posdeb2.setDataInizioValidita(new Date());
				posdeb2.setDataFineValidita(new Date());
				posdeb2.setDesRata("rata 2");
				posdeb2.setNotePerPagatore("note due");
				posdeb2.setCodEsito("ok");
				posdeb2.setDetEsito("okay");
				//
				SoggettoAnagraficoDto sogdeb2 = new SoggettoAnagraficoDto();
				sogdeb2.setIdUnivocoFiscale("MRABNC48R30C182R");
				sogdeb2.setTipologiaSoggettoEnum(TipologiaSoggettoEnum.PERSONA_FISICA);
				sogdeb2.setCognome("Bianchi");
				sogdeb2.setNome("Maria");
				sogdeb2.setIndirizzo("Piazza Blu");
				sogdeb2.setCivico("2");
				sogdeb2.setCap("15121");
				sogdeb2.setLocalita("Alessandria");
				sogdeb2.setProvincia("AL");
				sogdeb2.setNazione("IT");
				sogdeb2.setEmail("maria.bianchi1234@gmail.com");
				posdeb2.setSoggettoDebitore(sogdeb2);

				List<PosizioneDebitoriaDto> listaPosdeb = new ArrayList<PosizioneDebitoriaDto>();
				listaPosdeb.add(posdeb1);
				listaPosdeb.add(posdeb2);
				flussoPosdeb.setItemList(listaPosdeb);

				transaction.begin();
				idFlusso = dad.insertFlussoPosizioniDebitorie(flussoPosdeb, new Timestamp(System.currentTimeMillis()));
				transaction.commit();

				System.out.println("Inserito flusso con ID:" + idFlusso);
			}
		} catch (PersistenceException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}

		// Lettura Flusso DAO
		try {
			epaypaTFlussoDao.findOne(idFlusso);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}

		// Lettura Flusso
		FlussoCompletoDto<PosizioneDebitoriaDto> flussoCompletoReadDto = null;
		try {
			flussoCompletoReadDto = dad.findFlussoPosizioniDebitorie(idFlusso);
			Assert.assertEquals(importoTotale, flussoCompletoReadDto.getFlusso().getImportoTotale());
		} catch (PersistenceException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}

		// Aggiorna Flusso
		try {
			System.out.println("----------> UPDATE FLUSSO COMPLETO - BEGIN");
			System.out.println("idFlusso:" + idFlusso);
			FlussoDto flussoUpdDto = new FlussoDto(idFlusso);
			flussoUpdDto.setCodEsito("003");
			flussoUpdDto.setDetEsito("ok ok ok");
			//
			List<PosizioneDebitoriaDto> posdebUpdList = new ArrayList<PosizioneDebitoriaDto>();
			int i = 0;
			for (PosizioneDebitoriaDto posdebRead : flussoCompletoReadDto.getItemList()) {
				PosizioneDebitoriaDto posdebUpdDto = new PosizioneDebitoriaDto();
				posdebUpdDto.setIdPosizioneDebitoriaEsterna(posdebRead.getIdPosizioneDebitoriaEsterna());
				posdebUpdDto.setIUV((posdebRead.getIUV() != null ? posdebRead.getIUV() + "_UPD" : "UPD") + i);
				posdebUpdDto.setCodAvviso((posdebRead.getCodAvviso() != null ? posdebRead.getCodAvviso() + "_UPD" : "UPD") + i);
				posdebUpdDto.setCodEsito((posdebRead.getCodEsito() != null ? posdebRead.getCodEsito() + "_UPD" : "UPD") + i);
				posdebUpdDto.setDetEsito((posdebRead.getDetEsito() != null ? posdebRead.getDetEsito() + "_UPD" : "UPD") + i);
				//
				posdebUpdList.add(posdebUpdDto);
			}
			
			FlussoCompletoDto<PosizioneDebitoriaDto> flussoCompletoUpdDto = new FlussoCompletoDto<PosizioneDebitoriaDto>();
			flussoCompletoUpdDto.setFlusso(flussoUpdDto);
			flussoCompletoUpdDto.setItemList(posdebUpdList);
			
			transaction.begin();
			long numUpdates = dad.updateFlussoPosizioniDebitorie(flussoCompletoUpdDto, false);
			transaction.commit();

			Assert.assertEquals(numUpdates == posdebUpdList.size(), true);
			
			System.out.println("----------> UPDATE FLUSSO COMPLETO - END");

		} catch (PersistenceException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}

//		// Elimina Flusso
//		try {
//			transaction.begin();
//			dad.removeFlussoById(idFlusso);
//			transaction.commit();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			Assert.fail(e.getMessage());
//		}
	}
}
