/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dao.ApplicationDao;
import it.csi.mdp.core.business.dto.Application;
import it.csi.mdp.core.business.dto.ApplicationPk;
import it.csi.mdp.core.business.exceptions.ApplicationDaoException;
import java.util.Date;
import java.util.List;

public interface ApplicationDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 */
	public ApplicationPk insert(Application dto);

	public void update(ApplicationPk pk, Application dto) throws ApplicationDaoException;
	
	public void delete(ApplicationPk pk) throws ApplicationDaoException;
	/** 
	 * Returns all rows from the APPLICATION table that match the criteria ''.
	 */
	public List<Application> findAll(String idgroup) throws ApplicationDaoException;

	/** 
	 * Returns all rows from the APPLICATION table that match the criteria 'ID = :id'.
	 */
	public List<Application> findWhereIdEquals(String id,String idgroup) throws ApplicationDaoException;

	public void deleteApplicationConfiguration(ApplicationPk pk) throws ApplicationDaoException;
	
	public List<Application> listApplicationByFlussoApplicazione() throws ApplicationDaoException;


}
