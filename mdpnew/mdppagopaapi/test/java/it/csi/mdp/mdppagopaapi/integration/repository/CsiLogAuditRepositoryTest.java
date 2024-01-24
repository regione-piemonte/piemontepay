/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import it.csi.mdp.mdppagopaapi.integration.repository.CsiLogAuditRepository;
import it.csi.mdp.mdppagopaapi.testbed.config.UnitTestDB;
import it.csi.mdp.mdppagopaapi.testbed.model.ParentHttpCallTest;

/**
 * Spring data Jpa repository test for "CsiLogAudit" <br>
 *
 * @author fabio.fenoglio
 */

@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { UnitTestDB.class } )
@Transactional
public class CsiLogAuditRepositoryTest extends ParentHttpCallTest {

    @Autowired
    private CsiLogAuditRepository repository;

    @Test
    public void countShouldWork () {
        
		repository.count();
    }

}
