/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.interf;

import java.util.List;

import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBase;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTEnte;

//@formatter:off
public interface EpaypaTEnteDao extends EpaypaDaoBase<Integer, EpaypaTEnte> {

	public String  findCodFiscaleEnteById(Integer idEnte) throws PersistenceException;
	public Integer findIdEnteByCodFiscale(String codFiscaleEnte) throws PersistenceException;

	public EpaypaTEnte findOneByCodFiscale(String codFiscaleEnte) throws PersistenceException;

	public List<EpaypaTEnte> findAllByCodUtente(String codUtente) throws PersistenceException;

}
//@formatter:on
