/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetExtractor<T> {
	
	public T extractData(ResultSet rs) throws SQLException, Exception;

}
