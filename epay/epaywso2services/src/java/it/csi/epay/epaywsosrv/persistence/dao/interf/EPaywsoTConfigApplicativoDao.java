/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.dao.interf;

import java.sql.Timestamp;
import java.util.List;

import it.csi.epay.epaywsosrv.exception.PersistenceException;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoTConfigApplicativo;

//@formatter:off
public interface EPaywsoTConfigApplicativoDao {

	public List<EPaywsoTConfigApplicativo> findAllByParams(String codFiscaleEnte, List<String> codVersamentoList, Integer integer, Timestamp timestamp) throws PersistenceException;

}
//@formatter:on
