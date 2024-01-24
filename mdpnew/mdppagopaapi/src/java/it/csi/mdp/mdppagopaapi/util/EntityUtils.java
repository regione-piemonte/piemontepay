/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.util;

import java.io.IOException;
import java.util.Collection;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;


/**
 *
 */
public abstract class EntityUtils {

    private static ObjectMapper mapper;

    private static ObjectMapper getMapper () {
        if ( mapper == null ) {
            mapper = new ObjectMapper ();
        }

        return mapper;
    }

    public static Class<?> getTargetObject ( Object proxy ) throws Exception {

        if ( AopUtils.isJdkDynamicProxy ( proxy ) ) {
            while ( ( AopUtils.isJdkDynamicProxy ( proxy ) ) ) {
                return getTargetObject ( ( (Advised) proxy ).getTargetSource ().getTarget () );
            }
            return proxy.getClass ();
        } else if ( AopUtils.isCglibProxy ( proxy ) ) {
            Class<?> proxyClass = proxy.getClass ().getSuperclass ();
            while ( AopUtils.isCglibProxy ( proxyClass ) ) {
                proxyClass = proxy.getClass ().getSuperclass ();
            }
            return proxyClass;
        } else if ( proxy.getClass ().getCanonicalName ().contains ( "com.sun.proxy.$Proxy" ) ) {
            Class<?> [] interfaces = proxy.getClass ().getInterfaces ();
            if ( interfaces.length > 0 ) {
                return interfaces [0];
            } else {
                return proxy.getClass ();
            }
        } else {
            return proxy.getClass ();
        }
    }

    public static String represent ( Object raw ) {
        if ( raw == null ) {
            return "null";
        }
        try {
            if ( raw instanceof String ) {
                return "\"" + (String) raw + "\"";
            } else if ( raw instanceof Object ) {
                if ( raw.getClass ().getName ().startsWith ( "it.csi" ) ) {
                    return raw.getClass ().getName () + "#" + getMapper ().writerWithDefaultPrettyPrinter ().writeValueAsString ( raw );
                } else if ( raw instanceof Collection ) {
                    StringBuilder stringCollection = new StringBuilder ();
                    stringCollection.append ( raw.getClass ().getName () );
                    stringCollection.append ( "#[\n" );
                    for ( Object o: ( (Collection<?>) raw ) ) {
                        stringCollection.append ( "\t" );
                        stringCollection.append ( represent ( o ) );
                        stringCollection.append ( "," );
                    }
                    stringCollection.append ( "\n]" );
                    return stringCollection.toString ();
                } else if ( raw.getClass ().isArray () && String.class.isAssignableFrom ( raw.getClass ().getComponentType () ) ) {
                    return raw.getClass ().getComponentType ().getName () + "[]#" + getMapper ().writerWithDefaultPrettyPrinter ().writeValueAsString ( raw );
                } else if ( raw.getClass ().isArray () && raw.getClass ().getComponentType ().getName ().startsWith ( "it.csi" ) ) {
                    return raw.getClass ().getComponentType ().getName () + "[]#" + getMapper ().writerWithDefaultPrettyPrinter ().writeValueAsString ( raw );
                } else {
                    return raw.toString ();
                }
            } else {
                return String.valueOf ( raw );
            }
        } catch ( IOException e ) {
            return String.valueOf ( raw );
        }
    }
}
