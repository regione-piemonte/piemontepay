/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaysimweb.test.config.EpaysimulatorUnitTestConfigPostgre;
import it.csi.epay.epaysimweb.test.model.ParentUnitTest;


@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { EpaysimulatorUnitTestConfigPostgre.class } )
@Transactional
public class DummyServicePostgreTest extends ParentUnitTest {

    @Test
    public void verifSyuccess () throws Exception {

        assertTrue ( true );

    }

}
