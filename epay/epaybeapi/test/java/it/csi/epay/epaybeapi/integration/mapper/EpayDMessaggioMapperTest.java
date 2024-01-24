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

import it.csi.epay.epaybeapi.integration.domain.EpayDMessaggio;
import it.csi.epay.epaybeapi.integration.dto.EpayDMessaggioDTO;
import it.csi.epay.epaybeapi.integration.repository.EpayDMessaggioRepository;
import it.csi.epay.epaybeapi.testbed.config.UnitTestDB;
import it.csi.epay.epaybeapi.testbed.model.ParentHttpCallTest;

/**
 * Spring mapper test for "EpayDMessaggio" <br>
 *
 * @author EII
 */
@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { UnitTestDB.class } )
@Transactional
public class EpayDMessaggioMapperTest extends ParentHttpCallTest {

    @Autowired
    private EpayDMessaggioRepository repository;

    @Autowired
    private EpayDMessaggioMapper mapper;

    @Test
    public void mappingShouldWork () {
        Page<EpayDMessaggio> records = repository.findAll( new PageRequest ( 0, 10 ) );
        assertNotNull( records );
        
        for ( EpayDMessaggio record : records ) {
        	EpayDMessaggioDTO dto = mapper.toDTO( record );
        	assertNotNull( dto );
        }
		
		List<EpayDMessaggioDTO> dtos = mapper.toDTO( records );
		assertNotNull( dtos );
		
		assertEquals( records.getNumberOfElements(), dtos.size() );
    }

}
