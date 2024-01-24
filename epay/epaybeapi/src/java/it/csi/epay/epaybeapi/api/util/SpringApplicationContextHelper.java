/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.api.util;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.core.ResourceMethod;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;


/**
 * Classe di utility che espone l'ApplicationContext di Spring in modo statico. Permette l'accesso ad alcuni Service trasversali anche da componenti che
 * esistono esternamente al contesto di Spring, come ad esempio le risorse di RestEasy e i filter registrati a livello di Servlet.
 */
public class SpringApplicationContextHelper implements ApplicationContextAware {

    private static Logger logger = LoggerFactory.getLogger ( SpringApplicationContextHelper.class );

    private static ApplicationContext appContext;

    private static Map<String, Object> beanCache = new HashMap<> ();

    private static Map<String, Object> restEasyServiceCache = new HashMap<> ();

    private static Map<Object, Boolean> injectionStatus = new HashMap<> ();

    public static void setGlobalApplicationContext ( ApplicationContext applicationContext ) {
        SpringApplicationContextHelper.appContext = applicationContext;
    }

    public SpringApplicationContextHelper () {
    }

    @Override
    public void setApplicationContext ( ApplicationContext applicationContext ) throws BeansException {
        setGlobalApplicationContext ( applicationContext );
    }

    /**
     * Esegue la injection delle dipendenze richieste dentro la risorsa ricercata a partire dal nome specificato. Richiamare questo metodo su una risorsa
     * permette di rendere funzionanti le annotazioni Autowired o Inject anche su risorse normalmente esterne al contesto di Spring.
     *
     * @param resteasyServiceName nome della resource class in cui iniettare i bean di spring
     */
    protected static void injectSpringBeansIntoRestEasyService ( String resteasyServiceName ) {
        // mantengo una cache per non eseguire questa operazione di scansione, relativamente onerosa, ad ogni chiamata
        if ( !injectionStatus.getOrDefault ( resteasyServiceName, false ) ) {
            logger.debug ( "processo injection per servizio con chiave " + resteasyServiceName );
            for ( Object service: restEasyServiceCache.values () ) {
                if ( matchesServiceName ( service, resteasyServiceName ) ) {

                    logger.debug ( "matchato servizio con chiave " + resteasyServiceName + " alla classe " + service.getClass ().getName () );

                    SpringBeanAutowiringSupport.processInjectionBasedOnServletContext (
                        service, AppServletContextListener.getServletContext () );

                    injectionStatus.put ( resteasyServiceName, true );

                    if ( service instanceof InitializingBean ) {
                        logger.debug ( "eseguo metodo afterPropertiesSet per il servizio " + service );
                        try {
                            ( (InitializingBean) service ).afterPropertiesSet ();
                        } catch ( Exception e ) {
                            throw new RuntimeException ( "errore nell'esecuzione dell'hook afterPropertiesSet", e );
                        }
                    }

                    return;
                }
            }
        }
    }

    /**
     * Verifica se la risorsa corrisponde al servizio specificato per nome. In caso di implememtazione diretta il parametro in input deve coincidere con il nome
     * della classe, mentre in caso di implementazione indiretta (ovvero interface annotata alla jax-rs + classe di implementazione che estende da essa) occorre
     * verificare il match del nome servizio con il nome dell'interfaccia implementata.
     *
     * @param resource la risorsa da verificare
     * @param name il nome del servizio jax-rs.
     * @return true se il servizio matcha con il nome fornito
     */
    protected static boolean matchesServiceName ( Object resource, String name ) {
        // prima verifico se il nome coincide direttamente
        if ( resource.getClass ().getName ().equals ( name ) ) {
            return true;
        } else {
            // se non coincide direttamente, cerco tra le interfacce implementate
            Class<?> [] interfaces = resource.getClass ().getInterfaces ();
            if ( interfaces != null && interfaces.length > 0 ) {
                for ( int i = 0; i < interfaces.length; i++ ) {
                    if ( interfaces [i].getName ().equals ( name ) ) {
                        return true;
                    }
                }
                // se non trovato
                return false;
            } else {
                return false;
            }
        }
    }

    /**
     * Salva nella cache delle risorse di RestEasy la risorsa specificata. in questo modo la risorsa sara' disponibile quando verra' richiamato il metodo
     * injectSpringBeansIntoRestEasyService specificando una ricerca della risorsa per nome.
     *
     * @param service la risorsa da inserire in cache
     */
    protected static void registerRestEasyController ( Object service ) {
        restEasyServiceCache.put ( service.getClass ().getSimpleName (), service );
    }

    /**
     * Ottiene il bean specificato dall'application context di Spring
     *
     * @param class1 la classe del bean da cercare
     * @return il bean trovato
     */
    public static Object getBean ( Class<?> class1 ) {
        String beanName = class1.getSimpleName ();
        if ( beanCache.containsKey ( beanName ) ) {
            return beanCache.get ( beanName );
        }

        Object bean = appContext.containsBean ( beanName ) ? appContext.getBean ( beanName )
                        : appContext.getBean ( beanName.substring ( 0, 1 ).toLowerCase () + beanName.substring ( 1 ) );

        beanCache.put ( beanName, bean );
        return bean;
    }

