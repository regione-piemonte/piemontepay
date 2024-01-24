/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dao.LanguageDao;
import it.csi.mdp.core.business.dto.Language;
import it.csi.mdp.core.business.exceptions.LanguageDaoException;
import java.util.List;

public interface LanguageDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 */
	public void insert(Language dto);

	/** 
	 * Returns all rows from the LANGUAGE table that match the criteria ''.
	 */
	public List<Language> findAll() throws LanguageDaoException;

	/** 
	 * Returns all rows from the LANGUAGE table that match the criteria 'GATEWAYID = :gatewayid'.
	 */
	public List<Language> findWhereGatewayidEquals(String gatewayid) throws LanguageDaoException;

	/** 
	 * Returns all rows from the LANGUAGE table that match the criteria 'GTWLANGUAGECODE = :gtwlanguagecode'.
	 */
	public List<Language> findWhereGtwlanguagecodeEquals(String gtwlanguagecode) throws LanguageDaoException;

	/** 
	 * Returns all rows from the LANGUAGE table that match the criteria 'MDPLANGUAGECODE = :mdplanguagecode'.
	 */
	public List<Language> findWhereMdplanguagecodeEquals(String mdplanguagecode) throws LanguageDaoException;

	/** 
	 * Returns all rows from the LANGUAGE table that match the criteria 'LANGUAGEDESCR = :languagedescr'.
	 */
	public List<Language> findWhereLanguagedescrEquals(String languagedescr) throws LanguageDaoException;

	/** 
	 * Returns all rows from the LANGUAGE table that match the criteria 'ENABLED = :enabled'.
	 */
	public List<Language> findWhereEnabledEquals(String enabled) throws LanguageDaoException;
	public List<Language> findWhereGatewayidandMdpLanguageidEquals(String gatewayid, String language) throws LanguageDaoException;
}
