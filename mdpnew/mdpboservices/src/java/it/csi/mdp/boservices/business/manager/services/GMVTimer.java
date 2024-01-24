/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.boservices.business.manager.services;

/**
 *
 * @author Gianmarco Vanni
 * Utility for measuring time
 * Featured Chronometer with start - stop - resume features,
 * measures time in milliseconds up to hours.
 * 
 */
public final class GMVTimer {

    private static long msBegin,  msEnd,  msTime;

    public enum TimeFormat {

        MILLISECONDS, SECONDS, MINUTES, HOURS
    };

    private GMVTimer() {
    }

    /**
     * Start the timer.
     */
    public static void start() {
        msTime = 0;
        msBegin = System.currentTimeMillis();
    }

    /**
     * Stop the timer.
     */
    public static void stop() {
        msEnd = System.currentTimeMillis();
        msTime += msEnd - msBegin;
    }

    /**
     * Resume the timer, that is stopped by <code>stop()</code>.
     */
    public static void resume() {
        msBegin = System.currentTimeMillis();
    }

    /**
     * Return the elapsed time measured in milliseconds.
     * @return  long, the time.
     */
    public static long time() {
        return msTime;
    }

    /**
     * Return the elapsed time measured in seconds.
     * @return double, the time.
     *
     */
    public static double stime() {
        return msTime / 1000.;
    }

    /**
     * Return the elapsed time measured in minutes.
     * @return double, the time.
     *
     */
    public static double mtime() {
        return msTime / 60000.;
    }

    /**
     * Return the elapsed time measured in hours.
     * @return double, the time.
     *
     */
    public static double htime() {
        return msTime / 3600000.;
    }

    /**
     * Return the elapsed time measured according to the TimeFormat requested.
     * @return double, the time.
     *
     */
    public static double getTimeF(TimeFormat tf) {
        double t = 0.0;
        switch (tf) {
            case MILLISECONDS:
                t = time();
            case SECONDS:
                t = stime();
            case MINUTES:
                t = mtime();
            case HOURS:
                t = htime();
            default:
                t = time();
        }
        return t;

    }

    /**
     * Main function for testing only
     * @param arg command-line arguments.
     
    public static void main(String[] arg) {
        GMVTimer.start();
        for (int i = 1; i < 1000000; i++) {
        }
        GMVTimer.stop();
        GMVTimer.resume();
        for (int i = 1; i < 1000000; i++) {
        }
        GMVTimer.stop();
        System.out.println("ms " + GMVTimer.time());
        System.out.println("s " + GMVTimer.stime());
        System.out.println("m " + GMVTimer.mtime());
        System.out.println("h " + GMVTimer.htime());
    }*/
}
