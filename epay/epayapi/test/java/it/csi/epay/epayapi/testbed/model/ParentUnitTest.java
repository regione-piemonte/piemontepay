/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayapi.testbed.model;

import static org.junit.Assert.assertEquals;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.mockito.MockitoAnnotations;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import it.csi.epay.epayapi.api.util.SpringApplicationContextHelper;


public abstract class ParentUnitTest implements ApplicationContextAware {

    protected final Logger logger = Logger.getLogger ( ParentUnitTest.class );


    protected DataSource dataSource;

    protected JdbcTemplate jdbcTemplate;


    private ApplicationContext applicationContext;

    private ServletContext servletContext = new MockServletContext ();

    private static java.util.ResourceBundle res = java.util.ResourceBundle.getBundle ( "config" );

    static {
        res = java.util.ResourceBundle.getBundle ( "config" );
    }

    public static java.util.ResourceBundle getRes () {
        return res;
    }

    public ServletContext getServletContext () {
        return servletContext;
    }


    public JdbcTemplate getJdbcTemplate () {
        return jdbcTemplate;
    }


    public ApplicationContext getApplicationContext () {
        return applicationContext;
    }

    public Logger getLogger () {
        return logger;
    }

    @Before
    public void beforeParent () {
        mockServletEnvironment ();
        MockitoAnnotations.initMocks ( this );

        assertEquals ( "L'ambiente deve essere pre-configurato e dichiarato come JUNIT", "JUNIT", res.getString ( "nome.ambiente" ) );
    }

    protected void mockServletEnvironment () {
        MockHttpServletRequest request = new MockHttpServletRequest ();

        SpringApplicationContextHelper.AppServletContextListener.setServletContext ( servletContext );

        RequestContextHolder.setRequestAttributes ( new ServletRequestAttributes ( request ) );
    }

    protected void log ( Object... data ) {
        String dstr = "";
        for ( Object s: data ) {
            dstr += s + " ";
        }

        logger.info ( dstr );
    }


    @Autowired
    public void setDataSource ( DataSource dataSource ) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate ( dataSource );
    }


    @Override
    public void setApplicationContext ( ApplicationContext applicationContext ) throws BeansException {
        this.applicationContext = applicationContext;

        servletContext.setAttribute (
            WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE,
            applicationContext );
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

    protected void verifyDatabaseTestData () {

        int result = getJdbcTemplate ().queryForInt ( " select count(*) from epayapi_t_anagrafica" );
        if ( result < 1 ) {
            throw new RuntimeException ( "Test environment is not ready. Missing environment data identifier on DB" );
        }

        // TODO verificare tabella test-only per essere sicuri di non impattare un DB reale

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
