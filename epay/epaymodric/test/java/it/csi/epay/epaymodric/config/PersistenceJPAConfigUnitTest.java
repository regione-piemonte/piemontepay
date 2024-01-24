/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@PropertySource ( "classpath:database.properties" )
@ComponentScan ( basePackages = { "it.csi.epay.epaymodric" }, excludeFilters = {@Filter ( type = FilterType.ANNOTATION, value = Configuration.class ) } )
@EnableJpaRepositories ( basePackages = "it.csi.epay.epaymodric.business.epaymodric.repository" )
@EnableTransactionManagement
public class PersistenceJPAConfigUnitTest {

    @Resource
    private Environment env;

    
    
    @Bean
    public Jaxb2Marshaller jaxb2Marshaller () {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller ();
        marshaller.setPackagesToScan (
            new String [] { "it.csi.epay.epaymodric.util.xsd.epayriconciliazioneversamentipsp", "it.csi.epay.epaymodric.util.xsd.epayflussicompletipsp" } );
        Map<String, String> map = new HashMap<>();
        map.put ( "com.sun.xml.bind.namespacePrefixMapper", "it.csi.epay.epaymodric.business.epaymodric.utils.NamespacePrefixMapperImpl" );
        marshaller.setMarshallerProperties ( map );
        return marshaller;
    }
    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory () {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean ();
        em.setDataSource ( dataSource () );
        em.setPackagesToScan ( new String [] { "it.csi.epay.epaymodric.business.epaymodric.model" } );

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter ();
        em.setJpaVendorAdapter ( vendorAdapter );
        em.setJpaProperties ( additionalProperties () );

        return em;
    }

    @Bean
    public DataSource dataSource () {
        BasicDataSource ds = new BasicDataSource ();
        ds.setDriverClassName ( env.getRequiredProperty ( "db.driver" ) );
        ds.setUrl ( env.getRequiredProperty ( "db.url" ) );
        ds.setUsername ( env.getRequiredProperty ( "db.username" ) );
        ds.setPassword ( env.getRequiredProperty ( "db.password" ) );
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

        return properties;
    }
}
