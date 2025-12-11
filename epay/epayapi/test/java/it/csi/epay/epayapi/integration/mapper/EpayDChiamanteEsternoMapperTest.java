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

import it.csi.epay.epayapi.integration.domain.EpayDChiamanteEsterno;
import it.csi.epay.epayapi.integration.dto.EpayDChiamanteEsternoDTO;
import it.csi.epay.epayapi.integration.repository.EpayDChiamanteEsternoRepository;
import it.csi.epay.epayapi.testbed.config.UnitTestDB;
import it.csi.epay.epayapi.testbed.model.ParentHttpCallTest;

/**
 * Spring mapper test for "EpayDChiamanteEsterno" <br>
 *
 * @author fabio.fenoglio
 */
@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { UnitTestDB.class } )
@Transactional
public class EpayDChiamanteEsternoMapperTest extends ParentHttpCallTest {

    @Autowired
    private EpayDChiamanteEsternoRepository repository;

    @Autowired
    private EpayDChiamanteEsternoMapper mapper;

    @Test
    public void mappingShouldWork () {
        Page<EpayDChiamanteEsterno> records = repository.findAll( new PageRequest ( 0, 10 ) );
        assertNotNull( records );
        
        for ( EpayDChiamanteEsterno record : records ) {
        	EpayDChiamanteEsternoDTO dto = mapper.toDTO( record );
        	assertNotNull( dto );
        	
        }
		
		List<EpayDChiamanteEsternoDTO> dtos = mapper.toDTO( records );
		assertNotNull( dtos );
		
		assertEquals( records.getNumberOfElements(), dtos.size() );
    }

}
