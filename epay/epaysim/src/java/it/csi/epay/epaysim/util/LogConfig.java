/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysim.util;

public class LogConfig {

    public static String HANDLER_ROOT = "epaysim";

    public static String HANDLER_FRONTEND = HANDLER_ROOT + ".frontend";
    public static String HANDLER_ASPECT = HANDLER_ROOT + ".aspect";
    public static String HANDLER_MONITOR = HANDLER_ROOT + ".monitoraggio";
    public static String HANDLER_SECURITY = HANDLER_ROOT + ".security";
    public static String HANDLER_UTILS = HANDLER_ROOT + ".utils";
    public static String HANDLER_SERVICES = HANDLER_ROOT + ".services";
    public static String HANDLER_TEST = HANDLER_ROOT + ".test";

    static {
        // none
    }
}
