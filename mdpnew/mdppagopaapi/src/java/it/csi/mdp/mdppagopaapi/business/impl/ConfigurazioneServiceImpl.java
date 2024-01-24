/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdppagopaapi.business.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.mdp.mdppagopaapi.business.ConfigurazioneService;
import it.csi.mdp.mdppagopaapi.dto.common.ConfigurazioneDTO;
import it.csi.mdp.mdppagopaapi.util.Constants;
import it.csi.mdp.mdppagopaapi.util.Environments;
import it.csi.mdp.mdppagopaapi.util.ParametriApplicativo;
import it.csi.mdp.mdppagopaapi.util.ParametriApplicativo.ExposurePolicy;


@Service
@Transactional ( readOnly = true )
public class ConfigurazioneServiceImpl implements ConfigurazioneService, InitializingBean {

    private final static Logger logger = LoggerFactory.getLogger ( ConfigurazioneService.class );

    private static ResourceBundle currentProfileConfiguration;

    private static ResourceBundle runtimeProperties;

    private static ResourceBundle buildProperties;

    private static ResourceBundle commonProperties;

    private static ResourceBundle environmentProperties;

    private static Map<String, ResourceBundle> propertiesPerAmbiente;

    private static Map<String, String> mergedConfiguration = null;

    private static Environments currentEnvironment;

    private static boolean isTest = false;

    private static final String DB_GROUP = "mdppagopaapi";

    private String skeyDb;

    public String getSkeyDb () {
        if ( null == skeyDb ) {
            try ( Stream<String> stream = Files.lines ( Paths.get ( getConfig ( ParametriApplicativo.AUTH_KEY_DB ).getValue () ) ) ) {
                stream.findFirst ().ifPresent ( new Consumer<String> () {

                    @Override
                    public void accept ( String t ) {
                        skeyDb = t;
                    }
                } );
            } catch ( IOException e ) {
                logger.error ( "Erorre in fase di reperimento devo valore skeyDb" );
            }
        }
        return skeyDb;
    }

    static {
        caricaResourceBundle ();
    }

    private static ResourceBundle getConfigProperties ( String env ) {
        return ( env == null ) ? currentProfileConfiguration : propertiesPerAmbiente.get ( env );
    }

    @Override
    public void afterPropertiesSet () throws Exception {
        logger.info ( "Inizializzato servizio di configurazione per l'ambiente " +
                        this.getConfig ( ParametriApplicativo.NOME_AMBIENTE ).asString ( "[NULL]" ) );

        if ( !isTest ) {
            verificaConfigurazionePacchetti ();
        }
    }

    public static Map<String, String> getMergedConfiguration () {
        if ( mergedConfiguration == null ) {
            Map<String, String> builtMergedConfiguration = new HashMap<> ();

            for ( String key: currentProfileConfiguration.keySet () ) {
                builtMergedConfiguration.put ( key, currentProfileConfiguration.getString ( key ) );
            }
            for ( String key: commonProperties.keySet () ) {
                builtMergedConfiguration.putIfAbsent ( key, commonProperties.getString ( key ) );
            }

            mergedConfiguration = builtMergedConfiguration;
        }
        return mergedConfiguration;
    }

    @Override
    @Cacheable ( value = Constants.CACHES.CONFIGURATION, key = "#parametro" )
    public ConfigurazioneDTO getConfig ( String parametro ) {
        ConfigurazioneDTO result = fetchConfig ( null, parametro, true, true, true );

        return result;
    }

    @Override
    @Cacheable ( value = Constants.CACHES.CONFIGURATION, key = "#parametro.getCodice()" )
    public ConfigurazioneDTO getConfig ( ParametriApplicativo parametro ) {
        ConfigurazioneDTO result = fetchConfig ( null, parametro, true, true, true );

        if ( result == null || result.isEmpty () ) {
            if ( parametro.getObbligatorio () ) {
                throw new RuntimeException ( "Il parametro " + parametro + " e' richiesto ma non e' configurato" );
            }
        }

        return result;
    }

