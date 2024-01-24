/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.business.impl;

import java.io.File;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import it.csi.mdp.mdppagopaapi.business.MonitoringService;
import it.csi.mdp.mdppagopaapi.dto.monitoring.ServiceStatus;
import it.csi.mdp.mdppagopaapi.dto.monitoring.ServiceStatusDTO;
import it.csi.mdp.mdppagopaapi.integration.repository.ConfigRepository;
import it.csi.mdp.mdppagopaapi.integration.repository.IRepository;
import it.csi.mdp.mdppagopaapi.util.Monitorable;


@Service
public class MonitoringServiceImpl implements MonitoringService, ApplicationContextAware {

    private Logger logger = LoggerFactory.getLogger ( MonitoringService.class );

    private ApplicationContext applicationContext;

    protected final static long MINIMUM_REQUIRED_DISK_MB = 50;

    protected final static long MINIMUM_REQUIRED_RAM_MB_WARNING = 100;

    protected final static long MINIMUM_REQUIRED_RAM_MB_DOWN = 1;
    
    @Override
    public void setApplicationContext ( ApplicationContext applicationContext ) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public ServiceStatusDTO getServiceStatus () {
        logger.debug ( "getServiceStatus - avvio del check" );

        Map<String, Monitorable> beans = applicationContext.getBeansOfType ( Monitorable.class );
        Map<Monitorable, ServiceStatusDTO> beansStatuses = new ConcurrentHashMap<> ();

        ExecutorService executor = null;
        Instant before = Instant.now ();

        if ( beans.size () > 0 ) {
            executor = Executors.newFixedThreadPool ( beans.size () );

            for ( Monitorable bean: beans.values () ) {
                executor.submit ( new Runnable () {

                    @Override
                    public void run () {
                        beansStatuses.put ( bean, getServiceStatus ( bean ) );
                    }
                } );
            }
        }

        Monitorable databaseConnectionMonitor = new Monitorable () {

            @Override
            public ServiceStatusDTO checkStatus () throws Exception {
                //                return checkDatabaseConnectionStatus ();
                return checkDatabaseConnectionStatusLight ();
            }

        };

        //        Monitorable fileSystemResourcesMonitor = new Monitorable () {
        //
        //            @Override
        //            public ServiceStatusDTO checkStatus () throws Exception {
        //                return checkFileSystemResourcesStatus ();
        //            }
        //
        //        };

        beansStatuses.put ( databaseConnectionMonitor, getServiceStatus ( databaseConnectionMonitor ) );

        //        beansStatuses.put ( fileSystemResourcesMonitor, getServiceStatus ( fileSystemResourcesMonitor ) );

        if ( executor != null ) {
            executor.shutdown ();
            while ( !executor.isTerminated () ) {
                try {
                    Thread.sleep ( 100 );
                } catch ( InterruptedException e ) {
                    return ServiceStatusDTO.down ().withMessage ( "INTERNAL ERROR" ).build ();
                }
            }
        }
        logger.debug ( "getServiceStatus - tutti i check terminati" );

        return aggregate ( beansStatuses, ChronoUnit.MILLIS.between ( before, Instant.now () ) );
    }

    private ServiceStatusDTO getServiceStatus ( Monitorable bean ) {
        ServiceStatusDTO result;
        Instant before = Instant.now ();

        logger.debug ( "getServiceStatus - avvio check del servizio " + bean.getClass ().getName () );

        try {
            result = bean.checkStatus ();
            if ( result == null ) {
                result = ServiceStatusDTO.unknown ()
                    .withMessage ( bean.getClass ().getSimpleName () + " service did not respond" )
                    .build ();
            }

        } catch ( Exception e ) {

            result = ServiceStatusDTO.down ()
                .withMessage ( bean.getClass ().getSimpleName () + " service failed check" )
                .withDetail ( "error", e.getClass () + ": " + e.getMessage () + ( e.getCause () != null ? "; " + e.getCause ().getMessage () : "" ) )
                .build ();
        } catch ( Throwable e ) {

            result = ServiceStatusDTO.unknown ()
                .withMessage ( bean.getClass ().getSimpleName () + " service failed is unstable" )
                .withDetail ( "error", e.getClass () + ": " + e.getMessage () + ( e.getCause () != null ? "; " + e.getCause ().getMessage () : "" ) )
                .build ();
        }

        Instant after = Instant.now ();

        String desc = extractVisualDescription ( bean, result );

        return ServiceStatusDTO.builder ()
            .withDetails ( result.getDetails () )
            .withMessage ( result.getMessage () )
            .withName ( desc )
            .withStatus ( result.getStatus () )
            .withResponseTime ( ChronoUnit.MILLIS.between ( before, after ) )
            .build ();
    }


