/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dao.StatiEsteriMinDao;
import it.csi.mdp.core.business.dto.StatiEsteriMin;
import it.csi.mdp.core.business.exceptions.StatiEsteriMinDaoException;
import java.util.Date;
import java.util.List;

public interface StatiEsteriMinDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 */
	public void insert(StatiEsteriMin dto);

	/** 
	 * Returns all rows from the STATI_ESTERI_MIN table that match the criteria ''.
	 */
	public List<StatiEsteriMin> findAll() throws StatiEsteriMinDaoException;

	/** 
	 * Returns all rows from the STATI_ESTERI_MIN table that match the criteria 'ID_STATO_MINISTERO = :idStatoMinistero'.
	 */
	public List<StatiEsteriMin> findWhereIdStatoMinisteroEquals(long idStatoMinistero) throws StatiEsteriMinDaoException;

	/** 
	 * Returns all rows from the STATI_ESTERI_MIN table that match the criteria 'CONTINENTE = :continente'.
	 */
	public List<StatiEsteriMin> findWhereContinenteEquals(String continente) throws StatiEsteriMinDaoException;

	/** 
	 * Returns all rows from the STATI_ESTERI_MIN table that match the criteria 'STATO = :stato'.
	 */
	public List<StatiEsteriMin> findWhereStatoEquals(String stato) throws StatiEsteriMinDaoException;

	/** 
	 * Returns all rows from the STATI_ESTERI_MIN table that match the criteria 'TERRITORIO = :territorio'.
	 */
	public List<StatiEsteriMin> findWhereTerritorioEquals(String territorio) throws StatiEsteriMinDaoException;

	/** 
	 * Returns all rows from the STATI_ESTERI_MIN table that match the criteria 'CODICE = :codice'.
	 */
	public List<StatiEsteriMin> findWhereCodiceEquals(String codice) throws StatiEsteriMinDaoException;

	/** 
	 * Returns all rows from the STATI_ESTERI_MIN table that match the criteria 'D_START = :dStart'.
	 */
	public List<StatiEsteriMin> findWhereDStartEquals(Date dStart) throws StatiEsteriMinDaoException;

	/** 
	 * Returns all rows from the STATI_ESTERI_MIN table that match the criteria 'D_STOP = :dStop'.
	 */
	public List<StatiEsteriMin> findWhereDStopEquals(Date dStop) throws StatiEsteriMinDaoException;

	/** 
	 * Returns all rows from the STATI_ESTERI_MIN table that match the criteria 'CODRIF = :codrif'.
	 */
	public List<StatiEsteriMin> findWhereCodrifEquals(String codrif) throws StatiEsteriMinDaoException;

	/** 
	 * Returns all rows from the STATI_ESTERI_MIN table that match the criteria 'CODICE_PREV = :codicePrev'.
	 */
	public List<StatiEsteriMin> findWhereCodicePrevEquals(String codicePrev) throws StatiEsteriMinDaoException;

	/** 
	 * Returns all rows from the STATI_ESTERI_MIN table that match the criteria 'CODICE_NEXT = :codiceNext'.
	 */
	public List<StatiEsteriMin> findWhereCodiceNextEquals(String codiceNext) throws StatiEsteriMinDaoException;

	/** 
	 * Returns all rows from the STATI_ESTERI_MIN table that match the criteria 'R_STATUS = :rStatus'.
	 */
	public List<StatiEsteriMin> findWhereRStatusEquals(String rStatus) throws StatiEsteriMinDaoException;

}