    @Override
    @Cacheable ( value = Constants.CACHES.CONFIGURATION, key = "#parametro.getCodice()" )
    public ConfigurazioneDTO requireConfig ( ParametriApplicativo parametro ) {

        ConfigurazioneDTO found = fetchConfig ( null, parametro, true, true, true );
        if ( found == null || found.isEmpty () ) {
            throw new RuntimeException ( "Il parametro " + parametro + " e' richiesto ma non e' configurato" );
        }

        return found;
    }

    private ConfigurazioneDTO fetchConfig (
        Environments ambiente, String key, boolean fromDB, boolean fromCommonProperties, boolean fromCurrentProperties ) {
        logger.debug ( "avvio risoluzione parametro " + key + " per ambiente " + ( ambiente != null ? ambiente.name () : "corrente" ) );

        Set<String> variazioni = getVariazioniChiave ( key );

        ConfigurazioneDTO configurazione = null;

        if ( configurazione == null && fromDB ) {
            logger.trace ( "ricerco parametro [" + key + "] e relative varianti su database" );
            configurazione = risolviDaDatabase ( variazioni );
        }

        if ( configurazione == null && fromCurrentProperties ) {
            logger.trace ( "ricerco parametro [" + key + "] e relative varianti su file di properties per la configurazione corrente" );
            configurazione = risolviDaResourceBundle ( getConfigProperties ( ambiente != null ? ambiente.getCodice () : null ), variazioni );
        }

        if ( configurazione == null && fromCommonProperties ) {
            logger.trace ( "ricerco parametro [" + key + "] e relative varianti su file common.properties" );
            configurazione = risolviDaResourceBundle ( commonProperties, variazioni );
        }

        return configurazione == null ? null : ConfigurazioneDTO.builder ( key, configurazione.getValue () ).build ();
    }

    private ConfigurazioneDTO fetchConfig (
        Environments ambiente, ParametriApplicativo parametro, boolean fromDB, boolean fromCommonProperties, boolean fromCurrentProperties ) {
        String key = parametro.getCodice ();

        logger.debug ( "avvio risoluzione parametro " + key + " per ambiente " + ( ambiente != null ? ambiente.name () : "corrente" ) );

        Set<String> variazioni = getVariazioniChiave ( key );

        ConfigurazioneDTO configurazione = null;

        if ( configurazione == null && fromDB ) {
            logger.trace ( "ricerco parametro " + key + " e relative varianti su database" );
            configurazione = risolviDaDatabase ( variazioni );
        }

        if ( configurazione == null && fromCurrentProperties ) {
            logger.trace ( "ricerco parametro " + key + " e relative varianti su file di properties per la configurazione corrente" );
            configurazione = risolviDaResourceBundle ( getConfigProperties ( ambiente != null ? ambiente.getCodice () : null ), variazioni );
        }

        if ( configurazione == null && fromCommonProperties ) {
            logger.trace ( "ricerco parametro " + key + " e relative varianti su file common.properties" );
            configurazione = risolviDaResourceBundle ( commonProperties, variazioni );
        }

        if ( configurazione == null ) {
            logger.warn ( "Parametro di configurazione " + key + " non risolvibile" );
            configurazione = ConfigurazioneDTO.builder ( key, parametro.getValoreDefault () ).build ();
        }

        logger.trace ( "risolto parametro di configurazione " + key + " al valore " +
                        ( parametro.getPolicyEsposizione () != ExposurePolicy.SENSIBLE ? configurazione.getValue () : "???" ) + "" );

        return ConfigurazioneDTO.builder ( parametro.getCodice (), configurazione.getValue () ).build ();
    }

    private ConfigurazioneDTO getFromDatabase ( String key ) {

        //        EpayTParametri parametro = parametroApplicativoRepository.findOne ( new EpayTParametriKey ( DB_GROUP, key ) );
        //
        //        if ( parametro == null ) {
        //            return null;
        //        }
        //
        //        return ConfigurazioneDTO.builder ( parametro.getCodice (), parametro.getValore () ).build ();
        return null;

    }