    private String extractVisualDescription ( Monitorable bean, ServiceStatusDTO result ) {
        String desc = result.getName ();
        if ( desc == null ) {
            desc = bean.getClass ().getSimpleName ();
            if ( desc.endsWith ( "ServiceImpl" ) ) {
                desc = desc.substring ( 0, desc.length () - 11 );
            }
        }

        return desc;
    }

    private ServiceStatusDTO aggregate ( Map<Monitorable, ServiceStatusDTO> statuses, long time ) {
        ServiceStatus resultStatus = ServiceStatus.UP;
        int numUp = 0;
        int numUnknown = 0;
        int numDown = 0;
        int numWarn = 0;

        List<ServiceStatusDTO> details = new LinkedList<> ();

        for ( Entry<Monitorable, ServiceStatusDTO> statusEntry: statuses.entrySet () ) {

            details.add ( statusEntry.getValue () );

            if ( statusEntry.getValue ().getStatus () == ServiceStatus.UP ) {
                numUp++;
            } else if ( statusEntry.getValue ().getStatus () == ServiceStatus.WARNING ) {
                numWarn++;
            } else if ( statusEntry.getValue ().getStatus () == ServiceStatus.DOWN ) {
                numDown++;
                resultStatus = ServiceStatus.DOWN;
            } else { // UNKNOWN
                numUnknown++;
                resultStatus = ServiceStatus.DOWN;
            }
        }

        String message = "Service is " + resultStatus.name () + ": ";
        if ( numUp > 0 ) {
            message += numUp + " services UP, ";
        }
        if ( numUnknown > 0 ) {
            message += numUnknown + " services UNKNOWN, ";
        }
        if ( numDown > 0 ) {
            message += numDown + " services DOWN, ";
        }
        if ( numWarn > 0 ) {
            message += numWarn + " WARNING(s), ";
        }

        if ( message.endsWith ( ", " ) ) {
            message = message.substring ( 0, message.length () - 2 );
        }

        Collections.sort ( details, new Comparator<ServiceStatusDTO> () {

            @Override
            public int compare ( ServiceStatusDTO o1, ServiceStatusDTO o2 ) {
                int res = Integer.compare (
                    o2.getStatus ().getPriority (),
                    o1.getStatus ().getPriority () );

                if ( res == 0 ) {
                    res = o1.getName ().compareTo ( o2.getName () );
                }

                return res;
            }

        } );

        return ServiceStatusDTO.builder ()
            .withName ( "Mdpapi Service Status" )
            .withStatus ( resultStatus )
            .withMessage ( message )
            .withDetail ( "resources", details )
            .withResponseTime ( time )
            .build ();
    }

    @Deprecated
    @SuppressWarnings ( "rawtypes" )
    private ServiceStatusDTO checkDatabaseConnectionStatus () {
        ServiceStatus resultStatus = ServiceStatus.UP;
        List<String> failing = new LinkedList<> ();
        Instant before = Instant.now ();
        long totalRecords = 0;

        Map<String, IRepository> beans = applicationContext.getBeansOfType ( IRepository.class );

        for ( Entry<String, IRepository> bean: beans.entrySet () ) {
            try {
                totalRecords += bean.getValue ().count ();
            } catch ( Exception e ) {
                resultStatus = ServiceStatus.DOWN;
                failing.add ( bean.getKey () );
            }
        }

        long responseTime = ChronoUnit.MILLIS.between ( before, Instant.now () );
        String message;

        if ( resultStatus == ServiceStatus.UP ) {
            message = "Database connection is OK for all tables";
        } else {
            StringBuilder messageBuilder = new StringBuilder ( "Database connection is DOWN for " );
            messageBuilder.append ( failing.size () );
            messageBuilder.append ( " tables: " );

            for ( String name: failing ) {
                messageBuilder.append ( name );
                messageBuilder.append ( ", " );
            }
            message = messageBuilder.toString ().substring ( 0, messageBuilder.length () - 2 );
        }

        return ServiceStatusDTO.builder ()
            .withMessage ( message )
            .withName ( "Database connection" )
            .withStatus ( resultStatus )
            .withResponseTime ( responseTime )
            .withDetail ( "numero tabelle", beans.size () )
            .withDetail ( "numero record", totalRecords )
            .withDetail ( "numero errori", failing.size () )
            .build ();
    }

