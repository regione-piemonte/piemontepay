/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.dao.interf;

import java.sql.Timestamp;
import java.util.List;

import it.csi.epay.epaywsosrv.exception.PersistenceException;
import it.csi.epay.epaywsosrv.persistence.dao.EPaywsoDaoBase;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoTEnte;

//@formatter:off
public interface EPaywsoTEnteDao extends EPaywsoDaoBase<Integer, EPaywsoTEnte> {

	public String  findCodFiscaleById(Integer id, Timestamp timestamp) throws PersistenceException;
	public Integer findIdByCodFiscale(String codFiscale, Timestamp timestamp) throws PersistenceException;
	
	public EPaywsoTEnte findOneById(Integer id, Timestamp timestamp) throws PersistenceException;
	public EPaywsoTEnte findOneByCodFiscale(String codFiscale, Timestamp timestamp) throws PersistenceException;

	//--ADDED
    public Integer findIdEnteByCodFiscale(String codFiscaleEnte) throws PersistenceException;
    
    public String  findCodFiscaleEnteById(Integer idEnte) throws PersistenceException;

    public EPaywsoTEnte findOneByCodFiscale(String codFiscaleEnte) throws PersistenceException;

    public List<EPaywsoTEnte> findAllByCodUtente(String codUtente) throws PersistenceException;

}

//@formatter:on
