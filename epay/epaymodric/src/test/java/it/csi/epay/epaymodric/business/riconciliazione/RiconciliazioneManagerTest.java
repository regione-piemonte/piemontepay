/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.riconciliazione;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaymodric.business.epaymodric.manager.RiconciliazioneManager;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsMotoreDiRiconciliazione;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsMotoreDiRiconciliazione;
import it.csi.epay.epaymodric.interfacews.epaymodric.consts.CostantiErrori;
import it.csi.epay.epaymodric.test.config.EpaymodricUnitTestConfigH2;

@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { EpaymodricUnitTestConfigH2.class } )
@Transactional
public class RiconciliazioneManagerTest {
	
	@Autowired
	private RiconciliazioneManager riconciliazioneManager;
	
	@Test
	public void shouldNotFindSuitableInputParameters() {
		DTOInputWsMotoreDiRiconciliazione input = createBasicValidInput();
		input.setCodiceEnte(null);
		DTOOutputWsMotoreDiRiconciliazione  output = riconciliazioneManager.eseguiRieseguiElaborazione(input);
		
		assertEquals(CostantiErrori.WS_ESITO_KO_PARAM_REQUIRED, output.getEsito().getCodiceErrore());
		
	}
	
	@Test
	public void shouldNotFindSuitableInputParameters2() {
		DTOInputWsMotoreDiRiconciliazione input = createBasicValidInput();
		input.setIsRiesecuzione(null);
		DTOOutputWsMotoreDiRiconciliazione  output = riconciliazioneManager.eseguiRieseguiElaborazione(input);
		
		assertEquals(CostantiErrori.WS_ESITO_KO_PARAM_REQUIRED, output.getEsito().getCodiceErrore());
		
	}
	
	@Test
	public void shouldNotFindSuitableInputParameters3() {
		DTOInputWsMotoreDiRiconciliazione input = null;
		DTOOutputWsMotoreDiRiconciliazione  output = riconciliazioneManager.eseguiRieseguiElaborazione(input);
		
		assertEquals(CostantiErrori.WS_ESITO_KO_PARAM_REQUIRED, output.getEsito().getCodiceErrore());
		
	}
	
	@Test
	public void shouldNotExistElaborazione() {
		DTOInputWsMotoreDiRiconciliazione input = createBasicValidInputWithNonExistingData();
		DTOOutputWsMotoreDiRiconciliazione  output = riconciliazioneManager.eseguiRieseguiElaborazione(input);
		
		assertEquals(CostantiErrori.WS_ESITO_KO_DEFAULT, output.getEsito().getCodiceErrore());
		assertEquals(0, output.getListaErrori().size());
		
	}
	
	@Test
	public void shouldWork() {
		DTOInputWsMotoreDiRiconciliazione input = createBasicValidInput();
		DTOOutputWsMotoreDiRiconciliazione  output = riconciliazioneManager.eseguiRieseguiElaborazione(input);
		
		assertEquals(CostantiErrori.WS_ESITO_OK_DEFAULT, output.getEsito().getCodiceErrore());
		assertEquals(0, output.getListaErrori().size());
		
	}
	
	@Test
	public void shouldNotFindAnyDataToProcess() {
		DTOInputWsMotoreDiRiconciliazione input = createBasicValidInput();
		input.setCodiceEnte("0002");
		DTOOutputWsMotoreDiRiconciliazione  output = riconciliazioneManager.eseguiRieseguiElaborazione(input);
		
		assertEquals(CostantiErrori.WS_ESITO_OK_DEFAULT, output.getEsito().getCodiceErrore());
		assertEquals(0, output.getListaErrori().size());
	}
	
	private DTOInputWsMotoreDiRiconciliazione createBasicValidInput() {
		DTOInputWsMotoreDiRiconciliazione in = new DTOInputWsMotoreDiRiconciliazione();
		
		in.setCodiceEnte("0001");
		in.setIsRiesecuzione("false");
		
		return in;
	}
	
	private DTOInputWsMotoreDiRiconciliazione createBasicValidInputWithNonExistingData() {
		DTOInputWsMotoreDiRiconciliazione in = new DTOInputWsMotoreDiRiconciliazione();
		
		in.setCodiceEnte("9001");
		in.setIsRiesecuzione("false");
		
		return in;
	}

}
