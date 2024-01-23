/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dao.ApplicationcustomfieldsDao;
import it.csi.mdp.core.business.dto.Applicationcustomfields;
import it.csi.mdp.core.business.exceptions.ApplicationcustomfieldsDaoException;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public interface ApplicationcustomfieldsDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 */
	public void insert(Applicationcustomfields dto);
	/**
	 * Method 'update'
	 * 
	 * @param dto
	 */
	
	public void update(Applicationcustomfields dto);
	/**
	 * Method 'update'
	 * 
	 * @param dto
	 */
	
	public void delete(String appid, String gwid);
	
	/** 
	 * Returns all rows from the APPLICATIONCUSTOMFIELDS table that match the criteria ''.
	 */
	public List<Applicationcustomfields> findAll() throws ApplicationcustomfieldsDaoException;

	/** 
	 * Returns all rows from the APPLICATIONCUSTOMFIELDS table that match the criteria 'KEYID = :keyid'.
	 */
	public List<Applicationcustomfields> findWhereKeyidEquals(long keyid) throws ApplicationcustomfieldsDaoException;

	/** 
	 * Returns all rows from the APPLICATIONCUSTOMFIELDS table that match the criteria 'APPLICATIONID = :applicationid'.
	 */
	public List<Applicationcustomfields> findWhereApplicationidEquals(String applicationid) throws ApplicationcustomfieldsDaoException;

	/** 
	 * Returns all rows from the APPLICATIONCUSTOMFIELDS table that match the criteria 'FIELDNAME = :fieldname'.
	 */
	public List<Applicationcustomfields> findWhereFieldnameEquals(String fieldname) throws ApplicationcustomfieldsDaoException;


	/** 
	 * Returns all rows from the APPLICATIONCUSTOMFIELDS table that match the criteria 'APPLICATIONID = :applicationid AND FIELDNAME = :fieldname'.
	 */

	public List<Applicationcustomfields> findWhereApplicationidAndFieldNameEquals(String applicationid, String fieldname) throws ApplicationcustomfieldsDaoException;
	
	public List<Applicationcustomfields> findWhereApplicationidAndGatewayIdEquals(String applicationid, String gatewayid) throws ApplicationcustomfieldsDaoException;
	public List<Applicationcustomfields> findWhereApplicationidAndGatewayIdAndFieldNameEquals(String applicationid, String gatewayid, String fieldname)
	throws ApplicationcustomfieldsDaoException;
	
	public void setsKey(String sKey);
	public String getsKey();
	
	public List<Applicationcustomfields> findDistinctDomini() throws ApplicationcustomfieldsDaoException;
}
