/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import it.csi.epay.epaymodric.business.epaymodric.manager.PrenotazioneReportManager;
import it.csi.epay.epaymodric.config.PersistenceJPAConfigUnitTest;


@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { PersistenceJPAConfigUnitTest.class } )
public class ReportTest {

    private final int REPORT_BUILD_SIZE = 100;
    private final int REPORT_BUILD_RECORDS_PAGE_SIZE = 100;

    @Autowired
    private PrenotazioneReportManager reportManager;
    
    @Test
    public void reportTest () {
        reportManager.crateReport ( REPORT_BUILD_SIZE, REPORT_BUILD_RECORDS_PAGE_SIZE );
    }
}
