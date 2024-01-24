/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.interf;

import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBase;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaDTipoPagamento;

//@formatter:off
public interface EpaypaDTipoPagamentoDao extends EpaypaDaoBase<Integer, EpaypaDTipoPagamento> {

    public EpaypaDTipoPagamento findOneByCodice ( String cod ) throws PersistenceException;

}
//@formatter:on
