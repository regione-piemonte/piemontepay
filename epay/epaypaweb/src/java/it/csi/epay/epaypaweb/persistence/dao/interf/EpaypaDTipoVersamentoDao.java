/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.interf;

import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBase;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaDTipoVersamento;

//@formatter:off
public interface EpaypaDTipoVersamentoDao extends EpaypaDaoBase<Integer, EpaypaDTipoVersamento> {

	public EpaypaDTipoVersamento findOneByCod(String cod) throws PersistenceException;

}
//@formatter:on