    private Set<String> getVariazioniChiave ( String raw ) {
        Set<String> variazioni = new LinkedHashSet<> ();

        variazioni.add ( raw );
        variazioni.add ( raw.toUpperCase () );
        variazioni.add ( raw.toLowerCase () );
        variazioni.add ( raw.toUpperCase ().replaceAll ( "[^A-Z0-9]+", "_" ) );
        variazioni.add ( raw.toLowerCase ().replaceAll ( "[^a-z0-9]+", "." ) );
        variazioni.add ( raw.toUpperCase ().replaceAll ( "[^A-Z0-9]+", "." ) );
        variazioni.add ( raw.toLowerCase ().replaceAll ( "[^a-z0-9]+", "_" ) );

        return variazioni;
    }

    private ConfigurazioneDTO risolviDaResourceBundle ( ResourceBundle rb, Collection<String> variazioniChiave ) {
        ConfigurazioneDTO resolved = null;
        for ( String variazioneChiave: variazioniChiave ) {
            logger.trace ( "ricerco parametro [" + variazioneChiave + "] su file di properties" );
            if ( rb.containsKey ( variazioneChiave ) ) {
                if ( resolved == null ) {
                    resolved = ConfigurazioneDTO.builder ( variazioneChiave, rb.getString ( variazioneChiave ) ).build ();
                } else {
                    throw new RuntimeException ( "Parametro configurato su file di properties con troppe varianti: " + variazioneChiave );
                }
            }
        }
        return resolved;
    }

    private ConfigurazioneDTO risolviDaDatabase ( Collection<String> variazioniChiave ) {
        ConfigurazioneDTO resolved = null;
        for ( String variazioneChiave: variazioniChiave ) {
            logger.trace ( "ricerco parametro [" + variazioneChiave + "] su database" );
            ConfigurazioneDTO onDB = getFromDatabase ( variazioneChiave );
            if ( onDB != null ) {
                if ( resolved == null ) {
                    resolved = onDB;
                } else {
                    throw new RuntimeException ( "Parametro configurato su database con troppe varianti: " + variazioneChiave );
                }
            }
        }
        return resolved;
    }

    private static void caricaResourceBundle () {

        currentProfileConfiguration = ResourceBundle.getBundle ( "config" );

        if ( currentProfileConfiguration.getString ( "nome.ambiente" ).equals ( "JUNIT" ) ) {
            currentEnvironment = Environments.LOCAL;
            isTest = true;
            commonProperties = currentProfileConfiguration;
        } else {

            runtimeProperties = ResourceBundle.getBundle ( "properties-cache/runtime" );
            buildProperties = ResourceBundle.getBundle ( "properties-cache/build" );
            environmentProperties = ResourceBundle.getBundle ( "properties-cache/env" );
            commonProperties = ResourceBundle.getBundle ( "properties-cache/common" );

            currentEnvironment = Environments.fromCodice ( runtimeProperties.getString ( "target" ) );

            propertiesPerAmbiente = new HashMap<> ();
            propertiesPerAmbiente.put ( "config", currentProfileConfiguration );
            propertiesPerAmbiente.put ( "runtime", runtimeProperties );
            propertiesPerAmbiente.put ( "build", buildProperties );
            propertiesPerAmbiente.put ( "env", environmentProperties );
            propertiesPerAmbiente.put ( "common", commonProperties );

            for ( Environments env: Environments.values () ) {
                try {
                    propertiesPerAmbiente.put ( env.getCodice (), ResourceBundle.getBundle ( "properties-cache/" + env.getCodice () ) );
                } catch ( Exception e ) {
                    logger.error ( "Errore nella verifica del file di properties per l'ambiente " + env.getCodice (), e );
                }

            }
        }

        getMergedConfiguration ();
    }