    private ServiceStatusDTO checkDatabaseConnectionStatusLight () {
        ServiceStatus resultStatus = ServiceStatus.UP;
        List<String> failing = new LinkedList<> ();
        Instant before = Instant.now ();
        long totalRecords = 0;
        
        

        ConfigRepository repo = applicationContext.getBean ( ConfigRepository.class );

        try {
            totalRecords += repo.count ();
        } catch ( Exception e ) {
            resultStatus = ServiceStatus.DOWN;
            failing.add ( repo.getClass ().getName () );
        }

        long responseTime = ChronoUnit.MILLIS.between ( before, Instant.now () );
        String message;

        if ( resultStatus == ServiceStatus.UP ) {
            message = "Database connection is OK for all tables";
        } else {
            StringBuilder messageBuilder = new StringBuilder ( "Database connection is DOWN for " );
            messageBuilder.append ( failing.size () );
            messageBuilder.append ( " tables: " );

            for ( String name: failing ) {
                messageBuilder.append ( name );
                messageBuilder.append ( ", " );
            }
            message = messageBuilder.toString ().substring ( 0, messageBuilder.length () - 2 );
        }

        return ServiceStatusDTO.builder ()
            .withMessage ( message )
            .withName ( "Database connection" )
            .withStatus ( resultStatus )
            .withResponseTime ( responseTime )
            .withDetail ( "numero tabelle", 1 )
            .withDetail ( "numero record", totalRecords )
            .withDetail ( "numero errori", failing.size () )
            .build ();
    }

    @Deprecated
    private ServiceStatusDTO checkFileSystemResourcesStatus () {
        ServiceStatus status = ServiceStatus.UP;
        Map<String, Object> details = new TreeMap<> ();
        String message = "Enough resources are available";

        Double freeMemory;
        Double maxMemory; // can be NULL
        Double totalMemory;
        Double totalDisk;
        Double totalDiskUsable;

        /* This will return Long.MAX_VALUE if there is no preset limit */
        Long maxMemoryRaw = Runtime.getRuntime ().maxMemory ();
        if ( maxMemoryRaw != null && maxMemoryRaw.equals ( Long.MAX_VALUE ) ) {
            maxMemoryRaw = null;
        }

        maxMemory = toMB ( maxMemoryRaw );
        freeMemory = toMB ( Runtime.getRuntime ().freeMemory () );
        totalMemory = toMB ( Runtime.getRuntime ().totalMemory () );

        details.put ( "Free memory (MB)", freeMemory );
        details.put ( "Maximum memory (MB)", maxMemory );
        details.put ( "Total memory (MB)", totalMemory );

        /* Get a list of all filesystem roots on this system */
        File [] roots = File.listRoots ();

        /* For each filesystem root, print some info */
        totalDiskUsable = 0d;
        totalDisk = 0d;
        for ( File root: roots ) {
            String rootName = root.getAbsolutePath ().toString ();
            Double usable = toMB ( root.getUsableSpace () );
            Double total = toMB ( root.getTotalSpace () );
            totalDiskUsable += usable;
            totalDisk += total;

            details.put ( "Usable space (MB) on " + rootName, usable );
            details.put ( "Total size (MB) on " + rootName, total );
        }

        details.put ( "Usable space (MB) on all partitions", totalDiskUsable );
        details.put ( "Total size (MB) of all partitions", totalDisk );

        details.put ( "Usable space required treshold (MB)", MINIMUM_REQUIRED_DISK_MB );
        details.put ( "Free memory warning treshold (MB)", MINIMUM_REQUIRED_RAM_MB_WARNING );
        details.put ( "Free memory required treshold (MB)", MINIMUM_REQUIRED_RAM_MB_DOWN );

        if ( totalDiskUsable < MINIMUM_REQUIRED_DISK_MB ) {
            message = "DOWN: Out of disk space";
            status = ServiceStatus.DOWN;
        } else if ( freeMemory < MINIMUM_REQUIRED_RAM_MB_DOWN ) {
            message = "DOWN: Out of memory";
            status = ServiceStatus.DOWN;
        } else if ( freeMemory < MINIMUM_REQUIRED_RAM_MB_WARNING ) {
            message = "WARNING: Running low on memory";
            status = ServiceStatus.WARNING;
        }

        return ServiceStatusDTO.builder ()
            .withStatus ( status )
            .withName ( "System resource monitor" )
            .withMessage ( message )
            .withDetails ( details )
            .build ();
    }

    private Double toMB ( Long mb ) {
        if ( mb == null ) {
            return null;
        }
        return Math.round ( 100.0 * mb / ( 1024.0 * 1024.0 ) ) / 100.0;
    }
}