    /**
     * Interceptor delegato all'injection delle dipendenze Spring sulle risorse RestEasy.
     */
    @Provider
    public static class SpringInjectorInterceptor implements PreProcessInterceptor {

        /**
         * Esegue, se necessario, l'injection delle dipendenze Spring sulla risorsa RestEasy prima che ne vengano richiamati i metodi
         */
        @Override
        public ServerResponse preProcess ( HttpRequest request, ResourceMethod method ) throws Failure, WebApplicationException {
            SpringApplicationContextHelper.injectSpringBeansIntoRestEasyService ( method.getResourceClass ().getName () );
            return null;
        }
    }

    /**
     * questo metodo agisce come proxy per 'caricaRisorseRestEasyInPackage'. La differenza e' che, essendo richiamabile passando una classe come argomento, il
     * metodo continuera' a funzionare anche in caso di spostamento delle classi, cambio di nome dei package o altri refactoring. Se passassi invece
     * 'nome.del.package' come stringa, in tali casi il metodo rischierebbe di fallire in modo silenzioso, senza dare adeguata evidenza dell'errore.
     *
     * @param cl il classLoader da utilizzare per la scansione
     * @param singletons la collection in cui caricare gli oggetti
     * @param sampleClass una classe all'interno del package che si vuole sottoporre a scansione
     * @see caricaRisorseRestEasyInPackage
     * @throws IOException in caso di problemi con l'accesso al package specificato
     */
    public static void caricaRisorseRestEasyNellaStessaCartellaDi ( ClassLoader cl, Collection<? super Object> singletons, Class<?> sampleClass )
                    throws IOException {
        caricaRisorseRestEasyInPackage ( cl, singletons, sampleClass.getPackage ().getName () );
    }

    /**
     * Eseguo una scansione del package desiderato, utilizzando lo stesso motore che Spring utilizza per l'autoscan. Estraggo da tale package una istanza di
     * tutte le classi caricabili automaticamente; in questo modo posso evitare di dover referenziare manualmente tutte le risorse, operazione ripetitiva e
     * prona ad errori.
     *
     * @param cl il classLoader da utilizzare per la scansione
     * @param singletons la collection in cui caricare gli oggetti
     * @param completePackageName il nome completo del package da sottoporre a scansione
     * @throws IOException in caso di problemi con l'accesso al package specificato
     */
    public static void caricaRisorseRestEasyInPackage ( ClassLoader cl, Collection<? super Object> singletons, String completePackageName ) throws IOException {
        String packagePath = completePackageName.replace ( ".", "/" );

        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver ( cl );
        Resource [] resources;

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
                // istanzio la classe a partire dal suo nome completo.
                // ATTENZIONE: la classe deve avere un costruttore di default
                Class<?> foundClass = Class.forName ( className );

                if ( foundClass.isAnnotationPresent ( SpringSupportedResource.class ) ) {
                    Object instance = foundClass.newInstance ();
                    logger.info ( "registro la risorsa " + instance.getClass ().getSimpleName () );

                    SpringApplicationContextHelper.registerRestEasyController ( instance );
                    singletons.add ( instance );

                } else if ( foundClass.isAnnotationPresent ( Provider.class ) ) {

                    Object instance = foundClass.newInstance ();
                    logger.info ( "registro il provider " + instance.getClass ().getSimpleName () );
                    singletons.add ( instance );

                }

            } catch ( InstantiationException e ) {
                logger.error ( "errore nella registrazione della risorsa " + className + ": " +
                    "errore nella creazione dell'istanza. Verificare che la classe " + className +
                    "disponga di un costruttore di default e che non sia una classe astratta oppure un'interfaccia.", e );

            } catch ( ClassNotFoundException e ) {
                logger.error ( "errore nella registrazione della risorsa " + className + ": " +
                    "la classe non e' disponibile. Verificare la coerenza della classe " + className +
                    " col package " + completePackageName, e );

            } catch ( IllegalAccessException e ) {
                logger.error ( "errore nella registrazione della risorsa " + className + ": " +
                    "errore nell'accesso ai metodi di costruzione. Verificare che la classe " + className +
                    " disponga di un costruttore di default e che tale costruttore abbia visibilita' pubblica.", e );
            }
        }

        singletons.add ( new SpringInjectorInterceptor () );
    }

    /**
     * Registro gli interceptor che servono per gestire l'injection automatica
     *
     * @param singletons la collection di risorse in cui inserire gli Interceptor caricati
     */
    public static void caricaInterceptors ( Collection<? super Object> singletons ) {
        singletons.add ( new SpringInjectorInterceptor () );
    }

    /**
     * Listener per ricevere il ServletContext
     */
    public static class AppServletContextListener implements ServletContextListener {

        private static ServletContext servletContext;

        @Override
        public void contextInitialized ( ServletContextEvent sce ) {
            setServletContext ( sce.getServletContext () );
        }

        @Override
        public void contextDestroyed ( ServletContextEvent sce ) {
        }

        public static ServletContext getServletContext () {
            return servletContext;
        }

        public static void setServletContext ( ServletContext servletContext ) {
            AppServletContextListener.servletContext = servletContext;
        }

    }

}
