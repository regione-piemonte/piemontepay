/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaybeapi.integration.domain.EpayTPagamentoComponentiStorico;
import it.csi.epay.epaybeapi.integration.dto.EpayTPagamentoComponentiStoricoDTO;
import it.csi.epay.epaybeapi.integration.repository.EpayTPagamentoComponentiStoricoRepository;
import it.csi.epay.epaybeapi.testbed.config.UnitTestDB;
import it.csi.epay.epaybeapi.testbed.model.ParentHttpCallTest;

/**
 * Spring mapper test for "EpayTPagamentoComponentiStorico" <br>
 *
 * @author EII
 */
@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { UnitTestDB.class } )
@Transactional
public class EpayTPagamentoComponentiStoricoMapperTest extends ParentHttpCallTest {

    @Autowired
    private EpayTPagamentoComponentiStoricoRepository repository;

    @Autowired
    private EpayTPagamentoComponentiStoricoMapper mapper;

    @Test
    public void mappingShouldWork () {
        Page<EpayTPagamentoComponentiStorico> records = repository.findAll( new PageRequest ( 0, 10 ) );
        assertNotNull( records );
        
        for ( EpayTPagamentoComponentiStorico record : records ) {
        	EpayTPagamentoComponentiStoricoDTO dto = mapper.toDTO( record );
        	assertNotNull( dto );
        	
        }
		
		List<EpayTPagamentoComponentiStoricoDTO> dtos = mapper.toDTO( records );
		assertNotNull( dtos );
		
		assertEquals( records.getNumberOfElements(), dtos.size() );
    }

}
