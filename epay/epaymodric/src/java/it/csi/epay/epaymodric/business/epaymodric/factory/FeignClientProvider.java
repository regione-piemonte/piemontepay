/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.factory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.codehaus.jackson.map.DeserializationConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import it.csi.epay.epaymodric.business.epaymodric.factory.model.FeignClient;
import it.csi.epay.epaymodric.business.epaymodric.factory.model.FeignClientContext;
import it.csi.epay.epaymodric.business.epaymodric.manager.ConfigurazioneManager;


/**
 *
 */
@Component
public class FeignClientProvider implements BeanDefinitionRegistryPostProcessor, ApplicationContextAware {

    private static final Logger logger = LogManager.getLogger ( FeignClientProvider.class );

    private ApplicationContext applicationContext = null;

    public ConfigurazioneManager getConfigurazioneService () {
        if ( applicationContext != null ) {
            try {
                return applicationContext.getBean ( ConfigurazioneManager.class );
            } catch ( Exception e ) {
                throw new RuntimeException ( "Provider di configurazione non disponibile: " + e.getMessage (), e );
            }
        } else {
            throw new RuntimeException ( "Provider di configurazione non disponibile: nessun contesto disponibile" );
        }
    }

    public FeignClientProvider () {
        // NOP
    }

    @Override
    public void postProcessBeanFactory ( ConfigurableListableBeanFactory beanFactory ) throws BeansException {

        FeignClientContext context = new FeignClientContext ();

        RestTemplate restTemplate = new RestTemplate ();

        /*
         * configura manualmente il message converter in modo da avere un mapping piu' resiliente. [ FAIL_ON_UNKNOWN_PROPERTIES = false ] serve per evitare
         * eccezioni in caso di campi presenti sulle risposte del servizio ma non sui DTO
         */
        MappingJacksonHttpMessageConverter jsonConverter = new MappingJacksonHttpMessageConverter ();
        jsonConverter.getObjectMapper ().configure ( DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false );

        /*
         * utilizza il message converter appena configurato come unico converter per il rest template. e' necessario per evitare di utilizzare il converter di
         * default, che sarebbe prioritario rispetto a quello configurato manualmente.
         */
        final List<HttpMessageConverter<?>> listHttpMessageConverters = new ArrayList<> ();
        listHttpMessageConverters.add ( jsonConverter );
        restTemplate.setMessageConverters ( listHttpMessageConverters );

        context.setProvider ( this );
        context.setRestTemplate ( restTemplate );

        try {
            loadRegisteredFeignClients ( context, this.getClass ().getClassLoader (), beanFactory, "it.csi.epay.epaymodric.business.epaymodric.facade.rest" );
        } catch ( IOException e ) {
            throw new RuntimeException ( e );
        }

    }

    @Override
    public void postProcessBeanDefinitionRegistry ( BeanDefinitionRegistry beanRegistry ) throws BeansException {
        // NOP
        beanRegistry.toString ();
    }

    private static void loadRegisteredFeignClients (
        FeignClientContext context,
        ClassLoader cl,
        ConfigurableListableBeanFactory beanFactory,
        String completePackageName ) throws IOException {
        String packagePath = completePackageName.replace ( ".", "/" );

        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver ( cl );
        Resource [] resources;
        Set<String> registeredClasses = new HashSet<> ();

        // scansiono il percorso specificato, ricercando tutte le classi compilate che vi appartengono
        resources = resolver.getResources ( "classpath*:" + packagePath + "/**/*.class" );
        for ( Resource resource: resources ) {
            String resourceURI = resource.getURI ().toString ();

            // costruisco il nome della classe Java a partire dal percorso relativo all'interno del package specificato
            // in questo modo posso supportare anche le classi nei vari sub-package
            String subclassPath = resourceURI.substring ( resourceURI.indexOf ( packagePath ) + packagePath.length () + 1 );
            subclassPath = subclassPath.substring ( 0, subclassPath.length () - 6 );
            String className = completePackageName + "." + subclassPath.replace ( "/", "." );

            try {
                Class<?> foundClass = Class.forName ( className );

                if ( foundClass.isAnnotationPresent ( FeignClient.class ) ) {                
                	if ( !registeredClasses.contains ( foundClass.getName () ) ) {
                        registeredClasses.add ( foundClass.getName () );

                        Object bean = FeignClientFactory.buildClient ( foundClass, context );

                        beanFactory.registerSingleton ( foundClass.getSimpleName (), bean );

                    } else {
                        logger.debug ( "skipping duplicated class scanned :" + foundClass.getName () );
                    }
                }

            } catch ( Exception e ) {
                logger.error (  "error registering FeignClient " + className + ": class is not available.", e );
            }
        }
    }

    @Override
    public void setApplicationContext ( ApplicationContext ac ) throws BeansException {
        applicationContext = ac;
    }
}
