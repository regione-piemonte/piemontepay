/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.mapper;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import it.csi.mdp.mdppagopaapi.business.ConfigService;
import it.csi.mdp.mdppagopaapi.testbed.config.UnitTestDB;
import it.csi.mdp.mdppagopaapi.testbed.model.ParentHttpCallTest;

/**
 * Spring mapper test for "CsiLogAudit" <br>
 *
 * @author fabio.fenoglio
 */
@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { UnitTestDB.class } )
@Transactional
public class ConfigServiceTest extends ParentHttpCallTest {
	
	private static final String TEST_KEY = "chiaveditest1";

    @Autowired
    private ConfigService configService;


    @Test
    public void mappingShouldWork () {
        assertNotNull( configService.getConfig(TEST_KEY) );
        
    }

}
