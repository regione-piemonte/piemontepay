/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.test.helper;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.security.InvalidParameterException;

import org.apache.commons.beanutils.PropertyUtils;


public abstract class TestEntityHelper {

    public static Object getNestedPropertyOrNull ( Object o, String property ) {

        Object currentObject = o;

        for ( String token: property.split ( "\\." ) ) {
            if ( currentObject == null ) {
                break;
            } else {
                try {
                    currentObject = PropertyUtils.getProperty ( currentObject, token );
                } catch ( IllegalAccessException | InvocationTargetException | NoSuchMethodException e ) {
                    e.printStackTrace ();
                    throw new RuntimeException ( "getNestedPropertyOrNull exception: " + e.getMessage (), e );
                }
            }
        }

        return currentObject;
    }

    public static void assertFieldsEqual ( Object o1, Object o2, String... specifications ) {
        if ( o1 == null || o2 == null ) {
            throw new InvalidParameterException ();
        }

        for ( String specification: specifications ) {
            String key1, key2;
            if ( specification.contains ( "-" ) ) {
                key1 = specification.split ( "-" ) [0];
                key2 = specification.split ( "-" ) [1];
            } else {
                key1 = key2 = specification;
            }
            key1 = key1.trim ();
            key2 = key2.trim ();

            Object v1 = getNestedPropertyOrNull ( o1, key1 );
            Object v2 = getNestedPropertyOrNull ( o2, key2 );

            assertEquals ( "the field [" + o1.getClass ().getSimpleName () + " ." + key1 + "] should be equal to the field [" + o2.getClass ().getSimpleName ()
                + " ." + key2 + "]", v1, v2 );
        }
    }
}
