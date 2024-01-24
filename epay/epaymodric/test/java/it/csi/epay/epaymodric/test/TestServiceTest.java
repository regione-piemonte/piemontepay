/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import it.csi.epay.epaymodric.business.epaymodric.manager.TestResourcesManager;
import it.csi.epay.epaymodric.config.PersistenceJPAConfigUnitTest;


@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { PersistenceJPAConfigUnitTest.class } )
public class TestServiceTest {

    @Autowired
    private TestResourcesManager testResourcesManager;

    @Test
    public void simpleTest () {
        String test = testResourcesManager.testResources ();
        assertTrue ( test != null );
    }

    @Test
    public void saveTest () {
        String test = testResourcesManager.testResources ();
        assertTrue ( test != null );
    }

    public TestResourcesManager getTestResourcesManager () {
        return testResourcesManager;
    }

    public void setTestResourcesManager ( TestResourcesManager testResourcesManager ) {
        this.testResourcesManager = testResourcesManager;
    }

}
