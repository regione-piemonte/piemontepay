/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.repository;

import it.csi.epay.epaybeapi.integration.domain.EpayTTipoPagamento;
import it.csi.epay.epaybeapi.integration.repository.EpayTTipoPagamentoRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.apache.commons.collections.IteratorUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaybeapi.testbed.config.UnitTestDB;
import it.csi.epay.epaybeapi.testbed.model.ParentHttpCallTest;

/**
 * Spring data Jpa repository test for "EpayTTipoPagamento" <br>
 *
 * @author EII
 */

@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { UnitTestDB.class } )
@Transactional
public class EpayTTipoPagamentoRepositoryTest extends ParentHttpCallTest {

    @Autowired
    private EpayTTipoPagamentoRepository repository;

    @Test
    public void countShouldWork () {
        
		repository.count();
    }
    
    @Test
    public void findByEpayTEntiIdEnteAndCodiceVersamentoShouldWork() {
    	
    	Page<EpayTTipoPagamento> records = repository.findAll( new PageRequest ( 0, 5 ) );
        assertNotNull( records );
        
        EpayTTipoPagamento tp = (EpayTTipoPagamento)IteratorUtils.toList(records.iterator()).get(0);
        String cv = tp.getCodiceVersamento();
    	Long idEnte = tp.getEpayTEnti().getIdEnte();
    	EpayTTipoPagamento epayTTipoPagamento = repository.findByEpayTEntiIdEnteAndCodiceVersamento(idEnte, cv);
    	
    	assertNotNull(epayTTipoPagamento);
    	
    	assertEquals(cv, epayTTipoPagamento.getCodiceVersamento());
    	assertEquals(idEnte,epayTTipoPagamento.getEpayTEnti().getIdEnte());
    }

    @Test
    public void findByEpayTEntiIdEnteAndCodiceVersamentoShouldNotWork() {
    	String cv = "UNK";
    	Long idEnte = 978L;
    	EpayTTipoPagamento epayTTipoPagamento = repository.findByEpayTEntiIdEnteAndCodiceVersamento(idEnte, cv);
    	
    	assertNull(epayTTipoPagamento);
    	
    }
}
