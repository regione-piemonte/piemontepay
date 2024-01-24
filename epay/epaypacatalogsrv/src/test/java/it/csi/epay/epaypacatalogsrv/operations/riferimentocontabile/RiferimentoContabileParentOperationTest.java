/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.operations.riferimentocontabile;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.epay.epaypacatalogsrv.model.RiferimentoContabile;
import it.csi.epay.epaypacatalogsrv.repository.RiferimentoContabileRepository;
import it.csi.epay.epaypacatalogsrv.test.model.ParentOperationTest;


public abstract class RiferimentoContabileParentOperationTest extends ParentOperationTest {

    @Autowired
    private RiferimentoContabileRepository repository;

    @After
    public void afterRiferimentoContabileParentOperationTest () {
        assertDataIntegrity ();
    }

    protected void assertDataIntegrity () {
        List<RiferimentoContabile> all = repository.findAll ();

        for ( RiferimentoContabile dto: all ) {

            assertNotNull ( dto.getCodiceVersamento () );
            assertNotNull ( dto.getCodiceVersamento ().getVoceEntrata () );
            assertNotNull ( dto.getCodiceVersamento ().getVoceEntrata ().getMacrotipo () );
            assertNotNull ( dto.getCodiceVersamento ().getVoceEntrata ().getTematica () );
            assertNotNull ( dto.getDataInizioValidita () );

        }
    }

}
