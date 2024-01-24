/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.repository;

import it.csi.epay.epaybeapi.integration.domain.EpayTPdfReport;
import it.csi.epay.epaybeapi.integration.repository.EpayTPdfReportRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaybeapi.testbed.config.UnitTestDB;
import it.csi.epay.epaybeapi.testbed.model.ParentHttpCallTest;

/**
 * Spring data Jpa repository test for "EpayTPdfReport" <br>
 *
 * @author EII
 */

@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { UnitTestDB.class } )
@Transactional
public class EpayTPdfReportRepositoryTest extends ParentHttpCallTest {

    @Autowired
    private EpayTPdfReportRepository repository;

    @Test
    public void countShouldWork () {
        
		repository.count();
    }
    
    @Test
    public void findByCodiceShouldWork() {
    	
    	String codice = "AA01";
    	List<EpayTPdfReport> reports = repository.findByCodice(codice);
    	
    	assertNotNull(reports);
    	assertTrue(reports.size()==1);
    	assertEquals(codice, reports.get(0).getCodice());
    }
    
    @Test
    public void findByCodiceShouldNotWork() {
    	
    	String codice = "UNK";
    	List<EpayTPdfReport> reports = repository.findByCodice(codice);
    	
    	assertTrue(reports.size()==0);
    }

}
