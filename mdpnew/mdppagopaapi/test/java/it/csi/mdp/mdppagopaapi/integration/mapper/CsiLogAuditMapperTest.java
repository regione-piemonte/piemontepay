/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.mapper;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import it.csi.mdp.mdppagopaapi.integration.domain.CsiLogAudit;
import it.csi.mdp.mdppagopaapi.integration.repository.CsiLogAuditRepository;
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
public class CsiLogAuditMapperTest extends ParentHttpCallTest {

    @Autowired
    private CsiLogAuditRepository repository;


    @Test
    public void mappingShouldWork () {
        Page<CsiLogAudit> records = repository.findAll( new PageRequest ( 0, 10 ) );
        assertNotNull( records );
        
    }

}
