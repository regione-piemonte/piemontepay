/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.repository;

import it.csi.epay.epayapi.integration.repository.EpayDMessaggioRepository;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epayapi.testbed.config.UnitTestDB;
import it.csi.epay.epayapi.testbed.model.ParentHttpCallTest;

/**
 * Spring data Jpa repository test for "EpayDMessaggio" <br>
 *
 * @author fabio.fenoglio
 */

@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { UnitTestDB.class } )
@Transactional
public class EpayDMessaggioRepositoryTest extends ParentHttpCallTest {

    @Autowired
    private EpayDMessaggioRepository repository;

    @Test
    public void countShouldWork () {
        
		repository.count();
    }

}
