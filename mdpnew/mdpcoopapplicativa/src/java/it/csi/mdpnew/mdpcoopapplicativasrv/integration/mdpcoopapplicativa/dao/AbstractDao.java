/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.dao;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import it.csi.mdpnew.mdpcoopapplicativasrv.util.MdpcoopapplicativaConstants;

public class AbstractDao {

	public static final String LOGGER_PREFIX = MdpcoopapplicativaConstants.MDPCOOPAPPLICATIVA_ROOT_LOG_CATEGORY;
	
	protected static Logger log = Logger.getLogger(LOGGER_PREFIX + ".dao");
	 
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	public NamedParameterJdbcTemplate getNamedJdbcTemplate() {
		return namedJdbcTemplate;
	}

	public void setNamedJdbcTemplate(NamedParameterJdbcTemplate namedJdbcTemplate) {
		this.namedJdbcTemplate = namedJdbcTemplate;
	}
}
