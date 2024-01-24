/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dto.Enti;
import it.csi.mdp.core.business.exceptions.DaoException;

import java.util.List;

public interface EntiDao
{

	public void insertEnte(String enteId ,
			String partitaIva, 
			String descrizione,String attivo) throws DaoException;

	public void updateEnte(String enteId ,
			String partitaIva, 
			String descrizione,String attivo) throws DaoException;
	
	public void deleteEnte(String enteId) throws DaoException;
	
	public List<Enti> findEntiAll() throws DaoException;
	
	public List<Enti> getEntiByParam( String enteId ,
									String partitaIva, 
									String descrizione,
									String attivo) throws DaoException;
	
	
	public List<Enti> getEntiByApplicationId( String idApplicazione) throws DaoException;

	public Integer insRelEnteApplication( String idApplicazione,String enteId) throws DaoException;

	public Integer delRelEnteApplication( String idApplicazione,String enteId) throws DaoException;

}
