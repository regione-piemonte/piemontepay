/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.dao.interf;

import java.sql.Timestamp;
import java.util.List;

import it.csi.epay.epaywsosrv.exception.PersistenceException;
import it.csi.epay.epaywsosrv.persistence.dao.EPaywsoDaoBase;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoTCodiceVersamento;

//@formatter:off
public interface EPaywsoTCodiceVersamentoDao extends EPaywsoDaoBase<Integer, EPaywsoTCodiceVersamento> {

	public String findCodById(Integer id, Timestamp timestamp) throws PersistenceException;
	public List<Integer> findAllIdByCod(String cod, Timestamp timestamp) throws PersistenceException;

	public EPaywsoTCodiceVersamento findOneById(Integer id, Timestamp timestamp) throws PersistenceException;
	public List<EPaywsoTCodiceVersamento> findAllByCod(String cod, Timestamp timestamp) throws PersistenceException;
	
	//--ADDED
    public EPaywsoTCodiceVersamento findOneByCodAndEnte(String codVersamento, Integer idEnte) throws PersistenceException;
    public List<EPaywsoTCodiceVersamento> findAllByCodAndEnteAttivo(String codVersamento, Integer idEnte) throws PersistenceException;
    public EPaywsoTCodiceVersamento findOneByCodAndEnteAttivo(String codVersamento, Integer idEnte, Timestamp now) throws PersistenceException;
    
    public List<EPaywsoTCodiceVersamento> findAllByIdEnteAndCodiceVersamento ( Integer idEnte, String codiceVersamento ) throws PersistenceException;
    public List<EPaywsoTCodiceVersamento> findByIdEnte ( Integer idEnte ) throws PersistenceException;
    public int findCodVersIdMax () throws PersistenceException;
}
//@formatter:on
