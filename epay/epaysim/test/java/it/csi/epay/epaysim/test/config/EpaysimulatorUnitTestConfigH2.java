/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysim.test.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;

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
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@PropertySource ( "classpath:database.properties" )
@ComponentScan ( basePackages = { "it.csi.epay.epaysim" }, excludeFilters = {
    @Filter ( type = FilterType.ANNOTATION, value = Configuration.class ) } )
@EnableJpaRepositories ( basePackages = { "it.csi.epay.epaysim.business.epaysim.repository" } )
@EnableTransactionManagement
public class EpaysimulatorUnitTestConfigH2 {

    @Resource
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory () {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean ();
        em.setDataSource ( dataSource () );
        em.setPackagesToScan ( new String [] { "it.csi.epay.epaysim.business.epaysim.model" } );

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter ();
        em.setJpaVendorAdapter ( vendorAdapter );
        em.setJpaProperties ( additionalProperties () );

        return em;
    }

    @Bean
    public EmbeddedDatabase dataSource () {
        return new EmbeddedDatabaseBuilder ().setType ( EmbeddedDatabaseType.H2 )
                        .addScript ( "sql/init_schema.sql" )
                        .addScript ( "sql/init_data.sql" )
                        .build ();
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
        properties.setProperty ( "hibernate.dialect", "org.hibernate.dialect.H2Dialect" );

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
    public Jaxb2Marshaller jaxb2Marshaller () {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller ();
        marshaller.setContextPaths ( "it.csi.epay.epaysim.dto.flussi.riconciliazione_versamenti_psp.types",
            "it.csi.epay.epaysim.dto.flussi.types" );
        marshaller.setMarshallerProperties ( new HashMap<String, Object> () {
            private static final long serialVersionUID = 1L;

            {
                put ( javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true );
            }
        } );

        return marshaller;
    }
}
