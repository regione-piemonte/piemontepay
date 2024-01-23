/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dao.IbanEnteApplicationDao;
import it.csi.mdp.core.business.dto.IbanEnteApplication;
import it.csi.mdp.core.business.exceptions.DaoException;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface IbanEnteApplicationDao{
	public List<IbanEnteApplication> getIbanEnteApplicationByParam(
																	  Integer id,
																	  String applicationId,
																	  String idEnte,
																	  String tipoversamento,
																	  String identificativopsp,
																	  String iban,
																	  Date dataInizioValidita,
																	  Date dataFineValidita,
																	  String attivo) throws DaoException;
	
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 */
	public void insertIbanEnteApplication(
			  String applicationId,
			  String idEnte,
			  String tipoversamento,
			  String identificativopsp,
			  String iban,
			  Date dataInizioValidita,
			  Date dataFineValidita,
			  String attivo) throws DaoException;
	
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 */
	public void updateIbanEnteApplication(Integer id,
			  String applicationId,
			  String idEnte,
			  String tipoversamento,
			  String identificativopsp,
			  String iban,
			  Date dataInizioValidita,
			  Date dataFineValidita,
			  String attivo) throws DaoException;

}
