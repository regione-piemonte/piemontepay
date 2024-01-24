/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.repository;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import it.csi.epay.epaypacatalogsrv.repository.CduRepository;
import it.csi.epay.epaypacatalogsrv.repository.UtenteRepository;
import it.csi.epay.epaypacatalogsrv.test.config.EpaycatalogWsUnitTestConfigH2;


@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { EpaycatalogWsUnitTestConfigH2.class } )
public class RepositoryTest {

    @Autowired
    private CduRepository epaypaTCduRepository;

    @Autowired
    private UtenteRepository epaypaTUtenteRepository;

    @Test
    public void shouldBeAbleToReadCduTable () {
        assertTrue ( epaypaTCduRepository.count () >= 0 );
    }

    @Test
    public void shouldBeAbleToReadUtenteTable () {
        assertTrue ( epaypaTUtenteRepository.count () >= 0 );
    }
}
