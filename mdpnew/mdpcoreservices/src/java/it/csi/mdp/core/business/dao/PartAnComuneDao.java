/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dao.PartAnComuneDao;
import it.csi.mdp.core.business.dto.PartAnComune;
import it.csi.mdp.core.business.exceptions.PartAnComuneDaoException;
import java.util.Date;
import java.util.List;

public interface PartAnComuneDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 */
	public void insert(PartAnComune dto);

	/** 
	 * Returns all rows from the PART_AN_COMUNE table that match the criteria ''.
	 */
	public List<PartAnComune> findAll() throws PartAnComuneDaoException;

	/** 
	 * Returns all rows from the PART_AN_COMUNE table that match the criteria 'ID_COMUNE = :idComune'.
	 */
	public List<PartAnComune> findWhereIdComuneEquals(long idComune) throws PartAnComuneDaoException;

	/** 
	 * Returns all rows from the PART_AN_COMUNE table that match the criteria 'R_STATUS = :rStatus'.
	 */
	public List<PartAnComune> findWhereRStatusEquals(String rStatus) throws PartAnComuneDaoException;

	/** 
	 * Returns all rows from the PART_AN_COMUNE table that match the criteria 'ID_COMUNE_NEXT = :idComuneNext'.
	 */
	public List<PartAnComune> findWhereIdComuneNextEquals(long idComuneNext) throws PartAnComuneDaoException;

	/** 
	 * Returns all rows from the PART_AN_COMUNE table that match the criteria 'ID_COMUNE_PREV = :idComunePrev'.
	 */
	public List<PartAnComune> findWhereIdComunePrevEquals(long idComunePrev) throws PartAnComuneDaoException;

	/** 
	 * Returns all rows from the PART_AN_COMUNE table that match the criteria 'D_START = :dStart'.
	 */
	public List<PartAnComune> findWhereDStartEquals(Date dStart) throws PartAnComuneDaoException;

	/** 
	 * Returns all rows from the PART_AN_COMUNE table that match the criteria 'D_STOP = :dStop'.
	 */
	public List<PartAnComune> findWhereDStopEquals(Date dStop) throws PartAnComuneDaoException;

	/** 
	 * Returns all rows from the PART_AN_COMUNE table that match the criteria 'ISTAT_COMUNE = :istatComune'.
	 */
	public List<PartAnComune> findWhereIstatComuneEquals(String istatComune) throws PartAnComuneDaoException;

	/** 
	 * Returns all rows from the PART_AN_COMUNE table that match the criteria 'DESC_COMUNE = :descComune'.
	 */
	public List<PartAnComune> findWhereDescComuneEquals(String descComune) throws PartAnComuneDaoException;

	/** 
	 * Returns all rows from the PART_AN_COMUNE table that match the criteria 'CAP = :cap'.
	 */
	public List<PartAnComune> findWhereCapEquals(String cap) throws PartAnComuneDaoException;

	/** 
	 * Returns all rows from the PART_AN_COMUNE table that match the criteria 'ISTAT_PROVINCIA = :istatProvincia'.
	 */
	public List<PartAnComune> findWhereIstatProvinciaEquals(String istatProvincia) throws PartAnComuneDaoException;

	/** 
	 * Returns all rows from the PART_AN_COMUNE table that match the criteria 'DESC_PROVINCIA = :descProvincia'.
	 */
	public List<PartAnComune> findWhereDescProvinciaEquals(String descProvincia) throws PartAnComuneDaoException;

	/** 
	 * Returns all rows from the PART_AN_COMUNE table that match the criteria 'SIGLA_PROV = :siglaProv'.
	 */
	public List<PartAnComune> findWhereSiglaProvEquals(String siglaProv) throws PartAnComuneDaoException;

	/** 
	 * Returns all rows from the PART_AN_COMUNE table that match the criteria 'ISTAT_REGIONE = :istatRegione'.
	 */
	public List<PartAnComune> findWhereIstatRegioneEquals(String istatRegione) throws PartAnComuneDaoException;

	/** 
	 * Returns all rows from the PART_AN_COMUNE table that match the criteria 'DESC_REGIONE = :descRegione'.
	 */
	public List<PartAnComune> findWhereDescRegioneEquals(String descRegione) throws PartAnComuneDaoException;

}
