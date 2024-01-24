/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.util;

import java.sql.SQLException;
import java.util.Date;

import javax.naming.NamingException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class LogUtil {
	private Logger log;
	private String className;


	public LogUtil(Class clazz) {
		log = LogManager.getLogger(clazz);
		className = clazz.getSimpleName();

	}
	private String buildMsg(String methodName, Object msg) {
		StringBuilder sb = new StringBuilder();
		sb.append("[").append(className).append("|").append(methodName)
				.append("] ").append(msg);
		return sb.toString();
	}

	public void startMethod(String methodName) {
		log.info(buildMsg(methodName, "BEGIN"));
	}

	public void startMethod(String methodName, Object msg) {
		log.info(buildMsg(methodName, "BEGIN " + msg));
	}

	public void stopMethod(String methodName) {
		log.info(buildMsg(methodName, "END"));
	}

	public void stopMethod(String methodName, Object msg) {
		log.info(buildMsg(methodName, "END " + msg));
	}

	public void debug(String methodName, Object msg) {
		log.debug(buildMsg(methodName, msg));
	}

	public void info(String methodName, Object msg) {
		log.info(buildMsg(methodName, msg));
		// System.out.println(buildMsg(methodName,msg));

	}

	public void warn(String methodName, Object msg) {
		log.warn(buildMsg(methodName, msg));
	}

	public void warn(String methodName, Object msg, Throwable t) {
		log.warn(buildMsg(methodName, msg), t);
	}

	public void error(String methodName, Object msg) {
		log.error(buildMsg(methodName, msg));
	}

	public void error(String methodName, Object msg, Throwable t) {
		log.error(buildMsg(methodName, msg), t);
	}

	public void fatal(String methodName, Object msg) {
		log.fatal(buildMsg(methodName, msg));
	}

	public void fatal(String methodName, Object msg, Throwable t) {
		log.fatal(buildMsg(methodName, msg), t);
	}

	public String getName() {
		return log.getName();
	}

	public boolean isDebugEnabled() {
		return log.isDebugEnabled();
	}



	public void audit(String oggOper, String operazione, String utente)
			throws SQLException, NamingException, Exception {
		audit(new Date(), "CPEC_CPECETL", null, null, oggOper, operazione,
				utente);
	}

	public void audit(Date dataOra, String idApp, String ipAddress,
			String keyOper, String oggOper, String operazione, String utente)
			throws SQLException, NamingException, Exception {

	}
}
