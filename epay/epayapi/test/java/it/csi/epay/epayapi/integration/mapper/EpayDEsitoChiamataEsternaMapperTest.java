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

import it.csi.epay.epayapi.integration.domain.EpayDEsitoChiamataEsterna;
import it.csi.epay.epayapi.integration.dto.EpayDEsitoChiamataEsternaDTO;
import it.csi.epay.epayapi.integration.repository.EpayDEsitoChiamataEsternaRepository;
import it.csi.epay.epayapi.testbed.config.UnitTestDB;
import it.csi.epay.epayapi.testbed.model.ParentHttpCallTest;

/**
 * Spring mapper test for "EpayDEsitoChiamataEsterna" <br>
 *
 * @author fabio.fenoglio
 */
@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { UnitTestDB.class } )
@Transactional
public class EpayDEsitoChiamataEsternaMapperTest extends ParentHttpCallTest {

    @Autowired
    private EpayDEsitoChiamataEsternaRepository repository;

    @Autowired
    private EpayDEsitoChiamataEsternaMapper mapper;

    @Test
    public void mappingShouldWork () {
        Page<EpayDEsitoChiamataEsterna> records = repository.findAll( new PageRequest ( 0, 10 ) );
        assertNotNull( records );

        for ( EpayDEsitoChiamataEsterna record : records ) {
        	EpayDEsitoChiamataEsternaDTO dto = mapper.toDTO( record );
        	assertNotNull( dto );

        }

		List<EpayDEsitoChiamataEsternaDTO> dtos = mapper.toDTO( records );
		assertNotNull( dtos );

		assertEquals( records.getNumberOfElements(), dtos.size() );
    }

}
