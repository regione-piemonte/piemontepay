/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.application;

import it.csi.mdp.mdpetl.integration.util.dao.EstraiIdentificativiPSPDAO;
import it.csi.mdp.mdpetl.util.LogUtil;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.sql.rowset.serial.SerialException;

public class AccessoDatabaseFlussiRendicontazione {

	static LogUtil log = new LogUtil(AccessoDatabaseFlussiRendicontazione.class);
	
	public List<String> getElencoPSP() throws SerialException, SQLException, NamingException, Exception {
		String methodName="estraiElencoPSP";
		log.startMethod(methodName);
		List<String> elenco = new EstraiIdentificativiPSPDAO().executeQuery();	
		log.stopMethod(methodName);
		return elenco;
	}

}
