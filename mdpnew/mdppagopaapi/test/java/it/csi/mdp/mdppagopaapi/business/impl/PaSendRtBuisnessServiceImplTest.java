/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdppagopaapi.business.impl;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import it.csi.mdp.mdppagopaapi.business.PaSendRTBusinessService;
import it.csi.mdp.mdppagopaapi.pa.pafornode.PaSendRTRes;
import it.csi.mdp.mdppagopaapi.testbed.config.UnitTestDB;
import it.csi.mdp.mdppagopaapi.testbed.helper.TestHelperSendRT;
import it.csi.mdp.mdppagopaapi.testbed.model.ParentUnitTest;


/**
 *
 */
@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { UnitTestDB.class } )
@Transactional
public class PaSendRtBuisnessServiceImplTest extends ParentUnitTest {

    @Autowired
    PaSendRTBusinessService service;

    @Test
    public void shouldExist () {
        assertNotNull ( service );
    }

    @Test ( expected = NullPointerException.class )
    public void provaChiamataSenzaParametri () {
        service.paSendRT ( TestHelperSendRT.crea () );
    }

    @Test ( expected = NullPointerException.class )
    public void creaConParametri () {
        PaSendRTRes paSendRTResponse = service.paSendRT ( TestHelperSendRT.creaConParametri () );
        System.out.println ( paSendRTResponse );
    }

}
