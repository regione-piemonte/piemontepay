/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.test.config;

import java.io.IOException;
import java.util.Properties;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import it.csi.epay.epaypacatalogsrv.business.EpaypacatalogsrvImpl;
import it.csi.epay.epaypacatalogsrv.interfacews.Epaypacatalogsrv;


@Configuration
@PropertySource ( "classpath:database.properties" )
@ComponentScan ( basePackages = { "it.csi.epay.epaypacatalogsrv" }, excludeFilters = {
    @Filter ( type = FilterType.ANNOTATION, value = Configuration.class ) } )
@EnableJpaRepositories ( basePackages = { "it.csi.epay.epaypacatalogsrv.repository", "it.csi.epay.epaypacatalogsrv.test.repository" } )
@EnableTransactionManagement
public class EpaycatalogWsUnitTestConfigPostgre {

    @Resource
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory () {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean ();
        em.setDataSource ( dataSource () );
        em.setPackagesToScan ( new String [] { "it.csi.epay.epaypacatalogsrv.model" } );
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter ();
        em.setJpaVendorAdapter ( vendorAdapter );
        em.setJpaProperties ( additionalProperties () );
        return em;

    }

    @Bean
    public DataSource dataSource () {
        BasicDataSource ds = new BasicDataSource ();
        ds.setDriverClassName ( env.getRequiredProperty ( "csi.db.driver" ) );
        ds.setUrl ( env.getRequiredProperty ( "csi.db.url" ) );
        ds.setUsername ( env.getRequiredProperty ( "csi.db.username" ) );
        ds.setPassword ( env.getRequiredProperty ( "csi.db.password" ) );
        ds.setDefaultAutoCommit ( false );
        return ds;

    }

    @Bean
    public PlatformTransactionManager transactionManager ( EntityManagerFactory emf ) {
        JpaTransactionManager transactionManager = new JpaTransactionManager ();
        transactionManager.setEntityManagerFactory ( emf );
        return transactionManager;

    }
    
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation () {
        return new PersistenceExceptionTranslationPostProcessor ();
    }

    Properties additionalProperties () {
        Properties properties = new Properties ();
        properties.setProperty ( "hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect" );
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "false");
        properties.setProperty("hibernate.generate_statistics", "true");
        properties.setProperty("spring.jpa.properties.hibernate.generate_statistics", "true");
        return properties;
    }

    @Bean
    public DozerBeanMapperFactoryBean configDozer () throws IOException {
        DozerBeanMapperFactoryBean mapper = new DozerBeanMapperFactoryBean ();
        mapper.setMappingFiles ( new PathMatchingResourcePatternResolver ().getResources (
            "classpath*:/mapping/*.xml" ) );
        return mapper;
    }

    @Bean
    public Epaypacatalogsrv configEndpointBean () {
        Epaypacatalogsrv bean = new EpaypacatalogsrvImpl ();
        return bean;
    }
}
