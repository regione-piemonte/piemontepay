/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdppagopaapi.business.impl;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import it.csi.mdp.mdpapi.testbed.helper.TestHelperSendRT;
import it.csi.mdp.mdppagopaapi.business.PaGetPaymentBuisnessService;
import it.csi.mdp.mdppagopaapi.testbed.config.UnitTestDB;
import it.csi.mdp.mdppagopaapi.testbed.model.ParentUnitTest;
import it.csi.mdp.mdppagopaapi.util.EncryptionDecryptionUtil;

/**
 *
 */
@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { UnitTestDB.class } )
@Transactional
public class DecryptTest extends ParentUnitTest {

    @Autowired
    PaGetPaymentBuisnessService service;

    @Test
    public void shouldExist () {
        assertNotNull ( service );
    }


    @Test
    public void decryptString () {
        String str = EncryptionDecryptionUtil.decrypt ( "rayt0CG0+mjtcxM3YKZ1fA==", TestHelperSendRT.SKEY_DB );
        System.out.println ( "output=" + str );
    }


}

