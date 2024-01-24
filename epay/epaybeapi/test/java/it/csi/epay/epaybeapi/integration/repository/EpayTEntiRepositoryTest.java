/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.repository;

import it.csi.epay.epaybeapi.integration.domain.EpayTEnti;
import it.csi.epay.epaybeapi.integration.repository.EpayTEntiRepository;

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
 * Spring data Jpa repository test for "EpayTEnti" <br>
 *
 * @author EII
 */

@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { UnitTestDB.class } )
@Transactional
public class EpayTEntiRepositoryTest extends ParentHttpCallTest {

    @Autowired
    private EpayTEntiRepository repository;

    @Test
    public void countShouldWork () {
        
		repository.count();
    }
    
    @Test
    public void findByCodiceFiscaleShouldWork () {
    	Page<EpayTEnti> records = repository.findAll( new PageRequest ( 0, 10 ) );
        assertNotNull( records );
        String cf = ((EpayTEnti) IteratorUtils.toList(records.iterator()).get(0)).getCodiceFiscale();
    	EpayTEnti ente = repository.findByCodiceFiscale(cf);
    	assertNotNull(ente);
    	assertEquals(ente.getCodiceFiscale(),cf);	
    }
    
    @Test
    public void findByCodiceFiscaleShouldNotWork () {
    	String cf = "UNK";
    	EpayTEnti ente = repository.findByCodiceFiscale(cf);
    	
    	assertNull(ente);
    }

}
