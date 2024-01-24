/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaysim.test.config.EpaysimulatorUnitTestConfigH2;
import it.csi.epay.epaysim.test.model.ParentUnitTest;


@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { EpaysimulatorUnitTestConfigH2.class } )
@Transactional
public class DummyServiceH2Test extends ParentUnitTest {

    @Test
    public void verifySuccess () throws Exception {

        assertTrue ( true );

    }

}
