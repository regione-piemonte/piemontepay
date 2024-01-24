/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.test.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource ( "classpath:database.properties" )
@ComponentScan ( basePackages = { "it.csi.epay.epaymodric" }, excludeFilters = {
    @Filter ( type = FilterType.ANNOTATION, value = Configuration.class ) } )
@EnableJpaRepositories ( basePackages = { "it.csi.epay.epaymodric.business.epaymodric.repository" } )
@EnableTransactionManagement
public class EpaymodricUnitTestConfigH2 {
	
	 @Resource
	    private Environment env;

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
	    public EmbeddedDatabase dataSource () {
	        return new EmbeddedDatabaseBuilder ().setType ( EmbeddedDatabaseType.H2 )
	            .addScript ( "sql/init_database.sql" )
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

}
