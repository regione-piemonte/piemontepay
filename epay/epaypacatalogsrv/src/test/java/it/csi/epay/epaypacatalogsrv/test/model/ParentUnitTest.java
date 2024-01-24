/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.test.model;

import javax.sql.DataSource;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.core.JdbcTemplate;

import it.csi.epay.epaypacatalogsrv.model.Errore;
import it.csi.epay.epaypacatalogsrv.repository.ErroreRepository;
import it.csi.epay.epaypacatalogsrv.test.TestHelper;
import it.csi.epay.epaypacatalogsrv.vo.Constants;


public abstract class ParentUnitTest implements ApplicationContextAware {

    private final Logger logger = LogManager.getLogger ( ParentUnitTest.class );

    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    private ApplicationContext applicationContext;

    public Logger getLogger () {
        return logger;
    }

    protected void log ( String data ) {

        logger
            .info (
                "\n\n******************************* [TEST SEQUENCE] ********************************\n\n" +
                    data +
                    "\n\n********************************************************************************\n\n" );
    }

    @Autowired
    private ErroreRepository erroreRepository;

    @Autowired
    public void setDataSource ( DataSource dataSource ) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate ( dataSource );
    }

    @Override
    public void setApplicationContext ( ApplicationContext applicationContext ) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @SuppressWarnings ( "unchecked" )
    public static <T> T getTargetObject ( Object proxy ) {
        if ( ( AopUtils.isJdkDynamicProxy ( proxy ) ) ) {
            try {
                return (T) getTargetObject ( ( (Advised) proxy ).getTargetSource ().getTarget () );
            } catch ( Exception e ) {
                throw new RuntimeException ( "Failed to unproxy target.", e );
            }
        }
        return (T) proxy;
    }

    public JdbcTemplate getJdbcTemplate () {
        return jdbcTemplate;
    }

    protected void prepareDatabaseWithTestData () {
        TestHelper.prepareTestData ( dataSource );
    }

    protected void verifyDatabaseTestData () {
        int result = getJdbcTemplate ().queryForInt ( " select count(*) from epaycat_d_errore where codice = 'TEST_ENV' " );
        if ( result != 1 ) {
            throw new RuntimeException ( "Test environment is not ready. Missing environment data identifier on DB" );
        }

        Errore dto = erroreRepository.findOneByCodiceApplicativoAndCodiceLinguaAndCodice (
            Constants.APPLICATION_CODE, Constants.DEFAULT_LANGUAGE, "TEST_ENV" );

        if ( dto == null || !"TEST_ENV_ENABLED".equals ( dto.getMessaggio () ) ) {
            throw new RuntimeException ( "Test environment is not ready. Invalid environment data identifier on DB" );
        }
    }

    public ApplicationContext getApplicationContext () {
        return applicationContext;
    }

    @SuppressWarnings ( "unchecked" )
    protected <T> T getBean ( Class<T> beanClass ) {

        String name = beanClass.getSimpleName ();
        name = name.substring ( 0, 1 ).toLowerCase () + name.substring ( 1 );

        Object rawBean = getApplicationContext ().getBean ( name );
        T operation = null;

        if ( AopUtils.isAopProxy ( rawBean ) && rawBean instanceof Advised ) {
            Advised advised = (Advised) rawBean;
            try {
                operation = (T) advised.getTargetSource ().getTarget ();
            } catch ( Exception e ) {
                throw new RuntimeException ( e );
            }
        }

        return operation;
    }
}
