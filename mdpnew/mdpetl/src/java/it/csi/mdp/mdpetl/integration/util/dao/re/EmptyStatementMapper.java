/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao.re;

import it.csi.mdp.mdpetl.integration.util.dao.StatementMapper;

import java.sql.PreparedStatement;

public class EmptyStatementMapper implements StatementMapper {

	public String  mapStatementParameters(PreparedStatement stmt) {
		
		return "";
	}

}
