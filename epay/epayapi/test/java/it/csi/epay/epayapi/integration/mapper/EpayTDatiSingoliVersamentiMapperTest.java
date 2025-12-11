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

import it.csi.epay.epayapi.integration.domain.EpayTDatiSingoliVersamenti;
import it.csi.epay.epayapi.integration.dto.EpayTDatiSingoliVersamentiDTO;
import it.csi.epay.epayapi.integration.repository.EpayTDatiSingoliVersamentiRepository;
import it.csi.epay.epayapi.testbed.config.UnitTestDB;
import it.csi.epay.epayapi.testbed.model.ParentHttpCallTest;

/**
 * Spring mapper test for "EpayTDatiSingoliVersamenti" <br>
 *
 * @author fabio.fenoglio
 */
@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { UnitTestDB.class } )
@Transactional
public class EpayTDatiSingoliVersamentiMapperTest extends ParentHttpCallTest {

    @Autowired
    private EpayTDatiSingoliVersamentiRepository repository;

    @Autowired
    private EpayTDatiSingoliVersamentiMapper mapper;

    @Test
    public void mappingShouldWork () {
        Page<EpayTDatiSingoliVersamenti> records = repository.findAll( new PageRequest ( 0, 10 ) );
        assertNotNull( records );
        
        for ( EpayTDatiSingoliVersamenti record : records ) {
        	EpayTDatiSingoliVersamentiDTO dto = mapper.toDTO( record );
        	assertNotNull( dto );
        }
		
		List<EpayTDatiSingoliVersamentiDTO> dtos = mapper.toDTO( records );
		assertNotNull( dtos );
		
		assertEquals( records.getNumberOfElements(), dtos.size() );
    }

}
