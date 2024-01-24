/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dao.MdpBckusersDao;
import it.csi.mdp.core.business.dto.MdpBckusers;
import it.csi.mdp.core.business.dto.MdpBckusersPk;
import it.csi.mdp.core.business.exceptions.MdpBckusersDaoException;
import java.util.List;

public interface MdpBckusersDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return MdpBckusersPk
	 */
	public MdpBckusersPk insert(MdpBckusers dto);

	/** 
	 * Updates a single row in the mdp_bckusers table.
	 */
	public void update(MdpBckusersPk pk, MdpBckusers dto) throws MdpBckusersDaoException;

	/** 
	 * Deletes a single row in the mdp_bckusers table.
	 */
	public void delete(MdpBckusersPk pk) throws MdpBckusersDaoException;

	/** 
	 * Returns all rows from the mdp_bckusers table that match the criteria 'iduser = :iduser'.
	 */
	public MdpBckusers findByPrimaryKey(int iduser) throws MdpBckusersDaoException;

	/** 
	 * Returns all rows from the mdp_bckusers table that match the criteria ''.
	 */
	public List<MdpBckusers> findAll() throws MdpBckusersDaoException;

	/** 
	 * Returns all rows from the mdp_bckusers table that match the criteria 'firstname = :firstname'.
	 */
	public List<MdpBckusers> findWhereFirstnameEquals(String firstname) throws MdpBckusersDaoException;

	/** 
	 * Returns all rows from the mdp_bckusers table that match the criteria 'lastname = :lastname'.
	 */
	public List<MdpBckusers> findWhereLastnameEquals(String lastname) throws MdpBckusersDaoException;

	/** 
	 * Returns all rows from the mdp_bckusers table that match the criteria 'codfisc = :codfisc'.
	 */
	public List<MdpBckusers> findWhereCodfiscEquals(String codfisc) throws MdpBckusersDaoException;

	/** 
	 * Returns all rows from the mdp_bckusers table that match the criteria 'email = :email'.
	 */
	public List<MdpBckusers> findWhereEmailEquals(String email) throws MdpBckusersDaoException;

	/** 
	 * Returns all rows from the mdp_bckusers table that match the criteria 'iduser = :iduser'.
	 */
	public List<MdpBckusers> findWhereIduserEquals(int iduser) throws MdpBckusersDaoException;

	/** 
	 * Returns all rows from the mdp_bckusers table that match the criteria 'pwdservizio = :pwdservizio'.
	 */
	public List<MdpBckusers> findWherePwdservizioEquals(String pwdservizio) throws MdpBckusersDaoException;

	/** 
	 * Returns the rows from the mdp_bckusers table that matches the specified primary-key value.
	 */
	public MdpBckusers findByPrimaryKey(MdpBckusersPk pk) throws MdpBckusersDaoException;
	
	public void setsKey(String sKey);
	public String getsKey();

}