    private void verificaConfigurazionePacchetti () {
        Map<ParametriApplicativo, ConfigurazioneDTO> map = new HashMap<> ();

        List<String> errorMessages = new LinkedList<> ();

        // verifico che sia correttamente configurato l'ambiente corrente
        if ( currentEnvironment == null ) {
            throw new RuntimeException ( "Ambiente corrente non configurato o non corretto" );
        }

        // verifico che siano presenti tutti i parametri obbligatori per tutte le configurazioni
        for ( ParametriApplicativo parametro: ParametriApplicativo.values () ) {
            if ( parametro.getObbligatorio () ) {
                logger.debug ( "Verifico configurazione del parametro " + parametro.getCodice () + ": " );

                // verifico per la configurazione corrente
                ConfigurazioneDTO result = fetchConfig ( null, parametro, true, true, true );
                if ( result == null || result.isEmpty () ) {
                    errorMessages.add (
                        "Il parametro " + parametro.getCodice () + " e' richiesto ma non e' configurato nel file di properties corrente ne' su database." );
                } else {
                    logger.debug ( "parametro configurato " + parametro.getCodice () + " = " + result.asString () + "" );
                    map.put ( parametro, result );
                }

                // verifico anche per gli altri profili
                for ( Environments env: Environments.values () ) {
                    result = fetchConfig ( env, parametro, false, true, true );
                    if ( result == null || result.isEmpty () ) {
                        errorMessages.add (
                            "Il parametro " + parametro.getCodice () + " e' richiesto ma non e' configurato nel file di properties per l'ambiente " +
                                            env.name () + " (" + env.getCodice () + ").\n\tConfigurare la voce nel file " + env.getCodice ()
                                            + ".properties oppure nel file common.properties" );
                    }
                }
            }
        }

        if ( !errorMessages.isEmpty () ) {
            StringBuilder errorMessage
            = new StringBuilder ( "****************** \n\n\tERRORE: Sono presenti degli errori bloccanti nella configurazione dei profili: \n" );
            for ( String error: errorMessages ) {
                errorMessage.append ( "\t- " );
                errorMessage.append ( error );
                errorMessage.append ( "\n" );
            }
            errorMessage.append ( "\n\nATTENZIONE: il parametro puo' essere configurato nella apposita tabella del database di ogni ambiente, " +
                            "ma e' comunque richiesta la configurazione di un valore di fallback a livello di configurazione delle properties per ogni ambiente. \n" +
                            "In alternativa e' possibile configurare un valore di fallback comune a tutti gli ambienti utilizzando solamente il file common.properties." );
            errorMessage.append ( "\n\n******************\n" );

            logger.error ( errorMessage.toString () );

            throw new RuntimeException ( "Sono presenti degli errori bloccanti nella configurazione dei profili" );
        } else {
            // stampa un riepilogo
            for ( Entry<ParametriApplicativo, ConfigurazioneDTO> entry: map.entrySet () ) {
                logger.debug ( "configurazione parametro " + entry.getKey ().getCodice () + " = " +
                                ( entry.getKey ().getPolicyEsposizione () != ExposurePolicy.SENSIBLE ? entry.getValue ().getValue () : "???" ) + "" );
            }

        }
    }

    @Override
    public Map<String, Object> getBuildProperties () {
        Map<String, Object> output = new HashMap<> ();

        if ( !isTest ) {
            output.put ( "prodotto", buildProperties.getString ( "prodotto" ) );
            output.put ( "componente", buildProperties.getString ( "componente" ) );
            output.put ( "versione", buildProperties.getString ( "version" ) );
            output.put ( "ambiente", currentEnvironment.getCodice () );
        } else {
            output.put ( "prodotto", "mdpnew" );
            output.put ( "componente", "mdppagopaapi" );
            output.put ( "versione", "1.0.0" );
            output.put ( "ambiente", "local" );
        }

        return output;
    }

    @Override
    public List<ConfigurazioneDTO> getExternalConfiguration () {
        List<ConfigurazioneDTO> output = new LinkedList<> ();

        for ( ParametriApplicativo parametro: ParametriApplicativo.values () ) {
            if ( parametro.getPolicyEsposizione () == ExposurePolicy.EXTERNAL ) {
                output.add ( this.getConfig ( parametro ) );
            }
        }

        return output;
    }

    public static boolean isTest () {
        return isTest;
    }

    public static boolean isLocal () {
        return currentEnvironment == Environments.LOCAL;
    }

    public static boolean isCollaudoOrUpper () {
        return currentEnvironment == Environments.COLL || currentEnvironment == Environments.PROD;
    }

    public static boolean isTestOrLower () {
        return !isCollaudoOrUpper ();
    }

    public static String getRuntimeProperty ( String key ) {
        return runtimeProperties.getString ( key );
    }
}
