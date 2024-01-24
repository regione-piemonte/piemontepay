/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.common.config;

public class LogConfig {
	
	public static String HANDLER_ROOT = "epaypacatalogweb";
	
	public static String HANDLER_FRONTEND = HANDLER_ROOT + ".frontend";
	public static String HANDLER_ASPECT = HANDLER_ROOT + ".aspect";
	public static String HANDLER_MONITOR = HANDLER_ROOT + ".monitoraggio";
	public static String HANDLER_FLAIDOOR_SRV = HANDLER_ROOT + ".integration.flaidoorsrv";
	public static String HANDLER_SECURITY = HANDLER_ROOT + ".security";
	public static String HANDLER_UTILS = HANDLER_ROOT + ".utils";
	public static String HANDLER_SERVICES = HANDLER_ROOT + ".services";
	
	static {
		// none
	}
}
