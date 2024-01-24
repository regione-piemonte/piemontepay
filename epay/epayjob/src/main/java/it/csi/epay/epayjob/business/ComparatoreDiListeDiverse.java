/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.business;

import java.util.LinkedList;
import java.util.List;

public class ComparatoreDiListeDiverse {

    public interface ComparatoreDiElementiDiversi<T1, T2> {

        boolean compara ( T1 t1, T2 t2 );
    }

    public static <T1, T2>
    DifferenzaFraListeDiverse<T1, T2>
    compara ( List<T1> list1, List<T2> list2, ComparatoreDiElementiDiversi<T1, T2> matcher ) {
        DifferenzaFraListeDiverse<T1, T2> output = new DifferenzaFraListeDiverse<> ();

        if ( list1 == null && list2 == null ) {

        } else if ( list1 != null && list2 == null ) {

            for ( T1 o: list1 ) {
                output.getElementiSoloNellaPrima ().add ( o );
            }

        } else if ( list1 == null && list2 != null ) {

            for ( T2 o: list2 ) {
                output.getElementiSoloNellaSeconda ().add ( o );
            }

        } else if ( list1 != null && list2 != null ) {
            for ( int i1 = 0; i1 < list1.size (); i1++ ) {
                T1 el1 = list1.get ( i1 );
                T2 el2 = null;
                boolean found = false;
                for ( int i2 = 0; i2 < list2.size (); i2++ ) {
                    el2 = list2.get ( i2 );
                    if ( matcher.compara ( el1, el2 ) ) {
                        found = true;
                        break;
                    }
                }
                if ( found ) {
                    output.getElementiNellaPrimaCheSonoAncheNellaSeconda ().add ( el1 );
                    output.getElementiNellaSecondaCheSonoAncheNellaPrima ().add ( el2 );
                    output.getElementiInTutteEDue ().add ( new Coppia<> ( el1, el2 ) );
                } else {
                    output.getElementiSoloNellaPrima ().add ( el1 );
                }
            }

            for ( int i2 = 0; i2 < list2.size (); i2++ ) {
                T2 el2 = list2.get ( i2 );
                T1 el1 = null;
                boolean found = false;
                for ( int i1 = 0; i1 < list1.size (); i1++ ) {
                    el1 = list1.get ( i1 );
                    if ( matcher.compara ( el1, el2 ) ) {
                        found = true;
                        break;
                    }
                }
                if ( !found ) {
                    output.getElementiSoloNellaSeconda ().add ( el2 );
                }
            }
        }

        return output;
    }

    public static class DifferenzaFraListeDiverse<T1, T2> {

        private List<T1> inFirstNotInSecond;

        private List<T2> inSecondNotInFirst;

        private List<T1> inBothFromFirst;

        private List<T2> inBothFromSecond;

        private List<Coppia<T1, T2>> inBoth;

        public DifferenzaFraListeDiverse () {
            inFirstNotInSecond = new LinkedList<> ();
            inSecondNotInFirst = new LinkedList<> ();
            inBothFromFirst = new LinkedList<> ();
            inBothFromSecond = new LinkedList<> ();
            inBoth = new LinkedList<> ();
        }

        public List<T1> getElementiSoloNellaPrima () {
            return inFirstNotInSecond;
        }

        public List<T2> getElementiSoloNellaSeconda () {
            return inSecondNotInFirst;
        }

        public List<T1> getElementiNellaPrimaCheSonoAncheNellaSeconda () {
            return inBothFromFirst;
        }

        public List<T2> getElementiNellaSecondaCheSonoAncheNellaPrima () {
            return inBothFromSecond;
        }

        public List<Coppia<T1, T2>> getElementiInTutteEDue () {
            return inBoth;
        }
    }

    public static class Coppia<T1, T2> {

        private T1 first;

        private T2 second;

        public Coppia ( T1 o1, T2 o2 ) {
            first = o1;
            second = o2;
        }

        public T1 getPrimo () {
            return first;
        }

        public T2 getSecondo () {
            return second;
        }

    }
}
