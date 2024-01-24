/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.test;

import javax.sql.DataSource;

import it.csi.epay.epaypacatalogsrv.dto.CallerInputDto;
import it.csi.epay.epaypacatalogsrv.dto.PrincipalInputDto;
import it.csi.epay.epaypacatalogsrv.test.config.TestConstants;


public abstract class TestHelper {

    public static CallerInputDto getCallerAdmin () {
        return new CallerInputDto (
            TestConstants.TEST_APP,
            new PrincipalInputDto (
                TestConstants.ADMIN_CF,
                TestConstants.ADMIN_ENTE_CODE,
                TestConstants.ADMIN_ROLE_CODE ) );
    }

    public static CallerInputDto getCallerOperatore () {
        return new CallerInputDto (
            TestConstants.TEST_APP,
            new PrincipalInputDto (
                TestConstants.ADMIN_CF,
                TestConstants.ADMIN_ENTE_CODE,
                TestConstants.OPERATORE_ROLE_CODE ) );
    }

    public static void prepareTestData ( DataSource dataSource ) {
        // actually, H2 is ready;
        if ( true ) {
            return;
        }
        //
        //        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator ();
        //        resourceDatabasePopulator.addScript ( new ClassPathResource ( "sql/drop_data.sql" ) );
        //        resourceDatabasePopulator.addScript ( new ClassPathResource ( "sql/insert_base_data.sql" ) );
        //        resourceDatabasePopulator.addScript ( new ClassPathResource ( "sql/insert_test_data.sql" ) );
        //        resourceDatabasePopulator.setContinueOnError ( false );
        //
        //        try {
        //            resourceDatabasePopulator.populate ( DataSourceUtils.getConnection ( dataSource ) );
        //        } catch ( SQLException e ) {
        //            throw new RuntimeException ( "error preparing database", e );
        //        }
    }
}
