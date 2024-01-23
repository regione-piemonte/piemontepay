/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dao.StatoTransazioneDao;
import it.csi.mdp.core.business.dto.StatoTransazione;
import it.csi.mdp.core.business.dto.StatoTransazionePk;
import it.csi.mdp.core.business.exceptions.StatoTransazioneDaoException;
import java.util.List;

public interface StatoTransazioneDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return StatoTransazionePk
	 */
	public StatoTransazionePk insert(StatoTransazione dto);

	/** 
	 * Updates a single row in the stato_transazione table.
	 */
	public void update(StatoTransazionePk pk, StatoTransazione dto) throws StatoTransazioneDaoException;

	/** 
	 * Deletes a single row in the stato_transazione table.
	 */
	public void delete(StatoTransazionePk pk) throws StatoTransazioneDaoException;

	/** 
	 * Returns all rows from the stato_transazione table that match the criteria 'cod_stato = :codStato'.
	 */
	public StatoTransazione findByPrimaryKey(int codStato) throws StatoTransazioneDaoException;

	/** 
	 * Returns all rows from the stato_transazione table that match the criteria ''.
	 */
	public List<StatoTransazione> findAll() throws StatoTransazioneDaoException;

	/** 
	 * Returns all rows from the stato_transazione table that match the criteria 'descrizione = :descrizione'.
	 */
	public List<StatoTransazione> findWhereDescrizioneEquals(String descrizione) throws StatoTransazioneDaoException;

	/** 
	 * Returns all rows from the stato_transazione table that match the criteria 'cod_stato = :codStato'.
	 */
	public List<StatoTransazione> findWhereCodStatoEquals(int codStato) throws StatoTransazioneDaoException;


	/** 
	 * Returns the rows from the stato_transazione table that matches the specified primary-key value.
	 */
	public StatoTransazione findByPrimaryKey(StatoTransazionePk pk) throws StatoTransazioneDaoException;

}
