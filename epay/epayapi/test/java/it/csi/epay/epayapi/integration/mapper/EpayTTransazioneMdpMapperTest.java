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

import it.csi.epay.epayapi.integration.domain.EpayTTransazioneMdp;
import it.csi.epay.epayapi.integration.dto.EpayTTransazioneMdpDTO;
import it.csi.epay.epayapi.integration.repository.EpayTTransazioneMdpRepository;
import it.csi.epay.epayapi.testbed.config.UnitTestDB;
import it.csi.epay.epayapi.testbed.model.ParentHttpCallTest;

/**
 * Spring mapper test for "EpayTTransazioneMdp" <br>
 *
 * @author fabio.fenoglio
 */
@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { UnitTestDB.class } )
@Transactional
public class EpayTTransazioneMdpMapperTest extends ParentHttpCallTest {

    @Autowired
    private EpayTTransazioneMdpRepository repository;

    @Autowired
    private EpayTTransazioneMdpMapper mapper;

    @Test
    public void mappingShouldWork () {
        Page<EpayTTransazioneMdp> records = repository.findAll( new PageRequest ( 0, 10 ) );
        assertNotNull( records );
        
        for ( EpayTTransazioneMdp record : records ) {
        	EpayTTransazioneMdpDTO dto = mapper.toDTO( record );
        	assertNotNull( dto );
        	
        }
		
		List<EpayTTransazioneMdpDTO> dtos = mapper.toDTO( records );
		assertNotNull( dtos );
		
		assertEquals( records.getNumberOfElements(), dtos.size() );
    }

}
