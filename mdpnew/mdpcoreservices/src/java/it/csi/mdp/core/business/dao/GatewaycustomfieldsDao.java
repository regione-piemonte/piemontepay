/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dao.GatewaycustomfieldsDao;

import it.csi.mdp.core.business.dto.Gatewaycustomfields;

import it.csi.mdp.core.business.exceptions.GatewaycustomfieldsDaoException;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public interface GatewaycustomfieldsDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 */
	public void insert(Gatewaycustomfields dto);
	/**
	 * Method 'update'
	 * 
	 * @param dto
	 */
	
	public void update(Gatewaycustomfields dto);
	
	/**
	 * Method 'delete'
	 * 
	 * @param dto
	 */
	
	public void delete(String gwid) throws GatewaycustomfieldsDaoException;
	/** 
	 * Returns all rows from the APPLICATIONCUSTOMFIELDS table that match the criteria ''.
	 */
	public List<Gatewaycustomfields> findAll() throws GatewaycustomfieldsDaoException;


	/** 
	 * Returns all rows from the APPLICATIONCUSTOMFIELDS table that match the criteria 'FIELDNAME = :fieldname'.
	 */
	public List<Gatewaycustomfields> findWhereGatewayidEquals(String gwid)  throws GatewaycustomfieldsDaoException;
	public List<Gatewaycustomfields> findWhereGatewayidFieldnameEquals(String gwid, String fieldname)  throws GatewaycustomfieldsDaoException;




	
}
