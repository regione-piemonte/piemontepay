/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper;

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

import it.csi.epay.epayapi.integration.domain.EpayTEntiLog;
import it.csi.epay.epayapi.integration.dto.EpayTEntiLogDTO;
import it.csi.epay.epayapi.integration.repository.EpayTEntiLogRepository;
import it.csi.epay.epayapi.testbed.config.UnitTestDB;
import it.csi.epay.epayapi.testbed.model.ParentHttpCallTest;

/**
 * Spring mapper test for "EpayTEntiLog" <br>
 *
 * @author fabio.fenoglio
 */
@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { UnitTestDB.class } )
@Transactional
public class EpayTEntiLogMapperTest extends ParentHttpCallTest {

    @Autowired
    private EpayTEntiLogRepository repository;

    @Autowired
    private EpayTEntiLogMapper mapper;

    @Test
    public void mappingShouldWork () {
        Page<EpayTEntiLog> records = repository.findAll( new PageRequest ( 0, 10 ) );
        assertNotNull( records );
        
        for ( EpayTEntiLog record : records ) {
        	EpayTEntiLogDTO dto = mapper.toDTO( record );
        	assertNotNull( dto );
        }
		
		List<EpayTEntiLogDTO> dtos = mapper.toDTO( records );
		assertNotNull( dtos );
		
		assertEquals( records.getNumberOfElements(), dtos.size() );
    }

}
