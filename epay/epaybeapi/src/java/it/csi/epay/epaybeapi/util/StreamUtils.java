/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaybeapi.util;

import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import it.csi.epay.epaybeapi.integration.proto.TimeBoundedValidity;


/**
 *
 */

public abstract class StreamUtils {

    public static class Filters {

        public static Predicate<TimeBoundedValidity> currentlyValid () {
            return new Predicate<TimeBoundedValidity> () {

                @Override
                public boolean test ( TimeBoundedValidity t ) {
                    return t != null && t.inValidInterval ();
                }
            };
        }

        public static Predicate<String> startsWith ( String parameter ) {
            return new Predicate<String> () {

                @Override
                public boolean test ( String t ) {
                    return t != null && t.startsWith ( parameter );
                }
            };
        }

        public static Predicate<String> contains ( String parameter ) {
            return new Predicate<String> () {

                @Override
                public boolean test ( String t ) {
                    return t != null && t.contains ( parameter );
                }
            };
        }

        public static Predicate<String> equals ( String parameter ) {
            return new Predicate<String> () {

                @Override
                public boolean test ( String t ) {
                    if ( t == null && parameter == null ) {
                        return true;
                    }
                    return t != null && t.equals ( parameter );
                }
            };
        }

        public static Predicate<Long> equals ( Long parameter ) {
            return new Predicate<Long> () {

                @Override
                public boolean test ( Long t ) {
                    if ( t == null && parameter == null ) {
                        return true;
                    }
                    return t != null && t.equals ( parameter );
                }
            };
        }

        public static Predicate<Integer> equals ( Integer parameter ) {
            return new Predicate<Integer> () {

                @Override
                public boolean test ( Integer t ) {
                    if ( t == null && parameter == null ) {
                        return true;
                    }
                    return t != null && t.equals ( parameter );
                }
            };
        }

        public static Predicate<Object> equalsObject ( Object parameter ) {
            return new Predicate<Object> () {

                @Override
                public boolean test ( Object t ) {
                    if ( t == null && parameter == null ) {
                        return true;
                    }
                    return t != null && t.equals ( parameter );
                }
            };
        }

    }

    public static class Mappers {

        public static UnaryOperator<String> uppercase () {
            return new UnaryOperator<String> () {

                @Override
                public String apply ( String t ) {
                    return t != null ? t.toUpperCase () : null;
                }
            };
        }

        public static UnaryOperator<String> lowercase () {
            return new UnaryOperator<String> () {

                @Override
                public String apply ( String t ) {
                    return t != null ? t.toLowerCase () : null;
                }
            };
        }
    }

}
