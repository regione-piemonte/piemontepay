/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpiuvsrv.integration.mdpiuv.dao;

import it.csi.mdpnew.mdpiuvsrv.util.MdpiuvConstants;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class AbstractDao {

	
	public static final String LOGGER_PREFIX = MdpiuvConstants.MDPIUV_ROOT_LOG_CATEGORY;
	
	protected static Logger log = Logger.getLogger(LOGGER_PREFIX + ".dao");
	 
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	public NamedParameterJdbcTemplate getNamedJdbcTemplate() {
		return namedJdbcTemplate;
	}

	public void setNamedJdbcTemplate(NamedParameterJdbcTemplate namedJdbcTemplate) {
		this.namedJdbcTemplate = namedJdbcTemplate;
	}
}
