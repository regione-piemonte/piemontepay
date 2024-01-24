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

import it.csi.epay.epaybeapi.integration.domain.EpayTRt;
import it.csi.epay.epaybeapi.integration.dto.EpayTRtDTO;
import it.csi.epay.epaybeapi.integration.repository.EpayTRtRepository;
import it.csi.epay.epaybeapi.testbed.config.UnitTestDB;
import it.csi.epay.epaybeapi.testbed.model.ParentHttpCallTest;

/**
 * Spring mapper test for "EpayTRt" <br>
 *
 * @author EII
 */
@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { UnitTestDB.class } )
@Transactional
public class EpayTRtMapperTest extends ParentHttpCallTest {

    @Autowired
    private EpayTRtRepository repository;

    @Autowired
    private EpayTRtMapper mapper;

    @Test
    public void mappingShouldWork () {
        Page<EpayTRt> records = repository.findAll( new PageRequest ( 0, 10 ) );
        assertNotNull( records );
        
        for ( EpayTRt record : records ) {
        	EpayTRtDTO dto = mapper.toDTO( record );
        	assertNotNull( dto );
        	
        }
		
		List<EpayTRtDTO> dtos = mapper.toDTO( records );
		assertNotNull( dtos );
		
		
		assertEquals( records.getNumberOfElements(), dtos.size() );
    }

}
